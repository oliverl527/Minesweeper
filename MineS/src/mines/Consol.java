/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mines;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author lucyo
 */
public class Consol extends LogicB {
    
    Scanner input = new Scanner(System.in);
    private int x, y, space_left;
    private String[][] disp_board;
    
    public Consol() {
        int choice_x, choice_y;
        boolean is_end = false;
        Intro();
        while (!is_end) {
            display();
            choice_x = getAns("\n1. Place Flag\n2. Remove Flag\n3. Unearth Land\n4. Exit", 1, 4);
            if(choice_x == 4){
                is_end = true;
                continue;
            }
            if (choice_x == 1) {
                choice_y = getAns("y?", 0, y - 1);
                choice_x = getAns("x?", 0, x - 1);
                if (disp_board[choice_y][choice_x] == "*") {
                    disp_board[choice_y][choice_x] = "F";
                    
                }
                continue;
            }
            if (choice_x == 2) {
                choice_y = getAns("y?", 0, y - 1);
                choice_x = getAns("x?", 0, x - 1);
                if (disp_board[choice_y][choice_x] == "F") {
                    disp_board[choice_y][choice_x] = "*";
                    
                }
                continue;
            }
            choice_y = getAns("y?", 0, y - 1);
            choice_x = getAns("x?", 0, x - 1);
            if (getBNum(choice_x, choice_y) == -1) {
                fail();
                is_end = true;
                continue;
            }
            recurs(choice_x, choice_y);
            if (space_left == 0) {
                System.out.println("YOU WON!");
                display();
                is_end = true;
            }
        }
        
    }
    
    private boolean recurs(int r_x, int r_y) {
        if (InRange(0, x - 1, r_x) == -1 || InRange(0, y - 1, r_y) == -1 || disp_board[r_y][r_x].equals("F")) {
            return false;
        }
        if (disp_board[r_y][r_x] == "*") {
            disp_board[r_y][r_x] = Integer.toString(getBNum(r_x, r_y));
            space_left--;
        }
        if (disp_board[r_y][r_x].equals("0")) {
            disp_board[r_y][r_x] = " ";
            recurs(r_x + 1, r_y);
            recurs(r_x + 1, r_y + 1);
            recurs(r_x + 1, r_y - 1);
            recurs(r_x - 1, r_y);
            recurs(r_x - 1, r_y + 1);
            recurs(r_x - 1, r_y - 1);
            recurs(r_x, r_y + 1);
            recurs(r_x, r_y - 1);
        }
        return true;
    }
    
    private void fail() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (getBNum(j, i) == -1) {
                    System.out.print("-1\t");
                } else {
                    System.out.print(disp_board[i][j] + "\t");
                }
            }
            System.out.println("");
        }
    }
    
    private void Intro() {
        setLogicB(getAns("1. Easy\n2. Medium\n3. Hard", 1, 3));
        x = getMaxX();
        y = getMaxY();
        space_left = (x * y) - getBombNum();
        disp_board = new String[y][x];
        
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                disp_board[i][j] = "*";
            }
        }
    }
    
    private void display() {
        System.out.print("X\t");
        for (int i = 0; i < x; i++) {
            System.out.print(i + "\t");
        }
        System.out.println("");
        for (int i = 0; i < 8 * x + 3; i++) {
            System.out.print("_");
        }
        System.out.println("\n");
        
        for (int i = 0; i < y; i++) {
            if (i < 10) {
                System.out.print(i + " |\t");
            } else {
                System.out.print(i + "|\t");
            }
            for (int j = 0; j < x; j++) {
                System.out.print(disp_board[i][j] + "\t");
            }
            System.out.println("");
        }
        for (int i = 0; i < 8 * x + 3; i++) {
            System.out.print("_");
        }
        System.out.println("\n");
    }
    
    private int getAns(String s, int min, int max) {
        int ans = -1;
        while (ans == -1) {
            System.out.println(s);
            try {
                ans = InRange(min, max, input.nextInt());
            } catch (InputMismatchException IME) {
                input.next();
            }
        }
        return ans;
    }
}
