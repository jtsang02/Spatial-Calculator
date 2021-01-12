package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

/*
 * spatialCalc.cpp
 *
 *  Created on: Apr. 24, 2020
 *      Author: jtsan
 */

public class Compartment { 
	
	public static String name, group, frr, constr, clad;
	public static float h, w, a, LD, actOpns, uOpns;
	public static float r;
	public static boolean sprk;	
	static boolean haz;
	
	//--------------------------------------------------------------------------------------------------------//
	static boolean hazard(){ 
		if (group.equals("E") || group.equals("F-1") || group.equals("F-2"))
			return true;
		else
			return false;
	}
	//--------------------------------------------------------------------------------------------------------//
	static float area(){
		return h*w;
	}
	//--------------------------------------------------------------------------------------------------------//
	static float ratio(){
		if (h/w > w/h)
			return h/w;
		else
			return w/h;
	}
	//--------------------------------------------------------------------------------------------------------//
	static int ratioCode(){
		if (r < 3)
			return 1;
		else if (r <= 10)
			return 2;
		else
			return 3;
	}	
	//--------------------------------------------------------------------------------------------------------//
	static float getAO() {
		return actOpns / a * 100;
	}
	//--------------------------------------------------------------------------------------------------------//
	static float getUO() throws IOException{
		
		String table = null;
		int rowSize = 0, columnSize = 0, maxLD = 0, maxArea = 0; 
		int x = 0, y = 0, shift_y = 0, z = 0;
		float LD1 = 0, LD2 = 0, area1 = 0, area2 = 0;
		float u1 = 0, u2 = 0, u3 = 0, u4 = 0, u = 0;
		boolean exactArea = false, exactLD = false;
    
		if (sprk){
			shift_y = 1;
    
			if (haz){ // table 3.2.3.1.E
				rowSize = 15;
				columnSize = 20;
				table = "tableE.txt";
				maxLD = 15;
				maxArea = 200;
			}
			
			if (haz == false){ // table 3.2.3.1.D
				rowSize = 14;
				columnSize = 14;
				table = "tableD.txt";
				maxLD = 9;
				maxArea = 150;
			}
		}
    
		if (sprk == false){
			shift_y = 3;
			z = ratioCode();
			rowSize = 55;
			maxArea = 2000;
    
			if (haz){ // table 3.2.3.1.C
				columnSize = 33;
				table = "tableC.txt";
				maxLD = 70;
			}
			
			if (haz == false){ // table 3.2.3.1.B
				columnSize = 29;
				table = "tableB.txt";
				maxLD = 50;
			}
		}
		
		float[][] spTable = new float[rowSize][columnSize];
			InputStream inputStream = Compartment.class.getResourceAsStream("/resources/"+ table);
			Scanner sc = new Scanner(inputStream);
			for (int row = 0; row < rowSize; row++) 
				for (int column = 0; column < columnSize; column++)
					spTable[row][column] = sc.nextFloat();
				sc.close();
					
		for (int i = 0; i < columnSize; i++)
			if (LD >= spTable[0][i]){
				x = i;
				LD1 = spTable[0][x];
				LD2 = spTable[0][x + 1];
				if (LD == LD1)
					exactLD = true;
			}
    
		for (int j = 0; j < rowSize; j++)
			if (a >= spTable[j][0] && z == spTable[j][1]){
				y = j;
				area1 = spTable[y][0];
				area2 = spTable[y + shift_y][0];
				if (a == area1)
					exactArea = true;
			}
		
		u1 = spTable[y][x];
		u2 = spTable[y][x + 1];
		u3 = spTable[y + shift_y][x];
		u4 = spTable[y + shift_y][x + 1];
    
		if (exactLD && exactArea)
			u = u1;
		
		else if (exactLD || LD > maxLD)
			u = (a - area1) / (area2 - area1) * (u3 - u1) + u1;
		
		else if (exactArea || a > maxArea)
			u = (LD - LD1) / (LD2 - LD1) * (u2 - u1) + u1;
		
		else {
			float temp1 = (LD - LD1) / (LD2 - LD1) * (u2 - u1) + u1;
			float temp2 = (LD - LD1) / (LD2 - LD1) * (u4 - u3) + u3;
			u = (a - area1) / (area2 - area1) * (temp2 - temp1) + temp1;
		}
		return u;
	}
	//--------------------------------------------------------------------------------------------------------//
	static String construction(int p) throws IOException{
		String field = "None";
		if (uOpns >= 100)
			return field;
		
		else {
			int[] limits = {10, 25, 50, 100};
			String[][] matrix = new String[8][3];
			InputStream inputStream = Compartment.class.getResourceAsStream("/resources/3.2.3.7.txt");
			Scanner sc = new Scanner(inputStream);
			for (int row = 0; row < 8; row++) 
				for (int column = 0; column < 3; column++)
					matrix[row][column] = sc.next();
			sc.close();
			
			for (int i = 0; i < 4; i++)
				if (uOpns <= limits[i]){
					if (haz)
						field = matrix[i + 4][p];
					else
						field = matrix[i][p];
					break;
				}
			return field;
		}
	}
	//--------------------------------------------------------------------------------------------------------//
	public static void calculate() throws IOException{
		haz = hazard();
		a = area();
		r = ratio();
		actOpns = getAO();
		uOpns = getUO();
		frr = construction(0);
		constr = construction(1);
		clad = construction(2);
	}	
	//--------------------------------------------------------------------------------------------------------//	
}	