package model.maritalStatus;

import logger.LoggerApplication;
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
        List maritalStatuses = ConnectToDB.getInstance().getMaritalStatus();
        if(maritalStatuses.size() == 0){
            LoggerApplication.getInstance().setInfo("maritalStatuses is empty");
        }
        return maritalStatuses;
    }
}
