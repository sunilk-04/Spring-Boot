package com.prominentpixel.jpawithdynamicquery.repository;

import com.prominentpixel.jpawithdynamicquery.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    // By default, native query is false
    // Will list only active students
    @Query(value = "SELECT * FROM student s WHERE s.active = true", nativeQuery = true)
    List<Student> getAll();

    // JPQL - indexed parameter (Parameter id will replace '?1')
    @Query("SELECT s FROM Student s WHERE s.id = ?1")
    Student getById(int id);

    // JPQL - named parameter with sort feature
    @Query("SELECT s FROM Student s WHERE s.id IN :ids")
    List<Student> getByIds(@Param("ids") List<Integer> ids, Sort sort);

    // JPQL - update using modifying
    @Modifying
    @Query(value = "UPDATE student s SET s.active = false WHERE s.id = :id", nativeQuery = true)
    void deactivate(@Param("id") int id);

    @Modifying
    @Query(value = "UPDATE student s SET s.active = true WHERE s.id = :id", nativeQuery = true)
    void activate(@Param("id") int id);

    @Modifying
    @Query(value = "INSERT INTO student (name, city, email, active) VALUES (:name, :city, :email, 1)", nativeQuery = true)
    void insert(@Param("name") String name, @Param("email") String email, @Param("city") String city);

    @Query("SELECT s.id FROM Student s WHERE s.active = true")
    List<Integer> getActiveIds();
}
