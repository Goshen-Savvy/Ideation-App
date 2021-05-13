package org.ideation3.restapi.model;
import javax.persistence.Entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Ideation {
  private Long id;
  private String idea_no;
  private String topic;
  private String description;
}