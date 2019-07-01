package xgxt.xtwh.xtwhCriterion;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.xtwhCriterion.entities.Entitry;

/**
 * 
 */
public class CriterionService {
	/**
	 * ƴ����ʾ��ҳ���������е�Ȩ��
	 * @param purviews
	 * @return
	 */
	protected String getPurviewString(String[] purviews) {
		StringBuffer purviewTmp  =  new StringBuffer();
		for(int i = 0;i<purviews.length;i++){
			purviewTmp.append(purviews[i]);
			if(i!=purviews.length-1){
				purviewTmp.append("!!");
			}
		}
		String purview=purviewTmp.toString();
		return purview;
	}
	
	/**
	 * ����ʵ��
	 * @param entitry
	 * @return
	 * @throws Exception 
	 */
	public String updateEntitry(Entitry entitry) throws Exception{
		Class clazz = entitry.getClass();
		Field[] fields = clazz.getFields();
		String[] inputs = new String[fields.length];
		
		String message = "";
		
		AccessibleObject.setAccessible(fields, true);
		
		// �����ֶ�
		String pk = entitry.getPk();
		// ����ֵ
		String pkValue = entitry.getPkValue();
		// ӳ���
		String tableName = entitry.getMappingTable();
		
		DAO dao = DAO.getInstance();
		
		// �Ƿ�������ͻ
		boolean[] flag = dao.checkExists(tableName, pk, new String[]{pkValue});
		
		if(flag[0]){ 
			message = "���ݿ��������ظ��ļ�¼��";
		}else { // ���뵽���ݿ���
			StringBuilder sql = new StringBuilder();  // insert���
			sql.append("update ").append(tableName).append(" set ");
			
			for(int i=0; i<fields.length; i++){
				Field field = fields[i];
				
				inputs[i] = (String)field.get(entitry);
				sql.append(field.getName()).append("=?,");
			}
			
			// ɾ�����һ������
			sql.deleteCharAt(sql.length()-1);
			
			sql.append(") values(");
			
			for(int i=0; i<fields.length; i++){
				sql.append("?").append(",");
			}
			
			// ɾ�����һ������
			sql.deleteCharAt(sql.length()-1);
			
			
			message = dao.runUpdate(sql.toString(), inputs) ? "����ɹ���" : "����ʧ�ܣ�";
			
		}
		
		return message;
	}
	
	/**
	 * ����ʵ��
	 * @param entitry
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws SQLException 
	 */
	public String saveEntitry(Entitry entitry) throws IllegalArgumentException, IllegalAccessException, SQLException{
		Class clazz = entitry.getClass();
		Field[] fields = clazz.getDeclaredFields();
		String[] inputs = new String[fields.length];
		
		String message = "";
		
		AccessibleObject.setAccessible(fields, true);
		
		// �����ֶ�
		String pk = entitry.getPk();
		// ����ֵ
		String pkValue = entitry.getPkValue();
		// ӳ���
		String tableName = entitry.getMappingTable();
		
		DAO dao = DAO.getInstance();
		
		// �Ƿ�������ͻ
		boolean[] flag = dao.checkExists(tableName, pk, new String[]{pkValue});
		
		if(flag[0]){ 
			message = "���ݿ��������ظ��ļ�¼��";
		}else { // ���뵽���ݿ���
			StringBuilder sql = new StringBuilder();  // insert���
			sql.append("insert into ").append(tableName).append("(");
			
			for(int i=0; i<fields.length; i++){
				Field field = fields[i];
				
				inputs[i] = (String)field.get(entitry);
				sql.append(field.getName()).append(",");
			}
			
			// ɾ�����һ������
			sql.deleteCharAt(sql.length()-1);
			
			sql.append(") values(");
			
			for(int i=0; i<fields.length; i++){
				sql.append("?").append(",");
			}
			
			// ɾ�����һ������
			sql.deleteCharAt(sql.length()-1);
			sql.append(")");
			
			message = dao.insert(sql.toString(), inputs) ? "����ɹ���" : "����ʧ�ܣ�";
		}
		
		return message;
	}
	
	/**
	 * ���Entitry����
	 * @param clazz
	 * @param pkValue
	 * @return
	 */
	public Entitry getEntitry(Class<? extends Entitry> clazz, String pkValue){
		Entitry entitry = null;
		try {
			entitry = clazz.newInstance();
			
			String tableName = entitry.getMappingTable();
			String pk = entitry.getPk();
			
			StringBuilder sql = new StringBuilder(); 
			sql.append("select * from ")
				.append(tableName)
				.append(" where ")
				.append(pk).append("=?");
			
			DAO dao = DAO.getInstance();
			
			Map<String, String> map = dao.getMapNotOut(sql.toString(), new String[]{pkValue});
			
			// ͨ���������Ŀ����model��ֵ
			Method[] methods = clazz.getDeclaredMethods();
			
			for(int i=0; i<methods.length; i++){
				String methodName = methods[i].getName();
				String field = String.valueOf(methodName.charAt(3)).toLowerCase() + methodName.substring(4);
		
				if(methodName.substring(0, 3).equalsIgnoreCase("set")){
					methods[i].invoke(entitry, map.get(field));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entitry;
	}
	
	/**
	 * ͨ������ֵ��ȡjs��Ϣ
	 * @param pkVals
	 * @param outputs
	 * @return
	 */
	public List<HashMap<String, String>> getJsListForPk(String[] pkVals, String[] outputs){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_view_xtwh_jswh where 1=1 ");
		
		for(int i=0; i<pkVals.length; i++){
			if(i == 0){
				sql.append("and (");
			}
			sql.append("jsdm=? ");
			
			if(i < pkVals.length-1){
				sql.append("or ");
			}
			
			if(i == pkVals.length-1){
				sql.append(")");
			}
		}
		
		return DAO.getInstance().getList(sql.toString(), pkVals, outputs);
	}
	
	/**
	 * ����ҳ����Ϣ
	 * @param request
	 * @param del
	 * @param type 
	 */
	public void setMessage(HttpServletRequest request, Boolean yesNo, String type) {
		
		if(type.equalsIgnoreCase("del")){
			if(yesNo){
				request.setAttribute("message", "ɾ���ɹ�");
			}else{
				request.setAttribute("message", "ɾ��ʧ��");
			}
		}else if(type.equalsIgnoreCase("save")){
			if(yesNo){
				request.setAttribute("message", "����ɹ�");
			}else{
				request.setAttribute("message", "����ʧ��");
			}
		}
	}
	
	/**
	 * ��ȡ��ɫ
	 * @return
	 */
	public List<HashMap<String, String>> getRsList(String tableName, Map<String, String> queryMap, String[] colList){
		StringBuilder query = new StringBuilder(" where 1=1 ");
		 
		List<String> inputList = new ArrayList<String>();
 		
		// ƴдwhere����
		for(Map.Entry<String, String> entry : queryMap.entrySet()){
			if(StringUtils.isNotNull(entry.getValue())){
				query.append(" and ").append(entry.getKey()).append("=?");
				inputList.add(entry.getValue());
			}
		}
	
		return CommonQueryDAO.commonQueryforList(tableName, query.toString(), (String[])inputList.toArray(new String[]{}), colList, "");
	}
	
}
