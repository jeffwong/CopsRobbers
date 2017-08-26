public class CopsRobbers {

    // This is the entrypoint
    // It has the instructions for what the software will do when you open it
    public static void main(String[] args) {

        // First send a message
        System.out.println("hello world");

        // Create a city
        City SanFrancisco = new City("Spreadsheet 1");
        PoliceCommandCenter center = new PoliceCommandCenter();
        Person Bob = new Person("Bob");
        Person Kevin = new Person("Kevin");
        Police Eppes = new Police("Eppes");

        System.out.println(Bob.name);
        System.out.println(Kevin.name);
        System.out.println(Eppes.name);

        // Game loop
        for (int turn = 0; turn < 1000; turn++) {
            Bob.Run();
            Kevin.Walk();
            CityLocation location = Eppes.LookAround();

            // What will police do today?
            if (location.FindBadGuy()) {
                Eppes.CallCenter();
                center.ControlPolice(location);
                Eppes.Follow();
            } else {
                Eppes.Stop();
            }

            // What will bad guys do today?


        }


        // Fill it with people like police, robbers and cars

        // Finally run the AI simulation:


        // City needs to have normal building, police building, blah blah

        // Create 5 robbers

        // Set the robbers location to A

        // Police will start at B

        // Make police chase robbers
    }
}
