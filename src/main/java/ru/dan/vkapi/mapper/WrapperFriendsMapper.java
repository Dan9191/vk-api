package ru.dan.vkapi.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Обертка для разбора ответа на запрос друзей.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapperFriendsMapper {
    private FriendsMapper response;
}
