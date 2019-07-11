package proyecto.maven.MavenProyect;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.NodeList;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/darUnEmpleadoAlTunel")
    public Empleados pedirUnEmpleado(@RequestParam(value="name", defaultValue="World") String name) {

    	
    	try {
//        System.out.println(String.format(template, name));
//    	return new Empleados(counter.incrementAndGet(), "$1,300,000", "David");
//		URL yahoo = new URL("http://localhost:8080/darDatos");
//        URLConnection yc = yahoo.openConnection();
//        BufferedReader in = new BufferedReader(
//                                new InputStreamReader(
//                                yc.getInputStream()));
//        String inputLine;
//
//        while ((inputLine = in.readLine()) != null)
//            System.out.println(inputLine);
//        in.close();
	URL url;
	
		url = new URL("http://localhost:7800/data_msSOAP_HTTP_Service");
	
	HttpURLConnection con = (HttpURLConnection) url.openConnection();
	con.setRequestMethod("POST");
//	con.setDoOutput(true);
//	DataOutputStream out = new DataOutputStream(con.getOutputStream());
//	con.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");		
	String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:data=\"http://tempuri.org/data_ms\">"+
	  " <soapenv:Header/>"+
	 "  <soapenv:Body>"+
	   "   <data:requestData>"+
	  "       <data:id_empleado>?</data:id_empleado>"+
	  "    </data:requestData>"+
	 "  </soapenv:Body>"+
	"</soapenv:Envelope>";
	 con.setDoOutput(true);
	 DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	 wr.writeBytes(xml);
	 wr.flush();
	 wr.close();
	 String responseStatus = con.getResponseMessage();
	 System.out.println(responseStatus);
	 BufferedReader in = new BufferedReader(new InputStreamReader(
	 con.getInputStream()));
	 String inputLine;
	 StringBuffer response = new StringBuffer();
	 while ((inputLine = in.readLine()) != null) {
	 response.append(inputLine);
	 
	 }
	 MessageFactory factory = MessageFactory.newInstance();
	    SOAPMessage message = factory.createMessage(
	    new MimeHeaders(),
	    new ByteArrayInputStream(response.toString().getBytes(Charset.forName("UTF-8"))));

	    SOAPBody body = message.getSOAPBody();
	    System.out.println(body.toString());

	    NodeList list = body.getElementsByTagName("data:responseData");

	    NodeList innerList = list.item(0).getChildNodes();

	    // DATOS NOMBRE
		System.out.println(innerList.item(1).getNodeName());
		System.out.println(innerList.item(1).getTextContent());

		// DATOS SALARIO
		System.out.println(innerList.item(3).getNodeName());
		System.out.println(innerList.item(3).getTextContent());
	 in.close();
	 System.out.println("response:" + response.toString());
	 
	 return new Empleados(counter.incrementAndGet(), innerList.item(3).getTextContent(), innerList.item(1).getTextContent());

    	} catch (MalformedURLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		return null;
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    		return null;
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    		return null;
		} 
	 
    }
    
    @GetMapping("/darUnEmpleadoAAngular")
    public Empleados greeting(@RequestParam(value="name", defaultValue="World") List<String> params) {
    	return new Empleados(counter.incrementAndGet(), "$1,300,000", "David");
    }
}