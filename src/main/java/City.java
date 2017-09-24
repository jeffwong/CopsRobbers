import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class City extends JPanel {

    private static final int PREF_W = 500;
    private static final int PREF_H = PREF_W;

    private CityCell[][] grid;
    private List<Person> people;

    public City() {
        super();
    }

    public City(CityCell[][] cells) {
        this();
        this.grid = cells;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (CityCell[] cells : grid) {
            for (CityCell cell : cells) {
                g2.setColor(Color.BLACK);
                g2.draw(cell);
                g2.setColor(cell.getColor());
                g2.fillRect((int) cell.getX(), (int) cell.getY(), (int) cell.getWidth(), (int) cell.getHeight());
            }
        }
        g2.setColor(Color.BLACK);
        for (Person p : people) {
            g2.drawOval((int) p.getLocation().getX(), (int) p.getLocation().getY(),
                    (int) p.getLocation().getWidth(), (int) p.getLocation().getHeight());
        }
    }

    public CityCell[][] getGrid() {
        return grid;
    }

    public CityCell getCell(int i, int j) {
        return grid[i][j];
    }

    public void setGrid(CityCell[][] grid) {
        this.grid = grid;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<Person> getPeople() {
        return this.people;
    }

}
