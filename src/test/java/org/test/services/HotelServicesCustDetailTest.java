package org.test.services;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.test.models.Doubleroom;
import org.test.models.Singleroom;

@DisplayName("Unit Test for CustDetails Method")
class HotelServicesCustDetailTest {

    private HotelServiceImpl service;

    @BeforeEach
    void setUp() throws Exception {
        service = new HotelServiceImpl();

        // Reset semua array sebelum test
        HotelServiceImpl.hotel_ob.luxury_doublerrom = new Doubleroom[10];
        HotelServiceImpl.hotel_ob.deluxe_doublerrom = new Doubleroom[10];
        HotelServiceImpl.hotel_ob.luxury_singleerrom = new Singleroom[20];
        HotelServiceImpl.hotel_ob.deluxe_singleerrom = new Singleroom[20];
    }

    private void injectInput(String data) throws Exception {
        InputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);

        Field scField = HotelServiceImpl.class.getDeclaredField("sc");
        scField.setAccessible(true);
        scField.set(null, new Scanner(System.in));
    }

    // ================================
    // TC-01: Luxury Double Room
    // ================================
    @Nested
    @DisplayName("TC-01 Luxury Double Room")
    class TC01 {

        @Test
        void testBookingLuxuryDoubleRoom() throws Exception {
            injectInput("Andi\n08111\nM\nBudi\n08222\nM\n");

            service.CustDetails(1, 0);

            Doubleroom room = HotelServiceImpl.hotel_ob.luxury_doublerrom[0];

            assertNotNull(room);
            assertEquals("Andi", room.name);
            assertEquals("08111", room.contact);
            assertEquals("M", room.gender);
            assertEquals("Budi", room.name2);
            assertEquals("08222", room.contact2);
            assertEquals("M", room.gender2);
        }
    }

    // ================================
    // TC-02: Deluxe Double Room
    // ================================
    @Nested
    @DisplayName("TC-02 Deluxe Double Room")
    class TC02 {

        @Test
        void testBookingDeluxeDoubleRoom() throws Exception {
            injectInput("Citra\n08333\nF\nDewi\n08444\nF\n");

            service.CustDetails(2, 0);

            Doubleroom room = HotelServiceImpl.hotel_ob.deluxe_doublerrom[0];

            assertNotNull(room);
            assertEquals("Citra", room.name);
            assertEquals("Dewi", room.name2);
        }
    }

    // ================================
    // TC-03: Luxury Single Room
    // ================================
    @Nested
    @DisplayName("TC-03 Luxury Single Room")
    class TC03 {

        @Test
        void testBookingLuxurySingleRoom() throws Exception {
            injectInput("Eka\n08555\nF\n");

            service.CustDetails(3, 0);

            Singleroom room = HotelServiceImpl.hotel_ob.luxury_singleerrom[0];

            assertNotNull(room);
            assertEquals("Eka", room.name);
        }
    }

    // ================================
    // TC-04: Deluxe Single Room
    // ================================
    @Nested
    @DisplayName("TC-04 Deluxe Single Room")
    class TC04 {

        @Test
        void testBookingDeluxeSingleRoom() throws Exception {
            injectInput("Fajar\n08666\nM\n");

            service.CustDetails(4, 0);

            Singleroom room = HotelServiceImpl.hotel_ob.deluxe_singleerrom[0];

            assertNotNull(room);
            assertEquals("Fajar", room.name);
        }
    }

    // ================================
    // TC-05: Invalid Option (i = 5)
    // ================================
    @Nested
    @DisplayName("TC-05 Invalid Input (i = 5)")
    class TC05 {

        @Test
        void testInvalidOption() throws Exception {
            injectInput("Gilang\n08777\nM\n");

            service.CustDetails(5, 0);

            assertNull(HotelServiceImpl.hotel_ob.luxury_doublerrom[0]);
            assertNull(HotelServiceImpl.hotel_ob.deluxe_doublerrom[0]);
            assertNull(HotelServiceImpl.hotel_ob.luxury_singleerrom[0]);
            assertNull(HotelServiceImpl.hotel_ob.deluxe_singleerrom[0]);
        }
    }

    // ================================
    // TC-06: Invalid (i = 0)
    // ================================
    @Nested
    @DisplayName("TC-06 Invalid Input (i = 0)")
    class TC06 {

        @Test
        void testZeroInputBehavior() throws Exception {
            injectInput("Hani\n08888\nF\nIrma\n08999\nF\n");

            service.CustDetails(0, 0);

            // Tidak boleh ada object tersimpan
            assertNull(HotelServiceImpl.hotel_ob.luxury_doublerrom[0]);
            assertNull(HotelServiceImpl.hotel_ob.deluxe_doublerrom[0]);
            assertNull(HotelServiceImpl.hotel_ob.luxury_singleerrom[0]);
            assertNull(HotelServiceImpl.hotel_ob.deluxe_singleerrom[0]);
        }
    }
}