/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import javafx.scene.chart.PieChart.Data;

/**
 *
 * @author 15201677
 */
public class NewClass {
    
    public static void main(String args[]) throws Exception{
        System.out.println("Select 1 ");
        System.out.println(ManagerBD.getInstance().getAllFuncionarios());
        System.out.println("Ok");
        System.out.println("---------------------------------");
        System.out.println("Select 	4");
        System.out.println(ManagerBD.getInstance().getRelatorioDeReservasFuturas());
        System.out.println("Ok");
        System.out.println("---------------------------------");
        System.out.println("Select 5");
        System.out.println("Testar");
      
        ArrayList<String> teste = new ArrayList<String>();
        teste.add("a");
        teste.add("b");
   
    	
    	 }
    
}
