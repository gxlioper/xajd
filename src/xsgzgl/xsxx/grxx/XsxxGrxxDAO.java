package xsgzgl.xsxx.grxx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.rcsw.qjgl.RcswQjglForm;
import xsgzgl.xsxx.model.CsszModel;
import xsgzgl.xsxx.model.ZdxgModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_������Ϣ_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XsxxGrxxDAO extends CommDAO {
	
	/**
	 * ���ѧ����Ϣ
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public HashMap<String, String> getXsxxInfo(XsxxGrxxForm model,
			List<HashMap<String, String>> zdList) {

		String xh = model.getXh();

		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		
		ArrayList<String> outValue = new ArrayList<String>();
		if (zdList != null && zdList.size() > 0) {
			for (int i = 0; i < zdList.size(); i++) {

				HashMap<String, String> map = zdList.get(i);

				String zd = map.get("zd");
				String bm = map.get("bm");
				
				if (i != 0) {
					sql.append(",");
				}

				if ("bjdm".equalsIgnoreCase(zd)) {// ���ڲ���
					sql.append("nj||'(�꼶)'||' '");
					sql.append("||xymc||'("+Base.YXPZXY_KEY+")'||' '  ");
					sql.append("||zymc||'(רҵ)'||' '");
					sql.append("||bjmc||'(�༶)' bjdm ");
				} else if ("mz".equalsIgnoreCase(zd)) {// ����
					sql.append("(select b.mzmc from mzdmb b where a.mz=b.mzdm) mz ");
				} else if ("zzmm".equalsIgnoreCase(zd)) {// ������ò
					sql.append("(select b.zzmmmc from zzmmdmb b where a.zzmm=b.zzmmdm) zzmm ");
				} else if ("pycc".equalsIgnoreCase(zd)) {// �������
					sql.append("(select b.pyccmc from xg_xsxx_pyccdmb b where a.pycc=b.pyccdm) pycc ");
				} else if ("hkszd".equalsIgnoreCase(zd)) {// �������ڵ�
					sql.append("(select c.sydq from dmk_sydq c where c.sydqdm = substr(a.hkszd, 0, 6)) || ' ' ||");
					sql.append("(select d.qxmc from dmk_qx d where d.qxdm = substr(a.hkszd, 8, 6)) || ' '||");
					sql.append("(select e.qxmc from dmk_qx e where e.qxdm = substr(a.hkszd, 15, 6)) hkszd ");
				} else if ("jg".equalsIgnoreCase(zd)) {//����
					sql.append("(select c.sydq from dmk_sydq c where c.sydqdm = substr(a.jg, 0, 6)) || ' ' ||");
					sql.append("(select d.qxmc from dmk_qx d where d.qxdm = substr(a.jg, 8, 6)) || ' '||");
					sql.append("(select e.qxmc from dmk_qx e where e.qxdm = substr(a.jg, 15, 6)) jg ");
				} else if ("syd".equalsIgnoreCase(zd)) {//��Դ��
					sql.append("(select c.sydq from dmk_sydq c where c.sydqdm = substr(a.syd, 0, 6)) || ' ' ||");
					sql.append("(select d.qxmc from dmk_qx d where d.qxdm = substr(a.syd, 8, 6)) || ' '||");
					sql.append("(select e.qxmc from dmk_qx e where e.qxdm = substr(a.syd, 15, 6)) syd ");
				} else if ("yhdm".equalsIgnoreCase(zd)) {// ��������
					sql.append("(select b.yhmc from dmk_yh b where a.yhdm=b.yhdm) yhdm ");
				} else if ("pyfs".equalsIgnoreCase(zd)) {// ������ʽ
					sql.append("(select b.pyfsmc from xg_xsxx_pyfsdmb b where a.pyfs=b.pyfsdm) pyfs ");
				} else if ("kslb".equalsIgnoreCase(zd)) {// �������
					sql.append("(select b.kslbmc from xg_xsxx_kslbdmb b where a.kslb=b.kslbdm) kslb ");
				} else if ("rxfs".equalsIgnoreCase(zd)) {// ��ѧ��ʽ
					sql.append("(select b.rxfsmc from xg_xsxx_rxfsdmb b where a.rxfs=b.rxfsdm) rxfs ");
				} else if ("jtcy1_mz".equalsIgnoreCase(zd)) {// ��ͥ��Ա1_����
					sql.append("(select b.mzmc from mzdmb b where c.jtcy1_mz=b.mzdm) jtcy1_mz ");
				} else if ("jtcy1_zzmm".equalsIgnoreCase(zd)) {// ��ͥ��Ա1_������ò
					sql.append("(select b.zzmmmc from zzmmdmb b where c.jtcy1_zzmm=b.zzmmdm) jtcy1_zzmm ");
				} else if ("jtcy2_mz".equalsIgnoreCase(zd)) {// ��ͥ��Ա2_����
					sql.append("(select b.mzmc from mzdmb b where c.jtcy2_mz=b.mzdm) jtcy2_mz ");
				} else if ("jtcy2_zzmm".equalsIgnoreCase(zd)) {// ��ͥ��Ա2_������ò
					sql.append("(select b.zzmmmc from zzmmdmb b where c.jtcy2_zzmm=b.zzmmdm) jtcy2_zzmm ");
				} else if ("jtcy3_mz".equalsIgnoreCase(zd)) {// ��ͥ��Ա3_����
					sql.append("(select b.mzmc from mzdmb b where c.jtcy3_mz=b.mzdm) jtcy3_mz ");
				} else if ("jtcy3_zzmm".equalsIgnoreCase(zd)) {// ��ͥ��Ա3_������ò
					sql.append("(select b.zzmmmc from zzmmdmb b where c.jtcy3_zzmm=b.zzmmdm) jtcy3_zzmm ");
				} else {
					sql.append(bm + "." + zd);
				}
				
				outValue.add(zd);
			}
		}
		sql.append(" from view_xsxxb a ");
		sql.append(" left join xsfzxxb c on a.xh = c.xh ");
		sql.append(" where a.xh = ? ");

		DAO dao = DAO.getInstance();

		if (zdList != null && zdList.size() > 0) {

		}
		
		HashMap<String, String> map = dao.getMap(sql.toString(),
				new String[] { xh }, outValue.toArray(new String[]{}));

		return map;
	}
	
	/**
	 * �����ֶ��޸�
	 * 
	 * @author luojw
	 */
	public boolean saveZdxg(XsxxGrxxForm model, User user) {

		DAO dao = DAO.getInstance();
		
		ZdxgModel zdxgModel = model.getZdxgModel();
		
		String xh = zdxgModel.getXh();
		String sqid = zdxgModel.getSqid();
		String xgr = user.getUserName();
		String xgzd = zdxgModel.getXgzd();
		String ysz = zdxgModel.getXgzd();
		String xgz = zdxgModel.getXgz();
		
		String bm = dao.getOneValue("xg_xsxx_grxx_zdszb", "bm", "zd", xgzd);
		String tableName = ("a".equalsIgnoreCase(bm)) ? "view_xsxxb" : "xsfzxxb";
		boolean flag = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xsxx_grxx_zdxgb");
		sql.append("(sqid,xh,xgr,xgzd,ysz,xgz)");
		sql.append("select '' sqid, xh ");
		sql.append(",'" + xgr + "' xgr ");
		sql.append(",'" + xgzd + "' xgzd ");
		sql.append("," + ysz + " ysz ");
		sql.append(",'" + xgz + "' xgz ");
		sql.append("from ");
		sql.append(tableName);
		sql.append(" a ");
		sql.append("where a.xh = ? ");
		
		try {
			flag = dao.runUpdate(sql.toString(), new String[]{xh});
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * ������ڲ���
	 * 
	 * @author ΰ�����
	 * 
	 */
	public String getSzbm(String bjdm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("nj||'(�꼶)'||' '");
		sql.append("||xymc||'("+Base.YXPZXY_KEY+")'||' '  ");
		sql.append("||zymc||'(רҵ)'||' '");
		sql.append("||bjmc||'(�༶)' szbm ");
		sql.append("from view_njxyzybj ");
		sql.append("where bjdm = ? ");

		String szbm = dao.getOneRs(sql.toString(), new String[] { bjdm },
				"szbm");

		return szbm;
	}
	
	/**
	 * ���ʡ����
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getSsx(String ssx) {

		DAO dao = DAO.getInstance();
		String[] ssxArr = ssx.split("/");
		String sheng = "";
		String shi = "";
		String xian = "";
		if(null!=ssxArr){
			if(ssxArr.length==1){
				sheng = ssx.split("/")[0];
			}else if(ssxArr.length==2){
				sheng = ssx.split("/")[0];
				shi = ssx.split("/")[1];
			}else if(ssxArr.length==3){
				sheng = ssx.split("/")[0];
				shi = ssx.split("/")[1];
				xian = ssx.split("/")[2];
			}
		}

		StringBuilder sql = new StringBuilder();

		sql.append("select qxdm,qxmc ");
		sql.append("from dmk_qx ");
		sql.append("where qxdm = ? ");
		sql.append("or qxdm = ? ");
		sql.append("or qxdm = ? ");
		sql.append("order by to_number(qxdm) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { sheng, shi, xian }, new String[] { "qxmc" });

		return list;
	}

	/**
	 * ��ʼ���ֶ��޸ı�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public static Boolean initZdxgb(String xh) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("delete from ");
		sql.append("xg_xsxx_grxx_zdxgb ");
		sql.append("where 1=1 ");
		sql.append("and sqid is null ");
		sql.append("and xh=? ");

		return dao.runUpdate(sql.toString(), new String[] { xh });
	}	
	
	/**
	 * ��������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean insertXgsq(XsxxGrxxForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xsxx_grxx_xgsqb ");
		sql.append("(xh,sqr,sqjg) ");
		sql.append("values(?,?,?)");

		return dao.runUpdate(sql.toString(), new String[] { model.getXh(),
				user.getUserName(), "δ���" });
	}
	
	/**
	 * �޸�����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean updateXgsq(XsxxGrxxForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_xgsqb ");
		sql.append("set sqjg = ? ");
		sql.append("where id = ? ");

		return dao.runUpdate(sql.toString(), new String[] { "δ���",
				model.getSqid() });
	}
	
	/**
	 * �޸����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean updateXgsh(XsxxGrxxForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_xgshb ");
		sql.append("set shzt = ? ");
		sql.append(",shr = '' ");
		sql.append(",shyj = '' ");
		sql.append("where sqid = ? ");

		return dao.runUpdate(sql.toString(), new String[] { "δ���",
				model.getSqid() });
	}
	
	/**
	 * �������ID
	 * 
	 * @author luojw
	 */
	public String getSqid(XsxxGrxxForm model, User user) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.id ");
		sql.append("from xg_xsxx_grxx_xgsqb a ");
		sql.append("where exists (select 1 ");
		sql.append("from (select xh, max(sqsj) lastTime ");
		sql.append("from xg_xsxx_grxx_xgsqb ");
		sql.append("group by xh) b ");
		sql.append("where a.xh = b.xh ");
		sql.append("and a.sqsj = b.lastTime) ");
		sql.append("and a.xh=? ");
		sql.append("and a.sqjg<>? ");
		sql.append("and sqsj >= (select sqkssj from xg_xsxx_grxx_szb where rownum = 1) ");
		sql.append("and sqsj <= (select sqjssj from xg_xsxx_grxx_szb where rownum = 1) ");
		
		return dao.getOneRs(sql.toString(), new String[] { model.getXh(),"���ͨ��" },
				"id");
	}
	
	/**
	 * ��������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean insertXgsh(XsxxGrxxForm model, User user) throws Exception {

		CsszModel csszModel = model.getCsszModel();

		// ����ID
		String sqid = model.getSqid();

		// ����ID
		String lcid = csszModel.getLcid();
		
		// ��λ�б�
		List<HashMap<String, String>> gwList = csszModel.getGwList();

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xsxx_grxx_xgshb ");
		sql.append("(sqid,gwid,lcid) ");
		sql.append("values(?,?,?)");

		boolean flag = true;

		List<String[]> params = new ArrayList<String[]>();
		if (gwList != null && gwList.size() > 0) {

			for (int i = 0; i < gwList.size(); i++) {
				String[] value = new String[] { sqid,
						gwList.get(i).get("gwid"), lcid };
				params.add(value);
			}

			flag = saveArrDate(sql.toString(), params, "xg_xsxx_grxx_xgshb",
					user);
		}

		return flag;
	}
	
	/**
	 * ��������ID
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean saveSqid(XsxxGrxxForm model) throws Exception {

		DAO dao = DAO.getInstance();

		// ѧ��
		String xh = model.getXh();
		// ����ID
		String sqid = model.getSqid();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_zdxgb a set sqid=? ");
		sql.append("where exists (select 1 ");
		sql.append("from (select xh, xgzd, max(xgsj) lastTime ");
		sql.append("from xg_xsxx_grxx_zdxgb ");
		sql.append("group by xh, xgzd) b ");
		sql.append("where a.xh = b.xh ");
		sql.append("and a.xgzd = b.xgzd ");
		sql.append("and a.xgsj = b.lastTime) ");
		sql.append("and a.xh=? ");
		sql.append("and a.sqid is null ");
		
		boolean flag = dao.runUpdate(sql.toString(), new String[] { sqid, xh });

		return flag;
	}

	/**
	 * ��������Ϣ�б�
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getShInfoList(XsxxGrxxForm model) {

		DAO dao = DAO.getInstance();

		// ����ID
		String sqid = model.getSqid();

		StringBuilder sql = new StringBuilder();
		sql.append("select gwid,shzt,shyj from xg_xsxx_grxx_xgshb ");
		sql.append("where sqid = ? ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { sqid }, new String[] { "gwid", "shzt", "shyj" });

		return list;
	}
	
	/**
	 * ����ֶ��޸���Ϣ�б�
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getXgInfoList(XsxxGrxxForm model) {

		DAO dao = DAO.getInstance();
		
		// ѧ��
		String xh = model.getXh();
		// ����ID
		String sqid = model.getSqid();

		StringBuilder sql = new StringBuilder();	
		sql.append("select a.xgzd zd, a.ysz,a.xgz, b.zdlx, b.source_table, b.select_dm, b.select_mc ");
		sql.append("from xg_xsxx_grxx_zdxgb a, xg_xsxx_grxx_zdszb b ");
		sql.append("where a.xgzd = b.zd ");
		sql.append("and exists (select 1 ");
		sql.append("from (select sqid, xh, xgzd, max(xgsj) lastTime ");
		sql.append("from xg_xsxx_grxx_zdxgb ");
		sql.append("group by sqid, xh, xgzd) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and a.xh = b.xh ");
		sql.append("and a.xgsj = b.lastTime ");
		sql.append("and a.xgzd = b.xgzd) ");
		sql.append("and a.xh = ? ");
		sql.append("and a.sqid = ? ");		   

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xh, sqid }, new String[] { "zd", "zdlx", "xgz",
						"ysz", "source_table", "select_dm", "select_mc" });

		return list;
	}
	
	/**
	 * ����޸��ֶ��б�
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getXgzdList(XsxxGrxxForm model) {

		DAO dao = DAO.getInstance();
		
		// ѧ��
		String xh = model.getXh();

		StringBuilder sql = new StringBuilder();	
		sql.append("select a.xh,c.zd, a.xgz, c.bm ");
		sql.append("from xg_xsxx_grxx_zdxgb a, xg_xsxx_grxx_zdszb c ");
		sql.append("where a.xgzd = c.zd ");
		sql.append("and exists (select 1 ");
		sql.append("from (select xh, xgzd, max(xgsj) lastTime ");
		sql.append("from xg_xsxx_grxx_zdxgb ");
		sql.append("group by xh, xgzd) b ");
		sql.append("where a.xh = b.xh ");
		sql.append("and a.xgzd = b.xgzd ");
		sql.append("and a.xgsj = b.lastTime) ");
		sql.append("and a.xh = ? ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xh }, new String[] { "xh","zd", "xgz", "bm" });

		return list;
	}
	
	/**
	 * ����������ѧ����Ϣ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public boolean copyToXsxxb(String xh) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xsxxb ");
		sql.append("(xh,xm,xb,sfzh,nj,xjztm,xz,xydm,zydm,bjdm");
		sql.append(",ksh)");

		sql.append("select xh,xm,xbm xb,sfzh,to_char(nj) nj,xjztm,substr(xz, 0, 1) xz,bmdm xydm,zydm,bjdm");
		sql.append(",ksh ");
		sql.append("from bks_xsjbxx a ");
		sql.append("where a.xh=? ");

		boolean flag = dao.runUpdate(sql.toString(), new String[] { xh });

		return flag;
	}
	
	/**
	 * ����������������Ϣ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public boolean copyToFzxxb(String xh) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xsfzxxb ");
		sql.append("(xh)");

		sql.append("select xh ");
		sql.append("from bks_xsjbxx a ");
		sql.append("where a.xh=? ");

		boolean flag = dao.runUpdate(sql.toString(), new String[] { xh });

		return flag;
	}
	
	/**
	 * �ύ������Ϣ�޸�
	 * 
	 * @author ΰ�����
	 * 
	 */
	public boolean submitGrxx(XsxxGrxxForm model,
			List<HashMap<String, String>> zdxgList) {

		CsszModel csszModel = model.getCsszModel();

		// �Ƿ����
		String sfsh = csszModel.getSfsh();
		
		boolean flag = true;

		if (zdxgList != null && zdxgList.size() > 0) {
			
			String[] commitSql = new String[zdxgList.size()];
				
			for (int i = 0; i < zdxgList.size(); i++) {
				HashMap<String, String> map = zdxgList.get(i);
				
				String xh = map.get("xh");//ѧ��
				String zd = map.get("zd");//�ֶ�
				String xgz = map.get("xgz");//�޸�ֵ
				String bm = map.get("bm");//����
				
				StringBuilder sql = new StringBuilder();
				
				String tableName = ("a".equalsIgnoreCase(bm)) ? "xsxxb"
						: "xsfzxxb";
				
				sql.append(" update ");
				sql.append(tableName);
				sql.append(" a set ");
				sql.append(zd);
				sql.append(" ='" + xgz + "' ");
				sql.append(" where xh = '" + xh + "' ");
				if ("��".equalsIgnoreCase(sfsh)) {
					
					String sqid = model.getSqid();
					String shgw = model.getShgw();
					
					sql.append(" and exists (select 1 ");
					sql.append(" from (select b.xh, c.sqid, c.gwid, c.shzt ");
					sql.append(" from xg_xsxx_grxx_xgsqb b, xg_xsxx_grxx_xgshb c ");
					sql.append(" where b.id = c.sqid) d ");
					sql.append(" where a.xh = d.xh ");
					sql.append(" and d.sqid = '"+sqid+"' ");
					sql.append(" and d.gwid = '"+shgw+"') ");
				}
				
				commitSql[i] = sql.toString();
			}
			
			try {
				flag=saveArrDate(commitSql);
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}

		return flag;
	}

	/**
	 * ����޸�����б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXgshList(XsxxGrxxForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ��˸�λ
		String shgw = model.getShgw();
		
		StringBuilder sql = new StringBuilder();

		sql.append("select rownum r,a.* from (select c.lcid, ");
		sql.append("a.xh,f.xm,f.nj,f.xydm,f.xymc,f.zydm, ");
		sql.append("f.zymc,f.bjdm,f.bjmc,a.sqsj, ");
		sql.append("b.sqid,d.xh lv,e.maxlv,b.gwid, ");
		sql.append("b.shr,b.shzt,b.shsj,b.shyj ");
		sql.append("from ");
		sql.append("xg_xsxx_grxx_xgsqb a, ");
		sql.append("xg_xsxx_grxx_xgshb b, ");
		sql.append("(select lcid from xg_xsxx_grxx_szb where rownum = 1) c, ");
		sql.append("xg_xtwh_spbz d, ");
		sql.append("(select max(xh) maxlv, splc from xg_xtwh_spbz group by splc) e, ");
		
		sql.append("(");
		sql.append("select g.xh,g.xm,h.nj,h.xydm,h.xymc,h.zydm,h.zymc,h.bjdm,h.bjmc ");
		sql.append("from " + Base.xsxxb + " g ");
		sql.append("left join view_njxyzybj_all h on g.bjdm = h.bjdm ");
		sql.append(") f ");
		sql.append("where a.id = b.sqid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid ");
		sql.append("and f.xh = a.xh ");
		sql.append("and e.splc = d.splc) a ");
		sql.append("where 1 = 1 ");
		sql.append("and a.gwid='" + shgw + "' ");
		
		//��������
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");

		sql.append(searchTj);
		sql.append(searchTjByUser);

		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		sql.append("and (");
		//��С����
		sql.append("(a.lv = 1 and (a.shzt = 'δ���' or a.shzt = '������') and exists ");
		sql.append("(select 1 from (select c.lcid,a.xh, ");
		sql.append("a.sqsj,b.sqid,b.gwid,d.xh lv, ");
		sql.append("b.shr,b.shzt,b.shsj,b.shyj ");
		sql.append("from ");
		sql.append("xg_xsxx_grxx_xgsqb a, ");
		sql.append("xg_xsxx_grxx_xgshb b, ");
		sql.append("(select lcid from xg_xsxx_grxx_szb where rownum = 1) c, ");
		sql.append("xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and ((b.lv = a.lv + 1) or a.lv = a.maxlv) ");
		sql.append("and (b.shzt = 'δ���' or b.shzt = '�˻�'))) ");
		     
		// ��������
		sql.append("or ");
		sql.append("(a.lv <> 1 and a.lv <> a.maxlv and (a.shzt = 'δ���' or a.shzt = '������') and exists ");
		sql.append("(select 1 from (select c.lcid,a.xh, ");
		sql.append("a.sqsj,b.sqid,b.gwid,d.xh lv,b.shr,b.shzt, ");
		sql.append("b.shsj,b.shyj ");
		sql.append("from ");
		sql.append("xg_xsxx_grxx_xgsqb a, ");
		sql.append("xg_xsxx_grxx_xgshb b, ");
		sql.append("(select lcid from xg_xsxx_grxx_szb where rownum = 1) c, ");
		sql.append("xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and b.lv = a.lv - 1 ");
		sql.append("and b.shzt = 'ͨ��') and exists ");
		sql.append("(select 1 from (select ");
		sql.append("c.lcid,a.xh,a.sqsj,b.sqid, ");
		sql.append("b.gwid,d.xh lv,b.shr,b.shzt,b.shsj,b.shyj ");
		sql.append("from ");
		sql.append("xg_xsxx_grxx_xgsqb a, ");
		sql.append("xg_xsxx_grxx_xgshb b, ");
		sql.append("(select lcid from xg_xsxx_grxx_szb where rownum = 1) c, ");
		sql.append("xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and b.lv = a.lv + 1 ");
		sql.append("and (b.shzt = 'δ���' or b.shzt = '�˻�'))) ");
		
		// ��󼶱�
		sql.append("or ");
		sql.append("(a.lv = a.maxlv and (a.shzt = 'δ���' or a.shzt = '������') and exists ");
		sql.append("(select 1 from (select c.lcid,a.xh, ");
		sql.append("a.sqsj,b.sqid,b.gwid,d.xh lv, ");
		sql.append("b.shr,b.shzt,b.shsj,b.shyj ");
		sql.append("from  ");
		sql.append("xg_xsxx_grxx_xgsqb a, ");
		sql.append("xg_xsxx_grxx_xgshb b, ");
		sql.append("(select lcid from xg_xsxx_grxx_szb where rownum = 1) c, ");
		sql.append("xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and b.lv = a.lv - 1 ");
		sql.append("and b.shzt = 'ͨ��')) ");
		
		sql.append(")");
		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), sql.toString(), inputV,
						new String[] { "sqid", "xh", "xm", "bjmc", "sqsj",
								"shzt" });

		return list;
	}
	
	/**
	 * ����������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public boolean updateSqjg(XsxxGrxxForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		// ����ID
		String sqid = model.getSqid();
		// ��˸�λ����
		String shgwmc = model.getShgwmc();
		// ���״̬
		String shzt = model.getShzt();
		// ������
		String sqjg = "";
		// ���
		boolean isMax = model.isMax();
		// ��С��
		boolean isMin = model.isMin();
		
		if ("�˻�".equalsIgnoreCase(shzt)) {
			if (isMin) {
				sqjg = "�˻�";
			} else {
				sqjg = shgwmc + "�˻�";
			}
		} else if ("ͨ��".equalsIgnoreCase(shzt)) {
			if (isMax) {
				sqjg = "���ͨ��";
			} else {
				sqjg = shgwmc + "ͨ��";
			}
		} else {
			sqjg = shgwmc + "���" + shzt;
		}
				
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_xgsqb ");
		sql.append("set sqjg = ? ");
		sql.append("where id = ?");

		boolean flag = dao.runUpdate(sql.toString(),
				new String[] { sqjg, sqid });

		return flag;
	}
	
	/**
	 * ��������״̬
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public boolean updateCszt(XsxxGrxxForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		// ����ID
		String sqid = model.getSqid();
		// �ϼ���λID
		String pre_gwid = model.getPre_gwid();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_xgshb ");
		sql.append("set shzt = '������' ");
		sql.append("where sqid = ? ");
		sql.append("and gwid = ? ");

		boolean flag = dao.runUpdate(sql.toString(), new String[] { sqid,
				pre_gwid });

		return flag;
	}
	
	/**
	 * ����ͨ��״̬
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public boolean updateTgzt(XsxxGrxxForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		// ����ID
		String sqid = model.getSqid();
		// �󼶸�λID
		String next_gwid = model.getNext_gwid();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_xgshb ");
		sql.append("set shzt = 'δ���' ");
		sql.append("where sqid = ? ");
		sql.append("and gwid = ? ");

		boolean flag = dao.runUpdate(sql.toString(), new String[] { sqid,
			next_gwid });

		return flag;
	}
	
	/**
	 * ��ý����ѯ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getJgcxList(XsxxGrxxForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		StringBuilder sql = new StringBuilder();

		sql.append("select rownum r,a.* from (select a.id sqid, ");
		sql.append("a.xh,f.xm,f.nj,f.xydm,f.xymc,f.zydm, ");
		sql.append("f.zymc,f.bjdm,f.bjmc,a.sqsj,a.sqjg ");;
		sql.append("from ");
		sql.append("xg_xsxx_grxx_xgsqb a, ");
		sql.append("view_xsjbxx f ");
		sql.append("where f.xh = a.xh ");
		sql.append("order by a.sqsj desc ");
		sql.append(") a ");
		sql.append("where 1 = 1 ");
		
		// ��������

		SearchModel searchModel = model.getSearchModel();
		String[] sqjg = searchModel.getSearch_tj_sqjg();// ������
//		String[] sftj = model.getSearchModel().getSearch_tj_sf();// �Ƿ��ύ
		searchModel.setSearch_tj_sf(null);
		searchModel.setSearch_tj_sqjg(null);
		
		String searchTj = SearchService.getSearchTj(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
//		if (sftj != null && sftj.length > 0) {
//			for (int i = 0; i < sftj.length; i++) {
//				if ("��".equalsIgnoreCase(sftj[i])) {
//					
//					sql = new StringBuilder();
//					sql.append("select rownum r,a.* from (select '' sqid, ");
//					sql.append("a.xh,a.xm,a.nj,a.xydm,v.xymc,a.zydm, ");
//					sql.append("v.zymc,a.bjdm,v.bjmc,'δ�ύ' sqsj,'δ�ύ' sqjg ");;
//					sql.append("from ");
//					sql.append(Base.xsxxb+ " a ");
//					sql.append("left join view_njxyzybj v on a.bjdm = v.bjdm ");
//					sql.append("where not exists(");
//					sql.append("select 1 ");
//					sql.append("from xg_xsxx_grxx_xgsqb b ");
//					sql.append("where sqsj >= (select sqkssj from xg_xsxx_grxx_szb where rownum = 1) ");
//					sql.append("and sqsj <= (select sqjssj from xg_xsxx_grxx_szb where rownum = 1) ");
//					sql.append("and a.xh = b.xh");
//					sql.append(")");
//					//sql.append("order by a.xh ");
//					sql.append(") a ");
//					sql.append("where 1 = 1 ");
//				} else {
					if (sqjg != null && sqjg.length > 0) {

						sql.append("and( ");

						for (int j = 0; j < sqjg.length; j++) {

							if (j != 0) {
								sql.append(" or ");
							}

							if ("δ���".equalsIgnoreCase(sqjg[j])) {
								sql.append("sqjg = 'δ���' ");
							} else if ("���ͨ��".equalsIgnoreCase(sqjg[j])) {
								sql.append("sqjg = '���ͨ��' ");
							} else if ("��˲�ͨ��".equalsIgnoreCase(sqjg[j])) {
								sql.append("sqjg like '%��ͨ��%' ");
							} else {
								sql.append("(sqjg <> 'δ���' and sqjg <> '���ͨ��' and sqjg not like '%��ͨ��%')");
							}
						}

						sql.append(") ");
					}
//				}
//			}
//		}
		
		sql.append(searchTj);
		sql.append(searchTjByUser);


		String[] inputV = SearchService.getTjInput(searchModel);
		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), sql.toString(), inputV,
						new String[] { "sqid", "xh", "xm", "bjmc", "sqsj",
								"sqjg" });

		return list;
	}
	
	/**
	 * ����������Ϣ
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getShrList(XsxxGrxxForm model) {

		DAO dao = DAO.getInstance();

		String sqid = model.getSqid();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.gwid,a.shr, ");
		sql.append("(select b.xm from yhb b where a.shr = b.yhm) xm, ");
		sql.append("(select c.mc from xg_xtwh_spgw c where a.gwid = c.id) gwmc,");
		sql.append("shzt,shsj,shyj ");
		sql.append("from xg_xsxx_grxx_xgshb a ");
		sql.append("where a.sqid = ? ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { sqid }, new String[] { "gwid", "shr", "xm",
						"shzt", "gwmc", "shsj", "shyj" });

		return list;
	}
}
