package com.hateyahighschool.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * Created by A.A.MAMUN on 8/18/2019.
 */
public class SendSmsByTwilio {

    private final static String ACCOUNT_SID = "AC3872c022eb6c5f5b1b78f0d2b6cbcb62";
    private final static String AUTH_ID = "64f163d406f801f320ef675b8c138ced";

    static {
        Twilio.init(ACCOUNT_SID, AUTH_ID);
    }

    public static void sendSms(String toPhoneNumber, String message){
        Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber("+12038069078"),
                message).create();
    }
}
