package mx.uv.dependencia;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class DependenciaConf extends WsConfigurerAdapter{
    @Bean
    public XsdSchema dependenciaSchema(){
        return new SimpleXsdSchema(new ClassPathResource("dependencia.xsd"));
    }
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServler(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }
    @Bean(name="dependencia")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema dependenciaSchema){
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("dependenciaPort");
        wsdl.setLocationUri("/ws");
        wsdl.setTargetNamespace("t4is.uv.mx/dependencia");
        wsdl.setSchema(dependenciaSchema);
        return wsdl;
    }
}