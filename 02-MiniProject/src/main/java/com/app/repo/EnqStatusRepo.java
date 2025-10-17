
package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.EnqStatusEntity;

public interface EnqStatusRepo extends JpaRepository<EnqStatusEntity, Integer> {

}
