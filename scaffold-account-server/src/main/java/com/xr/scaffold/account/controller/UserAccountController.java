//package com.xr.scaffold.account.controller;
//
//import com.xr.scaffold.account.common.model.UserAccountModel;
//import com.xr.scaffold.account.common.service.IUserAccountService;
//import com.xr.base.core.dto.ResultDto;
//import com.xr.base.core.enums.Cluster;
//import com.xr.base.core.page.PageData;
//import io.swagger.annotations.Api;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//
///**
//* <b>author</b>: yang.changyan@foundbyte.com
//* <b>time</b>: 2020-08-07 14:55:01 <br>
//* <b>description</b>: 用户账户表 HTTP服务 <br>
//*/
//@Api(tags = "用户账户接口")
//@RestController
//public class UserAccountController {
//
//  @Resource
//  private IUserAccountService userAccountService;
//
//  /**
//   * <p>
//   * 插入一条记录
//   * </p>
//   *
//   * @param entity 实体对象
//   * @return
//   */
//  @PostMapping("/userAccount/insert")
//  public ResultDto insert(@RequestBody UserAccountModel entity) throws Exception {
//    userAccountService.insert(entity);
//    return ResultDto.success();
//  }
//
//  /**
//   * <p>
//   * 插入（批量）
//   * </p>
//   *
//   * @param entityList 对象列表
//   * @return 插入成功的记录数
//   */
//  @PostMapping("/userAccount/insert/batch")
//  public ResultDto<Integer> insertBatch(@RequestBody List<UserAccountModel> entityList) throws Exception {
//    userAccountService.insertBatch(entityList);
//    return ResultDto.success();
//  }
//
//  /**
//   * <p>
//   * 存在则更新，否则插入
//   * </p>
//   *
//   * @param entity 实体对象
//   * @return UserAccountModel 插入/更新成功的对象
//   */
//  @PostMapping("/userAccount/upsert")
//  public ResultDto<UserAccountModel> upsert(@RequestBody UserAccountModel entity) throws Exception {
//    userAccountService.upsert(entity);
//    return ResultDto.success();
//  }
//
//  /**
//   * <p>
//   * 根据 主键ID 删除
//   * </p>
//   *
//   * @param user_id 主键ID
//   * @return 删除的行数
//   */
//  @DeleteMapping("/userAccount/delete/{user_id}")
//  public ResultDto<Integer> deleteById(@PathVariable("user_id") long user_id) throws Exception {
//    return ResultDto.successData(userAccountService.deleteById(user_id));
//  }
//
//  /**
//   * <p>
//   * 根据 condition 条件，删除记录
//   * </p>
//   *
//   * @param condition 删除条件
//   * @return Integer 删除的行数
//   */
//  @DeleteMapping("/userAccount/delete")
//  public ResultDto<Long> delete(@RequestBody Map<String, Object> condition) throws Exception {
//    return ResultDto.successData(userAccountService.delete(condition));
//  }
//
//  /**
//   * <p>
//   * 根据 model 修改数据
//   * </p>
//   *
//   * @param entity 实体对象
//   * @return 更新的行数
//   */
//  @PostMapping("/userAccount/update/model")
//  public ResultDto<Long> update(@RequestBody UserAccountModel entity) throws Exception {
//    return ResultDto.successData(userAccountService.update(entity));
//  }
//
//  /**
//   * <p>
//   * 根据map条件 修改
//   * </p>
//   *
//   * @param columnMap 更新数据/更新条件
//   * @return 更新的行数
//   */
//  @PostMapping("/userAccount/update/map")
//  public ResultDto<Long> updateByMap(@RequestBody Map<String, Object> columnMap) throws Exception {
//    return ResultDto.successData(userAccountService.update(columnMap));
//  }
//
//  /**
//   * <p>
//   * 根据 主键ID 查询
//   * </p>
//   *
//   * @param user_id 主键ID
//   * @param master 主节点 or 从节点
//   * @return UserAccountModel
//   */
//  @GetMapping("/userAccount/select/{master}/{user_id}")
//  public ResultDto<UserAccountModel> selectById(@PathVariable("user_id") long user_id, @PathVariable("master") Cluster master) throws Exception {
//    return ResultDto.successData(userAccountService.selectById(user_id, master));
//  }
//
//  /**
//   * <p>
//   * 根据 ID 批量查询
//   * </p>
//   *
//   * @param idList 主键ID列表
//   * @param master 主节点 or 从节点
//   * @return List<UserAccountModel> 列表
//   */
//  @GetMapping("/userAccount/select/{master}/batch")
//  public ResultDto<List<UserAccountModel>> selectByIds(@RequestBody Collection<? extends Serializable> idList, @PathVariable("master") Cluster master) throws Exception {
//    return ResultDto.successData(userAccountService.selectByIds(idList, master));
//  }
//
//  /**
//   * <p>
//   * 查询（根据 condition 条件）
//   * </p>
//   *
//   * @param condition 查询条件
//   * @param master 主节点 or 从节点
//   * @return List<UserAccountModel> 列表
//   */
//  @GetMapping("/userAccount/select/{master}/list")
//  public ResultDto<List<UserAccountModel>> selectList(@RequestBody Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
//    return ResultDto.successData(userAccountService.selectList(condition, master));
//  }
//
//  /**
//   * <p>
//   * 根据 condition 条件，查询一条记录
//   * </p>
//   *
//   * @param condition 查询条件
//   * @param master 主节点 or 从节点
//   * @return UserAccountModel
//   */
//  @GetMapping("/userAccount/select/{master}/one")
//  public ResultDto<UserAccountModel> selectOne(@RequestBody Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
//    return ResultDto.successData(userAccountService.selectOne(condition, master));
//  }
//
//  /**
//   * <p>
//   * 根据 condition 条件，查询总记录数
//   * </p>
//   *
//   * @param condition 查询条件
//   * @param master 主节点 or 从节点
//   * @return long 记录数
//   */
//  @GetMapping("/userAccount/select/{master}/count")
//  public ResultDto<Long> selectCount(@RequestBody Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
//    return ResultDto.successData(userAccountService.selectCount(condition, master));
//  }
//
//  /**
//   * <p>
//   * 翻页查询
//   * </p>
//   *
//   * @param page 第几页
//   * @param size 每页记录数
//   * @param condition 查询条件
//   * @param master 主节点 or 从节点
//   * @return
//   */
//  @GetMapping("/userAccount/select/{master}/page/{page}/{size}")
//  public ResultDto<PageData<UserAccountModel>> selectPage(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
//    return ResultDto.successData(userAccountService.selectPage(page, size, condition, master));
//  }
//
//}
