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
        if (session != null){
            CardHand computerHand = (CardHand) session.getAttribute("computerHand");
            CardHand userHand = (CardHand) session.getAttribute("userHand");
            if(userHand != null){
                DeckOfCards deck = (DeckOfCards) session.getAttribute("deck");
                userHand.addToHand(deck);
                session.setAttribute("deck", deck);
                String turn = (String) session.getAttribute("Turn");
                if (turn == "user" && userHand.getValue() <= 21){
                    StringBuilder cardString = new StringBuilder(" ");
                    for (int i = 0; i < userHand.getSize(); i++) {
                        Card card = (Card) userHand.getCard(i);
                        cardString.append("Card " + i + ":");
                        cardString.append(card.getSuit()+ " ");
                        cardString.append(card.getCardValue()+ " ");
                    }

                    try (PrintWriter out = response.getWriter()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet start</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Servlet start at " + request.getContextPath() + "</h1>");
                        out.println("<p>");
                        out.println("Computer Hand: [] " + computerHand.getFirstCard().getSuit() + computerHand.getFirstCard().getCardValue());
                        out.println("User Hand: " + cardString);
                        out.println("User hand value " + userHand.getValue());
                        out.println("</p>");
                        out.println("</body>");
                        out.println("</form>");
                        out.println("<form action='/jack/move/hit' method='post'>");
                        out.println("<input type='submit' value='Hit'>");
                        out.println("</form>");
                        out.println("</form>");
                        out.println("<form action='/jack/move/stand' method='post'>");
                        out.println("<input type='submit' value='Stand'>");
                        out.println("</form>");
                        out.println("<a href='/jack/stats' class=button> Game Stats</a>");
                        out.println("</html>");
                    }
                }
                else{
                    String message = "Unknown error";
                    if(turn != "user"){
                        response.setStatus(400);
                        message = "Error. It is not your turn";
                    }
                    else if(userHand.getValue() > 21){
                        response.setStatus(400);
                        message = "Error. Your hand is over 21, you have already lost";

                    }
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet start</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<p>" + message + "<p>");
                        out.println("</body>");
                        out.println("<form action='/jack/move/stand' method='post'>");
                        out.println("<input type='submit' value='Stand'>");
                        out.println("</form>");
                        out.println("<a href='/jack/stats' class=button> Game Stats</a>");

                        out.println("</html>");
                    }
                }
            }
            //}
            else{
                response.setStatus(404);
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet start</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<p> Error, session not started<p> ");
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

            }}
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
