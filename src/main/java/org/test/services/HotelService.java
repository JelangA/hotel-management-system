package org.test.services;

public interface HotelService {
    void CustDetails(int i, int rn);
    void bookroom(int i);
    void features(int i);
    void availability(int i);
    void bill(int rn, int rtype);
    void deallocate(int rn, int rtype);
    void order(int rn, int rtype);
}
