/*
 * This file is generated by jOOQ.
 */
package edu.alma.teamleft.tables.records;


import edu.alma.teamleft.tables.Service;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ServiceRecord extends TableRecordImpl<ServiceRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.service.service_id</code>.
     */
    public void setServiceId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.service.service_id</code>.
     */
    public Integer getServiceId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.service.service_name</code>.
     */
    public void setServiceName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.service.service_name</code>.
     */
    public String getServiceName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Service.SERVICE.SERVICE_ID;
    }

    @Override
    public Field<String> field2() {
        return Service.SERVICE.SERVICE_NAME;
    }

    @Override
    public Integer component1() {
        return getServiceId();
    }

    @Override
    public String component2() {
        return getServiceName();
    }

    @Override
    public Integer value1() {
        return getServiceId();
    }

    @Override
    public String value2() {
        return getServiceName();
    }

    @Override
    public ServiceRecord value1(Integer value) {
        setServiceId(value);
        return this;
    }

    @Override
    public ServiceRecord value2(String value) {
        setServiceName(value);
        return this;
    }

    @Override
    public ServiceRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ServiceRecord
     */
    public ServiceRecord() {
        super(Service.SERVICE);
    }

    /**
     * Create a detached, initialised ServiceRecord
     */
    public ServiceRecord(Integer serviceId, String serviceName) {
        super(Service.SERVICE);

        setServiceId(serviceId);
        setServiceName(serviceName);
    }
}
