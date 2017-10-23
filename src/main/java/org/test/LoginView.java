package org.test;

import com.vaadin.ui.*;
import org.test.core.User;
import org.test.core.View;

public class LoginView extends VerticalLayout implements View {

    private TextField userName;
    private TextField password;
    private Button loginButton;

    @Override
    public void onInit() {

        userName = new TextField("User Name");
        password = new PasswordField("Password");
        loginButton = new Button("Login");

        addComponents(userName, password, loginButton);

        loginButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                User user = new User();
                user.setUserName(userName.getValue());
                user.setPassword(password.getValue());
                if(user.getUserName().equals("admin")){
                    user.setUserType(User.ADMIN_USER_TYPE);
                }
                UI.getCurrent().getSession().setAttribute(MyUI.USER, user);
                UI.getCurrent().getPage().reload();
            }
        });
    }
}
