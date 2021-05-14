package nz.ac.massey.cs.webtech.s19041253;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StartTest {


    @Test
    void doPost() throws ServletException, IOException {
        Start subject = new Start();
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);

        HttpSession mockSession = mock(HttpSession.class);
        when(mockRequest.getSession()).thenReturn(mockSession);

        subject.doPost(mockRequest, mockResponse);
        verify(mockResponse).setContentType("text/html;charset=UTF-8");
        verify(mockSession).setAttribute("turn", "user");
        verify(mockSession).setAttribute("winner", null);
        verify(mockSession).setAttribute("heading", "Black Jack");
        verify(mockSession).setAttribute("description", "Please choose whether to hit or stand");
        verify(mockResponse).sendRedirect(mockRequest.getContextPath() + "/blackjack.jsp");
        }


    @Test
    void doGet() throws ServletException, IOException {
        Start subject = new Start();

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);

        HttpSession mockOldSession = mock(HttpSession.class);
        HttpSession mockNewSession = mock(HttpSession.class);
        when(mockRequest.getSession()).thenReturn(mockOldSession,mockNewSession);

        subject.doGet(mockRequest, mockResponse);

        verify(mockResponse).setContentType("text/html;charset=UTF-8");
        verify(mockOldSession).invalidate();
        verify(mockNewSession).setAttribute("heading", "Black Jack");
        verify(mockNewSession).setAttribute("description", "Welcome to BlackJack would you like to play a game?");

        verify(mockResponse).sendRedirect(mockRequest.getContextPath() + "/blackjack.jsp");


    }
}