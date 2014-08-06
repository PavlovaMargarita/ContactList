package controller.command;

import logger.LoggerApplication;
import model.company.CompanyDAOImpl;
import model.country.CountryDAOImpl;
import model.maritalStatus.MaritalStatusDAOImpl;
import model.person.Person;
import model.person.PersonDAOImpl;
import model.template.TemplateDAOImpl;
import param.RequestParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Margarita on 23.07.2014.
 */
public class CorrectPersonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int id;
        try{
            id = Integer.parseInt(request.getParameter(RequestParams.ID));
            Person person = PersonDAOImpl.getInstance().getPerson(id);
            if(id <= 0 || person == null){
                throw new NumberFormatException();
            }
            request.setAttribute(RequestParams.PERSON, person);
            List countries = CountryDAOImpl.getInstance().getCountries();
            request.setAttribute(RequestParams.COUNTRIES, countries);
            List maritalStatuses = MaritalStatusDAOImpl.getInstance().getMaritalStatus();
            request.setAttribute(RequestParams.MARITAL_STATUSES, maritalStatuses);
            List companies = CompanyDAOImpl.getInstance().getCompanies();
            request.setAttribute(RequestParams.COMPANIES, companies);
            return RequestParams.CREATE_PERSON_JSP;
        } catch (NumberFormatException e){
            LoggerApplication.getInstance().setError("request have incorrect ID");
            int goToPage = 1;
            List personForPage = PersonDAOImpl.getInstance().getPersons(goToPage);
            request.setAttribute(RequestParams.PERSONS, personForPage);
            request.setAttribute(RequestParams.CURRENT_PAGE, goToPage);
            int preciousPage = PersonDAOImpl.getInstance().getPreviousPage(goToPage);
            int nextPage = PersonDAOImpl.getInstance().getNextPage(goToPage);
            if (preciousPage != 0) {
                request.setAttribute(RequestParams.PREVIOUS_PAGE, preciousPage);
            }
            if (nextPage != 0) {
                request.setAttribute(RequestParams.NEXT_PAGE, nextPage);
            }

            List templates = TemplateDAOImpl.getInstance().getTemplate();
            request.setAttribute(RequestParams.TEMPLATES, templates);
            return RequestParams.PERSON_LIST_JSP;
        }

    }
}
