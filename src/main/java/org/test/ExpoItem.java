package org.test;

import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.test.core.Exhibition;
//import org.test.core.Exhibition;

public class ExpoItem extends VerticalLayout {

    private Label title;
    private Image expoImg;
    private Label description;
    private Label startDate;
    private Label endDate;
    private Label venue;
    private Label time;
    private Button viewButton;

    public ExpoItem(Exhibition exhibition){
        viewButton = new Button("View");
        title = new Label(exhibition.getTitle());
        description = new Label(exhibition.getDescription());
        description.setSizeFull();
        title.setSizeFull();
        addComponents(title, description,viewButton);
        setWidth("100%");
        addStyleName("expo-item");
    }

    public Button getViewButton() {
        return viewButton;
    }
}
