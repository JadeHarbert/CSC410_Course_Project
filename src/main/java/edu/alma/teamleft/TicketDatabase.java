/**
 * TicketDatabase.java  --  Class that has methods used to execute SQL queries to obtain tickets in the database
 * CSC410
 * April 18th, 2023
 */
package edu.alma.teamleft;

import edu.alma.teamleft.tables.Ticket;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class TicketDatabase {
    static final String URL = "jdbc:postgresql://localhost/eightcap";

    public static final String PASSWORD = System.getenv("PG_PASSWORD");
    public static final String USER_NAME = System.getenv("PG_USERNAME");
    public static String SelectedClientid;
    public static String SelectedSubject;
    public static Integer SelectedStatus;
    public static Integer SelectedService;
    static Connection conn;
    public static Result TicketSearchResults;


    static {
        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);

    /**
     * Does a SQL query in JOOQ to get all tickets
     * @return Result representing all tickets
     */
    public static Result<Record> SelectAllTickets(){
        TicketSearchResults = create.select()
                .from(Ticket.TICKET)
                .fetch();
        System.out.println(TicketSearchResults);
        return TicketSearchResults;
    }

    /**
     * Does a SQL query in JOOQ to get all tickets with the ID specified
     * @return Result representing query
     */
    public static Result<Record> SelectTicketWithID(){
        TicketSearchResults = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .fetch();
        System.out.println(TicketSearchResults);
        return TicketSearchResults;
    }

    /**
     * Does a SQL query in JOOQ to get all tickets with subject
     * @return Result representing query
     */
    public static Result<Record> SelectTicketWithSubject(){
        TicketSearchResults = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .fetch();
        System.out.println(TicketSearchResults);
        return TicketSearchResults;
    }

    /**
     * Does a SQL query in JOOQ to get all tickets with the speciified subject
     * @return Result representing query
     */
    public static Result<Record> SelectTicketWithStatus(){
        TicketSearchResults = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .fetch();
        System.out.println(SelectedStatus);
        System.out.println(TicketSearchResults);
        return TicketSearchResults;
    }

    /**
     * Does a SQL query in JOOQ to get all tickets with specified service
     * @return Result representing query
     */
    public static Result<Record> SelectTicketWithService(){
        TicketSearchResults = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .fetch();
        System.out.println(TicketSearchResults);
        return TicketSearchResults;
    }

    /**
     * Does a SQL query in JOOQ to get all tickets with specified id and subject
     * @return Result representing query
     */
    public static Result<Record> SelectTicketWithIDAndSubject(){
        TicketSearchResults = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .fetch();
        System.out.println(TicketSearchResults);
        return TicketSearchResults;
    }

    /**
     * Does a SQL query in JOOQ to get all tickets with specified ID and Satatus
     * @return Result representing query
     */
    public static Result<Record> SelectTicketWithIDAndStatus(){
        TicketSearchResults = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .fetch();
        System.out.println(TicketSearchResults);
        return TicketSearchResults;
    }

    /**
     * Does a SQL query in JOOQ to get all tickets with specified ID and service
     * @return Result representing query
     */
    public static Result<Record> SelectTicketWithIDAndService(){
        TicketSearchResults = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .fetch();
        System.out.println(TicketSearchResults);
        return TicketSearchResults;
    }

    /**
     * Does a SQL query in JOOQ to get all tickets with specified subject and status
     * @return result representing query
     */
    public static Result<Record> SelectTicketWithSubjectAndStatus(){
        TicketSearchResults = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .and(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .fetch();
        System.out.println(TicketSearchResults);
        return TicketSearchResults;
    }

    /**
     * Does a SQL query in JOOQ to get all tickets with specified subject and service
     * @return result representing query
     */
    public static Result<Record> SelectTicketWithSubjectAndService(){
        TicketSearchResults = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .fetch();
        System.out.println(TicketSearchResults);
        return TicketSearchResults;
    }

    /**
     * Does a SQL query in JOOQ to get all tickets with specified status and service
     * @return result representing query
     */
    public static Result<Record> SelectTicketWithStatusAndService(){
        TicketSearchResults = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .fetch();
        System.out.println(TicketSearchResults);
        return TicketSearchResults;
    }

    /**
     * Does a SQL query in JOOQ to get all tickets with specified ID, subject, and status
     * @return result representing query
     */
    public static Result<Record> SelectTicketWithIDAndSubjectAndStatus(){
        TicketSearchResults = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .and(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .fetch();
        System.out.println(TicketSearchResults);
        return TicketSearchResults;
    }

    /**
     * Does a SQL query in JOOQ to get all tickets with specified ID, Subject, and service
     * @return Result representing query
     */
    public static Result<Record> SelectTicketWithIDAndSubjectAndService(){
        TicketSearchResults = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .fetch();
        System.out.println(TicketSearchResults);
        return TicketSearchResults;
    }

    /**
     * Does a SQL query in JOOQ to get all tickets with specified ID, status, and service
     * @return Result representing query
     */
    public static Result<Record> SelectTicketWithIDAndStatusAndService(){
        TicketSearchResults = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .fetch();
        System.out.println(TicketSearchResults);
        return TicketSearchResults;
    }

    /**
     * Does a SQL query in JOOQ to get all tickets with specified Subject, Status, and service
     * @return Result representing query
     */
    public static Result<Record> SelectTicketWithSubjectAndStatusAndService(){
        TicketSearchResults = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.SUBJECT.eq(SelectedSubject))
                .and(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .fetch();
        System.out.println(TicketSearchResults);
        return TicketSearchResults;
    }

    /**
     * Does a SQL query in JOOQ to get all tickets with specified ID, Subject, status, and service
     * @return Result representing query
     */
    public static Result<Record> SelectTicketWithAll(){
        TicketSearchResults = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .and(Ticket.TICKET.SUBJECT.eq(SelectedSubject))
                .fetch();
        System.out.println(TicketSearchResults);
        return TicketSearchResults;
    }
}