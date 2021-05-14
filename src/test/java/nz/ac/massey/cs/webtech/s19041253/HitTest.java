package nz.ac.massey.cs.webtech.s19041253;

import org.junit.jupiter.api.Test;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static org.mockito.Mockito.*;

class HitTest {

    @Test
    void doPost() throws ServletException, IOException {
        Hit subject = new Hit();
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpSession mockSession = mock(HttpSession.class);
        DeckOfCards deck = new DeckOfCards();
        CardHand userHand = new CardHand();
        CardHand computerHand = new CardHand();
        userHand.createHand(deck);

        StringBuilder cardString = new StringBuilder(" ");
        for (int i = 0; i < userHand.getSize(); i++) {
            Card card = (Card) userHand.getCard(i);
            cardString.append("Card ").append(i).append(":");
            cardString.append(card.getSuit()).append(" ");
            cardString.append(card.getCardValue()).append(" ");
        }

        userHand.createHand(deck);
        computerHand.createHand(deck);
        when(mockSession.getAttribute("turn")).thenReturn("user");
        when(mockSession.getAttribute("userHand")).thenReturn(userHand);
        when(mockSession.getAttribute("deck")).thenReturn(deck);
        when(mockRequest.getSession(false)).thenReturn(mockSession);
        when(mockSession.getAttribute("computerHand")).thenReturn(computerHand);

        subject.doPost(mockRequest, mockResponse);

        verify(mockResponse).setContentType("text/html;charset=UTF-8");
        verify(mockResponse).sendRedirect(mockRequest.getContextPath() + "/blackjack.jsp");

    }

    @Test
    void doPostError404() throws ServletException, IOException {
        Hit subject = new Hit();
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        when(mockRequest.getSession(false)).thenReturn(null);



        subject.doPost(mockRequest, mockResponse);

        verify(mockResponse).setStatus(404);
        verify(mockRequest).setAttribute("errorMessage", "Error. Game not started");
        verify(mockResponse).setContentType("text/html;charset=UTF-8");

        verify(mockResponse).sendRedirect(mockRequest.getContextPath() + "/error.jsp");

    }

    @Test
    void doPostError400() throws ServletException, IOException {
        DeckOfCards deck = new DeckOfCards();
        CardHand userHand = new CardHand();
        CardHand computerHand = new CardHand();
        userHand.createHand(deck);

        StringBuilder cardString = new StringBuilder(" ");
        for (int i = 0; i < userHand.getSize(); i++) {
            Card card = (Card) userHand.getCard(i);
            cardString.append("Card ").append(i).append(":");
            cardString.append(card.getSuit()).append(" ");
            cardString.append(card.getCardValue()).append(" ");
        }

        userHand.createHand(deck);
        computerHand.createHand(deck);


        Hit subject = new Hit();
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpSession mockSession = mock(HttpSession.class);
        when(mockRequest.getSession(false)).thenReturn(mockSession);
        when(mockSession.getAttribute("turn")).thenReturn("computer");
        when(mockSession.getAttribute("userHand")).thenReturn(userHand);
        when(mockSession.getAttribute("deck")).thenReturn(deck);
        when(mockSession.getAttribute("computerHand")).thenReturn(computerHand);


        subject.doPost(mockRequest, mockResponse);

        verify(mockResponse).setStatus(400);
        verify(mockResponse).sendRedirect(mockRequest.getContextPath() + "/error.jsp");

    }

    @Test
    void doGet() {
    }
}