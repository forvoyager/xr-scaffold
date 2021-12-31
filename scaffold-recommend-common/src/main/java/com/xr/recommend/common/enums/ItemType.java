package com.xr.recommend.common.enums;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-12-08 14:00:00
 * @description: 物品类型
 */
public enum ItemType {
  item(0, "物品"),
  article(1, "文章"),
  video(2, "视频"),
  audio(3, "音频"),
  image(4, "图像"),
  ;

  private int type;
  private String label;
  private ItemType(int type, String label){
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
