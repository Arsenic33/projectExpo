package org.test;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import org.test.core.domain.Stall;


public class StallItem extends VerticalLayout {

    private Label title;
    private Image stallImg;
    private Label description;
    private Label venue;
    private Button navigateButton;
    private Button sessionButton;
    private Button rateButton;

    public StallItem(Stall stall){

        navigateButton = new Button("Navigate");
        sessionButton = new Button("Sessions");
        rateButton = new Button("Rate");
        title = new Label(stall.getTitle());
        description = new Label(stall.getDescription());
        description.setSizeFull();
        title.setSizeFull();



        GridLayout grid = new GridLayout(3, 3);

        grid.setWidth(220, Sizeable.UNITS_PIXELS);
        grid.setHeight(50, Sizeable.UNITS_PIXELS);

        grid.addComponent(navigateButton, 0, 0);
        grid.setComponentAlignment(navigateButton, Alignment.TOP_LEFT);

        grid.addComponent(sessionButton, 1, 0);
        grid.setComponentAlignment(sessionButton, Alignment.TOP_CENTER);

        grid.addComponent(rateButton, 2, 0);
        grid.setComponentAlignment(rateButton, Alignment.TOP_RIGHT);

        addComponents(title, description,grid);

        setWidth("100%");
        addStyleName("expo-item");
    }

    public Button getNavigateButton() {

        return navigateButton;
    }

    public Button getSessionButton() {

        return sessionButton;
    }
    public Button getRateButton() {

        return rateButton;
    }
}