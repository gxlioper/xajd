package xgxt.wjsc.wjff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

/**
 * �ļ����Ŵ����ع�<br>
 * @author ³��
 * @version 1.0
 */
public class WjffDAO extends DAO {

	/**
	 * �ļ����Ų�ѯ
	 * @param model ���ݲ�ѯ����
	 * @param user ����user���Ʋ�ѯ��Χ
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> queryFile(WjffModel model,User user){
		
		HashMap<String , Object> map = getQueryFileSql(model, user);
		
		Pages pages = model.getPages();
		String count = getOneRs("select count(1) count from view_scwjxx where 1=1 "+map.get("sql"), (String[])map.get("input"), "count");
		pages.setMaxRecord(Integer.valueOf(count));
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (")
		   .append("select a.*,rownum r from (select * from view_scwjxx order by wjffsj desc) a where 1=1")
		   .append(map.get("sql"))
		   .append(" ) where r > ")
		   .append(pages.getStart())
		   .append(" and r <= ")
		   .append((pages.getStart() + pages.getPageSize()));
		
		return getListNotOut(sql.toString(), (String[])map.get("input"));
	}
	
	
	
	
	/**
	 * ƴ�ļ����Ų�ѯ����
	 * @param model
	 * @param user
	 * @return HashMap<String , Object>
	 */
	protected HashMap<String , Object> getQueryFileSql(WjffModel model,User user){
		
		HashMap<String , Object> map = new HashMap<String, Object>();
		
		List<String> input = new ArrayList<String>();
		StringBuilder querySql = new StringBuilder();
		
		//�ļ���
		if (StringUtils.isNotNull(model.getWjh())){
			querySql.append(" and wjh like '%'||?||'%' ");
			input.add(model.getWjh());
		}
		
		//�ļ���
		if (StringUtils.isNotNull(model.getWjm())){
			querySql.append(" and wjm like '%'||?||'%' ");
			input.add(model.getWjm());
		}
		
		//�û�����
		if (!"xx".equals(user.getUserType()) && !"admin".equals(user.getUserType())){
			querySql.append(" and ffr=?");
			input.add(user.getRealName());
		}
		
		//������
		if (StringUtils.isNotNull(model.getFfr())){
			querySql.append(" and ffr=?");
			input.add(model.getFfr());
		}
		
		//�ļ�����ʱ��
		if (StringUtils.isNotNull(model.getWjffKssj()) && StringUtils.isNull(model.getWjffJssj())) {
			querySql.append(" and to_date(wjffsj,'yyyy-mm-dd hh24:mi:ss') >= to_date(?,'yyyy-mm-dd')");
			input.add(model.getWjffKssj());
		}
		
		if (StringUtils.isNotNull(model.getWjffJssj()) && StringUtils.isNull(model.getWjffKssj()) ) {
			querySql.append(" and to_date(wjffsj,'yyyy-mm-dd hh24:mi:ss') <= to_date(?,'yyyy-mm-dd')");
			input.add(model.getWjffJssj());
		}
		
		if (StringUtils.isNotNull(model.getWjffKssj()) && StringUtils.isNotNull(model.getWjffJssj())) {
			querySql.append(" and to_date(wjffsj,'yyyy-mm-dd hh24:mi:ss') between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')+1");
			input.add(model.getWjffKssj());
			input.add(model.getWjffJssj());
		}
		
		
		map.put("sql", querySql);
		map.put("input", input.toArray(new String[]{}));
		
		return map;
	}
	
	
	
	/**
	 * �û����б��������˵�λ ��ѧ��
	 * @return
	 */
	public List<HashMap<String, String>> getYhzNotExistsYrdwAndStu(){
		
		String sql = "select zdm,zmc from yhzb a where a.zdm<>'6727' and not exists (select zdm from yhzb where zmc like '%���˵�λ%' and zdm = a.zdm) order by to_number(xssx) nulls last";
		
		return getList(sql, new String[]{}, new String[]{"zdm","zmc"});
	}
	
	/**
	 * �û��б��������˵�λ ��ѧ��
	 * @return
	 */
	public List<HashMap<String, String>> getYhNotExistsYrdwAndStu(){
		
		String sql = "select yhm,xm,a.zdm from yhb a,( select rownum rid,zdm, zmc,xssx from yhzb a where a.zdm <> '6727' and not exists (select zdm from yhzb " +
		"where zmc like '%���˵�λ%' and zdm = a.zdm) order by to_number(xssx) nulls last) b where a.zdm = b.zdm order by b.rid,to_number(a.xssx) nulls last";
		
		return getList(sql, new String[]{}, new String[]{"yhm","xm","zdm"});
		
	}
	
	
	/**
	 * �û����б�����ѧ��
	 * @return
	 */
	public List<HashMap<String, String>> getYhzNotExistsStu(){
		
		String sql = "select zdm,zmc from yhzb a where a.zdm<>'6727' order by zdm";
		return getList(sql, new String[]{}, new String[]{"zdm","zmc"});
		
	}
	
	/**
	 * �û��б�����ѧ��
	 * @return
	 */
	public List<HashMap<String, String>> getYhNotExistsStu(){
		
		String sql = "select yhm,xm,zdm from yhb where zdm<>'6727' order by zdm ";
		
		return getList(sql, new String[]{}, new String[]{"yhm","xm","zdm"});
	}
	
	
	
	/**
	 * �ļ�����ɾ�� 
	 * @param pkValues
	 * @return
	 * @throws Exception
	 */
	public boolean delWjs(String[] pkValues) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete from wjsc_scwjxxb where ");
		
		for (int i = 0 ; i < pkValues.length ; i++){
			
			sql.append("wjh=?");
			
			if (i != pkValues.length - 1){
				sql.append(" or ");
			} 
		}
		
		return runUpdate(sql.toString(), pkValues);
	}
}
