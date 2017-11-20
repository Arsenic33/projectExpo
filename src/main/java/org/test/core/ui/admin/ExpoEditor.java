package org.test.core.ui.admin;

import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.MapClickListener;
import com.vaadin.tapio.googlemaps.client.events.MapMoveListener;
import com.vaadin.tapio.googlemaps.client.events.MarkerClickListener;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolyline;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.test.core.dijkstra.Edge;
import org.test.core.dijkstra.Vertex;
import org.test.core.dijkstra.VertexMapMarker;
import org.test.core.domain.Exhibition;
import org.test.core.domain.Stall;
import org.test.core.domain.Waypoint;
import org.test.core.service.ExhibitionServiceImpl;
import org.test.core.ui.ExpoMap;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ExpoEditor extends VerticalLayout{

    private List<GoogleMapPolyline> googleMapPolylines;
    private HorizontalLayout toolBarContainer;
    private ExpoMap googleMap;
    private Exhibition exhibition;

    public ExpoEditor() {
        exhibition = new Exhibition();
        //Tool Bar
        this.toolBarContainer = new HorizontalLayout();
        List<Integer> pointTypes = Arrays.asList(Exhibition.VERTEX_TYPE.PARKING, Exhibition.VERTEX_TYPE.WAYPOINT, Exhibition.VERTEX_TYPE.STALL, Exhibition.VERTEX_TYPE.TICKETING);
        RadioButtonGroup<Integer> pointTypesRadioButtonGroup = new RadioButtonGroup<Integer>(null, pointTypes);
        pointTypesRadioButtonGroup.setItemCaptionGenerator(new ItemCaptionGenerator<Integer>() {
            @Override
            public String apply(Integer integer) {
                String name = "Unknown";
                switch (integer) {
                    case Exhibition.VERTEX_TYPE.PARKING : name = "Parking"; break;
                    case Exhibition.VERTEX_TYPE.WAYPOINT : name = "Waypoint"; break;
                    case Exhibition.VERTEX_TYPE.STALL : name = "Stall"; break;
                    case Exhibition.VERTEX_TYPE.TICKETING : name = "Ticketing"; break;
                }
                return name;
            }
        });
        pointTypesRadioButtonGroup.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
        pointTypesRadioButtonGroup.setSelectedItem(Exhibition.VERTEX_TYPE.WAYPOINT);
        Button saveBtn = new Button("Save/Update");
        saveBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ExhibitionServiceImpl.addUpdateExhibition(exhibition);
            }
        });
        toolBarContainer.addComponent(pointTypesRadioButtonGroup);
        toolBarContainer.addComponent(saveBtn);
        toolBarContainer.setWidth("100%");
        toolBarContainer.setSpacing(Boolean.TRUE);
        toolBarContainer.setMargin(Boolean.TRUE);
        toolBarContainer.setComponentAlignment(saveBtn, Alignment.MIDDLE_RIGHT);
        toolBarContainer.setComponentAlignment(pointTypesRadioButtonGroup, Alignment.MIDDLE_LEFT);
        //Map
        this.googleMap = new ExpoMap(null, null, null);
        googleMap.setSizeFull();
        addComponent(toolBarContainer);
        addComponent(googleMap);
        setExpandRatio(googleMap, 1);
        setSizeFull();
        setSpacing(Boolean.FALSE);
        setMargin(Boolean.FALSE);
        //markers
        final VertexMapMarker[] currentMarker = {null};
        final boolean[] clickedOnMarker = {false};
        googleMap.addMapClickListener(new MapClickListener() {
            @Override
            public void mapClicked(LatLon latLon) {
                clickedOnMarker[0] = false;
                VertexMapMarker newMarker = new VertexMapMarker("Point", latLon, Boolean.TRUE, "VAADIN/dot.png");
                Vertex location = new Waypoint(newMarker);
                switch (pointTypesRadioButtonGroup.getValue()){
                    case Exhibition.VERTEX_TYPE.PARKING :
                        newMarker = new VertexMapMarker("Parking", latLon, Boolean.TRUE, "VAADIN/automotive.png");
                        location = new Vertex(newMarker);
                        ; break;
                    case Exhibition.VERTEX_TYPE.STALL :
                        newMarker = new VertexMapMarker("Stall", latLon, Boolean.TRUE, "VAADIN/exhibitions.png");
                        location = new Stall(newMarker);
                        ; break;
                    case Exhibition.VERTEX_TYPE.TICKETING :
                        newMarker = new VertexMapMarker("Ticket Counter", latLon, Boolean.TRUE, "VAADIN/financial-services.png");
                        location = new Vertex(newMarker);
                        ; break;
                }
                googleMap.addMarker(newMarker);
                newMarker.setVertex(location);
                exhibition.addVertex(location);
                if(currentMarker[0] != null){
                    Edge edge = exhibition.addEdge(currentMarker[0].getVertex(), location, UUID.randomUUID().toString());
                    googleMap.addPath(edge);
                }
            }
        });
        googleMap.addMarkerClickListener(new MarkerClickListener() {
            @Override
            public void markerClicked(GoogleMapMarker googleMapMarker) {
                if(clickedOnMarker[0] && currentMarker[0] != null){
                    Edge edge = exhibition.addEdge(currentMarker[0].getVertex(), ((VertexMapMarker) googleMapMarker).getVertex(), UUID.randomUUID().toString());
                    googleMap.addPath(edge);
                    clickedOnMarker[0] = false;
                }else {
                    clickedOnMarker[0] = true;
                }
                currentMarker[0] = (VertexMapMarker) googleMapMarker;
            }
        });
        //Map listeners
        googleMap.addMapMoveListener(new MapMoveListener() {
            @Override
            public void mapMoved(int i, LatLon latLon, LatLon latLon1, LatLon latLon2) {
                exhibition.setCenterLong(googleMap.getCenter().getLon());
                exhibition.setCenterLat(googleMap.getCenter().getLat());
                exhibition.setZoom(i);
            }
        });
        googleMap.setCenter(new LatLon(7.259383, 80.597739));
        googleMap.setZoom(19);
        exhibition.setCenterLong(googleMap.getCenter().getLon());
        exhibition.setCenterLat(googleMap.getCenter().getLat());
        exhibition.setZoom(googleMap.getZoom());
    }

}
