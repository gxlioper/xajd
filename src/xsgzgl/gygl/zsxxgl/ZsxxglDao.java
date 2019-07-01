package xsgzgl.gygl.zsxxgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.gygl.comm.GyglNewDAO;
import xsgzgl.gygl.comm.GyglNewInit;
import xsgzgl.gygl.comm.GyglNewService;
import xsgzgl.gygl.tsgl.TsglForm;

import common.Globals;
public class ZsxxglDao extends GyglNewDAO {
	DAO dao = DAO.getInstance();

	private static String xsxxb_ntzydx = "( select * from view_xsbfxx "
			+ "where (sfyby = '��' or sfyby is null) and (sfzx = '��У' or sfzx is null) )";

	/**
	 * 
	 * @����:��ȡ�������б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-15 ����03:41:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return List<HashMap<String,String>> ��������
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKtsList(ZsxxglForm zf, User user)
			throws Exception {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");

		StringBuffer sb = new StringBuffer();
		sb.append(" select * from ( ");
		sb
				.append(" select t.nj || '_' || t.xydm || '_' || t.zydm njxyzy, t.nj,t.xymc,t.xydm,t.zymc,t.zydm,count(*) dlxrs");
		sb.append(" from view_xsbfxx t");
		sb.append(" inner join view_xg_gygl_new_cwxx cwxx");
		sb.append(" on t.xh=cwxx.xh");
		sb.append(" where 1=1");
		sb.append(" and t.sfzx='����У'");
		sb.append(" group by t.nj,t.xymc,t.xydm,t.zymc,t.zydm");
		sb.append(" ) a where 1=1 ");
		sb.append(searchTjByUser);
		ZsxxglDaoForPage zfp = new ZsxxglDaoForPage();
		return zfp.getPageListOld(zf, sb.toString(), new String[] {});
	}
	/**
	 * @����: �����Уδ����ѧ����Ϣ�б�
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�	2015-12-29 ����09:34:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zf
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLxxsList(ZsxxglForm zf, User user)
	throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" select a.ldmc, a.qsh, a.cwh, t.xh,a.qsxb, t.xm, t.bjmc,t.zydm,t.xydm,t.nj");
		sb.append("  from view_xsxxb t");
		sb.append(" inner join view_xg_gygl_new_cwxx a");
		sb.append(" on t.xh = a.xh");
		sb.append(" where 1 = 1");
		sb.append(" and t.sfzx = '����У'");
		sb.append(" and t.nj = ?");
		sb.append("  and t.xydm = ?");
		sb.append("  and t.zydm = ?");
		ZsxxglDaoForPage zfp = new ZsxxglDaoForPage();
		return zfp.getPageListOld(zf, sb.toString(), new String[] {zf.getNj(),zf.getXydm(),zf.getZydm()});
		}

	/**
	 * 
	 * @����:��ȡ�������б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-15 ����03:41:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return List<HashMap<String,String>> ��������
	 * @throws Exception
	 */
	public int getHjrs(ZsxxglForm zf, User user)
			throws Exception {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");

		StringBuffer sb = new StringBuffer();
		sb.append(" select nvl(sum(dlxrs),0) dlxrs from ( ");
		sb
				.append(" select t.nj || '_' || t.xydm || '_' || t.zydm njxyzy, t.nj,t.xymc,t.xydm,t.zymc,t.zydm,count(*) dlxrs");
		sb.append(" from view_xsbfxx t");
		sb.append(" inner join view_xg_gygl_new_cwxx cwxx");
		sb.append(" on t.xh=cwxx.xh");
		sb.append(" where 1=1");
		sb.append(" and t.sfzx='����У'");
		sb.append(" group by t.nj,t.xymc,t.xydm,t.zymc,t.zydm");
		sb.append(" ) a where 1=1 ");
		sb.append(searchTjByUser);
		String count = dao.getOneRs(sb.toString(), new String[]{}, "dlxrs");
		return Integer.valueOf(count);
	}

	/**
	 * 
	 * @����:��ȡ�������޵�ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-15 ����04:38:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getTsxs(User user) {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		StringBuffer sb = new StringBuffer();
		sb.append("select t.xh");
		sb.append(" from view_xsxxb t");
		sb.append(" inner join view_xg_gygl_new_cwxx cwxx");
		sb.append(" on t.xh=cwxx.xh");
		sb.append(" where 1=1");
		sb.append(" and t.sfzx='����У'");
		sb.append(" ");
		sb.append(searchTjByUser);
		return dao.getListNotOut(sb.toString(), new String[] {});
	}

	/**
	 * ��ѯ��λ��Ϣ
	 * 
	 * @param model
	 * @return
	 */
	public List<String[]> queryCw(ZsxxglForm myForm, HttpServletRequest request)
			throws Exception {
		return queryCw(myForm, request, true);
	}

	public List<String[]> queryCw(ZsxxglForm myForm,
			HttpServletRequest request, boolean isHavePage) throws Exception {
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// Ȩ�޿���
		String searchTjQx = "";

		SearchService searchService = new SearchService();
		String searchTjByUser = "";
		
		CommService service = new CommService();
		User user = service.getUser(request);
		/*
		 * ��λ�������ݷ�Χ�� 1�����䵽ѧԺ xg_gygl_new_jbszb �� cwfpdx�ֶ�ֵΪxydm
		 * ѧԺ�û�����ѯ������û�����ѧԺ�Ĵ�λ ����Ա�û�����ѯ�û���Ͻ�༶����ѧԺ�Ŀմ�λ���Լ��༶ѧ����ס��λ 2������Ҫ�༶
		 * xg_gygl_new_jbszb �� cwfpdx�ֶ�ֵΪ bjdm ѧԺ�û�����ѯ������û�����ѧԺ�Ĵ�λ
		 * ����Ա�û�����ѯ������û���Ͻ�༶�Ĵ�λ 3������Ҫ�༶ xg_gygl_new_jbszb �� cwfpdx�ֶ�ֵΪ zydm
		 * ѧԺ�û�����ѯ������û�����ѧԺ�Ĵ�λ ����Ա�û�����ѯ�û���Ͻ�༶����רҵ�Ŀմ�λ���Լ��༶ѧ����ס��λ
		 */
		if ("xydm".equalsIgnoreCase(GyglNewInit.CWFPDX)) {
			
			// ����ԱȨ��
			boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
			// ������Ȩ��
			boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
			if (fdyqx || bzrqx) {// ����Ǹ���Ա������Σ������û���������ѧԺ�Ĺ�������
				searchTjByUser = getFdySearch(user);
			} else {
				searchTjByUser = searchService.getSearchTjByUser(request, "a",
						"xydm", "xsbjdm"); // ѧԺ�û�
			}
			searchTj = searchTj.replaceAll("bjdm", "xsbjdm");// �߼���ѯ����bjdm��ѯѧ�������༶
			searchTj = searchTj.replaceAll("zydm", "xszydm");// �߼���ѯ����zydm��ѯѧ������רҵ
		} else if ("bjdm".equalsIgnoreCase(GyglNewInit.CWFPDX)) {
			searchTjByUser = searchService.getSearchTjByUser(request, "a",
					"xydm", "bjdm");
		} else if ("zydm".equalsIgnoreCase(GyglNewInit.CWFPDX)) {
			
			// ����ԱȨ��
			boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
			// ������Ȩ��
			boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
			if (fdyqx || bzrqx) {// ����Ǹ���Ա������Σ������û���������רҵ�Ĺ�������

				searchTjByUser = getFdySearchZyfp(user);

			} else if ("xy".equalsIgnoreCase(user.getUserType())) {
				searchTjByUser = searchService.getSearchTjByUser(request, "a",
						"xydm", "xsbjdm"); // ѧԺ�û�
			}
		}
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // ��Ԣ����Ա���ݷ�Χ��������

		/*
		 * ���û�Ϊ��Ԣ����Աʱ��xg_gygl_new_gyfdyb �� ���ڸ��û����� ����������ΪѧԺ���߸���Ա��Ȩ�޿��ƣ�ֻ����¥�����ݷ�Χ
		 */
		if("xy".equalsIgnoreCase(user.getUserType()) && "11799".equals(Base.xxdm)){
			searchTjQx = " and (a.xydm = '" + user.getUserDep() + "'  or a.xh is null )" ;
		} else if (searchTjByGyfdy != null && !"".equalsIgnoreCase(searchTjByGyfdy)) {// �û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		} else {// �û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = searchTjByUser;
		}

		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();

		// sql.append("select rownum r,a.* from (select lddm||qsh||cwh pkValue,a.*,b.xm,"
		// +
		// "(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis "+
		// "from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where 1=1 ");
		if (Globals.XXDM_NTZYDX.equals(Base.xxdm)) {// ������ְͨҵ���Ի���ѧ��ʽ
			sql
					.append("select rownum r,a.* from (select lddm||qsh||cwh pkValue,a.*,b.xm,"
							+ "(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis,(case when length(a.bz)>5 then substr(a.bz,1,5)||'...' else a.bz end)subbz, a.bjmc cwbjmc,a.xymc cwxymc,b.bjmc xsbjmc, b.zymc xszymc "
							+ "from view_xg_gygl_new_cwxx a left join "
							+ xsxxb_ntzydx
							+ " b on a.xh=b.xh order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where 1=1 ");
		}else if(Globals.XXDM_DBSYDX.equals(Base.xxdm)){// ����ʯ�ʹ�ѧ���Ի���������ס������������ʱ��
			sql .append("select rownum r,a.* from (select a.*,(select xymc from zxbz_xxbmdm where bmdm=a.xydm) cwxymc,(select bjmc from bks_bjdm where bjdm=a.bjdm) cwbjmc,b.tzsj,decode(a.xh,'',c.tssj,'') tssj from (select lddm||qsh||cwh pkValue,a.*,b.xm,b.zymc xszymc,b.zydm xszydm, b.bjmc xsbjmc,b.bjdm xsbjdm,b.xz,b.pycc,b.sjhm,"
							+ "(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis,(case when length(a.bz)>5 then substr(a.bz,1,5)||'...' else a.bz end)subbz "
							+ "from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh=b.xh order by a.lddm,to_number(a.ch),a.qsh,to_number(a.cwh)) a left join (select ydhlddm,ydhqsh,ydhcwh,tstzsj tzsj from " 
							+ "(SELECT a.*,row_number()over(partition by ydhlddm,ydhqsh,ydhcwh order by tstzsj desc ) rn FROM XG_GYGL_NEW_SSYD_SSYDJG a) where rn =1 ) b on a.lddm=b.ydhlddm and a.qsh=b.ydhqsh and a.cwh=b.ydhcwh ");
			sql.append("left join (select ydqlddm,ydqqsh,ydqcwh,tstzsj tssj from ( ");
			sql.append("SELECT a.*,row_number()over(partition by ydqlddm,ydqqsh,ydqcwh order by tstzsj desc ) rn FROM XG_GYGL_NEW_SSYD_SSYDJG a) where rn =1 ) c ");
			sql.append("on a.lddm=c.ydqlddm and a.qsh=c.ydqqsh and a.cwh=c.ydqcwh ) a ");
			sql.append("where 1=1 ");
		}else if("13033".equalsIgnoreCase(Base.xxdm)) {// ���ϻ���ְҵ����ѧԺ
			sql.append("select rownum r,a.*,(select xymc from zxbz_xxbmdm where bmdm=a.xydm) cwxymc,(select bjmc from bks_bjdm where bjdm=a.bjdm) cwbjmc from (select lddm||qsh||cwh pkValue,a.*,b.xm,b.zymc xszymc,b.zydm xszydm, b.bjmc xsbjmc,b.bjdm xsbjdm,b.xz,b.pycc,b.sjhm,"
					+ "(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis,(case when length(a.bz)>5 then substr(a.bz,1,5)||'...' else a.bz end)subbz, "
					+ "(case when c.n is null then 0 else c.n end) n "
					+ "from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh=b.xh  " 
					+ "left join (select count(1) n, a.xh from xg_wjcf_wjcfb a group by xh) c on a.xh = c.xh "
					+ "order by a.lddm,to_number(a.ch),a.qsh,to_number(a.cwh)) a where 1=1 ");
		}else {
			//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
			String sb = "";
			if("12898".equals(Base.xxdm)){
				sb = "a.cwh";
			}else{
				sb = "a.cwh";
			}
			if("10868".equals(Base.xxdm)){
				sql.append(" select rownum r,a.*,(select xymc from zxbz_xxbmdm where bmdm = a.xydm) cwxymc,(select bjmc from bks_bjdm where bjdm = a.bjdm) cwbjmc");
				sql.append(" from (select lddm || qsh || cwh pkValue,a.*,b.xm,b.zymc xszymc,b.zydm xszydm,b.bjmc xsbjmc, b.bjdm xsbjdm,b.xz,b.pycc,b.sjhm,(case when a.xydm is not null or a.nj is not null or");
				sql.append(" a.xh is not null then 'disabled'  else '' end) dis,(case when length(a.bz) > 5 then substr(a.bz, 1, 5) || '...' else a.bz end) subbz,");
				sql.append(" c.gxsj,c.sqxj,c.cxsj  from view_xg_gygl_new_cwxx a");
				sql.append("  left join view_xsbfxx b  on a.xh = b.xh");
				sql.append(" left join ( select * from (select row_number() over(partition by t.lddm , t.qsh order by t.gxsj desc) rn,t.gxsj,t.sqxj,t.cxsj,t.lddm || t.qsh qshx from xg_gygl_new_xjqsjgb t) where rn = 1 )c");
				sql.append(" on a.lddm || a.qsh = c.qshx  order by a.lddm, to_number(a.ch), a.qsh, "+sb+") a  where 1 = 1");
			}else{
				sql.append("select rownum r,a.*,(select xymc from zxbz_xxbmdm where bmdm=a.xydm) cwxymc,(select bjmc from bks_bjdm where bjdm=a.bjdm) cwbjmc from (select lddm||qsh||cwh pkValue,a.*,a.xymc as xsxymc,b.xm,b.zymc xszymc,b.zydm xszydm, b.bjmc xsbjmc,b.bjdm xsbjdm,b.xz,b.pycc,b.sjhm,"
						+ "(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis,(case when length(a.bz)>5 then substr(a.bz,1,5)||'...' else a.bz end)subbz "
						+ "from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh=b.xh order by a.lddm,a.ch,a.qsh,"+sb+") a where 1=1 ");
			}
			
		}

		String[] output = null;
		if (Globals.XXDM_zjgmzyjsxy.equalsIgnoreCase(Base.xxdm)) {
			output = new String[] { "pkValue", "dis", "ldmc", "qsh", "cwh",
					"qsxb", "xh", "xm", "xszymc", "xsbjmc", "sfbl", "nj",
					"xydm", "bjdm", "cwxymc", "cwbjmc", "zymc", "sjhm" };
		} else if("13033".equalsIgnoreCase(Base.xxdm)) {// ���ϻ���ְҵ����ѧԺ
			output = new String[] { "pkValue", "dis", "ldmc", "qsh", "cwh",
					"qsxb", "xh", "xm", "xszymc", "xsbjmc", "sfbl", "nj",
					"xydm", "bjdm", "cwxymc", "cwbjmc", "zymc", "subbz", "bz", "n" };
		}else if("10868".equals(Base.xxdm)){
			output = new String[] { "pkValue", "dis", "ldmc", "qsh", "cwh",
					"qsxb", "xh", "xm", "xszymc", "xsbjmc", "sfbl", "nj",
					"xydm", "bjdm", "cwxymc", "cwbjmc", "zymc", "subbz", "bz","gxsj","sqxj","cxsj"};
		}
		else if("12216".equals(Base.xxdm)){
			output = new String[] { "pkValue", "dis", "ldmc", "qsh", "cwh",
					"qsxb", "xh", "xm","xszymc", "xsbjmc", "sfbl", "nj",
					"xydm", "bjdm", "cwxymc", "cwbjmc", "zymc", "subbz", "bz", "lddm","xymc" };
		}else {
			output = new String[] { "pkValue", "dis", "ldmc", "qsh", "cwh",
					"qsxb", "xh", "xm", "xszymc", "xsbjmc", "sfbl", "nj",
					"xydm", "bjdm", "cwxymc", "cwbjmc", "zymc", "subbz", "bz", "lddm" };
		}
		try {
			String var = null;
			for(int i=0 ; i<output.length;i++){
				var += output[i]+",";
			}
			System.out.println(var);
			if (isHavePage) {
				list = CommonQueryDAO.commonQuery(sql.toString(), searchTj
						+ searchTjQx, inputV, output, myForm);
			} else {
				list = CommonQueryDAO.commonQueryNotFy(sql.toString(), searchTj
						+ searchTjQx, inputV, output, myForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @����:(����Ǹ���Ա���ǰ����ν�ɫ�����ɹ���������ѯ����Ա�������ڵ�רҵ�Ĵ�λ)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-9-5 ����10:04:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return String ��������
	 * @throws
	 */
	private String getFdySearchZyfp(User user) {
		// ����ԱȨ��
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// ������Ȩ��
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		// �û���
		String userName = user.getUserName();
		String search = "";
		if (!fdyqx && bzrqx) {// ֻ�а�����Ȩ��
			search = " and ((( sfrz = '��' ) and exists(select 1 from (select distinct xydm,nj,zydm from view_njxyzybj a where exists(select 1 from (select * from bzrbbb where zgh='"
					+ userName
					+ "' ) where bjdm=a.bjdm )) where a.xydm=xydm and nj=a.nj and zydm=a.zydm))or  (exists (select 1 from bzrbbb b where a.xsbjdm =  b.bjdm  and b.zgh = '"
					+ userName + "') ) )";
		} else if (fdyqx && !bzrqx) {// ֻ�и���ԱȨ��
			search = " and ((( sfrz = '��' ) and exists(select 1 from (select distinct xydm,nj,zydm from view_njxyzybj a where exists(select 1 from (select * from fdybjb where zgh='"
					+ userName
					+ "' ) where bjdm=a.bjdm )) where a.xydm=xydm and nj=a.nj and zydm=a.zydm))or  (exists (select 1 from fdybjb b where a.xsbjdm =  b.bjdm  and b.zgh = '"
					+ userName + "')) )";
		} else if (fdyqx && bzrqx) {// ����Ա������Ȩ��
			search = " and ((( sfrz = '��' ) and exists(select 1 from (select distinct xydm,nj,zydm from view_njxyzybj a where exists(select 1 from (select * from bzrbbb where zgh='"
					+ userName
					+ "' union select * from fdybjb where zgh='"
					+ userName
					+ "') where bjdm=a.bjdm )) where a.xydm=xydm and nj=a.nj and zydm=a.zydm))or  (exists (select 1 from bzrbbb b where a.xsbjdm =  b.bjdm  and b.zgh = '"
					+ userName
					+ "')  or exists (select 1 from fdybjb b where a.xsbjdm =  b.bjdm  and b.zgh = '"
					+ userName + "')) )";
		}
		return search;
	}

	/**
	 * @����:TODO(����Ǹ���Ա���ǰ����ν�ɫ�����ɹ���������ѯ����Ա�������ڵ�ѧԺ�Ĵ�λ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-6-18 ����05:50:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return String ��������
	 * @throws
	 */
	public String getFdySearch(User user) {
		// TODO �Զ����ɷ������
		// ����ԱȨ��
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// ������Ȩ��
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		// �û���
		String userName = user.getUserName();
		String search = "";
		if (!fdyqx && bzrqx) {// ֻ�а�����Ȩ��
			search = " and ((( sfrz = '��' ) and exists(select 1 from (select distinct xydm,nj from view_njxyzybj a where exists(select 1 from (select * from bzrbbb where zgh='"
					+ userName
					+ "' ) where bjdm=a.bjdm )) where a.xydm=xydm and nj=a.nj))or  (exists (select 1 from bzrbbb b where a.xsbjdm =  b.bjdm  and b.zgh = '"
					+ userName + "') ) )";
		} else if (fdyqx && !bzrqx) {// ֻ�и���ԱȨ��
			search = " and ((( sfrz = '��' ) and exists(select 1 from (select distinct xydm,nj from view_njxyzybj a where exists(select 1 from (select * from fdybjb where zgh='"
					+ userName
					+ "') where bjdm=a.bjdm )) where a.xydm=xydm and nj=a.nj))or  (exists (select 1 from fdybjb b where a.xsbjdm =  b.bjdm  and b.zgh = '"
					+ userName + "')) )";
		} else if (fdyqx && bzrqx) {// ����Ա������Ȩ��
			search = " and ((( sfrz = '��' ) and exists(select 1 from (select distinct xydm,nj from view_njxyzybj a where exists(select 1 from (select * from bzrbbb where zgh='"
					+ userName
					+ "' union select * from fdybjb where zgh='"
					+ userName
					+ "') where bjdm=a.bjdm )) where a.xydm=xydm and nj=a.nj))or  (exists (select 1 from bzrbbb b where a.xsbjdm =  b.bjdm  and b.zgh = '"
					+ userName
					+ "')  or exists (select 1 from fdybjb b where a.xsbjdm =  b.bjdm  and b.zgh = '"
					+ userName + "')) )";
		}
		return search;
	}

	/**
	 * ��ȡ��ǰ��ѯ���ݼ�����ס�Ĵ�λ����
	 * 
	 * @return
	 */
	public String getYrzrs(ZsxxglForm myForm, HttpServletRequest request)
			throws Exception {
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		// Ȩ�޿���
		String searchTjQx = "";
		
		CommService service = new CommService();
		User user = service.getUser(request);
		
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a",
				"xydm", "xsbjdm"); // ѧԺ�û�
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // ��Ԣ����Ա

		if("xy".equalsIgnoreCase(user.getUserType()) && "11799".equals(Base.xxdm)){
			searchTjQx = " and (a.xydm = '" + user.getUserDep() + "'  or a.xh is null )" ;
		} else if (searchTjByGyfdy != null && !"".equalsIgnoreCase(searchTjByGyfdy)) {// �û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		} else {// �û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = searchTjByUser;
		}
		
		StringBuffer sql = new StringBuffer();
		if (Globals.XXDM_NTZYDX.equals(Base.xxdm)) {
			sql.append("select count(*) num ");
			sql.append("from (select a.*,b.xm,b.rxfs rxfsdm,b.bjdm xsbjdm from view_xg_gygl_new_cwxx a");	
			sql.append(" left join view_xsxxb b on a.xh=b.xh) a where 1=1 "); 
		}else if(Globals.XXDM_DBSYDX.equals(Base.xxdm)){
			sql.append("select count(*) num ");  
			sql.append("from (select a.*, b.xm, b.xz, b.pycc, b.bjdm xsbjdm ");
			sql.append("from view_xg_gygl_new_cwxx a ");
			sql.append("left join view_xsjbxx b ");
			sql.append("on a.xh = b.xh) a ");			
			sql.append("left join (select ydhlddm, ydhqsh, ydhcwh, tstzsj tzsj from (");	
			sql.append("SELECT a.*,row_number() over(partition by ydhlddm, ydhqsh, ydhcwh order by tstzsj desc) rn ");			 
			sql.append("FROM XG_GYGL_NEW_SSYD_SSYDJG a) ");			
			sql.append("where rn = 1) b ");			
			sql.append("on a.lddm = b.ydhlddm ");
			sql.append("and a.qsh = b.ydhqsh ");			
			sql.append("and a.cwh = b.ydhcwh ");
			sql.append("left join (select ydqlddm, ydqqsh, ydqcwh, tstzsj tssj ");
			sql.append("from (SELECT a.*,");
			sql.append("row_number() over(partition by ydqlddm, ydqqsh, ydqcwh order by tstzsj desc) rn ");
			sql.append("FROM XG_GYGL_NEW_SSYD_SSYDJG a) ");
			sql.append("where rn = 1) c ");
			sql.append("on a.lddm = c.ydqlddm ");
			sql.append("and a.qsh = c.ydqqsh ");          
			sql.append("and a.cwh = c.ydqcwh ");        
			sql.append("where 1 = 1");
		}else{
			sql.append("select count(*) num from (");
			sql.append("select a.*,b.xm,b.xz,b.pycc,b.bjdm xsbjdm from ");
			sql.append("view_xg_gygl_new_cwxx a ");
			sql.append("left join view_xsjbxx b on a.xh=b.xh) a where 1=1 ");
		}

		String rs = dao.getOneRs(
				sql.toString() + searchTj + searchTjQx + " and sfrz ='��'", inputV, "num");
		return rs;
	}

	/**
	 * ��λ�Ե�
	 * 
	 * @param pk
	 * @return
	 */
	public boolean cwdd(String[] pk, ZsxxglForm model) {
		boolean flag = false;
		TsglForm tsglForm = new TsglForm();
		tsglForm.setTsyy("ס���춯");
		tsglForm.setTssj(model.getRzsj());
		tsglForm.setBz(model.getBz());
		tsglForm.setTsczr(model.getCzr());

		if (pk != null && pk.length == 2) {
			String sql = "select xh,qsxb from view_xg_gygl_new_cwxx where lddm||qsh||cwh=?";
			Map<String, String> cwxx1 = dao.getMap(sql, new String[] { pk[0] },
					new String[] { "xh", "qsxb" });
			Map<String, String> cwxx2 = dao.getMap(sql, new String[] { pk[1] },
					new String[] { "xh", "qsxb" });

			if (Base.isNull(cwxx1.get("qsxb"))
					|| !cwxx1.get("qsxb").equals(cwxx2.get("qsxb"))) {
				return flag;
			}
			String xh1 = cwxx1.get("xh");
			String xh2 = cwxx2.get("xh");

			sql = "select nj,xydm,bjdm,zydm from view_xsjbxx where xh=?";
			List<String> sqlArr = new ArrayList<String>();
			String[] njxy;
			// �����һ����λ��ѧ����sql���ڵڶ�����λ��
			if (!Base.isNull(xh1)) {
				njxy = dao.getOneRs(sql, new String[] { xh1 }, new String[] {
						"nj", "xydm", "bjdm", "zydm" });
				sqlArr.add("update xg_gygl_new_cwxxb set nj='" + njxy[0]
						+ "',xydm='" + njxy[1] + "',bjdm='" + njxy[2]
						+ "',zydm='" + njxy[3] + "',xh='" + xh1 + "',"
						+ "rzsj='" + model.getRzsj() + "',rzyydm='"
						+ model.getRzyy() + "',rzczr='" + model.getCzr()
						+ "' where lddm||qsh||cwh='" + pk[1] + "'");
				tsglForm.setLddm(pk[0]);
				sqlArr.add(getTsxxSql(tsglForm));
			} else {
				sqlArr
						.add("update xg_gygl_new_cwxxb set xh='',rzsj='',rzczr='' where lddm||qsh||cwh='"
								+ pk[1] + "'");
			}
			// ����ڶ�����λ��ѧ����sql���ڵ�һ����λ��
			if (!Base.isNull(xh2)) {
				njxy = dao.getOneRs(sql, new String[] { xh2 }, new String[] {
						"nj", "xydm", "bjdm", "zydm" });
				sqlArr.add("update xg_gygl_new_cwxxb set nj='" + njxy[0]
						+ "',xydm='" + njxy[1] + "',bjdm='" + njxy[2]
						+ "',zydm='" + njxy[3] + "',xh='" + xh2 + "',"
						+ "rzsj='" + model.getRzsj() + "',rzyydm='"
						+ model.getRzyy() + "',rzczr='" + model.getCzr()
						+ "' where lddm||qsh||cwh='" + pk[0] + "'");
				tsglForm.setLddm(pk[1]);
				sqlArr.add(getTsxxSql(tsglForm));
			} else {
				sqlArr
						.add("update xg_gygl_new_cwxxb set xh='',rzsj='',rzczr='' where lddm||qsh||cwh='"
								+ pk[0] + "'");
			}

			CommDAO dao = new CommDAO();
			try {
				flag = dao.saveArrDate(sqlArr.toArray(new String[] {}));
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}

		return flag;
	}

	/**
	 * ��ѯ��λ��Ϣ_(������ͷ���õĽ����)
	 * 
	 * @param model
	 * @return
	 */
	public List<String[]> queryCw_expCtk(ZsxxglForm myForm,
			HttpServletRequest request) throws Exception {

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// Ȩ�޿���
		String searchTjQx = "";

		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a",
				"xydm", "bjdm"); // ѧԺ�û�
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // ��Ԣ����Ա

		if (searchTjByGyfdy != null && !"".equalsIgnoreCase(searchTjByGyfdy)) {// �û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		} else {// �û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = searchTjByUser;
		}

		// �����ѡ��ĸ�ѡ����ô�Ͱ��ո�ѡ�����ѡ��
		String[] pks = myForm.getPrimarykey_checkVal();
		if (pks != null && pks.length > 0) {
			if (pks.length > 1000) {
				new ArrayList<String[]>();
			}
			searchTj = " and lddm||qsh||cwh in (";
			searchTjQx = "";
			inputV = pks;

			for (int i = 0; i < pks.length; i++) {
				searchTj += "?,";
			}
			searchTj = searchTj.substring(0, searchTj.length() - 1);
			searchTj += ") ";
		}

		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();

		sql
				.append("select rownum r,a.* from (select lddm||qsh||cwh pkValue,a.*,b.xm,"
						+ "(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis "
						+ ",c.xymc xsxymc,c.zymc xszymc,c.bjmc xsbjmc "
						+ "from view_xg_gygl_new_cwxx a left join "
						+ Base.xsxxb
						+ " b on a.xh=b.xh "
						+ "left join view_njxyzybj_all c on b.bjdm=c.bjdm "
						+ "order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where 1=1 ");

		// sql.append("select * from (" +
		// "select a.lddm,a.ldmc,a.qsh,a.cwh,a.xh,b.xm,c.xymc,c.zymc,c.bjmc,a.bjdm "
		// +
		// "from view_xg_gygl_new_cwxx a " +
		// "left join "+Base.xsxxb+" b on a.xh=b.xh " +
		// "left join view_njxyzybj_all c on b.bjdm=c.bjdm " +
		// "order by a.lddm,to_number(a.ch),a.qsh,a.cwh" +
		// ") a where 1=1 ");

		String[] output = new String[] { "ldmc", "qsh", "cwh", "xh", "xm",
				"xsxymc", "xszymc", "xsbjmc" };

		try {
			list = CommonQueryDAO.commonQueryNotFy(sql.toString(), searchTj
					+ searchTjQx, inputV, output, myForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @����:TODO(��ȡ��λ����ʱ�䷶Χ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-6-20 ����03:17:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return String ��������
	 * @throws
	 */
	public String getCwczsjfw() {
		// TODO �Զ����ɷ������
		DAO dao = DAO.getInstance();
		String sql = "select csz from xg_gygl_new_jbszb where csdm='fdyczsjsz'";
		dao.getOneRs(sql, new String[] {}, "csz");
		return dao.getOneRs(sql, new String[] {}, "csz");
	}

	/**
	 * ס����Ϣ�����Զ��嵼��
	 * 
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> queryExportCw(ZsxxglForm myForm,
			HttpServletRequest request) throws Exception {

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// Ȩ�޿���
		String searchTjQx = "";

		SearchService searchService = new SearchService();
		String searchTjByUser = "";
		if ("xydm".equalsIgnoreCase(GyglNewInit.CWFPDX)) {
			searchTjByUser = searchService.getSearchTjByUser(request, "a",
					"xydm", "xsbjdm"); // ѧԺ�û�
			searchTj = searchTj.replaceAll("bjdm", "xsbjdm");
			searchTj = searchTj.replaceAll("zydm", "xszydm");
		}
		if ("bjdm".equalsIgnoreCase(GyglNewInit.CWFPDX)) {
			searchTjByUser = searchService.getSearchTjByUser(request, "a",
					"xydm", "bjdm");
		}
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // ��Ԣ����Ա

		if (searchTjByGyfdy != null && !"".equalsIgnoreCase(searchTjByGyfdy)) {// �û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		} else {// �û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = searchTjByUser;
		}

		StringBuilder sql = new StringBuilder();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (Globals.XXDM_NTZYDX.equals(Base.xxdm)) {// ������ְͨҵ���Ի���ѧ��ʽ
			sql
					.append("select rownum r,a.* from (select lddm||qsh||cwh pkValue,a.*,b.xm,"
							+ "(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis ,a.bjmc cwbjmc,a.xymc cwxymc,b.bjmc xsbjmc, b.zymc xszymc "
							+ "from view_xg_gygl_new_cwxx a left join "
							+ xsxxb_ntzydx
							+ " b on a.xh=b.xh order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where 1=1 ");
		} else {
			sql.append("select rownum r,a.*,(select xymc from zxbz_xxbmdm where bmdm=a.xydm) cwxymc,(select bjmc from bks_bjdm where bjdm=a.bjdm) cwbjmc ");
			sql.append("from (select lddm||qsh||cwh pkValue,a.*,b.xm,b.zymc xszymc,b.zydm xszydm, b.sjhm lxfs, b.bjmc xsbjmc,b.bjdm xsbjdm,e.bzr,f.fdy, ");
			sql.append("(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis ,b.xz,b.pycc,g.tzjl ");
			if("10868".equals(Base.xxdm)){
				sql.append(" ,c.gxsj,c.sqxj,c.cxsj ");
			}
			sql.append("from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh left join (select bjdm, wm_concat(bzr) bzr from ");
			sql.append("(select a.bjdm, b.xm || b.lxdh bzr from bzrbbb a left join fdyxxb b on a.zgh = b.zgh) group by bjdm) e on b.bjdm = e.bjdm ");
			sql.append(" left join (select wm_concat(fdy) fdy, bjdm from (select b.xm || b.lxdh fdy, a.bjdm from fdybjb a left join ");
			sql.append("fdyxxb b on a.zgh = b.zgh )group by bjdm) f on b.bjdm = f.bjdm ");
			sql.append("left join (select * from (select t.xh, wm_concat(t.ydjl) over(partition by xh order by ydjl) tzjl, row_number() over(partition by xh order by ydjl desc) r from "
					+ "(select t.xh, case when t.ssydlx = '01' then tstzsj || ' ' || ssydlxmc || ' ' || tsqs || ' -> ' || rzqs else tstzsj || ' ' || ssydlxmc || ' ' || tsqs || rzqs end ydjl "
					+ "from (select * from (select t.xh, t.ssydlx, t.tstzsj, t.ydqldmc || '_' || t.ydqqsh || '_' || t.ydqcwh tsqs, case when t.ydhldmc is not null and t.ydhqsh "
					+ "is not null and t.ydhcwh is not null then t.ydhldmc || '_' || t.ydhqsh || '_' || t.ydhcwh else '' end as rzqs, case when t.ssydlx = '00' then '����' "
					+ "when t.ssydlx = '01' then '�������' when t.ssydlx = '03' then '��ס' end ssydlxmc from XG_GYGL_NEW_SSYD_SSYDJG t) ) t) t) where r = 1) g on a.xh = g.xh "); //����ѧ�����ҵ�����¼�ֶ�
			if("10868".equals(Base.xxdm)){
				sql.append(" left join (select * from (select row_number() over(partition by t.lddm , t.qsh order by t.gxsj desc) rn,t.gxsj,t.sqxj,t.cxsj,t.lddm || t.qsh qshx from xg_gygl_new_xjqsjgb t) where rn = 1");
				sql.append("  )c on a.lddm || a.qsh = c.qshx ");
			}
			sql.append( "order by a.lddm,to_number(a.ch),a.qsh,to_number(a.cwh)) a where 1=1 ");
		}

		String[] output = null;
		output = new String[] { "pkValue", "dis", "ldmc", "qsh", "cwh", "qsxb",
				"xh", "xm", "xszymc", "xsbjmc", "sfbl", "nj", "xydm", "bjdm",
				"cwxymc", "cwbjmc", "sfbz","bzr","fdy","lxfs" };

		try {
			list = CommonQueryDAO.commonQueryforExportList(sql.toString(),
					searchTj + searchTjQx, inputV, output, myForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * �����סԭ����Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRzyyList(ZsxxglForm model) throws Exception {
		String searchTj = "";
		List<String> inputV = new ArrayList<String>();
		String sql = "select * from (select a.*,rownum r from xg_gygl_new_rzyydmb a order by a.rzyydm) ";
		String[] output = new String[] { "rzyydm", "rzyydm", "rzyymc" };
		return CommonQueryDAO.commonQuery(sql, searchTj, inputV
				.toArray(new String[] {}), output, model);
	}

	/**
	 * 
	 * @����:TODO��סԭ�����ά��
	 * @����xucy[���ţ�754]
	 * @���ڣ�2013-8-29 ����10:29:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	public String saveRzyy(ZsxxglForm model, String type) throws Exception {

		String msg = "��������";
		boolean b = false;
		String sql;
		if ("add".equals(type)) {
			sql = "select count(1) num from xg_gygl_new_rzyydmb where rzyydm=?";
			String num = dao.getOneRs(sql, new String[] { model.getRzyydm() },
					"num");
			if ("0".equals(num)) {
				sql = "insert into xg_gygl_new_rzyydmb(rzyydm,rzyymc) values(?,?)";
				b = dao.runUpdate(sql, new String[] { model.getRzyydm(),
						model.getRzyymc() });
				msg = (b ? "�����ɹ���" : "����ʧ�ܣ�");
			} else {
				msg = "��סԭ������Ѵ��ڣ�";
			}
		} else if ("update".equals(type)) {
			sql = "update xg_gygl_new_rzyydmb  set rzyymc=? where rzyydm=? ";
			b = dao.runUpdate(sql, new String[] { model.getRzyymc(),
					model.getRzyydm() });
			msg = (b ? "�����ɹ���" : "����ʧ�ܣ�");
		} else if ("delete".equals(type)) {

			StringBuilder check = new StringBuilder();
			check
					.append(" select rzyydm from xg_gygl_new_cwxxb where rzyydm=? ");
			String gyjlcfdm = dao.getOneRs(check.toString(),
					new String[] { model.getRzyydm() }, "rzyydm");

			if (Base.isNull(gyjlcfdm)) {
				sql = "delete from  xg_gygl_new_rzyydmb  where rzyydm=? ";
				b = dao.runUpdate(sql, new String[] { model.getRzyydm() });
				msg = (b ? "�����ɹ���" : "����ʧ�ܣ�");
			} else {
				msg = "��סԭ������Ѿ�ʹ�ã�����ɾ����";
			}
		}
		return msg;
	}
	//���ѧԺ����
	public String getXymc(String xydm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xymc from VIEW_NJXYZYBJ where xydm = ? ");
		String[] inputValue = { xydm };
		String[] outputValue = { "xymc" };
		String[] oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String mc = "";
		if (oneRs != null && oneRs.length > 0) {
			mc = oneRs[0];
		}
		return mc;
	}
	//���רҵ����
	public String getZymc(String zydm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select zymc from VIEW_NJXYZYBJ where zydm = ? ");
		String[] inputValue = { zydm };
		String[] outputValue = { "zymc" };
		String[] oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String mc = "";
		if (oneRs != null && oneRs.length > 0) {
			mc = oneRs[0];
		}
		return mc;
	}
	
	
	/**
	 * 
	 * @����: ѧ��Υ����ϸ��Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-2-25 ����10:16:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXswjxx(ZsxxglForm model, User user)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,(select xqmc from XQDZB where xqdm = a.xq) xqmc,b.xm,b.xb,b.bjdm,b.bjmc,c.fjmc ssfjmc,c.fj ssfj,c.sqly, ");
	    sql.append(" (case when jcwh is not null then jcsj when zzsj is not null then zzsj when sssj is not null then sssj else cfsj end) fwsj, ");
	    sql.append(" (case when jcwh is not null then jcwh when zzwh is not null then zzwh when sswh is not null then sswh else cfwh end) fwwh, ");
	    sql.append(" (case when jcwh is not null then '�������' when zzwh is not null then '��ֹ����' when a.ssjg is not null then a.ssjg else '���ֳ���' end) fwjg ");
	    sql.append(" from xg_wjcf_wjcfb a left join view_xsxxb b on a.xh = b.xh left join xg_wjcf_wjcfssb c on a.cfid = c.cfid ");
	    sql.append(" where 1 = 1 ");
	    sql.append(" and a.xh = ? ");
		ZsxxglDaoForPage dao = new ZsxxglDaoForPage();
		return dao.getPageListOld(model, sql.toString(), new String[] {model.getXh()});
	}
	
	/**
	 * 
	 * @����:��ȡ����Ա��������ũ�ѵ���������������ר�á�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-6 ����11:58:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qsh
	 * @param searchTj
	 * @param inputV
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getFdy(String qsh,String searchTj,String[] inputV){
		StringBuilder sql = new StringBuilder();
		List<String> paralist = new ArrayList<String>();
		sql.append("  select replace(wm_concat(xm), ';', '��') fdy");
		sql.append(" from fdyxxb");
		sql.append(" where zgh in");
		sql.append(" (select distinct zgh");
		sql.append(" from fdybjb");
		sql.append(" where bjdm in");//and lddm = ?
		sql.append(" (select bjdm from(");
		sql.append(" select rownum r,");
		sql.append("  a.*,");
		sql.append(" (select xymc from zxbz_xxbmdm where bmdm = a.xydm) cwxymc,");
		sql.append(" (select bjmc from bks_bjdm where bjdm = a.bjdm) cwbjmc");
		sql.append(" from (select lddm || qsh || cwh pkValue,");
		sql.append(" a.*,");
		sql.append(" b.xm,");
		sql.append(" b.zymc xszymc,");
		sql.append(" b.zydm xszydm,");
		sql.append(" b.bjmc xsbjmc,");
		sql.append(" b.bjdm xsbjdm,");
		sql.append(" b.xz,");
		sql.append(" b.pycc,");
		sql.append(" b.sjhm,");
		sql.append(" (case");
		sql.append(" when a.xydm is not null or a.nj is not null or");
		sql.append(" a.xh is not null then");
		sql.append(" 'disabled'");
		sql.append("  else");
		sql.append(" ''");
		sql.append(" end) dis,");
		sql.append(" (case");
		sql.append(" when length(a.bz) > 5 then");
		sql.append(" substr(a.bz, 1, 5) || '...'");
		sql.append(" else");
		sql.append(" a.bz");
		sql.append(" end) subbz");
		sql.append(" from view_xg_gygl_new_cwxx a");
		sql.append(" left join view_xsbfxx b");
		sql.append(" on a.xh = b.xh");
		sql.append(" order by a.lddm, to_number(a.ch), a.qsh, to_number(a.cwh)) a");
		sql.append(" where 1 = 1 ");
		sql.append(searchTj);
		sql.append(" ");
		sql.append(" ) where qsh = ? ))");
		for(int i=0;i<inputV.length;i++){
			paralist.add(inputV[i]);
		}
		paralist.add(qsh);
//		sql.append("  (select bjdm from view_xg_gygl_new_cwxx where qsh = ? ))");
		return dao.getOneRs(sql.toString(),paralist.toArray(new String[]{}), "fdy");
	}
	/**
	 * @throws Exception  
	 * @����:�������洲λ��Ϣ��ע���޸�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��29�� ����2:29:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkValueArr
	 * @param bz
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveBzBatchForUpdate(String[] pkValueArr, String bz) throws Exception {
		StringBuilder sql = new StringBuilder("UPDATE xg_gygl_new_cwxxb SET BZ = ? WHERE (LDDM||QSH||CWH) IN (");
		List<String> paraList = new ArrayList<String>();
		paraList.add(bz);
		for(int i=0;i<pkValueArr.length;i++){
			paraList.add(pkValueArr[i]);
			sql.append("?");
			if(i!=pkValueArr.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		boolean result = dao.runUpdate(sql.toString(),paraList.toArray(new String[]{}));
		return result;
	}
	
	/** 
	 * @����:����ѧ����մ�λ��Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-23 ����02:12:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean deleteCwxxByXh(String xh) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("update xg_gygl_new_cwxxb set xh = null where xh = ?");
		return dao.runUpdate(sb.toString(), new String[]{xh});
	}
	
	/** 
	 * @����:���ݴ�λ������Ϣ��մ�λ��Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-23 ����02:31:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @param qsh
	 * @param cwh
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateCwxxByDetils(String lddm,String qsh,String cwh,String xh) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("update xg_gygl_new_cwxxb set xh = ? where lddm = ? and qsh = ? and cwh = ?");
		return dao.runUpdate(sb.toString(), new String[]{xh,lddm,qsh,cwh});
	}
	
	/**
	 * 
	 * @����: ���ְཻͨҵ����ѧԺ�Զ��嵼��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-1 ����11:47:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> queryExportCwForGsjt(ZsxxglForm myForm,
			HttpServletRequest request) throws Exception {
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// Ȩ�޿���
		String searchTjQx = "";

		SearchService searchService = new SearchService();
		String searchTjByUser = "";
		if ("xydm".equalsIgnoreCase(GyglNewInit.CWFPDX)) {
			searchTjByUser = searchService.getSearchTjByUser(request, "a",
					"xydm", "xsbjdm"); // ѧԺ�û�
			searchTj = searchTj.replaceAll("bjdm", "xsbjdm");
			searchTj = searchTj.replaceAll("zydm", "xszydm");
		}
		if ("bjdm".equalsIgnoreCase(GyglNewInit.CWFPDX)) {
			searchTjByUser = searchService.getSearchTjByUser(request, "a",
					"xydm", "bjdm");
		}
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // ��Ԣ����Ա

		if (searchTjByGyfdy != null && !"".equalsIgnoreCase(searchTjByGyfdy)) {// �û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		} else {// �û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = searchTjByUser;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select replace(wm_concat(distinct bjmc),';',',') bjmc,replace(wm_concat(distinct xm),';',',') xm,ldmc,qsh from (");
		sql.append(" select  a.*,(select xymc from zxbz_xxbmdm where bmdm = a.xydm) cwxymc,");
		sql.append(" (select bjmc from bks_bjdm where bjdm = a.bjdm) cwbjmc");
		sql.append(" from (select lddm || qsh || cwh pkValue,");
		sql.append(" a.*,");
		sql.append(" b.xm,");
		sql.append(" b.zymc xszymc,");
		sql.append(" b.zydm xszydm,");
		sql.append(" b.sjhm lxfs,");
		sql.append(" b.bjmc xsbjmc,");
		sql.append(" b.bjdm xsbjdm,");
		sql.append(" e.bzr,");
		sql.append(" f.fdy,");
		sql.append(" (case");
		sql.append(" when a.xydm is not null or a.nj is not null or");
		sql.append(" a.xh is not null then");
		sql.append(" 'disabled'");
		sql.append(" else");
		sql.append(" ''");
		sql.append(" end) dis,");
		sql.append(" b.xz,");
		sql.append(" b.pycc,");
		sql.append(" g.tzjl");
		sql.append(" from view_xg_gygl_new_cwxx a");
		sql.append(" left join view_xsbfxx b");
		sql.append(" on a.xh = b.xh");
		sql.append(" left join (select bjdm, wm_concat(bzr) bzr");
		sql.append(" from (select a.bjdm, b.xm || b.lxdh bzr");
		sql.append(" from bzrbbb a");
		sql.append(" left join fdyxxb b");
		sql.append(" on a.zgh = b.zgh)");
		sql.append(" group by bjdm) e");
		sql.append(" on b.bjdm = e.bjdm");
		sql.append(" left join (select wm_concat(fdy) fdy, bjdm");
		sql.append(" from (select b.xm || b.lxdh fdy, a.bjdm");
		sql.append(" from fdybjb a");
		sql.append(" left join fdyxxb b");
		sql.append(" on a.zgh = b.zgh)");
		sql.append(" group by bjdm) f");
		sql.append(" on b.bjdm = f.bjdm");
		sql.append(" left join (select *");
		sql.append(" from (select t.xh,");
		sql.append(" wm_concat(t.ydjl) over(partition by xh order by ydjl) tzjl,");
		sql.append(" row_number() over(partition by xh order by ydjl desc) r");
		sql.append(" from (select t.xh,");
		sql.append(" case when t.ssydlx = '01' then");
		sql.append(" tstzsj || ' ' || ssydlxmc || ' ' || tsqs ||  ' -> ' || rzqs");
		sql.append(" else tstzsj || ' ' || ssydlxmc || ' ' || tsqs || rzqs  end ydjl");
		sql.append(" from (select * from (select t.xh,t.ssydlx,t.tstzsj,t.ydqldmc || '_' || t.ydqqsh || '_' || t.ydqcwh tsqs,");
		sql.append(" case when t.ydhldmc is not null and  t.ydhqsh is not null and  t.ydhcwh is not null then");
		sql.append(" t.ydhldmc || '_' || t.ydhqsh || '_' ||t.ydhcwh else '' end as rzqs,");
		sql.append(" case  when t.ssydlx = '00' then '����'");
		sql.append(" when t.ssydlx = '01' then '�������'");
		sql.append(" when t.ssydlx = '03' then '��ס' end ssydlxmc from XG_GYGL_NEW_SSYD_SSYDJG t)) t) t) where r = 1) g");
		sql.append(" on a.xh = g.xh");
		sql.append(" order by a.lddm, to_number(a.ch), a.qsh, to_number(a.cwh)) a");
		sql.append(" where 1 = 1");
		sql.append(" "+searchTj + searchTjQx);
		sql.append(" ");
		sql.append("  )  group by ldmc,qsh");
		return DAO.getInstance().getListNotOut(sql.toString(), inputV);
	}
}
