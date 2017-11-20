package org.test;

import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.MarkerClickListener;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.ui.*;
import org.test.core.dijkstra.VertexMapMarker;
import org.test.core.domain.Exhibition;
import org.test.core.domain.Stall;
import org.test.core.View;
import org.test.core.service.*;
import org.test.core.ui.ExpoMap;


import java.util.List;

public class ExpoView extends VerticalLayout implements View {

    @Override
    public void onInit() {

        Exhibition exhibition = ExhibitionServiceImpl.getExhibitions().get(0);
        ExpoMap expoMap = new ExpoMap(null, null, null);
        expoMap.setSizeFull();
        expoMap.setExhibition(exhibition);

        //marker click event
        expoMap.addMarkerClickListener(new MarkerClickListener() {
            @Override
            public void markerClicked(GoogleMapMarker clickedMarker) {
                if (((VertexMapMarker)clickedMarker).getVertex() instanceof Stall) {
                    StallService stallService = new StallServiceImpl();
                    Window window = new Window("Stalls");
                    VerticalLayout content = new VerticalLayout();

                    List<Stall> exceptionList = stallService.getStalls();
                    for (Stall stall : exceptionList) {
                        StallItem stallItem = new StallItem(stall);
                        content.addComponent(stallItem);
                        stallItem.getNavigateButton().addClickListener(new Button.ClickListener() {
                            // @Override
                            public void buttonClick(Button.ClickEvent clickEvent) {
                                expoMap.indicateShortest(((VertexMapMarker)clickedMarker).getVertex());
                                window.close();
                            }
                        });
                    }
                    window.setContent(content);
                    UI.getCurrent().addWindow(window);
                    window.setSizeFull();
                }
            }
        });
/*        Geolocation geoLocaltion = new Geolocation(UI.getCurrent());
        geoLocaltion.getCurrentPosition(new Consumer<Position>() {
            @Override
            public void accept(Position position) {
                LatLon currentLocation = new LatLon(position.getCoordinates().getLatitude(), position.getCoordinates().getLongitude());
                GoogleMapMarker currentLocationMarker = new GoogleMapMarker("You", currentLocation, false);
                googleMap.setCenter(currentLocation);
                googleMap.addMarker(currentLocationMarker);
            }
        });*/
        addComponent(expoMap);
        setSizeFull();
        setSpacing(Boolean.FALSE);
        setMargin(Boolean.FALSE);
    }
}