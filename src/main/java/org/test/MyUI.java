package org.test;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.test.core.User;
import org.test.core.View;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    public final static String USER = "user";
    private View currentView;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        User user = (User) getCurrent().getSession().getAttribute(USER);
        if(user == null){
            currentView = new LoginView();
        }else{
            if(user.isAdmin()){
                currentView = new AdminView();
            }else{
                currentView = new UserView();
            }
        }
        setContent(currentView);
        currentView.onInit();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
