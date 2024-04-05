package algonquin.cst2335.pajaappfinal;
/*
-------------------------------------------------------
Course: CST 2335 - Mobile Graphical Interface Programming
Final Project: Deezer Song Search API
Student Name: Allan Torres
Student Number: 041022473
-------------------------------------------------------
*/

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Singleton class for managing Volley's RequestQueue.
 */
public class VolleySingleton {
    private RequestQueue requestQueue;
    private static VolleySingleton mInstance;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * @param context The application context.
     */
    private VolleySingleton(Context context){
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    /**
     * Returns the singleton instance of VolleySingleton.
     * @param context The application context.
     * @return The singleton instance of VolleySingleton.
     */
    public static synchronized VolleySingleton getInstance(Context context){
        if (mInstance == null){
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    /**
     * Gets the RequestQueue associated with this VolleySingleton instance.
     * @return The RequestQueue.
     */
    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
}
