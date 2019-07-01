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
	 * ������Żݿ�������Ϣ
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
	 * ���Żݿ������ѯ
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
	 * ���Żݿ�������˲�ѯ
	 * @param model
	 * @param colList
	 * @param gwmc ��ǰ������ϵ����и�λ
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
			nextGwmc = gwmc[index+1];//��һ��������λ
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
	 * ������˻��Żݿ�����
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
				nextGwmc = gwmc[index+1];//��һ��������λ
			}
		}
		
		if (null != pkValues && pkValues.length>0 && StringUtils.isNotNull(gnmc)){
			try {
				
				if (StringUtils.isNotNull(nextGwmc)  && "ͨ��".equals(model.getShjg())){
					//�������һ��������λΪ�˻ص���Ҫ����Ϊ��δ��ˡ�
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
	 * ���Żݿ����������Ϣ
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
	 * ���Żݿ�����ɾ��
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
