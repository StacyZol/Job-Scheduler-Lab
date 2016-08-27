package com.example.stacyzolnikov.jobschedulerlab;

/**
 * Created by stacyzolnikov on 8/27/16.
 */
public class DataSingleton {
    private String myText;

    private String myText2;



    public interface TextChangeListener {


        void onTextChanged(String oldText, String newText);


    }



    private TextChangeListener textChangeListener;

    private static DataSingleton instance;

    private DataSingleton() {

    }



    public static DataSingleton getInstance() {
        if (instance == null) {
            instance = new DataSingleton();

        }
         return instance;

    }


    public void setListener(TextChangeListener listener) {
        textChangeListener = listener;

    }


    private void notifyDataChanged(String text, String text2) {
         if (textChangeListener != null) {
            textChangeListener.onTextChanged(text, text2);

        }

    }

    public void updateMyText(String text, String text2) {
        String oldMessage = "This is the old message: " + myText;
        myText = text;
        String newMessage = "This is the new message " + myText2;
        myText2 = text2;
        notifyDataChanged(oldMessage, newMessage);

    }


    public String getMyText() {
        return myText;

    }



    public String getMyText2() {
        return myText2;
    }
}
