package xgxt.qgzx.xcxy.service;

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

import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.form.QgzxForm;
import xgxt.qgzx.xcxy.dao.QgzxXcxyDAO;
import xgxt.qgzx.zzsf.dao.QgzxZzsfDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ѧԺ�ڹ���ѧService</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-07-07</p>
 */
public class QgzxXcxyService {
	QgzxXcxyDAO dao = new QgzxXcxyDAO();
	QgzxDao qgzxDao = new QgzxDao();
	/**
	 * �����λ��Ϣ
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean saveXsgwxx(QgzxForm model, HttpServletRequest request) throws Exception{
		QgzxZzsfDAO zzsfDao = new QgzxZzsfDAO();
		boolean result = false;
		String tableName = "xsgwxxb";
		String[] columns = {};
		String[] values = {};
		String primaryKey = "xh||gwdm||'-'||gwsbsj";
		String pkValue = "";
				
		model.setXmdm(DealString.toGBK(model.getXmdm()));
		model.setLxdh(DealString.toGBK(model.getLxdh()));
		model.setXssq(DealString.toGBK(model.getXssq()));
		model.setGzjl(DealString.toGBK(model.getGzjl()));
		//��ȡ��λ��Ϣ
		HashMap<String, String> map = zzsfDao.getGwxx(model.getXmdm());
		boolean isKns = dao.isKns(model.getXh());
		String kns = isKns == true ? "��" : "��";
		
		if(zzsfDao.checkXsgwExists(model.getXh(), model.getXmdm())){//�����¼�Ѿ����ڽ����޸Ĳ���
			columns = new String[]{"lxdh","gzjl","xssq","bz","sfpks"};
			values = new String[]{model.getLxdh(), model.getGzjl(), model.getXssq(), model.getBz(),kns};
			pkValue = model.getXh() + model.getXmdm();
			result = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
		}else{//��¼�����ڽ������Ӳ���
			HashMap<String, String> tmpMap = qgzxDao.getSqsjInfo();
			
			columns = new String[]{"xn","nd","xq","xh", "gwdm" , "gwsbsj", "lxdh", "gzjl", "xssq", "bz", "sfpks"};
			values = new String[]{tmpMap.get("xn"),tmpMap.get("nd"),tmpMap.get("xq"),model.getXh(), map.get("gwdm"), map.get("gwsbsj"),model.getLxdh(),model.getGzjl(),model.getXssq(),model.getBz(),kns};
			result = StandardOperation.insert(tableName, columns, values, request);
		}
		
		return result;
	}
	
	/**
	 * ��ȡѧ����λ��Ϣ
	 * @param pkV
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsgwxx(String pkV){
		return dao.getXsgwxx(pkV);
	}
	
	/**
	 * ��ӡ���ͨ��ѧ��
	 * */
	@SuppressWarnings("unchecked")
	public void printPassStu(WritableWorkbook wwb, String userName, QgzxForm model){
		QgzxXcxyDAO xcxyDao = new QgzxXcxyDAO();		
		List list = xcxyDao.getPassStuInfo(model);
		String day = GetTime.getNowTime();
		String bmmc = xcxyDao.getYhbm(userName);
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(14);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,0,"�ڹ���ѧѧ����Ϣͳ�Ʊ�" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(1,1,bmmc ,wcfTytle));//�û����� 
		 ws.addCell(new Label(5,1,day ,wcfTytle));//ʱ��
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 for(int i=0; i<list.size(); i++){
			 HashMap<String, String> map = new HashMap<String, String>();
			 map = (HashMap<String, String>) list.get(i);
			 ws.addCell(new Label(0,3+i,map.get("xm"),wcfTytle));
			 ws.addCell(new Label(1,3+i,map.get("xb"),wcfTytle));
			 ws.addCell(new Label(2,3+i,map.get("xymc"),wcfTytle));
			 ws.addCell(new Label(3,3+i,map.get("nj")+map.get("zymc"),wcfTytle));
			 ws.addCell(new Label(4,3+i,map.get("gwdm"),wcfTytle));
			 ws.addCell(new Label(5,3+i,map.get("sfpks"),wcfTytle));
			 ws.addCell(new Label(6,3+i,map.get("lxdh"),wcfTytle));
		 }
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);		 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
