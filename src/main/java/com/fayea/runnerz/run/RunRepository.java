package com.fayea.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    // Implement CRUD operations here
    private List<Run> runs = new ArrayList<>();
    List<Run> findAll() {return runs; };

    @PostConstruct
    void init() {
        runs.add(new Run(1, "Run 1", LocalDateTime.now(), LocalDateTime.now(), 10, Location.OUTDOOR));
        runs.add(new Run(2, "Run 2", LocalDateTime.now(), LocalDateTime.now(), 15, Location.INDOOR));
    }

    public Optional<Run> findById(Integer id) {
        return runs.stream()
                .filter(run -> run.id().equals(id))
                .findFirst();
    }

    public void save(Run run) {
        runs.add(run);

    }
    public void update(Run run, Integer id) {
        Optional<Run> existing= findById(id);
        if(existing.isPresent()){
            runs.set(runs.indexOf(existing.get()), run);
        }

    }
    public void deleteById(Integer id) {
        runs.removeIf(run -> run.id().equals(id));
    }
}
