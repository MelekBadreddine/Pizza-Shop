<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.Commande" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Commandes</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Your custom CSS -->
    <link href="css/styles.css" rel="stylesheet">
    <style>
        /* Your custom styles here */
    </style>
</head>
<body>
    <div class="container">
        <h1>Liste des Commandes</h1>

        <table class="table">
            <thead>
                <tr>
                    <th>Commande ID</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Adresse</th>
                    <th>Prix Total</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Commande> commandes = (List<Commande>) application.getAttribute("commandes");
                    if (commandes != null && !commandes.isEmpty()) {
                        for (Commande commande : commandes) {
                %>
                            <tr>
                                <td><%= commande.getId() %></td>
                                <td><%= commande.getNom() %></td>
                                <td><%= commande.getPrenom() %></td>
                                <td><%= commande.getAdresse() %></td>
<td><a href="http://localhost:8082/Project/views/Details.jsp?Commande=CommandId"<%= commande.getId() %>"><%= commande.getTotalPrice() %></a></td>
                            </tr>
                <%
                        }
                    } else {
                %>
                        <tr>
                            <td colspan="5">Aucune commande trouvée.</td>
                        </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <!-- Other content as needed -->
        <form action="http://localhost:8082/Project/Sauvgarder" method="post">
            <button type="submit" class="btn btn-primary mt-3">Sauvegarder</button>
        </form>
    </div>

    <!-- Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
