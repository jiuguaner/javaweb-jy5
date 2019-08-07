package com.itdr.service;

import com.itdr.common.ResponseCode;
import com.itdr.dao.ProductDao;
import com.itdr.pojo.Product;

import java.util.List;

public class ProductService {

    private ProductDao pd = new ProductDao();
    public ResponseCode getAll() {
        ResponseCode rs = null;
        List<Product> li = pd.selectAll();
        rs = ResponseCode.successRS(li);
        return rs;

    }
}
