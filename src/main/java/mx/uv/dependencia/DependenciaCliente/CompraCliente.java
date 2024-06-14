package mx.uv.dependencia.DependenciaCliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import xx.mx.uv.consumo.wsdl.EstadoCompraRequest;
import xx.mx.uv.consumo.wsdl.EstadoCompraResponse;
import xx.mx.uv.consumo.wsdl.RecibirCompraRequest;
import xx.mx.uv.consumo.wsdl.RecibirCompraResponse;

public class CompraCliente extends WebServiceGatewaySupport{

    @Autowired
    private Jaxb2Marshaller marshallerCompra;

    public RecibirCompraResponse solicitarCompra(RecibirCompraRequest request){
        try{
            return (RecibirCompraResponse) getWebServiceTemplate()
            .marshalSendAndReceive(request, new SoapActionCallback("https://compras-production-8e4a.up.railway.app/ws/compras"));
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            return null;
        } 
    }

    public EstadoCompraResponse solicitarSeguimiento(EstadoCompraRequest request){
        try{
            return (EstadoCompraResponse) getWebServiceTemplate()
            .marshalSendAndReceive(request, new SoapActionCallback("https://compras-production-8e4a.up.railway.app/ws/compras"));
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            return null;
        } 
    }


}
