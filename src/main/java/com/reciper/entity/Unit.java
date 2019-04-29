package com.reciper.entity;

import com.reciper.entity.base.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "units")
@Getter
@Setter
public class Unit extends AbstractEntity {
    @NotNull
    @Size(max = 120)
    private String name;
}
