// Zaid Lozano
// CMSC 315 Project 4
// This class represents a vertex in the graph with properties such as name and coordinates.
// It includes methods to access these properties and overrides equals, hashCode, and toString
// for correct usage in data structures and displays.

import java.util.Objects;

public class Vertex {
    // Holds the name of the vertex, used as an identifier
    private String name;
    // X-coordinate of the vertex on the graphical interface
    private double x;
    // Y-coordinate of the vertex on the graphical interface
    private double y;

    // Constructor initializes the vertex with a name, x-coordinate, and y-coordinate
    public Vertex(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    // Overrides the equals method to compare vertices based on their name
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Checks if the object references the same
        if (o == null || getClass() != o.getClass()) return false; // Checks for null and compares class types
        Vertex vertex = (Vertex) o;
        return Objects.equals(name, vertex.name); // Compares based on vertex name
    }

    // Generates hash code based on vertex's name
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // Returns the name of the vertex
    public String getName() {
        return name;
    }

    // Returns the x-coordinate of the vertex
    public double getX() {
        return x;
    }

    // Returns the y-coordinate of the vertex
    public double getY() {
        return y;
    }

    // Returns the vertex's name for display purposes
    @Override
    public String toString() {
        return name; // Uses name as the string representation of the vertex
    }
}
