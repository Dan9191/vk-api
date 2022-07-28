package ru.dan.vkapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dan.vkapi.entity.SearchingGroupEntity;

/**
 * Репозиторий для работы с результатом поиска групп.
 */
@Repository
public interface SearchingGroupRepository extends JpaRepository<SearchingGroupEntity, Long> {
    Page<SearchingGroupEntity> findAllByOrderByTechDateResponse(Pageable pageable);
}
