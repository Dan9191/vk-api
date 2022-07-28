package ru.dan.vkapi.service;

import org.springframework.stereotype.Service;
import ru.dan.vkapi.entity.SearchingGroupEntity;
import ru.dan.vkapi.model.Group;
import ru.dan.vkapi.model.SearchingGroupDetails;
import ru.dan.vkapi.repository.SearchingGroupRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для обработки результатов поиска
 */
@Service
public class SearchingGroupService {

    /**
     * Репозиторий для работы с результатом поиска групп.
     */
    private final SearchingGroupRepository groupRepository;

    /**
     * Сервис для обращения к API vk.
     */
    private final ApiConnectionService apiConnectionService;

    public SearchingGroupService(SearchingGroupRepository groupRepository, ApiConnectionService apiConnectionService) {
        this.groupRepository = groupRepository;
        this.apiConnectionService = apiConnectionService;
    }

    /**
     * Поиск групп по подстроке.
     *
     * @param substring Подстрока, по которой происходит поиск.
     * @return Список групп.
     */
    @Transactional
    public List<Group> searchGroups(String substring) {
        List<Group> groups = apiConnectionService.searchingGroups(substring);
        SearchingGroupDetails searchingGroupDetails = new SearchingGroupDetails.SearchingGroupBuilder()
                .param(substring)
                .result(groups.stream().map(Group::getName).collect(Collectors.joining("; ")))
                .build();
        groupRepository.save(new SearchingGroupEntity(searchingGroupDetails));
        return groups;
    }

    /**
     * Поиск групп по подстроке.
     *
     * @param substring Подстрока, по которой происходит поиск.
     * @return Список групп.
     */
    public List<Group> searchGroupsWithFriends(String substring) {
        return apiConnectionService.searchingGroupsWithFriends(substring);
    }

    /**
     * Вывод деталей результата поисков.
     */
    @Transactional
    public List<SearchingGroupDetails> findSearchingGroups() {
        return groupRepository.findAllByOrderByTechDateResponse().stream()
                .map(entity -> new SearchingGroupDetails.SearchingGroupBuilder()
                        .param(entity.getParam())
                        .result(entity.getResult())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
