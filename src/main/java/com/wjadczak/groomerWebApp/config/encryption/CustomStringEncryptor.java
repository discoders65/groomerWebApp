package com.wjadczak.groomerWebApp.config.encryption;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class CustomStringEncryptor implements StringEncryptor {

    private final StandardPBEStringEncryptor encryptor;

    public CustomStringEncryptor() {
        this.encryptor = new StandardPBEStringEncryptor();
        this.encryptor.setPassword("MSN_04_Sazabi");
    }

    @Override
    public String encrypt(String input) {
        return encryptor.encrypt(input);
    }

    @Override
    public String decrypt(String encryptedMessage) {
        return encryptor.decrypt(encryptedMessage);
    }
}
