package com.foodForHungry.entity;

/**
 * Created by mashara on 1/12/17.
 */
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "user_detail")
public class UserDetail implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name = "enabled")
	private boolean enabled;

	private String name;

	@Column(unique = true)
	private String email;

	private String passwordHash;

	@OneToMany(mappedBy="userDetail",targetEntity = Food.class)
	private Set<Food> foods;

	@OneToMany(mappedBy="userDetail",targetEntity = VerificationToken.class,fetch = FetchType.EAGER)
	private Set<VerificationToken> verificationTokens;

	public Set<VerificationToken> getVerificationTokens() {
		return verificationTokens;
	}

	public void setVerificationTokens(Set<VerificationToken> verificationTokens) {
		this.verificationTokens = verificationTokens;
	}

	public Set<Food> getFoods() {
		return foods;
	}

	public void setFoods(Set<Food> foods) {
		this.foods = foods;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	@Override
	public String toString() {
		return "UserDetail{" +
				"id=" + id +
				", enabled=" + enabled +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", passwordHash='" + passwordHash + '\'' +
				", foods=" + foods +
				'}';
	}
}