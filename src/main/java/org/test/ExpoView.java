package org.test;

import com.vaadin.addon.geolocation.Geolocation;
import com.vaadin.addon.geolocation.Position;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.MarkerClickListener;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.test.core.View;

import java.util.function.Consumer;

public class ExpoView extends VerticalLayout implements View {

    @Override
    public void onInit() {
        final GoogleMap googleMap = new GoogleMap(null, null, null);
        googleMap.setSizeFull();
        //customize map
        googleMap.setZoom(50);
        //markers
        GoogleMapMarker sfMarker = new GoogleMapMarker("Science Faculty", new LatLon(7.259023, 80.597640), false);
        googleMap.addMarker(sfMarker);
        googleMap.setCenter(sfMarker.getPosition());
        //marker click event
        googleMap.addMarkerClickListener(new MarkerClickListener() {
            @Override
            public void markerClicked(GoogleMapMarker clickedMarker) {
                Window window = new Window("Stalls");
                VerticalLayout content = new VerticalLayout();
                content.addComponent(new Label("Test"));
                window.setContent(content);
                UI.getCurrent().addWindow(window);
                window.setSizeFull();
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
        addComponent(googleMap);
        setSizeFull();
        setSpacing(Boolean.FALSE);
        setMargin(Boolean.FALSE);
    }
}