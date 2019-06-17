package test2.spring.batch.controller;

import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test2.spring.batch.model.User;
import test2.spring.batch.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Job userJob;

    @Autowired
    private JobLauncher jobLauncher;

    @PostMapping
    public ResponseEntity save(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity
                .ok("user saved successfully");
    }

    @SneakyThrows
    @GetMapping("/transform-to-excel")
    public ResponseEntity createUserExcel(){
        System.out.println("Controller thread : " + Thread.currentThread());
        JobExecution execution = jobLauncher.run(userJob, new JobParameters());
        return ResponseEntity
                .ok("job status : " + execution.getStatus());
    }
}
