package kr.co.woosuk.oauth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="custom_client_details")
public class CustomClientDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="client_seq")
	private Long clientSeq;
	
	@Column(name="client_id")
	private String clientId;
	
	@Column(name="access_token_validity")
	private int accessTokenValidity;
	
	@Column(name="additional_information")
	private String additionalInformation;
	
	@Column(name="authorities")
	private String authorities;
	
	@Column(name="authorized_grant_types")
	private String authorizedGrantTypes;
	
	@Column(name="autoapprove")
	private String autoapprove;

	@Column(name="client_secret")
	private String clientSecret;
	
	@Column(name="refresh_token_validity")
	private int refreshTokenValidity;
	
	@Column(name="resource_ids")
	private String resourceIds;
	
	@Column(name="scope")
	private String scope;
	
	@Column(name="web_server_redirect_uri")
	private String webServerRedirectUri;

	public Long getClientSeq() {
		return clientSeq;
	}

	public void setClientSeq(Long clientSeq) {
		this.clientSeq = clientSeq;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public int getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(int accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getAutoapprove() {
		return autoapprove;
	}

	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public int getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(int refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}

	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}
	
}
