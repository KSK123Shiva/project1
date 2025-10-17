package com.app.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;

@Entity
@Data
@Table(name="customer_Enq_table")
public class CustomertEnquiriesEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer enqId;
	private String customerName;
	private Integer customerPhno;
	private String viewMode;
	private String serviceName;
	private String enqStatus;
	
	@CreationTimestamp
	@Column(name="createdDate", updatable=false)
	private LocalDate createdDate;
	
	@UpdateTimestamp
	@Column(name="updatedDate", insertable=false)
	private LocalDate updatedDate;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserDetailsEntity user;

}
