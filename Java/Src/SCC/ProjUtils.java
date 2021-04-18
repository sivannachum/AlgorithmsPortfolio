//-----------------------------------------------------
// Author: 		Prof. Streinu
// Date: 		April 12, 2021
// Description:	Java code for various utilities
//-----------------------------------------------------
import java.io.*;
import java.lang.System;
import java.util.LinkedList;
import java.awt.geom.*;

public class ProjUtils{
	
	final static boolean DEBUG = true;
	
	// Utilities
	public static int toMathId(int n){ 
		// internal vertex ids are Java style: 0, 1, ...
		// external (user) ids are Mathematica/s style: 1, 2, ...
		return n+1;
	}
	public static int toJavaId(int n){ 
		// internal vertex ids are Java style: 0, 1, ...
		// external (user) ids are Mathematica/s style: 1, 2, ...
		return n-1;
	}
	
	public static void printDebug(String message){
		if (DEBUG){
			System.out.println(message);
		}
	}
	public static void printArrayMathInt(int[] array){
		for (int i=0; i<array.length; i++){
			printDebug(""+ toMathId(i) + ": " + toMathId(array[i]));
		}
	}	

	public static String makeAbsoluteInputFilePath(String inputFilePath){
		File input = new File(inputFilePath);
		String absInputFilePath = "";
		try{
			absInputFilePath= input.getCanonicalPath();
			// System.out.println("absInputFilePath="+absInputFilePath);
		} catch(IOException e) {
			System.out.println("ERROR: cound not run getCanonicalPath on file "+inputFilePath);
		}

		return absInputFilePath;
	}	

	public static String makeAbsoluteInputFilePathWithExt(String inputFilePath, String ext){
		File input = new File(inputFilePath);
	
		String inputFileFolder = input.getParent();
		// System.out.println("inputFileFolder="+inputFileFolder);
		String fileName = input.getName();
		// System.out.println("fileName="+fileName);
		String name = fileName.split("\\.")[0];
		// System.out.println("name="+name);
			
		
		// make output file path with extension
		String newInputFilePath = inputFileFolder + "/" + name + "." + ext;
		// System.out.println("newInputFilePath="+newInputFilePath);
		
		File newInput= new File(newInputFilePath);
		
		String absNewInputFilePath = "";
		try{
			absNewInputFilePath = newInput.getCanonicalPath();
			// System.out.println("absNewInputFilePath="+absNewInputFilePath);
		} catch(IOException e) {
			System.out.println("ERROR: cound not run getCanonicalPath on file "+newInputFilePath);
		}
		
		return absNewInputFilePath;
	}	

	public static String makeAbsoluteOutputFilePathWithExt(String inputFilePath, String outputFileFolder, String ext){
		File input = new File(inputFilePath);
	
		String parentPath = input.getParent();
		// System.out.println("parentPath="+parentPath);
		String fileName = input.getName();
		// System.out.println("fileName="+fileName);
		String name = fileName.split("\\.")[0];
		// System.out.println("name="+name);
			
		
		// make output file path with extension
		String outputFilePath = outputFileFolder + "/" + name + "." + ext;
		// System.out.println("outputFilePath="+outputFilePath);
		
		File output= new File(outputFilePath);
		
		String absOutputFilePath = "";
		try{
			absOutputFilePath = output.getCanonicalPath();
			// System.out.println("absOutputFilePath="+absOutputFilePath);
		} catch(IOException e) {
			System.out.println("ERROR: cound not run getCanonicalPath on file "+outputFilePath);
		}
		
		return absOutputFilePath;
	}

	public static void copyFileUsingStream(String inputFilePath, String outputFilePath) throws IOException {
	  
		File source = new File(inputFilePath);
		File dest = new File(outputFilePath);
		InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
	
    //-------------------------------------
    // Helpers:
    // Random points 
    //-------------------------------------
	public static Point2D makeRandomPoint2D(int range){
		
		int x = (int)(Math.random() * (range+1)); 
		int y = (int)(Math.random() * (range+1)); 
		Point2D pt = new Point2D.Double(x,y);
		
		return pt;
	}
	public static Point2D[] makeRandomPoints2D(int range, int nrP){
		Point2D[] pts = new Point2D[nrP];
		for (int i=0; i<nrP; i++){
			pts[i]=makeRandomPoint2D(range);
		};
		return pts;
	}
	
}