package ru.dan.vkapi.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dan.vkapi.model.SearchingGroupDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Детали поиска.
 */
@Entity
@ApiModel(description = "Детали поиска")
@Data
@NoArgsConstructor
@Table(name = "search_result")
public class SearchingGroupEntity {

    /**
     * Идентификатор записи в табилце.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Идентификатор записи в табилце.")
    @Column(name = "id")
    private Long id;

    /**
     * Параметры поиска.
     */
    @ApiModelProperty("Параметры поиска.")
    @Column(name = "param")
    private String param;

    /**
     * Найденные группы.
     */
    @ApiModelProperty("Найденные группы.")
    @Column(name = "result")
    private String result;

    /**
     * Время поиска.
     */
    @ApiModelProperty("Время поиска.")
    @Column(name = "tech_date_response")
    private LocalDateTime techDateResponse;

    public SearchingGroupEntity(SearchingGroupDetails searchingGroup) {
        this.param = searchingGroup.getParam();
        this.result = searchingGroup.getResult();
        this.techDateResponse = searchingGroup.getTechDateResponse();
    }
}
