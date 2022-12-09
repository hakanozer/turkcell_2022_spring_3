package com.works.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class Base {

    @ApiModelProperty(hidden = true)
    @CreatedBy
    private String createdBy;

    @ApiModelProperty(hidden = true)
    @CreatedDate
    private Long createdDate;

    @ApiModelProperty(hidden = true)
    @LastModifiedBy
    private String lastModifiedBy;

    @ApiModelProperty(hidden = true)
    @LastModifiedDate
    private Long lastModifiedDate;

}
