package com.xr.recommend.common.enums;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-08-11 17:56:00
 * @description: 用户行为类型
 */
public enum ActionType {

  // 10打头，通用
  expose(100, -1, -1, "曝光"),
  click(101, 3, 1, "点击"),
  search_click(12, 4, 1, "搜索点击"),
  share(103, 5, 1, "分享"),
  collect(104, 5, 1, "收藏"),
  uncollect(105, -4, -1, "取消收藏"),
  like(106, 8, 1, "点赞"),
  comment(107, 2, 1, "备注"),
  consume(108, 10, 1, "打赏/下单"),

  // 20打头 电商相关
  add_cart(200, 9, 1, "加购物车"),

  // 30打头 内容相关
  use(300, 6, 1, "观看视频/听音乐/阅读"),
  subscribe(301, 7, 1, "订阅"),
  dislike(302, -8, -1, "点衰/踩"),
  ;

  /**
   * 编码
   */
  private int code;
  /**
   * 分值，负向行为为负值
   */
  private int score;
  /**
   * -1负向行为 1正向行为
   */
  private int positive;
  /**
   * 备注
   */
  private String remark;

  public int getCode() {
    return code;
  }

  public int getScore() {
    return score;
  }

  public int getPositive() {
    return positive;
  }

  public String getRemark() {
    return remark;
  }

  private ActionType(int code, int score, String remark){
    this(code, score, 1, remark);
  }

  private ActionType(int code, int score, int positive, String remark){
    this.code = code;
    this.score = score;
    this.positive = positive;
    this.remark = remark;
  }

  public static ActionType parseActionType(String type){
    if(type != null){
      ActionType[] types = values();
      for(ActionType t : types){
        if(t.name().equals(type)){
          return t;
        }
      }
    }

    throw new IllegalArgumentException("不存在的行为类型："+type);
  }
}
