package org.test;

import com.vaadin.addon.geolocation.Geolocation;
import com.vaadin.addon.geolocation.Position;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.MarkerClickListener;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.ui.*;
import org.test.core.Exhibition;
import org.test.core.Stall;
import org.test.core.View;
import org.test.core.service.*;
import org.test.core.service.ExhibitionServiceImpl;


import java.util.List;
import java.util.function.Consumer;

public class ExpoView extends VerticalLayout implements View {

    @Override
    public void onInit() {
        final GoogleMap googleMap = new GoogleMap(null, null, null);
        googleMap.setSizeFull();
        //set the zoom level
        googleMap.setZoom(19);

        //markers
        GoogleMapMarker centerMarker = new GoogleMapMarker("Science Faculty", new LatLon(7.259518, 80.598009), false);
        googleMap.setCenter(centerMarker.getPosition());

        String locCaption[] = {"csDep","chemDep","newChem","pgis","newPhy","mathsDep","deansOffice","workshopPhy","phyDep","ecologyDep","labBotny","mbDep","library","zoology1","newGeo","botnyDep","zoology2","parkFrontDeansOffice","parkFrontWije", "parkLeftWije","parkRightGarden","parkLeftGarden","parkFrontCommon","parkNearZoology"};
        double latitude[] = {7.258702,7.259012,7.258741,7.258855,7.259741,7.259912,7.259335,7.259982,7.259657,7.259459,7.259465,7.258933,7.258699,7.259370,7.258838,7.258990,7.259025,7.259442,7.260442,7.260344,7.25983,7.25887,7.258517,7.259486};
        double longatitue[] = {80.598618,80.598559,80.598986,80.596534,80.597829,80.598324,80.599037,80.598820,80.598506,80.597623,80.596875,80.597883,80.597746,80.597638,80.596145,80.596961,80.597520,80.599117,80.599408,80.598839,80.599266,80.599195,80.598782,80.597917};

        int arrayLength = latitude.length;
        GoogleMapMarker location[] = new GoogleMapMarker[arrayLength];

        for(int i=0; i<arrayLength; i++){
            location[i]  = new GoogleMapMarker(locCaption[i], new LatLon(latitude[i], longatitue[i]), false);
            googleMap.addMarker(location[i]);
        }

        //marker click event
        googleMap.addMarkerClickListener(new MarkerClickListener() {
            @Override
            public void markerClicked(GoogleMapMarker clickedMarker) {
                StallService stallService = new StallServiceImpl();
                Window window = new Window("Stalls");
                VerticalLayout content = new VerticalLayout();

                List<Stall> exceptionList = stallService.getStalls();
                for(Stall stall : exceptionList){
                    StallItem stallItem = new StallItem(stall);
                    content.addComponent(stallItem);
                    stallItem.getNavigateButton().addClickListener(new Button.ClickListener() {
                       // @Override
                        public void buttonClick(Button.ClickEvent clickEvent) {
                            removeAllComponents();
                            ExpoView expoView = new ExpoView();
                            addComponent(expoView);
                            expoView.onInit();
                        }
                    });
                }

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