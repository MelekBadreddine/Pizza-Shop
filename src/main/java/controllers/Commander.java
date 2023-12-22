package controllers;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Commande;

import java.io.IOException;
import java.util.*;

@WebServlet("/Commander")
public class Commander extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        // Retrieve user input from the Form
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adresse = request.getParameter("adresse");

        // Retrieve pizzas and boissons from session
        List<models.Pizza> pizzas = (List<models.Pizza>) session.getAttribute("pizzas");
        List<models.Boisson> boissons = (List<models.Boisson>) session.getAttribute("boissons");

        // Calculate total price
        double totalPrice = calculateTotalPrice(pizzas, boissons);

        // Create a Commande bean
        Commande commande = new Commande(UUID.randomUUID().toString(), nom, prenom, adresse, pizzas, boissons, totalPrice);

        // Store Commande bean in the application context
        ServletContext context = request.getServletContext();
        List<Commande> commandes = (List<Commande>) context.getAttribute("commandes");
        if (commandes == null) {
            commandes = new ArrayList<>();
        }
        commandes.add(commande);
        context.setAttribute("commandes", commandes);

        // After creating the Commande object
        request.setAttribute("commandes", commandes);
        // Forward to Details.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/Details.jsp");
        dispatcher.forward(request, response);

    }

    private double calculateTotalPrice(List<models.Pizza> pizzas, List<models.Boisson> boissons) {
        double totalPrice = 0.0;
        if (pizzas != null) {
            for (models.Pizza pizza : pizzas) {
                totalPrice += pizza.getPrix()*pizza.getQuantite();
            }
        }
        if (boissons != null) {
            for (models.Boisson boisson : boissons) {
                totalPrice += boisson.getPrix()*boisson.getQuantite();
            }
        }
        return totalPrice;
    }
}
