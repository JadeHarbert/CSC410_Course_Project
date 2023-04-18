/**
 * EightCAPController.java  --  Controller for the GUI that is responsible for making the GUI operate
 * CSC410
 * April 18th, 2023
 */
package edu.alma.teamleft;

import edu.alma.teamleft.tables.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.jooq.Record;
import org.jooq.Result;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

import static edu.alma.teamleft.Tables.*;
import static edu.alma.teamleft.TicketDatabase.*;
import static java.lang.Integer.parseInt;

public class EightCAPController {

    public TextField dbTicketSubject, dbTicketClientID, ticketSubject, ticketClient, followUpTicketID,
            dbClientID, dbClientFirstName,  dbCLientLastName,  dbClientDBA, dbClientHMIS, dbClientEmail,
            dbClientPhoneNumber, clientPhoneNumber, clientEmail, clientFirstName, clientLastName, clientDBA,
            clientHMIS, clientAddressLine1, clientAddressLine2, clientCity, clientState, dbFollowUpTicketID;
    public ChoiceBox<String> dbTicketStatus, dbTicketService, ticketService, dbClientCounty, dbClientPCM, clientCounty, clientPCM, clientAddressType, statusupdate;
    public TextArea ticketDetails, followUpOutcome, clientNotes;
    public String NewTicketSubject,  NewTicketDetails, NewFollowupOutcome, CurrentClientFirstName, CurrentClientLastName,
    CurrentClientEmail;
    public Integer NewTicketClient, NewTicketService, NewFollowUpTicket, CurrentClientID, CurrentClientCounty,
            CurrentClientPCM, CurrentClientDBA, CurrentClientHMIS, NewFollowupStatus;
    public LocalDateTime NewTicketCallDate;
    public DatePicker ticketCallDate, dbClientDOB, clientDOB;
    public LocalDate CurrentClientDOB;
    Result<Record> statuses = create.select()
            .from(Status.STATUS)
            .fetch();
    Result<Record> services = create.select()
            .from(Service.SERVICE)
            .fetch();
    Result<Record> counties = create.select()
            .from(County.COUNTY)
            .fetch();
    Result<Record> contactmethods = create.select()
            .from(PreferredContact.PREFERRED_CONTACT)
            .fetch();
    ArrayList<String> addressTypes = new ArrayList<>();

    /**
     * Initialize choice box with the items that are needed in it
     */
    public void initialize() {
        for (Record r : statuses) {
            dbTicketStatus.getItems().add((r.getValue(Status.STATUS.STATUS_NAME)));
            statusupdate.getItems().add((r.getValue(Status.STATUS.STATUS_NAME)));
        }
        for (Record r : services) {
            dbTicketService.getItems().add((r.getValue(Service.SERVICE.SERVICE_NAME)));
            ticketService.getItems().add((r.getValue(Service.SERVICE.SERVICE_NAME)));

        }
        for (Record r: counties) {
            dbClientCounty.getItems().add((r.getValue(County.COUNTY.COUNTY_NAME)));
        }
        for (Record r: contactmethods) {
            dbClientPCM.getItems().add((r.getValue(PreferredContact.PREFERRED_CONTACT.CONTACT_METHOD)));
        }
        Result<Record> result = create.select()
                .from(COUNTY)
                .fetch();
        for(Record r : result){
            clientCounty.getItems().add(r.getValue(COUNTY.COUNTY_NAME));
        }
        result = create.select()
                .from(PREFERRED_CONTACT)
                .fetch();
        for(Record r: result){
            clientPCM.getItems().add(r.getValue(PREFERRED_CONTACT.CONTACT_METHOD));
        }
        result = create.select()
                .from(ADDRESS_TYPE)
                .fetch();
        for(Record r: result){
            String s = r.getValue(ADDRESS_TYPE.ADDRESS_TYPE_);
            clientAddressType.getItems().add(s);
            addressTypes.add(s);
        }
    }

    /**
     * Adds a phone number to the PHONE_NUMBER table when button is clicked based on user input
     */
    public void AddPhoneNumber() {
        String number = clientPhoneNumber.getText();
        create.insertInto(PHONE_NUMBER, PHONE_NUMBER.PHONE_NUMBER_)
                .values(number)
                .execute();

        Result<Record> result = create.select()
                .from(PHONE_NUMBER)
                .fetch();
        for(Record r: result) {
            System.out.print(r.getValue(PHONE_NUMBER.PHONE_NUMBER_ID));
            System.out.print(" " + r.getValue(PHONE_NUMBER.PHONE_NUMBER_));
            System.out.println(" " + r.getValue(PHONE_NUMBER.NUMBER_TYPE_ID));
        }
    }

    /**
     * Add an address into the ADDRESS table when button is clicked based on user input
     */
    public void AddAddress() {
        String line1 = clientAddressLine1.getText();
        String line2 = clientAddressLine2.getText();
        String city = clientCity.getText();
        String state = clientState.getText();
        String addressType = clientAddressType.getValue();
        int addressTypeNum = 0;
        int i = 1;

        // Get index of address type
        for (String s: clientAddressType.getItems()){
            if (s.equals(addressType)){
                addressTypeNum = i;
            }
            else{
                i++;
            }
        }

        create.insertInto(ADDRESS,
                        ADDRESS.ADDRESS_LINE_1, ADDRESS.ADDRESS_LINE_2, ADDRESS.CITY, ADDRESS.STATE, ADDRESS.ADDRESS_TYPE_ID)
                .values(line1, line2, city, state, addressTypeNum)
                .execute();
        System.out.println("Address added");
    }

    /**
     * Add a client into the CLIENT table when button is clicked based on user input
     */
    public void AddClient() {
        String first = clientFirstName.getText();
        String last = clientLastName.getText();
        LocalDate dob = clientDOB.getValue();
        String email = clientEmail.getText();
        String notes = clientNotes.getText();
        int hmis = Integer.parseInt(clientHMIS.getText());
        int dba = Integer.parseInt(clientDBA.getText());
        LocalDate now = LocalDate.now();

        create.insertInto(CLIENT,
                        CLIENT.FIRST_NAME, CLIENT.LAST_NAME, CLIENT.DATE_OF_BIRTH, CLIENT.EMAIL, CLIENT.NOTES, CLIENT.HMIS, CLIENT.DBA_ID, CLIENT.DATETIME_OF_ENTRY)
                .values(first, last, dob, email, notes, hmis, dba, now.atStartOfDay())
                .execute();
        System.out.println("Client added");
    }

    /**
     * Execute an SQL query that searches through the CLIENT table based on user input
     */
    public void ClientSearch() {
        Result ClientSearchResults;
        try {
            if (dbClientID != null) {
                CurrentClientID = parseInt(dbClientID.getText());
            }
            assert dbClientID != null;
            if (dbClientID.getText().equals("")) {
                CurrentClientID = null;
            }
        } catch (Exception e) {
            CurrentClientID = null;
        }
        if (dbClientFirstName != null) {
            CurrentClientFirstName = dbClientFirstName.getText();
        }
        assert dbClientFirstName != null;
        if (dbClientFirstName.getText().equals("")) {
            CurrentClientFirstName = null;
        }
        if (dbCLientLastName != null) {
            CurrentClientLastName = dbCLientLastName.getText();
        }
        assert dbCLientLastName != null;
        if (dbCLientLastName.getText().equals("")) {
            CurrentClientLastName = null;
        }
        if (dbClientDOB != null) {
            CurrentClientDOB = dbClientDOB.getValue();
        }
        try {
            if (dbClientCounty.getValue().equals("Montcalm")) {
                CurrentClientCounty = 1;
            } else if (dbClientCounty.getValue().equals("Gratiot")) {
                CurrentClientCounty = 2;
            } else if (dbClientCounty.getValue().equals("Ionia")) {
                CurrentClientCounty = 3;
            } else if (dbClientCounty.getValue().equals("Isabella")) {
                CurrentClientCounty = 4;
            } else {
                CurrentClientCounty = null;
            }
        } // Set County
        catch (Exception e) {
            CurrentClientCounty = null;
        }
        try {
            if (dbClientPCM.getValue().equals("phone")) {
                CurrentClientPCM = 1;
            } else if (dbClientPCM.getValue().equals("WI-FI Phone")) {
                CurrentClientPCM = 2;
            } else if (dbClientPCM.getValue().equals("email")) {
                CurrentClientPCM = 3;
            } else {
                CurrentClientPCM = null;
            }
        } // Set Preferred Contact Method
        catch (Exception e) {
            CurrentClientPCM = null;
        }
        try {
            if (dbClientDBA.getText() != null) {
                CurrentClientDBA = parseInt(dbClientDBA.getText());
            }
            if (dbClientDBA.getText().equals("")) {
                CurrentClientDBA = null;
            }
        } catch (Exception e) {
            CurrentClientDBA = null;
        }
        try {
            if (dbClientHMIS.getText() != null) {
                CurrentClientHMIS = parseInt(dbClientHMIS.getText());
            }
            if (dbClientHMIS.getText().equals("")) {
                CurrentClientHMIS = null;
            }
        } catch (Exception e) {
            CurrentClientHMIS = null;
        }
        if (dbClientEmail.getText() != null) {
            CurrentClientEmail = dbClientEmail.getText();
        }
        if (dbClientEmail.getText().equals("")) {
            CurrentClientEmail = null;
        }
        if (CurrentClientID == null) {
            if (CurrentClientFirstName == null) {
                if (CurrentClientLastName == null) {
                    if (CurrentClientDOB == null) {
                        if (CurrentClientCounty == null) {
                            if (CurrentClientPCM == null) {
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) { // All Null
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else { // Email Not Null
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else { // HMIS not null, Email Not Checked
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {// HMIS not null, Email not null
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else { // DBA not null, hmis not checked, email not checked
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {//DBA not null, hmis not null, email not checked
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            } else { //PCM not null, dba not checked, hmis not checked, email not checked
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else { // PCM not null, Email Not null
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {// PCM not null, hmis not null, email not checked
                                        if (dbClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else { // PCM not null, dba not null, hmis not checked, email not checked
                                    if (dbClientHMIS == null) {
                                        if (dbClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {//PCM not null, dba not null, email not null
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else { // PCM not null, dba not null, hmis not null, email not checked
                                        if (dbClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        } else { // County not null, PCM not checked, DBA not checked, HMIS not checked, Email not checked
                            if (CurrentClientPCM == null) {
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                        } else {//county, email not null
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else { // county, hmis not null, email not checked
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else { // County not null, dba not null, hmis not checked, email not checked
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {// County not null, dba not null, hmis not null, email not checked
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            } else {// County not null, PCM not null, dba not checked, hmis not checked, email not checked
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else { // county not null, pcm not null, hmis not null, email not checked
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else { // county not null, pcm not null, dba not null, hmis not checked, email not checked
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                    } else {//DOB not null, County not checked,pcm not checked, dba not checked, hmis not checked, email not checked
                        if (CurrentClientCounty == null) {
                            if (CurrentClientPCM == null) {
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            } else {// DOB not null, pcm not null, dba not checked, hmis not checked, email not checked
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else {// DOB not null, pcm not null, dba not null, hmis not checked, email not checked
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        } else {// dob not null, county not null, pcm not checked, dba not checked, hmis not checked, email not checked
                            if (CurrentClientPCM == null) {
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            } else { //DOB not null, county not null, pcm not null, dba not checked, hmis not checked, email not checked
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {// Last name not null, dob not checked, county not checked, pcm not checked, dba not checked, hmis not checked, email not checked
                    if (CurrentClientDOB == null) {
                        if (CurrentClientCounty == null) {
                            if (CurrentClientPCM == null) {
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else { // last name not null, dba not null, hmis not checked, email not checked
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            } else {// Last name not null, pcm not null, DBA not checked, HMIS not checked, email not checked
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                        else {// Last name not null, county not null, pcm not checked, dba not checked, hmis not checked, email not checked
                            if (CurrentClientPCM == null) {
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else {//Last name not null, county not null, dba not null, hmis not checked, email not checked
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            } else {// Last name not null, county not null, pcm not null, dba not checked, hmis not checked, email not checked
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else {// Last name not null, county not null, pcm not null, dba not null, hmis not checked, email not checked
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else{ // Last name not null, DOB not null, County not checked, PCM not checked, DBA not checked, HMIS not checked, Email not checked
                        if(CurrentClientCounty == null){
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{// Last name not null, dob not null, pcm not null, dba not checked, hmis not checked, email not checked
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                        else{// Last name not null, DOB not null, county not null, pcm not checked, dba not checked, hmis  not checked, email not checked
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                                ClientSearchResults = create.select()
                                                        .from(Client.CLIENT)
                                                        .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                        .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                        .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                        .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                        .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                        .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                        .fetch();
                                                System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else{// First name not null, last name not checked, dob not checked, county not checked, pcm not checked, dba not checked, hmis not checked, email not checked
                if(CurrentClientLastName == null){
                    if(CurrentClientDOB == null){
                        if(CurrentClientCounty == null){
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                        else{// First name not null,county not null, pcm not checked, dba not checked, hmis not checked, email not checked
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else{// First name not null, dob not null, county not checked, pcm not checked, dba not checked, hmis not checked, email not checked
                        if(CurrentClientCounty == null){
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                        else{
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else{// First name not null, last name not null, dob not checked, county not checked, pcm not checked, dba not checked, hmis not checked, email not checked
                    if(CurrentClientDOB == null){
                        if(CurrentClientCounty == null){
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                        else{
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientEmail == null){
                                        ClientSearchResults = create.select()
                                                .from(Client.CLIENT)
                                                .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                .fetch();
                                        System.out.println(ClientSearchResults);
                                    }
                                    else{
                                        ClientSearchResults = create.select()
                                                .from(Client.CLIENT)
                                                .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                .fetch();
                                        System.out.println(ClientSearchResults);
                                    }
                                }
                            }
                            else{
                                if(CurrentClientHMIS == null){
                                    if(CurrentClientEmail == null){
                                        ClientSearchResults = create.select()
                                                .from(Client.CLIENT)
                                                .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                .fetch();
                                        System.out.println(ClientSearchResults);
                                    }
                                    else{
                                        ClientSearchResults = create.select()
                                                .from(Client.CLIENT)
                                                .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                .fetch();
                                        System.out.println(ClientSearchResults);
                                    }
                                }
                                else{
                                    if(CurrentClientEmail == null){
                                        ClientSearchResults = create.select()
                                                .from(Client.CLIENT)
                                                .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                .fetch();
                                        System.out.println(ClientSearchResults);
                                    }
                                    else{
                                        ClientSearchResults = create.select()
                                                .from(Client.CLIENT)
                                                .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                .fetch();
                                        System.out.println(ClientSearchResults);
                                    }
                                }
                            }
                        }
                    }
                    else{ // First Name not null, last name not null, dob not null, county not checked, etc.
                        if(CurrentClientCounty == null){
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                        else{
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else{
            ClientSearchResults = create.select()
                    .from(Client.CLIENT)
                    .where(Client.CLIENT.CLIENT_ID.eq(CurrentClientID))
                    .fetch();
        }
    }

    /**
     * Adds a ticket into the TICKET table when button is clicked based on user input
     */
    public void AddTicket() {
        NewTicketSubject = ticketSubject.getText();
        NewTicketDetails = ticketDetails.getText();
        NewTicketClient = parseInt(ticketClient.getText());
        NewTicketCallDate = ticketCallDate.getValue().atTime(LocalTime.now());
        if(ticketService.getValue().equals("Community Service")) {
            NewTicketService = 1;
        }
        if(ticketService.getValue().equals("Eviction Diversion")) {
            NewTicketService = 2;
        }
        create.insertInto(Ticket.TICKET, Ticket.TICKET.SUBJECT, Ticket.TICKET.DETAILS, Ticket.TICKET.CLIENT_ID, Ticket.TICKET.CREATOR_OF_TICKET, Ticket.TICKET.ENTRY_DATE, Ticket.TICKET.CALL_DATE, Ticket.TICKET.STATUS_ID, Ticket.TICKET.SERVICE_TYPE_ID)
                .values(NewTicketSubject, NewTicketDetails, NewTicketClient, 1, LocalDateTime.now(), NewTicketCallDate, 1, NewTicketService)
                .execute();
        System.out.println("Ticket added");
    }

    /**
     * Executes an SQL query that searches the TICKET table when button is clicked based on user input
     */
    public void SearchTicket() {

        try {
            if (dbTicketClientID != null) {
                SelectedClientid = dbTicketClientID.getText();
            }
            if (dbTicketSubject != null) {
                SelectedSubject = dbTicketSubject.getText();
            }
            if (dbTicketStatus != null) {
                if (dbTicketStatus.getValue().equals("Open")) {
                    SelectedStatus = 1;
                }
                if (dbTicketStatus.getValue().equals("Closed")) {
                    SelectedStatus = 2;
                }
                if (dbTicketStatus.getValue().equals("Waiting On Client")) {
                    SelectedStatus = 3;
                }
                if (dbTicketStatus.getValue().equals("Pending Prescreening")) {
                    SelectedStatus = 4;
                }
            }
            else{
                SelectedStatus = null;
            }
        }
        catch (Exception e){
            SelectedStatus = null;
        }
        try {
            if (dbTicketService != null) {
                if (dbTicketService.getValue().equals("Community Service")) {
                    SelectedService = 1;
                }
                if (dbTicketService.getValue().equals("Eviction Diversion")) {
                    SelectedService = 2;
                }
            }
            else{
                SelectedService = null;
            }
        }
        catch (Exception e){
            SelectedService = null;
        }
        if(Objects.equals(SelectedClientid, ""))
        {SelectedClientid = null;}
        if(Objects.equals(SelectedSubject, ""))
        {SelectedSubject = null;}
        if (SelectedClientid == null) {
            if(SelectedSubject == null){
                if(SelectedStatus == null){
                    if(SelectedService == null){
                        SelectAllTickets();
                    }
                    else{
                        SelectTicketWithService();
                    }
                }
                else{
                    if(SelectedService == null){
                        SelectTicketWithStatus();
                    }
                    else{
                        SelectTicketWithStatusAndService();
                    }
                }
            }
            else{
                if(SelectedStatus == null){
                    if(SelectedService == null){
                        SelectTicketWithSubject();
                    }
                    else{
                        SelectTicketWithSubjectAndService();
                    }
                }
                else{
                    if(SelectedService == null){
                        SelectTicketWithSubjectAndStatus();
                    }
                    else{
                        SelectTicketWithSubjectAndStatusAndService();
                    }
                }
            }
        }
        else{
            if(SelectedSubject == null){
                if(SelectedStatus == null){
                    if(SelectedService == null){
                        SelectTicketWithID();
                    }
                    else{
                        SelectTicketWithIDAndService();
                    }
                }
                else{
                    if(SelectedService == null){
                        SelectTicketWithIDAndStatus();
                    }
                    else{
                        SelectTicketWithIDAndStatusAndService();
                    }
                }
            }
            else{
                if(SelectedStatus == null){
                    if(SelectedService == null){
                        SelectTicketWithIDAndSubject();
                    }
                    else{
                        SelectTicketWithIDAndSubjectAndService();
                    }
                }
                else{
                    if(SelectedService == null){
                        SelectTicketWithIDAndSubjectAndStatus();
                    }
                    else{
                        SelectTicketWithAll();
                    }
                }
            }
        }
    }

    /**
     * Adds a followup to the FOLLOW_UP table when button is clicked based on user input
     */
    public void AddFollowUP() {
        NewFollowUpTicket = parseInt(followUpTicketID.getText());
        NewFollowupOutcome = followUpOutcome.getText();
        if(statusupdate.getValue().equals("Open")){
            NewFollowupStatus = 1;
        }
        if(statusupdate.getValue().equals("Closed")){
            NewFollowupStatus = 2;
        }
        if(statusupdate.getValue().equals("Waiting On Client")){
            NewFollowupStatus = 3;
        }
        if(statusupdate.getValue().equals("Pending Prescreening")){
            NewFollowupStatus = 4;
        }
        create.insertInto(Followups.FOLLOWUPS, Followups.FOLLOWUPS.TICKET_ID, Followups.FOLLOWUPS.FOLLOWUP_DATE, Followups.FOLLOWUPS.FOLLOWUP_OUTCOME)
                .values(NewFollowUpTicket, LocalDateTime.now(), NewFollowupOutcome)
                .execute();
        create.update(Ticket.TICKET)
                .set(Ticket.TICKET.STATUS_ID, NewFollowupStatus)
                .where(Ticket.TICKET.TICKET_ID.eq(NewFollowUpTicket))
                .execute();
        System.out.println("Follow-up added");
    }

    /**
     * Executes a SQL query that looks through the FOLLOW_UP table when button is clicked based on user input
     */
    public void FollowUpSearch() {
        int ticketID = 0;
        Result<Record> result;
        try {
            ticketID = Integer.parseInt(dbFollowUpTicketID.getText());
        } catch (Exception ignored){

        }

        if (ticketID != 0) {
            result = create.select()
                    .from(FOLLOWUPS)
                    .where(FOLLOWUPS.TICKET_ID.eq(ticketID))
                    .fetch();
        } else {
            result = create.select()
                    .from(FOLLOWUPS)
                    .fetch();
        }

        System.out.println(result);

    }
}
