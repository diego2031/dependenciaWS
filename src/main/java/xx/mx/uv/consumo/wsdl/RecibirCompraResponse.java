
package xx.mx.uv.consumo.wsdl;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="totalCompra" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="folio_seguimiento" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "totalCompra",
    "folioSeguimiento"
})
@XmlRootElement(name = "RecibirCompraResponse")
public class RecibirCompraResponse {

    @XmlElement(required = true)
    protected String totalCompra;
    @XmlElement(name = "folio_seguimiento", required = true)
    protected String folioSeguimiento;

    /**
     * Obtiene el valor de la propiedad totalCompra.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalCompra() {
        return totalCompra;
    }

    /**
     * Define el valor de la propiedad totalCompra.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalCompra(String value) {
        this.totalCompra = value;
    }

    /**
     * Obtiene el valor de la propiedad folioSeguimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioSeguimiento() {
        return folioSeguimiento;
    }

    /**
     * Define el valor de la propiedad folioSeguimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioSeguimiento(String value) {
        this.folioSeguimiento = value;
    }

}
