package xsgzgl.pjpy.bjlhdx.zhcp;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_综合测评_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * 
 * @version 1.0
 */

public class PjpyZhcpService  extends xsgzgl.pjpy.general.zhcp.PjpyZhcpService {

	PjpyZhcpDAO dao=new PjpyZhcpDAO();
	
	/**
	 * 综测项目分计算
	 * 综测总分、综测系项分计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean zcxmjs(PjpyGeneralForm model,User user) throws Exception {
		
		boolean blog=true;

		// =============有子项目的项目分计算 begin===================
		List<HashMap<String,String>>zcjsList=dao.getZcjsSql(model, user);
		// =============有子项目的项目分计算 end===================
		
		// -----------执行 begin---------------
		blog=zcxmfjs(zcjsList);
		
		//9月24日根据学校张老师QQ要求取消五分制转换
//		if(blog){
//			// 分数换算（100分制转换成5分制）
//			blog=dao.updateZcf();
//			
//		}
		// -----------执行 end---------------
		
		return blog;
	}
}
