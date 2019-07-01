package xgxt.xsxx.cslg.xsjl;

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
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;

public class XsxxCslgXsjlService {
	
	public String getXqmc(String xq){
		
		XsxxCslgXsjlDao dao =new XsxxCslgXsjlDao();
		HashMap<String,String>hashMap=dao.getXqmc(xq);
		return hashMap.get("xqmc");
	}
	
	/**
	 * ��������
	 * �����豸��Ϣ
	 */
	public void plSave(HttpServletRequest request,XsxxCslgXsjlForm form) throws Exception{
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();
	
		//�������ݲ��� �ı���
		String realTable ="xsxx_cslg_xxjlb";
		
		//�����ѯ����������Ϣ��XH����
		String xh =form.getXh();
		
		//��ʼʱ��
		String []qssj= form.getQssj();
		//��ֹʱ��
		String []zzsj = form.getZzsj();
		//��ע
		String []bz=form.getBz();
		//��֤��
		String []jzr=form.getJzr();
		//��λ����
		String []dwmc=form.getDwmc();
		//�жϲ����Ƿ�ɹ�
		String []pkValue=new String[qssj.length];
		String []xsxh=new String[qssj.length];
		boolean reslut = false;
		
		//ƴ��pkValue
		for(int i=0;i<qssj.length;i++){
			pkValue[i]=xh+qssj[i]+zzsj[i];
			xsxh[i]=xh;
		}
		
		String pk = "xh || qssj || zzsj  ";
		String[] arrzd = new String[] {"xh","qssj","zzsj","dwmc","jzr","bz"};
	
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		
				
		XsxxCslgXsjlModel model=new XsxxCslgXsjlModel();
		
		model.setXh(xsxh);
		model.setDwmc(dwmc);
		model.setQssj(qssj);
		model.setZzsj(zzsj);
		model.setJzr(jzr);
		model.setBz(bz);
		
		reslut = ghxyNjzrwhService.saveTyxx(saveForm, model);
		request.setAttribute("result", reslut);
			
	}
	
	public List<String[]>getXxjlXx(String pkValue){
		XsxxCslgXsjlDao dao=new XsxxCslgXsjlDao();
		return dao.getXxjlXx(pkValue);
	}
	
	/**
	 * ɾ��ѧ������ѧҵ����
	 * @throws Exception 
	 */
	public void deleteXxjl(XsxxCslgXsjlForm form) throws Exception{
		XsxxCslgXsjlDao dao=new XsxxCslgXsjlDao();
		dao.deleteXxjl(form);
	}
	
	/**
	 * ����ɾ��ѧ������ѧҵ����
	 * @throws Exception 
	 */
	public void delXxjl(XsxxCslgXsjlForm form) throws Exception{
		XsxxCslgXsjlDao dao=new XsxxCslgXsjlDao();
		dao.delXxjl(form);
	}
	
	/**
	 * ;����
	 */
	public void expData(XsxxCslgXsjlForm xsjlForm,
			HttpServletRequest request, WritableWorkbook wwb)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		XsxxCslgXsjlDao dao=new XsxxCslgXsjlDao();
		MakeQuery makeQuery=new MakeQuery();
		String []colLikeList={"xh","xm"};
		String []colList={"zydm","bjdm","xydm","nj"};
		
	
		xsjlForm.setXm(xsjlForm.getQuerylike_xm());
		xsjlForm.setXh(xsjlForm.getQuerylike_xh());
		xsjlForm.setNj(xsjlForm.getQueryequals_nj());
		xsjlForm.setBjdm(xsjlForm.getQueryequals_bjdm());
		xsjlForm.setXydm(xsjlForm.getQueryequals_xydm());
		xsjlForm.setZydm(xsjlForm.getQueryequals_zydm());
		makeQuery.makeQuery(colList, colLikeList, xsjlForm);
		List<HashMap<String,String>>list=dao.getTjList(makeQuery.getInputList(),makeQuery.getQueryString().toString()+getSql(xsjlForm).toString());
		
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
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setWrap(true);
//			 ���ñ��߿�
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
	

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			ws.addCell(new Label(0,0,"ѧ��",wcfTytle));
			ws.addCell(new Label(1,0,"����",wcfTytle));
			ws.addCell(new Label(2,0,"�꼶",wcfTytle));
			ws.addCell(new Label(3,0,"�༶����",wcfTytle));
			ws.addCell(new Label(4,0,"רҵ����",wcfTytle));
			ws.addCell(new Label(5,0,Base.YXPZXY_KEY+"����",wcfTytle));
			ws.addCell(new Label(6,0,"��ʼʱ��",wcfTytle));
			ws.addCell(new Label(7,0,"��ֹʱ��",wcfTytle));
			ws.addCell(new Label(8,0,"��λ����",wcfTytle));
			ws.addCell(new Label(9,0,"��֤��",wcfTytle));
			
			for(int i=0; i <list.size();i++){
				HashMap<String,String>hashMap=list.get(i);
				ws.addCell(new Label(0,i+1,hashMap.get("xh"),wcfTytle));
				ws.addCell(new Label(1,i+1,hashMap.get("xm"),wcfTytle));
				ws.addCell(new Label(2,i+1,hashMap.get("nj"),wcfTytle));
				ws.addCell(new Label(3,i+1,hashMap.get("bjmc"),wcfTytle));
				ws.addCell(new Label(4,i+1,hashMap.get("zymc"),wcfTytle));
				ws.addCell(new Label(5,i+1,hashMap.get("xymc"),wcfTytle));
				ws.addCell(new Label(6,i+1,hashMap.get("qssj"),wcfTytle));
				ws.addCell(new Label(7,i+1,hashMap.get("zzsj"),wcfTytle));
				ws.addCell(new Label(8,i+1,hashMap.get("dwmc"),wcfTytle));
				ws.addCell(new Label(9,i+1,hashMap.get("jzr"),wcfTytle));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ƴ��SQL
	 */
	public StringBuilder getSql(XsxxCslgXsjlForm xsjlForm){
		String userType=xsjlForm.getUserType();
		String isFdy=xsjlForm.getIsFdy();
		String isBzr=xsjlForm.getIsBzr();
		String userName=xsjlForm.getUserName();
		String userDep=xsjlForm.getUserDep();
		  // �û�Ϊ����Ա
		StringBuilder sql=new StringBuilder();
		if ("true".equalsIgnoreCase(isFdy) && !"true".equalsIgnoreCase(isBzr)) {
			sql.append(" and exists (select 1 from view_xsjbxx b  ");
			sql.append(" where a.xh = b.xh and exists (select 1 ");
			sql.append(" from fdybjb c where c.bjdm = b.bjdm ");
			sql.append(" and c.zgh = '" + userName + "')) ");
		}
				// �û�Ϊ������
		if ("true".equalsIgnoreCase(isBzr) && !"true".equalsIgnoreCase(isFdy)) {
			sql.append(" and exists (select 1 from view_xsjbxx b  ");
			sql.append(" where a.xh = b.xh and exists (select 1 ");
			sql.append(" from bzrbbb c where c.bjdm = b.bjdm ");
			sql.append(" and c.zgh = '" + userName + "')) ");
		}
				// �û�Ϊ�����μ渨��Ա
		if ("true".equalsIgnoreCase(isBzr) && "true".equalsIgnoreCase(isFdy)) {
			sql.append(" and (exists (select 1 from view_xsjbxx b  ");
			sql.append(" where a.xh = b.xh and exists (select 1 ");
			sql.append(" from fdybjb c where c.bjdm = b.bjdm ");
			sql.append(" and c.zgh = '" + userName + "')) ");
			sql.append(" or exists (select 1 from view_xsjbxx b  ");
			sql.append(" where a.xh = b.xh and exists (select 1 ");
			sql.append(" from bzrbbb c where c.bjdm = b.bjdm ");
			sql.append(" and c.zgh = '" + userName + "'))) ");
		}
				// �û�ѧԺ�û�
		if ("xy".equalsIgnoreCase(userType)) {
			sql.append(" and exists (select 1 from view_xsjbxx b  ");
			sql.append(" where a.xh = b.xh and b.xydm = '" + userDep + "') ");
		}
		return sql;
		

	}
}
