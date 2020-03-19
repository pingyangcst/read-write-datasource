/** 
 * copyright(c) 2019-2029 mamcharge.com
 */
 
package com.github.xjs.readwrite.dao;

import com.github.xjs.readwrite.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {

    /**根据id查询*/
    Product selectById(@Param("id")Long id);

    int insert(Product p);
}