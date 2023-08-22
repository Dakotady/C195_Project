package Connections;

import Application.ListModifications;
import Application.LoginActivity;
import Application.ReferencedMethods;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Scanner;

/**
 * This class creates and reads the login_activity.txt file and when it reads the file it stores it to the ListModification.allLoginActivity ObservableList.
 */
public class FileIO {


    /**
     * This creates and writes to the file login_activity.txt. This file is located here: src/SupportFiles/login_activity.txt
     * @param localDateTime
     * @param attempt
     * @throws IOException
     */
    public static void writeToFile(LocalDateTime localDateTime, int attempt) throws IOException {

        Timestamp utcTime = ReferencedMethods.ConvertToUTC(localDateTime, ReferencedMethods.getLocalTimeZone());

        String fileName = "src/SupportFiles/login_activity.txt";

        FileWriter fw = new FileWriter(fileName, true);

        PrintWriter pw = new PrintWriter(fw);

        pw.println(utcTime + "/" + attempt);

        pw.close();
    }

    /**
     * This reads and stores the information from login_activity.txt
     * @throws FileNotFoundException
     */
    public static void readFile() throws FileNotFoundException {

        String fileName = "src/SupportFiles/login_activity.txt";

        File file = new File(fileName);

        Scanner scanner = new Scanner(file);


        while (scanner.hasNext()){

            String temp = scanner.nextLine();
            Scanner tempScanner = new Scanner(temp);
            tempScanner.useDelimiter("/");

            Timestamp fileDate = Timestamp.valueOf(tempScanner.next());

            LocalDateTime currentLocal = ReferencedMethods.localTimeConversion(fileDate, ZoneId.of("UTC"));

            int attempt = Integer.parseInt(tempScanner.next());

            LoginActivity loginActivity = new LoginActivity(attempt, currentLocal);

            ListModifications.addLoginActivity(loginActivity);
        }

        scanner.close();

    }
}
