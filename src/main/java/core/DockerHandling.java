package core;

import org.junit.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class DockerHandling {

    String fileName = "logOutput.txt";
    //    String currentLine;


    public void startServer() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start dockerUP.bat");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 300);
        long stopMonitoring = cal.getTimeInMillis();
        Thread.sleep(3000);
        try {
            while (System.currentTimeMillis() < stopMonitoring) {
                if (Hooks.isServerUP() == true) {
                    break;
                } else {
                    BufferedReader reader = new BufferedReader(new FileReader(fileName));
                    String currentLine = reader.readLine();
                    while (currentLine != null && Hooks.isServerUP() == false) {
                        if (currentLine.contains("Node has been added")) {
//                    Started Selenium Hub
                            Hooks.setServerStatus(true);
                            System.out.println("found my text server up" + "isServerUP() " + Hooks.isServerUP());
                            System.out.println("found my text");
                            break;
                        } else {
                            currentLine = reader.readLine();
                        }
                        System.out.println("here6 " + currentLine);
                    }

                    reader.close();


                }
            }

        } catch (Exception exception) {
            System.out.println("server has not started");
        }
    }


    public void stopServer() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start dockerDown.bat");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 300);
        long stopMonitoring = cal.getTimeInMillis();
        Thread.sleep(10000);
        try {
            while (System.currentTimeMillis() < stopMonitoring) {
                if (Hooks.isServerUP() == false) {
                    break;
                } else {
                    BufferedReader reader = new BufferedReader(new FileReader("logOutput.txt"));
                    String currentLine = reader.readLine();
                    while (currentLine != null && Hooks.isServerUP() == true) {
                        if (currentLine.contains("Shutdown complete")) {
                            // Text is case sensetive
                            Hooks.setServerStatus(false);
                            System.out.println("found my text server down" + "isServerUP() " + Hooks.isServerUP());
                            System.out.println("found my text");
                            break;
                        } else {
                            currentLine = reader.readLine();
                        }
                        System.out.println("here7 " + currentLine);
                    }
                    reader.close();
                }
            }
        } catch (Exception exception) {
            System.out.println("server has not stopped");
        }
        Thread.sleep(3000);
        File file = new File("logOutput.txt");
        if (file.delete()) {
            System.out.println("Server Logs Deleted");
        }
    }


    public void scaleInstances() throws IOException, InterruptedException {
        Thread.sleep(10000);
        Assert.assertTrue(Hooks.isServerUP());
        // isServerUp should be true
        System.out.println("Hooks.isServerUP() : scale" + Hooks.isServerUP());
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start dockerScale.bat");
        Thread.sleep(10000);
    }


}
