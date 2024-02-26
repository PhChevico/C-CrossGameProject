import java.util.List;

public class Board {
    private int sizeCols_board = 14;
    private int sizeRows_board = 9;
    private Square board[][] = new Square[sizeRows_board][sizeCols_board];
    private Zone zone;


    //that's how we define the zones in the board
    private Zone A = new Zone('A', List.of(board[4][0], board[3][0], board[3][1], board[3][2], board[4][2]));
    private Zone B = new Zone('B', List.of(board[4][1], board[5][1], board[5][2], board[5][3], board[4][3]));
    private Zone C = new Zone('C', List.of(board[7][1], board[6][1], board[6][2], board[6][3], board[7][2]));
    private Zone D = new Zone('D', List.of(board[7][2], board[8][2], board[8][3], board[8][4], board[7][4]));
    private Zone E = new Zone('E', List.of(board[3][3], board[2][3], board[2][4], board[2][5], board[3][5]));
    private Zone F = new Zone('F', List.of(board[3][4], board[4][4], board[4][5], board[4][6], board[3][6]));
    private Zone G = new Zone('G', List.of(board[6][4], board[5][4], board[5][5], board[5][6], board[6][6]));
    private Zone H = new Zone('H', List.of(board[4][0], board[3][0], board[3][1], board[3][2], board[4][2]));
    private Zone I = new Zone('I', List.of(board[2][6], board[1][6], board[1][5], board[1][4], board[2][4]));
    private Zone J = new Zone('J', List.of(board[2][7], board[3][7], board[3][8], board[3][9], board[2][9]));
    private Zone K = new Zone('K', List.of(board[5][7], board[4][7], board[4][8], board[4][9], board[5][9]));
    private Zone L = new Zone('L', List.of(board[5][8], board[6][8], board[6][9], board[6][10], board[5][10]));
    private Zone M = new Zone('M', List.of(board[1][9], board[0][9], board[0][10], board[0][11], board[1][11]));
    private Zone N = new Zone('N', List.of(board[1][10], board[2][10], board[2][11], board[2][12], board[1][12]));
    private Zone O = new Zone('O', List.of(board[4][10], board[3][10], board[3][11], board[3][12], board[4][12]));
    private Zone P = new Zone('P', List.of(board[4][11], board[5][11], board[5][12], board[5][13], board[4][13]));

    //to the continued






}
