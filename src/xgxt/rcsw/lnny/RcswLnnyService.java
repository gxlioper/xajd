package xgxt.rcsw.lnny;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.SaveForm;
import xgxt.mdqr.MdqrModel;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.sztz.csmzzyjsxy.SztzTjlgDAO;
import xgxt.sztz.form.SztzForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;

public class RcswLnnyService {
	
	RcswLnnyDAO dao=new RcswLnnyDAO();
	/**
	 * 
	 * @return
	 */
	public List<HashMap<String,String>>getXmlxList(){
		return dao.getXmlxList();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<HashMap<String,String>>getXmList(){
		return dao.getXmList();
	}
	
	public List<String[]>getRs(RcswLnnyForm myForm,HttpServletRequest request) throws Exception{
		return dao.getXmFzList(myForm,request);
	}
	
	public List<HashMap<String,String>>getXmList(RcswLnnyForm myForm){
		
		return dao.getXmList(myForm);
	}
	
	
	public boolean saveYrgf(RcswLnnyForm myForm,HttpServletRequest request) throws Exception{
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();
		
		RcswLnnyModel model=new RcswLnnyModel();
		// �������ݲ��� �ı���
		String realTable = "xg_rcsw_yrgfb";

		// ��ǰҳ��ѧ������(��ɾ��������ҳ��Ϣɾ��ʱ)
		String[] xh = myForm.getXhArr();
		
		List<HashMap<String,String>>xmlist=dao.getXmList(myForm);
		
		String dfsj=myForm.getDfsj();
		
		//�����ֶ�
		String pk="xh||xmdm||dfsj";
		
		int m = 0;
		int pkLen = xh.length * xmlist.size();
		String[] pkValue = new String[pkLen];
		//================����ƴ��========================
		
		for (int i = 0; i < xh.length; i++) {
			// ����ʱ��Ŀ��
			for (int j = 0; j < xmlist.size(); j++) {
				HashMap<String, String> hashMap = xmlist.get(j);
				pkValue[m] = xh[i] + hashMap.get("dm") + dfsj;
				m++;
			}
		}
		//================����ƴ�����========================
		m=0;
		int n=0;
		String[]fzArr=new String[xmlist.size()*xh.length];
		for(int j=0;j<xh.length;j++){
			for(int i=0;i<xmlist.size();i++){
			
				fzArr[m]=request.getParameter("fzArr_"+i+"_"+j);
				if(!"null".equalsIgnoreCase(fzArr[m])
						&& null!=fzArr[m]){
					n++;
				}
				m++;
			}
		}
		
		
		m=0;
		
		String[]xhArr=new String[n];
		String[]xmArr=new String[n];
		String[]fz=new String[n];
		n=0;
		for (int i = 0; i < xh.length; i++) {
			// ����ʱ��Ŀ��
			for (int j = 0; j < xmlist.size(); j++) {
				if(!"null".equalsIgnoreCase(fzArr[m])
						&& null!=fzArr[m]){
					HashMap<String,String>xmMap=xmlist.get(j);
					xhArr[n]=xh[i];
					xmArr[n]=xmMap.get("dm");
					//�ж���Ŀ�Ǽ��ֻ��Ǽӷ�
					if("����".equalsIgnoreCase(xmMap.get("xmxz"))){
						fz[n]="-"+xmMap.get("fz");
					}else{
						fz[n]=xmMap.get("fz");
					}
					n++;
				}
				m++;
			}
		}
		
		model.setXh(xhArr);
		model.setXmdm(xmArr);
		model.setFz(fz);
		model.setXn(myForm.getXn());
		model.setXq(myForm.getXq());
		model.setNd(myForm.getNd());
		model.setDjr(myForm.getUserName());
		model.setDjsj(myForm.getDjsj());
		model.setDfsj(myForm.getDfsj());
		
		SaveForm saveForm = new SaveForm();
		
		String []arrzd={"xh","xmdm","fz"};	
		String []onezd={"xn","xq","nd","djr","djsj","dfsj"};	
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		return ghxyNjzrwhService.saveTyxx(saveForm, model);
	}
	
	public List<String[]>getTjxx(RcswLnnyForm myForm,HttpServletRequest request) throws Exception{
		return dao.getTjxx(myForm,request);
	}
	
	
	/**
	 * ����ְҵ�ڶ���ּ�¼���ܱ��ӡ
	 * @throws Exception 
	 */
	public void yrgffTj(RcswLnnyForm myForm,
			HttpServletRequest request, WritableWorkbook wwb)
			throws Exception {
			//��ȡ��Ŀͳ���б�
			List<HashMap<String,String>>tjxmList=dao.getTjXm();
			
			//��ȡ��Ŀ���ͳ���б�
			List<HashMap<String,String>>tjxmlxList=dao.getTjXmlx();
			
			//��ȡ��Ŀͳ����Ϣ
			List<String[]>tjxmxxList=dao.getTjxx(myForm);
			try {
				// ����xls��SHEET����
				WritableSheet ws = wwb.getSheet(0);
				WritableCellFormat wcfTytle = new WritableCellFormat();
				// ���ö��뷽ʽ
				Alignment alignMent = Alignment.CENTRE;
				VerticalAlignment vag = VerticalAlignment.CENTRE;

				wcfTytle.setVerticalAlignment(vag);
				wcfTytle.setAlignment(alignMent);

				WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
				wfTytle.setBoldStyle(WritableFont.BOLD);
				wfTytle.setPointSize(16);
				wcfTytle.setFont(wfTytle);
				//�ϲ���Ԫ��(��ͷ)
				ws.mergeCells(0, 0,3+tjxmList.size(), 0);
				//ѧУ����
				ws.addCell(new Label(0,0,Base.xxmc,wcfTytle));
				ws.mergeCells(0, 1,3+tjxmList.size(), 1);
				ws.addCell(new Label(0,1,"ѧ��һ�չ淶��ͳ��",wcfTytle));
				wcfTytle = new WritableCellFormat();
				alignMent = Alignment.LEFT;
				vag = VerticalAlignment.CENTRE;

				wcfTytle.setVerticalAlignment(vag);
				wcfTytle.setAlignment(alignMent);
				wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

				wfTytle = new WritableFont(WritableFont.ARIAL);
				wfTytle.setBoldStyle(WritableFont.BOLD);
				wfTytle.setPointSize(10);
				wcfTytle.setFont(wfTytle);

				wcfTytle = new WritableCellFormat();
				alignMent = Alignment.CENTRE;
				vag = VerticalAlignment.CENTRE;

				wcfTytle.setVerticalAlignment(vag);
				wcfTytle.setAlignment(alignMent);
				wcfTytle.setWrap(true);
				//���ñ��߿�
				 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		

				wfTytle = new WritableFont(WritableFont.ARIAL);
				wfTytle.setPointSize(8);
				wcfTytle.setFont(wfTytle);
				
				
				// ===================ͳ����Ϣ ��ͷ===========================
				
				// ====================������Ϣ=========================
				ws.mergeCells(0, 2,0, 3);//�ϲ�
				ws.addCell(new Label(0,2,"ѧ��",wcfTytle));
				ws.mergeCells(1, 2,1, 3);//�ϲ�
				ws.addCell(new Label(1,2,"����",wcfTytle));
				ws.mergeCells(2, 2,2, 3);//�ϲ�
				ws.addCell(new Label(2,2,"�༶",wcfTytle));
				
				// ===================��Ŀ����===============================
				int m=0;//����
				for(int i=0;i<tjxmlxList.size();i++){
					HashMap<String,String>tjxmlxMap=tjxmlxList.get(i);
					int hbh=Integer.parseInt(tjxmlxMap.get("jls"));//�ϲ�����
					if(m==0){
						m=3;
					}
					ws.mergeCells(m, 2,m+hbh-1, 2);//�ϲ�
					ws.addCell(new Label(m,2,tjxmlxMap.get("xmlxmc"),wcfTytle));
					
					// =====================��Ŀ����=====================
					int n=0;
					for(int j=0;j<tjxmList.size();j++){
						HashMap<String,String>tjxmMap=tjxmList.get(j);
						if(tjxmlxMap.get("xmlxdm").equalsIgnoreCase(tjxmMap.get("xmlxdm"))){
							ws.addCell(new Label(m+n,3,tjxmMap.get("xmmc")+"("+(tjxmMap.get("xmxz").equalsIgnoreCase("�ӷ�")?"+":"-")+ tjxmMap.get("fz")+")",wcfTytle));
							n++;
						}
					}
					m+=hbh;
				
				}
				ws.mergeCells(m, 2,m, 3);//�ϲ�
				ws.addCell(new Label(m,2,"�ܷ�",wcfTytle));
				
			// ===================ͳ����Ϣ ��ͷend===========================
				
			//  ===================ѧ��һ�չ淶ͳ����Ϣ===========================
	
			for(int i=0;i<tjxmxxList.size();i++){
				String[]tjxxArr=tjxmxxList.get(i);
				for(int j=0;j<tjxxArr.length;j++){
					ws.addCell(new Label(j,4+i,tjxxArr[j],wcfTytle));
				}
				
			}	
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			// ��ͻ������
			ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public void initXmlxdm(RcswLnnyForm myForm){
		if("".equalsIgnoreCase(myForm.getXmlxdm())
				|| null==myForm.getXmlxdm()){
			List<HashMap<String,String>>xmlxList=dao.getXmlxList();
			if(xmlxList.size()>0){
				HashMap<String,String>xmlxMap=xmlxList.get(0);
				myForm.setXmlxdm(xmlxMap.get("dm"));
				myForm.setXmlxmc(xmlxMap.get("mc"));
			}
		}
	}
	
	/**
	 * ����ѧ�Ż�ȡѧ�����е���Ŀ����
	 * @param myForm
	 * @return List<HashMap<String,String>>
	 * author qlj
	 */
	public List<HashMap<String,String>>getXsXmlxList(RcswLnnyForm myForm){
		return dao.getXsXmlxList(myForm);
	}
	
	/**
	 * ����ѧ�Ż�ȡѧ����Ϣ
	 * @param myForm
	 * @return List<HashMap<String,String>>
	 * author qlj
	 * @throws Exception 
	 */
	public List<HashMap<String,String>>getXsXmList(RcswLnnyForm myForm) throws Exception{
		return dao.getXsXmList(myForm);
	}
	
	public boolean  checkExistsXm(){
		String[] nums=dao.checkExistsXm();
		int num=Integer.parseInt(nums[0]);
		if(num>0){
			return true;
		}else{
			return false;
		}
	}
}
