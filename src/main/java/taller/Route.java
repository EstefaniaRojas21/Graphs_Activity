package taller;

public class Route {
    DistributionCenter destination;
    int weight;
 
     public Route(DistributionCenter destination, int weight) {
         this.destination = destination;
         this.weight = weight;
     }
 
     public DistributionCenter getDestination() {
         return destination;
     }
 
     public void setDestination(DistributionCenter destination) {
         this.destination = destination;
     }
 
     public int getWeight() {
         return weight;
     }
 
     public void setWeight(int weight) {
         this.weight = weight;
     }
 }
 