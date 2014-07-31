package model.maritalStatus;

import model.ConnectToDB;

import java.util.List;

/**
 * Created by Margarita on 24.07.2014.
 */
public class MaritalStatusDAOImpl implements MaritalStatusDAO {
    private static MaritalStatusDAOImpl ourInstance = new MaritalStatusDAOImpl();

    public static MaritalStatusDAOImpl getInstance() {
        return ourInstance;
    }

    private MaritalStatusDAOImpl() {
    }

    @Override
    public List getMaritalStatus() {
        return ConnectToDB.getInstance().getMaritalStatus();
    }
}
