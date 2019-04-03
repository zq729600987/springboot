package springboot.pojo;


import javax.persistence.Id;
import java.io.Serializable;

public class User implements Serializable {
    @Id
    //@KeySql(useGeneratedKeys = true)   //配置主键策略
    //@KeySql(dialect = IdentityDialect.MYSQL)
    //@KeySql(sql = "select SEQ_ID.nextval from dual", order = ORDER.BEFORE)
    private Integer id;
    private String username;
    private String password;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
