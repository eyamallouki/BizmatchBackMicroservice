package com.esprit.Bizmatch.Register.Register.util;

import java.util.UUID;

public class UserCode {
    public static String getCode() {
        return UUID.randomUUID().toString();
    }
}
