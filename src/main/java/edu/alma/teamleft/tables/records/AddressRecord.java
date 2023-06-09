/*
 * This file is generated by jOOQ.
 */
package edu.alma.teamleft.tables.records;


import edu.alma.teamleft.tables.Address;

import org.jooq.Field;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AddressRecord extends TableRecordImpl<AddressRecord> implements Record7<Integer, String, String, String, String, String, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.address.address_id</code>.
     */
    public void setAddressId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.address.address_id</code>.
     */
    public Integer getAddressId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.address.address_line_1</code>.
     */
    public void setAddressLine_1(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.address.address_line_1</code>.
     */
    public String getAddressLine_1() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.address.address_line_2</code>.
     */
    public void setAddressLine_2(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.address.address_line_2</code>.
     */
    public String getAddressLine_2() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.address.city</code>.
     */
    public void setCity(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.address.city</code>.
     */
    public String getCity() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.address.state</code>.
     */
    public void setState(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.address.state</code>.
     */
    public String getState() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.address.zip</code>.
     */
    public void setZip(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.address.zip</code>.
     */
    public String getZip() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.address.address_type_id</code>.
     */
    public void setAddressTypeId(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.address.address_type_id</code>.
     */
    public Integer getAddressTypeId() {
        return (Integer) get(6);
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, String, String, String, String, String, Integer> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Integer, String, String, String, String, String, Integer> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Address.ADDRESS.ADDRESS_ID;
    }

    @Override
    public Field<String> field2() {
        return Address.ADDRESS.ADDRESS_LINE_1;
    }

    @Override
    public Field<String> field3() {
        return Address.ADDRESS.ADDRESS_LINE_2;
    }

    @Override
    public Field<String> field4() {
        return Address.ADDRESS.CITY;
    }

    @Override
    public Field<String> field5() {
        return Address.ADDRESS.STATE;
    }

    @Override
    public Field<String> field6() {
        return Address.ADDRESS.ZIP;
    }

    @Override
    public Field<Integer> field7() {
        return Address.ADDRESS.ADDRESS_TYPE_ID;
    }

    @Override
    public Integer component1() {
        return getAddressId();
    }

    @Override
    public String component2() {
        return getAddressLine_1();
    }

    @Override
    public String component3() {
        return getAddressLine_2();
    }

    @Override
    public String component4() {
        return getCity();
    }

    @Override
    public String component5() {
        return getState();
    }

    @Override
    public String component6() {
        return getZip();
    }

    @Override
    public Integer component7() {
        return getAddressTypeId();
    }

    @Override
    public Integer value1() {
        return getAddressId();
    }

    @Override
    public String value2() {
        return getAddressLine_1();
    }

    @Override
    public String value3() {
        return getAddressLine_2();
    }

    @Override
    public String value4() {
        return getCity();
    }

    @Override
    public String value5() {
        return getState();
    }

    @Override
    public String value6() {
        return getZip();
    }

    @Override
    public Integer value7() {
        return getAddressTypeId();
    }

    @Override
    public AddressRecord value1(Integer value) {
        setAddressId(value);
        return this;
    }

    @Override
    public AddressRecord value2(String value) {
        setAddressLine_1(value);
        return this;
    }

    @Override
    public AddressRecord value3(String value) {
        setAddressLine_2(value);
        return this;
    }

    @Override
    public AddressRecord value4(String value) {
        setCity(value);
        return this;
    }

    @Override
    public AddressRecord value5(String value) {
        setState(value);
        return this;
    }

    @Override
    public AddressRecord value6(String value) {
        setZip(value);
        return this;
    }

    @Override
    public AddressRecord value7(Integer value) {
        setAddressTypeId(value);
        return this;
    }

    @Override
    public AddressRecord values(Integer value1, String value2, String value3, String value4, String value5, String value6, Integer value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AddressRecord
     */
    public AddressRecord() {
        super(Address.ADDRESS);
    }

    /**
     * Create a detached, initialised AddressRecord
     */
    public AddressRecord(Integer addressId, String addressLine_1, String addressLine_2, String city, String state, String zip, Integer addressTypeId) {
        super(Address.ADDRESS);

        setAddressId(addressId);
        setAddressLine_1(addressLine_1);
        setAddressLine_2(addressLine_2);
        setCity(city);
        setState(state);
        setZip(zip);
        setAddressTypeId(addressTypeId);
    }
}
