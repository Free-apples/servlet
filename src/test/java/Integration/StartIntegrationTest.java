package Integration;

import nz.ac.massey.cs.webtech.s19041253.Start;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartIntegrationTest {

    @Test
    public void StartIntegrationTest() throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8080/jack/start");
        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader
                (new InputStreamReader(
                        response.getEntity().getContent()));

        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            builder.append(line);
        }
        String html = builder.toString();

        System.out.println("html1 = " + html);

        assert response.getStatusLine().getStatusCode() == 200;
    }
    //Todo figure out why this doesn't work
    @Test
    public void StartPostIntegrationTest() throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:8080/jack/start");
        HttpResponse response = client.execute(post);

        BufferedReader rd = new BufferedReader
                (new InputStreamReader(
                        response.getEntity().getContent()));

        StringBuilder builder = new StringBuilder();
        String line = "";

        while ((line = rd.readLine()) != null) {
            builder.append(line);
            System.out.println(line);
        }
        String html2 = builder.toString();
        System.out.println("html2 = " + html2);
    }


}
