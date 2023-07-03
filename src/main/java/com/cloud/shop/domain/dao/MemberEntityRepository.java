package com.cloud.shop.domain.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.shop.domain.entity.MemberEntity;

public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long>{

	Optional<MemberEntity> findById(String id);

	Optional<MemberEntity> findByEmail(String email);

}
