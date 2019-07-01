package xsgzgl.xsxx.bzrpy.bzrpygl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.comm.BasicService;
import xsgzgl.xsxx.bzrpy.BasicModel;

public class XsxxBzrpyDAO extends CommDAO{
	
	/**
	 * ͨ�ò�ѯ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getBasicList(BasicModel model) throws Exception{
		
		
		BasicService basicService=new BasicService();
		
		//�߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		//�û�����
		User user=model.getUser();
		
		String viewName=model.getViewName();
		
		String []colList=model.getColList();
		
		// �û�����
		String userType=user.getUserType();
		
		//====================��������===================================
		user.setUserStatus(userType);
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================�������� end================================
		
		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.*,rownum r from ( ");
		sql.append(viewName);
		sql.append(" )a ");
		
		sql.append(query);
		
		String orderBy=basicService.ArrayToStr(model.getOrderBy(), ",");
	
		if(!Base.isNull(orderBy)){
			sql.append(" order by ");
			sql.append(orderBy);
		}
		
		// ====================SQLƴװ end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", inputV, colList, model);
		
		return list;
	}
	
	/**
	 * ���ݴ���� ����ֵ��ʽ���ֶ���Ϣ�����޸�
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean updateInfo(BasicModel model){
			
		HashMap<String,String>valueMap=model.getValueMap();
		
		String tableName=model.getTableName();
		
		CommService service=new CommService();
		
		DAO dao=DAO.getInstance();
		//����SQL
		StringBuilder sql=new StringBuilder();
		//�ֶ���
		StringBuilder comments=new StringBuilder();
		//pkValue
		StringBuilder pkValue=new StringBuilder();
		//pkS
		StringBuilder pks=new StringBuilder();
		//ֵ
		List<String> inputV=new ArrayList<String>();
		
		String[]pkArr=model.getPk();
		
		sql.append(" update  ");
		sql.append( tableName );
		sql.append(" set ");
		
		int n=0;
		for(Map.Entry<String, String>entry:valueMap.entrySet()){
			String paramName = entry.getKey();
			String paramValue= entry.getValue();
			if(n!=0){
				comments.append(",");
				
			}
			
			comments.append(paramName);
			comments.append("=?");

			for(int i=0;i<pkArr.length;i++){
				if(pkArr[i].equalsIgnoreCase(paramName)){
					if(i!=0){
						
						pkValue.append("!!@@!!");
						pks.append("||'!!@@!!'||");
					}
					pkValue.append(paramValue);
					pks.append(pkArr[i]);
				}
			}
			
			inputV.add(service.unicode2Gbk(paramValue));
			n++;
		}
		inputV.add(pkValue.toString());
		//������ֶ�
		sql.append(comments);
		sql.append(" where ");
		sql.append(pks);
		sql.append(" = ? ");
		boolean flag = false;
		
		try {
			flag = dao.runUpdate(sql.toString(), inputV
					.toArray(new String[] {}));
		} catch (Exception e) {
			
			flag = false;
			System.out.println("<------- �޸�ʧ�� ------>");
			e.printStackTrace();
		}
		
		return flag;
	} 
	
	/**
	 * ���ݴ���� ����ֵ��ʽ���ֶ���Ϣ���б���
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean saveInfo(BasicModel model){
		
		HashMap<String,String>valueMap=model.getValueMap();
		
		String tableName=model.getTableName();
		
		CommService service=new CommService();
		
		DAO dao=DAO.getInstance();
		//����SQL
		StringBuilder sql=new StringBuilder();
		//�ֶ���
		StringBuilder comments=new StringBuilder();
		//�ֶ�ֵ
		StringBuilder commentsValue=new StringBuilder();
		//ֵ
		List<String> inputV=new ArrayList<String>();
		sql.append(" insert into ");
		sql.append(tableName);
		sql.append(" ( ");
		
		commentsValue.append(" values( ");
		int n=0;
		for(Map.Entry<String, String>entry:valueMap.entrySet()){
			String paramName = entry.getKey();
			String paramValue= entry.getValue();
			if(n!=0){
				comments.append(",");
				commentsValue.append(",");
			}
			comments.append(paramName);
			commentsValue.append("?");
			inputV.add(service.unicode2Gbk(paramValue));
			n++;
		}
		commentsValue.append(" ) ");
		//������ֶ�
		sql.append(comments);
		sql.append(") ");
		//����ֵ
		sql.append(commentsValue);
		
		boolean flag = false;
		
		try {
			flag = dao.runUpdate(sql.toString(), inputV
					.toArray(new String[] {}));
		} catch (Exception e) {
			
			flag = false;
			System.out.println("<------- ���뱣��ʧ�� ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * �������湦��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean  saveBatch(BasicModel model) {
		
		String save=model.getSave();
		
		User user=new User();
		
		String[] saveArr=save.split("!!@@!!");
		
		String tableName=model.getTableName();
		
		HashMap<String,Object>arrayMap=model.getArrayMap();
		
		List<String[]>params=new ArrayList<String[]>();
		
		StringBuilder sql= new StringBuilder();
		
		StringBuilder valueSql= new StringBuilder();
		
		sql.append(" insert into ");
		sql.append(tableName);
		sql.append(" (");
		valueSql.append(" ( ");
		
		int n=0;
		for(int i=0;i<saveArr.length;i++){
			//�豣���ֶ���
			String zdm=saveArr[i];
			
			
			
			if(i!=0){
				sql.append(",");
				valueSql.append(",");
			}
			sql.append(zdm);
			valueSql.append("?");
			
			//��Ҫ������ֶ���
			
			n=((String[])arrayMap.get(zdm)).length;
			
		
			
		}
		
		for(int i=0;i<n;i++){
			
			List<String>valueList=new ArrayList<String>();
			
			for(Map.Entry<String, Object>entry:arrayMap.entrySet()){
				
				String[] paramValue=(String[]) entry.getValue();
				
				valueList.add(paramValue[i]);
			
			}
			params.add(valueList.toArray(new String[]{}));
			
		}
		
		
		sql.append(" )values ");
		valueSql.append(") ");
		
		try {
			
			return this.saveArrDate(sql.toString(), params, tableName, user);
		
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return false;
	}
	
	public HashMap<String,String>getOneBzrpy(XsxxBzrpyModel model){
		
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select xh,xn,pyr,pyyj,pysj from xg_xsxx_bzrpyb ");
		sql.append(" where 1=1 and xh='"+model.getXh()+"' ");
		sql.append(" and xn='"+model.getXn()+"'");
		
		return dao.getMap(sql.toString(), new String[]{},
				new String[]{"xh","xn","pyr","pyyj","pysj"});
		
	}
	
	/**
	 * ��ȡ��ǰѧ�������ֹʱ��
	 * @return
	 */
	public HashMap<String,String>getBzrpyJzsj(){
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select ");
		sql.append(" substr(pysj,1,4)||'��'||");
		sql.append(" substr(pysj,5,2)||'��'||");
		sql.append(" substr(pysj,7,2)||'��'");
		sql.append(" pysj from xg_xsxx_pysjszb ");
		sql.append(" where xn=(select dqxn from xtszb where rownum=1) ");
		return dao.getMap(sql.toString(), new String[]{}, new String[]{"pysj"});
	}
}
