package mert.sena.banksystem;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * Created by mertcelen on 16/12/15.
 */
    public class FileManagement {

    public FileManagement() throws IOException, ClassNotFoundException {
//        createFile();
    }

    public static void save() throws IOException {
        Log.i("mertFilter","save girdi");
        FileOutputStream fos = new FileOutputStream("users.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(app.accounts.list);
        oos.close();
    }

    public static void load() throws IOException, ClassNotFoundException {
        Log.i("mertFilter","load girdi");
        FileInputStream fis = new FileInputStream("users.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        app.accounts.list = (ArrayList<Account>) ois.readObject();
        ois.close();
    }

    public static void createFile(Context context) {
        System.out.println(context.getFilesDir());
//        Log.i("mertFilter","File creation start!");
//        File file = new File(., "users.bin");
//        Log.i("mertFilter", Environment.getDataDirectory().toString() + "/com.android.banksystem/");
    }
}
