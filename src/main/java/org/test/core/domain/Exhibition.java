package org.test.core.domain;

import com.vaadin.tapio.googlemaps.client.LatLon;
import org.test.core.dijkstra.Edge;
import org.test.core.dijkstra.Vertex;

import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Exhibition {

    public static final class VERTEX_TYPE{
        private VERTEX_TYPE() {
            //prevent classes being created
        }
        public static final int STALL = 1;
        public static final int WAYPOINT = 2;
        public static final int TICKETING = 3;
        public static final int PARKING = 4;

    }

    private List<Vertex> nodes;
    private List<Edge> edges;
    private double centerLong;
    private double centerLat;
    private String id;
    private String title;
    private Image image;
    private String description;
    private Date startDate;
    private Date endDate;
    private String venue;
    private Time time;
    private int zoom;

    public Exhibition() {
        this.nodes = new ArrayList<Vertex>();
        this.edges = new ArrayList<Edge>();
    }

    public void addVertex(Vertex vertex){
        nodes.add(vertex);
    }

    public Edge addEdge(Vertex from, Vertex to, String id){
        ArrayList<LatLon> points = new ArrayList<LatLon>();
        points.add(from.getVertexMapMarker().getPosition());
        points.add(to.getVertexMapMarker().getPosition());
        Edge lane = new Edge(id, from, to, 1 );
        edges.add(lane);
        return lane;
    }

    public List<Vertex> getNodes() {
        return nodes;
    }

    public void setNodes(List<Vertex> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public double getCenterLong() {
        return centerLong;
    }

    public void setCenterLong(double centerLong) {
        this.centerLong = centerLong;
    }

    public double getCenterLat() {
        return centerLat;
    }

    public void setCenterLat(double centerLat) {
        this.centerLat = centerLat;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exhibition that = (Exhibition) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
