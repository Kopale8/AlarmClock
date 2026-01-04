
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main{
    public static void main (String[] args){

        Scanner s = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime alarmTime = null;
        String filePath = "D:\\ProgrammingWorkspace\\JAVA_Workspace\\Tutorials\\Authors\\Bro_Code\\AlarmClock\\src\\a-ha - Take On Me (Official Video) [4K].wav";

    while(alarmTime == null){

        try {
            
            System.out.print("ENTER THE ALARM TIME(HH:MM:SS): ");
            String inputTime = s.nextLine();
         
            alarmTime = LocalTime.parse(inputTime, formatter);
            System.out.println("ALARM SET FOR: " + alarmTime);


        } catch (DateTimeParseException e) {
            System.out.println("Please enter correct time format  HH:MM:SS");
        }

    }


        AlarmClock alarmClock = new AlarmClock(alarmTime, filePath, s);
        Thread alarmThread = new Thread(alarmClock);
        alarmThread.start();
       
        
    }
}