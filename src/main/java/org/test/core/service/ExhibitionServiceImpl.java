package org.test.core.service;

import org.test.core.Exhibition;

import java.util.ArrayList;
import java.util.List;

public class ExhibitionServiceImpl implements ExhibitionService
{
    @Override
    public List<Exhibition> getExhibitions() {
        List<Exhibition> exhibitionList = new ArrayList<>();
        for(int index = 0; index < 4; index++){
            Exhibition exhibition = new Exhibition();
            exhibition.setTitle("Expo " + index);
            exhibition.setDescription("A world's fair, world fair, world exposition, or universal exposition is a large international exhibition designed to showcase achievements of nations. These exhibitions vary in character and are held in different parts of the world.");
            exhibitionList.add(exhibition);
        }
        return exhibitionList;
    }
}
