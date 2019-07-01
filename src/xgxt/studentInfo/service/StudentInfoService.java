
package xgxt.studentInfo.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.search.SearchService;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.User;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.studentInfo.dao.StudentInfoDao;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.model.StudentStatusModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

import common.Globals;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ѧ����Ϣģ��Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-30</p>
 */
public class StudentInfoService {
	StudentInfoDao stuDao = new StudentInfoDao();
	DAO dao = DAO.getInstance();
	
	/**
	 * �춯���޸�ѧ����Ϣ
	 * @param xh
	 * @return flag
	 * @throws Exception 
	 * **/
	public boolean modStudentBaseInfo(String xh,StudentInfoForm form,HttpServletRequest  request){
		boolean flag = true;
		//��ѯѧ����Ϣ��xsxxb���Ƿ��Ѿ����ˣ��о�update��û�оͽ�xsxxb�е������ֶ���view_xsxxb�е���Ϣ����
		try {
			if(!stuDao.isExistInXsxx(xh)){
				//��xsxxb����û�м�¼			
				flag = stuDao.addStuInfoFromView(xh, request);			
			}
			if(flag){
				StudentStatusModel model = new StudentStatusModel();
				BeanUtils.copyProperties(model, form);
				flag = stuDao.modBaseXsxx(xh, model, request);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return flag;
	}
		
	/***************************************************************************
	 * ��ѯѧ���춯��Ϣ
	 * 
	 * @param form
	 * @param cols_en
	 * @return list
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List getStatusDifferentInfo(String userName, StudentInfoForm form,
			String[] cols_en, HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		List<String[]> list = null;
		StuInfoDAO stuDao = new StuInfoDAO();
		String xxdm = StandardOperation.getXxdm();
		String tableName = "view_xjydjbxx";
		
		//��ȡҳ�������ֵ
		String ydhnj = form.getYdhnj();
		String xh = form.getXh();
		String xm = DealString.toGBK(form.getXm());
		String ydhxydm = form.getYdhxydm();
		String ydhzydm = form.getYdhzydm();
		String ydhbjdm = form.getYdhbjdm();
		String ydlbdm = DealString.toGBK(form.getYdlbdm());
		String ydrqks = form.getYdrqks();
		String ydrqjs = form.getYdrqjs();
		String ydjzrqks = form.getYdjzrqks();
		String ydjzrqjs = form.getYdjzrqjs();
		String sffx = form.getSffx();
		
		form.setXm(xm);
		
		StringBuffer sb = new StringBuffer("select rownum r, ydxh,xh,xm,ydlbmc,ydrq,ydqbjmc,ydhxymc,ydhzymc,ydhbjmc from ");
				
		sb.append(tableName);
		sb.append(" a where 1=1");
		sb.append((ydhnj == null || ydhnj.trim().length() < 1) ? "": " and ydqnj='" + ydhnj.trim() + "'");
		sb.append(xh == null || xh.trim().length() < 1 ? "": " and xh like '%" + xh.trim() + "%'");
		sb.append(xm == null || xm.trim().length() < 1 ? "": " and xm like '%" + xm.trim() + "%'");		
		sb.append((ydhxydm == null || ydhxydm.trim().length() < 1) ? "": " and ydqxydm='" + ydhxydm + "'");		
		sb.append((ydhzydm == null || ydhzydm.trim().length() < 1) ? "": " and ydqzydm='" + ydhzydm + "'");
		sb.append((ydhbjdm == null || ydhbjdm.trim().length() < 1) ? "": " and ydqbjdm='" + ydhbjdm + "'");
		sb.append((ydlbdm == null || ydlbdm.trim().length() < 1) ? "": " and ydlbdm='" + ydlbdm+ "'");
		//�춯���ڿ�ʼ
		sb.append( StringUtils.isNotNull(ydrqks) 
				   ? " and to_number(substr(ydrq,0,4)||substr(ydrq,6,2)||substr(ydrq,9,2)) >=" + ydrqks.replaceAll("-", "")
				   : "");
		//�춯���ڽ���
		sb.append( StringUtils.isNotNull(ydrqjs) 
				   ? " and to_number(substr(ydrq,0,4)||substr(ydrq,6,2)||substr(ydrq,9,2)) <=" + ydrqjs.replaceAll("-", "")
				   : "");
		//�춯��ֹ���ڿ�ʼ
		sb.append( StringUtils.isNotNull(ydjzrqks) 
				   ? " and to_number(substr(ydjzrq,0,4)||substr(ydjzrq,6,2)||substr(ydjzrq,9,2)) >=" + ydjzrqks.replaceAll("-", "")
				   : "");
		//�춯��ֹ���ڽ���
		sb.append( StringUtils.isNotNull(ydjzrqjs) 
				   ? " and to_number(substr(ydjzrq,0,4)||substr(ydjzrq,6,2)||substr(ydjzrq,9,2)) <=" + ydjzrqjs.replaceAll("-", "")
				   : "");
		if("��".equalsIgnoreCase(sffx)){
			sb.append(" and exists(select 1 from view_xjydjbxx b where to_number(a.ydxh)<to_number(b.ydxh) and b.ydlbmc='��ѧ' and a.xh=b.xh) and ydlbmc='��ѧ'");
		}
		if("��".equalsIgnoreCase(sffx)){
			sb.append(" and not exists(select 1 from view_xjydjbxx b where to_number(a.ydxh)<to_number(b.ydxh) and b.ydlbmc='��ѧ' and a.xh=b.xh) and ydlbmc='��ѧ'");
		}
		if("true".equalsIgnoreCase(form.getIsFdy())){
			//����Ա��¼
			sb.append(" and exists(select 1 from view_fdybbj b where (a.ydhbjdm=b.bjdm or a.ydqbjdm=b.bjdm) and b.zgh='" + userName + "')");
		}
		
		// �߼���ѯ
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a",
				"ydqxydm", "ydqbjdm");

		String searchTj = SearchService.getSearchTj(form.getSearchModel());
		String[] inputV = SearchService.getTjInput(form.getSearchModel());
		sb.append(searchTj);
		sb.append(searchTjByUser);
		sb.append(" order by ydxh asc");
		
		StringBuffer sbff = new StringBuffer();
		sbff.append("select * from (select * from (");
		sbff.append(sb);
		sbff.append(") a  where r<=");
		sbff.append(form.getPages().getStart()+form.getPages().getPageSize());
		sbff.append(") where r>");
		sbff.append(form.getPages().getStart());
		
		list = dao.rsToVator(sbff.toString(), inputV, cols_en);
		
		//TODO ��ҳ
		String sNum = dao.getOneRs("select count(*) count from("+sb.toString()+") a", inputV, "count");
		form.getPages().setMaxRecord(Integer.parseInt(sNum));
		
		return list;
	}
	
	
	/**
	 * ��ѯ������¼���춯��Ϣ
	 * @param pkValue
	 * @return map
	 * */
	public HashMap<String, String> getOneStatuDiffInfo(String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();		

		map = stuDao.getOneStatuDiffInfo(pkValue);		
		return map;
	}
	
	/**
	 * ��ѯ������¼���춯��Ϣ
	 * @param pkValue
	 * @return map
	 * */
	public boolean delStatuDiffInfo(String xh,String ydxh,HttpServletRequest request){
		boolean flag = false;
		String primaryKey = "";
		String tableName = "bks_xjydxx";
		
		primaryKey = "xh||ydxh";
		try {
			//ɾ���������춯��Ϣ
			flag = StandardOperation.delete(tableName, primaryKey, xh+ydxh, request);
//			if(flag){
//				//ɾ�������춯���ѵ���Ϣ
//				primaryKey = "xh";
				//flag = StandardOperation.delete(tableName, primaryKey, xh, request);
//			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}		
		return flag;
	}
	
	/**
	 * ����ѧ���춯��Ϣ
	 * @param StudentInfoForm form
	 * @return boolean flag
	 * */
	public boolean addStatuStuInfo(StudentInfoForm form,HttpServletRequest request){
		boolean flag = false;		
		String xxdm = StandardOperation.getXxdm();
		StudentStatusModel model = new StudentStatusModel();
		try {
			BeanUtils.copyProperties(model, form);
			//����Ϣ���ӵ�ѧ���춯��Ϣ����
			flag = stuDao.addStatuInfo(model,request);
			if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) || xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
				//�Ϻ����� �������� �춯����
				if(flag){
					flag = stuDao.addStatuDiffInfo(model,request);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return flag;
	}
	
	/**
	 * �޸�ѧ���춯��Ϣ
	 * @param StudentInfoForm form
	 * @return boolean flag
	 * */
	public boolean modStatuStuInfo(StudentInfoForm form,HttpServletRequest request){
		boolean flag = false;
		String xxdm = StandardOperation.getXxdm();
		StudentStatusModel model= new StudentStatusModel();
		try {
			BeanUtils.copyProperties(model, form);
			//�޸�ѧ���춯��Ϣ
			flag = stuDao.modStatuInfo(model,request);
			if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) || xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
				//�Ϻ����� �������� �춯����
				if(flag){
					flag = stuDao.modStatuDiffInfo(model, request);
				}
			}
		}catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}		
		return flag;
	}
	
	/**
	 * ��û��δ��ҵѧ���İ༶ɾ����Ȼ����뵽��ҵ���༶��
	 * @param request
	 * @return boolean
	 * */
	@SuppressWarnings("unchecked")
	public boolean checkGraduateToFinish(HttpServletRequest request){
		boolean flag = true;
		//��ȡ�б�ҵ���༶�İ༶�����������
		List list = stuDao.getGraduateInfo();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				//�жϱ�ҵ��ѧ�����Ƿ�������е�ѧ����������Ǿ�˵��ȫ��ѧ������ҵ�ˣ����ð༶���뵽��ҵ����
				HashMap<String, String> map = (HashMap<String, String>)list.get(i);
				//�༶������
				String sBjdm = map.get("bjdm");
				String sCount = map.get("count");
				int count = stuDao.getCountByBjdm(sBjdm);
				if(sCount!=null && Integer.parseInt(sCount)==count){
					flag = stuDao.addBybjInfo(sBjdm,request);
				}
			}
		}
		return flag;
	}
	
	/**
	 * ��ȡ��ҵ�༶��Ϣ
	 * @param condition
	 * @return List
	 * */
	public List getBjList(String condition,String type){
		List bjList = null;
		bjList = stuDao.getBybjList(condition,type);
		return bjList;
	}
	
	/**
	 * ���ѧ��������Ϣ��ѯsql
	 * @param whereSql
	 * @param city
	 * @return String
	 * */
	public String getSql(String whereSql,String city){
		String sql = "";
		sql = "select a.*,nvl(b.fdyxm,'') fdyxm,nvl(b.fdylxdh,'') fdylxdh from " + 
               "(" + 
               "select a.* ,nvl(b.bzrxm,'') bzrxm,nvl(b.bzrlxdh,'') bzrlxdh from " + 
               "(" + 
               "select a.*,nvl(b.bz,'') bzxm,nvl(b.lxdh,'') bzlxdh from " + 
               "(" + 
               "select a.*,nvl(b.snrs,0) snrs,nvl(nvl(zrs,0)-nvl(b.snrs,0),0) swrs from " + 
               "(" + 
               "select a.*,nvl(b.girlrs,0) girlrs from " + 
               "(" +
               "select a.*,nvl(b.boyrs,0) boyrs from " +  
               "(" + 

               "select b.*,nvl(a.zsrs,0) zsrs,nvl(nvl(b.zrs,0) - nvl(a.zsrs,0),0) wzrs from " +  
               "(" + 
               "select bjdm,nvl(count(*),0) zsrs from (select a.xh,a.bjdm from view_xsjbxx a,view_xszsxx b " + whereSql + " and a.xh=b.xh) group by bjdm " + 
               ") a right join " +  
               "(" + 
               "select max(bjdm)bjdm,max(xydm)xydm,max(zydm)zydm,max(nj)nj,max(bjmc)bjmc,max(xymc)xymc,max(zymc)zymc,nvl(count(*),0) zrs from view_xsjbxx a "+ whereSql +" group by bjdm " + 
               ") b on a.bjdm=b.bjdm " + 

               ") a " +  
               "left join " + 
               "(" + 
               "select bjdm,max(xb)xb,count(xb) boyrs from view_xsjbxx a" + whereSql+ " and xb='��' group by bjdm " + 
               ") b on a.bjdm=b.bjdm " + 
               ") a left join " + 
               "(" + 
               "select bjdm,max(xb)xb,count(xb) girlrs from view_xsjbxx a " + whereSql + " and xb='Ů' group by bjdm " + 
               ") b on a.bjdm=b.bjdm " + 
               ") a left join " + 
               "(" +  
               "select bjdm,count(*) snrs from xsxxb a " + whereSql+ " and jg like '" + city + "%' group by bjdm " + 
               ") b on a.bjdm=b.bjdm " + 
               ") a left join " + 
               "( " + 
               "select b.xm bz,a.lxdh,a.bjdm bjdm from view_xsjbxx a left join view_xsgbxx b on a.xh=b.xh " + whereSql + " and b.drzw like '%�೤%' group by a.bjdm,b.xm,a.lxdh " + 
               ") b on a.bjdm=b.bjdm " + 
               ") a left join " + 
               "(" + 
               "select max(xm) bzrxm,bjdm,max(lxdh) bzrlxdh from view_fdybjdz where zw like '%������%' group by bjdm " + 
               ") b on a.bjdm=b.bjdm " + 
               ") a left join " + 
               "(" + 
               "select max(xm) fdyxm,bjdm,max(lxdh) fdylxdh from view_fdybjdz where zw like '%����Ա%' group by bjdm " + 
               ") b on a.bjdm=b.bjdm";
		return sql;
	}
	
	/**
	 * ��ȡҪ������ֶ�
	 * @return sql 
	 * */
	public String getColumnOfXsxx(){
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String tabName = "xsxxb";
		if (xxdm.equalsIgnoreCase(Globals.XXDM_DBLYDX)) {
			//������ҵ
			sql = "select xh,xm,xb,nj,xydm,xy,zydm,zymc,bjdm,bjmc,csrq,sfzh,sjhm,ah,tc,dzyx,sfdk,jrgcdsj," +
					"jrgqtsj,jtdz,jtyb,jtdh,jtjjqk,jtqkjj,jlcfjl,bz,shgxxm1,shgxgx1,shgxgzdw1,shgxzw1,shgxdwdh1," +
					"shgxsjhm1,shgxxm2,shgxgx2,shgxgzdw2,shgxzw2,shgxdwdh2,shgxsjhm2 from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
			//�Ϻ����̼�����ѧ
			sql = "select xh,xm,xb,nj,xz,xmpy,cym,xjztm,xy,zymc,bjmc,bjdm,zydm,xydm,mz,zzmm,sg,tz,tc,syd,csrq," +
					"pyfs,pycc,rxfs,kslb,sfzh,lxdh,dzyx,jg,sjhm,jrgcdsj,jrgqtsj,byxx from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
			//�人��
			sql = "select xh,xm,xb,nj,xydm,xy,zydm,zymc,bjdm,bjmc,xz,zslb,kh,xjztm,mz," +
				  "zzmm,sfzh,gj,jg,sg,tz,tc,sfjh,ccqj,lxdh,sjhm,dzyx,qqhm from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
			//��������
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh," +
					"jg,lxdh,dzyx,sg,tz,tc,kslb,pycc,jg,xz,zw,xxfx,rxrq,byny,qqhm from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			//��������Ժ
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,xydm,zydm,bjdm,nj,xjztm,xmpy,ksh,cym,zsjj,csrq,dqszj,pyfs,pycc" +
				  ",pyfx,csd,rxfs,kslb,sfzh,dzyx,syd,lxdh,jg,sjhm,xz,zyfx,rxrq,zylb,fxzy,fxzyfx,bxxs,bxlx,xxxs,byny,zsbh," +
				  "zsxlh,xw,xwzsbh,xwzsxlh,bjyjl,xxszd,xzxm,shbj,dybj,thbs,bz from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)){
			//���ս�����ҵѧԺ
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,kh from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_XBMZDX)){
			//���������ѧ
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,sfzx from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
			//�������ϴ�ѧ
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,kh,sfzfx,zjdm from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)){
			//�ϲ���ѧ�Ƽ�ѧԺ
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,xx,jtdzs,jtdzx,ssyq,ssld from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZZDX)){
			//���ݴ�ѧ
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,sfzx from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)){
			//�㶫Ů��ְҵ����ѧԺ
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,rxrq,fdyxm,gkcj,jtjjqk,jtdz,jkzk from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)){
			//�㶫����ѧԺ
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,rxrq,kh,hkszd,hkxz,zyjb,whcd from " + tabName;
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
			//�㽭����ְҵ����ѧԺ
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "sjhm,dzyx,sg,tz,tc,kslb,pycc,xz,kh from " + tabName;
		}else {
			//ͨ��
			sql = "select xh,xm,xb,mz,zzmm,xy,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
				+ "sjhm,dzyx,sg,tz,tc,kslb,pycc,xz from " + tabName;
		}
		return sql ;
	}
	
//	/**
//	 * ��ȡҪ�������ֶ�
//	 * @return String 
//	 * */
//	public String getXsxxToExp(){
//		String xxdm = StandardOperation.getXxdm();
//		String sql = "";
//		String tabName = "view_xsxxb";
//		if (xxdm.equalsIgnoreCase(Globals.XXDM_DBLYDX)) {
//			//������ҵ
//			sql = "select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,csrq,sfzh,sjhm,ah,tc,dzyx,sfdk," +
//					"jrgcdsj,jrgqtsj,jtdz,jtyb,jtdh,jtjjqk,jtqkjj,jlcfjl,bz,shgxxm1,shgxgx1,shgxgzdw1," +
//					"shgxzw1,shgxdwdh1,shgxsjhm1,shgxxm2,shgxgx2,shgxgzdw2,shgxzw2,shgxdwdh2,shgxsjhm2,sfbys from " +
//					tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
//			//�Ϻ����̼�����ѧ
//			sql = "select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,mz,mzmc,xjlb,xmpy,cym,csrq,pyfs,pycc," +
//			      "zzmm,zzmmmc,sfzh,jg,sg,tz,tc,rxfs,kslb,dzyx,syd,lxdh,sjhm,xz,jrgcdsj,jrgqtsj,byxx,ssbh," +
//			      "ssch,zsrq,zsjzrq,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
//			//�人��
//			sql = "select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xz,zslb,kh,xjlb,mz,mzmc," +
//				  "zzmm,zzmmmc,sfzh,gj,jg,sg,tz,tc,sfjh,ccqj,lxdh,sjhm,dzyx,qqhm,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
//			//��������
//			sql = "select xh,xm,xb,mz,zzmm,xyMC,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,zw,xxfx,rxrq,byny,qqhm,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
//			//��������Ժ
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,xydm,zydm,bjdm,nj,xjlb,xmpy,ksh,cym,zsjj,csrq,dqszj,pyfs,pycc" +
//				  ",pyfx,csd,rxfs,kslb,sfzh,dzyx,syd,lxdh,jg,sjhm,xz,zyfx,rxrq,zylb,fxzy,fxzyfx,bxxs,bxlx,xxxs,byny,zsbh," +
//				  "zsxlh,xw,xwzsbh,xwzsxlh,bjyjl,xxszd,xzxm,shbj,dybj,thbs,bz,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)){
//			//���ս�����ҵѧԺ
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,kh,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_XBMZDX)){
//			//���������ѧ
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,sfzx,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
//			//�������ϴ�ѧ
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,kh,sfzfx,zjdm,zjmc,hkszd,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)){
//			//�ϲ���ѧ�Ƽ�ѧԺ
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,xx,jtdzs,jtdzx,ssbh,ssch,ssyq,ssld,qsdh,zsrq,zsjzrq,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZZDX)){
//			//���ݴ�ѧ
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,zzdx,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)){
//			//�㶫Ů��ְҵ����ѧԺ
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,rxrq,fdyxm,gkcj,jtjjqk,jtdz,jkzk,ssbh,ssch,zsrq,zsjzrq,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)){
//			//�㶫����ѧԺ
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,jg,xz,rxrq,kh,hkszd,hkxz,zyjb,whcd,ssbh,ssch,zsrq,zsjzrq,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
//			//�㽭����ְҵ����ѧԺ
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,xz,sfbys,bysj,zkzh,sfcj,yhkh,yhmc,grjl,jlcfjl,bz from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJGSZYJSXY)){
//			//�㽭����רҵ����ѧԺ
//			sql = "select xh,xm,xb,mz,mzmc,zzmm,zzmmmc,yxdm,yxmc,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,xjztm,sfzh,xw,xwmc,syd,sydmc,sfzz,sfsf," +
//					"sfdl,xz,dxhwp,rxrq,bysj,zsbh,zxwyyzdm,zxwyyzmc,wydj,jsjdj,sjhm,qqhm,lxdz,yzbm,shzw,jypx,xmsj,zgzs,jljn," +
//					"sybz1,sybz2,sybz3,xldm,xlmc,zslb,zslbmc,pyfs,pyfsmc,dzyx,lxdh,kh,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)){
//			//�㽭����ְҵ����ѧԺ
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxrq,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,xz,jlcfjl,bz,zkzh,sfcj,grjl,sfbys from " + tabName;
//		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
//			//������
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,xz,sfyby,sfzx,sfbys from " + tabName;
//		}else if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
//			//�й����ʴ�ѧ
//			sql = "select xh,xm,xb,mz,mzmc,zzmm,zzmmmc,xymc,zymc,bjmc,bjdm,zydm," +
//				  "xydm,nj,syd,xjztm,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,dzyx," +
//				  "sg,tz,tc,kslb,pycc,xz,rxqdw,rxrq,rxnj,sfzc,xslb,ksh,sfzx,nfby," +
//				  "byny,xslbmc,xslx,xslxmc,bz,ydlbm,ydlbmc from " + tabName;
//		}else if(Globals.XXDM_LSSFXY.equalsIgnoreCase(xxdm)){
//			//��ɽʦ��ѧԺ
//			sql = "select xh,xm,xb,mz,zzmm,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,xz,jtdz,rxqdw,qqhm,ssbh,sjhm,kh,byny,sfbys from " + tabName;			
//		}else if(Globals.XXDM_NJJS.equalsIgnoreCase(xxdm)){
//			//�Ͼ���ʦ
//			sql = "select xh,xm,xb,mz,mzmc,zzmm,zzmmmc,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,xz,yhdm,yhmc,kh,sfbys,byny,hkshen,hkshi,hkxian,zcsxhm,rxqwhcd,hkxz from " + tabName;
//		}else if(Globals.XXDM_NNZYJSXY.equalsIgnoreCase(xxdm)){
//			//����ְҵ
//			sql = "select xh,xm,xb,mz,mzmc,zzmm,zzmmmc,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,xz,yhdm,yhmc,kh,sfbys,byny,ksh from " + tabName;
//		}else{
//			//ͨ��
//			sql = "select xh,xm,xb,mz,mzmc,zzmm,zzmmmc,xymc,zymc,bjmc,bjdm,zydm,xydm,nj,syd,xjlb,xmpy,cym,csrq,pyfs,rxfs,sfzh,jg,lxdh,"
//				+ "dzyx,sg,tz,tc,kslb,pycc,xz,yhdm,yhmc,kh,sfbys,byny from " + tabName;
//		}
//		return sql ;
//	}
	
	/**
	 * ��ȡҪ����ļ�ͥ��Ϣ�ֶ�
	 * @return String 
	 * */
	public String getColumnOfXsfzxx(){
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String tabName = "xsfzxxb";
		sql = "select xh,jtszd,yb,jjzk,jtcy1_xm,jtcy1_nl,jtcy1_zw,jtcy1_gx,jtcy1_zy,jtcy1_mz,jtcy1_zzmm,"
			+ "jtcy1_gzdz,jtcy1_lxdh1,jtcy1_lxdh2,jtcy2_xm,jtcy2_zw,jtcy2_gx,jtcy2_zy,jtcy2_mz,jtcy2_zzmm,"
			+ "jtcy2_gzdz,jtcy2_nl,jtcy2_lxdh1,jtcy2_lxdh2,jtcy3_xm,jtcy3_zw,jtcy3_gx,jtcy3_zy,jtcy3_mz,jtcy3_zzmm,"
			+ "jtcy3_gzdz,jtcy3_nl,jtcy3_lxdh1,jtcy3_lxdh2 from "
			+ tabName;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
			//�人����ѧ
			sql = "select xh,jtszd,yb,jtcy1_xm,jtcy1_gx,jtcy1_lxdh1,jtcy1_gzdz,jtcy1_jtdz," +
					"jtcy2_xm,jtcy2_gx,jtcy2_lxdh1,jtcy2_gzdz,jtcy2_jtdz,jtcy3_xm,jtcy3_gx," +
					"jtcy3_lxdh1,jtcy3_gzdz,jtcy3_jtdz from " + tabName;
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_DBLYDX)){
			//������ҵ��ѧ
			sql = "select xh,jtszd,yb,jjzk,jtcy1_xm,jtcy1_zw,jtcy1_gx,"
				+ "jtcy1_gzdz,jtcy1_lxdh1,jtcy1_lxdh2,jtcy2_xm,jtcy2_zw,jtcy2_gx,"
				+ "jtcy2_gzdz,jtcy2_lxdh1,jtcy2_lxdh2,jtcy3_xm,jtcy3_zw,jtcy3_gx,"
				+ "jtcy3_gzdz,jtcy3_lxdh1,jtcy3_lxdh2 from "
				+ tabName;
		}
		return sql;
	}
	
	/**
	 * ��ȡ��ͥ��Ϣ�������ֶ�
	 * @return String
	 * */
	public String getXsfzxxToExp(){
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String tabName = "view_xsjtxx";
		
		sql = "select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,jtszd,jtyb,jjzk,jtcy1_xm,jtcy1_nl,jtcy1_zw,jtcy1_gx,jtcy1_zy,jtcy1_mz,jtcy1_zzmm,"
			+ "jtcy1_gzdz,jtcy1_lxdh1,jtcy1_lxdh2,jtcy2_xm,jtcy2_zw,jtcy2_gx,jtcy2_zy,jtcy2_mz,jtcy2_zzmm,"
			+ "jtcy2_gzdz,jtcy2_nl,jtcy2_lxdh1,jtcy2_lxdh2,jtcy3_xm,jtcy3_zw,jtcy3_gx,jtcy3_zy,jtcy3_mz,jtcy3_zzmm,"
			+ "jtcy3_gzdz,jtcy3_nl,jtcy3_lxdh1,jtcy3_lxdh2 from "
			+ tabName;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
			//�人����ѧ 
			sql = "select xh,xm,xb,xymc,zymc,bjmc,nj,jtszd,jtyb,jtcy1_xm,jtcy1_gx,jtcy1_lxdh1,jtcy1_gzdz,jtcy1_jtdz," +
					"jtcy2_xm,jtcy2_gx,jtcy2_lxdh1,jtcy2_gzdz,jtcy2_jtdz,jtcy3_xm,jtcy3_gx,jtcy3_lxdh1,jtcy3_gzdz,jtcy3_jtdz from " + tabName;
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_DBLYDX)){
			//������ҵ��ѧ
			sql = "select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,jtszd,jtyb,jjzk,jtcy1_xm,jtcy1_zw,jtcy1_gx,"
				+ "jtcy1_gzdz,jtcy1_lxdh1,jtcy1_lxdh2,jtcy2_xm,jtcy2_zw,jtcy2_gx,"
				+ "jtcy2_gzdz,jtcy2_lxdh1,jtcy2_lxdh2,jtcy3_xm,jtcy3_zw,jtcy3_gx,"
				+ "jtcy3_gzdz,jtcy3_lxdh1,jtcy3_lxdh2 from "
				+ tabName;
		}
		if(xxdm.equalsIgnoreCase("10649")){
			//������ҵ��ѧ
			sql = "select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,jtszd,jtyb,jjzk,jtcy1_xm,jtcy1_nl,jtcy1_zw,jtcy1_gx,jtcy1_zy,jtcy1_mz,jtcy1_zzmm,"
				+ "jtcy1_gzdz,jtcy1_lxdh1,jtcy1_lxdh2,jtcy2_xm,jtcy2_zw,jtcy2_gx,jtcy2_zy,jtcy2_mz,jtcy2_zzmm,"
				+ "jtcy2_gzdz,jtcy2_nl,jtcy2_lxdh1,jtcy2_lxdh2,jtcy3_xm,jtcy3_zw,jtcy3_gx,jtcy3_zy,jtcy3_mz,jtcy3_zzmm,"
				+ "jtcy3_gzdz,jtcy3_nl,jtcy3_lxdh1,jtcy3_lxdh2,zyshgxxx1,zyshgxxx2,zyshgxxx3 from "
				+ tabName;
		}
		return sql;
	}
	
	/**
	 * �жϼ�¼�Ƿ����
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName,String pk,String pkValue){
		String sql = "select count(*)num from " + tableName + " where " + pk + "=?";
		String result = dao.getOneRs(sql, new String[]{pkValue}, "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		return Integer.parseInt(result)>0 ? true : false;
	}
	
	/**
	 * �޸�ѧ����Ϣ
	 * @param xh
	 * @param col Ҫ�޸ĵ���
	 * @param colValue Ҫ�޸ĵ��е���ֵ
	 * @param request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean modiStuinfo(String xh, String[] col, String[] colValue, HttpServletRequest request) throws Exception{
		boolean flag = false;
		if(stuDao.checkExists("xsxxb", "xh", xh)){//��¼��xsxxb�д���ֱ���޸�
			flag = StandardOperation.update("xsxxb", col, colValue, "xh", xh, request);
		}else{//��¼��xsxxb�в���������Ӻ����޸�
			flag = StandardOperation.update("xsxxb", "insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,rzrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx)(select xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,zsrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx from view_xsxxb where xh='" + xh + "')" , request);
			if(flag){
				flag = StandardOperation.update("xsxxb", col, colValue, "xh", xh, request);
			}
		}		
		return flag;
	}
	
	/**
	 * ��ȡ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getNewStu(String xh) throws Exception {
		return stuDao.getNewStu(xh);
	}
	
	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		return stuDao.getStu(xh);
	}
	
	/**
	 * ��ȡ��ѧ����
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getRxrq(String xh) throws Exception {
		return stuDao.getRxrq(xh);
	}
	
	/**
	 * �Ƿ��ύ�����Ǽ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String isNewXs(String xh) throws Exception {
		return stuDao.getNewXs(xh);
	}
	
	/**
	 * ������������Ǽ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public Boolean jsxxXsdjbSave(String xh, HttpServletRequest request)
			throws Exception {
		return stuDao.jsxxXsdjbSave(xh,request);
	}
	
	/**
	 * ��ȡ�������÷��ص�ҳ��
	 * */
	public String getReturnPage(String xxdm){
		String page = "";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(Globals.XXDM_HZZY, "hzzy_conf");
		page = map.get(xxdm);
		
		page = StringUtils.isNull(page) ? "success"  : page;
		return page;
	}
	
	/**
	 * ��ȡ�����Ǽ��б�
	 * @author luojw
	 */

	public ArrayList<String[]> getNewStuList(String tableName, CommanForm form,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return stuDao.getNewStuList(tableName, form, colList);
	}
	
	/**
	 * ��������ۺ����ʼ���
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getNewStuInfo(String xh) {
		return stuDao.getNewStuInfo(xh);
	}
	
	/**
	 * ���ѧ��������Ϣ
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}
	
	/**
	 * ��������ۺ����ʼ���
	 * 
	 * @author luojw
	 * @throws SQLException 
	 */
	public boolean saveNewStuInfo(CommanForm myForm) throws SQLException {
		return stuDao.saveNewStuInfo(myForm);
	}
	
	/**
	 * ɾ����������Ǽ�
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public boolean delNewStuInfo(String[] key)
			throws Exception {
		return stuDao.delNewStuInfo(key);
	}
	
	@SuppressWarnings("unchecked")
	public void printPayReport(WritableWorkbook wwb,String xydm,String nj){		
		StudentInfoDao dao = new StudentInfoDao();
		List<Object> o = dao.getGdzlData(xydm,nj);
		List<String> top = (List<String>) o.get(0);
		List<String[]> rs = (List<String[]>) o.get(1);
		String xymc = (String) o.get(2);
		
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(18);
		 wcfTytle.setFont(wfTytle);
		 
		 
		 
		 if(Globals.XXDM_HBJJXY.equalsIgnoreCase(Base.xxdm)){
			 ws.mergeCells(0, 2, top.size()+4, 2);
		 }else{
			 
			 ws.mergeCells(0, 2, top.size()+2, 2);
			 
		 }
		 ws.addCell(new Label(0,2,xymc+"(ϵ)"+nj+"���ҵ�������ͽ��嵥" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
//		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,3,"��ҵ�������� "+rs.size()+ " ��",wcfTytle));
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,4,"���",wcfTytle));
		 ws.addCell(new Label(1,4,"ѧ��",wcfTytle));
		 ws.addCell(new Label(2,4,"����",wcfTytle));
		 for(int i=0;i<top.size();i++){
			 ws.addCell(new Label(i+3,4,top.get(i),wcfTytle));
		 }
		
		 if(Globals.XXDM_HBJJXY.equalsIgnoreCase(Base.xxdm)){
			 ws.addCell(new Label(top.size()+3,4,"�Ƿ��ύ",wcfTytle));
			 ws.addCell(new Label(top.size()+4,4,"��У�������",wcfTytle));;
		 }
		 
		 
		 for(int i = 0;i<rs.size();i++){
			 ws.addCell(new Label(0,5+i,String.valueOf(i+1),wcfTytle));
			 for(int g = 0;g<rs.get(i).length;g++){
				 ws.addCell(new Label(g+1,5+i,rs.get(i)[g],wcfTytle));
			 }
		 }
		 
		 
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 if(Globals.XXDM_HBJJXY.equalsIgnoreCase(Base.xxdm)){
			
			 ws.mergeCells(0, rs.size()+5, top.size()+4, rs.size()+5);
		
		 }else{
			 
			 ws.mergeCells(0, rs.size()+5, top.size()+2, rs.size()+5);
			 
		 }
		 
		 ws.addCell(new Label(0,rs.size()+5,"�ͽ���(ǩ��):                               ������(ǩ��):                        ����:												",wcfTytle));
		 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ��ȡҪ�������ֶ�
	 * @param zd
	 * @param tableName
	 * */
	public String getDczd(String zd, String tableName){		
		if(stuDao.checkExists("dcb", "zdssb||xxdm", tableName+Base.xxdm)){
			List<String[]> list = stuDao.getDrzdList(tableName);
			if(list != null && list.size()>0){
				zd="";
				int i=0;
				for(String[] arr : list){
					if(i!=0){
						zd += "," + arr[0];
					}else{
						zd += arr[0];
					}
					i++;
				}
			}
		}
		return zd;
	}
	/**
	 * ��ȡҪ�������ֶ�
	 * @param zd
	 * @param tableName
	 * */
	public String getDczd(String expColumnStr){
		StringBuilder zd = new StringBuilder();
		String[] columns = expColumnStr.split("!!SplitOne!!");
		if(columns != null && columns.length>0){
			for(String str : columns){
				if(StringUtils.isNotNull(str)){
					zd.append(str);
					zd.append(",");
				}
			}
		}
		if(StringUtils.isNotNull(zd.toString())){
			zd.deleteCharAt(zd.length()-1);
		}
		return zd.toString();
	}
	
	/**
	 * ��ȡҳ���ͷ
	 * @param act
	 * @return
	 */
	public String[] getTopTr(String act){
		String[] topTr=new String[]{};
		if("xxjy".equals(act)){
			topTr=new String[]{"ѧ��","����","�꼶","���֤��","�Ա�","֤���Ա�","��������","֤����������","����","֤������"};
		}
		return topTr;
	}
	
	/**
	 * ��ȡ���֤��У����Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getSfzhJyInfoList(CommanForm model) throws Exception{
		return stuDao.getSfzhJyInfoList(model);
	}
	
	/**
	 * �������֤��У����Ϣ
	 * @param updateType
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateSfzhJyInfo(String[] updateType,CommanForm model,HttpServletRequest request) throws Exception{
		return stuDao.updateSfzhJyInfo(updateType, model, request);
	}
	
	/**
	 * ����ѧԺ���Ի���ס�޷Ѳ�ѯ����ȡҳ���ͷ
	 * @return
	 * @author honglin
	 * @date 2012-9-10-22
	 */
	public List<HashMap<String, String>> getZsfTopTr(){
		String[] colList = new String[]{};
		colList = new String[]{"pk","xn","xh","xm","nj","xymc","zymc","bjmc","ldmc","qsh","yjje","sjje","sfjq"};
		String[] colListCN = new String[]{};
		colListCN = new String[]{"","ѧ��","ѧ��","����","�꼶","ѧԺ","רҵ","�༶","¥������","���Һ�","Ӧ�ս��","ʵ�ս��","�Ƿ����"};
		List<HashMap<String, String>> topTr = dao.arrayToList(colList, colListCN);
		return topTr;
	}
	
	/**
	 * ����ѧԺ���Ի���ס�޷Ѳ�ѯ����ѯ�ӽ������͹��������ݣ�
	 * @param model
	 * @return
	 * @throws Exception 
	 * @author honglin
	 * @date 2012-9-10-22
	 */
	public List<String[]>getZsfInfoList(CommanForm model,User user) throws Exception{
		return stuDao.getZsfInfoList(model,user);
	}
}
