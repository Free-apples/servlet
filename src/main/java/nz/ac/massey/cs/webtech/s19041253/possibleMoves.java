package nz.ac.massey.cs.webtech.s19041253;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class possibleMoves extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userHand") != null){
            String possibleMoves = " ";
            CardHand userHand = (CardHand) session.getAttribute("userHand");
            if(userHand.getValue() <= 21 && session.getAttribute("turn") == "user"){
                possibleMoves = "hit, stand";
            }
            else{
                possibleMoves = "won";
            }
            session.setAttribute("possibleMoves", possibleMoves);
        }else{
            response.setStatus(404);
            session.setAttribute("errorMessage", "No game started");
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
