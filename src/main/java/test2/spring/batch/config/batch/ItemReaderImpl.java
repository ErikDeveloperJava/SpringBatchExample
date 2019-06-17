package test2.spring.batch.config.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test2.spring.batch.model.User;
import test2.spring.batch.repository.UserRepository;

import java.util.List;

//@Component
public class ItemReaderImpl implements ItemReader<List<User>> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return userRepository.findAll();
    }
}
