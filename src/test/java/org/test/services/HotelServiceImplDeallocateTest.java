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

class HotelServiceImplDeallocateTest {

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

    // -------- deallocate() --------

    @Test
    @DisplayName("deallocate path 1: rtype=1 and room empty")
    void deallocatePath1_case1_empty() {
        TestableHotelServiceImpl service = new TestableHotelServiceImpl();

        service.deallocate(0, 1);

        assertNull(HotelServiceImpl.hotel_ob.luxury_doublerrom[0]);
        assertFalse(service.billCalled);
    }

    @Test
    @DisplayName("deallocate path 2: rtype=1 occupied and cancel checkout")
    void deallocatePath2_case1_occupiedCancel() {
        TestableHotelServiceImpl service = new TestableHotelServiceImpl();
        Doubleroom before = occupiedDouble();
        HotelServiceImpl.hotel_ob.luxury_doublerrom[0] = before;
        setScannerInput("n\n");

        service.deallocate(0, 1);

        assertSame(before, HotelServiceImpl.hotel_ob.luxury_doublerrom[0]);
        assertFalse(service.billCalled);
    }

    @Test
    @DisplayName("deallocate path 3: rtype=1 occupied and confirm checkout")
    void deallocatePath3_case1_occupiedCheckout() {
        TestableHotelServiceImpl service = new TestableHotelServiceImpl();
        HotelServiceImpl.hotel_ob.luxury_doublerrom[0] = occupiedDouble();
        setScannerInput("y\n");

        service.deallocate(0, 1);

        assertNull(HotelServiceImpl.hotel_ob.luxury_doublerrom[0]);
        assertTrue(service.billCalled);
        assertEquals(0, service.billRn);
        assertEquals(1, service.billRtype);
    }

    @Test
    @DisplayName("deallocate path 4: rtype=2 and room empty")
    void deallocatePath4_case2_empty() {
        TestableHotelServiceImpl service = new TestableHotelServiceImpl();

        service.deallocate(0, 2);

        assertNull(HotelServiceImpl.hotel_ob.deluxe_doublerrom[0]);
        assertFalse(service.billCalled);
    }

    @Test
    @DisplayName("deallocate path 5: rtype=2 occupied and cancel checkout")
    void deallocatePath5_case2_occupiedCancel() {
        TestableHotelServiceImpl service = new TestableHotelServiceImpl();
        Doubleroom before = occupiedDouble();
        HotelServiceImpl.hotel_ob.deluxe_doublerrom[0] = before;
        setScannerInput("n\n");

        service.deallocate(0, 2);

        assertSame(before, HotelServiceImpl.hotel_ob.deluxe_doublerrom[0]);
        assertFalse(service.billCalled);
    }

    @Test
    @DisplayName("deallocate path 6: rtype=2 occupied and confirm checkout")
    void deallocatePath6_case2_occupiedCheckout() {
        TestableHotelServiceImpl service = new TestableHotelServiceImpl();
        HotelServiceImpl.hotel_ob.deluxe_doublerrom[0] = occupiedDouble();
        setScannerInput("Y\n");

        service.deallocate(0, 2);

        assertNull(HotelServiceImpl.hotel_ob.deluxe_doublerrom[0]);
        assertTrue(service.billCalled);
        assertEquals(0, service.billRn);
        assertEquals(2, service.billRtype);
    }

    @Test
    @DisplayName("deallocate path 7: rtype=3 and room empty")
    void deallocatePath7_case3_empty() {
        TestableHotelServiceImpl service = new TestableHotelServiceImpl();

        service.deallocate(0, 3);

        assertNull(HotelServiceImpl.hotel_ob.luxury_singleerrom[0]);
        assertFalse(service.billCalled);
    }

    @Test
    @DisplayName("deallocate path 8: rtype=3 occupied and cancel checkout")
    void deallocatePath8_case3_occupiedCancel() {
        TestableHotelServiceImpl service = new TestableHotelServiceImpl();
        Singleroom before = occupiedSingle();
        HotelServiceImpl.hotel_ob.luxury_singleerrom[0] = before;
        setScannerInput("n\n");

        service.deallocate(0, 3);

        assertSame(before, HotelServiceImpl.hotel_ob.luxury_singleerrom[0]);
        assertFalse(service.billCalled);
    }

    @Test
    @DisplayName("deallocate path 9: rtype=3 occupied and confirm checkout")
    void deallocatePath9_case3_occupiedCheckout() {
        TestableHotelServiceImpl service = new TestableHotelServiceImpl();
        HotelServiceImpl.hotel_ob.luxury_singleerrom[0] = occupiedSingle();
        setScannerInput("y\n");

        service.deallocate(0, 3);

        assertNull(HotelServiceImpl.hotel_ob.luxury_singleerrom[0]);
        assertTrue(service.billCalled);
        assertEquals(0, service.billRn);
        assertEquals(3, service.billRtype);
    }

    @Test
    @DisplayName("deallocate path 10: rtype=4 and room empty")
    void deallocatePath10_case4_empty() {
        TestableHotelServiceImpl service = new TestableHotelServiceImpl();

        service.deallocate(0, 4);

        assertNull(HotelServiceImpl.hotel_ob.deluxe_singleerrom[0]);
        assertFalse(service.billCalled);
    }

    @Test
    @DisplayName("deallocate path 11: rtype=4 occupied and cancel checkout")
    void deallocatePath11_case4_occupiedCancel() {
        TestableHotelServiceImpl service = new TestableHotelServiceImpl();
        Singleroom before = occupiedSingle();
        HotelServiceImpl.hotel_ob.deluxe_singleerrom[0] = before;
        setScannerInput("x\n");

        service.deallocate(0, 4);

        assertSame(before, HotelServiceImpl.hotel_ob.deluxe_singleerrom[0]);
        assertFalse(service.billCalled);
    }

    @Test
    @DisplayName("deallocate path 12: rtype=4 occupied and confirm checkout")
    void deallocatePath12_case4_occupiedCheckout() {
        TestableHotelServiceImpl service = new TestableHotelServiceImpl();
        HotelServiceImpl.hotel_ob.deluxe_singleerrom[0] = occupiedSingle();
        setScannerInput("y\n");

        service.deallocate(0, 4);

        assertNull(HotelServiceImpl.hotel_ob.deluxe_singleerrom[0]);
        assertTrue(service.billCalled);
        assertEquals(0, service.billRn);
        assertEquals(4, service.billRtype);
    }

    @Test
    @DisplayName("deallocate path 13: default branch for invalid rtype")
    void deallocatePath13_defaultInvalidRtype() {
        TestableHotelServiceImpl service = new TestableHotelServiceImpl();
        Doubleroom d = occupiedDouble();
        HotelServiceImpl.hotel_ob.luxury_doublerrom[0] = d;

        assertDoesNotThrow(() -> service.deallocate(0, 99));

        assertSame(d, HotelServiceImpl.hotel_ob.luxury_doublerrom[0]);
        assertFalse(service.billCalled);
    }

    private static class TestableHotelServiceImpl extends HotelServiceImpl {
        boolean billCalled;
        int billRn;
        int billRtype;

        @Override
        public void bill(int rn, int rtype) {
            billCalled = true;
            billRn = rn;
            billRtype = rtype;
        }
    }
}

