package org.test;

import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import javafx.scene.layout.Pane;
import org.test.core.Exhibition;
import org.test.core.View;
import org.test.core.service.ExhibitionService;
import org.test.core.service.ExhibitionServiceImpl;

import java.util.List;
import java.util.function.Consumer;

public class UserView extends VerticalLayout implements View {

    private Panel contentPanel;
    private ExhibitionService exhibitionService;

    @Override
    public void onInit() {
        exhibitionService = new ExhibitionServiceImpl();
        contentPanel = new Panel();
        VerticalLayout content = new VerticalLayout();
        List<Exhibition> exceptionList = exhibitionService.getExhibitions();
        for(Exhibition exhibition : exceptionList){
            ExpoItem expoItem = new ExpoItem(exhibition);
            content.addComponent(expoItem);
            expoItem.getViewButton().addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    removeAllComponents();
                    ExpoView expoView = new ExpoView();
                    addComponent(expoView);
                    expoView.onInit();
                }
            });
        }
        contentPanel.setContent(content);
        addComponent(contentPanel);
        setSizeFull();
        contentPanel.setSizeFull();
    }
}
