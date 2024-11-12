package com.utsavsharma.smartContactManager.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * SocialLink.java
 * Created by utsav on 28-Jul-2024 at 4:08:26 pm.
 */
@Entity
@Data

public class SocialLink {
	@Id
	private String id;
	private String link;
	private String title;
	@ManyToOne
	private Contact contact;

	public SocialLink() {
		super();
	}

}
