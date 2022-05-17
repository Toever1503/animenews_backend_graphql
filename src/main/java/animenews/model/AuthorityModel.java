package animenews.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AuthorityModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String authorityName;

    private Integer authorityLevel;

}