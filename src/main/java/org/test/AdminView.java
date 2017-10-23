package org.test;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.test.core.View;

public class AdminView extends VerticalLayout implements View {

    @Override
    public void onInit() {
        addComponent(new Label("Admin View"));
    }
}
