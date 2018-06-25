package uk.ac.uea.carfinder;

import android.graphics.Color;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;
import java.util.Map;

/**
 * Deprecated class used to create custom Nodes on the map
 * to be used for custom PolyLines.
 * Created by Beren on 30/01/2016.
 */
     public class MapNode
    {

        private LatLng latLng;
        private String name;
        private List<MapNode> neigbourMapNodes;


        /**
         *
         * @param lat
         * @param lng
         * @param name
         */
       public MapNode(double lat, double lng, String name)
        {
            this.latLng = new LatLng(lat, lng);
            this.name = name;
            neigbourMapNodes = null;
        }

        /**
         *
         * @param mapNode
         */
        public void addNodeToNeighbours(MapNode mapNode)
        {
            neigbourMapNodes.add(mapNode);

        }

        /**
         *
         * @param index
         */
        public void removeNodeAt(int index)
        {
            neigbourMapNodes.remove(index);

        }

        /**
         *
         * @param nodesList
         * @param loc
         * @return
         */
        public MapNode getStartNode(List<MapNode> nodesList, Location loc)
        {
            MapNode startNode = nodesList.get(0);
            float dist =  new Location( nodesList.get(0).getName()).distanceTo (loc);
            for(int i = 0; i< nodesList.size(); i ++)
            {
                if(getDistanceBetweenNodes(nodesList.get(i), new MapNode(loc.getLatitude(), loc.getLongitude(),"Name")) < dist);
                startNode = nodesList.get(i);
            }

            return startNode;
        }

        /**
         *
         * @param mapNode1
         * @param mapNode2
         * @return
         */
        public float getDistanceBetweenNodes(MapNode mapNode1, MapNode mapNode2)
        {

            Location loc1 = new Location(mapNode1.getName());
            Location loc2 = new Location(mapNode2.getName());

            loc1.setLatitude(mapNode1.getLatLng().latitude);
            loc1.setLatitude(mapNode1.getLatLng().longitude);

            loc2.setLatitude(mapNode2.getLatLng().latitude);
            loc2.setLatitude(mapNode2.getLatLng().longitude);

            return loc1.distanceTo(loc2);

        }

        /**
         *
         * @param mapNode
         * @return
         */
        public MarkerOptions getMarker(MapNode mapNode)
        {
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.title(mapNode.getName()).position(mapNode.getLatLng());
            return markerOption;
        }

        /**
         *
         * @param mapNode
         * @return
         */
        public PolylineOptions getPolyLine(MapNode mapNode)
        {
            PolylineOptions plyOpt = new PolylineOptions();
            plyOpt.add(mapNode.getLatLng()).color(Color.BLUE).width(10);
            return plyOpt;
        }

        /**
         *
         * @param mapNode1
         * @param mapNode2
         * @return
         */
        public boolean isNeighbour(MapNode mapNode1, MapNode mapNode2)
        {
            return(mapNode1.neigbourMapNodes.contains(mapNode2));

        }


        // GETTERS AND SETTERS

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<MapNode> getNeigbourMapNodes() {
            return neigbourMapNodes;
        }

        public void setNeigbourMapNodes(List<MapNode> neigbourMapNodes) {
            this.neigbourMapNodes = neigbourMapNodes;
        }

        public LatLng getLatLng() {
            return latLng;
        }

        public void setLatLng(LatLng latLng) {
            this.latLng = latLng;
        }
        





}
