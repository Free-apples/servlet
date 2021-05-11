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

@WebServlet(name = "Start", urlPatterns = {"/jack/start"})
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
            HttpSession oldSess = request.getSession();
            oldSess.invalidate();
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

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet start</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet start at " + request.getContextPath() + "</h1>");
            out.println("<p>");
            out.println("Computer Hand: [] " + computerHand.getFirstCard().getSuit() + Integer.toString(computerHand.getFirstCard().getRank()));
            out.println("</p>");
            out.println("<p>");
            out.println("User Hand: " + cardString);
            out.println("</p>");
            out.println("</body>");
            out.println("<form action='/jack/move/stand' method='post'>");
            out.println("<input type='submit' value='Stand'>");
            out.println("</form>");
            out.println("<form action='/jack/move/hit' method='post'>");
            out.println("<input type='submit' value='Hit'>");
            out.println("</form>");
            out.println("<a href='/jack/stats' class=button> Game Stats</a>");
            out.println("</html>");

        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet start</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet start at " + request.getContextPath() + "</h1>");
            out.println("<p>");
            out.println("Start new game");
            out.println("</p>");
            out.println("<form action='/jack/start' method='post'>");
            out.println("<input type='submit' value='Start'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }

    }
}
