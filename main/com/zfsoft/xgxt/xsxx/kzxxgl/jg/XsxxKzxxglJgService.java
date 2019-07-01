/**
 * @部门:学工产品事业部
 * @日期：2015-6-19 上午09:35:23 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl.jg;

import org.apache.commons.lang.StringUtils;

import xgxt.form.User;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.zdzm.comm.ZdzmComm;
import com.zfsoft.xgxt.rcsw.zdzm.jggl.ZdzmJgForm;
import com.zfsoft.xgxt.rcsw.zdzm.jggl.ZdzmJgService;
import com.zfsoft.xgxt.rcsw.zdzm.sh.ZdzmShForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-19 上午09:35:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxxKzxxglJgService extends
		SuperServiceImpl<XsxxKzxxglJgForm, XsxxKzxxglJgDAO> {
	
	public boolean deleteJgBySqid(String sqid)throws Exception{
		if(StringUtils.isBlank(sqid)){
			return true;
		}
		return dao.deleteBySqid(sqid);
	}
	
	public XsxxKzxxglJgForm getModelBySqid(String sqid) throws Exception{
		if(StringUtils.isBlank(sqid)){
			return null;
		}
		return dao.getModelBySqid(sqid);
	}
	
	public XsxxKzxxglJgForm getModelByXh(String xh) throws Exception{
		if(StringUtils.isBlank(xh)){
			return null;
		}
		return dao.getModelByXh(xh);
	}
	
	public boolean deleteByXh(String xh) throws Exception{
		if(StringUtils.isNotBlank(xh)){
			return dao.deleteByXh(xh);
		}
		return true;
	}
	

}
