/**
 * @部门:学工产品事业部
 * @日期：2017-7-14 下午02:13:35 
 */  
package com.zfsoft.xgxt.base.export.business.imp.system;


import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.export.business.BusinessExtend;
import com.zfsoft.xgxt.base.export.model.ImportModel;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学号姓名一致性验证导入扩展
 * @类功能描述: 学号姓名一致性验证
 * @作者：tgj[工号:1075]
 * @时间： 2017-7-14 下午03:34:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XhXmRule extends BusinessExtend {
	private final static String _YWKZ_JEYZ_KEY = "xhxm_yzxyz";
	private final String defaultMessage = "姓名与学号不匹配！";

	public XhXmRule(String drmkdm) {
		super(drmkdm, _YWKZ_JEYZ_KEY);
	}

	@Override
	protected String check(String param, List<ImportModel> data) {
		String message = this._YZTG;
		if (StringUtils.isNull(param)) {
			return message;
		}
		Map<String, String> map = getParamExcleValue(data);

		String xh = map.get("XH");
		String xm = map.get("XSXM");
		
		message = checkXhxm(xh, xm);
		
		return message;
	}
	
	private String checkXhxm(String xh,String xm){
		String message = this._YZTG;
		StringBuffer sb=new StringBuffer();
		sb.append("select count(1) num from xsxxb where xh=? and xm=? ");
		String num = DAO.getInstance().getOneRs(sb.toString(), new String[] { xh, xm }, "num");
		if("0".equals(num)){
			message=defaultMessage;
		}
		return message;
	}
}
