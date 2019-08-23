package xsgzgl.qgzx.cjffsq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.tsqktbgl.sh.TsqkshForm;

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
public class QgzxCjffsqDAO extends SuperDAOImpl<QgzxCjffsqForm> {
	private ShlcInterface shlc = new CommShlcImpl();
	
	DAO dao = DAO.getInstance();
	
	
	/**
	 * ��ó����Ϣ�б�
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCjxxList(QgzxCjffsqForm myForm) throws Exception {
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// �û�����
		User user = myForm.getUser();
		String[] colList = new String[] { "pkValue", "r", "sqid", "splc", "xn","yrdwmc", "ffny", "yjsrs", "bksrs","ffje", "shztmc" };
		/*if("12309".equals(Base.xxdm)){
			  colList = new String[] { "pkValue", "r", "sqid", "splc", "xn","yrdwmc", "ffny", "yffrs", "ffrs","jfhbje","zcje","ffje","shztmc" };
		}
		if("xq".equals(QgCommUtilf.getQgzq())){
			if("12309".equals(Base.xxdm)){
				  colList = new String[] { "pkValue", "r", "sqid", "splc", "xn","xqmc","yrdwmc", "ffny", "yffrs", "ffrs","jfhbje","zcje","ffje","shztmc" };
			}else {
				  colList = new String[] { "pkValue", "r", "sqid", "splc", "xn","xqmc","yrdwmc", "ffny", "yffrs", "ffrs","ffje", "shztmc" };
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
		sql.append("select a.*, rownum r from (select a.xn || '!!@@!!' || a.yrdwdm || '!!@@!!' || a.ffny || '!!@@!!' || nvl(a.xq,'none') pkValue," +
				"a.shzt,decode(a.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����')shztmc,a.sqid,a.splc,"+
				"a.xn,xq.xqmc,a.xq,a.yrdwdm,(select bmmc from view_xg_qgzx_yrdwdmb b where a.yrdwdm = b.bmdm) yrdwmc," +
				"a.ffny,substr(a.ffny, 0, 4) nd,substr(a.ffny, 6, 2) yf," +
				"case when a.tjzt = '1' then '���ύ' else 'δ�ύ' end as tjzt," +
				"(select count(1) from (select a.yf,b.xn,c.XYDM yrdwdm,a.xq from xg_qgzx_jcffsqb a left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id left join view_xsjbxx e on a.xh = e.xh where e.pycc = '1' or e.pycc = '2') d" +
				" where a.xn = d.xn and a.yrdwdm = d.yrdwdm and a.ffny = d.yf and (a.xq is null or a.xq = d.xq)) yjsrs,"+
				"(select count(1) from (select a.yf,b.xn,c.XYDM yrdwdm,a.xq from xg_qgzx_jcffsqb a left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id left join view_xsjbxx e on a.xh = e.xh where e.pycc = '3') d" +
				" where a.xn = d.xn and a.yrdwdm = d.yrdwdm and a.ffny = d.yf and (a.xq is null or a.xq = d.xq)) bksrs,(select sum(je)from " +
				"(select a.yf,a.je,b.xn,c.xydm yrdwdm,a.xq from xg_qgzx_jcffsqb a left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id) e " +
				"where a.xn = e.xn and a.yrdwdm = e.yrdwdm and a.ffny = e.yf and (a.xq is null or a.xq = e.xq)) ffje, " +
				" (select sum(jfhbje) from (select a.yf, a.je,a.jfhbje,a.zcje, b.xn, b.yrdwdm from xg_qgzx_jcffsqb a  left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm) e " +
				" where a.xn = e.xn and a.yrdwdm = e.yrdwdm and a.ffny = e.yf) jfhbje," +
				" (select sum(zcje) from (select a.yf, a.je,a.jfhbje,a.zcje, b.xn, b.yrdwdm from xg_qgzx_jcffsqb a  left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm) e " +
				" where a.xn = e.xn and a.yrdwdm = e.yrdwdm and a.ffny = e.yf) zcje,(select t.zgh from xg_qgzx_yrdwdmb t where t.xydm = a.yrdwdm ) zgh " +
				" from xg_qgzx_gwffztsqb a " +
				" left join xqdzb xq on a.xq = xq.xqdm " +
				"order by a.xn, a.tjzt, a.yrdwdm, a.ffny desc) a where 1 = 1");
		// ====================SQLƴװ end================================
			return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}

	public List<HashMap<String,String>> export(QgzxCjffsqForm myForm,User user) throws Exception {
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// �û�����
//		User user = myForm.getUser();
		String[] colList = new String[] { "pkValue", "r", "sqid", "splc", "xn","yrdwmc", "ffny", "yjsrs", "bksrs","ffje", "shztmc" };
		/*if("12309".equals(Base.xxdm)){
			  colList = new String[] { "pkValue", "r", "sqid", "splc", "xn","yrdwmc", "ffny", "yffrs", "ffrs","jfhbje","zcje","ffje","shztmc" };
		}
		if("xq".equals(QgCommUtilf.getQgzq())){
			if("12309".equals(Base.xxdm)){
				  colList = new String[] { "pkValue", "r", "sqid", "splc", "xn","xqmc","yrdwmc", "ffny", "yffrs", "ffrs","jfhbje","zcje","ffje","shztmc" };
			}else {
				  colList = new String[] { "pkValue", "r", "sqid", "splc", "xn","xqmc","yrdwmc", "ffny", "yffrs", "ffrs","ffje", "shztmc" };
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
			searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, rownum r from (select a.xn || '!!@@!!' || a.yrdwdm || '!!@@!!' || a.ffny || '!!@@!!' || nvl(a.xq,'none') pkValue," +
				"a.shzt,decode(a.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����')shztmc,a.sqid,a.splc,"+
				"a.xn,xq.xqmc,a.xq,a.yrdwdm,(select bmmc from view_xg_qgzx_yrdwdmb b where a.yrdwdm = b.bmdm) yrdwmc," +
				"a.ffny,substr(a.ffny, 0, 4) nd,substr(a.ffny, 6, 2) yf," +
				"case when a.tjzt = '1' then '���ύ' else 'δ�ύ' end as tjzt," +
				"(select count(1) from (select a.yf,b.xn,c.XYDM yrdwdm,a.xq from xg_qgzx_jcffsqb a left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id left join view_xsjbxx e on a.xh = e.xh where e.pycc = '1' or e.pycc = '2') d" +
				" where a.xn = d.xn and a.yrdwdm = d.yrdwdm and a.ffny = d.yf and (a.xq is null or a.xq = d.xq)) yjsrs,"+
				"(select count(1) from (select a.yf,b.xn,c.XYDM yrdwdm,a.xq from xg_qgzx_jcffsqb a left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id left join view_xsjbxx e on a.xh = e.xh where e.pycc = '3') d" +
				" where a.xn = d.xn and a.yrdwdm = d.yrdwdm and a.ffny = d.yf and (a.xq is null or a.xq = d.xq)) bksrs,(select sum(je)from " +
				"(select a.yf,a.je,b.xn,c.xydm yrdwdm,a.xq from xg_qgzx_jcffsqb a left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id) e " +
				"where a.xn = e.xn and a.yrdwdm = e.yrdwdm and a.ffny = e.yf and (a.xq is null or a.xq = e.xq)) ffje, " +
				" (select sum(jfhbje) from (select a.yf, a.je,a.jfhbje,a.zcje, b.xn, b.yrdwdm from xg_qgzx_jcffsqb a  left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm) e " +
				" where a.xn = e.xn and a.yrdwdm = e.yrdwdm and a.ffny = e.yf) jfhbje," +
				" (select sum(zcje) from (select a.yf, a.je,a.jfhbje,a.zcje, b.xn, b.yrdwdm from xg_qgzx_jcffsqb a  left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm) e " +
				" where a.xn = e.xn and a.yrdwdm = e.yrdwdm and a.ffny = e.yf) zcje " +
				" from xg_qgzx_gwffztsqb a " +
				" left join xqdzb xq on a.xq = xq.xqdm " +
				"order by a.xn, a.tjzt, a.yrdwdm, a.ffny desc) a where 1 = 1 ");
		sql.append(searchTj);
		sql.append(" ");
		sql.append(searchTjQx);
		// ====================SQLƴװ end================================
		return dao.getListNotOut(sql.toString(),inputV);
	}
	/**
	 * ������˲����б�
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getYrbm(QgzxCjffsqForm model) {
		String sql = " select distinct bmdm,bmmc from view_xg_qgzx_yrdwdmb where bmmc<>'δȷ��' order by bmdm ";
		return dao.getList(sql, new String[]{}, new String[]{"bmdm","bmmc"});
	}
	
	/**
	 * ����û����������˵�λ
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getYrbmOfUser(User user) {
		String sql = " select distinct t1.bmdm,t1.bmmc from xg_qgzx_yrdwdmb t ";
			sql+=" left join ZXBZ_XXBMDM t1 on t.xydm = t1.bmdm ";
			sql+=" where t.zgh=?";
		return dao.getList(sql, new String[]{user.getUserName()}, new String[]{"bmdm","bmmc"});
	}
	
	/**
	 * ����ѧ�����˲��Ŵ����ø�λ�����б�
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getGwdm(QgzxCjffsqForm model) {
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append(" select t.gwdm,t.gwmc from xg_qgzx_gwxxb t");
		sql.append(" left join xg_qgzx_yrdwdmb t1 on t.yrdwid = t1.id ");
		sql.append(" where t.xn = ? and t1.xydm = ? and t.shzt = '1'");
		if ("12309".equals(Base.xxdm)) {
			sql.append(" and (gwjssj='' or gwjssj is null or gwjssj>=to_char(sysdate,'yyyymmdd'))");
		}
		paraList.add(model.getXn());
		paraList.add(model.getYrbm());
		if(StringUtils.isNotNull(model.getXq())){
			sql.append(" and t.xq = ? ");
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
	public ArrayList<String[]> getGwryList(QgzxCjffsqForm model,String query) throws Exception {

		StringBuffer sql = new StringBuffer();
		String[] colList ;
		sql.append(" select rownum r, a.* from (");
		sql.append(" select a.gwdm,a.xh,a.xm,a.pycc,a.gwmc,a.gwxzmc,a.zgzt,a.zgztmc,a.sgsj,nvl(a.gs,'0') gs,a.xn,a.xq,a.gwcjsx,a.yrdwdm");
		sql.append(" ,nvl(b.je,(a.gwcjsx / a.gssx) * nvl(a.gs,'0')) je,b.bz,b.khdj,b.yf,(a.gwcjsx / a.gssx) mxsgz ");
		sql.append(" from (select a.gwdm,a.xh,d.xm,case when d.pycc='1' then '��ʿ��' when d.pycc='2' then '�о���' when d.pycc='3' then '������' else d.pycc end pycc");
		sql.append("      ,e.gwmc,e.gssx,f.gwxzmc,a.zgzt,case when a.zgzt='zg' then '�ڸ�' when a.zgzt='tg' then '����ְ' else a.zgzt end zgztmc");
		sql.append("      ,a.sgsj,(select sum(gs) from XG_QGZX_MRKH_JGB t where t.gwdm = a.gwdm and t.xh = a.xh and substr(t.gzrq,0,7) = ?) gs");
		sql.append("      ,e.xn,e.xq,nvl(e.gwcjsx,'0') gwcjsx,b.xydm yrdwdm");
		sql.append("      from xg_qgzx_new_xsgwxxb a");
		sql.append("      left join xg_qgzx_gwxxb e on a.gwdm = e.gwdm");
		sql.append("      left join xg_qgzx_yrdwdmb b on e.yrdwid = b.id");
		sql.append("      left join ZXBZ_XXBMDM c on b.xydm = c.bmdm");
		sql.append("      left join view_xsjbxx d on a.xh = d.xh");
		sql.append("      left join XG_QGZX_GWXZDMB f on e.gwlb = f.gwxzdm");
		sql.append("      ) a");
		sql.append(" left join (select * from xg_qgzx_jcffsqb where yf = ?) b");
		sql.append(" on a.gwdm = b.gwdm and a.xh = b.xh");
		sql.append(" order by a.gwdm, a.zgzt, a.sgsj desc, a.xh) a");
		sql.append(" where 1=1 "+query);
		if("yll".equals(model.getSqzt())){//��¼��
			sql.append(" and exists (select 1 from xg_qgzx_jcffsqb t where a.xh = t.xh and a.gwdm = t.gwdm and a.yf = t.yf)");
		} else if("wll".equals(model.getSqzt())){//δ¼��
			sql.append(" and not exists (select 1 from xg_qgzx_jcffsqb t where a.xh = t.xh and a.gwdm = t.gwdm and a.yf = t.yf)");
		} else {//ȫ��
			sql.append(" and 1=1 ");
		}
		colList = new String[]{"r","xh","gwdm","xm","gwmc","gwxzmc","zgztmc","sgsj","gs","je","bz","gwcjsx","khdj","pycc","mxsgz"};

		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "", new String[]{model.getFfny(),model.getFfny()},colList, model);
	}
	
	
	/**
	 * �����Ϣ��������
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean cjxxPlbc(List<String[]> params) throws SQLException {
		String sql = "insert into xg_qgzx_jcffsqb(xh,gwdm,yf,gs,je,bz)values(?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	public HashMap<String,String> getFfzt(QgzxCjffsqForm model) throws SQLException {
		String sql = "select * from xg_qgzx_gwffztsqb where xn=? and yrdwdm=? and ffny=?";
		return dao.getMapNotOut(sql.toString(), new String[]{model.getXn(),model.getYrbm(),model.getFfny()});
	}
	
	
	/**
	 * ����Ƿ񳬹�ʣ����
	 * @param myForm
	 * @param zje
	 * @return
	 */
	public boolean isOver(QgzxCjffsqForm model, double zje) {
		String sql=null;
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			sql = "select nvl(sum(syje),'0') syje from view_xg_qgzx_jfhbb_sq_yf where yrdwdm = ? ";
		}else{
			sql = "select nvl(sum(syje),'0') syje from view_xg_qgzx_jfhbb_sq_yf where yrdwdm = ? ";
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
	public HashMap<String, String> getGwxxMap(QgzxCjffsqForm model) {
		String sql = "select * from view_xg_qgzx_gwxxb where gwdm = ? and rownum = 1 ";
		String[] inputValue = {model.getGwdm()};
		return dao.getMapNotOut(sql, inputValue);
	}
	
	
	/**
	 * @�������Ƴ�����������״̬��Ϊδ�ύ�����˻صļ�¼���Լ�������е����м�¼
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��6��14�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * String ��������
	 */
	public String getGwffny(QgzxCjffsqForm model){
		String sql = "select distinct ffny from (select ffny,xn,shzt,yrdwdm from xg_qgzx_gwffztsqb a union "
				+ "select ffny,xn,'6',yrdwdm from xg_qgzx_gwffztb b ) where shzt <> '0' and shzt<>'3' and xn = ? and yrdwdm = ? ";
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
		String sql = "delete from xg_qgzx_jcffsqb a where  xh = ? and gwdm = ? and yf = ? ";
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
		String sql = "insert into xg_qgzx_jcffsqb(xh,gwdm,yf,gs,je,bz,khdj,cjffr,xn,xq,jfhbje,zcje)values(?,?,?,?,?,?,?,?,?,?,?,?)";
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
		String sql = "insert into xg_qgzx_jcffsqb(xh,gwdm,yf,gs,je,bz,cjffr,xn,xq)values(?,?,?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * �����λ����״̬
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean bcGwffZt(QgzxCjffsqForm model) throws Exception {
		boolean flag = true;
		String sql ="select count(1) num from xg_qgzx_gwffztsqb where xn = ? and yrdwdm = ? and ffny = ?";
		String[] inputValue = new String[]{model.getXn(),model.getYrbm(),model.getFfny()}; 
		String num = dao.getOneRs(sql, inputValue, "num");
		if("0".equalsIgnoreCase(num)){
			inputValue = new String[]{model.getXn(),model.getYrbm(),model.getFfny(),model.getTjzt(),model.getXq()};
			sql = "insert into xg_qgzx_gwffztsqb(xn,yrdwdm,ffny,tjzt,xq)values(?,?,?,?,?)";
			flag = dao.runUpdate(sql, inputValue);
		}else{
			inputValue = new String[]{model.getTjzt(),model.getXn(),model.getYrbm(),model.getFfny(),model.getXq()};
			sql = "update xg_qgzx_gwffztsqb set tjzt=? where xn = ? and yrdwdm = ? and ffny = ? and (xq = ? or xq is null or xq = '')";
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
		String sql = "delete from xg_qgzx_jcffsqb a where exists (select 1 from xg_qgzx_gwxxb b where a.gwdm = b.gwdm and xn = ? and yrdwdm = ?) and yf = ?  and (xq = ? or xq is null or xq = '')";
		int[] result = dao.runBatch(sql, params);
		flag = dao.checkBatchResult(result);
		if(flag){
			sql = "delete from xg_qgzx_gwffztsqb where xn = ? and yrdwdm = ? and ffny = ?  and (xq = ? or xq is null or xq = '') ";
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
		String sql = "update xg_qgzx_gwffztsqb set tjzt='1' where xn = ? and yrdwdm=? and ffny=? and (xq = ? or xq is null or xq = '') and tjzt <>'1'";
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
	 * @�������ύ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��6��12�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * boolean ��������
	 */
	public boolean tjCjffxx(List<String[]> params,User user) throws Exception {
		String sql = "select sqid from xg_qgzx_gwffztsqb where xn = ? and yrdwdm=? and ffny=? and (xq = ? or xq is null or xq = '') and tjzt <>'1'";
		String sqid=dao.getOneRs(sql, params.get(0), "sqid");
		String splc=getSplcId();
		sql="update xg_qgzx_gwffztsqb set shzt='"+Constants.YW_SHZ+"',splc='"+splc+"'"
				+ " where xn = ? and yrdwdm=? and ffny=? and (xq = ? or xq is null or xq = '') and tjzt <>'1'";
		boolean result = dao.runUpdate(sql, params.get(0));
		if(result){
			result = shlc.runSubmit(sqid, splc, user.getUserName(), "qgzx_cjffsh_cjxxgl.do", "qgzx_cjffsq_cjxxgl.do");
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
		/*String sql = "update xg_qgzx_gwffztsqb set tjzt='0' where xn = ? and yrdwdm=? and ffny=? and (xq is null or xq = '' or xq = ?)";
		boolean result =false;
		for (String[] param:params) {
			result = dao.runUpdate(sql, param);
			result = delCjff(param);
		}*/
		boolean result =false;
		String sql = "select sqid from xg_qgzx_gwffztsqb where xn = ? and yrdwdm=? and ffny=? and (xq = ? or xq is null or xq = '') ";
		String sqid=dao.getOneRs(sql, params.get(0), "sqid");
		String splc=getSplcId();
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		result=shlc.firstStepCancle(sqid,splc);
		if(result){
			ShlcDao shlcdao = new ShlcDao();
			String shzt;
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				shzt=Constants.YW_YTH;
			}else{
				shzt=Constants.YW_WTJ;
			}
			sql="update xg_qgzx_gwffztsqb set shzt=? where sqid= ?";
			result = dao.runUpdate(sql, new String[]{shzt,sqid});
		};
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
		sql.append(" delete from xg_qgzx_cjff where xn = ? and yrbm = ? and ffsj = ? and sjbs = '1' ");
		
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
		sql.append(" from (select a.*, b.gwmc, b.yrdwdm from xg_qgzx_jcffsqb a, xg_qgzx_gwxxb b  where a.gwdm = b.gwdm) a	");
		sql.append(" left join (select * from xg_qgzx_xsgwxxb where zgzt = 'zg' union ");
		sql.append(" select * from xg_qgzx_xsgwxxb a where zgzt = 'tg' and not exists ");
		sql.append(" (select 1 from xg_qgzx_xsgwxxb b where a.gwdm = b.gwdm and a.xh = b.xh and zgzt = 'zg')) b");
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
		String conditionSql = "select xn,yrdwdm,ffny yf,xq from xg_qgzx_gwffztsqb t where t.tjzt=0";
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
	public HashMap<String, String> cjxxCk(QgzxCjffsqForm model) {
		StringBuilder sql=new StringBuilder();
		sql.append("select a.xn,a.yrdwdm,a.xq,(select bmmc from view_xg_qgzx_yrdwdmb b where a.yrdwdm = b.bmdm) yrdwmc,a.sqid,a.splc,a.shzt,");
		sql.append("a.ffny,a.tjzt,case when a.tjzt = '1' then '���ύ'else 'δ�ύ'end as tjztmc ");
		sql.append("from xg_qgzx_gwffztsqb a where xn = ? and yrdwdm = ? and ffny = ? and (xq is null or xq = '' or xq = ?) and rownum = 1 ");
		String[] inputValue = model.getPkValue().split("!!@@!!");
		
		return dao.getMapNotOut(sql.toString(), inputValue);
	}

	
	/**
	 * �����Ϣ������ϸ
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getCjmxList(QgzxCjffsqForm model) {
		StringBuilder sql=new StringBuilder();
		sql.append("select rownum r,a.*,b.xm,b.yhkh from (select a.*,z.xm cjffrXm,(select gwmc from xg_qgzx_gwxxb b ");
		sql.append("where a.gwdm = b.gwdm)gwmc,(select t1.gwxzmc from xg_qgzx_gwxzdmb t1,xg_qgzx_gwxxb t2 where t1.gwxzdm=t2.gwlb ");
		sql.append("and t2.gwdm=a.gwdm) gwxzmc from xg_qgzx_jcffsqb a left join yhb z on z.yhm = a.cjffr where exists ");
		sql.append("(select 1 from xg_qgzx_gwxxb b left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id where a.gwdm = b.gwdm and xn = ? and c.xydm = ?) and yf = ? and (xq is null or xq = '' or xq =?) ");
		sql.append("order by xh,gwdm) a left join (select a.xh, a.xm, a.xb, b.nj, b.xydm, b.zydm, a.bjdm, a.yhkh from xsxxb a ");
		sql.append("left join view_njxyzybj_all b on a.bjdm=b.bjdm) b on a.xh = b.xh where 1=1 ");
		String[] inputValue = model.getPkValue().split("!!@@!!");
		String[] outputValue={"r","xh","xm","gwmc","gwxzmc","gs","je","yhkh","bz","cjffrXm"};
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
	public String yzCjffxx(QgzxCjffsqForm model) {
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
				" left join (select * from xg_qgzx_jcffsqb where yf = ?) b on a.gwdm = b.gwdm and a.xh = b.xh" +
				" order by gwdm, zgzt desc, xh) a where 1 = 1 " + querySql;
		String[] inputValue2 = new String[]{model.getFfny()};
		String usedje = dao.getOneRs(sql2, inputValue2, "usedje");
		
		String sql=null;
		if ("12309".equals(Base.xxdm)) {
			if("1".equals(new QgzxCsszService().getJfhbfs())){
				sql ="select wcsysyje from view_xg_qgzx_jfhbb_sq_yf a where yf = ? and yrdwdm = ?";
			}else{
				sql ="select wcsysyje from view_xg_qgzx_jfhbb_sq_nd a where yf = ? and yrdwdm = ?";
			}
		}else {
			if("1".equals(new QgzxCsszService().getJfhbfs())){
				sql ="select syje from view_xg_qgzx_jfhbb_sq_yf a where yf = ? and yrdwdm = ?";
			}else{
				sql ="select syje from view_xg_qgzx_jfhbb_sq_nd a where yf = ? and yrdwdm = ?";
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
	public String getStringToSplit(QgzxCjffsqForm model) {
		String sql = "select ffny from xg_qgzx_gwffztsqb where gwdm = ?";
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
	public List<HashMap<String, String>> getYrbmYgw(QgzxCjffsqForm model) {
		
		// ��ȡ�и�λ�Ĳ����б�
		List<HashMap<String, String>> lst;
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct t2.xydm bmdm, t3.bmmc ");
		sql.append("   from XG_QGZX_GWXXB t1 ");
		sql.append("   left join xg_qgzx_yrdwdmb t2 ");
		sql.append("     on t1.yrdwid = t2.id ");
		sql.append("   left join ZXBZ_XXBMDM t3 on t2.xydm = t3.bmdm ");
		sql.append(" where t1.shzt = '1' and t2.dwlb = '01'");
		sql.append(" order by bmdm ");
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
			sql.append("select sum(hbzje) hbzje,sum(yffje) yffje, sum(syje) syje from view_xg_qgzx_jfhbb_sq_yf where nd = '"+Base.currNd+"'");
		}else{
			sql.append("select sum(hbzje) hbzje,sum(yffje) yffje, sum(syje) syje from view_xg_qgzx_jfhbb_sq_nd where nd = '"+Base.currNd+"'");
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
		sql.append("b.gs,b.je from view_xg_qgzx_xsgwxxb a left join (select * from xg_qgzx_jcffsqb where yf=?) b on a.gwdm = b.gwdm and a.xh = b.xh " );
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
		sql.append("b.je from view_xg_qgzx_xsgwxxb a left join (select * from xg_qgzx_jcffsqb where yf=?) b on a.gwdm = b.gwdm and a.xh = b.xh " );
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
	public QgzxCjffsqForm getCjffXx(GzkhKhjgForm model)throws Exception{
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
	public List<HashMap<String, String>> getPageList(QgzxCjffsqForm t) throws Exception {
		return null;
	}
	
	/*
    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	
	@Override
	public List<HashMap<String, String>> getPageList(QgzxCjffsqForm t, User user) throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(QgzxCjffsqForm.class);
		super.setKey("wbh");
		super.setTableName("XG_QGZX_CJFF");
		
	}
	
	public String getSplcId() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_QGZX_SPLCSSZB where rownum=1");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	
	public List<HashMap<String, String>> getCjxxShList(QgzxCjffsqForm t, User user)	throws Exception {
		SearchModel searchModel = t.getSearchModel();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//Ȩ�޿��� 	
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//��������ڹ�����Ա
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, rownum r from (select a.xn || '!!@@!!' || a.yrdwdm || '!!@@!!' || a.ffny || '!!@@!!' || nvl(a.xq,'none') pkValue," +
				"row_number() over(partition by a.sqid order by m.shsj desc) rn,a.sqid,a.splc,m.guid shid,m.gwid,m.shzt, "+
				" n.mc || '[' ||decode(m.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,"+
				"a.xn,xq.xqmc,a.xq,a.yrdwdm,(select bmmc from view_xg_qgzx_yrdwdmb b where a.yrdwdm = b.bmdm) yrdwmc," +
				"a.ffny,substr(a.ffny, 0, 4) nd,substr(a.ffny, 6, 2) yf," +
				"case when a.tjzt = '1' then '���ύ' else 'δ�ύ' end as tjzt," +
				"(select count(1) from (select a.yf,b.xn,c.XYDM yrdwdm,a.xq from xg_qgzx_jcffsqb a left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id left join view_xsjbxx e on a.xh = e.xh where e.pycc = '1' or e.pycc = '2') d" +
				" where a.xn = d.xn and a.yrdwdm = d.yrdwdm and a.ffny = d.yf and (a.xq is null or a.xq = d.xq)) yjsrs,"+
				"(select count(1) from (select a.yf,b.xn,c.XYDM yrdwdm,a.xq from xg_qgzx_jcffsqb a left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id left join view_xsjbxx e on a.xh = e.xh where e.pycc = '3') d" +
				" where a.xn = d.xn and a.yrdwdm = d.yrdwdm and a.ffny = d.yf and (a.xq is null or a.xq = d.xq)) bksrs,(select sum(je)from " +
				"(select a.yf,a.je,b.xn,c.xydm yrdwdm,a.xq from xg_qgzx_jcffsqb a left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm left join xg_qgzx_yrdwdmb c on b.yrdwid = c.id) e " +
				"where a.xn = e.xn and a.yrdwdm = e.yrdwdm and a.ffny = e.yf and (a.xq is null or a.xq = e.xq)) ffje, " +
				" (select sum(jfhbje) from (select a.yf, a.je,a.jfhbje,a.zcje, b.xn, b.yrdwdm from xg_qgzx_jcffsqb a  left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm) e " +
				" where a.xn = e.xn and a.yrdwdm = e.yrdwdm and a.ffny = e.yf) jfhbje," +
				" (select sum(zcje) from (select a.yf, a.je,a.jfhbje,a.zcje, b.xn, b.yrdwdm from xg_qgzx_jcffsqb a  left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm) e " +
				" where a.xn = e.xn and a.yrdwdm = e.yrdwdm and a.ffny = e.yf) zcje " +
				" from xg_qgzx_gwffztsqb a " +
				" left join xqdzb xq on a.xq = xq.xqdm " +
				"left join xg_xtwh_shztb m on a.sqid=m.ywid "+
				"left join xg_xtwh_spgw n  on m.gwid = n.id "+
				"left join xg_xtwh_spgwyh o on m.gwid = o.spgw  where o.spyh='" + user.getUserName() + "' ");
				
		
		if (!t.getShzt().equals("dsh")) {
			sql.append(" and (m.shzt<>0 and m.shzt<>4) ");
		} else {
			sql.append(" and (m.shzt=0 or m.shzt = 4 ) ");
		}
		sql.append("order by a.xn, a.tjzt, a.yrdwdm, a.ffny desc) a where rn='1' ");
		sql.append(searchTj);
		sql.append(searchTjQx);
		
		return getPageList(t, sql.toString(), inputV);
	}

	public boolean updateShzt (String sqid,String shzt) throws Exception{
		String sql="update xg_qgzx_gwffztsqb set shzt=? where sqid=? ";
		return dao.runUpdate(sql, new String[]{shzt,sqid});
	}

	/**
	 * @������ɾ��2�Ž�������Ѵ��ڵ�����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��6��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * boolean ��������
	 */
	public int[] deleteJg (String sqid) throws Exception{
		String sql1="delete from xg_qgzx_jcffb t where exists(select 1 from "+
				"(select t1.* from xg_qgzx_jcffb t1 "+
				"left join xg_qgzx_gwxxb t2 on t1.gwdm=t2.gwdm "+
				"left join xg_qgzx_yrdwdmb t4 on t2.yrdwid = t4.id "+
				"left join xg_qgzx_gwffztsqb t3 on t4.xydm=t3.yrdwdm and t1.yf=t3.ffny "+
				"where t3.sqid='"+sqid+"' )a where a.xn=t.xn and a.gwdm=t.gwdm and a.yf=t.yf ) ";
		String sql2="delete from xg_qgzx_gwffztb t where exists "+
				"(select 1 from xg_qgzx_gwffztsqb a where a.sqid='"+sqid+"' "+
				"and a.yrdwdm=t.yrdwdm and a.ffny=t.ffny and a.xn=t.xn) ";
		return dao.runBatch(new String[]{sql1,sql2});
	}
	
	/**
	 * @��������������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��6��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * int[] ��������
	 */
	public int[] insertJg (String sqid) throws Exception{
		String sql1="insert into xg_qgzx_jcffb (XH, GWDM, YF, GS, JE, BZ, KHDJ, CJFFR, ZHDLGS, JCDLGS, XN, XQ, JFHBJE, ZCJE) "+
				"select XH, GWDM, YF, GS, JE, BZ, KHDJ, CJFFR, ZHDLGS, JCDLGS, XN, XQ, JFHBJE, ZCJE from (select t1.* from xg_qgzx_jcffsqb t1 "+
				"left join xg_qgzx_gwxxb t2 on t1.gwdm=t2.gwdm "+
				"left join xg_qgzx_yrdwdmb t4 on t2.yrdwid = t4.id "+
				"left join xg_qgzx_gwffztsqb t3 on t4.xydm=t3.yrdwdm and t1.yf=t3.ffny "+
				"where t3.sqid='"+sqid+"' )a ";
		String sql2="insert into xg_qgzx_gwffztb (YRDWDM, FFNY, TJZT, XN, GWDM, XQ, SJLY) "+
				"select YRDWDM, FFNY, TJZT, XN, GWDM, XQ, '1' from xg_qgzx_gwffztsqb a where a.sqid='"+sqid+"' ";
		return dao.runBatch(new String[]{sql1,sql2});
	}
	
	public String getPkvalue(String sqid){
		String sql="select a.xn || '!!@@!!' || a.yrdwdm || '!!@@!!' || a.ffny || '!!@@!!' || nvl(a.xq,'none') pkValue "
				+ "from XG_QGZX_GWFFZTSQB a where sqid=? ";
		return dao.getOneRs(sql, new String[]{sqid}, "pkValue");
	}
	
	/**
	 * @����: ȡ�������ñ��еĲ���ֵ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-8-10 ����10:33:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cjffgs
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCspzxx (String cjffgs){
		String sql = "select csz from xg_qgzx_new_cspzb where csdm = ?";
		return dao.getOneRs(sql.toString(), new String[]{cjffgs}, "csz");
	}
	
	
	/**
	 * @description	�� ��ȡδά����λ���ƣ��ൺ�Ƶ����ְҵѧԺ��
	 * @author 		�� ������1282��
	 * @date 		��2017-11-21 ����04:15:35
	 * @param params
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getWwhGw(String[] params) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct a.gwmc from");
		sb.append(" (select a.xh,a.gwdm,b.xn,b.gwmc,case when a.zgzt = 'zg' and b.sfyxgw = '��' then 'tg' else a.zgzt end zgztnew");
		sb.append(" from view_xg_qgzx_xsgwxxb a");
		sb.append(" left join");
		sb.append(" (select a.xn,a.gwdm,a.gwmc,a.gwjssj,a.yrdwdm,case when a.gwjssj = '' or a.gwjssj is null or a.gwjssj >=  replace(?, '-', '') || '31' then '��' else '��' end sfyxgw from xg_qgzx_gwxxb a) b on a.gwdm = b.gwdm where b.xn = ? and b.yrdwdm = ?)a");
		sb.append(" where not exists(select 1 from xg_qgzx_jcffsqb b where a.xh = b.xh and a.xn = b.xn and a.gwdm = b.gwdm and b.yf = ?)");
		sb.append(" and a.zgztnew = 'zg'");
		return dao.getListNotOut(sb.toString(), params);
	}

	public boolean batchInsertDataIntoDB(List<String[]> jcffsqbList,List<String[]> gwffztsqList) throws SQLException {

		String sql = "insert into XG_QGZX_JCFFSQB(xh,gwdm,yf,gs,je,xn) select ?,?,?,?,?,? from dual";
		String sql2 = "insert into XG_QGZX_GWFFZTSQB(yrdwdm,ffny,xn,gwdm,sqid,shzt,splc) select ?,?,?,?,?,'5',? from dual";
		if(dao.runBatchBoolean(sql,jcffsqbList)){
			return dao.runBatchBoolean(sql2,gwffztsqList);
		}
		return false;
	}
}
