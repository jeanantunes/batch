package com.poc.tesouro.controller;

import com.poc.tesouro.model.User;
import com.poc.tesouro.repository.UserRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.batch.operations.JobRestartException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/user")
@EnableScheduling
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job1;

    @Scheduled(cron = "0 */1 * * * ?")
    public void perform() throws Exception
    {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(job1, params);
    }

    /*  */
    @GetMapping(path = "/findAll")
    public @ResponseBody
    Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping(path = {"/findById/{id}"})
    public ResponseEntity findById(@PathVariable long id) {
        return userRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/create")
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody User user) {
        return userRepository.findById(id)
                .map(record -> {
                    record.setName(user.getName());
                    record.setDescription(user.getDescription());

                    User updated = userRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/delete/{id}"})
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(record -> {
                    userRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    /* */


    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String description){
        User n = new User();
        n.setName(name);
        n.setDescription(description);

        userRepository.save(n);
        return "Usuário " + n.getName().toUpperCase() + " adicionado com sucesso!!";
    }
     

    /*
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUser(){
        return userRepository.findAll();
    }
     */

    @RequestMapping("/job1")
    @ResponseBody
    String requestJob1() throws JobExecutionAlreadyRunningException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException,
            org.springframework.batch.core.repository.JobRestartException {
        jobLauncher.run(job1, createInitialJobParameterMap());
        return "Job 1! "+ '\n' +"Executado com sucesso.";
    }

    private JobParameters createInitialJobParameterMap() {
        Map<String, JobParameter> m = new HashMap<>();
        m.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters p = new JobParameters(m);
        return p;
    }

}
