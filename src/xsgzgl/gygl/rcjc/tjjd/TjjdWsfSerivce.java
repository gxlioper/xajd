/**
 * @部门:学工产品事业部
 * @日期：2017-8-15 下午05:36:09 
 */  
package xsgzgl.gygl.rcjc.tjjd;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 天津机电卫生分个性化统计管理模块
 * @类功能描述: 按楼栋和学院统计
 * @作者： cq [工号:785]
 * @时间： 2017-8-15 下午05:36:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TjjdWsfSerivce extends SuperServiceImpl<TjjdWsfForm, TjjdWsfDao> {
	
	private static final String LINK_LD = "ld"; //楼栋
	private static final String LINK_XY = "xy"; //学院
	private TjjdWsfDao dao = new TjjdWsfDao();
	
	public TjjdWsfSerivce() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:按楼栋统计卫生分
	 * @作者：cq [工号：785]
	 * @日期：2017-8-15 下午06:03:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLdtjList(TjjdWsfForm t) throws Exception{
		return dao.getLdtjList(t);
	}
	
	/**
	 * 
	 * @描述:按学院统计卫生分
	 * @作者：cq [工号：785]
	 * @日期：2017-8-15 下午06:03:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXytjList(TjjdWsfForm t) throws Exception{
		return dao.getXytjList(t);
	}
	
	
	/**
	 * 
	 * @描述:获取卫生分统计
	 * @作者：cq [工号：785]
	 * @日期：2017-8-16 上午11:33:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getWsftjList(TjjdWsfForm t) throws Exception{
		//统计页面不需要分页
		t.getPages().setPageSize(Integer.MAX_VALUE);
		if(LINK_XY.equals(t.getLink())){
			return getXytjList(t);
		}else{
			return getLdtjList(t);
		}
	}

}
