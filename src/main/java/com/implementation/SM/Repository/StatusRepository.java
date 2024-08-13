package com.implementation.SM.Repository;

import com.implementation.SM.Entity.Project;
import com.implementation.SM.Entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}