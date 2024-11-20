package taller;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;

public class DistributorGUI {
    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "swing");

        Distributor distributor = new Distributor();
        for (int i = 0; i < 6; i++) {
            distributor.addCenter(new DistributionCenter(i));
        }

        distributor.addRoute(0, 1, 10);
        distributor.addRoute(0, 2, 5);
        distributor.addRoute(1, 3, 1);
        distributor.addRoute(2, 1, 3);
        distributor.addRoute(2, 3, 8);
        distributor.addRoute(2, 4, 2);
        distributor.addRoute(3, 5, 6);
        distributor.addRoute(4, 3, 7);
        distributor.addRoute(4, 5, 9);

        Dijkstra.dijkstra(distributor, 0);

        JFrame frame = new JFrame("Distributor Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());

        Graph graph = new SingleGraph("Distributor");
        graph.setAttribute("ui.stylesheet", "node {size: 20px; fill-color: blue; text-alignment: center;} edge {text-size: 12px;}");

        for (DistributionCenter center : distributor.centers) {
            if (graph.getNode(String.valueOf(center.id)) == null) {
                graph.addNode(String.valueOf(center.id))
                    .setAttribute("ui.label", "Center " + center.id);
            }
        }
        
        for (DistributionCenter center : distributor.centers) {
            for (Route route : center.routes) {
                String edgeId = center.id + "-" + route.destination.id;
                String reverseEdgeId = route.destination.id + "-" + center.id;
        
                if (graph.getEdge(edgeId) == null && graph.getEdge(reverseEdgeId) == null) {
                    graph.addEdge(edgeId, String.valueOf(center.id), String.valueOf(route.destination.id), true)
                        .setAttribute("ui.label", String.valueOf(route.weight));
                }
            }
        }
        

        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        JPanel graphPanel = viewer.addDefaultView(false); // Canvas del grafo
        frame.add(graphPanel, BorderLayout.CENTER);

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(200, 600));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        StringBuilder result = new StringBuilder("Shortest Path Results:\n");
        for (DistributionCenter center : distributor.centers) {
            if (center.id != 0) {
                result.append("Center 0 to Center ").append(center.id).append(": ").append(center.distance).append("\n");
            }
        }
        textArea.setText(result.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        sidePanel.add(scrollPane);

        frame.add(sidePanel, BorderLayout.EAST);

        frame.setVisible(true);
    }
}
