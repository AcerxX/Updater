/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updater;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 *
 * @author alexa_000
 */
public class Updater {

    /**
     * @param args the command line arguments
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException, IOException {

        /* Getting the new app */
        URL website = new URL("http://aica.org.ro/images/FTP/AcerX_File_Transfer_App.jpg");
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("AcerX_File_Transfer_App");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

        /* Removing old app */
        File oldApp = new File("AcerX_File_Transfer_App.jar");
        oldApp.delete();

        /* Replace with the new app */
        File newApp = new File("AcerX_File_Transfer_App");
        newApp.renameTo(oldApp);

        // Start the Main App
        ProcessBuilder mainApp = new ProcessBuilder("", "-jar", "AcerX_File_Transfer_App.jar");
        Process p = mainApp.start();
        
        System.exit(0);

    }

}
