package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    Page<Category> pageSelect(CategoryPageQueryDTO categoryPageQueryDTO);

    void update(Category build);

    @Delete("delete from category where id = #{id}")
    void detele(Long id);

    @Insert("insert into category(type,name,sort,status,create_time,create_user,update_time,update_user) values (#{type}, #{name},#{sort},#{status},#{createTime},#{createUser},#{updateTime},#{updateUser})")
    void add(Category category);
}
