package com.xr.recommend.common;

/**
 * @author: yang.changyan@foundbyte.com
 * @time: 2021-08-11 17:56:00
 * @description: 用户行为类型
 */
public enum ActionType {

  // 10打头 电商相关
  expose(100, -1, -1, "曝光"),
  click(101, 3, 1, "点击"),
  search_click(12, 4, 1, "搜索点击"),
  share(103, 5, 1, "分享"),
  collect(104, 5, 1, "收藏"),
  uncollect(105, -4, -1, "取消收藏"),
  add_cart(106, 9, 1, "加购物车"),
  consume(107, 10, 1, "消费下单"),
  like(108, 8, 1, "点赞"),
  comment(109, 2, 1, "备注"),

  // 20打头 内容相关
  use(200, 6, 1, "观看视频/听音乐/阅读"),
  tip(201, 10, 1, "打赏"),
  subscribe(202, 7, 1, "订阅"),
  dislike(203, -8, -1, "点衰/踩"),
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
   * 1正向行为 -1负向行为
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
