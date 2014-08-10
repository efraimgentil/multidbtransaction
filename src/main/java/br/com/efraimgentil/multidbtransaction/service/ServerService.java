package br.com.efraimgentil.multidbtransaction.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.efraimgentil.multidbtransaction.model.postgres.Server;

@Service
public class ServerService {
  
  @Resource(name = "mysqlDS")
  private DataSource mysqlDS;
  
  @Resource(name = "postgresDS")
  private DataSource postgresDS;
  
  
  @Transactional
  public void saveServer( Server server ) throws SQLException{
    Connection mysql = mysqlDS.getConnection();
    Connection postgres = postgresDS.getConnection();
    
    mysql.prepareStatement("INSERT INTO teste(nome) VALUES('TESTE WITH SERVICE')").executeUpdate();
    postgres.prepareStatement("INSERT INTO server (name) VALUES ( 'TEST WITH SERVICE' )").executeUpdate();
    
    throw new RuntimeException(); 
  }
  
}