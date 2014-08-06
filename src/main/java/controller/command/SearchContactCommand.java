package controller.command;
import model.country.CountryDAOImpl;
import model.maritalStatus.MaritalStatusDAOImpl;
import param.RequestParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Margarita on 24.07.2014.
 */
public class SearchContactCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List countries = CountryDAOImpl.getInstance().getCountries();
        request.setAttribute(RequestParams.COUNTRIES, countries);
        List maritalStatuses = MaritalStatusDAOImpl.getInstance().getMaritalStatus();
        request.setAttribute(RequestParams.MARITAL_STATUSES, maritalStatuses);
        return RequestParams.SEARCH_PERSON_JSP;
    }
}
