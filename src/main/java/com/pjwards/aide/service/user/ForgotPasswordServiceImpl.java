package com.pjwards.aide.service.user;

import com.pjwards.aide.repository.ForgotPasswordRepository;
import com.pjwards.aide.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForgotPasswordServiceImpl.class);
    //private final ForgotPasswordRepository forgotPasswordRepository;
    //private final UserRepository userRepository;
    //private final Utils utils;

/*    @Autowired
    public ForgotPasswordServiceImpl(ForgotPasswordRepository forgotPasswordRepository, UserRepository userRepository, Utils utils) {
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.userRepository = userRepository;
        this.utils = utils;
    }

    @Override
    public Iterable<ForgotPassword> findAllByUserANDBYValidEntity(User user, ValidEntity validEntity) {
        LOGGER.debug("Find forgot password By user={}, validEntity={}", user, validEntity);

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QForgotPassword.forgotPassword.user.id.eq(user.getId()));
        builder.and(QForgotPassword.forgotPassword.validEntity.eq(validEntity));

        return forgotPasswordRepository.findAll(builder);
    }

    @Override
    public ForgotPassword create(User user) {

        ForgotPassword forgotPassword = new ForgotPassword();
        forgotPassword.setUser(user);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 15);
        forgotPassword.setExpiredDate(calendar.getTime());
        forgotPassword.setValidEntity(ValidEntity.VALID);

        forgotPassword.setKeyHash(utils.getRandomHash(calendar.getTime().toString()));

        LOGGER.debug("Forgot Password Generate user email={}, random={}, expiredDate={}", user.getEmail(), forgotPassword.getExpiredDate());
        LOGGER.debug("Forgot Password Generate key={}", forgotPassword.getKeyHash());

        Iterable<ForgotPassword> forgotPasswordList = findAllByUserANDBYValidEntity(forgotPassword.getUser(), ValidEntity.VALID);
        for(ForgotPassword list : forgotPasswordList){
            list.setValidEntity(ValidEntity.INVALID);
            forgotPasswordRepository.save(list);
        }

        return forgotPasswordRepository.save(forgotPassword);
    }

    @Override
    public User resetPassword(ForgotPasswordForm form, ForgotPassword forgotPassword) {

        User user = forgotPassword.getUser();
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        userRepository.save(user);

        forgotPassword.setValidEntity(ValidEntity.INVALID);
        forgotPasswordRepository.save(forgotPassword);
        return user;
    }*/
}
