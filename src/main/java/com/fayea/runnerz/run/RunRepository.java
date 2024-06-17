package com.fayea.runnerz.run;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RunRepository extends ListCrudRepository<Run, Integer> {
    @Query
    List<Run> findAllByLocation(String location);
}
