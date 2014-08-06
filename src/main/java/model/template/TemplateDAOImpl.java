package model.template;

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
        return ConnectToDB.getInstance().getTemplates();
    }
}
