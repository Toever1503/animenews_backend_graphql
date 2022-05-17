package animenews.model.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserFilter {
    private String q;
    private List<Long> id_in;
    private List<Long> id_not_in;
    private String userEmail;
    private String userLogin;
    private String authority;
    private Boolean userStatus;
}
