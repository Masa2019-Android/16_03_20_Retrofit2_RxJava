package com.telran.a16_03_20.data.dto;

import java.util.List;

public class ContactListDto {
    List<ContactDto> contacts;

    public ContactListDto() {
    }

    public ContactListDto(List<ContactDto> contacts) {
        this.contacts = contacts;
    }

    public List<ContactDto> getContacts() {
        return contacts;
    }

    @Override
    public String toString() {
        return "ContactListDto{" +
                "contacts=" + contacts +
                '}';
    }
}
