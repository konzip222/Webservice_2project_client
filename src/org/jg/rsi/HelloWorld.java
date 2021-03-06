
package org.jg.rsi;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "HelloWorld", targetNamespace = "http://rsi.jg.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HelloWorld {


    /**
     * 
     * @param month
     * @param year
     * @param name
     * @param description
     * @param id
     * @param type
     * @param day
     */
    @WebMethod
    @RequestWrapper(localName = "modifyEvent", targetNamespace = "http://rsi.jg.org/", className = "org.jg.rsi.ModifyEvent")
    @ResponseWrapper(localName = "modifyEventResponse", targetNamespace = "http://rsi.jg.org/", className = "org.jg.rsi.ModifyEventResponse")
    @Action(input = "http://rsi.jg.org/HelloWorld/modifyEventRequest", output = "http://rsi.jg.org/HelloWorld/modifyEventResponse")
    public void modifyEvent(
        @WebParam(name = "id", targetNamespace = "")
        int id,
        @WebParam(name = "name", targetNamespace = "")
        String name,
        @WebParam(name = "type", targetNamespace = "")
        String type,
        @WebParam(name = "day", targetNamespace = "")
        int day,
        @WebParam(name = "month", targetNamespace = "")
        int month,
        @WebParam(name = "year", targetNamespace = "")
        int year,
        @WebParam(name = "description", targetNamespace = "")
        String description);

    /**
     * 
     * @param month
     * @param year
     * @param name
     * @param description
     * @param type
     * @param day
     */
    @WebMethod
    @RequestWrapper(localName = "addEvent", targetNamespace = "http://rsi.jg.org/", className = "org.jg.rsi.AddEvent")
    @ResponseWrapper(localName = "addEventResponse", targetNamespace = "http://rsi.jg.org/", className = "org.jg.rsi.AddEventResponse")
    @Action(input = "http://rsi.jg.org/HelloWorld/addEventRequest", output = "http://rsi.jg.org/HelloWorld/addEventResponse")
    public void addEvent(
        @WebParam(name = "name", targetNamespace = "")
        String name,
        @WebParam(name = "type", targetNamespace = "")
        String type,
        @WebParam(name = "day", targetNamespace = "")
        int day,
        @WebParam(name = "month", targetNamespace = "")
        int month,
        @WebParam(name = "year", targetNamespace = "")
        int year,
        @WebParam(name = "description", targetNamespace = "")
        String description);

    /**
     * 
     * @param arg0
     * @return
     *     returns byte[]
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getImageByName", targetNamespace = "http://rsi.jg.org/", className = "org.jg.rsi.GetImageByName")
    @ResponseWrapper(localName = "getImageByNameResponse", targetNamespace = "http://rsi.jg.org/", className = "org.jg.rsi.GetImageByNameResponse")
    @Action(input = "http://rsi.jg.org/HelloWorld/getImageByNameRequest", output = "http://rsi.jg.org/HelloWorld/getImageByNameResponse")
    public byte[] getImageByName(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param week
     * @param year
     * @return
     *     returns java.util.List<org.jg.rsi.Events>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getEventsOfWeek", targetNamespace = "http://rsi.jg.org/", className = "org.jg.rsi.GetEventsOfWeek")
    @ResponseWrapper(localName = "getEventsOfWeekResponse", targetNamespace = "http://rsi.jg.org/", className = "org.jg.rsi.GetEventsOfWeekResponse")
    @Action(input = "http://rsi.jg.org/HelloWorld/getEventsOfWeekRequest", output = "http://rsi.jg.org/HelloWorld/getEventsOfWeekResponse")
    public List<Events> getEventsOfWeek(
        @WebParam(name = "week", targetNamespace = "")
        int week,
        @WebParam(name = "year", targetNamespace = "")
        int year);

    /**
     * 
     * @param month
     * @param year
     * @param day
     * @return
     *     returns java.util.List<org.jg.rsi.Events>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getEventsOfDay", targetNamespace = "http://rsi.jg.org/", className = "org.jg.rsi.GetEventsOfDay")
    @ResponseWrapper(localName = "getEventsOfDayResponse", targetNamespace = "http://rsi.jg.org/", className = "org.jg.rsi.GetEventsOfDayResponse")
    @Action(input = "http://rsi.jg.org/HelloWorld/getEventsOfDayRequest", output = "http://rsi.jg.org/HelloWorld/getEventsOfDayResponse")
    public List<Events> getEventsOfDay(
        @WebParam(name = "day", targetNamespace = "")
        int day,
        @WebParam(name = "month", targetNamespace = "")
        int month,
        @WebParam(name = "year", targetNamespace = "")
        int year);

}
