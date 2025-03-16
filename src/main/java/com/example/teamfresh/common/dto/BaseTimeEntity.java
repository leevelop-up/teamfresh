package com.example.teamfresh.common.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseTimeEntity {
    @CreatedDate
    @Column(name="create_date_time", updatable = false)
    private LocalDateTime createDateTime;

    @LastModifiedDate
    @Column(name="update_date_time",updatable = true)
    private LocalDateTime updateDateTime;
}
