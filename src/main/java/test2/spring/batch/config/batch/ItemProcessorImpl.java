package test2.spring.batch.config.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import test2.spring.batch.model.User;

@Component
public class ItemProcessorImpl implements ItemProcessor<User, User> {
    @Override
    public User process(User user) throws Exception {
        System.out.println("Processing Thread : " + Thread.currentThread());
        user.setPassword("*****************");
        return user;
    }
}
