package com.xr.recommend.common.enums;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-12-20 15:26:00
 * @description: 推荐场景类型
 */
public enum SceneType {

  hot(100, "热门推荐"),
  favorite(200, "猜你喜欢"),
  related(300, "关联推荐"), // 比如：相似物品推荐
  ;

  private int type;
  private String label;
  private SceneType(int type, String label){
    this.type = type;
    this.label = label;
  }

  public int getType() {
    return type;
  }

  public String getLabel() {
    return label;
  }

}
