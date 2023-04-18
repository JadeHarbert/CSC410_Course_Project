/*
 * This file is generated by jOOQ.
 */
package edu.alma.teamleft.tables;


import edu.alma.teamleft.Keys;
import edu.alma.teamleft.Public;
import edu.alma.teamleft.tables.records.PhoneNumberRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PhoneNumber extends TableImpl<PhoneNumberRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.phone_number</code>
     */
    public static final PhoneNumber PHONE_NUMBER = new PhoneNumber();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PhoneNumberRecord> getRecordType() {
        return PhoneNumberRecord.class;
    }

    /**
     * The column <code>public.phone_number.phone_number_id</code>.
     */
    public final TableField<PhoneNumberRecord, Integer> PHONE_NUMBER_ID = createField(DSL.name("phone_number_id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.phone_number.phone_number</code>.
     */
    public final TableField<PhoneNumberRecord, String> PHONE_NUMBER_ = createField(DSL.name("phone_number"), SQLDataType.CHAR(10), this, "");

    /**
     * The column <code>public.phone_number.number_type_id</code>.
     */
    public final TableField<PhoneNumberRecord, Integer> NUMBER_TYPE_ID = createField(DSL.name("number_type_id"), SQLDataType.INTEGER, this, "");

    private PhoneNumber(Name alias, Table<PhoneNumberRecord> aliased) {
        this(alias, aliased, null);
    }

    private PhoneNumber(Name alias, Table<PhoneNumberRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.phone_number</code> table reference
     */
    public PhoneNumber(String alias) {
        this(DSL.name(alias), PHONE_NUMBER);
    }

    /**
     * Create an aliased <code>public.phone_number</code> table reference
     */
    public PhoneNumber(Name alias) {
        this(alias, PHONE_NUMBER);
    }

    /**
     * Create a <code>public.phone_number</code> table reference
     */
    public PhoneNumber() {
        this(DSL.name("phone_number"), null);
    }

    public <O extends Record> PhoneNumber(Table<O> child, ForeignKey<O, PhoneNumberRecord> key) {
        super(child, key, PHONE_NUMBER);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<PhoneNumberRecord, Integer> getIdentity() {
        return (Identity<PhoneNumberRecord, Integer>) super.getIdentity();
    }

    @Override
    public List<UniqueKey<PhoneNumberRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.PHONE_NUMBERS_PHONE_NUMBER_ID_KEY);
    }

    @Override
    public List<ForeignKey<PhoneNumberRecord, ?>> getReferences() {
        return Arrays.asList(Keys.PHONE_NUMBER__PHONE_NUMBERS_NUMBER_TYPE_ID_FKEY);
    }

    private transient PhoneNumberType _phoneNumberType;

    /**
     * Get the implicit join path to the <code>public.phone_number_type</code>
     * table.
     */
    public PhoneNumberType phoneNumberType() {
        if (_phoneNumberType == null)
            _phoneNumberType = new PhoneNumberType(this, Keys.PHONE_NUMBER__PHONE_NUMBERS_NUMBER_TYPE_ID_FKEY);

        return _phoneNumberType;
    }

    @Override
    public PhoneNumber as(String alias) {
        return new PhoneNumber(DSL.name(alias), this);
    }

    @Override
    public PhoneNumber as(Name alias) {
        return new PhoneNumber(alias, this);
    }

    @Override
    public PhoneNumber as(Table<?> alias) {
        return new PhoneNumber(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public PhoneNumber rename(String name) {
        return new PhoneNumber(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PhoneNumber rename(Name name) {
        return new PhoneNumber(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public PhoneNumber rename(Table<?> name) {
        return new PhoneNumber(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super Integer, ? super String, ? super Integer, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super Integer, ? super String, ? super Integer, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}