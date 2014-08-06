package model.template;

/**
 * Created by Margarita on 05.08.2014.
 */
public class Template {
    private int id;
    private String template;
    private String templateName;
    public Template(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
