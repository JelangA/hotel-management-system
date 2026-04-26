package org.test.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.test.models.Doubleroom;
import org.test.models.Singleroom;
import org.test.models.holder;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HotelServiceImplBookRoomTest {

    @BeforeEach
    void setUp() {
        HotelServiceImpl.hotel_ob = new holder();
        setScannerInput("");
    }

    private void setScannerInput(String input) {
        HotelServiceImpl.sc = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    }

    private Doubleroom occupiedDouble() {
        return new Doubleroom("A", "081", "M", "B", "082", "F");
    }

    private Singleroom occupiedSingle() {
        return new Singleroom("A", "081", "M");
    }

    // -------- bookroom() --------

    @Test
    @DisplayName("bookroom path 1: case 1 success with available first room")
    void bookroomPath1_case1_successSkipLoop() {
        TestableBookroomService service = new TestableBookroomService();
        setScannerInput("1\n");

        service.bookroom(1);

        assertTrue(service.custDetailsCalled);
        assertEquals(1, service.custType);
        assertEquals(0, service.custRn);
        assertNotNull(HotelServiceImpl.hotel_ob.luxury_doublerrom[0]);
    }

    @Test
    @DisplayName("bookroom path 2: case 1 success with one occupied room in loop")
    void bookroomPath2_case1_successWithLoopIteration() {
        TestableBookroomService service = new TestableBookroomService();
        HotelServiceImpl.hotel_ob.luxury_doublerrom[0] = occupiedDouble();
        setScannerInput("2\n");

        service.bookroom(1);

        assertTrue(service.custDetailsCalled);
        assertEquals(1, service.custType);
        assertEquals(1, service.custRn);
        assertNotNull(HotelServiceImpl.hotel_ob.luxury_doublerrom[1]);
    }

    @Test
    @DisplayName("bookroom path 3: case 1 fail when selected room already occupied")
    void bookroomPath3_case1_failRoomNotAvailable() {
        TestableBookroomService service = new TestableBookroomService();
        Doubleroom before = occupiedDouble();
        HotelServiceImpl.hotel_ob.luxury_doublerrom[0] = before;
        setScannerInput("1\n");

        service.bookroom(1);

        assertFalse(service.custDetailsCalled);
        assertSame(before, HotelServiceImpl.hotel_ob.luxury_doublerrom[0]);
    }

    @Test
    @DisplayName("bookroom path 4: case 2 success with available first room")
    void bookroomPath4_case2_successSkipLoop() {
        TestableBookroomService service = new TestableBookroomService();
        setScannerInput("11\n");

        service.bookroom(2);

        assertTrue(service.custDetailsCalled);
        assertEquals(2, service.custType);
        assertEquals(0, service.custRn);
        assertNotNull(HotelServiceImpl.hotel_ob.deluxe_doublerrom[0]);
    }

    @Test
    @DisplayName("bookroom path 5: case 2 success with one occupied room in loop")
    void bookroomPath5_case2_successWithLoopIteration() {
        TestableBookroomService service = new TestableBookroomService();
        HotelServiceImpl.hotel_ob.deluxe_doublerrom[0] = occupiedDouble();
        setScannerInput("12\n");

        service.bookroom(2);

        assertTrue(service.custDetailsCalled);
        assertEquals(2, service.custType);
        assertEquals(1, service.custRn);
        assertNotNull(HotelServiceImpl.hotel_ob.deluxe_doublerrom[1]);
    }

    @Test
    @DisplayName("bookroom path 6: case 2 fail when selected room already occupied")
    void bookroomPath6_case2_failRoomNotAvailable() {
        TestableBookroomService service = new TestableBookroomService();
        Doubleroom before = occupiedDouble();
        HotelServiceImpl.hotel_ob.deluxe_doublerrom[0] = before;
        setScannerInput("11\n");

        service.bookroom(2);

        assertFalse(service.custDetailsCalled);
        assertSame(before, HotelServiceImpl.hotel_ob.deluxe_doublerrom[0]);
    }

    @Test
    @DisplayName("bookroom path 7: case 3 success with available first room")
    void bookroomPath7_case3_successSkipLoop() {
        TestableBookroomService service = new TestableBookroomService();
        setScannerInput("31\n");

        service.bookroom(3);

        assertTrue(service.custDetailsCalled);
        assertEquals(3, service.custType);
        assertEquals(0, service.custRn);
        assertNotNull(HotelServiceImpl.hotel_ob.luxury_singleerrom[0]);
    }

    @Test
    @DisplayName("bookroom path 8: case 3 success with one occupied room in loop")
    void bookroomPath8_case3_successWithLoopIteration() {
        TestableBookroomService service = new TestableBookroomService();
        HotelServiceImpl.hotel_ob.luxury_singleerrom[0] = occupiedSingle();
        setScannerInput("32\n");

        service.bookroom(3);

        assertTrue(service.custDetailsCalled);
        assertEquals(3, service.custType);
        assertEquals(1, service.custRn);
        assertNotNull(HotelServiceImpl.hotel_ob.luxury_singleerrom[1]);
    }

    @Test
    @DisplayName("bookroom path 9: case 3 fail when selected room already occupied")
    void bookroomPath9_case3_failRoomNotAvailable() {
        TestableBookroomService service = new TestableBookroomService();
        Singleroom before = occupiedSingle();
        HotelServiceImpl.hotel_ob.luxury_singleerrom[0] = before;
        setScannerInput("31\n");

        service.bookroom(3);

        assertFalse(service.custDetailsCalled);
        assertSame(before, HotelServiceImpl.hotel_ob.luxury_singleerrom[0]);
    }

    @Test
    @DisplayName("bookroom path 10: case 4 success with available first room")
    void bookroomPath10_case4_successSkipLoop() {
        TestableBookroomService service = new TestableBookroomService();
        setScannerInput("41\n");

        service.bookroom(4);

        assertTrue(service.custDetailsCalled);
        assertEquals(4, service.custType);
        assertEquals(0, service.custRn);
        assertNotNull(HotelServiceImpl.hotel_ob.deluxe_singleerrom[0]);
    }

    @Test
    @DisplayName("bookroom path 11: case 4 success with one occupied room in loop")
    void bookroomPath11_case4_successWithLoopIteration() {
        TestableBookroomService service = new TestableBookroomService();
        HotelServiceImpl.hotel_ob.deluxe_singleerrom[0] = occupiedSingle();
        setScannerInput("42\n");

        service.bookroom(4);

        assertTrue(service.custDetailsCalled);
        assertEquals(4, service.custType);
        assertEquals(1, service.custRn);
        assertNotNull(HotelServiceImpl.hotel_ob.deluxe_singleerrom[1]);
    }

    @Test
    @DisplayName("bookroom path 12: case 4 fail when selected room already occupied")
    void bookroomPath12_case4_failRoomNotAvailable() {
        TestableBookroomService service = new TestableBookroomService();
        Singleroom before = occupiedSingle();
        HotelServiceImpl.hotel_ob.deluxe_singleerrom[0] = before;
        setScannerInput("41\n");

        service.bookroom(4);

        assertFalse(service.custDetailsCalled);
        assertSame(before, HotelServiceImpl.hotel_ob.deluxe_singleerrom[0]);
    }

    @Test
    @DisplayName("bookroom path 13: default branch with invalid room type")
    void bookroomPath13_default_invalidType() {
        TestableBookroomService service = new TestableBookroomService();

        service.bookroom(99);

        assertFalse(service.custDetailsCalled);
        assertNull(HotelServiceImpl.hotel_ob.luxury_singleerrom[0]);
    }

    @Test
    @DisplayName("bookroom path 14: invalid type does not fallback into case 3 flow")
    void bookroomPath14_default_noFallbackToCase3() {
        TestableBookroomService service = new TestableBookroomService();
        Singleroom before = occupiedSingle();
        HotelServiceImpl.hotel_ob.luxury_singleerrom[0] = before;

        service.bookroom(-1);

        assertFalse(service.custDetailsCalled);
        assertSame(before, HotelServiceImpl.hotel_ob.luxury_singleerrom[0]);
    }

    private static class TestableBookroomService extends HotelServiceImpl {
        boolean custDetailsCalled;
        int custType;
        int custRn;

        @Override
        public void CustDetails(int i, int rn) {
            custDetailsCalled = true;
            custType = i;
            custRn = rn;

            if (i == 1) {
                hotel_ob.luxury_doublerrom[rn] = new Doubleroom();
            } else if (i == 2) {
                hotel_ob.deluxe_doublerrom[rn] = new Doubleroom();
            } else if (i == 3) {
                hotel_ob.luxury_singleerrom[rn] = new Singleroom();
            } else if (i == 4) {
                hotel_ob.deluxe_singleerrom[rn] = new Singleroom();
            }
        }
    }
}

