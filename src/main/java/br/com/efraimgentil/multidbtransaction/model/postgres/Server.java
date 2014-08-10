package br.com.efraimgentil.multidbtransaction.model.postgres;

import javax.persistence.Entity;

public class Server {
  
  private Integer id;
  private String nome;
  private String ip;
 
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getIp() {
    return ip;
  }
  public void setIp(String ip) {
    this.ip = ip;
  }
  
}