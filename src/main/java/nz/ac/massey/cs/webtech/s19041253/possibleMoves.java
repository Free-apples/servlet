package nz.ac.massey.cs.webtech.s19041253;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class possibleMoves extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        if (session.getAttribute("deck") != null){
            response.setStatus(200);
            String possibleMoves = " ";
            CardHand userHand = (CardHand) session.getAttribute("userHand");
            if(userHand.getValue() <= 21 && session.getAttribute("turn") == "user"){
                possibleMoves = "hit, stand";
            }
            else{
                possibleMoves = "won";
            }

                out.println(possibleMoves);




        }else{
            response.setStatus(404);


                out.println("start");



        }
        out.close();
    }
}
