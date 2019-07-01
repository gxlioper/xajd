package xgxt.studentInfo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.DAO.GetDropDownList;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.search.SearchService;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.SaveForm;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.studentInfo.dao.StudentInfoDao;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.model.XsxxpzModel;
import xgxt.studentInfo.zgdzdx.XsxxZgdzdxDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import common.Globals;
import common.GlobalsVariable;

public class XsxxglService {
	XsxxglDAO dao = new XsxxglDAO();  
	StuInfoDAO stuDao = new StuInfoDAO();
	String xxdm = StandardOperation.getXxdm();
	
	public String getTableName(){
		HashMap<String, String> map = new HashMap<String, String>();
		return map.get(xxdm);
	}
	
	public String getXqmc(String xqmc){
		return dao.selectXqmc(xqmc);
	}
	
	/**
	 * ��ȡҪ��������
	 * @param tableName
	 * @return List
	 * */	  
	public List<HashMap<String, String>> getColumn2Export(String tableName){		 
//		StudentInfoService service = new StudentInfoService();
		GetDropDownList util = new GetDropDownList();
//		String sql = service.getXsxxToExp();		
//		return dao.getColumn2ExpList(sql,tableName);
		return util.getTableColForExp("view_xsxxb");
	}
	
	/**
	 * ��ϲ�ѯ���ֶεĲ�ѯ���
	 * @param tableName
	 * @param key
	 * @param form
	 * @return String
	 * */
	public String getColumnSql2Exp(String tableName,String[]key,CommanForm form){
		String sql = "select ";
		StringBuffer column = new StringBuffer();//��ѯ�ֶ�		
		
		for(int i=0;i<key.length; i++){//���Ҫ�����ľ����ֶ�
			if(key[i].toLowerCase().equalsIgnoreCase("sfyby")){
				column.append(" decode(sfyby,'','��',sfyby) sfyby");
			}else if(key[i].toLowerCase().equalsIgnoreCase("sfzx")){
				column.append(" decode(sfzx,'','��У',sfzx) sfzx");
			}else{
				column.append(key[i]);
			}
			column.append(",");
		}
		column.deleteCharAt(column.length()-1);//ȥ������","��	
		sql += column;
		sql += " from " + tableName + " a ";

		return sql;
	}
	
	/**
	 * ѧ����Ϣ����ҳ��������ȡ
	 * @param model
	 * @param isFdy
	 * @return String
	 * */
	public String getWhereSql(CommanForm model,String isFdy){
		String xxdm = StandardOperation.getXxdm();
		String userName = model.getUserName();
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xh = model.getXh();
		String xm = DealString.toGBK(model.getXm());
		String xb = DealString.toGBK(model.getXb());
		String mzdm = DealString.toGBK(model.getMzdm());
		String zzmmdm = DealString.toGBK(model.getZzmmdm());
		String jtcyxm = DealString.toGBK(model.getJtcyxm());
		String ssbh = DealString.toGBK(model.getSsbh());
		String sfzh = model.getSfzh();
		String xjztm = DealString.toGBK(model.getXjzt());
		String xz = model.getXz();
		String jg = DealString.toGBK(model.getJg());
		String syd = DealString.toGBK(model.getSyd());
		String rxsj = model.getRxsj();
		String byny = model.getByny();
		String zw = DealString.toGBK(model.getZw());
		String pycc = DealString.toGBK(model.getPycc());
		String sfzx = DealString.toGBK(model.getSfzx());
		String hkszd = DealString.toGBK(model.getHkszd());
		String sfyby = DealString.toGBK(model.getSfyby());
		String fdyQx = model.getFdyQx();
		
		//String sfzx = DealString.toGBK(model.getSfzx());
		
		
		if(fdyQx != null && fdyQx.equalsIgnoreCase("true")){
			whereSql.append(" and exists(select 1 from view_fdybbj f where a.bjdm=f.bjdm and f.zgh='"+userName+"')");
		}
		
		//ͨ������start
		if(nj!=null && !nj.equals("")){//�꼶
			whereSql.append(" and nj='");
			whereSql.append(nj);
			whereSql.append("'");
		}
		if(xydm!=null && !xydm.equals("")){//ѧԺ����
			whereSql.append(" and xydm='");
			whereSql.append(xydm);
			whereSql.append("'");
		}
		if(zydm!=null && !zydm.equals("")){//רҵ����
			whereSql.append(" and zydm='");
			whereSql.append(zydm);
			whereSql.append("'");
		}
		if(bjdm!=null && !bjdm.equals("")){//�༶����
			whereSql.append(" and bjdm='");
			whereSql.append(bjdm);
			whereSql.append("'");
		}
		if(xh!=null && !xh.equals("")){//ѧ��
			whereSql.append(" and xh like '%");
			whereSql.append(xh);
			whereSql.append("%'");
		}
		if(xm!=null && !xm.equals("")){//����
			whereSql.append(" and xm like '%");
			whereSql.append(xm);
			whereSql.append("%' ");
		}
		if(xb!=null && !xb.equals("")){//�Ա�
			whereSql.append(" and xb ='");
			whereSql.append(xb);
			whereSql.append("' ");
		}
		if(mzdm!=null && !mzdm.equals("")){//�������
			whereSql.append(" and mzdm ='");
			whereSql.append(mzdm);
			whereSql.append("' ");
		}
		if(zzmmdm!=null && !zzmmdm.equals("")){//������ò����
			whereSql.append(" and zzmmdm ='");
			whereSql.append(zzmmdm);
			whereSql.append("' ");
		}
		if(jtcyxm!=null && !jtcyxm.equals("")){//��ͥ��Ա����
			whereSql.append(" and (jtcy1_xm ='");
			whereSql.append(jtcyxm);
			whereSql.append("' ");
			whereSql.append(" or jtcy2_xm ='");
			whereSql.append(jtcyxm);
			whereSql.append("' ");
			whereSql.append(" or jtcy3_xm ='");
			whereSql.append(jtcyxm);
			whereSql.append("') ");
		}
		if(ssbh!=null && !ssbh.equals("")){//������
			whereSql.append(" and ssbh like '%");
			whereSql.append(ssbh);
			whereSql.append("%' ");
		}
		if(hkszd!=null && !hkszd.equals("")){//�������ڵ�
			whereSql.append(" and hkszd ='");
			whereSql.append(hkszd);
			whereSql.append("' ");
		}
		if(sfzh!=null && !sfzh.equals("")){//���֤��
			whereSql.append(" and sfzh like'%");
			whereSql.append(sfzh);
			whereSql.append("%' ");
		}		
		if(xz!=null && !xz.equals("")){//ѧ��
			whereSql.append(" and xz='");
			whereSql.append(xz);
			whereSql.append("' ");
		}
		if(jg!=null && !jg.equals("")){//����
			whereSql.append(" and jg like'%");
			whereSql.append(jg);
			whereSql.append("%' ");
		}
		if(syd!=null && !syd.equals("")){//��Դ�� ��������
			whereSql.append(" and syd ='");
			whereSql.append(syd);
			whereSql.append("' ");
		}
		if(rxsj!=null && !rxsj.equals("")){//��ѧʱ��
			whereSql.append(" and rxrq ='");
			whereSql.append(rxsj);
			whereSql.append("' ");
		}
		if(byny!=null && !byny.equals("")){//��ҵ����
			whereSql.append(" and byny ='");
			whereSql.append(byny);
			whereSql.append("' ");
		}
		if(zw!=null && !zw.equals("")){//ְ�� ��������
			whereSql.append(" and zw ='");
			whereSql.append(zw);
			whereSql.append("' ");
		}
		if(pycc!=null && !pycc.equals("")){//�������
			whereSql.append(" and pycc='");
			whereSql.append(pycc);
			whereSql.append("' ");
		}		
		if(xjztm!=null && !xjztm.equals("")){//ѧ��״̬�� 
			if(xxdm.equals(Globals.XXDM_XBMZDX)){//���������ѧ
				if(xjztm.equals("��ѧ��")){
					whereSql.append(" and (xjlb='");
					whereSql.append(xjztm);
					whereSql.append("' or xjlb='��' or xjlb is null)");
				}else if(xjztm.equals("��ѧ��")){
					whereSql.append(" and (xjlb='");
					whereSql.append(xjztm);
					whereSql.append("' or xjlb='��')");
				}					
			}else{//����ѧУ
				whereSql.append(" and xjlb='");
				whereSql.append(xjztm);
				whereSql.append("' ");
			}
		}
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){//������
			whereSql.append("and (xjztm<>'��ѧ��' and xjztm is not null)");
		}
		if(!xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){//�Ǻ���ְҵ����ѧԺ
			if(sfyby!=null && !sfyby.equals("")){//�Ƿ��ѱ�ҵ
				if(sfyby.equalsIgnoreCase("��")){
					whereSql.append(" and (sfyby ='��' or sfyby is null)");
				}else{
					whereSql.append(" and sfyby='��'");
				}
			}
			if(sfzx!=null && !sfzx.equals("")){//�Ƿ���У
				if(sfzx.equalsIgnoreCase("��У")){
					whereSql.append(" and (sfzx ='��У' or sfzx is null)");
				}else{
					whereSql.append(" and sfzx='����У'");
				}
			}
		}
		//ͨ������end
		
		//ѧУ�������� start
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){//����Ժ
			whereSql.append(" and (sfyby<>'��' or sfyby is null )");//δ��ҵ��ѧ��
			whereSql.append(" and not exists(select 1 from view_xjydjbxx b where b.xh=view_xsxxb.xh and ydxh=(select max(ydxh) from view_xjydjbxx b where b.xh=view_xsxxb.xh) and " +
					"exists(select 1 from dm_ydlb c where c.sfzx='����У' and b.ydlbmc=c.ydlbmc))");//�춯����У��ѧ��			
		}		
		
		if(xxdm.equals(Globals.XXDM_XBMZDX)){//���������ѧ
			if(sfzx!=null && sfzx.equalsIgnoreCase("1")){	
				//��Уѧ�� 
				whereSql.append(" and exists(select xh from (select xh,xm,decode(nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx),null,'��У',nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx)) sfzx from view_xsxxb f) f where f.sfzx='��У' and view_xsxxb.xh=f.xh)");
			}
			if(sfzx!=null && sfzx.equalsIgnoreCase("0")){
				//����У
				whereSql.append(" and exists(select xh from (select xh,xm,decode(nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx),null,'��У',nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx)) sfzx from view_xsxxb f) f where f.sfzx='����У' and view_xsxxb.xh=f.xh)");
			}	
		}
		
		if(xxdm.equals(Globals.XXDM_WHLGDX)){//�人����ѧ
			if(isFdy!=null && isFdy.equals("true")){//����Ա
				whereSql.append("and exists(select 1 from view_fdybbj b where b.bjdm=view_xsxxb.bjdm and b.zgh='" + userName + "')");
			}			
		}
		//ѧУ�������� end
		//��ϲ�ѯ�������end
		return whereSql.toString();
	}
	
	/**
	 * ����ѧ����Ϣ��Ϣ������Ϣ
	 * @param title
	 * @param content
	 * @param request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean saveStuInfoPub(String title, String content, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String tableName = "xsxx_xxfbb";
		flag = StandardOperation.delete(tableName, "title", title, request);
		if(flag){
			flag = StandardOperation.insert(tableName, new String[]{"title", "content"}, new String[]{title,content},request);
		}		
		return flag;
	}
	
	/**
	 * ��ʼ����ҵ���
	 * @param condition
	 * @param request
	 * @return boolean
	 * */
	public boolean saveByny(CommanForm model, HttpServletRequest request) throws Exception{
		String sql = "update xsxxb set byny = (to_number(nj) + to_number(xz))||'-" + model.getYf() + "-" + model.getRq() + "' where 1=1 ";
		StringBuffer sb = new StringBuffer();
		
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xz = model.getXz();
		
		sb.append(nj != null && !"".equalsIgnoreCase(nj) ? " and nj='" + nj + "'" : "");
		sb.append(xydm != null && !"".equalsIgnoreCase(xydm) ? " and xydm='" + xydm + "'" : "");
		sb.append(zydm != null && !"".equalsIgnoreCase(zydm) ? " and zydm='" + zydm + "'" : "");
		sb.append(bjdm != null && !"".equalsIgnoreCase(bjdm) ? " and bjdm='" + bjdm + "'" : "");
		sb.append(xz != null && !"".equalsIgnoreCase(xz) ? " and xz='" + xz + "'" : "");
		
		sql += sb.toString();
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		sql += searchTj;
		//String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");
		request.setAttribute("searchTj", model.getSearchModel());

		//SearchService searchService = new SearchService();
		//String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");
	
		//��xsxxb�в����ڵ�ѧ�����뵽ѧ����Ϣ����
		StandardOperation.update("xsxxb", "insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,rzrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx)(select xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,zsrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx from view_xsxxb a where not exists(select 1 from xsxxb b where a.xh=b.xh))" , request);
		
		DAO dao = DAO.getInstance();
		boolean flag = dao.runUpdate(sql, inputV);
		
		return flag;
	}
	
	/**
	 * ��ʼ����ҵ��
	 * @param CommanForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * */
	public boolean saveBys(CommanForm model, HttpServletRequest request) throws Exception{
		String sql = "update xsxxb set sfbys = '��' where 1=1 ";
		StringBuffer sb = new StringBuffer();
		
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xz = model.getXz();
		
		sb.append(nj != null && !"".equalsIgnoreCase(nj) ? " and nj='" + nj + "'" : "");
		sb.append(xydm != null && !"".equalsIgnoreCase(xydm) ? " and xydm='" + xydm + "'" : "");
		sb.append(zydm != null && !"".equalsIgnoreCase(zydm) ? " and zydm='" + zydm + "'" : "");
		sb.append(bjdm != null && !"".equalsIgnoreCase(bjdm) ? " and bjdm='" + bjdm + "'" : "");
		sb.append(xz != null && !"".equalsIgnoreCase(xz) ? " and xz='" + xz + "'" : "");
		
		sql += sb.toString();
		//��xsxxb�в����ڵ�ѧ�����뵽ѧ����Ϣ����
		StandardOperation.update("xsxxb", "insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,rzrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx)(select xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,zsrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx from view_xsxxb a where not exists(select 1 from xsxxb b where a.xh=b.xh))" , request);
		
		return StandardOperation.update("xsxxb", sql, request);
	}
	
	/**
	 * ��ѡ���ѧ����ʼ����ҵ���
	 * @param pk
	 * @param request
	 * @return boolean
	 * */
	public boolean saveBynyByData(CommanForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String pk = model.getPkValue();
		String yf = model.getYf();
		String rq = model.getSj();
		String[] xh = pk.split("!!");
		String message = "";
		//��xsxxb�в����ڵ�ѧ�����뵽ѧ����Ϣ����
		StandardOperation.update("xsxxb", "insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,rzrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx)(select xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,zsrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx from view_xsxxb a where not exists(select 1 from xsxxb b where a.xh=b.xh))" , request);
		
		for(int i=0; i<xh.length; i++){
			if(xh[i] != null && !"".equalsIgnoreCase(xh[i])){
				flag = StandardOperation.update("xsxxb", "update xsxxb set byny = (to_number(nj) + to_number(xz))||'-" + yf + "-" + rq + "' where xh='"+xh[i]+"' ", request);
				if(!flag){
					if(message == ""){
						message += i+1;
					}else{
						message += "," + (i+1);
					}					 
				}
			}
		}
		
		if(message != null && !"".equalsIgnoreCase(message)){
			request.setAttribute("mes", "����ʧ�ܵļ�¼��\n��" + message + "����¼��");
		}
		return flag;
	}
	
	/**
	 * ��ѡ���ѧ����ʼ����ҵ��
	 * @param String pk
	 * @param HttpServletRequest request
	 * @return boolean
	 * */
	public boolean saveBysByData(String pk, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String[] xh = pk.split("!!");
		String message = "";
		//��xsxxb�в����ڵ�ѧ�����뵽ѧ����Ϣ����
		StandardOperation.update("xsxxb", "insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,rzrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx)(select xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,zsrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx from view_xsxxb a where not exists(select 1 from xsxxb b where a.xh=b.xh))" , request);
		
		for(int i=0; i<xh.length; i++){
			if(xh[i] != null && !"".equalsIgnoreCase(xh[i])){
				flag = StandardOperation.update("xsxxb", "update xsxxb set sfbys = '��' where xh='"+xh[i]+"' ", request);
				if(!flag){
					if(message == ""){
						message += i+1;
					}else{
						message += "," + (i+1);
					}					 
				}
			}
		}
		
		if(message != null && !"".equalsIgnoreCase(message)){
			request.setAttribute("mes", "����ʧ�ܵļ�¼��\n��" + message + "����¼��");
		}
		return flag;
	}
	
	/**
	 * ��ѯѧ��������Ϣ
	 * @param String xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectStuinfo(String xh){
		return dao.selectStuDetail(xh);
	}
	
	/**
	 * ��ѯѧ�����˹�Ԣ��Ϣ
	 * @param String xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectStuinfoGy(String xh){
		return dao.selectStuDetailGy(xh);
	}	
	
	/**
	 * ��ȡѧ�������Ϣ
	 * @param xh
	 * @param tableName
	 * @param colList1
	 * @return List
	 * */
	public List<String[]> getInfoOfStu(String xh,String tableName,String[] colList1){
		return stuDao.getAllOfInfoByPrint(xh, tableName, colList1);
	}
	
	/**
	 * ��ȡѧ����ͥ��Ϣ
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuJtxx(String xh){
		HashMap<String, String> map = stuDao.getXsjtxx(xh);
		//����ͥ��Ա��������ת��������
		map.put("jtcy1_nl", changeNl(map.get("jtcy1_csrq")));
		map.put("jtcy2_nl", changeNl(map.get("jtcy2_csrq")));
		map.put("jtcy3_nl", changeNl(map.get("jtcy3_csrq")));
		return map;
	}
	
	public List<HashMap<String, String>> getTabCNofXszz(){		
		return stuDao.getTabCNofXszz(xxdm);
	}
	
	public String[] getColOfXszz(String table){
		return stuDao.getColOfXszz(xxdm, table);
	}
	
	public List<String[]> getXszzInfo(String xh, String table){		
		return stuDao.getXszzInfo(xh, xxdm, table);
	}
	
	/**
	 * �й���ҵ��ѧ��ȡ�ۺ����ʲ�����Ϣ
	 * @param xh
	 * @param colList
	 * @return List
	 * */
	public List<String[]> getZhszcpxx(String xh,String[] colList) throws Exception{
		return dao.getZhszcpxx(xh, colList);
	}
	
	/**
	 * �й���ҵ��ѧ��ȡ��Ա��Ϣ
	 * @param xh
	 * @return List
	 * */
	public List<String[]> getDyxx(String xh){
		return dao.getDyxx(xh);
	}
	
	/**
	 * ��ȡҳ��ѡ�������
	 * @param whereSql
	 * @return String
	 * */
	public String getCondition(String whereSql){
		String[] keys = whereSql.split("@");
		StringBuffer condition = new StringBuffer(" where 1=1 ");
		for(int i=0; i<keys.length; i++){
			String[] value = keys[i].split("!!"); 
			if(value != null && value.length>1){
				String key = value[0];
				String val = value[1];
				if(key.equalsIgnoreCase("nfby")){
					if(!StringUtils.isNull(val) && "yes".equalsIgnoreCase(val)){
						val = "��";
					}
				}
				if(key.equalsIgnoreCase("jtcyxm")){
					condition.append(" and  exists(select 1 from view_xsjtxx b where a.xh=b.xh and (b.jtcy1_xm like '%");
					condition.append(val + "%' or jtcy2_xm like '%");
					condition.append(val + "%' or jtcy3_xm like '%");
					condition.append(val + "%'))");
				}else if(key.equalsIgnoreCase("jtnsrd") || key.equalsIgnoreCase("jtnsrg")){
					condition.append(" and exists(select 1 from view_xsjtxx b where a.xh=b.xh and exists(select jtzsr from (select xh,to_number(jtzsr)jtzsr from view_xsjtxx where trim(jtzsr) is not null) c where b.xh=c.xh ");
					if(key.equalsIgnoreCase("jtnsrd")){
						condition.append(" and to_number(jtzsr)>= ");
						condition.append(val);
					}
					if(key.equalsIgnoreCase("jtnsrg")){
						condition.append(" and to_number(jtzsr)<=");
						condition.append(val);
					}
					condition.append("))");
				}else if(key.equalsIgnoreCase("jgshen") || key.equalsIgnoreCase("jgshi") || key.equalsIgnoreCase("jgxian")){
					condition.append(" and jg like '%");
					condition.append(val.replace("'", "��") + "%'");
				}else if(key.equalsIgnoreCase("sydshen") || key.equalsIgnoreCase("sydshi") || key.equalsIgnoreCase("sydxian")){
					condition.append(" and syd like '%");
					condition.append(val.replace("'", "��") + "%'");
				}else if(key.equalsIgnoreCase("hkszdshen") || key.equalsIgnoreCase("hkszdshi") || key.equalsIgnoreCase("hkszdxian")){
					condition.append(" and hkszd like '%");
					condition.append(val.replace("'", "��") + "%'");
				}else if("sfzxk".equalsIgnoreCase(key) && "no".equalsIgnoreCase(val.replace("'", "��"))){//�����Ƿ�����У��
					condition.append(" and (sfzx='����У' or sfyby='��')");
				}else{
					condition.append(" and ");
					condition.append(key);
					
					if(key.equalsIgnoreCase("xh") 
							||key.equalsIgnoreCase("xm") 
							|| key.equalsIgnoreCase("sfzh")
							|| key.equalsIgnoreCase("ssbh")
							|| key.equalsIgnoreCase("jg")
							|| key.equalsIgnoreCase("pycc")
							|| key.equalsIgnoreCase("zw")
							|| key.equalsIgnoreCase("csrq")
							|| key.equalsIgnoreCase("syd")
							|| key.equalsIgnoreCase("byny")
							|| key.equalsIgnoreCase("rxrq")
							|| key.equalsIgnoreCase("sjhm")
							|| key.equalsIgnoreCase("hkszd")
							|| key.equalsIgnoreCase("ksh")
							|| key.equalsIgnoreCase("xz")){//ģ����ѯ�ֶ�
						condition.append( " like '%" );
						condition.append(val);
						condition.append("%'");
					}else{
						condition.append( " = '" );
						condition.append(val);
						condition.append("'");
					}
			}
		}
		}
		//�����Ƿ�����У��
		if(!whereSql.contains("sfzxk") && !Base.xxdm.equals(Globals.XXDM_ZGDZDX)){
			condition.append(" and (sfyby='��' or sfyby is null) and (sfzx='��У' or sfzx is null)");
		}
		return condition.toString();
	}
	
	/**
	 * ��ѯ�༶�ĸ�����Ϣ
	 * @param bjdm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryBjParentInfo(String bjdm){
		XsxxglDAO dao = new XsxxglDAO();
		return dao.queryBjParentInfo(bjdm);
	}
	
	/**
	 * ��ȡѧ���б�
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXzList(){
		return dao.getXzList();
	}
	
	/**
	 * ��ȡѧ���ɼ����浥��Ϣ��ѯ��ͷ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getCjbgdxxToptr(){
		String[] colList = new String[]{"����","xn","xqmc","xh","xm","xb","nj","bjmc","yskts","sskts","bingjia","shijia","chidao"};
		String[] colListCN = dao.getColumnNameCN(colList, "view_hzzy_cjbgdxxb");
		return dao.arrayToList(colList, colListCN);
	}
	
	/**
	 * ��ѯѧ���ɼ����浥��Ϣ
	 * @param CommanForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public List<String[]> queryXscjbgdxx(CommanForm model) throws Exception{
		return dao.selectXscjbgdxx(model);
	}
	
	/**
	 * ��ѯ����ѧ���ɼ����浥��Ϣ
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryXscjbgdxxOne(String pkValue){
		return dao.selectXscjbgdxxOne(pkValue);
	}
	
	/**
	 * ����ѧ���ɼ����浥��Ϣ
	 * @param CommanForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveXscjbgdxx(CommanForm model, HttpServletRequest request) throws Exception{
		boolean result = false;
		String tableName = "view_hzzy_cjbgdxxb";
		String pk = "xn||xq||xh";
		String pkValue = model.getXn()+DealString.toGBK(model.getXq())+DealString.toGBK(model.getXh());
		
		String[] columns = new String[]{"xn","xq","xh","yskts","sskts","bingjia","shijia","kuangke","chidao","qita","zhszpd","bzr","bzrlxdh"};
		String[] values = FormModleCommon.modelToStrings(columns, model);
		if(dao.checkExists(tableName, pk,pkValue)){	
			//�޸�
			result = StandardOperation.update("hzzy_cjbgdxxb", columns, values, pk, pkValue, request);
		}else{
			//����
			result = StandardOperation.insert("hzzy_cjbgdxxb", columns, values, request);
		}
		return result;
	}
	
	/**
	 * ɾ��ѧ���ɼ��ϱ�����Ϣ
	 * @param CommanForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * */
	public boolean delXscjbgdxx(CommanForm model){
		boolean result = true;
		String[] value = model.getPkV();
		String[] sqlArr = new String[value.length];
		for(int i=0; i<value.length; i++){
			sqlArr[i] = "delete from hzzy_cjbgdxxb where xn||xq||xh ='" + value[i] + "'";
		}
		try{
			dao.runBatch(sqlArr);
		}catch(Exception e){
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	/**
	 * ��ѯ�ɼ����浥��ӡʱ����Ϣ
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectCjbgddysjxx(){
		return dao.selectCjbgddysjxx();
	}
	
	/**
	 * ��ȡʡ
	 * */
	public List<HashMap<String, String>> getSsList(){
		return stuDao.getSsList();
	}
	
	/**
	 * ��ȡ��/��
	 * @param String shenId
	 * @return HashMap<String, List<HashMap<String, String>>>
	 * */
	public HashMap<String, List<HashMap<String, String>>> getShiXianList(String shenId){
		return stuDao.getShiList(shenId);
	}
	
	/**
	 * ��ʼ��ѧ����Ϣ�б�
	 * @param type
	 * @return List<HashMap<String,String>>
	 * */
	public List<HashMap<String,String>> initXjxxList(String type){		
		if("yhzk".equalsIgnoreCase(type)){//����״��
			return stuDao.getHyzkList();
		}else if("gb".equalsIgnoreCase(type)){//����
			return stuDao.getGbList();
		}else if("xq".equalsIgnoreCase(type)){//У��
			return dao.getWhList("dm_zju_xq", "dm", "xqmc", "", "", "");			
		}else if("xslb".equalsIgnoreCase(type)){//ѧ�����
			return dao.getWhList("dmk_xslb", "lbdm", "lbmc","","", "");
		}else if("zjlx".equalsIgnoreCase(type)){//֤������
			return dao.getWhList("dmk_zjlx", "zjlxdm", "zjlxmc","","", "");
		}else if("gatq".equalsIgnoreCase(type)){//�۰�̨��
			return dao.getWhList("dmk_gatq", "gatqdm", "gatqmc","","", "");
		}else if("zykl".equalsIgnoreCase(type)){//רҵ����
			return dao.getWhList("dmk_zykl", "zykldm", "zyklmc","","", "");
		}else if("pylx".equalsIgnoreCase(type)){//��������
			return dao.getWhList("dmk_pylx", "pylxdm", "pylxmc","","", "");
		}else if("xxxs".equalsIgnoreCase(type)){//ѧϰ��ʽ
			return dao.getWhList("dmk_xxxs", "xxxsdm", "xxxsmc","","", "");
		}else if("hdxlfs".equalsIgnoreCase(type)){//���ѧ����ʽ
			return dao.getWhList("dmk_hdxlfs", "hdxlfsdm", "hdxlfsmc","","", "");
		}else if("xxfs".equalsIgnoreCase(type)){//ѧϰ��ʽ
			return dao.getWhList("dmk_xxfs", "xxfsdm", "xxfsmc","","", "");
		}else if("xsly".equalsIgnoreCase(type)){//ѧ����Դ
			return dao.getWhList("dmk_xsly", "xslydm", "xslymc","","", "");
		}else if("zb".equalsIgnoreCase(type)){//�ݱ�
			return dao.getWhList("dmk_zb", "zbdm", "zbmc","","", "");
		}else if("jdfs".equalsIgnoreCase(type)){//�Ͷ���ʽ
			return dao.getWhList("dmk_jdfs", "jdfsdm", "jdfsmc","","", "");
		}else if("wyyz".equalsIgnoreCase(type)){//��������
			return dao.getWhList("dmk_wyyz", "wyyzdm", "wyyzmc","","", "");
		}
		return new ArrayList<HashMap<String,String>>();
	}
	
	
	/**
	 * ��ѯ������Ϣ
	 * @return List<HashMap<String,String>>
	 * */
	public List<HashMap<String,String>> getYhList(){
		return dao.selectYhxx();
	}
	
	/**
	 * �жϵ�ǰʱ���Ƿ����趨���޸�ѧ����Ϣʱ�䷶Χ��
	 * @return boolean
	 * */
	public boolean isXsxxxgsj(){
		boolean flag = true;
		//��ȡ����������Ϣ
		HashMap<String, String> confMap = dao.selectXsxxglXsxx();		
		if("yes".equalsIgnoreCase(confMap.get("issz"))){
			//��Ҫʱ�������
			if(!dao.isXsxxxgsj()){
				//�ڲ����޸ĵ�ʱ�����
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * ѧ����Ϣ�޸��Ƿ��Ѿ�ͨ��
	 * @param xh
	 * @return boolean
	 * */
	public boolean checkXsxgqx(String xh){
		boolean flag = true;
		if(dao.checkExists("xsxx_xsxgxxb", "xh||shjg", xh+"ͨ��")){
			flag = false;
		}
		return flag;
	}
	
	/**
	 * ��ȡѧ����Ϣ�����������
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsxxCs(){
		return dao.selectXsxxglXsxx();
	}
	
	/**
	 * ��ѯ��ѧ����Ŀ��޸��ֶ�
	 * @param String yhjs
	 * */
	public String getzdNoXh(String yhjs){
		return dao.selectXgzdNoXh(yhjs);
	}
	
	/**
	 * ��ȡѧ����Ϣ
	 * */
	public HashMap<String, String> getStuAndFamily(String xh, String yhjs){
		String xxdm = StandardOperation.getXxdm();
		HashMap<String, String> map = dao.getStuAndFamily(xh, yhjs, xxdm);
		return map;
	}
	
	/**
	 * ��ȡѧ����Ϣ�޸���ʾ��Ϣ
	 * */
	public String getXsxxxgtsxx(String xh,String shjg){		
		String message = "";		
		//�ж�ѧ���Ƿ��޸����ֶ�
		if(StringUtils.isNull(shjg)
				&& dao.checkExists("xg_xsxx_xsxgzdb", "xh", xh)){
			message = "�����Ϣ�޸��Ѿ�ͨ����ˣ�";			
		}else if("δ���".equalsIgnoreCase(shjg)){
			message = "�����Ϣ�޸Ļ�û�о�����ˣ�";
		}else if("��ͨ��".equalsIgnoreCase(shjg)){
			message = "�����Ϣ�޸�û��ͨ����ˣ�";
		}
		
		return message;
	}
	
	/**
	 * ��ȡѧ��������Ϣ��ͷ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXfszTopTr(){
		String[] colList = {"bjdm","nj","xymc","zymc","bjmc","xfje"};		
		String[] colListCN = dao.getColumnNameCN(colList, "view_xsxxgl_xfszb");
		return dao.arrayToList(colList, colListCN);
	}
	
	/**
	 * ��ѯѧ��������Ϣ
	 * @param CommanForm model
	 * @return List<String[]>
	 * */
	public  List<String[]> queryXfszxx(CommanForm model){
		return dao.selectXfszxx(model);
	}
	
	/**
	 * ѧ������
	 * @throws Exception 
	 * */
	public boolean saveXfsz(CommanForm model) throws Exception{
		return dao.updateXfszxx(model);
	}
	
	/**
	 * ��ѯ�ɼ�����Ϣ
	 * @param CommanForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<List<HashMap<String, String>>> queryXscjb(CommanForm model){
		return dao.selectCjb(model);
	}
	
	/**
	 * ��÷�����Ϣ
	 * @param CommanForm model
	 * @return List<HashMap<String, String>>
	 * */
	public String getInfo_ser(String title){
		String sql = "select content from xsxx_xxfbb where title=?";
		return dao.getOneRs(sql, new String[]{title},"content");
	}
	
	/**
	 * ��ѯѧ����ѧӦ��ѧ��
	 * @param String xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXfje(String xh){
		return dao.getXsxf(xh);
	}
	
	/**
	 * ��ȡѧ���ɼ���ѯ���
	 * @param CommanForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryXscjbForPrint(CommanForm model){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String sql = "select kcsbm,cj from cjb a where xh=? and xn=? and xq=?";
		List<HashMap<String,String>> listTem = dao.getList(sql,new String[]{model.getXh(),model.getXn(),model.getXq()},new String[]{"kcsbm","cj"});
		int listLen = listTem.size();			
		int HalfLen = 0;
		int m = 0;
		//Ĭ����ʾ12��
		m = listLen%2;
		int n=0;
		if(m==1){//listLen��ż������������
			if(listLen==2){
				HalfLen = 2; 
			}else{
				HalfLen = listTem.size()/2;
				n=HalfLen+1;
			}					
		}else{
			if(listLen==1){
				HalfLen = 2;
			}else{
				HalfLen = (listTem.size()+1)/2;
				n= HalfLen;
			}
		}
		for(int i=0;i<n;i++){						
			HashMap<String,String> map = new HashMap<String, String>();
			HashMap<String, String> value = listTem.size()>i ? listTem.get(i) : new HashMap<String, String>();
			HashMap<String, String> nextValue = listTem.size()> i+1 ? listTem.get(i+1) : new HashMap<String, String>();
			HashMap<String, String> halfValue = listTem.size() > n+i ? listTem.get(n+i) : new HashMap<String, String>();
			if(listLen==1||listLen==2){
                if(listLen==2){
                	if(i==0){
                		map.put("rowOneA",String.valueOf(i+1));
                		map.put("kcOneA",value== null ? "" :value.get("kcsbm"));
                		map.put("cjOneA",value== null ? "" :value.get("cj"));	
                		map.put("rowOneB",String.valueOf(2));
                		map.put("kcOneB",nextValue== null ? "" :nextValue.get("kcsbm"));
                		map.put("cjOneB",nextValue== null ? "" :nextValue.get("cj"));
                	}
                }else{
                	if(i==0){
                		map.put("rowOneA",String.valueOf(i+1));
                		map.put("kcOneA",value== null ? "" :value.get("kcsbm"));
                		map.put("cjOneA",value== null ? "" :value.get("cj"));	
                		map.put("rowOneB","");
                		map.put("kcOneB","");
                		map.put("cjOneB","");
                	}
                }
			}else{
				if(i==n-1){
						map.put("rowOneA",String.valueOf(i+1));
						map.put("kcOneA",value== null ? "" :value.get("kcsbm"));
						map.put("cjOneA",value== null ? "" :value.get("cj"));	
						map.put("rowOneB",String.valueOf(n+i+1));
						map.put("kcOneB",halfValue == null ? "" : halfValue.get("kcsbm"));
						map.put("cjOneB",halfValue == null ? "" : halfValue.get("cj"));
				}else{
					map.put("rowOneA",String.valueOf(i+1));
					map.put("kcOneA",value== null ? "" :value.get("kcsbm"));
					map.put("cjOneA",value== null ? "" :value.get("cj"));	
					map.put("rowOneB",String.valueOf(n+i+1));
					map.put("kcOneB",halfValue== null ? "" : halfValue.get("kcsbm"));
					map.put("cjOneB",halfValue == null ? "" : halfValue.get("cj"));
				}
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * ����ѧ����Ϣģ�����������Ϣ
	 * @param StudentInfoForm model
	 * @return boolean 
	 * @throws Exception 
	 * */
	public boolean saveStuConfig(StudentInfoForm model) throws Exception{
		return dao.saveStuConfig(model);
	}
	
	/**
	 *�ж�ѧ����Ϣ�Ƿ��Ѿ�ȷ��,ȷ�Ϸ���true�����򷵻�false
	 *@param xh
	 *@return boolean 
	 * */
	public boolean checkXsxxsfqr(String xh){
		return dao.checkExists("xsxxb", "xh||xsqrxxbz", xh+"��");
	}
	
	/**
	 * ����ѧ��ѧϰ������Ϣ
	 * @param xh
	 * @param valueMap
	 * @return boolean
	 * */
	public boolean saveXsxxjl(String xh, HashMap<String, String[]> valueMap){
		return dao.saveXsxxjlb(xh, valueMap);
	}
	
	/**
	 * ����ѧ������ϵ��Ϣ
	 * @param xh
	 * @param valueMap
	 * @return boolean
	 * */
	public boolean saveXsshgx(String xh, HashMap<String, String[]> valueMap){
		return dao.saveXsshgx(xh, valueMap);
	}
	
	
	/**
	 * ɾ��ѧ��ѧϰ������Ϣ
	 * @param xh
	 * @param valueMap
	 * @return boolean
	 * */
	public boolean deleteXsxxjl(HttpServletRequest request){
		boolean result = false;
		try{
			result = StandardOperation.delete("xsxxjlb", "xh", request.getParameter("save_xh"), request);
		}catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ��ѯѧ��ѧϰ������Ϣ
	 * @param xh
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXsxxjl(String xh){
		return stuDao.getXsxxjl(xh);
	}
	
	/**
	 * ��ѯѧ��ѧϰ������Ϣ
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsxxjlb(String xh){
		return stuDao.getXsxxjlb(xh);
	}
	
	/**
	 * ����������ת��������
	 * @param csrq
	 * @return String
	 * */
	public String changeNl(String csrq){
		String csn = "";//������
		String nl = "";//����
		if(StringUtils.isNotNull(csrq) && csrq.length()>3){
			csn = csrq.substring(0,4);
			nl = (Integer.parseInt(GetTime.getNowYear()) - Integer.parseInt(csn)) + ""; 
		}
		
		return nl;
	}
	
	/**
	 * ��ȡѧ��������Ϣ
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsglxx(String xh){
		return stuDao.selectXsglxx(xh);
	}
	
	
	/**
	 * ��ȡѧ����չ�ֶ�zd1,zd2,zd3,zd4,zd5
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXskzzd(String xh){
		return stuDao.getXskzzd(xh);
	}
	

	/**
	 * �Ƿ�����ѧ��
	 * @param xh
	 * @return
	 */
	public String sfTsxs(String xh) {
		String count = stuDao.getTsxs(xh);
		return "0".equalsIgnoreCase(count)?"��":"��";
	}
	
	/**
	 * ��ȡ�����б�����
	 * @param lx
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getList(String lx){
		//ѧ��״̬
		if(GlobalsVariable.XSXX_KTEYS_XJZTLIST.equalsIgnoreCase(lx)){
			return stuDao.getXjztList();
		}
		//�춯���
		if(GlobalsVariable.XSXX_KTEYS_YDLBLIST.equalsIgnoreCase(lx)){
			XsxxZgdzdxDAO dao = new XsxxZgdzdxDAO();
			return dao.getYdlbList();
		}
		//�Ƿ���У
		if(GlobalsVariable.XSXX_KTEYS_SFZXLIST.equalsIgnoreCase(lx)){
			//������ά����ϵͳĬ��ֵ
			List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "��У");
			map.put("mc", "��У");
			list.add(map);
			
			map = new HashMap<String, String>();
			map.put("dm", "����У");
			map.put("mc", "����У");
			list.add(map);
			return list;
		}
		//����б�
		if(GlobalsVariable.XTWH_SH_LIST.equalsIgnoreCase(lx)){
			DAO dao = DAO.getInstance();			
			return dao.getChkList(3);
		}
		//�������
		if(GlobalsVariable.XTWH_XXBMLB_LIST.equalsIgnoreCase(lx)){
			return dao.getWhList("xg_xsxx_bmlbdmb", "dm", "mc", "", "", "");
		}
		//ѧ�����
		if(GlobalsVariable.XTWH_PYCC_LIST.equalsIgnoreCase(lx)){
			return dao.getWhList("xg_xsxx_pyccdmb", "pyccdm", "pyccmc", "", "", "");
		}
		//�������
		if(GlobalsVariable.XTWH_KSLB_LIST.equalsIgnoreCase(lx)){
			return dao.getWhList("xg_xsxx_kslbdmb", "kslbdm", "kslbmc", "", "", "");
		}
		//��ѧ��ʽ
		if(GlobalsVariable.XTWH_RXFS_LIST.equalsIgnoreCase(lx)){
			return dao.getWhList("xg_xsxx_rxfsdmb", "rxfsdm", "rxfsmc", "", "", "");
		}
		//������ʽ
		if(GlobalsVariable.XTWH_PYFS_LIST.equalsIgnoreCase(lx)){
			return dao.getWhList("xg_xsxx_pyfsdmb", "pyfsdm", "pyfsmc", "", "", "");
		}
		
		//�������
		if("shlcList".equalsIgnoreCase(lx)){
			return dao.getWhList("xg_xtwh_splc", "id", "mc", "", "djlx", "xsxx");
		}
		
		//����
		if(GlobalsVariable.XTWH_MZ_LIST.equalsIgnoreCase(lx)){
			return dao.getMzList();			
		}
		//������ò
		if(GlobalsVariable.XTWH_ZZMM_LIST.equalsIgnoreCase(lx)){
			return dao.getZzmmList();
		}
		//����
		if(GlobalsVariable.XTWH_YH_LIST.equalsIgnoreCase(lx)){
			return getYhList();
		}
		
		return new ArrayList<HashMap<String,String>>();
	}
	
	/**
	 * ��ȡ����춯���
	 * @return String
	 * */
	public String getMaxYdxh(){
		StudentInfoDao studao = new StudentInfoDao();
		return studao.getMaxYdxh();
	}
	
	/**
	 * ��ȡ��ͥ��ϵList
	 * @return
	 * @author sjf
	 */
	public List<HashMap<String, String>> getJtgxList(){
		String[] colList = new String[]{"dm", "mc"};
		String query = " order by dm";
		
		return CommonQueryDAO.commonQueryforList("xszz_jtcygxb", query, new String[]{}, colList, "");
	}
	
	/**
	 * ��ȡ��ϸҳ������ѡ�list
	 * @return
	 * @author sjf
	 */
	public List<HashMap<String, String>> getPagesList(){
		String[] colList = new String[]{"ename", "cname", "sfxs", "xssx"};
		return CommonQueryDAO.commonQueryforList("xg_xsxx_xspz", "", new String[]{}, colList, "");
	}
	
	/**
	 * ��������
	 * @param model
	 * @return
	 * @author sjf
	 */
	public boolean savePages(XsxxpzModel model){
		boolean flag = false;
		String[] arrzd = new String[]{"ename", "cname", "sfxs","xssx"};
		String[] onezd = new String[]{};
		String[] pkValue = model.getEname();
		String pk = "ename";
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName("xg_xsxx_xspz");
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		
		DAO dao = DAO.getInstance();
		try {
			flag = dao.saveData(saveForm, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag; 
	}
	
	public List<HashMap<String, String>> getXsPages(){
		String[] colList = new String[]{"en", "cn"};
		String sql = "select ename en, cname cn from xg_xsxx_xspz where sfxs<>'0' order by xssx";
		DAO dao = DAO.getInstance();
		return dao.getList(sql, new String[]{}, colList);
	}
	
	/**
	 * ��ȡѧ��������Ϣ
	 * @param xh
	 * @return List<HashMap<String, String>>
	 * author qlj
	 */
	public HashMap<String, String> getXsstList(String xh){
		return dao.getXsstList(xh);
	}
	/**
	 * ��ȡѧ������
	 * @param xh
	 */
	public String getStuName(String xh) {
	    String xm = dao.getStuInfo(xh);
	    return xm;
	    
	    
	}
	
	/**
	 * ��ȡ��ǰʱ��
	 * @param xh
	 */
	public String getNowTime() {
	    String nowTime = dao.nowTime();
	   	return nowTime;
	    
	}
	
	/**
	 * ��ȡѧ��ס�޻�����Ϣ
	 * @param xh
	 * @return
	 * @author gaobb 2011-11-22 �°湫Ԣ
	 */
	public HashMap<String,String> getXszsjbxx(String xh){
		return dao.getXszsjbxx(xh);
	}
	
	/**
	 * ��ȡ���ݹ�ҵ԰���ۺϲ����б�
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getSzgyyqZhcpList(String xh){
		return dao.getSzgyyqZhcpList(xh);
	}
}