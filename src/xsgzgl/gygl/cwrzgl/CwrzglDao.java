package xsgzgl.gygl.cwrzgl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xsgzgl.gygl.comm.GyglNewDAO;
import xsgzgl.gygl.comm.GyglNewService;
import xsgzgl.gygl.tsgl.TsglForm;

public class CwrzglDao extends GyglNewDAO {

	/**
	 * ��ȡ��λ����ͳ������
	 * 
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCwrzglInfoList(CwrzglForm myForm, HttpServletRequest request) throws Exception {

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// Ȩ�޿���
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");

		// ѧԺ���꼶ѧԺ����ͳ�����ݼ�
		/*
		 * String sql_rs = "( select nj,xydm,xb,count(*) num " +
		 * "from view_xsjbxx " + "group by nj,xydm,xb )";
		 * 
		 * String sql_cws = "( select nj,xydm,qsxb,count(*) num, " +
		 * "sum(case when xh is not null then 1 end) yycws, " +
		 * "sum(case when xh is null then 1 end) sycws " +
		 * "from (select a.*,b.qsxb from xg_gygl_new_cwxxb a " +
		 * "left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh)" +
		 * "group by nj,xydm,qsxb )";
		 * 
		 * String sql =
		 * "select rownum r,a.* from (select a.*,a.num-a.yycws wrzrs," +
		 * "(select distinct b.xymc from view_njxyzybj_all b where a.xydm = b.xydm) xymc "
		 * + "from (select * from (select a.xydm||'_'||a.nj||'_'||a.xb pk,a.*,"
		 * + "case when b.num >0 then b.num else 0 end cws, " +
		 * "case when b.yycws >0 then b.yycws else 0 end yycws, " +
		 * "case when b.sycws >0 then b.sycws else 0 end sycws from "+ sql_rs
		 * +" a left join "+sql_cws+
		 * " b on a.xydm = b.xydm and a.nj =b.nj and a.xb =b.qsxb ) " +
		 * "order by nj desc,xydm,xb) a )a where 1=1 ";
		 */

		// --��λ���䰴ѧԺͳ���޸�--zhanghui--20130201--start--
		String sql_rs = "(select a.*,nvl(b.num,'0') rzrs,nvl(a.num- nvl(b.num, '0'),'0') wrzrs from " + "(select nj,xydm,xb,count(*) num from view_xsjbxx a group by nj,xydm,xb) a " + "left join "
				+ "(select nj,xydm,xb,count(*) num from ( select a.* from view_xsjbxx a,xg_gygl_new_cwxxb b where a.xh = b.xh) group by nj,xydm,xb) b "
				+ "on a.nj= b.nj and a.xydm = b.xydm and a.xb = b.xb)";

		String sql_cws = "( select nj,xydm,qsxb,count(*) num, " + "sum(case when xh is not null then 1 end) yycws " + "from (select a.*,b.qsxb from xg_gygl_new_cwxxb a "
				+ "left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh)" + "group by nj,xydm,qsxb )";

		String sql = "select rownum r, a.* from (select a.*,(select distinct b.xymc from view_njxyzybj_all b where a.xydm = b.xydm) xymc "
				+ "from (select * from (select a.xydm||'_'||a.nj||'_'||a.xb pk,a.*," + "nvl(b.num,'0') cws, nvl(b.yycws,'0') yycws, nvl(b.num-nvl(b.yycws,0),'0') sycws from " + sql_rs
				+ " a left join " + sql_cws + " b on a.xydm = b.xydm and a.nj =b.nj and a.xb =b.qsxb ) order by nj desc,xydm,xb) a ) a where 1=1 ";
		// --��λ���䰴ѧԺͳ���޸�--zhanghui--20130201---end---

		String[] colList = new String[] { "pk", "nj", "xymc", "xb", "num", "rzrs", "wrzrs", "cws", "yycws", "sycws" };

		return commonQuery(sql, searchTj + searchTjByUser, inputV, colList, myForm);
	}

	/**
	 * ͳ��¥��������Ϣ
	 * 
	 * @param request
	 * @param lddm
	 */
	public void tjldFpxx(HttpServletRequest request, CwrzglForm myForm) {
		String lddm = myForm.getLddm();
		String xb = myForm.getXb();
		String cwzt = myForm.getCwzt();
		DAO dao = DAO.getInstance();
		String sql = "select * from xg_gygl_new_ldxxb where lddm=?";// --��ѯ¥��������Ϣ
		HashMap<String, String> ldjbxx = dao.getMapNotOut(sql, new String[] { lddm });

		sql = "select count(1) qss,count(case when xydm is not null or nj is not null then 1 end) yfp_qss, " + "count(case when xydm is not null or nj is not null then null else 1 end) wfp_qss "
				+ "from xg_gygl_new_qsxxb where lddm=?";// --¥������ͳ����Ϣ
		HashMap<String, String> ldqstjxx = dao.getMapNotOut(sql, new String[] { lddm });
		ldjbxx.putAll(ldqstjxx);

		sql = "select count(1) cws,count(case when xydm is not null or nj is not null then 1 end) yfp_cws, " + "count(case when xydm is not null or nj is not null then null else 1 end) wfp_cws "
				+ "from xg_gygl_new_cwxxb where lddm=?";// --¥����λͳ����Ϣ
		HashMap<String, String> ldcwtjxx = dao.getMapNotOut(sql, new String[] { lddm });
		ldjbxx.putAll(ldcwtjxx);

		request.setAttribute("ldjbxx", ldjbxx);

		sql = "select a.ch,a.chmc,a.cws,a.yfp_cws,a.wfp_cws,a.bxy_cws,b.qss,b.yfp_qss,b.wfp_qss from " + "( " + "select ch,chmc,count(1) cws, "
				+ "count(case when xh is not null then 1 end) yfp_cws, " + "count(case when xh is null then 1 end) wfp_cws, " + "count(case when xydm=? and nj=? and qsxb=? then 1 end) bxy_cws "
				+ "from view_xg_gygl_new_cwxx where lddm=? and qsxb=? and nj=? and xydm=? " + "group by ch,chmc order by to_number(ch) "
				+ // --¥�㴲λͳ����Ϣ
				") a, " + "( " + "select ch,count(1) qss, " + "count(case when xydm is not null or nj is not null then 1 end) yfp_qss, "
				+ "count(case when xydm is not null or nj is not null then null else 1 end) wfp_qss " + "from xg_gygl_new_qsxxb where lddm=? and nj=? and xydm=? group by ch order by to_number(ch) " + // --¥��ͳ����Ϣ
				") b where a.ch=b.ch(+) order by to_number(ch) ";
		List<HashMap<String, String>> ldlcxx = dao.getListNotOut(sql, new String[] { myForm.getXydm(), myForm.getNj(), xb, lddm, xb, myForm.getNj(), myForm.getXydm(), lddm, myForm.getNj(),
				myForm.getXydm() });
		request.setAttribute("ldlcxx", ldlcxx);

		String cwzt_sql = "";
		if ("δ��ס".equals(cwzt)) {
			cwzt_sql = " and a.xh is null ";
		} else if ("����ס".equals(cwzt)) {
			cwzt_sql = " and a.xh is not null ";
		}
		//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = " order by to_number(ch),qsh,cwh ";
		}else{
			sb = " order by to_number(ch),qsh,to_number(cwh) ";
		}
		if("13560".equalsIgnoreCase(Base.xxdm)){
			sql = "select a.*,b.xm,b.bjmc,b.mz,c.zd4 yzlbdm,d.yzlbmc,(select c.qxmc from dmk_qx c where c.qxdm = substr(b.syd, 0, 2) || '0000') || (select d.qxmc from dmk_qx d where d.qxdm = substr(b.syd, 0, 4) || '00' and b.syd <> substr(b.syd, 0, 2) || '0000') sydqmc from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh left join xsxxb c on a.xh = c.xh left join dmk_yzlb_hbxy d on c.zd4 = d.yzlbdm " + "where lddm=? and a.qsxb=? and a.xydm=? and a.nj=? " + cwzt_sql
			+ " "+sb+" ";// --¥��¥�����Ҵ�λ��Ϣ 
		}else{			
			sql = "select a.*,b.xm,b.bjmc,b.mz,(select c.qxmc from dmk_qx c where c.qxdm = substr(b.syd, 0, 2) || '0000') || (select d.qxmc from dmk_qx d where d.qxdm = substr(b.syd, 0, 4) || '00' and b.syd <> substr(b.syd, 0, 2) || '0000') sydqmc from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh " + "where lddm=? and a.qsxb=? and a.xydm=? and a.nj=? " + cwzt_sql
			+ " "+sb+" ";// --¥��¥�����Ҵ�λ��Ϣ
		}
		List<HashMap<String, String>> ldlcqscwxxb = dao.getListNotOut(sql, new String[] { lddm, xb, myForm.getXydm(), myForm.getNj() });
		request.setAttribute("ldlcqscwxxb", ldlcqscwxxb);
		
		String[] colList = null;
		if("13560".equalsIgnoreCase(Base.xxdm)){
			 colList = new String[] { "nj", "xydm", "zydm", "bjdm", "xb", "rzqk", "yzlbdm" };			
		}else{
			// ��ȡѧ����Ϣ
			 colList = new String[] { "nj", "xydm", "zydm", "bjdm", "xb", "rzqk" };		
		}
		

		String[] colLikeList = new String[] {};

		MakeQuery make = new MakeQuery();
		try {
			make.makeQuery(colList, colLikeList, myForm);
			String query = make.getQueryString();
			String[] inputs = make.getInputList();
			String tableName;
			//���칤�̴�ѧ����ת�߶���ѧ��
			if(Base.xxdm.equals("11799")){
				tableName = "(select a.*,(case when b.xh is null then 'δ��ס' else '����ס' end) rzqk,b.ldmc,b.qsh,b.cwh " + "from " + xsxxb_zxs + " a left join view_xg_gygl_new_cwxx b on a.xh=b.xh where not exists (select 1 from xg_gygl_new_bzxbzb d where a.xh = d.xh))";
			}else{
				tableName = "(select a.*,(case when b.xh is null then 'δ��ס' else '����ס' end) rzqk,b.ldmc,b.qsh,b.cwh " + "from " + (Base.xxdm.equals("13560")?xsxxb_zxs_13560:xsxxb_zxs) + " a left join view_xg_gygl_new_cwxx b on a.xh=b.xh)";
			}
			if (!Base.isNull(myForm.getXhxm())) {
				String xhxm = myForm.getXhxm().trim();
				if (xhxm.length() < 20) {// ������С��20��Ӧ�þ���һ��ѧ��
					query += " and (xh like '%" + xhxm + "%' or xm like '%" + xhxm + "%')";
				} else {// ������ѯ
					String splitmark = ",";
					if (xhxm.trim().indexOf("\r\n") > -1) {
						splitmark = "\r\n";
					} else if (xhxm.trim().indexOf("\r") > -1) {
						splitmark = "\r";
					} else if (xhxm.trim().indexOf("\n") > -1) {
						splitmark = "\n";
					} else if (xhxm.indexOf(",") > -1) {
						splitmark = ",";
					} else if (xhxm.indexOf(";") > -1) {
						splitmark = ";";
					} else if (xhxm.trim().indexOf(" ") > -1) {
						splitmark = " ";
					}

					String[] xhs = xhxm.split(splitmark);
					StringBuffer xh = new StringBuffer();
					for (int i = 0; i < xhs.length; i++) {
						xh.append("'");
						xh.append(xhs[i].trim());
						xh.append("'");
						if (i < xhs.length - 1) {
							xh.append(",");
						}
					}
					query += " and xh in(" + xh.toString() + ")";
				}
			}
			if("13871".equals(Base.xxdm)){
				query+=" and xh not in(select xh from XG_GYGL_XYZSGL where to_date(sqsjstart,'yyyyMMdd')<sysdate and to_date(sqsjend,'yyyyMMdd')+1>sysdate) ";
			}
			if(!"12673".equals(Base.xxdm)){
			query += " order by rzqk,zydm,bjdm,xh";
			}
			else
			{
				query += "order by dbms_random.value()";
			}
			List<String[]> xsxxList = commonQueryNotFy(tableName, query, inputs, Base.xxdm.equals("13560")?new String[] { "xh", "xm", "rzqk", "ldmc", "qsh", "cwh", "bjmc", "rs", "sydqmc", "mzmc", "yzlbmc", "yzlbdm"}:new String[] { "xh", "xm", "rzqk", "ldmc", "qsh", "cwh", "bjmc", "rs", "sydqmc", "mzmc" }, "");
			HashMap<String, List<String[]>> xsxxMap = new HashMap<String, List<String[]>>();
			HashMap<String, List<String[]>> pxMap = new HashMap<String, List<String[]>>();
			List<String[]> list = null;
			for (String[] xsxx : xsxxList) {
				if (xsxxMap.containsKey(xsxx[8])) {
					xsxxMap.get(xsxx[8]).add(xsxx);
				} else {
					list = new ArrayList<String[]>();
					list.add(xsxx);
					xsxxMap.put(xsxx[8], list);
				}
			}
			List<String[]> newList = new ArrayList<String[]>();
			int cou = 0;
			for (Map.Entry<String, List<String[]>> entry : xsxxMap.entrySet()) {
				pxMap.put(cou + "", entry.getValue());
				cou++;
			}
			xsxxDg(pxMap, newList);
			if ("10264".equals(Base.xxdm)) {
				request.setAttribute("xsxxlist", newList);
			} else {
				request.setAttribute("xsxxlist", xsxxList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @����:�ݹ�ѧ����Ϣ��������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-19 ����02:07:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	private void xsxxDg(HashMap<String, List<String[]>> pxMap, List<String[]> newList) {
		HashMap<String, List<String[]>> fzMap = new HashMap<String, List<String[]>>();
		int cou = 0;
		for (Map.Entry<String, List<String[]>> entry : pxMap.entrySet()) {
			fzMap.put(cou + "", entry.getValue());
			cou++;
		}
		int len = 0;
		List<String> key = new ArrayList<String>();
		for (Map.Entry<String, List<String[]>> entry : fzMap.entrySet()) {
			len += entry.getValue().size();
		}

		int j;

		for (int i = 1; i < len + 1; i++) {
			j = 0;

			for (j = 0; j < fzMap.size(); j++) {

				if (i % fzMap.size() == j) {
					if (fzMap.get(j + "").size() > 0) {
						newList.add(fzMap.get(j + "").get(0));
						fzMap.get(j + "").remove(0);
					}
				}
			}
		}
		for (Map.Entry<String, List<String[]>> entry : fzMap.entrySet()) {
			if (entry.getValue().size() == 0) {
				key.add(entry.getKey());
			}
		}
		pxMap = fzMap;
		for (String string : key) {
			pxMap.remove(string);
		}
		if (pxMap.size() > 0) {
			xsxxDg(pxMap, newList);
		}

	}

	/**
	 * ��ȡ¥���б�
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLdList(CwrzglForm myForm) {
		DAO dao = DAO.getInstance();
		String sql = "select lddm,ldmc||(case when ldxb='��ס' then '('||ldxb||')' else '' end) ldmc " + "from xg_gygl_new_ldxxb a where 1=1 and "
				+ " exists (select 1 from view_xg_gygl_new_cwxx x where x.qsxb=? and x.nj=? and x.xydm=? and x.lddm=a.lddm )";
		return dao.getListNotOut(sql, new String[] { myForm.getXb(), myForm.getNj(), myForm.getXydm() });
	}

	/**
	 * ѧ����ס������Ϣ
	 * 
	 * @param request
	 * @param lddm
	 * @return
	 */
	public synchronized boolean saveXsrzfpxx(HttpServletRequest request, CwrzglForm myForm) {
		String lddm = myForm.getLddm();
		String nj = myForm.getNj();
		String xydm = myForm.getXydm();
		HttpSession session = request.getSession();
		String rzczr = session.getAttribute("userName").toString();// ���ò�����
		String rzsj = myForm.getRzsj();// ��סʱ��
		String rzyy = myForm.getRzyy();// ��סԭ��
		boolean b = false;
		ArrayList<String> sqls = new ArrayList<String>();
		CommDAO commdao = new CommDAO();
		String[] xhs = request.getParameterValues("div_xs_xh");// ѧ��
		String[] cwhs = request.getParameterValues("checkbox_cwh");// ��λ��
		//��������ְҵ����ѧԺ����������ѧ����ס��ʱ�򣬲���XG_GYGL_NEW_SSYD_SSYDJG���춯����Ϊ��ס��
		if("12688".equals(Base.xxdm)){
			try {
			ArrayList<String> sql_12688 = new ArrayList<String>();
			String  czsj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			if (cwhs != null && cwhs.length > 0 && xhs != null && xhs.length > 0) {
				for (int i = 0; i < cwhs.length && i < xhs.length; i++) {
					String[] cwh = cwhs[i].split("_");
					/*String sql = "select a.lddm ydqlddm,b.ldmc ydqldmc,a.qsh ydqqsh,a.cwh ydqcwh from xg_gygl_new_cwxxb a " +
							" left join XG_GYGL_NEW_LDXXB b on a.lddm=b.lddm where xh= "+xhs[i];
					HashMap< String, String> ydqmap = dao.getRSArray(sql, new String[]{"ydqlddm","ydqldmc","ydqqsh","ydqcwh"});*/
					
					sql_12688.add("insert into XG_GYGL_NEW_SSYD_SSYDJG (xh,czsj,xn,xq,ssydlx,tstzsj,tstzyy," 
							+ " YDHLDDM,YDHLDMC,YDHQSH,YDHCWH,YDQQSRZSJ) " 
							+ " values('" + xhs[i] + "','" + czsj + "',"
							+ "'" + myForm.getXn() + "','" + myForm.getXq() + "','" +"03" + "','" + rzsj + "','"
							+ rzyy + "','" + lddm + "'," + "(select ldmc from XG_GYGL_NEW_LDXXB where lddm='" + lddm + "')" + ",'" 
							+ cwh[0] + "','" + cwh[1] + "','" + rzsj +"')");
				}
			}
			
				b = commdao.saveArrDate(sql_12688.toArray(new String[] {}));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		// ����ѧ����ס��Ϣ
		if (cwhs != null && cwhs.length > 0 && xhs != null && xhs.length > 0) {
			for (int i = 0; i < cwhs.length && i < xhs.length; i++) {
				sqls.add("update xg_gygl_new_cwxxb set xh='" + xhs[i] + "'," + "bjdm=(select bjdm from view_xsjbxx where xh='" + xhs[i] + "')," + "zydm=(select zydm from view_xsjbxx where xh='"
						+ xhs[i] + "')," + "xydm=(select xydm from view_xsjbxx where xh='" + xhs[i] + "')," + "rzyydm='" + rzyy + "'," + "rzsj='" + rzsj + "'," + "rzczr='" + rzczr + "' "
						+ " where lddm='" + lddm + "' " + " and qsh='" + cwhs[i].split("_")[0] + "'" + " and cwh='" + cwhs[i].split("_")[1] + "' " + " and not exists ("
						+ "  select 1 from xg_gygl_new_cwxxb " + "  where xh='" + xhs[i] + "') " + "    and ((nj='" + nj + "' " + "    and xydm='" + xydm + "') or xydm is null)");
			}
		}

		
		try {
			b = commdao.saveArrDate(sqls.toArray(new String[] {}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * ȡ��ѧ����סϢ
	 * 
	 * @param request
	 * @param lddm
	 * @return
	 */
	public boolean qxXsrzfpxx(HttpServletRequest request, CwrzglForm myForm) {
		// String lddm=myForm.getLddm();
		HttpSession session = request.getSession();
		String tsczr = session.getAttribute("userName").toString();// ���ò�����
		String tssj = myForm.getRzsj();
		String tsyy = myForm.getTsyy();
		String bz = myForm.getBz();

		TsglForm tsglForm = new TsglForm();

		// �����춯���ͣ�00[����]
		tsglForm.setSsydlx("00");

		// ׷��ѧ��ѧ��
		tsglForm.setXn(myForm.getXn());
		tsglForm.setXq(myForm.getXq());
		tsglForm.setSfcwcsh("��");// ��λ����ʼ��
		tsglForm.setTsczr(tsczr);
		tsglForm.setTssj(tssj);
		tsglForm.setTsyy(tsyy);
		tsglForm.setBz(bz);

		boolean b = false;
		Set<String> sqls = new HashSet<String>();

		String[] xhs = request.getParameterValues("checkbox_xh");// ���Һ�
		String[] cwhs = request.getParameterValues("checkbox_cwh");// ��λ��

		// ȡ��ѧ����ס��Ϣ ����ѧ��
		if (xhs != null && xhs.length > 0) {
			for (int i = 0; i < xhs.length; i++) {
				sqls.add("update xg_gygl_new_cwxxb set xh='',rzsj='',rzczr='' where xh='" + xhs[i] + "'");
				tsglForm.setXh(xhs[i]);
				sqls.add(getTsxxNewSql(tsglForm));
			}
		}

		String lddm = myForm.getLddm();
		tsglForm.setXh(null);
		// ȡ��ѧ����ס��Ϣ �������Ҵ�λ
		if (cwhs != null && cwhs.length > 0) {
			for (int i = 0; i < cwhs.length; i++) {
				sqls.add("update xg_gygl_new_cwxxb set xh='',rzsj='',rzczr='' where lddm='" + lddm + "' and qsh='" + cwhs[i].split("_")[0] + "' and cwh='" + cwhs[i].split("_")[1] + "'");
				tsglForm.setLddm(lddm + cwhs[i].replace("_", ""));
				sqls.add(getTsxxNewSql(tsglForm));
			}
		}
		// �ô���Ҫ�������ظ���������Ϣ�������
		CommDAO dao = new CommDAO();
		try {
			b = dao.saveArrDate(sqls.toArray(new String[] {}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * ��ȡָ��ѧԺ�꼶��ͳ������
	 * 
	 * @param inputV
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCwfpglInfo(CwrzglForm myForm) {

		// ѧԺ���꼶ѧԺ����ͳ�����ݼ�
		String sql_rs = "( select nj,xydm,xb,count(*) num " + "from view_xsjbxx where nj=? and xydm=? and xb=? " + "group by nj,xydm,xb )";

		String sql_cws = "( select nj,xydm,qsxb,count(*) num, " + "sum(case when xh is not null then 1 end) yycws, " + "sum(case when xh is null then 1 end) sycws "
				+ "from (select a.*,b.qsxb from xg_gygl_new_cwxxb a " + "left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh)" + "group by nj,xydm,qsxb )";

		String sql = "select a.*,a.num-a.yycws wrzrs,(select distinct b.xymc from view_njxyzybj_all b where a.xydm = b.xydm) xymc,"
				+ "rownum r from (select * from (select a.xydm||'_'||a.nj||'_'||a.xb pk,a.*," + "case when b.num >0 then b.num else 0 end cws, "
				+ "case when b.yycws >0 then b.yycws else 0 end yycws, " + "case when b.sycws >0 then b.sycws else 0 end sycws from " + sql_rs + " a left join " + sql_cws
				+ " b on a.xydm = b.xydm and a.nj =b.nj and a.xb =b.qsxb )  " + " ) a ";
		String[] colList = new String[] { "pk", "nj", "xymc", "xb", "num", "yycws", "wrzrs", "cws", "sycws" };

		DAO dao = DAO.getInstance();
		return dao.getMap(sql, new String[] { myForm.getNj(), myForm.getXydm(), myForm.getXb() }, colList);
	}

	/**
	 * ��ȡѧԺ�꼶�Ա��¥���������
	 * 
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String, String>> getXynjxbLdfpxx(CwrzglForm myForm) {
		String sql = "select a.*,b.xyqss,b.xycws,b.xywrzcws,b.xyyrzcws from " + "( " + "select lddm,ldmc, " + "count(1) cws, " + "count(case when xydm is not null then 1 end) yfpcws, "
				+ "count(case when xydm is null then 1 end) wfpcws, " + "count(distinct qsh) qss, " + "count(case when qsxydm is not null then 1 end) yfpqss, "
				+ "count(case when qsxydm is null then 1 end) wfpqss " + "from view_xg_gygl_new_cwxx group by lddm,ldmc " + ") a, " + "( "
				+ "select lddm,count(distinct qsh) xyqss,count(1) xycws,count(case when xh is null then 1 end) xywrzcws,"
				+ "count(case when xh is not null then 1 end) xyyrzcws from view_xg_gygl_new_cwxx " + "where nj=? and xydm=? and qsxb=? group by lddm " + ") b " + "where a.lddm=b.lddm";
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql, new String[] { myForm.getNj(), myForm.getXydm(), myForm.getXb() });
	}

	/**
	 * ��������
	 * 
	 * @param filePath
	 * @param request
	 * @return
	 */
	public String importData(HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		int excelXsCount = 0;// excel�ļ�ѧ���ļ�¼��
		boolean b = false;
		// ������ʱ��
		try {
			// ���Ƚ���ʱ���е��������
			b = dao.runUpdate("delete from xg_gygl_new_impcwxxb", new String[] {});
			if (!b) {
				return "��ʱ������ɾ��ʧ�ܣ������µ��룡";
			}

			String path = request.getAttribute("filepath").toString();
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();// ���Դexcel������
			String[] row;
			ArrayList<String> excelData_sql = new ArrayList<String>();// ���ڱ����excel��õ�����
			for (int rownum = 1; rownum < sourceRowCount; rownum++) {// ÿ����¼
				// ���Ҫ�����¼ start
				row = ExcelMethods.getOneRow(sourceSheet, rownum, 7);
				if(row[6] == null || "".equals(row[6])){ // Ĭ����סʱ��
					row[6] = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				}
				if (!"".equalsIgnoreCase(row[0].trim()) && null != row[0]
				    && !"".equalsIgnoreCase(row[1].trim()) && null != row[1]
				    && !"".equalsIgnoreCase(row[2].trim()) && null != row[2]
					&& !"".equalsIgnoreCase(row[3].trim()) && null != row[3]
					&& !"".equalsIgnoreCase(row[6].trim()) && null != row[6]) {
					excelData_sql.add("insert into xg_gygl_new_impcwxxb(lddm,qsh,cwh,xh,xm,bjmc,rzsj) " + "values( trim('" + row[0] + "'),trim('" + row[1] + "'),trim('" + row[2] + "'),trim('" + row[3]
							+ "'),trim('" + row[4] + "'),trim('" + row[5] + "'),trim('" + row[6] + "') )");
					// ���Ҫ�����¼ end
				}
			}
			CommDAO commdao = new CommDAO();
			excelXsCount = excelData_sql.size();
			if (excelXsCount > 0) {
				b = commdao.saveArrDate(excelData_sql.toArray(new String[] {}));
				if (!b) {
					return "�����ֶι������޷��������ݿ⣡";
				}
			} else {
				return "�ļ���û�����ݿɵ��룡";
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			return "���ݵ�����ʱ��ʱ�����쳣�������������ֶι������£�";

		}

		try {
			// Ԥ����ɾ���������봲λ��Ϣ����¥�����룬���Һţ���λ�ţ�ѧ����ȫһ�µ�����
			String sql = "delete from xg_gygl_new_impcwxxb a " + "where exists (select 1 from xg_gygl_new_cwxxb b where a.lddm=b.lddm and a.qsh=b.qsh and a.cwh=b.cwh and a.xh=b.xh) ";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "Ԥ����ɾ����������ʧ�ܣ�";
			}

			// ��һ������ǵ��������У�¥�����룬���Һţ���λ�ţ�ѧ��Ϊ�յ�����
			sql = "update xg_gygl_new_impcwxxb a set mark='0',bz='���������ֶ��п�ֵ' " + "where a.mark<>'0' and a.lddm is null or a.qsh is null or a.cwh is null or a.xh is null";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "���������ֶ��п�ֵ���ʧ�ܣ�";
			}
			// ��һ����2������֤ʱ���ʽ�Ĺ淶�ԣ���סʱ��ҪôΪ�գ�Ҫô��ʽ����Ϊyyyy-mm-dd�������֤��Ҫ��Ϊ����ʷ���ݵĲ�ѯͳ��
			sql = "update xg_gygl_new_impcwxxb set mark='0',bz='��סʱ���ʽ���淶' " + "where mark<>'0' and rzsj is not null and (length(rzsj)<>10 or substr(rzsj,5,1)<>'-' or substr(rzsj,8,1)<>'-')";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "��סʱ���ʽ���淶���ʧ�ܣ�";
			}
			// �ڶ�������Ƿǿ������У������ڵĴ�λ
			sql = "update xg_gygl_new_impcwxxb a set mark='0',bz='��λ������' " + "where a.mark<>'0' and "
					+ "not exists (select 1 from xg_gygl_new_cwxxb b where a.lddm=b.lddm and a.qsh=b.qsh and a.cwh=b.cwh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "��λ�����ڱ��ʧ�ܣ�";
			}

			// ����������Ǵ��ڵĴ�λ�����У������Ĵ�λ
			sql = "update xg_gygl_new_impcwxxb a set mark='0',bz='�ô�λΪ������λ' " + "where a.mark<>'0' and "
					+ "exists (select 1 from xg_gygl_new_cwxxb b where a.lddm=b.lddm and a.qsh=b.qsh and a.cwh=b.cwh and " + "b.sfbl='��')";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "��λΪ������λ���ʧ�ܣ�";
			}

			// ���Ĳ�����Ǵ����ҷǱ����Ĵ�λ�����У�����ѧ����ס�Ĵ�λ
			sql = "update xg_gygl_new_impcwxxb a set mark='0',bz='�ô�λ����סѧ��' " + "where a.mark<>'0' and "
					+ "exists (select 1 from xg_gygl_new_cwxxb b where a.lddm=b.lddm and a.qsh=b.qsh and a.cwh=b.cwh and " + "b.xh is not null)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "��λ����סѧ�����ʧ�ܣ�";
			}

			// ���岽����Ǵ����ҷǱ���δ��ס�Ĵ�λ�����У���λ�ظ�������
			sql = "update xg_gygl_new_impcwxxb a set mark='0',bz='��λ�ظ�' " + "where a.mark<>'0' and " + "exists (select 1 from "
					+ "(select lddm,qsh,cwh from xg_gygl_new_impcwxxb where mark<>'0' group by lddm,qsh,cwh having count(1)>1) b " + "where a.lddm=b.lddm and a.qsh=b.qsh and a.cwh=b.cwh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "��λ�ظ����ʧ�ܣ�";
			}

			// �����������ѧ�Ų����ڵ�����
			sql = "update xg_gygl_new_impcwxxb a set mark='0',bz='��ѧ��ѧ��������' " + "where a.mark<>'0' and not exists (select 1 from view_xsjbxx b where a.xh=b.xh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "ѧ��ѧ�������ڱ��ʧ�ܣ�";
			}
			
			// ==========��λ�Ƿ������û�����ѧԺbegin==========
			HttpSession session = request.getSession();
			String userType = session.getAttribute("userType").toString();
			String userDep = session.getAttribute("userDep").toString();
			String cwTj = "";
			String[] cwTjParams = new String[] {};
			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				// ѧУ�û�������Ա��
			}else{
				cwTj = " and b.xydm=? ";
				cwTjParams = new String[] {userDep};
				sql = "update xg_gygl_new_impcwxxb a set mark='0',bz='��λ�������û�����ѧԺ' where a.mark<>'0' and not exists (select 1 from xg_gygl_new_cwxxb b where a.lddm=b.lddm and a.qsh=b.qsh and a.cwh=b.cwh " + cwTj + " ) ";
				b = dao.runUpdate(sql,cwTjParams);
				if (!b) {
					return "��λ�������û�����ѧԺ���ʧ�ܣ�";
				}
			}
			// ==========��λ�Ƿ������û�����ѧԺ end==========
			// ==========ѧ���Ƿ������û���Ͻ begin==========
			String searchTjQx="";
			
			SearchService searchService=new SearchService();
			String searchTjByUser = searchService.getSearchTjByUser(request, "d","xydm", "bjdm"); 	//ѧԺ�û�
			GyglNewService gyglNewService = new GyglNewService();
			String searchTjByGyfdyNotIn = gyglNewService.getSearchTjByGyfdyNotIn(request);				//��Ԣ����Ա		
			
			if(searchTjByGyfdyNotIn !=null && !"".equalsIgnoreCase(searchTjByGyfdyNotIn)){//�û�Ϊ��Ԣ����Ա
				searchTjQx = searchTjByGyfdyNotIn;
			}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
				searchTjQx = " and not exists ( select 1 from view_xsjbxx d where a.xh=d.xh " + searchTjByUser + " ) ";
			}
			sql = "update xg_gygl_new_impcwxxb a set mark='0',bz='ѧ���������û���Ͻ' where a.mark<>'0' " + searchTjQx;
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "ѧ���������û���Ͻ���ʧ�ܣ�";
			}
			// ==========ѧ���Ƿ������û���Ͻ end==========
			
			// ���߲������ѧ�Ŵ���������ס������
			sql = "update xg_gygl_new_impcwxxb a set mark='0',bz='��ѧ������ס' " + "where a.mark<>'0' and " + "exists (select 1 from xg_gygl_new_cwxxb b where a.xh=b.xh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "����סѧ�����ʧ�ܣ�";
			}

			// �ڰ˲������ѧ�Ŵ�����δ��ס��ѧ����ѧ���ظ�������
			sql = "update xg_gygl_new_impcwxxb a set mark='0',bz='ѧ���ظ�' " + "where a.mark<>'0' and " + "exists (select 1 from "
					+ "(select xh from xg_gygl_new_impcwxxb where mark<>'0' group by xh having count(1)>1) b where a.xh=b.xh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "ѧ���ظ����ʧ�ܣ�";
			}

			// �ھŲ������ѧ���Ա��봲λ�Ա�һ�µ�����
			sql = "update xg_gygl_new_impcwxxb a set mark='0',bz='�ô�λ�������Ա���ѧ�����Ա�ͬ' " + "where a.mark<>'0' and " + "not exists (select 1 from ("
					+ "select c.lddm,c.qsh,c.cwh,c.xh from view_xsjbxx a ,view_xg_gygl_new_cwxx b,xg_gygl_new_impcwxxb c "
					+ "where a.xb=b.qsxb and a.xh=c.xh and b.lddm=c.lddm and b.qsh=c.qsh and b.cwh=c.cwh and c.mark<>'0'" + ") x where x.lddm=a.lddm and x.qsh=a.qsh and x.cwh=a.cwh and x.xh=a.xh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "��λ�������Ա���ѧ�����Ա�ͬ���ʧ�ܣ�";
			}

			// ###############
			// sql="update xg_gygl_new_impcwxxb a set mark='0',bz='�ô�λδ������������ڵ��꼶ѧԺ' "
			// +
			// "where a.mark<>'0' and " +
			// "not exists (select 1 from (" +
			// "select c.lddm,c.qsh,c.cwh,c.xh from view_xsjbxx a ,xg_gygl_new_cwxxb b,xg_gygl_new_impcwxxb c "+
			// "where (a.xydm=b.xydm and a.nj=b.nj and a.xh=c.xh and b.lddm=c.lddm and b.qsh=c.qsh and b.cwh=c.cwh and c.mark<>'0') "
			// +
			// "or (b.xydm is null or b.nj is null)" +
			// ") x where x.lddm=a.lddm and x.qsh=a.qsh and x.cwh=a.cwh and x.xh=a.xh)";
			// b=dao.runUpdate(sql, new String[]{});
			// if(!b){
			// return "��λδ�������Ӧѧ�����ڵ��꼶ѧԺ���ʧ�ܣ�";
			// }
			// ###############

			// ���ȸ���ѧ�ź���סʱ��
			String rzczr = session.getAttribute("userName").toString();// ���ò�����

			sql = "update xg_gygl_new_cwxxb a set " + "xh=(select xh from xg_gygl_new_impcwxxb x where mark<>'0' and x.lddm=a.lddm and x.qsh=a.qsh and x.cwh=a.cwh), "
					+ "rzsj=(select nvl(rzsj,to_char(sysdate,'yyyy-mm-dd')) from xg_gygl_new_impcwxxb x where mark<>'0' and x.lddm=a.lddm and x.qsh=a.qsh and x.cwh=a.cwh), " + "rzczr='" + rzczr
					+ "' " + "where xh is null and exists(select 1 from xg_gygl_new_impcwxxb x where mark<>'0' and x.lddm=a.lddm and x.qsh=a.qsh and x.cwh=a.cwh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "ѧ�Ÿ���ʧ�ܣ�";
			}
			// �����ѵ���ѧ�����������Ĵ�λ����
			// ����ʱ��ͼ���������Ż�
			//String view_xsjbxx = Base.xsxxb;
			// "( select to_char(nj) nj,bmdm xydm,xh from bks_xsjbxx aa " +
			// "where not exists(select 1 from xsxxb bb where aa.xh=bb.xh) union all select nj,xydm,xh from xsxxb)";
			sql = "update xg_gygl_new_cwxxb a set (nj,xydm,zydm,bjdm)=(select nj,xydm,zydm,bjdm from view_xsbfxx x where x.xh = a.xh) "
					//+ ("bjdm".equals(GyglNewInit.CWFPDX) ? ",bjdm=(select bjdm from " + view_xsjbxx + " x where x.xh=a.xh) " : "") 
					+ "where xh is not null " +
					// "and exists(select 1 from "+view_xsjbxx+" x where x.xh=a.xh) "
					// +
					"and exists (select 1 from xg_gygl_new_impcwxxb x where mark<>'0' and " + "x.lddm=a.lddm and x.qsh=a.qsh and x.cwh=a.cwh and x.xh=a.xh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "��λ�꼶" + Base.YXPZXY_KEY + "����ʧ�ܣ�";
			}
			// ������������
			sql = "update xg_gygl_new_qsxxb a set " + "nj=(select max(nj) from xg_gygl_new_cwxxb x " + "where x.lddm=a.lddm and x.qsh=a.qsh and x.nj is not null and xydm is not null), "
					+ "xydm=(select max(xydm) from xg_gygl_new_cwxxb x " + "where x.lddm=a.lddm and x.qsh=a.qsh and x.nj is not null and xydm is not null) " + "where nj is null and xydm is null "
					+ "and exists (select 1 from xg_gygl_new_impcwxxb x where mark<>'0' and x.lddm=a.lddm and x.qsh=a.qsh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "�����꼶" + Base.YXPZXY_KEY + "����ʧ�ܣ�";
			}

		} catch (Exception e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
			return "���ݸ��½׶η����쳣��";
		}

		List<String[]> xsList = new ArrayList<String[]>();
		try {
			String sql = "select * from xg_gygl_new_impcwxxb where mark='0'";
			String[] outputValue = new String[] { "lddm", "qsh", "cwh", "xh", "xm", "bjmc", "rzsj", "bz" };
			String[] colListCN = new String[] { "¥������", "���Һ�", "��λ��", "ѧ��", "����", "�༶", "��סʱ��", "����ʧ��ԭ��" };
			xsList = dao.rsToVator(sql, new String[] {}, outputValue);

			if (xsList != null && xsList.size() > 0) {
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				Excel2Oracle.exportData(xsList, outputValue, colListCN, response.getOutputStream());
				request.setAttribute("sfdcExcel", "yes");
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			response.reset();
			response.setContentType("text/html");
			return "���ݵ���ɹ����������Ϲ淶��" + ((xsList == null || xsList.size() == 0) ? "" : (xsList.size() + "��")) + "�����ڷ���ʱ�������쳣��";
		}

		return "����ɹ���";
	}

	/**
	 * �Ƿ��ȡ����ס
	 * 
	 * @param myForm
	 * @return
	 */
	public boolean isKqxrz(CwrzglForm myForm) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = formatter.format(currentTime);
		String sql = "select count(1) num from xg_gygl_new_qxrzkgkzb where xydm=? and nj=? and sfqy='��' " + "and nvl(kssj,'0000')<? and ?<nvl(jssj,'9999')";
		DAO dao = DAO.getInstance();
		String num = dao.getOneRs(sql, new String[] { myForm.getXydm(), myForm.getNj(), date, date }, "num");
		if ("0".equals(num)) {
			return false;
		} else {
			return true;
		}
	}

	// ##############################################################����Ϊ����Ա�����β���
	/**
	 * ��ȡ��λ����ͳ�����ݣ�ѧԺ��
	 * 
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCwrzglInfoList_xy(HttpServletRequest request, CwrzglForm myForm) throws Exception {
		String fdybzrbjview = getFdyBzrQxBjView(request);

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");

		// ѧԺ���꼶ѧԺ����ͳ�����ݼ�
		/*
		 * String sql_rs = "( select nj,xydm,zydm,bjdm,xb,count(*) num " +
		 * "from view_xsjbxx a " + "group by nj,xydm,zydm,bjdm,xb )";
		 * 
		 * String sql_cws = "( select nj,xydm,qsxb,bjdm,count(*) num, " +
		 * "sum(case when xh is not null then 1 end) yycws, " +
		 * "sum(case when xh is null then 1 end) sycws " +
		 * "from (select a.*,b.qsxb from xg_gygl_new_cwxxb a " +
		 * "left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh)" +
		 * "group by nj,xydm,bjdm,qsxb )";
		 * 
		 * String sql = "select a.*,a.num-a.yycws wrzrs," +
		 * "(select distinct b.xymc from view_njxyzybj_all b where a.xydm = b.xydm) xymc,"
		 * +
		 * "(select b.bjmc from view_njxyzybj_all b where a.bjdm = b.bjdm) bjmc,"
		 * +
		 * "rownum r from (select * from (select a.xydm||'_'||a.nj||'_'||a.bjdm||'_'||a.xb||'_'||a.zydm pk,a.*,"
		 * + "case when b.num >0 then b.num else 0 end cws, " +
		 * "case when b.yycws >0 then b.yycws else 0 end yycws, " +
		 * "case when b.sycws >0 then b.sycws else 0 end sycws from "+ sql_rs
		 * +" a left join "+sql_cws+
		 * " b on a.xydm = b.xydm and a.nj =b.nj and a.bjdm=b.bjdm and a.xb =b.qsxb ) order by nj desc,xydm,bjdm,xb) a where 1=1 "
		 * ;
		 */
		// --��λ���䰴�༶ͳ���޸�--zhanghui--20130201--start--
		String sql_rs = "(select a.*,nvl(b.num,'0') rzrs,nvl(a.num- nvl(b.num, '0'),'0') wrzrs from " + "(select nj,xydm,zydm,bjdm,xb,count(*) num from view_xsjbxx  group by nj,xydm,zydm,bjdm,xb) a "
				+ "left join " + "(select nj,xydm,zydm,bjdm,xb,count(*) num from ( select a.* from view_xsjbxx a,xg_gygl_new_cwxxb b " + "where a.xh = b.xh ) group by nj,xydm,zydm,bjdm,xb) b "
				+ "on a.nj= b.nj and a.xydm = b.xydm and a.zydm = b.zydm and a.bjdm=b.bjdm and a.xb = b.xb)";

		String sql_cws = "( select nj,xydm,bjdm,qsxb,count(*) num, " + "sum(case when xh is not null then 1 end) yycws " + "from (select a.*,b.qsxb from xg_gygl_new_cwxxb a "
				+ "left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh)" + "group by nj,xydm,bjdm,qsxb )";

		String sql = "select a.*,(select distinct b.zymc from view_njxyzybj_all b where a.xydm = b.xydm and a.zydm=b.zydm) zymc,"
				+ "(select b.bjmc from view_njxyzybj_all b where a.xydm = b.xydm and a.bjdm=b.bjdm) bjmc,"
				+ "rownum r from (select * from (select a.xydm||'_'||a.nj||'_'||a.zydm||'_'||a.bjdm||'_'||a.xb pk,a.*,"
				+ "nvl(b.num,'0') cws, nvl(b.yycws,'0') yycws, nvl(b.num-b.yycws,'0') sycws from " + sql_rs + " a left join " + sql_cws
				+ " b on a.xydm = b.xydm and a.nj =b.nj and a.bjdm=b.bjdm and a.xb =b.qsxb ) where 1=1 " + searchTj + " order by nj desc,xydm,bjdm,xb) a where 1=1 ";
		// --��λ���䰴�༶ͳ���޸�--zhanghui--20130201---end---

		String[] colList = new String[] { "pk", "nj", "bjmc", "xb", "num", "rzrs", "wrzrs", "cws", "yycws", "sycws" };
		return commonQuery(sql, searchTjByUser, inputV, colList, myForm);
	}

	/**
	 * ��ȡ����Ա�����εİ༶Ȩ�޵���ͼ
	 * 
	 * @param request
	 * @return
	 */
	private String getFdyBzrQxBjView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		boolean isFdy = false;
		boolean isBzr = false;
		if (session.getAttribute("isFdy") != null) {
			isFdy = Boolean.parseBoolean(session.getAttribute("isFdy").toString());
		}
		if (session.getAttribute("isBzr") != null) {
			isBzr = Boolean.parseBoolean(session.getAttribute("isBzr").toString());
		}
		String userName = session.getAttribute("userName").toString();// �û���
		String sql_fdy = "";
		if (isFdy && isBzr) {// ͬʱΪ����Ա�Ͱ�����
			sql_fdy = "select bjdm from fdybjb where zgh='" + userName + "' union select bjdm from bzrbbb where zgh='" + userName + "'";
		} else if (isFdy) {// ����Ա
			sql_fdy = "select bjdm from fdybjb where zgh='" + userName + "'";
		} else {// ������
			sql_fdy = "select bjdm from bzrbbb where zgh='" + userName + "'";
		}
		return "(" + sql_fdy + ")";
	}

	/**
	 * ͳ��¥��������Ϣ��ѧԺ��
	 * 
	 * @param request
	 * @param lddm
	 */
	public void tjldFpxx_xy(HttpServletRequest request, CwrzglForm myForm) {
		String lddm = myForm.getLddm();
		String xb = myForm.getXb();
		String cwzt = myForm.getCwzt();
		String nj = myForm.getNj();
		String xydm = myForm.getXydm();
		String bjdm = myForm.getBjdm();
		DAO dao = DAO.getInstance();

		String sql = "select * from xg_gygl_new_ldxxb where lddm=?";// --��ѯ¥��������Ϣ
		HashMap<String, String> ldjbxx = dao.getMapNotOut(sql, new String[] { lddm });

		// sql="select count(1) qss,count(case when xydm is not null or nj is not null then 1 end) yfp_qss, "+
		// "count(case when xydm is not null or nj is not null then null else 1 end) wfp_qss "+
		// "from xg_gygl_new_qsxxb where lddm=?";//--¥������ͳ����Ϣ
		// HashMap<String, String> ldqstjxx=dao.getMapNotOut(sql, new
		// String[]{lddm});
		// ldjbxx.putAll(ldqstjxx);

		sql = "select count(1) cws,count(case when xydm is not null or nj is not null then 1 end) yfp_cws, " + "count(case when xydm is not null or nj is not null then null else 1 end) wfp_cws "
				+ "from xg_gygl_new_cwxxb where lddm=?";// --¥����λͳ����Ϣ
		HashMap<String, String> ldcwtjxx = dao.getMapNotOut(sql, new String[] { lddm });
		ldjbxx.putAll(ldcwtjxx);

		request.setAttribute("ldjbxx", ldjbxx);

		sql = "select a.ch,a.chmc,a.cws,a.yfp_cws,a.wfp_cws,a.bxy_cws,b.qss,b.yfp_qss,b.wfp_qss from " + "( " + "select ch,chmc,count(1) cws, "
				+ "count(case when xh is not null then 1 end) yfp_cws, " + "count(case when xh is null then 1 end) wfp_cws, " + "count(case when xydm=? and nj=? and qsxb=? then 1 end) bxy_cws "
				+ "from view_xg_gygl_new_cwxx where lddm=? and qsxb=? and nj=? and xydm=? and bjdm=? " + "group by ch,chmc order by to_number(ch) "
				+ // --¥�㴲λͳ����Ϣ
				") a, " + "( " + "select ch,count(1) qss, " + "count(case when xydm is not null or nj is not null then 1 end) yfp_qss, "
				+ "count(case when xydm is not null or nj is not null then null else 1 end) wfp_qss " + "from xg_gygl_new_qsxxb where lddm=? and nj=? and xydm=? group by ch order by to_number(ch) " + // --¥��ͳ����Ϣ
				") b where a.ch=b.ch(+) order by to_number(ch)";
		List<HashMap<String, String>> ldlcxx = dao.getListNotOut(sql, new String[] { xydm, nj, xb, lddm, xb, nj, xydm, bjdm, lddm, nj, xydm });
		request.setAttribute("ldlcxx", ldlcxx);

		String cwzt_sql = "";
		if ("δ��ס".equals(cwzt)) {
			cwzt_sql = " and a.xh is null ";
		} else if ("����ס".equals(cwzt)) {
			cwzt_sql = " and a.xh is not null ";
		}
		//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = " order by to_number(ch),qsh,cwh ";
		}else{
			sb = " order by to_number(ch),qsh,to_number(cwh) ";
		}
		sql = "select a.*,b.xm,b.bjmc from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh " + "where lddm=? and a.qsxb=? and a.xydm=? and a.nj=? and a.bjdm=? " + cwzt_sql
				+ " "+sb+" ";// --¥��¥�����Ҵ�λ��Ϣ
		List<HashMap<String, String>> ldlcqscwxxb = dao.getListNotOut(sql, new String[] { lddm, xb, xydm, nj, bjdm });
		request.setAttribute("ldlcqscwxxb", ldlcqscwxxb);

		// ��ȡѧ����Ϣ
		String[] colList = new String[] { "nj", "xydm", "zydm", "bjdm", "xb", "rzqk" };
		String[] colLikeList = new String[] {};

		MakeQuery make = new MakeQuery();
		try {
			make.makeQuery(colList, colLikeList, myForm);
			String query = make.getQueryString();
			String[] inputs = make.getInputList();
			String tableName = "(select a.*,(case when b.xh is null then 'δ��ס' else '����ס' end) rzqk,b.ldmc,b.qsh,b.cwh from " + xsxxb_zxs + " a left join view_xg_gygl_new_cwxx b on a.xh=b.xh)";
			if (!Base.isNull(myForm.getXhxm())) {
				String xhxm = myForm.getXhxm().trim();
				if (xhxm.length() < 20) {// ������С��20��Ӧ�þ���һ��ѧ��
					query += " and (xh like '%" + xhxm + "%' or xm like '%" + xhxm + "%')";
				} else {// ������ѯ
					String splitmark = ",";
					if (xhxm.trim().indexOf("\r\n") > -1) {
						splitmark = "\r\n";
					} else if (xhxm.trim().indexOf("\r") > -1) {
						splitmark = "\r";
					} else if (xhxm.trim().indexOf("\n") > -1) {
						splitmark = "\n";
					} else if (xhxm.indexOf(",") > -1) {
						splitmark = ",";
					} else if (xhxm.indexOf(";") > -1) {
						splitmark = ";";
					} else if (xhxm.trim().indexOf(" ") > -1) {
						splitmark = " ";
					}

					String[] xhs = xhxm.split(splitmark);
					StringBuffer xh = new StringBuffer();
					for (int i = 0; i < xhs.length; i++) {
						xh.append("'");
						xh.append(xhs[i].trim());
						xh.append("'");
						if (i < xhs.length - 1) {
							xh.append(",");
						}
					}
					query += " and xh in(" + xh.toString() + ")";
				}
			}
			query += " order by rzqk,zydm,bjdm,xh";
			List<String[]> xsxxList = commonQueryNotFy(tableName, query, inputs, new String[] { "xh", "xm", "rzqk", "ldmc", "qsh", "cwh", "bjmc" ,"sydqmc"}, "");
			request.setAttribute("xsxxlist", xsxxList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ¥���б�ѧԺ��
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLdList_xy(CwrzglForm myForm) {
		DAO dao = DAO.getInstance();
		String sql = "select lddm,ldmc||(case when ldxb='��ס' then '('||ldxb||')' else '' end) ldmc " + "from xg_gygl_new_ldxxb a where (ldxb=? or ldxb='��ס') and "
				+ " exists (select 1 from xg_gygl_new_cwxxb x where x.bjdm=? and x.lddm=a.lddm )";
		return dao.getListNotOut(sql, new String[] { myForm.getXb(), myForm.getBjdm() });
	}

	/**
	 * �����Ա𡢰༶���룬��ȡ���䵽רҵ��¥���б���רҵ���䷽ʽ��
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLdList_zy(CwrzglForm myForm) {
		DAO dao = DAO.getInstance();
		String sql = "select lddm,ldmc||(case when ldxb='��ס' then '('||ldxb||')' else '' end) ldmc " + "from xg_gygl_new_ldxxb a where (ldxb=? or ldxb='��ס') and "
				+ " exists (select 1 from xg_gygl_new_cwxxb x where exists(select 1 from view_njxyzybj t where bjdm=? and x.zydm=t.zydm) and x.lddm=a.lddm )";
		return dao.getListNotOut(sql, new String[] { myForm.getXb(), myForm.getBjdm() });
	}

	/**
	 * ��ȡָ��ѧԺ�꼶��ͳ��������ѧԺ��
	 * 
	 * @param inputV
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCwfpglInfo_xy(CwrzglForm myForm) {

		// ѧԺ���꼶ѧԺ����ͳ�����ݼ�
		String sql_rs = "( select nj,xydm,bjdm,xb,count(*) num " + "from view_xsjbxx where nj=? and xydm=? and bjdm=? and xb=? " + "group by nj,xydm,bjdm,xb )";

		String sql_cws = "( select nj,xydm,bjdm,qsxb,count(*) num, " + "sum(case when xh is not null then 1 end) yycws, " + "sum(case when xh is null then 1 end) sycws "
				+ "from (select a.*,b.qsxb from xg_gygl_new_cwxxb a " + "left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh)" + "group by nj,xydm,bjdm,qsxb )";

		String sql = "select a.*,a.num-a.yycws wrzrs,(select distinct b.xymc from view_njxyzybj_all b where a.xydm = b.xydm) xymc,"
				+ "rownum r from (select * from (select a.xydm||'_'||a.nj||'_'||a.xb pk,a.*," + "case when b.num >0 then b.num else 0 end cws, "
				+ "case when b.yycws >0 then b.yycws else 0 end yycws, " + "case when b.sycws >0 then b.sycws else 0 end sycws from " + sql_rs + " a left join " + sql_cws
				+ " b on a.xydm = b.xydm and a.nj =b.nj and a.bjdm=b.bjdm and a.xb =b.qsxb )  " + " ) a ";
		String[] colList = new String[] { "pk", "nj", "xymc", "xb", "num", "yycws", "wrzrs", "cws", "sycws" };

		DAO dao = DAO.getInstance();
		return dao.getMap(sql, new String[] { myForm.getNj(), myForm.getXydm(), myForm.getBjdm(), myForm.getXb() }, colList);
	}

	/**
	 * ��ȡѧԺ�꼶�Ա��¥�����������ѧԺ��
	 * 
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String, String>> getXynjxbLdfpxx_xy(CwrzglForm myForm) {
		String xydm = myForm.getXydm();
		String sql = "select a.*,b.xyqss,b.xycws,b.xywrzcws,b.xyyrzcws from " + "( " + "select lddm,ldmc, " + "count(case when xydm=? then 1 end) cws, "
				+ "count(case when xydm=? and bjdm is not null then 1 end) yfpcws, " + "count(case when xydm=? and bjdm is null then 1 end) wfpcws, " + "count(distinct qsh) qss, "
				+ "count(case when qsxydm is not null then 1 end) yfpqss, " + "count(case when qsxydm is null then 1 end) wfpqss " + "from view_xg_gygl_new_cwxx group by lddm,ldmc " + ") a, " + "( "
				+ "select lddm,count(distinct qsh) xyqss,count(1) xycws,count(case when xh is null then 1 end) xywrzcws,"
				+ "count(case when xh is not null then 1 end) xyyrzcws from view_xg_gygl_new_cwxx " + "where nj=? and xydm=? and bjdm=? and qsxb=? group by lddm " + ") b " + "where a.lddm=b.lddm";
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql, new String[] { xydm, xydm, xydm, myForm.getNj(), xydm, myForm.getBjdm(), myForm.getXb() });
	}

	/**
	 * ��ȡѧԺ�꼶�Ա��¥�������������רҵΪ�������
	 * 
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String, String>> getXynjxbLdfpxx_zy(CwrzglForm myForm) {
		String xydm = myForm.getXydm();
		String sql = "select a.*,b.zyqss,b.zycws,b.zywrzcws,b.zyyrzcws from " + "( " + "select lddm,ldmc, " + "count(case when xydm=? then 1 end) cws, "
				+ "count(case when xydm=? and zydm is not null then 1 end) yfpcws, " + "count(case when xydm=? and zydm is null then 1 end) wfpcws, " + "count(distinct qsh) qss, "
				+ "count(case when qsxydm is not null then 1 end) yfpqss, " + "count(case when qsxydm is null then 1 end) wfpqss " + "from view_xg_gygl_new_cwxx group by lddm,ldmc " + ") a, " + "( "
				+ "select lddm,count(distinct qsh) zyqss,count(1) zycws,count(case when xh is null then 1 end) zywrzcws,"
				+ "count(case when xh is not null then 1 end) zyyrzcws from view_xg_gygl_new_cwxx t "
				+ "where nj=? and xydm=? and exists(select 1 from view_njxyzybj x where x.bjdm = ? and x.zydm=t.zydm) and qsxb=? group by lddm " + ") b " + "where a.lddm=b.lddm";
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql, new String[] { xydm, xydm, xydm, myForm.getNj(), xydm, myForm.getBjdm(), myForm.getXb() });
	}

	/*
	 * public static Stringxsxxb=
	 * " (select a.*,b.bjmc from (select xh,xm,xbm xb,to_char(nj) nj, bmdm xydm,zydm,bjdm from bks_xsjbxx a "
	 * + "where not exists (select 1 from xsxxb b where a.xh = b.xh) "+
	 * "union all "+
	 * "select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm from xsxxb a " +
	 * "where (sfyby = '��' or sfyby is null) and (sfzx = '��У' or sfzx is null)) a left join VIEW_NJXYZYBJ B on A.BJDM = B.bjdm ) "
	 * ;
	 */

	public static String xsxxb_zxs = " (select a.*,c.rs from "
			+ " ( select a.xh,a.xm,(case a.xb when '1' then '��' when '2' then 'Ů' else a.xb end) xb,a.nj,a.xydm,a.zydm,a.bjdm,a.bjmc,"
			+ " (select c.qxmc from dmk_qx c where c.qxdm = substr(a.syd, 0, 2) || '0000') || (select d.qxmc from dmk_qx d where d.qxdm = substr(a.syd, 0, 4) || '00' "
			+ " and a.syd <> substr(a.syd, 0, 2) || '0000') sydqmc,a.mzmc from view_xsbfxx a where a.sfzx = '��У' or a.sfzx is null ) "
			+ " a left join (select nj,xydm,zydm,bjdm,count(*) rs from  view_xsjbxx group by nj,xydm,zydm,bjdm) c on a.nj=c.nj and a.xydm=c.xydm and a.zydm=c.zydm and a.bjdm=c.bjdm) ";
	
	//�½�ҽ�ƴ�ѧ��ѧԺ
	public static String xsxxb_zxs_13560 = " (select a.*,c.rs from "
		+ " ( select a.xh,a.xm,(case a.xb when '1' then '��' when '2' then 'Ů' else a.xb end) xb,a.nj,a.xydm,a.zydm,a.bjdm,a.bjmc,"
		+ " (select c.qxmc from dmk_qx c where c.qxdm = substr(a.syd, 0, 2) || '0000') || (select d.qxmc from dmk_qx d where d.qxdm = substr(a.syd, 0, 4) || '00' "
		+ " and a.syd <> substr(a.syd, 0, 2) || '0000') sydqmc,a.mzmc,a.zd4 yzlbdm,b.yzlbmc from view_xsxxb a left join dmk_yzlb_hbxy b on a.zd4 = b.yzlbdm where a.sfzx = '��У' or a.sfzx is null ) "
		+ " a left join (select nj,xydm,zydm,bjdm,count(*) rs from  view_xsjbxx group by nj,xydm,zydm,bjdm) c on a.nj=c.nj and a.xydm=c.xydm and a.zydm=c.zydm and a.bjdm=c.bjdm) ";
	

	public static String xsxxb = " (select a.*,b.bjmc,c.rs from "
			+ Base.xsxxb
			+ " a left join VIEW_NJXYZYBJ B on A.BJDM = B.bjdm left join (select nj,xydm,zydm,bjdm,count(*) rs from  view_xsjbxx group by nj,xydm,zydm,bjdm) c on a.nj=c.nj and a.xydm=c.xydm and a.zydm=c.zydm and a.bjdm=c.bjdm) ";

	/**
	 * @����:(��λ��ס����-��ȡ��ǰרҵ�Ĵ�λ��Ϣ)��רҵ������䴲λ��ʽ
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-9-5 ����03:53:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param myForm
	 *            void ��������
	 * @throws
	 */
	public void tjldFpxx_zy(HttpServletRequest request, CwrzglForm myForm) {
		String lddm = myForm.getLddm();
		String xb = myForm.getXb();
		String cwzt = myForm.getCwzt();
		String nj = myForm.getNj();
		String xydm = myForm.getXydm();
		String bjdm = myForm.getBjdm();
		DAO dao = DAO.getInstance();

		String sql = "select * from xg_gygl_new_ldxxb where lddm=?";// --��ѯ¥��������Ϣ
		HashMap<String, String> ldjbxx = dao.getMapNotOut(sql, new String[] { lddm });

		sql = "select count(1) cws,count(case when xydm is not null or nj is not null then 1 end) yfp_cws, " + "count(case when xydm is not null or nj is not null then null else 1 end) wfp_cws "
				+ "from xg_gygl_new_cwxxb where lddm=?";// --¥����λͳ����Ϣ
		HashMap<String, String> ldcwtjxx = dao.getMapNotOut(sql, new String[] { lddm });
		ldjbxx.putAll(ldcwtjxx);

		request.setAttribute("ldjbxx", ldjbxx);

		sql = "select a.ch,a.chmc,a.cws,a.yfp_cws,a.wfp_cws,a.bxy_cws,b.qss,b.yfp_qss,b.wfp_qss from " + "( " + "select ch,chmc,count(1) cws, "
				+ "count(case when xh is not null then 1 end) yfp_cws, " + "count(case when xh is null then 1 end) wfp_cws, " + "count(case when xydm=? and nj=? and qsxb=? then 1 end) bxy_cws "
				+ "from view_xg_gygl_new_cwxx a where lddm=? and qsxb=? and nj=? and xydm=? and exists(select 1 from view_njxyzybj x where bjdm = ? and a.zydm=x.zydm)  "
				+ "group by ch,chmc order by to_number(ch) "
				+ // --¥�㴲λͳ����Ϣ
				") a, " + "( " + "select ch,count(1) qss, " + "count(case when xydm is not null or nj is not null then 1 end) yfp_qss, "
				+ "count(case when xydm is not null or nj is not null then null else 1 end) wfp_qss " + "from xg_gygl_new_qsxxb where lddm=? and nj=? and xydm=? group by ch order by to_number(ch) " + // --¥��ͳ����Ϣ
				") b where a.ch=b.ch(+) order by to_number(ch)";
		List<HashMap<String, String>> ldlcxx = dao.getListNotOut(sql, new String[] { xydm, nj, xb, lddm, xb, nj, xydm, bjdm, lddm, nj, xydm });
		request.setAttribute("ldlcxx", ldlcxx);

		String cwzt_sql = "";
		if ("δ��ס".equals(cwzt)) {
			cwzt_sql = " and ((exists (select 1 from view_njxyzybj x where x.bjdm ='" + bjdm + "'  and a.zydm = x.zydm) and a.xh is null)) ";
		} else if ("����ס".equals(cwzt)) {
			cwzt_sql = " and (a.bjdm='" + bjdm + "' and a.xh is not null) ";
		} else {
			cwzt_sql = " and ((exists (select 1 from view_njxyzybj x where x.bjdm = '" + bjdm + "' and a.zydm = x.zydm) and a.xh is null) or (a.bjdm='" + bjdm + "' and a.xh is not null))";
		}
		//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = " order by to_number(ch),qsh,cwh ";
		}else{
			sb = " order by to_number(ch),qsh,to_number(cwh) ";
		}
		sql = "select a.*,b.xm,b.bjmc from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh " + "where lddm=? and a.qsxb=? and a.xydm=? and a.nj=?" + cwzt_sql
				+ " "+sb+" ";// --¥��¥�����Ҵ�λ��Ϣ
		List<HashMap<String, String>> ldlcqscwxxb = dao.getListNotOut(sql, new String[] { lddm, xb, xydm, nj });
		request.setAttribute("ldlcqscwxxb", ldlcqscwxxb);

		// ��ȡѧ����Ϣ
		String[] colList = new String[] { "nj", "xydm", "zydm", "bjdm", "xb", "rzqk" };
		String[] colLikeList = new String[] {};

		MakeQuery make = new MakeQuery();
		try {
			make.makeQuery(colList, colLikeList, myForm);
			String query = make.getQueryString();
			String[] inputs = make.getInputList();
			String tableName = "(select a.*,(case when b.xh is null then 'δ��ס' else '����ס' end) rzqk,b.ldmc,b.qsh,b.cwh " + "from view_xsjbxx a left join view_xg_gygl_new_cwxx b on a.xh=b.xh)";
			if (!Base.isNull(myForm.getXhxm())) {
				String xhxm = myForm.getXhxm().trim();
				if (xhxm.length() < 20) {// ������С��20��Ӧ�þ���һ��ѧ��
					query += " and (xh like '%" + xhxm + "%' or xm like '%" + xhxm + "%')";
				} else {// ������ѯ
					String splitmark = ",";
					if (xhxm.trim().indexOf("\r\n") > -1) {
						splitmark = "\r\n";
					} else if (xhxm.trim().indexOf("\r") > -1) {
						splitmark = "\r";
					} else if (xhxm.trim().indexOf("\n") > -1) {
						splitmark = "\n";
					} else if (xhxm.indexOf(",") > -1) {
						splitmark = ",";
					} else if (xhxm.indexOf(";") > -1) {
						splitmark = ";";
					} else if (xhxm.trim().indexOf(" ") > -1) {
						splitmark = " ";
					}

					String[] xhs = xhxm.split(splitmark);
					StringBuffer xh = new StringBuffer();
					for (int i = 0; i < xhs.length; i++) {
						xh.append("'");
						xh.append(xhs[i].trim());
						xh.append("'");
						if (i < xhs.length - 1) {
							xh.append(",");
						}
					}
					query += " and xh in(" + xh.toString() + ")";
				}
			}
			query += " order by rzqk,zydm,bjdm,xh";
			List<String[]> xsxxList = commonQueryNotFy(tableName, query, inputs, new String[] { "xh", "xm", "rzqk", "ldmc", "qsh", "cwh", "bjmc" }, "");
			request.setAttribute("xsxxlist", xsxxList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * @����:��ȡ�������(�½�ҽ�ƴ�ѧ��ѧԺ���Ի�ר��)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-24 ����11:00:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYzlbList(){
		String sql = "select yzlbdm,yzlbmc from dmk_yzlb_hbxy";
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql, new String[]{});
	}
}
