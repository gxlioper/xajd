package xsgzgl.dtjs.comm;

import xgxt.action.Base;
import xgxt.comm.CommService;

public class DtjsCommService extends CommService{
	/**
	 * 获取topTr
	 * @param mk
	 * @return
	 */
	public String[] getTopTr(String mk){
		String[] topTr = null;
	
		if("dtxxgl".equalsIgnoreCase(mk)){
			topTr = new String[]{"学号","姓名&nbsp;&nbsp;&nbsp;","性别","政治面貌",Base.YXPZXY_KEY,"专业","班级","当前阶段名称","开始时间"};
		}else if("dtxxcx".equalsIgnoreCase(mk)){
			topTr = new String[]{"学号","姓名","性别","政治面貌",Base.YXPZXY_KEY,"专业","班级","阶段名称","开始时间","是否当前阶段"};
		}
		
		return topTr;
	}
}
