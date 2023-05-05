package com.aster.plugin.garble.spring.sample.model;

import lombok.Data;

import javax.persistence.Id;

@Data
public class GarbleTask {

    @Id
    private Long id;

    private Long eId;

    private String tName;

    private Integer updateRecord;

    private String authCodeCol;

}
