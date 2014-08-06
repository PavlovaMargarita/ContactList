package logger;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import java.io.File;

/**
 * Created by Margarita on 06.08.2014.
 */
public class LoggerApplication {
    private static LoggerApplication ourInstance = new LoggerApplication();

    static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HttpServlet.class);

    public static LoggerApplication getInstance() {
        return ourInstance;
    }

    private LoggerApplication() {
    }
    public void setConfig(ServletConfig config){
        String homeDir = config.getServletContext().getRealPath("/");
        File propertiesFile = new File(homeDir, "WEB-INF/log4j.properties");
        PropertyConfigurator.configure(propertiesFile.toString());
    }
    public void setInfo(String message){
        logger.info(message);
    }

    public void setError(String message){
        logger.error(message);
    }

}
