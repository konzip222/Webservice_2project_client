
package org.jg.rsi;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.jg.rsi package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ModifyEventResponse_QNAME = new QName("http://rsi.jg.org/", "modifyEventResponse");
    private final static QName _ModifyEvent_QNAME = new QName("http://rsi.jg.org/", "modifyEvent");
    private final static QName _GetEventsOfDayResponse_QNAME = new QName("http://rsi.jg.org/", "getEventsOfDayResponse");
    private final static QName _GetEventsOfWeekResponse_QNAME = new QName("http://rsi.jg.org/", "getEventsOfWeekResponse");
    private final static QName _GetEventsOfDay_QNAME = new QName("http://rsi.jg.org/", "getEventsOfDay");
    private final static QName _AddEvent_QNAME = new QName("http://rsi.jg.org/", "addEvent");
    private final static QName _GetImageByNameResponse_QNAME = new QName("http://rsi.jg.org/", "getImageByNameResponse");
    private final static QName _AddEventResponse_QNAME = new QName("http://rsi.jg.org/", "addEventResponse");
    private final static QName _GetEventsOfWeek_QNAME = new QName("http://rsi.jg.org/", "getEventsOfWeek");
    private final static QName _GetImageByName_QNAME = new QName("http://rsi.jg.org/", "getImageByName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.jg.rsi
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddEvent }
     * 
     */
    public AddEvent createAddEvent() {
        return new AddEvent();
    }

    /**
     * Create an instance of {@link GetImageByNameResponse }
     * 
     */
    public GetImageByNameResponse createGetImageByNameResponse() {
        return new GetImageByNameResponse();
    }

    /**
     * Create an instance of {@link GetEventsOfDay }
     * 
     */
    public GetEventsOfDay createGetEventsOfDay() {
        return new GetEventsOfDay();
    }

    /**
     * Create an instance of {@link GetEventsOfWeekResponse }
     * 
     */
    public GetEventsOfWeekResponse createGetEventsOfWeekResponse() {
        return new GetEventsOfWeekResponse();
    }

    /**
     * Create an instance of {@link GetEventsOfWeek }
     * 
     */
    public GetEventsOfWeek createGetEventsOfWeek() {
        return new GetEventsOfWeek();
    }

    /**
     * Create an instance of {@link GetImageByName }
     * 
     */
    public GetImageByName createGetImageByName() {
        return new GetImageByName();
    }

    /**
     * Create an instance of {@link AddEventResponse }
     * 
     */
    public AddEventResponse createAddEventResponse() {
        return new AddEventResponse();
    }

    /**
     * Create an instance of {@link ModifyEvent }
     * 
     */
    public ModifyEvent createModifyEvent() {
        return new ModifyEvent();
    }

    /**
     * Create an instance of {@link ModifyEventResponse }
     * 
     */
    public ModifyEventResponse createModifyEventResponse() {
        return new ModifyEventResponse();
    }

    /**
     * Create an instance of {@link GetEventsOfDayResponse }
     * 
     */
    public GetEventsOfDayResponse createGetEventsOfDayResponse() {
        return new GetEventsOfDayResponse();
    }

    /**
     * Create an instance of {@link Events }
     * 
     */
    public Events createEvents() {
        return new Events();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyEventResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rsi.jg.org/", name = "modifyEventResponse")
    public JAXBElement<ModifyEventResponse> createModifyEventResponse(ModifyEventResponse value) {
        return new JAXBElement<ModifyEventResponse>(_ModifyEventResponse_QNAME, ModifyEventResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rsi.jg.org/", name = "modifyEvent")
    public JAXBElement<ModifyEvent> createModifyEvent(ModifyEvent value) {
        return new JAXBElement<ModifyEvent>(_ModifyEvent_QNAME, ModifyEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEventsOfDayResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rsi.jg.org/", name = "getEventsOfDayResponse")
    public JAXBElement<GetEventsOfDayResponse> createGetEventsOfDayResponse(GetEventsOfDayResponse value) {
        return new JAXBElement<GetEventsOfDayResponse>(_GetEventsOfDayResponse_QNAME, GetEventsOfDayResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEventsOfWeekResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rsi.jg.org/", name = "getEventsOfWeekResponse")
    public JAXBElement<GetEventsOfWeekResponse> createGetEventsOfWeekResponse(GetEventsOfWeekResponse value) {
        return new JAXBElement<GetEventsOfWeekResponse>(_GetEventsOfWeekResponse_QNAME, GetEventsOfWeekResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEventsOfDay }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rsi.jg.org/", name = "getEventsOfDay")
    public JAXBElement<GetEventsOfDay> createGetEventsOfDay(GetEventsOfDay value) {
        return new JAXBElement<GetEventsOfDay>(_GetEventsOfDay_QNAME, GetEventsOfDay.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rsi.jg.org/", name = "addEvent")
    public JAXBElement<AddEvent> createAddEvent(AddEvent value) {
        return new JAXBElement<AddEvent>(_AddEvent_QNAME, AddEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImageByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rsi.jg.org/", name = "getImageByNameResponse")
    public JAXBElement<GetImageByNameResponse> createGetImageByNameResponse(GetImageByNameResponse value) {
        return new JAXBElement<GetImageByNameResponse>(_GetImageByNameResponse_QNAME, GetImageByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddEventResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rsi.jg.org/", name = "addEventResponse")
    public JAXBElement<AddEventResponse> createAddEventResponse(AddEventResponse value) {
        return new JAXBElement<AddEventResponse>(_AddEventResponse_QNAME, AddEventResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEventsOfWeek }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rsi.jg.org/", name = "getEventsOfWeek")
    public JAXBElement<GetEventsOfWeek> createGetEventsOfWeek(GetEventsOfWeek value) {
        return new JAXBElement<GetEventsOfWeek>(_GetEventsOfWeek_QNAME, GetEventsOfWeek.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImageByName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rsi.jg.org/", name = "getImageByName")
    public JAXBElement<GetImageByName> createGetImageByName(GetImageByName value) {
        return new JAXBElement<GetImageByName>(_GetImageByName_QNAME, GetImageByName.class, null, value);
    }

}
