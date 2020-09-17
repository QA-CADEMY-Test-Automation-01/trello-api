package trello.api.get;

import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import trello.api.Board;
import trello.api.Lista;
import trello.api.RequestManager;

public class TrelloApiListGetTest {
    private Board board;
    private String idBoard;

    private Lista lista;
    private String idLista;

    @Before
    public void init(){
    //como prerequisito:
     // crear el board y crear lista
        Board newBoard = new Board();
        newBoard.setName("Board-2");
        board= RequestManager.post("/1/boards/",newBoard).as(Board.class);
        idBoard= board.getId();
        System.out.println("este idBoard: "+idBoard);

     // crea un lista con la Id Board
        Lista newLista = new Lista();
           newLista.setName("lista-2");
           newLista.setClosed(false);
           newLista.setId(idBoard);
           newLista.setIdBoard(idBoard);
           lista = RequestManager.post("/1/lists/",newLista).as(Lista.class);
          // idLista=lista.getId();


    }
    @Test
    public void testListGetWithJunit(){
         // con la Id lista obtenida
        Response response = RequestManager.get("/1/lists/"+lista.getId());

    }


}
