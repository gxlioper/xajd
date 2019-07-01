package xgxt.pjpy.ntzydx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.pjpy.tyb.zhszcp.action.PjpyZhszcpwhActionForm;
import xgxt.pjpy.tyb.zhszcp.dao.PjpyZctjszDAO;
import xgxt.pjpy.zjjd.JxjpdxxModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

public class PjpyNtzydxDAO {
	ArrayList<String> value = new ArrayList<String>();
	/**
	 * ��ȡ��ѯ����
	 * @param model
	 * @return StringBuilder
	 * */
	public StringBuilder getWhereSql(PjpyZhszcpwhActionForm model){
		value = new ArrayList<String>();
		String xh = model.getQuerylike_xh();
		String xm = model.getQuerylike_xm();
		String xn = model.getQueryequals_xn();
		String nd = model.getQueryequals_nd();
		String xq = model.getQueryequals_xq();
		String xydm = model.getQueryequals_xydm();
		String zydm = model.getQueryequals_zydm();
		String bjdm = model.getQueryequals_bjdm();
		String nj = model.getQueryequals_nj();
		
		StringBuilder sb = new StringBuilder("where 1=1 ");
		if(xydm !=null && !xydm.equalsIgnoreCase("")){
			sb.append( " and xydm=?");
			value.add(xydm);
		}
		if(zydm !=null && !zydm.equalsIgnoreCase("")){
			sb.append( " and zydm=?");
			value.add(zydm);
		}
		if(bjdm !=null && !bjdm.equalsIgnoreCase("")){
			sb.append( " and bjdm=?");
			value.add(bjdm);
		}
		if(nj !=null && !nj.equalsIgnoreCase("")){
			sb.append( " and nj=?");
			value.add(nj);
		}
		if(xh !=null && !xh.equalsIgnoreCase("")){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(xm !=null && !xm.equalsIgnoreCase("")){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
		}
		if(xn !=null && !xn.equalsIgnoreCase("")){
			sb.append( " and xn=?");
			value.add(xn);
		}
		if(nd !=null && !nd.equalsIgnoreCase("")){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(xq !=null && !xq.equalsIgnoreCase("")){
			sb.append( " and xq=?");
			value.add(xq);
		}
		return sb;
	}
	
	/**
	 * ��ȡ��ѧ��������Ϣ
	 * @param jxjpdModel
	 * @param resMap
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getJxjpdxx(JxjpdxxModel jxjpdModel,
			                                  HashMap<String, String> resMap){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		if (StringUtils.isNotNull(jxjpdModel.getXh())) {
			sql.append("select nvl(a.fs,0) zf,a.pm,(select mc from pjpy_zctjszb b where a.dm=b.dm) mc from pjpy_zhszcpb a where jb='1' and xh='");
			sql.append(jxjpdModel.getXh());
			sql.append("'");
			if (StringUtils.isNotNull(jxjpdModel.getXn())) {
				sql.append(" and xn='"); 
				sql.append(jxjpdModel.getXn());
				sql.append("'");
			}
			if (StringUtils.isNotNull(jxjpdModel.getXq())) {
				sql.append(" and xq='"); 
				sql.append(jxjpdModel.getXq());
				sql.append("'");
			}
		} else {
			System.out.println("���������,ѧ�Ų���Ϊ��!!!!!!");
		}
		
		resMap.putAll(dao.getMap(sql.toString(),new String[]{},new String[]{"zf","pm"}));
		
		//��ȡѧ������ѧ�ּ�����Ϣ
		sql = new StringBuilder("select pjxfjd from view_ntzydx_cjb where xn=? and xq=? and xh=?");
		resMap.put("pjxfjd", dao.getOneRs(sql.toString(), 
				                          new String[]{jxjpdModel.getXn(), jxjpdModel.getXq(), jxjpdModel.getXh()}, 
				                          "pjxfjd"));
		return resMap;
	}
	
	/**
	 * ��ȡѧ���ۺϲ�����ѧ����
	 * @param model
	 * @return int
	 * */
	public int getXnzhcpJxjs(JxjpdxxModel model){
		DAO dao = DAO.getInstance();
		String sql = StringUtils.joinStr("select count(*)num from view_pjpy_ty_zhcpjxjsq",
				          " a where to_number(nvl(jxjjb,0))<=(select to_number(nvl(jxjjb,0)) from jxjdmb where jxjdm=? ) ",
				          "and xn=? and xxsh='ͨ��'");
		String count = dao.getOneRs(sql, new String[]{model.getJxjdm(), model.getXn()}, "num");
		count = StringUtils.isNull(count) ? "0" : count;
		return Integer.parseInt(count);
	}
	
	/**
	 * ��ѯ�ۺϲ����ܷ���Ϣ
	 * @param pjzq
	 * @param jb
	 * @param map
	 * @param model
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]>  queryZhszcpzfForExp(String pjzq, 
									           String jb,
									           HashMap<String, String> map,
									           String[] output,
									           PjpyZhszcpwhActionForm model, 
									           boolean isPage) throws Exception{
		PjpyZctjszDAO zctjszDao = new PjpyZctjszDAO();
		String xmdm = map.get(jb);//��Ŀ����
		String[] xmdmArray = new String[]{};		
		List<HashMap<String, String>> list = zctjszDao.queryZhcpXmxx(jb,xmdmArray);
		String viewName = "view_pjpy_zhszcpb";//��ͼ
		StringBuilder sql = new StringBuilder("select a.*,a.xh||a.xn||a.xq pk,rownum r from (select xh,xm,nj,xymc,zymc,bjmc,xn,xq,xqmc,nd ");
		//��̬��ѯ�ֶ�
		int i=0;
		for(HashMap<String, String> xmMap : list){
			xmdm = xmMap.get("dm");
			output = StringUtils.joinStrArr(output, new String[]{"fs" + i, "pm" + i});
			
			sql.append(",max((select fs from pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
			sql.append(xmdm);
			sql.append("')) fs");
			sql.append(i);
			sql.append(",max((select pm from pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
			sql.append(xmdm);
			sql.append("')) pm");
			sql.append(i++);			
		}
		//�ܷ���Ϣ
		xmdm = "999";//Ĭ�ϵ��ִܷ���
		sql.append(",max((select fs from pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
		sql.append(xmdm);
		sql.append("')) fs");
		sql.append(i);
		sql.append(",max((select pm from pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
		sql.append(xmdm);
		sql.append("')) pm");
		sql.append(i);
		sql.append(",nvl(max((select nvl(cj,0)cj from view_ntzydx_cjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq)),0)cj");
		output = StringUtils.joinStrArr(output, new String[]{"fs" + i, "pm" + i,"cj"});
		
		sql.append(" from ");
		sql.append(viewName);
		sql.append(" a ");
		//��ѯ����
		sql.append(getWhereSql(model));
		sql.append(" group by xh,xm,nj,xymc,zymc,bjmc,xn,xq,xqmc,nd) a");
		
		if (isPage) {
			
			return CommonQueryDAO.commonQuery(sql.toString(), "", value != null ? value.toArray(new String[0]) : new String[]{}, output, model);
		}		
		return CommonQueryDAO.commonQueryNotFy(viewName, "" , value != null ? value.toArray(new String[0]) : new String[]{}, output, sql.toString(), model);
	}
	
	/**
	 * ��ѯѧ���ɼ���Ϣ
	 * @param model
	 * */
	public List<HashMap<String, String>> queryXscjxx(PjpyZhszcpwhActionForm model){
		DAO dao = DAO.getInstance();
		String sql = StringUtils.joinStr("select xh,xm,xymc,bjmc,pjxfjd,cj,",
				"(select round(sum(to_number(nvl(fs,'0')))/2,2)||'' from pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and dm='999')zf ",
				"from view_ntzydx_cjb a ");
		//ƽ��ѧ�ּ��㰴ѧ�꼼��
		model.setQueryequals_xq("");
		
		String sqlWhere = getWhereSql(model).toString();
		String[] inputValue = value != null ? value.toArray(new String[0]) : new String[]{};
		sql = StringUtils.joinStr(sql,
				sqlWhere
//				,
//				" and exists(select 1 from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq)"
				);
		sql += " union all select xh,xm,xymc,bjmc,'0' pjxfjd,'0' cj,(select round(sum(to_number(nvl(fs,'0')))/2,2)||'' from pjpy_zhszcpb b where a.xh=b.xh and b.xn=? and dm='999') zf from view_xsjbxx a where not exists(select 1 from view_ntzydx_cjb b where a.xh=b.xh ) and bjdm=? ";
		inputValue = StringUtils.joinStrArr(inputValue,new String[]{model.getQueryequals_xn(),model.getQueryequals_bjdm()});
		return dao.getList(sql, inputValue, new String[]{"xh","xm","xymc","bjmc","pjxfjd","cj","zf"});
	}
	
	/**
	 * ��ѯ�ۺϲ���ͳ�Ʊ����ͷ��Ϣ
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getZhcpxmfTitle(PjpyZhszcpwhActionForm model){
		DAO dao = DAO.getInstance();
		//ѧԺ����
		//�༶����
		//ѧ������
		String sql = StringUtils.joinStr("select ",
				"(select distinct xymc from view_njxyzybj where xydm=?) xymc,",
				"(select distinct xymc from view_njxyzybj where bjdm=?)bjmc,",
				"(select distinct xqmc from xqdzb where xqdm=?)xqmc from dual");
		return dao.getMap(sql, 
				new String[]{model.getQueryequals_xydm(), 
				             model.getQueryequals_bjdm(),
				             model.getQueryequals_xq()},
				new String[]{"xymc","bjmc","xqmc"});
	}
}
