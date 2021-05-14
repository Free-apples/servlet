package nz.ac.massey.cs.webtech.s19041253;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;



public class Won extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession(false);
            if (session != null){
                CardHand computerHand = (CardHand) session.getAttribute("computerHand");
                CardHand userHand = (CardHand) session.getAttribute("userHand");
                int userWon = 0;
                if (userHand != null){
                    String winner = "No one, it's a draw!";
                    if ( userHand.getValue() <= 21 && (userHand.getValue() > computerHand.getValue() || computerHand.getValue() > 21)) {
                        winner = "User!";
                        session.setAttribute("winner", "user");
                        userWon = 1;
                    }
                    else if(computerHand.getValue() <= 21 && (userHand.getValue() < computerHand.getValue() || userHand.getValue() > 21)){
                        winner = "Computer";
                    }

                    //using stats data
                    StatsData statsData = StatsData.getInstance();
                    statsData.incrementGames();
                    if (userWon == 1) {
                        statsData.incrementWins();
                    }
                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet won</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Servlet won at " + request.getContextPath() + "</h1>");
                        out.println("<p>Winner is: " + winner +"</p>");
                        out.println("<p>Computer hand value is: " + computerHand.getValue() + "</p>");
                        out.println("<p>User hand value is: " + userHand.getValue()+ "</p>");
                        out.println("</body>");
                        out.println("</html>");

                    }
                }
            }
            else{
                try (PrintWriter out = response.getWriter()) {
                    response.setStatus(404);
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet won</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Servlet won at " + request.getContextPath() + "</h1>");
                    out.println("Error, no game found");
                    out.println("</body>");
                    out.println("</html>");
                }

            }
        }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
