package com.itdr.dao;

import com.itdr.pojo.Product;
import com.itdr.utils.PoolUTil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {
    public List<Product> selectAll() {
        QueryRunner qr = new QueryRunner(PoolUTil.getCom());
        String sql = "select * from product";
        List<Product> li = null;
        try {
            li = qr.query(sql,new BeanListHandler<Product>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return li;
    }
}
