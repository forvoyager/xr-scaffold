package com.recommend.test;

import com.xr.base.core.FixedLengthList;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author: forvoyager@outlook.com
 * @time: 2022-04-07 14:12:00
 * @description:
 */
public class TempTest {

  @Test
  public void testFixedList(){
    FixedLengthList fll = new FixedLengthList(10);
    for(int i=0; i<20; i++){
      fll.add(i+"-");
    }
    System.out.println(Arrays.toString(fll.toArray()));

  }

}
