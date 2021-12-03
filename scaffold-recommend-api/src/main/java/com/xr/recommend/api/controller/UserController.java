package com.xr.recommend.api.controller;

import com.xr.base.core.dto.ResultDto;
import com.xr.base.core.enums.Cluster;
import com.xr.base.core.page.PageData;
import com.xr.recommend.common.model.UserModel;
import com.xr.recommend.common.service.IUserService;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
* <b>author</b>: forvoyager@outlook.com
* <b>time</b>: 2021-08-10 14:31:18 <br>
* <b>description</b>: 用户数据 HTTP服务 <br>
*/
@Api(tags = "用户数据相关操作")
@RestController
@RequestMapping("/user")
public class UserController {

  @DubboReference
  private IUserService userService;

  /**
   * <p>
   * 存在则更新，否则插入
   * </p>
   *
   * @param entity 实体对象
   * @return UserModel 插入/更新成功的对象
   */
  @PostMapping("/upsert")
  public ResultDto<UserModel> upsert(@RequestBody UserModel entity) throws Exception {
    userService.upsert(entity);
    return ResultDto.success();
  }

  /**
   * <p>
   * 插入（批量）
   * </p>
   *
   * @param entityList 对象列表
   * @return 插入成功的记录数
   */
  @PostMapping("/insert/batch")
  public ResultDto<Integer> insertBatch(@RequestBody List<UserModel> entityList) throws Exception {
    userService.insertBatch(entityList);
    return ResultDto.success();
  }

  /**
   * <p>
   * 根据 主键ID 删除
   * </p>
   *
   * @param id 主键ID
   * @return 删除的行数
   */
  @DeleteMapping("/delete/{id}")
  public ResultDto<Integer> deleteById(@PathVariable("id") long id) throws Exception {
    return ResultDto.successData(userService.deleteById(id));
  }

  /**
   * <p>
   * 根据 condition 条件，删除记录
   * </p>
   *
   * @param condition 删除条件
   * @return Integer 删除的行数
   */
  @DeleteMapping("/delete")
  public ResultDto<Long> delete(@RequestBody Map<String, Object> condition) throws Exception {
    return ResultDto.successData(userService.delete(condition));
  }

  /**
   * <p>
   * 根据 主键ID 查询
   * </p>
   *
   * @param id 主键ID
   * @param cluster 主节点 or 从节点
   * @return UserModel
   */
  @GetMapping("/select/{cluster}/{id}")
  public ResultDto<UserModel> selectById(@PathVariable("id") long id, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(userService.selectById(id, cluster));
  }

  /**
   * <p>
   * 根据 ID 批量查询
   * </p>
   *
   * @param idList 主键ID列表
   * @param cluster 主节点 or 从节点
   * @return List<UserModel> 列表
   */
  @GetMapping("/select/{cluster}/batch")
  public ResultDto<List<UserModel>> selectByIds(@RequestBody Collection<? extends Serializable> idList, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(userService.selectByIds(idList, cluster));
  }

  /**
   * <p>
   * 查询（根据 condition 条件）
   * </p>
   *
   * @param condition 查询条件
   * @param cluster 主节点 or 从节点
   * @return List<UserModel> 列表
   */
  @GetMapping("/select/{cluster}/list")
  public ResultDto<List<UserModel>> selectList(@RequestBody Map<String, Object> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(userService.selectList(condition, cluster));
  }

  /**
   * <p>
   * 根据 condition 条件，查询一条记录
   * </p>
   *
   * @param condition 查询条件
   * @param cluster 主节点 or 从节点
   * @return UserModel
   */
  @GetMapping("/select/{cluster}/one")
  public ResultDto<UserModel> selectOne(@RequestBody Map<String, Object> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(userService.selectOne(condition, cluster));
  }

  /**
   * <p>
   * 根据 condition 条件，查询总记录数
   * </p>
   *
   * @param condition 查询条件
   * @param cluster 主节点 or 从节点
   * @return long 记录数
   */
  @GetMapping("/select/{cluster}/count")
  public ResultDto<Long> selectCount(@RequestBody(required = false) Map<String, Object> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(userService.selectCount(condition, cluster));
  }

  /**
   * <p>
   * 翻页查询
   * </p>
   *
   * @param page 第几页
   * @param size 每页记录数
   * @param condition 查询条件
   * @param cluster 主节点 or 从节点
   * @return
   */
  @GetMapping("/select/{cluster}/page/{page}/{size}")
  public ResultDto<PageData<UserModel>> selectPage(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody(required = false) Map<String, Object> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(userService.selectPage(page, size, condition, cluster));
  }

}
