package xgxt.qgzx;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.MoneyFormat;
/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ڹ���ѧ-��𷢷�-�������ù���ͳ��-service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author HongLin
 * @version 1.0
 */
/**
 *
 */
/**
 *
 */
public class QgzxGbmygExcelService extends CommService  {

	/**
	 * ͳ�Ƶ�������excel
	 * @param myForm
	 * @param message
	 * @param request
	 * @param response
	 */
	public void printGbmyg(QgzxGbmygExcelActionForm myForm,MessageResources message,
			HttpServletRequest request,HttpServletResponse response)
			throws Exception,IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String title = "�������ù���ͳ��";
		String ffsjks = myForm.getFfsjks();
		String ffsjjs = myForm.getFfsjjs();
		title +="��";
		if(!StringUtils.isNull(ffsjks) && !StringUtils.isNull(ffsjjs)){
			title+=ffsjks+"��"+ffsjjs;
		}
		else if (!StringUtils.isNull(ffsjks) && StringUtils.isNull(ffsjjs)){
			title+=ffsjks+"-"+"����";
		}
		else if (StringUtils.isNull(ffsjks) && !StringUtils.isNull(ffsjjs)){
			title+="��ֹ��"+ffsjjs;
		}else{
			title+="ȫ��";
		}
		title+="��";
		//����һ��execl���
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response.getOutputStream());
		WritableCellFormat wcfTytle = new WritableCellFormat();//��Ԫ����ʽ
		WritableSheet ws = wwb.createSheet(title+"("+GetTime.getSystemTime()+")", 0);//�������
		//��ȡ���� 
		List<HashMap<String, String>> bmList= getBmmcList(myForm,request);
		
		try {			
			ws.setColumnView(1, 20);//���õ�Ԫ����(�У����)
			ws.setRowView(1, 50);//���õ�Ԫ��߶�(�У��߶�)
			ws.setColumnView(2, 30);
			ws.setColumnView(3, 20);
			ws.setColumnView(4, 20);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 20);
			//����
			wcfTytle = ExcelMB.getWritableCellFormat(18,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// ��Ԫ����ʽ
			ws.mergeCells(1, 2,6, 3);//�ϲ���Ԫ�񣨿�ʼy�У���ʼx������y������x�����ϲ�B3��G3�ĵ�Ԫ�񣬲��Һϲ�3��4��
			ws.addCell(new Label(1,2,title,wcfTytle));
			
			//��ͷ
			wcfTytle = ExcelMB.getWritableCellFormat(11,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// ��Ԫ����ʽ
			ws.addCell(new Label(1,4,"����",wcfTytle));//B5
			ws.addCell(new Label(2,4,"�ص�",wcfTytle));//C5
			ws.addCell(new Label(3,4,"������ʦ",wcfTytle));//D5
			ws.addCell(new Label(4,4,"��ϵ�绰",wcfTytle));//E5
			ws.addCell(new Label(5,4,"�̶����ˣ�",wcfTytle));//F5
			ws.addCell(new Label(6,4,"����",wcfTytle));//G5
			//��һ��
			wcfTytle = ExcelMB.getWritableCellFormat(11,false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// ��Ԫ����ʽ
			int zrs = 0;//������
			int zje = 0;//�ܽ��
			if(bmList != null && bmList.size()>0){
				//ѭ������
				for(int j=0; j<bmList.size();j++){
					HashMap<String,String> bmMap = bmList.get(j);
					String bmmc = bmMap.get("yrdwmc").toString();
					String bmdm = bmMap.get("yrdwdm").toString();
					ws.addCell(new Label(1,j+5,bmmc,wcfTytle));//B6
					ws.addCell(new Label(3,j+5,"",wcfTytle));//D6��������
					ws.addCell(new Label(4,j+5,"",wcfTytle));//E6��������
					//�ڶ���
					//��ȡ�ص�
					List<HashMap<String, String>> ddList= getDdmcList(myForm,request,bmdm);
					String dd="";//��Ž����
					int rs =0;//���������
					int je =0;//�������
					if(ddList != null && ddList.size()>0){
						//������ѭ������ѯ�������¥����ѧԺ��Ӧ�ķ�����
						dd="";
						for(int d=0; d<ddList.size();d++){
							HashMap<String,String> ddMap = ddList.get(d);
							String ddmc = ddMap.get("gwdm").toString();
							dd+=ddmc+"��";
							//������
							//��ȡ�̶����ˣ�������ʼ
							List<HashMap<String, String>> rsList= getRsslList(myForm,request,ddmc);
							rs+=rsList.size();
							zrs+=rsList.size();
							if(rsList != null && rsList.size()>0){
								for(int r=0; r<rsList.size();r++){
									HashMap<String,String> rsMap = rsList.get(r);
									String rsmc = rsMap.get("gwdm").toString();
									//���һ��,��ȡ����
									List<HashMap<String, String>> fyList= getFyjeList(myForm,request,rsmc,ffsjks,ffsjjs);
									if(fyList != null && fyList.size()>0){
										for(int f=0; f<fyList.size();f++){
											HashMap<String,String> fyMap = fyList.get(f);
											String cjje = fyMap.get("cjje").toString();
											je+=Integer.parseInt(cjje);
											zje+=Integer.parseInt(cjje);
										}
									}else{
										je+=0;
									}
								}
							}else{
								rs+=0;
								je+=0;
							}
							//��ȡ���� ����
						}
						ws.addCell(new Label(2,j+5,dd,wcfTytle));//C6���ص�
						ws.addCell(new Label(5,j+5,rs+"",wcfTytle));//f6������
						ws.addCell(new Label(6,j+5,je+"",wcfTytle));//g6������ ���
					}else{
						dd="";
						rs=0;
						ws.addCell(new Label(2,j+5,dd,wcfTytle));//C6���ص�
						ws.addCell(new Label(5,j+5,rs+"",wcfTytle));//f6������
						ws.addCell(new Label(6,j+5,je+"",wcfTytle));//g6�����Ž��
						continue;
					}
				}
				wcfTytle = ExcelMB.getWritableCellFormat(12,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// ��Ԫ����ʽ
				int num = bmList.size()+5;
				ws.addCell(new Label(1,num,"�ܼ�",wcfTytle));
				ws.addCell(new Label(2,num,"",wcfTytle));
				ws.addCell(new Label(3,num,"",wcfTytle));
				ws.addCell(new Label(4,num,"",wcfTytle));
				ws.addCell(new Label(5,num,zrs+"",wcfTytle));
				ws.addCell(new Label(6,num,zje+"",wcfTytle));
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	QgzxGbmygExcelDAO dao = new QgzxGbmygExcelDAO();
	/**
	 * ��ȡ�����б�
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getBmmcList(QgzxGbmygExcelActionForm myForm,HttpServletRequest request) throws Exception {
		return dao.getBmmcList(myForm,request);
	}
	
	/**
	 * ��ȡÿ�����ŵĵ�ַ
	 * @param myForm
	 * @param request
	 * @param sqdw
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDdmcList(QgzxGbmygExcelActionForm myForm,HttpServletRequest request,String sqdw) throws Exception {
		return dao.getDdmcList(myForm,request,sqdw);
	}
	/**
	 * ��ȡ�̶�����
	 * @param myForm
	 * @param request
	 * @param gwdm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRsslList(QgzxGbmygExcelActionForm myForm,HttpServletRequest request,String gwdm) throws Exception {
		return dao.getRsslList(myForm,request,gwdm);
	}
	/**
	 * ��ȡ���Ž��
	 * @param myForm
	 * @param request
	 * @param gwdm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFyjeList(QgzxGbmygExcelActionForm myForm,HttpServletRequest request,String gwdm,String ffsjks,String ffsjjs) throws Exception {
		return dao.getFyjeList(myForm,request,gwdm,ffsjks,ffsjjs);
	}
	/**
	 * 
	 * ���ʷ��ű�
	 * @param myForm
	 * @param message
	 * @param request
	 * @param response
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void printGzff(QgzxGbmygExcelActionForm myForm,MessageResources message,
			HttpServletRequest request,HttpServletResponse response)
			throws Exception,IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String title = "�㽭����ְҵ����ѧԺ�ڹ���ѧ���ʷ��ű�";
		String ffsjks = myForm.getFfsjks();
		String ffsjjs = myForm.getFfsjjs();
		title +="��";
		if(!StringUtils.isNull(ffsjks) && !StringUtils.isNull(ffsjjs)){
			title+=ffsjks+"��"+ffsjjs;
		}
		else if (!StringUtils.isNull(ffsjks) && StringUtils.isNull(ffsjjs)){
			title+=ffsjks+"-"+"����";
		}
		else if (StringUtils.isNull(ffsjks) && !StringUtils.isNull(ffsjjs)){
			title+="��ֹ��"+ffsjjs;
		}else{
			title+="ȫ��";
		}
		title+="��";
		//����һ��execl���
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response.getOutputStream());
		WritableCellFormat wcfTytle = new WritableCellFormat();//��Ԫ����ʽ
		WritableSheet ws = wwb.createSheet("���ʷ��ű�"+"("+GetTime.getSystemTime()+")", 0);//�������
		//��ȡ���� 
		List<HashMap<String, String>> bmList= getBmmcList(myForm,request);
		
		try {			
//			ws.setColumnView(1, 20);//���õ�Ԫ����(�У����)
			ws.setRowView(0, 700);//���õ�Ԫ��߶�(�У��߶�)
			ws.setColumnView(0, 10);
			ws.setColumnView(1, 15);
			ws.setColumnView(2, 20);
			ws.setColumnView(3, 20);
			ws.setColumnView(4, 30);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 20);
			ws.setColumnView(7, 10);
			ws.setColumnView(8, 10);
			ws.setColumnView(9, 10);
			ws.setColumnView(10, 20);
			//����
			wcfTytle = ExcelMB.getWritableCellFormat(12,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// ��Ԫ����ʽ
			ws.mergeCells(0, 0,10, 0);//�ϲ���Ԫ�񣨿�ʼy�У���ʼx������y������x��,��һ�кϲ�11����Ԫ��
			ws.addCell(new Label(0,0,title,wcfTytle));
			
			//��ͷ
			wcfTytle = ExcelMB.getWritableCellFormat(12,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// ��Ԫ����ʽ
			ws.addCell(new Label(0,1,"���",wcfTytle));
			ws.addCell(new Label(1,1,"�������",wcfTytle));
			ws.addCell(new Label(2,1,"����",wcfTytle));
			ws.addCell(new Label(3,1,"��λ����",wcfTytle));
			ws.addCell(new Label(4,1,"�༶",wcfTytle));
			ws.addCell(new Label(5,1,"����",wcfTytle));
			ws.addCell(new Label(6,1,"ѧ��",wcfTytle));
			ws.addCell(new Label(7,1,"Сʱ",wcfTytle));
			ws.addCell(new Label(8,1,"���",wcfTytle));
			ws.addCell(new Label(9,1,"��λ",wcfTytle));
			ws.addCell(new Label(10,1,"��ע",wcfTytle));
			//��һ��
			wcfTytle = ExcelMB.getWritableCellFormat(11,false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// ��Ԫ����ʽ
			int num=0;//��Ԫ��������
			int xlh =1;//���к�
			int hj =0;//�ϼ�
			if(bmList != null && bmList.size()>0){
				//ѭ������
				for(int j=0; j<bmList.size();j++){
					HashMap<String,String> bmMap = bmList.get(j);
					String bmmc = bmMap.get("yrdwmc").toString();
					String bmdm = bmMap.get("yrdwdm").toString();
					//ws.addCell(new Label(2,j+2,bmmc,wcfTytle));//����
					//����ÿ�����ŵĹ��ʷ�����Ϣ
					List<HashMap<String, String>> gzffList= getgzffList(myForm,request,bmmc,ffsjks,ffsjjs);
					if(gzffList != null && gzffList.size()>0){
						for(int g=0; g<gzffList.size();g++){
							HashMap<String,String> gzffMap = gzffList.get(g);
							String deptnum = gzffMap.get("deptnum").toString();//�������
							String yrdwmc = gzffMap.get("yrdwmc").toString();//��������
							String gwdm = gzffMap.get("gwdm").toString();//��λ����
							String bjmc = gzffMap.get("bjmc").toString();//�༶����
							String xm = gzffMap.get("xm").toString();//����
							String xh = gzffMap.get("xh").toString();//ѧ��
							String gzsj = gzffMap.get("gzsj").toString();//Сʱ
							String cjje = gzffMap.get("cjje").toString();//���
							String gwxzmc = gzffMap.get("gwxzmc").toString();//��λ
							String bz;//��ע
							if(gzffMap.get("bz") !=null && gzffMap.get("bz").length()>0){
								bz = gzffMap.get("bz").toString();
							}else{
								bz = "";
							}
							//��Ԫ������
							
							hj=hj+Integer.parseInt(cjje);
							ws.addCell(new Label(0,num+2,xlh+"",wcfTytle));//���
							ws.addCell(new Label(1,num+2,deptnum,wcfTytle));//�������
							ws.addCell(new Label(2,num+2,yrdwmc,wcfTytle));//����
							ws.addCell(new Label(3,num+2,gwdm,wcfTytle));//��λ����
							ws.addCell(new Label(4,num+2,bjmc,wcfTytle));//�༶����
							ws.addCell(new Label(5,num+2,xm,wcfTytle));//����
							ws.addCell(new Label(6,num+2,xh,wcfTytle));//ѧ��
							ws.addCell(new Label(7,num+2,gzsj,wcfTytle));//Сʱ
							ws.addCell(new Label(8,num+2,cjje,wcfTytle));//���
							ws.addCell(new Label(9,num+2,gwxzmc,wcfTytle));//��λ
							ws.addCell(new Label(10,num+2,bz,wcfTytle));//��ע
							num=num+1;//��Ԫ��������
							xlh=xlh+1;//���к�
						}
					}
					
				}
			}
			wcfTytle = ExcelMB.getWritableCellFormat(11,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// ��Ԫ����ʽ
			//�ϼ�
			ws.addCell(new Label(6,num+2,"�ϼ�",wcfTytle));
			ws.addCell(new Label(8,num+2,hj+"",wcfTytle));
			ws.addCell(new Label(0,num+2,"",wcfTytle));
			ws.addCell(new Label(1,num+2,"",wcfTytle));
			ws.addCell(new Label(2,num+2,"",wcfTytle));
			ws.addCell(new Label(3,num+2,"",wcfTytle));
			ws.addCell(new Label(4,num+2,"",wcfTytle));
			ws.addCell(new Label(5,num+2,"",wcfTytle));
			ws.addCell(new Label(7,num+2,"",wcfTytle));
			ws.addCell(new Label(9,num+2,"",wcfTytle));
			ws.addCell(new Label(10,num+2,"",wcfTytle));
			//��д
			MoneyFormat mf = new MoneyFormat();//��дת��������
			String nr;
			if(hj==0){
				nr = "��д�����:"+"��Ԫ��"+"                                                        ��"+hj;
			}else{
				String dxje = mf.format(hj+"");
				nr = "��д�����:"+dxje
							+"                                                        ��"+hj;
			}
			ws.mergeCells(0, num+2+1,10, num+2+1);//�ϲ���Ԫ��
			ws.addCell(new Label(0,num+2+1,nr,wcfTytle));
			ws.setRowView(num+2+1, 800);//��д�и�
			//ǩ��
			wcfTytle = ExcelMB.getWritableCellFormat(11,true, Alignment.LEFT, VerticalAlignment.CENTRE,Border.ALL);// ��Ԫ����ʽ
			ws.mergeCells(0, num+2+2,10, num+2+2);//�ϲ���Ԫ��
			ws.addCell(new Label(0,num+2+2,"Ժ�쵼ǩ�֣�                                                        ѧ�����쵼ǩ�֣�                                                        ���������쵼ǩ�֣�                                                        �Ʊ�                            ",wcfTytle));
			ws.setRowView(num+2+2, 1600);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * ���ʷ��ű�����
	 * @param myForm
	 * @param request
	 * @param gwdm
	 * @param ffsjks
	 * @param ffsjjs
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getgzffList(QgzxGbmygExcelActionForm myForm,HttpServletRequest request,String gwdm,String ffsjks,String ffsjjs) throws Exception {
		return dao.getgzffList(myForm,request,gwdm,ffsjks,ffsjjs);
	}
	
	
	// ----------------��ȡ��ʱ��λ��𷢷���Ϣ begin---------------------
	/**
	 * ��������excel
	 * @param myForm
	 * @param message
	 * @param request
	 * @param response
	 */
	public void printLsgwcjInfo(QgzxGbmygExcelActionForm myForm,MessageResources message,
			HttpServletRequest request,HttpServletResponse response)
			throws Exception,IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		QgzxGbmygExcelDAO dao=new QgzxGbmygExcelDAO();
		
//		 -------------ͳ��ѧ����𷢷���ϢBY��ʱ��λ����ǰѧ�ꡢѧ����Ϣ��--------------
		List<String[]> lsgwInfo=dao.getXscjInfoByLsgw(myForm);
		
		// -------------��ȡ��𷢷�������������ʱ����ǰѧ�ꡢѧ�ڣ�------------------
		HashMap<String,String>zrsJeGsInfo=dao.getZrsJeGs(myForm);
		//��ȡѧ������
		String xqmc=dao.getXqmc(Base.currXq);
		
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response.getOutputStream());
		WritableCellFormat wcfTytle = new WritableCellFormat();
		WritableSheet ws = wwb.createSheet(Base.currXn+"��"+Base.currXq+"ѧ���ڹ���ѧ��ʱ��λͳ�Ʊ�", 0);
		
		
		try {			
			
			//����
			wcfTytle = ExcelMB.getWritableCellFormat(16,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// ��Ԫ����ʽ
			ws.mergeCells(0, 0,6, 0);
			ws.addCell(new Label(0,0,Base.currXn+"��"+xqmc+"ѧ���ڹ���ѧ��ʱ��λͳ�Ʊ�",wcfTytle));
			
			wcfTytle = ExcelMB.getWritableCellFormat(11,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// ��Ԫ����ʽ
			ws.addCell(new Label(0,1,"����",wcfTytle));
			ws.addCell(new Label(1,1,"��λ����",wcfTytle));
			ws.addCell(new Label(2,1,"�༶",wcfTytle));
			ws.addCell(new Label(3,1,"����",wcfTytle));
			ws.addCell(new Label(4,1,"ѧ��",wcfTytle));
			ws.addCell(new Label(5,1,"������ʱ��",wcfTytle));
			ws.addCell(new Label(6,1,"���",wcfTytle));
			
			int[] hbwz={0,1};
			
			// --------------------��Ҫ�ϲ������� begin--------------------------
			this.hbdyg(lsgwInfo,hbwz, 3, ws);
			// --------------------��Ҫ�ϲ������� end--------------------------
			
			for(int i = 0;i<lsgwInfo.size();i++){
				
				String[]lsgwArr=lsgwInfo.get(i);
				
				for(int j=2;j<lsgwArr.length;j++){
					
					ws.addCell(new Label(j-2,2+i,lsgwArr[j],wcfTytle));
					
				}
			}
			
			ws.mergeCells(0, 2+lsgwInfo.size(),4, 2+lsgwInfo.size());
			ws.addCell(new Label(0,2+lsgwInfo.size(),"�����ܼƹ�"+zrsJeGsInfo.get("zrs")+"��",wcfTytle));
			
			ws.addCell(new Label(5,2+lsgwInfo.size(),"ʱ���ܼƣ�"+zrsJeGsInfo.get("zgs"),wcfTytle));
			
			ws.addCell(new Label(6,2+lsgwInfo.size(),"����ܼƣ�"+zrsJeGsInfo.get("zje"),wcfTytle));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
//	 ----------------��ȡ��ʱ��λ��𷢷���Ϣ end---------------------
}
