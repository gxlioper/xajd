package xsgzgl.gygl.wsjc.fslr;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xsgzgl.comm.BasicModel;
import xsgzgl.gygl.comm.GyglNewInit;
import xsgzgl.gygl.comm.GyglNewService;

import com.zfsoft.utils.StringUtil;
import common.Globals;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-28 ����11:29:22
 * </p>
 */
public class FslrDAO extends CommDAO {

	/**
	 * ������飬������¼����Ϣ�Ĳ�ѯ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFslrCx(FslrForm model,HttpServletRequest request) throws Exception {
		// �߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		// �û�����
		User user = model.getUser();
		String[] colList = null;
		
		if (GyglNewInit.WSJC_XJQS){
			colList = new String[] { "guid", "xn", "xqmc", "jcrc","lxmc", "kssj", "jssj", "qss", "ypfqss", "wpfqss","tjzt" };
		} else {
			colList = new String[] { "guid", "xn", "xqmc", "jcrc", "kssj", "jssj", "qss", "ypfqss", "wpfqss","tjzt" };
			
		}
		if("12688".equals(Base.xxdm)){
			colList = new String[] { "guid", "xn", "xqmc", "jcrc","pfjbmc", "kssj", "jssj", "qss", "ypfqss", "wpfqss","tjzt" };
		}
		// �û�����
		String userType = user.getUserType();
		// ====================��������===================================
		user.setUserStatus(userType);
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		
		//Ȩ�޿���
		String searchTjQx="";
		
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}
		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String query = " where 1 = 1 ";
		String queryOtherOne = " where 1 = 1 ";
		String queryOtherTwo = " ";
		queryOtherOne +=searchTjQx;
		queryOtherTwo +=searchTjQx;
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append("select c.guid,c.xn,c.xq,c.jcrc,c.lxmc,");
		if("12688".equals(Base.xxdm))
			sql.append(" c.pfjbmc, ");
		sql.append("c.kssj,c.jssj,c.bz,c.xqmc,c.qss,c.ypfqss,c.wpfqss,c.sf,c.tjzt,rownum r from (");
		sql.append(" select b.guid,b.xn,b.xq,b.mc jcrc,b.lxmc,");
		if("12688".equals(Base.xxdm))
			sql.append(" b.pfjbmc, ");
		sql.append("b.kssj,b.jssj,b.bz,b.xqmc,b.qss,b.ypfqss, b.qss - b.ypfqss wpfqss,(case when b.tjzt = '1' then 1 else 0 end) tjzt, ");
		sql.append(" (case when ypfqss='0' then '��' else '��' end) sf ");
		sql.append(" from (select a.*,decode(a.jclx,'0','����','1','�ȼ�','2','�Ǽ�') lxmc,  ");
		if("12688".equals(Base.xxdm))
			sql.append("  decode(a.pfjb, 'xj', 'У��', 'yj', 'Ժ��') pfjbmc, ");
		sql.append(" (select xqmc from xqdzb b where a.xq = b.xqdm) xqmc, ");
		sql.append(" (select sum(count(qsh)) ");
		sql.append(" from VIEW_XG_GYGL_NEW_QSXX ");
		sql.append(queryOtherOne);
		sql.append(" group by lddm, qsh) qss, ");
		sql.append(" (select case when sum(count(qsh))>=0 then sum(count(qsh)) else 0 end ");
		sql.append(" from XG_GYGL_NEW_WSJC_QSFSB b ");
		sql.append(" where a.guid = b.guid ");
		sql.append(queryOtherTwo);
		sql.append(" group by lddm, qsh) ypfqss ");
		sql.append(" from XG_GYGL_NEW_WSJC_JCRCB a ");
		sql.append(" where xn='");
		sql.append(Base.currXn);
		sql.append("' and xq='");
		sql.append(Base.currXq);
		sql.append("'");
		sql.append(" )b ");
		sql.append(" order by kssj desc ) c");
		sql.append(query);
		// ====================SQLƴװ end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", inputV, colList, model);
		return list;
	}

	/**
	 * ������飬������¼����Ϣ�Ĳ���
	 * 
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFslrCz(FslrForm model, String pkValue,HttpServletRequest request)throws Exception {
		// �߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		// �û�����
		User user = model.getUser();
		//�û����ݷ�Χ����
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b", "xydm", "bjdm");
		
		String[] colList;
		if(Base.xxdm.equals(Globals.XXDM_TJJDZYJSXY)){
			colList = new String[]{ "ld", "ldmc", "ch", "qsh", "cws", "rzrs", "mrf", "fz" , "kfyj", "pfbz"  };
		}else if(Base.xxdm.equals("33333")){
			colList = new String[]{ "ld", "ldmc", "ch", "qsh", "cws", "rzrs", "fz" , "kfyj", "pfbz"  };
		}else if("11647".equals(Base.xxdm)){
			//�㽭��ý�WԺ���Ի�
			colList = new String[]{ "ld", "ldmc", "ch", "qsh", "cws", "rzrs", "sfbyqs","fz" , "pfbz"  };
		}else{
			colList =  new String[]{ "ld", "ldmc", "ch", "qsh", "cws", "rzrs", "fz" , "pfbz" };
		}
		// �û�����
		String userType = user.getUserType();
		// ====================��������===================================
		user.setUserStatus(userType);
		
		String searchTjQx="";
		
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}
		
		HashMap<String,String> jcrc = getFslrCz2(null,pkValue);
		String jclx = jcrc.get("jclx");
		
		if (StringUtil.isNull(jclx)){
			jclx = GyglNewInit.JFFS;
		}
		
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj+searchTjQx;
		// ====================�������� end================================
		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();
		if ("0".equals(jclx)) {
			if(Base.xxdm.equals("33333")||Base.xxdm.equals(Globals.XXDM_TJJDZYJSXY)){
				sql.append(" select a.ld,a.ldmc,a.ch,a.xymc,a.qsh,a.shnj,a.shxy,a.cws,a.rzrs,a.sfrz,(case when a.fz is null and a.kf is not null then a.kf when a.fz is null and a.kf is null then '0' else a.fz end)");
					if(Base.xxdm.equals(Globals.XXDM_TJJDZYJSXY)){
						sql.append("+80");//������ְҵ����ѧԺҪ��Ĭ�ϼ�80��
					}
				sql.append(" fz, '80' mrf, a.guid,a.lrsj,a.pfbz,(case when a.kfyj is null then a.kfyjstr else a.kfyj end)kfyj,a.lddm,rownum r from (");
			}else if(Base.xxdm.equals("11647")){
				sql.append(" select a.ld,a.ldmc,a.ch,a.xymc,a.qsh,a.shnj,a.shxy,a.cws,a.rzrs,a.sfrz,a.fz,a.guid,a.lrsj,a.pfbz,a.sfbyqs,rownum r from (");
			}else{				
				sql.append(" select a.ld,a.ldmc,a.ch,a.xymc,a.qsh,a.shnj,a.shxy,a.cws,a.rzrs,a.sfrz,a.fz,a.guid,a.lrsj,a.pfbz,rownum r from (");
			}
			sql.append(" select distinct qsxx.lddm||'!@'||qsxx.qsh LD,qsxx.lddm , ");
			sql.append(" (select ldmc from xg_gygl_new_ldxxb c where c.lddm = qsxx.lddm) ldmc,");
			sql.append(" (select ch from xg_gygl_new_qsxxb d where d.lddm = qsxx.lddm and d.qsh = qsxx.qsh) ch,");
			sql.append(" (select bmmc from zxbz_xxbmdm b where b.bmdm = qsxx.XYDM) xymc,(case when qsxx.YZCWS = '0' then '��' else '��' end)sfrz,qsxx.QSH QSH,qsxx.NJ SHNJ,qsxx.xydm SHXY,qsxx.QSCWS CWS,qsxx.YZCWS RZRS,qsfsb.fs fz,qsfsb.guid guid,qsfsb.lrsj lrsj,qsfsb.pfbz pfbz,(case when fs is not null then '��' else '��' end) sf");
			if(Base.xxdm.equals("33333")||Base.xxdm.equals(Globals.XXDM_TJJDZYJSXY)){
				sql.append(",qsfsb.kfyj,kfb.kf,kfb.kfyjstr ");
			}
			//�㽭��ý�WԺ
			if("11647".equals(Base.xxdm)){
				sql.append("  ,nvl(x.sfbyqs,'��') sfbyqs");
			}
			sql.append(" from VIEW_XG_GYGL_NEW_QSXX qsxx ");
			if(Base.xxdm.equals("33333")||Base.xxdm.equals(Globals.XXDM_TJJDZYJSXY)){//�㽭��ҵ��ʦѧԺ���Ի�
				sql.append(" left join (");
				sql.append(" select a.jcrcid,a.lddm,a.qsh,replace(a.kfyjstr,';',',') kfyjstr,b.kf from");
				sql.append(" (select a.* from (select jcrcid,lddm,qsh,kfdh,wm_concat(kfyj) over(partition by qsh order by to_number(kfdh) asc) kfyjstr");
				sql.append(" from ");
				sql.append(" (select jcrcid, lddm, qsh,kf, kfdh,('(' || kfdh || ')' || kf) kfyj from xg_gygl_new_wsjc_qskfb");
				sql.append(" where jcrcid = ?)");
				sql.append(" group by jcrcid, lddm, qsh, kfyj, kfdh) a");
				sql.append(" where exists(select 1 from (select max(to_number(kfdh)) kfdh,jcrcid,lddm,qsh from xg_gygl_new_wsjc_qskfb where jcrcid = ? group by jcrcid,lddm,qsh) b where a.jcrcid = b.jcrcid and a.lddm = b.lddm and a.qsh = b.qsh and a.kfdh = b.kfdh))a");
				sql.append(" left join");
				sql.append(" (select jcrcid,lddm,qsh, (case when instr(to_char(sum(kf)),'.') < 1 then to_char(sum(kf)) || '.00' when instr(to_char(sum(kf)),'.') + 1 = length(to_char(sum(kf))) then to_char(sum(kf)) || '0' else to_char(trunc(sum(kf),2)) end) kf from xg_gygl_new_wsjc_qskfb where jcrcid = ?");
				sql.append(" group by jcrcid, lddm, qsh) b on a.jcrcid = b.jcrcid and a.lddm = b.lddm and a.qsh = b.qsh");
				sql.append(" ) kfb on qsxx.lddm = kfb.lddm and qsxx.qsh = kfb.qsh");
				sql.append(" left join (select LDDM, QSH, FS, guid, lrsj,pfbz,kfyj from XG_GYGL_NEW_WSJC_QSFSB where guid='");
			}else{				
				sql.append(" left join (select LDDM, QSH, FS, guid, lrsj,pfbz from XG_GYGL_NEW_WSJC_QSFSB where guid='");
			}
			sql.append(pkValue);
			sql.append("') qsfsb on qsxx.LDDM = qsfsb.lddm and qsxx.QSH = qsfsb.qsh ");
			if("11647".equals(Base.xxdm)){
				sql.append(" left join (");
				sql.append(" select case when ");
				sql.append(" a.nums > 0 then '��'");
				sql.append(" else '��' end sfbyqs,a.lddm,a.qsh  from ");
				sql.append(" (select t.lddm,t.qsh,count(1) nums");
				sql.append(" from xg_gygl_new_cwxxb t");
				sql.append(" left join view_xsbfxx t1");
				sql.append(" on t.xh = t1.xh");
				sql.append(" left join  xtszb t2");
				sql.append(" on 1= 1");
				sql.append(" where t.xh is not null and substr(t2.dqxn,6,4)-t1.xz-t1.nj = 0");
				sql.append(" group by t.lddm,t.qsh)a");
				sql.append("  )x");
				sql.append(" on qsxx.lddm = x.lddm  and qsxx.qsh = x.qsh");
			}
			//ɽ��������ҽְҵѧԺ
			if("12947".equals(Base.xxdm)){
				sql.append(" left join ");
				sql.append(" (select distinct a.lddm,a.qsh,b.xydm,b.bjdm from xg_gygl_new_cwxxb a left join view_xsbfxx b on a.xh = b.xh where a.xh is not null) b ");
				sql.append(" on qsxx.lddm = b.lddm and qsxx.qsh = b.qsh where 1 = 1 ");
				sql.append(searchTjByUser);
			}
			sql.append(" order by lddm,ch,qsh");
			sql.append(" ) a ");
			sql.append(query);
		} else {
			colList = new String[] { "ld", "ldmc", "ch", "qsh", "cws", "rzrs", "dj", "pfbz"};
			sql.append(" select a.ld,a.ldmc,a.ch,a.xymc,a.qsh,a.shnj,a.shxy,a.cws,a.rzrs,a.sfrz,a.dj,a.guid,a.lrsj,a.pfbz,rownum r from (");
			sql.append(" select distinct qsxx.lddm||'!@'||qsxx.qsh LD,qsxx.lddm , ");
			sql.append(" (select ldmc from xg_gygl_new_ldxxb c where c.lddm = qsxx.lddm) ldmc,");
			sql.append(" (select ch from xg_gygl_new_qsxxb d where d.lddm = qsxx.lddm and d.qsh = qsxx.qsh) ch,");
			sql.append(" (select bmmc from zxbz_xxbmdm b where b.bmdm = qsxx.XYDM) xymc,(case when qsxx.YZCWS = '0' then '��' else '��' end)sfrz,qsxx.QSH QSH,qsxx.NJ SHNJ,qsxx.xydm SHXY,qsxx.QSCWS CWS,qsxx.YZCWS RZRS,qsfsb.DJ dj,qsfsb.guid guid,qsfsb.lrsj lrsj,qsfsb.pfbz pfbz,(case when dj is not null then '��' else '��' end) sf");
			sql.append(" from VIEW_XG_GYGL_NEW_QSXX qsxx ");
			sql.append(" left join (select LDDM, QSH, DJ, guid, lrsj, pfbz from XG_GYGL_NEW_WSJC_QSFSB where guid='");
			sql.append(pkValue);
			sql.append("') qsfsb on qsxx.LDDM = qsfsb.lddm and qsxx.QSH = qsfsb.qsh");
			//ɽ��������ҽְҵѧԺ
			if("12947".equals(Base.xxdm)){
				sql.append(" left join ");
				sql.append(" (select distinct a.lddm,a.qsh,b.xydm,b.bjdm from xg_gygl_new_cwxxb a left join view_xsbfxx b on a.xh = b.xh where a.xh is not null) b ");
				sql.append(" on qsxx.lddm = b.lddm and qsxx.qsh = b.qsh where 1 = 1 ");
				sql.append(searchTjByUser);
			}
			sql.append(" order by lddm,ch,qsh");
			sql.append(" ) a ");
			sql.append(query);
		}
		// ====================SQLƴװ end================================
		ArrayList<String[]> list;
		if(("33333".equals(Base.xxdm)||Base.xxdm.equals(Globals.XXDM_TJJDZYJSXY)) && "0".equals(jclx)){
			 list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", xgxt.utils.String.StringUtils.joinStrArr(new String[]{pkValue,pkValue,pkValue},inputV),colList, model);
		}else{			
			 list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", inputV,colList, model);
		}
		return list;
	}

	/**
	 * ������飬������¼���ճ���Ϣ�Ĳ���
	 * 
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws
	 */
	public HashMap<String, String> getFslrCz2(FslrForm model, String pkValue) {
		DAO dao = DAO.getInstance();
		String sql = "select guid,mc,kssj,jssj,jclx,rownum r from XG_GYGL_NEW_WSJC_JCRCB where guid=?";
		return dao.getMapNotOut(sql.toString(), new String[] { pkValue });
	}

	/**
	 * @param bzStr 
	 * ������飬��������¼����Ϣ�ı���
	 * 
	 * @param model
	 * @param pkValue
	 * @param valArr
	 * @param username
	 * @return
	 * @throws
	 */
	public boolean fslrBc(FslrForm model, String pkValue, String[] valArr, String[] bzStr, String username, String sfsdj) throws SQLException {
		List<String> sqlArr = new ArrayList<String>();
		String jcrq = model.getJcrq();
		String jcbm = model.getJcbm();
		String jcry = model.getJcry();
		String bz = model.getBz();
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		
		String lrrq = df.format(date);
		for (String threeVal : valArr) {
			String[] twoVal = threeVal.split("!!@@");
			String sql = "delete from XG_GYGL_NEW_WSJC_QSFSB where guid= '";
			sql += pkValue;
			sql += "' and lddm= '";
			sql += twoVal[0].split("!@")[0];
			sql += "' and qsh= '";
			sql += twoVal[0].split("!@")[1];
			sql += "'";
			sqlArr.add(sql);
		}
		DAO dao = DAO.getInstance();
		boolean flag = dao.checkBatch(dao.runBatch(sqlArr != null ? sqlArr.toArray(new String[0]) : new String[] {}));

		if (flag) {
			sqlArr = new ArrayList<String>();
			for (int i=0;i<valArr.length;i++) {
				String[] twoStr=bzStr[i].split("!!@@");
				String[] twoVal = valArr[i].split("!!@@");
				String sql = "insert into XG_GYGL_NEW_WSJC_QSFSB (GUID,LDDM,QSH,JCRQ,JCBM,JCRY,FS,DJ,BZ,LRR,LRSJ,pfbz";
				//����о���ó��ѧУ
				if(Globals.XXDM_TJJM.equals(Base.xxdm)){
					sql+=",pfbzname";
				}
				sql += ") values ('";
				sql += pkValue;
				sql += "','";
				sql += twoVal[0].split("!@")[0];
				sql += "','";
				sql += twoVal[0].split("!@")[1];
				sql += "','";
				sql += jcrq;
				sql += "','";
				sql += jcbm;
				sql += "','";
				sql += jcry;
				sql += "','";
				if (!("0").equals(sfsdj)) {
					sql += "";
					sql += "','";
					if(twoVal.length>=2){
						sql += twoVal[1];
					}else{
						sql += "";
					}
				} else {
					if(twoVal.length>=2){
						sql += twoVal[1];
					}else{
						sql += "";
					}
					sql += "','";
					sql += "";
				}
				sql += "','";
				sql += bz;
				sql += "','";
				sql += username;
				sql += "','";
				sql += lrrq;
				sql += "','";
				if(twoStr.length>=2){
					sql += twoStr[1];
				}else{
					sql += "";
				}
				//����о���ó��ѧУ
				if(Globals.XXDM_TJJM.equals(Base.xxdm)){
					sql += "','";
					String pfbzmc = getFzbzmc(twoStr[1].split("��"));
					sql += pfbzmc;
				}
				sql += "')";
				if(!StringUtils.isBlank(twoVal[1])){ //�������û�У�ֻɾ����
					sqlArr.add(sql);
				}
			}
			flag = dao.checkBatch(dao.runBatch(sqlArr != null ? sqlArr.toArray(new String[0]) : new String[] {}));
		}
		return flag;
	}

	public List<String[]> getFslrDj() throws IllegalArgumentException,SecurityException, IllegalAccessException,InvocationTargetException, NoSuchMethodException {
		BasicModel model = new BasicModel();
		String sql = " select DJ,rownum r from XG_GYGL_NEW_WSJC_DJFSB where lx='0'";
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", new String[] {},new String[] { "DJ" }, model);
		return list;
	}
	
	public List<String[]> getFslrXj() throws IllegalArgumentException,SecurityException, IllegalAccessException,InvocationTargetException, NoSuchMethodException {
		BasicModel model = new BasicModel();
		String sql = " select DJ,rownum r from XG_GYGL_NEW_WSJC_DJFSB where lx='1'";
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", new String[] {},new String[] { "DJ" }, model);
		return list;
	}

	public String importData(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO �Զ����ɷ������
		DAO dao = DAO.getInstance();
		String pkValue=request.getParameter("pkValue");
		
		HashMap<String,String> jcrc = getFslrCz2(null,pkValue);
		String jclx = jcrc.get("jclx");
		
		if (StringUtil.isNull(jclx)){
			jclx = GyglNewInit.JFFS;
		}
		
		
		String userName=(String) request.getSession().getAttribute("userName");
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String lrrq = df.format(date);
		
		int excelXsCount=0;//excel�ļ�ѧ���ļ�¼��
		boolean b=false;
		try {
			//���Ƚ���ʱ���е��������
			b=dao.runUpdate("delete from XG_GYGL_NEW_WSJC_QSFSTMPB", new String[]{});
			if(!b){
				return "��ʱ������ɾ��ʧ�ܣ������µ��룡";
			}
			String path=request.getAttribute("filepath").toString();
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);			
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			String[] row;
			ArrayList<String> excelData_sql = new ArrayList<String>();//���ڱ����excel��õ�����
			for(int rownum=1;rownum<sourceRowCount;rownum++){//ÿ����¼
				//���Ҫ�����¼   start
				row = ExcelMethods.getOneRow(sourceSheet,rownum,8);
				if("0".equals(jclx)){
					excelData_sql.add("insert into XG_GYGL_NEW_WSJC_QSFSTMPB(lddm,qsh,jcrq,jcbm,jcry,fs,bz,pfbz) " +
							"values( trim('"+row[0]+"'),trim('"+row[1]+"'),trim('"+row[2]+"'),trim('"+row[3]+"'),trim('"+row[4]+"'),trim('"+row[5]+"'),trim('"+row[6]+"'),trim('"+row[7]+"') )");
					//���Ҫ�����¼   end
				}else{
					excelData_sql.add("insert into XG_GYGL_NEW_WSJC_QSFSTMPB(lddm,qsh,jcrq,jcbm,jcry,dj,bz,pfbz) " +
							"values( trim('"+row[0]+"'),trim('"+row[1]+"'),trim('"+row[2]+"'),trim('"+row[3]+"'),trim('"+row[4]+"'),trim('"+row[5]+"'),trim('"+row[6]+"'),trim('"+row[7]+"') )");
					//���Ҫ�����¼   end
				}
			}
			CommDAO commdao=new CommDAO();
			excelXsCount=excelData_sql.size();
			if(excelXsCount>0){
				b=commdao.saveArrDate(excelData_sql.toArray(new String[]{}));
				if(!b){
					return "�����ֶι������޷��������ݿ⣡";
				}
			}else{
				return "�ļ���û�����ݿɵ��룡";
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			return "���ݵ�����ʱ��ʱ�����쳣�������������ֶι������£�";

		}
		
		try {
			//������Ч���ж�
			//1����ǵ��������У�¥�����룬���ҺŲ�����
			String sql="update XG_GYGL_NEW_WSJC_QSFSTMPB a set mark='0',info='�������ݲ�����' " +
			"where not exists (select 1 from xg_gygl_new_qsxxb b where a.lddm=b.lddm and a.qsh=b.qsh)";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "��������ϵͳ�޶�Ӧ��Ϣ���ʧ�ܣ�";
			}
			//2�����������Ѵ���
			//20180126��markΪ1��ʾ���潫������и��²���
			sql="update XG_GYGL_NEW_WSJC_QSFSTMPB a set mark='1',info='�������������Ѵ���' "+
			"where exists(select 1 from XG_GYGL_NEW_WSJC_QSFSB b where a.lddm=b.lddm and a.qsh=b.qsh and b.guid=?) and mark is null";
			b=dao.runUpdate(sql, new String[]{pkValue});
			if(!b){
				return "���������Ѵ��ڱ��ʧ�ܣ�";
			}
			//3�������ֶ�Ϊ���ж�
			sql="update XG_GYGL_NEW_WSJC_QSFSTMPB a set mark='0',info='�����ֶ�Ϊ��' "+
			"where (lddm is null or qsh is null or jcrq is null";
//	2018.01.25ǰ��		"where (lddm is null or qsh is null or jcrq is null or jcbm is null or jcry is null";
			if("0".equalsIgnoreCase(jclx)){
				sql+=" or fs is null)";
			}else{
				sql+=" or dj is null)";
			}
			sql+=" and  mark is null";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "�������ݱ����ֶ�Ϊ�ձ��ʧ�ܣ�";
			}
			//4��ʱ���ʽ�ж�
			sql="update XG_GYGL_NEW_WSJC_QSFSTMPB a set mark='0',info='���ڸ�ʽ����ȷ' "+
			"where (ltrim(jcrq,'0123456789') is not null or length(jcrq)<>8) and  mark is null";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "�����������ڸ�ʽ�жϱ��ʧ�ܣ�";
			}
			//5����������Ƿ��ڼ���ճ̷�Χ��
			sql="update XG_GYGL_NEW_WSJC_QSFSTMPB a set mark='0',info='������ڲ��ڼ���ճ̷�Χ��' "+
			"where not exists(select 1 from xg_gygl_new_wsjc_jcrcb b where b.guid=? and b.kssj<=a.jcrq and b.jssj>=a.jcrq) and mark is null";
			b=dao.runUpdate(sql, new String[]{pkValue});
			if(!b){
				return "�������ݼ�������Ƿ��ڼ���ճ̷�Χ�ڱ��ʧ�ܣ�";
			}
			
			if("0".equalsIgnoreCase(jclx)){
				sql="update XG_GYGL_NEW_WSJC_QSFSTMPB a set mark='0',info='�����ֶθ�ʽ����ȷ' "+
				"where ltrim(fs,'.0123456789') is not null and mark is null";
				b=dao.runUpdate(sql, new String[]{});
				if(!b){
					return "�������ݷ����ֶθ�ʽ���ʧ�ܣ�";
				}
			}else if("1".equalsIgnoreCase(jclx)){
				//6.�ȼ��ֶθ�ʽ����ȷ
				sql="update XG_GYGL_NEW_WSJC_QSFSTMPB a set mark='0',info='�ȼ��ֶθ�ʽ����ȷ' "+
				"where not exists(select 1 from XG_GYGL_NEW_WSJC_DJFSB b where a.dj=b.dj and b.lx='0') and mark is null";
				b=dao.runUpdate(sql, new String[]{});
				if(!b){
					return "�������ݵȼ��ֶθ�ʽ���ʧ�ܣ�";
				}
			}else {
				//6.���ֶθ�ʽ����ȷ
				sql="update XG_GYGL_NEW_WSJC_QSFSTMPB a set mark='0',info='�Ǽ��ֶθ�ʽ����ȷ' "+
				"where not exists(select 1 from XG_GYGL_NEW_WSJC_DJFSB b where a.dj=b.dj and b.lx='1') and mark is null";
				b=dao.runUpdate(sql, new String[]{});
				if(!b){
					return "���������Ǽ��ֶθ�ʽ���ʧ�ܣ�";
				}
			}

			//�����ظ����ж�
			//1����ǵ��������У�¥�����룬���Һ��ظ�
			sql="update XG_GYGL_NEW_WSJC_QSFSTMPB a set mark='0',info='���������ظ�' " +
			"where exists (select 1 from (select lddm,qsh from XG_GYGL_NEW_WSJC_QSFSTMPB t group by lddm,qsh having(count(*)) >1) b where a.lddm=b.lddm and a.qsh=b.qsh)";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "���������ظ����ʧ�ܣ�";
			}
			//���ȸ���ѧ�ź���סʱ��
//			HttpSession session = request.getSession();
//			String rzczr=session.getAttribute("userName").toString();//���ò�����
			
			sql="insert into XG_GYGL_NEW_WSJC_QSFSB (guid,lddm,qsh,jcrq,jcbm,jcry,fs,dj,bz,lrr,lrsj,pfbz)"+
			"(select '"+pkValue+"' guid,lddm,qsh,jcrq,jcbm,jcry,fs,dj,bz,'"+userName+"' lrr,'"+lrrq+"' lrsj,pfbz from XG_GYGL_NEW_WSJC_QSFSTMPB where mark is null)";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "�����ֲ���ʧ�ܣ�";
			}

			//���²���
			sql="update XG_GYGL_NEW_WSJC_QSFSB a set (jcrq,jcbm,jcry,fs,dj,bz,lrr,lrsj,pfbz) = "+
					"(select jcrq,jcbm,jcry,fs,dj,bz,'"+userName+"' lrr,'"+lrrq+"' lrsj,pfbz from XG_GYGL_NEW_WSJC_QSFSTMPB b where mark " +
					" = '1' and a.lddm = b.lddm and a.qsh = b.qsh) where a.guid = ? and " +
					"exists(SELECT 1 FROM XG_GYGL_NEW_WSJC_QSFSTMPB b WHERE mark = '1' " +
					"AND b.LDDM = a.LDDM AND b.QSH = a.QSH)";
			b=dao.runUpdate(sql, new String[]{pkValue});
			if(!b){
				return "�����ָ���ʧ�ܣ�";
			}
		} catch (Exception e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
			return "���ݸ��½׶η����쳣��";
		}
		
		List<String[]> xsList=new ArrayList<String[]>();
		try {
			String sql="select * from XG_GYGL_NEW_WSJC_QSFSTMPB where mark='0'";
			String[] outputValue=null;
			String[] colListCN = null;
			if("0".equals(jclx)){
			outputValue=new String[]{"lddm","qsh","jcrq","jcbm","jcry","fs","bz","pfbz","info"};
			colListCN=new String[]{"¥������","���Һ�","�������","��鲿��","�����Ա","����","��ע","���ֱ�ע","����ʧ��ԭ��"};
			}
			else if("1".equals(jclx)){
				outputValue=new String[]{"lddm","qsh","jcrq","jcbm","jcry","dj","bz","pfbz","info"};
				colListCN=new String[]{"¥������","���Һ�","�������","��鲿��","�����Ա","�ȼ�","��ע","���ֱ�ע","����ʧ��ԭ��"};	
			}else {
				outputValue=new String[]{"lddm","qsh","jcrq","jcbm","jcry","dj","bz","pfbz","info"};
				colListCN=new String[]{"¥������","���Һ�","�������","��鲿��","�����Ա","�Ǽ�","��ע","���ֱ�ע","����ʧ��ԭ��"};	
			}
			xsList=dao.rsToVator(sql, new String[]{}, outputValue);

			if(xsList!=null&&xsList.size()>0){
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				Excel2Oracle.exportData(xsList,outputValue,colListCN, response.getOutputStream());
				request.setAttribute("sfdcExcel", "yes");
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			response.reset();
			response.setContentType("text/html");
			return "���ݵ���ɹ����������Ϲ淶��"+((xsList==null||xsList.size()==0)?"":(xsList.size()+"��"))+"�����ڷ���ʱ�������쳣��";
		}
		
		return "����ɹ���";
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:�㽭��ý���Ի��޸��Ƿ��ҵ�����ֶ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-6-30 ����03:11:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkvalue
	 * @param byqs
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateByqsForZjCm(List<String[]> params) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update XG_GYGL_NEW_WSJC_QSFSB set byqs = ? where guid = ? and lddm || '!@' || qsh = ?");
		return DAO.getInstance().runBatchBoolean(sql.toString(), params);
	}
	
	/**
	 * 
	 * @����: ��ȡ��ֵ��ע
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-10-16 ����03:38:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getFzBz() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select kfdm,kfmc,kffz from xg_gygl_wsflrbz");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����:��ȡ���ֱ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-7 ����04:01:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getFzbzmc(String[] kfdms){
		StringBuilder sql = new StringBuilder();
		sql.append(" select replace(wm_concat(kfmc),';',',') kfmc from XG_GYGL_WSFLRBZ where kfdm in( ");
		for (int i = 0; i < kfdms.length; i++) {
			sql.append("?");
			if(i != kfdms.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return DAO.getInstance().getOneRs(sql.toString(), kfdms, "kfmc");
	}
	
	/**
	 * @throws Exception 
	 * @param bzStr 
	 * ������飬��������¼����Ϣ�ı���
	 * 
	 * @param model
	 * @param pkValue
	 * @param valArr
	 * @param username
	 * @return
	 * @throws
	 */
	public boolean saveXsFslr(FslrForm model, String pkValue, String[] valArr, String[] bzStr, String username, String sfsdj, String rcmc) throws Exception {
		List<String> sqlArr = new ArrayList<String>();
		String jcrq = model.getJcrq();
		String bz = model.getBz();
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		
		String lrrq = df.format(date);
		for (String threeVal : valArr) {
			String[] twoVal = threeVal.split("!!@@");
			String sql = "delete from xg_gygl_new_wsjc_12688_xsfsb  where RCID= '";
			sql += pkValue;
			sql += "' and lddm= '";
			sql += twoVal[0].split("!@")[0];
			sql += "' and qsh= '";
			sql += twoVal[0].split("!@")[1];
			sql += "'";
			sqlArr.add(sql);
		}
		DAO dao = DAO.getInstance();
		boolean flag = dao.checkBatch(dao.runBatch(sqlArr != null ? sqlArr.toArray(new String[0]) : new String[] {}));

		if (flag == true) {
			sqlArr = new ArrayList<String>();
			for (int i=0;i<valArr.length;i++) {
				String[] twoVal = valArr[i].split("!!@@");
				String lddm = twoVal[0].split("!@")[0];
				String qsh = twoVal[0].split("!@")[1];
				String fs = "";
				if(twoVal.length>=2){
					String temp = twoVal[1];//����
					if(!StringUtil.isNull(temp)){
						double d = Double.parseDouble(temp)/2.0;
						fs = new DecimalFormat("0.00").format(d); 
					}
				}
				String[] xhArr = getXhs(lddm, qsh);
				for (String xh : xhArr) {
					String sql = "insert into xg_gygl_new_wsjc_12688_xsfsb  (RCID,LDDM,QSH,XH,JCRQ,FS,BZ,LRR,LRSJ,RCMC";
					sql += ") values ('";
					sql += pkValue;
					sql += "','";
					sql += lddm;//lddm¥������
					sql += "','";
					sql += qsh;//qsh���Һ�
					sql += "','";
					sql += xh;
					sql += "','";
					sql += jcrq;
					sql += "','";
					sql += fs;
					sql += "','";
					sql += bz;
					sql += "','";
					sql += username;
					sql += "','";
					sql += lrrq;
					sql += "','";
					sql += rcmc;
					sql += "')";
					if(!StringUtils.isBlank(twoVal[1])){ //�������û�У�ֻɾ����
						sqlArr.add(sql);
					}
				}
				
			}
			flag = dao.checkBatch(dao.runBatch(sqlArr != null ? sqlArr.toArray(new String[0]) : new String[] {}));
		}
		return flag;
	}
	public String[] getXhs(String lddm,String qsh) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select xh from XG_VIEW_GYGL_NEW_XSZSGL where lddm=? and qsh=? ";
		return dao.getRs(sql, new String[]{lddm,qsh},"xh");
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-9 ����04:31:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkValue
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getRcmcById(String id) {
		DAO dao = DAO.getInstance();
		String sql = "select mc from XG_GYGL_NEW_WSJC_JCRCB where guid=? ";
		return dao.getOneRs(sql, new String[]{id},"mc");
	}
}