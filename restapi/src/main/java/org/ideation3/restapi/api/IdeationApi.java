package org.ideation3.restapi.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.ideation3.restapi.repository.IdeationRepository;
import org.ideation3.restapi.model.Ideation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repo/ideation")
public class IdeationApi {

  @Autowired
  private IdeationRepository ideaRepository;

  @PostMapping("/register-product")
  public Ideation register(@RequestBody Ideation idea) {
    idea.setSerialNumber(RandomStringUtils.randomAlphanumeric(10).toUpperCase());
    this.ideaRepository.save(idea);
    return idea;
  }

  @GetMapping("/view/{id}")
  public Ideation view(@PathVariable("id") long id) {
    Optional<Ideation> getOpt = this.ideaRepository.findById(id);
    if(getOpt.isPresent()){
      return getOpt.get();
    }
    return null;
  }

  @PutMapping("/update/{id}")
  public Ideation update(@PathVariable("id") long id, @RequestBody Ideation idea) {
    Optional<Ideation> getOpt = this.ideaRepository.findById(id);
    if(getOpt.isPresent()){
      Ideation goToEdit = getOpt.get();
      goToEdit.setBrand(idea.idea_no());
      goToEdit.setModel(idea.topic());
      goToEdit.setPrice(idea.description());
      this.ideaRepository.save(goToEdit);
      return goToEdit;
    }
    return null;
  }

  @GetMapping("/get-all")
  public List<Ideation> getAll() {
    List<Ideation> ideas = new ArrayList<>();
    this.ideaRepository.findAll().forEach(ideas::add);
    return ideas;
  }

  @GetMapping("/search/{keyword}")
  public List<Ideation> search(@PathVariable("keyword") String keyword) {
    List<Ideation> ideas = new ArrayList<>();
    this.ideaRepository.findAllByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(keyword, keyword)
      .forEach(ideas::add);
    return ideas;
  }

  @DeleteMapping("/delete/{id}")
  public Ideation delete(@PathVariable("id") long id) {
    Optional<Ideation> getOpt = this.ideaRepository.findById(id);
    if(getOpt.isPresent()){
      Ideation goToDelete = getOpt.get();
      this.ideaRepository.delete(goToDelete);
      return goToDelete;
    }
    return null;
  }
}

