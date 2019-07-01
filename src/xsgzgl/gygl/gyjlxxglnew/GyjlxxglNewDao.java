package xsgzgl.gygl.gyjlxxglnew;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.date.DateUtils;
import xsgzgl.gygl.comm.GyglNewService;
import xsgzgl.gygl.gyjlxxgl.GyjlxxglForm;

public class GyjlxxglNewDao extends DAO{
	DAO dao = DAO.getInstance();

	/**
	 * ��ü�����Ϣlist
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> gyjlxxwhCx(GyjlxxglNewForm myForm,HttpServletRequest request) throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//Ȩ�޿���
		String searchTjQx="";
		
		SearchService searchService=new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//ѧԺ�û�
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = searchTjByUser;
		}
		StringBuilder sql = new StringBuilder();
		String[] colList= new String[] { "pkValue","xh","xm","xb","bjmc","zsqs","wjsj","wjlb","cljgmc","shztmc" };
		if("12309".equals(Base.xxdm)){
			colList = new String[] { "pkValue","xh","xm","xb","bjmc","zsqs","wjsj","wjlb","cljgmc","shztmc" ,"czrxm","czrbm","czsj"};
		}
		//�����ʵ��ѧ
		if("13627".equals(Base.xxdm)){
			colList= new String[] { "pkValue","xh","xm","xb","bjmc","zsqs","wjsj","wjlb","cljgmc","shztmc","fdyxm" };
		}
		sql.append(" select a.*,rownum r from (select * from (select a.*,substr(wjsj,1,10) wjrq, (select xqmc from xqdzb where xqdm = wjxq) xqmc,  a.xh || '!!shen!!'  || a.gyjllbdm || '!!shen!!'  || wjsj pkValue," );
		sql.append("(select xm from view_xsxxb b where a.xh=b.xh) xm,");
		sql.append("(select xb from view_xsxxb b where a.xh=b.xh) xb,ldmc||','||qsh||','||cwh||'�Ŵ�' zsqs,");
		sql.append("(select gyjllbdlmc from xg_gygl_new_gyjllbdlb b where a.gyjllbdldm=b.gyjllbdldm)||'('||");
		sql.append("(select gyjllbmc from xg_gygl_new_gyjllbdmb b where a.gyjllbdm=b.gyjllbdm)||')' wjlb," );
		sql.append("nvl((select c.gyjlcfmc from (select * from xg_gygl_new_gyjlcflbb union select 'th','�˻�' from dual) c where a.cljg=c.gyjlcfdm),'δ����') cljgmc ");
		sql.append(",(case when shzt = 'wsh' then 'δ���' when shzt='tg' then 'ͨ��' when shzt='btg' then '��ͨ��' when shzt='th' then '�˻�' end)  shztmc ");
		sql.append(",t1.xm czrxm,t2.bmmc czrbm ");
		//�����ʵ��ѧ
		if("13627".equals(Base.xxdm)){
			sql.append(" ,fdy.xm fdyxm ");
		}
		sql.append(" from xg_gygl_new_gyjlb a  left join fdyxxb t1 on a.czr=t1.zgh left join ZXBZ_XXBMDM t2 on t1.bmdm=t2.bmdm ");
		//�����ʵ��ѧ
		if("13627".equals(Base.xxdm)){
			sql.append(" left join view_xg_fdyxx fdy on a.bjdm = fdy.bjdm "); 
		}
		sql.append(" ) a where 1=1 ");
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx+" order by wjsj desc ) a", inputV, colList, myForm);
	
	}
	
	/**
	 * ��ü��ɴ���
	 * @param request
	 * @return
	 */
	public List<HashMap<String, String>> getJldlList(HttpServletRequest request) {
		String sql = "select gyjllbdldm jldldm,gyjllbdlmc jldlmc from xg_gygl_new_gyjllbdlb order by gyjllbdldm";
		return dao.getList(sql, new String[]{},new String[]{"jldldm","jldlmc"});
	}
	
	
	/**
	 * ���ѧ��ס����Ϣ
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> gyjlxscx(GyjlxxglNewForm myForm,HttpServletRequest request) throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//Ȩ�޿���
		String searchTjQx="";
		
		SearchService searchService=new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//ѧԺ�û�
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = searchTjByUser;
		}
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "xh","xh","xm","xb","nj","xymc","bjmc","ldmc","qsh","cwh" };
		sql.append("select a.*,rownum r from (select a.* from xg_view_gygl_new_xszsgl a where sfzs='��'");
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx+" order by xh asc ) a", inputV, colList, myForm);
	
	}
	
	
	/**
	 * ���ѧ��ס����Ϣ
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getStuInfo(GyjlxxglNewForm myForm) throws Exception{
		String[] inputValue = new String[]{myForm.getXh()};
		StringBuilder sql = new StringBuilder();
		String[] outputValue = new String[] { "xh","xh","xm","xb","nj","xymc","bjmc","zymc","ldmc","qsh","cwh","qsdh" };
		sql.append("select a.* from xg_view_gygl_new_xszsgl a where sfzs='��' and xh = ? order by xh asc");
		// ====================SQLƴװ end================================
		return getMap(sql.toString(), inputValue, outputValue);
	}

	/**
	 * �����ü�����Ϣlist
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> gyjlxxclCx(GyjlxxglNewForm myForm,HttpServletRequest request) throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);

		//Ȩ�޿���
		String searchTjQx="";
		
		SearchService searchService=new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//ѧԺ�û�
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = searchTjByUser;
		}
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "pkValue","xh","xm","xb","zsqs","wjsj","wjlb","cljgmc","shztmc" };
		//�����ʵ��ѧ
		if("13627".equals(Base.xxdm)){
			 colList = new String[] { "pkValue","xh","xm","xb","zsqs","wjsj","wjlb","cljgmc","shztmc","ylzd3","fdyxm" };
		}
		sql.append(" select a.*,rownum r from (select * from (select a.*,substr(wjsj,1,10) wjrq,a.xh || '!!shen!!'  || a.gyjllbdm || '!!shen!!'  || wjsj pkValue,(select xm from view_xsxxb b where a.xh=b.xh) xm," );
		sql.append("(select xb from view_xsxxb b where a.xh=b.xh) xb,ldmc||','||qsh||','||cwh||'�Ŵ�' zsqs,");
		sql.append("(select gyjllbdlmc from xg_gygl_new_gyjllbdlb b where a.gyjllbdldm=b.gyjllbdldm)||'('||");
		sql.append("(select gyjllbmc from xg_gygl_new_gyjllbdmb b where a.gyjllbdm=b.gyjllbdm)||')' wjlb," );
		sql.append("nvl((select c.gyjlcfmc from (select * from xg_gygl_new_gyjlcflbb union select 'th','�˻�' from dual) c where a.cljg=c.gyjlcfdm),'δ����') cljgmc,");
		sql.append("case when shzt = 'wsh' then 'δ���' when shzt='tg' then 'ͨ��' when shzt='btg' then '��ͨ��' when shzt='th' then '�˻�' end  shztmc");
		//�����ʵ��ѧ
		if("13627".equals(Base.xxdm)){
			sql.append(",fdy.xm fdyxm");
		}
		sql.append(" from xg_gygl_new_gyjlb a");
		//�����ʵ��ѧ
		if("13627".equals(Base.xxdm)){
			sql.append(" left join view_xg_fdyxx fdy on a.bjdm = fdy.bjdm ");
		}
		sql.append(" ) a where 1=1 ");
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx+" order by cljg desc,wjsj desc ) a", inputV, colList, myForm);
	
	}

	/**
	 * ��˻�ü�����Ϣlist
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> gyjlxxshCx(GyjlxxglNewForm myForm,HttpServletRequest request) throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);

		//Ȩ�޿���
		String searchTjQx="";
		
		SearchService searchService=new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//ѧԺ�û�
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = searchTjByUser;
		}
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "pkValue","xh","xm","xb","zsqs","wjsj","wjlb","cljgmc","shztmc" };
		//�����ʵ��ѧ
		if("13627".equals(Base.xxdm)){
			colList = new String[] { "pkValue","xh","xm","xb","zsqs","wjsj","wjlb","cljgmc","shztmc","ylzd3","shsj","fdyxm" };
		}
		sql.append(" select a.*,rownum r from (select * from (select a.*,substr(wjsj,1,10) wjrq,a.xh || '!!shen!!'  || a.gyjllbdm || '!!shen!!'  || wjsj pkValue,(select xm from view_xsxxb b where a.xh=b.xh) xm," );
		sql.append("(select xb from view_xsxxb b where a.xh=b.xh) xb,ldmc||','||qsh||','||cwh||'�Ŵ�' zsqs,");
		sql.append("(select gyjllbdlmc from xg_gygl_new_gyjllbdlb b where a.gyjllbdldm=b.gyjllbdldm)||'('||");
		sql.append("(select gyjllbmc from xg_gygl_new_gyjllbdmb b where a.gyjllbdm=b.gyjllbdm)||')' wjlb," );
		sql.append(	"nvl((select c.gyjlcfmc from xg_gygl_new_gyjlcflbb c where a.cljg=c.gyjlcfdm),'δ����') cljgmc,");
		sql.append("case when shzt = 'wsh' then 'δ���' when shzt='tg' then 'ͨ��' when shzt='btg' then '��ͨ��' when shzt='th' then '�˻�' end  shztmc");
		//�����ʵ��ѧ���Ի�
		if("13627".equals(Base.xxdm)){
			sql.append(",fdy.xm fdyxm ");
		}
		sql.append(" from xg_gygl_new_gyjlb a");
		//�����ʵ��ѧ���Ի�
		if("13627".equals(Base.xxdm)){
			sql.append(" left join view_xg_fdyxx fdy on a.bjdm = fdy.bjdm ");
		}
		sql.append(") a where 1=1 and cljg !='wcl'");
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx+" order by shzt desc,wjsj desc ) a", inputV, colList, myForm);
	
	}

	/**
	 * �����ʷΥ����Ϣ
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getWjxxList(GyjlxxglNewForm myForm) throws Exception{
		String[] inputValue = new String[]{myForm.getXh()};
		StringBuilder sql = new StringBuilder();
		String[] outputValue = new String[] { "wjxn","xqmc","wjlb","wjsj","cljg","ylzd1" };
		sql.append("select ylzd1,wjxn,(select xqmc from xqdzb b where a.wjxq=b.xqdm)xqmc, (select gyjllbdlmc from xg_gygl_new_gyjllbdlb b where a.gyjllbdldm=b.gyjllbdldm)||'('||");
		sql.append("(select gyjllbmc from xg_gygl_new_gyjllbdmb b where a.gyjllbdm=b.gyjllbdm)||')' wjlb,wjsj,");
		sql.append("nvl((select c.gyjlcfmc from xg_gygl_new_gyjlcflbb c where a.cljg=c.gyjlcfdm),'δ����') cljg");
		sql.append(" from ( select * from xg_gygl_new_gyjlb ) a  where xh = ? and shzt='tg'");
		
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputValue, outputValue, myForm);
	}

	
	/**
	 * ��Ԣ������Ϣ��������
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean gyjlxxPlcl(List<String[]> params) throws SQLException {
		String sql = "update  xg_gygl_new_gyjlb a set cljg=?,dcqk=?,ylzd1=? where a.xh||'!!shen!!'||a.gyjllbdm||'!!shen!!'||wjsj=?";
		//�����ʵ��ѧ��ͨѧԺ
		if("13627".equals(Base.xxdm)){
			sql = "update  xg_gygl_new_gyjlb a set cljg=?,dcqk=?,ylzd1=?,ylzd3=? where a.xh||'!!shen!!'||a.gyjllbdm||'!!shen!!'||wjsj=?";
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * ��Ԣ������Ϣ�������
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean gyjlxxPlsh(List<String[]> params) throws SQLException {
		String sql = "  update  xg_gygl_new_gyjlb a set shzt=?,shyj=?,shr=?,shsj=? where a.xh||'!!shen!!'||a.gyjllbdm||'!!shen!!'||wjsj=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}

	/**
	 * ɾ����Ԣ������Ϣ
	 * @param myForm
	 * @param valArr
	 * @param username
	 * @return
	 */
	public boolean gyjlSc(GyjlxxglForm myForm, String[] valArr, String username) throws SQLException  {
		List<String[]> sqlList = new ArrayList<String[]>();
		String sql = "delete from xg_gygl_new_gyjlb where xh||'shen'||gyjllbdm||'shen'||wjsj=?";
		String[] val = null;
		for (String threeVal : valArr) {
			val = new String[1];
			val[0] = threeVal.replaceAll("!!", "");
			sqlList.add(val);
		}
		DAO dao = DAO.getInstance();
		boolean flag = dao.checkBatchResult(dao.runBatch(sql, sqlList));
		return flag;
	}
	/**
	 * ������Ԣ���ɴ���
	 */
	public boolean gyjlCancelCl(GyjlxxglForm myForm, String[] valArr, String username) throws SQLException  {
		List<String[]> sqlList = new ArrayList<String[]>();
		String sql = "update xg_gygl_new_gyjlb set dcqk='', ylzd1='',cljg='wcl',ylzd3 = '' where xh||'shen'||gyjllbdm||'shen'||wjsj=?";
		String[] val = null;
		for (String threeVal : valArr) {
			val = new String[1];
			val[0] = threeVal.replaceAll("!!", "");
			sqlList.add(val);
		}
		DAO dao = DAO.getInstance();
		boolean flag = dao.checkBatchResult(dao.runBatch(sql, sqlList));
		return flag;
	}
	/**
	 * ������Ԣ�������
	 */
	public boolean gyjlCancelSh(GyjlxxglForm myForm, String[] valArr, String username) throws SQLException  {
		List<String[]> sqlList = new ArrayList<String[]>();
		String sql = "update xg_gygl_new_gyjlb set shzt='wsh' , shr='' , shyj='' , shsj='' where xh||'shen'||gyjllbdm||'shen'||wjsj=?";
		String[] val = null;
		for (String threeVal : valArr) {
			val = new String[1];
			val[0] = threeVal.replaceAll("!!", "");
			sqlList.add(val);
		}
		DAO dao = DAO.getInstance();
		boolean flag = dao.checkBatchResult(dao.runBatch(sql, sqlList));
		return flag;
	}

	/**
	 * ѧ��������Ϣ��ѯ
	 * @param myForm
	 * @return
	 */
	public ArrayList<String[]> xsjlxxcx(GyjlxxglNewForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
		String[] colList =null;
		if("13033".equals(Base.xxdm)){
			colList = new String[] { "pk","wjxn","xqmc","gyjllbdlmc","gyjllbmc","wjsj","gyjlcfmc","ylzd1" };
		}
		else{
			colList = new String[] { "pk","wjxn","xqmc","gyjllbdlmc","gyjllbmc","wjsj","gyjlcfmc"};
		}
		sql.append(" select a.*,rownum r from (select a.xh||'!@'||a.gyjllbdm||'!@'||a.wjsj pk,a.wjxn,e.xqmc,a.ylzd1,b.gyjllbdlmc,c.gyjllbmc,a.wjsj,d.gyjlcfmc from xg_gygl_new_gyjlb a  ");
		sql.append(" left join xg_gygl_new_gyjllbdlb b on a.gyjllbdldm=b.gyjllbdldm ");
		sql.append(" left join xg_gygl_new_gyjllbdmb c on a.gyjllbdm=c.gyjllbdm ");
		sql.append(" left join xg_gygl_new_gyjlcflbb d on a.cljg = d.gyjlcfdm ");
		sql.append(" left join xqdzb e on a.wjxq=e.xqdm ");
		sql.append(" where a.xh=? and a.shzt='tg') a");
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQuery(sql.toString()," order by a.wjsj desc", new String[]{myForm.getXh()}, colList, myForm);
	}
	
	/**
	 * ��Ԣ������Ϣ�����Զ�������
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> gyjlxxwhExportCx(GyjlxxglNewForm myForm,HttpServletRequest request) throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//Ȩ�޿���
		String searchTjQx="";
		
		SearchService searchService=new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//ѧԺ�û�
		GyglNewService gyglNewService = new GyglNewService();
		//����request�е�path�ж��Ƿ���Ҫ��Ԣ����Ա���ݷ�Χ
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = searchTjByUser;
		}
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "pkValue","xh","xm","xb","bjmc","zsqs","wjsj","wjlb","cljgmc" };
		//�����ʵ��ѧ��ͨѧԺ
		if("13627".equals(Base.xxdm)){
			colList = new String[] { "pkValue","xh","xm","xb","bjmc","zsqs","wjsj","wjlb","cljgmc","fdyxm" };
		}
		/*sql.append(" select a.*,rownum r from (select * from (select a.*,substr(wjsj,1,10) wjrq,a.xh || '!!shen!!'  || a.gyjllbdm || '!!shen!!'  || wjsj pkValue," );
		sql.append("(select xm from view_xsxxb b where a.xh=b.xh) xm,");
		sql.append("(select xb from view_xsxxb b where a.xh=b.xh) xb,ldmc||','||qsh||','||cwh||'�Ŵ�' zsqs,");
		sql.append("(select gyjllbdlmc from xg_gygl_new_gyjllbdlb b where a.gyjllbdldm=b.gyjllbdldm)||'('||");
		sql.append("(select gyjllbmc from xg_gygl_new_gyjllbdmb b where a.gyjllbdm=b.gyjllbdm)||')' wjlb," );
		sql.append("nvl((select c.gyjlcfmc from xg_gygl_new_gyjlcflbb c where a.cljg=c.gyjlcfdm),'δ����') cljgmc ");
		sql.append("from xg_gygl_new_gyjlb a ) a where 1=1");*/
		sql.append("(select rownum r, b.gyjllbdlmc jldl, c.gyjllbmc jldb, a.shr shrxm, a.czr czrxm, a.*  ");
		//�����ʵ��ѧ��ͨѧԺ
		if("13627".equals(Base.xxdm)){
			sql.append(",fdy.xm fdyxm ");
		}
		sql.append(" from VIEW_NEW_DC_GYGL_GYJLXXWH a ");
		sql.append("  left join xg_gygl_new_gyjllbdlb b on a.gyjllbdldm = b.gyjllbdldm ");
		sql.append("  left join xg_gygl_new_gyjllbdmb c on c.gyjllbdm=a.gyjllbdm ");
		//�����ʵ��ѧ��ͨѧԺ
		if("13627".equals(Base.xxdm)){
			sql.append(" left join view_xg_fdyxx fdy ");
			sql.append(" on a.bjdm = fdy.bjdm ");
		}
		sql.append(" where 1=1 ");

		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj+searchTjQx+" order by wjsj desc ) a", inputV, colList, myForm);
	
	}

	/** 
	 * @����:��Ԣ������Ϣ���  �Զ��嵼��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-12-30 ����03:32:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> gyjlxxshExportCx(
			GyjlxxglNewForm model, HttpServletRequest request) throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//Ȩ�޿���
		String searchTjQx="";
		
		SearchService searchService=new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//ѧԺ�û�
		GyglNewService gyglNewService = new GyglNewService();
		//����request�е�path�ж��Ƿ���Ҫ��Ԣ����Ա���ݷ�Χ
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = searchTjByUser;
		}
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "pkValue","xh","xm","xb","bjmc","zsqs","wjsj","wjlb","shr","jldb","jldl","czr","cljgmc","ylzd1" };
		//�����ʵ��ѧ��ͨѧԺ
		if("13627".equals(Base.xxdm)){
			colList = new String[] { "pkValue","xh","xm","xb","bjmc","zsqs","wjsj","wjlb","shr","jldb","jldl","czr","cljgmc","ylzd1","fdyxm" };
		}
		sql.append("(select rownum r , b.gyjllbdlmc jldl,c.gyjllbmc jldb,a.shr shrxm,a.czr czrxm,a.* ");
		//�����ʵ��ѧ��ͨѧԺ
		if("13627".equals(Base.xxdm)){
			sql.append(",fdy.xm fdyxm ");
		}
		sql.append("  from VIEW_NEW_DC_GYGL_GYJLXXWH a ");
		sql.append("  left join xg_gygl_new_gyjllbdlb b on a.gyjllbdldm = b.gyjllbdldm ");
		//�����ʵ��ѧ��ͨѧԺ
		if("13627".equals(Base.xxdm)){
			sql.append(" left join view_xg_fdyxx fdy ");
			sql.append(" on a.bjdm = fdy.bjdm ");
		}
		sql.append("  left join xg_gygl_new_gyjllbdmb c on c.gyjllbdm=a.gyjllbdm where cljg !='wcl' ");

		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj+searchTjQx+" order by wjsj desc ) a", inputV, colList, model);
	
	}


}
