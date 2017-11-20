package org.test.core.domain;

import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import org.test.core.dijkstra.Vertex;
import org.test.core.dijkstra.VertexMapMarker;

public class Stall extends Vertex {

    private String title;
    private String image;
    private String description;
    private String venue;
    private double rate;

    public Stall(VertexMapMarker vertexMapMarker) {
        super(vertexMapMarker);
        super.setType(Exhibition.VERTEX_TYPE.STALL);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
