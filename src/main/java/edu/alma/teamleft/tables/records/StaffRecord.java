/*
 * This file is generated by jOOQ.
 */
package edu.alma.teamleft.tables.records;


import edu.alma.teamleft.tables.Staff;

import org.jooq.Field;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class StaffRecord extends TableRecordImpl<StaffRecord> implements Record5<Integer, String, String, String, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.staff.staff_id</code>.
     */
    public void setStaffId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.staff.staff_id</code>.
     */
    public Integer getStaffId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.staff.first_name</code>.
     */
    public void setFirstName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.staff.first_name</code>.
     */
    public String getFirstName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.staff.last_name</code>.
     */
    public void setLastName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.staff.last_name</code>.
     */
    public String getLastName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.staff.work_phone_number</code>.
     */
    public void setWorkPhoneNumber(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.staff.work_phone_number</code>.
     */
    public String getWorkPhoneNumber() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.staff.service_id</code>.
     */
    public void setServiceId(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.staff.service_id</code>.
     */
    public Integer getServiceId() {
        return (Integer) get(4);
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, String, String, String, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Integer, String, String, String, Integer> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Staff.STAFF.STAFF_ID;
    }

    @Override
    public Field<String> field2() {
        return Staff.STAFF.FIRST_NAME;
    }

    @Override
    public Field<String> field3() {
        return Staff.STAFF.LAST_NAME;
    }

    @Override
    public Field<String> field4() {
        return Staff.STAFF.WORK_PHONE_NUMBER;
    }

    @Override
    public Field<Integer> field5() {
        return Staff.STAFF.SERVICE_ID;
    }

    @Override
    public Integer component1() {
        return getStaffId();
    }

    @Override
    public String component2() {
        return getFirstName();
    }

    @Override
    public String component3() {
        return getLastName();
    }

    @Override
    public String component4() {
        return getWorkPhoneNumber();
    }

    @Override
    public Integer component5() {
        return getServiceId();
    }

    @Override
    public Integer value1() {
        return getStaffId();
    }

    @Override
    public String value2() {
        return getFirstName();
    }

    @Override
    public String value3() {
        return getLastName();
    }

    @Override
    public String value4() {
        return getWorkPhoneNumber();
    }

    @Override
    public Integer value5() {
        return getServiceId();
    }

    @Override
    public StaffRecord value1(Integer value) {
        setStaffId(value);
        return this;
    }

    @Override
    public StaffRecord value2(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public StaffRecord value3(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public StaffRecord value4(String value) {
        setWorkPhoneNumber(value);
        return this;
    }

    @Override
    public StaffRecord value5(Integer value) {
        setServiceId(value);
        return this;
    }

    @Override
    public StaffRecord values(Integer value1, String value2, String value3, String value4, Integer value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached StaffRecord
     */
    public StaffRecord() {
        super(Staff.STAFF);
    }

    /**
     * Create a detached, initialised StaffRecord
     */
    public StaffRecord(Integer staffId, String firstName, String lastName, String workPhoneNumber, Integer serviceId) {
        super(Staff.STAFF);

        setStaffId(staffId);
        setFirstName(firstName);
        setLastName(lastName);
        setWorkPhoneNumber(workPhoneNumber);
        setServiceId(serviceId);
    }
}
