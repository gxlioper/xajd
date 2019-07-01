package xgxt.szdw.bjlh.fdykh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.szdw.bjlh.SzkhCssz;
import xgxt.utils.ExcelMethods;
import xsgzgl.comm.BasicService;

import common.Globals;

public class BjlhFdykhService extends BasicService{

	BjlhFdykhDAO dao = new BjlhFdykhDAO();
	
	/**
	 * ��ȡ��ͷ
	 * @param type
	 * @return
	 */
	public String[] getTopTr(String type){
		String[] topTr=new String[]{};
		if("khdxxz".equals(type)){
			topTr=new String[]{"ְ����","����","ѧԺ","����С���Ա"};
		}else if("khdxxzyh".equals(type)){
			topTr=new String[]{"�û���","����","ѧԺ"};
		}else if("fdykh".equals(type)){
			topTr=new String[]{"ѧ��","���˱�ID","���˱�����","���ֶ���","�Ƿ�����"};
		}else if("khbxm".equals(type)){//���˱���Ŀ
			topTr=new String[]{"һ��ָ��","����ָ��","��������","��ֵ","��������","��ʾ˳��"};
		}else if("khbtj".equals(type)){//����ͳ�� 
			// ѧУ����
			String xxdm = Base.xxdm;
			if(xxdm.equals(Globals.XXDM_CZZYJSXY)){
				topTr=new String[]{"ѧ��", "ְ����","����", "���ڲ���","ѧ���������","ѧ��ƽ����","����С��ƽ����","�ܷ�","���˵ȼ�","�����ȼ�"};
			}else{
				if("xs".equalsIgnoreCase(SzkhCssz.KHRY)){//ֻ��ѧ������
					topTr=new String[]{"ѧ��", "ְ����","����", "���ڲ���","ѧ���������","ѧ��ƽ����","�ܷ�"};
				} else if("cpz".equalsIgnoreCase(SzkhCssz.KHRY)){//ֻ�в����鿼��
					topTr=new String[]{"ѧ��", "ְ����","����", "���ڲ���","����С��ƽ����","�ܷ�"};
				}else{
					topTr=new String[]{"ѧ��", "ְ����","����", "���ڲ���","ѧ���������","ѧ��ƽ����","����С��ƽ����","�ܷ�"};
				}
				
			}
		}else if("jskhcp".equals(type)){
			topTr=new String[]{"ѧ��","ְ����","����","���ڲ���","�Ƿ���","�ܷ�"};
		}
		
		return topTr;
	}
	/**
	 * ��ȡ����Ա�����б�
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getTableList(BjlhFdykhForm myForm,HttpServletRequest request) throws Exception{
		return dao.getTableList(myForm,request);
	}
	
	/**
	 * ��ѯ�û�
	 * @param form
	 * @return
	 */
	public List<String[]> queryYh(BjlhFdykhForm myForm){
		return dao.queryYh(myForm);
	}

	public boolean saveFdyKh(String[] yhs, String[] khzgh) {
		List<String[]> params = new ArrayList<String[]>(); 
		List<String[]> delParams = new ArrayList<String[]>();
		
		for(int i=0; i<khzgh.length; i++){
			delParams.add(new String[]{khzgh[i]});
			for(int j=0; j<yhs.length; j++){
				params.add(new String[]{yhs[j],khzgh[i]});
			}
		}
		
		return dao.saveFdyKh(params, delParams);
	}
	
	/**
	 * ��ȡ����Ա�����б�
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getFdykhList(BjlhFdykhForm model) throws Exception{
		return dao.getFdykhList(model);
	}
	
	/**
	 * ��ȡ���ֶ����б�
	 * @return
	 */
	public List<HashMap<String,String>> getPfdxList(){
		List<HashMap<String,String>> pfdx=new ArrayList<HashMap<String,String>>();
		if(SzkhCssz.KHRY.equalsIgnoreCase("xs")){
			HashMap<String,String> xs=new HashMap<String, String>();
			xs.put("dm", "xs");
			xs.put("mc", "ѧ��");
			pfdx.add(xs);
		}else if(SzkhCssz.KHRY.equalsIgnoreCase("cpz")){
			HashMap<String,String> pfxz=new HashMap<String, String>();
			pfxz.put("dm", "pfxz");
			pfxz.put("mc", "����С��");
			pfdx.add(pfxz);
		}else{
			HashMap<String,String> xs=new HashMap<String, String>();
			xs.put("dm", "xs");
			xs.put("mc", "ѧ��");
			pfdx.add(xs);
			HashMap<String,String> pfxz=new HashMap<String, String>();
			pfxz.put("dm", "pfxz");
			pfxz.put("mc", "����С��");
			pfdx.add(pfxz);
		}
		
		return pfdx;
	}
	
	/**
	 * ���濼�˱���Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveKhbInfo(BjlhFdykhForm model,String type) throws Exception{
		return dao.saveKhbInfo(model, type);
	}
	
	/**
	 * ɾ�����˱���Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String deleteKhbInfo(BjlhFdykhForm model) throws Exception{
		String msg=dao.checkKhbQx(model);
		if("".equals(msg)){
			msg=dao.deleteKhbInfo(model)?"ɾ���ɹ���":"ɾ��ʧ�ܣ�";
		}else{
			msg+="������ɾ����";
		}
		return msg;
	}
	
	/**
	 * ��֤���˱�Ȩ��
	 * @param model
	 * @return
	 */
	public String checkKhbQx(BjlhFdykhForm model){
		return dao.checkKhbQx(model);
	}
	
	/**
	 * ���ƿ��˱�
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean copyKhbInfo(BjlhFdykhForm model) throws Exception{
		return dao.copyKhbInfo(model);
	}

	/**
	 * ���˱�����״̬ά��
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String sfqyKhb(BjlhFdykhForm model) throws Exception{
		return dao.sfqyKhb(model);
	}
	
	/**
	 * ��ȡ����Ա������Ŀ�б�
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getFdykhXmList(BjlhFdykhForm model){
		return dao.getFdykhXmList(model);
	}
	
	/**
	 * ���濼�˱���Ŀ��Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveKhbXmxx(BjlhFdykhForm model,String type) throws Exception{
		return dao.saveKhbXmxx(model,type);
	}
	
	/**
	 * ɾ�����˱���Ŀ��Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteKhbXmxx(BjlhFdykhForm model) throws Exception{
		return dao.deleteKhbXmxx(model);
	}
	
	/**
	 * ��ȡ���˱���Ŀ��Ϣ������¼
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getKhbXmxxOne(BjlhFdykhForm model){
		return dao.getKhbXmxxOne(model);
	}
		
	/**
	 * ��ȡ���˱���Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getKhbxx(BjlhFdykhForm model){
		return dao.getKhbxx(model);
	}
	
	/**
	 * ��ȡ��������һ��ָ��Ŀ��˱���Ŀ�б�
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getKhbXmYjzbColNum(BjlhFdykhForm model){
		List<HashMap<String,String>> xmList=getFdykhXmList(model);//������Ŀ�б�
		if(xmList==null||xmList.size()==0){
			return null;
		}
		HashMap<String,String> currXm=xmList.get(0);
		HashMap<String,String> yjzbStartXm=currXm;//һ��ָ����ʼ��Ŀ
		String yjzb=currXm.get("yjzb");//һ��ָ��
		int yjzbRowNum=0;//һ��ָ��ϲ���Ԫ�����
		for(int i=0;i<xmList.size();i++){
			currXm=xmList.get(i);
			if(yjzb.equals(currXm.get("yjzb"))){
				yjzbRowNum++;
			}else{
				yjzbStartXm.put("yjzbRowNum", yjzbRowNum+"");
				yjzbStartXm=currXm;
				yjzbRowNum=1;
				yjzb=currXm.get("yjzb");
			}
			//�����ѭ�����������ֱ�ӷ���
			if(i==xmList.size()-1){
				yjzbStartXm.put("yjzbRowNum", yjzbRowNum+"");
			}
		}
		return xmList;
	}
	
	/**
	 * ��ȡ����Ա���˲���
	 * @param userName
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public  List<String[]> getFdykhcpList(String userName, BjlhFdykhForm myForm,
			HttpServletRequest request) throws Exception {
		return dao.getFdykhcpList(userName,myForm,request);
	}
		
	/**
	 * ��ȡͳ��
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFdyKhTjInfo(BjlhFdykhForm myForm,HttpServletRequest request) throws Exception{
		return dao.getFdyKhTjInfo(myForm,request);
	}
	
	/**
	 * ��ȡͳ�ƣ�����ѧԺ��
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFdyKhTjInfoforCzxy(BjlhFdykhForm myForm,HttpServletRequest request) throws Exception{
		return dao.getFdyKhTjInfoforCzxy(myForm,request);
	}
	
	/**
	 * ��ø���Ա��Ϣ
	 * @param yhm
	 * @return
	 */
	public List<HashMap<String,String>> getYhmxx(String yhm,String lx) {
		return dao.getYhmxx(yhm,lx);
	}
	
	public HashMap<String, String> getMrsz(String pfdx) {
		return dao.getMrsz(pfdx);
	}
	
	public String getDate(){
		return dao.getNowTime("YYYYMMDD");
	}
	public boolean saveFdykhpfb(String[] xmid, String[] df,BjlhFdykhForm myForm) throws Exception {
		return dao.saveFdykhpfb(xmid,df,myForm);
	}
	
	public boolean checkFdykhpf(String xmid[],String[] df) throws Exception{
		boolean flag;
		flag = true;
		for(int i=0;i<df.length;i++){
			if(!dao.checkFdykhpf(xmid[i], df[i])){
				flag =false;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * ��ȡ���˱��Ƿ��������ʾ�
	 * @param model
	 * @return
	 */
	public boolean getKhbSfzd(BjlhFdykhForm myForm){
		return dao.getKhbSfzd(myForm);
	}
	
	/**
	 * �ж��Ƿ񳬹�����ʱ��
	 * @param
	 * @return
	 */
	public boolean getSfcgkhsj(){
		String dqsj = dao.getNowTime("YYYYMMDD");
		return dao.getSfcgkhsj(dqsj);
	}
	
	/**
	 * ����Ա�ɼ�ͳ��ά��
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> fdycjTjWh(BjlhFdykhForm myForm) {
		return dao.fdycjTjWh(myForm);
	}
	
	/**
	 * ��ȡ���˵ȼ�����ֵ
	 * @param request
	 * @return
	 */
	public Object getKhDjList(HttpServletRequest request) {
		return dao.getKhDjList(request);
	}
	
	/**
	 * ��ȡ�����ȼ�����ֵ
	 * @param request
	 * @return
	 */
	public Object getJtDjList(HttpServletRequest request) {
		return dao.getJtDjList(request);
	}
	
	/**
	 * ����Ա�ɼ�ͳ��ά������
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public boolean fdycjTjWhBc(BjlhFdykhForm myForm) throws Exception {
		return dao.fdycjTjWhBc(myForm);
	}
	
	/**
	 * ����Ա�ɼ�ͳ�Ƶ���
	 * @param myForm
	 * @param outputStream
	 * @param xn
	 * @param yf
	 * @param xymc
	 * @throws Exception 
	 */
	public void expFdycjTj(BjlhFdykhForm myForm,ServletOutputStream os,HttpServletRequest request) throws Exception {
		String title = "����Ա�ɼ�ͳ��";
		// �̶���
		String[] gdName = new String[] { "ѧ��", "ְ����", "����", "���ڲ���", "ѧ���������","ѧ��ƽ����","����С��ƽ����","�ܷ�","���˵ȼ�","�����ȼ�" };
		// ���������ͷ
		List<HashMap<String, String>> topTr = getTopList(gdName);
		// ��������Ĺ̶�����
		List<String[]> gdlist = dao.getFdyKhTjInfoforCzxyNotFy(myForm,request);

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ����ͷ
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 10, 800);// ����
		
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.setColumnView(i, 25);
				ws.addCell(new Label(i, 1, map.get("cn"), wcf2));
			}
		}
		// �������
		if (gdlist != null && gdlist.size() > 0) {
			for (int i = 0; i < gdlist.size(); i++) {
				String[] info = gdlist.get(i);
				if (info != null && info.length > 0) {
					for (int j = 1; j < info.length; j++) {
						ws.addCell(new Label(j-1, i + 2, info[j], wcf2));
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/** 
	 * @����:(��Ŀ��ƽ�����б�һ�������д���)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-9 ����04:58:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * Object �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFdykhmx(BjlhFdykhForm model) {
		List<HashMap<String,String>> xmList=getKhXmPjfList(model);//������Ŀ�б�ƽ����
		if(xmList==null||xmList.size()==0){
			return null;
		}
		HashMap<String,String> currXm=xmList.get(0);
		HashMap<String,String> yjzbStartXm=currXm;//һ��ָ����ʼ��Ŀ
		String yjzb=currXm.get("yjzb");//һ��ָ��
		int yjzbRowNum=0;//һ��ָ��ϲ���Ԫ�����
		for(int i=0;i<xmList.size();i++){
			currXm=xmList.get(i);
			if(yjzb.equals(currXm.get("yjzb"))){
				yjzbRowNum++;
			}else{
				yjzbStartXm.put("yjzbRowNum", yjzbRowNum+"");
				yjzbStartXm=currXm;
				yjzbRowNum=1;
				yjzb=currXm.get("yjzb");
			}
			//�����ѭ�����������ֱ�ӷ���
			if(i==xmList.size()-1){
				yjzbStartXm.put("yjzbRowNum", yjzbRowNum+"");
			}
		}
		return xmList;
	}
	/** 
	 * @����:(��ȡ����Ա���˱����Ŀ��ƽ�����б�)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-9 ����05:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	private List<HashMap<String, String>> getKhXmPjfList(BjlhFdykhForm model) {
		// TODO �Զ����ɷ������
		return dao.getKhXmPjfList(model);
	}
	/** 
	 * @����:��ȡѧ����Ҫ���˵ĸ���Աlist
	 * @���ߣ�cmj
	 * @���ڣ�2013-12-13 ����10:08:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKhFdyList(BjlhFdykhForm myForm) {
		String lx="";
		if("fdy".equalsIgnoreCase(SzkhCssz.KHDX)){//���ֶ��󸨵�Ա
			lx="����Ա";
		} else if("bzr".equalsIgnoreCase(SzkhCssz.KHDX)){//���ֶ��������
			lx="������";
		}
		return dao.getKhFdyList(myForm,lx);
	}
	/** 
	 * @����:��������
	 * @���ߣ�������
	 * @���ڣ�2013-12-16 ����05:28:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getAllList(BjlhFdykhForm model,
			HttpServletRequest request) throws Exception{
		return dao.getAllList(model,request);
	}
}
