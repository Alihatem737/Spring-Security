package com.course.springscurityex.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.course.springscurityex.model.users;
import org.springframework.stereotype.Repository;


@Repository
public interface Userrepo extends JpaRepository<users , Integer> {


    users findByUsername(String username);
}
