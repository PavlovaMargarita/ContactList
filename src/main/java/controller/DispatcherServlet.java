package controller;

import controller.command.*;
import param.RequestParams;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Margarita on 21.07.2014.
 */
public class DispatcherServlet extends HttpServlet {
    private Map<String, Command> commandMap;

    @Override
    public void init(ServletConfig config){

        commandMap = new HashMap<String,Command>();
        commandMap.put(RequestParams.CORRECT_PERSON, new CorrectPersonCommand());
        commandMap.put(RequestParams.CREATE_PERSON, new CreatePersonCommand());
        commandMap.put(RequestParams.FORM_CORRECT_PERSON, new FormCorrectPersonCommand());
        commandMap.put(RequestParams.SHOW_ALL_PERSONS, new ShowAllPersonCommand());
        commandMap.put(RequestParams.FORM_SEARCH_PERSON, new FormSearchPersonCommand());
        commandMap.put(RequestParams.DELETE_PERSON, new DeletePersonCommand());
        commandMap.put(RequestParams.SEARCH_PERSON, new SearchContactCommand());
    }
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter(RequestParams.METHOD);
        Command command = commandMap.get(method);
        String page = command.execute(request, response);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
