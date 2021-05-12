package nz.ac.massey.cs.webtech.s19041253;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;

public class Hit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        if (session != null) {
            //CardHand computerHand = (CardHand) session.getAttribute("computerHand");
            CardHand userHand = (CardHand) session.getAttribute("userHand");
            if (userHand != null) {
                DeckOfCards deck = (DeckOfCards) session.getAttribute("deck");
                userHand.addToHand(deck);
                session.setAttribute("deck", deck);
                String turn = (String) session.getAttribute("turn");
                if (turn.equals("user") && userHand.getValue() <= 21) {
                    StringBuilder cardString = new StringBuilder(" ");
                    for (int i = 0; i < userHand.getSize(); i++) {
                        Card card = (Card) userHand.getCard(i);
                        cardString.append("Card ").append(i).append(":");
                        cardString.append(card.getSuit()).append(" ");
                        cardString.append(card.getCardValue()).append(" ");
                    }
                    session.setAttribute("userHandString", "User Hand: " + cardString);
                    session.setAttribute("button", "<form action='/jack/move/stand' method='post'><input type='submit' value='Stand'>" +
                            " </form><form action='/jack/move/hit' method='post'> <input type='submit' value='Hit'></form>" +
                            " </form><form action='/jack/won' method='post'> <input type='submit' value='See winner'></form>" +
                            "<a href='/jack/stats' class=button> Game Stats</a>");
                    response.sendRedirect(request.getContextPath() + "/blackjack.jsp");
                } else {
                    String message = "Unknown error";
                    if (!turn.equals("user")) {
                        message = "Error. It is not your turn";
                    } else if (userHand.getValue() > 21) {
                        message ="Error. Your hand is over 21, you have already lost";
                    }
                response.setStatus(400);
                session.setAttribute("errorMessage", message);
                response.sendRedirect(request.getContextPath() + "/error.jsp");
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

