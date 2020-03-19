/** 
 * copyright(c) 2019-2029 mamcharge.com
 */
 
package com.github.xjs.readwrite.service;


import com.github.xjs.readwrite.config.Master;
import com.github.xjs.readwrite.dao.ProductMapper;
import com.github.xjs.readwrite.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public Product getById(Long id){
        return productMapper.selectById(id);
    }

    @Master
    public Product query(Long id){
        return productMapper.selectById(id);
    }

    public int insert(Product p){
        return productMapper.insert(p);
    }

}
