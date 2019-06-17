package test2.spring.batch.config.batch;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import test2.spring.batch.model.User;

import java.io.FileOutputStream;
import java.util.List;

//@Component
public class ItemWriterImpl implements ItemWriter<User> {

    @Value("${outputFile.location}")
    private String fileLocation;

    @SneakyThrows
    @Override
    public void write(List<? extends User> users) throws Exception {
        Workbook workbook = null;
        int rowIndex = 0;
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("users");
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue("Id");
            row.createCell(1).setCellValue("Name");
            row.createCell(2).setCellValue("Surname");
            row.createCell(3).setCellValue("Email");
            row.createCell(4).setCellValue("Password");
            for (User user : users) {
                row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getName());
                row.createCell(2).setCellValue(user.getSurname());
                row.createCell(3).setCellValue(user.getEmail());
                row.createCell(4).setCellValue(user.getPassword());
            }
            workbook.write(new FileOutputStream(fileLocation));
            workbook.close();
    }
}
