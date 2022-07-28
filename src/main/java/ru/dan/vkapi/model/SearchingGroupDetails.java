package ru.dan.vkapi.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Модель для вывода информации о результате поиска из БД.
 */
@Data
public class SearchingGroupDetails {

    /**
     * Параметры поиска.
     */
    private String param;

    /**
     * Результат поиска.
     */
    private String result;

    /**
     * Время поиска.
     */
    private LocalDateTime techDateResponse;

    public SearchingGroupDetails(SearchingGroupBuilder searchingGroupBuilder) {
        this.param = searchingGroupBuilder.getParam();
        this.result = searchingGroupBuilder.getResult();
        this.techDateResponse = LocalDateTime.now();
    }

    /**
     * Билдер для SearchingGroup.
     */
    @Data
    public static class SearchingGroupBuilder {
        private String param;
        private String result;

        public SearchingGroupBuilder() {
            super();
        }

        public SearchingGroupBuilder param(String param) {
            this.param = param;
            return this;
        }

        public SearchingGroupBuilder result(String result) {
            this.result = result;
            return this;
        }

        public SearchingGroupDetails build() {
            return new SearchingGroupDetails(this);
        }
    }
}
