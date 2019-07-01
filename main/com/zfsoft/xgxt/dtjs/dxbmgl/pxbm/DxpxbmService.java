package com.zfsoft.xgxt.dtjs.dxbmgl.pxbm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @功能描述：党校培训报名service
 * @author：杨珩 【1346】
 * @date：2017-11-1 下午03:14:00 
 */
public class DxpxbmService extends SuperServiceImpl<DxpxbmForm, DxpxbmDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/** 
	 * @功能描述：报名
	 * @author：杨珩 【1346】
	 * @date：2017-11-7 上午09:54:19 
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateBm(DxpxbmForm model) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
		String splc=dao.getSplc();//获取审批流程
		model.setSplc(splc);
		boolean result = dao.updateBm(model);
		if (result) {
			result = shlc.runSubmit(sqid, splc, model.getXh(), "dxbmgl_dxbmsh.do", "dxbmgl_dxpxbm.do");
		}
		return result;
	}
}
