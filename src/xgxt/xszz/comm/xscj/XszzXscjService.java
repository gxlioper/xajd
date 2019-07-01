package xgxt.xszz.comm.xscj;

import java.util.HashMap;
import java.util.List;

import xgxt.xszz.XszzTyForm;

public class XszzXscjService {
	
	/**
	 * 获取评定时间
	 * @param xmdm
	 * @return String
	 */
	public String getPdsj(String xmdm){
		XszzXscjDAO xscjDao=new XszzXscjDAO();
		String pdsj="";
		pdsj= xscjDao.getPdsj(xmdm).get("pdsj");
		return pdsj;
	}
	
	
	public List<HashMap<String, String>> getXscj(XszzTyForm xszzForm){
		XszzXscjDAO xscjDao=new XszzXscjDAO();
		return xscjDao.getXscj(xszzForm);
	}
	
	/**
	 * 获取上一学年
	 * @param xn
	 * @return String
	 */
	public XszzTyForm getSxn(XszzTyForm xszzForm){
		//拆分学年分别-1
		String xmdm=xszzForm.getXmdm();
		String xn=xszzForm.getXn();
		String pdsj=getPdsj(xmdm);
		
		if("本次".equalsIgnoreCase(pdsj)){
			
		}else if("前次".equalsIgnoreCase(pdsj)){
			//获取上一学年
			int firstNd=Integer.parseInt(xn.substring(0,4))-1;
			int secoundNd=Integer.parseInt(xn.substring(5, 9))-1;
			xn=firstNd+"-"+secoundNd;
		}
		xszzForm.setXn(xn);
		return xszzForm;
	}
	
	/**
	 * 根据申请时间获取学年
	 * @param xn
	 * @return String
	 */
	public String getSqsjToXn(String sqsj){
		//截取年
		sqsj=sqsj.substring(0,4);
		int secoundNd=Integer.parseInt(sqsj)+1;
		String xn=sqsj+"-"+secoundNd;
		return xn;
	}
	
	
	/**
	 * 获取项目表名
	 * @param xszzForm
	 * @return String 
	 */
	public String getCxyXn(XszzTyForm xszzForm){
		XszzXscjDAO xscjDao=new XszzXscjDAO();
		String xmb=xscjDao.getXmb(xszzForm).get("xmb");
		return  xmb;
	}
	
	/**
	 * 根据项目信息获取学年
	 * @param xszzForm
	 * @return XszzTyForm 
	 */
	public XszzTyForm getXmxn(XszzTyForm xszzForm){
		
		XszzXscjDAO xscjDao=new XszzXscjDAO();
		//获取项目表
		String xmb = getCxyXn(xszzForm);
		
		String xn="";
		String sqsj="";
		//判断是否是外设表
		if("xszz_comm_zzwsb".equalsIgnoreCase(xmb)){
			xn=xscjDao.getWsbXn(xszzForm).get("xn");
			if("".equalsIgnoreCase(xn) || null==xn){
				sqsj=xscjDao.getWsbXn(xszzForm).get("sqsj");
				xn=getSqsjToXn(sqsj);
			}
		}else {
			xn=xscjDao.getXmbXn(xszzForm).get("xn");
			if("".equalsIgnoreCase(xn) || null==xn){
				sqsj=xscjDao.getXmbXn(xszzForm).get("sqsj");
				xn=getSqsjToXn(sqsj);
			}	
		}
		xszzForm.setXn(xn);
		return xszzForm;
	}
}
