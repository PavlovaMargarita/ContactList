package model.country;

import logger.LoggerApplication;
import model.ConnectToDB;

import java.util.List;

/**
 * Created by Margarita on 24.07.2014.
 */
public class CountryDAOImpl implements CountryDAO {
    private static CountryDAOImpl ourInstance = new CountryDAOImpl();

    public static CountryDAOImpl getInstance() {
        return ourInstance;
    }

    private CountryDAOImpl() {
    }

    @Override
    public List getCountries() {
        List countries = ConnectToDB.getInstance().getCountries();
        if(countries.size() == 0){
            LoggerApplication.getInstance().setInfo("countries is empty");
        }
        return countries;
    }
}
