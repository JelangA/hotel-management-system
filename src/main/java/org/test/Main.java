package org.test;

import org.test.models.holder;
import org.test.services.HotelServiceImpl;
import org.test.services.HotelService;
import org.test.services.write;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;


public class Main {
    public static void main(String[] args){

        try
        {
            HotelService hotelService = new HotelServiceImpl();
            File f = new File("backup");
            if(f.exists())
            {
                FileInputStream fin=new FileInputStream(f);
                ObjectInputStream ois=new ObjectInputStream(fin);
                HotelServiceImpl.hotel_ob=(holder)ois.readObject();
            }
            Scanner sc = new Scanner(System.in);
            int ch,ch2;
            char wish;
            x:
            do{

                System.out.println("\n" +
                        "Enter your choice :\n" +
                        "1.Display room details\n" +
                        "2.Display room availability \n" +
                        "3.Book\n" +
                        "4.Order food\n" +
                        "5.Checkout\n" +
                        "6.Exit\n");
                ch = sc.nextInt();
                switch(ch){
                    case 1: System.out.println("\n" +
                            "Choose room type :\n" +
                            "1.Luxury Double Room \n" +
                            "2.Deluxe Double Room \n" +
                            "3.Luxury Single Room \n" +
                            "4.Deluxe Single Room\n");
                        ch2 = sc.nextInt();
                        hotelService.features(ch2);
                        break;
                    case 2:System.out.println("\n" +
                            "Choose room type :\n" +
                            "1.Luxury Double Room \n" +
                            "2.Deluxe Double Room \n" +
                            "3.Luxury Single Room\n" +
                            "4.Deluxe Single Room\n");
                        ch2 = sc.nextInt();
                        hotelService.availability(ch2);
                        break;
                    case 3:System.out.println("\n" +
                            "Choose room type :\n" +
                            "1.Luxury Double Room \n" +
                            "2.Deluxe Double Room \n" +
                            "3.Luxury Single Room\n" +
                            "4.Deluxe Single Room\n");
                        ch2 = sc.nextInt();
                        hotelService.bookroom(ch2);
                        break;
                    case 4:
                        System.out.print("Room Number -");
                        ch2 = sc.nextInt();
                        if(ch2>60)
                            System.out.println("Room doesn't exist");
                        else if(ch2>40)
                            hotelService.order(ch2-41,4);
                        else if(ch2>30)
                            hotelService.order(ch2-31,3);
                        else if(ch2>10)
                            hotelService.order(ch2-11,2);
                        else if(ch2>0)
                            hotelService.order(ch2-1,1);
                        else
                            System.out.println("Room doesn't exist");
                        break;
                    case 5:
                        System.out.print("Room Number -");
                        ch2 = sc.nextInt();
                        if(ch2>60)
                            System.out.println("Room doesn't exist");
                        else if(ch2>40)
                            hotelService.deallocate(ch2-41,4);
                        else if(ch2>30)
                            hotelService.deallocate(ch2-31,3);
                        else if(ch2>10)
                            hotelService.deallocate(ch2-11,2);
                        else if(ch2>0)
                            hotelService.deallocate(ch2-1,1);
                        else
                            System.out.println("Room doesn't exist");
                        break;
                    case 6:break x;

                }

                System.out.println("\nContinue : (y/n)");
                wish=sc.next().charAt(0);
                if(!(wish=='y'||wish=='Y'||wish=='n'||wish=='N'))
                {
                    System.out.println("Invalid Option");
                    System.out.println("\nContinue : (y/n)");
                    wish=sc.next().charAt(0);
                }

            }while(wish=='y'||wish=='Y');

            Thread t=new Thread(new write(HotelServiceImpl.hotel_ob));
            t.start();
        }
        catch(Exception e)
        {
            System.out.println("Not a valid input");
            e.printStackTrace();
        }
    }
}