public class Robber extends Person {

    public Robber(String name, CityCell location) {
        super(name, location);
    }

    //Although there is no Run function for a Robber
    //The robber will still know how to do this
    //because it learned from the super class

    public void Fight() {

    }

    public void Steal() {

    }

}
