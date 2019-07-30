package com.capelania.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class EntityJpa {

	@Basic
	@Column(name = "created_date", nullable = false, updatable = false)
	@JsonIgnore
	private LocalDateTime createdDate;
	
	@Basic
	@Column(name = "updated_date")
	@JsonIgnore
	private LocalDateTime updatedDate;

	@Column(name = "created_by", nullable = false, updatable = false)
	@JsonIgnore
	private Long createdBy;

	@Column(name = "updated_by")
	@JsonIgnore
	private Long updatedBy;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean active = false;

	public abstract Long getId();
}