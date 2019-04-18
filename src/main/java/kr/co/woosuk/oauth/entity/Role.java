package kr.co.woosuk.oauth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_seq")
	private Long roleSeq;
	
	@Column(name="user_name")
	private String username;
	
	@Column(name="role_name")
	private String roleName;

	public Long getRoleSeq() {
		return roleSeq;
	}

	public void setRoleSeq(Long roleSeq) {
		this.roleSeq = roleSeq;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
