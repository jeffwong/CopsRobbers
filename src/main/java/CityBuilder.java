import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.services.sheets.v4.Sheets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CityBuilder {
    /** Application name. */
    private static final String APPLICATION_NAME =
            "Cops and Robbers";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/sheets.googleapis.com-java-quickstart");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/sheets.googleapis.com-java-quickstart
     */
    private static final List<String> SCOPES =
            Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
                Quickstart.class.getResourceAsStream("/client_secret.json");
        if (in == null) {
            System.out.println("warning IN is null");
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Sheets API client service.
     * @return an authorized Sheets API client service
     * @throws IOException
     */
    public static Sheets getSheetsService() throws IOException {
        Credential credential = authorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static java.awt.Color GoogleColor2JavaColor(Color color) {
        float greenValue = 0;
        float redValue = 0;
        float blueValue = 0;
        for (int k = 0; k < color.keySet().size(); k++) {
            String rgbColor = (String) color.keySet().toArray()[k];
            float rgbValue = (float) color.values().toArray()[k];
            if (rgbColor == "red") {
                redValue = rgbValue;
            } else if (rgbColor == "green") {
                greenValue = rgbValue;
            } else if (rgbColor == "blue") {
                blueValue = rgbValue;
            }
        }
        java.awt.Color javaColor = new java.awt.Color(redValue, greenValue, blueValue);
        return javaColor;
    }

    public static City buildCity(String spreadsheet) throws IOException {
        // Build a new authorized API client service.
        Sheets service = getSheetsService();

        // Prints the names and majors of students in a sample spreadsheet:
        // https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
        Spreadsheet response = service.spreadsheets()
                .get(spreadsheet)
                .setIncludeGridData(true)
                .execute();
        GridData gd = response.getSheets().get(0).getData().get(0);

        CityCell[][] cells = new CityCell[10][10];
        for (int i = 0; i < 10; i++) {
            cells[i] = new CityCell[10];
            for (int j = 0; j < 10; j++) {
                Color color = gd.getRowData().get(j)
                        .getValues().get(i)
                        .getEffectiveFormat().getBackgroundColor();
                CityCell c = new CityCell(i*50, j*50, 50, 50, j, i);
                c.setColor(GoogleColor2JavaColor(color));
                cells[i][j] = c;
            }
        }
        return new City(cells);
    }


}