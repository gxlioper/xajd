package xgxt.qgzx.whlgdx.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.qgzx.whlgdx.dao.WhlgdxGwglDAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块武汉理工大学岗位管理Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-10</p>
 */
public class WhlgdxGwglService {
	
	/**
	 * 增加岗位信息service
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean addGwxx(CommanForm model,HttpServletRequest request){
		boolean flag = false;
		String[] input = null;
		String[] value = null;
		String xq = model.getXqdm();//校区 
		String gwmc = DealString.toGBK(model.getGwdm());
		String gwxz = model.getGwxz();
		String sqdw = model.getSqdw();
		String xn = model.getXn();
		String nd = model.getNd();
		String xueqi = model.getXueqi();
		String gzkssj = model.getGzkssj();
		String gzjssj = model.getGzjssj();
		String xyrs = model.getXyrs();
		String syknss = model.getSyknss();
		String jcfs = model.getJcfs();
		String jybcbz = model.getJybcbz();
		String mssjd = model.getMssjd();
		String msdd = model.getMsdd();
		String fzr = DealString.toGBK(model.getFzr());
		String lxdh = model.getLxdh();
		String gzdd = DealString.toGBK(model.getGzdd());
		String gzsj = DealString.toGBK(model.getGzsj());
		String gznr = DealString.toGBK(model.getGznr());
		String xyddm = model.getXyddm();
		String rzyq_nj = model.getRzyq_nj();
		String rzyq_xb = DealString.toGBK(model.getRzyq_xb());
		String rzyq_zy = model.getRzyq_zy();
		String rzyq_wyyq = DealString.toGBK(model.getRzyq_wyyq());
		String rzyq_gzjn = DealString.toGBK(model.getRzyq_gzjn());
		String rzyq_qt = DealString.toGBK(model.getRzyq_qt());
		String gzyd = DealString.toGBK(model.getGzyd());
		String gzmd = DealString.toGBK(model.getGzmd());
		String gznd = DealString.toGBK(model.getGznd());
		String gzjj = DealString.toGBK(model.getGzjj());
		String mtbzgz = DealString.toGBK(model.getMtbzgz());
		String dqbzgz = DealString.toGBK(model.getDqbzgz());
		String bz = DealString.toGBK(model.getBz());
		
		input = new String[]{"xq","gwdm","xn","nd","xueqi","gwxz","sqdw","gzksrq","gzjsrq","xyrs","syknss","jcfs",
				"jybcbz","mssjd","fzr","msdd","lxdh","gzdd","gzsj","gznr","xyddm","rzyq_nj","rzyq_xb","rzyq_zy",
				"rzyq_wyyq","rzyq_gzjn","rzyq_qt","gzmd","gzyd","gznd","gzjj","mtbzgz","dqbzgz","bz","spbcbz",
				"sqsyrs","sqsyknss"};
		value = new String[]{xq,gwmc,xn,nd,xueqi,gwxz,sqdw,gzkssj,gzjssj,xyrs,syknss,jcfs,
				jybcbz,mssjd,fzr,msdd,lxdh,gzdd,gzsj,gznr,xyddm,rzyq_nj,rzyq_xb,rzyq_zy,
				rzyq_wyyq,rzyq_gzjn,rzyq_qt,gzmd,gzyd,gznd,gzjj,mtbzgz,dqbzgz,bz,jybcbz,
				xyrs,syknss};
		flag = StandardOperation.insert("gwxxb", input, value, request);
		return flag;
	}
	
	/**
	 * 修改岗位信息service
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean modGwxx(String pkValue,CommanForm model,HttpServletRequest request){
		boolean flag = false;
		String PKSTRING = "gwdm||gwsbsj";
		String[] input = null;
		String[] value = null;
		String xq = model.getXqdm();//校区 
		String gwxz = model.getGwxz();
		String sqdw = model.getSqdw();
		String xn = model.getXn();
		String nd = model.getNd();
		String xueqi = model.getXueqi();
		String gzkssj = model.getGzkssj();
		String gzjssj = model.getGzjssj();
		String xyrs = model.getXyrs();
		String syknss = model.getSyknss();
		String jcfs = model.getJcfs();
		String jybcbz = model.getJybcbz();
		String mssjd = model.getMssjd();
		String msdd = model.getMsdd();
		String fzr = DealString.toGBK(model.getFzr());
		String lxdh = model.getLxdh();
		String gzdd = DealString.toGBK(model.getGzdd());
		String gzsj = DealString.toGBK(model.getGzsj());
		String gznr = DealString.toGBK(model.getGznr());
		String xyddm = model.getXyddm();
		String rzyq_nj = model.getRzyq_nj();
		String rzyq_xb = DealString.toGBK(model.getRzyq_xb());
		String rzyq_zy = model.getRzyq_zy();
		String rzyq_wyyq = DealString.toGBK(model.getRzyq_wyyq());
		String rzyq_gzjn = DealString.toGBK(model.getRzyq_gzjn());
		String rzyq_qt = DealString.toGBK(model.getRzyq_qt());
		String gzyd = DealString.toGBK(model.getGzyd());
		String gzmd = DealString.toGBK(model.getGzmd());
		String gznd = DealString.toGBK(model.getGznd());
		String gzjj = DealString.toGBK(model.getGzjj());
		String mtbzgz = DealString.toGBK(model.getMtbzgz());
		String dqbzgz = DealString.toGBK(model.getDqbzgz());
		String bz = DealString.toGBK(model.getBz());
		pkValue = DealString.toGBK(pkValue);
		input = new String[]{"xq","xn","nd","xueqi","gwxz","sqdw","gzksrq","gzjsrq","xyrs","syknss","jcfs",
				"jybcbz","mssjd","fzr","msdd","lxdh","gzdd","gzsj","gznr","xyddm","rzyq_nj","rzyq_xb","rzyq_zy",
				"rzyq_wyyq","rzyq_gzjn","rzyq_qt","gzmd","gzyd","gznd","gzjj","mtbzgz","dqbzgz","bz","spbcbz",
				"sqsyrs","sqsyknss"};
		value = new String[]{xq,xn,nd,xueqi,gwxz,sqdw,gzkssj,gzjssj,xyrs,syknss,jcfs,
				jybcbz,mssjd,fzr,msdd,lxdh,gzdd,gzsj,gznr,xyddm,rzyq_nj,rzyq_xb,rzyq_zy,
				rzyq_wyyq,rzyq_gzjn,rzyq_qt,gzmd,gzyd,gznd,gzjj,mtbzgz,dqbzgz,bz,jybcbz,
				xyrs,syknss};
		try {
			flag = StandardOperation.update("gwxxb", input, value, PKSTRING, pkValue, request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 学生查询岗位详细信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwxxDetail(String pkValue){
		WhlgdxGwglDAO gwDao = new WhlgdxGwglDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		map = gwDao.getGwxx(pkValue);
		return map;		
	}
	
	/**
	 * 学生酬金发放岗位信息查询
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getCjffInfo(String pkValue){
		WhlgdxGwglDAO gwDao = new WhlgdxGwglDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		map = gwDao.getCjffGwxx(pkValue);
		return map;
	}
	
	/**
	 * 学生酬金发放学生酬金信息查询
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public List getCjffInfoOfStu(String pkValue){
		WhlgdxGwglDAO gwDao = new WhlgdxGwglDAO();
		List list= null;
		list = gwDao.getStuCjffxx(pkValue);
		return list;
	}
	
	/**
	 * 酬金发放审核保存
	 * @param pkValue
	 * @param request
	 * @return boolean 
	 * */
	public boolean saveAudit(String pkValue,HttpServletRequest request){
		boolean flag = false;
		WhlgdxGwglDAO gwDao = new WhlgdxGwglDAO();
		String yesNo = DealString.toGBK(request.getParameter("xxsh"));
		String nd = gwDao.getNdYf().get("nd");
		String yf = gwDao.getNdYf().get("yf");
		String cjffsj = gwDao.getCjffsj();
		
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj)){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}
		
		String sql = "update xscjffb a set xxsh='"+yesNo+"' where exists(select 1 from view_xsgwxx b where a.xh=b.xh and a.gwdm=b.gwdm and b.gwdm||b.gwsbsj='"+pkValue+"') and a.nd='"+nd+"' and a.yf='"+yf+"'";
		try {
			flag = StandardOperation.update("xscjffb",sql, request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return flag;
	}
}
