/**
 * @部门:学工产品事业部
 * @日期：2017-2-22 上午11:23:04 
 */  
package xsgzgl.qgzx.QgCommUtil;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.coyote.Request;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cssz.QgzxCsszService;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2017-2-22 上午11:36:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class QgCommUtilf{
	/**
	 * 
	 * @描述: 用于重置path路径，和高级查询条件有关
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-2-22 上午11:36:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * void 返回类型 
	 * @throws
	 */
	public static void setCzpath(HttpServletRequest request,SearchModel searchModel){
		String qgzq =  QgCommUtilf.getQgzq();
		request.setAttribute("qgzq", qgzq);
		if( StringUtils.isNotNull(qgzq) && qgzq.equals("xq")){
			String czpath = (String) request.getAttribute("path");
			request.setAttribute("czpath", czpath.replace(".do", "_xq.do"));
			if(searchModel != null){
				searchModel.setSearch_tj_xq(new String[]{Base.currXq});
			}
		}
	}
	
	/**
	 * 
	 * @描述: 获取勤工周期
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-2-22 上午11:36:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public static String getQgzq(){
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		HashMap<String,String> cs = qgzxCsszService.getCssz();
		return cs.get("qgzq");
	}
	
	/**
	 * 
	 * @描述: 获取cssz表中的内容并置入request域
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-2-22 下午03:13:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws
	 */
	public static void setCssz(HttpServletRequest request){
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		HashMap<String,String> cs = qgzxCsszService.getCssz();
		request.setAttribute("cs", cs);
	}
}
