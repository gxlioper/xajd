package xgxt.action.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.action.base.BaseDAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.utils.String.StringUtils;
import common.Globals;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ϵͳ����ģ��Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-04-28</p>
 */
public class SystemService {
	BaseDAO dao = new BaseDAO();
	/**
	 * ��ȡ����ά����ѯ��Ϣsql
	 * @param tName
	 * @param tTem
	 * @return String
	 * */
	@SuppressWarnings("unchecked")
	public String getQuerrySQL(String tName,String tTem){
		HashMap<String, String> map = (HashMap<String, String>)dao.getPkOfTable(tName).get(0);
		String pk = map.get("pk");
		String xxdm = StandardOperation.getXxdm();
		String openFlag = Base.initProperties.get("codeByDept");
		String sql = "";
		
		if(openFlag != null && "true".equalsIgnoreCase(openFlag)){
			sql = "select rownum �к�,a.*,(select xm from yhb where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))�����,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))����,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')���ʱ�� from " + tName + " a";
		
			if (tName.equalsIgnoreCase("xljk_stsslbdmb")) {
				sql = "select rownum �к�,a.SSLXDM,a.SSLXMC,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')�����,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))����,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" )���ʱ�� from " + tName + " a";
			}
			if ("sztz_xmdmb".equalsIgnoreCase(tName)) {
				sql = "select rownum �к�,a.*,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')�����,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))����,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')���ʱ�� from view_sztz_xmdmb a";
			}
			if ("sztz_sqsblyb".equalsIgnoreCase(tName)) {
				sql = "select rownum �к�,a.* ,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')�����,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))����,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')���ʱ�� from view_sztz_sqsblyb a";
			}
			if ("sztz_hjjbdmb".equalsIgnoreCase(tName)) {
				sql = "select rownum �к�,a.*,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')�����,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))����,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')���ʱ�� from view_sztz_hjjbdmb a";
			}
			if ("yrdwdmb".equalsIgnoreCase(tName)) {
				tName = "view_yrdwdmb";
				sql = "select rownum �к�,a.yrdwdm,a.yrdwmc,a.lxr,a.lxdh,a.dwlb,a.ssxq,a.xymc, a.dlm,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')�����,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))����,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')���ʱ�� from view_yrdwdmb a";
			}
			if ("gywsjcbmb".equalsIgnoreCase(tName)) {
				sql = "select rownum �к�,a.bmdm,a.bmmc,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')�����,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))����,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')���ʱ�� from gywsjcbmb a ";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				if ("yrdwdmb".equalsIgnoreCase(tName)) {
					sql = "select rownum �к�, a.yrdwdm,a.yrdwmc,a.lxr,a.lxdh,a.dwlb,a.ssxq,a.xydm,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')�����,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))����,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')���ʱ�� from view_yrdwdmb a ";
				}
			}
			if ("rcgl_dmwhb".equalsIgnoreCase(tName)) {
				sql = "select rownum �к�,a.MKDM,a.MKMC,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')�����,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))����,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')���ʱ�� from rcgl_dmwhb a where a.MKSS='"
						+ tTem + "'";
			}
	        if("zbrydmb".equalsIgnoreCase(tName)){
	        	sql = "select rownum �к�, a.zbrydm,a.zbrymc,(select ldmc from sslddmb b where a.szlddm=b.lddm )ldmc ,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')�����,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')���ʱ��,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))���� from zbrydmb a";
	        }
			if ("jxjdmb".equalsIgnoreCase(tName)) {
				if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
					sql = "select rownum �к�,a.jxjdm,a.jxjmc,a.jxjjb,a.jlje,(select b.jxjlbmc from jxjlbdmb b where b.jxjlbdm=a.jxjlb) jxjlb,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')�����,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))����,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')���ʱ�� from "
							+ tName + " a";
				}
			}
		}else{
			sql = "select rownum �к�,a.* from " + tName + " a";
			
			if (tName.equalsIgnoreCase("xljk_stsslbdmb")) {
				sql = "select rownum �к�,a.SSLXDM,a.SSLXMC from " + tName + " a";
			}
			if ("sztz_xmdmb".equalsIgnoreCase(tName)) {
				sql = "select rownum �к�,a.* from view_sztz_xmdmb a";
			}
			if ("sztz_sqsblyb".equalsIgnoreCase(tName)) {
				sql = "select rownum �к�,a.* from view_sztz_sqsblyb a";
			}
			if ("sztz_hjjbdmb".equalsIgnoreCase(tName)) {
				sql = "select rownum �к�,a.* from view_sztz_hjjbdmb a";
			}
			if ("yrdwdmb".equalsIgnoreCase(tName)) {
				tName = "view_yrdwdmb";
				sql = "select rownum �к�,a.yrdwdm,a.yrdwmc,a.lxr,a.lxdh,a.dwlb,a.ssxq,a.xymc, a.dlm,a.yrdwdz from view_yrdwdmb a";
			}
			if ("gywsjcbmb".equalsIgnoreCase(tName)) {
				sql = "select rownum �к�,a.bmdm,a.bmmc from gywsjcbmb a ";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				if ("yrdwdmb".equalsIgnoreCase(tName)) {
					sql = "select rownum �к�, a.yrdwdm,a.yrdwmc,a.lxr,a.lxdh,a.dwlb,a.ssxq,a.xydm from view_yrdwdmb a ";
				}
			}
			if ("rcgl_dmwhb".equalsIgnoreCase(tName)) {
				sql = "select rownum �к�,a.MKDM,a.MKMC from rcgl_dmwhb a where a.MKSS='"
						+ tTem + "'";
			}
	        if("zbrydmb".equalsIgnoreCase(tName)){
	        	sql = "select rownum �к�, a.zbrydm,a.zbrymc,(select ldmc from sslddmb b where a.szlddm=b.lddm )ldmc from zbrydmb a";
	        }
			if ("jxjdmb".equalsIgnoreCase(tName)) {
				if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
					sql = "select rownum �к�,a.jxjdm,a.jxjmc,a.jxjjb,a.jlje,(select b.jxjlbmc from jxjlbdmb b where b.jxjlbdm=a.jxjlb) jxjlb from "
							+ tName + " a";
				}
			}
		}
		return sql;
	}
	
	/**
	 * ����¼�Ƿ����
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		return dao.checkExists(tableName, pk, pkValue);
	}
	
	/**
	 * ��ȡ�����û���Ȩ�޵Ĳ�ѯ���
	 * @param model
	 * @return String
	 * */
	public String querryZqx(CommanForm model){
		String zdm = model.getZdm();
		String gnmkdm = model.getGnmkdm();
		StringBuffer sql = new StringBuffer("select zdm,zmc,gnmkdm,gnmkmc,dxq,dyym,xssx from view_yhzqx where 1=1 ");
		
		if(zdm != null && !"".equalsIgnoreCase(zdm)&&!zdm.equalsIgnoreCase("null")){
			sql.append(" and zdm='");
			sql.append(zdm);
			sql.append("'");
		}
		
		if(gnmkdm != null && !"".equalsIgnoreCase(gnmkdm)&&!gnmkdm.equalsIgnoreCase("null")){
			sql.append(" and gnmkdm like '");
			sql.append(gnmkdm);
			sql.append("%'");
		}
		return sql.toString();
	}
	
	/**
	 * ��ȡ�����û�Ȩ�޵Ĳ�ѯ���
	 * @param model
	 * @return String
	 * */
	public String querryYhqx(CommanForm model){
		String yhm = model.getYhm();
		String gnmkdm = model.getGnmkdm();
		StringBuffer sql = new StringBuffer("select yhm,gnmkdm,gnmkmc,dxq,dyym,xssx,zdm from view_yhqx where 1=1 ");
		
		if(yhm != null && !"".equalsIgnoreCase(yhm)){
			sql.append(" and yhm='");
			sql.append(yhm);
			sql.append("'");
		}
		
		if(gnmkdm != null && !"".equalsIgnoreCase(gnmkdm)){
			sql.append(" and gnmkdm like '");
			sql.append(gnmkdm);
			sql.append("%'");
		}
		return sql.toString();
	}
	
	/**
	 * ���ݲ������Ʋ�ѯ����id,��ѯ����id������һ���µļ�¼�����ز����id
	 * */
	public String getUserPartId(String bmmc, String unit,HttpServletRequest request){
		String result = "";
		String sql = "select bmdm from zxbz_xxbmdm where bmmc=?";
		result = dao.getOneRs(sql, new String[]{bmmc}, "bmdm");
		if(StringUtils.isNull(result)){//���ݲ������Ʋ�ѯ�������Ŵ���
			result = "xg" + System.currentTimeMillis();//���ɲ��Ŵ���
			String[] columns = {"bmdm","bmmc","bmjb","bmlb"};
			String[] values = {result,bmmc,"2",unit};//��ѧԺ�Ĳ��Ų��ż���Ĭ��Ϊ2
			StandardOperation.insert("zxbz_xxbmdm", columns, values, request);
		}
		return result;
	}
	
	/**
	 * ���ݵ�λ���Ʋ�ѯ����id,��ѯ����id������һ���µļ�¼�����ز����id
	 * */
	public String getUserUnitId(String dwmc, HttpServletRequest request){
		String result = "";
		String sql = "select dwdm from bks_dwdmb where dwmc=?";
		result = dao.getOneRs(sql, new String[]{dwmc}, "dwdm");
		if(StringUtils.isNull(result)){//���ݵ�λ���Ʋ�ѯ������λ����
			result = "xg" + System.currentTimeMillis();//���ɲ��Ŵ���
			String[] columns = {"dwdm","dwmc"};
			String[] values = {result,dwmc};
			StandardOperation.insert("bks_dwdmb", columns, values, request);
		}
		return result;
	}

}
