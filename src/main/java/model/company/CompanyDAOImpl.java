package model.company;

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
        return ConnectToDB.getInstance().getCompanies();
    }
}
