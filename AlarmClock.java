
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AlarmClock implements Runnable {
    
    private final LocalTime alarmTime;
    private final String filePath;
    private final Scanner s;
    

    public AlarmClock(LocalTime alarmTime, String filePath, Scanner s) {
        this.alarmTime = alarmTime;
        this.filePath = filePath;
        this.s = s;
    }

    @Override
    public void run(){
      
      
       while(LocalTime.now().isBefore(alarmTime)){
           try {

            LocalTime now = LocalTime.now();
               

            Thread.sleep(1000);
            System.out.printf("\r%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond() );

           }
            catch (InterruptedException e) {
              System.out.println("thread was interuppted!");
           }
       }

       System.out.println("\n*******Alarm*******");
       for(int i = 0; i <= 10; i ++){
        playSound(filePath);
       
       }
    }
    private void playSound(String filePath){
        File audioFile = new File(filePath);
        

        try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);){
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            System.out.println("Press *Enter* to stop the alarm ");
            s.nextLine();
            clip.stop();


            s.close();
        }
        catch(UnsupportedAudioFileException e){
            System.out.println("file is not supported");
        }
        catch(IOException e){
            System.out.println("could not locate the file");
        }
        catch(LineUnavailableException e){
            System.out.println("Audio is unavailable");
        }
        
       
    }

}

    

