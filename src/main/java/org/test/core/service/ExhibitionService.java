package org.test.core.service;

import org.test.core.domain.Exhibition;

import java.util.List;

public interface ExhibitionService {

    /**
     * Get All Exhibitions
     * @return
     */
    List<Exhibition> getExhibitions();

    /**
     * Add Exhibition
     * @param exhibition
     */
    void addUpdateExhibition(Exhibition exhibition);

}
