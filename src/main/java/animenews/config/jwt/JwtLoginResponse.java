package animenews.config.jwt;

import animenews.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class JwtLoginResponse {
    private String token;
    private String type = "Bearer";
    private List<String> authorities;
}
