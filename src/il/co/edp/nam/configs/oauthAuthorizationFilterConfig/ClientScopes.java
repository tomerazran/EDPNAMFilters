package il.co.edp.nam.configs.oauthAuthorizationFilterConfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clientScopes", propOrder = {
    "clientID",
    "scopes"
})
public class ClientScopes {

    @XmlElement(required = true)
    protected String clientID;
    @XmlElement(required = true)
    protected Scopes scopes;

    /**
     * Gets the value of the clientID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientID() {
        return clientID;
    }

    /**
     * Sets the value of the clientID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientID(String value) {
        this.clientID = value;
    }

    /**
     * Gets the value of the scopes property.
     * 
     * @return
     *     possible object is
     *     {@link Scopes }
     *     
     */
    public Scopes getScopes() {
        return scopes;
    }

    /**
     * Sets the value of the scopes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Scopes }
     *     
     */
    public void setScopes(Scopes value) {
        this.scopes = value;
    }

}
