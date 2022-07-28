package ru.dan.vkapi.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Информация о друге.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendsMapper {
    private Integer count;
    private List<Integer> items;
}
