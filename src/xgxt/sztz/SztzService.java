package xgxt.sztz;

import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import xgxt.audit.spgc.AuditingManage;
import xgxt.audit.spgc.AuditingModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class SztzService  extends AuditingManage{
	
	private SztzDAO dao = new SztzDAO();
	
	
	/**
	 * ������չͨ�ñ���
	 */
	public boolean saveSztz(SaveForm form, SztzForm model,
			HttpServletRequest request) throws Exception {
		return dao.submitData(form, model, request);
	}
	
	/**
	 * ������չ����
	 */
	public boolean updateXmsb(SztzForm model) throws Exception {
		
		boolean result = dao.updateXmsb(model);
	
			if (result) {
				return dao.saveXmjx(model);
			}
		
		return result;
	}
	
	/**
	 * ��Ŀά��
	 * @param form
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKmwh(SaveForm form, SztzForm model,
			HttpServletRequest request) throws Exception {

		boolean result = saveSztz(form, model, request);

		if (null != model.getHxnldm() && null != model.getHxnlmc()) {
			if (result) {
				return dao.saveHxnl(model);
			}
		}

		return result;
	}
	
	
	/**
	 * ���ݿ�Ŀ���������Ӧ�ĺ�������
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String,String>> getHxnl(String pkValue){
		
		return dao.getHxnl(pkValue);
	}
	

	/**
	 * ���ҿ�Ŀ����
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String,String>> getXmBykmdm(String pkValue){
		
		return dao.getXmBykmdm(pkValue);
	}
	
	/**
	 * ��ѯ��Ŀ
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String,String>> getXmlist(String pkValue){
		
		return dao.getXmlist(pkValue);
	}
	
	/**
	 * ɾ����Ŀ����غ�������
	 * @param kmdm
	 * @return
	 */
	public boolean delKmsz(String[] kmdm){
		
		if (null != kmdm && kmdm.length > 0){
			try {
				return dao.delKmsz(kmdm);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	
	/**
	 * ��Ŀ�걨
	 * @param form
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXmsb(SaveForm form, SztzForm model,
			HttpServletRequest request) throws Exception {

		boolean result = saveSztz(form, model, request);
		
		if (null != model.getJxdm() && null != model.getJxmc()
				&& null != model.getJxfs()) {
			if (result) {
				return dao.saveXmjx(model);
			}
		}

		return result;
	}
	
	
	/**
	 * ��Ŀ�����б�
	 * @param xmid
	 * @return
	 */
	public List<HashMap<String,String>> getXmjxList(String xmid){
		
		return dao.getXmjxList(xmid);
	}
	
	
	/**
	 * ɾ����Ŀ
	 * @param xmid
	 * @return
	 */
	public boolean delXm(String[] xmid){
		
		if (null != xmid && xmid.length > 0){
			try {
				return dao.delXm(xmid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	
	/**
	 * ���ص�ǰѧ�ꡢѧ����Ŀ�б�
	 * @return
	 */
	public List<HashMap<String,String>> getXmList(){
		
		return dao.getXmList();
	}
	
	
	/**
	 * ѧ����˲�ѯ
	 */
	public List<String[]> xfshQuery(SztzForm model,User user) throws Exception{
		
		return dao.xfshQuery(model, user);
	}

	
	/**
	 * ��Ŀ��˲�ѯ
	 */
	public List<String[]> xmshQuery(SztzForm model,User user) throws Exception{
		
		return dao.xmshQuery(model, user);
	}
	

	/**
	 * ������˺�Ĳ���
	 */
	protected boolean auditingAfter(Object o) {
		AuditingModel model = (AuditingModel) o;
		//��ǰ��˸�λ�����Ϊ�ձ�ʾ�ύ�����������
		String xtgwid = model.getXtgwid();
		String shzt = "";
		
		if (StringUtils.isNull(xtgwid)){
			//�����¼�ύ���������󣬰�������е����״̬��Ϊ����аɣ�
			shzt = "δ���";
		} else {
			//���������Ǿ�����˲���
			if ("��ͨ��".equalsIgnoreCase(model.getShzt())){
				//���״̬Ϊ��ͨ������Ҫֹͣ�ģ�����������״̬��Ϊ����ͨ����
				shzt = "��ͨ��";
			} else if ("ͨ��".equalsIgnoreCase(model.getShzt())){
				//ͨ���Ļ����µ�ǰ������λ����һ������������һ����Ҫ�������¼���е����״̬��Ϊͨ�����������һ����Ϊ ������С�
				shzt = isLastAudit(model) ? "ͨ��" : "�����";
				//������Ҫ���춯��Ϣ�ύ��ѧ����Ϣ��ʽ��
			} else if ("�˻�".equalsIgnoreCase(model.getShzt()) && "Applicant".equalsIgnoreCase(model.getNextPost()) ){
				//������˻ز����������˻ص�Ŀ����������
				shzt = "�˻�";
			}
		}
		
		//�޸�������е����״̬
		if (StringUtils.isNotNull(shzt)){
			//String sql = "update xg_sztz_xssqb set shzt=? where id=?";
			String sql = "update "+ model.getSqjlb() +" set shzt=? where id=?";
			try {
				dao.runUpdate(sql, new String[]{shzt,model.getId()});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return super.auditingAfter(o);
	}
	
	
	/**
	 * �����б�
	 * @param flg
	 * @return
	 */
	protected List<HashMap<String,String>> getList(String flg){
		
		if ("shzt".equalsIgnoreCase(flg)){
			return dao.getChkList(32);
		} else if ("shjg".equalsIgnoreCase(flg)){
			return dao.getChkList(33);
		}
		
		return null;
	}
	
	
	/**
	 * ������ˣ�ÿ����¼��˺�Ĳ���
	 */
	protected boolean doAuditingAfter(Object o) {
		
		return auditingAfter(o);
	}
	
	
	/**
	 * ��ѯ������չ������Ϣ
	 * @param id
	 * @return
	 */
	protected HashMap<String,String> getXmsqInfoById(String id){
		
		String sql = "select * from xg_view_sztz_xssqb where id=?";
		
		return dao.getMapNotOut(sql, new String[]{id});
	}
	
	
	/**
	 * ����ѧ����ȡ�Ľ���ͻ�õ�ѧ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	protected boolean updateSdjx(SztzForm model) throws Exception{
		
		String sql = "update xg_sztz_xssqb set shjx=? , sdxf=? where id=?";
		
		return dao.runUpdate(sql, new String[]{model.getShjx(),model.getSdxf(),model.getId()});
	}
	
	
	/**
	 *������չ�����ѯ 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	protected List<String[]> sztzJgcx(SztzForm model,User user) throws Exception{
		
		return dao.sztzJgcx(model, user);
	}
	
	
	/**
	 * ������չ��¼ɾ��
	 * @param model
	 * @return
	 */
	protected boolean delSztz(SztzForm model){
		
		String[] pkValues = model.getPrimarykey_cbv();
		
		if (null != pkValues && pkValues.length > 0){
			return dao.delSztz(pkValues);
		}
		
		return false;
	}
	
	
	/**
	 * ִ�и��ֲ�ѯ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	protected List<String[]> sztzXfcx(SztzForm model,User user) throws Exception{
		
		return dao.sztzXfcx(model, user);
	}
	
	
	/**
	 * ִ�и��ֱ���
	 * @param pkValues
	 * @param sdxf
	 * @return
	 */
	public boolean saveZxff(String[] pkValues,String[] sdxf){
		if (null != pkValues && null != sdxf && pkValues.length > 0 && pkValues.length == sdxf.length){
			List<String[]> params = new ArrayList<String[]>();
			
			for (int i = 0 ; i < pkValues.length; i++){
				String[] tempArr = new String[2];
				tempArr[0] = sdxf[i];
				tempArr[1] = pkValues[i];
				params.add(tempArr);
			}
			
			String sql = "update xg_sztz_xssqb set sdxf=? where id=?";
			
			try {
				int[] result = dao.runBatch(sql, params);
				return dao.checkBatchResult(result);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} 
		
		return false;
	}
	
	
	/**
	 * ��ӡ������չ�ɼ���
	 * @param model
	 * @param os
	 * @throws SQLException
	 */
	protected void printSztzCjd(SztzForm model,OutputStream os) throws SQLException {
		
		String title= "��2-ѧ����ҵ�ɼ���";
		
		HashMap<String,String> stuInfo = CommonQueryDAO.getStuInfo(model.getXh());
		//����ѧ�Ų�ѯ��ѧ�������ͨ����������չ����
		List<HashMap<String,String>> data = dao.getSztzCjdData(model.getXh());
		//��ȡ����Ӧ�������Щ����
		String[] zq = dao.getSztzCjdZq(model.getXh());
		//������չ����Щ������Ŀ����������
		List<HashMap<String,String>> kmlx = dao.getSztzKmlx();
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("������չ�ɼ���", 0);
		
		try {
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(10,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			ws.mergeCells(0, 0, zq.length*2+2, 0);// ����
			ws.addCell(new Label(0,0 , title, wcfTytle));
			ws.addCell(new Label(0,1 , "ѧ����Ϣ", wcfTytle));
			ws.mergeCells(1, 1, zq.length*2+2, 1);
			
			StringBuilder stu = new StringBuilder();
			stu.append("ѧ��:")
			   .append(model.getXh())
			   .append(",����:")
			   .append(stuInfo.get("xm"))
			   .append(",�༶����:")
			   .append(stuInfo.get("bjmc"));
			ws.addCell(new Label(1,1 , stu.toString(), wcfTytle));
			
			ws.addCell(new Label(0,2 , "������Ŀ", wcfTytle));
			ws.addCell(new Label(1,2 , "��������", wcfTytle));
			
			if (null != zq && zq.length > 0){
				
				//��������ѧ����������Щ���ڵ�������չ
				for (int i = 0 ; i < zq.length ; i++){
					ws.addCell(new Label(2*(i+1),2 , zq[i]+"  ѧ��", wcfTytle));
					ws.addCell(new Label(2*(i+1)+1,2 , "���޳ɼ�", wcfTytle));
				}
				//����ѧ��
				ws.addCell(new Label(zq.length*2+2,2 , "����ѧ��", wcfTytle));
				
				int[] hjfs = new int[zq.length];
				int[] zphj = new int[kmlx.size()];//�����ϼ�
				//distinct ��Ŀ���ơ���������
				for (int i = 0 ; i < kmlx.size() ; i++){
					ws.addCell(new Label(0,3+i ,kmlx.get(i).get("kmmc"), wcfTytle));
					ws.addCell(new Label(1,3+i ,kmlx.get(i).get("hxnlmc"), wcfTytle));
					int zpxf = 0;//����ѧ��
					int n = 0;
					for (int j = 0 ; j < zq.length ; j++){
						String sdxf = "";
						String cxxf = "";
						String cxcj = "";
						
						//��ѧ��������չ����
						for (int m = 0 ; m < data.size() ; m++){
							String kmmc = data.get(m).get("kmmc");
							String hxnlmc = data.get(m).get("hxnlmc");
							String xn = data.get(m).get("xn");
							String xq = data.get(m).get("xqmc");
							
							//ѭ���ҳ���Ӧ���ڡ���Ŀ���ơ���������������ѧ�ֺ�����ѧ��
							if (kmlx.get(i).get("kmmc").equalsIgnoreCase(kmmc) 
									&& kmlx.get(i).get("hxnlmc").equalsIgnoreCase(hxnlmc)
									&& zq[j].trim().equalsIgnoreCase(xn+"("+xq+")")){
								sdxf = data.get(m).get("sdxf");
								cxxf = data.get(m).get("cxxf");
								
								int minfs = Integer.valueOf(data.get(m).get("minfs"));
								
								if (!cxxf.equals("0")){
									cxcj = Integer.valueOf(cxxf) < minfs ? "���ϸ�" : "�ϸ�";
								}
								hjfs[j]+=Integer.valueOf(sdxf);
								zpxf += Integer.valueOf(sdxf); 
								n+=1;
								
								//�������ѧ�֡�����ѧ��
								ws.addCell(new Label(2*(j+1),3+i , sdxf, wcfTytle));
								ws.addCell(new Label(2*(j+1)+1,3+i , cxcj, wcfTytle));
							}
						}
						if (n > 0){
							ws.addCell(new Label(2*(j+1)+2,3+i , String.valueOf(zpxf/n), wcfTytle));
							//hjfs[zq.length]+=(zpxf/n);
							zphj[i] = zpxf/n;
						}
					}
				}
				
				ws.addCell(new Label(0,kmlx.size()+3 , "�ϼ�", wcfTytle));
				ws.addCell(new Label(1,kmlx.size()+3 , "�ܷ�", wcfTytle));
				
				int n = 2;
				for (int m = 0 ; m < hjfs.length ; m++){
					ws.addCell(new Label(n,kmlx.size()+3 , String.valueOf(hjfs[m]), wcfTytle));
//					if (m != hjfs.length-1){
						ws.addCell(new Label(n+1,kmlx.size()+3 , "", wcfTytle));
//					}
					n+=2;
				}
				
				int zphjfs = 0;
				
				for (int x = 0 ; x < zphj.length ; x++){
					zphjfs+=zphj[x];
				}
				
				ws.addCell(new Label(n,kmlx.size()+3 , String.valueOf(zphjfs), wcfTytle));
			}
			
			
			//�ϲ����ʷ��൥Ԫ��
			ExcelMB.mergeCells(ws, kmlx.size(), 0, 3);
		}catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * ��ѯ��Ӧ����˼�¼
	 * @param id
	 * @return
	 */
	protected List<HashMap<String,String>> getShxxList(String id,String shjlb){
		
		return dao.getShxxList(id,shjlb);
	}

	
	/**
	 * ����Ŀ�����Ƿ����
	 * @param kmdm
	 * @return
	 */
	protected boolean checkKmdmExists(String kmdm) {
		
		
		return "0".equals(dao.checkKmdmExists(kmdm));
	}

	
	/**
	 * ��ѧ����������Ŀ�����������Ϣ
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getXmsqList(SztzForm model){
		
		return dao.getXmsqList(model);
	}
	
	public List<HashMap<String,String>> getXsxmsq(SztzForm model){
		
		return dao.getXsxmsq(model);
	}
	
	
	/**
	 * ��ȡ��������
	 * @return
	 */
	public HashMap<String,String> getCssz(String xxdm){
			
		HashMap<String,String> map = dao.getCssz(xxdm);
		
		//�ж��Ƿ����걨ʱ����
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			if(map.get("sbkssj")!=null && map.get("sbjssj")!=null && map.get("dqsj")!=null){
				Date sbkssj = sdf.parse(map.get("sbkssj"));
				Date sbjssj = sdf.parse(map.get("sbjssj"));
				Date dqsj = sdf.parse(map.get("dqsj"));
				map.put("isSbsj", String.valueOf(dqsj.after(sbkssj) && dqsj.before(sbjssj)));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * ÿѧ�ڳɼ���
	 * @param model
	 * @param os
	 * @throws SQLException
	 */
	protected void printMxqCjd(SztzForm model,OutputStream os) throws SQLException {
		//��ѯ������չÿѧ�ڳɼ���
		List<HashMap<String,String>> data = dao.getMxqCjd(model.getXh(),model.getXn(),model.getXq());
		//ѧ��������Ϣ
		HashMap<String,String> stuInfo = CommonQueryDAO.getStuInfo(model.getXh());
		
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		String title=stuInfo.get("xm")+"ѧ��"+model.getXn()+"ѧ��"+model.getXq()+"ѧ�����ʽ����ɼ���";
		WritableSheet ws = wwb.createSheet(title, 0);
		
		try {
			//��Ԫ����ʽ
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);
			
//			ws.mergeCells(0, 0, 6, 0);
			ws.mergeCells(0, 0, 5, 0);
			ws.addCell(new Label(0,0 , title, wcfTytle));
			ws.addCell(new Label(0,1 , "ѧ����Ϣ", wcfTytle));
//			ws.mergeCells(1, 1, 6, 1);
			ws.mergeCells(1, 1, 5, 1);
			wcfTytle = ExcelMB.getWritableCellFormat(10,false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);
			StringBuilder stu = new StringBuilder();
			stu.append("ѧ��:")
			   .append(model.getXh())
			   .append(",����:")
			   .append(stuInfo.get("xm"))
			   .append(",�༶����:")
			   .append(stuInfo.get("bjmc"));
			ws.addCell(new Label(1,1 , stu.toString(), wcfTytle));
			wcfTytle = ExcelMB.getWritableCellFormat(12,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);
			ws.setColumnView(0, 15);
			ws.setColumnView(1, 15);
			ws.setColumnView(2, 20);
			ws.setColumnView(3, 20);
			ws.setColumnView(4, 10);
			ws.setColumnView(5, 15);
			ws.addCell(new Label(0,2 , "������Ŀ", wcfTytle));
			ws.addCell(new Label(1,2 , "��������", wcfTytle));
			ws.addCell(new Label(2,2 , "��Ŀ����", wcfTytle));
			ws.addCell(new Label(3,2 , "��Ӧ�걨ѧ��", wcfTytle));
			ws.addCell(new Label(4,2 , "�ɼ�", wcfTytle));
			ws.addCell(new Label(5,2 , "���޳ɼ�", wcfTytle));
//			ws.addCell(new Label(2,2 , "��Ӧ�걨ѧ��", wcfTytle));
//			ws.addCell(new Label(3,2 , "ѧ��", wcfTytle));
//			ws.addCell(new Label(4,2 , "�ɼ�", wcfTytle));
//			ws.addCell(new Label(5,2 , "����ѧ��", wcfTytle));
//			ws.addCell(new Label(6,2 , "���޳ɼ�", wcfTytle));
			wcfTytle = ExcelMB.getWritableCellFormat(10,false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);
			int zxf = 0;
			String pjcj = "";
			Set<String> kmmc = new HashSet<String>();
			
			for (int i = 0 ; i < data.size() ; i++){
				kmmc.add(data.get(i).get("kmmc"));
				ws.addCell(new Label(0,3+i , data.get(i).get("kmmc"), wcfTytle));
				ws.addCell(new Label(1,3+i , data.get(i).get("hxnlmc"), wcfTytle));
				ws.addCell(new Label(2,3+i , data.get(i).get("xmmc"), wcfTytle));
				ws.addCell(new Label(3,3+i , data.get(i).get("xn")+"("+data.get(i).get("xqmc")+")", wcfTytle));
//				ws.addCell(new Label(2,3+i , data.get(i).get("xn")+"("+data.get(i).get("xqmc")+")", wcfTytle));
				
				String sdxf = data.get(i).get("sdxf");
				String sdcj = "";
				if(sdxf==null){
					sdxf ="0";
				}
				
				//�жϳɼ�
				try{
					int fs = Integer.valueOf(sdxf);
					int minfs = Integer.valueOf(data.get(i).get("minfs"));
					int maxfs = Integer.valueOf(data.get(i).get("maxfs"));
					
					if (fs < minfs){
						sdcj = "���ϸ�";
						pjcj = "���ϸ�";
					} else if (fs > maxfs && "��".equals(data.get(i).get("sfcx"))){
						sdcj = "����";
					} else {
						sdcj = "�ϸ�";
					}
				} catch(Exception e){
					e.printStackTrace();
				}
				
				if ("��".equals(data.get(i).get("sfcx"))){
					ws.addCell(new Label(4,3+i , "", wcfTytle));
					ws.addCell(new Label(5,3+i , sdxf, wcfTytle));
//					ws.addCell(new Label(3,3+i , "", wcfTytle));
//					ws.addCell(new Label(4,3+i , "", wcfTytle));
//					ws.addCell(new Label(5,3+i , sdxf, wcfTytle));
//					ws.addCell(new Label(6,3+i , sdcj, wcfTytle));
				} else {
					zxf+=Integer.valueOf(sdxf);
					ws.addCell(new Label(4,3+i , sdxf, wcfTytle));
					ws.addCell(new Label(5,3+i , "", wcfTytle));
//					ws.addCell(new Label(3,3+i , sdxf, wcfTytle));
//					ws.addCell(new Label(4,3+i , sdcj, wcfTytle));
//					ws.addCell(new Label(5,3+i , "", wcfTytle));
//					ws.addCell(new Label(6,3+i , "", wcfTytle));
				}
			}
			wcfTytle = ExcelMB.getWritableCellFormat(12,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);
//			ws.addCell(new Label(0,3+data.size() , "�ϼ�", wcfTytle));
			ws.addCell(new Label(0,3+data.size() , "", wcfTytle));
			ws.addCell(new Label(1,3+data.size() , "�ܷ�", wcfTytle));
			ws.addCell(new Label(2,3+data.size() , "", wcfTytle));
			
			
//			ws.addCell(new Label(3,3+data.size() , String.valueOf(zxf), wcfTytle));
			ws.addCell(new Label(3,3+data.size() , "", wcfTytle));
			ws.addCell(new Label(4,3+data.size() , String.valueOf(zxf), wcfTytle));
			int pjxf = zxf/kmmc.size();
			pjxf = pjxf > 100 ? 100 : pjxf;
			if ("".equals(pjcj)){
				if (pjxf < 60){
					pjcj = "���ϸ�";
				} else if (pjxf > 85){
					pjcj = "����";
				} else {
					pjcj = "�ϸ�";
				}
			}
//			ws.addCell(new Label(4,3+data.size() , pjcj, wcfTytle));
			ws.addCell(new Label(5,3+data.size() , "", wcfTytle));
//			ws.addCell(new Label(6,3+data.size() , "", wcfTytle));
			ExcelMB.mergeCells(ws, data.size()+3, 0, 3);
			ExcelMB.mergeCells(ws, data.size()+3, 1, 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	public List<HashMap<String,String>> getMxqCjd(String xh){
		
		return dao.getMxqCjd(xh);
	}

	/**
	 * ��Ŀ��ӡ
	 * @param outputStream
	 * @param model
	 */
	public void xmDy(ServletOutputStream outputStream, SztzForm model) throws Exception{
		// ��������Ĺ̶�����
		ArrayList<String[]> gdlist = xmDyTj(model);
		// �����������Ŀ��ӡ��Ϣ
		HashMap<String,String> map = dao.getxmDyxx(model.getId());
		String title = "���ְҵ��ѧ"+map.get("xn")+"ѧ���"+map.get("xqmc")+"ѧ�����ʽ�����Ŀ���˳ɼ����浥";
		WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ����ͷ
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 10, 1000);// ����
		ws.addCell(new Label(0,1,"��Ŀ����",wcf2));
		ws.mergeCells(1,1,4,1);
		ws.addCell(new Label(1,1,map.get("xmmc"),wcf2));
		ws.addCell(new Label(5,1,"������Ŀ",wcf2));
		ws.mergeCells(6,1,9,1);
		ws.addCell(new Label(6,1,map.get("kmmc"),wcf2));
		
		ws.addCell(new Label(0,2,"��Ŀ����",wcf2));
		ws.addCell(new Label(1,2,map.get("xymc"),wcf2));
		ws.addCell(new Label(2,2,"�����ɼ�",wcf2));
		ws.addCell(new Label(3,2,map.get("jcf"),wcf2));
		ws.addCell(new Label(4,2,"��������",wcf2));
		ws.addCell(new Label(5,2,map.get("hxnlmc"),wcf2));
		ws.addCell(new Label(6,2,"ѧʱ",wcf2));
		ws.addCell(new Label(7,2,map.get("xs"),wcf2));
		ws.addCell(new Label(8,2,"��Ŀ�����ʦ",wcf2));
		ws.addCell(new Label(9,2,map.get("fzr"),wcf2));
		
		ws.mergeCells(0,3,1,3);
		ws.addCell(new Label(0,3,"���",wcf2));
		ws.mergeCells(2,3,3,3);
		ws.addCell(new Label(2,3,"ѧ��",wcf2));
		ws.mergeCells(4,3,5,3);
		ws.addCell(new Label(4,3,"����",wcf2));
		ws.mergeCells(6,3,7,3);
		ws.addCell(new Label(6,3,"�Ƿ�����",wcf2));
		ws.mergeCells(8,3,9,3);
		ws.addCell(new Label(8,3,"����ѧ��(����)",wcf2));
		// �������
		if (gdlist != null && gdlist.size() > 0) {
			for (int i = 0; i < gdlist.size(); i++) {
				String[] info = gdlist.get(i);
				if (info != null && info.length > 0) {
					for (int j = 0; j < info.length; j++) {
						ws.mergeCells(j*2, i + 4,j*2+1, i + 4);
						ws.addCell(new Label(j*2, i + 4, info[j], wcf2));
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * ��Ŀ��ӡͳ��
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	private ArrayList<String[]> xmDyTj(SztzForm model) throws Exception {
		return dao.xmDytj(model);
	}

	/**
	 * ��ȡ��Ŀ����
	 * @param xn
	 * @param xq
	 * @return
	 */
	public List<HashMap<String, String>> getXmmc(String xn, String xq) {
		String[] inputValue = new String[] { xn, xq };
		String[] outputValue = new String[] { "id","xmmc" };
		return dao.getXmmc(inputValue, outputValue);
	}

	/**
	 * ��ȡ����ѧ��
	 * @param jxdm
	 * @param xmid 
	 * @return
	 */
	public List<HashMap<String, String>> getSdxf(String jxdm, String xmid) {
		String[] inputValue = new String[] { jxdm,xmid };
		String[] outputValue = new String[] { "jxfs" };
		return dao.getSdxf(inputValue, outputValue);
	}
	
}
