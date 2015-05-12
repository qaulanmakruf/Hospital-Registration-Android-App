package com.example.hospital;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class Global extends Application implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3320868770814386590L;

	/**
     * 
     * @param context
     * @param object
     * @param filename
     */
    public static void write(Context context, Object object) throws Exception {

        ObjectOutputStream objectOut = null;
        try {

            FileOutputStream fileOut = context.openFileOutput("save.dat", Activity.MODE_PRIVATE);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(object);
            fileOut.getFD().sync();
            fileOut.flush();

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (objectOut != null) {
                try {
                    objectOut.close();
                } catch (IOException e) {
                	e.printStackTrace();
                	throw e;
                }
            }
        }
    }


    /**
     * 
     * @param context
     * @param filename
     * @return
     */
    public static Object read (Context context) throws Exception {

        ObjectInputStream objectIn = null;
        Object object = null;
        try {

            FileInputStream fileIn = context.getApplicationContext().openFileInput("save.dat");
            objectIn = new ObjectInputStream(fileIn);
            object = objectIn.readObject();
            return object;

        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        	throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (objectIn != null) {
                try {
                    objectIn.close();
                } catch (IOException e) {
                	e.printStackTrace();
                }
            }
        }


    }
	
}
