package xsgzgl.gygl.gypy.gypywh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.gygl.comm.GyglNewService;
/**
 * ��Ԣ����-��Ԣ����-��Ԣ���Ź���
 * @author yeyipin
 * @since 2012.12.25 merry christmas
 */
public class GypywhDao extends DAO{

	/**
	 * ��Ԣ���Ų�ѯ
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> gypyCx(GypywhForm model,HttpServletRequest request) throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		String[] colList =new String[]{ "pyid", "xn", "xq","pylbmc","ldmc", "chmc", "qsh","qsxb","xh","xm","pysj" };

		//Ȩ�޿��� 	
		String searchTjQx="";
		// �û�����
		User user = model.getUser();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b", "xydm", "bjdm");
		
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		StringBuilder sql = new StringBuilder();
		
//		785:2014��7��3�� �������û����ݷ�Χ���ƣ�ѧ���ɷ���
		sql.append("select rownum r, a.* from (select * from (select distinct a.*,'' xh,'' xm from view_xg_gygl_pyxxwhb a left join ");
		sql.append("(select a.lddm, a.qsh, a.xh, b.bjdm, b.zydm, b.xydm, b.nj from xg_gygl_new_cwxxb a left join view_xsbfxx b on a.xh = b.xh ");
		sql.append("where a.xh is not null) b on a.lddm = b.lddm and a.qsh = b.qsh where 1=1 and pylbmc not like '%���ҳ�'"+searchTjByUser+" ");
		sql.append(" union all ");
		sql.append(" select distinct a.pyid, a.xn, (select xqmc from xqdzb where xqdm = a.xq) xq, a.pylbdm, a.pysj, a.lddm, '' qsh, ");
        sql.append(" a.ldmc, '' ch, '' chmc, '' qsxb, '' nj, '' xymc, a.pylbmc,'' xh,'' xm from (select a.*, b.nj, b.xymc, b.ldmc, c.pylbmc ");
        sql.append(" from xg_gygl_pyxxwhb a left join view_xg_gygl_new_qsxx b on a.lddm = b.lddm left join xg_gygl_pylbdmb c on a.pylbdm = c.pylbdm ");
        sql.append(" where 1 = 1 and a.qsh is null) a where 1 = 1"+searchTjByUser+" ");
        sql.append(" union all ");
        sql.append(" select distinct a.pyid, a.xn, (select xqmc from xqdzb where xqdm = a.xq) xq, a.pylbdm, a.pysj, a.lddm, case when a.qsh = '#' then '' else a.qsh end qsh, ");
        sql.append(" a.ldmc, case when a.ch = '#' then '' else a.ch end ch, case when a.ch = '#' then '' else a.ch end chmc, case when a.qsh <> '#' then a.qsxb else '' end qsxb, a.nj, a.xymc, a.pylbmc, a.xh, a.xm ");
        sql.append(" from (select a.*, t.nj || '' nj, t.xymc, b.ldmc,b.qsxb,c.pylbmc,t.xm from xg_gygl_pyxxwhb a left join view_xg_gygl_new_qsxx b on a.lddm = b.lddm ");
        sql.append(" left join xg_gygl_pylbdmb c on a.pylbdm = c.pylbdm left join view_xsbfxx t on a.xh = t.xh where 1 = 1) a ");
        sql.append(" where 1 = 1 and a.xh is not null"+searchTjByUser+") a order by a.pylbdm, a.pysj desc ");
		sql.append(" ) a where 1=1 ");
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, model);
	}

	/**
	 * ��Ԣ���Ų�ѯ for ����
	 * @param model
	 * @return
	 */
	public List<HashMap<String , String>> gypyCxForDc(GypywhForm model,HttpServletRequest request) throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		String[] colList =new String[]{ "pyid", "xn", "xq","pylbmc" , "pylbdm" , "ldmc" , "lddm" , "qsh", "ch","chmc" , "qsxb","nj","xymc","pysj","xh","xm" };

		//Ȩ�޿��� 	
		String searchTjQx="";
		
		// �û�����
		User user = model.getUser();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b", "xydm", "bjdm");
		
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		StringBuilder sql = new StringBuilder();
		
//		785:2014��7��3�� �������û����ݷ�Χ���ƣ�ѧ���ɷ���
		
		sql.append("select rownum r, a.* from (select distinct a.*,'' xh,'' xm from view_xg_gygl_pyxxwhb a left join ");
		sql.append("(select a.lddm, a.qsh, a.xh, b.bjdm, b.zydm, b.xydm, b.nj from xg_gygl_new_cwxxb a left join view_xsbfxx b on a.xh = b.xh ");
		sql.append("where a.xh is not null) b on a.lddm = b.lddm and a.qsh = b.qsh where 1=1 and pylbmc not like '%���ҳ�'"+searchTjByUser+" ");
		sql.append(" union all ");
		sql.append(" select distinct a.pyid, a.xn, (select xqmc from xqdzb where xqdm = a.xq) xq, a.pylbdm, a.pysj, a.lddm, '' qsh, ");
        sql.append(" a.ldmc, '' ch, '' chmc, '' qsxb, '' nj, '' xymc, a.pylbmc,'' xh,'' xm from (select a.*, b.nj, b.xymc, b.ldmc, c.pylbmc ");
        sql.append(" from xg_gygl_pyxxwhb a left join view_xg_gygl_new_qsxx b on a.lddm = b.lddm left join xg_gygl_pylbdmb c on a.pylbdm = c.pylbdm ");
        sql.append(" where 1 = 1 and a.qsh is null) a where 1 = 1"+searchTjByUser+" ");
        sql.append(" union all ");
        sql.append(" select distinct a.pyid, a.xn, (select xqmc from xqdzb where xqdm = a.xq) xq, a.pylbdm, a.pysj, a.lddm, case when a.qsh = '#' then '' else a.qsh end qsh, ");
        sql.append(" a.ldmc, case when a.ch = '#' then '' else a.ch end ch, case when a.ch = '#' then '' else a.ch end chmc, case when a.qsh <> '#' then a.qsxb else '' end qsxb, a.nj, a.xymc, a.pylbmc, a.xh, a.xm ");
        sql.append(" from (select a.*, t.nj || '' nj, t.xymc, b.ldmc,b.qsxb,c.pylbmc,t.xm from xg_gygl_pyxxwhb a left join view_xg_gygl_new_qsxx b on a.lddm = b.lddm ");
        sql.append(" left join xg_gygl_pylbdmb c on a.pylbdm = c.pylbdm left join view_xsbfxx t on a.xh = t.xh where 1 = 1) a ");
        sql.append(" where 1 = 1 and a.xh is not null"+searchTjByUser+" ");
		sql.append(" ) a where 1=1 ");
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj+searchTjQx, inputV, colList, model);
	}
	
	/**
	 * �����������б�
	 * @return
	 */
	public List<HashMap<String,String>> getPylbList() {
		String sql = "select * from xg_gygl_pylbdmb order by pylbdm";
		return getListNotOut(sql, new String[]{});
	}


	/**
	 * �������Ҳ�ѯ
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> pyqsCx(GypywhForm model,HttpServletRequest request) throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		String[] colList =new String[]{ "pkValue", "ldmc", "chmc","qsh","nj","xymc","cws","yzcws" };

		//Ȩ�޿��� 	
		String searchTjQx = "";
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		StringBuilder sql = new StringBuilder();
		sql.append(" select lddm||'!!@@!!'||qsh pkValue,rownum r,a.* from view_xg_gygl_new_qsxx a where not exists");
		sql.append("(select 1 from xg_gygl_pyxxwhb where a.lddm = lddm and a.qsh = qsh and xn = '"+model.getXn()+"' and xq = '"+model.getXqdm()+"' and pylbdm = '"+model.getPylbdm()+"' and pysj = '"+model.getPysj()+"') ");
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, model);
	}
	
	
	/**
	 * 
	 * @����: ����¥����ѯ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-8-24 ����03:44:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> �������� 
	 * @throws
	 */
	public ArrayList<String[]> pyldCx(GypywhForm model,HttpServletRequest request) throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		String[] colList =new String[]{ "pkValue", "ldmc", "ldxb","ldcs" };

		//Ȩ�޿��� 	
		String searchTjQx = "";
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.lddm pkValue, rownum r, a.* from xg_gygl_new_ldxxb a where not exists ");
		sql.append("(select 1 from xg_gygl_pyxxwhb where a.lddm = lddm and xn = '"+model.getXn()+"' and xq = '"+model.getXqdm()+"' and pylbdm = '"+model.getPylbdm()+"' and pysj = '"+model.getPysj()+"') ");
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, model);
	}
	
	/**
	 * 
	 * @����: ����¥���㳤���ҳ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-8-24 ����05:11:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> �������� 
	 * @throws
	 */
	public ArrayList<String[]> pyzzCx(GypywhForm model,HttpServletRequest request) throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		String[] colList =new String[]{ "pkValue", "ldmc", "chmc", "qsh", "gllx", "xb", "xh","xm","sjhm","qsdh","bz" };

		//Ȩ�޿��� 	
		String searchTjQx = "";
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		StringBuilder sql = new StringBuilder();
		sql.append(" select rownum r, a.lddm || '!!@@!!' || a.ch || '!!@@!!' || a.qsh || '!!@@!!' || a.xh pkValue, a.* from xg_view_gygl_new_gyglryb a where not exists ");
		sql.append("(select 1 from xg_gygl_pyxxwhb where a.lddm = lddm and a.qsh = qsh and xn = '"+model.getXn()+"' and xq = '"+model.getXqdm()+"' and pylbdm = '"+model.getPylbdm()+"' and pysj = '"+model.getPysj()+"') ");
		if("2".equalsIgnoreCase(model.getPydx())) {
			sql.append(" and a.gllx = '¥��' and a.xh is not null ");
		}else if("3".equalsIgnoreCase(model.getPydx())) {
			sql.append(" and a.gllx = '�㳤' and a.xh is not null ");
		}else if("4".equalsIgnoreCase(model.getPydx())) {
			sql.append(" and a.gllx = '���ҳ�' and a.xh is not null ");
		}
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, model);
	}
	

	/**
	 * ���ѧ������
	 * @param xqdm
	 * @return
	 */
	public String getXqmc(String xqdm) {
		String sql = "select xqmc from xqdzb where xqdm = ? ";
		return getOneRs(sql, new String[]{xqdm}, "xqmc");
	}

	/**
	 * ��������������
	 * @param pylbdm
	 * @return
	 */
	public String getPylbmc(String pylbdm) {
		String sql = "select pylbmc from xg_gygl_pylbdmb where pylbdm = ? ";
		return getOneRs(sql, new String[]{pylbdm}, "pylbmc");
	}


	/**
	 * ��Ԣ��������
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean gypyZj(List<String[]> params) throws SQLException {
		String sql = " insert into xg_gygl_pyxxwhb(xn,xq,pylbdm,pysj,lddm,qsh,xh,ch) values(?,?,?,?,?,?,?,?)";
		int[] result = runBatch(sql, params);
		return checkBatchResult(result);
	}
	
	
	/**
	 * ��Ԣ����ɾ��
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean gypySc(List<String[]> params) throws SQLException {
		String sql = " delete from xg_gygl_pyxxwhb where pyid = ? ";
		int[] result = runBatch(sql, params);
		return checkBatchResult(result);
	}

}
