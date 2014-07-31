package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Margarita on 23.07.2014.
 */
public interface Command {
    public String execute(HttpServletRequest request, HttpServletResponse response);
}
