package xsgzgl.qgzx.gwgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import common.XszzXmdm;
/**
 * �ڹ���ѧ-�ڹ���λ����-��λ��Ϣ����
 * @author yeyipin
 * @since 2012.7.17
 */
public class QgzxGwglDAO {
	DAO dao = DAO.getInstance();
	
	
	/**
	 * ��ø�λ��ϢList
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwsqList(QgzxGwglForm myForm) throws Exception {
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// �û�����
		User user = myForm.getUser();
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
		String[] colList = new String[] { "gwdm","r","xn", "yrdwmc", "gwmc","gwxzmc", "xqrs", "knsrs", "sqsj", "shzt" };
		sql.append(" select a.*,rownum r,case when a.shzt='ͨ��'or a.shzt='��ͨ��' then 'disabled' end as dis from view_xg_qgzx_gwxxsqb a where 1=1 ");
		//	
		
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}
	
	
	/**
	 * ��ø�λ��ϢList
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwxxList(QgzxGwglForm myForm) throws Exception {
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// �û�����
		User user = myForm.getUser();
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
		String[] colList=null;
		if("10052".equals(Base.xxdm)){
			 colList = new String[] { "gwdm", "r", "xn", "yrdwmc", "gwxh","gwmc", "gwxzmc","xqrs", "zgrs","tgrs" };
		}else{
			 colList = new String[] { "gwdm", "r", "xn", "yrdwmc","gwmc", "gwxzmc","xqrs", "zgrs","tgrs" };
		}
		
		sql.append(" select a.*,rownum r from view_xg_qgzx_gwxxb a where 1=1 ");
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}
	
	
	/**
	 * ��ø�λ��ϢList
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwshList(QgzxGwglForm myForm) throws Exception {
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// �û�����
		User user = myForm.getUser();
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
		String[] colList = new String[] { "gwdm","dis", "r", "xn", "yrdwmc","gwmc","gwxzmc", "xqrs", "knsrs", "sqsj", "shzt" };
		sql.append(" select a.*,rownum r,case when (select count(1) from xg_qgzx_gwxxb b where a.gwdm = b.gwdm)<>'0' or a.shzt = '��ͨ��'then'disabled' end as dis from view_xg_qgzx_gwxxsqb a where 1=1 ");
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}
	
	
	/**
	 * ѧ����Ϣ�б�
	 * @param myForm
	 * @param searchTj2 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getStuList(QgzxGwglForm myForm,HttpServletRequest request) throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//Ȩ�޿��� 	
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "a","xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "xh", "r", "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc","sfkns" };
		
		// ��������Ŀ����
		String xmdm = XszzXmdm.XSZZ_KNS;
		HashMap<String, String> xmInfo = dao.getMapNotOut("select * from view_xszz_comm_xmwh where pk=? ", new String[]{xmdm});
		// ��������������
		String sqzq = xmInfo.get("sqzq");
		// ��������˼���
		String shjb = xmInfo.get("shjb");
		String xn = Base.currXn;
		String xq = Base.currXq;
		String nd = Base.currNd;
		sql.append(" select a.*,rownum r from (select a.*,  decode(b.xh ,null,'��','��') sfkns from view_xsjbxx a left join  ");
		if("10335".equals(Base.xxdm)){
			sql.append(" (select b.xh,max(b.sqsj) from view_knsjgb_fqxrd b group by b.xh ");
		}else{
			sql.append(" (select b.xh,max(b.sqsj) from xg_xszz_new_knsjgb b ");
			
			//����ѧ���ѯ������ ʹ�ڹ���λ��Աֻ���ǵ�ǰ��λѧԺ��������
			sql.append(" where b.xn='");
			sql.append(myForm.getXn());
			
			sql.append("' group by b.xh ");
		}
		if("yes".equals(request.getParameter("sfxyzgsc"))){
			sql.append(") b on a.xh = b.xh where not exists(select 1 from xg_qgzx_xsgwxxb c where a.xh = c.xh and zgzt='zg' and gwdm = '"+myForm.getPkValue()+"') and a.xh in(select xh from xg_qgzx_qgzxxsb)) a where 1=1");	
		}
		else{
			sql.append(") b on a.xh = b.xh where not exists(select 1 from xg_qgzx_xsgwxxb c where a.xh = c.xh and zgzt='zg' and gwdm = '"+myForm.getPkValue()+"') order by a.nj,a.xydm,a.zydm,a.bjdm,a.xh) a where 1=1");
		}
				
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}
	
	
	/**
	 * ��ø�λ��ϢMap
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String> getGwxxMap(QgzxGwglForm myForm){
		String sql = "select * from view_xg_qgzx_gwxxb where gwdm =? ";
		String[] inputValue = {myForm.getPkValue()};
		return dao.getMapNotOut(sql, inputValue);
	}
	
	/**
	 * ��ø�λ����Map
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String> getGwsqMap(QgzxGwglForm myForm){
		String sql = "select * from view_xg_qgzx_gwxxsqb where gwdm =? ";
		String[] inputValue = {myForm.getPkValue()};
		return dao.getMapNotOut(sql, inputValue);
	}
	
	
	/**
	 * �����λ��Ϣ
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public boolean gwxxBc(QgzxGwglForm myForm) throws Exception {
		//String gwdm = myForm.getGwdm();
		String xn = myForm.getXn();
		String yrbm = myForm.getYrbm();
		String gwmc = myForm.getGwmc();
		String gwxzdm = myForm.getGwxzdm();
		String xqrs = myForm.getXqrs();
		String knsrs = myForm.getKnsrs();
		String gwms = myForm.getGwms();
		String gwryyq = myForm.getGwryyq();
		String gwyqryxg = myForm.getGwyqryxg();
		String bz = myForm.getBz();
		String gwcjsx=myForm.getGwcjsx();
		String sql = "insert into xg_qgzx_gwxxb (gwxh,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,gwyqryxg,bz,gwcjsx) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		String[] input = { myForm.getGwxh(),xn, yrbm, gwmc, gwxzdm, xqrs, knsrs, gwms, gwryyq,gwyqryxg, bz,gwcjsx};
		return dao.runUpdate(sql,input);
	}
	
	
	/**
	 * ͬһѧ��,ͬһ�����Ƿ������ͬ��λ���Ƶĸ�λ��Ϣ
	 * @param model
	 * @return
	 */
	public boolean isExist(QgzxGwglForm model) {
		String pkValue = Base.isNull(model.getPkValue())?" ":model.getPkValue();
		String xn = model.getXn();
		String yrbm = model.getYrbm();
		String gwmc = model.getGwmc();
		String sql ="select count(1) num from xg_qgzx_gwxxb where gwdm<>? and xn=? and yrdwdm=? and gwmc=? ";
		String[] inputValue = new String[]{pkValue,xn,yrbm,gwmc};
		String num = dao.getOneRs(sql, inputValue, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	
	
	/**
	 * ͬһѧ��,ͬһ�����Ƿ������ͬ��λ���Ƶĸ�λ��Ϣ
	 * @param model
	 * @return
	 */
	public boolean checkZjGwsqInfoExist(QgzxGwglForm model) {
		String pkValue = Base.isNull(model.getPkValue())?" ":model.getPkValue();
		String xn = model.getXn();
		String yrbm = model.getYrbm();
		String gwmc = model.getGwmc();
		String sql ="select count(1) num from xg_qgzx_gwxxsqb where gwdm<>? and xn=? and yrdwdm=? and gwmc=? ";
		String[] inputValue = new String[]{pkValue,xn,yrbm,gwmc};
		String num = dao.getOneRs(sql, inputValue, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	
	
	
	/**
	 * �жϸ�λ�Ƿ��ѱ�ʹ��
	 * @param pkValue
	 * @return
	 */
	public boolean isUsed(String pkValue) {
		String sql="select count(1) num from xg_qgzx_xsgwxxb where gwdm = ? ";
		String num = dao.getOneRs(sql, new String[]{pkValue}, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	
	
	/**
	 * �޸ĸ�λ��Ϣ
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean gwxxXg(QgzxGwglForm myForm) throws Exception {
		String pkValue = myForm.getPkValue();
		String gwmc = myForm.getGwmc();
		String gwxzdm = myForm.getGwxzdm();
		String xqrs = myForm.getXqrs();
		String knsrs = myForm.getKnsrs();
		String gwms = myForm.getGwms();
		String gwryyq = myForm.getGwryyq();
		String gwyqryxg = myForm.getGwyqryxg();
		String bz = myForm.getBz();
		String gwcjsx=myForm.getGwcjsx();
		String sql = "update xg_qgzx_gwxxb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,gwms=?,gwryyq=?,gwyqryxg=?,bz=?,gwcjsx=? where gwdm=?";
		String[] input = {gwmc,gwxzdm,xqrs, knsrs, gwms, gwryyq, gwyqryxg,bz,gwcjsx,pkValue};
		return dao.runUpdate(sql,input);
	}
	
	
	/**
	 * �޸ĸ�λ��Ϣ
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean bcXgGwsq(QgzxGwglForm myForm) throws Exception {
		String pkValue = myForm.getPkValue();
		String gwmc = myForm.getGwmc();
		String gwxzdm = myForm.getGwxzdm();
		String xqrs = myForm.getXqrs();
		String knsrs = myForm.getKnsrs();
		String gwms = myForm.getGwms();
		String gwryyq = myForm.getGwryyq();
		String gwyqryxg = myForm.getGwyqryxg();
		String bz = myForm.getBz();
		String gwcjsx=myForm.getGwcjsx();
		String sql = "update xg_qgzx_gwxxsqb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,gwms=?,gwryyq=?,gwyqryxg=?,bz=?,sqsj=to_char(sysdate,'yyyy-MM-dd hh:mi:ss'),gwcjsx=?,shzt='0' where gwdm=?";
		String[] input = {gwmc,gwxzdm,xqrs, knsrs, gwms, gwryyq, gwyqryxg, bz, gwcjsx,pkValue};
		return dao.runUpdate(sql,input);
	}
	
	/**
	 * �޸ĸ�λ��Ϣ�������˵�λ��λ���롿�ļ�¼��ˡ�ͨ�������ڡ���λ��Աά�����н�����Ϣ�޸ģ� �����˵�λ��λ���롿����Ӧ����ϢҲ���޸ģ�
	 */
	public boolean bcXgGwsqByJg(QgzxGwglForm myForm) throws Exception {
		String pkValue = myForm.getPkValue();
		String gwmc = myForm.getGwmc();
		String gwxzdm = myForm.getGwxzdm();
		String xqrs = myForm.getXqrs();
		String knsrs = myForm.getKnsrs();
		String gwms = myForm.getGwms();
		String gwryyq = myForm.getGwryyq();
		String bz = myForm.getBz();
//		String gwcjsx=myForm.getGwcjsx();
		String sql = "update xg_qgzx_gwxxsqb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,gwms=?,gwryyq=?,bz=? where gwdm=?";
		String[] input = {gwmc,gwxzdm,xqrs, knsrs, gwms, gwryyq, bz,pkValue};
		return dao.runUpdate(sql,input);
	}
	
	
	/**
	 * ������˲���List
	 * @return
	 */
	public List<HashMap<String, String>> getYrbm(QgzxGwglForm myForm) {
		String sql = " select distinct bmdm,bmmc from view_xg_qgzx_yrdwdmb a where bmmc<>'δȷ��' order by bmdm ";
		String[] outputValue = {"bmdm","bmmc"};
		return dao.getList(sql, new String[]{}, outputValue);
	}
	
	
	/**
	 * ������ĸ�λ����
	 * @return
	 */
	public String getMaxGwdm() {
		String sql = " select max(to_number(substr(gwdm,10,4 ))) num from xg_qgzx_gwxxb ";
		return dao.getOneRs(sql, new String[]{}, "num");
	}
	
	
	/**
	 * ����ɾ����λ��Ϣ
	 * @param pkValue
	 * @return
	 * @throws SQLException 
	 */
	public boolean gwxxSc(List<String[]> params) throws SQLException {
		String sql = " delete from xg_qgzx_gwxxb where gwdm=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * ����ɾ����λ��Ϣ
	 * @param pkValue
	 * @return
	 * @throws SQLException 
	 */
	public boolean gwsqSc(List<String[]> params) throws SQLException {
		String sql = " delete from xg_qgzx_gwxxsqb where gwdm=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * ���������λ��Ϣ
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean gwxxSave(List<String[]> params) throws SQLException {
		String sql = null;
			 sql = "insert into xg_qgzx_gwxxb(gwxh,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,gwyqryxg,bz,gwcjsx) select gwxh,? xn ,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,gwyqryxg,bz,gwcjsx from xg_qgzx_gwxxb where gwdm = ?";
		int[] result = dao.runBatch(sql,params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * �ڸ���Ա��ϢList
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String[] getRyxhList(QgzxGwglForm model) throws SQLException {
		String sql = " select xh||'!!@@!!'||sgsj||'!!@@!!'||sjly pkValue from xg_qgzx_xsgwxxb where gwdm = ? and zgzt = ? order by sgsj desc,xh ";
		String[] inputValue = {model.getPkValue(),model.getZgzt()};
		return dao.getArray(sql, inputValue, "pkValue");
	}
	
	
	/**
	 * ��ø�λѧ����Ϣ
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getGwxsxx(QgzxGwglForm model) {
		String sql = "select a.sgsj,a.tgsj,a.zgzt,a.sjly,a.sqbh,b.*,(select count(1) from xg_qgzx_xsgwxxb where xh = ? and zgzt='zg' )qggws from xg_qgzx_xsgwxxb a left join view_xsbfxx b on a.xh = b.xh where a.xh = ? and a.gwdm = ? and a.zgzt = ? and rownum = 1";
		String[] inputValue = {model.getXh(),model.getXh(),model.getPkValue(),model.getZgzt()};
		return dao.getMapNotOut(sql, inputValue);
	}

	
	/**
	 * ���ѧ����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getXsxx(QgzxGwglForm model) {
		String sql = "select a.*,(select count(1) from xg_qgzx_xsgwxxb where xh = ? and zgzt='zg' )qggws from view_xsbfxx a where a.xh = ? and rownum = 1";
		String[] inputValue = {model.getXh(),model.getXh()};
		String[] outputValue = {"xh","xm","bjmc","qggws","xb","nj","xymc","zymc","lxdh"};
		return dao.getMap(sql, inputValue, outputValue);
	}

	
	/**
	 * ����������Ա��Ϣ
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean bcZjryxx(List<String[]> params) throws SQLException {
		String sql = " insert into xg_qgzx_new_xsgwxxb(gwdm,xh,sgsj,zgzt)values(?,?,to_char(sysdate,'yyyyMMdd'),'zg')";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * ����ɾ����Ա��Ϣ
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean scRyxx(List<String[]> params) throws SQLException {
		String sql = " delete from xg_qgzx_new_xsgwxxb where gwdm = ? and xh = ? and zgzt = 'zg'";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * ���������˸���Ա��Ϣ
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean bcTgryxx(List<String[]> params) throws SQLException {
		String sql = " update xg_qgzx_new_xsgwxxb set zgzt='tg',tgsj=to_char(sysdate,'yyyyMMdd'),tgyy=? where gwdm=? and xh = ? and sgsj = ?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @����: ������������Ϊ��ʼ�����ݣ�δ���ͨ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-30 ����02:21:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 */
	public boolean hySqsj(List<String[]> params) throws SQLException {
		String sql = " update XG_QGZX_XSGWSQB set shgw='-1' where sqbh=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}

	/**
	 * ����ѧ�ź��ڸ�״̬��ø�λ��Ϣ
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getGwxx(QgzxGwglForm model) {
		String sql = "select a.*,rownum r from (select a.sgsj,b.*,c.bmmc,d.gwxzmc from xg_qgzx_xsgwxxb a " +
				"left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm " +
				"left join view_xg_qgzx_yrdwdmb c on b.yrdwdm = c.bmdm " +
				"left join xg_qgzx_gwxzdmb d on b.gwxzdm = d.gwxzdm where a.xh = ? and a.zgzt = ? order by a.sgsj desc,b.xn,b.yrdwdm,b.gwdm,b.gwxzdm) a";
		String[] inputValue = {model.getXh(),model.getZgzt()};
		String[] outputValue = {"r","xn","bmmc","gwmc","gwxzmc","sgsj"};
		return dao.getList(sql, inputValue, outputValue);
	}


	/**
	 * �������˸�ʱ��
	 * @param model
	 * @return
	 */
	public String getZhtgsj(String[] inputValue) {
		String sql = " select max(tgsj) tgsj from xg_qgzx_xsgwxxb where gwdm = ? and xh = ? ";
		return dao.getOneRs(sql, inputValue, "tgsj");
	}

	

	/**
	 * ��ø�λѧ��ѧ��
	 * @param xn
	 * @param xsgws
	 * @return
	 * @throws SQLException 
	 */
	public String[] getGwxsxh(String xn, String xsgws) throws SQLException {
		String sql = "select xh from (select xh,count(1) num from xg_qgzx_xsgwxxb where substr(gwdm,0,9) = ? and zgzt = 'zg' group by xh) where num >=to_number(?)";
		return dao.getArray(sql, new String[]{xn,xsgws}, "xh");
	}


	/**
	 * ��ø�λ��
	 * @param gwdm
	 * @param xh
	 * @return
	 */
	public String getGws(String gwdm, String xh) {
		String sql = "select count(1) num from xg_qgzx_xsgwxxb c where exists" +
				"(select 1 from xg_qgzx_gwxxb a where exists" +
				"(select 1 from xg_qgzx_gwxxb b where b.gwdm = ? " +
				"and a.xn = b.xn)	and a.gwdm = c.gwdm	) and zgzt = 'zg' and xh = ?  ";
		return dao.getOneRs(sql, new String[]{gwdm,xh}, "num");
	}


	/**
	 * ��Ա�Ƿ񱻷��Ź����
	 * @param gwdm
	 * @param xh
	 * @return
	 */
	public boolean isUsed(String gwdm, String xh) {
		String sql = "select count(1) num from xg_qgzx_jcffb where gwdm = ? and xh = ? ";
		return "0".equalsIgnoreCase(dao.getOneRs(sql, new String[]{gwdm,xh}, "num"))?false:true;
	}


	/**
	 * ������˵�λ����
	 * @param yrdwdm
	 * @return
	 */
	public String getYrdwmc(String yrdwdm) {
		String sql = "select bmmc from view_xg_qgzx_yrdwdmb where bmdm = ? ";
		return dao.getOneRs(sql, new String[]{yrdwdm}, "bmmc");
	}


	/**
	 * �������Ӹ�λ����
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean bcZjGwsq(QgzxGwglForm model) throws Exception {
		String xn = Base.currXn;
		String yrbm = model.getUser().getUserDep();
		String gwmc = model.getGwmc();
		String gwxzdm = model.getGwxzdm();
		String xqrs = model.getXqrs();
		String knsrs = model.getKnsrs();
		String gwms = model.getGwms();
		String gwryyq = model.getGwryyq();
		String gwyqryxg = model.getGwyqryxg();
		String bz = model.getBz();
		String sqr = model.getUser().getUserName();
		String gwcjsx=model.getGwcjsx();
		String sql = "insert into xg_qgzx_gwxxsqb (xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,gwyqryxg,bz,sqsj,sqr,gwcjsx) values (?,?,?,?,?,?,?,?,?,?,to_char(sysdate,'yyyy-MM-dd hh:mi:ss'),?,?)";
		String[] input = {xn, yrbm, gwmc, gwxzdm, xqrs, knsrs, gwms, gwryyq,gwyqryxg, bz, sqr,gwcjsx};
		return dao.runUpdate(sql,input);
	}

	
	/**
	 * ��λ��Ϣ��˱���
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean gwxxshBc(List<String[]> params) throws SQLException {
		String sql = " update xg_qgzx_gwxxsqb set shyj=?,shzt=?,shsj=to_char(sysdate,'yyyy-MM-dd hh:mi:ss'),shr=? where gwdm=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}


	/**
	 * ������ʽ��
	 * @param paramsByZs
	 * @return
	 * @throws SQLException
	 */
	public boolean insertByZs(List<String[]> paramsByZs) throws SQLException {
		String sql = " insert into xg_qgzx_gwxxb(gwxh,gwdm,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,bz,gwcjsx,gwyqryxg)" +
				"select ?,gwdm,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,bz,gwcjsx,gwyqryxg from xg_qgzx_gwxxsqb where gwdm = ?";
		int[] result = dao.runBatch(sql, paramsByZs);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * ���˵�λ��λ���� �Զ��嵼��
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGwsqExportList(QgzxGwglForm myForm,User user) throws Exception {
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// �û�����
		//User user = myForm.getUser();
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
		String[] colList = new String[] { "gwdm", "r", "xn", "yrdwmc", "gwmc","gwxzmc", "xqrs", "knsrs", "sqsj", "shzt" };
		sql.append(" select a.*,rownum r,case when a.shzt='ͨ��'or a.shzt='��ͨ��' then 'disabled' end as dis from view_xg_qgzx_gwxxsqb a where 1=1 ");
		//sql.append("select rownum r ,k.* from VIEW_NEW_DC_QGZX_GWSQ k where 1=1 ");
		// ====================SQLƴװ end================================
		//List<HashMap<String, String>> 
		
		return CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}
	/**
	 * 
	 * @����:��ȡ��λ���
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-10-21 ����04:49:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String GetGwxh(String yrdwdm,String xn) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select ( case when length(gwxh)=1 then '00'||gwxh when "); 
		sql.append(" length(gwxh)=2 then '0'||gwxh else to_char(gwxh) end) gwxh");
		sql.append(" from (select (to_number(nvl(max(to_number(gwxh)),0))+1) gwxh from xg_qgzx_gwxxb");
		sql.append(" where yrdwdm=? and xn=?)");
		return dao.getOneRs(sql.toString(), new String[]{yrdwdm,xn}, "gwxh");
}
}
