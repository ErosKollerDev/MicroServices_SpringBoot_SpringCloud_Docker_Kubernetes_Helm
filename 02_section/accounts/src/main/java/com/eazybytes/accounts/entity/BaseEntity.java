package com.eazybytes.accounts.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @CreationTimestamp
    @Column(updatable = false)
    protected LocalDateTime createdAt;

    @Column(updatable = true, nullable = true)
    protected String createdBy;

    @UpdateTimestamp
    @Column(nullable = false, insertable = false)
    protected LocalDateTime updatedAt;

    protected String updatedBy;

}
