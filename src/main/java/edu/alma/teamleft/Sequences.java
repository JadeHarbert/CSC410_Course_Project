/*
 * This file is generated by jOOQ.
 */
package edu.alma.teamleft;


import org.jooq.Sequence;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;


/**
 * Convenience access to all sequences in public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>public.address_types_id_seq</code>
     */
    public static final Sequence<Integer> ADDRESS_TYPES_ID_SEQ = Internal.createSequence("address_types_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.addresses_address_id_seq</code>
     */
    public static final Sequence<Integer> ADDRESSES_ADDRESS_ID_SEQ = Internal.createSequence("addresses_address_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.client_id_seq</code>
     */
    public static final Sequence<Integer> CLIENT_ID_SEQ = Internal.createSequence("client_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.county_id_seq</code>
     */
    public static final Sequence<Integer> COUNTY_ID_SEQ = Internal.createSequence("county_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.phone_number_types_id_seq</code>
     */
    public static final Sequence<Integer> PHONE_NUMBER_TYPES_ID_SEQ = Internal.createSequence("phone_number_types_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.phone_numbers_phone_number_id_seq</code>
     */
    public static final Sequence<Integer> PHONE_NUMBERS_PHONE_NUMBER_ID_SEQ = Internal.createSequence("phone_numbers_phone_number_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.preferred_contact_id_seq</code>
     */
    public static final Sequence<Integer> PREFERRED_CONTACT_ID_SEQ = Internal.createSequence("preferred_contact_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.services_service_id_seq</code>
     */
    public static final Sequence<Integer> SERVICES_SERVICE_ID_SEQ = Internal.createSequence("services_service_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.statuses_status_id_seq</code>
     */
    public static final Sequence<Integer> STATUSES_STATUS_ID_SEQ = Internal.createSequence("statuses_status_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);
}
