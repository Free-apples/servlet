package nz.ac.massey.cs.webtech.s19041253;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;


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

            session.setAttribute("computerHandString", "Computer Hand: " + computerHand.getFirstCard().getSuit() + computerHand.getFirstCard().getCardValue() + computerString);
            session.setAttribute("turn", "computer");
            response.sendRedirect(request.getContextPath() + "/blackjack.jsp");

        } else {

                response.setStatus(404);
                session.setAttribute("errorMessage", "error no game started");
                response.sendRedirect(request.getContextPath() + "/error.jsp");

            }

        }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
