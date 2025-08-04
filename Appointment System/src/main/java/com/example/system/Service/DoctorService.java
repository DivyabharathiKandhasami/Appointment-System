package com.example.system.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.system.Entity.Doctor;
import com.example.system.Repository.DoctorRepo;

import jakarta.transaction.Transactional;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Service
@Data
public class DoctorService {
	
	
	@Autowired
    private DoctorRepo doctorRepository;
	
	 //add doctor
	 public Doctor addDoctor(Doctor doctor) 
	 {
	  return doctorRepository.save(doctor);
	 }
	
	 public List<Doctor> searchDoctors(String location, String specialization) 
	 {
	        return doctorRepository.findByLocationAndSpecialization(location, specialization);
	 }
	
	 public Doctor updateDoctor(UUID id, Doctor updatedDoctor) 
	 {
	         updatedDoctor.setId(id);
	        return doctorRepository.save(updatedDoctor);
	 }
	 
	 @Transactional
	 public String deleteDoctor(UUID id) 
	 {
	        return doctorRepository.deleteById(id);
	        
	 }

}
