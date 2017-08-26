public class CityLocation {

    private int crowded;
    private int type; //when the type is 0 you cannot fight here. If the type is 1 it is ok to fight
    private Camera camera;
    private Person[] people;

    public CityLocation() {

    }

    public CityLocation(int type) {
        this.type = type;
    }

    public boolean FindBadGuy() {

    }
}
