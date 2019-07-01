package xgxt.qgzx.xbemy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;

public class XbemyQgzxDAO {
	
	DAO dao = DAO.getInstance();
	
	//获取维护项目列表
	public List getWhxmList(){
		List xmList = null;
		String sql = "";
		
		sql = "select distinct xmdm,xmmc from xbemy_qgzx_zdyxmdmb";
		xmList = dao.getList(sql, new String[]{}, new String[]{"xmdm","xmmc"});
		
		return xmList;
	}
	
	//多条件查询信息
	public List selectInfo(ActionForm form,String tableName, String[] input){	
		XbemyQgzxForm xbForm = (XbemyQgzxForm) form;
		List rs=null;
		String sql = "";
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		
		String xmdm = xbForm.getXmdm();
		
		querry.append(xmdm==null || xmdm.equalsIgnoreCase("") ? "" : " and xmdm='" + xmdm + "'");
		
		sql = "select * from " + tableName + querry.toString();		
		rs = dao.rsToVator(sql, new String[]{}, input);
		
		return rs;
	}
	
	//信息保存
	public boolean saveInfo(HttpServletRequest request,ActionForm form,String tableName,String[] pk,String[] input) throws Exception{
		boolean flag = false;
		XbemyQgzxForm xbForm = (XbemyQgzxForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		String[] value = new String[input.length];
		String sql = "";
		String pkStr = "";
		String pkValue = "";
		
		String xmdm = DealString.toGBK(xbForm.getXmdm());
		String xmmc = DealString.toGBK(xbForm.getXmmc());
		String zddm = DealString.toGBK(xbForm.getZddm());
		String zdmc = DealString.toGBK(xbForm.getZdmc());
		String zdlx = DealString.toGBK(xbForm.getZdlx());
		String bxwsz = DealString.toGBK(xbForm.getBxwsz());
		String zddx = DealString.toGBK(xbForm.getZddx());
		
		map.put("xmdm", xmdm);
		map.put("xmmc", xmmc);
		map.put("zddm", zddm);
		map.put("zdmc", zdmc);
		map.put("zdlx", zdlx);
		map.put("bxwsz", bxwsz);
		map.put("zddx", zddx);
		//获取主键和主键值
		for(int i=0; i<pk.length; i++){
			pkStr += pk[i];
			pkValue += map.get(pk[i]);
		}
		
		//判断是否有记录存在
		sql = "select count(*) str from " + tableName + " where " + pkStr + "=?";
		String str = dao.getOneRs(sql, new String[]{pkValue}, "str");
		str = str==null || "".equalsIgnoreCase(str)? "0": str;
		int num = Integer.parseInt(str);
		
		//获取要保存的值
		for(int i=0; i<input.length; i++){
			value[i] = map.get(input[i]);
		}
		
		if(num>0){
			//update
			flag = StandardOperation.update(tableName, input, value, pkStr, pkValue, request);
		}else{
			//insert
			flag = StandardOperation.insert(tableName, input, value, request);
		}
		
		return flag;
	}
	
	//修改表结构
	public boolean modifyTable(HttpServletRequest request,HashMap<String,String> tabInfo,ActionForm filedInfo,String type) throws Exception{
		XbemyQgzxForm xbForm = (XbemyQgzxForm)filedInfo;
		String xxdm = StandardOperation.getXxdm();
		String zddm = xbForm.getZddm();
		String zdmc = DealString.toGBK(xbForm.getZdmc());
		String zdlx = "varchar2";
		String zddx = DealString.toGBK(xbForm.getZddx());
		String bxwsz = DealString.toGBK(xbForm.getBxwsz());
		
		String tableName = tabInfo.get("xmssb");
		String viewTab = tabInfo.get("xmssst");
		
		//如果字段类型必须是数字将字段类型修改为number
		zdlx = (bxwsz!=null && bxwsz.equalsIgnoreCase("是")) ? "number" : zdlx;
		
		boolean flag = false;
		String sql = "";
		if(type!=null && type.equalsIgnoreCase("add")){
			sql = "alter table " + tableName + " add (" + zddm + " " + zdlx + "(" + zddx + ")) ";
		}
		if(type!=null && type.equalsIgnoreCase("modify")){
			sql = "alter table " + tableName + " modify (" + zddm + " " + zdlx + "(" + zddx + ") )";
		}
		//修改表的字段结构
		if(sql!=null && !sql.equalsIgnoreCase("del")){
			flag = StandardOperation.update(tableName, sql, request);
			//增加注释到表
			sql = "comment on column " + tableName + "." + zddm + " is '" + zdmc + "'";
			flag = StandardOperation.update(tableName, sql, request);
		}
		
		if(type!=null && type.equalsIgnoreCase("del")){
			//删除字段时先清空数据
			sql = "update " + tableName + " set " + zddm + " = ''";
			flag = StandardOperation.update(tableName, sql, request);
			//删除字段
			if(flag){
				sql = "alter table " + tableName + " drop column " + zddm;
				flag = StandardOperation.update(tableName, sql, request); 
			}
		}
		
		//重建视图
		if(viewTab!=null && !"".equalsIgnoreCase(viewTab)){			
			String[] comments = dao.getViewComm(viewTab);
			String tableComment = "";
			if("view_gwxx".equalsIgnoreCase(viewTab)){
				sql = "CREATE OR REPLACE VIEW VIEW_GWXX AS"+
						" select a.*,(select xydm from view_yrdwdmb f where a.sqdw=f.yrdwdm) xymc,nvl(a.lxdh,(SELECT lxdh FROM yrdwdmb c WHERE a.sqdw=c.yrdwdm)) yrdwlxdh,(SELECT gwxzmc FROM gwxzdmb e WHERE a.gwxz=e.gwxzdm) gwxzmc,(SELECT xqmc FROM dm_zju_xq d WHERE a.xq=d.dm) xqmc,(SELECT yrdwmc FROM yrdwdmb c WHERE a.sqdw=c.yrdwdm) yrdwmc,(SELECT xydm FROM yrdwdmb c WHERE a.sqdw=c.yrdwdm) xydm,(SELECT xqmc FROM xqdzb b WHERE a.xueqi=b.xqdm) xueqimc" +
						" from" + 
						"(" +
						 "select a.*" +
						" from" + 
						"(" + 
						 "select a.*,decode(a.jcfs,'h','按小时','d','按天','w','按周','m','按月','') jcfsmc,decode(a.shjg,'通过','有效','无效') sfyx from gwxxb a" +
						 ") a" +
						") a";
				tableComment = "岗位信息";
			}
			if("view_xsgwxx".equalsIgnoreCase(viewTab)){
				sql = "create or replace view view_xsgwxx as "
						+ "select a.*,(select xqmc from xqdzb e where a.xq=e.xqdm) xqmc,(select zzmmmc from zzmmdmb b where a.zzmmm=b.zzmmdm) zzmm  from"
						+ "("
						+ "select a.*,(select zzmmm from bks_xsqtxx c where a.xh=c.xh) zzmmm  from"
						+ "("
						+ "select a.*,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.bjmc XZB "
						+ "from xsgwxxb a,view_xsjbxx b "
						+ "where a.xh=b.xh"
						+ ") a"
						+ ") a";
				tableComment = "学生申请岗位信息 ";
			}
			dao.creatView(sql, comments);
			if(type!=null && !type.equalsIgnoreCase("del")){
				//增加新注释到视图
				sql = "comment on column " + viewTab + "." + zddm + " is '" + zdmc + "'";
				flag = StandardOperation.update(viewTab, sql, request);				
				sql = "comment on table " + viewTab + " is '" + tableComment +"'";
				flag = StandardOperation.update(viewTab, sql, request); 
			}
		}
		
		//维护查询表数据
		if(type!=null && type.equalsIgnoreCase("add")){
			//增加
			StandardOperation.insert("cxb", new String[] { "ssmk","cxbm", "sxxm", "sxbj", "xxmc" },
					new String[] {
							"work",
							viewTab.toUpperCase(),
							zddm.toUpperCase(), "0",
							xxdm }, request);
		}else if(type!=null && type.equalsIgnoreCase("del")){
			//删除
			StandardOperation.delete("cxb", "ssmk||cxbm", "work" + viewTab.toUpperCase(), request);
		}
		
		return flag;
	}

	//根据项目代码获取表名
	public HashMap<String, String> getTabNameByCode(ActionForm form){
		XbemyQgzxForm xbForm = (XbemyQgzxForm)form;
		HashMap<String, String> map = new HashMap<String, String>();
		String xmdm = xbForm.getXmdm();
		
		String sql = "select distinct xmssb,xmssst from xbemy_qgzx_zdyxmdmb where xmdm=?";
		map = dao.getMap(sql, new String[]{xmdm}, new String[]{"xmssb","xmssst"});
		
		return map;
	}
	
	//根据项目代码获取表名(用于删除)
	public HashMap<String, String> getTabNameByCode(ActionForm form,String del){
		XbemyQgzxForm xbForm = (XbemyQgzxForm)form;
		HashMap<String, String> map = new HashMap<String, String>();
		String zddm = xbForm.getZddm();
		
		String xmdm = dao.getOneRs("select xmdm from xbemy_qgzx_zdwhxxb where zddm = ?", new String[]{zddm}, "xmdm");
		
		String sql = "select distinct xmssb,xmssst from xbemy_qgzx_zdyxmdmb where xmdm=?";
		map = dao.getMap(sql, new String[]{xmdm}, new String[]{"xmssb","xmssst"});
		
		return map;
	}

	//根据主键查询信息
	public HashMap<String, String> getInfoByPk(String tableName,String pk,String pkValue,String[] input){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from " + tableName + " where " + pk + " = ?";
		
		map = dao.getMap(sql, new String[]{pkValue}, input);
		return map;
	}
	
	//获取自定义的字段信息
	public List<HashMap<String, String>> getFiledInfo(String xmdm){
		List<HashMap<String, String>> rs = null;
		String sql = "select xmdm,zdmc,zddm,zdlx,zddx,bxwsz from xbemy_qgzx_zdwhxxb where xmdm=?";
		rs =  dao.getList(sql, new String[]{xmdm}, new String[]{"xmdm","zddm","zdmc","zdlx","zddx","bxwsz"});		
		return rs;
	}

	public List<HashMap<String, String>> getFiledOfZddm(String xmdm){
		List<HashMap<String, String>> filed = null;
		filed = dao.getList("select zddm from xbemy_qgzx_zdwhxxb where xmdm=?", new String[]{xmdm}, new String[]{"zddm"});
		return filed;
	}
	
	//根据表名称获取所有的字段
	public String[] getColumnByTabName(String tabName){
		String[] column = dao.getColumnName("select * from " + tabName + " where 1=1");
		return column;
	}
}
