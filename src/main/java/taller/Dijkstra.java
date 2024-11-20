package taller;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra {
    public static void dijkstra(Distributor distributor, int startId) {
        PriorityQueue<DistributionCenter> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));
        DistributionCenter start = distributor.centers.get(startId);
        start.distance = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            DistributionCenter current = queue.poll();

            for (Route route : current.routes) {
                DistributionCenter target = route.destination;
                int newDistance = current.distance + route.weight;

                if (newDistance < target.distance) {
                    queue.remove(target);
                    target.distance = newDistance;
                    target.previous = current;
                    queue.add(target);
                }
            }
        }
    }
}
