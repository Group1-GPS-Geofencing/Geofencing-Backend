package com.geofence.geofencing_backend.services;

/* TwilioSMSService
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 03-05-2024
 * Last Modified on: 06-05-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 *
 * Service for Sending SMS alerts in the occurrence of an event i.e., fence entry or exit
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

@Service
public class TwilioSMSService {

    private final String accountSid;
    private final String authToken;
    private final String twilioPhoneNumber;
    private final String receiverPhoneNumber;

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

    // To be called to send an SMS in occurrence of an event
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
