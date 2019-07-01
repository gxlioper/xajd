package xgxt.qgzx.hngydx.service;

import javax.servlet.http.HttpServletRequest;

import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.qgzx.hngydx.dao.HngydxGwglDAO;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块湖南工业岗位管理Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-10</p>
 */
public class HngydxGwglService {
	
	/**
	 * 增加岗位信息
	 * @param gwxx
	 * @return boolean
	 * */
	public boolean addGwxx(CommanForm gwxx,HttpServletRequest request){
		boolean flag = false;
		HngydxGwglDAO gwDao = new HngydxGwglDAO();
		String[] input = {"gwdm","gwxz","sqdw","xq","xueqi","xn","nd","gzsj",
						  "gzksrq","gzjsrq","jcfs","jybcbz","fzr","lxdh","dwdz",
						  "gwtsyq","sqdwyj","bz","xyrs","spbcbz","sqsyrs","gwsl"};
		String[] inputValue = null;
		String gwmc = "";
		String gwsl = "";
		String gwxzdm = "";
		
		String sqdwdm = gwxx.getSqdw(); 
		String xiaoqu = gwxx.getXq();
		String nd = gwxx.getNd();
		String xn = gwxx.getXn();
		String gzsj = DealString.toGBK(gwxx.getGzsj());
		String jcfs = gwxx.getJcfs();
		String jcbz = DealString.toGBK(gwxx.getJybcbz());
		String gzkssj = gwxx.getGzkssj();
		String gzjssj = gwxx.getGzjssj();
		String fzr = DealString.toGBK(gwxx.getFzr());
		String lxdh = DealString.toGBK(gwxx.getLxdh());
		String dwdz = DealString.toGBK(gwxx.getDwdz());
		String gwtsyq = DealString.toGBK(gwxx.getGwtsyq());
		String sqdwyj = DealString.toGBK(gwxx.getSqdwyj());
		String bz = DealString.toGBK(gwxx.getBz());
		String xq = gwDao.getCurrXq();
		
		for(int i=1; i<6; i++){
			gwmc = DealString.toGBK(request.getParameter("gwmc" + i));
			gwsl = request.getParameter("gwsl" + i);
			gwxzdm = request.getParameter("gwxz" + i);
			inputValue = new String[]{gwmc,gwxzdm,sqdwdm,xiaoqu,xq,xn,nd,gzsj,
					  gzkssj,gzjssj,jcfs,jcbz,fzr,lxdh,dwdz,
					  gwtsyq,sqdwyj,bz,gwsl,jcbz,gwsl,gwsl};
			if(gwmc!=null && !"".equalsIgnoreCase(gwmc)){
				flag = StandardOperation.insert("gwxxb", input, inputValue, request);
			}
		}		
		return flag;
	}
	
	/**
	 * 修改岗位信息
	 * @param gwxx
	 * @return boolean
	 * */
	public boolean modGwxx(CommanForm gwxx,HttpServletRequest request){
		boolean flag = false;
		HngydxGwglDAO gwDao = new HngydxGwglDAO();
		
		String[] input = {"gwxz","sqdw","xq","xueqi","xn","nd","gzsj",
				  "gzksrq","gzjsrq","jcfs","jybcbz","fzr","lxdh","dwdz",
				  "gwtsyq","sqdwyj","bz","xyrs","spbcbz","sqsyrs","gwsl"};
		String[] inputValue = null;
		String gwmc = DealString.toGBK(request.getParameter("gwmc1"));
		String gwsl = request.getParameter("gwsl1");
		String gwxzdm = request.getParameter("gwxz1");
		String gwsbsj = request.getParameter("gwsbsj1");
		
		String sqdwdm = gwxx.getSqdw(); 
		String xiaoqu = gwxx.getXq();
		String nd = gwxx.getNd();
		String xn = gwxx.getXn();
		String gzsj = DealString.toGBK(gwxx.getGzsj());
		String jcfs = gwxx.getJcfs();
		String jcbz = DealString.toGBK(gwxx.getJybcbz());
		String gzkssj = gwxx.getGzkssj();
		String gzjssj = gwxx.getGzjssj();
		String fzr = DealString.toGBK(gwxx.getFzr());
		String lxdh = DealString.toGBK(gwxx.getLxdh());
		String dwdz = DealString.toGBK(gwxx.getDwdz());
		String gwtsyq = DealString.toGBK(gwxx.getGwtsyq());
		String sqdwyj = DealString.toGBK(gwxx.getSqdwyj());
		String bz = DealString.toGBK(gwxx.getBz());
		String xq = gwDao.getCurrXq();
		
		try {
			inputValue = new String[]{gwxzdm,sqdwdm,xiaoqu,xq,xn,nd,gzsj,
					  gzkssj,gzjssj,jcfs,jcbz,fzr,lxdh,dwdz,
					  gwtsyq,sqdwyj,bz,gwsl,jcbz,gwsl,gwsl};
			flag = StandardOperation.update("gwxxb", input, inputValue, "gwdm||gwsbsj", gwmc+gwsbsj, request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return flag ;
	}
}
