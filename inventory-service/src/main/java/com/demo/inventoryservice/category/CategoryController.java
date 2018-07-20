package com.demo.inventoryservice.category;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping ( value = "category", produces = "application/hal+json" )
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public ResponseEntity<Resources<CategoryResource>> getCategories() {
        List<CategoryResource> collection = StreamSupport.stream(categoryRepository.findAll().spliterator(), false).map(CategoryResource::new).collect(Collectors.toList());
        final Resources<CategoryResource> resources = new Resources<>(collection);
      /*  final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));*/
        //resources.add(linkTo(methodOn(CategoryController.class).getCategories()).withSelfRel());
        return ResponseEntity.ok(resources);
    }

    @GetMapping ( "/{id}" )
    public ResponseEntity<CategoryResource> getCategory(@PathVariable ( value = "id" ) Long id) {
        final CategoryResource resource = new CategoryResource(categoryRepository.findById(id).get());
        //resource.add(linkTo(methodOn(CategoryController.class).getCategory(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

}
