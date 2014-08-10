package br.com.efraimgentil.multidbtransaction;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import br.com.efraimgentil.multidbtransaction.service.ServerService;

public class TestServiceMain {

  public static void main(String[] args) throws SQLException {
    
    ApplicationContext context = new FileSystemXmlApplicationContext("**/spring/application.xml");
    ServerService serverService = (ServerService) context.getBean("serverService");
    
    serverService.saveServer( null );
    
  }
  
}
