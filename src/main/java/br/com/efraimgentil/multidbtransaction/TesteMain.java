package br.com.efraimgentil.multidbtransaction;

import java.sql.Connection;
import java.sql.SQLException;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
import com.mysql.jdbc.Driver;

public class TesteMain {

  public static void main(String[] args) throws SQLException, IllegalStateException, SecurityException, SystemException {
    ApplicationContext context = new FileSystemXmlApplicationContext("**/spring/application.xml");
    
    UserTransactionManager manager = (UserTransactionManager) context.getBean("AtomikosTransactionManager");
    try {
      manager.begin();
      AtomikosNonXADataSourceBean mysqlDS = (AtomikosNonXADataSourceBean) context.getBean("mysqlDS");
      AtomikosNonXADataSourceBean postgresDS = (AtomikosNonXADataSourceBean) context.getBean("postgresDS");
      Connection mysql = mysqlDS.getConnection();
      Connection postgres = postgresDS.getConnection();
      
      mysql.prepareStatement("INSERT INTO teste(nome) VALUES('nome')").executeUpdate();
      postgres.prepareStatement("INSERT INTO server (name , host) VALUES ( 'NOME' , '10.0.0.1')").executeUpdate();
      
      if(true) throw new RuntimeException();
      
      manager.commit();
    } catch (Exception e) {
      e.printStackTrace();
      manager.rollback();
    }
  }

}
