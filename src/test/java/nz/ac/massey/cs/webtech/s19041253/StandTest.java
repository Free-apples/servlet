package nz.ac.massey.cs.webtech.s19041253;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StandTest {

    @Test
    void doPostError() throws ServletException, IOException {
        Stand subject = new Stand();
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);

        HttpSession mockSession = mock(HttpSession.class);
        mockSession.invalidate();
        HttpSession newMockSession = mock(HttpSession.class);
        when(mockRequest.getSession()).thenReturn(newMockSession);


        subject.doPost(mockRequest, mockResponse);
        verify(mockResponse).setStatus(404);
        verify(mockResponse).setContentType("text/html;charset=UTF-8");
        verify(newMockSession).setAttribute("errorMessage", "error no game started");
    }

    @Test
    void doPostSuccess() throws ServletException, IOException {
        Stand subject = new Stand();
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpSession mockSession = mock(HttpSession.class);


        DeckOfCards deck = new DeckOfCards();
        CardHand userHand = new CardHand();
        CardHand computerHand = new CardHand();
        userHand.createHand(deck);

        userHand.createHand(deck);
        computerHand.createHand(deck);
        when(mockSession.getAttribute("turn")).thenReturn("user");
        when(mockSession.getAttribute("userHand")).thenReturn(userHand);
        when(mockSession.getAttribute("deck")).thenReturn(deck);
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockSession.getAttribute("computerHand")).thenReturn(computerHand);

        subject.doPost(mockRequest, mockResponse);

        verify(mockResponse).setContentType("text/html;charset=UTF-8");
        verify(mockSession).setAttribute("turn", "computer");

    }
}