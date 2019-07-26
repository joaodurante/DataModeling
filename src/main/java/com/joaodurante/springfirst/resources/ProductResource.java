package com.joaodurante.springfirst.resources;

import com.joaodurante.springfirst.DTO.ProductDTO;
import com.joaodurante.springfirst.domain.Product;
import com.joaodurante.springfirst.resources.utils.UrlUtils;
import com.joaodurante.springfirst.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> find(@PathVariable Integer id){
        Product obj = this.productService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categoriesIds,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "24") Integer size,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ){
        List<Integer> ids = UrlUtils.decodeIntegerList(categoriesIds);
        String decodedName = UrlUtils.decodeParam(name);

        Page<Product> list = productService.search(decodedName, ids, page, size, direction, orderBy);
        Page<ProductDTO> listDTO = list.map(obj -> new ProductDTO(obj));

        return ResponseEntity.ok().body(listDTO);
    }
}







