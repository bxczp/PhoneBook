package com.bx.service;


import com.bx.entity.PhoneBook;

import java.util.List;

public interface PhoneBookService {

    /**
     * 根据姓名首字母查询电话簿记录
     *
     * @param initial
     * @return
     */
    List<PhoneBook> loadByInitial(String initial);

    /**
     * 添加通讯记录
     *
     * @param phoneBook
     * @return
     */
    Integer add(PhoneBook phoneBook);


    /**
     * 修改信息
     *
     * @param phoneBook
     * @return
     */
    Integer update(PhoneBook phoneBook);

    /**
     * 根据id查询通讯录记录
     *
     * @param id
     * @return
     */
    PhoneBook findById(Integer id);


    /**
     * 删除信息
     *
     * @param id
     * @return
     */
    Integer delete(Integer id);

}
