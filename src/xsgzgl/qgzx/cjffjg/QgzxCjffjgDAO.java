package xsgzgl.qgzx.cjffjg;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import common.newp.ArrayUtil;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;
import xsgzgl.qgzx.mrgzkh.khjg.GzkhKhjgForm;
/**
 * �ڹ���ѧ-������-�����Ϣ����
 * @author yeyipin
 * @since 2012.7.23
 */
public class QgzxCjffjgDAO extends SuperDAOImpl<QgzxCjffjgForm> {
	
	
	DAO dao = DAO.getInstance();
	
	
	/**
	 * ��ó����Ϣ�б�
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCjxxList(QgzxCjffjgForm myForm) throws Exception {
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// �û�����
		User user = myForm.getUser();
		String[] colList = new String[] { "pkValue", "r", "xn","yrdwmc", "ffny", "yjsrs", "bksrs","ffje", "sjly" };
		/*if("12309".equals(Base.xxdm)){
			  colList = new String[] { "pkValue", "r", "xn","yrdwmc", "ffny", "yffrs", "ffrs","jfhbje","zcje","ffje","sjly" };
		}
		if("xq".equals(QgCommUtilf.getQgzq())){
			if("12309".equals(Base.xxdm)){
				  colList = new String[] { "pkValue", "r", "xn","xqmc","yrdwmc", "ffny", "yffrs", "ffrs","jfhbje","zcje","ffje","sjly" };
			}else {
				  colList = new String[] { "pkValue", "r", "xn","xqmc","yrdwmc", "ffny", "yffrs", "ffrs","ffje", "sjly" };
			}
		}*/
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//Ȩ�޿��� 	
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//��������ڹ�����Ա
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			searchTjQx+=" and zgh = '"+user.getUserName()+"' ";
		}		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, rownum r from (select decode(a.sjly,'1','����') sjly,a.xn || '!!@@!!' || a.yrdwdm || '!!@@!!' || a.ffny || '!!@@!!' || nvl(a.xq,'none') pkValue," +
				"a.xn,xq.xqmc,a.xq,a.yrdwdm,(select bmmc from view_xg_qgzx_yrdwdmb b where a.yrdwdm = b.bmdm) yrdwmc," +
				"a.ffny,substr(a.ffny, 0, 4) nd,substr(a.ffny, 6, 2) yf," +
				"case when a.tjzt = '1' then '���ύ' else 'δ�ύ' end as tjzt," +
				"(select count(1) from (select a.yf,b.xn,c.XYDM yrdwdm,a.xq from xg_qgzx_jcffsqb a left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id left join view_xsjbxx e on a.xh = e.xh where e.pycc = '1' or e.pycc = '2') d" +
				" where a.xn = d.xn and a.yrdwdm = d.yrdwdm and a.ffny = d.yf and (a.xq is null or a.xq = d.xq)) yjsrs,"+
				"(select count(1) from (select a.yf,b.xn,c.XYDM yrdwdm,a.xq from xg_qgzx_jcffsqb a left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id left join view_xsjbxx e on a.xh = e.xh where e.pycc = '3') d" +
				" where a.xn = d.xn and a.yrdwdm = d.yrdwdm and a.ffny = d.yf and (a.xq is null or a.xq = d.xq)) bksrs,(select sum(je)from " +
				"(select a.yf,a.je,b.xn,c.xydm yrdwdm,a.xq from xg_qgzx_jcffsqb a left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id) e " +
				"where a.xn = e.xn and a.yrdwdm = e.yrdwdm and a.ffny = e.yf and (a.xq is null or a.xq = e.xq)) ffje, " +
				" (select sum(jfhbje) from (select a.yf, a.je,a.jfhbje,a.zcje, b.xn, b.yrdwdm from xg_qgzx_jcffb a  left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm) e " +
				" where a.xn = e.xn and a.yrdwdm = e.yrdwdm and a.ffny = e.yf) jfhbje," +
				" (select sum(zcje) from (select a.yf, a.je,a.jfhbje,a.zcje, b.xn, b.yrdwdm from xg_qgzx_jcffb a  left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm) e " +
				" where a.xn = e.xn and a.yrdwdm = e.yrdwdm and a.ffny = e.yf) zcje,(select t.zgh from xg_qgzx_yrdwdmb t where t.xydm = a.yrdwdm ) zgh " +
				" from xg_qgzx_gwffztb a " +
				" left join xqdzb xq on a.xq = xq.xqdm " +
				"order by a.xn, a.tjzt, a.yrdwdm, a.ffny desc) a where 1 = 1");
		// ====================SQLƴװ end================================
			return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}
	
	/**
	 * ������˲����б�
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getYrbm(QgzxCjffjgForm model) {
		String sql = " select distinct bmdm,bmmc from view_xg_qgzx_yrdwdmb where bmmc<>'δȷ��' order by bmdm ";
		return dao.getList(sql, new String[]{}, new String[]{"bmdm","bmmc"});
	}
	
	/**
	 * ����û����ڲ���
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getYrbmOfUser(User user) {
		String sql = " select distinct bmdm,bmmc from view_xg_qgzx_yrdwdmb where bmdm=?";
		return dao.getList(sql, new String[]{user.getUserDep()}, new String[]{"bmdm","bmmc"});
	}
	
	/**
	 * ����ѧ�����˲��Ŵ����ø�λ�����б�
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getGwdm(QgzxCjffjgForm model) {
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append(" select gwdm,gwmc from xg_qgzx_gwxxb where xn = ? and yrdwdm = ?");
		if ("12309".equals(Base.xxdm)) {
			sql.append(" and (gwjssj='' or gwjssj is null or gwjssj>=to_char(sysdate,'yyyymmdd'))");
		}
		paraList.add(model.getXn());
		paraList.add(model.getYrbm());
		if(StringUtils.isNotNull(model.getXq())){
			sql.append(" and xq = ? ");
			paraList.add(model.getXq());
		}
		sql.append(" order by gwdm ");
		return dao.getList(sql.toString(), paraList.toArray(new String[]{}), new String[]{"gwdm","gwmc"});
	}

	
	/**
	 * ��λ��ԱList
	 * @param myForm
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwryList(QgzxCjffjgForm model,String query) throws Exception {
		
		// ============= ���������ѧ���Ƿ��ڸ� < ============
		StringBuffer ffnySql = new StringBuffer();
		if("12309".equals(Base.xxdm)){
			ffnySql.append(" select a.*,b.gwcjbz,b.jfhb,b.zc,case when a.zgzt='zg' and b.sfyxgw='��' then 'tg' else a.zgzt end zgztnew  ");
			ffnySql.append(" from ");
			ffnySql.append(" view_xg_qgzx_xsgwxxb a  ");
			ffnySql.append(" left join ( ");
			ffnySql.append("    select  ");
			ffnySql.append("a.gwdm, ");
			ffnySql.append("a.gwjssj, ");
			ffnySql.append("a.sfsgwsqsxz,a.gwcjbz,a.jfhb,a.zc,");
			ffnySql.append("case when a.gwjssj='' or a.gwjssj is null or a.gwjssj>=replace(?,'-','')||'31' then '��' else '��' end sfyxgw ");
			ffnySql.append("    from xg_qgzx_gwxxb a ");
			ffnySql.append("  ) b on a.gwdm=b.gwdm ");
		}else {
		
		ffnySql.append(" select a.*,case when a.zgzt='zg' and b.sfyxgw='��' then 'tg' else a.zgzt end zgztnew  ");
			
			/**
		 * ���ݴ�ѧ���Ի�
		 */
		if("10351".equals(Base.xxdm)){
			ffnySql.append(",case when xh in (select xh from XG_XSZZ_NEW_KNSJGB " +
					" where xn = (select rdxn from xg_xszz_new_knsjbszb where rownum = 1) " +
					" and xq = (select nvl(rdxq, 'on') from xg_xszz_new_knsjbszb where rownum = 1)) " +
					" then '������' " +
					" else '��������' end sfkns");
			
		}
			
			ffnySql.append(" from ");
			ffnySql.append(" view_xg_qgzx_xsgwxxb a  ");
			ffnySql.append(" left join ( ");
			ffnySql.append("    select  ");
			ffnySql.append("a.gwdm, ");
			ffnySql.append("a.gwjssj, ");
			ffnySql.append("a.sfsgwsqsxz, ");
			ffnySql.append("case when a.gwjssj='' or a.gwjssj is null or a.gwjssj>=replace(?,'-','')||'31' then '��' else '��' end sfyxgw ");
			ffnySql.append("    from xg_qgzx_gwxxb a ");
			ffnySql.append("  ) b on a.gwdm=b.gwdm ");
		}
		
		// ============= ���������ѧ���Ƿ��ڸ� > ============
		StringBuffer sql = new StringBuffer();
		String[] colList ;
		
		//�㽭�����Ի�
		if("10338".equals(Base.xxdm)){
			sql.append("select rownum r, a.* from");
			sql.append("(select a.xh,a.xm,a.bjmc,a.xn,a.xq,a.yrdwdm,a.gwdm,a.gwmc,a.gwxzmc,a.gwcjsx,a.sgsj,a.tgsj,");
			sql.append("a.zgztnew zgzt, decode(a.zgztnew, 'zg','�ڸ�','tg','�˸�','') zgztmc, ");
			sql.append("b.gs,b.je,b.bz,b.khdj,b.zhdlgs,(select dl from xg_qgzx_cjdlpzb where lb = '��������') jcdl,");
			sql.append("(select dl from xg_qgzx_cjdlpzb where lb = '�ۺϹ���') zhdl,b.jcdlgs,");
			sql.append("(select dl from xg_qgzx_cjdlpzb d where nvl(c.dcmc, '��������')=d.lb) kndjdl ");
			sql.append("from ( ");
			sql.append(ffnySql.toString());
			sql.append(") a ");
			sql.append("left join (select * from xg_qgzx_jcffb where yf=?) b on a.gwdm = b.gwdm and a.xh = b.xh ");
			sql.append("left join (select * from (select a.xh,(select dcmc from xg_xszz_new_kndcdmb b where a.rddc = b.dcdm) dcmc,");
			sql.append("row_number() over(partition by xh order by sqsj desc) rn from xg_xszz_new_knsjgb a where xn = '"+model.getXn()+"') ");
			sql.append("where rn = 1) c on a.xh = c.xh ");
			sql.append("order by gwdm,zgzt,sgsj desc,xh) a where 1=1 "+query);
			
			colList = new String[]{"r","xh","gwdm","xm","gwmc","gwxzmc","zgztmc","sgsj","gs","je","bz","gwcjsx","khdj","zhdlgs","jcdlgs","kndjdl","jcdl","zhdl"};
		}else if ("12309".equals(Base.xxdm)){
			sql.append("select rownum r, a.* from");
			sql.append("(select a.xh,a.xm,a.bjmc,a.xn,a.xq,a.yrdwdm,a.gwdm,a.gwmc,a.gwxzmc,a.gwcjsx,a.sgsj,a.tgsj,");
			sql.append("a.zgztnew zgzt, decode(a.zgztnew, 'zg','�ڸ�','tg','�˸�','') zgztmc, ");
			sql.append("a.gwcjbz,a.jfhb,a.zc,b.gs,b.je,b.bz,b.khdj from ( ");
			sql.append(ffnySql.toString());
			sql.append(") a ");
			sql.append("left join (select * from xg_qgzx_jcffb where yf=?) b on a.gwdm = b.gwdm and a.xh = b.xh ");
			if("10264".equals(Base.xxdm)) {
				sql.append("order by xh,gwdm,zgzt,sgsj desc) ");
			}else {
				sql.append("order by gwdm,zgzt,sgsj desc,xh) ");
			}
			sql.append("a where 1=1 "+query);
			colList = new String[]{"r","xh","gwdm","xm","gwmc","gwxzmc","zgztmc","sgsj","gs","jfhb","zc","je","bz","gwcjsx","gwcjbz","khdj"};
		}else{
			sql.append("select rownum r, a.* from");
			sql.append("(select a.xh,a.xm,a.bjmc,a.xn,a.xq,a.yrdwdm,a.gwdm,a.gwmc,a.gwxzmc,a.gwcjsx,a.sgsj,a.tgsj,");
			sql.append("a.zgztnew zgzt, decode(a.zgztnew, 'zg','�ڸ�','tg','�˸�','') zgztmc, ");
			if("10351".equals(Base.xxdm)){
				//���ݴ�ѧ�Ƿ��������ֶ�
				sql.append(" a.sfkns,");
			}
			sql.append("b.gs,b.je,b.bz,b.khdj from ( ");
			sql.append(ffnySql.toString());
			sql.append(") a ");
			sql.append("left join (select * from xg_qgzx_jcffb where yf=?) b on a.gwdm = b.gwdm and a.xh = b.xh ");
			if("10264".equals(Base.xxdm)) {
				sql.append("order by xh,gwdm,zgzt,sgsj desc) ");
			}else {
				sql.append("order by gwdm,zgzt,sgsj desc,xh) ");
			}
			sql.append("a where 1=1 "+query);
			colList = new String[]{"r","xh","gwdm","xm","gwmc","gwxzmc","zgztmc","sgsj","gs","je","bz","gwcjsx","khdj"};
			if("10351".equals(Base.xxdm)){
				colList = new String[]{"r","xh","gwdm","xm","gwmc","gwxzmc","zgztmc","sgsj","gs","je","bz","gwcjsx","khdj","sfkns"};
			}
		}
		
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "", new String[]{model.getFfny(),model.getFfny()},colList, model);
	}
	
	
	/**
	 * �����Ϣ��������
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean cjxxPlbc(List<String[]> params) throws SQLException {
		String sql = "insert into xg_qgzx_jcffb(xh,gwdm,yf,gs,je,bz)values(?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	public HashMap<String,String> getFfzt(QgzxCjffjgForm model) throws SQLException {
		String sql = "select * from xg_qgzx_gwffztb where xn=? and yrdwdm=? and ffny=?";
		return dao.getMapNotOut(sql.toString(), new String[]{model.getXn(),model.getYrbm(),model.getFfny()});
	}
	
	
	/**
	 * ����Ƿ񳬹�ʣ����
	 * @param myForm
	 * @param zje
	 * @return
	 */
	public boolean isOver(QgzxCjffjgForm model, double zje) {
		String sql=null;
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			sql = "select nvl(sum(syje),'0') syje from view_xg_qgzx_jfhbb_yf where yrdwdm = ? ";
		}else{
			sql = "select nvl(sum(syje),'0') syje from view_xg_qgzx_jfhbb_nd where yrdwdm = ? ";
		}
		String[] inputValue = {model.getYrbm()};
		String num = dao.getOneRs(sql, inputValue, "syje");
		if(num==null||"".equalsIgnoreCase(num)){
			num="0";
		}
		return Double.parseDouble(num)<zje;
	}
	
	
	/**
	 * ��ø�λ��ϢMAP
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getGwxxMap(QgzxCjffjgForm model) {
		String sql = "select * from view_xg_qgzx_gwxxb where gwdm = ? and rownum = 1 ";
		String[] inputValue = {model.getGwdm()};
		return dao.getMapNotOut(sql, inputValue);
	}
	
	
	/**
	 * ��ø�λ��������
	 * @param model
	 * @return
	 */
	public String getGwffny(QgzxCjffjgForm model){
		String sql = "select * from xg_qgzx_gwffztb where xn = ? and yrdwdm = ? and tjzt = '1' ";
		String[] inputValue = {model.getXn(),model.getYrbm()};
		String[] outputValue={"ffny"};
		return dao.getStringToSplit(sql, inputValue, outputValue);
	}
	
	
	/**
	 * ɾ����𷢷���Ϣ
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public boolean scXsCjffxx(List<String[]> params) throws Exception {
		boolean flag = false;
		String sql = "delete from xg_qgzx_jcffb a where  xh = ? and gwdm = ? and yf = ? ";
		int[] result = dao.runBatch(sql, params);
		flag = dao.checkBatchResult(result);
		return flag;
	}
	
	/**
	 * ���������𷢷�(���������Ի�12309)
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean bcCjffxxforWcsy(List<String[]> params) throws SQLException {
		String sql = "insert into xg_qgzx_jcffb(xh,gwdm,yf,gs,je,bz,khdj,cjffr,xn,xq,jfhbje,zcje)values(?,?,?,?,?,?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * ���������𷢷�
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean bcCjffxx(List<String[]> params) throws SQLException {
		String sql = "insert into xg_qgzx_jcffb(xh,gwdm,yf,gs,je,bz,khdj,cjffr,jcdlgs,zhdlgs,xn,xq)values(?,?,?,?,?,?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * �����λ����״̬
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean bcGwffZt(QgzxCjffjgForm model) throws Exception {
		boolean flag = true;
		String sql ="select count(1) num from xg_qgzx_gwffztb where xn = ? and yrdwdm = ? and ffny = ?";
		String[] inputValue = new String[]{model.getXn(),model.getYrbm(),model.getFfny()}; 
		String num = dao.getOneRs(sql, inputValue, "num");
		if("0".equalsIgnoreCase(num)){
			inputValue = new String[]{model.getXn(),model.getYrbm(),model.getFfny(),model.getTjzt(),model.getXq()};
			sql = "insert into xg_qgzx_gwffztb(xn,yrdwdm,ffny,tjzt,xq)values(?,?,?,?,?)";
			flag = dao.runUpdate(sql, inputValue);
		}else{
			inputValue = new String[]{model.getTjzt(),model.getXn(),model.getYrbm(),model.getFfny(),model.getXq()};
			sql = "update xg_qgzx_gwffztb set tjzt=? where xn = ? and yrdwdm = ? and ffny = ? and (xq = ? or xq is null or xq = '')";
			flag = dao.runUpdate(sql,inputValue);
		}
		return flag;
	}
	
	/**
	 * ɾ����𷢷���Ϣ
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public boolean scCjffxx(List<String[]> params) throws Exception {
		boolean flag = false;
		String sql = "delete from xg_qgzx_jcffb a where exists (select 1 from xg_qgzx_gwxxb b where a.gwdm = b.gwdm and xn = ? and yrdwdm = ?) and yf = ?  and (xq = ? or xq is null or xq = '')";
		int[] result = dao.runBatch(sql, params);
		flag = dao.checkBatchResult(result);
		if(flag){
			sql = "delete from xg_qgzx_gwffztb where xn = ? and yrdwdm = ? and ffny = ?  and (xq = ? or xq is null or xq = '') ";
			result = dao.runBatch(sql, params);
			flag = dao.checkBatchResult(result);
		}
		return flag;
	}
	/**
	 * 
	 * @����: �Ƿ��Ѿ��з�����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-22 ����04:11:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return
	 * boolean �������� 
	 */
	public boolean isHaveFfxx(List<String[]> qf){
		boolean isH=false;
		StringBuffer sb=new StringBuffer();
		sb.append(" select * from VIEW_NEW_DC_QGZX_CIFFSJWH a left join xg_qgzx_cjff b on  a.xh=b.xh and a.ffsj=b.ffsj and a.yrbm=b.yrbm and a.gwdm=b.gwdm");
		sb.append(" where a.xn=?");
		sb.append(" and a.yrbm=?");
		sb.append(" and a.ffsj=?");
		sb.append(" and (b.xq is null or b.xq = ''or b.xq = ?)");
		//2013-2014, 524000, 2013-11
		List<HashMap<String, String>> list=null;
		for(String[] str:qf){
			list=dao.getListNotOut(sb.toString(), str);
			if(null!=list&&list.size()>0){
				isH=true;
				break;
			}
		}
		return isH;
	}

	/**
	 * �����ύ��𷢷���Ϣ
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public boolean tjCjffxx(List<String[]> params) throws Exception {
		String sql = "update xg_qgzx_gwffztb set tjzt='1' where xn = ? and yrdwdm=? and ffny=? and (xq = ? or xq is null or xq = '') and tjzt <>'1'";
		boolean result =false;
		for (String[] param:params) {
			int i = dao.runUpdate(sql, param, "");
			if(i>0){
				result = delCjff(param);
				result = tjCjff(param);
			}
		}
		
		return result;
	}
	/**
	 * 
	 * @����:�����ύ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-27 ����08:03:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cxtj(List<String[]> params) throws Exception {
		String sql = "update xg_qgzx_gwffztb set tjzt='0' where xn = ? and yrdwdm=? and ffny=? and (xq is null or xq = '' or xq = ?)";
		boolean result =false;
		for (String[] param:params) {
			result = dao.runUpdate(sql, param);
			result = delCjff(param);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @����:�����ύ֮ǰɾ��������д��ڵ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-10-14 ����11:17:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	
	public boolean delCjff(String[] params) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from xg_qgzx_cjff where xn = ? and yrbm = ? and ffsj = ? and (xq = ? or xq is null or xq = '') and sjbs = '1' ");
		
		return dao.runUpdate(sql.toString(), params);
		
	}
	
	
	/**�����ύ��𷢺����������xg_qgzx_cjff ���ݽ���ά��
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public boolean tjCjff(String[] params) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into xg_qgzx_cjff (xh,  xn, yrbm, gwdm, zgzt, gs, je,  ffsj,bz,sjbs,khdj,xq,jfhbje,zcje) ");
		sql.append(" select a.xh, a.xn, a.yrdwdm, a.gwdm, b.zgzt, a.gs, a.je, a.yf, a.bz,'1'sjbs,a.khdj,a.xq,a.jfhbje,a.zcje ");
		sql.append(" from (select a.*, b.gwmc, c.xydm yrdwdm from xg_qgzx_jcffb a, xg_qgzx_gwxxb b ,xg_qgzx_yrdwdmb c where a.gwdm = b.gwdm and b.yrdwid = c.id) a	");
		sql.append(" left join (select * from xg_qgzx_xsgwxxb where zgzt = 'zg') b");
		sql.append(" on a.xh = b.xh and a.gwdm = b.gwdm  ");
		sql.append(" where a.xn = ?  and a.yrdwdm = ? and a.yf = ? and (a.xq is null or a.xq = '' or a.xq = ?) ");
		return dao.runUpdate(sql.toString(), params);
	}

	/**
	 * �Զ������ύ��𷢷���Ϣ�����ڵģ�
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public boolean aotoTjCjffxx() throws Exception {
		String conditionSql = "select xn,yrdwdm,ffny yf,xq from xg_qgzx_gwffztb t where t.tjzt=0";
		List<String[]> params = dao.rsToListNotOut(conditionSql, new String[]{});
		if(params==null || params.size()==0){
			return false;
		}
		return this.tjCjffxx(params);
	}
	
	
	/**
	 * �����Ϣ�鿴
	 * @param model
	 * @return
	 */
	public HashMap<String, String> cjxxCk(QgzxCjffjgForm model) {
		StringBuilder sql=new StringBuilder();
		sql.append("select a.xn,a.yrdwdm,a.xq,b.bmmc yrdwmc, b.dwfzr,b.lxdh,");
		sql.append("a.ffny,a.tjzt,case when a.tjzt = '1' then '���ύ'else 'δ�ύ'end as tjztmc ");
		sql.append("from xg_qgzx_gwffztb a  left join view_xg_qgzx_yrdwdmb b on a.yrdwdm = b.bmdm ");
		sql.append(" where xn = ? and yrdwdm = ? and ffny = ? and (xq is null or xq = '' or xq = ?) and rownum = 1 ");
		String[] inputValue = model.getPkValue().split("!!@@!!");
		
		return dao.getMapNotOut(sql.toString(), inputValue);
	}

	
	/**
	 * �����Ϣ������ϸ
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getCjmxList(QgzxCjffjgForm model) {
		StringBuilder sql=new StringBuilder();
		sql.append("select rownum r,a.*,b.xm,b.yhkh,b.bjmc from (select a.*,z.xm cjffrXm,(select gwmc from xg_qgzx_gwxxb b ");
		sql.append("where a.gwdm = b.gwdm)gwmc,(select t1.gwxzmc from xg_qgzx_gwxzdmb t1,xg_qgzx_gwxxb t2 where t1.gwxzdm=t2.gwlb ");
		sql.append("and t2.gwdm=a.gwdm) gwxzmc from xg_qgzx_jcffb a left join yhb z on z.yhm = a.cjffr where exists ");
		sql.append("(select 1 from xg_qgzx_gwxxb b left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id where  a.gwdm = b.gwdm and xn = ? and c.xydm = ?) and yf = ? and (xq is null or xq = '' or xq =?) ");
		sql.append("order by xh,gwdm) a left join (select a.xh, a.xm, a.xb, b.nj, b.xydm, b.zydm, a.bjdm,b.bjmc, a.yhkh from xsxxb a ");
		sql.append("left join view_njxyzybj_all b on a.bjdm=b.bjdm) b on a.xh = b.xh where 1=1 ");
		String[] inputValue = model.getPkValue().split("!!@@!!");
		String[] outputValue={"r","xh","xm","gwmc","gwxzmc","gs","je","yhkh","bz","cjffrXm","bjmc"};
		List<String> list=new ArrayList<String>();
		for(String input:inputValue){
			list.add(input);
		}
		if(null!=model.getXh()&&!model.getXh().trim().isEmpty()){
			sql.append("and (a.xh like '%'||?||'%' or b.xm like '%'||?||'%' )");
			list.add(model.getXh().trim());
			list.add(model.getXh().trim());
		}
		return dao.getList(sql.toString(), list.toArray(new String[] {}), outputValue);
	}
	
	/**
	 * ��֤��𷢷���Ϣ
	 * @param model
	 * @return
	 */
	public String yzCjffxx(QgzxCjffjgForm model) {
		String querySql = " and zgzt ='zg' ";
		if(!Base.isNull(model.getXn())){
			querySql+= " and xn = '"+model.getXn()+"'" ;
		}
		if(!Base.isNull(model.getYrbm())){
			querySql+=  " and yrdwdm = '"+model.getYrbm()+"'" ;
		}
		if(!Base.isNull(model.getGwdm())){
			querySql+= " and gwdm = '"+model.getGwdm()+"'" ;
		}
		String sql2 ="select nvl(sum(je),0) as usedje  from (select a.xh,a.xm,a.bjmc,a.xn,a.yrdwdm,a.gwdm,a.gwmc," +
				" a.sgsj,a.tgsj,a.zgzt,a.zgztmc,b.gs,b.je,b.bz from view_xg_qgzx_xsgwxxb a" +
				" left join (select * from xg_qgzx_jcffb where yf = ?) b on a.gwdm = b.gwdm and a.xh = b.xh" +
				" order by gwdm, zgzt desc, xh) a where 1 = 1 " + querySql;
		String[] inputValue2 = new String[]{model.getFfny()};
		String usedje = dao.getOneRs(sql2, inputValue2, "usedje");
		
		String sql=null;
		if ("12309".equals(Base.xxdm)) {
			if("1".equals(new QgzxCsszService().getJfhbfs())){
				sql ="select wcsysyje from view_xg_qgzx_jfhbb_yf a where yf = ? and yrdwdm = ?";
			}else{
				sql ="select wcsysyje from view_xg_qgzx_jfhbb_nd a where yf = ? and yrdwdm = ?";
			}
		}else {
			if("1".equals(new QgzxCsszService().getJfhbfs())){
				sql ="select syje from view_xg_qgzx_jfhbb_yf a where yf = ? and yrdwdm = ?";
			}else{
				sql ="select syje from view_xg_qgzx_jfhbb_nd a where yf = ? and yrdwdm = ?";
			}
		}
		String[] inputValue = new String[]{model.getFfny().substring(0, 4),model.getYrbm()};
		//�㽭��ְͨҵѧԺgexingh
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			inputValue = new String[]{model.getFfny(),model.getYrbm()};
		}
		String syje;
		if ("12309".equals(Base.xxdm)) {
			syje = dao.getOneRs(sql, inputValue, "wcsysyje");
		}else {
			
			syje = dao.getOneRs(sql, inputValue, "syje");
		}
		//����ʹ��ʣ����+����ҳ��û�޸�֮ǰ�õ��Ľ�����ж�
		if ("12309".equals(Base.xxdm)) {
			if(Base.isNull(syje)){
				return "�ò���δ�о��ѻ�������ȷ��";
			}else{
				if(!Base.isNull(model.getJe())&&!Base.isNull(model.getZc())){
					if (Double.parseDouble(model.getJe())-Double.parseDouble(model.getZc())>Double.parseDouble(syje)+Double.parseDouble(usedje)){
						return "��𷢷��ܽ��ܴ��ڸ����˵�λ��ʣ�ྭ��";
					}
				}
			}
		}else {
			if(Base.isNull(syje)){
				return "�ò���δ�о��ѻ�������ȷ��";
			}else if(Double.parseDouble(Base.isNull(model.getJe())?"0":model.getJe())>Double.parseDouble(syje)+Double.parseDouble(usedje)){
				return "��𷢷��ܽ��ܴ��ڸ����˵�λ��ʣ�ྭ��";
			}
		}
		
		return "";
	}

	/**
	 * ��ø�λ�·�
	 * @param model
	 * @return
	 */
	public String getStringToSplit(QgzxCjffjgForm model) {
		String sql = "select ffny from xg_qgzx_gwffztb where gwdm = ?";
		return dao.getStringToSplit(sql, new String[]{model.getGwdm()}, new String[]{"ffny"});
	}

	/**
	 * ������������Ѽ���
	 * @param xh
	 * @return
	 */
	public String getXmzzjb(String xh) {
		String sql = "select * from xszz_shztb where xmdm = '5002' and xh = ?";
		return dao.getOneRs(sql, new String[]{xh}, "xmzzjb");
	}

	/**
	 * ��ó���׼��Ϣ
	 * @param zd1
	 * @param zd2
	 * @param zd3
	 * @param zd4
	 * @param xxdm
	 * @return
	 */
	public String getCjbz(String[] inputValue) {
		String sql = "select * from xg_qgzx_cjbzpzb where zd1=? and zd2=? and xxdm = ?";
		return dao.getOneRs(sql, inputValue, "cjbz");
	}

	/**
	 * @����:��ȡ�и�λ�Ĳ����б�
	 * @���ߣ�������[���ţ�964]
	 * @���ڣ�2014-4-21 ����04:48:19
	 * @param model
	 * @return �����б� List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getYrbmYgw(QgzxCjffjgForm model) {
		
		// ��ȡ�и�λ�Ĳ����б�
		List<HashMap<String, String>> lst = new ArrayList<HashMap<String, String>>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct t1.yrdwdm bmdm, t2.bmmc ");
		sql.append("   from XG_QGZX_GWXXB t1 ");
		sql.append("   left join view_xg_qgzx_yrdwdmb t2 ");
		sql.append("     on t1.yrdwdm = t2.bmdm ");
		sql.append(" order by t1.yrdwdm  ");
		lst = dao.getList(sql.toString(), new String[] {}, new String[] { "bmdm", "bmmc" });
		return lst;
	}

	
	/**
	 * 
	 * @����:��ѯ���Ѷ��٣��Ѿ�ʹ�ö��٣�ʣ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-21 ����03:53:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getJftx(User user){
		
		StringBuilder sql = new StringBuilder();
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			sql.append("select sum(hbzje) hbzje,sum(yffje) yffje, sum(syje) syje from view_xg_qgzx_jfhbb_yf where nd = '"+Base.currNd+"'");
		}else{
			sql.append("select sum(hbzje) hbzje,sum(yffje) yffje, sum(syje) syje from view_xg_qgzx_jfhbb_nd where nd = '"+Base.currNd+"'");
		}
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		
		//��������ڹ�����Ա
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			sql.append(" and yrdwdm = '"+user.getUserDep()+"'");
		}	
		
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	/**
	 * 
	 * @����:��ȡ��Ա���»�ó��״��
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-10-27 ����11:15:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param query
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> �������� 
	 * @throws
	 */
	public HashMap<String,String> getCjxxByXh(String xh,String yf,String[] gwdm) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(je) zje,sum(gs) zgs from(select rownum r, a.* from"); 
		sql.append("(select a.xh,a.gwdm," );
		sql.append("b.gs,b.je from view_xg_qgzx_xsgwxxb a left join (select * from xg_qgzx_jcffb where yf=?) b on a.gwdm = b.gwdm and a.xh = b.xh " );
		sql.append("order by gwdm desc,xh) a where 1=1 and ");
		sql.append(" xh=? and gwdm not in(");
		for (int i = 0; i < gwdm.length; i++) {
			if(i<gwdm.length-1){
			   sql.append("'"+gwdm[i]+"'");
			   sql.append(",");
			}
			else{
				sql.append("'"+gwdm[i]+"'");	
			}
		}
		sql.append("))");
		return dao.getMapNotOut(sql.toString(), new String[]{yf,xh});
		
	}
	/**
	 * 
	 * @����:��ȡ��λ���³�𷢷��ܶ�
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-10-28 ����10:54:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param yf
	 * @param gwdm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getCjxxByGwdm(String gwdm,String yf,String[] xh) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(je) zje from(select rownum r, a.* from"); 
		sql.append("(select a.xh,a.gwdm," );
		sql.append("b.je from view_xg_qgzx_xsgwxxb a left join (select * from xg_qgzx_jcffb where yf=?) b on a.gwdm = b.gwdm and a.xh = b.xh " );
		sql.append("order by gwdm desc,xh) a where 1=1 and ");
		sql.append(" gwdm=? and xh not in(");
		for (int i = 0; i < xh.length; i++) {
			if(i<xh.length-1){
			   sql.append("'"+xh[i]+"'");
			   sql.append(",");
			}
			else{
				sql.append("'"+xh[i]+"'");	
			}
		}
		sql.append("))");
		return dao.getMapNotOut(sql.toString(), new String[]{yf,gwdm});
		
	}
	/**
	 * ��ȡѧ����𷢷���Ϣ
	 */
	public QgzxCjffjgForm getCjffXx(GzkhKhjgForm model)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_qgzx_cjff where xh=? and xn=? and gwdm=? and ffsj =? and zgzt='zg'");
		return super.getModel(sql.toString(),new String[]{model.getXh(),model.getXn(),model.getGwdm(),model.getGzrq().substring(0, 7)});
	}
	/**
	 * ��ȡ��λ����
	 * 
	 */
	public HashMap<String,String> getGwxx(String gwdm) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select gwmc,gwcjsx from xg_qgzx_gwxxb where gwdm = ?"); 
		return dao.getMapNotOut(sql.toString(), new String[]{gwdm});
}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QgzxCjffjgForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QgzxCjffjgForm t, User user) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(QgzxCjffjgForm.class);
		super.setKey("wbh");
		super.setTableName("XG_QGZX_CJFF");
	}


    public List<HashMap<String,String>> getCjffList(QgzxCjffjgForm t, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select a.*, rownum r from (select decode(a.sjly,'1','����') sjly,a.xn || '!!@@!!' || a.yrdwdm || '!!@@!!' || a.ffny || '!!@@!!' || nvl(a.xq,'none') pkValue,\n" +
				"a.xn,xq.xqmc,a.xq,a.yrdwdm,(select bmmc from view_xg_qgzx_yrdwdmb b where a.yrdwdm = b.bmdm) yrdwmc,\n" +
				"a.ffny,substr(a.ffny, 0, 4) nd,substr(a.ffny, 6, 2) yf,\n" +
				"case when a.tjzt = '1' then '���ύ' else 'δ�ύ' end as tjzt,\n" +
				"(select count(1) from (select a.yf,b.xn,c.XYDM yrdwdm,a.xq from xg_qgzx_jcffsqb a left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id left join view_xsjbxx e on a.xh = e.xh where e.pycc = '1' or e.pycc = '2') d\n" +
				" where a.xn = d.xn and a.yrdwdm = d.yrdwdm and a.ffny = d.yf and (a.xq is null or a.xq = d.xq)) yjsrs,\n" +
				"(select count(1) from (select a.yf,b.xn,c.XYDM yrdwdm,a.xq from xg_qgzx_jcffsqb a left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id left join view_xsjbxx e on a.xh = e.xh where e.pycc = '3') d\n" +
				" where a.xn = d.xn and a.yrdwdm = d.yrdwdm and a.ffny = d.yf and (a.xq is null or a.xq = d.xq)) bksrs,(select sum(je)from \n" +
				"(select a.yf,a.je,b.xn,c.xydm yrdwdm,a.xq from xg_qgzx_jcffsqb a left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id) e \n" +
				"where a.xn = e.xn and a.yrdwdm = e.yrdwdm and a.ffny = e.yf and (a.xq is null or a.xq = e.xq)) ffje, \n" +
				" (select sum(jfhbje) from (select a.yf, a.je,a.jfhbje,a.zcje, b.xn, b.yrdwdm from xg_qgzx_jcffb a  left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm) e \n" +
				" where a.xn = e.xn and a.yrdwdm = e.yrdwdm and a.ffny = e.yf) jfhbje,\n" +
				" (select sum(zcje) from (select a.yf, a.je,a.jfhbje,a.zcje, b.xn, b.yrdwdm from xg_qgzx_jcffb a  left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm) e \n" +
				" where a.xn = e.xn and a.yrdwdm = e.yrdwdm and a.ffny = e.yf) zcje \n" +
				" from xg_qgzx_gwffztb a \n" +
				" left join xqdzb xq on a.xq = xq.xqdm \n" +
				"order by a.xn, a.tjzt, a.yrdwdm, a.ffny desc) a where 1 = 1 ");
		sql.append(searchTj);
		ArrayUtil arrayutil = new ArrayUtil();
		//��������
		String[] inputVnew = arrayutil.unionArray(paraList.toArray(new String[]{}), inputV);
		return getPageList(t, sql.toString(), inputVnew);
    }
}
