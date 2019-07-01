package xgxt.xszz.comm.xscj;

import java.util.HashMap;
import java.util.List;

import xgxt.xszz.XszzTyForm;

public class XszzXscjService {
	
	/**
	 * ��ȡ����ʱ��
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
	 * ��ȡ��һѧ��
	 * @param xn
	 * @return String
	 */
	public XszzTyForm getSxn(XszzTyForm xszzForm){
		//���ѧ��ֱ�-1
		String xmdm=xszzForm.getXmdm();
		String xn=xszzForm.getXn();
		String pdsj=getPdsj(xmdm);
		
		if("����".equalsIgnoreCase(pdsj)){
			
		}else if("ǰ��".equalsIgnoreCase(pdsj)){
			//��ȡ��һѧ��
			int firstNd=Integer.parseInt(xn.substring(0,4))-1;
			int secoundNd=Integer.parseInt(xn.substring(5, 9))-1;
			xn=firstNd+"-"+secoundNd;
		}
		xszzForm.setXn(xn);
		return xszzForm;
	}
	
	/**
	 * ��������ʱ���ȡѧ��
	 * @param xn
	 * @return String
	 */
	public String getSqsjToXn(String sqsj){
		//��ȡ��
		sqsj=sqsj.substring(0,4);
		int secoundNd=Integer.parseInt(sqsj)+1;
		String xn=sqsj+"-"+secoundNd;
		return xn;
	}
	
	
	/**
	 * ��ȡ��Ŀ����
	 * @param xszzForm
	 * @return String 
	 */
	public String getCxyXn(XszzTyForm xszzForm){
		XszzXscjDAO xscjDao=new XszzXscjDAO();
		String xmb=xscjDao.getXmb(xszzForm).get("xmb");
		return  xmb;
	}
	
	/**
	 * ������Ŀ��Ϣ��ȡѧ��
	 * @param xszzForm
	 * @return XszzTyForm 
	 */
	public XszzTyForm getXmxn(XszzTyForm xszzForm){
		
		XszzXscjDAO xscjDao=new XszzXscjDAO();
		//��ȡ��Ŀ��
		String xmb = getCxyXn(xszzForm);
		
		String xn="";
		String sqsj="";
		//�ж��Ƿ��������
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
