/*
 * This file is generated by jOOQ.
 */
package edu.alma.teamleft.tables;


import edu.alma.teamleft.Keys;
import edu.alma.teamleft.Public;
import edu.alma.teamleft.tables.records.StaffRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function5;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row5;
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
public class Staff extends TableImpl<StaffRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.staff</code>
     */
    public static final Staff STAFF = new Staff();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StaffRecord> getRecordType() {
        return StaffRecord.class;
    }

    /**
     * The column <code>public.staff.staff_id</code>.
     */
    public final TableField<StaffRecord, Integer> STAFF_ID = createField(DSL.name("staff_id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.staff.first_name</code>.
     */
    public final TableField<StaffRecord, String> FIRST_NAME = createField(DSL.name("first_name"), SQLDataType.VARCHAR(25).nullable(false), this, "");

    /**
     * The column <code>public.staff.last_name</code>.
     */
    public final TableField<StaffRecord, String> LAST_NAME = createField(DSL.name("last_name"), SQLDataType.VARCHAR(25).nullable(false), this, "");

    /**
     * The column <code>public.staff.work_phone_number</code>.
     */
    public final TableField<StaffRecord, String> WORK_PHONE_NUMBER = createField(DSL.name("work_phone_number"), SQLDataType.CHAR(10), this, "");

    /**
     * The column <code>public.staff.service_id</code>.
     */
    public final TableField<StaffRecord, Integer> SERVICE_ID = createField(DSL.name("service_id"), SQLDataType.INTEGER, this, "");

    private Staff(Name alias, Table<StaffRecord> aliased) {
        this(alias, aliased, null);
    }

    private Staff(Name alias, Table<StaffRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.staff</code> table reference
     */
    public Staff(String alias) {
        this(DSL.name(alias), STAFF);
    }

    /**
     * Create an aliased <code>public.staff</code> table reference
     */
    public Staff(Name alias) {
        this(alias, STAFF);
    }

    /**
     * Create a <code>public.staff</code> table reference
     */
    public Staff() {
        this(DSL.name("staff"), null);
    }

    public <O extends Record> Staff(Table<O> child, ForeignKey<O, StaffRecord> key) {
        super(child, key, STAFF);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<StaffRecord, Integer> getIdentity() {
        return (Identity<StaffRecord, Integer>) super.getIdentity();
    }

    @Override
    public List<UniqueKey<StaffRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.STAFF_STAFF_ID_KEY);
    }

    @Override
    public List<ForeignKey<StaffRecord, ?>> getReferences() {
        return Arrays.asList(Keys.STAFF__STAFF_SERVICE_ID_FKEY);
    }

    private transient Service _service;

    /**
     * Get the implicit join path to the <code>public.service</code> table.
     */
    public Service service() {
        if (_service == null)
            _service = new Service(this, Keys.STAFF__STAFF_SERVICE_ID_FKEY);

        return _service;
    }

    @Override
    public Staff as(String alias) {
        return new Staff(DSL.name(alias), this);
    }

    @Override
    public Staff as(Name alias) {
        return new Staff(alias, this);
    }

    @Override
    public Staff as(Table<?> alias) {
        return new Staff(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Staff rename(String name) {
        return new Staff(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Staff rename(Name name) {
        return new Staff(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Staff rename(Table<?> name) {
        return new Staff(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, String, String, String, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function5<? super Integer, ? super String, ? super String, ? super String, ? super Integer, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function5<? super Integer, ? super String, ? super String, ? super String, ? super Integer, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}