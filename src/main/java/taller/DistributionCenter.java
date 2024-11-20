package taller;

import java.util.ArrayList;
import java.util.List;

public class DistributionCenter {
    int id;
    List<Route> routes;
    int distance;
    DistributionCenter previous;

    DistributionCenter(int id) {
        this.id = id;
        routes = new ArrayList<>();
        distance = Integer.MAX_VALUE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public DistributionCenter getPrevious() {
        return previous;
    }

    public void setPrevious(DistributionCenter previous) {
        this.previous = previous;
    }

    void addRoute(Route route) {
        routes.add(route);
    }
}
