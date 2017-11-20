package org.test.core.service;

import org.test.core.domain.Exhibition;

import java.util.ArrayList;
import java.util.List;

public class ExhibitionServiceImpl
{

    private static List<Exhibition> exhibitionList = new ArrayList<>();

    public static List<Exhibition> getExhibitions() {
        return exhibitionList;
    }

    public static void addUpdateExhibition(Exhibition exhibition) {
        if(!exhibitionList.contains(exhibition)) {
            exhibitionList.add(exhibition);
        }
    }
}
