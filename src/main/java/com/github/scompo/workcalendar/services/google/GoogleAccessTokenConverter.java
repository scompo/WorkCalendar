//package com.github.scompo.workcalendar.services.google;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.LinkedHashSet;
//import java.util.Map;
//import java.util.Set;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.OAuth2Request;
//import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
//import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
//
//public class GoogleAccessTokenConverter extends DefaultAccessTokenConverter {
//
//	private UserAuthenticationConverter userTokenConverter;
//
//	public GoogleAccessTokenConverter() {
//		setUserTokenConverter(new DefaultUserAuthenticationConverter());
//	}
//
//	/**
//	 * Converter for the part of the data in the token representing a user.
//	 *
//	 * @param userTokenConverter the userTokenConverter to set
//	 */
//	public final void setUserTokenConverter(UserAuthenticationConverter userTokenConverter) {
//		this.userTokenConverter = userTokenConverter;
//		super.setUserTokenConverter(userTokenConverter);
//	}
//
//	public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
//
//		Map<String, String> parameters = new HashMap<>();
//		Set<String> scope = parseScopes(map);
//		Authentication user = userTokenConverter.extractAuthentication(map);
//		String clientId = (String) map.get(CLIENT_ID);
//		parameters.put(CLIENT_ID, clientId);
//		@SuppressWarnings("unchecked")
//		Set<String> resourceIds = new LinkedHashSet<>(map.containsKey(AUD) ? (Collection<String>) map.get(AUD)
//				: Collections.<String> emptySet());
//		OAuth2Request request = new OAuth2Request(parameters, clientId, null, true, scope, resourceIds, null, null,
//				null);
//		return new OAuth2Authentication(request, user);
//	}
//
//	private Set<String> parseScopes(Map<String, ?> map) {
//		// Parsing of scopes coming back from Google are slightly different from the default implementation
//		// Instead of it being a collection it is a String where multiple scopes are separated by a space.
//		Object scopeAsObject = map.containsKey(SCOPE) ? map.get(SCOPE) : "";
//		Set<String> scope = new LinkedHashSet<>();
//		if (String.class.isAssignableFrom(scopeAsObject.getClass())) {
//			String scopeAsString = (String) scopeAsObject;
//			Collections.addAll(scope, scopeAsString.split(" "));
//		}
//		else if (Collection.class.isAssignableFrom(scopeAsObject.getClass())) {
//			@SuppressWarnings("unchecked")
//			Collection<String> scopes = (Collection<String>) scopeAsObject;
//			scope.addAll(scopes);
//		}
//		return scope;
//	}
//}