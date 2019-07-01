/**
 * @部门:学工产品事业部
 * @日期：2015-2-11 上午09:12:58 
 */  
package com.zfsoft.xgxt.zdxljk.tbxs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @类功能描述: 浙大心理健康--特别关心学生 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-2-11 上午09:11:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class TbxsService extends SuperServiceImpl<TbxsModel, TbxsDao> {


	/**保存谈话记录**/
	public boolean save(TbxsModel model){
		try {
			dao.updateGzlx(model);
			dao.delThjl(model.getXh());
			List<String[]> params = new ArrayList<String[]>();
			
			String[] thsj = model.getThsjArr();
			String[] canUpdate = model.getCanUpdateArr();
			if (thsj == null || thsj.length == 0){
				return false;
			}
			
			for (int i = 0, j = thsj.length ; i < j ; i++){
				if (StringUtil.isNull(thsj[i])||"N".equals(canUpdate[i])){
					continue;
				}
				params.add(new String[]{model.getXh(),thsj[i],model.getGxlxArr()[i],
						model.getGzlx(),model.getQxyy(),model.getThnrArr()[i],model.getCljgArr()[i],model.getFtrArr()[i]});
			}
			
			return dao.saveThjl(model,params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**按学号查询谈话记录**/
	public List<HashMap<String,String>> getThjlByXh(String xh){
		
		return dao.getThjlByXh(xh);
	}
	
	
	/**导出查询***/
	public List<HashMap<String, String>> getExportData(TbxsModel t, User user)
		throws Exception {
		
		return dao.getExportData(t, user);
	}
	
	
	/**保存设置关注*/
	public boolean saveSzgz(TbxsModel t,String ids) throws Exception{
		
		return dao.saveSzgz(t, ids.split(","));
	}
	
	public List<HashMap<String,String>> getQxgzList(String xh){
		
		return dao.getQxgzList(xh);
	}
	
	
}
