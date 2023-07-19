package pojo;

import lombok.Data;

@Data
public class CreateUserResponse {

    public String _id;
    public String currency_code;
    public String email;
    public String name;
    public String password_change;
    public String password_repeat;
    public String surname;
    public String username;
    public int __v;
}
