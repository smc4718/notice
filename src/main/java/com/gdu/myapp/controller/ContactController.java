package com.gdu.myapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.myapp.dto.ContactDto;
import com.gdu.myapp.service.ContactService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ContactController {
  
  private final ContactService contactService;
  
  @RequestMapping(value="/", method=RequestMethod.GET)
  public String index() {
    return "index";
  }

  @RequestMapping(value="/contact/list.do", method=RequestMethod.GET)
  public String list(Model model) {
    log.info("");
    List<ContactDto> contactList = contactService.getContactList();
    model.addAttribute("contactList", contactList);
    log.info(contactList.toString());
    return "contact/list";
  }
  
  @RequestMapping(value="/contact/write.do", method=RequestMethod.GET)
  public String write() {
    return "contact/write";
  }
  
  @RequestMapping(value="/contact/add.do", method=RequestMethod.POST)
  public String add(ContactDto contactDto, RedirectAttributes redirectAttributes) {
    log.info("add: " + contactDto);
    int addResult = contactService.addContact(contactDto);
    redirectAttributes.addFlashAttribute("addResult", addResult);
    return "redirect:/contact/list.do";   // 목록보기로 다시 넘어가기 (list.do)
  }
  
  @RequestMapping(value="/contact/detail.do", method=RequestMethod.GET)
  public String detail(@RequestParam(value="notice_no", required=false, defaultValue="0") int notice_no, Model model) {
    log.info("detail:" + notice_no);
    model.addAttribute("contact", contactService.getContactByNo(notice_no));
    return "contact/detail";
  }
  
  @RequestMapping(value="/contact/modify.do", method=RequestMethod.POST)
  public String modify(ContactDto contactDto, RedirectAttributes redirectAttributes) {
    log.info("modify:" + contactDto);
    int modifyResult = contactService.modifyContact(contactDto);
    redirectAttributes.addFlashAttribute("modifyResult", modifyResult);
    return "redirect:/contact/detail.do?notice_no=" + contactDto.getNotice_no();  // 수정 후에 상세보기로 가겠다. (상세보기에는 반드시 전달할 번호가 필요하다.)
  }
  
  @RequestMapping(value="/contact/delete.do", method=RequestMethod.POST)
  public String delete(@RequestParam(value="notice_no", required=false, defaultValue="0") int notice_no, RedirectAttributes redirectAttributes) {
    log.info("delete:" + notice_no);
    int deleteResult = contactService.deleteContact(notice_no);
    redirectAttributes.addFlashAttribute("deleteResult", deleteResult);
    return "redirect:/contact/list.do";
  }
  
}
