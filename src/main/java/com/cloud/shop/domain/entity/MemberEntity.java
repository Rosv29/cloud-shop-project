package com.cloud.shop.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import com.cloud.shop.security.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
@Entity
public class MemberEntity extends BaseDateEntity{

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long no;
	
	@Column(unique = true)
	private String id;
	
	@Column(columnDefinition = "varchar(255) collate utf8mb4_bin ")
	private String password;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String phone;
	
	@ColumnDefault(value = "false")
	private boolean socialAccount;
	
	@Builder.Default
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "memberRole")
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<MemberRole> roles=new HashSet<>();
	
	public MemberEntity addRole(MemberRole role) {
		roles.add(role);
		return this;
	}
}
