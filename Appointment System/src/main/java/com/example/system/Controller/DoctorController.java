package com.example.system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.system.Entity.Doctor;
import com.example.system.Service.DoctorService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	// create new doctor
	@PostMapping("/create")
	public String createDoctor(@RequestBody Doctor doctor) {

		doctorService.addDoctor(doctor);
		return "Doctor Created Sucessfully";
	}

	// Search by location and specialization
	@GetMapping("/search")
	public List<Doctor> searchDoctors(@RequestParam String location, @RequestParam String specialization) {
		return doctorService.searchDoctors(location, specialization);
	}

	// Update Doctor Account
	@PutMapping("update/{id}")
	public String updateDoctor(@PathVariable UUID id, @RequestBody Doctor doctor) {
		doctorService.updateDoctor(id, doctor);
		return "Updated Doctor Account";
	}

	// Delete Doctor Account
	@DeleteMapping("delete/{id}")
	public String deleteDoctor(@PathVariable UUID id) {
		doctorService.deleteDoctor(id);
		return "Delete Doctor Account";
	}
}
