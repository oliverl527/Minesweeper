/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mines;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lucyo
 */
public class GUIM extends LogicB {

    JFrame jf = new JFrame();
    JButton[][] squares;
    JPanel bjp = new JPanel(), end_panel = new JPanel();
    JLabel ending = new JLabel();
    int spaces_left;

    public GUIM() {
        int xScreen, yScreen, x, y;
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        xScreen = d.width;
        yScreen = d.height;
        x = 2 * xScreen / 3;
        y = 2 * yScreen / 3;
        jf.setBounds(xScreen / 6, yScreen / 6, x, y);
        jf.setTitle("MINESWEEPER");
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setResizable(false);
        get_lvls(x,y);
        jf.setVisible(true);
        
        end_panel.setLayout(null);
        end_panel.add(ending);
        ending.setBounds(x/2-(x/100), y/2, x/10, y/10);
        ending.setFont(new Font("", Font.PLAIN, y / 30));
    }

    private void game_begin(int x_screen_size, int y_screen_size) {
        //creating the panel and stuff
        spaces_left = (getMaxX() * getMaxY()) - getBombNum();
        squares = new JButton[getMaxY()][getMaxX()];
        bjp.setBounds(0, 0, x_screen_size, y_screen_size);
        bjp.setLayout(null);
        bjp.setLayout(new GridLayout(getMaxY(), getMaxX()));
        
        for (int i = 0; i < getMaxY(); i++) {
            for (int j = 0; j < getMaxX(); j++) {
                squares[i][j] = new JButton();
                squares[i][j].setFont(new Font("", Font.PLAIN, y_screen_size / 30));
                bjp.add(squares[i][j]);
            }
        }
        jf.add(bjp);
        int[] temp1 = new int[getMaxX()], temp2 = new int[getMaxY()];
        for (int i = 0; i < getMaxX(); i++) {
            temp1[i] = i;
        }
        for (int i = 0; i < getMaxY(); i++) {
            temp2[i] = i;
        }
        for (final int i : temp1) {
            for (final int j : temp2) {
                squares[j][i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttTouch(j, i);
                    }
                });
            }
        }
    }

    private void buttTouch(int r_x, int r_y) {
        if (getBNum(r_x, r_y) == -1) {
            //put in losing clause
            bjp.setVisible(false);
            jf.remove(bjp);
            jf.add(end_panel);
            ending.setText("Ya Lost");
        }
            recurs(r_x, r_y);
            
        //check to see if winner
        if (spaces_left == 0) {
             bjp.setVisible(false);
            jf.remove(bjp);
            jf.add(end_panel);
            ending.setText("Ya Won");
        }
    }

    private boolean recurs(int r_x, int r_y) {
        if (InRange(0, getMaxY() - 1, r_y) == -1 || InRange(0, getMaxX() - 1, r_x) == -1 || !squares[r_x][r_y].isEnabled()) {
            return false;
        }
        squares[r_x][r_y].setEnabled(false);
        squares[r_x][r_y].setText(Integer.toString(getBNum(r_x, r_y)));
        spaces_left--;
        if(!squares[r_x][r_y].getText().equals("0")){
            return true;
        }
        
            squares[r_x][r_y].setText("");
            recurs(r_x + 1, r_y);
            recurs(r_x + 1, r_y + 1);
            recurs(r_x + 1, r_y - 1);
            recurs(r_x - 1, r_y);
            recurs(r_x - 1, r_y + 1);
            recurs(r_x - 1, r_y - 1);
            recurs(r_x, r_y + 1);
            recurs(r_x, r_y - 1);
            return true;
    }

    private void get_lvls(int x, int y) {
        JPanel lvl_pan = new JPanel();
        lvl_pan.setBounds(0, 0, x, y);
        lvl_pan.setLayout(new GridLayout(3, 1));
        JButton[] lvls = new JButton[3];
        for (int i = 0; i < 3; i++) {
            lvls[i] = new JButton();
            lvls[i].setFont(new Font("", Font.PLAIN, y / 30));
            lvl_pan.add(lvls[i]);
        }
        lvls[0].setText("EASY");
        lvls[1].setText("MEDIUM");
        lvls[2].setText("HARD");
        lvls[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLogicB(1);
                lvl_pan.setVisible(false);
                jf.remove(lvl_pan);
                game_begin(x,y);
            }

        });
        lvls[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLogicB(2);
                lvl_pan.setVisible(false);
                jf.remove(lvl_pan);
                game_begin(x,y);
            }

        });
        lvls[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLogicB(3);
                lvl_pan.setVisible(false);
                jf.remove(lvl_pan);
                game_begin(x,y);
            }

        });
        jf.add(lvl_pan);
    }
}
