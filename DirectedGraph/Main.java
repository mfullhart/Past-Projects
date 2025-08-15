public class Main
{
    public static void main(String[] args)
    {
        DirectedGraph<String, Integer> graph = new MatrixGraph<>();

        try
        {
            // Add vertices
            graph.add("A");
            graph.add("B");
            graph.add("C");

            // Add edges
            graph.addEdge("A", "B", 10);
            graph.addEdge("B", "C", 20);

            // Display vertices
            System.out.println("Vertices:");
            graph.vertices().forEachRemaining(vertex -> System.out.println(vertex.getLabel()));

            // Display edges
            System.out.println("Edges:");
            graph.edges().forEachRemaining(edge ->
                    System.out.printf("From %s to %s with label %d%n", edge.getU(), edge.getV(), edge.getLabel())
            );

            // Check adjacency
            System.out.println("Adjacent to A:");
            graph.adjacent("A").forEachRemaining(vertex -> System.out.println(vertex.getLabel()));

        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
