package com.example.davidclifford.beermaps;

/**
 * Created by davidclifford on 4/21/15.
 */
public class Brewery {

    private int _id;
    private String _Brewery;
    private String _xLocation;
    private String _yLocation;
    private String _flagshipBeer;

    public Brewery() {

    }

    public Brewery(int id, String Brewery, String xLocation, String yLocation, String flagshipBeer) {
        this._id = id;
        this._Brewery = Brewery;
        this._xLocation = xLocation;
        this._yLocation = yLocation;
        this._flagshipBeer = flagshipBeer;
    }

    public Brewery(String Brewery, String xLocation, String yLocation, String flagshipBeer) {
        this._Brewery = Brewery;
        this._xLocation = xLocation;
        this._yLocation = yLocation;
        this._flagshipBeer = flagshipBeer;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_Brewery() {
        return _Brewery;
    }

    public void set_Brewery(String _Brewery) {
        this._Brewery = _Brewery;
    }

    public String get_xLocation() {
        return _xLocation;
    }

    public void set_xLocation(String _xLocation) {
        this._xLocation = _xLocation;
    }

    public String get_yLocation() {
        return _yLocation;
    }

    public void set_yLocation(String _yLocation) {
        this._yLocation = _yLocation;
    }

    public String get_flagshipBeer() {
        return _flagshipBeer;
    }

    public void set_flagshipBeer(String _flagshipBeer) {
        this._flagshipBeer = _flagshipBeer;
    }
}
