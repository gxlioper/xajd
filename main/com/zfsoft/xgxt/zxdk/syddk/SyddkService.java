/**
 * @部门:学工产品事业部
 * @日期：2014-9-29 上午09:34:15 
 */  
package com.zfsoft.xgxt.zxdk.syddk;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 助学贷款-生源地贷款
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-9-29 上午09:34:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SyddkService extends SuperServiceImpl<SyddkModel, SyddkDao> {

	/*
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runInsert(java.lang.Object)
	 */
	
	@Override
	public boolean runInsert(SyddkModel t) throws Exception {
		
		t.setId(UniqID.getInstance().getUniqIDHash());
		boolean result = super.runInsert(t);
		
		if (result){
			result = insertDkxx(t);
		}
		
		return result;
	}

	private boolean insertDkxx(SyddkModel t)
			throws SQLException {
		String[] dkxn = t.getDkxn();
		String[] xf = t.getXf();
		String[] zsf = t.getZsf();
		String[] shf = t.getShf();
		boolean result = true;
		
		if (dkxn != null && dkxn.length > 0){
			List<String[]> params = new ArrayList<String[]>();
			for (int i = 0 ; i < dkxn.length ; i++){
				
//				if (StringUtil.isNull(xf[i])){
//					continue;
//				}
				
				String[] param = new String[]{t.getId(),dkxn[i],xf[i],zsf[i],shf[i]};
				params.add(param);
			}
			
			result= dao.insertDkxx(params);
		}
		return result;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runUpdate(java.lang.Object)
	 */
	
	@Override
	public boolean runUpdate(SyddkModel t) throws Exception {

		boolean result = super.runUpdate(t);
		
		if (result){
			dao.delDkxx(new String[]{t.getId()});
			this.insertDkxx(t);
		}
		
		return super.runUpdate(t);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runDelete(java.lang.String[])
	 */
	
	@Override
	public int runDelete(String[] values) throws Exception {
		dao.delDkxx(values);
		return super.runDelete(values);
	}
	
	public List<HashMap<String,String>> getDkxxList(String id){
		return dao.getDkxxList(id);
	}
	
	
	/**
	 * 
	 * @描述: 按学号查询生源地贷款
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-27 上午09:31:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getSydkList(String xh){
		
		return dao.getSydkList(xh);
	}
	
	/**
	 * 
	 * @描述:重复判断
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-13 上午09:05:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public boolean getExists(String xh,String xn) throws Exception {
		String num = dao.getExists(xh, xn);
		return Integer.valueOf(num) > 0;	
	}

}
