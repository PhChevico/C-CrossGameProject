import java.util.List;

public class Board {
    private int sizeCols_board = 13;
    private int sizeRows_board = 8;
    private Square board[][] = new Square[sizeRows_board][sizeCols_board];
    private Zone zone;


    //that's how we define the zones in the board
    private Zone A = new Zone('A', List.of(board[4][0], board[3][0], board[3][1], board[3][2], board[4][2]));
    private Zone B = new Zone('B', List.of(board[4][1], board[5][1], board[5][2], board[5][3], board[4][3]));
    //to the continued






}
