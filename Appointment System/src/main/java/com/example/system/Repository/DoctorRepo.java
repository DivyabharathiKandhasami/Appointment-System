package com.example.system.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.system.Entity.Doctor;

public interface DoctorRepo extends JpaRepository <Doctor, Long> 
{
	
	 List<Doctor> findByLocationAndSpecialization(String location, String specialization);

	 String deleteById(UUID id);

}
