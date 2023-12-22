package controllers;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Boisson;
import models.Pizza;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/Ajouter")
public class Ajouter extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        String[] selectedPizzas = request.getParameterValues("pizza");
        if (selectedPizzas != null && selectedPizzas.length > 0) {
            for (String pizza : selectedPizzas) {
                if (pizza.equals("Margherita")) {
                    addPizzaToSession(session, "Margherita", 10.99, 1); // Modify parameters as needed
                } else if (pizza.equals("Pepperoni")) {
                    addPizzaToSession(session, "Pepperoni", 12.99, 1); // Modify parameters as needed
                } else if (pizza.equals("Hawaiian")) {
                    addPizzaToSession(session, "Hawaiian", 11.99, 1); // Modify parameters as needed
                } else if (pizza.equals("Vegetarian")) {
                    addPizzaToSession(session, "Vegetarian", 13.99, 1); // Modify parameters as needed
                }
                // Add logic to handle other pizza types if needed
            }
            response.sendRedirect("views/MenuPizza.html"); // Redirect to MenuPizza.html after processing pizzas
        }

        String[] selectedArticles = request.getParameterValues("article");
        if (selectedArticles != null && selectedArticles.length > 0) {
            for (String article : selectedArticles) {
                if (article.equals("Soda")) {
                    addBoissonToSession(session, "Soda", 2.99, 1); // Modify parameters as needed
                } else if (article.equals("Juice")) {
                    addBoissonToSession(session, "Juice", 3.99, 1); // Modify parameters as needed
                } else if (article.equals("Water")) {
                    addBoissonToSession(session, "Water", 1.99, 1); // Modify parameters as needed
                } else if (article.equals("Smoothie")) {
                    addBoissonToSession(session, "Smoothie", 4.99, 1); // Modify parameters as needed
                }
                // Add logic to handle other beverage types if needed
            }
            response.sendRedirect("views/MenuBoisson.html"); // Redirect to MenuBoisson.html after processing beverages
        }
    }

    private void addPizzaToSession(HttpSession session, String nom, double prix, int quantite) {
        List<Pizza> pizzas = (List<Pizza>) session.getAttribute("pizzas");
        if (pizzas != null) {
            for (Pizza pizza : pizzas) {
                if (pizza.getNom().equals(nom)) {
                    // Update quantity and price of existing pizza
                    pizza.setQuantite(pizza.getQuantite() + quantite);
                    pizza.setPrix(prix);
                    return;
                }
            }
        }
        // Add new pizza if it doesn't already exist
        Pizza newPizza = new Pizza(nom, prix, quantite);
        if (pizzas == null) {
            pizzas = new ArrayList<>();
        }
        pizzas.add(newPizza);
        session.setAttribute("pizzas", pizzas);
    }

    private void addBoissonToSession(HttpSession session, String nom, double prix, int quantite) {
        List<Boisson> boissons = (List<Boisson>) session.getAttribute("boissons");
        if (boissons != null) {
            for (Boisson boisson : boissons) {
                if (boisson.getNom().equals(nom)) {
                    // Update quantity and price of existing beverage
                    boisson.setQuantite(boisson.getQuantite() + quantite);
                    boisson.setPrix(prix);
                    return;
                }
            }
        }
        // Add new beverage if it doesn't already exist
        Boisson newBoisson = new Boisson(nom, prix, quantite);
        if (boissons == null) {
            boissons = new ArrayList<>();
        }
        boissons.add(newBoisson);
        session.setAttribute("boissons", boissons);
    }
}
