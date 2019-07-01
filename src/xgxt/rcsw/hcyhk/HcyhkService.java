package xgxt.rcsw.hcyhk;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.exception.SystemException;

import xgxt.audit.AuditUtil;
import xgxt.comm.CommService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

public class HcyhkService extends CommService{

	private HcyhkDAO dao = new HcyhkDAO();
	
	
	/**
	 * 保存火车优惠卡补办信息
	 * @param model
	 * @param gnmc
	 * @return
	 */
	public boolean saveHcyhkbbsh(HcyhkForm model,String gnmc){
		
		if (StringUtils.isNotNull(gnmc)){
			try {
				String[] spgw = AuditUtil.getSpgwByGnmc(gnmc);
				return dao.saveHcyhkbbsh(model, spgw);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	
	/**
	 * 火车优惠卡补办查询
	 * @param model
	 * @param gnmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryHcyhkbb(HcyhkForm model,String[] colList,String[] gwmc,HttpServletRequest request) throws Exception{
		
		if (null != gwmc && gwmc.length>0){
			String[] tempShzt = new String[gwmc.length]; 
			
			User user = getUser(request);
			StringBuilder query = new StringBuilder();
			for (int i = 0 ; i < gwmc.length ; i++){
				String gwmcValue = request.getParameter(gwmc[i]);
				tempShzt[i] = gwmc[i]+":"+gwmcValue;
				
				if (StringUtils.isNotNull(gwmcValue)){
					query.append(" and ").append(gwmc[i]).append("='").append(gwmcValue).append("' ");
				}
			}
			request.setAttribute("tempShzt", Arrays.toString(tempShzt));
			return dao.queryHcyhkbb(user,model,StringUtils.joinStrArr(colList,gwmc),gwmc,query.toString());
		} else {
			throw new SystemException("Error-1023");
		}
		
	}
	
	
	
	
	/**
	 * 火车优惠卡补办审核查询
	 * @param model
	 * @param colList
	 * @param gwmc 当前审核流上的所有岗位
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,Object> queryHcyhkbbsh(User user,HcyhkForm model,String[] colList,String[] topTr,String[] gwmc) throws Exception{
		
		String shgw = model.getShgw();
		String beforeGwmc="";
		String nextGwmc ="";
		int index = StringUtils.getStrIndexInArray(shgw,gwmc);
		
		if (index > 0){
			beforeGwmc = gwmc[index-1];
		}
		
		if (index > -1 && index != gwmc.length-1){
			nextGwmc = gwmc[index+1];//下一级审批岗位
		}
		
		if (StringUtils.isNotNull(shgw)){
			colList = StringUtils.joinStrArr(colList,new String[]{shgw});
			topTr = StringUtils.joinStrArr(topTr,new String[]{shgw});
		}
		
		if (StringUtils.isNotNull(nextGwmc)){
			colList = StringUtils.joinStrArr(colList,new String[]{nextGwmc});
			topTr = StringUtils.joinStrArr(topTr,new String[]{nextGwmc});
		}
		
		List<String[]> result = dao.queryHcyhkbbsh(user,model,colList, gwmc, beforeGwmc,nextGwmc);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("result", result);
		map.put("topTr", topTr);
		return map;
	}
	
	
	
	/**
	 * 批量审核火车优惠卡补办
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public boolean plshHcyhkbb(HcyhkForm model,String[] pkValues,String gnmc) throws SQLException{
		
		String nextGwmc = "";
		String[] gwmc = AuditUtil.getGwmcByGnmc(gnmc);
		
		if (null != gwmc && gwmc.length>0){
			int index = StringUtils.getStrIndexInArray(model.getShgw(),gwmc);
			
			if (index > -1 && index != gwmc.length-1){
				nextGwmc = gwmc[index+1];//下一级审批岗位
			}
		}
		
		if (null != pkValues && pkValues.length>0 && StringUtils.isNotNull(gnmc)){
			try {
				
				if (StringUtils.isNotNull(nextGwmc)  && "通过".equals(model.getShjg())){
					//如果有下一级审批岗位为退回的需要重置为“未审核”
					return dao.plshHcyhkbb(model, pkValues, gnmc) && dao.resetAfterShzt(model, pkValues, gnmc, nextGwmc);
				} else {
					return dao.plshHcyhkbb(model, pkValues, gnmc) ;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		return false;
	}
	
	
	/**
	 * 火车优惠卡补办审核信息
	 * @param pkValue
	 * @param gnmc
	 * @return
	 */
	public List<HashMap<String,String>> getHcyhkbbShxx(String pkValue,String gnmc){
		if (StringUtils.isNotNull(pkValue) && StringUtils.isNotNull(gnmc)){
			return dao.getHcyhkbbShxx(pkValue, gnmc);
		}
		
		return null;
	}
	
	
	/**
	 * 火车优惠卡补办删除
	 * @param pkValues
	 * @return
	 */
	public boolean delHcyhkbb(String[] pkValues){
		
		if (null != pkValues && pkValues.length>0){
			try {
				return dao.delHcyhkbb(pkValues);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
