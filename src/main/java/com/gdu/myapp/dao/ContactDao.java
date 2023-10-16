package com.gdu.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gdu.myapp.dto.ContactDto;

@Repository
public class ContactDao {
  
  @Autowired
  private JdbcConnection jdbcConnection;
  
  private Connection con;
  private PreparedStatement ps;
  private ResultSet rs;
  
  
  
  public int insert(ContactDto contactDto) {
      
      int insertCount = 0;
      
      try {
        
        con = jdbcConnection.getConnection();

        String sql = "INSERT INTO NOTICE_T(NOTICE_NO, GUBUN, TITLE, CONTENT) VALUES(NOTICE_SEQ.NEXTVAL, ?, ?, ?)";
        ps = con.prepareStatement(sql);
        ps.setInt(1, contactDto.getGubun());
        ps.setString(2, contactDto.getTitle());
        ps.setString(3, contactDto.getContent());
        insertCount = ps.executeUpdate();
        
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        jdbcConnection.close(con, ps, rs);
      }
      
      return insertCount;
      
    }
    
  /**
   * 수정 메소드<br>
   */
  public int update(ContactDto contactDto) {
    
        
    int updateCount = 0;
    
    try {
      
      con = jdbcConnection.getConnection();
      String sql = "UPDATE NOTICE_T SET GUBUN = ?, TITLE = ?, CONTENT = ? WHERE NOTICE_NO = ?";    
      ps = con.prepareStatement(sql);
      ps.setInt(1, contactDto.getGubun());
      ps.setString(2, contactDto.getTitle());
      ps.setString(3, contactDto.getContent());
      ps.setInt(4, contactDto.getNotice_no());
      updateCount = ps.executeUpdate();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcConnection.close(con, ps, rs);
    }
    
    return updateCount;
    
  }
    
  /**
   * 삭제 메소드<br>
   */
  public int delete(int notice_no) {
    
    int deleteCount = 0;
    
    try {
      
      con = jdbcConnection.getConnection();
      String sql = "DELETE FROM NOTICE_T WHERE NOTICE_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, notice_no);
      deleteCount = ps.executeUpdate();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcConnection.close(con, ps, rs);
    }
    
    return deleteCount;
    
  }
  
  /**
   * 전체 조회 메소드<br>
   */
  public List<ContactDto> selectList() {
    
    List<ContactDto> list = new ArrayList<ContactDto>();
    
    try {
      
      con = jdbcConnection.getConnection();
      String sql = "SELECT NOTICE_NO, GUBUN, TITLE, CONTENT FROM NOTICE_T ORDER BY NOTICE_NO DESC";
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      
        // 검색 결과 행 하나를 Dto로 바꾸는 과정
        // (행 하나당 컨택 Dto 1개로 바꿔 준다.)
      while(rs.next()) {
        ContactDto contactDto = new ContactDto();
        contactDto.setNotice_no(rs.getInt("NOTICE_NO"));
        contactDto.setGubun(rs.getInt("GUBUN"));
        contactDto.setTitle(rs.getString("TITLE"));
        contactDto.setContent(rs.getString("CONTENT"));
        list.add(contactDto);
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcConnection.close(con, ps, rs);
    }
    
    return list;
    
  }
  
  /**
   * 상세 조회 메소드<br>
   */
  public ContactDto selectContactByNo(int notice_no) {
    
    
    ContactDto contactDto = null;
    
    try {
      
      con = jdbcConnection.getConnection();
      String sql = "SELECT NOTICE_NO, GUBUN, TITLE, CONTENT FROM NOTICE_T WHERE NOTICE_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, notice_no);
      rs = ps.executeQuery();
      if(rs.next()) {
        contactDto = new ContactDto();
        contactDto.setNotice_no(rs.getInt(1));
        contactDto.setGubun(rs.getInt(2));
        contactDto.setTitle(rs.getString(3));
        contactDto.setContent(rs.getString(4));
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcConnection.close(con, ps, rs);
    }
    
    return contactDto;
    
  }
  
}
