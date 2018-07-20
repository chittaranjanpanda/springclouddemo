package com.demo.inventoryservice.category;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Data
public class CategoryResource extends ResourceSupport {

  private final Category category;

  public CategoryResource(final Category category){
    this.category = category;
    final Long id = category.getId();
    add(linkTo(CategoryController.class).withRel("category"));
    add(linkTo(methodOn(CategoryController.class).getCategory(id)).withRel("self"));
  }

}
