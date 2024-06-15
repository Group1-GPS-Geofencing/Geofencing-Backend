package com.geofence.geofencing_backend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

/**
 *  TwilioSMSService
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 03-05-2024
 * Last Modified on: 06-05-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 * Service for Sending SMS alerts in the occurrence of an event i.e., fence entry or exit
 */

@Service
public class TwilioSMSService {

    private final String accountSid;
    private final String authToken;
    private final String twilioPhoneNumber;
    private final String receiverPhoneNumber;

    /**
     * Constructs a TwilioSMSService instance by loading API keys from a JSON file.
     *
     * @throws IOException if there is an issue reading the API keys file
     */
    public TwilioSMSService() throws IOException {
        File apiKeysFile = new File("config/api_keys/twilio_keys.json");
        FileInputStream fileInputStream = new FileInputStream(apiKeysFile);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> twilioKeys = objectMapper.readValue(fileInputStream, Map.class);

        accountSid = twilioKeys.get("account_sid");
        authToken = twilioKeys.get("auth_token");
        twilioPhoneNumber = twilioKeys.get("phone_number");
        receiverPhoneNumber = twilioKeys.get("receiver_phone_number");

        fileInputStream.close();
    }

    /**
     * Sends an SMS with the specified message body using Twilio.
     *
     * @param messageBody the body of the SMS to be sent
     */
    public void sendSMS(String messageBody) {
        //Authenticate with Twilio
        Twilio.init(accountSid, authToken);

        // Send SMS using Twilio
        Message message = Message.creator(
                new PhoneNumber(receiverPhoneNumber),
                new PhoneNumber(twilioPhoneNumber),
                messageBody
        ).create();
        System.out.println("Message SID: " + message.getSid());
    }

}
