package org.test.core.ui.admin;

import com.vaadin.ui.*;
import org.test.core.View;

public class AdminView extends VerticalLayout implements View {

    private ExpoEditor expoEditor;
    @Override
    public void onInit() {
        expoEditor = new ExpoEditor();
        addComponent(expoEditor);
        setSizeFull();
        expoEditor.setSizeFull();
        setSpacing(Boolean.FALSE);
        setMargin(Boolean.FALSE);
    }
}
