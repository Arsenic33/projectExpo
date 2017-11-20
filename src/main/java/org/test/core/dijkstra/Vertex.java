package org.test.core.dijkstra;

import com.vaadin.tapio.googlemaps.client.LatLon;

public class Vertex {
    private String id;
    private String name;
    private String iconUrl;
    private double longitude;
    private double latitude;
    private int type;
    private VertexMapMarker vertexMapMarker;

    public Vertex(VertexMapMarker googleMapMarker) {
        this.vertexMapMarker = googleMapMarker;
        this.id = String.valueOf(googleMapMarker.getId());
        this.name = googleMapMarker.getCaption();
        this.longitude = googleMapMarker.getPosition().getLon();
        this.latitude = googleMapMarker.getPosition().getLat();
        this.iconUrl = googleMapMarker.getIconUrl();
    }

    public void init(){
        this.vertexMapMarker = new VertexMapMarker(name, new LatLon(latitude, longitude), false, iconUrl);
        this.vertexMapMarker.setVertex(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public VertexMapMarker getVertexMapMarker() {
        return vertexMapMarker;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}