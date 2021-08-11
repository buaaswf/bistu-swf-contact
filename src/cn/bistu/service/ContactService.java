package cn.bistu.service;

import cn.bistu.entity.Contact;
import cn.bistu.exception.NameRepeatException;

import java.util.List;

public interface ContactService {
    public void addContact(Contact contact) throws NameRepeatException;
    public void updateContact(Contact contact);
    public void deleteContact(String id);
    public List<Contact> finaAll();
    public Contact findById(String id);
}
