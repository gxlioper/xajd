/**
 * @部门:学工产品事业部
 * @日期：2013-12-31 上午09:10:02 
 */
package com.zfsoft.xgxt.base.export.business.imp.qgzx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.jfgl.QgzxJfglService;

import com.zfsoft.xgxt.base.export.business.BusinessExtend;
import com.zfsoft.xgxt.base.export.model.ImportModel;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 酬金方法导入扩展
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-12-31 上午09:10:02
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class CjffRule extends BusinessExtend {
	private final static String _YWKZ_JEYZ_KEY = "YWKZ_QGZX";

	public CjffRule(String drmkdm) {
		super(drmkdm, _YWKZ_JEYZ_KEY);
	}

	@Override
	protected String check(String param, List<ImportModel> data) {
		String message = this._YZTG;
		if (StringUtils.isNull(param)) {
			return message;
		}
		Map<String, String> map = getParamExcleValue(data);
		// XN-YRBM
		String xn = map.get("XN").substring(0,4);
		String yrdwdm = map.get("YRBM");
		String je = map.get("JE");
		StringBuffer sb = new StringBuffer();
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			xn = map.get("FFNY");
			sb.append("select syje from view_xg_qgzx_jfhbb_yf where yf=? and yrdwdm=?");
		}else{
			sb.append("select syje from view_xg_qgzx_jfhbb_nd where nd=? and yrdwdm=?");
		}
		String syje = DAO.getInstance().getOneRs(sb.toString(),
				new String[] { xn, yrdwdm }, "syje");
		boolean isKzhbje = new QgzxJfglService().isKzHbjs();
		if (isKzhbje && StringUtils.isNull(syje)) {
			message = "对应用人单位或岗位不存在，不能发放！";
		}else if(this.isSuccess(message)) {
			message=checkXsCjff(map.get("XH"), map.get("FFNY"),map.get("GWDM"));
		} else if(isKzhbje) {
			Integer syjeI = StringUtils.paseStringToInteger(syje);
			Integer jeI = StringUtils.paseStringToInteger(je);
			if (jeI > syjeI) {
				message = compileMessage(map, param);
			}
		}
		return message;
	}
	private String checkXsCjff(String xh,String ffny,String gwdm){
		String message = this._YZTG;
		StringBuffer sb=new StringBuffer();
		sb.append("select * from  XG_QGZX_CJFF where xh=? and ffny=? and gwdm=?");
		List<HashMap<String, String>> list=DAO.getInstance().getListNotOut(sb.toString(), new String[]{xh,ffny,gwdm});
		if(null!=list&&list.size()>0){
			message="此岗位学生["+xh+"]已有"+ffny+"年月的发放信息不能发放！";
		}
		return message;
	}
}
