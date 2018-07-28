package com.demo.inventoryservice.category;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.ws.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RefreshScope
@RestController
@RequestMapping ( value = "category", produces = "application/hal+json" )
public class CategoryController {

    private final CategoryRepository categoryRepository;

    // for config refresh testing
    @Value("${custom.property}")
    String property;

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

    // Spring config refresh test
    @GetMapping ( "/refresh-test" )
    public ResponseEntity<String> refreshTest(){
        return ResponseEntity.ok(property);
    }

}
