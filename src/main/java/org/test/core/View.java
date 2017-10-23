package org.test.core;

import com.vaadin.ui.Component;

public interface View extends Component{

    /**
     * Initialize the view content
     */
    void onInit();

}
