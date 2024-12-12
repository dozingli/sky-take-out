package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api(tags = "分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/page")
    @ApiOperation("分类分页查询")
    public Result<PageResult> pageSelect(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageResult pageResult = categoryService.pageSelect(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    @ApiOperation("启动/禁用分类")
    public Result<Object> enableOrDisableCategory(@PathVariable Integer status, Long id) {
        categoryService.enableOrDisableCategory(status, id);
        return Result.success();
    }

    @PutMapping
    @ApiOperation("修改分类")
    public Result<Object> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("根据id删除分类")
    public Result<Object> deleteCategory(Long id) {
        categoryService.deleteCategory(id);
        return Result.success();
    }

    @PostMapping
    @ApiOperation("新增分类")
    public Result<Object> addCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("根据类型查询分类")
    public Result<List<Category>> listCategory(String type) {
        List<Category> list = categoryService.getCategoryList(type);
        return Result.success(list);
    }
}
