package il.co.edp.nam.filters;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import il.co.edp.nam.configs.oauthAuthorizationFilterConfig.ClientScopes;
import il.co.edp.nam.configs.oauthAuthorizationFilterConfig.OAuthFilterConfig;
import il.co.edp.nam.utils.ConfigUtils;

public class OAuthScopesFilter implements Filter {

	private final Log log = LogFactory.getLog(OAuthScopesFilter.class); // must
																		// not
																		// be
																		// static
	private OAuthFilterConfig config;

	public static final String FILTER_CONFIG_PATH = "oauth.filter.config.path";
	public static final String DEFAULT_FILTER_CONFIG_PATH = "/etc/edp/config/oauth_filter.xml";

	public static final String CLIENT_ID_PARAM_NAME = "client_id";
	public static final String SCOPES_PARAM_NAME = "scope";

	private Thread configFileWatchingThread;

	private void loadConfigFromFile(String configFilePath) {

		try {
			log.info("Parsing config file from " + configFilePath);
			// getting the xml file to read
			File file = new File(configFilePath);
			log.debug("Unmarshall xml file to config object");
			// creating the JAXB context
			JAXBContext jContext = JAXBContext.newInstance(OAuthFilterConfig.class);
			// creating the unmarshall object
			Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
			// calling the unmarshall method
			config = (OAuthFilterConfig) unmarshallerObj.unmarshal(file);
		} catch (Exception e) {
			log.error("Got an error while trying to parse oauth filter config file", e);
			config = new OAuthFilterConfig();
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		final String configFilePath = ConfigUtils.getInitParameter(filterConfig, FILTER_CONFIG_PATH,
				DEFAULT_FILTER_CONFIG_PATH);
		loadConfigFromFile(configFilePath);
		Path _configFilePath = Paths.get(configFilePath);
		final Path configFileDir = _configFilePath.getParent();
		final String configFileName = _configFilePath.getFileName().toString();
		configFileWatchingThread = new Thread() {
			public void run() {
				try {
					while (true) {
						WatchService watcher = configFileDir.getFileSystem().newWatchService();
						configFileDir.register(watcher, ENTRY_MODIFY);
						WatchKey watckKey = watcher.take();
						List<WatchEvent<?>> events = watckKey.pollEvents();
						for (WatchEvent event : events) {
							if (event.kind() == ENTRY_MODIFY) {
								String entryName = event.context().toString();								
								log.debug(String.format("File system modified. File is: %s", entryName));
								if (configFileName.equals(entryName)) {
									log.info(String.format("Config file changed - reloading it"));
									loadConfigFromFile(configFilePath);
								}
							}
						}
						watcher = null;
					}

				} catch (Exception e) {
					log.error("Got an exception on config file watching", e);
				}
			};
		};
		configFileWatchingThread.start();
	}

	@Override
	public void destroy() {
		log.debug("OAUTH filter is going to be destroyed");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		String clientID = req.getParameter(CLIENT_ID_PARAM_NAME);
		String scope = req.getParameter(SCOPES_PARAM_NAME);

		if (scope != null && clientID != null) {
			Pattern pattern = Pattern.compile("([^+\\s]+)");
			Matcher m = pattern.matcher(scope);
			while (m.find()) {
				String s = m.group();
				if (config.getPublicScopes().getScope().contains(s)) {
					log.debug(String.format("Scope %s is allowed - it is public", s));
				} else {
					boolean scopeAllowed = false;
					log.debug(String.format("Scope %s is not public allowed. Check if it is allowed for client %s", s,
							clientID));
					for (ClientScopes cs : config.getClientSpecificScopes().getClientScopes()) {
						if (clientID.equals(cs.getClientID())) {
							if (cs.getScopes().getScope().contains(s)) {
								log.debug(String.format("Scope %s is allowed for scope %s", s, clientID));
								scopeAllowed = true;
								continue;
							} else {
								log.info(String.format(
										"Client %s asks for scope %s but scope is not allowed for this client",
										clientID, s));
								break;
							}
						}
					}
					if (!scopeAllowed) {
						((HttpServletResponse) resp).sendError(HttpServletResponse.SC_UNAUTHORIZED,
								String.format("Scope %s is not allowed", s));
						fc.doFilter(req, resp);
						return;
					}
				}
			}
		}
		fc.doFilter(req, resp);

	}

}
