package com.mobicule.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

public class ImageManipulation 
{

	public static void display()
	{
		File file = new File("/home/ajinkya/Pictures/csvImport.png");
		
		try
		{
			FileInputStream fis = new FileInputStream(file);
			byte imageData[] = new byte[(int) file.length()];
			fis.read(imageData);
			
			
			// Converting Image byte array into Base64 String
			//String imageDataString = encodeImage(imageData);

			// Converting a Base64 String into Image byte array
			//byte[] imageByteArray = decodeImage(imageDataString);
			
			FileOutputStream imageOutFile = new FileOutputStream(
					"/home/ajinkya/Pictures/Java-Image-Write/csvImport.png");
			
			//imageOutFile.write(imageByteArray);
			fis.close();
			imageOutFile.close();
			
			
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}

	public static byte[] decodeImageString(String imageString)
	{
		byte[] imageByteArray = Base64.decodeBase64(imageString);
		return imageByteArray;
	}
	
}
