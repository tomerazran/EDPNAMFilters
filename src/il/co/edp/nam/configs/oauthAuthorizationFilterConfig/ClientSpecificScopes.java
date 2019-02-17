package il.co.edp.nam.configs.oauthAuthorizationFilterConfig;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clientSpecificScopes", propOrder = {
    "clientScopes"
})
public class ClientSpecificScopes {

    protected List<ClientScopes> clientScopes;

    /**
     * Gets the value of the clientScopes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clientScopes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClientScopes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClientScopes }
     * 
     * 
     */
    public List<ClientScopes> getClientScopes() {
        if (clientScopes == null) {
            clientScopes = new ArrayList<ClientScopes>();
        }
        return this.clientScopes;
    }

}
