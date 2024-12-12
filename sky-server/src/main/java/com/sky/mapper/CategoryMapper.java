package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    Page<Category> pageSelect(CategoryPageQueryDTO categoryPageQueryDTO);

    @AutoFill(OperationType.UPDATE)
    void update(Category build);

    @Delete("delete from category where id = #{id}")
    void detele(Long id);

    @AutoFill(OperationType.INSERT)
    @Insert("insert into category(type,name,sort,status,create_time,create_user,update_time,update_user) values (#{type}, #{name},#{sort},#{status},#{createTime},#{createUser},#{updateTime},#{updateUser})")
    void add(Category category);

    @Select("select * from category where type = #{type}")
    List<Category> getCategoryList(String type);
}
