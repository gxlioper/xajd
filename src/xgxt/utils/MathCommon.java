package xgxt.utils;

import java.math.BigDecimal;

public class MathCommon {

//	����һ��double ���ؽ�ȡС�����iλ�ķ���
	public  static Double getDouble(Double doubleTmp, int i) throws Exception {
		  BigDecimal   b  =  new BigDecimal(doubleTmp);   
		  return b.setScale(i,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
	}
}
