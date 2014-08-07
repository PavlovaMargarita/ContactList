package model.company;

import logger.LoggerApplication;
import model.ConnectToDB;

import java.util.List;

/**
 * Created by Margarita on 24.07.2014.
 */
public class CompanyDAOImpl implements CompanyDAO {
    private static CompanyDAOImpl ourInstance = new CompanyDAOImpl();

    public static CompanyDAOImpl getInstance() {
        return ourInstance;
    }

    private CompanyDAOImpl() {
    }

    @Override
    public List getCompanies() {
        List companies = ConnectToDB.getInstance().getCompanies();
        if(companies.size() == 0){
            LoggerApplication.getInstance().setInfo("companies is empty");
        }
        return companies;
    }
}
