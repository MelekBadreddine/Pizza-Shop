<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Afficher ma commande</title>
    <!-- Link Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Your custom CSS -->
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>
	<!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">Votre Commande</a>
        </div>
    </nav>
    
    <div class="container">

        <h2>Pizzas:</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Quantité</th>
                    <th>Prix unitaire</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    Object pizzasObj = session.getAttribute("pizzas");
                    if (pizzasObj != null && pizzasObj instanceof java.util.List) {
                        java.util.List<models.Pizza> pizzas = (java.util.List<models.Pizza>) pizzasObj;
                        java.util.HashMap<String, models.Pizza> pizzaMap = new java.util.HashMap<String, models.Pizza>();
                        for (models.Pizza pizza : pizzas) {
                            if (pizzaMap.containsKey(pizza.getNom())) {
                                // If pizza already exists, update quantity and price
                                models.Pizza existingPizza = pizzaMap.get(pizza.getNom());
                                existingPizza.setQuantite(existingPizza.getQuantite() + 1);
                                existingPizza.setPrix(existingPizza.getPrix() + pizza.getPrix());
                            } else {
                                pizzaMap.put(pizza.getNom(), pizza);
                            }
                        }
                        // Display the updated list
                        for (models.Pizza pizza : pizzaMap.values()) {
                %>
                            <tr>
                                <td><%= pizza.getNom() %></td>
                                <td><%= pizza.getQuantite() %></td>
                                <td>$<%= pizza.getPrix() %></td>
                                <td>
				                    <form action="http://localhost:8082/Project/Supprimer" method="post">
				                        <input type="hidden" name="nom" value="<%= pizza.getNom() %>">
				                        <input type="hidden" name="type" value="pizza">
				                        <button type="submit" class="btn btn-danger">Supprimer</button>
				                    </form>
				                </td>
                            </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>

		 <h2>Boissons:</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Quantité</th>
                    <th>Prix unitaire</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    Object boissonsObj = session.getAttribute("boissons");
                    if (boissonsObj != null && boissonsObj instanceof java.util.List) {
                        java.util.List<models.Boisson> boissons = (java.util.List<models.Boisson>) boissonsObj;
                        java.util.HashMap<String, models.Boisson> boissonMap = new java.util.HashMap<String, models.Boisson>();
                        for (models.Boisson boisson : boissons) {
                            if (boissonMap.containsKey(boisson.getNom())) {
                                // If boisson already exists, update quantity and price
                                models.Boisson existingBoisson = boissonMap.get(boisson.getNom());
                                existingBoisson.setQuantite(existingBoisson.getQuantite() + 1);
                                existingBoisson.setPrix(existingBoisson.getPrix() + boisson.getPrix());
                            } else {
                                boissonMap.put(boisson.getNom(), boisson);
                            }
                        }
                        // Display the updated list
                        for (models.Boisson boisson : boissonMap.values()) {
                %>
                            <tr>
                                <td><%= boisson.getNom() %></td>
                                <td><%= boisson.getQuantite() %></td>
                                <td>$<%= boisson.getPrix() %></td>
                                <td>
				                    <form action="http://localhost:8082/Project/Supprimer" method="post">
				                        <input type="hidden" name="nom" value="<%= boisson.getNom() %>">
				                        <input type="hidden" name="type" value="boisson">
				                        <button type="submit" class="btn btn-danger">Supprimer</button>
				                    </form>
				                </td>
                            </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>

        <h2 class="total">Total:</h2>
        <div id="totalPrice">
            <% 
                double totalPrice = 0.0;
                if (pizzasObj != null && pizzasObj instanceof java.util.List) {
                    java.util.List<models.Pizza> pizzas = (java.util.List<models.Pizza>) pizzasObj;
                    for (models.Pizza pizza : pizzas) {
                        totalPrice += pizza.getPrix()*pizza.getQuantite();
                    }
                }
                if (boissonsObj != null && boissonsObj instanceof java.util.List) {
                    java.util.List<models.Boisson> boissons = (java.util.List<models.Boisson>) boissonsObj;
                    for (models.Boisson boisson : boissons) {
                        totalPrice += boisson.getPrix()*boisson.getQuantite();
                    }
                }
            %>
            <p>Total Price: $<%= totalPrice %></p>
        </div>
        
        <!-- Buttons for "Valider" and "Afficher le menu" -->
    <div class="row mt-3">
        <div class="col-auto">
            <form action="Formulaire.html" method="post">
                <button type="submit" class="btn btn-success">Valider</button>
            </form>
        </div>
        <div class="col-auto">
            <form action="MenuPizza.html">
                <button type="submit" class="btn btn-primary">Afficher le menu</button>
            </form>
        </div>
        
    </div>
    </div>
  
    

    <!-- Link Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
