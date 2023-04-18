/*
 * This file is generated by jOOQ.
 */
package edu.alma.teamleft.tables;


import edu.alma.teamleft.Keys;
import edu.alma.teamleft.Public;
import edu.alma.teamleft.tables.records.AddressRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function7;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row7;
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
public class Address extends TableImpl<AddressRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.address</code>
     */
    public static final Address ADDRESS = new Address();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AddressRecord> getRecordType() {
        return AddressRecord.class;
    }

    /**
     * The column <code>public.address.address_id</code>.
     */
    public final TableField<AddressRecord, Integer> ADDRESS_ID = createField(DSL.name("address_id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.address.address_line_1</code>.
     */
    public final TableField<AddressRecord, String> ADDRESS_LINE_1 = createField(DSL.name("address_line_1"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.address.address_line_2</code>.
     */
    public final TableField<AddressRecord, String> ADDRESS_LINE_2 = createField(DSL.name("address_line_2"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.address.city</code>.
     */
    public final TableField<AddressRecord, String> CITY = createField(DSL.name("city"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.address.state</code>.
     */
    public final TableField<AddressRecord, String> STATE = createField(DSL.name("state"), SQLDataType.CHAR(2), this, "");

    /**
     * The column <code>public.address.zip</code>.
     */
    public final TableField<AddressRecord, String> ZIP = createField(DSL.name("zip"), SQLDataType.CHAR(5), this, "");

    /**
     * The column <code>public.address.address_type_id</code>.
     */
    public final TableField<AddressRecord, Integer> ADDRESS_TYPE_ID = createField(DSL.name("address_type_id"), SQLDataType.INTEGER, this, "");

    private Address(Name alias, Table<AddressRecord> aliased) {
        this(alias, aliased, null);
    }

    private Address(Name alias, Table<AddressRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.address</code> table reference
     */
    public Address(String alias) {
        this(DSL.name(alias), ADDRESS);
    }

    /**
     * Create an aliased <code>public.address</code> table reference
     */
    public Address(Name alias) {
        this(alias, ADDRESS);
    }

    /**
     * Create a <code>public.address</code> table reference
     */
    public Address() {
        this(DSL.name("address"), null);
    }

    public <O extends Record> Address(Table<O> child, ForeignKey<O, AddressRecord> key) {
        super(child, key, ADDRESS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<AddressRecord, Integer> getIdentity() {
        return (Identity<AddressRecord, Integer>) super.getIdentity();
    }

    @Override
    public List<UniqueKey<AddressRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.ADDRESSES_ADDRESS_ID_KEY);
    }

    @Override
    public List<ForeignKey<AddressRecord, ?>> getReferences() {
        return Arrays.asList(Keys.ADDRESS__ADDRESSES_ADDRESS_TYPE_ID_FKEY);
    }

    private transient AddressType _addressType;

    /**
     * Get the implicit join path to the <code>public.address_type</code> table.
     */
    public AddressType addressType() {
        if (_addressType == null)
            _addressType = new AddressType(this, Keys.ADDRESS__ADDRESSES_ADDRESS_TYPE_ID_FKEY);

        return _addressType;
    }

    @Override
    public Address as(String alias) {
        return new Address(DSL.name(alias), this);
    }

    @Override
    public Address as(Name alias) {
        return new Address(alias, this);
    }

    @Override
    public Address as(Table<?> alias) {
        return new Address(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Address rename(String name) {
        return new Address(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Address rename(Name name) {
        return new Address(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Address rename(Table<?> name) {
        return new Address(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, String, String, String, String, String, Integer> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function7<? super Integer, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Integer, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function7<? super Integer, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Integer, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}