package nz.ac.massey.cs.webtech.s19041253;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;
import nz.ac.massey.cs.webtech.s19041253.CardHand.*;

public class Start extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DeckOfCards deck = new DeckOfCards();
            deck.Shuffle();
            CardHand userHand = new CardHand();
            CardHand computerHand = new CardHand();
            userHand.createHand(deck);
            computerHand.createHand(deck);
            HttpSession session = request.getSession();

            session.setAttribute("Turn", "user");
            session.setAttribute("userHand", userHand);
            session.setAttribute("computerHand", computerHand);
            session.setAttribute("deck", deck);
            session.setAttribute("winner", null);
            StringBuilder cardString = new StringBuilder(" ");
            for (int i = 0; i < userHand.getSize(); i++) {
                Card card = (Card) userHand.getCard(i);
                cardString.append("Card " + i + ":");
                cardString.append(card.getSuit()+ " ");
                cardString.append(card.getRank()+ " ");
            }

            session.setAttribute("computerHandString", "Computer Hand: [] " + computerHand.getFirstCard().getSuit() + Integer.toString(computerHand.getFirstCard().getRank()));
            session.setAttribute("userHandString", "User Hand: " + cardString );
            session.setAttribute("button", "<form action='/jack/move/stand' method='post'><input type='submit' value='Stand'>" +
                   " </form><form action='/jack/move/hit' method='post'> <input type='submit' value='Hit'></form>" +
                    "<a href='/jack/stats' class=button> Game Stats</a>" );
            response.sendRedirect(request.getContextPath() + "/blackjack.jsp");

        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession oldSess = request.getSession();
        oldSess.invalidate();
        HttpSession session = request.getSession();

            session.setAttribute("heading", "Black Jack");
            session.setAttribute("description", "Welcome to BlackJack would you like to play a game?");
            session.setAttribute("button", "<form action='/jack/start' method='post'><input type='submit' value='Start new game'>" +
                    "<a href='/jack/stats' class=button> Game Stats</a>");
            response.sendRedirect(request.getContextPath() + "/blackjack.jsp");

        }

    }

