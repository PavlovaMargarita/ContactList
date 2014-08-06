package controller.command;
import model.company.CompanyDAOImpl;
import model.country.CountryDAOImpl;
import model.maritalStatus.MaritalStatusDAOImpl;
import param.RequestParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Margarita on 23.07.2014.
 */
public class CreatePersonCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List countries = CountryDAOImpl.getInstance().getCountries();
        request.setAttribute(RequestParams.COUNTRIES, countries);
        List maritalStatuses = MaritalStatusDAOImpl.getInstance().getMaritalStatus();
        request.setAttribute(RequestParams.MARITAL_STATUSES, maritalStatuses);
        List companies = CompanyDAOImpl.getInstance().getCompanies();
        request.setAttribute(RequestParams.COMPANIES, companies);
        return RequestParams.CREATE_PERSON_JSP;

    }
}
