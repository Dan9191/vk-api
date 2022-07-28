package ru.dan.vkapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dan.vkapi.service.SearchingGroupService;

@RestController
@RequestMapping("/test")
@Api("Контроллер для поиска по группам.")
public class SearchingGroupController {

    private SearchingGroupService searchingGroupService;

    public SearchingGroupController(SearchingGroupService searchingGroupService) {
        this.searchingGroupService = searchingGroupService;
    }

    /**
     * Выдает результат поиска по подстроке среди групп пользователя, результат сохраняется в БД.
     *
     * @param substring  Подстрока поиска.
     * @return Результат поиска.
     */
    @PostMapping("/my_groups_search")
    @ApiOperation("Поиска по подстроке среди групп пользователя.")
    public ResponseEntity searchingGroups(@ApiParam("Подстрока для поиска")
                                          @RequestParam(value = "substring") String substring) {
        System.out.println(substring);
        try {
            return ResponseEntity.ok().body(searchingGroupService.searchGroups(substring));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Выдает результат поиска по подстроке среди групп пользователя и его друзей.
     *
     * @param substring  Подстрока поиска.
     * @return Результат поиска.
     */
    @PostMapping("/groups_search")
    @ApiOperation("Поиска по подстроке среди групп пользователя и его друзей.")
    public ResponseEntity searchingGroupsWithFriends(@ApiParam("Подстрока для поиска")
                                                     @RequestParam(value = "substring") String substring) {
        System.out.println(substring);
        try {
            return ResponseEntity.ok().body(searchingGroupService.searchGroupsWithFriends(substring));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Выдает информацию о попытках поиска групп.
     */
    @GetMapping("/get_upload_details")
    @ApiOperation("Вывод информации о попытках поиска групп.")
    public ResponseEntity getSearchResult(@PageableDefault(size = 3) Pageable pageable) {
        try {
            return ResponseEntity.ok().body(searchingGroupService.findSearchingGroups(pageable));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
