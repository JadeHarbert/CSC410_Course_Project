/*
 * This file is generated by jOOQ.
 */
package edu.alma.teamleft.tables;


import edu.alma.teamleft.Keys;
import edu.alma.teamleft.Public;
import edu.alma.teamleft.tables.records.AddressTypeRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
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
public class AddressType extends TableImpl<AddressTypeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.address_type</code>
     */
    public static final AddressType ADDRESS_TYPE = new AddressType();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AddressTypeRecord> getRecordType() {
        return AddressTypeRecord.class;
    }

    /**
     * The column <code>public.address_type.address_type_id</code>.
     */
    public final TableField<AddressTypeRecord, Integer> ADDRESS_TYPE_ID = createField(DSL.name("address_type_id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.address_type.address_type</code>.
     */
    public final TableField<AddressTypeRecord, String> ADDRESS_TYPE_ = createField(DSL.name("address_type"), SQLDataType.VARCHAR(50), this, "");

    private AddressType(Name alias, Table<AddressTypeRecord> aliased) {
        this(alias, aliased, null);
    }

    private AddressType(Name alias, Table<AddressTypeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.address_type</code> table reference
     */
    public AddressType(String alias) {
        this(DSL.name(alias), ADDRESS_TYPE);
    }

    /**
     * Create an aliased <code>public.address_type</code> table reference
     */
    public AddressType(Name alias) {
        this(alias, ADDRESS_TYPE);
    }

    /**
     * Create a <code>public.address_type</code> table reference
     */
    public AddressType() {
        this(DSL.name("address_type"), null);
    }

    public <O extends Record> AddressType(Table<O> child, ForeignKey<O, AddressTypeRecord> key) {
        super(child, key, ADDRESS_TYPE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<AddressTypeRecord, Integer> getIdentity() {
        return (Identity<AddressTypeRecord, Integer>) super.getIdentity();
    }

    @Override
    public List<UniqueKey<AddressTypeRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.ADDRESS_TYPES_ID_KEY);
    }

    @Override
    public AddressType as(String alias) {
        return new AddressType(DSL.name(alias), this);
    }

    @Override
    public AddressType as(Name alias) {
        return new AddressType(alias, this);
    }

    @Override
    public AddressType as(Table<?> alias) {
        return new AddressType(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public AddressType rename(String name) {
        return new AddressType(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AddressType rename(Name name) {
        return new AddressType(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public AddressType rename(Table<?> name) {
        return new AddressType(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super Integer, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super Integer, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
