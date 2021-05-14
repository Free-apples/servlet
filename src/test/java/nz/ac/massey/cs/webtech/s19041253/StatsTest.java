package nz.ac.massey.cs.webtech.s19041253;

import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class StatsTest {

    //todo write tests
    @Test
    void name() {
    }

    @Test
    void StatsGetTest() throws ServletException, IOException {
        Stats subject = new Stats();

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);

//        HttpSession mockOldSession = mock(HttpSession.class);
//        HttpSession mockNewSession = mock(HttpSession.class);
//        when(mockRequest.getSession()).thenReturn(mockOldSession,mockNewSession);
        PrintWriter printWriter = new PrintWriter(System.out);
        when(mockResponse.getWriter()).thenReturn(printWriter);

        subject.doGet(mockRequest, mockResponse);

        verify(mockResponse).setContentType("text/html;charset=UTF-8");
//        verify(mockOldSession).invalidate();
//        verify(mockNewSession).setAttribute("heading", "Black Jack");
//        verify(mockNewSession).setAttribute("description", "Welcome to BlackJack would you like to play a game?");
//
//        verify(mockResponse).sendRedirect(mockRequest.getContextPath() + "/blackjack.jsp");

    }
}