
package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.CourseEntity;

public interface CourseEntityRepo extends JpaRepository<CourseEntity, Integer> {

}
