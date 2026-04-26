package org.test.services;

import org.test.models.Doubleroom;
import org.test.models.Food;
import org.test.models.Singleroom;
import org.test.models.holder;
import org.test.exceptions.NotAvailable;

import java.util.Scanner;

public class HotelServiceImpl implements HotelService {
    public static holder hotel_ob = new holder();
    public static Scanner sc = new Scanner(System.in);

    public void CustDetails(int i, int rn) {
        String name, contact, gender;
        String name2 = null, contact2 = null;
        String gender2 = "";
        System.out.print("\nEnter customer name: ");
        name = sc.next();
        System.out.print("Enter contact number: ");
        contact = sc.next();
        System.out.print("Enter gender: ");
        gender = sc.next();
        if (i < 3) {
            System.out.print("Enter second customer name: ");
            name2 = sc.next();
            System.out.print("Enter contact number: ");
            contact2 = sc.next();
            System.out.print("Enter gender: ");
            gender2 = sc.next();
        }

        switch (i) {
            case 1:
                hotel_ob.luxury_doublerrom[rn] = new Doubleroom(name, contact, gender, name2, contact2, gender2);
                break;
            case 2:
                hotel_ob.deluxe_doublerrom[rn] = new Doubleroom(name, contact, gender, name2, contact2, gender2);
                break;
            case 3:
                hotel_ob.luxury_singleerrom[rn] = new Singleroom(name, contact, gender);
                break;
            case 4:
                hotel_ob.deluxe_singleerrom[rn] = new Singleroom(name, contact, gender);
                break;
            default:
                System.out.println("Wrong option");
                break;
        }
    }
   // Path: bookroom(int i)
    public void bookroom(int i) {
        int j;   // [Node 1] Inisialisasi variabel loop
        int rn;  // [Node 2] Inisialisasi variabel nomor kamar (index hasil input)

        System.out.println("\nChoose room number from : "); // [Node 3] Output daftar pilihan awal

        switch (i) { // [Node 4] Keputusan utama berdasarkan tipe kamar

            case 1: // [Node 5] Cabang tipe 1: luxury double
                for (j = 0; j < hotel_ob.luxury_doublerrom.length; j++) { // [Node 6] Loop scan seluruh kamar tipe 1
                    if (hotel_ob.luxury_doublerrom[j] == null) { // [Node 7] Keputusan: kamar kosong?
                        System.out.print(j + 1 + ","); // [Node 8] Aksi jika kosong: tampilkan nomor kamar
                    } // [Node 9] False branch: kamar terisi, tidak ditampilkan
                } // [Node 10] Akhir loop tipe 1

                System.out.print("\nEnter room number: "); // [Node 11] Minta input nomor kamar
                try { // [Node 12] Mulai blok validasi input + alokasi
                    rn = sc.nextInt(); // [Node 13] Baca input nomor kamar
                    rn--;              // [Node 14] Konversi nomor kamar ke index array
                    if (hotel_ob.luxury_doublerrom[rn] != null) // [Node 15] Keputusan: kamar sudah terisi?
                        throw new NotAvailable();                // [Node 16] Jika ya -> lempar exception
                    CustDetails(i, rn); // [Node 17] Jika tersedia -> isi data pelanggan
                } catch (Exception e) { // [Node 18] Jalur error: input invalid / index error / NotAvailable
                    System.out.println("Invalid Option"); // [Node 19] Pesan gagal
                    return; // [Node 20] Exit dini fungsi
                }
                break; // [Node 21] Keluar dari switch case 1

            case 2: // [Node 22] Cabang tipe 2: deluxe double
                for (j = 0; j < hotel_ob.deluxe_doublerrom.length; j++) { // [Node 23] Loop scan kamar tipe 2
                    if (hotel_ob.deluxe_doublerrom[j] == null) { // [Node 24] Keputusan kamar kosong?
                        System.out.print(j + 11 + ","); // [Node 25] Tampilkan kamar kosong
                    } // [Node 26] False branch: kamar terisi
                } // [Node 27] Akhir loop tipe 2

                System.out.print("\nEnter room number: "); // [Node 28] Input kamar
                try { // [Node 29] Validasi input + alokasi
                    rn = sc.nextInt(); // [Node 30] Baca input
                    rn = rn - 11;      // [Node 31] Normalisasi ke index
                    if (hotel_ob.deluxe_doublerrom[rn] != null) // [Node 32] Keputusan terisi?
                        throw new NotAvailable();                // [Node 33] Jalur terisi
                    CustDetails(i, rn); // [Node 34] Jalur tersedia
                } catch (Exception e) { // [Node 35] Jalur error
                    System.out.println("Invalid Option"); // [Node 36]
                    return; // [Node 37] Exit dini
                }
                break; // [Node 38] Keluar case 2

            case 3: // [Node 39] Cabang tipe 3: luxury single
                for (j = 0; j < hotel_ob.luxury_singleerrom.length; j++) { // [Node 40] Loop scan kamar tipe 3
                    if (hotel_ob.luxury_singleerrom[j] == null) { // [Node 41] Keputusan kosong?
                        System.out.print(j + 31 + ","); // [Node 42] Tampilkan kamar kosong
                    } // [Node 43] False branch: kamar terisi
                } // [Node 44] Akhir loop tipe 3

                System.out.print("\nEnter room number: "); // [Node 45] Input kamar
                try { // [Node 46] Validasi input + alokasi
                    rn = sc.nextInt(); // [Node 47]
                    rn = rn - 31;      // [Node 48]
                    if (hotel_ob.luxury_singleerrom[rn] != null) // [Node 49] Keputusan terisi?
                        throw new NotAvailable();                 // [Node 50]
                    CustDetails(i, rn); // [Node 51]
                } catch (Exception e) { // [Node 52]
                    System.out.println("Invalid Option"); // [Node 53]
                    return; // [Node 54]
                }
                break; // [Node 55] Keluar case 3

            case 4: // [Node 56] Cabang tipe 4: deluxe single
                for (j = 0; j < hotel_ob.deluxe_singleerrom.length; j++) { // [Node 57] Loop scan kamar tipe 4
                    if (hotel_ob.deluxe_singleerrom[j] == null) { // [Node 58] Keputusan kosong?
                        System.out.print(j + 41 + ","); // [Node 59] Tampilkan kamar kosong
                    } // [Node 60] False branch: kamar terisi
                } // [Node 61] Akhir loop tipe 4

                System.out.print("\nEnter room number: "); // [Node 62] Input kamar
                try { // [Node 63] Validasi input + alokasi
                    rn = sc.nextInt(); // [Node 64]
                    rn = rn - 41;      // [Node 65]
                    if (hotel_ob.deluxe_singleerrom[rn] != null) // [Node 66] Keputusan terisi?
                        throw new NotAvailable();                 // [Node 67]
                    CustDetails(i, rn); // [Node 68]
                } catch (Exception e) { // [Node 69]
                    System.out.println("Invalid Option"); // [Node 70]
                    return; // [Node 71]
                }
                break; // [Node 72] Keluar case 4

            default: // [Node 73] Cabang default: tipe kamar tidak valid
                System.out.println("Enter valid option"); // [Node 74] Pesan invalid type
                break; // [Node 75] Keluar switch
        }

        System.out.println("Room Booked"); // [Node 76] Jalur sukses (tidak kena return di catch)
    }

    public void features(int i) {
        switch (i) {
            case 1:
                System.out.println("Number of double beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day:4000 ");
                break;
            case 2:
                System.out.println("Number of double beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day:3000  ");
                break;
            case 3:
                System.out.println("Number of single beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day:2200  ");
                break;
            case 4:
                System.out.println("Number of single beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day:1200 ");
                break;
            default:
                System.out.println("Enter valid option");
                break;
        }
    }

    public void availability(int i) {
        int j, count = 0;
        switch (i) {
            case 1:
                for (j = 0; j < 10; j++) {
                    if (hotel_ob.luxury_doublerrom[j] == null)
                        count++;
                }
                break;
            case 2:
                for (j = 0; j < hotel_ob.deluxe_doublerrom.length; j++) {
                    if (hotel_ob.deluxe_doublerrom[j] == null)
                        count++;
                }
                break;
            case 3:
                for (j = 0; j < hotel_ob.luxury_singleerrom.length; j++) {
                    if (hotel_ob.luxury_singleerrom[j] == null)
                        count++;
                }
                break;
            case 4:
                for (j = 0; j < hotel_ob.deluxe_singleerrom.length; j++) {
                    if (hotel_ob.deluxe_singleerrom[j] == null)
                        count++;
                }
                break;
            default:
                System.out.println("Enter valid option");
                break;
        }
        System.out.println("Number of rooms available : " + count);
    }

    public void bill(int rn, int rtype) {
        double amount = 0;
        String list[] = {"Sandwich", "Pasta", "Noodles", "Coke"};
        System.out.println("\n*******");
        System.out.println(" Bill:-");
        System.out.println("*******");

        switch (rtype) {
            case 1:
                amount += 4000;
                System.out.println("\nRoom Charge - " + 4000);
                System.out.println("\n===============");
                System.out.println("Food Charges:- ");
                System.out.println("===============");
                System.out.println("Item   Quantity    Price");
                System.out.println("-------------------------");
                for (Food obb : hotel_ob.luxury_doublerrom[rn].food) {
                    amount += obb.price;
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format, list[obb.itemno - 1], obb.quantity, obb.price);
                }
                break;
            case 2:
                amount += 3000;
                System.out.println("Room Charge - " + 3000);
                System.out.println("\nFood Charges:- ");
                System.out.println("===============");
                System.out.println("Item   Quantity    Price");
                System.out.println("-------------------------");
                for (Food obb : hotel_ob.deluxe_doublerrom[rn].food) {
                    amount += obb.price;
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format, list[obb.itemno - 1], obb.quantity, obb.price);
                }
                break;
            case 3:
                amount += 2200;
                System.out.println("Room Charge - " + 2200);
                System.out.println("\nFood Charges:- ");
                System.out.println("===============");
                System.out.println("Item   Quantity    Price");
                System.out.println("-------------------------");
                for (Food obb : hotel_ob.luxury_singleerrom[rn].food) {
                    amount += obb.price;
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format, list[obb.itemno - 1], obb.quantity, obb.price);
                }
                break;
            case 4:
                amount += 1200;
                System.out.println("Room Charge - " + 1200);
                System.out.println("\nFood Charges:- ");
                System.out.println("===============");
                System.out.println("Item   Quantity    Price");
                System.out.println("-------------------------");
                for (Food obb : hotel_ob.deluxe_singleerrom[rn].food) {
                    amount += obb.price;
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format, list[obb.itemno - 1], obb.quantity, obb.price);
                }
                break;
            default:
                System.out.println("Not valid");
        }
        System.out.println("\nTotal Amount- " + amount);
    }

    //Path deallocate(int rn, int rtype) {
    public void deallocate(int rn, int rtype) {
        char w; // [Node 1] Inisialisasi
        switch (rtype) { // [Node 2] Percabangan utama

            case 1:
                // [Node 3] Pengecekan kondisi kamar
                if (hotel_ob.luxury_doublerrom[rn] != null) {
                    // [Node 4] Jika tidak kosong
                    System.out.println("Room used by " + hotel_ob.luxury_doublerrom[rn].name);
                } else {
                    // [Node 5] Jika kosong (Terminal Node / Exit)
                    System.out.println("Empty Already");
                    return;
                }

                // [Node 6] Sekuensial setelah Node 4
                System.out.println("Do you want to checkout ?(y/n)");
                w = sc.next().charAt(0);

                // [Node 7] Percabangan checkout
                if (w == 'y' || w == 'Y') {
                    // [Node 8] Proses checkout
                    bill(rn, rtype);
                    hotel_ob.luxury_doublerrom[rn] = null;
                    System.out.println("Deallocated succesfully");
                }
                break; // [Node 9] Keluar dari switch

            case 2:
                // [Node 10] Pengecekan kondisi kamar
                if (hotel_ob.deluxe_doublerrom[rn] != null) {
                    // [Node 11] Jika tidak kosong
                    System.out.println("Room used by " + hotel_ob.deluxe_doublerrom[rn].name);
                } else {
                    // [Node 12] Jika kosong (Terminal Node / Exit)
                    System.out.println("Empty Already");
                    return;
                }

                // [Node 13] Sekuensial setelah Node 11
                System.out.println(" Do you want to checkout ?(y/n)");
                w = sc.next().charAt(0);

                // [Node 14] Percabangan checkout
                if (w == 'y' || w == 'Y') {
                    // [Node 15] Proses checkout
                    bill(rn, rtype);
                    hotel_ob.deluxe_doublerrom[rn] = null;
                    System.out.println("Deallocated succesfully");
                }
                break; // [Node 16] Keluar dari switch
            case 3:
                // [Node 17] Pengecekan kondisi kamar
                if (hotel_ob.luxury_singleerrom[rn] != null) {
                    // [Node 18] Jika tidak kosong
                    System.out.println("Room used by " + hotel_ob.luxury_singleerrom[rn].name);
                } else {
                    // [Node 19] Jika kosong (Terminal Node / Exit)
                    System.out.println("Empty Already");
                    return;
                }
                // [Node 20] Sekuensial setelah Node 18
                System.out.println(" Do you want to checkout ? (y/n)");
                w = sc.next().charAt(0);

                // [Node 21] Percabangan checkout
                if (w == 'y' || w == 'Y') {
                    // [Node 22] Proses checkout
                    bill(rn, rtype);
                    hotel_ob.luxury_singleerrom[rn] = null;
                    System.out.println("Deallocated succesfully");
                }
                break; // [Node 23] Keluar dari switch

            case 4:
                // [Node 24] Pengecekan kondisi kamar
                if (hotel_ob.deluxe_singleerrom[rn] != null) {
                    // [Node 25] Jika tidak kosong
                    System.out.println("Room used by " + hotel_ob.deluxe_singleerrom[rn].name);
                } else {
                    // [Node 26] Jika kosong (Terminal Node / Exit)
                    System.out.println("Empty Already");
                    return;
                }
                // [Node 27] Sekuensial setelah Node 25
                System.out.println(" Do you want to checkout ? (y/n)");
                w = sc.next().charAt(0);

                // [Node 28] Percabangan checkout
                if (w == 'y' || w == 'Y') {
                    // [Node 29] Proses checkout
                    bill(rn, rtype);
                    hotel_ob.deluxe_singleerrom[rn] = null;
                    System.out.println("Deallocated succesfully");
                }
                break; // [Node 30] Keluar dari switch

            default:
                // [Node 31] Penanganan Default
                System.out.println("\nEnter valid option : ");
                break; // [Node 32] Keluar dari switch
        }
    }

    public void order(int rn, int rtype) {
        int i, q;
        char wish;
        try {
            System.out.println("\n==========\n   Menu:  \n==========\n\n1.Sandwich\tRs.50\n2.Pasta\t\tRs.60\n3.Noodles\tRs.70\n4.Coke\t\tRs.30\n");
            do {
                i = sc.nextInt();
                System.out.print("Quantity- ");
                q = sc.nextInt();

                switch (rtype) {
                    case 1:
                        hotel_ob.luxury_doublerrom[rn].food.add(new Food(i, q));
                        break;
                    case 2:
                        hotel_ob.deluxe_doublerrom[rn].food.add(new Food(i, q));
                        break;
                    case 3:
                        hotel_ob.luxury_singleerrom[rn].food.add(new Food(i, q));
                        break;
                    case 4:
                        hotel_ob.deluxe_singleerrom[rn].food.add(new Food(i, q));
                        break;
                }
                System.out.println("Do you want to order anything else ? (y/n)");
                wish = sc.next().charAt(0);
            } while (wish == 'y' || wish == 'Y');
        } catch (NullPointerException e) {
            System.out.println("\nRoom not booked");
        } catch (Exception e) {
            System.out.println("Cannot be done");
        }
    }
}
