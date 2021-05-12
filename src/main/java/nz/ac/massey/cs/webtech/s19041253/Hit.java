package nz.ac.massey.cs.webtech.s19041253;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nz.ac.massey.cs.webtech.s19041253.CardHand.*;

@WebServlet(name = "Hit", urlPatterns ={"/jack/move/hit"})
public class Hit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        if (session != null) {
            CardHand computerHand = (CardHand) session.getAttribute("computerHand");
            CardHand userHand = (CardHand) session.getAttribute("userHand");
            if (userHand != null) {
                DeckOfCards deck = (DeckOfCards) session.getAttribute("deck");
                userHand.addToHand(deck);
                session.setAttribute("deck", deck);
                String turn = (String) session.getAttribute("Turn");
                if (turn == "user" && userHand.getValue() <= 21) {
                    StringBuilder cardString = new StringBuilder(" ");
                    for (int i = 0; i < userHand.getSize(); i++) {
                        Card card = (Card) userHand.getCard(i);
                        cardString.append("Card " + i + ":");
                        cardString.append(card.getSuit() + " ");
                        cardString.append(card.getCardValue() + " ");
                    }

                    session.setAttribute("userHandString", "User Hand: " + cardString);
                    session.setAttribute("button", "<form action='/jack/move/stand' method='post'><input type='submit' value='Stand'>" +
                            " </form><form action='/jack/move/hit' method='post'> <input type='submit' value='Hit'></form>" +
                            "<a href='/jack/stats' class=button> Game Stats</a>");
                    response.sendRedirect(request.getContextPath() + "/blackjack.jsp");
                } else {
                    String message = "Unknown error";
                    if (turn != "user") {
                        response.setStatus(400);
                        session.setAttribute("errorMessage", "Error. It is not your turn");
                        response.sendRedirect(request.getContextPath() + "/error.jsp");
                    } else if (userHand.getValue() > 21) {
                        response.setStatus(400);
                        session.setAttribute("errorMessage", "Error. Your hand is over 21, you have already lost");
                        response.sendRedirect(request.getContextPath() + "/error.jsp");
                    }

                }
            }

            else {
                response.setStatus(404);
                request.setAttribute("errorMessage", "Error. Game not started");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }

        }
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

