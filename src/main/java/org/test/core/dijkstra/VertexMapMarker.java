package org.test.core.dijkstra;

import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;

public class VertexMapMarker extends GoogleMapMarker {

    private Vertex vertex;

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public VertexMapMarker(String caption, LatLon position, boolean draggable, String iconUrl) {
        super(caption, position, draggable, iconUrl);
    }
}
