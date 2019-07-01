package xgxt.pjpy.comm.zhcp.zcjf;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mortbay.html.Page;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommDAO;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.utils.Pages;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_�۲�ӷ�_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class ZhcpZcjfDAO extends PjpyCommDAO {

	/**
	 * ��üӷ�����б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getJfshList(ZhcpZcjfForm model,
			User user, List<HashMap<String, String>> xmList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ��������
		String query = " where 1 = 1 ";

		SearchService searchService = new SearchService();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// Ȩ�޿���
		HashMap<String, String> notCtrlStatus = new HashMap<String, String>();
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		String searchTjByUser = searchService.getSearchTjByUser("a", user);

		searchTj += searchTjByUser;
		
		StringBuilder sql = new StringBuilder();

		sql.append("select a.* from ( ");
		sql.append("select a.*,rownum n from ( ");
		sql.append("select a.* from ( ");
		sql.append("select a.xh,b.xm,b.nj,b.xydm,b.zydm,b.bjdm,");
		sql.append("b.bjmc,nvl(c.sftj,'��') sftj,c.sfsh, ");
		
		List<String> colList = new ArrayList<String>();
		colList.add("xh");
		colList.add("xm");
		colList.add("nj");
		colList.add("bjmc");
		
		if (xmList != null && xmList.size() > 0) {
			for (int i = 0; i < xmList.size(); i++) {
				HashMap<String, String> xmInfo = xmList.get(i);
				String xmdm = xmInfo.get("xmdm");
				
				sql.append("nvl(" + xmdm + "." + xmdm + "sq,0) " + xmdm + "sq,");
				sql.append("case when c.sftj = '��' then nvl(" + xmdm + "." + xmdm + "sh,0) ");
				sql.append("else 0 end " + xmdm + "sh,");
				colList.add(xmdm+"sq");
				colList.add(xmdm+"sh");
			}
		}
		
		colList.add("shr");
		colList.add("sftj");
		
		sql.append("case when a.shr1 is null then 'δ���' else ");
		sql.append("(select c.xm from fdyxxb c where a.shr1 = c.zgh) end shr ");
		sql.append("from (select xh,shr1 from xg_pjpy_zcjfsqb ");
		sql.append("where xn='" + pjxn + "' ");
		sql.append("and xq='" + pjxq + "' ");
		sql.append("and nd='" + pjnd + "') a ");
		sql.append("left join xg_view_pjpy_pjryk b on a.xh = b.xh");
		
		if (xmList != null && xmList.size() > 0) {
			for (int i = 0; i < xmList.size(); i++) {
				
				HashMap<String, String> xmInfo = xmList.get(i);
				
				String xmdm = xmInfo.get("xmdm");
				
				sql.append(" left join ( ");
				sql.append(" select xh, xmdm, sum(sqfs) "+xmdm+"sq, ");
				sql.append(" sum(shfs) "+xmdm+"sh ");
				sql.append(" from xg_pjpy_zcjffsb a where xn = '" + pjxn + "' ");
				sql.append(" and xq = '" + pjxq + "' and nd = '" + pjnd + "' ");
				sql.append(" and xmdm = '"+xmdm+"' ");
				sql.append(" and exists(select 1 from xg_pjpy_zcxmjfb b ");
				sql.append("where a.xmdm = b.xmdm and a.jfdm = b.jfdm ");
				sql.append(" and a.xn=b.xn and a.xq=b.xq and a.nd = b.nd) ");
				sql.append(" group by xh, xmdm ");
				sql.append(" ) "+xmdm);
				sql.append(" on "+xmdm+".xh=a.xh ");
			}
		}
		
		sql.append(" left join (select xh,sftj1 sftj,decode(shr1,'','��','��') sfsh ");
		sql.append(" from xg_pjpy_zcjfsqb where xn = '" + pjxn + "' ");
		sql.append(" and xq = '" + pjxq + "' and nd = '" + pjnd + "' ");
		sql.append(" ) c on c.xh=a.xh ");
                 
		sql.append(" ) a ");
		sql.append(" ) a ");
		
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			if (Base.isNull(model.getSearchModel().getInput_mhcx())) {
				inputV = new String[] { user.getUserName() };
			}
			sql.append(" where a.bjdm = (select bjdm from view_xsjbxx where xh=?)");
		} else {
			sql.append(query + searchTj);
		}
		
		sql.append(" ) a where 1 = 1 ");
		
		String nofy = model.getNofy();
		
		DAO dao = DAO.getInstance();
		
		if (Base.isNull(nofy)) {
			// ����
			String count = dao.getOneRs("select count(*) count from ("
					+ sql.toString() + ") a ", inputV, "count");
			count = Base.isNull(count) ? "0" : count;
			model.getPages().setMaxRecord(Integer.parseInt(count));

			sql.append(" and n > " + model.getPages().getStart());
			sql.append(" and n <= "
					+ (model.getPages().getStart() + model.getPages()
							.getPageSize()));
		}
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputV, colList.toArray(new String[] {}));

		return list;
	}
	
	/**
	 * �޸��۲�ӷַ���
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean updateZcjffs(ZhcpZcjfForm model, User user) throws Exception {

		boolean flag = true;

		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		// ��˷���
		String[] shfs = model.getShfs();
		// ��Ŀ����
		String[] xmdm = model.getXmdm();
		// �ӷִ���
		String[] jfdm = model.getJfdm();
		// ����
		String tableName = "xg_pjpy_zcjffsb";

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ������
		String xh = model.getXh();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_zcjffsb set shfs = ? ");
		sql.append(" where xh=? ");
		sql.append(" and xn=? ");
		sql.append(" and xq=? ");
		sql.append(" and nd=? ");
		sql.append(" and xmdm=? ");
		sql.append(" and jfdm=? ");

		List<String[]> params = new ArrayList<String[]>();

		if (shfs != null && shfs.length > 0) {
			for (int i = 0; i < shfs.length; i++) {
				String[] value = new String[] { shfs[i], xh, pjxn, pjxq, pjnd,
						xmdm[i], jfdm[i] };
				params.add(value);

			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}
	
	/**
	 * ��ȡ���ύ�ܷ�
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getTjZf(ZhcpZcjfForm model, User user) throws Exception{
		
		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		DAO dao=DAO.getInstance();
		// ===============�۲�����========================
		//����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ===============�۲�����end========================
		StringBuilder sql=new StringBuilder();
		sql.append(" select xn,xq,nd,xh,xmdm,sum(shfs)fs from( ");
		sql.append(" select b.xn,b.xq,b.nd,b.xh,b.xmdm,to_number(nvl(b.shfs,0))shfs ");
		sql.append("  from xg_pjpy_zcjfsqb a  left join xg_pjpy_zcjffsb b ");
		sql.append(" on a.xn = b.xn and a.xq = b.xq  and a.nd = b.nd ");
		sql.append(" and a.xh = b.xh  where a.shzt1='ͨ��'  and a.sftj1='��') ");
		sql.append("  where 1=1 and xn='"+pjxn+"' and xq='"+pjxq+"' and nd='"+pjnd+"'  ");
		sql.append(" and ( ");
		for(int i=0;i<model.getCheckVal().length;i++){
			if(i!=0){
				
				sql.append(" or ");
			}
			sql.append(" xh= ");
			sql.append("'"+model.getCheckVal()[i]+"'");
		}
		sql.append(" ) ");
		sql.append(" group by xn,xq,nd,xh,xmdm  ");
		
		//System.out.println(sql);
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xn","xq","nd","xh","xmdm","fs"});
	}
	
	/**
	 * �۲�ӷ������(�ύ��xg_pjpy_zhcpb�۲����)
	 * @param tjzf
	 * @return
	 * @throws Exception
	 */
	public boolean zsjfCommit(List<HashMap<String, String>> tjzf)
			throws Exception {

		List<String>sqlArr=new ArrayList<String>();
		
		//String[] sqlArr = new String[tjzf.size()];
		for (int i = 0; i < tjzf.size(); i++) {
			
			HashMap<String, String> tjzfMap = tjzf.get(i);
			
			if("zd2".equalsIgnoreCase(tjzfMap.get("xmdm"))
					||"zd3".equalsIgnoreCase(tjzfMap.get("xmdm"))
					||"zd4".equalsIgnoreCase(tjzfMap.get("xmdm")) ){
			
				StringBuilder sql = new StringBuilder();
				
				
				sql.append(" update xg_pjpy_zhcpb set ");
				//��������д����
				if("zd2".equalsIgnoreCase(tjzfMap.get("xmdm"))){
					sql.append(" zd6 ");
				}
				if("zd3".equalsIgnoreCase(tjzfMap.get("xmdm"))){
					sql.append(" zd8 ");
				}
				if("zd4".equalsIgnoreCase(tjzfMap.get("xmdm"))){
					sql.append(" zd10 ");
				}
				sql.append(" = ");
				sql.append("'" + tjzfMap.get("fs") + "'");
				sql.append(" where xn='"+tjzfMap.get("xn")+"' ");
				sql.append(" and xq='"+tjzfMap.get("xq")+"' ");
				sql.append(" and nd='"+tjzfMap.get("nd")+"' ");
				sql.append(" and xh='"+tjzfMap.get("xh")+"' ");
				sqlArr.add(sql.toString());
			}
		}
		
		return saveArrDate(sqlArr.toArray(new String[]{}));

	}
	
	public String checkShfs(ZhcpZcjfForm model,String xh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		
		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;

		// ===============�۲�����========================
		//����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();	
		
		sql.append(" select count(1) num ");
		sql.append("  from xg_pjpy_zcjffsb ");
		sql.append("  where xh = ? ");
		sql.append("  and xn = ? ");
		sql.append("  and xq = ? ");
		sql.append("  and nd = ? ");
		sql.append("  and shfs is not null ");
		
		return dao.getOneRs(sql.toString(), new String[]{xh,pjxn,pjxq,pjnd}, "num");
	}
	
	public String checkShfIsModi(ZhcpZcjfForm model,HashMap<String,Object>map){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		
		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		
		String xh=(String)map.get("xh");

		// ===============�۲�����========================
		//����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();	
		
		sql.append(" select count(1)num from(");
		sql.append(" select a.xn,a.xq,a.nd,a.xh,a.xmdm,");
		sql.append(" a.jfdm,a.sqfs,a.sqly,");
		sql.append(" case when shfs is null then '0' else shfs end shfs");
		sql.append(" from xg_pjpy_zcjffsb a where xh=? ");
		sql.append("  and xn = ? ");
		sql.append("  and xq = ? ");
		sql.append("  and nd = ? ");
		sql.append(" )  where ( ");

		
		map.remove("xh");
		
		int n=0;
		
		for(Map.Entry<String, Object> entry : map.entrySet()){
			
			String paramName = entry.getKey();
			String value = (String)entry.getValue();
			
			if(Base.isNull(value)){
				value="0";
			}
			
			String[] xmdmInfo=paramName.split("_");
			String xmdm=xmdmInfo[1];
			String jfdm=xmdmInfo[2];
			
			if(n!=0){			
				sql.append(" or ");
			}
			
			sql.append(" xmdm='"+xmdm+"' and jfdm='"+jfdm+"'  and shfs<>'"+value+"' ");
			
			n++;					
		}
		
		sql.append(" ) ");
		
		return dao.getOneRs(sql.toString(), new String[]{xh,pjxn,pjxq,pjnd}, "num");
	}
}
