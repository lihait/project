package subjectclient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * <p>
 * An example of how this class may be used:
 * 
 * <pre>
 * getSubjectByIdService service = new getSubjectByIdService();
 * GetSubjectByIdDelegate portType = service.getGetSubjectByIdPort();
 * portType.getSubjectById(...);
 * </pre>
 * 
 * </p>
 * 
 */
@WebServiceClient(name = "getSubjectByIdService", targetNamespace = "http://service/", wsdlLocation = "file:/D:/Workspaces/MyEclipse%20Professional/mySubjectWeb/WebRoot/WEB-INF/wsdl/getSubjectByIdService.wsdl")
public class GetSubjectByIdService extends Service {

	private final static URL GETSUBJECTBYIDSERVICE_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(subjectclient.GetSubjectByIdService.class.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = subjectclient.GetSubjectByIdService.class
					.getResource(".");
			url = new URL(
					baseUrl,
					"file:/D:/Workspaces/MyEclipse%20Professional/mySubjectWeb/WebRoot/WEB-INF/wsdl/getSubjectByIdService.wsdl");
		} catch (MalformedURLException e) {
			logger.warning("Failed to create URL for the wsdl Location: 'file:/D:/Workspaces/MyEclipse%20Professional/mySubjectWeb/WebRoot/WEB-INF/wsdl/getSubjectByIdService.wsdl', retrying as a local file");
			logger.warning(e.getMessage());
		}
		GETSUBJECTBYIDSERVICE_WSDL_LOCATION = url;
	}

	public GetSubjectByIdService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public GetSubjectByIdService() {
		super(GETSUBJECTBYIDSERVICE_WSDL_LOCATION, new QName("http://service/",
				"getSubjectByIdService"));
	}

	/**
	 * 
	 * @return returns GetSubjectByIdDelegate
	 */
	@WebEndpoint(name = "getSubjectByIdPort")
	public GetSubjectByIdDelegate getGetSubjectByIdPort() {
		return super.getPort(
				new QName("http://service/", "getSubjectByIdPort"),
				GetSubjectByIdDelegate.class);
	}

}
