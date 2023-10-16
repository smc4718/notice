package com.gdu.myapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gdu.myapp.dao.ContactDao;
import com.gdu.myapp.dto.ContactDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor  
@Service                 
public class ContactServiceImpl implements ContactService {

  private final ContactDao contactDao;
  
  @Override
  public int addContact(ContactDto contactDto) {
    int addResult = contactDao.insert(contactDto);
    return addResult;
  }

  @Override
  public int modifyContact(ContactDto contactDto) {
    int modifyResult = contactDao.update(contactDto);
    return modifyResult;
  }

  @Override
  public int deleteContact(int notice_no) {
    int deleteResult = contactDao.delete(notice_no);
    return deleteResult;
  }

  @Override
  public List<ContactDto> getContactList() {
    return contactDao.selectList();

  }

  @Override
  public ContactDto getContactByNo(int notice_no) {
    return contactDao.selectContactByNo(notice_no);
  }

}
