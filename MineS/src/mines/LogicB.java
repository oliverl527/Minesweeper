/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mines;

import java.util.Random;

/**
 *
 * @author lucyo
 */
public class LogicB implements BInterface {

    private int x, y, bomb_num;
    private int[][] board;

    public void setup() {
        
    }

    public int InRange(int min, int max, int num) {
        if (num < min || num > max) {
            num = -1;
        }
        return num;
    }
    public int getBNum(int x, int y){
        return board[y][x];
    }
    public int getMaxY(){
        return y;
    }
    public int getMaxX(){
        return x;
    }
    public int getBombNum(){
        return bomb_num;
    }
    public void setLogicB(int diff) {
        int bomb_loc_x, bomb_loc_y;
        Random r = new Random();
        switch (diff) {
            case 1:
                x = y = 8;
                bomb_num = 10;
                break;
            case 2:                
                x = y = 16;
                bomb_num = 40;
                break;
            case 3:
                x = 30;
                y = 16;
                bomb_num = 99;
        }
        board = new int[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                board[i][j] = 0;                
            }
        }
        for (int i = 0; i < bomb_num; i++) {
            bomb_loc_x = r.nextInt(x);
            bomb_loc_y = r.nextInt(y);
            if(board[bomb_loc_y][bomb_loc_x] == -1){
                i--;
                continue;
            }
                board[bomb_loc_y][bomb_loc_x] = -1;
                
                if(bomb_loc_x + 1 < x && bomb_loc_y + 1 < y && board[bomb_loc_y + 1][bomb_loc_x + 1] != -1){
                    board[bomb_loc_y + 1][bomb_loc_x + 1]++;
                }
                if(bomb_loc_x + 1< x && bomb_loc_y > 0 && board[bomb_loc_y - 1][bomb_loc_x + 1] != -1){
                    board[bomb_loc_y - 1][bomb_loc_x + 1]++;
                }
                if(bomb_loc_x + 1 < x && board[bomb_loc_y][bomb_loc_x + 1] != -1){
                    board[bomb_loc_y][bomb_loc_x + 1]++;
                }
                if(bomb_loc_x > 0 && board[bomb_loc_y][bomb_loc_x - 1] != -1){
                    board[bomb_loc_y][bomb_loc_x - 1]++;
                }
                if(bomb_loc_x > 0 && bomb_loc_y > 0 && board[bomb_loc_y - 1][bomb_loc_x - 1] != -1){
                    board[bomb_loc_y - 1][bomb_loc_x - 1]++;
                }
                if(bomb_loc_x > 0 && bomb_loc_y + 1 < y &&  board[bomb_loc_y + 1][bomb_loc_x - 1] != -1){
                    board[bomb_loc_y + 1][bomb_loc_x - 1]++;
                }
                if(bomb_loc_y + 1 < y &&  board[bomb_loc_y + 1][bomb_loc_x] != -1){
                    board[bomb_loc_y + 1][bomb_loc_x]++;
                }
                if(bomb_loc_y > 0 && board[bomb_loc_y - 1][bomb_loc_x ] != -1){
                    board[bomb_loc_y - 1][bomb_loc_x]++;
                }
        }
        
    }
}
