package com.notion.notioncore.repository;

import com.notion.notioncore.domain.Notion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotionRepository extends JpaRepository<Notion, Long> {
}
