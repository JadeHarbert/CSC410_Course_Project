/*
 * This file is generated by jOOQ.
 */
package edu.alma.teamleft.tables.records;


import edu.alma.teamleft.tables.PreferredContact;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PreferredContactRecord extends TableRecordImpl<PreferredContactRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.preferred_contact.preferred_contact_id</code>.
     */
    public void setPreferredContactId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.preferred_contact.preferred_contact_id</code>.
     */
    public Integer getPreferredContactId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.preferred_contact.contact_method</code>.
     */
    public void setContactMethod(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.preferred_contact.contact_method</code>.
     */
    public String getContactMethod() {
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
        return PreferredContact.PREFERRED_CONTACT.PREFERRED_CONTACT_ID;
    }

    @Override
    public Field<String> field2() {
        return PreferredContact.PREFERRED_CONTACT.CONTACT_METHOD;
    }

    @Override
    public Integer component1() {
        return getPreferredContactId();
    }

    @Override
    public String component2() {
        return getContactMethod();
    }

    @Override
    public Integer value1() {
        return getPreferredContactId();
    }

    @Override
    public String value2() {
        return getContactMethod();
    }

    @Override
    public PreferredContactRecord value1(Integer value) {
        setPreferredContactId(value);
        return this;
    }

    @Override
    public PreferredContactRecord value2(String value) {
        setContactMethod(value);
        return this;
    }

    @Override
    public PreferredContactRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PreferredContactRecord
     */
    public PreferredContactRecord() {
        super(PreferredContact.PREFERRED_CONTACT);
    }

    /**
     * Create a detached, initialised PreferredContactRecord
     */
    public PreferredContactRecord(Integer preferredContactId, String contactMethod) {
        super(PreferredContact.PREFERRED_CONTACT);

        setPreferredContactId(preferredContactId);
        setContactMethod(contactMethod);
    }
}
