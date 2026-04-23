package org.test.services;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.test.models.Doubleroom;
import org.test.models.Food;
import org.test.models.Singleroom;

@Tag("unit")
@DisplayName("Unit tests for HotelServiceImpl Order Method")
class HotelServiceImplOrderTest {

    private HotelServiceImpl hotelService;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        hotelService = new HotelServiceImpl();
        // Reset objek statis hotel_ob untuk setiap test
        HotelServiceImpl.hotel_ob.luxury_doublerrom = new Doubleroom[10];
        HotelServiceImpl.hotel_ob.deluxe_doublerrom = new Doubleroom[10];
        HotelServiceImpl.hotel_ob.luxury_singleerrom = new Singleroom[20];
        HotelServiceImpl.hotel_ob.deluxe_singleerrom = new Singleroom[20];
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Nested
    @DisplayName("Test class for order scenarios - Luxury Double Room")
    class LuxuryDoubleRoomOrderTest {

        @Test
        @DisplayName("Memverifikasi sistem menambahkan makanan ke Luxury Double Room dan menghentikan pemesanan saat continue = 'n'")
        void given_whenOrderFoodWithMenuChoice1Qty2_thenAssertBody() throws Exception {
            // Given
            HotelServiceImpl.hotel_ob.luxury_doublerrom[0] = new Doubleroom("John Doe", "1234567890", "M", "Jane Doe", "0987654321", "F");
            String input = "1\n2\nn\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            Field scField = HotelServiceImpl.class.getDeclaredField("sc");
            scField.setAccessible(true);
            scField.set(null, new Scanner(System.in));

            // When
            hotelService.order(0, 1);

            // Then
            assertNotNull(HotelServiceImpl.hotel_ob.luxury_doublerrom[0].food, "Food list harus ada");
            assertEquals(1, HotelServiceImpl.hotel_ob.luxury_doublerrom[0].food.size(), "Food list harus berisi tepat 1 item");
            Food addedFood = HotelServiceImpl.hotel_ob.luxury_doublerrom[0].food.getFirst();
            assertEquals(1, addedFood.itemno, "Pilihan menu harus 1 (Sandwich)");
            assertEquals(2, addedFood.quantity, "Kuantitas harus 2");
            assertEquals(100, addedFood.price, "Harga harus 100 (2 * 50)");
        }

        @Test
        @DisplayName("Memverifikasi sistem memungkinkan penambahan multiple items saat continue = 'y' dan menghentikan saat = 'n'")
        void given_whenOrderMultipleItemsWithContinueFlag_thenAssertBody() throws Exception {
            // Given
            HotelServiceImpl.hotel_ob.luxury_doublerrom[0] = new Doubleroom("John Doe", "1234567890", "M", "Jane Doe", "0987654321", "F");
            String input = "1\n1\ny\n2\n1\nn\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            Field scField = HotelServiceImpl.class.getDeclaredField("sc");
            scField.setAccessible(true);
            scField.set(null, new Scanner(System.in));

            // When
            hotelService.order(0, 1);

            // Then
            assertEquals(2, HotelServiceImpl.hotel_ob.luxury_doublerrom[0].food.size(), "Food list harus berisi tepat 2 item");
            Food firstFood = HotelServiceImpl.hotel_ob.luxury_doublerrom[0].food.getFirst();
            assertEquals(1, firstFood.itemno, "Item pertama harus Sandwich");
            assertEquals(1, firstFood.quantity, "Kuantitas item pertama harus 1");
            Food secondFood = HotelServiceImpl.hotel_ob.luxury_doublerrom[0].food.get(1);
            assertEquals(2, secondFood.itemno, "Item kedua harus Pasta");
            assertEquals(1, secondFood.quantity, "Kuantitas item kedua harus 1");
        }
    }

    @Nested
    @DisplayName("Test class for order scenarios - Deluxe Double Room")
    class DeluxeDoubleRoomOrderTest {

        @Test
        @DisplayName("Memverifikasi sistem menambahkan Pasta ke Deluxe Double Room dengan kalkulasi harga yang benar")
        void given_whenOrderFoodWithMenuChoice2Qty1_thenAssertBody() throws Exception {
            // Given
            HotelServiceImpl.hotel_ob.deluxe_doublerrom[0] = new Doubleroom("Alice Smith", "1111111111", "F", "Bob Smith", "2222222222", "M");
            String input = "2\n1\nn\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            Field scField = HotelServiceImpl.class.getDeclaredField("sc");
            scField.setAccessible(true);
            scField.set(null, new Scanner(System.in));

            // When
            hotelService.order(0, 2);

            // Then
            assertNotNull(HotelServiceImpl.hotel_ob.deluxe_doublerrom[0].food, "Food list harus ada");
            assertEquals(1, HotelServiceImpl.hotel_ob.deluxe_doublerrom[0].food.size(), "Food list harus berisi tepat 1 item");
            Food addedFood = HotelServiceImpl.hotel_ob.deluxe_doublerrom[0].food.getFirst();
            assertEquals(2, addedFood.itemno, "Pilihan menu harus 2 (Pasta)");
            assertEquals(1, addedFood.quantity, "Kuantitas harus 1");
            assertEquals(60, addedFood.price, "Harga harus 60 (1 * 60)");
        }
    }

    @Nested
    @DisplayName("Test class for order scenarios - Luxury Single Room")
    class LuxurySingleRoomOrderTest {

        @Test
        @DisplayName("Memverifikasi sistem menambahkan Noodles ke Luxury Single Room dengan kalkulasi harga yang benar")
        void given_whenOrderFoodWithMenuChoice3Qty1_thenAssertBody() throws Exception {
            // Given
            HotelServiceImpl.hotel_ob.luxury_singleerrom[0] = new Singleroom("Charlie Brown", "3333333333", "M");
            String input = "3\n1\nn\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            Field scField = HotelServiceImpl.class.getDeclaredField("sc");
            scField.setAccessible(true);
            scField.set(null, new Scanner(System.in));

            // When
            hotelService.order(0, 3);

            // Then
            assertNotNull(HotelServiceImpl.hotel_ob.luxury_singleerrom[0].food, "Food list harus ada");
            assertEquals(1, HotelServiceImpl.hotel_ob.luxury_singleerrom[0].food.size(), "Food list harus berisi tepat 1 item");
            Food addedFood = HotelServiceImpl.hotel_ob.luxury_singleerrom[0].food.getFirst();
            assertEquals(3, addedFood.itemno, "Pilihan menu harus 3 (Noodles)");
            assertEquals(1, addedFood.quantity, "Kuantitas harus 1");
            assertEquals(70, addedFood.price, "Harga harus 70 (1 * 70)");
        }
    }

    @Nested
    @DisplayName("Test class for order scenarios - Deluxe Single Room")
    class DeluxeSingleRoomOrderTest {

        @Test
        @DisplayName("Memverifikasi sistem menambahkan Coke ke Deluxe Single Room dengan kalkulasi harga yang benar")
        void given_whenOrderFoodWithMenuChoice4Qty2_thenAssertBody() throws Exception {
            // Given
            HotelServiceImpl.hotel_ob.deluxe_singleerrom[0] = new Singleroom("Diana Prince", "4444444444", "F");
            String input = "4\n2\nn\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            Field scField = HotelServiceImpl.class.getDeclaredField("sc");
            scField.setAccessible(true);
            scField.set(null, new Scanner(System.in));

            // When
            hotelService.order(0, 4);

            // Then
            assertNotNull(HotelServiceImpl.hotel_ob.deluxe_singleerrom[0].food, "Food list harus ada");
            assertEquals(1, HotelServiceImpl.hotel_ob.deluxe_singleerrom[0].food.size(), "Food list harus berisi tepat 1 item");
            Food addedFood = HotelServiceImpl.hotel_ob.deluxe_singleerrom[0].food.getFirst();
            assertEquals(4, addedFood.itemno, "Pilihan menu harus 4 (Coke)");
            assertEquals(2, addedFood.quantity, "Kuantitas harus 2");
            assertEquals(60, addedFood.price, "Harga harus 60 (2 * 30)");
        }
    }

    @Nested
    @DisplayName("Test class for error handling scenarios")
    class ErrorHandlingTest {

        @Test
        @DisplayName("Memverifikasi sistem menangani NullPointerException saat kamar belum dibooking dan menampilkan pesan 'Room not booked'")
        void givenRoomNotBooked_thenShowRoomNotBookedMessage() throws Exception {
            // Given
            // kamar tidak diinisialisasi (null)
            String input = "1\n1\nn\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);

            Field scField = HotelServiceImpl.class.getDeclaredField("sc");
            scField.setAccessible(true);
            scField.set(null, new Scanner(System.in));

            // Capture output
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // When
            hotelService.order(0, 1);

            // Then
            String output = outContent.toString();
            assertTrue(output.contains("Room not booked"),
                    "Harus menampilkan pesan 'Room not booked'");
        }
    }

    @Nested
    @DisplayName("Test class for price calculation verification")
    class PriceCalculationTest {

        @Test
        @DisplayName("Verify Sandwich price calculation (50 per unit)")
        void given_whenOrderSandwich_thenVerifyPriceCalculation() throws Exception {
            // Given
            HotelServiceImpl.hotel_ob.luxury_doublerrom[0] = new Doubleroom("Test", "123", "M", "Test2", "456", "F");
            String input = "1\n3\nn\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            Field scField = HotelServiceImpl.class.getDeclaredField("sc");
            scField.setAccessible(true);
            scField.set(null, new Scanner(System.in));

            // When
            hotelService.order(0, 1);

            // Then
            assertEquals(150, HotelServiceImpl.hotel_ob.luxury_doublerrom[0].food.getFirst().price, "Harga Sandwich harus 150 (3 * 50)");
        }

        @Test
        @DisplayName("Verify Pasta price calculation (60 per unit)")
        void given_whenOrderPasta_thenVerifyPriceCalculation() throws Exception {
            // Given
            HotelServiceImpl.hotel_ob.deluxe_doublerrom[0] = new Doubleroom("Test", "123", "M", "Test2", "456", "F");
            String input = "2\n2\nn\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            Field scField = HotelServiceImpl.class.getDeclaredField("sc");
            scField.setAccessible(true);
            scField.set(null, new Scanner(System.in));

            // When
            hotelService.order(0, 2);

            // Then
            assertEquals(120, HotelServiceImpl.hotel_ob.deluxe_doublerrom[0].food.getFirst().price, "Harga Pasta harus 120 (2 * 60)");
        }

        @Test
        @DisplayName("Verify Noodles price calculation (70 per unit)")
        void given_whenOrderNoodles_thenVerifyPriceCalculation() throws Exception {
            // Given
            HotelServiceImpl.hotel_ob.luxury_singleerrom[0] = new Singleroom("Test", "123", "M");
            String input = "3\n3\nn\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            Field scField = HotelServiceImpl.class.getDeclaredField("sc");
            scField.setAccessible(true);
            scField.set(null, new Scanner(System.in));

            // When
            hotelService.order(0, 3);

            // Then
            assertEquals(210, HotelServiceImpl.hotel_ob.luxury_singleerrom[0].food.getFirst().price, "Harga Noodles harus 210 (3 * 70)");
        }

        @Test
        @DisplayName("Verify Coke price calculation (30 per unit)")
        void given_whenOrderCoke_thenVerifyPriceCalculation() throws Exception {
            // Given
            HotelServiceImpl.hotel_ob.deluxe_singleerrom[0] = new Singleroom("Test", "123", "M");
            String input = "4\n4\nn\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            Field scField = HotelServiceImpl.class.getDeclaredField("sc");
            scField.setAccessible(true);
            scField.set(null, new Scanner(System.in));

            // When
            hotelService.order(0, 4);

            // Then
            assertEquals(120, HotelServiceImpl.hotel_ob.deluxe_singleerrom[0].food.getFirst().price, "Harga Coke harus 120 (4 * 30)");
        }
    }
}
