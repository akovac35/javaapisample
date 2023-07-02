package si.zpiz.sample.infrastructure.user_details_related.command_query;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import si.zpiz.sample.domain.persistence.GrantedAuthorityImpl;
import si.zpiz.sample.domain.user_details_related.UserDetailsDbo;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.mediator.IMediatorHandler;
import si.zpiz.sample.infrastructure.user_details_related.UserDetailsDboRepository;

@Service
public class CreateUserDetailsHandler implements IMediatorHandler<CreateUserDetailsCommand, UserDetailsDbo> {

    private UserDetailsDboRepository userDetailsDboRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CreateUserDetailsHandler(UserDetailsDboRepository userDetailsDboRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsDboRepository = userDetailsDboRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public UserDetailsDbo handle(CreateUserDetailsCommand request) throws MediatorException {
        UserDetailsDbo userDetailsDbo = new UserDetailsDbo();
        userDetailsDbo.setUsername(request.getUsername());
        userDetailsDbo.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userDetailsDbo.setAuthorities(request.getAuthorities().stream().map(GrantedAuthorityImpl::new)
                .collect(java.util.stream.Collectors.toList()));
        userDetailsDbo.setEnabled(true);

        userDetailsDbo = userDetailsDboRepository.save(userDetailsDbo);
        return userDetailsDbo;
    }

}
