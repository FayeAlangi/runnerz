package com.fayea.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {
    private final RunRepository runRepository;
    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping ("/hello")
    List<Run> findAll(){
        return runRepository.findAll()  ;
    }

    @GetMapping ("/{id}")
    Run findById(@PathVariable Integer id)  {
        Optional<Run> run = runRepository.findById(id);
        if(run.isEmpty()) {
            throw new RunNotFoundException();
        }
        return run.get();
    }
    // Implement other CRUD operations here
@ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void createRun(@Valid @RequestBody Run run) {
        runRepository.save(run);
    }

   @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("{id}")
    void update(@RequestBody Run run, @PathVariable Integer id) {
        runRepository.update(run,id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.deleteById(id);
    }

}