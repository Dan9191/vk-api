package ru.dan.vkapi.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Список группы.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupsMapper {
    private Integer count;
    private List<GroupMapper> items;
}
