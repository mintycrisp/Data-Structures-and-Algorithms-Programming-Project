// Zaid Lozano
// CMSC 315 Project 4
// This class is responsible for the visual representation of the graph.
// It manages user interactions for creating vertices and edges and updates the display accordingly.

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class GraphPane extends Pane {
    private Graph graph; // Reference to the Graph object for graph data manipulation
    // List to store line representations of edges
    private List<Line> edges = new ArrayList<>();
    // Initial character for naming vertices
    //private char nextVertexName = 'A';
    private VertexNameGenerator nameGenerator = new VertexNameGenerator();

    // Constructor that initializes the GraphPane with a reference to a Graph object
    // and sets up the pane to respond to mouse clicks for adding vertices
    public GraphPane(Graph graph) {
        this.graph = graph;
        setOnMouseClicked(e -> handleMouseClick(e.getX(), e.getY()));
    }

    // Handles mouse clicks to create and add a vertex at the click location
    // Prevents adding more than 26 vertices, corresponding to letters 'A' to 'Z'
    public void handleMouseClick(double x, double y) {
        String vertexName = nameGenerator.getNextName();
        Vertex newVertex = new Vertex(vertexName, x, y);
        System.out.println("Vertex Created: " + newVertex.getName() + " at x: " + newVertex.getX() + ", y: " + newVertex.getY());
        graph.addVertex(newVertex);
        addVertex(newVertex);
    }
    

    // Adds a visual representation of a vertex to the pane
    public void addVertex(Vertex vertex) {
        // Create a circle to represent the vertex visually
        Circle circle = new Circle(vertex.getX(), vertex.getY(), 10);
        circle.setFill(Color.BLACK); // Set the fill color of the vertex
        // Create a label for the vertex
        Text label = new Text(vertex.getX() - 4, vertex.getY() + 4, vertex.getName());
        label.setFill(Color.WHITE); // Set the label color for visibility
        getChildren().addAll(circle, label); // Add the circle and label to the pane
    }

    // Adds a visual representation of an edge between two vertices to the pane
    public void addEdge(Vertex v1, Vertex v2) {
        System.out.println("Adding edge between " + v1.getName() + " and " + v2.getName());
        System.out.println("Coordinates: (" + v1.getX() + ", " + v1.getY() + ") to (" + v2.getX() + ", " + v2.getY() + ")");
        Line line = new Line(v1.getX(), v1.getY(), v2.getX(), v2.getY());
        line.setStroke(Color.GRAY);
        line.setStrokeWidth(2);
        edges.add(line);
        getChildren().add(line);
    }
    
}
