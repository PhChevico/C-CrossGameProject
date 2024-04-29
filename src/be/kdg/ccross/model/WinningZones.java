package be.kdg.ccross.model;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinningZones {
    public List<List<Zone>> getWinningZones(Board board){//declare all possible winning possibilities-->more to add
        List<List<Zone>> zone = new ArrayList<>();

        zone.add(Arrays.asList(board.getZone('A'), board.getZone('B'), board.getZone('C'), board.getZone('D')));
        zone.add(Arrays.asList(board.getZone('E'), board.getZone('F'), board.getZone('G'), board.getZone('H')));
        zone.add(Arrays.asList(board.getZone('I'), board.getZone('J'), board.getZone('K'), board.getZone('L')));
        zone.add(Arrays.asList(board.getZone('M'), board.getZone('N'), board.getZone('O'), board.getZone('P')));
        zone.add(Arrays.asList(board.getZone('A'), board.getZone('E'), board.getZone('I'), board.getZone('M')));
        zone.add(Arrays.asList(board.getZone('B'), board.getZone('F'), board.getZone('J'), board.getZone('N')));
        zone.add(Arrays.asList(board.getZone('C'), board.getZone('G'), board.getZone('K'), board.getZone('O')));
        zone.add(Arrays.asList(board.getZone('D'), board.getZone('H'), board.getZone('L'), board.getZone('P')));
        zone.add(Arrays.asList(board.getZone('A'), board.getZone('B'), board.getZone('G'), board.getZone('H')));
        zone.add(Arrays.asList(board.getZone('E'), board.getZone('F'), board.getZone('K'), board.getZone('L')));
        zone.add(Arrays.asList(board.getZone('I'), board.getZone('J'), board.getZone('O'), board.getZone('P')));
        zone.add(Arrays.asList(board.getZone('M'), board.getZone('J'), board.getZone('F'), board.getZone('B')));
        zone.add(Arrays.asList(board.getZone('I'), board.getZone('J'), board.getZone('O'), board.getZone('P')));
        zone.add(Arrays.asList(board.getZone('E'), board.getZone('F'), board.getZone('K'), board.getZone('L')));
        zone.add(Arrays.asList(board.getZone('A'), board.getZone('B'), board.getZone('G'), board.getZone('H')));
        zone.add(Arrays.asList(board.getZone('E'), board.getZone('B'), board.getZone('C'), board.getZone('D')));
        zone.add(Arrays.asList(board.getZone('A'), board.getZone('B'), board.getZone('G'), board.getZone('H')));
        zone.add(Arrays.asList(board.getZone('E'), board.getZone('F'), board.getZone('K'), board.getZone('L')));
        zone.add(Arrays.asList(board.getZone('E'), board.getZone('F'), board.getZone('G'), board.getZone('D')));
        zone.add(Arrays.asList(board.getZone('E'), board.getZone('B'), board.getZone('C'), board.getZone('D')));
        zone.add(Arrays.asList(board.getZone('E'), board.getZone('F'), board.getZone('K'), board.getZone('F')));
        zone.add(Arrays.asList(board.getZone('A'), board.getZone('B'), board.getZone('G'), board.getZone('D')));
        zone.add(Arrays.asList(board.getZone('M'), board.getZone('I'), board.getZone('E'), board.getZone('A')));
        zone.add(Arrays.asList(board.getZone('M'), board.getZone('J'), board.getZone('K'), board.getZone('L')));
        zone.add(Arrays.asList(board.getZone('M'), board.getZone('J'), board.getZone('K'), board.getZone('H')));
        zone.add(Arrays.asList(board.getZone('M'), board.getZone('I'), board.getZone('E'), board.getZone('B')));
        zone.add(Arrays.asList(board.getZone('M'), board.getZone('J'), board.getZone('F'), board.getZone('B')));
        zone.add(Arrays.asList(board.getZone('M'), board.getZone('N'), board.getZone('O'), board.getZone('L')));
        zone.add(Arrays.asList(board.getZone('B'), board.getZone('F'), board.getZone('J'), board.getZone('O')));
        zone.add(Arrays.asList(board.getZone('B'), board.getZone('F'), board.getZone('J'), board.getZone('M')));
        zone.add(Arrays.asList(board.getZone('B'), board.getZone('E'), board.getZone('I'), board.getZone('M')));
        zone.add(Arrays.asList(board.getZone('B'), board.getZone('G'), board.getZone('K'), board.getZone('O')));
        zone.add(Arrays.asList(board.getZone('B'), board.getZone('F'), board.getZone('J'), board.getZone('N')));
        zone.add(Arrays.asList(board.getZone('D'), board.getZone('H'), board.getZone('L'), board.getZone('O')));
        zone.add(Arrays.asList(board.getZone('D'), board.getZone('H'), board.getZone('K'), board.getZone('O')));
        return zone;
    }
}

