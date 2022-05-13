package animenews.security;


public class SecurityUtils {
      public static String ROLE_ADMIN = "ROLE_ADMIN";
      public static String ROLE_USER = "ROLE_USER";

//    public static CustomUserDetail getCurrentLoggedUser() {
//        Authentication authentication = getAuthentication();
//        return authentication == null ? null : (CustomUserDetail) authentication.getPrincipal();
//    }
//
//    private static Authentication getAuthentication() {
//        return SecurityContextHolder.getContext().getAuthentication();
//    }

//    public static boolean hasLogged() {
//        return getAuthentication() != null;
//    }

}
