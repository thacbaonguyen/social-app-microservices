package com.thacbao.social.commentservice.repository;

import com.thacbao.social.commentservice.entity.EditHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditHistoryRepository extends JpaRepository<EditHistory, Long> {
}
