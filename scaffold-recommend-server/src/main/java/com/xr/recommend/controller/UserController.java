package com.xr.recommend.controller;

import com.xr.recommend.common.model.UserModel;
import com.xr.recommend.common.service.IUserService;
import com.xr.base.core.dto.ResultDto;
import com.xr.base.core.enums.Cluster;
import com.xr.base.core.page.PageData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
* <b>author</b>: forvoyager@outlook.com
* <b>time</b>: 2021-08-10 13:19:29 <br>
* <b>description</b>: 用户数据 HTTP服务 <br>
*/
@RestController
public class UserController {

  @Resource
  private IUserService userService;

  /**
   * <p>
   * 插入一条记录
   * </p>
   *
   * @param entity 实体对象
   * @return
   */
  @RequestMapping("/user/insert")
  public ResultDto insert(@RequestBody UserModel entity) throws Exception {
    userService.insert(entity);
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
  @RequestMapping("/user/insert/batch")
  public ResultDto<Integer> insertBatch(@RequestBody List<UserModel> entityList) throws Exception {
    userService.insertBatch(entityList);
    return ResultDto.success();
  }

  /**
   * <p>
   * 存在则更新，否则插入
   * </p>
   *
   * @param entity 实体对象
   * @return UserModel 插入/更新成功的对象
   */
  @RequestMapping("/user/upsert")
  public ResultDto<UserModel> upsert(@RequestBody UserModel entity) throws Exception {
    userService.upsert(entity);
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
  @RequestMapping("/user/delete/{id}")
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
  @RequestMapping("/user/delete")
  public ResultDto<Long> delete(@RequestBody Map<String, Object> condition) throws Exception {
    return ResultDto.successData(userService.delete(condition));
  }

  /**
   * <p>
   * 根据 model 修改数据
   * </p>
   *
   * @param entity 实体对象
   * @return 更新的行数
   */
  @RequestMapping("/user/update/model")
  public ResultDto<Long> update(@RequestBody UserModel entity) throws Exception {
    return ResultDto.successData(userService.update(entity));
  }

  /**
   * <p>
   * 根据map条件 修改
   * </p>
   *
   * @param columnMap 更新数据/更新条件
   * @return 更新的行数
   */
  @RequestMapping("/user/update/map")
  public ResultDto<Long> updateByMap(@RequestBody Map<String, Object> columnMap) throws Exception {
    return ResultDto.successData(userService.update(columnMap));
  }

  /**
   * <p>
   * 根据 主键ID 查询
   * </p>
   *
   * @param id 主键ID
   * @param master 主节点 or 从节点
   * @return UserModel
   */
  @RequestMapping("/user/select/{master}/{id}")
  public ResultDto<UserModel> selectById(@PathVariable("id") long id, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(userService.selectById(id, master));
  }

  /**
   * <p>
   * 根据 ID 批量查询
   * </p>
   *
   * @param idList 主键ID列表
   * @param master 主节点 or 从节点
   * @return List<UserModel> 列表
   */
  @RequestMapping("/user/select/{master}/batch")
  public ResultDto<List<UserModel>> selectByIds(@RequestBody Collection<? extends Serializable> idList, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(userService.selectByIds(idList, master));
  }

  /**
   * <p>
   * 查询（根据 condition 条件）
   * </p>
   *
   * @param condition 查询条件
   * @param master 主节点 or 从节点
   * @return List<UserModel> 列表
   */
  @RequestMapping("/user/select/{master}/list")
  public ResultDto<List<UserModel>> selectList(@RequestBody Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(userService.selectList(condition, master));
  }

  /**
   * <p>
   * 根据 condition 条件，查询一条记录
   * </p>
   *
   * @param condition 查询条件
   * @param master 主节点 or 从节点
   * @return UserModel
   */
  @RequestMapping("/user/select/{master}/one")
  public ResultDto<UserModel> selectOne(@RequestBody Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(userService.selectOne(condition, master));
  }

  /**
   * <p>
   * 根据 condition 条件，查询总记录数
   * </p>
   *
   * @param condition 查询条件
   * @param master 主节点 or 从节点
   * @return long 记录数
   */
  @RequestMapping("/user/select/{master}/count")
  public ResultDto<Long> selectCount(@RequestBody(required = false) Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(userService.selectCount(condition, master));
  }

  /**
   * <p>
   * 翻页查询
   * </p>
   *
   * @param page 第几页
   * @param size 每页记录数
   * @param condition 查询条件
   * @param master 主节点 or 从节点
   * @return
   */
  @RequestMapping("/user/select/{master}/page/{page}/{size}")
  public ResultDto<PageData<UserModel>> selectPage(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody(required = false) Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(userService.selectPage(page, size, condition, master));
  }

}
