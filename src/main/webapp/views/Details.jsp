<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.Commande" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Détails de la Commande</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Your custom CSS -->
    <link href="css/styles.css" rel="stylesheet">
    <style>
        /* Your custom styles here */
    </style>
</head>
<body>

	<!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">Détails de la Commande</a>
        </div>
    </nav>
    <div class="container">

        <% 
            List<Commande> commandes = (List<Commande>) request.getAttribute("commandes");
            if (commandes != null && !commandes.isEmpty()) {
                Commande lastCommande = commandes.get(commandes.size() - 1);
        %>
                <div class="card">
                    <div class="card-header">
                        Commande ID: <%= lastCommande.getId() %>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Informations sur la Commande</h5>
                        <p class="card-text">Nom: <%= lastCommande.getNom() %></p>
                        <p class="card-text">Prénom: <%= lastCommande.getPrenom() %></p>
                        <p class="card-text">Adresse: <%= lastCommande.getAdresse() %></p>
                        
                        <h5 class="card-title">Pizzas commandées :</h5>
                        <ul>
                            <% 
                                List<models.Pizza> pizzas = lastCommande.getPizzas();
                                if (pizzas != null && !pizzas.isEmpty()) {
                                    for (models.Pizza pizza : pizzas) {
                            %>
                                        <li><%= pizza.getNom() %> - <%= pizza.getPrix() %> €</li>
                            <%
                                    }
                                } else {
                            %>
                                    <li>Aucune pizza commandée</li>
                            <%  
                                }
                            %>
                        </ul>
                        
                        <h5 class="card-title">Boissons commandées :</h5>
                        <ul>
                            <% 
                                List<models.Boisson> boissons = lastCommande.getBoissons();
                                if (boissons != null && !boissons.isEmpty()) {
                                    for (models.Boisson boisson : boissons) {
                            %>
                                        <li><%= boisson.getNom() %> - <%= boisson.getPrix() %> €</li>
                            <%
                                    }
                                } else {
                            %>
                                    <li>Aucune boisson commandée</li>
                            <%  
                                }
                            %>
                        </ul>
                        
                        <p class="card-text">Prix Total: <%= lastCommande.getTotalPrice() %></p>
                        <!-- Display other details of the last commande as needed -->
                    </div>
                </div>
                <br>
        <%  } else {
        %>
                <p>Aucune commande trouvée.</p>
        <%  }
        %>

        <!-- Other content as needed -->
    </div>

    <!-- Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
