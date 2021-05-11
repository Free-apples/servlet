package nz.ac.massey.cs.webtech.s19041253;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileReader;

@WebServlet(name = "Stats", urlPatterns = {"/jack/stats"})
public class Stats extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                JSONParser jsonParser = new JSONParser();
                FileReader reader = (new FileReader("stats.json"));
                Object obj = jsonParser.parse(reader);
                JSONObject jsonobj = (JSONObject) obj;
                Long gamesPlayed = (Long) jsonobj.get("gamesPlayed");
                Long gamesWonByUser = (Long) jsonobj.get("gamesWonByUser");
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Stats</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>In stats file read</h1>");
                out.println("games played: " + gamesPlayed);
                out.println("Games won by user" + gamesWonByUser);
                out.println("</body");
            } catch (Exception e) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Stats</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>In stats- no file read</h1>");
                out.println("</body");

            }
        }catch(Exception e){

        }
    }
}
