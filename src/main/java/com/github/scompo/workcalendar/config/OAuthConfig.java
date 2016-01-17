package com.github.scompo.workcalendar.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.context.WebApplicationContext;

import com.github.scompo.workcalendar.helper.JsonLoader;
import com.github.scompo.workcalendar.services.google.GoogleTokenServices;

@Configuration
@EnableOAuth2Client
public class OAuthConfig {

	@Autowired
	private CallBackUri callbackUri;
	
	@Autowired
	private JsonLoader jsonLoader;

	@Autowired
	private OAuth2ClientContext oauth2ClientContext;

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
	public OAuth2RestOperations restTemplate() {

		return new OAuth2RestTemplate(workCalendarOAuth2ResourceDetails(), oauth2ClientContext);
	}

	@Bean
	public OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationProcessingFilter() {

		OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(
				"/oauth2Callback");

		filter.setRestTemplate(restTemplate());
		filter.setTokenServices(getTokenServices());

		return filter;
	}

	@Bean
	public RemoteTokenServices getTokenServices() {

		GoogleTokenServices defaultTokenServices = new GoogleTokenServices();
		defaultTokenServices.setCheckTokenEndpointUrl("https://www.googleapis.com/oauth2/v1/tokeninfo");

		return defaultTokenServices;
	}

	@Bean
	public TokenStore tokenStore() {

		return new InMemoryTokenStore();
	}

	@Bean
	@Scope(WebApplicationContext.SCOPE_SESSION)
	public OAuth2ProtectedResourceDetails workCalendarOAuth2ResourceDetails() {

		AuthorizationCodeResourceDetails authorizationCodeResourceDetails = new AuthorizationCodeResourceDetails();

		authorizationCodeResourceDetails.setId(jsonLoader.getByName("web.project_id"));
		authorizationCodeResourceDetails.setClientId(jsonLoader.getByName("web.client_id"));
		authorizationCodeResourceDetails.setClientSecret(jsonLoader.getByName("web.client_secret"));
		authorizationCodeResourceDetails.setAccessTokenUri(jsonLoader.getByName("web.token_uri"));
		authorizationCodeResourceDetails.setUserAuthorizationUri(jsonLoader.getByName("web.auth_uri"));
		authorizationCodeResourceDetails.setTokenName("access_token");
		authorizationCodeResourceDetails.setScope(Arrays
				.asList(new String[] { "https://www.googleapis.com/auth/calendar" }));
		authorizationCodeResourceDetails.setPreEstablishedRedirectUri(callbackUri.getCallbackUri() + "oauth2Callback");
		authorizationCodeResourceDetails.setUseCurrentUri(false);
		authorizationCodeResourceDetails.setAuthenticationScheme(AuthenticationScheme.query);
		authorizationCodeResourceDetails.setClientAuthenticationScheme(AuthenticationScheme.query);

		return authorizationCodeResourceDetails;
	}
}