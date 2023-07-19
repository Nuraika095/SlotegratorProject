package pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateUserRequest {

    public String currency_code;
    public String email;
    public String name;
    public String password_change;
    public String password_repeat;
    public String surname;
    public String username;


}
