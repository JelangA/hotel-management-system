package org.test.services;

public interface HotelService {
    void CustDetails(int i, int rn); //Fadilah
    void bookroom(int i); //Luthfi
    void features(int i); //Fadilah
    void availability(int i);
    void bill(int rn, int rtype);
    void deallocate(int rn, int rtype); //Luthfi
    void order(int rn, int rtype); //Jelang
}
