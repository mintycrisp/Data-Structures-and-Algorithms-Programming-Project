// Zaid Lozano
// CMSC 315 Project 4
// This class serves as the entry point for the JavaFX application, handling the initialization
// of the GUI, setting up event handlers for user interactions, and managing the graph visualization.
// It integrates the GraphPane for visual display and Graph class for data management.

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    // GraphPane object for visualizing the graph
    private GraphPane graphPane;
    // Graph object for managing graph operations
    private Graph graph;
    // TextField for displaying status messages and results to the user
    private TextField statusField;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the graph object, the core structure for graph operations
        graph = new Graph();
        // Initialize the graphical representation of the graph with the graph object
        graphPane = new GraphPane(graph);
        // Root layout pane where other UI elements are added
        BorderPane root = new BorderPane();

        // Setting the visual representation of the graph in the center of the layout
        root.setCenter(graphPane);

        // Initialize text fields for inputting vertex names for edge creation
        TextField vertex1Field = new TextField();
        TextField vertex2Field = new TextField();
        // Initialize the status field, set to non-editable
        statusField = new TextField();
        statusField.setEditable(false);

        // Buttons for adding edges and performing graph-related queries
        Button addEdgeButton = new Button("Add Edge");
        // Setup the handler for adding an edge when the button is clicked
        addEdgeButton.setOnAction(e -> handleAddEdgeButton(vertex1Field.getText(), vertex2Field.getText()));

        Button isConnectedButton = new Button("Is Connected");
        // Setup the handler for checking graph connectivity
        isConnectedButton.setOnAction(e -> handleIsConnectedButton());

        Button hasCyclesButton = new Button("Has Cycles");
        // Setup the handler for checking if the graph has cycles
        hasCyclesButton.setOnAction(e -> handleHasCyclesButton());

        Button depthFirstSearchButton = new Button("Depth First Search");
        // Setup the handler for performing a depth-first search
        depthFirstSearchButton.setOnAction(e -> handleDepthFirstSearchButton());

        Button breadthFirstSearchButton = new Button("Breadth First Search");
        // Setup the handler for performing a breadth-first search
        breadthFirstSearchButton.setOnAction(e -> handleBreadthFirstSearchButton());

        // Horizontal layout for input fields and the add edge button
        HBox inputControls = new HBox(10, vertex1Field, vertex2Field, addEdgeButton);
        // Horizontal layout for graph operation buttons
        HBox operationControls = new HBox(10, isConnectedButton, hasCyclesButton, depthFirstSearchButton, breadthFirstSearchButton);
        // Vertical layout for stacking the input and operation controls below the status field
        VBox bottomControls = new VBox(5, statusField, inputControls, operationControls);

        // Placement of input controls at the top of the layout
        root.setTop(inputControls);
        // Placement of operation controls at the bottom of the layout
        root.setBottom(bottomControls);

        // Set the scene with the root pane and size, then display it
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("Graph Visualization");
        primaryStage.show();
    }

    // Handler for adding an edge, triggered by the add edge button
    private void handleAddEdgeButton(String vertex1, String vertex2) {
        try {
            // Retrieve vertices based on full input names
            Vertex v1 = graph.getVertex(vertex1);
            Vertex v2 = graph.getVertex(vertex2);
            if (v1 == null || v2 == null) {
                statusField.setText("One or both vertices do not exist.");
                return;
            }
            graph.addEdge(v1, v2);
            graphPane.addEdge(v1, v2);
            statusField.setText("Edge added between " + vertex1 + " and " + vertex2);
        } catch (IllegalArgumentException e) {
            statusField.setText("Invalid vertex names.");
        } catch (Exception e) {
            statusField.setText("Error adding edge: " + e.getMessage());
        }
    }
    

    // Handler to check if the graph is connected
    private void handleIsConnectedButton() {
        boolean isConnected = graph.isConnected();
        statusField.setText("Graph is " + (isConnected ? "connected" : "disconnected"));
    }

    // Handler to check if the graph has cycles
    private void handleHasCyclesButton() {
        boolean hasCycles = graph.hasCycles();
        statusField.setText("Graph " + (hasCycles ? "has" : "does not have") + " cycles");
    }

    // Handler to perform a depth-first search and output the visiting order
    private void handleDepthFirstSearchButton() {
        List<Vertex> result = graph.depthFirstSearch();
        statusField.setText("DFS Order: " + result);
    }

    // Handler to perform a breadth-first search and output the visiting order
    private void handleBreadthFirstSearchButton() {
        List<Vertex> result = graph.breadthFirstSearch();
        statusField.setText("BFS Order: " + result);
    }

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        System.out.println("Welcome to the Vertices Project!");
        System.out.println("Zaid Lozano ||| CMSC 315 ||| Project 4");
        launch(args);
    }
}
