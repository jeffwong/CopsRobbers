import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CopsRobbers extends JFrame{

    public static String spreadsheetId = "1BqSUDnEX57J2LwpdL4usVaJZbejscPiLZCkJyb16Fzs";
    public static City city;

    public CopsRobbers() {
        super("Cops and Robbers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a city JPanel
        try {
            city = CityBuilder.buildCity(spreadsheetId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getContentPane().add(city);

        // Add people
        List<Person> people = new ArrayList<Person>();
        Person Bob = new Person("Bob", city.getCell(0,0));
        Person Kevin = new Person("Kevin", city.getCell(5, 5));
        Police Eppes = new Police("Eppes", city.getCell(1, 1));
        people.add(Bob);
        people.add(Kevin);
        people.add(Eppes);
        city.setPeople(people);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateGame() {
        // Game loop
        /*for (int turn = 0; turn < 1000; turn++) {
            Bob.Run();
            Kevin.Walk();
            CityCell location = Eppes.LookAround();

            // What will police do today?
                    if (location.FindBadGuy()) {
                        Eppes.CallCenter();
                        center.ControlPolice(location);
                        Eppes.Follow();
                    } else {
                        Eppes.Stop();
                    }

            // What will bad guys do today?
        }*/


        // Fill it with people like police, robbers and cars

        // Finally run the AI simulation:

        // City needs to have normal building, police building, blah blah

        // Create 5 robbers

        // Set the robbers location to A

        // Police will start at B

        // Make police chase robbers
        List<Person> people = city.getPeople();
        people.get(1).Walk();
    }

    public static void playGame() {
        // Init
        CopsRobbers cr = new CopsRobbers();
        // Game Loop
        for (int i = 0; i < 4; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Updating game");
            cr.updateGame();
            cr.repaint();
        }
    }

    // This is the entrypoint
    // It has the instructions for what the software will do when you open it
    public static void main(String[] args) {
        /*SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                playGame();
            }
        });*/
        playGame();
    }
}
