package org.test.core.service;

import com.vaadin.tapio.googlemaps.client.LatLon;
import org.test.core.dijkstra.VertexMapMarker;
import org.test.core.domain.Stall;

import java.util.ArrayList;
import java.util.List;

public class StallServiceImpl implements StallService
{
    @Override
    public List<Stall> getStalls() {
        List<Stall> stallList = new ArrayList<>();

        for(int index = 0; index < 10; index++){
            Stall stall = new Stall(new VertexMapMarker("Test", new LatLon(), Boolean.FALSE, "dummy"));
            stall.setTitle("Stall " + index);
            stall.setDescription("This is a stall conducted bu Department of Computer Science & Statistics that related with IOT. You can get a good knowledge about IOT related applications.");
            stallList.add(stall);
        }
        return stallList;
    }
}