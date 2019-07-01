/**
 * @部门:学工产品事业部
 * @日期：2014-8-27 下午04:31:08 
 */  
package xsgzgl.gygl.gzwpcmdjgl;


import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理
 * @类功能描述: 贵重物品出门登记管理 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2014-8-27 下午04:31:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzwpcmdjService extends SuperServiceImpl<GzwpcmdjForm, GzwpcmdjDao> {
	
	public GzwpcmdjService(){
		setDao(new GzwpcmdjDao());
	}
	
	/**
	 * 
	 * @描述: 获取贵重物品出门登记信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 上午10:57:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gzwpdjid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getGzwpcmdjxx(String gzwpdjid) {
		
		return dao.getGzwpcmdjxx(gzwpdjid);
	}
	
	/**
	 * 
	 * @描述: 保存更新新增贵重物品出门登记信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 上午11:05:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveGzwpcmdjxx(GzwpcmdjForm model) throws Exception{
		return dao.saveGzwpcmdjxx(model);
	}
	
	/**
	 * 
	 * @描述: 删除信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 上午11:10:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gzwpdjid
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int deleteGzwpcmdjxx(String gzwpdjid) throws Exception{
		return dao.deleteGzwpcmdjxx(gzwpdjid);
	}
	
	
	/**
	 * 
	 * @描述: 删除信息批量
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 上午11:11:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pks
	 * @return
	 * @throws Exception
	 * int[] 返回类型 
	 * @throws
	 */
	public int[] deleteGzwpcmdjxxPl(List<String[]> pks) throws Exception{
		return dao.deleteGzwpcmdjxxPl(pks);
	}
	
	/**
	 * 
	 * @描述: 获取单个学生信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 上午11:13:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXsxxOne(String xh){
		return dao.getXsxxOne(xh);
	}
}
