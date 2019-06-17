package test2.spring.batch.config.batch;

import com.opencsv.CSVWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import test2.spring.batch.model.User;

import java.io.FileWriter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemWriterImpl2 implements ItemWriter<User> {

    @Value("${csv.outputFile.location}")
    private String outputFile;

    @Override
    public void write(List<? extends User> list) throws Exception {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(outputFile));
        csvWriter.writeAll(transformUser(list));
        csvWriter.close();
    }

    private List<String[]> transformUser(List<? extends User> userList) {
        return userList
                .stream()
                .map(u -> new String[]{u.getId() + "", u.getName(), u.getSurname(), u.getEmail(), u.getPassword()})
                .collect(Collectors.toList());
    }
}
