package nz.ac.massey.cs.webtech.s19041253;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@WebServlet(name = "Stand", urlPatterns = {"/jack/move/stand"})
public class Stand extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        CardHand computerHand = (CardHand) session.getAttribute("computerHand");
        CardHand userHand = (CardHand) session.getAttribute("userHand");
        DeckOfCards deck = (DeckOfCards) session.getAttribute("deck");
        if (userHand != null) {
            while (computerHand.getValue() <= 17) {
                computerHand.addToHand(deck);
            }
            session.setAttribute("deck", deck);
            StringBuilder cardString = new StringBuilder(" ");
            for (int i = 0; i < userHand.getSize(); i++) {
                Card card = (Card) userHand.getCard(i);
                cardString.append("Card " + i + ":");
                cardString.append(card.getSuit() + " ");
                cardString.append(card.getCardValue() + " ");

            }

            StringBuilder computerString = new StringBuilder(" ");
            for (int i = 0; i < computerHand.getSize(); i++) {
                computerString.append("[Hidden]");
            }


            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet start</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet start at " + request.getContextPath() + "</h1>");
                out.println("<p>");
                out.println("Computer Hand: " + computerHand.getFirstCard().getSuit() + Integer.toString(computerHand.getFirstCard().getCardValue()) + computerString);
                out.println("User Hand: " + cardString);
                out.println("</p>");
                out.println("</body>");
                out.println("</form>");
                out.println("<form action='/jack/won' method='post'>");
                out.println("<input type='submit' value='See winner'>");
                out.println("</form>");
                out.println("<a href='/jack/stats' class=button> Game Stats</a>");
                out.println("</html>");
            }

        } else {
            try (PrintWriter out = response.getWriter()) {
                response.setStatus(404);
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet start</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<p>");
                out.println("Error, no game started");
                out.println("</p>");
                out.println("</body>");
                out.println("<button type='button'><a href='/jack/start'>Start</a> </button>");
                out.println("<a href='/jack/stats' class=button> Game Stats</a>");

                out.println("</html>");
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
