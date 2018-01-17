package com.foodForHungry.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mashara on 6/12/17.
 */
@Entity
@Table(name = "verification_token")
public class VerificationToken implements Serializable{
	private static final int EXPIRATION = 60 * 24;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String token;

	@ManyToOne
	@JoinColumn
	private UserDetail userDetail;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	@Override
	public String toString() {
		return "VerificationToken{" +
				"id=" + id +
				", token='" + token + '\'' +
				", userDetail=" + userDetail +
				'}';
	}

	//	private Date expiryDate;
//
//	private Date calculateExpiryDate(int expiryTimeInMinutes) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Timestamp(cal.getTime().getTime()));
//		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
//		return new Date(cal.getTime().getTime());
//	}

	// standard constructors, getters and setters
}
