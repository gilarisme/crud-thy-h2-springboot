package net.crunchdroid.entity;


import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class BaseEntity {

    @CreatedBy
    @Column(name = "created_by")
    private String insertBy;

    @CreatedDate
    @Column(name = "creation_time")
    private Timestamp insertDate;

    @LastModifiedBy
    @Column(name = "modified_by")
    private String updateBy;

    @LastModifiedDate
    @Column(name = "modification_time")
    private Timestamp updateDate;
}
