package com.springboot.pojo;


import javax.persistence.Id;
import java.io.Serializable;

public class User implements Serializable {

    //@KeySql(useGeneratedKeys = true)   //配置主键策略
    //@KeySql(dialect = IdentityDialect.MYSQL)
    //@KeySql(sql = "select SEQ_ID.nextval from dual", order = ORDER.BEFORE)
    @Id
    private Integer id;
    private String userno;
    private String username;
    private String password;
    private String telephone;
    private String cteatetime;
    private String modifytime;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCteatetime() {
        return cteatetime;
    }

    public void setCteatetime(String cteatetime) {
        this.cteatetime = cteatetime;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
