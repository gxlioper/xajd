package xgxt.rcsw.zjxy;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.rcsw.RcswDAO;
import xgxt.rcsw.RcswForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicService;
import common.Globals;

public class RcswZjxyDAO extends RcswDAO {

	/**
	 * ���ʵ�﷢��ά����Ϣ
	 * 
	 * @author luojw
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSwffXmwhInfo(String pk) {

		DAO dao = DAO.getInstance();

		String sql = "select t.xmlx,t.xn,t.xq,t.nd,t.ffrq,t.ffsj,t.ffdd,t.bz,t.ffjssj,t.xmmc from "
				+ "rcsw_swffxmwhb t where t.xn||t.xq||t.nd||t.xmlx||t.ffsj = ?";
		String[] inputValue = new String[] { pk };
		String[] outputValue = new String[] { "xmlx", "xn", "xq", "nd", "ffrq",
				"ffsj","ffdd", "bz","ffjssj","xmmc"};

		List<HashMap<String, String>> list = dao.getList(sql, inputValue,
				outputValue);

		return list;
	}
	
	/**
	 * ��ð༶ѧ��(��������ɲ�)
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getBjxsList(RcswZjxyModel model,
			List<String> gbList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		DAO dao = DAO.getInstance();
		
		//��ѯ����
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj","sfff"};
		String[] queryLikeList = new String[] { "xh", "xm" };
		String xq=model.getXq();
		String xn=model.getXn();
		String nd=model.getNd();
		String ffsj=model.getFfsj();
		String xmlx=model.getXmlx();
		
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();
		
		//��ѯ���
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select a.xh pk,a.xh,a.xm,a.xymc,a.xydm,a.zymc,a.bjmc ,  ");
		sql.append(" (case when d.xhzgh is null then 'δ����' else '�ѷ���' end)sfff ");
		sql.append("from view_xsjbxx a left join rcsw_swffrqwhb d on a.xh=d.xhzgh and d.lx='xs' ");
		sql.append(Base.isNull(xn) ? "" : " and d.xn = '" + xn + "' ");
		sql.append(Base.isNull(xq) ? "" : " and d.xq = '" + xq + "' ");
		sql.append(Base.isNull(nd) ? "" : " and d.nd = '" + nd + "' ");
		sql.append(Base.isNull(xmlx) ? "" : " and d.xmlx = '" + xmlx + "' ");
		sql.append(Base.isNull(ffsj) ? "" : " and d.ffsj = '" + ffsj + "' ");
		sql.append(") a");
		sql.append(query);
		sql.append("and not exists (select * from view_bjgbxx b where a.xh = b.xh  ");
		if (gbList != null && gbList.size() > 0) {
			sql.append("and (");
			for (int i = 0; i < gbList.size(); i++) {
				if (i == 0) {
					sql.append(" bjgbmc = '" + gbList.get(i) + "'");
				}else{
					sql.append(" or bjgbmc = '" + gbList.get(i) + "'");
				}
			}
			sql.append(")");
		}
		sql.append(")");
//		sql.append("and not exists (select 1 from rcsw_swffrqwhb d where a.xh=d.xhzgh and d.lx='xs' ");
//		sql.append(Base.isNull(xn) ? "" : " and c.xn = '" + xn + "' ");
//		sql.append(Base.isNull(xq) ? "" : " and c.xq = '" + xq + "' ");
//		sql.append(Base.isNull(nd) ? "" : " and c.nd = '" + nd + "' ");
//		sql.append(Base.isNull(xmlx) ? "" : " and c.xmlx = '" + xmlx + "' ");
//		sql.append(Base.isNull(ffsj) ? "" : " and c.ffsj = '" + ffsj + "' ");
//		sql.append(" )");
		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] { "pk", "xh", "xm", "xymc", "zymc",
				"bjmc","sfff" };
		
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputValue, outputValue);
		
		return list;
	}
	
	public ArrayList<String[]> getFfxsList(RcswZjxyModel model) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		//��ѯ����
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj"};
		String[] queryLikeList = new String[] { "xh", "xm" };
		
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		
		//��ѯ���
		StringBuffer sql = new StringBuffer();
		sql.append("select * from(select a.*,rownum r,(case when b.xhzgh is null then 'δ����' else '�ѷ���' end)sfff");
		sql.append(" from (select a.xh  pkValue,a.xh,a.xm,a.xymc,a.xydm,a.zymc,a.zydm,a.bjdm,a.bjmc,a.nj,");
		sql.append(" d.ffsj, d.xmlx,d.nd, d.xn,d.xq ");
		sql.append("from view_xsjbxx a ,xg_swff_ffryb d where a.xh=d.xhzgh   ");
		sql.append(" and d.xn||d.xq||d.nd||d.xmlx||d.ffsj='"+model.getPkValue()+"' " );
		sql.append(")a left join rcsw_swffrqwhb b on a.xn=b.xn and a.xq=b.xq and a.nd=b.nd and a.xmlx=b.xmlx and a.ffsj=b.ffsj  and xh=xhzgh where xhzgh is null)");
		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] { "pkValue", "xh", "xm","nj", "xymc", "zymc", "bjmc"};
		ArrayList<String[]> list = CommonQueryDAO.commonQuery(sql.toString()+myQuery.getQueryString(), "", inputValue, outputValue, model);
		
		return list;
	}
	
	
	public ArrayList<String[]> getYffry(RcswForm myForm,HttpServletRequest request) throws IllegalArgumentException,
		SecurityException, IllegalAccessException,
		InvocationTargetException, NoSuchMethodException {
		
		myForm.getSearchModel().setPath("rcsw_swff_xmff.do");
		RcswZjxyService service = new RcswZjxyService();
		SearchService searchService = new SearchService();	
		BasicService basicService=new BasicService();
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel())+service.getQuery(myForm);	
		//�߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
				
		//ѧԺ������ԱȨ��	
		String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");	
		
		//��ѯ���
		StringBuffer sql = new StringBuffer();
		
		sql.append("select rownum r,a.* from view_xg_swff_ffryb a where 1=1 ");
		if(!"".equalsIgnoreCase(myForm.getSfqd()) && null!=myForm.getSfqd()){
			sql.append(" and sfqd='"+myForm.getSfqd()+"' ");
		}
		request.setAttribute("topTr", basicService.getTopTr("mdqr_xmszb",
				new String[] { "","ѧ��", "����","�꼶", Base.YXPZXY_KEY, "רҵ", "�༶", "�Ƿ�ȷ��","�Ƿ���Ҫ֪ͨ" }));
		String[] outputValue ={"checked","disabled","xhzgh","xm","nj","xymc","zymc","bjmc","sfqd","sftz"};
		
		ArrayList<String[]> list = CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjByUser, inputV, outputValue, myForm);
		
		return list;
	}
	
	/**
	 * ��ð�ɲ�ѧ��
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getGbxsList(RcswZjxyModel model,
			List<String> gbList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		DAO dao = DAO.getInstance();
		
		//	��ѯ����
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj","sfff"};
		String[] queryLikeList = new String[] { "xh", "xm" };
		
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();
		String xq=model.getXq();
		String xn=model.getXn();
		String nd=model.getNd();
		String ffsj=model.getFfsj();
		String xmlx=model.getXmlx();
		
		//��ѯ���
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select a.xh pk,a.xh,a.xm,a.xydm,a.xymc,a.zymc,a.bjmc ,  ");
		sql.append(" (case when d.xhzgh is null then 'δ����' else '�ѷ���' end)sfff ");
		sql.append("from view_xsjbxx a left join rcsw_swffrqwhb d on a.xh=d.xhzgh and d.lx='xs' ");
		sql.append(Base.isNull(xn) ? "" : " and d.xn = '" + xn + "' ");
		sql.append(Base.isNull(xq) ? "" : " and d.xq = '" + xq + "' ");
		sql.append(Base.isNull(nd) ? "" : " and d.nd = '" + nd + "' ");
		sql.append(Base.isNull(xmlx) ? "" : " and d.xmlx = '" + xmlx + "' ");
		sql.append(Base.isNull(ffsj) ? "" : " and d.ffsj = '" + ffsj + "' ");
		sql.append(") a");
		sql.append(query);
		sql.append("and exists (select * from view_bjgbxx b where a.xh = b.xh ");
		if (gbList != null && gbList.size() > 0) {
			sql.append("and (");
			for (int i = 0; i < gbList.size(); i++) {
				if (i == 0) {
					sql.append(" bjgbmc = '" + gbList.get(i) + "'");
				}else{
					sql.append(" or bjgbmc = '" + gbList.get(i) + "'");
				}
			}
			sql.append(")");
		}
		sql.append(" )");
		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] { "pk", "xh", "xm", "xymc", "zymc",
				"bjmc","sfff" };
		
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputValue, outputValue);
		
		return list;
	}
	
	/**
	 * ��ø���Ա��Ϣ
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getFdyxxList(RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();
		
		//	��ѯ����
		String[] queryList = new String[] { "bmdm", "zw", "sfff"};
		String[] queryLikeList = new String[] { "zgh", "xm" };
		
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();
		String xq=model.getXq();
		String xn=model.getXn();
		String nd=model.getNd();
		String ffsj=model.getFfsj();
		String xmlx=model.getXmlx();
		
		// ��ѯ���
		StringBuffer sql = new StringBuffer();
		sql.append("select * from( select a.zgh pk,a.zgh,a.xm,a.bmdm,a.bmmc,a.zwmc,");
		sql.append(" (case when d.xhzgh is null then 'δ����' else '�ѷ���' end)sfff ");
		sql.append("  from view_fdyxx a left join rcsw_swffrqwhb d ");
		sql.append(" on a.zgh=d.xhzgh and lx='ls' ");
		sql.append(Base.isNull(xn) ? "" : " and d.xn = '" + xn + "' ");
		sql.append(Base.isNull(xq) ? "" : " and d.xq = '" + xq + "' ");
		sql.append(Base.isNull(nd) ? "" : " and d.nd = '" + nd + "' ");
		sql.append(Base.isNull(xmlx) ? "" : " and d.xmlx = '" + xmlx + "' ");
		sql.append(Base.isNull(ffsj) ? "" : " and d.ffsj = '" + ffsj + "' ");
		sql.append(") a");
		sql.append(query);
		sql.append(" and exists (select 1 from fdybjb b where a.zgh = b.zgh) ");
		sql.append(" and not exists (select 1 from bzrbbb c where a.zgh = c.zgh) ");
		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] { "pk", "zgh", "xm", "bmmc", "zwmc","sfff" };
		
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputValue, outputValue);
		
		return list;
	}
	
	/**
	 * ��ð�������Ϣ
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getBzrxxList(RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();
		
		//	��ѯ����
		String[] queryList = new String[] { "bmdm", "zw","sfff"};
		String[] queryLikeList = new String[] { "zgh", "xm" };
		
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();
		String xq=model.getXq();
		String xn=model.getXn();
		String nd=model.getNd();
		String ffsj=model.getFfsj();
		String xmlx=model.getXmlx();
		
		// ��ѯ���
		StringBuffer sql = new StringBuffer();
		sql.append("select * from( select a.zgh pk,a.zgh,a.xm,a.bmdm,a.bmmc,a.zwmc,");
		sql.append(" (case when d.xhzgh is null then 'δ����' else '�ѷ���' end)sfff ");
		sql.append("  from view_fdyxx a left join rcsw_swffrqwhb d ");
		sql.append(" on a.zgh=d.xhzgh and lx='ls' ");
		sql.append(Base.isNull(xn) ? "" : " and d.xn = '" + xn + "' ");
		sql.append(Base.isNull(xq) ? "" : " and d.xq = '" + xq + "' ");
		sql.append(Base.isNull(nd) ? "" : " and d.nd = '" + nd + "' ");
		sql.append(Base.isNull(xmlx) ? "" : " and d.xmlx = '" + xmlx + "' ");
		sql.append(Base.isNull(ffsj) ? "" : " and d.ffsj = '" + ffsj + "' ");
		sql.append(") a");
		sql.append(query);
		sql.append(" and exists (select 1 from bzrbbb b where a.zgh = b.zgh) ");
		sql.append(" and not exists (select 1 from fdybjb c where a.zgh = c.zgh) ");
		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] { "pk", "zgh", "xm", "bmmc", "zwmc","sfff" };
		
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputValue, outputValue);
		
		return list;
	}
	
//	/**
//	 * ���汻��������Ϣ(�����������ʱ�޷�����ͨ�÷���)
//	 * 
//	 * @author luojw
//	 * @throws Exception
//	 */
//	public void saveBffzxx(RcswZjxyModel model) throws Exception{
//		
//		DAO dao = DAO.getInstance();
//		
//		//ѧ�ţ�ְ���ţ�
//		String[] xhzgh = model.getXhzgh();
//		//����
//		String lx = model.getLx();
//		//����
//		String tableName = "rcsw_swffbffzb";
//		
//		String[] exec = new String[xhzgh.length + 1];
//		exec[0] = "delete from " + tableName + "";
//		
//		for (int i = 0; i < xhzgh.length; i++) {
//			exec[i + 1] = "insert into " + tableName + " values ('" + xhzgh[i] + "','"
//					+ lx + "')";
//		}
//		
//		dao.saveArrDate(exec);
//	}
	
	/**
	 * ɾ������������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void delffzxx() throws Exception{
		
		DAO dao = DAO.getInstance();
		
		String sql = "delete from rcsw_swffbffzb";
		
		dao.runUpdate(sql, new String[]{});
	}
	
	/**
	 * ��÷��Ŷ����б���Ϣ��ѧ����
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXsFfdxList(RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();

		// ��ѯ����
		String[] queryList = new String[] { "xn", "xq", "nd", "xmlx","ffsj","lx" };
		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();
		
		String xn = model.getXn();
		String xq = model.getXq();
		String nd = model.getNd();
		String xmlx = model.getXmlx();
		String ffsj = model.getFfsj();
		String xydm = model.getXydm();
		//��ѯ���
		StringBuffer sql = new StringBuffer();

		sql.append("select b.xh pk, b.xh, b.xm,b.nj,b.xydm,b.xymc,b.zymc, b.bjmc ");
		sql.append("from rcsw_swffrqwhb a, view_xsjbxx b ");
		sql.append(query);
		sql.append(" and a.xhzgh = b.xh ");
		if("xy".equalsIgnoreCase(model.getUserType())){
			sql.append(" and xydm='"+model.getUserDep()+"'");
		}
		sql.append("union all ");
		sql.append("select b.xh pk, b.xh, b.xm,b.nj,b.xydm,b.xymc,b.zymc, b.bjmc ");
		sql.append("from rcsw_swffbffzb a, view_xsjbxx b ");
		sql.append("where a.xhzgh = b.xh and not exists ");
		sql.append("(select 1 from rcsw_swffrqwhb c where a.xhzgh = c.xhzgh ");
		sql.append(Base.isNull(xn) ? "" : " and c.xn = '" + xn + "' ");
		sql.append(Base.isNull(xq) ? "" : " and c.xq = '" + xq + "' ");
		sql.append(Base.isNull(nd) ? "" : " and c.nd = '" + nd + "' ");
		sql.append(Base.isNull(xmlx) ? "" : " and c.xmlx = '" + xmlx + "' ");
		sql.append(Base.isNull(ffsj) ? "" : " and c.ffsj = '" + ffsj + "' ");
		sql.append(")");
		if("xy".equalsIgnoreCase(model.getUserType())){
			sql.append(" and xydm='"+model.getUserDep()+"'");
		}
		
	
		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] { "pk", "xh", "xm","nj", "xymc", "zymc",
				"bjmc" };
	
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputValue,
				outputValue);

		return list;
	}
	
	/**
	 * ��÷��Ŷ����б���Ϣ����ʦ��
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getLsFfdxList(RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();

		// ��ѯ����
		String[] queryList = new String[] { "xn", "xq", "nd", "xmlx","ffsj","lx" };
		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();
		
		String xn = model.getXn();
		String xq = model.getXq();
		String nd = model.getNd();
		String xmlx = model.getXmlx();
		String ffsj = model.getFfsj();
		String xydm = model.getXydm();		
		// ��ѯ���
		StringBuffer sql = new StringBuffer();

		sql.append("select b.zgh pk, b.zgh,b.bmdm, b.xm,b.bmmc,b.zwmc ");
		sql.append("from rcsw_swffrqwhb a, view_fdyxx b ");
		sql.append(query);
		sql.append(" and a.xhzgh = b.zgh ");
		if("xy".equalsIgnoreCase(model.getUserType())){
			sql.append(" and bmdm='"+model.getUserDep()+"'");
		}
		sql.append("union all ");
		sql.append("select b.zgh pk, b.zgh, b.xm,b.bmdm,b.bmmc,b.zwmc ");
		sql.append("from rcsw_swffbffzb a, view_fdyxx b ");
		sql.append("where a.xhzgh = b.zgh and not exists ");
		sql.append("(select 1 from rcsw_swffrqwhb c where a.xhzgh = c.xhzgh ");
		sql.append(Base.isNull(xn) ? "" : " and c.xn = '" + xn + "' ");
		sql.append(Base.isNull(xq) ? "" : " and c.xq = '" + xq + "' ");
		sql.append(Base.isNull(nd) ? "" : " and c.nd = '" + nd + "' ");
		sql.append(Base.isNull(xmlx) ? "" : " and c.xmlx = '" + xmlx + "' ");
		sql.append(Base.isNull(ffsj) ? "" : " and c.ffsj = '" + ffsj + "' ");
		sql.append(")");
		if("xy".equalsIgnoreCase(model.getUserType())){
			sql.append(" and bmdm='"+model.getUserDep()+"'");
		}
		
		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] { "pk", "zgh", "xm", "bmmc", "zwmc" };
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputValue,
				outputValue);

		return list;
	}
	
	/**
	 * �����Ŀ����б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getXmList(RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();

		// ��ѯ����
		String[] queryList = new String[] { "xn", "xq", "nd", "xmlx" };
		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();

		StringBuffer sql = new StringBuffer();

		sql.append("select t.xmlx, t.xn, t.xq, t.nd, t.ffsj, t.ffrq, ");
		sql.append("(select b.mc from rcsw_swfflxb b where t.xmlx = b.dm) xmmc, ");
		sql.append("(select b.xqmc from xqdzb b where t.xq = b.xqdm) xqmc ");
		sql.append("from rcsw_swffxmwhb t");
		sql.append(query);
		sql.append(" order by ffrq ");

		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] { "xmlx", "xn", "xq", "xmmc",
				"xqmc", "nd", "ffsj", "ffrq" };
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * ��÷�����������
	 */
	public String getFfjgNumber(
			RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String dateTable = getFfjgSql(model);
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) count from (");
		sql.append(dateTable);
		sql.append(")");
		
		DAO dao = DAO.getInstance();

		// ��ѯ����
		String[] queryList = new String[] { "lx", "xydm","xn","xq","nd","xmlx"};
		String[] queryLikeList = new String[] { "zgh", "xm" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		
		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] {"count"};
		
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputValue,
				outputValue);
		
		return list.get(0)[0];
	}
	
	/**
	 * ��÷��Ž���б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ArrayList<String[]> getFfjgList(
			 RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();

		// ��ѯ����
		String[] queryList = new String[] { "lx", "xydm","xn","xq","nd","xmlx" };
		String[] queryLikeList = new String[] { "zgh", "xm" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		
		Pages pages = model.getPages();
		int size = pages.getPageSize();
		int start = pages.getStart();
		int end = start + size;
		
		String maxRecord = getFfjgNumber(model);
		pages.setMaxRecord(Integer.parseInt(maxRecord));

		String dataTable = getFfjgSql(model);
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append(dataTable);
		sql.append(") where r>");
		sql.append(start);
		sql.append(" and r<=");
		sql.append(end);
		
		String[] inputValue = myQuery.getInputList();
		
		String[] outputValue = new String[] { "zgh", "xm", "xymc", "lx", "xn",
				"xqmc", "nd", "xmlxmc", "ffsj","isff" };
		if(Globals.XXDM_ZJXY.equalsIgnoreCase(Base.xxdm)){
			 outputValue = new String[] { "zgh", "xm", "xymc","xmlxmc","xmmc", "xn",
						"xqmc", "nd", "ffsj","ffjssj","isff" };
		}
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputValue,
				outputValue);

		return list;
	}
	
	public String getFfjgSql(RcswZjxyModel model)
		throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		// ��ѯ����
		String isff = model.getIsff();//�Ƿ񷢷�
		
		String[] queryList = new String[] { "lx", "xydm","xn","xq","nd","xmlx" };
		String[] queryLikeList = new String[] { "zgh", "xm" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();
		
		StringBuilder sql = new StringBuilder();
		
		//--------2010.12.23 qph----���ݶ���view_rcsw_swffjg�����ͼ����-----------
		sql.append("select rownum r,a.* from (")
		   .append("select a.*,case when b.xhzgh is null then 'δ����' else '����' end isff from (");
		   if(Globals.XXDM_ZJXY.equals(Base.xxdm)){
			   sql.append("select * from(select (xhzgh)zgh,'��ͨ'lx,xqmc,xydm,zydm,bjdm,xymc,xmmc,zymc,bjmc,nj,xm,xn,xq,nd,ffsj,xmlx,ffjssj,xmlxmc,sfqd from view_xg_swff_ffryb )");
			   sql.append(query);
			   sql.append(" and  sfqd='��'");
		   }else{
			   sql.append("select * from view_rcsw_swffjg ");
			   sql.append(query);
		   }
		   sql.append(") a left join rcsw_swffrqwhb b on a.xn = b.xn and a.xq = b.xq ")
		   .append("and a.nd = b.nd and a.xmlx = b.xmlx and a.ffsj = b.ffsj ")
		   .append(" and a.zgh = b.xhzgh) a ");
		   
		sql.append(Base.isNull(isff) ? "" : " where isff = '"+isff+"'");
		
		//------------end -----------------------------------------------------------
		/*sql.append("select rownum r,a.* from (select a.*,");
		sql.append("case when b.xhzgh is null then 'δ����' else '����' end isff ");
		sql.append("from (select * from ( ");
		for (int i = 0; i < xmList.size(); i++) {
			
			if(i != 0){
				sql.append(" union all ");
			}
			
			HashMap<String, String> map = xmList.get(i);
			String ffrq = map.get("ffrq");
			String xmlx = map.get("xmlx");
			String xmmc = map.get("xmmc");
			String xn = map.get("xn");
			String xq = map.get("xq");
			String xqmc = map.get("xqmc");
			String nd = map.get("nd");
			String ffsj = map.get("ffsj");
			
			//�Ƿ�ɲ�
			boolean isGb = false;
			//�Ƿ�༶
			boolean isBj = false;
			
			if("�༶".equalsIgnoreCase(ffrq)){
				isBj = true;
			}
			
			for (int j = 0;j < gbList.size(); j++) {
				String gb = gbList.get(j).get("mc");
				if(ffrq.equalsIgnoreCase(gb)){
					isGb = true;
				}
			}
			
			if (isGb) {
				sql.append("select a.xh zgh,a.xm,a.xydm,a.xymc,a.bjgbmc lx ");
				sql.append(",'" + xn + "'xn ");
				sql.append(",'" + xqmc + "'xqmc ");
				sql.append(",'" + nd + "'nd ");
				sql.append(",'" + xmmc + "'xmmc ");
				sql.append(",'" + ffsj + "'ffsj ");
				sql.append(",'" + xmlx + "'xmlx ");
				sql.append(",'" + xq + "'xq ");
				sql.append("from view_bjgbxx a ");
				sql.append("where a.bjgbmc = '" + ffrq + "'");
			} else if (isBj) {
				sql.append("select a.xh zgh,a.xm,a.xydm,a.xymc,'��ͨ' lx ");
				sql.append(",'" + xn + "'xn ");
				sql.append(",'" + xqmc + "'xqmc ");
				sql.append(",'" + nd + "'nd ");
				sql.append(",'" + xmmc + "'xmmc ");
				sql.append(",'" + ffsj + "'ffsj ");
				sql.append(",'" + xmlx + "'xmlx ");
				sql.append(",'" + xq + "'xq ");
				sql.append("from view_xsjbxx a ");
				//sql.append("where not exists (select 1 from view_bjgbxx b where a.xh = b.xh)");
			}else if ("��ͨ".equalsIgnoreCase(ffrq)) {
				sql.append("select a.xh zgh, a.xm,a.xydm,a.xymc,'��ͨ' lx ");
				sql.append(",'" + xn + "'xn ");
				sql.append(",'" + xqmc + "'xqmc ");
				sql.append(",'" + nd + "'nd ");
				sql.append(",'" + xmmc + "'xmmc ");
				sql.append(",'" + ffsj + "'ffsj ");
				sql.append(",'" + xmlx + "'xmlx ");
				sql.append(",'" + xq + "'xq ");
				sql.append("from view_xsjbxx a ");
				sql.append("where not exists (select 1 from view_bjgbxx b where a.xh = b.xh)");
			} else if ("����Ա".equalsIgnoreCase(ffrq)) {
				sql.append("select a.zgh, a.xm,bmdm xydm,bmmc xymc, '����Ա' lx ");
				sql.append(",'" + xn + "'xn ");
				sql.append(",'" + xqmc + "'xqmc ");
				sql.append(",'" + nd + "'nd ");
				sql.append(",'" + xmmc + "'xmmc ");
				sql.append(",'" + ffsj + "'ffsj ");
				sql.append(",'" + xmlx + "'xmlx ");
				sql.append(",'" + xq + "'xq ");
				sql.append("from view_fdyxx a ");
				sql.append("where exists (select 1 from fdybjb b where a.zgh = b.zgh) ");
				sql.append("and not exists (select 1 from bzrbbb c where a.zgh = c.zgh) ");
			} else if ("������".equalsIgnoreCase(ffrq)) {
				sql.append("select a.zgh, a.xm,bmdm xydm,bmmc xymc, '������' lx ");
				sql.append(",'" + xn + "'xn ");
				sql.append(",'" + xqmc + "'xqmc ");
				sql.append(",'" + nd + "'nd ");
				sql.append(",'" + xmmc + "'xmmc ");
				sql.append(",'" + ffsj + "'ffsj ");
				sql.append(",'" + xmlx + "'xmlx ");
				sql.append(",'" + xq + "'xq ");
				sql.append("from view_fdyxx a ");
				sql.append("where exists (select 1 from bzrbbb b where a.zgh = b.zgh) ");
				sql.append("and not exists (select 1 from fdybjb c where a.zgh = c.zgh) ");
			}
		}
		sql.append(")");
		sql.append(query);
		sql.append(") a left join rcsw_swffrqwhb b on a.xn = b.xn ");
		sql.append("and a.xq = b.xq and a.nd = b.nd and a.xmlx = b.xmlx ");
		sql.append("and a.ffsj = b.ffsj and a.zgh = b.xhzgh");
		sql.append(") a");
		sql.append(Base.isNull(isff)?"":" where isff = '"+isff+"'");*/
		
		return sql.toString();
	}
	
	/**
	 * ʵ�﷢����������(����)
	 * 
	 * @author luojw
	 * @throws SQLException 
	 * 
	 * @throws Exception
	 */
	public Boolean setSwffmd(String pk,String userName) throws SQLException{

		DAO dao = DAO.getInstance();

		boolean flag = false;
		
		String[] inputValue = new String[] { pk };
		String[] outputValue = new String[] { "xhzgh", "lx", "xn", "xqmc",
				"nd", "xmmc", "ffsj" ,"xm"};

		StringBuffer sql = new StringBuffer();

		sql.append("select t.xn, t.nd, t.ffsj,t.lx,t.xhzgh, ");
		sql.append("(select b.mc from rcsw_swfflxb b where t.xmlx = b.dm) xmmc, ");
		sql.append("(select b.xqmc from xqdzb b where t.xq = b.xqdm) xqmc, ");
		sql.append("case when (select a.xm from view_xsjbxx a where a.xh = t.xhzgh) is not null then ");
		sql.append("(select a.xm from view_xsjbxx a where a.xh = t.xhzgh)");
		sql.append("else (select a.xm from view_fdyxx a where a.zgh = t.xhzgh) end xm ");
		sql.append("from rcsw_swffrqwhb t ");
		sql.append("where t.xn || t.xq || t.nd || t.xmlx || t.ffsj = ?");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);
		
		if (list != null && list.size() > 0) {
			HashMap<String, String> map = list.get(0);
			String xn = map.get("xn");
			String xq = map.get("xqmc");
			String xmmc = map.get("xmmc");
			String newstitle = xn + "ѧ��" + xq + "ѧ��" + xmmc + "����";
			String puber = userName;
			String fbnr = "���Ŷ���";
			
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);
				fbnr += map.get("xm");
				fbnr += "��";
			}
			fbnr = fbnr.substring(0, fbnr.length()-1);
			String[] values = new String[] { newstitle, fbnr, puber };
			String gsinsql = "insert into newscontent(newsid,newstitle,newspart,newscont,puber,xwlx,towho) values(s_news_id.nextval,?,'N15',?,?,'003','1')";
			flag = dao.insert(gsinsql, values);
		}
		
		return flag;
	}

	/**
	 * ��÷��Ŷ�����ʱѧ�ţ�ְ���ţ�
	 * 
	 * @author luojw
	 * @throws Exception
	 * @throws SQLException
	 * 
	 * @throws Exception
	 */
	public String[] getLsXhZgh() throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "select xhzgh from rcsw_swffbffzb";

		String[] xhzgh = dao.getRs(sql, new String[] {}, "xhzgh");

		return xhzgh;
	}
	
	/**
	 * ��ȡ�������ż�¼
	 */
	public HashMap<String,String>getOneFfjl(String pkValue){
		DAO dao = DAO.getInstance();
		String[] outPutStr = { "pkValue","zgh", "xm","xmlx","lx", "xymc","xn", "xq","xqmc", "nd",
				"xmmc", "ffsj","mycd","pjyj"};
		String sql = "  select * from(select distinct(a.zgh),a.xn||a.xq||a.nd||b.xmlx||a.ffsj||b.lx||a.zgh pkValue,a.xn,a.xq,a.xqmc,a.nd,a.ffsj,a.xmlx,a.xmmc,a.xm,a.xydm,a.xymc,b.mycd,b.pjyj ";
		sql+=" ,b.lx from view_rcsw_swffjg a ";
		sql+=" left join rcsw_swffrqwhb b on a.xn = b.xn and a.xq = b.xq  and a.nd = b.nd and a.xmlx = b.xmlx ";
		sql+=" and a.ffsj = b.ffsj and a.zgh = b.xhzgh)  where pkValue=?  ";
		return dao.getMap(sql, new String[]{pkValue}, outPutStr);
	}
	
	/**
	 * ��ȡ����ͳ����Ϣ
	 * RcswForm myForm,String czlx(��������)
	 * @return HashMap<String,String>
	 */
	public List<String[]>getPjtjXx(RcswForm myForm)throws Exception{
		
		MakeQuery mQuery=new MakeQuery();
		
		StringBuilder query=new StringBuilder();
		//��ѯ�ֶ�
		String[]colList={"xn","xq","nd","xmlx"};
		//ģ����ѯ�ֶ�
		String[]colLikeList={"xh","xmmc"};
		mQuery.makeQuery(colList, colLikeList, myForm);
		
		query.append(mQuery.getQueryString());
		//���ſ�ʼʱ��
		if(!"".equalsIgnoreCase(myForm.getKssj()) && null!=myForm.getKssj()){
			query.append(" and ffsj>='"+myForm.getKssj()+"' ");
		}
		if(!"".equalsIgnoreCase(myForm.getJssj()) && null!=myForm.getJssj()){
			query.append(" and ffsj<='"+myForm.getJssj()+"' ");
		}
		
		//���Ž���ʱ��
		if(!"".equalsIgnoreCase(myForm.getFfjskssj())&& null!=myForm.getFfjskssj()){
			query.append(" and ffjssj>='"+myForm.getFfjskssj()+"' ");
		}
		if(!"".equalsIgnoreCase(myForm.getFfjsjssj()) && null!=myForm.getFfjsjssj()){
			query.append(" and ffjssj<='"+myForm.getFfjsjssj()+"' ");
		}
		
		query.append("");
		StringBuilder sql=new StringBuilder();
		sql.append("select  xn,xqmc, nd,xq xmlx,xmlxmc,xmmc,ffsj,ffjssj, ");
		sql.append(" (case when fcmy is null then '0��' else  fcmy||'��' end)fcmy,");
		sql.append(" (case when bjmy is null then '0��' else  bjmy||'��' end)bjmy,");
		sql.append(" (case when my is null then '0��' else  my||'��' end)my,");
		sql.append(" (case when bmy is null then '0��' else  bmy||'��' end)bmy,");
		sql.append(" (case when wpj is null then '0��' else  wpj||'��' end)wpj from");
		sql.append(" (select xn,(select xqmc from xqdzb where xqdm=xq)xqmc,xq, ");
		sql.append(" nd,xmlx,xmlxmc,xmmc,ffsj,ffjssj,sum(fcmycs)fcmy,sum(bjmycs)bjmy,sum(mycs)my,sum(bmycs)bmy,sum(wpj)wpj ");
		sql.append(" from (select xn,xq,nd,ffjssj,xmlx,xmlxmc,xmmc,ffsj,case when mycd='�ǳ�����' then pjcs end fcmycs, ");
		sql.append(" case when mycd='�Ƚ�����' then pjcs end bjmycs,case when mycd='����' then pjcs end mycs, ");
		sql.append(" case when mycd='������' then pjcs end bmycs,case when mycd is null then pjcs end wpj ");
		sql.append(" from (select xn,xq,nd,xmlx,ffjssj,xmlxmc,xmmc,ffsj,mycd,count(1)pjcs  ");
		sql.append(" from view_rcsw_swffrqwh group by ffjssj,xn,xq,nd,xmlx,ffsj,xmlxmc,xmmc,mycd))group by ffjssj,xn,xmlxmc,xmmc,xq,nd,xmlx,ffsj) ");
		
		String[] outPut={"xmlxmc","xmmc","xn","xqmc","nd","ffsj","ffjssj","fcmy","bjmy","my","bmy","wpj"};
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), query.toString(), mQuery.getInputList(), outPut, myForm);
	}
	
	/**
	 * ����Ҫ���Ͷ��ŵ��������浽������ȡ��
	 * @param myForm
	 * @return saveLqtzInfo
	 * @throws SQLException
	 * author qlj
	 */
	public boolean saveLqtzInfo(RcswForm myForm) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		String[]pkValue=myForm.getTzdxxh();
		for(int i=0;i<pkValue.length;i++){
			pkValue[i]=pkValue[i]+myForm.getXn()+myForm.getXq()+myForm.getNd()+myForm.getXmlx()+myForm.getFfsj();
			
		}
		
		String []addSql=new String[pkValue.length];
		for(int i=0;i<pkValue.length;i++){
			StringBuilder sql=new StringBuilder();
			sql.append(" insert into xg_rcsw_swfflq a (userDefId, xhzgh, xmlx, xmlxmc, xn, xq, nd, lx, ffsj, xm, ffdd, sftz,xmmc,ffjssj,dxtzsj)   ");
			sql.append(" select S_SWFFTZ_ID.nextval,a.* from(select  xhzgh, xmlx,mc,xn,xq,nd, 'xs' lx, ffsj,xm,ffdd, '��֪ͨ' sftz,xmmc,ffjssj,'"+myForm.getDxtzsj()+"' ");
			sql.append("  from view_xg_swff_lqtzjk a) a left join xg_rcsw_swfflq b on ");
			sql.append(" a.xhzgh=b.xhzgh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd and a.xmlx=b.xmlx and a.ffsj=b.ffsj ");
			sql.append(" where  a.xhzgh||a.xn||a.xq||a.nd||a.xmlx||a.ffsj='"+pkValue[i]+"' and (b.sftz is null or b.sftz='δ֪ͨ') ");
			addSql[i]=sql.toString();
		}
		dao.runBatch(addSql);
		return  true;
	}
	
	/**
	 * ɾ������ʧ�ܵļ�¼
	 * @param myForm
	 * @return saveLqtzInfo
	 * @throws SQLException
	 * author qlj
	 */
	public void deleteLqtz(RcswForm myForm) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		String[]pkValue=myForm.getTzdxxh();
		
		String []deleteSql=new String[pkValue.length];
		for(int i=0;i<pkValue.length;i++){
			deleteSql[i]=" delete from xg_rcsw_swfflq where xhzgh||xn||xq||nd||xmlx||ffsj='"+pkValue[i]+"'";
		}
		dao.runBatch(deleteSql);
	}
	
	/**
	 * ��ȡ��Уͨ������Կ
	 * @return HashMap<String,String>
	 * author qlj
	 */
	public HashMap<String,String> getPassWord(){
		DAO dao=DAO.getInstance();
		String sql=" select gxtmy from xg_wbs_myb ";
		return dao.getMap(sql, new String[]{},new String[]{"gxtmy"});
	}
	
	public void setFhzt(String[][]sedResult) throws SQLException{
		
		DAO dao=DAO.getInstance();
		String []sql=null;
		if(sedResult!=null){
			sql=new String[sedResult.length];
		}
		for(int i=0;i<sedResult.length;i++){
			sql[i]=" update xg_rcsw_swfflq set fhzt='"+sedResult[i][1]+"' where userdefid='"+sedResult[i][0]+"'";
		}
		dao.runBatch(sql);
	}
	
	/**
	 * ���ݻ�ִ��𷵻ػ�ִ�б�
	 * @return HashMap<String,String>
	 * author qlj
	 */
	public List<HashMap<String,String>> getDxtzHzxx(String hzlb){
		DAO dao=DAO.getInstance();
		String sql=" select hzdm,hznr,'0'tjsl  from xg_rcsw_swff_hzdmb where hzlb =? ";
		return dao.getList(sql, new String[]{hzlb},new String[]{"hzdm","hznr","tjsl"});
	}
	
	/**
	 * ɾ����Ŀʱɾ��������Ա��������
	 * @param pkValue
	 * @throws Exception
	 * author qlj
	 */
	public void delSwffXmwh(String []pkValue) throws Exception{
		DAO dao=DAO.getInstance();
		String sqlArr[]=new String[pkValue.length];
		for(int i=0;i<pkValue.length;i++){
			 sqlArr[i]="delete from xg_swff_ffryb where  xn || xq || nd || xmlx || ffsj='"+pkValue[i]+"' ";
		}
		dao.runBatch(sqlArr);
	}
	
	public List<HashMap<String,String>>getLqtzry(RcswForm myForm){
		DAO dao=DAO.getInstance();
		String sql=" select * from xg_rcsw_swfflq where sftz='��֪ͨ' ";
		return dao.getList(sql, new String[]{}, new String[]{"userdefid","xhzgh","xmmc","xhzgh","ffsj","ffdd","xm","ffjssj","xmlxmc","dxtzsj"});
	}
	
	public List<HashMap<String,String>>getLqtzByqr(RcswForm myForm){
		DAO dao=DAO.getInstance();
		String sql=" select * from xg_rcsw_swfflq where 1=1 ";
		
		if(myForm.getXhzgh().length>0){
			for(int i=0;i<myForm.getXhzgh().length;i++){
				String pkValue=myForm.getXmlx()+myForm.getXn()+myForm.getXq()+myForm.getNd()+myForm.getFfsj()+"xs"+myForm.getXhzgh()[i];
				if(i==0){
					
					sql+=" and ( ";
				}else{
					
					sql+=" or ";
				}
				sql+="  xmlx||xn||xq||nd||ffsj||lx||xhzgh= '"+pkValue+"'";
			}
			sql+=" ) ";
		}
		
		
		return dao.getList(sql, new String[]{}, new String[]{"userdefid","xhzgh","xmmc","xhzgh","ffsj","ffdd","xm","ffjssj","xmlxmc","dxtzsj"});
	}
	
	public List<HashMap<String,String>>getTxtzFhzt(RcswForm myForm){
		DAO dao=DAO.getInstance();
		//����״̬Ϊ�ջ���1023�ļ�¼��Ҫͬ������
		String sql="select * from xg_rcsw_swfflq where fhzt is null or fhzt ='1023' or fhzt='8888' ";
		return dao.getList(sql, new String[]{}, new String[]{"userdefid","xhzgh","xmmc","xhzgh","ffsj","ffdd","xm"});
	}
	
	/**
	 * �޸ķ���״̬
	 * @param txList
	 * @throws SQLException
	 */
	public void updateFhzt(List<HashMap<String,String>>txList) throws SQLException{
		DAO dao=DAO.getInstance();
		String[]sql=null;
		if(txList.size()>0){
			sql=new String[txList.size()];
		}
		for(int i=0;i<txList.size();i++){
			HashMap<String,String>txMap=txList.get(i);
			sql[i]=" update xg_rcsw_swfflq set fhzt='"+txMap.get("fhzt")+"' where userdefid='"+txMap.get("userdefid")+"'";
		}
		if(txList.size()>0){
			dao.runBatch(sql);
		}
	}
	
	public boolean updateSftz() throws Exception{
		DAO dao=DAO.getInstance();
		String sql=" update xg_rcsw_swfflq set sftz='��֪ͨ' where sftz='��֪ͨ' ";
		return dao.runUpdate(sql, new String[]{});
	} 
	
	/**
	 * ��ȡѧ�����ż�¼��Ϣ
	 * getFfryTjxx
	 * @param myForm
	 * @return List<String[]>
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]>getFfryTjxx(RcswForm myForm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StringBuilder sql=new StringBuilder();
		sql.append("select * from( select rownum r,a.*,(case when b.sftz is null then 'δ֪ͨ' else b.sftz end)sftz from  ");
		sql.append(" (select a.xhzgh,a.xmlx, a.xn,(select xqmc from xqdzb where a.xq = xqdm) xq,  ");
		sql.append(" a.nd,a.ffsj,(select mc from rcsw_swfflxb where a.xmlx=dm)xmmc,(case when b.xhzgh is null then 'δ��ȡ'else '��ȡ' end) sflq ");
		sql.append(" from xg_swff_ffryb a left join rcsw_swffrqwhb b on a.xhzgh = b.xhzgh ");
		sql.append(" and a.xn = b.xn and a.xq = b.xq and a.nd = b.nd  and a.ffsj = b.ffsj ");
		sql.append(" and a.xmlx = b.xmlx)a left join xg_rcsw_swfflq b  on a.xhzgh = b.xhzgh ");
		sql.append(" and a.xn = b.xn and a.xq = b.xq and a.nd = b.nd  and a.ffsj = b.ffsj  and a.xmlx = b.xmlx ");
		sql.append(" )where  xhzgh=? ");

		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "", new String[]{myForm.getXh()}, new String[]{"xn","xq","nd","ffsj","xmmc","sflq","sftz"}, myForm);
	}
	
	/**
	 * ��ȡ��ȡ�����б�
	 * @return  List<HashMap<String,String>>
	 * author qlj
	 */
	public List<HashMap<String,String>>getLqcsList(){
		DAO dao=DAO.getInstance();
		String sql=" select distinct(lqcs)dm,lqxm mc from VIEW_XG_SWFF_FFXMTJ  order by lqcs  ";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * ��ȡ���Ŵ����б�
	 * @return  List<HashMap<String,String>>
	 * author qlj
	 */
	public List<HashMap<String,String>>getFfcsList(){
		DAO dao=DAO.getInstance();
		String sql=" select distinct(ffcs) dm,ffxm mc from VIEW_XG_SWFF_FFXMTJ  order by ffcs    ";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	public List<HashMap<String,String>>getXmmcList(){
		DAO dao=DAO.getInstance();
		String sql=" select xmlx lx,b.xmmc dm,b.xmmc mc from rcsw_swfflxb a,rcsw_swffxmwhb b where a.dm=b.xmlx and xmmc is not null ";
		return dao.getList(sql, new String[]{}, new String[]{"lx","dm","mc"});
	}
	
	public HashMap<String,String> getSwffxm(String pkValue){
		DAO dao=DAO.getInstance();
		String sql=" select * from view_rcsw_swffxmwh where xn||xq||nd||xmlx||ffsj=? ";
		return dao.getMap(sql, new String[]{pkValue}, new String[]{"xn","xq","nd","xmlx","ffsj","ffjssj","xmlxmc","xmmc"});
	}
	
	public List<String[]>getXsFfxm(RcswForm myForm) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		StringBuilder query=new StringBuilder();

		String []colList={"pk","xmlxmc","xmmc","xn","xqmc","nd","ffsj","ffjssj"};
		
		MakeQuery mQuery=new MakeQuery();
		mQuery.makeQuery(new String[]{}, new String[]{}, myForm);
		if(!"".equalsIgnoreCase(myForm.getKssj())){
			query.append(" and ffsj>='"+myForm.getKssj()+"' ");
		}
		if(!"".equalsIgnoreCase(myForm.getJssj())){
			query.append(" and ffsj<='"+myForm.getJssj()+"' ");
		}
		if(!"".equalsIgnoreCase(myForm.getFfjskssj())){
			query.append(" and ffjssj>='"+myForm.getFfjskssj()+"' ");
		}
		if(!"".equalsIgnoreCase(myForm.getFfjsjssj())){
			query.append(" and ffjssj<='"+myForm.getFfjsjssj()+"' ");
		}
		if(!Base.isNull(myForm.getSfzh())){
			query.append("  and exists(select 1 from view_xsjbxx b where a.zgh=b.xh and sfzh = '"+myForm.getSfzh()+"') ");
		}
		if(!Base.isNull(myForm.getZgh())){
			query.append(" and zgh='"+myForm.getZgh()+"'");
		}
		
		sql.append("select a.*,rownum r from(select a.*,b.xhzgh zgh from xg_swff_ffryb b left join view_rcsw_swffxmwh  a ");
		sql.append(" on a.xn=b.xn and a.xq=b.xq and a.nd=b.nd and a.ffsj=b.ffsj and a.xmlx=b.xmlx ");
		sql.append(" where not exists (select 1 from view_rcsw_swffrqwh c ");
		sql.append(" where a.xn=c.xn and a.xq=c.xq and a.nd=c.nd and a.xmlx =c.xmlx and a.ffsj=c.ffsj and b.xhzgh=c.zgh))a  ");
		
		return CommonQueryDAO.commonQuery(sql.toString(), mQuery.getQueryString()+query, mQuery.getInputList(), colList, myForm);
	}
	
	/**
	 * ��ѧ��Ϊ���������������ţ�
	 * @param request
	 * @param myForm
	 * @throws Exception
	 */
	public boolean saveFfxm(RcswForm myForm)
		throws Exception {
		DAO dao=DAO.getInstance();
		String[]checkVal=myForm.getCheckVal();
		String[]sqlArr=new String[checkVal.length];
		for(int i=0;i<checkVal.length;i++){
			sqlArr[i]=" insert into rcsw_swffrqwhb(xn,xq,nd,xmlx,ffsj,lx,xhzgh,ffr,ffbz,mycd,pjyj) " +
					"select xn,xq,nd,xmlx,ffsj,'xs','"+myForm.getZgh()+"','"+myForm.getUserName()+"','','','' from rcsw_swffxmwhb where xn||xq||nd||xmlx||ffsj='"+checkVal[i]+"' ";
			
		}
		int[] result=dao.runBatch(sqlArr);
		for(int i=0;i<result.length;i++){
			if(result[i]==0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * ��ȡѧ��(�������֤��)
	 * @param sfzh
	 * @return
	 */
	public HashMap<String, String> getXhBySfzh(String sfzh) {
		
		StringBuilder sql = new StringBuilder();
		DAO dao = DAO.getInstance();
		sql.append(" select xh,sfzh,xm,bjmc,zymc,xymc,nj from view_xsjbxx where sfzh=? ");
		return dao.getMap(sql.toString(),
				new String[] {sfzh}, new String[] { "xh","xm","sfzh","bjmc","xymc","zymc","nj" });
	}
	
	/**
	 * �������,��������ѯ
	 * @return  List<String[]>
	 * @throws Exception
	 */
	public List<String[]>getFfxxList(RcswForm model) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		StringBuilder query=new StringBuilder();
		MakeQuery mQuery=new MakeQuery();
		
		String[]col={"xmlx","xn","xq","nd"};
		String[]colLikeList={"xmmc"};
		mQuery.makeQuery(col, colLikeList, model);
		
		User user=model.getUser();
		String userStatus=user.getUserStatus();
		
		sql.append(" select rownum r,xn||'!!@@!!'||xq||'!!@@!!'||nd||'!!@@!!'||xmlx||'!!@@!!'||ffsj pkValue, xn,xq,xqmc,nd,xmlxmc,xmmc,ffsj,ffjssj,xffrs,yffrs,(xffrs-yffrs)wffrs,ytz,yfs,ypj,wpj from ( ");
		sql.append(" select a.xn,a.xq,a.nd,a.xqmc,a.xmlx,a.xmlxmc,a.xmmc,a.ffsj,a.ffjssj,nvl(xffrs,0)xffrs,nvl(yffrs,0)yffrs, ");
		sql.append(" nvl(ytz,0)ytz,nvl(yfs,0)yfs,nvl(ypj,0)ypj,nvl(wpj,0)wpj from view_rcsw_swffxmwh a left join  (");
		
		
		sql.append(" select xn,xq,nd,xmlx,ffsj,sum(nvl(xffrs,0))xffrs,sum(nvl(yffrs,0))yffrs,");
		sql.append(" sum(nvl(yfs,0))yfs,sum(nvl(ytz,0))ytz,sum(nvl(ypj,0))ypj,sum(nvl(wpj,0))wpj from(");
		sql.append(" select  xn,xq,nd,xmlx,ffsj,");
		sql.append(" case when xhzgh is not null then 1 else 0 end xffrs,");
		sql.append(" case when cxh is not null then 1 else 0 end yffrs,");
		sql.append(" case when dxh is not null then 1 else 0 end yfs,");
		sql.append(" case when exh is not null then 1 else 0 end ytz,");
		sql.append(" case when fxh is not null then 1 else 0 end ypj,");
		sql.append(" case when gxh is not null then 1 else 0 end wpj from ");
		
		sql.append("(select a.*,cxh,dxh,exh,fxh,gxh from (select xn, xq, nd, xmlx, ffsj, xhzgh  ");
		sql.append(" from xg_swff_ffryb a)a ");
		sql.append("left join (select xn, xq, nd, xmlx, ffsj, xhzgh cxh ");
		sql.append(" from rcsw_swffrqwhb a)c on a.xn=c.xn and a.xq=c.xq and "); 
		sql.append(" a.nd=c.nd and a.xhzgh=c.cxh and a.xmlx=c.xmlx and a.ffsj=c.ffsj ");
				
		sql.append(" left join (select xn, xq, nd, xmlx, ffsj, xhzgh dxh");
		sql.append(" from xg_rcsw_swfflq a)d on a.xn=d.xn and a.xq=d.xq and a.nd=d.nd ");
		sql.append(" and a.xhzgh=d.dxh and a.xmlx=d.xmlx and a.ffsj=d.ffsj");
		         
		         
		         
		sql.append(" left join (select xn, xq, nd, xmlx, ffsj, xhzgh exh");
		sql.append(" from xg_rcsw_swfflq a  where sftz = '��֪ͨ')e on a.xn=e.xn and a.xq=e.xq ");
		sql.append(" and a.nd=e.nd and a.xhzgh=e.exh and a.xmlx=e.xmlx and a.ffsj=e.ffsj  ");
		          
		           
		sql.append(" left join (select xn, xq, nd, xmlx, ffsj, xhzgh fxh");
		sql.append(" from rcsw_swffrqwhb a where  mycd is not null)f  on a.xn=f.xn and a.xq=f.xq ");
		sql.append(" and a.nd=f.nd and a.xhzgh=f.fxh and a.xmlx=f.xmlx and a.ffsj=f.ffsj");
		          
		sql.append(" left join (select xn, xq, nd, xmlx, ffsj, xhzgh gxh ");
		sql.append("  from rcsw_swffrqwhb a where  mycd is null  ) g on a.xn=g.xn and a.xq=g.xq ");
		sql.append(" and a.nd=g.nd and a.xhzgh=g.gxh and a.xmlx=g.xmlx and a.ffsj=g.ffsj");
		sql.append(" where exists (select 1 from view_xsjbxx b where a.xhzgh=b.xh");
		
		
		if("jd".equalsIgnoreCase(userStatus)){
			sql.append(" and (exists(select 1 from fdybjb h where h.bjdm=b.bjdm and zgh='"+user.getUserName()+"')");
			sql.append(" or exists(select 1 from bzrbbb h where h.bjdm=b.bjdm and zgh='"+user.getUserName()+"')) ");
		}else if("fdy".equalsIgnoreCase(userStatus)){
			sql.append(" and exists(select 1 from fdybjb h where h.bjdm=b.bjdm and zgh='"+user.getUserName()+"')");
		}else if("bzr".equalsIgnoreCase(userStatus)){
			sql.append(" and exists(select 1 from bzrbbb h where h.bjdm=b.bjdm and zgh='"+user.getUserName()+"')");
		}else if("xy".equalsIgnoreCase(userStatus)){
			sql.append(" and xydm='"+user.getUserDep()+"'  ");
		}else if("stu".equalsIgnoreCase(userStatus)){
			sql.append(" and xh='"+user.getUserName()+"'");
		}
		sql.append(")))group by xn,xq,nd,xmlx,ffsj ");
		          
		sql.append("  )b on a.xn=b.xn and a.xq=b.xq and a.nd=b.nd ");
		sql.append("  and a.xmlx=b.xmlx and a.ffsj=b.ffsj) ");
		
		query.append(mQuery.getQueryString());
		//���ſ�ʼʱ��(��ʼ)
		if(!Base.isNull(model.getKssj())){
			query.append(" and ffsj >= "+model.getKssj());
		}
		//���ſ�ʼʱ��(����)
		if(!Base.isNull(model.getJssj())){
			query.append(" and ffsj <= "+model.getJssj());
		}
		//���Ž���ʱ��(��ʼ)
		if(!Base.isNull(model.getFfjskssj())){
			query.append(" and ffjssj >= "+model.getFfjskssj());
		}
		//���Ž���ʱ��(����)
		if(!Base.isNull(model.getFfjsjssj())){
			query.append(" and ffjssj >= "+model.getFfjsjssj());
		}
		String[]colList={"xn","xqmc","nd","xmlxmc","xmmc","ffsj","ffjssj","xffrs","yffrs","wffrs","ytz","yfs","ypj","wpj","pkValue"};
	
		return CommonQueryDAO.commonQuery(sql.toString(),query.toString(), mQuery.getInputList(), colList, model);
	}
	
	public List<String[]>getTjxxOne(RcswForm model) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		StringBuilder userSql=new StringBuilder();
		MakeQuery mQuery=new MakeQuery();
		User user=model.getUser();
		String userStatus=user.getUserStatus();
		
		String[]col={"nj","xydm","zydm","bjdm"};
		String[]colLikeList={"xh","xm"};
		mQuery.makeQuery(col, colLikeList, model);
		sql.append(" select rownum r,a.xh,a.xm,a.nj,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc from view_xsjbxx a  ");
		sql.append(mQuery.getQueryString());
		sql.append(" and exists (select 1 from(");
		sql.append(getQueryTj(model));
		sql.append(" )b ");
		
		sql.append(" where 1=1 ");
		sql.append(" and a.xh=b.xhzgh) ");
		
		if("jd".equalsIgnoreCase(userStatus)){
			userSql.append(" and (exists(select 1 from fdybjb b where a.bjdm=b.bjdm and zgh='"+user.getUserName()+"')");
			userSql.append(" or exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and zgh='"+user.getUserName()+"')) ");
		}else if("fdy".equalsIgnoreCase(userStatus)){
			userSql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and zgh='"+user.getUserName()+"')");
		}else if("bzr".equalsIgnoreCase(userStatus)){
			userSql.append(" and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and zgh='"+user.getUserName()+"')");
		}else if("xy".equalsIgnoreCase(userStatus)){
			userSql.append(" and xydm='"+user.getUserDep()+"'  ");
		}else if("stu".equalsIgnoreCase(userStatus)){
			userSql.append(" and xh='"+user.getUserName()+"'");
		}
		sql.append(userSql);
		String[] colList={"xh","xm","nj","xymc","zymc","bjmc"};
		return CommonQueryDAO.commonQuery(sql.toString(), "", mQuery.getInputList(), colList, model);
	}
	
	public String getQueryTj(RcswForm model){
		String cxxx=model.getCxxx();
		String tableName="";
		if("xffrs".equalsIgnoreCase(cxxx)){
			tableName=" select a.* from xg_swff_ffryb a where 1=1";
			tableName+="  and xn||'!!@@!!'||xq||'!!@@!!'||nd||'!!@@!!'||xmlx||'!!@@!!'||ffsj= ";
			tableName+="'"+model.getPk()+"'";
		}else if("yffrs".equalsIgnoreCase(cxxx)){
			tableName=" select a.* from rcsw_swffrqwhb a where 1=1 ";
			tableName+="  and xn||'!!@@!!'||xq||'!!@@!!'||nd||'!!@@!!'||xmlx||'!!@@!!'||ffsj= ";
			tableName+="'"+model.getPk()+"'";
		}else if("wffrs".equalsIgnoreCase(cxxx)){
			tableName=" select a.* from xg_swff_ffryb a ";
			tableName+="  where not exists(select 1 from rcsw_swffrqwhb b where a.xhzgh=b.xhzgh  ";
			tableName+="  and a.xn=b.xn and a.xq=b.xq  and a.nd=b.nd  and a.xmlx=b.xmlx and a.ffsj=b.ffsj)  ";
			tableName+="  and xn||'!!@@!!'||xq||'!!@@!!'||nd||'!!@@!!'||xmlx||'!!@@!!'||ffsj= ";
			tableName+="'"+model.getPk()+"'";
		}else if("ytz".equalsIgnoreCase(cxxx)){
			tableName=" select a.* from xg_rcsw_swfflq a where 1=1";
			tableName+="  and xn||'!!@@!!'||xq||'!!@@!!'||nd||'!!@@!!'||xmlx||'!!@@!!'||ffsj= ";
			tableName+="'"+model.getPk()+"'";
			tableName+=" and sftz='��֪ͨ'  ";
		}else if("yfs".equalsIgnoreCase(cxxx)){
			tableName=" select a.* from xg_rcsw_swfflq a where 1=1";
			tableName+="  and xn||'!!@@!!'||xq||'!!@@!!'||nd||'!!@@!!'||xmlx||'!!@@!!'||ffsj= ";
			tableName+="'"+model.getPk()+"'";
		}else if("ypj".equalsIgnoreCase(cxxx)){
			tableName=" select a.* from rcsw_swffrqwhb a where 1=1";
			tableName+="  and xn||'!!@@!!'||xq||'!!@@!!'||nd||'!!@@!!'||xmlx||'!!@@!!'||ffsj= ";
			tableName+="'"+model.getPk()+"'";
			tableName+=" and mycd is not null  ";
		}else if("wpj".equalsIgnoreCase(cxxx)){
			tableName=" select a.* from rcsw_swffrqwhb a where 1=1";
			tableName+="  and xn||'!!@@!!'||xq||'!!@@!!'||nd||'!!@@!!'||xmlx||'!!@@!!'||ffsj= ";
			tableName+="'"+model.getPk()+"'";
			tableName+=" and  mycd is null  ";
		}
		
		return tableName;
	}
}
