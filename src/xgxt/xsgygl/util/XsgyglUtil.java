
package xgxt.xsgygl.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts.action.ActionForm;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CommonQueryDAO;
import xgxt.xsgygl.action.XsgyglForm;
import xgxt.xsgygl.dao.GyglShareDAO;
import xgxt.xsgygl.dao.gyglDao;

public class XsgyglUtil {
	/**�����ܳ��õ��ı���*/
	private String xn = "";//ѧ��
	private String xq = "";//ѧ��
	private String xh = "";//ѧ��
	private String lddm = "";//¥������
//	private String nd = "";//���
	private String nj = "";//�꼶
	private String xy = "";//ѧԺ
	private String zy = "";//רҵ
	private String bj = "";//�༶
	private String xm = "";//����
	private String qsh = "";//���Һ�
	private String cwh = "";//��λ��
	private String pycc = "";//�������

	public XsgyglUtil(HttpServletRequest request,ActionForm form) {	
		XsgyglForm xsgyForm = (XsgyglForm)form;
		xn   = xsgyForm.getXn();
		xq   = xsgyForm.getXq();
	    lddm = xsgyForm.getLddm();
	    xh   = xsgyForm.getXh();
//	    nd   = xsgyForm.getNd();
	    nj   = xsgyForm.getNj();
	    zy   = xsgyForm.getZydm();
	    bj   = xsgyForm.getBjdm();
	    xm   = DealString.toGBK(xsgyForm.getXm());
	    qsh  = xsgyForm.getQsh();
	    cwh  = xsgyForm.getCwh();
	    xy   = xsgyForm.getXydm();
	    pycc = xsgyForm.getPycc();
	    xsgyForm.setXm(xm);
	   
	}
	public void utilGywxsq(HttpServletRequest request,ActionForm form) throws Exception{
		DAO dao = DAO.getInstance();
		gyglDao  myDao = new gyglDao();
		HashMap<String,String> map = new HashMap<String,String>();
		String doType = request.getParameter("doType");//��������
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();//�û�����
		String userName = session.getAttribute("userName").toString();//�û���¼��
		XsgyglForm xsgyForm = (XsgyglForm)form;		
		String bxr = xsgyForm.getBxr();//������
		String bxsj = request.getParameter("bxsj");//����ʱ��
		String ssbh = request.getParameter("ssbh");//������
		String xm = DealString.toGBK(request.getParameter("xm"));//����������
		String wxnr = DealString.toGBK(request.getParameter("wxnr"));//ά������
		String bz = DealString.toGBK(request.getParameter("bz"));//��ע
		String fzbm = DealString.toGBK(request.getParameter("fzbm"));
		String lxfs = DealString.toGBK(request.getParameter("lxfs"));//��ϵ��ʽ
		String realTable = "gywxglsqb";//��Ԣά�޹�������������
		String xxdm = dao.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){//��ɳ����ְҵ����ѧԺ
			request.setAttribute("isCSMZZYJSXY", "CSMZZYJSXY");
			request.setAttribute("wxnrList",myDao.getGyWxNrList());//ά�������б�
			request.setAttribute("fzbmList",myDao.getGyWxFzBmList());//�������б�
		}
		boolean done = false;
        if(userType.equalsIgnoreCase("student")){//ѧ���û�����ʱ��ȡ�����Ϣ
        	map = dao.getMap("select xh,xm,ssbh,ldmc,qsh from view_xszsxx where xh=?", new String[]{userName}, new String[]{"xh","xm","ssbh","ldmc","qsh"});
            map.put("bxr",userName);
        }else{//��ʦ�û�����ʱ��ȡ�����Ϣ
        	request.setAttribute("isNotStu", "isNotStu");
        }
		
		if(Base.isNull(doType)){//Ĭ�ϵ�ǰѧ��ѧ��
			map.put("xn",Base.currXn);
			map.put("xq",Base.currXq);
		}else{
			if(doType.equalsIgnoreCase("save")){//��������
				done = StandardOperation.delete(realTable, new String[]{"ssbh","bxsj"},new String[]{ssbh,bxsj},request);
				if(done){
					done = StandardOperation.insert(realTable, new String[]{"xn","xq","bz","ssbh","bxsj","wxnr","bxr","bxrxm","fzbm","lxfs"},
							new String[]{xn,xq,bz,ssbh,bxsj,wxnr,bxr,xm,fzbm,lxfs}, request);
					map.put("xn",xn);
					map.put("xq",xq);
					map.put("bxsj",bxsj);
					map.put("wxnr",wxnr);
					map.put("bz",bz);
					map.put("fzbm",fzbm);
					map.put("lxfs",lxfs);
				}
			}
			if(done){
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}
		request.setAttribute("userType", userType);
		gyglDao.getXnxqList(request);//��ȡѧ��ѧ��List����
		request.setAttribute("rs", map);
	}
	public void gywxSqShXx(HttpServletRequest request,ActionForm form){
		DAO dao = DAO.getInstance();
		gyglDao  myDao = new gyglDao();
		XsgyglForm xsgyForm = (XsgyglForm)form;
		StringBuffer querry = new StringBuffer();
		Vector<Object> rs = new Vector<Object>();
		String go = request.getParameter("go");
		String tips = "��Ԣ���� - ��� - ��Ԣ(����)ά�����";
		String rsNum = "";
		String pk = "ssbh||bxsj";
		String tableName = "view_gywxglsqxx";
		String realTable = "gywxglsqb";
		String kssj = xsgyForm.getKssj();//��ʼ���ڲ�ѯ����
		String jssj = xsgyForm.getJssj();//��������
		String shzt = DealString.toGBK(xsgyForm.getYesNo());//���״̬��־	
		String fzbm = xsgyForm.getFzbm();//�����Ŵ���
        String xxdm = dao.getXxdm();
		xsgyForm.setYesNo(shzt);
		List topTr = null;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){//��ɳ����ְҵ����ѧԺ
			request.setAttribute("isCSMZZYJSXY", "CSMZZYJSXY");
			request.setAttribute("fzbmList",myDao.getGyWxFzBmList());//�������б�
		}
		if(Base.isNull(go)){
			xn = Base.currXn;
			xq = Base.currXq;
			xsgyForm.setXn(xn);
			xsgyForm.setXq(xq);
		}
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
		querry.append(Base.isNull(shzt)?"":" and xxsh='"+shzt+"' ");
		querry.append(Base.isNull(fzbm)?"":" and fzbm='"+fzbm+"' ");
		if(!Base.isNull(kssj)&&!Base.isNull(jssj)){
			querry.append(" and bxsj between '"+kssj+"' and '"+jssj+"'");
		}else{
			if(!Base.isNull(kssj)){
				querry.append(" and bxsj='"+kssj+"'  ");
			}else if(!Base.isNull(jssj)){
				querry.append(" and bxsj='"+jssj+"' ");
			}
		}		
		if(!Base.isNull(go)){
			String[] colList = new String[]{"bgcolor","����","xn","xq","bxsj","wxnr","bxrxm","ldmc","qsh","xxsh"};
			String[] colCNList = dao.getColumnNameCN(colList, tableName);
			StringBuffer sql = new StringBuffer();
			sql.append(" select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor, a.* from ( ");
			if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){//��ɳ����ְҵ����ѧԺ				
				sql.append(" select "+pk+" ���� ,xn,xq,bxsj,(select nrmc from csmz_gywxnrdmb c where c.nrdm=wxnr) wxnr,bxrxm,ldmc,qsh,xxsh from "+tableName+querry+" order by xxsh desc ) a ");
			}else{
				sql.append(" select "+pk+" ���� ,xn,xq,bxsj,wxnr,bxrxm,ldmc,qsh,xxsh from "+tableName+querry+" order by xxsh desc ) a ");
			}
			topTr = dao.arrayToList(colList, colCNList);
				rs.addAll(dao.rsToVator(sql.toString(), new String[] {}, colList));
				request.setAttribute("rs", rs);
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}		
		}
		request.setAttribute("rsNum",rsNum);
		request.setAttribute("tips", tips);
		request.setAttribute("topTr",topTr);	
		gyglDao.getLdLcQshList(request);
		request.setAttribute("chkList", dao.getChkList(3));//���״̬�б�
		request.setAttribute("tableName",tableName);
		request.setAttribute("realTable",realTable);
	}
	public void gywxSqSh(HttpServletRequest request,ActionForm form) throws Exception{
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>(); 
	    String pkValue = request.getParameter("pkValue");
	    String doType = request.getParameter("doType");
	    String xxshyy = DealString.toGBK(request.getParameter("xxshyy"));
		String wxsj =  DealString.toGBK(request.getParameter("wxsj"));
		String rydm =  DealString.toGBK(request.getParameter("rydm"));
		wxsj = Base.isNull(wxsj)?"��δ����":wxsj;
		rydm = Base.isNull(rydm)?"��δ����":rydm;
	    String sql = "";
	    String pk = "ssbh||bxsj";
	    boolean done = false;
	    if(dao.getXxdm().equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){//��ɳ����ְҵ����ѧԺ
	    	request.setAttribute("isCSMZZYJSXY", "CSMZZYJSXY");
			sql = "select a.* from (select a.ssbh,a.xn,a.xq,a.ldmc,a.qsh,a.bxsj,(select nrmc from csmz_gywxnrdmb c where c.nrdm=a.wxnr)wxnr, " 
				+"(select fzbmmc from csmz_gywxfzbmdmb c where c.fzbmdm=a.fzbm)fzbm,a.bxrxm,a.xxsh yesNo,a.bz sqbz ,a.lxfs,b.wxsj,b.rydm,b.bz from  "
				+"view_gywxglsqxx a left join view_gywxglxx b on a.ssbh||a.bxsj=b.ssbh||b.bxsj) a where a.ssbh||a.bxsj=? ";
	    }else{
			sql = "select a.* from (select a.ssbh,a.xn,a.xq,a.ldmc,a.qsh,a.bxsj,a.wxnr,a.bxrxm,a.xxsh yesNo,a.bz sqbz,a.fzbm,a.lxfs,a.wxsj,a.wxry rydm,b.bz from  "
				   +"view_gywxglsqxx a left join view_gywxglxx b on a.ssbh||a.bxsj=b.ssbh||b.bxsj) a where a.ssbh||a.bxsj=?";
	    }
		map = dao.getMap(sql,new String[]{pkValue},new String[]{"ssbh","xn","xq","ldmc","qsh","bxsj","wxnr","bxrxm","yesNo","sqbz","fzbm","wxsj","rydm","lxfs","bz"});
		List ryList = dao.getList("select rydm,rymc from gywxryb where rydm <> '000'", new String[] {}, new String[] {
				"rydm", "rymc" });
		if(!Base.isNull(doType)&&"save".equalsIgnoreCase(doType)){
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			done = StandardOperation.update("gywxglsqb",new String[]{"xxsh","xxshyy","wxsj","wxry"},new String[]{yesNo,xxshyy,wxsj,rydm},pk,pkValue,request);
			if(done){
				if(yesNo.equalsIgnoreCase("ͨ��")){
					String bz  = DealString.toGBK(request.getParameter("bz"));
					done = StandardOperation.delete("gywxglb", pk,pkValue, request);
					if(done){
						done = StandardOperation.insert("gywxglb",new String[]{"xn","xq","ssbh","bxsj","wxnr","bz"},
								new String[]{map.get("xn"),map.get("xq"),map.get("ssbh"),map.get("bxsj"),map.get("wxnr"),
								bz},request);
					}
				}else{				
					StandardOperation.delete("gywxglb", pk, pkValue, request);
				}
			}
			if(done==true){
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}	
		//��˽��
		String[] shJg = dao.getOneRs("select xxshyy from gywxglsqb where "+ pk + "=? ",new String[]{pkValue},new String[]{"xxshyy"}); 
		if(shJg!=null){
			map.put("xxshyy",shJg[0]);
		}
		request.setAttribute("ryList", ryList);
		request.setAttribute("rs",map);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("userType",dao.getUserType(request.getSession().getAttribute("userDep").toString()));
	}
	public void ssYdSq(HttpServletRequest request,ActionForm form) throws Exception{
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		HttpSession session = request.getSession();
		String doType = request.getParameter("doType");
		String userType = session.getAttribute("userOnLine").toString();//�û�����
		String userName = session.getAttribute("userName").toString();//�û���¼��
		boolean done = false;
		String realTable = "ssydsqb";//�����춯�����
		if(userType.equalsIgnoreCase("student")){//ѧ���û�����ʱ��ȡ�����Ϣ
        	map = dao.getMap("select xh,xm,xb,nj,xymc,zymc,bjmc,rzrq,ssbh,lddm yqdlddm,ldmc ydqldmc,qsh ydqqsh,cwh ydqcwh from view_xszsxx where xh=?",
        		new String[]{userName}, new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","rzrq","ssbh","yqdlddm","ydqldmc","ydqqsh","ydqcwh"});
            xh = userName;       
		}else{//��ʦ�û�����ʱ��ȡ�����Ϣ
			request.setAttribute("isNotStu", "isNotStu");
        }
		if(Base.isNull(doType)){//Ĭ�ϵ�ǰѧ��ѧ��
			map.put("xn",Base.currXn);
			map.put("xq",Base.currXq);
		}else{
			if(doType.equalsIgnoreCase("save")){//��������
				String ydsj = request.getParameter("ydsj");
				String ydly = DealString.toGBK(request.getParameter("ydly"));
				String ydqssbh = map.get("ssbh");
				String ydhssbh = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh");//lddm+"-"+qsh;
				done = StandardOperation.delete(realTable, new String[]{"xh","sqydsj"},new String[]{xh,ydsj},request);
				if(done){
					done = StandardOperation.insert(realTable, new String[]{"xh","xn","xq","ydqssbh","ydhssbh","ydly","sqydsj","ydhcwh"},
							new String[]{xh,xn,xq,ydqssbh,ydhssbh,ydly,ydsj,cwh}, request);					
				}
				map.put("xn",xn);
				map.put("xq",xq);
				map.put("lddm", lddm);
				map.put("qsh",qsh);
				map.put("cwh",cwh);
				map.put("ydsj",ydsj);
				map.put("ydly",ydly);
			}
			if(done){
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}		
		gyglDao.getLdLcQshList(request);//��ȡ¥��ѧ��ѧ��List����
		request.setAttribute("rs",map);
	}
	public void ssYdSqShxx(HttpServletRequest request,ActionForm form,String userType) {
		DAO dao = DAO.getInstance();
		XsgyglForm xsgyForm = (XsgyglForm)form;
		StringBuffer querry = new StringBuffer();//��ѯ����
		Vector<Object> rs = new Vector<Object>();
		String go = request.getParameter("go");//��ѯ��־
		String rsNum = "";//��¼��
		String pk = "xh||sqydsj";//�ؼ��ֶ�
		String tableName = "view_ssydsqxx";//��ͼ
		String realTable = "ssydsqb";//����ͼ
		String kssj = xsgyForm.getKssj();//��ʼ���ڲ�ѯ����
		String jssj = xsgyForm.getJssj();//��������
		String shzt = DealString.toGBK(xsgyForm.getYesNo());//���״̬��־
		xsgyForm.setYesNo(shzt);
		List topTr = null;//�ֶ�˵��List
		if(Base.isNull(go)){//Ĭ�ϵ�ǰѧ�ꡢ���
			xn = Base.currXn;
			xq = Base.currXq;
			xsgyForm.setXn(xn);
			xsgyForm.setXq(xq);
		}
		if(userType.equalsIgnoreCase("xy")){
			xy = request.getSession().getAttribute("userDep").toString();
			xsgyForm.setXydm(xy);
		}
		
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(zy)?"":" and zydm='"+zy+"' ");
		querry.append(Base.isNull(bj)?"":" and bjdm='"+bj+"' ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
		querry.append(Base.isNull(xy)?"":" and xydm='"+xy+"' ");			
		if(!Base.isNull(kssj)&&!Base.isNull(jssj)){
			querry.append(" and sqydsj between '"+kssj+"' and '"+jssj+"'");
		}else{
			if(!Base.isNull(kssj)){
				querry.append(" and sqydsj='"+kssj+"'  ");
			}else if(!Base.isNull(jssj)){
				querry.append(" and sqydsj='"+jssj+"' ");
			}
		}		
		if(!Base.isNull(go)&&"go".equalsIgnoreCase(go)){
			String[] colList = new String[]{"bgcolor","����","xh","xm","xb","sqydsj","ydqldmc","ydqqsh","ldmc","qsh","xxsh"};
			StringBuffer sql = new StringBuffer();
			if(userType.equalsIgnoreCase("xx")
					||userType.equalsIgnoreCase("admin")){
				querry.append(Base.isNull(shzt)?"":" and xxsh='"+shzt+"' ");
				querry.append(" and xysh='ͨ��' ");
				sql.append(" select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor, a.* from ( ");
				sql.append(" select "+pk+" ���� ,xh,xm,xb,sqydsj,ydqldmc,ydqqsh,ldmc,qsh,xxsh from "+tableName+querry+" order by xxsh desc,sqydsj desc ) a ");
			}else if(userType.equalsIgnoreCase("xy")){
				querry.append(Base.isNull(shzt)?"":" and xysh='"+shzt+"' ");
				sql.append(" select (case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor, a.* from ( ");
				sql.append(" select "+pk+" ���� ,xh,xm,xb,sqydsj,ydqldmc,ydqqsh,ldmc,qsh,xysh from "+tableName+querry+" order by xxsh desc,sqydsj desc ) a ");
				colList = new String[]{"bgcolor","����","xh","xm","xb","sqydsj","ydqldmc","ydqqsh","ldmc","qsh","xysh"};
			}
			String[] colCNList = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colCNList);
				rs.addAll(dao.rsToVator(sql.toString(), new String[] {}, colList));
				request.setAttribute("rs", rs);
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}	
		}
		gyglDao.getXnxqList(request);//ѧ��ѧ��List
		gyglDao.getXyZyBjxx(request);//ѧԺרҵ�༶List
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum",rsNum);
		request.setAttribute("chkList", dao.getChkList(3));//���״̬�б�
		request.setAttribute("tableName",tableName);
		request.setAttribute("realTable",realTable);
	}
	public void ssYdSqSh(HttpServletRequest request,ActionForm form,String userType) throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>(); 
	    String pkValue = request.getParameter("pkValue");//����ֵ
	    String doType = request.getParameter("doType");//��������
	    String xyshyy  = DealString.toGBK(request.getParameter("xyshyy"));
	    String xxshyy  = DealString.toGBK(request.getParameter("xxshyy"));
	    String sql = "";
	    String pk = "xh||sqydsj";//ssydsqb������
	    String shType = "";
	    if(userType.equalsIgnoreCase("xx")
				||userType.equalsIgnoreCase("admin")){
	    	shType = "xxsh";
	    }else{
	    	shType = "xysh";
	    }
	    boolean done = false;
		//���ssydsqb�����ֵ��
	    sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,xq,ydqldmc,ydqqsh,ydqssbh,ydqcwh,ydqrzsj,ldmc,qsh,ydhssbh,ydhcwh,sqydsj,ydly from view_ssydsqxx where "+pk+"=? ";
		map = dao.getMap(sql,new String[]{pkValue},new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","xn","xq","ydqldmc","ydqqsh","ydqssbh","ydqcwh","ydqrzsj","ldmc","qsh","ydhssbh","ydhcwh","sqydsj","ydly"});
		//���ݱ���
		if(!Base.isNull(doType)&&"save".equalsIgnoreCase(doType)){//��ѯ
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if(userType.equalsIgnoreCase("xy")){
				done = StandardOperation.update("ssydsqb",new String[]{"xysh","xyshyy"},new String[]{yesNo,xyshyy},pk,pkValue,request);//�޸�ssydsqb����Ժϵ���״̬			
			}else if(userType.equalsIgnoreCase("xx")
					||userType.equalsIgnoreCase("admin")){
				done = StandardOperation.update("ssydsqb",new String[]{"xxsh","xxshyy"},new String[]{yesNo,xxshyy},pk,pkValue,request);//�޸�ssydsqb����ѧУ���״̬
				if(yesNo.equalsIgnoreCase("ͨ��")){	//ѧУ���û����ͨ����						
					String bz  = DealString.toGBK(request.getParameter("bz"));
					boolean del = StandardOperation.delete("ssydxxb", "xh||ydsj",pkValue, request);
					if(del){
						//ͨ��ʱ�����������Ϣ���������춯��Ϣ����
						boolean ins = StandardOperation.insert("ssydxxb",new String[]{"xh","xn","xq","ydqssbh","ydhssbh","ydly","ydsj","bz","ydqcwh","ydhcwh","ydqrzsj","ydhrzsj"},
								new String[]{map.get("xh"),map.get("xn"),map.get("xq"),map.get("ydqssbh"),map.get("ydhssbh"),map.get("ydly"),map.get("sqydsj"),bz,map.get("ydqcwh"),map.get("ydhcwh"),map.get("ydqrzsj"),
								map.get("ydhrzsj")},request);
						if(ins){
							//ͨ��ʱ�޸�ѧ��ס����Ϣ
							   StandardOperation.update("xszsxxb", new String[] { "ssbh",
									"cwh", "rzrq" }, new String[] {map.get("ydhssbh"),map.get("ydhcwh") , 
									map.get("sqydsj") }, new String[] { "xh" }, new String[] { map.get("xh") },
									request);
						}							
					}
				}else {//��ͨ��ʱ���	�����춯��Ϣ�����������Ϣɾ�������Ϣ				
					StandardOperation.delete("ssydxxb",  "xh||ydsj",pkValue, request);
				}
			}			
			
			if(done==true){
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}
		//��˽��
		String[] shJg = dao.getOneRs("select "+shType+" yesNo,xyshyy,xxshyy from ssydsqb where "+ pk + "=? ",new String[]{pkValue},new String[]{"yesNo","xyshyy","xxshyy"}); 
		if(shJg!=null){
			map.put("yesNo",shJg[0]);
			map.put("xyshyy",shJg[1]);
			map.put("xxshyy",shJg[2]);
		}
		sql = map.get("ydqrzsj");
		request.setAttribute("rs",map);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("userType",dao.getUserType(request.getSession().getAttribute("userDep").toString()));
		request.setAttribute("chkList", dao.getChkList(3));
	}
	public void jqLxSq(HttpServletRequest request,ActionForm form) throws Exception{
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		HashMap<String,String> jqmap = new HashMap<String,String>();
		HashMap<String,String> qsmap = new HashMap<String,String>();
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String doType = request.getParameter("doType");
		String userType = session.getAttribute("userOnLine").toString();//�û�����
		String userName = session.getAttribute("userName").toString();//�û���¼��
		boolean done = false;
		String realTable = "gygl_Jqlxsqb";//�����춯�����
		if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){//��������ѧԺ
			realTable="gygl_jqlxxxb";
		}
		if(userType.equalsIgnoreCase("student")){//ѧ���û�����ʱ��ȡ�����Ϣ
        	map = dao.getMap("select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?",
        		new String[]{userName}, new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc"});
            xh = userName;  
		}else{//��ʦ�û�����ʱ��ȡ�����Ϣ
			request.setAttribute("isNotStu", "isNotStu");
        }
		if(Base.isNull(doType)){//Ĭ�ϵ�ǰѧ��ѧ��
			map.put("xn",Base.currXn);
			map.put("xq",Base.currXq);
		}else{
			if(doType.equalsIgnoreCase("save")){//��������
				String ssbh = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh");//lddm+"-"+qsh;
				if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
					ssbh = null;
				}
		        String kssj = request.getParameter("kssj");
		        String jssj = request.getParameter("jssj");
		        String lxyy = DealString.toGBK(request.getParameter("lxyy"));
		        String lxdh = DealString.toGBK(request.getParameter("lxdh"));
				done = StandardOperation.delete(realTable, new String[]{"xn","xq","xh"},new String[]{xn,xq,xh},request);
				if(done){
					
					done = StandardOperation.insert(realTable, new String[]{"xn","xq","xh","ssbh","zskssj","zsjssj","lxyy","lxdh"},
							new String[]{xn,xq,xh,ssbh,kssj,jssj,lxyy,lxdh}, request);
				}
				jqmap =dao.getMap("select xn,xq,zskssj kssj,zsjssj jssj,lxdh,lxyy,ssbh from "+realTable+" where xh=?",
	        			new String[]{userName}, new String[]{"xn","xq","kssj","jssj","lxdh","lxyy","ssbh"});
	        	qsmap =dao.getMap("select qsh,lddm from view_xszsxx where ssbh= ?", new String[]{jqmap.get("ssbh")}, new String[]{"qsh","lddm"});
	            xh = userName;  
	            map.putAll(jqmap);
	            map.putAll(qsmap);
			}
			if(done){
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}
		gyglDao.getLdLcQshList(request);//��ȡ¥��ѧ��ѧ��List����	
		request.setAttribute("rs",map);
	}
	
	
	public void jqLxSqShXx(HttpServletRequest request,ActionForm form){
		DAO dao = DAO.getInstance();
		XsgyglForm xsgyForm = (XsgyglForm)form;
		StringBuffer querry = new StringBuffer();//��ѯ����
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		Vector<Object> rs = new Vector<Object>();
		String xxdm = StandardOperation.getXxdm();
		String go = request.getParameter("go");//��ѯ��־
		String rsNum = "";//��¼��
		String pk = "xn||xq||xh";//�ؼ��ֶ�
		String tableName = "";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			 tableName = "view_xbemy_gygl_jqlxsq";//��ͼ
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){//��������
			 tableName = "view_xbemy_gygl_jqlxxx";
		}else{
			 tableName = "view_gygl_jqlxsq";//��ͼ
		}
		List topTr = null;//�ֶ�˵��List
		String realTable = "gygl_jqlxsqb";//�����
		String shzt = DealString.toGBK(xsgyForm.getYesNo());//���״̬��־
		xsgyForm.setYesNo(shzt);
		if(Base.isNull(go)){//Ĭ�ϵ�ǰѧ�ꡢ���
			xn = Base.currXn;
			xq = Base.currXq;
			xsgyForm.setXn(xn);
			xsgyForm.setXq(xq);
		}
		if(userType.equalsIgnoreCase("xy")){
			xy = userDep;
			xsgyForm.setXydm(xy);
		}
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(zy)?"":" and zydm='"+zy+"' ");
		querry.append(Base.isNull(bj)?"":" and bjdm='"+bj+"' ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
		if(!Base.isNull(go)&&"go".equalsIgnoreCase(go)){//��ѯ
			
			String[] colList = { "bgcolor","����","xn", "xq", "xh", "xb", "xm", "nj", "xymc","zymc", "bjmc","zskssj", "zsjssj","xxsh"};				
			if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
				 colList = new String[]{ "bgcolor","����","xn", "xqmc", "xh", "xb", "xm", "nj", "xymc","zymc", "bjmc","zskssj", "zsjssj","xxsh"};
			}
			StringBuffer sql = new StringBuffer();
			if(userType.equalsIgnoreCase("xx")
					|| userType.equalsIgnoreCase("admin")) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
					querry.append(Base.isNull(shzt) ? "" : " and xxsh='" + shzt
							+ "' ");
					sql.append(" select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor, a.* from ( ");
					sql.append(" select "
									+ pk
									+ " ���� ,xn, xq, xh, xb, xm, nj, xymc,zymc, bjmc,zskssj, zsjssj,xxsh from "
									+ "view_hh_gygl_jqlxsq" + querry
									+ " order by xxsh desc,zskssj desc ) a ");
				}if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){ 
					querry.append(Base.isNull(shzt) ? "" : " and xxsh='" + shzt
							+ "' ");
					
					sql.append(" select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor, a.* from ( ");
					sql.append(" select "
									+ pk
									+ " ���� ,xn, xqmc, xh, xb, xm, nj, xymc,zymc, bjmc,zskssj, zsjssj,xxsh from "
									+ tableName + querry
									+ " order by xxsh desc,zskssj desc ) a ");
				}else {
					querry.append(Base.isNull(shzt) ? "" : " and xxsh='" + shzt
							+ "' ");
					querry.append(" and xysh='ͨ��'");
					sql.append(" select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor, a.* from ( ");
					sql.append(" select "
									+ pk
									+ " ���� ,xn, xq, xh, xb, xm, nj, xymc,zymc, bjmc,zskssj, zsjssj,xxsh from "
									+ tableName + querry
									+ " order by xxsh desc,zskssj desc ) a ");
				}
			}else if(userType.equalsIgnoreCase("xy")){
				querry.append(Base.isNull(shzt)?"":" and xysh='"+shzt+"' ");
				sql.append(" select (case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor, a.* from ( ");
				sql.append(" select "+pk+" ���� ,xn, xq, xh, xb, xm, nj, xymc,zymc, bjmc,zskssj, zsjssj,xysh from "+tableName+querry+" order by xysh desc,zskssj desc ) a ");
				colList = new String[]{ "bgcolor","����","xn", "xq", "xh", "xb", "xm", "nj", "xymc","zymc", "bjmc","zskssj", "zsjssj","xysh"};	
			}
			String[] colCNList = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colCNList);			
			rs.addAll(dao.rsToVator(sql.toString(), new String[] {}, colList));
			request.setAttribute("rs", rs);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}	
		}
		gyglDao.getXnxqList(request);//ѧ��ѧ��List
		gyglDao.getXyZyBjxx(request);//ѧԺרҵ�༶List
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum",rsNum);
		request.setAttribute("chkList", dao.getChkList(3));//���״̬�б�
		request.setAttribute("tableName",tableName);
		request.setAttribute("realTable",realTable);
	}
	public void jqLxSqSh(HttpServletRequest request,ActionForm form) throws Exception{
		DAO dao = DAO.getInstance();
		String userType = request.getSession().getAttribute("userType").toString();
		HashMap<String,String> map = new HashMap<String,String>(); 
	    String pkValue = request.getParameter("pkValue");//����ֵ
	    String xyshyy  = DealString.toGBK(request.getParameter("xyshyy"));
	    String xxshyy  = DealString.toGBK(request.getParameter("xxshyy"));
	    String doType = request.getParameter("doType");//��������
	    String xxdm = StandardOperation.getXxdm();
	    String sql = "";
	    String pk = "xn||xq||xh";//gygl_jqlxsqb������
	    boolean done = false;
	    String shType ="";
	    if(userType.equalsIgnoreCase("xx")
				||userType.equalsIgnoreCase("admin")){
	    	shType = "xxsh";
	    }else{
	    	shType = "xysh";
	    }
//	  ���gygl_jqlxsqb�����ֵ��
	    if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
			sql = " select xn,xq,xh,xm,xb,nj,xymc,zymc,bjmc,zskssj,zsjssj,lxdh,lxyy,ssbh from view_hh_gygl_jqlxsq where "
					+ pk + "=? ";
			map = dao.getMap(sql, new String[] { pkValue }, new String[] {
					"xn", "xq", "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc",
					"zskssj", "zsjssj", "lxdh", "lxyy", "ssbh"});
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			sql = " select xn,xq,xh,xm,xb,nj,xymc,zymc,bjmc,zskssj,zsjssj,lxdh,lxyy,ssbh from view_xbemy_gygl_jqlxsq where "
				+ pk + "=? ";
			map = dao.getMap(sql, new String[] { pkValue }, new String[] {
				"xn", "xq", "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc",
				 "zskssj", "zsjssj", "lxdh", "lxyy", "ssbh"});
		} else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){//��������
			sql = " select xn,xq,xh,xm,xb,nj,xymc,zymc,bjmc,ldmc,qsh,zskssj,zsjssj,lxdh,lxyy,ssbh from view_xbemy_gygl_jqlxxx where "
				+ pk + "=? ";
			map = dao.getMap(sql, new String[] { pkValue }, new String[] {
				"xn", "xq", "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc","qsh","ldmc",
				"zskssj", "zsjssj", "lxdh", "lxyy", "ssbh"});
		} else{
			sql = " select xn,xq,xh,xm,xb,nj,xymc,zymc,bjmc,ldmc,qsh,zskssj,zsjssj,lxdh,lxyy,ssbh from view_gygl_jqlxsq where "
					+ pk + "=? ";
			map = dao.getMap(sql, new String[] { pkValue }, new String[] {
					"xn", "xq", "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc",
					"ldmc", "qsh", "zskssj", "zsjssj", "lxdh", "lxyy", "ssbh" });
		}	    
		//���ݱ���
		if(!Base.isNull(doType)&&"save".equalsIgnoreCase(doType)){
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));	
			if(userType.equalsIgnoreCase("xx")
					||userType.equalsIgnoreCase("admin")){
				/*if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
					done = StandardOperation.update("gygl_jqlxsqb",new String[]{"xxsh"},new String[]{yesNo},pk,pkValue,request);//�޸�ssydsqb�������״̬
				}else{*/
					if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){//��������
						done = StandardOperation.update("gygl_jqlxxxb",new String[]{"xxsh","xxshyy"},new String[]{yesNo,xxshyy},pk,pkValue,request);//�޸�ssydsqb�������״̬
					}else{
						done = StandardOperation.update("gygl_jqlxsqb",new String[]{"xxsh","xxshyy"},new String[]{yesNo,xxshyy},pk,pkValue,request);//�޸�ssydsqb�������״̬
						if(done){
					    	if(yesNo.equalsIgnoreCase("ͨ��")){//ͨ��ʱ�����������Ϣ���������У��Ϣ����							
					    		boolean del = StandardOperation.delete("gygl_jqlxxxb", pk,pkValue, request);
					    		if(del){
					    			StandardOperation.insert("gygl_jqlxxxb",new String[]{"xn","xq","xh","ssbh","zskssj","zsjssj","lxyy","lxdh"},
					    					new String[]{map.get("xn"),map.get("xq"),map.get("xh"),map.get("ssbh"),map.get("zskssj"),map.get("zsjssj"),map.get("lxyy"),map.get("lxdh")},request);
					    		}
					    	}else{//��ͨ��ʱ���	������У��Ϣ�����������Ϣɾ�������Ϣ				
					    		StandardOperation.delete("gygl_jqlxxxb",pk,pkValue, request);
					    	}
					    }
					}
					
				//}
			}else if(userType.equalsIgnoreCase("xy")){
				done = StandardOperation.update("gygl_jqlxsqb",new String[]{"xysh","xyshyy"},new String[]{yesNo,xyshyy},pk,pkValue,request);//�޸�ssydsqb�������״̬
			}
			
			if(done==true){
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}
		
		//��˽��
		String tablename="gygl_jqlxsqb";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
			tablename="gygl_jqlxxxb";
		}
		String[] shJg = dao.getOneRs("select "+shType+" yesNo,xyshyy,xxshyy from "+tablename+" where "+ pk + "=? ",new String[]{pkValue},new String[]{"yesNo","xyshyy","xxshyy"}); 
		if(shJg!=null){
			map.put("yesNo",shJg[0]);
			map.put("xyshyy",shJg[1]);
			map.put("xxshyy",shJg[2]);
		}
		request.setAttribute("rs",map);
		request.setAttribute("userType",dao.getUserType(request.getSession().getAttribute("userDep").toString()));
		request.setAttribute("pkValue",pkValue);		
		request.setAttribute("chkList", dao.getChkList(3));
	}
	public void sqJgCx(HttpServletRequest request,ActionForm form){		
		DAO dao = DAO.getInstance();
		String userType = request.getSession().getAttribute("userOnLine").toString();//�û�����
		String userName = request.getSession().getAttribute("userName").toString();//�û���¼��
		String sql = "";
		String xxdm = dao.getXxdm();
		request.setAttribute("xxdm", xxdm);
		Vector<Object> rsWx = new Vector<Object>();//��Ԣά�������ѯ�����
		Vector<Object> rsYd = new Vector<Object>();//�����춯�����ѯ�����
		Vector<Object> rsLx = new Vector<Object>();//������У�����ѯ�����
		Vector<Object> rsWz = new Vector<Object>();//ѧ����ס�����ѯ�����
		Vector<Object> rsJswmhd = new Vector<Object>();//ѧ����������������ѯ�����
		String[] colList = null;
		String[] colListCN = null;
		if("student".equalsIgnoreCase(userType)){
			xh = userName; 
			//��Ԣά��
			sql = " select (case when xxsh='ͨ��'then  '#FFFFFF' else '#CCCCCC'  end )color,xn,(select xqmc from xqdzb b where b.xqdm=xq)xq,ldmc,qsh,bxsj,wxnr,bxr,bxrxm,xxsh,xxshyy,wxsj,(select rymc from gywxryb b where b.rydm=a.wxry )wxry  from view_gywxglsqxx a where bxr=? order by bxsj desc";
			colList = new String[] { "color","xn","xq","ldmc","qsh","bxsj","wxnr","bxr","bxrxm","xxsh","xxshyy","wxsj","wxry"};
			colListCN = dao.getColumnNameCN(colList, "view_gywxglsqxx");
			List topTrWx = dao.arrayToList(colList, colListCN);
			rsWx.addAll(dao.rsToVator(sql, new String[]{xh}, colList));			
			request.setAttribute("rsWx",rsWx);
			request.setAttribute("topTrWx", topTrWx);
			request.setAttribute("numWx", String.valueOf(rsWx.size()));
			//�����춯
			sql = "select (case when xxsh='ͨ��'then  '#FFFFFF' else '#CCCCCC'  end )color, sqydsj,ydqldmc,ydqqsh,ldmc,qsh,xysh,xxsh,xyshyy,xxshyy from view_ssydsqxx where xh=? order by sqydsj desc ";
			colList = new String[] { "color","sqydsj","ydqldmc","ydqqsh","ldmc","qsh","xysh","xxsh" ,"xyshyy","xxshyy"};
			colListCN = dao.getColumnNameCN(colList, "view_ssydsqxx");
			List topTrYd = dao.arrayToList(colList, colListCN);
			rsYd.addAll(dao.rsToVator(sql, new String[]{xh},colList));
			request.setAttribute("rsYd",rsYd);
			request.setAttribute("topTrYd",topTrYd);
			request.setAttribute("numYd",String.valueOf(rsYd.size()));
			
			//������У
			colListCN = dao.getColumnNameCN(colList, "view_gygl_jqlxsq");
//			if(Globals.XXDM_XBEMY.equalsIgnoreCase(xxdm)){
			 sql = "select (case when a.xxsh='ͨ��' then  '#FFFFFF' else '#CCCCCC'  end )color,a.xh,a.xn,(select xqmc from xqdzb b where b.xqdm=a.xq)xq,(case when b.ldmc is null then " +
			 		"'��δ����' else b.ldmc end) ldmc,(case when b.qsh is null" +
			 		" then '��δ����' else b.qsh end) qsh,a.zskssj,a.zsjssj," +
			 		"a.lxdh,a.lxyy,(case when b.ssbh is null then '��δ����' else " +
			 		"b.ssbh end) ssbh,a.xxsh,a.xysh,a.xyshyy,a.xxshyy,(case when b.cwh is null " +
			 		" then '��δ����' else b.cwh end) cwh from gygl_jqlxsqb a left " +
			 		"join  view_gygl_jqlxxx b on a.xh=b.xh and a.xn=b.xn and " +
			 		"a.xq=b.xq where a.xh=? order by a.xn desc ";
			 colList=new String[]{"color","xn","xq","ldmc","qsh","zskssj","zsjssj","lxdh","lxyy","xysh","xxsh","xyshyy","xxshyy"};
			 colListCN=new String[]{"color","ѧ��","ѧ��","¥������","���Һ�","ס�޿�ʼʱ��","ס�޽���ʱ��","��ϵ�绰","��У����","Ժϵ���","ѧУ���",Base.YXPZXY_KEY+"���˵��","ѧУ���˵��"};			 
//			}else{
//				sql = "select xn,xq,xh,nj,xymc,zymc,bjmc,ldmc,qsh,zskssj,zsjssj,lxdh,lxyy,ssbh,xysh,xxsh,xyshyy,xxshyy from view_gygl_jqlxsq where xh=? order by zskssj desc ";
//				colList = new String[] { "xn","xq","ldmc","qsh","zskssj","zsjssj","lxdh","lxyy","ssbh","xysh","xxsh","xyshyy","xxshyy" };
//			}
			
			List topTrLx = dao.arrayToList(colList, colListCN);
			rsLx.addAll(dao.rsToVator(sql,new String[]{xh},colList));
			request.setAttribute("rsLx",rsLx);
			request.setAttribute("topTrLx", topTrLx);
			request.setAttribute("numLx",String.valueOf(rsLx.size()));
			
			//ѧ����ס������
			sql = "select  (case when xxsh='ͨ��'then  '#FFFFFF' else '#CCCCCC'  end )color,xn,(select xqmc from xqdzb b where b.xqdm=xq)xq,wzksrq,jhwzsj,wzlxdm,wzyy,wzdz,xxsh,xysh from gygl_xswzsqb where xh=? order by wzksrq asc ";
			colList = new String[] { "color","xn","xq","wzksrq","jhwzsj","wzlxdm","wzyy","wzdz","xxsh","xysh"};
			colListCN = dao.getColumnNameCN(colList, "gygl_xswzsqb");
            List topTrWz =  dao.arrayToList(colList, colListCN);
            rsWz.addAll(dao.rsToVator(sql,new String[]{xh},colList));
            request.setAttribute("rsWz",rsWz);
            request.setAttribute("topTrWz",topTrWz);
            request.setAttribute("numWz", String.valueOf(rsWz.size()));
            
            //ѧ���������������
    		sql = "select  (case when xxsh='ͨ��'then  '#FFFFFF' else '#CCCCCC'  end )color,hdmc,zbdw,hddd,hdksrq,hdjsrq,fzrxm,fzrlxfs,(case when hdnr is null then '' else substr(hdnr,1,5)||'...' end)hdnr,'Ժϵ��ˣ�'||xysh||'  '||'ѧУ��ˣ�'||xxsh shzt,sqsj from jswmhdsqb where sqrdlm=?";
    		colList   = new String[]{"color","hdmc","zbdw","hddd","hdksrq","hdjsrq","fzrxm","fzrlxfs","hdnr","shzt","sqsj" };
            colListCN = new String[]{"color", "�����","���쵥λ","��ص�","���ʼ����","���������","����������","��������ϵ��ʽ","�����","������","����ʱ��"};
            List topTrJswmhd =  dao.arrayToList(colList, colListCN);
            rsJswmhd.addAll(dao.rsToVator(sql,new String[]{xh},colList));
            request.setAttribute("rsJswmhd",rsJswmhd);
            request.setAttribute("topTrJswmhd",topTrJswmhd);
            request.setAttribute("numJswmhd", String.valueOf(rsJswmhd.size()));
                       	
            //�ܽ����¼��
            request.setAttribute("numCout",String.valueOf(rsWx.size()+rsYd.size()+rsLx.size()+rsWz.size()+rsJswmhd.size()));
		}else{		   
            request.setAttribute("isNotStu", "isNotStu");
		}
	}
	public void pubYj(HttpServletRequest request,ActionForm form,String userName) throws Exception{		
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");//�Ƿ񱣴����		
		String id = DealString.toString(request.getParameter("id"));//�����¼ID
		HashMap<String,String> map = new HashMap<String,String>(); 
		boolean done = false;
		String sql = "";
		if(!Base.isNull(doType)&&doType.equalsIgnoreCase("pub")){//����
			String title = DealString.toGBK(request.getParameter("title"));//�������
			String content = DealString.toGBK(request.getParameter("content"));//�������
			if(Base.isNull(id)){//�������
				sql = "insert into xsgygl_xsyjb(id,title,content,xh) values(S_GYGLXSYJ_ID.NEXTVAL,?,?,?)";
				done = dao.insert(sql,new String[]{title,content,userName});
				if(done){dao.writeLog(sql, new String[]{title,content,userName}, null,"��ѧ�����������¼", request);}//д����־
			}else{//�޸����
				sql = "update xsgygl_xsyjb set title=?,content=? where id=? ";
				done = StandardOperation.update("xsgygl_xsyjb", new String[]{"title","content"},new String[]{title,content},"id",id, request);
                id="";//��¼�޸ĺ�ID����ֵ���			
			}
			if(done==true){			    
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}else if(!Base.isNull(doType)&&doType.equalsIgnoreCase("modi")){
			map = dao.getMap(" select title,content from xsgygl_xsyjb where id=?",new String[]{id},new String[]{"title","content"});
		}else if(!Base.isNull(doType)&&doType.endsWith("del")){//ɾ������
			StandardOperation.delete("xsgygl_xsyjb","id",id, request);//ɾ��ѧ��������и�����¼
			StandardOperation.delete("xsgygl_xsyjhfb","id",id, request);//ɾ��ѧ������ظ����и�����¼
		}
		//�лظ���ѧ�������޸�ɾ������Ȩ��
		sql = "select id,title,content,pubdate,xh,(case when id in (select id from xsgygl_xsyjhfb) then '��' else '��' end ) ywhf from xsgygl_xsyjb where xh=? order by pubdate desc ";
		List yjList = dao.getList(sql,new String[]{userName},new String[]{"id","title","content","pubdate","xh","ywhf"});
		request.setAttribute("yjList", yjList);
		request.setAttribute("rs", map);
		request.setAttribute("yjid",id);
	}
	public void YjXxQur(HttpServletRequest request,ActionForm form,String userName) throws Exception{
		DAO dao = DAO.getInstance();
//		XsgyglForm dsForm = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		if(!Base.isNull(doType)&&doType.equalsIgnoreCase("del")){//ɾ�������¼
			String id = request.getParameter("id");
			StandardOperation.delete("xsgygl_xsyjb","id",id, request);//ɾ��ѧ��������и�����¼
			StandardOperation.delete("xsgygl_xsyjhfb","id",id, request);//ɾ��ѧ������ظ����и�����¼
		}
		String sql = "select id,title,content,pubdate,xh,(case when id in (select id from xsgygl_xsyjhfb) then '��' else '��' end ) ywhf from xsgygl_xsyjb order by pubdate desc ";
		List yjList = dao.getList(sql,new String[]{},new String[]{"id","title","content","pubdate","xh","ywhf"});
		request.setAttribute("rs", yjList);
	}
	public void viewYjXx(HttpServletRequest request,ActionForm form,String userName) throws Exception{
		DAO dao = DAO.getInstance();
		HashMap<String,String> mapa = new HashMap<String,String>();
		HashMap<String,String> mapb = new HashMap<String,String>();
		String id = request.getParameter("id");
		String sql = " select id,title,content,pubdate,xh from xsgygl_xsyjb where id=? ";
		mapa = dao.getMap(sql,new String[]{id},new String[]{"id","title","content","pubdate","xh"});
		sql = "select recontent,repubdate,yhm from xsgygl_xsyjhfb where id=? ";
		mapb = dao.getMap(sql, new String[]{id},new String[]{"recontent","repubdate","yhm"});
	    request.setAttribute("rsYj",mapa);
		request.setAttribute("rsRe",mapb);
	}
	public void yjHf(HttpServletRequest request,ActionForm form,String userName) throws Exception{
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String doType = request.getParameter("doType");//�Ƿ񱣴����
		String act = request.getParameter("act");//������
		String id = DealString.toString(request.getParameter("id"));//�����¼ID
		boolean done = false;
		if(!Base.isNull(act)&&act.equalsIgnoreCase("add")){
			map = dao.getMap(" select 'RE��'||title title from xsgygl_xsyjb where id=?",new String[]{id},new String[]{"title"});
		}else if(!Base.isNull("modi")){
			map = dao.getMap(" select 'RE��'||a.title title,b.recontent content from xsgygl_xsyjb a right join xsgygl_xsyjhfb b on a.id=b.id and a.id=?",new String[]{id},new String[]{"title","content"});
		}		
		if(!Base.isNull(doType)&&doType.equalsIgnoreCase("save")){//�ظ����ݱ���
			String recontent = DealString.toGBK(request.getParameter("content"));
			if(act.equalsIgnoreCase("add")){//����ظ�
				done = StandardOperation.insert("xsgygl_xsyjhfb", new String[]{"id","recontent","yhm"},new String[]{id,recontent,userName}, request);
			}else if(act.equalsIgnoreCase("modi")){//����ظ��޸�
				done = StandardOperation.update("xsgygl_xsyjhfb", new String[]{"recontent","yhm"},new String[]{recontent,userName},"id",id, request);
			}
			if(done==true){			    
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}	
		request.setAttribute("rs", map);
		request.setAttribute("act",act);
		request.setAttribute("id",id);
	}
	public void xsGyGL_LsSj(HttpServletRequest request,ActionForm form) throws Exception{
		DAO dao = DAO.getInstance();
		List<String[]>rs=new ArrayList<String[]>();
		StringBuffer querry = new StringBuffer();//��ѯ����
		String xsbz = DealString.toGBK(request.getParameter("xsbz"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		XsgyglForm xsgyForm = (XsgyglForm)form;
		xsgyForm.setXsbz(xsbz);
		xsgyForm.setXb(xb);
		String xxdm = dao.getXxdm();
		String rsNum = "0";//��¼��
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"'");
		querry.append(Base.isNull(xy)?"":" and xydm='"+xy+"'");
		querry.append(Base.isNull(zy)?"":" and zydm='"+zy+"'");
		querry.append(Base.isNull(bj)?"":" and bjdm='"+bj+"'");
		querry.append(Base.isNull(xh)?"":" and xh like '%'||?||'%' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%'||?||'%' ");
		querry.append(Base.isNull(xsbz)?"":" and xsbz like '"+xsbz+"'");
		querry.append(Base.isNull(xb)?"":" and xb = '"+xb+"'");
		String[] colList = new String[] { "xh", "xm", "nj", "xb", "xymc","zymc","bjmc" };//��ʾ�ֶ�
		String[] colListCN = dao.getColumnNameCN(colList, "view_xszslsxx");//��ʾ�ֶ�ע��
		String sql = "select a.* from (select distinct xh,rownum r, xm,nj,xb,xymc,zymc,bjmc from view_xszslsxx "+ querry+") a ";
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			colList = new String[] { "xh", "xm", "nj", "xb", "xymc","zymc","bjmc","xsbz" };//��ʾ�ֶ�
			colListCN = dao.getColumnNameCN(colList, "view_xszslsxx");//��ʾ�ֶ�ע��
			sql = " select a.* from ( distinct xh,rownum r, xm,nj,xb,xymc,zymc,bjmc,xsbz from view_xszslsxx "+ querry+") a ";
		}
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			String[] inputValue = new String[]{};
			
			if (!Base.isNull(xh) && !Base.isNull(xm)) {
				inputValue = new String[] { xh, xm };
			} else if (!Base.isNull(xh)) {
				inputValue = new String[] { xh };
			} else if (!Base.isNull(xm)) {
				inputValue = new String[] { xm };
			}
			
			rs=CommonQueryDAO.commonQuery(sql, "", inputValue, colList, xsgyForm);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}			
		}	
		gyglDao.getXyZyBjxx(request);
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("tableName","view_xszslsxx");
		request.setAttribute("xxdm",xxdm);
		request.setAttribute("realTable","xszslsxxb");
	}
	public void viewGyLsSj(HttpServletRequest request,ActionForm form) throws SQLException{
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String sql = " ";
		map = dao.getMap(" select xh,xm from view_xsjbxx where xh=? ",new String[]{xh},new String[]{"xh","xm"});
		request.setAttribute("rsStu", map);       
		sql = "select xh,xm,ssbh,ldmc,qsh,cwh,rzrq,tfrq,sfbz from view_xszslsxx where xh=? order by rzrq";			
		List zslsList = dao.getList(sql, new String[]{xh}, new String[]{"xh","xm","ssbh","ldmc","qsh","cwh","rzrq","tfrq","sfbz"});
		request.setAttribute("zslsList",zslsList);//��ס�����
	}
	public void fykBbtj(HttpServletRequest request,ActionForm form,HttpServletResponse response) throws IOException, WriteException{
		DAO dao = DAO.getInstance();
		String sql = "";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("��Դ��ͳ��", 0);
		
		WritableFont wf = new WritableFont(WritableFont.TIMES); // ���������ʽ
		WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wf.setBoldStyle(WritableFont.NO_BOLD); // ���������ʽ(�Ǵ���)
		wf.setColour(Colour.getInternalColour(20)); // ���������ʽ(��ɫ)
		wf.setUnderlineStyle(UnderlineStyle.NO_UNDERLINE); // ���������ʽ(���»���)
		wf.setPointSize(13); // ���������ʽ(��С)
		wcf.setFont(wf); // ����ָ�������ʽ�ĵ�Ԫ���ʽ
		wcf.setAlignment(Alignment.CENTRE); // ָ����ʽ�����ַ����Ҿ���
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // ָ����ʽ�����ַ����¾���
		//wcf.setWrap(true); // ָ����ʽ�����Զ�����
		ws.setColumnView(0, 12); // ����1�е��п�
		ws.setColumnView(1, 12); // ����2�е��п�
		ws.setColumnView(2, 12); // ����3�е��п�
		ws.setColumnView(3, 12); // ����4�е��п�
		ws.setColumnView(4, 40); // ����5�е��п�
	    
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2.setVerticalAlignment(VerticalAlignment.CENTRE); // ָ����ʽ�����ַ����¾���
		
		ws.addCell(new Label(0, 0,"¥��",wcf)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(1, 0,"������",wcf));
		ws.addCell(new Label(2, 0,"�մ�λ��",wcf));
		ws.addCell(new Label(3, 0,"��ס����",wcf));
		ws.addCell(new Label(4, 0,"��ס��ѧ�š�����",wcf));		
		
		List<HashMap<String, String>> ldList = dao.getList(" select lddm,ldmc from sslddmb  order by  lddm  ", new String[]{}, new String[]{"lddm","ldmc"}); // ��ȡ¥���б�
		int nextNumV1 = 0;
		int nextNumV2 = 1;
		for(int i=0;i<ldList.size();i++){//ѭ������ÿ��¥
			String lddm = ldList.get(i).get("lddm");
			String ldmc = ldList.get(i).get("ldmc");
			sql = "select count(*) cout from ssxxb where lddm=? ";
			String sscout_ald = dao.getOneRs(sql,new String[]{lddm}, "cout");
			Integer.parseInt(sscout_ald);						
			if(i==0){// ����1��2����ʼ����ϲ���Ԫ��
				ws.mergeCells(0,1,0,Integer.parseInt(sscout_ald)); 
			}else{//�����ϴκϲ���Ϊ�������ϲ���Ԫ��
				String sscout_ald_last = dao.getOneRs(sql,new String[]{ldList.get(i-1).get("lddm")}, "cout");
				nextNumV1 += Integer.parseInt(sscout_ald_last);
				ws.mergeCells(0,nextNumV1-Integer.parseInt(sscout_ald_last)+1,0,nextNumV1);				
			}
			
			ws.addCell(new Label(0,nextNumV1+1,ldmc,wcf2));//����һ��¥������
			
			sql = "select ssbh from ssxxb where lddm=? order by ssbh";
			List<HashMap<String, String>> ssbhList = dao.getList(sql, new String[]{lddm},new String[]{"ssbh"});
			
			StringBuffer sqlV = new StringBuffer();//��ѯ��ס��Ա
			sqlV.append(" select ssbh, cy from ( select ssbh, max(ltrim(sys_connect_by_path(xh||xm,'��'),'��')) cy from ( ");
			sqlV.append(" select distinct xh,xm,ssbh, row_number() over(partition by ssbh order by xh ) pno,  ");
			sqlV.append(" row_number() over(partition by ssbh order by xh )-1 sno ");
			sqlV.append(" from view_xszsxx ) a start with pno='1' connect by prior pno = sno and prior ssbh=ssbh");
			sqlV.append(" group by ssbh order by ssbh ) where ssbh = ?");		
			for(int j=0;j<ssbhList.size();j++){//ѭ������ÿ��¥����
				String ssbh = ssbhList.get(j).get("ssbh");
				ws.addCell(new Label(1,nextNumV2,ssbh));
				
				String yzrs = dao.getOneRs(" select count(*) cout from xszsxxb where ssbh = ?",new String[]{ssbh},"cout");
				String zcws = dao.getOneRs(" select nvl(cws,'0')cws from ssxxb where ssbh=? ",new String[]{ssbh},"cws");
				String kycw = String.valueOf(Integer.parseInt(Base.isNull(zcws)?"0":zcws)-Integer.parseInt(Base.isNull(yzrs)?"0":yzrs));
				
				ws.addCell(new Label(2,nextNumV2,kycw));
				ws.addCell(new Label(3,nextNumV2,yzrs));
				
				String cy = dao.getOneRs(sqlV.toString(),new String[]{ssbh},"cy");//�����Ա 
				ws.addCell(new Label(4,nextNumV2,cy));
				
				nextNumV2++;//��Ԫ�����
			}
		}
		wwb.write();
		wwb.close();
	}
	public void jQlXsPb(HttpServletRequest request,ActionForm form){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String pkValue = request.getParameter("pkValue");
		String sql = "";
		String csnl = "";
		if(!Base.isNull(pkValue)){//
			sql =" select (select lydq from view_stu_details a where a.xh=xh and rownum=1 )lydq,(select ssbh from xszsxxb a where a.xh=xh and rownum=1 )ssbh," +
				 "substr((select csrq from view_stu_details a where a.xh=xh and rownum=1),1,4)csnd, xn,xq,xh,xm,xb,nj,xymc,zymc,bjmc,ldmc," +
				 "qsh,zskssj,zsjssj,lxdh,lxyy,ssbh,ldmc,qsh from view_gygl_jqlxsq where xn||xq||xh=? ";
			map = dao.getMap(sql,new String[]{pkValue},new String[]{"lydq","csnd","xn","xq","xh","xm","xb","nj","xymc","zymc","bjmc","ldmc","qsh","zskssj","zsjssj","lxdh","lxyy","ssbh","ldmc","qsh"});				
			csnl = String.valueOf(Integer.parseInt(Base.currNd)-Integer.parseInt(Base.isNull(map.get("csnd"))?"0":map.get("csnd")));
		}else if(!Base.isNull(xh)){//
			sql ="select (select lydq from view_stu_details a where a.xh=xh and rownum=1 )lydq, "
				+"substr((select csrq from view_stu_details a where a.xh=xh and rownum=1),1,4)csnd, "
				+"xh,xm,xb,nj,xymc,zymc,bjmc,xz,ssbh from view_xsjbxx where xh=?";
			map = dao.getMap(sql,new String[]{xh}, new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","xz","ssbh","lydq","csnd"});	
			csnl = String.valueOf(Integer.parseInt(Base.currNd)-Integer.parseInt(Base.isNull(map.get("csnd"))?"0":map.get("csnd")));
			map.put("lxyy", DealString.toGBK(request.getParameter("lxyy")));
			map.put("lxdh",request.getParameter("lxdh"));
		}
		if(csnl.equalsIgnoreCase(Base.currNd)){
			csnl = "";
		}
		map.put("nd",csnl);
		map.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", map);
	}
	public void sSyDsPb(HttpServletRequest request,ActionForm form){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String pkValue = request.getParameter("pkValue");
		String  sql = "";
		String csnl = "";
		if(!Base.isNull(pkValue)){//
			sql = "select (select lydq from view_stu_details a where a.xh=xh and rownum=1 )lydq, " +
					" substr((select csrq from view_stu_details a where a.xh=xh and rownum=1),1,4)csnd, " +
					"xh,xm,xb,nj,xymc,zymc,bjmc,ydqssbh ssbh,ydhssbh,ydly from view_ssydsqxx where xh||sqydsj=?  ";
			map = dao.getMap(sql,new String[]{pkValue},new String[]{"lydq","csnd","xh","xm","xb","nj","xymc","zymc","bjmc","ssbh","ydhssbh","ydly"});
			csnl = String.valueOf(Integer.parseInt(Base.currNd)-Integer.parseInt(Base.isNull(map.get("csnd"))?"0":map.get("csnd")));
		}else if(!Base.isNull(xh)){//
			sql = "select (select lydq from view_stu_details a where a.xh=xh and rownum=1 )lydq," 
					+"substr((select csrq from view_stu_details a where a.xh=xh and rownum=1),1,4)csnd, "
			        +"xh,xm,xb,nj,xymc,zymc,bjmc,xz,ssbh,lxdh from view_xsjbxx where xh=?";
			map = dao.getMap(sql,new String[]{xh}, new String[]{"lydq","csnd","xh","xm","xb","nj","xymc","zymc","bjmc","xz","ssbh"});		      				
			map.put("ydly", DealString.toGBK(request.getParameter("ydly")));
			csnl = String.valueOf(Integer.parseInt(Base.currNd)-Integer.parseInt(Base.isNull(map.get("csnd"))?"0":map.get("csnd")));
			
		}
		if(csnl.equalsIgnoreCase(Base.currNd)){
			csnl = "";
		}
		map.put("nl",csnl);
		map.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", map);
	}
	public boolean plDelete(HttpServletRequest request) throws Exception{
		DAO dao = DAO.getInstance();
//		String tabName = request.getParameter("tableName");
		String realTable = request.getParameter("realTable");
		String toHis = DealString.toGBK(request.getParameter("toHistory"));
		String tssj  = request.getParameter("tssjv");
		String delPk = request.getParameter("delPk");
		String pk = request.getParameter("pk");
		String sfsf = request.getParameter("sfsf");
		String[] pkValueA = delPk.split("!!");
		String sql = "";
		String[] instPkSql = null;
		String[] delPkSql  = new String[pkValueA.length];
		StringBuffer instSqlStr = new StringBuffer();
		for(int i=0;i<pkValueA.length;i++){
			sql = " insert into xszslsxxb(xh,ssbh,bz,cwh,rzrq,tfrq,xm,xb,nj,xymc,zymc,bjmc,ldmc,qsh,sfbz) " 
				+" select xh,ssbh,bz,cwh,rzrq,'"+tssj+"'tfrq,xm,xb,nj,xymc,zymc,bjmc,yqmc||ldmc,qsh,sfbz from view_xszsxx where "+pk+"='"+pkValueA[i]+"'";
			instSqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");						
		}
		instPkSql = instSqlStr.toString().split("!!");
		 //-----------------2010.12.6 qph----ѧ�����޺��ͷŷ�Դ��Ϣ-----------------
		StringBuilder querySql = new StringBuilder();
		querySql.append("select distinct a.ssbh from xszsxxb a where xh in (");
		  //-----------------2010.12.6 qph--end-------------------
		
	    for (int i = 0; i < pkValueA.length; i++) {
	    	delPkSql[i] = Base.isNull(pkValueA[i])?"":"delete "+realTable+" where "+pk+"= '"+pkValueA[i]+"'";	
	    	
	    	 //-----------------2010.12.6 qph---------------------
	    	if (!Base.isNull(pkValueA[i])) {
	    		querySql.append("'")
	    				.append(pkValueA[i]);
	    		
	    		if (i==pkValueA.length-1) {
	    			querySql.append("')");
	    		} else {
	    			querySql.append("',");
	    		}
	    		
	    	}
	    }              
	
	    String[] ssbhArr = dao.getArray(querySql.toString(), new String[] {}, "ssbh");
	    //-----------------2010.12.6 qph--end-------------------
	    
	    boolean doFlag = false;
        if(toHis.equalsIgnoreCase("yes")){//����ѧ��ס����Ϣɾ����ת�Ƶ�ס����ʷ��Ϣ����   
        	String[] exesql = dao.unionArray(instPkSql, delPkSql);
        	int[] array = dao.runBatch(exesql);
        	doFlag = dao.checkBatch(array);    	
        }else{
        	int[] array = dao.runBatch(delPkSql);
        	doFlag = dao.checkBatch(array);   
        }
        
        //-----------------2010.12.6 qph---------------------
        if ("yes".equals(sfsf) && null != ssbhArr && ssbhArr.length>0) {
        	
        	String[] delSqlArr = new String[ssbhArr.length];
        	
        	for (int i = 0 ; i < ssbhArr.length ; i++) {
        		StringBuilder delSql = new StringBuilder();
        		
        		delSql.append("delete from ssfpb a where ssbh='")
        			  .append(ssbhArr[i])
        			  .append("'  and (select count(1) from xszsxxb where ssbh='")
        			  .append(ssbhArr[i])
        			  .append("')=0");
        		delSqlArr[i] = delSql.toString();
        	}
        	int[] result = dao.runBatch(delSqlArr);
        	doFlag = dao.checkBatch(result);
        }
        //-----------------2010.12.6 qph--end-------------------
        
        String msg = doFlag ? "���޳ɹ���" : "����ʧ�ܣ�";
        request.setAttribute("msg", msg);
        
		return doFlag;
	}
	public void xsZsXxToHis(HttpServletRequest request) throws Exception{
		DAO dao = DAO.getInstance();
		String sfsf = request.getParameter("sfsf");//�Ƿ��ͷŷ�Դ
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xy)?"":" and xydm='"+xy+"' ");
		querry.append(Base.isNull(zy)?"":" and zydm='"+zy+"' ");
		querry.append(Base.isNull(bj)?"":" and bjdm='"+bj+"' ");
		querry.append(Base.isNull(pycc)?"":" and pycc='"+pycc+"' ");
		boolean done = false;
		String tableName = "view_xszsxx";
		//String tableName2 = "view_xsjbxx";
		//=============2010.11.27 edit by luojw=======================
		String tableName2 = "view_xsxxb";
	    String	userName = request.getSession().getAttribute("userName").toString();
	    String tssj = request.getParameter("tssjv"); 
		if(GyglShareDAO.isSssAdmin(userName)){
			tableName  = "view_ssszsxx";
			tableName2  = "sss_xxb";
			
		}
		String sql = "insert into xszslsxxb(xh,ssbh,bz,cwh,rzrq,tfrq,xm,xb,nj,xymc,zymc,bjmc,ldmc,qsh,sfbz)" +
				" select xh,ssbh,bz,cwh,rzrq,'"+tssj+"'tfrq,xm,xb,nj,xymc,zymc,bjmc,yqmc||ldmc,qsh,sfbz from  "+tableName+" "+querry.toString();
		done = dao.runProcuder(sql, new String[]{});
		
		 //-----------------2010.12.6 qph----ѧ�����޺��ͷŷ�Դ��Ϣ-----------------
		StringBuilder querySql = new StringBuilder();
		querySql.append("select distinct a.ssbh from xszsxxb a where xh in (")
				.append("select xh from ")
				.append(tableName2)
				.append(querry)
				.append(")");
		 String[] ssbhArr = dao.getArray(querySql.toString(), new String[] {}, "ssbh");
		  //-----------------2010.12.6 qph--end-------------------
		
		if(done){
			dao.writeLog(sql, new String[]{},new String[]{},"��ѧ��ס����Ϣ��������"+querry.toString()+"������ѧ��ס����Ϣ����ѧ��ס����ʷ��Ϣ��xszslsxxb����", request);
			sql = "delete from xszsxxb where xh in (select xh from "+tableName2+querry+")";
			done = dao.runUpdate(sql, new String[]{});
			if(done){
				dao.writeLog(sql,new String[]{},new String[]{},"ɾ��ѧ��ס����Ϣ��������"+querry.toString()+"������ѧ��ס����Ϣ",request);
			}
		}
		
		 //-----------------2010.12.6 qph---------------------
        if ("yes".equals(sfsf) && null != ssbhArr && ssbhArr.length>0) {
        	
        	String[] delSqlArr = new String[ssbhArr.length];
        	
        	for (int i = 0 ; i < ssbhArr.length ; i++) {
        		StringBuilder delSql = new StringBuilder();
        		
        		delSql.append("delete from ssfpb a where ssbh='")
        			  .append(ssbhArr[i])
        			  .append("'  and (select count(1) from xszsxxb where ssbh='")
        			  .append(ssbhArr[i])
        			  .append("')=0");
        		delSqlArr[i] = delSql.toString();
        	}
        	int[] result = dao.runBatch(delSqlArr);
        	done = dao.checkBatch(result);
        }
        //-----------------2010.12.6 qph--end-------------------
        
		
		
		String msg = done ? "�������޳ɹ���" : "��������ʧ�ܣ�";
		request.setAttribute("msg", msg);
	}
	/**���ص�¼�û������Ϣ*/
	public HashMap<String,String> getUserInfo(String userType,String sUName){
		DAO dao    = DAO.getInstance();
		String sql = "";
		HashMap<String,String> map = new HashMap<String,String>();
		if(userType!=null&&userType.equalsIgnoreCase("stu")){
			sql = "select xh,xm,xb,nj,xymc,bjmc,zymc,sjhm,lxdzxx,lxdh from view_xsjbxx where xh=?";
			map = dao.getMap(sql,new String[]{sUName},new String[]{"xh","xm","xb","nj","xymc","bjmc","zymc","sjhm","lxdzxx","lxdh"});
		}
		return map;
	}
	/**ѧ����ס������Ϣ����*/
    public boolean xsZdSqSave(HttpServletRequest request) throws Exception{
    	boolean done = false;
    	String realTable = "gygl_xswzsqb";
    	String primaryKey = "xh||xn||xq||wzksrq";
    	String wzksrq = request.getParameter("wzksrq");
    	String jhwzsj = DealString.toGBK(request.getParameter("jhwzsj"));
    	String wzlxdm = DealString.toGBK(request.getParameter("wzlxdm"));
    	String wzdz   = DealString.toGBK(request.getParameter("wzdz"));
    	String wzyy   = DealString.toGBK(request.getParameter("wzyy"));
//    	String lxfs   = DealString.toGBK(request.getParameter("lxfs"));
    	String jzsfty = DealString.toGBK(request.getParameter("jzsfty"));
    	done = StandardOperation.delete(realTable, primaryKey, xh+xn+xq+wzksrq, request);//ɾ����¼
    	if(done){
    		done = StandardOperation.insert(realTable, new String[]{"xh","xn","xq","wzksrq","jhwzsj","wzlxdm","wzyy","wzdz","jzsfty"},
    			   new String[]{xh,xn,xq,wzksrq,jhwzsj,wzlxdm,wzyy,wzdz,jzsfty}, request);//����
    	}
    	return done;
    }
    /**�߶�(��ס)������˲�ѯ*/
	public void xsZdSqShXx(HttpServletRequest request,ActionForm form){
		DAO dao = DAO.getInstance();
		XsgyglForm xsgyForm = (XsgyglForm)form;
		StringBuffer querry = new StringBuffer();//��ѯ����
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		Vector<Object> rs = new Vector<Object>();
		String go = request.getParameter("go");//��ѯ��־
		String rsNum = "";//��¼��
		String pk = "xh||xn||xq||wzksrq";//�ؼ��ֶ�
		String tableName = "view_xswzsqxx";//��ͼ
		String realTable = "gygl_xswzsqb";
		List topTr = null;//�ֶ�˵��List
		String shzt = DealString.toGBK(xsgyForm.getYesNo());//���״̬��־
		xsgyForm.setYesNo(shzt);
		String wzlxdm = xsgyForm.getWzlxdm();
		String wzksrq = xsgyForm.getWzksrq();
		if(Base.isNull(go)){//Ĭ�ϵ�ǰѧ�ꡢ���
			xn = Base.currXn;
			xq = Base.currXq;
			xsgyForm.setXn(xn);
			xsgyForm.setXq(xq);
		}
		if(userType.equalsIgnoreCase("xy")){
			xy = userDep;
			xsgyForm.setXydm(xy);
		}
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(zy)?"":" and zydm='"+zy+"' ");
		querry.append(Base.isNull(bj)?"":" and bjdm='"+bj+"' ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
		querry.append(Base.isNull(wzksrq)?"":" and wzksrq='"+wzksrq+"'");
		querry.append(Base.isNull(wzlxdm)?"":" and wzlxdm='"+wzlxdm+"'");
		
		if(!Base.isNull(go)
				&&"go".equalsIgnoreCase(go)){//��ѯ
			String[] colList = { "bgcolor","����","xh","xn", "xq", "xb", "xm", "nj","zymc", "bjmc","wzksrq", "jhwzsj","wzlxmc","wzdz","xxsh"};				
			StringBuffer sql = new StringBuffer();
			if(userType.equalsIgnoreCase("xx")
					||userType.equalsIgnoreCase("admin")){
				querry.append(Base.isNull(shzt)?"":" and xxsh='"+shzt+"' ");
				querry.append(" and xysh='ͨ��'") ;
				sql.append(" select (case when nvl(xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
				sql.append(pk+" ���� ,xh,xn, xq, xb, xm, nj,zymc, bjmc,wzksrq, jhwzsj,wzlxmc,wzdz,xxsh from "+tableName+querry+" order by xxsh desc,wzksrq desc ");
			}else if(userType.equalsIgnoreCase("xy")){
				querry.append(Base.isNull(shzt)?"":" and xysh='"+shzt+"' ");
				sql.append(" select (case when nvl(xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
				sql.append(pk+" ���� ,xh,xn, xq, xb, xm, nj,zymc, bjmc,wzksrq, jhwzsj,wzlxmc,wzdz,xysh from "+tableName+querry+" order by xysh desc,wzksrq desc  ");
				colList = new String[]{ "bgcolor","����","xh","xn", "xq", "xb", "xm", "nj","zymc", "bjmc","wzksrq", "jhwzsj","wzlxmc","wzdz","xysh"};				
			}
			
			String[] colCNList = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colCNList);			
			rs.addAll(dao.rsToVator(sql.toString(), new String[] {}, colList));
			request.setAttribute("rs", rs);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}	
		}
		gyglDao.getXnxqList(request);//ѧ��ѧ��List
		gyglDao.getXyZyBjxx(request);//ѧԺרҵ�༶List
		request.setAttribute("wzlxList",gyglDao.getWzlxList());//��ס�����б�
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum",rsNum);
		request.setAttribute("chkList", dao.getChkList(3));//���״̬�б�
		request.setAttribute("tableName",tableName);
		request.setAttribute("realTable",realTable);
	}
	/**ѧ���߶�(��ס)���������Ϣ��ʾ������
	 * @throws Exception */
	public void xsZdSqSh(HttpServletRequest request) throws Exception{
		DAO dao = DAO.getInstance();
		String userType = request.getSession().getAttribute("userType").toString();
		HashMap<String,String> map = new HashMap<String,String>(); 
	    String pkValue = request.getParameter("pkValue");//����ֵ
	    String doType = request.getParameter("doType");//��������
	    String sql = "";
	    String pk = "xh||xn||xq||wzksrq";//�ؼ��ֶ�
	    String TableName = "view_xswzsqxx";
	    String realTable = "gygl_xswzsqb";
	    boolean done = false;	
	    String shType = "";
	    if(userType.equalsIgnoreCase("xx")
				||userType.equalsIgnoreCase("admin")){
	    	shType = "xxsh";
	    }else{
	    	shType = "xysh";
	    }
		//��������ֵ��	    
	    sql = " select xh,xn,xq,xm,xb,nj,xymc,zymc,bjmc,wzksrq,jhwzsj,wzlxdm,wzlxmc,wzyy,wzdz,jzsfty,sjhm,lxdh,lxdzxx,"+shType+" yesNo from "+TableName+" where "+pk+"=? ";
		map = dao.getMap(sql,new String[]{pkValue},new String[]{"xh","xn","xq","xm","xb","nj","xymc","zymc","bjmc","wzksrq","jhwzsj","wzlxdm","wzlxmc","wzyy","wzdz","jzsfty","sjhm","lxdh","lxdzxx","yesNo"});
		request.setAttribute("rs",map);
//		���ݱ���
		if(!Base.isNull(doType)&&"save".equalsIgnoreCase(doType)){
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));	
			if(userType.equalsIgnoreCase("xx")
					||userType.equalsIgnoreCase("admin")){
				done = StandardOperation.update(realTable,new String[]{"xxsh"},new String[]{yesNo},pk,pkValue,request);//�޸�ssydsqb�������״̬
				if(done){
					if(yesNo.equalsIgnoreCase("ͨ��")){//ͨ��ʱ�����������Ϣ������סѧ����Ϣ����							
						boolean del = StandardOperation.delete("gygl_xswzxxb", pk,pkValue, request);
						if(del){
							boolean ins = StandardOperation.insert("gygl_xswzxxb",new String[]{"xh","wzlxdm","wzyy","wzdz","xn","xq","wzksrq","jhwzsj"},
									new String[]{map.get("xh"),map.get("wzlxdm"),map.get("wzyy"),map.get("wzdz"),map.get("xn"),map.get("xq"),map.get("wzksrq"),map.get("jhwzsj")},request);
							if(ins){
								dao.runUpdate("delete from xszsxxb where xh=? ",new String[]{map.get("xh")});
							}
						}
					}else{//��ͨ��ʱ���	��סѧ����Ϣ�����������Ϣɾ�������Ϣ				
						StandardOperation.delete("gygl_xswzxxb",pk,pkValue, request);
					}
				}
			}else if(userType.equalsIgnoreCase("xy")){
				done = StandardOperation.update(realTable,new String[]{"xysh"},//�޸�ssydsqb�������״̬
						new String[]{yesNo},pk,pkValue,request);
			}			
			if(done){
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}			
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("chkList", dao.getChkList(3));
	}
	
	public void jqlxPrint(HttpServletRequest request) throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pk"));

		sql = "select a.xn, a.xq,(case when a.xq='01' then '��' else '��' end) xqmc,a.xh,a.xb,a.xm,a.nj,a.xymc,a.zymc,a.bjmc,b.ssbh,a.zskssj,a.zsjssj,"
				+ " a.lxdh,a.lxyy,b.bz,b.cwh from view_hh_gygl_jqlxsq a,gygl_jqlxxxb b where a.xn||a.xq||a.xh =? "
				+ " and a.xn||a.xq||a.xh = b.xn||b.xq||b.xh";
		String[] colList = new String[] { "xn", "xq", "xqmc", "xh", "xb", "xm",
				"nj", "xymc", "zymc", "bjmc", "ssbh", "zskssj", "zsjssj",
				"lxdh", "lxyy", "bz","cwh" };
		map = dao.getMap(sql, new String[] { pkValue }, colList);
		String xh = map.get("xh");
		
		HashMap<String, String> tempmap1 = dao.getMap(
				"select * from xszsxxb where xh=?", new String[] { xh },
				new String[] { "ssbh", "cwh" });
		String ssbh = tempmap1.get("ssbh");
		String yssbh="";
		if(ssbh!=null&&!"".equals(ssbh)){
		String cwh = tempmap1.get("cwh");
		String ybh[] = ssbh.split("-");
		yssbh = ybh[0] + "��¥" + ybh[1] + "/" + cwh + "��";
		}
		ssbh = map.get("ssbh");
		if(ssbh!=null&&!"".equals(ssbh)){
		String[] bh = ssbh.split("-");
		String cwh=map.get("cwh");
		ssbh = bh[0] + "��¥" + bh[1]+ "/" + cwh + "��";;
		}
		
		map.put("ssbh", ssbh);
		map.put("yssbh", yssbh);

		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("rs", map);

	}
	public HashMap<String,String> getWxDxx (String pkValue){
		DAO dao = DAO.getInstance();
		String pk = "ssbh||bxsj";
		String sql = " select xn,(select xqmc from xqdzb where xqdm=xq)xq,ldmc,qsh,bxsj,wxnr,bxr,bxrxm,xxshyy,wxsj,(select rymc from gywxryb b where b.rydm=a.wxry )wxry,lxfs  from view_gywxglsqxx a where "+pk+"=? ";
		String[] colList = new String[] { "xn","xq","ldmc","qsh","bxsj","wxnr","bxr","bxrxm","xxshyy","wxsj","wxry","lxfs"};
		return dao.getMap(sql,new String[]{pkValue},colList);
	}
	public ArrayList<String[]> getNjrzsj (String nj){
		DAO dao = DAO.getInstance();
		String sql = "";
		if(Base.isNull(nj)){
			sql = "select nj,rzsj from njrzsj order by nj";
		}else{
			sql = "select nj,rzsj from njrzsj where nj='"+nj+"'";
		}
		return dao.rsToVator2(sql, new String[]{}, new String[]{"nj","rzsj"});
	}
	public String saveNjrzsj (String nj,String rzsj){
		DAO dao = DAO.getInstance();
		String sql = "delete from njrzsj where nj=?";
		boolean flag = false;
		try{
			flag = dao.runUpdate(sql, new String[]{nj});
			if(flag && !Base.isNull(rzsj)){
				sql = "insert into njrzsj(nj,rzsj) values (?,?)";
				flag = dao.runUpdate(sql,  new String[]{nj,rzsj});
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return flag == true? "yes":"no";
	}
	public boolean xssjTb() throws Exception {
		DAO dao = DAO.getInstance();
		String nj = Base.currNd;
		String sql = "{call gygl_zsxxtb(?)}";
		boolean res = dao.runProcuder(sql,new String []{nj});
		return res;
	}
}
