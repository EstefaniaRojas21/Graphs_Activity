package taller;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSinkImages;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Distributor {
    List<DistributionCenter> centers;

    public Distributor() {
        centers = new ArrayList<>();
    }

    public void addCenter(DistributionCenter center) {
        centers.add(center);
    }

    public void addRoute(int sourceId, int targetId, int weight) {
        DistributionCenter source = centers.get(sourceId);
        DistributionCenter target = centers.get(targetId);

        source.addRoute(new Route(target, weight));
        target.addRoute(new Route(source, weight));
    }

    public void saveGraphAsImage(String imagePath) {
        Graph graph = new SingleGraph("Distributor");
    
        for (DistributionCenter center : centers) {
            graph.addNode(String.valueOf(center.id)).setAttribute("ui.label", "Center " + center.id);
        }
    
        for (DistributionCenter center : centers) {
            for (Route route : center.routes) {
                String edgeId = center.id + "-" + route.destination.id;
                String reverseEdgeId = route.destination.id + "-" + center.id;
    
                if (graph.getEdge(edgeId) == null && graph.getEdge(reverseEdgeId) == null) {
                    graph.addEdge(edgeId, String.valueOf(center.id), String.valueOf(route.destination.id), false)
                            .setAttribute("ui.label", String.valueOf(route.weight));
                }
            }
        }
    
        graph.setAttribute("ui.stylesheet", "node {text-alignment: center; text-size: 14px;} edge {text-size: 12px;}");

        FileSinkImages pic = new FileSinkImages(FileSinkImages.OutputType.PNG, null);
        pic.setResolution(1920, 1080); 

        try {
            pic.writeAll(graph, imagePath);
            System.out.println("Graph saved as image at: " + imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
