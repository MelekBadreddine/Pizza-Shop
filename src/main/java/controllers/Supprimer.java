package controllers;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.*;

@WebServlet("/Supprimer")
public class Supprimer extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        String nom = request.getParameter("nom");
        String type = request.getParameter("type");

        if (type != null && nom != null) {
            if (type.equals("pizza")) {
                removePizzaFromSession(session, nom);
            } else if (type.equals("boisson")) {
                removeBoissonFromSession(session, nom);
            }
        }

        // Redirect back to the same page to update the content after deletion
        response.sendRedirect("views/Affiche.jsp");
    }

    private void removePizzaFromSession(HttpSession session, String nom) {
        List<models.Pizza> pizzas = (List<models.Pizza>) session.getAttribute("pizzas");
        if (pizzas != null) {
            for (Iterator<models.Pizza> iterator = pizzas.iterator(); iterator.hasNext();) {
                models.Pizza pizza = iterator.next();
                if (pizza.getNom().equals(nom)) {
                    iterator.remove();
                    break; // Exit loop once the item is removed
                }
            }
            session.setAttribute("pizzas", pizzas);
        }
    }


    private void removeBoissonFromSession(HttpSession session, String nom) {
        List<models.Boisson> boissons = (List<models.Boisson>) session.getAttribute("boissons");
        if (boissons != null) {
            for (Iterator<models.Boisson> iterator = boissons.iterator(); iterator.hasNext();) {
                models.Boisson boisson = iterator.next();
                if (boisson.getNom().equals(nom)) {
                    iterator.remove();
                    break; // Exit loop once the item is removed
                }
            }
            session.setAttribute("boissons", boissons);
        }
    }
}
