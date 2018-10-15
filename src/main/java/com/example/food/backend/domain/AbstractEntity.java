package com.example.food.backend.domain;

import com.example.food.ui.utils.converters.LocalDateTimeConverter;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long pk;
  private int version;
  private LocalDateTime lastUpdated;
  private LocalDateTime created;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "PK", nullable = false)
  public Long getPk() {
    return pk;
  }

  public void setPk(Long pk) {
    this.pk = pk;
  }

  @Version
  @Column(name = "VERSION", nullable = false)
  public int getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  @Convert(converter = LocalDateTimeConverter.class)
  @Column(name = "LAST_UPDATED", nullable = false)
  public LocalDateTime getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(LocalDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  @Convert(converter = LocalDateTimeConverter.class)
  @Column(name = "CREATED", nullable = false)
  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  @PrePersist
  protected void onCreate() {
    lastUpdated = LocalDateTime.now();
    created = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    lastUpdated = LocalDateTime.now();
  }

  @Override
  public int hashCode() {
    if (pk == null) {
      return super.hashCode();
    }

    return 31 + pk.hashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (pk == null) {
      // New entities are only equal if the instance if the same
      return super.equals(other);
    }

    if (this == other) {
      return true;
    }
    if (!(other instanceof AbstractEntity)) {
      return false;
    }
    return pk.equals(((AbstractEntity) other).pk);
  }

}
