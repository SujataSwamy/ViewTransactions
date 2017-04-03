package com.example.assignment.Utils;

import android.content.Context;
import android.util.Log;
import com.example.assignment.restclient.model.Transactions;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by sujata on 03/04/17.
 */

public class CacheDataOnDisk {

    public static List<Transactions> getTransactionsData(Context context, String fileName, String fileModelName) {
        try {
            List<Transactions> role_users = null;
            File path = context.getDir(fileName, 0);
            File file = new File(path.getAbsolutePath() + "/" + "" + fileModelName);

            if (file.exists()) {
                FileInputStream fileInputeStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputeStream);
                role_users = (List<Transactions>) objectInputStream.readObject();

                objectInputStream.close();
                fileInputeStream.close();
            }
            return role_users;

        } catch (Exception error) {

        }
        return null;
    }

    public static void saveTransactionsData(List<Transactions> location, Context context, String fileName, String fileModelName) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutput objectOutput = new ObjectOutputStream(byteArrayOutputStream);
            objectOutput.writeObject(location);
            byte[] buffer = byteArrayOutputStream.toByteArray();
            if (context != null) {
                File path = context.getDir(fileName, 0);
                File file = new File(path.getAbsolutePath() + "/" + fileModelName);

                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();

                FileOutputStream fileOutputStream = new FileOutputStream(file);

                fileOutputStream.write(buffer);
                fileOutputStream.close();

                objectOutput.flush();
                objectOutput.close();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
            }
        } catch (IOException ioe) {
            Log.d("exp raised", ioe.getLocalizedMessage());
        }
    }
}
