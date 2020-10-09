package com.example.latihandatabase.model;

public class Data {

    private String _id;
    private String _name;
    private String _address;

    public Data() {

    }
    public Data(String id, String name, String address) {
        this._id = id;
        this._name = name;
        this._address = address;
    }



    public String get_id() {
        return _id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String address) {
        this._address = address;
    }



}
