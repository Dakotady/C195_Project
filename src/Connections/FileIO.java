package Connections;

import Application.ListModifications;
import Application.LoginActivity;
import Application.ReferencedMethods;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Scanner;

public class FileIO {

    public static void writeToFile(LocalDateTime localDateTime, int attempt) throws IOException {

        Timestamp utcTime = ReferencedMethods.ConvertToUTC(localDateTime, ReferencedMethods.getLocalTimeZone());

        String fileName = "src/SupportFiles/login_activity.txt";

        FileWriter fw = new FileWriter(fileName, true);

        PrintWriter pw = new PrintWriter(fw);

        pw.println(utcTime + "/" + attempt);

        pw.close();
    }

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
