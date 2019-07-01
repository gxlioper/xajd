/**
 * @部门:学工产品事业部
 * @日期：2014-8-20 上午09:01:41 
 */  
package xsgzgl.gygl.lfryxxgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.gygl.qsdsgl.QsdswhDao;
import com.zfsoft.xgxt.gygl.qsdsgl.QsdswhForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理
 * @类功能描述: 来访人员登记管理 
 * @作者：沈晓波[工号:1123]
 * @时间： 2014-8-20 上午09:01:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LfrydjService extends SuperServiceImpl<LfrydjForm, LfrydjDao> {
	
	public LfrydjService(){
		setDao(new LfrydjDao());
	}
	
	/**
	 * 
	 * @描述: 获取学生来访登记信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-25 上午11:34:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lfrydjid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getLfrydjxx(String lfrdjid){
		
		return dao.getLfrydjxx(lfrdjid);
	}
	
	/**
	 * 
	 * @描述: 删除信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-21 上午10:20:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lfrdjid
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int deleteLfrydjxx(String lfrdjid) throws Exception{
		return dao.deleteLfrydjxx(lfrdjid);
	}
	
	
	/**
	 * 
	 * @描述: 删除信息批量
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-21 上午10:41:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pks
	 * @return
	 * @throws Exception
	 * int[] 返回类型 
	 * @throws
	 */
	public int[] deleteLfrydjxxPl(List<String[]> pks) throws Exception{	
		return dao.deleteLfrydjxxPl(pks);	
	}
	
	/**
	 * 
	 * @描述: 获取单个学生信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-26 上午09:28:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXsxxOne(String xh){
		return dao.getXsxxOne(xh);
	}

	/** 
	 * @描述:查询来访事由列表
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月17日 上午9:00:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getLfsyList() {
		return dao.getLfsyList();
	}
}
