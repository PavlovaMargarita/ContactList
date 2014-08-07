package model.template;

import logger.LoggerApplication;
import model.ConnectToDB;
import java.util.List;



/**
 * Created by Margarita on 05.08.2014.
 */
public class TemplateDAOImpl implements TemplateDAO {
    private static TemplateDAOImpl ourInstance = new TemplateDAOImpl();

    public static TemplateDAOImpl getInstance() {
        return ourInstance;
    }

    private TemplateDAOImpl() {
    }

    @Override
    public List getTemplate() {
        List templates = ConnectToDB.getInstance().getTemplates();
        if(templates.size() == 0){
            LoggerApplication.getInstance().setInfo("templates is empty");
        }
        return templates;
    }
}
