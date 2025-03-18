package nz.clem.book.config;

import nz.clem.book.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.swing.text.html.Option;
import java.util.Optional;

public class ApplicationAuditAware implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null ||
                !authentication.isAuthenticated() ||
                    authentication instanceof AnonymousAuthenticationToken ) {
           return Optional.empty();
        }
        User userPrinciple = (User)authentication.getPrincipal();
        return Optional.ofNullable(userPrinciple.getId());
    }
}
