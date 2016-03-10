package subjectclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the subjectclient package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _GetSubjectById_QNAME = new QName(
			"http://service/", "getSubjectById");
	private final static QName _GetSubjectByIdResponse_QNAME = new QName(
			"http://service/", "getSubjectByIdResponse");
	private final static QName _Exception_QNAME = new QName("http://service/",
			"Exception");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: subjectclient
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link GetSubjectByIdResponse }
	 * 
	 */
	public GetSubjectByIdResponse createGetSubjectByIdResponse() {
		return new GetSubjectByIdResponse();
	}

	/**
	 * Create an instance of {@link Exception }
	 * 
	 */
	public Exception createException() {
		return new Exception();
	}

	/**
	 * Create an instance of {@link GetSubjectById }
	 * 
	 */
	public GetSubjectById createGetSubjectById() {
		return new GetSubjectById();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetSubjectById }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service/", name = "getSubjectById")
	public JAXBElement<GetSubjectById> createGetSubjectById(GetSubjectById value) {
		return new JAXBElement<GetSubjectById>(_GetSubjectById_QNAME,
				GetSubjectById.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link GetSubjectByIdResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service/", name = "getSubjectByIdResponse")
	public JAXBElement<GetSubjectByIdResponse> createGetSubjectByIdResponse(
			GetSubjectByIdResponse value) {
		return new JAXBElement<GetSubjectByIdResponse>(
				_GetSubjectByIdResponse_QNAME, GetSubjectByIdResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Exception }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service/", name = "Exception")
	public JAXBElement<Exception> createException(Exception value) {
		return new JAXBElement<Exception>(_Exception_QNAME, Exception.class,
				null, value);
	}

}
