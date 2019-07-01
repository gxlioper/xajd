package xsgzgl.qgzx.jfgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
/**
 * �ڹ���ѧ-�ڹ����ѹ���-������Ϣ����
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxJfglDAO extends SuperDAOImpl<QgzxJfglForm>{
	DAO dao = DAO.getInstance();
	/**
	 * ��þ�����ϢList
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getJfxx(QgzxJfglForm myForm) throws Exception {
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		String[] colList = new String[]{};
		if("12309".equals(Base.xxdm)){
			 colList =new String[]{ "pkValue", "r", "yf","yrdwmc","hbzje","jfhbje","wcsysyje" };
		}else {
			 colList =new String[]{ "pkValue", "r", "yf","yrdwmc","hbzje","yffje","syje" };
		}
		// �û�����
		User user = myForm.getUser();
		//Ȩ�޿��� 	
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//��������ڹ�����Ա
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		StringBuilder sql = new StringBuilder();
		sql.append(" select rownum r,a.* from (select yf||'!!@@!!'||yrdwdm pkValue,a.* from ");
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			sql.append("view_xg_qgzx_jfhbb_yf");
		}else{
			sql.append("view_xg_qgzx_jfhbb_nd");
		}
		sql.append(" a order by yf,yrdwdm) a where 1 = 1");
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}
	/**
	 * 
	 * @����:��ȡ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-24 ����10:23:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yrdwdm
	 * @param nd
	 * @return
	 * Map<String,String> �������� 
	 */
	public Map<String, String> getJfxx(String yrdwdm, String nd) {
		StringBuilder sql = new StringBuilder();
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			sql.append("select * from view_xg_qgzx_jfhbb_yf where yrdwdm=? and yf=?");
		}else{
			sql.append("select * from view_xg_qgzx_jfhbb_nd where yrdwdm=? and yf=?");
		}
		return dao.getMapNotOut(sql.toString(), new String[] { yrdwdm, nd });
	}
	
	/**
	 * ��ò����б�
	 * @return
	 */
	public List<HashMap<String, String>> getBm() {
		String sql = " select distinct t.xydm bmdm,t1.bmmc from xg_qgzx_yrdwdmb t ";
		sql += " left join ZXBZ_XXBMDM t1 on t.xydm = t1.bmdm ";
		sql += " where t.dwlb = '01' order by t.xydm ";
		String[] outputValue = {"bmdm","bmmc"};
		return dao.getList(sql, new String[]{}, outputValue);
	}
	public List<HashMap<String, String>> getBms(String xn,String xq) {
		ArrayList<String> paraList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct t1.xydm bmdm,t2.bmmc from XG_QGZX_GWXXB t ");
		sql.append(" left join xg_qgzx_yrdwdmb t1 on t.yrdwid=t1.id ");
		sql.append(" left join ZXBZ_XXBMDM t2 on t1.xydm = t2.bmdm ");
		sql.append(" where t.shzt = '1' and t1.dwlb = '01' and t.xn=?");
		paraList.add(xn);
		if(StringUtils.isNotNull(xq)){
			sql.append(" and t.xq = ?");
			paraList.add(xq);
		}
		
		sql.append("   order by t1.xydm ");
		String[] outputValue = {"bmdm","bmmc"};
		return dao.getList(sql.toString(),paraList.toArray(new String[]{}), outputValue);
	}
	
	public List<HashMap<String, String>> getBm(String xn,String nd) {
		String sql = " select distinct yrdwdm bmdm,yrdwmc bmmc from VIEW_XG_QGZX_GWXXB where yrdwdm<>'δȷ��' and xn=? "
				+ "and yrdwdm not in(select yrdwdm from (select distinct Substr(nd, 0, 4) nd,yrdwdm from XG_QGZX_JFHBB) where nd= ? ) order by yrdwdm";
		String[] outputValue = {"bmdm","bmmc"};
		return dao.getList(sql, new String[]{xn,nd}, outputValue);
	}
	public HashMap<String, String> getGwxx(String xn,String yrdwdm,String xq) {
		StringBuffer sb=new StringBuffer();
		sb.append("select sum(zgrs) zgrs,sum(xqrs) xqrs,sum(xqrs*gwcjsx) hbje,count(*) gws ");
		sb.append(" from (select t.xqrs,t.gwcjsx,t.gwdm,t.xn,t.xq,t1.xydm yrdwdm ");
		sb.append("      ,(select count(1) from xg_qgzx_new_xsgwxxb d where t.gwdm = d.gwdm and d.zgzt='zg') zgrs ");
		sb.append("      from XG_QGZX_GWXXB t");
		sb.append("      left join xg_qgzx_yrdwdmb t1 on t.yrdwid=t1.id ");
		sb.append("      where t.shzt = '1') a");
		sb.append(" where xn=? and yrdwdm=? and (xq = ? or xq is null or xq = '')");
		return dao.getMap(sb.toString(), new String[]{xn,yrdwdm,xq},new String[]{"zgrs","xqrs","hbje","gws"});
	}
	/**
	 * ��������
	 * @return
	 * @throws Exception 
	 */
	public boolean jfxxBc(List<String[]> params) throws Exception {
		String sql ="insert into xg_qgzx_jfhbb(xn,xq,nd,yrdwdm,hbsj,hbje,bz)values(?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql,params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * �Ƿ����ͬһʱ������˵�λ����
	 * @param elSel
	 * @return
	 */
	public boolean isExist(String[] inputValue) {
		String sql = "select count(1) num from xg_qgzx_jfhbb where nd = ? and yrdwdm = ? and hbsj = ? ";
		String num = dao.getOneRs(sql, inputValue, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	
	
	/**
	 * ��þ�����ϢList
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,String>> getJfxxList(QgzxJfglForm myForm) {
		String sql = "select hbsj,hbje,bz,xn,xq,t1.xqmc from xg_qgzx_jfhbb t " +
				"left join xqdzb t1 on t.xq=t1.xqdm " +
				"where nd = ? and yrdwdm = ?";
		String[] inputValue = {myForm.getNd(),myForm.getBm()};
		String[] outputValue = {"hbsj","hbje","bz","xn","xq","xqmc"};
		return dao.getList(sql, inputValue, outputValue);
	}
	
	
	/**
	 * ��ò�������
	 * @param bm
	 * @return
	 */
	public String getBmmc(String bm) {
		String sql = "select bmmc from view_xg_qgzx_yrdwdmb where bmdm = ? and rownum = 1 ";
		return dao.getOneRs(sql, new String[]{bm}, "bmmc");
	}
	
	
	/**
	 * ������Ϣɾ��
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean jfxxDel(QgzxJfglForm myForm) throws Exception {
		String sql = "delete from xg_qgzx_jfhbb where nd = ? and yrdwdm = ?";
		String[] input = {myForm.getNd(),myForm.getBm()};
		return dao.runUpdate(sql, input);
	}
	
	
	/**
	 * ������Ϣ����
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean jfxxSave(List<String[]> params) throws SQLException {
		String sql = "insert into xg_qgzx_jfhbb(xn,xq,nd,yrdwdm,hbsj,hbje,bz)values(?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * ��û����ܽ��
	 * @param model
	 * @return
	 */
	public Double getHbzje(QgzxJfglForm model) {
		String sql =null;
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			sql= "select yffje from view_xg_qgzx_jfhbb_yf where yf = ? and yrdwdm = ? and rownum = 1";
		}else{
			sql= "select yffje from view_xg_qgzx_jfhbb_nd where yf = ? and yrdwdm = ? and rownum = 1";
		}
		String[] inputValue = {model.getNd(),model.getBm()};
		String num = dao.getOneRs(sql, inputValue, "yffje");
		return Double.valueOf(num);
	}
	
	
	/**
	 * ���ĳ����ĳ���ʣ����ý��
	 * @param nd
	 * @param yrdwdm
	 * @return
	 */
	public String getBmNdSyje(String nd, String yrdwdm) {
		String sql =null;
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			sql = "select syje from view_xg_qgzx_jfhbb_yf where yf = ? and yrdwdm = ? ";
		}else{
			sql = "select syje from view_xg_qgzx_jfhbb_nd where yf = ? and yrdwdm = ? ";
		}
		return dao.getOneRs(sql, new String[]{nd,yrdwdm}, "syje");
	}
	
	
	/** ===================������ڹ�ͨ�÷���begin===================*/
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QgzxJfglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	/**
	 * ���ã���ѯ�б��ҳ
	 * ʱ�䣺2016-12-17
	 * ���ߣ�Meng.Wei
	 */
	@Override
	public List<HashMap<String, String>> getPageList(QgzxJfglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.nd || '!!@@!!' || a.yrdwdm pkValue, a.nd, a.yrdwdm, c.bmmc, a.hbzje, b.ffzje, a.hbzje - b.ffzje syje ");
		sql.append(" from (select nd, yrdwdm, sum(nvl(hbje, 0)) hbzje ");
		sql.append(" from xg_qgzx_jfhbb ");
		sql.append(" group by (nd, yrdwdm)) a ");
		sql.append(" left join (select substr(ffndyf, 0, 4) nd, yrdwdm, sum(nvl(bcje, 0)) ffzje ");
		sql.append(" from xg_qgzx_zjdx_cjffb where sftj = '1' ");
		sql.append(" group by substr(ffndyf, 0, 4), yrdwdm) b ");
		sql.append(" on a.nd = b.nd and a.yrdwdm = b.yrdwdm ");
		sql.append(" left join zxbz_xxbmdm c on a.yrdwdm = c.bmdm order by bmmc ");
		sql.append(" )t where 1 = 1");
		QgzxJfglService service = new QgzxJfglService();
		//��������ڹ�����Ա
		if(!service.sfQggly(user.getUserName())){
			sql.append(" and yrdwdm = '"+user.getUserDep()+"' ");
		}
		//sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
	}
	/**
	 * @����: �õ����˲���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-12-20 ����03:54:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYrbm() {
		String sql = " select bmdm,bmpy||bmmc bmmc from ( select distinct bmdm,bmmc,F_PINYIN(substr(bmmc,0,1))bmpy from zxbz_xxbmdm ) order by bmmc ";
		String[] outputValue = {"bmdm","bmmc"};
		return dao.getList(sql, new String[]{}, outputValue);
	}
	/**
	 * @����: �鿴ҳ���𷢷���Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-12-20 ����03:55:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getFfmxList(QgzxJfglForm myForm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select c.* ,d.* from ( ");
		sql.append(" select sum(yiyue)yiyue,sum(eryue)eryue,sum(sanyue)sanyue,sum(siyue)siyue,sum(wuyue)wuyue,sum(liuyue)liuyue, ");
		sql.append(" sum(qiyue)qiyue,sum(bayue)bayue,sum(jiuyue)jiuyue,sum(shiyue)shiyue,sum(shiyiyue)shiyiyue,sum(shieryue)shieryue,yrdwdm from ( ");
		sql.append(" select case when ffndyf = ?||'-01' then a.ze else  0 end yiyue, ");
		sql.append(" case when ffndyf = ?||'-02' then a.ze else  0 end eryue, ");
		sql.append(" case when ffndyf = ?||'-03' then a.ze else  0 end sanyue, ");
		sql.append(" case when ffndyf = ?||'-04' then a.ze else  0 end siyue, ");
		sql.append(" case when ffndyf = ?||'-05' then a.ze else  0 end wuyue, ");
		sql.append(" case when ffndyf = ?||'-06' then a.ze else  0 end liuyue, ");
		sql.append(" case when ffndyf = ?||'-07' then a.ze else  0 end qiyue, ");
		sql.append(" case when ffndyf = ?||'-08' then a.ze else  0 end bayue, ");
		sql.append(" case when ffndyf = ?||'-09' then a.ze else  0 end jiuyue, ");
		sql.append(" case when ffndyf = ?||'-10' then a.ze else  0 end shiyue, ");
		sql.append(" case when ffndyf = ?||'-11' then a.ze else  0 end shiyiyue, ");
		sql.append(" case when ffndyf = ?||'-12' then a.ze else  0 end shieryue,yrdwdm ");
		sql.append(" from (select yrdwdm, ffndyf, sum(nvl(bcje, 0)) ze ");
		sql.append(" from xg_qgzx_zjdx_cjffb ");
		sql.append(" where ffndyf like '%'||?||'%' ");
		sql.append(" and yrdwdm = ? ");
		sql.append(" and sftj = '1' ");
		sql.append(" group by yrdwdm, ffndyf ");
		sql.append(" order by ffndyf)a ");
		sql.append(" ) group by yrdwdm )c left join ( ");
		sql.append(" select e.nd,e.yrdwdm,e.hbzje,f.ffzje,e.hbzje - f.ffzje syje ");
		sql.append(" from (select nd, yrdwdm, sum(nvl(hbje, 0)) hbzje ");
		sql.append(" from xg_qgzx_jfhbb ");
		sql.append(" group by (nd, yrdwdm)) e ");
		sql.append(" left join (select substr(ffndyf, 0, 4) nd, yrdwdm,sum(nvl(bcje, 0)) ffzje  from xg_qgzx_zjdx_cjffb  where sftj = '1' ");
		sql.append(" group by substr(ffndyf, 0, 4), yrdwdm) f on e.nd = f.nd and e.yrdwdm = f.yrdwdm where e.nd = ?)d ");
		sql.append(" on c.yrdwdm = d.yrdwdm ");
		String[] inputValue = {myForm.getNd(),myForm.getNd(),myForm.getNd(),myForm.getNd(),myForm.getNd(),
				myForm.getNd(),myForm.getNd(),myForm.getNd(),myForm.getNd(),myForm.getNd(),myForm.getNd(),myForm.getNd(),
				myForm.getNd(),myForm.getBm(),myForm.getNd()};
		return dao.getMapNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * @����: �Ƿ����ڹ�����Ա
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-12-20 ����05:24:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yhm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean sfQggly(String yhm) {
		String sql = "select count(1) num from XG_QGZX_QGGLYB where yhm = ? ";
		String num = dao.getOneRs(sql, new String[]{yhm}, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	/** ===================������ڹ�ͨ�÷���END===================*/
	
	/**
	 * ��ȡ����Դ�·�
	 */
	public List<HashMap<String,String>> getSourceMonth(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct nd from xg_qgzx_jfhbb where nd >=(select to_char(add_months(trunc(sysdate),-3),'yyyy-mm') from dual) order by nd asc ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:���ƾ��ѻ�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-4-24 ����11:37:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean copyJfhbData(String targetmonth,String hbsj,String sourcemonth) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_qgzx_jfhbb select ?,yrdwdm,?,hbje,bz,xq,xn from xg_qgzx_jfhbb where nd = ?");
		return dao.runUpdate(sql.toString(),new String[]{targetmonth,hbsj,sourcemonth});
	}
	
	/**
	 * 
	 * @����: ��ǰ�·��Ƿ���ھ��ѻ�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-4-24 ����03:24:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotJfhbDataExist(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_qgzx_jfhbb where nd = (select to_char(sysdate,'yyyy-mm') from dual)");
		String cnt = dao.getOneRs(sql.toString(),new String[]{},"cnt");
		return "0".equals(cnt) ? true :false;
	}
	
}
