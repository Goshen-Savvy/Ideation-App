package org.ideation3.restapi.repository;

import org.ideation3.restapi.model.Ideation;
import org.springframework.data.repository.CrudRepository;

public interface IdeationRepository extends CrudRepository<Ideation, Long> {

  /**
   * Case-insensitive search gadgets where brand like '%brand%' or model like '%model%'
   * @param brand
   * @param model
   * @return Iterable<Gadget>
   */
  Iterable<Ideation> findAllByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(String brand, String model);
}