package f2.spw;


import java.applet.*;
import java.net.*;

public class Music {
   
   private AudioClip sound;

   public void playSound(int i){
      try {
        
            if(i==1){
            sound = Applet.newAudioClip(new URL("file:f2/soundbg.wav"));
            sound.loop();
         }   
         if(i==2){
            sound = Applet.newAudioClip(new URL("file:f2/soundbg.wav"));
            sound.loop();
         }
         if(i==3){
            sound = Applet.newAudioClip(new URL("file:f2/soundbg.wav"));
            sound.play();
         }
      
         
            
      } catch (MalformedURLException e) {
         e.printStackTrace();
      }
      sound.loop();
   }


   public void stopSound(){
      sound.stop();
   }
}