package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    Page<Category> pageSelect(CategoryPageQueryDTO categoryPageQueryDTO);

    void update(Category build);

    @Delete("delete from category where id = #{id}")
    void detele(Long id);
}
