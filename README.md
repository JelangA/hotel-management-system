# Hotel Management System

Sistem manajemen hotel berbasis Java yang memungkinkan pengguna untuk melakukan pemesanan kamar, ordering makanan, dan checkout dengan perhitungan bill otomatis.

## Struktur Folder

```
unit-test-ppl/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── test/
│   │   │           ├── Main.java
│   │   │           ├── model/
│   │   │           │   ├── Food.java
│   │   │           │   ├── Singleroom.java
│   │   │           │   └── Doubleroom.java
│   │   │           ├── service/
│   │   │           │   └── HotelService.java
│   │   │           ├── data/
│   │   │           │   └── HotelData.java
│   │   │           ├── exception/
│   │   │           │   └── NotAvailable.java
│   │   │           └── persistence/
│   │   │               └── DataPersistence.java
│   │   └── resources/
│   └── test/
│       └── java/
├── target/
├── backup
├── pom.xml
└── README.md
```

## Deskripsi Modul

### 1. Model Layer (org.test.model)

#### Food.java
Mewakili item makanan/minuman yang dapat dipesan di hotel.
- **Atribut:**
  - `itemno`: Nomor item (1=Sandwich Rp.50, 2=Pasta Rp.60, 3=Noodles Rp.70, 4=Coke Rp.30)
  - `quantity`: Jumlah item yang dipesan
  - `price`: Harga total item

#### Singleroom.java
Model untuk kamar tipe single.
- **Atribut:**
  - `name`: Nama tamu
  - `contact`: Nomor kontak tamu
  - `gender`: Jenis kelamin tamu
  - `food`: ArrayList berisi riwayat pesanan makanan

#### Doubleroom.java
Model untuk kamar tipe double yang memperluas Singleroom.
- **Atribut tambahan:**
  - `name2`: Nama tamu kedua
  - `contact2`: Nomor kontak tamu kedua
  - `gender2`: Jenis kelamin tamu kedua

### 2. Exception Layer (org.test.exception)

#### NotAvailable.java
Exception custom yang digunakan ketika kamar tidak tersedia.

### 3. Data Layer (org.test.data)

#### HotelData.java
Menyimpan data kamar hotel dalam berbagai tipe.
- **Atribut:**
  - `luxury_doublerrom`: Array 10 kamar double luxury
  - `deluxe_doublerrom`: Array 20 kamar double deluxe
  - `luxury_singleerrom`: Array 10 kamar single luxury
  - `deluxe_singleerrom`: Array 20 kamar single deluxe

### 4. Service Layer (org.test.service)

#### HotelService.java
Berisi logika bisnis utama aplikasi hotel.
- **Metode utama:**
  - `CustDetails(int i, int rn)`: Input detail customer
  - `bookroom(int i)`: Proses pemesanan kamar
  - `features(int i)`: Tampilkan fitur kamar
  - `availability(int i)`: Tampilkan ketersediaan kamar
  - `bill(int rn, int rtype)`: Hitung dan tampilkan bill
  - `deallocate(int rn, int rtype)`: Proses checkout
  - `order(int rn, int rtype)`: Proses pesanan makanan

### 5. Persistence Layer (org.test.persistence)

#### DataPersistence.java
Menangani penyimpanan data hotel ke file backup menggunakan serialization.
- Menggunakan Thread untuk menyimpan data secara asynchronous
- Membuat/memperbarui file `backup`

### 6. Main Application (org.test)

#### Main.java
Entry point aplikasi yang mengkoordinasikan semua modul.
- Menginisialisasi HotelData dan HotelService
- Membaca backup data jika file exists
- Menampilkan menu utama aplikasi
- Menghandle input pengguna

## Daftar Kamar dan Harga

### Luxury Double Room (Rooms 1-10)
- Jumlah double bed: 1
- AC: Ya
- Free breakfast: Ya
- Harga/hari: Rp. 4000

### Deluxe Double Room (Rooms 11-30)
- Jumlah double bed: 1
- AC: Tidak
- Free breakfast: Ya
- Harga/hari: Rp. 3000

### Luxury Single Room (Rooms 31-40)
- Jumlah single bed: 1
- AC: Ya
- Free breakfast: Ya
- Harga/hari: Rp. 2200

### Deluxe Single Room (Rooms 41-60)
- Jumlah single bed: 1
- AC: Tidak
- Free breakfast: Ya
- Harga/hari: Rp. 1200

## Menu Makanan

| No | Item | Harga |
|----|------|-------|
| 1 | Sandwich | Rp. 50 |
| 2 | Pasta | Rp. 60 |
| 3 | Noodles | Rp. 70 |
| 4 | Coke | Rp. 30 |

## Cara Menggunakan Aplikasi

### Kompilasi
```bash
javac -d target/classes src/main/java/org/test/**/*.java
```

### Run
```bash
java -cp target/classes org.test.Main
```

### Menu Utama
1. **Display Room Details** - Tampilkan fitur dan spesifikasi kamar
2. **Display Room Availability** - Lihat ketersediaan kamar berdasarkan tipe
3. **Book** - Pesan kamar baru
4. **Order Food** - Pesan makanan untuk kamar yang sudah dipesan
5. **Checkout** - Proses checkout dan bayar bill
6. **Exit** - Keluar dari aplikasi

## Alur Pemesanan

1. Pilih tipe kamar (Luxury Double, Deluxe Double, Luxury Single, Deluxe Single)
2. Lihat nomor kamar yang tersedia
3. Masukkan data tamu (nama, nomor kontak, jenis kelamin)
4. Kamar berhasil dipesan
5. Customer dapat memesan makanan menggunakan nomor kamar
6. Saat checkout, bill otomatis dihitung dan ditampilkan
7. Data disimpan ke file backup secara otomatis

## Fitur

- ✅ Pemesanan kamar dengan berbagai tipe
- ✅ Sistem billing otomatis
- ✅ Ordering makanan/minuman
- ✅ Tracking ketersediaan kamar real-time
- ✅ Penyimpanan data persistent menggunakan serialization
- ✅ Multi-threading untuk backup data
- ✅ Exception handling untuk kamar yang tidak tersedia

## Teknologi

- Java 8+
- Object-Oriented Programming (OOP)
- Serialization untuk data persistence
- Multi-threading
- Exception Handling

## File Output

- `backup`: File berisi serialized object HotelData untuk restore data saat startup

## Author

Tim Pengembang

## License

MIT
