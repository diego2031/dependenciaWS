package mx.uv.dependencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import mx.uv.dependencia.DependenciaCliente.CompraCliente;
import mx.uv.t4is.dependencia.SolicitarCompraRequest;
import mx.uv.t4is.dependencia.SolicitarCompraResponse;
import mx.uv.t4is.dependencia.SolicitarSeguimientoRequest;
import mx.uv.t4is.dependencia.SolicitarSeguimientoResponse;
import xx.mx.uv.consumo.wsdl.EstadoCompraRequest;
import xx.mx.uv.consumo.wsdl.EstadoCompraResponse;
import xx.mx.uv.consumo.wsdl.RecibirCompraRequest;
import xx.mx.uv.consumo.wsdl.RecibirCompraResponse;

@Endpoint
public class DependenciaEndPoint {

  @Autowired
  private IDependencia iDependencias;

  @Autowired
  private CompraCliente compraCliente;

  @PayloadRoot(localPart = "SolicitarCompraRequest", namespace = "t4is.uv.mx/dependencia")
  @ResponsePayload
  public SolicitarCompraResponse solicitarCompra(@RequestPayload SolicitarCompraRequest peticion) {
  
    SolicitarCompraResponse respuesta = new SolicitarCompraResponse();
    SolicitarCompraRequest solicitar = new SolicitarCompraRequest();
    
    RecibirCompraRequest recibir = new RecibirCompraRequest();
  
    solicitar.setNombreCliente(peticion.getNombreCliente());
    solicitar.setEmail(peticion.getEmail());
    solicitar.setDireccion(peticion.getDireccion());
    solicitar.setRfc(peticion.getRfc());
    solicitar.setNomProducto(peticion.getNomProducto());
    solicitar.setCantidad(peticion.getCantidad());
    solicitar.setPrecio(peticion.getPrecio());

    recibir.setNombreCliente(peticion.getNombreCliente());
    recibir.setEmail(peticion.getEmail());
    recibir.setDireccion(peticion.getDireccion());
    recibir.setRfc(peticion.getRfc());
    recibir.setNombreProducto(peticion.getNomProducto());
    recibir.setCantidadProducto(peticion.getCantidad());
    recibir.setPrecioProducto(peticion.getPrecio());

    //respuesta.setFolioCompra("respuesta");
    RecibirCompraResponse respuestaCompra = compraCliente.solicitarCompra(recibir);
    System.out.println(respuestaCompra.getFolioSeguimiento());

    double total=0;
    total = recibir.getCantidadProducto() * recibir.getPrecioProducto();
    String totalF = String.format("%.2f", total);

    respuesta.setTotalCompra("El total de la compra es: "+totalF);
    respuesta.setFolioCompra("El folio de seguimiento de tu compra es: " + respuestaCompra.getFolioSeguimiento());

    Dependencias dependencias= new Dependencias();
    dependencias.setNombreCliente(peticion.getNombreCliente());
    dependencias.setCorreoElectronico(peticion.getEmail());
    dependencias.setFolio(respuesta.getFolioCompra());
    
    iDependencias.save(dependencias);
    return respuesta;
  }

  @PayloadRoot(localPart = "SolicitarSeguimientoRequest", namespace = "t4is.uv.mx/dependencia")
  @ResponsePayload
  public SolicitarSeguimientoResponse solicitarSeguimiento(@RequestPayload SolicitarSeguimientoRequest peticion) {
    SolicitarSeguimientoResponse respuesta = new SolicitarSeguimientoResponse();
    SolicitarSeguimientoRequest seguimiento = new SolicitarSeguimientoRequest();
    EstadoCompraRequest estado = new EstadoCompraRequest();
    
    String resultado = peticion.getFolioSeguimiento().toString();

    seguimiento.setFolioSeguimiento(peticion.getFolioSeguimiento());
    estado.setFolio(peticion.getFolioSeguimiento());

    EstadoCompraResponse respuestaEstado = compraCliente.solicitarSeguimiento(estado);

    respuesta.setRespuesta(respuestaEstado.getRespuesta());
    return respuesta;

  }
}
