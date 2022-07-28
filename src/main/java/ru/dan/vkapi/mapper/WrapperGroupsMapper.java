package ru.dan.vkapi.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Обертка для разбора ответа на запрос групп.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapperGroupsMapper {
    private GroupsMapper response;
}
