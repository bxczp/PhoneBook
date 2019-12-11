package com.bx.service.impl;

import com.bx.entity.PhoneBook;
import com.bx.mapper.PhoneBookMapper;
import com.bx.service.PhoneBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("phoneBookService")
public class PhoneBookServiceImpl implements PhoneBookService {

    @Autowired
    private PhoneBookMapper phoneBookMapper;


    @Override
    public Integer add(PhoneBook phoneBook) {
        return phoneBookMapper.add(phoneBook);
    }

    @Override
    public List<PhoneBook> loadByInitial(String initial) {
        return phoneBookMapper.loadByInitial(initial);
    }


    @Override
    public Integer update(PhoneBook phoneBook) {
        return phoneBookMapper.update(phoneBook);
    }

    @Override
    public PhoneBook findById(Integer id) {
        return phoneBookMapper.findById(id);
    }

    @Override
    public Integer delete(Integer id) {
        return phoneBookMapper.delete(id);
    }
}
