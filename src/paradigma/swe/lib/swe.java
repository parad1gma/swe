/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paradigma.swe.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author paradigma
 */
public class swe {
    
    public static String userFolder = System.getProperty("user.dir"); 
    // System.out.println(userFolder);
    
    public static File SelectedFile;
    
    public static List<String[]> selectedLines = new ArrayList<>();
                    
    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static int numberOfLines;
    
    public static int getNumberOfLines () {
        return numberOfLines;
    }
    
    private static int numberOfSelectedLines;
    
    public static int getNumberOfSelectedLines () {
        return numberOfSelectedLines;
    }
    
    private static float amountReceived;
    
    public static float getAmountReceived () {
        return amountReceived;
    }
    
    public static void ReadLogFile (){
        selectedLines.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(SelectedFile.getAbsolutePath()))) { 
            String line;           
            numberOfLines = 0;
            numberOfSelectedLines = 0;
            amountReceived = 0;
            while ((line = br.readLine()) != null) {
                numberOfLines++;
                String[] splitLine = line.split(" ");                
                for (int x=0; x < splitLine.length; x++) {
                    if (splitLine[x].equals("received")) {
                        numberOfSelectedLines++;
                        for (int i=0; i < splitLine.length; i++) {
                                splitLine[i] = splitLine[i].replace("," , "");
                        }
                        selectedLines.add(splitLine);
                        try {                           
                            amountReceived += Float.parseFloat(splitLine[++x]);
                        }
                        catch (NumberFormatException nfe) {
                            System.out.println("NumberFormatException: " + nfe.getMessage());
                        }                        
                        break;
                    }
                }
            }
            
            for (int i = 0; i < selectedLines.size(); i++) {
                System.out.println(Arrays.toString(selectedLines.get(i)));
            }
            System.out.println();
            System.out.println("Amount:" + amountReceived);
            System.out.println("Number of lines: " + numberOfLines);
            System.out.println("Number of selected lines: " + numberOfSelectedLines);
            System.out.println("User directory: " + System.getProperty("user.dir"));
        } catch (IOException ioe) {
            System.out.println( ioe.getMessage() );
        }
    }
}