package org.test.services;

import org.test.models.holder;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class write implements Runnable {
    public holder hotel_ob;

    public write(holder hotel_ob) {
        this.hotel_ob = hotel_ob;
    }

    @Override
    public void run() {
        try {
            FileOutputStream fout = new FileOutputStream("backup");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(hotel_ob);
        } catch (Exception e) {
            System.out.println("Error in writing " + e);
        }

    }
}
