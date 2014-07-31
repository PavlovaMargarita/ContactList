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
        request.setAttribute("countries", countries);
        List maritalStatuses = MaritalStatusDAOImpl.getInstance().getMaritalStatus();
        request.setAttribute("maritalStatuses", maritalStatuses);
        List companies = CompanyDAOImpl.getInstance().getCompanies();
        request.setAttribute("companies", companies);
        return RequestParams.CREATE_PERSON_JSP;

    }
}
