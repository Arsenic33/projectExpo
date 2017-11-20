package org.test.core.ui;

import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolyline;
import org.test.core.dijkstra.*;
import org.test.core.domain.Exhibition;
import org.test.core.domain.Stall;
import org.test.core.domain.Waypoint;

import java.util.*;

public class ExpoMap extends GoogleMap {

    private Exhibition exhibition;
    private List<GoogleMapPolyline> googleMapPolylines;
    private Map<String, GoogleMapPolyline> googleMapPolylineHashMap;

    public ExpoMap(String apiKey, String clientId, String language) {
        super(apiKey, clientId, language);
        googleMapPolylines = new ArrayList<>();
        googleMapPolylineHashMap = new HashMap<String, GoogleMapPolyline>();
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
        setZoom(exhibition.getZoom());
        setCenter(new LatLon(exhibition.getCenterLat(), exhibition.getCenterLong()));
        for(Vertex vertex : exhibition.getNodes()){
            addMarker(vertex.getVertexMapMarker());
        }
        for(Edge edge : exhibition.getEdges()){
            addPath(edge);
        }
    }

    public GoogleMapPolyline addPath(Edge edge){
        ArrayList<LatLon> points = new ArrayList<LatLon>();
        points.add(edge.getSource().getVertexMapMarker().getPosition());
        points.add(edge.getDestination().getVertexMapMarker().getPosition());
        GoogleMapPolyline overlay = new GoogleMapPolyline(
                points, "#ffc300", 0.8, 8);
        addPolyline(overlay);
        googleMapPolylineHashMap.put(edge.getSource().getId() + edge.getDestination().getId(), overlay);
        return overlay;
    }

    public void indicateShortest(Vertex location){
        for(GoogleMapPolyline googleMapPolyline : googleMapPolylines){
            //removePolyline(googleMapPolyline);
            googleMapPolyline.setStrokeColor("#ffc300");
        }
        googleMapPolylines.clear();
        Graph graph = new Graph(exhibition.getNodes(), exhibition.getEdges());
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(exhibition.getNodes().get(0));
        LinkedList<Vertex> path = dijkstra.getPath(location);
        Vertex vertexCached = null;
        for (Vertex vertex : path) {
            System.out.println(vertex);
            if(vertexCached != null){
                GoogleMapPolyline googleMapPolyline = googleMapPolylineHashMap.get(vertexCached.getId() + vertex.getId());
                if(googleMapPolyline == null){
                    googleMapPolyline = googleMapPolylineHashMap.get(vertex.getId() + vertexCached.getId());
                }
                if(googleMapPolyline != null) {
                    removePolyline(googleMapPolyline);
                    ArrayList<LatLon> points = new ArrayList<LatLon>();
                    points.add(vertexCached.getVertexMapMarker().getPosition());
                    points.add(vertex.getVertexMapMarker().getPosition());
                    GoogleMapPolyline overlay = new GoogleMapPolyline(
                            points, "#00cc00", 1, 8);
                    addPolyline(overlay);
                    googleMapPolylines.add(overlay);
                }
            }
            vertexCached = vertex;
        }
    }
}
