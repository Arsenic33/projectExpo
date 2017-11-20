package org.test.core.domain;

import org.test.core.dijkstra.Vertex;
import org.test.core.dijkstra.VertexMapMarker;

public class Waypoint extends Vertex {

    public Waypoint(VertexMapMarker googleMapMarker) {
        super(googleMapMarker);
        super.setType(Exhibition.VERTEX_TYPE.WAYPOINT);
    }
}
