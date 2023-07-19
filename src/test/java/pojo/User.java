package pojo;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    public String id;
    public String email;
    public String name;
    public String surname;
    public String role;
    public String position;
    public String status;
    public Object isReport;
    public Object comment;
    public String createBy;
    public Object report;
    public Date updated_at;
    public Date created_at;
    public boolean feedback;
    public Date finished_at;
    public Object linkedin;
    public Object city;
    public Object jira;
}
