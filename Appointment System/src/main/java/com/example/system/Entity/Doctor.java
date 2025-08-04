package com.example.system.Entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctors")

public class Doctor {

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", nullable = false, updatable = false, length = 36)
    private UUID id;

	@Column(name = "name", updatable = false, nullable = false)
	private String name;

	@Column(name = "specialization", updatable = false, nullable = false)
	private String specialization;

	@Column(name = "experienceYears", updatable = false, nullable = false)
	private int experienceYears;

	@Column(name = "location", updatable = false, nullable = false)
	private String location;

	@Column(name = "consultationFee", updatable = false, nullable = false)
	private int consultationFee;

	@Column(name = "rating", updatable = false, nullable = false)
	private int rating;

	@Column(name = "patientStories", updatable = false, nullable = false)
	private int patientStories;

	@Column(name = "availableToday", updatable = false, nullable = false)
	private boolean availableToday;

}
