package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Commande;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Sauvgarder")
public class Sauvgarder extends HttpServlet {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Update these constants with your MySQL database information
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/commandes";
    private static final String JDBC_USER = "root"; // Change to your PhpMyAdmin username
    private static final String JDBC_PASSWORD = ""; // Change to your PhpMyAdmin password

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the list of commandes from the application context
        List<Commande> commandes = (List<Commande>) getServletContext().getAttribute("commandes");

        // Implement logic to save commandes to the MySQL database and clear the list
        saveCommandesToDatabase(commandes);

        // Remove commandes from the application context
        getServletContext().removeAttribute("commandes");

        // Redirect to a success page or back to the menu
        response.sendRedirect("views/MenuPizza.html");
    }

    private void saveCommandesToDatabase(List<Commande> commandes) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // Insert commandes into the MySQL database
            String query = "INSERT INTO commandes (id, nom, prenom, adresse, totalPrice) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (Commande commande : commandes) {
                    // Set the values for each column in the PreparedStatement
                    preparedStatement.setString(1, commande.getId());
                    preparedStatement.setString(2, commande.getNom());
                    preparedStatement.setString(3, commande.getPrenom());
                    preparedStatement.setString(4, commande.getAdresse());
                    preparedStatement.setDouble(5, commande.getTotalPrice());

                    preparedStatement.executeUpdate(); // Execute the insert
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately for your application
        }
    }
}

