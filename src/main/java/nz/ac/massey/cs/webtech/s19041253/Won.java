package nz.ac.massey.cs.webtech.s19041253;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

@WebServlet(name = "Won", urlPatterns = {"/jack/won"})

//todo create file to save persistent data to
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
                    try {
                        JSONParser jsonParser = new JSONParser();
                        FileReader reader = (new FileReader("stats.json"));
                        Object obj = jsonParser.parse(reader);
                        JSONObject jsonObject = (JSONObject)obj;
                        Long gamesPlayed = (Long) jsonObject.get("gamesPlayed");
                        Long gamesWonByUser = (Long) jsonObject.get("gamesWonByUser");
                        JSONObject updateJsonObject = new JSONObject();
                        updateJsonObject.put("gamesPlayed", gamesPlayed + 1);
                        updateJsonObject.put("gamesWonByUser", userWon + gamesWonByUser);
                        File file = new File("./stats.json");
                        FileWriter fileWrite = new FileWriter(file);
                        fileWrite.write(updateJsonObject.toJSONString());
                        fileWrite.flush();
                    } catch(Exception e) {

                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("gamesPlayed", 1);
                        jsonObject.put("gamesWonByUser", userWon );
                        File file = new File("./stats.json");
                        FileWriter fileWrite = new FileWriter(file);
                        fileWrite.write(jsonObject.toJSONString());
                        fileWrite.flush();
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
                        out.println("Winner is: " + winner);
                        out.println("Computer hand value is: " + computerHand.getValue());
                        out.println("User hand value is: " + userHand.getValue());
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
