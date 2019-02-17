package il.co.edp.nam.configs.oauthAuthorizationFilterConfig;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _Config_QNAME = new QName("http://edp.co.il/oauth_authorization_filter/", "config");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: il.co.edp.nam.configs.oauthAuthorizationFilterConfig
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OAuthFilterConfig }
     * 
     */
    public OAuthFilterConfig createOAuthFilterConfig() {
        return new OAuthFilterConfig();
    }

    /**
     * Create an instance of {@link ClientScopes }
     * 
     */
    public ClientScopes createClientScopes() {
        return new ClientScopes();
    }

    /**
     * Create an instance of {@link ClientSpecificScopes }
     * 
     */
    public ClientSpecificScopes createClientSpecificScopes() {
        return new ClientSpecificScopes();
    }

    /**
     * Create an instance of {@link Scopes }
     * 
     */
    public Scopes createScopes() {
        return new Scopes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OAuthFilterConfig }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://edp.co.il/oauth_authorization_filter/", name = "config")
    public JAXBElement<OAuthFilterConfig> createConfig(OAuthFilterConfig value) {
        return new JAXBElement<OAuthFilterConfig>(_Config_QNAME, OAuthFilterConfig.class, null, value);
    }

}
