/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mines;
/**
 *
 * @author lucyo
 */

public interface BInterface {

    public void setup();
    public int InRange(int min, int max, int num);
    public void setLogicB(int diff);
    public int getBNum(int x, int y);
    public int getMaxX();
    public int getMaxY();
}
