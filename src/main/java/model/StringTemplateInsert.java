package model;

import model.person.Person;
import org.antlr.stringtemplate.*;
import param.RequestParams;

/**
 * Created by Margarita on 06.08.2014.
 */
public class StringTemplateInsert {
    private static StringTemplateInsert ourInstance = new StringTemplateInsert();

    public static StringTemplateInsert getInstance() {
        return ourInstance;
    }

    private StringTemplateInsert() {
    }
    public String insert(String template, Person person){
        StringTemplate message = new StringTemplate(template);
        message.setAttribute(RequestParams.SURNAME, person.getSurname());
        message.setAttribute(RequestParams.NAME, person.getName());
        message.setAttribute(RequestParams.PATRONYMIC, person.getPatronymic());
        message.setAttribute(RequestParams.DATE_OF_BIRTH, person.getDateOfBirth());
        message.setAttribute(RequestParams.NATIONALITY, person.getNationality());
        message.setAttribute(RequestParams.WEB_SITE, person.getWebSite());
        message.setAttribute(RequestParams.EMAIL, person.getEmail());
        message.setAttribute(RequestParams.COMPANY, person.getCompany());
        message.setAttribute(RequestParams.CITY, person.getCity());
        message.setAttribute(RequestParams.STREET, person.getStreet());
        message.setAttribute(RequestParams.HOME, person.getHome());
        message.setAttribute(RequestParams.FLAT, person.getFlat());
        message.setAttribute(RequestParams.INDEX, person.getIndex());
        return message.toString();
    }
}
