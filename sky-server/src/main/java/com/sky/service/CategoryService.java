package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {
    PageResult pageSelect(CategoryPageQueryDTO categoryPageQueryDTO);

    void enableOrDisableCategory(Integer status, Long id);

    void updateCategory(CategoryDTO categoryDTO);

    void deleteCategory(Long id);

    void addCategory(CategoryDTO categoryDTO);

    List<Category> getCategoryList(String type);
}
