package animenews.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class UserRegisterModel {
    private String userLogin;

    private String userPass;

    private String userNicename;

    private String userEmail;
}
