package il.co.edp.nam.configs.oauthAuthorizationFilterConfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oAuthFilterConfig", propOrder = {
    "publicScopes",
    "clientSpecificScopes"
})
@XmlRootElement(name="config")
public class OAuthFilterConfig {

    protected Scopes publicScopes;
    protected ClientSpecificScopes clientSpecificScopes;

    /**
     * Gets the value of the publicScopes property.
     * 
     * @return
     *     possible object is
     *     {@link Scopes }
     *     
     */
    public Scopes getPublicScopes() {
        return publicScopes;
    }

    /**
     * Sets the value of the publicScopes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Scopes }
     *     
     */
    public void setPublicScopes(Scopes value) {
        this.publicScopes = value;
    }

    /**
     * Gets the value of the clientSpecificScopes property.
     * 
     * @return
     *     possible object is
     *     {@link ClientSpecificScopes }
     *     
     */
    public ClientSpecificScopes getClientSpecificScopes() {
        return clientSpecificScopes;
    }

    /**
     * Sets the value of the clientSpecificScopes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientSpecificScopes }
     *     
     */
    public void setClientSpecificScopes(ClientSpecificScopes value) {
        this.clientSpecificScopes = value;
    }

}
