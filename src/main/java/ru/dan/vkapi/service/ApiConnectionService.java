package ru.dan.vkapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.dan.vkapi.mapper.WrapperFriendsMapper;
import ru.dan.vkapi.mapper.WrapperGroupsMapper;
import ru.dan.vkapi.model.Group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для обращения к API vk.
 */
@Service
public class ApiConnectionService {

    /**
     * Ключ доступа приложения.
     */
    private final String TOKEN;

    /**
     * Id пользователя.
     */
    private final Integer USER_ID;

    /**
     * Запрос для получения списка групп.
     */
    private final String GET_GROUP = "https://api.vk.com/method/groups.get?access_token=%s&user_id=%s&extended=1&v=5.131";

    /**
     * Запрос для получения списка друзей.
     */
    private final String GET_FRIENDS = "https://api.vk.com/method/friends.get?access_token=%s&user_id=%s&extended=1&v=5.131";

    /**
     * Json маппер.
     */
    private final ObjectMapper objectMapper;

    public ApiConnectionService(@Value("${access_token}") String TOKEN,
                                @Value("${user_id}") Integer USER_ID,
                                ObjectMapper objectMapper) {
        this.TOKEN = TOKEN;
        this.USER_ID = USER_ID;
        this.objectMapper = objectMapper;
    }

    /**
     * Список групп пользователя, найденных по подстроке.
     *
     * @param substring Подстрока, по которой происходит поиск.
     * @return Список групп, удовлетворябщих условию.
     */
    public List<Group> searchingGroups(String substring) {
        return getSearchingGroups(Collections.singletonList(USER_ID), substring);
    }

    /**
     * Список групп пользователя и его друзей, найденных по подстроке.
     *
     * @param substring Подстрока, по которой происходит поиск.
     * @return Список групп, удовлетворябщих условию.
     */
    public List<Group> searchingGroupsWithFriends(String substring) {
        List<Integer> ids;
        try {
            ids = new ArrayList<>(List.of(USER_ID));
            HttpResponse<JsonNode> friendsResponse = Unirest.post(String.format(GET_FRIENDS, TOKEN, USER_ID)).asJson();
            String friends = String.valueOf(friendsResponse.getBody());
            WrapperFriendsMapper wrapperFriends = objectMapper.readValue(friends, WrapperFriendsMapper.class);
            ids.addAll(wrapperFriends.getResponse().getItems());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return getSearchingGroups(ids, substring);
    }

    /**
     * Список групп, найденных по подстроке.
     *
     * @param peopleIds Список id пользователей.
     * @param substring Подстрока, по которой происходит поиск.
     * @return Список групп.
     */
    private List<Group> getSearchingGroups(List<Integer> peopleIds, String substring) {
        List<Group> groupList = new ArrayList<>();
        peopleIds.forEach(id -> {
            try {
                HttpResponse<JsonNode> groupsResponse = Unirest.post(String.format(GET_GROUP, TOKEN, id)).asJson();
                String groups = String.valueOf(groupsResponse.getBody());
                WrapperGroupsMapper wrapperGroups = objectMapper.readValue(groups, WrapperGroupsMapper.class);
                wrapperGroups.getResponse().getItems().forEach(groupMapper ->
                        groupList.add(new Group(groupMapper.getId(), groupMapper.getName())));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        return groupList.stream()
                .filter(group -> group.getName().toLowerCase().contains(substring.toLowerCase()))
                .collect(Collectors.toList());
    }
}
