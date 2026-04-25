package org.test.services;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.*;
import org.test.models.Doubleroom;
import org.test.models.Food;
import org.test.models.Singleroom;

@Tag("unit")
@DisplayName("Unit tests for HotelServiceImpl Bill Method")
class HotelServiceImplBillTest {

    private HotelServiceImpl hotelService;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        hotelService = new HotelServiceImpl();

        // reset data
        HotelServiceImpl.hotel_ob.luxury_doublerrom = new Doubleroom[10];
        HotelServiceImpl.hotel_ob.deluxe_doublerrom = new Doubleroom[10];
        HotelServiceImpl.hotel_ob.luxury_singleerrom = new Singleroom[20];
        HotelServiceImpl.hotel_ob.deluxe_singleerrom = new Singleroom[20];
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    /* =========================
       HELPER (WAJIB UNTUK OUTPUT)
    ========================= */
    private String getOutput(ByteArrayOutputStream outContent) {
        String output = outContent.toString();

        // restore ke terminal
        System.setOut(originalOut);

        // tampilkan ke console
        System.out.println("=== DEBUG OUTPUT ===");
        System.out.println(output);

        return output;
    }

    /* =========================
       LUXURY DOUBLE ROOM
    ========================= */
    @Nested
    @DisplayName("Bill Test - Luxury Double Room")
    class LuxuryDoubleRoomBillTest {

        @Test
        @DisplayName("TC-01: Dengan makanan (Sandwich qty 2)")
        void givenFoodExists_whenBillCalled_thenCorrectOutput() {

            // Given
            HotelServiceImpl.hotel_ob.luxury_doublerrom[0] =
                    new Doubleroom("John","08123","M","Jane","08456","F");
            HotelServiceImpl.hotel_ob.luxury_doublerrom[0].food.add(new Food(1,2));

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // When
            hotelService.bill(0,1);

            // Then
            String output = getOutput(outContent);

            assertTrue(output.contains("Room Charge - 4000"));
            assertTrue(output.contains("Sandwich"));
            assertTrue(output.contains("100.0"));
            assertTrue(output.contains("Total Amount- 4100.0"));
        }

        @Test
        @DisplayName("TC-02: Tanpa makanan")
        void givenNoFood_whenBillCalled_thenOnlyRoomCharge() {

            // Given
            HotelServiceImpl.hotel_ob.luxury_doublerrom[0] =
                    new Doubleroom("John","08123","M","Jane","08456","F");

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // When
            hotelService.bill(0,1);

            // Then
            String output = getOutput(outContent);

            assertTrue(output.contains("Room Charge - 4000"));
            assertFalse(output.contains("Sandwich"));
            assertFalse(output.contains("Pasta"));
            assertFalse(output.contains("Noodles"));
            assertFalse(output.contains("Coke"));
            assertTrue(output.contains("Total Amount- 4000.0"));
        }
    }

    /* =========================
       DELUXE DOUBLE ROOM
    ========================= */
    @Nested
    @DisplayName("Bill Test - Deluxe Double Room")
    class DeluxeDoubleRoomBillTest {

        @Test
        @DisplayName("TC-03: Dengan makanan (Pasta)")
        void givenFoodExists_whenBillCalled_thenCorrectOutput() {

            // Given
            HotelServiceImpl.hotel_ob.deluxe_doublerrom[0] =
                    new Doubleroom("Alice","0811","F","Bob","0822","M");
            HotelServiceImpl.hotel_ob.deluxe_doublerrom[0].food.add(new Food(2,1));

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // When
            hotelService.bill(0,2);

            // Then
            String output = getOutput(outContent);

            assertTrue(output.contains("Room Charge - 3000"));
            assertTrue(output.contains("Pasta"));
            assertTrue(output.contains("60.0"));
            assertTrue(output.contains("Total Amount- 3060.0"));
        }

        @Test
        @DisplayName("TC-04: Tanpa makanan")
        void givenNoFood_whenBillCalled_thenOnlyRoomCharge() {

            // Given
            HotelServiceImpl.hotel_ob.deluxe_doublerrom[0] =
                    new Doubleroom("Alice","0811","F","Bob","0822","M");

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // When
            hotelService.bill(0,2);

            // Then
            String output = getOutput(outContent);

            assertTrue(output.contains("Room Charge - 3000"));
            assertFalse(output.contains("Pasta"));
            assertTrue(output.contains("Total Amount- 3000.0"));
        }
    }

    /* =========================
       LUXURY SINGLE ROOM
    ========================= */
    @Nested
    @DisplayName("Bill Test - Luxury Single Room")
    class LuxurySingleRoomBillTest {

        @Test
        @DisplayName("TC-05: Dengan makanan (Noodles)")
        void givenFoodExists_whenBillCalled_thenCorrectOutput() {

            // Given
            HotelServiceImpl.hotel_ob.luxury_singleerrom[0] =
                    new Singleroom("Charlie","0833","M");
            HotelServiceImpl.hotel_ob.luxury_singleerrom[0].food.add(new Food(3,1));

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // When
            hotelService.bill(0,3);

            // Then
            String output = getOutput(outContent);

            assertTrue(output.contains("Room Charge - 2200"));
            assertTrue(output.contains("Noodles"));
            assertTrue(output.contains("70.0"));
            assertTrue(output.contains("Total Amount- 2270.0"));
        }

        @Test
        @DisplayName("TC-06: Tanpa makanan")
        void givenNoFood_whenBillCalled_thenOnlyRoomCharge() {

            // Given
            HotelServiceImpl.hotel_ob.luxury_singleerrom[0] =
                    new Singleroom("Charlie","0833","M");

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // When
            hotelService.bill(0,3);

            // Then
            String output = getOutput(outContent);

            assertTrue(output.contains("Room Charge - 2200"));
            assertFalse(output.contains("Noodles"));
            assertTrue(output.contains("Total Amount- 2200.0"));
        }
    }

    /* =========================
       DELUXE SINGLE ROOM
    ========================= */
    @Nested
    @DisplayName("Bill Test - Deluxe Single Room")
    class DeluxeSingleRoomBillTest {

        @Test
        @DisplayName("TC-07: Dengan makanan (Coke)")
        void givenFoodExists_whenBillCalled_thenCorrectOutput() {

            // Given
            HotelServiceImpl.hotel_ob.deluxe_singleerrom[0] =
                    new Singleroom("Diana","0844","F");
            HotelServiceImpl.hotel_ob.deluxe_singleerrom[0].food.add(new Food(4,2));

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // When
            hotelService.bill(0,4);

            // Then
            String output = getOutput(outContent);

            assertTrue(output.contains("Room Charge - 1200"));
            assertTrue(output.contains("Coke"));
            assertTrue(output.contains("60.0"));
            assertTrue(output.contains("Total Amount- 1260.0"));
        }
    }

    /* =========================
       INVALID CASE
    ========================= */
    @Nested
    @DisplayName("Bill Test - Invalid Case")
    class InvalidBillTest {

        @Test
        @DisplayName("TC-08: Room type tidak valid")
        void givenInvalidRoomType_whenBillCalled_thenShowError() {

            // Given
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // When
            hotelService.bill(0,5);

            // Then
            String output = getOutput(outContent);

            assertTrue(output.contains("Not valid"));
            assertFalse(output.contains("Room Charge"));
            assertTrue(output.contains("Total Amount- 0.0"));
        }
    }
}