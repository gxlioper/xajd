package xgxt.utils;

import java.math.BigDecimal;

public class MathCommon {

//	传入一个double 返回截取小数点后i位的方法
	public  static Double getDouble(Double doubleTmp, int i) throws Exception {
		  BigDecimal   b  =  new BigDecimal(doubleTmp);   
		  return b.setScale(i,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
	}
}
