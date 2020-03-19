/** 
 * copyright(c) 2019-2029 mamcharge.com
 */
 
package com.github.xjs.readwrite.domain;

import lombok.Data;

@Data
public class Product {
    /**商品ID*/
    private Long id;

    /**商品名称*/
    private String prodName;

    /**商品标题*/
    private String prodTitle;

    /**商品加价格*/
    private Integer prodPrice;

    /**商品详情*/
    private String prodDetail;

}
