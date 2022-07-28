package ru.dan.vkapi.mapper;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Информация о группе.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMapper {
    private Integer id;
    private String name;
    private String screen_name;
    private Integer is_closed;
    private String type;
    private Integer is_admin;
    private Integer is_member;
    private Integer is_advertiser;
    private String photo_50;
    private String photo_100;
    private String photo_200;
}
