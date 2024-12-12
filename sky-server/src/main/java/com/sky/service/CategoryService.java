package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;

public interface CategoryService {
    PageResult pageSelect(CategoryPageQueryDTO categoryPageQueryDTO);

    void enableOrDisableCategory(Integer status, Long id);

    void updateCategory(CategoryDTO categoryDTO);

    void deleteCategory(Long id);
}
