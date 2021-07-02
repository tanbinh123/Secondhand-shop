package com.example.shop.controller;

import com.example.shop.dao.CategoryDao;
import com.example.shop.entity.Category;
import com.example.shop.entity.CategoryQuery;
import com.example.shop.entity.Good;
import com.example.shop.entity.TableData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/category/")
public class CategoryController {
    @Resource
    private CategoryDao categoryDao;


    @GetMapping("query")
    public TableData query(CategoryQuery query){
        query.setPage((query.getPage() - 1) * query.getLimit());
        TableData tableData = new TableData();
        tableData.setData(categoryDao.query(query));
        tableData.setCount(categoryDao.queryCount(query));
        return tableData;
    }
    // 后台删除
    @GetMapping("delList")
    public boolean delList(@RequestParam(name = "idList[]") List<Integer> idList){
        categoryDao.delList(idList);
        return true;
    }
    @GetMapping("getById")
    public Category getById(int id){
        return categoryDao.getById(id);
    }
    @PostMapping("update")
    public boolean userUpdate(Category category){
        categoryDao.update(category);
        return true;
    }
}
