package xgxt.jygl.tables;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xgxt.utils.String.StringUtils;


public class JyglTablesService extends CommService{

	private JyglTablesDAO dao = new JyglTablesDAO();
	
	/**
	 * ��ѯ����ѧ����Ϣ�б� 
	 * @param model
	 * @return
	 */
	public List<String[]> getStudents(JyglTablesForm model,String query,String[] input){
		
		try {
			return dao.getStudents(model,query,input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * ��ȡ�γ��б�
	 * @return
	 */
	public List<HashMap<String,String>> getKcList(){
		
		return dao.getKcList();
	}
	
	/**
	 * ��ȡ�γ��б�
	 * @return
	 */
	public List<HashMap<String,String>> getKcListByXh(String xh){
		
		return dao.getKcListByXh(xh);
	}
	
	/**
	 * ��ȡ�γ��б�
	 * @return
	 */
	public List<HashMap<String,String>> getKcListByBjdm(String bjdm){
		
		return dao.getKcListByBjdm(bjdm);
	}
	
	/**
	 * �������ô�ӡ�γ�
	 * @param model
	 * @return
	 */
	public boolean savePlszDykc(JyglTablesForm model){
		
		boolean del = false;
		String[] xkkh = model.getXkkh();
		
		if (StringUtils.isNotNull(model.getBjdm())){
			try {
				del = dao.delDykcByBjdm(model.getBjdm());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (null != xkkh && xkkh.length > 0 && del){
			try {
				return dao.savePlszDykc(model);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	/**
	 * ����ѧ�����ô�ӡ�γ�
	 * @param xh
	 * @param model
	 * @return
	 */
	public boolean saveDgszDykc(String xh,JyglTablesForm model){
		boolean del = false;
		String[] xkkh = model.getXkkh();
		
		if (StringUtils.isNotNull(xh)){
			try {
				del = dao.delDykcByXh(xh);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (null != xkkh && xkkh.length > 0 && del){
			try {
				return dao.saveDgszDykc(xh, xkkh);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	/**
	 * ��ѯ�����ô�ӡ�γ̶�Ӧ�ĳɼ�
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getXscjByXh(String xh){
	
		List<HashMap<String,String>> cjList = dao.getXscjByXh(xh);

		while (cjList.size() < 24){
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("xkkh", "");
			map.put("kcmc", "");
			map.put("cj", "");
			cjList.add(map);
		}
	
		return cjList;
	}
	
	/**
	 * ѧ����Уְ��
	 * @param xh
	 * @return
	 * @throws SQLException
	 */
	public String getXszwByXh(String xh) throws SQLException{
		
		String[] bjgbmc = dao.getXszwByXh(xh);
		
		if (null != bjgbmc && bjgbmc.length > 0){
			return Arrays.toString(bjgbmc).replace("[", "").replace("]", "");
		} else {
			return "";
		}
		
	}
}
