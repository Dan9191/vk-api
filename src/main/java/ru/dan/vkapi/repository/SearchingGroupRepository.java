package ru.dan.vkapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dan.vkapi.entity.SearchingGroupEntity;

import java.util.List;

/**
 * Репозиторий для работы с результатом поиска групп.
 */
@Repository
public interface SearchingGroupRepository extends JpaRepository<SearchingGroupEntity, Long> {
    List<SearchingGroupEntity> findAllByOrderByTechDateResponse();
}
