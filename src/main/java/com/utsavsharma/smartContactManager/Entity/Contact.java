package com.utsavsharma.smartContactManager.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * User.java
 * Created by utsav on 28-Jul-2024 at 3:31:34 pm.
 */
@Entity
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Contact {
	@Id
	private String id;
	private String Name;
	private String email;
	private String phoneNumber;
	private String address;
	private String picture;
	@Column(length = 500)
	private String discription;
	private boolean favorite;
	private String webSiteStringLink;
	private String linkedInLink;
	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)

	private List<SocialLink> socialLinks;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

}
