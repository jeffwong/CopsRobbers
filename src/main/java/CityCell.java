import javax.swing.*;
import java.awt.*;

public class CityCell extends Rectangle{

    private int crowded;
    private int type; //when the type is 0 you cannot fight here. If the type is 1 it is ok to fight
    private Camera camera;
    private Person[] people;
    private Color color;

    private int gridI;
    private int gridJ;

    public CityCell(int x, int y, int width, int height, int i, int j) {
        super(x, y, width, height);
        this.gridI = i;
        this.gridJ = j;
    }

    public CityCell(int type) {
        this.type = type;
    }

    public boolean FindBadGuy() {
        return false;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public int getGridI() {
        return gridI;
    }

    public void setGridI(int gridI) {
        this.gridI = gridI;
    }

    public int getGridJ() {
        return gridJ;
    }

    public void setGridJ(int gridJ) {
        this.gridJ = gridJ;
    }
}
