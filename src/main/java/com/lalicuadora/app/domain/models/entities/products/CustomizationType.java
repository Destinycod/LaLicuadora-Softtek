package com.lalicuadora.app.domain.models.entities.products;

import javax.persistence.Table;

@Table(name = "customizationType")
public enum CustomizationType{

    COLOR_IMAGE,
    BLACK_AND_WHITE_IMAGE,
    TEXT,
    EMOJI

}
