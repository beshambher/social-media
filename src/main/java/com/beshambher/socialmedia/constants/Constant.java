package com.beshambher.socialmedia.constants;

import lombok.AllArgsConstructor;

public class Constant {

	@AllArgsConstructor
	public static enum Client {

		GITHUB("github"), GOOGLE("google");

		private final String clientId;

		@Override
		public String toString() {
			return clientId;
		}
	}

	@AllArgsConstructor
	public static enum Role {

		ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

		private final String role;

		@Override
		public String toString() {
			return role;
		}
	}

}