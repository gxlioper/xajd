package xgxt.pjpy.comm.pjpy.pjlc.jgcx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.search.SearchService;
import xgxt.gygl.gywh.DelDetectModel;
import xgxt.utils.String.StringUtils;

public class PjpyJgcxService {

	PjpyJgcxDAO dao = new PjpyJgcxDAO();
	
	/**
	 * 评奖结果查询
	 * @param model
	 * @param colList
	 * @param topTr
	 * @return
	 */
	public Map<String,Object> queryPjjg(PjpyJgcxForm model,String[] colList,String[] topTr,HttpServletRequest request){
		
		List<String[]> data = null;
		Map<String,Object> result = new HashMap<String,Object>();
		SearchService searchService = new SearchService();
		//用户查询范围
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm");
		
		if (StringUtils.isNotNull(model.getXmdm())){
			try {
				String[] lcmc = dao.getLcmcByXmdm(model);//查询某个项目有哪些审核岗位
				colList = StringUtils.joinStrArr(colList,lcmc);//合并列数组
				topTr = StringUtils.joinStrArr(topTr,lcmc);//合并表头
				
				//动态获取各级审核过滤条件
				String[] lcmcValue = new String[lcmc.length];
				for (int i = 0 ; i < lcmc.length ; i++){
					lcmcValue[i] = request.getParameter(lcmc[i]);
				}
				model.setLcmcValue(lcmcValue);
				data = dao.findPjjgByXmdm(model,colList,lcmc,searchTjByUser);//查询
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			colList = StringUtils.joinStrArr(colList,new String[]{"shzt"});//合并列数组
			topTr = StringUtils.joinStrArr(topTr,new String[]{"最终审核"});//合并表头
			try {
				//查询
				data = dao.findPjjg(model,colList,searchTjByUser);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		result.put("rs", data);
		result.put("topTr", topTr);
		return result;
	}
	
	/**
	 * 删除评奖记录
	 * @param pkValues
	 * @return
	 */
	public boolean delPjjl(String[] pkValues){
		if (null != pkValues && pkValues.length > 0){
			try {
				return dao.delPjjl(pkValues);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 删除评奖申请、审核记录
	 * @param pkValues
	 * @return
	 * @throws Exception
	 */
	public String checkDel(DelDetectModel model){
		return dao.checkDel(model);
	}
}
