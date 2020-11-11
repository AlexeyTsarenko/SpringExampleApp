package com.springExampleApp.entities;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "history")
public class HistoryEntity extends BaseEntity {

    @NotNull
    @Column
    Long entityId;

    @NotNull
    @Column
    String fieldName;

    @NotNull
    @Column
    String oldValue;

    @NotNull
    @Column
    String newValue;
}
