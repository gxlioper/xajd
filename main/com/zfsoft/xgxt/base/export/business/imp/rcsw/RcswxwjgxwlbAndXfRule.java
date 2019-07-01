/**
 * @部门:学工产品事业部
 * @日期：2015-11-27 下午02:23:57 
 */  
package com.zfsoft.xgxt.base.export.business.imp.rcsw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.export.business.BusinessExtend;
import com.zfsoft.xgxt.base.export.model.ImportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2015-11-27 下午02:23:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RcswxwjgxwlbAndXfRule extends BusinessExtend{
	private final static String _YWKZ_JEYZ_KEY ="rcsw_xwlb_xfyz";

	public RcswxwjgxwlbAndXfRule(String drmkdm) {
		super(drmkdm, _YWKZ_JEYZ_KEY);
	}

	@Override
	protected String check(String param, List<ImportModel> data) {
		String message = this._YZTG;
		if (StringUtils.isNull(param)) {
			return message;
		}
		Map<String, String> map = getParamExcleValue(data);
		// XN-
		String pdfz = map.get("FZ");
		String rcxwlbdm = map.get("RCXWLBDM");
		StringBuffer sb = new StringBuffer();
		sb.append(" select t.rcxwlbzgfz maxfz,t.rcxwlbzdfz  minfz from xg_rcsw_rcxwlbdmb t where t.rcxwlbdm = ?");
		/*获取最小最大分值*/
		HashMap<String, String> min_maxfz = DAO.getInstance().getMapNotOut(sb.toString(), new String[]{rcxwlbdm});
		String minfz = min_maxfz.get("minfz");
		String maxfz = min_maxfz.get("maxfz");
	    if(this.isSuccess(message)){
			if (StringUtils.isNull(maxfz) || StringUtils.isNull(minfz)) {
				if(StringUtils.isNotNull(maxfz) && !maxfz.trim().equals(pdfz.trim())){
					message = "分值应等于固定值"+maxfz;
				}else if(StringUtils.isNotNull(minfz) && !minfz.trim().equals(pdfz.trim())){
					message = "分值应等于固定值"+minfz;
				}				
			}else{
				double szgsminfz = Double.parseDouble(minfz);
				double szgsmaxfz = Double.parseDouble(maxfz);
				double fz = Double.parseDouble(pdfz);
				if(!(fz >= szgsminfz && fz <= szgsmaxfz)){
					message = "分值应在"+minfz+"-"+maxfz+"之间";
				}
				
			}
		} 
		return message;
	}
}
