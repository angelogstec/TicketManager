package com.party.repositories;

import com.party.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    boolean existsByLoginLikeIgnoreCase(@NonNull String login);
}
