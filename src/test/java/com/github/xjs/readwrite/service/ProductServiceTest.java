/** 
 * copyright(c) 2019-2029 mamcharge.com
 */
 
package com.github.xjs.readwrite.service;

import com.github.xjs.readwrite.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testGetById() {
        Product p = productService.getById(1L);
        System.out.println(p);
    }

    @Test
    public void testQuery() {
        Product p = productService.query(1L);
        System.out.println(p);
    }

    @Test
    public void testInsert() {
        Product p = new Product();
        p.setProdName("name_1");
        p.setProdTitle("title_1");
        p.setProdPrice(101);
        p.setProdDetail("detail_1");
        int ret = productService.insert(p);
        System.out.println(p.getId());
    }


}