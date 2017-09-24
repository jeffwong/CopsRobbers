import java.awt.*;

public class Person {

    // All the information we need for a Person
    // is called class variable

    // These are the class variables
    String name;
    CityCell location;

    // This is the constructor
    // It is the instructions for how to create an object
    public Person(String name, CityCell location) {
        this.name = name;
        this.location = location;
    }

    // Each person has a function like walk
    public void Walk() {
        CityCell newLocation = CopsRobbers.city.getCell(location.getGridI()+ 1, location.getGridJ() + 1);
        this.location = newLocation;
    }

    // and run
    public void Run() {

    }

    public void Stop() {

    }

    public CityCell LookAround() {
        return location;
    }

    public void Hide() {

    }

    public CityCell getLocation() {
        return location;
    }

    public void setLocation(CityCell location) {
        this.location = location;
    }
}
