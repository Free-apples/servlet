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


class possibleMovesTest {



    @Test
    void doGet() throws ServletException, IOException {
        possibleMoves subject = new possibleMoves();

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpSession mockSession = mock(HttpSession.class);

        when(mockRequest.getSession()).thenReturn(mockSession);
        DeckOfCards deck = new DeckOfCards();

        when(mockSession.getAttribute("deck")).thenReturn(deck);
        CardHand cardHand = mock(CardHand.class);
        when(cardHand.getValue()).thenReturn(20);
        when(mockSession.getAttribute("userHand")).thenReturn(cardHand);
        when(mockSession.getAttribute("turn")).thenReturn("user");
        PrintWriter mockPrintWriter = mock(PrintWriter.class);
        when(mockResponse.getWriter()).thenReturn(mockPrintWriter);

        subject.doGet(mockRequest, mockResponse);

        verify(mockResponse).setStatus(200);
        verify(mockPrintWriter).println("hit, stand");

    }


    @Test
    void doGetError() throws ServletException, IOException {
        possibleMoves subject = new possibleMoves();

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpSession mockSession = mock(HttpSession.class);

        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockSession.getAttribute("deck")).thenReturn(null);
        PrintWriter mockPrintWriter = mock(PrintWriter.class);
        when(mockResponse.getWriter()).thenReturn(mockPrintWriter);
        subject.doGet(mockRequest, mockResponse);
        verify(mockResponse).setStatus(404);
        verify(mockPrintWriter).println("start");

    }
}