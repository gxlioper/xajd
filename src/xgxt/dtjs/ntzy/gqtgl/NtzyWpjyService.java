package xgxt.dtjs.ntzy.gqtgl;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;
import xgxt.utils.MakeQuery;
import xgxt.xsgygl.GyglTyService;

import com.zfsoft.basic.BasicService;

public class NtzyWpjyService {
	/**
	 * ��ȡ��½������
	 */
	public String getDlrXm(String userName){
		NtzyWpjyDao dao=new NtzyWpjyDao();
		String xm=dao.getDlrXm(userName).get("xm");
		xm=Base.isNull(xm)? "" : xm;
		return xm;
	}
	
	/**
	 * ��ȡѧ������
	 */
	public String getXsxm(String userName){
		NtzyWpjyDao dao=new NtzyWpjyDao();
		String xm=dao.getXsXm(userName).get("xm");	
		xm=Base.isNull(xm)?"" :xm;
		return xm;
	}
	
	/**
	 * ��������
	 * �����豸��Ϣ
	 */
	public void plSave(HttpServletRequest request,NtzyWpjyForm form) throws Exception{
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();
	
		//�������ݲ��� �ı���
		String realTable ="ntzy_wpb";
		
		//�����ѯ����������Ϣ��XH����
		String sqsj =form.getSave_sqsj();
		String jyrq= form.getSave_jyrq();
		String sqr =form.getSave_sqr(); 
		String xn=Base.currXn;
		String xq=Base.currXq;
		String []plsbdm= form.getSbdm();
		String []plsbdw = form.getSbdw();
		String []plsbsl = form.getSbsl();
		String []plghbz = form.getGhqk();
		String []pkValue =new String[plsbdm.length];
		//�жϲ����Ƿ�ɹ�
		boolean reslut = false;
		
		for(int i=0;i<plsbdm.length;i++){
			pkValue[i]=sqr+sqsj+jyrq;
		}
		
		String []sqsji=new String[pkValue.length];
		String []jyrqi=new String[pkValue.length];
		String []sqri=new String[pkValue.length];
		String []xni=new String[pkValue.length];
		String []xqi=new String[pkValue.length];
		for(int i=0;i<pkValue.length;i++){
			sqsji[i]=sqsj;
			jyrqi[i]=jyrq;
			sqri[i]=sqr;
			xni[i]=xn;
			xqi[i]=xq;	
		}
		
		String pk = "sqr || sqsj || jyrq  ";
		String[] arrzd = new String[] {"sqr","sqsj","jyrq","xn","xq","sbdm","sbdw","sbsl","ghbz"};
	
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		
				
		NtzyWpjyModel ntzyWpjyModel=new NtzyWpjyModel();
		ntzyWpjyModel.setSqr(sqri);//������
		ntzyWpjyModel.setSqsj(sqsji);//����ʱ��
		ntzyWpjyModel.setJyrq(jyrqi);//��������
		ntzyWpjyModel.setSbdm(plsbdm);//�걨����
		ntzyWpjyModel.setSbdw(plsbdw);//�걨��λ
		ntzyWpjyModel.setSbsl(plsbsl);//�걨����
		ntzyWpjyModel.setGhbz(plghbz);//�黹��ע
		ntzyWpjyModel.setXn(xni);
		ntzyWpjyModel.setXq(xqi);
		reslut = ghxyNjzrwhService.saveTyxx(saveForm, ntzyWpjyModel);
		request.setAttribute("result", reslut);
			
	}
	
	
	/**
	 * ɾ�����������Ϣ(��Ʒ��)
	 * @throws Exception 
	 * 
	 */
	public void delWpbxx() throws Exception{
		NtzyWpjyDao dao=new NtzyWpjyDao();
		dao.delWpbXx();
	}
	
	
	
	/**
	 * ��ȡ���ֵ
	 */
	public HashMap<String,String> getValueMap(HttpServletRequest request,
			String userType,String shjg){
		HashMap<String,String> hashMap=new HashMap<String,String>();
		String shjb="";//��˼���
		String shsj="";//���ʱ��
		if("xy".equalsIgnoreCase(userType)){
			shjb="xysh";
			shsj="xyshsj";
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			shjb="xxsh";
			shsj="xxshsj";
			hashMap.put("xysh", "ͨ��");
		}
		hashMap.put(shjb, shjg);
		hashMap.put(shsj,GetTime.getNowTime2());
		return hashMap;
	}
	
	/**
	 * �黹��Ʒ
	 * 
	 */
	public HashMap<String,String> getGhwpMap(String userName,String act){
		NtzyWpjyService service =new NtzyWpjyService();
		HashMap<String,String> hashMap=new HashMap<String,String>();
		if("gh".equals(act)){
			hashMap.put("ghrq", GetTime.getNowTime2());
			hashMap.put("ysr",service.getDlrXm(userName));
		}else{
			hashMap.put("ghrq","δ�黹");
			hashMap.put("ysr","δ�黹");
		}
		
		return hashMap;
	}
	
	/**
	 * �ж����ʱ��Ȩ��
	 * 
	 */
	public void setDisabled(HttpServletRequest request,String userType,String userDep){
		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case when xxsh='ͨ��' then 'disabled' else '' end ) disabled, ");
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("annexTerm", " and (xysh='ͨ��' or bmdm='"+userDep+"') ");
			request.setAttribute("clientColumns", "'' disabled, ");
		}else {
			request.setAttribute("clientColumns", "'disabled' disabled, ");
		}
	}
	
	/**
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * 
	 */
	public List<String[]> getWpbXx(String pkValue) throws Exception{
		NtzyWpjyModel model=new NtzyWpjyModel();
		String sql="select rownum r,a.* from( select * from ntzy_wpb  where sqr||sqsj||jyrq=?)a";
		String []colList={"r","sbdm","sbdw","sbsl","ghbz"};
		List<String[]> li= CommonQueryDAO.commonQueryNotFy(sql, "", new String[]{pkValue},colList , model);
		if(li.size()<10){
			int len=li.size();
			for(int i=len;i<10;i++){
				String[] str=new String[5];
				str[0]=""+(i+1);
				li.add(str);
			}
		}
		return li;
	}
	
	/**
	 * ��Ʒ�黹
	 * @param request
	 * @param hashMap
	 * @param userType
	 */
	public void setWpgh(HttpServletRequest request,String userType){
		request.setAttribute("clientColumns", "'' disabled, ");
		//ֻ��ѧУ���ͨ�������Ʒ�����ݣ����ܽ��й黹����
		request.setAttribute("annexTerm", " and xxsh='ͨ��' ");
	}
	
	public void getShql(HttpServletRequest request,HashMap<String,String>hashMap,
			String userType){
		String write="yes";
		if(!"xx".equalsIgnoreCase(userType)
				|| !"admin".equalsIgnoreCase(userType)){
			if("ͨ��".equalsIgnoreCase(hashMap.get("xxsh"))){
				write="no";
			}
		}
		request.setAttribute("write", write);
	}
	
	/**
	 * ��ȡ��Ʒ���ñ�List
	 */
	public ArrayList<String[]> getWpjyList(NtzyWpjyForm wpjyForm) throws Exception{
		String sql="select * from ntzy_wpjyb ";
		MakeQuery mQuery=new MakeQuery();
		String[] colList={"xn","xq","bmdm","xysh","xxsh"};
		String[] colLikeLis={"sqr","jbr"};
		String[] output={"sqr","xn","xq","sqsj","sqbm","jyrq","xysh","xxsh",
				"jbr","lxdh","sqsy","sqbyj","sybyj","ghrq","ysr","bz","bmdm","xyshsj","xxshsj","xyyj","xxyj"};
		mQuery.makeQuery(colList, colLikeLis, wpjyForm);
		return CommonQueryDAO.commonQueryNotFy(sql, mQuery.getQueryString(),mQuery.getInputList() , output, wpjyForm);
	}
	
	/**
	 * ��ȡ��Ʒ��List
	 */
	public ArrayList<String[]> getWpbList(NtzyWpjyForm wpjyForm) throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("select a.* from ntzy_wpb a,ntzy_wpjyb b where a.sqr=b.sqr and a.sqsj=b.sqsj and a.jyrq=b.jyrq  ");
		if(!"".equals(wpjyForm.getXn())){
			sql.append(" and b.xn='"+wpjyForm.getXn()+"'");
		}
		if(!"".equals(wpjyForm.getXq())){
			sql.append(" and b.xq='"+wpjyForm.getXq()+"'");
		}
		if(!"".equals(wpjyForm.getBmdm())){
			sql.append(" and b.bmdm='"+wpjyForm.getBmdm()+"'");
		}
		if(!"".equals(wpjyForm.getXysh())){
			sql.append(" and b.xysh='"+wpjyForm.getXysh()+"'");
		}
		if(!"".equals(wpjyForm.getXxsh())){
			sql.append(" and b.xxsh='"+wpjyForm.getXxsh()+"'");
		}
		if(!"".equals(wpjyForm.getSqr())){
			sql.append(" and b.sqr='"+wpjyForm.getSqr()+"'");
		}
		if(!"".equals(wpjyForm.getJbr())){
			sql.append(" and b.jbr='"+wpjyForm.getJbr()+"'");
		}
		String[] outPut={"sqr","xn","xq","sqsj","jyrq","sbdm","sbdw","sbsl","ghbz"};
		return CommonQueryDAO.commonQueryNotFy(sql.toString(),"" ,new String[]{} ,outPut , wpjyForm);
	}
	
	public void expDate(OutputStream os,NtzyWpjyForm form,HttpServletRequest request,String viewName) throws Exception {
		//��ҳ����Ҫ�����ı���
		GyglTyService gyglTyService = new GyglTyService();
		BasicService basicService = new BasicService();
		NtzyWpjyService service =new NtzyWpjyService();
		String []topTr=null;
		
		form.setBmdm(form.getQueryequals_bmdm());
		form.setXn(form.getQueryequals_xn());
		form.setXq(form.getQueryequals_xq());
		form.setXysh(form.getQueryequals_xysh());
		form.setXxsh(form.getQueryequals_xxsh());
		form.setSqr(form.getQuerylike_sqr());
		form.setJbr(form.getQuerylike_jbr());
		
		if("ntzy_wpb".equals(viewName)){
			topTr=new String[]{"������","ѧ��","ѧ��","����ʱ��","��������","�걨����",
					"�걨��λ","�걨����","�黹��ע"};
			gyglTyService.expToExcel("sheet1", basicService.getTopTr("",topTr), service.getWpbList(form),os);
		}else{
			topTr=new String[]{"������","ѧ��","ѧ��","����ʱ��","���벿��","��������",
					Base.YXPZXY_KEY+"���","ѧУ���","������","��ϵ�绰","��������","���벿�����",
					"���в������","�黹����","������","��ע","���Ŵ���",Base.YXPZXY_KEY+"���ʱ��",
					"ѧУ���ʱ��",Base.YXPZXY_KEY+"���","ѧУ���"};
			
			
			gyglTyService.expToExcel("sheet1", basicService.getTopTr("",topTr), service.getWpjyList(form),os);
			
		}
		
		
	}
}
