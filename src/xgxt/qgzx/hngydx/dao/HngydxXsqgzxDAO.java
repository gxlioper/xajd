package xgxt.qgzx.hngydx.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.form.CommanForm;
import xgxt.utils.GetTime;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块湖南工业学生勤工助学管理DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-11</p>
 */

public class HngydxXsqgzxDAO extends DAO {
	/**
	 * 获取学生可以申请的岗位列表	 
	 * @return List 
	 * */
	public List getGwList(){
		String sql = "select gwdm gwmc,gwdm||'-'||gwsbsj gwdm from view_gwxx where SHJG='通过' and gzjsrq>to_char(sysdate,'yyyymmdd')";
		
		return  getList(sql, new String[] {}, new String[] { "gwdm", "gwmc" });
	}
	
	/**
	 * 获取空闲时间表格的左边项
	 * @return List 
	 * */
	@SuppressWarnings("unchecked")
	public List getWhkxList(){
		List kxList = new ArrayList();
		String[] sj = { "1-2节", "3-4节", "5-6节", "7-8节", "晚 上"};
		for (int i = 0; i < 5; i++) {
			HashMap<String, String> map2 = new HashMap<String, String>();
			map2.put("sj", sj[i]);
			map2.put("sjIndex", String.valueOf(i));
			kxList.add(map2);
		}
		return kxList;
	}
	
	/**
	 * 获取岗位的申请时间范围
	 * @return boolean 
	 * */
	public boolean isRangeOfSqsj(){
		String sql = "";
		sql = "select count(*) count from gwsqsjb where kssj<to_char(sysdate,'yyyymmddhh24miss') and jssj>to_char(sysdate,'yyyymmddhh24miss')";
		return Integer.parseInt(getOneRs(sql,new String[]{}, "count"))>0 ? true : false;
	}
	
	/**
	 * 获取当前勤工助学时间
	 * @param map
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getCurrXnNdXq(HashMap<String, String> map){
		String sql = "select nd,xn,(select xqmc from xqdzb b where a.xq=b.xqdm )xq from gwsqsjb a";
		map.putAll(getMap(sql, new String[]{}, new String[]{"xn","nd","xq"}));
		return map;
	}
	
	/**
	 * 加载学生个人信息
	 * @param xh
	 * @param colum
	 * @param map
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> addStuInfo(String xh,String colum ,HashMap<String, String> map){
		String[] columns = colum.split("-");
		String sql = " select * from view_xsxxb where xh=?";
		map.putAll(getMap(sql, new String[]{xh}, columns));
		return map;
	}
	
	/**
	 * 查询学生申请信息
	 * @param pkValue
	 * @return 
	 * */
	public HashMap<String, String> getStuinfo(String pkValue){
		HashMap<String,String> map = new HashMap<String, String>();
		String sql = "select a.gwdm||'-'||a.gwsbsj gwdm,gwdm gwsjmc,a.lxdh,a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc," +
					 "a.zzmm zzmmmc,a.ssbh,a.yhtc,a.gzjl,a.xssq sqly,a.bz,(select jg from view_xsxxb b where a.xh=b.xh) jg," +
					 "(select fzr from view_gwxx b where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj) fzr," +
					 "(select lxdh from view_gwxx b where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj) dwlxdh," +
					 "xn,nd,xq from view_xsgwxx a where xh||gwdm||sqsj=?";
		map = getMap(sql, new String[]{pkValue}, new String[]{"xh","xm","xb","xymc","zymc","bjmc","jg","zzmmmc","ssbh",
					"gwdm","xn","nd","xq","fzr","dwlxdh","lxdh","sqly","gzjl","bz","nj","yhtc","gwsjmc"});
		return map;
	}
	
	/**
	 * 获取学生可参加勤工助学的空闲时间
	 * @param xh
	 * @return List
	 * */
	@SuppressWarnings("unchecked")
	public List getKxSj(String xh){
		List kxList = new ArrayList();
		String[] sj = { "1-2节", "3-4节", "5-6节", "7-8节", "晚 上"};
		String[] xq = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
		
		if (xh != null && !xh.equalsIgnoreCase("")) {				
			String sql = "select kxbz from xsqgzxsjb where xh = ?  order by xq,sj";
			String[] kxbz = null;
			try {
				kxbz = getRs(sql, new String[] { xh }, "kxbz");
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			if(kxbz!=null && kxbz.length>34){
				String[] kx = new String[7];
				int j = 0;
				for (int i = 0; i < 5; i++) {
					for (int x = 0; x < 7; x++) {
						kx[x] = kxbz[x + j];
					}
					j += 7;
					HashMap<String, String> map2 = new HashMap<String, String>();
					for (int p = 0; p < 7; p++) {
						map2.put(xq[p], String.valueOf(kx[p]));
					}
					map2.put("sj", sj[i]);
					map2.put("sjIndex", String.valueOf(i));
					kxList.add(map2);
				}		
			}				
			}
		return kxList;
	}
	
	/**
	 * 获取学生申请时填写的基本信息
	 * @param pkVal
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuAppInfo(String pkVal){
		String sql = "select xh,lxdh,yhtc,(select zzmmmc from view_xsxxb b where a.xh=b.xh) zzmmmc,(select jg from view_xsxxb b where a.xh=b.xh)jg," +
					 "gzjl,xssq sqly,bz from view_xsgwxx a where a.xh||a.gwdm||a.sqsj=?";
		return getMap(sql, new String[]{pkVal},new String[]{"xh","lxdh","yhtc","zzmmmc","jg","gzjl","sqly","bz"});
	}
	
	/**
	 * 勤工助学花名册
	 * @param xn
	 * @return List
	 * */
	public List getRoster(String xn){	
		String sql = "select rownum r, xm, xb, xymc, bjmc, nj, ssbh, lxdh, (select yrdwmc from yrdwdmb b where a.yrdwdm=b.yrdwdm)yrdwmc, gwdm, bz, sfpks, " +
				"'' cj from view_xsgwxx a where a.xxyj='通过' and a.xn=?";
		
		return rsToVator(sql, new String[]{xn}, new String[]{"r","xm","xb","xymc","bjmc","nj","ssbh","lxdh","yrdwmc","gwdm","bz","sfpks","cj"});
	}
	
	/**
	 * 获取学生成绩信息
	 * @param xh
	 * @return Lists
	 * */
	public List getXscjInfo(String xh){
		String xn = Base.currXn;
		List list = null;
		String[] columns = {"xq","xn", "xh", "xm", "bjmc", "kcmc", "bkcj","cxcj", "cj"};
		String sql = "select a.xn,a.xq,a.xh,(select xm from view_xsjbxx b where a.xh=b.xh) xm,(select bjmc from view_xsjbxx b where a.xh=b.xh)bjmc,a.kcmc,a.bkcj,a.cxcj,a.cj from cjb a where a.kcxz like '%必修课%' and greatest(nvl(bkcj,0),nvl(cxcj,0),nvl(cj,0))<60 and xh=? and xn=?";
		list = getList(sql, new String[]{xh,xn}, columns);
		return list;
	}
	
	/**
	 * 获取打印申请表页面数据
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getPintInfo(CommanForm model){
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = model.getXh();
		//加载学生基本信息
		map = addStuInfo(xh, "xh-xm-xb-zymc-bjmc-jg-zzmmmc-ssbh", map);
		
		//加载申请页面输入信息
		map.put("lxdh", model.getLxdh());
		map.put("yhtc", DealString.toGBK(model.getYhtc()));
		map.put("gwjl", DealString.toGBK(model.getGzjl()));
		
		//加载时间
		map.put("sj", GetTime.getNowTime());
		return map;
	}
}
