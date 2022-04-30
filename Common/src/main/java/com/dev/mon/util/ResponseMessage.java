package com.dev.mon.util;

import java.util.Map;

public class ResponseMessage
{

    private String message;
    private int code;
    private Map<String, Object> challenge;

    public ResponseMessage(String message, int code)
    {
        this.message = message;
        this.code = code;
    }

    public ResponseMessage(String message, int code, Map<String, Object> challenge)
    {
        this.message = message;
        this.code = code;
        this.challenge = challenge;
    }

    public ResponseMessage()
    {
    }

    public ResponseMessage message(String message)
    {
        return new ResponseMessage(message, 0, null);
    }

    public ResponseMessage code(int code)
    {
        return new ResponseMessage(null, code, null);
    }

    public String getMessage()
    {
        return message;
    }

    public int getCode()
    {
        return code;
    }

    public Map<String, Object> getChallenge()
    {
        return challenge;
    }

}