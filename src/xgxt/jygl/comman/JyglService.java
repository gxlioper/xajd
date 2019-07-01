package xgxt.jygl.comman;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.bdsz.BdszDAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicService;
import com.zfsoft.database.model.TableModel;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��ҵ����Service</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: zfsoft</p>
 * <p>Author: �����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2010-06-13</p>
 */
public class JyglService {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	JyglDAO dao = new JyglDAO();
	
	
	/**
	 * ��ҵЭ���ѧ����Ϣ
	 * @param xh
	 * @return
	 */
	protected HashMap<String,String> getJyxyStuInfo(String xh) {
		return dao.getJyxyStuInfo(xh);
	}
	
	
	/**
	 * ѧ��������Ϣ
	 * @param xh
	 * @return
	 */
	protected HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}
	
	
	/**
	 * ��ѯĳѧ�����ڰ�ĸ���Ա
	 * @param xh
	 * @return
	 * @throws SQLException 
	 */
	protected String getFdy(String xh) throws SQLException {
		
		String[] temp = dao.getFdy(xh);
		StringBuffer sb = new StringBuffer();
		
		if (null != temp && temp.length>0) {
			
			for ( int i = 0 ; i < temp.length ; i ++ ) {
				sb.append(temp[i]);
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
		}
		
		return sb.toString();
	}
	
	
	/**
	 * �����б�
	 * @param request
	 * @param flg
	 */
	protected void setList(HttpServletRequest request,String flg) {
		
		DAO da = DAO.getInstance();
		
		
		if ("tjb".equalsIgnoreCase(flg)) {
			//����ԭ��
			List<HashMap<String, String>> yyList = dao.getSelectList("bbyy");
			request.setAttribute("yyList", yyList);
			
			//�������
			List<HashMap<String, String>> clList = dao.getSelectList("sxcl");
			request.setAttribute("clList", clList);
			
		}  else if ("jysb".equals(flg)) {
			//����
			List<HashMap<String, String>> mzList = da.getWhList("mzdmb", "mzdm", "mzmc", "", "", "");
			request.setAttribute("mzList", mzList);
			
			//������ò
			List<HashMap<String, String>> zzmmList = da.getWhList("dmk_zzmm", "zzmmdm", "zzmm", "", "", "");
			request.setAttribute("zzmmList", zzmmList);
			
			//ѧУ
			List<HashMap<String, String>> xxList = da.getWhList("dmk_xx", "xxdm", "xxmc", "", "", "");
			request.setAttribute("xxList", xxList);
			
			//רҵ����
			List<HashMap<String, String>> gbzyList = da.getWhList("dmk_zydmdzb", "jygl_zydm", "jygl_zymc", "", "", "");
			request.setAttribute("gbzyList",gbzyList);
			
			//ѧ��
			List<HashMap<String, String>> xlList = da.getWhList("dmk_xl", "xldm", "xl", "", "", "");
			request.setAttribute("xlList",xlList);
			
			//������ʽ
			List<HashMap<String, String>> pyfsList = da.getWhList("dmk_bzpyfs", "pyfsdm", "pyfs", "", "", "");
			request.setAttribute("pyfsList",pyfsList);
			
			//��Դ������ʡ��
			List<HashMap<String, String>> sydqList = da.getWhList("dmk_sydq", "sydqdm", "sydq", "", "", "");
			request.setAttribute("sydqList",sydqList);
			
			//�������
			List<HashMap<String, String>> zsList = da.getWhList("dmk_zslb", "dm","mc", "", "", "");
			request.setAttribute("zsList", zsList);
			
			
		} else if ("jyxy".equals(flg)) {
			//��λ����
			List<HashMap<String, String>> dwxzList = da.getWhList("dmk_dwxz", "dwxzdm", "dwxz", "", "", "");
			request.setAttribute("dwxzList",dwxzList);
			
			//��ҵ
			List<HashMap<String, String>> hyList = da.getWhList("dmk_hyfl", "hyfldm", "hyfl", "", "", "");
			request.setAttribute("hyList",hyList);
			
			//�»������
			List<HashMap<String, String>> xjcList = da.getWhList("dmk_xjcqk", "dm", "mc", "", "", "");
			request.setAttribute("xjcList", xjcList);
			
			//��Դ����
			List<HashMap<String, String>> lsList = da.getWhList("dmk_sydq", "sydqdm", "sydq", "", "", "");
			request.setAttribute("lsList", lsList);
			
			//��ҵ��־
			List<HashMap<String, String>> jybzList = da.getWhList("dmk_jybz", "dm", "mc", "", "", "");
			request.setAttribute("jybzList", jybzList);
			
			//���˵�λ
			List<HashMap<String, String>> yrdwList = da.getWhList("dmk_yrdw", "dm", "mc", "", "", "");
			request.setAttribute("yrdwList", yrdwList);
			
			//��������
			//List<HashMap<String, String>> lsbmList = da.getWhList("dmk_zgbm", "zgbmdm", "zgbm", "", "", "");
			List<HashMap<String, String>> lsbmList = dao.getLsbmOption();
			request.setAttribute("lsbmList", lsbmList);
			
			//�Ƽ�����
			List<HashMap<String, String>> tjlxList = da.getWhList("dmk_tjlx", "dm", "mc", "", "", "");
			request.setAttribute("tjlxList", tjlxList);
			
			//��λ���ڵ�
			List<HashMap<String, String>> dwdzList = da.getWhList("dmk_dwdq", "dwdqdm", "dwdq", "", "", "");
			request.setAttribute("dwdzList", dwdzList);
			
			request.setAttribute("nfList", getNfList());
			
			//���ܵ�λ
			//List<HashMap<String, String>> zgdwList = da.getWhList("dmk_zgdw", "dm", "mc", "", "", "");
			List<HashMap<String, String>> zgdwList = dao.getZgdwOption();
			request.setAttribute("zgdwList", zgdwList);
		} else if ("zgdd".equals(flg)) {
			//ѧ��
			List<HashMap<String, String>> xlList = da.getWhList("dmk_zgdd_xlb", "xldm", "xl", "", "", "");
			request.setAttribute("xlList",xlList);
			
			//������ʽ
			List<HashMap<String, String>> pyfsList = da.getWhList("dmk_zgdd_pyfs", "pyfsdm", "pyfs", "", "", "");
			request.setAttribute("pyfsList",pyfsList);
			
			//��������
			List<HashMap<String, String>> yzList = da.getWhList("dmk_zgdd_zxwy", "yzdm", "yz", "", "", "");
			request.setAttribute("yzList",yzList);
			
			//��Դ������ʡ��
			List<HashMap<String, String>> sydqList = da.getWhList("dmk_sydq", "sydqdm", "sydq", "", "", "");
			request.setAttribute("sydqList",sydqList);
			
			//����
			List<HashMap<String, String>> mzList = da.getWhList("mzdmb", "mzdm", "mzmc", "", "", "");
			request.setAttribute("mzList", mzList);
			
			//������ò
			List<HashMap<String, String>> zzmmList = da.getWhList("dmk_zzmm", "zzmmdm", "zzmm", "", "", "");
			request.setAttribute("zzmmList", zzmmList);
			
			//ѧУ
			List<HashMap<String, String>> xxList = da.getWhList("dmk_xx", "xxdm", "xxmc", "", "", "");
			request.setAttribute("xxList", xxList);
		} else if ("zgddJyxy".equals(flg)) {
			//��ҵȥ��
			List<HashMap<String, String>> byqxList = da.getWhList("dmk_zgdd_byqx", "byqxdm", "byqx", "", "", "");
			request.setAttribute("byqxList", byqxList);
			
			//��ҵ���
			List<HashMap<String, String>> jyqkList = da.getWhList("dmk_zgdd_jybz", "dm", "mc", "", "", "");
			request.setAttribute("jyqkList", jyqkList);
			
			//���˵�λ����
			List<HashMap<String, String>> dwxzList = da.getWhList("dmk_dwxz", "dwxzdm", "dwxz", "", "", "");
			request.setAttribute("dwxzList", dwxzList);
			
			//��λ��������
			List<HashMap<String, String>> lsbmList = da.getWhList("dmk_zgdd_zgbm", "zgbmdm", "zgbm", "", "", "");
			request.setAttribute("lsbmList", lsbmList);
			
			//��λ���ڵ�
			List<HashMap<String, String>> dwszdList = da.getWhList("dmk_dwdq", "dwdqdm", "dwdq", "", "", "");
			request.setAttribute("dwszdList", dwszdList);
			
			//���˵�λ
			List<HashMap<String, String>> yrdwList = da.getWhList("dmk_zgdd_yrdw", "dm", "mc", "", "", "");
			request.setAttribute("yrdwList", yrdwList);
			
			//��ҵ
			List<HashMap<String, String>> hyList = da.getWhList("dmk_hyfl", "hyfldm", "hyfl", "", "", "");
			request.setAttribute("hyList",hyList);
		}  else if ("xysgl".equals(flg)) {
			
			//Э���鲹�����
			List<HashMap<String, String>> bblbList = da.getWhList("dmk_xysbblb", "dm", "mc", "", "", "");
			request.setAttribute("bblbList",bblbList);
		} else if ("gp".equals(flg)) {
			
			//���˵�λ
			List<HashMap<String, String>> yrdwList = da.getWhList("dmk_yrdw", "dm", "mc", "", "", "");
			request.setAttribute("yrdwList", yrdwList);
			
			//��λ���ڵ�
			List<HashMap<String, String>> dwdzList = da.getWhList("dmk_dwdq", "dwdqdm", "dwdq", "", "", "");
			request.setAttribute("dwdzList", dwdzList);
		} else if ("fc".equals(flg)) {
			
			//�������
			List<HashMap<String, String>> fclxList = da.getWhList("dmk_fclx", "dm", "mc", "", "", "");
			request.setAttribute("fclxList", fclxList);
		} else if("yxbys".equals(flg)) {
			List<HashMap<String, String>> sqlbList = dao.getSelectList("yxbys");
			request.setAttribute("sqlbList", sqlbList);
		} else if("xb".equals(flg)){
			List<HashMap<String, String>> xbList = dao.getSelectList("xb");
			request.setAttribute("xbList", xbList);
		} else if ("jytj".equals(flg)) {
			//��ҵͳ��
			
			List<HashMap<String, String>> tjxmList = da.getWhList("jygl_jyxgtj", "tjdm", "tjmc", "", "xxdm", Base.xxdm);
			request.setAttribute("tjxmList", tjxmList);
		} else if ("jygzzph".equals(flg)) {
			//��ҵ����
			List<HashMap<String, String>> qyflList = da.getWhList("dmk_qyflb", "dm", "mc", "", "", "");
			request.setAttribute("qyflList", qyflList);
			//��Ƹ������
			List<HashMap<String, String>> zphlxList = da.getWhList("dmk_zphlx", "dm", "mc", "", "", "");
			request.setAttribute("zphlxList", zphlxList);
		} else if ("jygzhy".equals(flg)) {
			//��������
			List<HashMap<String, String>> hylxList = da.getWhList("dmk_hylxb", "dm", "mc", "", "", "");
			request.setAttribute("hylxList", hylxList);
		} else if ("jyssz".equals(flg)) {
			//��ɫ����
			List<HashMap<String, String>> jsflList = da.getWhList("dmk_jsfldm", "dm", "mc", "", "", "");
			request.setAttribute("jsflList", jsflList);
			//�Ƽ����
			List<HashMap<String, String>> tjjgList = da.getWhList("dmk_tjjgdmb", "dm", "mc", "", "", "");
			request.setAttribute("tjjgList", tjjgList);
		} else if ("jyxys".equals(flg)) {
			//Э������ȡ���
			List<HashMap<String, String>> lqqkList = dao.getSelectList("jyxys");
			request.setAttribute("lqqkList", lqqkList);
		} else if ("xysbl".equals(flg)) {
			//�������
			List<HashMap<String, String>> bblbList = da.getWhList("dmk_bblb", "dm", "mc", "", "", "");
			request.setAttribute("bblbList", bblbList);
		} 
		
		//�Ƿ�
		List<HashMap<String, String>> isNot = dao.getSelectList("isNot");
		request.setAttribute("isNot", isNot);
		
		//	���״̬
		List<HashMap<String, String>> shztList = dao.getSelectList("shzt");
		request.setAttribute("shztList", shztList);
		
		request.setAttribute("now", getNow());
		
		request.setAttribute("nfList", getNfList());
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
	}
	
	
	/**
	 * ��ǰʱ��
	 * @return
	 */
	protected String getNow() {
		
		return dao.getNow();
	}
	
	
	/**
	 * ��ҵ�������ϱ�
	 * @param xh
	 * @return
	 * @throws SQLException
	 */
	protected boolean byssb(String[] xh,String sbr,String sbnd) throws SQLException {
		
		return dao.byssb(xh,sbr,sbnd);
	}
	
	
	/**
	 * ��Դ�طֲ����ͳ��
	 * @param wwb
	 * @param nj
	 * @throws SQLException
	 */
	protected void printPayReport(OutputStream os,String nj) throws SQLException{		
		
		List<HashMap<String,String>> sydshi = new StuInfoDAO().getShiList("330000").get("shiList");
		HashMap<String, String[]> rs = dao.getBysrs(nj);
		List<String[]> xyzy = dao.getXyZy(nj);
		List<String[] > bysrs = dao.getBysByZy(nj); 
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("��Դ�طֲ������", 0);
		try{
			ExcelMB excel = new ExcelMB();
			excel.printTitle(ws, Base.xxmc+nj+"���ҵ����Դ�طֲ������", 4+sydshi.size(), 800);// ����
			
			 WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
						false, Alignment.CENTRE, VerticalAlignment.CENTRE,
						Border.ALL);// ��Ԫ����ʽ
			 
			 //��int�� ����int���𡢿�int �С���int ����
			 ws.mergeCells(0, 0,3+sydshi.size(), 0);
			 
			 ws.addCell(new Label(0,2,Base.YXPZXY_KEY+"����",wcfTytle));
			 ws.addCell(new Label(1,2,"רҵ����",wcfTytle));
			 ws.addCell(new Label(2,2,"��ҵ����",wcfTytle));
			 ws.addCell(new Label(3,2,"�㽭��Դ",wcfTytle));
			 
			 for ( int i = 1 ; i < sydshi.size() ; i ++ ) {
				 ws.addCell(new Label(3+i,2,sydshi.get(i).get("shimc").substring(0, 2),wcfTytle));
			 }
			 ws.addCell((new Label(3+sydshi.size(),2,"��ʡ��Դ",wcfTytle)));
			 
			 ws.mergeCells(0,3,1,3);//int ����int ���𣬿�int �У�int �н���
			 ws.addCell(new Label(0,3,"ȫУ�ϼ�",wcfTytle));
			 ws.addCell(new Label(2,3,rs.get("zrs")[0],wcfTytle));
			 ws.addCell(new Label(3,3,rs.get("zjsrs")[0],wcfTytle));
			 
			 String[] shibys = rs.get("bysrs");
			 for ( int i = 0 ; i < shibys.length ; i ++ ) {
				 ws.addCell(new Label(4+i,3,shibys[i],wcfTytle));
			 }
			 ws.addCell(new Label(4+shibys.length,3,rs.get("wsrs")[0],wcfTytle));
			 
			 for( int i = 0 ; i < xyzy.size() ; i ++) {
				 ws.addCell(new Label(0,4+i,xyzy.get(i)[0],wcfTytle));
				 ws.addCell(new Label(1,4+i,xyzy.get(i)[1],wcfTytle));
			 }
			 ExcelMB.mergeCellsData(xyzy, ws, 0, 4, wcfTytle);
			 
			 for ( int i = 0; i<bysrs.size() && i < xyzy.size(); i ++) {
				 ws.addCell(new Label(2,4+i,bysrs.get(i)[0],wcfTytle));
				 ws.addCell(new Label(3,4+i,bysrs.get(i)[1],wcfTytle));
				 int wsrs = Integer.valueOf(bysrs.get(i)[0])-Integer.valueOf(bysrs.get(i)[1]);
				 ws.addCell(new Label(3+sydshi.size(),4+i,wsrs+"",wcfTytle));
			 }
	
			 for ( int i = 0 ; i < xyzy.size() ; i ++) {
				 String zydm = xyzy.get(i)[2];
				 for ( int j = 1 ; j < sydshi.size() ; j ++ ) {
					 ws.addCell(new Label(3+j,4+i,dao.getBys(zydm, sydshi.get(j).get("shidm"), nj),wcfTytle));
				 }
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //��ͻ������
		 ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * ���༶��ҵͳ��
	 * 
	 * @author honglin
	 * @param wwb
	 * @param nj
	 * @throws SQLException
	 */
	protected void printBjjytj(OutputStream os,String[] nj) throws SQLException{		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
		String rq=sdf.format(date);
		
		List<HashMap<String,String>> sydshi = new StuInfoDAO().getShiList("330000").get("shiList");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("�༶��ҵͳ�Ʊ�", 0);
		String njStr="";
		for (int i = 0 ; i < nj.length ; i++){
			njStr+=nj[i];
			if (i != nj.length-1){
				njStr=njStr+"��";
			}
		}
		String title =Base.xxmc+njStr+"���ҵ����ҵ��չ���ͳ�Ʊ�ͳ������"+rq+"��";
		try{
			ExcelMB excel = new ExcelMB();
			//excel.printTitle(ws, Base.xxmc+nj+"���ҵ����ҵ��չ���ͳ�Ʊ�ͳ������2012��3��12�գ�", 17,800 );// ����
			
			/////////////�����⿪ʼ/////////////
			 WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
						true, Alignment.CENTRE, VerticalAlignment.CENTRE,
						Border.ALL);// ��Ԫ����ʽ
			 ws.mergeCells(0,0,16,0);//�ϲ�17��
			 ws.addCell(new Label(0,0,title,wcfTytle));
			 ws.setRowView(0, 700);
			 //��int�� ����int���𡢿�int �С���int ����
			 ws.mergeCells(0, 0,3+sydshi.size(), 0);
			 ws.mergeCells(0,1,0,2);//�ϲ�����
			 ws.addCell(new Label(0,1,"���",wcfTytle));
			 ws.mergeCells(1,1,1,2);//�ϲ�����,Base.YXPZXY_KEY
			 ws.addCell(new Label(1,1,"ϵ��",wcfTytle));
			 ws.mergeCells(2,1,2,2);//�ϲ�����
			 ws.addCell(new Label(2,1,"�༶",wcfTytle));
			 ws.mergeCells(3,1,3,2);//�ϲ�����
			 ws.addCell(new Label(3,1,"ѧ��",wcfTytle));
			 ws.mergeCells(4,1,6,1);//�ϲ�������Ԫ��
			 ws.addCell(new Label(4,1,"����",wcfTytle));
			 ws.addCell(new Label(4,2,"��",wcfTytle));
			 ws.addCell(new Label(5,2,"��",wcfTytle));
			 ws.addCell(new Label(6,2,"Ů",wcfTytle));
			 ws.mergeCells(7,1,7,2);//�ϲ�����
			 ws.addCell(new Label(7,1,"��ȡЭ��",wcfTytle));
			 ws.mergeCells(8,1,8,2);//�ϲ�����
			 ws.addCell(new Label(8,1,"��ҵ����",wcfTytle));
			 ws.mergeCells(9,1,9,2);//�ϲ�����
			 ws.addCell(new Label(9,1,"ǩԼ",wcfTytle));
			 ws.mergeCells(10,1,10,2);//�ϲ�����
			 ws.addCell(new Label(10,1,"����",wcfTytle));
			 ws.mergeCells(11,1,11,2);//�ϲ�����
			 ws.addCell(new Label(11,1,"����",wcfTytle));
			 ws.mergeCells(12,1,12,2);//�ϲ�����
			 ws.addCell(new Label(12,1,"ǩԼ��",wcfTytle));
			 ws.mergeCells(13,1,13,2);//�ϲ�����
			 ws.addCell(new Label(13,1,"��ҵ������",wcfTytle));
			 ws.mergeCells(14,1,14,2);//�ϲ�����
			 ws.addCell(new Label(14,1,"������",wcfTytle));
			 ws.mergeCells(15,1,15,2);//�ϲ�����
			 ws.addCell(new Label(15,1,"����",wcfTytle));
			 ws.mergeCells(16,1,16,2);//�ϲ�����
			 ws.addCell(new Label(16,1,"��ʡ",wcfTytle));
			 ws.setColumnView(0, 4);
			 ws.setColumnView(1, 4);
			 ws.setColumnView(2, 15);
			 ws.setColumnView(3, 4);
			 ws.setColumnView(4, 4);
			 ws.setColumnView(5, 4);
			 ws.setColumnView(6, 4);
			 ws.setColumnView(7, 6);
			 ws.setColumnView(8, 6);
			 ws.setColumnView(9, 4);
			 ws.setColumnView(10, 4);
			 ws.setColumnView(11, 4);
			 ws.setColumnView(12, 9);
			 ws.setColumnView(13, 9);
			 ws.setColumnView(14, 9);
			 ws.setColumnView(15, 4);
			 ws.setColumnView(16, 4);
			 /////////////���������/////////////
			 
			 /////////////������ݿ�ʼ/////////////
			 
			 WritableCellFormat wcfTytle2 = ExcelMB.getWritableCellFormat(11,
						false, Alignment.CENTRE, VerticalAlignment.CENTRE,
						Border.ALL);// ��Ԫ����ʽ
			 wcfTytle2.setWrap(true);
			 
			 //��ȡѧУѧԺList
			 List<HashMap<String, String>> xxxy = dao.getXxXy(nj);
			 if(xxxy.size()>0){
				 String xymc ="";
				 String xydm ="";
				 int yrow =0;//ÿ��ѧԺ�༶����
				 int yAllrow=0;//ÿ��ѧԺ�����а༶�����ۼ�
				 int bjrow=2;//�༶����������ָ���༶����
				 int xh=0;//��ţ�����ָ���༶����
				 int zrow=0;//���һ������ 
				 
				 int xbjzrs =0;//ѧУ������
				 int xboyzrs =0;//ѧУ����������
				 int xgirlzrs =0;//ѧУŮ��������
				 int xxyzrs =0;//ѧУ��ȡЭ��;�ҵ����������
				 int xqyzrs =0;//ѧУǩԼ������
				 int xcgzrs =0;//ѧУ����������
				 int xszzrs =0;//ѧУ����������
				 double xlylrs =0;//ѧУǩԼ������
				 int xtkzrs =0;//ѧУ����������
				 int xwszrs =0;//ѧУ��ʡ������
				 int xbzrzrs=0;//ѧУ����������
				 double xqylzrs=0;
				 NumberFormat df = new DecimalFormat("#0.00");//�ٷֱ�
				 int zygs = 0;//��ѧԺ��רҵ����
				 
				 
				 for(int i =0;i<xxxy.size();i++){
					 HashMap<String, String> map = xxxy.get(i);
					 xymc=map.get("xymc");//ѧԺ����
					 xydm=map.get("xydm");//ѧԺ����
					 zygs=zygs+dao.getZygs(nj,xydm);
					 //��ȡѧԺ�еİ༶List
					 List<HashMap<String, String>> bjgzList = dao.getBjByGz(nj,xydm);//��ְ
					 List<HashMap<String, String>> bjbk = dao.getBjByBk(nj,xydm);//����
					 
					 List<HashMap<String, String>> bjgz = new ArrayList<HashMap<String, String>>();
					 //ȥ����ְ��Ҳ���Ʊ��Ƶ�����
					 if(bjgzList.size()>0){
						 for(int bj=0;bj<bjgzList.size();bj++){
							 HashMap<String, String> wclbjmap =bjgzList.get(bj);
							 String wclbjdm=wclbjmap.get("bjdm");//û�д�����ĸ�ְ�༶����
							 boolean bz = false;
							 if(bjbk.size()>0){
								 for(int bkbj=0;bkbj<bjbk.size();bkbj++){
									 HashMap<String, String> wclbkbjmap =bjbk.get(bkbj);
									 String wclbkbjdm=wclbkbjmap.get("bjdm");
									 if(wclbjdm.equals(wclbkbjdm)){
										 bz=true;
										 break;
									 }
								 }
							 }
							 if(bz==false){
								 HashMap<String, String> bjjg = new HashMap<String, String>();
								 bjjg.put("bjdm", wclbjmap.get("bjdm"));
								 bjjg.put("bjmc", wclbjmap.get("bjmc"));
								 bjgz.add(bjjg);
							 }
						 }
					 }
					 String bjmc ="";//�༶����
					 String bjdm ="";//�༶����
					 ////////////////���ϵ��--��ʼ/////////////////////
					 yrow = bjbk.size()+bjgz.size();
					 yAllrow=yrow+yAllrow;
					 if(yrow>0){
							 ws.mergeCells(1,yAllrow-yrow+3,1,yAllrow+3);//ѧԺ�ϲ�����
							 ws.addCell(new Label(1,yAllrow-yrow+3,xymc,wcfTytle2));
							 
							 if(bjbk.size()>0){
								 ws.mergeCells(3,yAllrow-yrow+3,3,yAllrow-bjgz.size()+2);//���ƺϲ�����
								 ws.addCell(new Label(3,yAllrow-yrow+3,"����",wcfTytle2));
								 if(bjgz.size()>0){
									 ws.mergeCells(1,yAllrow-bjbk.size()+3,1,yAllrow+2);//��ְ�ϲ�����
									 ws.addCell(new Label(3,yAllrow-yrow+3,"��ְ",wcfTytle2));
								 }
							 }else{
								 if(bjgz.size()>0){
									 ws.mergeCells(3,yAllrow-bjbk.size()+3,3,yAllrow+2);//��ְ�ϲ�����
									 ws.addCell(new Label(3,yAllrow-yrow+3,"��ְ",wcfTytle2));
								 }
							 }
							 
					 }
					 
					 ////////////////���ϵ��--����/////////////////////
					 ////////////////����༶--��ʼ/////////////////////
					 int bjzrs =0;//ѧԺ������
					 int boyzrs =0;//ѧԺ����������
					 int girlzrs =0;//ѧԺŮ��������
					 int xyzrs =0;//ѧԺ��ȡЭ��;�ҵ����������
					 int qyzrs =0;//ѧԺǩԼ������
					 int cgzrs =0;//ѧԺ����������
					 int szzrs =0;//ѧԺ����������
					 double lylrs =0;//ѧԺǩԼ������
					 int tkzrs =0;//ѧԺ����������
					 int wszrs =0;//ѧԺ��ʡ������
					 int bzrzrs=0;//ѧԺ����������
					 if(bjbk.size()>0){
						 zrow = zrow+bjbk.size();
						 for(int b =0;b<bjbk.size();b++){
							 HashMap<String, String> bkmap = bjbk.get(b);
							 bjmc=bkmap.get("bjmc");//�༶����
							 bjdm=bkmap.get("bjdm");//�༶����
							 bjrow=bjrow+1;//�༶��������
							 xh=xh+1;//���
							 ws.addCell(new Label(0,bjrow,xh+"",wcfTytle2));//���
							 ws.addCell(new Label(2,bjrow,bjmc,wcfTytle2));//�༶����
							 HashMap<String, String> zrsmap = dao.getBjZrs(bjdm);//�༶������
							 ws.addCell(new Label(4,bjrow,zrsmap.get("zrs"),wcfTytle2));//�༶������
							 HashMap<String, String> brsmap = dao.getBjBrs(bjdm);//�༶����������
							 ws.addCell(new Label(5,bjrow,brsmap.get("brs"),wcfTytle2));//�༶����������
							 HashMap<String, String> grsmap = dao.getBjGrs(bjdm);//�༶Ů��������
							 ws.addCell(new Label(6,bjrow,grsmap.get("grs"),wcfTytle2));//�༶Ů��������
							 HashMap<String, String> lqxyrsmap = dao.getBjLqxyrs(bjdm);//��ȡЭ������
							 ws.addCell(new Label(7,bjrow,lqxyrsmap.get("rs"),wcfTytle2));//��ȡЭ������
							 ws.addCell(new Label(8,bjrow,lqxyrsmap.get("rs"),wcfTytle2));//��ҵ��������
							 HashMap<String, String> qyrsmap = dao.getBjQyrs(bjdm);//ǩԼ����
							 ws.addCell(new Label(9,bjrow,qyrsmap.get("rs"),wcfTytle2));//ǩԼ����
							 HashMap<String, String> cgrsmap = dao.getBjCgrs(bjdm);//��������
							 ws.addCell(new Label(10,bjrow,cgrsmap.get("rs"),wcfTytle2));//��������
							 HashMap<String, String> szrsmap = dao.getBjSzrs(bjdm);//��������
							 ws.addCell(new Label(11,bjrow,szrsmap.get("rs"),wcfTytle2));//��������
							 
							 HashMap<String, String> qylmap = dao.getQyl(bjdm);//ǩԼ������
							 double q =Double.valueOf(qylmap.get("rs"));
							 double z =Double.valueOf(zrsmap.get("zrs"));
							 ws.addCell(new Label(12,bjrow,df.format(q/z*100)+"%",wcfTytle2));//ǩԼ��
							 double j =Double.valueOf(lqxyrsmap.get("rs"));
							 ws.addCell(new Label(13,bjrow,df.format(j/z*100)+"%",wcfTytle2));//��ҵ������
							 HashMap<String, String> bzrmap = dao.getBjBzr(bjdm);//��ȡ����������
							 ws.addCell(new Label(14,bjrow,bzrmap.get("xm"),wcfTytle2));//��ȡ����������
//							 if(bzrmap.get("xm")!=null){
//								 if(bzrmap.get("xm").length()>0){
//									 bzrzrs+=1;
//								 }
//							 }
							 HashMap<String, String> tksrsmap = dao.getBjTksrs(bjdm);//��ȡ�༶����������
							 ws.addCell(new Label(15,bjrow,tksrsmap.get("rs"),wcfTytle2));//��ȡ�༶����������
							 HashMap<String, String> wsrsmap = dao.getBjWsrs(bjdm);//��ȡ�༶��ʡ����
							 ws.addCell(new Label(16,bjrow,wsrsmap.get("rs"),wcfTytle2));//��ȡ�༶��ʡ����
							 
							 bjzrs = bjzrs + Integer.parseInt(zrsmap.get("zrs"));
							 boyzrs = boyzrs +Integer.parseInt(brsmap.get("brs"));
							 girlzrs = girlzrs +Integer.parseInt(grsmap.get("grs"));
							 xyzrs = xyzrs +Integer.parseInt(lqxyrsmap.get("rs"));
							 qyzrs = qyzrs +Integer.parseInt(qyrsmap.get("rs"));
							 cgzrs = cgzrs +Integer.parseInt(cgrsmap.get("rs"));
							 szzrs = szzrs +Integer.parseInt(szrsmap.get("rs"));
							 lylrs = lylrs + q;
							 tkzrs = tkzrs +Integer.parseInt(tksrsmap.get("rs"));
							 wszrs = wszrs +Integer.parseInt(wsrsmap.get("rs"));
						 }
					 }
					 if(bjgz.size()>0){
						 zrow = zrow+bjgz.size();
						 for(int g =0;g<bjgz.size();g++){
							 HashMap<String, String> gzmap = bjgz.get(g);
							 bjmc=gzmap.get("bjmc");//�༶����
							 bjdm=gzmap.get("bjdm");//�༶����
							 bjrow=bjrow+1;//�༶��������
							 xh=xh+1;//���
							 ws.addCell(new Label(0,bjrow,xh+"",wcfTytle2));//���
							 ws.addCell(new Label(2,bjrow,bjmc,wcfTytle2));//�༶����
							 HashMap<String, String> zrsmap = dao.getBjZrs(bjdm);//�༶������
							 ws.addCell(new Label(4,bjrow,zrsmap.get("zrs"),wcfTytle2));//�༶������
							 HashMap<String, String> brsmap = dao.getBjBrs(bjdm);//�༶����������
							 ws.addCell(new Label(5,bjrow,brsmap.get("brs"),wcfTytle2));//�༶����������
							 HashMap<String, String> grsmap = dao.getBjGrs(bjdm);//�༶Ů��������
							 ws.addCell(new Label(6,bjrow,grsmap.get("grs"),wcfTytle2));//�༶Ů��������
							 HashMap<String, String> lqxyrsmap = dao.getBjLqxyrs(bjdm);//��ȡЭ������
							 ws.addCell(new Label(7,bjrow,lqxyrsmap.get("rs"),wcfTytle2));//��ȡЭ������
							 ws.addCell(new Label(8,bjrow,lqxyrsmap.get("rs"),wcfTytle2));//��ҵ��������
							 HashMap<String, String> qyrsmap = dao.getBjQyrs(bjdm);//ǩԼ����
							 ws.addCell(new Label(9,bjrow,qyrsmap.get("rs"),wcfTytle2));//ǩԼ����
							 HashMap<String, String> cgrsmap = dao.getBjCgrs(bjdm);//��������
							 ws.addCell(new Label(10,bjrow,cgrsmap.get("rs"),wcfTytle2));//��������
							 HashMap<String, String> szrsmap = dao.getBjSzrs(bjdm);//��������
							 ws.addCell(new Label(11,bjrow,szrsmap.get("rs"),wcfTytle2));//��������
							 
							 HashMap<String, String> qylmap = dao.getQyl(bjdm);//ǩԼ������
							 double q =Double.valueOf(qylmap.get("rs"));
							 double z =Double.valueOf(zrsmap.get("zrs"));
							 ws.addCell(new Label(12,bjrow,df.format(q/z*100)+"%",wcfTytle2));//ǩԼ��
							 double j =Double.valueOf(lqxyrsmap.get("rs"));
							 ws.addCell(new Label(13,bjrow,df.format(j/z*100)+"%",wcfTytle2));//��ҵ������
							 HashMap<String, String> bzrmap = dao.getBjBzr(bjdm);//��ȡ����������
							 ws.addCell(new Label(14,bjrow,bzrmap.get("xm"),wcfTytle2));//��ȡ����������
//							 if(bzrmap.get("xm")!=null){
//								 if(bzrmap.get("xm").length()>0){
//									 bzrzrs+=1;
//								 }
//							 }
							 HashMap<String, String> tksrsmap = dao.getBjTksrs(bjdm);//��ȡ�༶����������
							 ws.addCell(new Label(15,bjrow,tksrsmap.get("rs"),wcfTytle2));//��ȡ�༶����������
							 HashMap<String, String> wsrsmap = dao.getBjWsrs(bjdm);//��ȡ�༶��ʡ����
							 ws.addCell(new Label(16,bjrow,wsrsmap.get("rs"),wcfTytle2));//��ȡ�༶��ʡ����
							 
							 bjzrs = bjzrs + Integer.parseInt(zrsmap.get("zrs"));
							 boyzrs = boyzrs +Integer.parseInt(brsmap.get("brs"));
							 girlzrs = girlzrs +Integer.parseInt(grsmap.get("grs"));
							 xyzrs = xyzrs +Integer.parseInt(lqxyrsmap.get("rs"));
							 qyzrs = qyzrs +Integer.parseInt(qyrsmap.get("rs"));
							 cgzrs = cgzrs +Integer.parseInt(cgrsmap.get("rs"));
							 szzrs = szzrs +Integer.parseInt(szrsmap.get("rs"));
							 lylrs = lylrs + q;
							 tkzrs = tkzrs +Integer.parseInt(tksrsmap.get("rs"));
							 wszrs = wszrs +Integer.parseInt(wsrsmap.get("rs"));
						 }
					 }
					 ////////////////����༶--����/////////////////////
					 WritableCellFormat wcfTytle3 = ExcelMB.getWritableCellFormat(11,
								false, Alignment.CENTRE, VerticalAlignment.CENTRE,
								Border.ALL);// ��Ԫ����ʽ
					 wcfTytle3.setWrap(true);
					 wcfTytle3.setBackground(Colour.YELLOW);
					 if(yAllrow>0){
						 bjrow=bjrow+1;
						 yAllrow=yAllrow+1;
						 double qylzrs=bjzrs;
						 xqylzrs=xqylzrs+qylzrs;
						 ws.addCell(new Label(0,bjrow,"",wcfTytle2));
						 ws.addCell(new Label(2,bjrow,"",wcfTytle2));
						 //ws.addCell(new Label(3,yAllrow+3,"d",wcfTytle3));
						 xbjzrs =xbjzrs+bjzrs;
						 ws.addCell(new Label(4,bjrow,bjzrs+"",wcfTytle3));
						 xboyzrs=xboyzrs+boyzrs;
						 ws.addCell(new Label(5,bjrow,boyzrs+"",wcfTytle3));
						 xgirlzrs=xgirlzrs+girlzrs;
						 ws.addCell(new Label(6,bjrow,girlzrs+"",wcfTytle3));
						 xxyzrs=xxyzrs+xyzrs;
						 ws.addCell(new Label(7,bjrow,xyzrs+"",wcfTytle3));
						 ws.addCell(new Label(8,bjrow,xyzrs+"",wcfTytle3));
						 xqyzrs=xqyzrs+qyzrs;
						 ws.addCell(new Label(9,bjrow,qyzrs+"",wcfTytle3));
						 xcgzrs = xcgzrs+cgzrs;
						 ws.addCell(new Label(10,bjrow,cgzrs+"",wcfTytle3));
						 xszzrs= xszzrs+szzrs;
						 ws.addCell(new Label(11,bjrow,szzrs+"",wcfTytle3));
						 xlylrs=xlylrs+lylrs;
						 ws.addCell(new Label(12,bjrow,df.format(lylrs/qylzrs*100)+"%",wcfTytle3));
						 ws.addCell(new Label(13,bjrow,df.format(xyzrs/qylzrs*100)+"%",wcfTytle3));
						 HashMap<String, String> bzrzrsmap = dao.getBzrzrs(xydm);
						 bzrzrs = Integer.parseInt(bzrzrsmap.get("rs"));
						 xbzrzrs =xbzrzrs+bzrzrs;
						 ws.addCell(new Label(14,bjrow,bzrzrs+"��",wcfTytle3));
						 xtkzrs=xtkzrs+tkzrs;
						 ws.addCell(new Label(15,bjrow,tkzrs+"",wcfTytle3));
						 xwszrs=xwszrs+wszrs;
						 ws.addCell(new Label(16,bjrow,wszrs+"",wcfTytle3));
						 zrow = zrow+1;
					 }
					 
				 }
				 WritableCellFormat wcfTytle4 = ExcelMB.getWritableCellFormat(11,
							false, Alignment.CENTRE, VerticalAlignment.CENTRE,
							Border.ALL);// ��Ԫ����ʽ
				 wcfTytle4.setWrap(true);
				 wcfTytle4.setBackground(Colour.GREEN);
				 ws.mergeCells(0,zrow+3,3,zrow+3);//�ϲ��ĸ���Ԫ��
				 ws.addCell(new Label(0,zrow+3,xxxy.size()+"��ϵ"+zygs+"��רҵ"+xh+"���༶",wcfTytle4));
				 ws.addCell(new Label(4,zrow+3,xbjzrs+"",wcfTytle4));
				 ws.addCell(new Label(5,zrow+3,xboyzrs+"",wcfTytle4));
				 ws.addCell(new Label(6,zrow+3,xgirlzrs+"",wcfTytle4));
				 ws.addCell(new Label(7,zrow+3,xxyzrs+"",wcfTytle4));
				 ws.addCell(new Label(8,zrow+3,xxyzrs+"",wcfTytle4));
				 ws.addCell(new Label(9,zrow+3,xqyzrs+"",wcfTytle4));
				 ws.addCell(new Label(10,zrow+3,xcgzrs+"",wcfTytle4));
				 ws.addCell(new Label(11,zrow+3,xszzrs+"",wcfTytle4));
				 ws.addCell(new Label(12,zrow+3,df.format(xlylrs/xqylzrs*100)+"%",wcfTytle4));
				 ws.addCell(new Label(13,zrow+3,df.format(xxyzrs/xqylzrs*100)+"%",wcfTytle4));
				 ws.addCell(new Label(14,zrow+3,xbzrzrs+"��",wcfTytle4));
				 ws.addCell(new Label(15,zrow+3,xtkzrs+"",wcfTytle4));
				 ws.addCell(new Label(16,zrow+3,xwszrs+"",wcfTytle4));
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //��ͻ������
		 ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ��רҵ��ҵͳ��
	 * 
	 * @author honglin
	 * @date 2012-4-23
	 * 
	 * @param wwb
	 * @param nj
	 * @throws SQLException
	 * 
	 */
	public void printZyjytj(OutputStream os,String[] nj,String[] jyxy) throws SQLException{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
		String rq=sdf.format(date);
		
		String njStr="";
		for (int i = 0 ; i < nj.length ; i++){
			njStr+=nj[i];
			if (i != nj.length-1){
				njStr=njStr+"��";
			}
		}
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("��רҵ��ҵ��ͳ��", 0);
		String title =Base.xxmc+njStr+"���ҵ����ҵ��չ���ͳ�Ʊ�";
		
		try {
			//��Ҫʹ�õ���ʽ
			WritableCellFormat btys = ExcelMB.getWritableCellFormat(12,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ����ⵥԪ����ʽ
			WritableCellFormat dygbtys2 = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 2�б��ⵥԪ����ʽ���޵�ɫ
			WritableCellFormat dygbtys3 = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// 3�б��ⵥԪ����ʽ���޵�ɫ
			WritableCellFormat btdsys = ExcelMB.getWritableCellFormat(10,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ�������ʽ���е�ɫ��ǳ��ɫ
			btdsys.setBackground(Colour.LIGHT_TURQUOISE2);
			WritableCellFormat dygdsys = ExcelMB.getWritableCellFormat(10,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ���е�ɫ��ǳ��ɫ
			dygdsys.setBackground(Colour.LIGHT_TURQUOISE2);
			WritableCellFormat dygys = ExcelMB.getWritableCellFormat(10,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ���޵�ɫ
			WritableCellFormat dygjcys = ExcelMB.getWritableCellFormat(10,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ���Ӵ�,�޵�ɫ
			WritableCellFormat dyghsys = ExcelMB.getWritableCellFormat(10,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ���е�ɫ����ɫ
			dyghsys.setBackground(Colour.GREY_25_PERCENT);
			//������
			 //��int�� ����int���𡢿�int �С���int ����
			 ws.mergeCells(0, 0,42, 0);
			 ws.addCell(new Label(0,0,title,btys));
			 //�������
			 ws.mergeCells(0, 1,42, 1);
			 ws.addCell(new Label(0,1,"��ͳ������"+rq+"��",dygbtys2));
			 ws.mergeCells(0, 2,42, 2);
			 ws.addCell(new Label(0,2,"�Ʊ�λ��������ҵ��",dygbtys3));
			 
			 
			 //������
			 ws.mergeCells(0,3,0,5);//�ϲ�����
			 ws.addCell(new Label(0,3,"רҵ����",btdsys));
			 ws.setColumnView(0, 15);
			 ws.mergeCells(1,3,1,5);//�ϲ�����
			 ws.addCell(new Label(1,3,"����",btdsys));
			 ws.setColumnView(1, 4);
			 ws.mergeCells(2, 3,13, 3);//�ϲ�12��
			 ws.addCell(new Label(2,3,"��Դ����",btdsys));
			 ws.mergeCells(2, 4,4, 4);//�ϲ�3��
			 ws.addCell(new Label(2,4,"�ϼ�",btdsys));
			 ws.addCell(new Label(2,5,"��",btdsys));
			 ws.setColumnView(2, 4);
			 ws.addCell(new Label(3,5,"Ů",btdsys));
			 ws.setColumnView(3, 4);
			 ws.addCell(new Label(4,5,"��",btdsys));
			 ws.setColumnView(4, 4);
			 ws.mergeCells(5, 4,7, 4);//�ϲ�3��
			 ws.addCell(new Label(5,4,"��",btdsys));
			 ws.addCell(new Label(5,5,"��",btdsys));
			 ws.setColumnView(5, 4);
			 ws.addCell(new Label(6,5,"Ů",btdsys));
			 ws.setColumnView(6, 4);
			 ws.addCell(new Label(7,5,"��",btdsys));
			 ws.setColumnView(7, 4);
			 ws.mergeCells(8, 4,10, 4);//�ϲ�3��
			 ws.addCell(new Label(8,4,"����",btdsys));
			 ws.addCell(new Label(8,5,"��",btdsys));
			 ws.setColumnView(8, 4);
			 ws.addCell(new Label(9,5,"Ů",btdsys));
			 ws.setColumnView(9, 4);
			 ws.addCell(new Label(10,5,"��",btdsys));
			 ws.setColumnView(10, 4);
			 ws.mergeCells(11, 4,13, 4);//�ϲ�3��
			 ws.addCell(new Label(11,4,"��ְ",btdsys));
			 ws.addCell(new Label(11,5,"��",btdsys));
			 ws.setColumnView(11, 4);
			 ws.addCell(new Label(12,5,"Ů",btdsys));
			 ws.setColumnView(12, 4);
			 ws.addCell(new Label(13,5,"��",btdsys));
			 ws.setColumnView(13, 4);
			 ws.mergeCells(14, 3,21, 3);//�ϲ�8��
			 ws.addCell(new Label(14,3,"��ҵ������",btdsys));
			 ws.mergeCells(14, 4,14, 5);//�ϲ�2��
			 ws.addCell(new Label(14,4,"��",btdsys));
			 ws.setColumnView(14, 4);
			 ws.mergeCells(15, 4,15, 5);//�ϲ�2��
			 ws.addCell(new Label(15,4,"��",btdsys));
			 ws.setColumnView(15, 8);
			 ws.mergeCells(16, 4,16, 5);//�ϲ�2��
			 ws.addCell(new Label(16,4,"����",btdsys));
			 ws.setColumnView(16, 4);
			 ws.mergeCells(17, 4,17, 5);//�ϲ�2��
			 ws.addCell(new Label(17,4,"��",btdsys));
			 ws.setColumnView(17, 8);
			 ws.mergeCells(18, 4,18, 5);//�ϲ�2��
			 ws.addCell(new Label(18,4,"��ְ",btdsys));
			 ws.setColumnView(18, 4);
			 ws.mergeCells(19, 4,19, 5);//�ϲ�2��
			 ws.addCell(new Label(19,4,"��",btdsys));
			 ws.setColumnView(19, 8);
			 ws.mergeCells(20, 4,20, 5);//�ϲ�2��
			 ws.addCell(new Label(20,4,"�ϼ�",btdsys));
			 ws.setColumnView(20, 4);
			 ws.mergeCells(21, 4,21, 5);//�ϲ�2��
			 ws.addCell(new Label(21,4,"��",btdsys));
			 ws.setColumnView(21, 8);
			 ws.mergeCells(22, 3,41, 3);//�ϲ�20��
			 ws.addCell(new Label(22,3,"ǩԼ��",btdsys));
			 ws.mergeCells(22, 4,24, 4);//�ϲ�3��
			 ws.addCell(new Label(22,4,"��",btdsys));
			 ws.addCell(new Label(22,5,"��",btdsys));
			 ws.setColumnView(22, 4);
			 ws.addCell(new Label(23,5,"Ů",btdsys));
			 ws.setColumnView(23, 4);
			 ws.addCell(new Label(24,5,"��",btdsys));
			 ws.setColumnView(24, 4);
			 ws.mergeCells(25, 4,27, 4);//�ϲ�3��
			 ws.addCell(new Label(25,4,"��",btdsys));
			 ws.addCell(new Label(25,5,"��",btdsys));
			 ws.setColumnView(25, 8);
			 ws.addCell(new Label(26,5,"Ů",btdsys));
			 ws.setColumnView(26, 8);
			 ws.addCell(new Label(27,5,"��",btdsys));
			 ws.setColumnView(27, 8);
			 ws.mergeCells(28, 4,30, 4);//�ϲ�3��
			 ws.addCell(new Label(28,4,"����",btdsys));
			 ws.addCell(new Label(28,5,"��",btdsys));
			 ws.setColumnView(28, 4);
			 ws.addCell(new Label(29,5,"Ů",btdsys));
			 ws.setColumnView(29, 4);
			 ws.addCell(new Label(30,5,"��",btdsys));
			 ws.setColumnView(30, 4);
			 ws.mergeCells(31, 4,33, 4);//�ϲ�3��
			 ws.addCell(new Label(31,4,"��",btdsys));
			 ws.addCell(new Label(31,5,"��",btdsys));
			 ws.setColumnView(31, 8);
			 ws.addCell(new Label(32,5,"Ů",btdsys));
			 ws.setColumnView(32, 8);
			 ws.addCell(new Label(33,5,"��",btdsys));
			 ws.setColumnView(33, 8);
			 ws.mergeCells(34, 4,36, 4);//�ϲ�3��
			 ws.addCell(new Label(34,4,"��ְ",btdsys));
			 ws.addCell(new Label(34,5,"��",btdsys));
			 ws.setColumnView(34, 4);
			 ws.addCell(new Label(35,5,"Ů",btdsys));
			 ws.setColumnView(35, 4);
			 ws.addCell(new Label(36,5,"��",btdsys));
			 ws.setColumnView(36, 4);
			 ws.mergeCells(37, 4,39, 4);//�ϲ�3��
			 ws.addCell(new Label(37,4,"��",btdsys));
			 ws.addCell(new Label(37,5,"��",btdsys));
			 ws.setColumnView(37, 8);
			 ws.addCell(new Label(38,5,"Ů",btdsys));
			 ws.setColumnView(38, 8);
			 ws.addCell(new Label(39,5,"��",btdsys));
			 ws.setColumnView(39, 8);
			 ws.mergeCells(40, 4,40, 5);//�ϲ�2��
			 ws.addCell(new Label(40,4,"�ϼ�",btdsys));
			 ws.setColumnView(40, 8);
			 ws.mergeCells(41, 4,41, 5);//�ϲ�2��
			 ws.addCell(new Label(41,4,"��",btdsys));
			 ws.setColumnView(41, 8);
			 ws.addCell(new Label(42,3,"ȥ��ͬ��",btdsys));
			 ws.setColumnView(42, 8);
			 ws.mergeCells(42, 4,42, 5);//�ϲ�2��
			 ws.addCell(new Label(42,4,"ǩԼ��",btdsys));
			 ws.setColumnView(42, 8);
			 
			 
			 //�������
			 
			 //�ܼƿ�ʼ
			 int syrs=0;//��Դ����
			 int sybrs=0;//��Դ��������
			 int sygrs=0;//��ԴŮ������
			 int sybkrs=0;//��Դ��������
			 int sybkbrs=0;//��Դ������������
			 int sybkgrs=0;//��Դ����Ů������
			 int sygzrs=0;//��Դ��ְ����
			 int sygzbrs=0;//��Դ��ְ��������
			 int sygzgrs=0;//��Դ��ְŮ������
			 int jylbkrs=0;//��ҵ�����ʱ�������
			 int jylgzrs=0;//��ҵ�����ʸ�ְ����
			 int jylrs=0;//��ҵ����������
			 int qylbkrs=0;//ǩԼ�ʱ�������
			 int qylbkbrs=0;//ǩԼ�ʱ�����������
			 int qylbkgrs=0;//ǩԼ�ʱ���Ů������
			 int qylgzrs=0;//ǩԼ�ʸ�ְ����
			 int qylgzbrs=0;//ǩԼ�ʸ�ְ��������
			 int qylgzgrs=0;//ǩԼ�ʸ�ְŮ������
			 int qylgjrs=0;//ǩԼ�ʺϼ�����
			 //�ܼƽ���
			 
			 //��ȡѧԺ�еİ༶�б�
			 List<HashMap<String , String >> jyzyList=dao.getXyByZy(nj,jyxy);
			 NumberFormat df = new DecimalFormat("#0.00");//�ٷֱ�
			 if(jyzyList.size()>0){
				 for (int i = 0 ; i< jyzyList.size(); i++ ) {
					 HashMap<String , String > jyzyMap = jyzyList.get(i);
					 String zymc = jyzyMap.get("zymc");//רҵ����
					 String zydm = jyzyMap.get("zydm");//רҵ����
					 //רҵ����
					 ws.addCell(new Label(0,6+i,zymc,btdsys));
					 //���
					 ws.addCell(new Label(1,6+i,i+1+"",dygdsys));
					 //��Դ����-�ϼ�-��������
					 HashMap<String, String> sybzrsMap= dao.getZyBrs(nj,zydm);
					 ws.addCell(new Label(2,6+i,sybzrsMap.get("brs"),dygdsys));
					 sybrs=sybrs+Integer.parseInt(sybzrsMap.get("brs"));
					//��Դ����-�ϼ�-Ů������
					 HashMap<String, String> sygzrsMap= dao.getZyGrs(nj,zydm);
					 ws.addCell(new Label(3,6+i,sygzrsMap.get("grs"),dygdsys));
					 sygrs=sygrs+Integer.parseInt(sygzrsMap.get("grs"));
					//��Դ����-�ϼ�-������
					 HashMap<String, String> syzrsMap= dao.getZyZrs(nj,zydm);
					 ws.addCell(new Label(4,6+i,syzrsMap.get("zrs"),dygdsys));
					 syrs=syrs+Integer.parseInt(syzrsMap.get("zrs"));
					 //��Դ����-��-����������
					 ws.addCell(new Label(5,6+i,"",dyghsys));
					//��Դ����-��-Ů��������
					 ws.addCell(new Label(6,6+i,"",dyghsys));
					//��Դ����-��-������
					 ws.addCell(new Label(7,6+i,"0",dygdsys));
					//��Դ����-����-����������
					 HashMap<String, String> sybkbzrsMap= dao.getZyBkBrs(nj,zydm);
					 ws.addCell(new Label(8,6+i,sybkbzrsMap.get("rs"),dygys));
					 sybkbrs=sybkbrs+Integer.parseInt(sybkbzrsMap.get("rs"));
					//��Դ����-����-Ů��������
					 HashMap<String, String> sybkgzrsMap= dao.getZyBkGrs(nj,zydm);
					 ws.addCell(new Label(9,6+i,sybkgzrsMap.get("rs"),dygys));
					 sybkgrs=sybkgrs+Integer.parseInt(sybkgzrsMap.get("rs"));
					//��Դ����-����-������
					 HashMap<String, String> sybkzrsMap= dao.getZyBkZrs(nj,zydm);
					 ws.addCell(new Label(10,6+i,sybkzrsMap.get("rs"),dygdsys));
					 sybkrs=sybkrs+Integer.parseInt(sybkzrsMap.get("rs"));
					//��Դ����-��ְ-����������
					 HashMap<String, String> sygzbzrsMap= dao.getZyGzBrs(nj,zydm);
					 ws.addCell(new Label(11,6+i,sygzbzrsMap.get("rs"),dygys));
					 sygzbrs=sygzbrs+Integer.parseInt(sygzbzrsMap.get("rs"));
					//��Դ����-��ְ-Ů��������
					 HashMap<String, String> sygzgzrsMap= dao.getZyGzGrs(nj,zydm);
					 ws.addCell(new Label(12,6+i,sygzgzrsMap.get("rs"),dygys));
					 sygzgrs=sygzgrs+Integer.parseInt(sygzgzrsMap.get("rs"));
					//��Դ����-��ְ-������
					 HashMap<String, String> sygzzrsMap= dao.getZyGzZrs(nj,zydm);
					 ws.addCell(new Label(13,6+i,sygzzrsMap.get("rs"),dygdsys));
					 sygzrs=sygzrs+Integer.parseInt(sygzzrsMap.get("rs"));
					//��ҵ������-��
					 ws.addCell(new Label(14,6+i,"",dygys));
					//��ҵ������-��-%
					 ws.addCell(new Label(15,6+i,"",dygdsys));
					//��ҵ������-����
					 HashMap<String,String> jylbkzrsMap = dao.getZyJylBkZrs(nj,zydm);
					 ws.addCell(new Label(16,6+i,jylbkzrsMap.get("rs"),dygys));
					 jylbkrs=jylbkrs+Integer.parseInt(jylbkzrsMap.get("rs"));
					//��ҵ������-����-%
					 double jylbkzrs = Double.parseDouble(jylbkzrsMap.get("rs"));//��ҵ�ʱ�������������
					 double sybkzrs = Double.parseDouble(sybkzrsMap.get("rs"));//��Դ����������
					 ws.addCell(new Label(17,6+i,df.format(jylbkzrs/sybkzrs*100)+"%",dygdsys));
					 if(sybkzrs==0.0){
						 ws.addCell(new Label(17,6+i,"0.00%",dygdsys));
					 }else{
						 ws.addCell(new Label(17,6+i,df.format(jylbkzrs/sybkzrs*100)+"%",dygdsys));
					 }
					//��ҵ������-��ְ
					 HashMap<String,String> jylgzzrsMap = dao.getZyJylGzZrs(nj,zydm);
					 ws.addCell(new Label(18,6+i,jylgzzrsMap.get("rs"),dygys));
					 jylgzrs=jylgzrs+Integer.parseInt(jylgzzrsMap.get("rs"));
					//��ҵ������-��ְ-%
					 double jylgzzrs = Double.parseDouble(jylgzzrsMap.get("rs"));//��ҵ�ʸ�ְ������
					 double sygzzrs = Double.parseDouble(sygzzrsMap.get("rs"));//��Դ��ְ������
					 if(sygzzrs==0.0){
						 ws.addCell(new Label(19,6+i,"0.00%",dygdsys));
					 }else{
						 ws.addCell(new Label(19,6+i,df.format(jylgzzrs/sygzzrs*100)+"%",dygdsys));
					 }
					//��ҵ������-�ϼ�
					 ws.addCell(new Label(20,6+i,Integer.parseInt(jylbkzrsMap.get("rs"))+Integer.parseInt(jylgzzrsMap.get("rs"))+"",dygdsys));
					 jylrs=jylrs+Integer.parseInt(jylbkzrsMap.get("rs"))+Integer.parseInt(jylgzzrsMap.get("rs"));
					//��ҵ������-�ϼ�-%
					 double syzrs = sybkzrs+sygzzrs;//��Դ������
					 double jyyxzrs = jylbkzrs+jylgzzrs;//��ҵ����������
					 if(jyyxzrs==0.0){
						 ws.addCell(new Label(21,6+i,"0.00%",dygdsys));
					 }else{
						 ws.addCell(new Label(21,6+i,df.format(jyyxzrs/syzrs*100)+"%",dygdsys));
					 }
					//ǩԼ��-�У��С�Ů���ܣ�
					 ws.addCell(new Label(22,6+i,"",dyghsys));
					 ws.addCell(new Label(23,6+i,"",dyghsys));
					 ws.addCell(new Label(24,6+i,"0",dygdsys));
					//ǩԼ��-��-%���С�Ů���ܣ�
					 ws.addCell(new Label(25,6+i,"",dygdsys));
					 ws.addCell(new Label(26,6+i,"",dygdsys));
					 ws.addCell(new Label(27,6+i,"",dygdsys));
					//ǩԼ��-����-����������
					 HashMap<String, String> qylbkbzrsMap= dao.getZyQylBkBZrs(nj,zydm);
					 ws.addCell(new Label(28,6+i,qylbkbzrsMap.get("rs"),dygys));
					 qylbkbrs=qylbkbrs+Integer.parseInt(qylbkbzrsMap.get("rs"));
					//ǩԼ��-����-Ů��������
					 HashMap<String, String> qylbkgzrsMap= dao.getZyQylBkGZrs(nj,zydm);
					 ws.addCell(new Label(29,6+i,qylbkgzrsMap.get("rs"),dygys));
					 qylbkgrs=qylbkgrs+Integer.parseInt(qylbkgzrsMap.get("rs"));
					//ǩԼ��-����-������
					 HashMap<String, String> qylbkzrsMap= dao.getZyQylBkZrs(nj,zydm);
					 ws.addCell(new Label(30,6+i,qylbkzrsMap.get("rs"),dygdsys));
					 qylbkrs=qylbkrs+Integer.parseInt(qylbkzrsMap.get("rs"));
					//ǩԼ��-�ж���Դ����������
					 if(sybkzrs==0.0){
						//ǩԼ��-����-����������-%
						 ws.addCell(new Label(31,6+i,"0.00%",dygdsys));
						//ǩԼ��-����-Ů��������-%
						 ws.addCell(new Label(32,6+i,"0.00%",dygdsys));
						//ǩԼ��-����-������-%
						 ws.addCell(new Label(33,6+i,"0.00%",dygdsys));
					 }else{
						//ǩԼ��-����-����������-%
						 double qylbkbzrs = Double.parseDouble(qylbkbzrsMap.get("rs"));
						 ws.addCell(new Label(31,6+i,df.format(qylbkbzrs/sybkzrs*100)+"%",dygdsys));
						//ǩԼ��-����-Ů��������-%
						 double qylbkgzrs = Double.parseDouble(qylbkgzrsMap.get("rs"));
						 ws.addCell(new Label(32,6+i,df.format(qylbkgzrs/sybkzrs*100)+"%",dygdsys));
						//ǩԼ��-����-������-%
						 double qylbkzrs = Double.parseDouble(qylbkzrsMap.get("rs"));
						 ws.addCell(new Label(33,6+i,df.format(qylbkzrs/sybkzrs*100)+"%",dygdsys));
					 }
					//ǩԼ��-��ְ-����������
					 HashMap<String, String> qylgzbzrsMap= dao.getZyQylGzBZrs(nj,zydm);
					 ws.addCell(new Label(34,6+i,qylgzbzrsMap.get("rs"),dygys));
					 qylgzbrs=qylgzbrs+Integer.parseInt(qylgzbzrsMap.get("rs"));
					//ǩԼ��-��ְ-Ů��������
					 HashMap<String, String> qylgzgzrsMap= dao.getZyQylGzGZrs(nj,zydm);
					 ws.addCell(new Label(35,6+i,qylgzgzrsMap.get("rs"),dygys));
					 qylgzgrs=qylgzgrs+Integer.parseInt(qylgzgzrsMap.get("rs"));
					//ǩԼ��-��ְ-������
					 HashMap<String, String> qylgzzrsMap= dao.getZyQylGzZrs(nj,zydm);
					 ws.addCell(new Label(36,6+i,qylgzzrsMap.get("rs"),dygdsys));
					 qylgzrs=qylgzrs+Integer.parseInt(qylgzzrsMap.get("rs"));
					//�ж���Դ��ְ������
					 if(sygzzrs==0.0){
						//ǩԼ��-��ְ-����������-%
						 ws.addCell(new Label(37,6+i,"0.00%",dygdsys));
						//ǩԼ��-��ְ-Ů��������-%
						 ws.addCell(new Label(38,6+i,"0.00%",dygdsys));
						//ǩԼ��-��ְ-������-%
						 ws.addCell(new Label(39,6+i,"0.00%",dygdsys));
					 }else{
						//ǩԼ��-��ְ-����������-%
						 double qylgzbzrs = Double.parseDouble(qylgzbzrsMap.get("rs"));
						 ws.addCell(new Label(37,6+i,df.format(qylgzbzrs/sygzzrs*100)+"%",dygdsys));
						//ǩԼ��-��ְ-Ů��������-%
						 double qylgzgzrs = Double.parseDouble(qylgzgzrsMap.get("rs"));
						 ws.addCell(new Label(38,6+i,df.format(qylgzgzrs/sygzzrs*100)+"%",dygdsys));
						//ǩԼ��-��ְ-������-%
						 double qylgzzrs = Double.parseDouble(qylgzzrsMap.get("rs"));
						 ws.addCell(new Label(39,6+i,df.format(qylgzzrs/sygzzrs*100)+"%",dygdsys));
					 }
					 ws.addCell(new Label(40,6+i,Integer.parseInt(qylgzzrsMap.get("rs"))+Integer.parseInt(qylbkzrsMap.get("rs"))+"",dygdsys));
					 qylgjrs=qylgjrs+Integer.parseInt(qylgzzrsMap.get("rs"))+Integer.parseInt(qylbkzrsMap.get("rs"));
					 double qylzrs = Double.parseDouble(qylgzzrsMap.get("rs"))+Double.parseDouble(qylbkzrsMap.get("rs"));
					 ws.addCell(new Label(41,6+i,df.format(qylzrs/syzrs*100)+"%",dygdsys));
					 ws.addCell(new Label(42,6+i,"",dygys));
				 }
				 int row =jyzyList.size()+6;
				 ws.mergeCells(0, row,1, row);
				 ws.addCell(new Label(0,row,"�ܼ�",btdsys));
				 //�ܼƿ�ʼ
				 ws.addCell(new Label(2,row,sybrs+"",dygdsys));//��Դ��������
				 ws.addCell(new Label(3,row,sygrs+"",dygdsys));//��ԴŮ������
				 ws.addCell(new Label(4,row,syrs+"",dygdsys));//��Դ����
				 ws.addCell(new Label(5,row,"0",dygdsys));
				 ws.addCell(new Label(6,row,"0",dygdsys));
				 ws.addCell(new Label(7,row,"0",dygdsys));
				 ws.addCell(new Label(8,row,sybkbrs+"",dygdsys));
				 ws.addCell(new Label(9,row,sybkgrs+"",dygdsys));
				 ws.addCell(new Label(10,row,sybkrs+"",dygdsys));//��Դ��������
				 ws.addCell(new Label(11,row,sygzbrs+"",dygdsys));
				 ws.addCell(new Label(12,row,sygzgrs+"",dygdsys));
				 ws.addCell(new Label(13,row,sygzrs+"",dygdsys));//��Դ��ְ����
				 ws.addCell(new Label(14,row,"0",dygdsys));
				 ws.addCell(new Label(15,row,"",dygdsys));
				 ws.addCell(new Label(16,row,jylbkrs+"",dygdsys));//��ҵ�����ʱ�������
				 double dsybkrs = sybkrs;//��Դ��������
				 double djylbkrs = jylbkrs;//��ҵ�����ʱ�������
				 ws.addCell(new Label(17,row,df.format(djylbkrs/dsybkrs*100)+"%",dygdsys));//��ҵ�����ʱ���
				 ws.addCell(new Label(18,row,jylgzrs+"",dygdsys));//��ҵ�����ʸ�ְ����
				 double dsygzrs = sygzrs;//��Դ��ְ����
				 double djylgzrs = jylgzrs;//��ҵ�����ʸ�ְ����
				 ws.addCell(new Label(19,row,df.format(djylgzrs/dsygzrs*100)+"%",dygdsys));//��ҵ�����ʸ�ְ
				 ws.addCell(new Label(20,row,jylrs+"",dygdsys));//��ҵ����������
				 double dsyrs = syrs;//��Դ����
				 double djylrs = jylrs;//��ҵ����������
				 ws.addCell(new Label(21,row,df.format(djylrs/dsyrs*100)+"%",dygdsys));//��ҵ������
				 ws.addCell(new Label(22,row,"0",dygdsys));//ǩԼ��-��
				 ws.addCell(new Label(23,row,"0",dygdsys));
				 ws.addCell(new Label(24,row,"0",dygdsys));
				 ws.addCell(new Label(25,row,"",dygdsys));
				 ws.addCell(new Label(26,row,"",dygdsys));
				 ws.addCell(new Label(27,row,"",dygdsys));
				 
				 ws.addCell(new Label(28,row,qylbkbrs+"",dygdsys));//ǩԼ�ʱ�����������
				 ws.addCell(new Label(29,row,qylbkgrs+"",dygdsys));//ǩԼ�ʱ���Ů������
				 ws.addCell(new Label(30,row,qylbkrs+"",dygdsys));//ǩԼ�ʱ�������
				 double dqylbkbrs = qylbkbrs;
				 ws.addCell(new Label(31,row,df.format(dqylbkbrs/dsybkrs*100)+"%",dygdsys));//ǩԼ�ʱ�������
				 double dqylbkgrs = qylbkgrs;
				 ws.addCell(new Label(32,row,df.format(dqylbkgrs/dsybkrs*100)+"%",dygdsys));//ǩԼ�ʱ���Ů��
				 double dqylbkrs = qylbkrs;
				 ws.addCell(new Label(33,row,df.format(dqylbkrs/dsybkrs*100)+"%",dygdsys));//ǩԼ�ʱ���
				 ws.addCell(new Label(34,row,qylgzbrs+"",dygdsys));//ǩԼ�ʸ�ְ��������
				 ws.addCell(new Label(35,row,qylgzgrs+"",dygdsys));//ǩԼ�ʸ�ְŮ������
				 ws.addCell(new Label(36,row,qylgzrs+"",dygdsys));//ǩԼ�ʺϼ�����
				 double dqylgzbrs = qylgzbrs ;
				 ws.addCell(new Label(37,row,df.format(dqylgzbrs/dsygzrs*100)+"%",dygdsys));//ǩԼ�ʸ�ְ����
				 double dqylgzgrs = qylgzgrs;
				 ws.addCell(new Label(38,row,df.format(dqylgzgrs/dsygzrs*100)+"%",dygdsys));//ǩԼ�ʸ�ְŮ��
				 double dqylgzrs = qylgzrs;
				 ws.addCell(new Label(39,row,df.format(dqylgzrs/dsygzrs*100)+"%",dygdsys));//ǩԼ�ʸ�ְ����
				 ws.addCell(new Label(40,row,qylgjrs+"",dygdsys));//ǩԼ�ʺϼ�����
				 double dqylgjrs = qylgjrs;
				 ws.addCell(new Label(41,row,df.format(dqylgjrs/dsyrs*100)+"%",dygdsys));
				 ws.addCell(new Label(42,row,"",dygys));
				 //�ܼƽ���
				 ws.mergeCells(0, row+1,42, row+1);
				 ws.addCell(new Label(0,row+1,"������������������������������������������������������������������������������ˣ�",dygjcys));
				 ws.mergeCells(0, row+2,42, row+2);
				 ws.addCell(new Label(0,row+2,"��ע��1.��ҵ�����ʰ��������Թ��ȴ�ǩԼ����������ѧ��������ҵ��",dygbtys3));
				 //ws.mergeCells(0, row+3,42, row+3);
				 //ws.addCell(new Label(0,row+3,"      2.��ǩԼ�ʴӸߵ�������",dygbtys3));
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}
	
	
	/**
	 * ��ҵ��ͳ��
	 * @param wwb
	 * @param nj
	 * @throws SQLException
	 */
	protected void printJyltj(OutputStream os, String[] nj) throws SQLException {
		List<HashMap<String, String>> xybj = dao.getXyBj(nj);
		HashMap<String, String> jyltj = dao.getJylHj(nj);//��ҵ�ʺϼ�
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("��ҵ��ͳ��", 0);
		
		try {
			
			excel.printTitle(ws, Base.xxmc + Arrays.toString(nj).replace("[", "").replace("]", "").replaceAll(",", "��") + "���ҵ�����ξ�ҵ��ͳ�Ʊ�", 23, 800);// ����
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ

			for (int i = 0 ; i < 7 ; i ++) {
				ws.mergeCells(i, 2, i, 3);// int ����int ����int �н�����int �н���
			}
			
			for (int i =12 ; i < 18 ; i ++) {
				ws.mergeCells(i, 2, i, 3);
			}
			
			ws.mergeCells(20, 2, 20, 3);
			ws.mergeCells(21, 2, 21, 3);
			ws.mergeCells(22, 2, 22, 3);
			Base.YXPZXY_KEY = message.getMessage("lable.xb");
			ws.addCell(new Label(0, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(1, 2, "����"+Base.YXPZXY_KEY, wcfTytle));
			ws.addCell(new Label(2, 2, "רҵ", wcfTytle));
			ws.addCell(new Label(3, 2, "�༶", wcfTytle));
			ws.addCell(new Label(4, 2, "��ҵ����", wcfTytle));
			ws.addCell(new Label(5, 2, "ǩԼ����", wcfTytle));
			
			//ǩԼ�ʡ�����ҵ���е�style
			WritableCellFormat format =  ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			format.setBackground(Colour.AQUA);
			format.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			//��ҵ��־�е�style
			WritableCellFormat bzColor =  ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			bzColor.setBackground(Colour.YELLOW);
			bzColor.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			//	��ҵ�ʵ�style
			WritableCellFormat jyColor =  ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			jyColor.setBackground(Colour.ICE_BLUE);
			jyColor.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			/*
			 * ��ͷ
			 */
			ws.addCell(new Label(6, 2, "ǩԼ��", format));
			ws.mergeCells(7, 2, 11, 2);//�ϲ����в���
			ws.addCell(new Label(7, 2, "����", wcfTytle));
			ws.addCell(new Label(7, 3, "ǩЭ��", wcfTytle));
			ws.addCell(new Label(8, 3, "����", wcfTytle));
			ws.addCell(new Label(9, 3, "��ѧ", wcfTytle));
			ws.addCell(new Label(10, 3, "����Ա", wcfTytle));
			ws.addCell(new Label(11, 3, "����", wcfTytle));
			ws.addCell(new Label(12, 2, "����ҵ", wcfTytle));
			ws.addCell(new Label(13, 2, "����ҵ��", format));
			ws.addCell(new Label(14, 2, "ӦƸ��", wcfTytle));
			ws.addCell(new Label(15, 2, "ӦƸ��", format));
			ws.addCell(new Label(16, 2, "��ҵ��", jyColor));
			ws.addCell(new Label(17, 2, "δ��ҵ��", wcfTytle));
			ws.mergeCells(18, 2, 19, 2);//�ϲ����в���
			ws.addCell(new Label(18, 2, "����", wcfTytle));
			ws.addCell(new Label(18, 3, "����ҵ", wcfTytle));
			ws.addCell(new Label(19, 3, "�ݲ���ҵ", wcfTytle));
			ws.addCell(new Label(20, 2, "רҵǩԼ��", wcfTytle));
			ws.addCell(new Label(21, 2, "רҵǩԼ��", wcfTytle));
			ws.addCell(new Label(22, 2, "����ҵ��", format));
			
			int row = 4;
			
			for ( int i = 0 ; i < xybj.size() ; i ++) {
				String xymc = xybj.get(i).get("xymc");
				String xydm = xybj.get(i).get("xydm");
				List<HashMap<String, String>> jyl = dao.getJyl(xydm,nj);
				int bjs = Integer.valueOf(xybj.get(i).get("bjs"));
				/*
				 * С�Ʋ�������
				 */	
				int bysrs = 0;
				int qyrs = 0;
				int qxjrs = 0;
				int cgrs = 0;
				int sxrs = 0;
				int jgrs = 0;
				int xbrs = 0;
				int lhjyrs = 0;
				int yps = 0 ;
				int wjys = 0;
				int djys = 0;
				int zbjys = 0;
				
				for ( int j = 0 ; j < bjs ; j++) {
					bysrs+=Integer.valueOf(jyl.get(j).get("bysrs"));
					qyrs+=Integer.valueOf(jyl.get(j).get("qys"));
					qxjrs+=Integer.valueOf(jyl.get(j).get("qxjrs"));
					cgrs+=Integer.valueOf(jyl.get(j).get("cgrs"));
					sxrs+=Integer.valueOf(jyl.get(j).get("sxrs"));
					jgrs+=Integer.valueOf(jyl.get(j).get("jgrs"));
					xbrs+=Integer.valueOf(jyl.get(j).get("xbrs"));
					lhjyrs+=Integer.valueOf(jyl.get(j).get("lhjys"));
					yps+=Integer.valueOf(jyl.get(j).get("yps"));
					wjys+=Integer.valueOf(jyl.get(j).get("wjys"));
					djys+=Integer.valueOf(jyl.get(j).get("djys"));
					zbjys+=Integer.valueOf(jyl.get(j).get("zbjys"));
					
					ws.addCell(new Label(1, row+j, xymc, wcfTytle));
					ws.addCell(new Label(2, row+j, jyl.get(j).get("zymc"), wcfTytle));
					ws.addCell(new Label(3, row+j, jyl.get(j).get("bjmc"), wcfTytle));
					ws.addCell(new Label(4, row+j, jyl.get(j).get("bysrs"), wcfTytle));
					ws.addCell(new Label(5, row+j, jyl.get(j).get("qys"), wcfTytle));
					ws.addCell(new Label(6, row+j, jyl.get(j).get("qyl"), format));
					ws.addCell(new Label(7, row+j, jyl.get(j).get("qxjrs"), bzColor));
					ws.addCell(new Label(8, row+j, jyl.get(j).get("cgrs"), bzColor));
					ws.addCell(new Label(9, row+j, jyl.get(j).get("sxrs"), bzColor));
					ws.addCell(new Label(10, row+j, jyl.get(j).get("jgrs"), bzColor));
					ws.addCell(new Label(11, row+j, jyl.get(j).get("xbrs"), bzColor));
					ws.addCell(new Label(12, row+j, jyl.get(j).get("lhjys"), wcfTytle));
					ws.addCell(new Label(13, row+j, jyl.get(j).get("lhjyl"), format));
					ws.addCell(new Label(14, row+j, jyl.get(j).get("yps"), bzColor));
					ws.addCell(new Label(15, row+j, jyl.get(j).get("ypl"), format));
					ws.addCell(new Label(16, row+j, jyl.get(j).get("jyl"), jyColor));
					ws.addCell(new Label(17, row+j, jyl.get(j).get("wjys"), wcfTytle));
					ws.addCell(new Label(18, row+j, jyl.get(j).get("djys"), wcfTytle));
					ws.addCell(new Label(19, row+j, jyl.get(j).get("zbjys"), bzColor));
					
					ws.addCell(new Label(22, row+j, jyl.get(j).get("djyl"), format));
				}
				
				ws.addCell(new Label(1, row+bjs, xymc, wcfTytle));
				
				/*
				 * С�Ʋ������
				 */
				ws.mergeCells(2, row+bjs, 3, row+bjs);
				ws.addCell(new Label(2, row+bjs, "С��", wcfTytle));
				ws.addCell(new Label(4, row+bjs, bysrs+"", wcfTytle));
				ws.addCell(new Label(5, row+bjs, qyrs+"", wcfTytle));
				ws.addCell(new Label(7, row+bjs, qxjrs+"", bzColor));
				ws.addCell(new Label(8, row+bjs, cgrs+"", bzColor));
				ws.addCell(new Label(9, row+bjs, sxrs+"", bzColor));
				ws.addCell(new Label(10, row+bjs, jgrs+"", bzColor));
				ws.addCell(new Label(11, row+bjs, xbrs+"", bzColor));
				ws.addCell(new Label(12, row+bjs, lhjyrs+"", wcfTytle));
				ws.addCell(new Label(14, row+bjs, yps+"", bzColor));
				ws.addCell(new Label(17, row+bjs, wjys+"", wcfTytle));
				ws.addCell(new Label(18, row+bjs, djys+"", wcfTytle));
				ws.addCell(new Label(19, row+bjs, zbjys+"", bzColor));
				
				/*
				 * С�Ʋ���ͳ��
				 */
				if (bysrs==0) {
					ws.addCell(new Label(6, row+bjs, "0.00%", format));
					ws.addCell(new Label(13, row+bjs, "0.00%", format));
					ws.addCell(new Label(15, row+bjs, "0.00%", format));
					ws.addCell(new Label(16, row+bjs, "0.00%", jyColor));
				} else {
					ws.addCell(new Label(6, row+bjs, Math.round((Float.valueOf(qyrs)/Float.valueOf(bysrs))*100)+"%", format));
					ws.addCell(new Label(13, row+bjs, Math.round((Float.valueOf(lhjyrs)/Float.valueOf(bysrs))*100)+"%", format));
					ws.addCell(new Label(15, row+bjs, Math.round((Float.valueOf(yps)/Float.valueOf(bysrs))*100)+"%", format));
					ws.addCell(new Label(16, row+bjs, Math.round((Float.valueOf(qyrs+yps+lhjyrs)/Float.valueOf(bysrs))*100)+"%", jyColor));
				}
				
				
				row += bjs+1;
			}
			
			this.mergeCellsByXy(ws, row, 1, 4,format);
			this.mergeCellsByZy(ws, row, 2, 4);
			
			ws.mergeCells(0,4,0, row);
			ws.addCell(new Label(0,4,"����",wcfTytle));
			/*
			 * �ϼƲ������
			 */
			ws.mergeCells(1,row, 3, row);
			ws.addCell(new Label(1,row,"�ϼ�",wcfTytle));
			ws.addCell(new Label(4,row,jyltj.get("bysrs"),wcfTytle));
			ws.addCell(new Label(5,row,jyltj.get("qys"),wcfTytle));
			ws.addCell(new Label(6,row,jyltj.get("qyl"),format));
			ws.addCell(new Label(7,row,jyltj.get("qxjrs"),bzColor));
			ws.addCell(new Label(8,row,jyltj.get("sxrs"),bzColor));
			ws.addCell(new Label(9,row,jyltj.get("cgrs"),bzColor));
			ws.addCell(new Label(10,row,jyltj.get("jgrs"),bzColor));
			ws.addCell(new Label(11,row,jyltj.get("xbrs"),bzColor));
			ws.addCell(new Label(12,row,jyltj.get("lhjys"),wcfTytle));
			ws.addCell(new Label(13,row,jyltj.get("lhjyl"),format));
			ws.addCell(new Label(14,row,jyltj.get("yps"),bzColor));
			ws.addCell(new Label(15,row,jyltj.get("ypl"),format));
			ws.addCell(new Label(16,row,jyltj.get("jyl"),jyColor));
			ws.addCell(new Label(17,row,jyltj.get("wjys"),wcfTytle));
			ws.addCell(new Label(18,row,jyltj.get("djys"),wcfTytle));
			ws.addCell(new Label(19,row,jyltj.get("zbjys"),bzColor));
			ws.addCell(new Label(20,row,jyltj.get("qys"),wcfTytle));
			ws.addCell(new Label(21,row,jyltj.get("qyl"),wcfTytle));
			ws.addCell(new Label(22,row,jyltj.get("djyl"),format));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * ѧԺ�ϲ���Ԫ��
	 * @param ws
	 * @param size
	 * @param col
	 * @param row
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	private void mergeCellsByXy(WritableSheet ws, int size, int col, int row,WritableCellFormat format)
			throws RowsExceededException, WriteException {

		int count = 1;
		boolean isHb = false;
		int bysrs = 0;
		int djyrs = 0;

		for (int i = 0; i < size; i++) {
			WritableCell currCell = ws.getWritableCell(col, row + i);// ��ǰ��Ԫ��
			WritableCell beforeCell = ws.getWritableCell(col, row + i - 1);// ��һ��Ԫ��
			String currCellStr = currCell.getContents();
			String beforeCellStr = beforeCell.getContents();

			String content = ws.getWritableCell(18, row + i).getContents();
			String byscontent = ws.getWritableCell(4, row + i).getContents();

			if (!Base.isNull(content)) {
				djyrs += Integer.valueOf(content);
				bysrs += Integer.valueOf(byscontent);
			}

			if (currCellStr.equals(beforeCellStr)) {
				count++;
				isHb = true;
			}

			// �ж��Ƿ�úϲ�
			if ((!currCellStr.equals(beforeCellStr)) && isHb) {
				ws.mergeCells(col, row + i - count, col, row + i - 1);

				if (bysrs == 0 || djyrs == 0) {
					ws.addCell(new Label(22, row + i - count, "0.00%",format));
				} else {
					ws.addCell(new Label(22, row + i - count, Math.round((Float
							.valueOf(djyrs) / Float.valueOf(bysrs)) * 100)
							+ "%",format));
				}

				ws.mergeCells(22, row + i - count, 22, row + i - 1);

				// ��Ԫ��ϲ������ò���
				count = 1;
				isHb = false;
				djyrs = 0;
				bysrs = 0;
			}
		}
	}
	
	
	/**
	 * רҵ�ϲ�����
	 * @param ws
	 * @param size
	 * @param col
	 * @param row
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	private void mergeCellsByZy(WritableSheet ws, int size, int col, int row)
			throws RowsExceededException, WriteException {
		
		int count = 1;
		boolean isHb = false;
		int zyqys = 0;
		int bysrs = 0;
		int hjbys = 0;
		int hjqys = 0;
		
		for ( int i = 0 ; i < size ; i++) {
			WritableCell currCell = ws.getWritableCell(col, row+i );//��ǰ��Ԫ��
			WritableCell beforeCell = ws.getWritableCell(col, row+i - 1);//��һ��Ԫ��
			String currCellStr = currCell.getContents();
			String beforeCellStr = beforeCell.getContents();
			
			String content = ws.getWritableCell(col+3, row+i ).getContents();
			String byscontent = ws.getWritableCell(col+2, row+i ).getContents();
			
			if (!Base.isNull(content)) {
				zyqys += Integer.valueOf(content);
				bysrs += Integer.valueOf(byscontent);
			}
		
			
			if (currCellStr.equals(beforeCellStr)) {
				count++;
				isHb = true;
			}
			
			//�ж��Ƿ�úϲ�
			if ((!currCellStr.equals(beforeCellStr)) && isHb) {
				ws.mergeCells(col, row + i - count, col, row + i - 1);
				
				ws.addCell(new Label(20,row + i - count,zyqys+""));
				ws.mergeCells(20, row + i - count, 20, row + i - 1);
				
				if (bysrs == 0 || zyqys == 0) {
					ws.addCell(new Label(21,row + i - count,"0.00%"));
				} else {
					int zyjyl = Math.round((Float.valueOf(zyqys)/Float.valueOf(bysrs))*100);	
					ws.addCell(new Label(21,row + i - count,zyjyl+"%"));
				}
				ws.mergeCells(21, row + i - count, 21, row + i - 1);
				// ��Ԫ��ϲ������ò���
				count = 1;
				isHb = false;
				
				hjqys+=zyqys;
				hjbys+=bysrs;
				
				zyqys=0;
				bysrs=0;
			}
		}
		ws.addCell(new Label(20,size-1,hjqys+""));
		ws.addCell(new Label(21,size-1,Math.round((Float.valueOf(hjqys)/Float.valueOf(hjbys))*100)+"%"));
	}
	
	
	
	/**
	 * ��ȡ����״̬
	 * @return
	 */
	protected String getKgzt() {
		
		if (Base.isNull(dao.getKgzt())) {
			return "close";
		} else {
			return "open";
		}
	}
	
	
	/**
	 * �ش��ҵ��ͳ��
	 * @param wwb
	 * @param nj
	 * @throws SQLException
	 */
	protected void zgddPrintJyltj(OutputStream os, String nj) throws SQLException {
		List<HashMap<String, String>> xybj = dao.getXyBj(nj);
		HashMap<String, String> jylhj = dao.getZgddJylhj(nj);//��ҵ�ʺϼ�
		
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("��ҵ��ͳ��", 0);
		
		try {
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(18);
			wcfTytle.setFont(wfTytle);

			// int ����int ���𣬿�int �У�int �н���
			ws.mergeCells(0, 0, 12, 0);
			ws.addCell(new Label(0, 0, Base.xxmc + nj + "���ҵ����ҵ��ͳ�Ʊ�", wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			//���õ�Ԫ�����¾���
			wcfTytle.setVerticalAlignment(VerticalAlignment.CENTRE);

			for (int i = 0 ; i < 13 ; i ++) {
				ws.mergeCells(i, 2, i, 3);// int ����int ����int �н�����int �н���
			}
			
			/*
			 * ��ͷ
			 */
			ws.addCell(new Label(0, 2, Base.YXPZXY_KEY, wcfTytle));
			ws.addCell(new Label(1, 2, "רҵ", wcfTytle));
			ws.addCell(new Label(2, 2, "�༶", wcfTytle));
			ws.addCell(new Label(3, 2, "��ҵ������", wcfTytle));
			ws.addCell(new Label(4, 2, "ǩԼ", wcfTytle));
			ws.addCell(new Label(5, 2, "��ѧ", wcfTytle));
			ws.addCell(new Label(6, 2, "����", wcfTytle));
			ws.addCell(new Label(7, 2, "����ְҵ", wcfTytle));
			ws.addCell(new Label(8, 2, "������ҵ", wcfTytle));
			ws.addCell(new Label(9, 2, "����ҵ", wcfTytle));
			ws.addCell(new Label(10, 2, "ǩԼ��", wcfTytle));
			ws.addCell(new Label(11, 2, "��ѧ��", wcfTytle));
			ws.addCell(new Label(12, 2, "��ҵ��", wcfTytle));
			
			int row = 4;
			/*
			 * ���Ժϵ��רҵ���༶
			 */
			for ( int i = 0 ; i < xybj.size() ; i ++) {
				String xymc = xybj.get(i).get("xymc");
				int bjs = Integer.valueOf(xybj.get(i).get("bjs"));
				List<HashMap<String, String>> jyl = dao.getZgddJyl(xybj.get(i).get("xydm"),nj);
				
				int bysrs = 0;//С�ƣ���ҵ������
				int qyzs = 0;//С�ƣ�ǩԼ����
				int sxrs = 0;//С�ƣ���ѧ����
				int cgrs = 0;//С�ƣ���������
				int zyzy = 0;//С�ƣ�����ְҵ����
				int zzcy = 0;//С�ƣ�������ҵ����
				int djys = 0;//С�ƣ�����ҵ����
				
				for ( int j = 0 ; j < bjs ; j++) {
					
					bysrs+=Integer.valueOf(jyl.get(j).get("bysrs"));
					qyzs+=Integer.valueOf(jyl.get(j).get("qyzs"));
					sxrs+=Integer.valueOf(jyl.get(j).get("sxrs"));
					cgrs+=Integer.valueOf(jyl.get(j).get("cgrs"));
					zyzy+=Integer.valueOf(jyl.get(j).get("zyzy"));
					zzcy+=Integer.valueOf(jyl.get(j).get("zzcy"));
					djys+=Integer.valueOf(jyl.get(j).get("djys"));
					
					ws.addCell(new Label(0, row+j, xymc, wcfTytle));
					ws.addCell(new Label(1, row+j, jyl.get(j).get("zymc"), wcfTytle));
					ws.addCell(new Label(2, row+j, jyl.get(j).get("bjmc"), wcfTytle));
					ws.addCell(new Label(3, row+j, jyl.get(j).get("bysrs"), wcfTytle));
					ws.addCell(new Label(4, row+j, jyl.get(j).get("qyzs"), wcfTytle));
					ws.addCell(new Label(5, row+j, jyl.get(j).get("sxrs"), wcfTytle));
					ws.addCell(new Label(6, row+j, jyl.get(j).get("cgrs"), wcfTytle));
					ws.addCell(new Label(7, row+j, jyl.get(j).get("zyzy"), wcfTytle));
					ws.addCell(new Label(8, row+j, jyl.get(j).get("zzcy"), wcfTytle));
					ws.addCell(new Label(9, row+j, jyl.get(j).get("djys"), wcfTytle));
					ws.addCell(new Label(10, row+j,jyl.get(j).get("qyl"), wcfTytle));
					ws.addCell(new Label(11, row+j,jyl.get(j).get("sxl"), wcfTytle));
					ws.addCell(new Label(12, row+j,jyl.get(j).get("jyl"), wcfTytle));
					
				}
				
				/*
				 * С����
				 */
				ws.addCell(new Label(1, row+bjs, xymc, wcfTytle));
				ws.mergeCells(0, row+bjs, 2, row+bjs);
				ws.addCell(new Label(0, row+bjs, "С��", wcfTytle));
				ws.addCell(new Label(3, row+bjs, String.valueOf(bysrs), wcfTytle));
				ws.addCell(new Label(4, row+bjs, String.valueOf(qyzs), wcfTytle));
				ws.addCell(new Label(5, row+bjs, String.valueOf(sxrs), wcfTytle));
				ws.addCell(new Label(6, row+bjs, String.valueOf(cgrs), wcfTytle));
				ws.addCell(new Label(7, row+bjs, String.valueOf(zyzy), wcfTytle));
				ws.addCell(new Label(8, row+bjs, String.valueOf(zzcy), wcfTytle));
				ws.addCell(new Label(9, row+bjs, String.valueOf(djys), wcfTytle));
				
				/*
				 * ǩԼ�ʡ���ѧ�ʡ���ҵ��
				 * ��ҵ������Ϊ0�� ��ȫ��Ϊ0.00��
				 * ����
				 * ǩԼ�ʣ�ǩԼ����/��ҵ������
				 * ��ѧ�ʣ���ѧ����/��ҵ������
				 * ��ҵ�ʣ��Ǵ���ҵ����/��ҵ������
				 */
				if (bysrs==0) {
					ws.addCell(new Label(10, row+bjs,"0.00%", wcfTytle));
					ws.addCell(new Label(11, row+bjs,"0.00%", wcfTytle));
					ws.addCell(new Label(12, row+bjs,"0.00%", wcfTytle));
				} else {
					ws.addCell(new Label(10, row+bjs, Math.round((Float.valueOf(qyzs)/Float.valueOf(bysrs))*100)+"%", wcfTytle));
					ws.addCell(new Label(11, row+bjs, Math.round((Float.valueOf(sxrs)/Float.valueOf(bysrs))*100)+"%", wcfTytle));
					ws.addCell(new Label(12, row+bjs, Math.round((Float.valueOf(qyzs-djys)/Float.valueOf(bysrs))*100)+"%", wcfTytle));
				}
				
				row += bjs+1;
			}
			
			ExcelMB.mergeCells(ws, row, 0, 4);//�ϲ�ѧԺ
			ExcelMB.mergeCells(ws, row, 1, 4);//�ϲ�רҵ
			
			/*
			 * �ϼƲ������
			 */
			ws.mergeCells(0,row, 2, row);
			ws.addCell(new Label(0,row,"�ϼ�",wcfTytle));
			ws.addCell(new Label(3,row,jylhj.get("bysrs"),wcfTytle));
			ws.addCell(new Label(4,row,jylhj.get("qyzs"),wcfTytle));
			ws.addCell(new Label(5,row,jylhj.get("sxrs"),wcfTytle));
			ws.addCell(new Label(6,row,jylhj.get("cgrs"),wcfTytle));
			ws.addCell(new Label(7,row,jylhj.get("zyzy"),wcfTytle));
			ws.addCell(new Label(8,row,jylhj.get("zzcy"),wcfTytle));
			ws.addCell(new Label(9,row,jylhj.get("djys"),wcfTytle));
			ws.addCell(new Label(10,row,jylhj.get("qyl"),wcfTytle));
			ws.addCell(new Label(11,row,jylhj.get("sxl"),wcfTytle));
			ws.addCell(new Label(12,row,jylhj.get("jyl"),wcfTytle));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * ����б� 
	 * @return
	 */
	protected List<HashMap<String, String>> getNfList() {
		int year = Integer.parseInt(dao.getNow().substring(0, 4));
		String[] years = new String[10];
		
		int n = 0 ;
		for(int i = year-5; i<year+5 ;i++) {
			years[n++] = String.valueOf(i);
		}
		return DAO.getInstance().arrayToList(years, years);
	}
	
	
	/**
	 * ��ȡ��˲�ѯ��Ҫ�Զ�����ֶ�
	 * @param yhlx
	 * @param shjb
	 * @return String
	 * */
	protected void getCustomAudiColumn(HttpServletRequest request, String yhlx){
		StringBuilder sb = new StringBuilder();
		
		if (yhlx.equalsIgnoreCase("xy")) {
			sb.append(" (case when xxsh='ͨ��' then 'disabled' else '' end) disabled,");
			sb.append(" (case when xysh='ͨ��' then 'tgsh' when xysh='��ͨ��' then 'wtg' else '' end) bgcolor, ");
		}else if (yhlx.equalsIgnoreCase("fdy")){
			sb.append(" (case when xysh='ͨ��' then 'disabled' else '' end) disabled, ");
			sb.append(" (case when fdysh='ͨ��' then 'tgsh' when fdysh='��ͨ��' then 'wtg' else '' end) bgcolor, ");
		}else if (Base.isNull(yhlx)) {
			sb.append(" (case when shzt='ͨ��' then 'disabled' else '' end) disabled, ");
			sb.append(" (case when shzt='ͨ��' then 'tgsh' when shzt='��ͨ��' then 'wtg' else '' end) bgcolor, ");
		}else if ("stu".equals(yhlx)){
			sb.append(" (case when xysh='ͨ��' then 'disabled' else '' end) disabled,");
			sb.append(" (case when xysh='ͨ��' then 'tgsh' when xysh='��ͨ��' then 'wtg' else '' end) bgcolor, ");
		}else{
			sb.append(" '' disabled, ");
			sb.append(" (case when xxsh='ͨ��' then 'tgsh' when xxsh='��ͨ��' then 'wtg' else '' end) bgcolor, ");
		}
		
		request.setAttribute("clientColumns", sb.toString());
	}


	/**
	 * ��ҵ����disabled 
	 * @param request
	 * @param flg
	 */
	protected void setCustomAudiColumn(HttpServletRequest request, String flg){
		StringBuilder sb = new StringBuilder();
		String userType = (String) request.getSession().getAttribute("userType");
	
		if ("shzt".equals(flg)) {
			sb.append(" (case when shzt='ͨ��' or shzt='��ͨ��' then 'disabled' when shzt='�˻�' then 'th' else '' end) disabled, ");
		} else if ("xysh".equals(flg)) {
			sb.append(" (case when xxsh='ͨ��' or xxsh='��ͨ��' then 'disabled' else '' end) disabled, ");
			sb.append(" (case when xysh='ͨ��' or xysh='��ͨ��' then 'disabled'  when xysh='�˻�' then 'th' else '' end) isdis, ");
		} else if ("xxsh".equals(flg) || "adminsh".equals(flg) || "stush".equals(flg)) {
			sb.append("'' disabled,");
			sb.append(" (case when xxsh='ͨ��' or xxsh='��ͨ��' then 'disabled' when xxsh='�˻�' then 'th' else '' end) isdis, ");
		} else if ("jgcx".equals(flg)) {
			sb.append(" (case when xysh='ͨ��' or xysh='��ͨ��' then 'disabled' when xysh='�˻�' then 'th' else '' end) disabled, ");
		} else if ("byssh".equals(flg)){
		
			if ("xy".equalsIgnoreCase(userType)){
				sb.append("(case when shzt='ͨ��' or shzt='��ͨ��' then 'disabled' else '' end) disabled,");
			}  else {
				sb.append("'' disabled,");
			}
		}
		
		request.setAttribute("clientColumns", sb.toString());
	}
	
	
	
	/**
	 * ��������--ѧԺ��ҵ��ͳ�Ʊ�
	 * @param xydm
	 * @param os
	 */
	protected void printJylForXy(JyglForm model,OutputStream os) {
		
		String xydm = model.getXydm();
		String nf = model.getNf();
		StringBuilder title = new StringBuilder();
		
		title.append(Base.xxmc);
		title.append(nf);
		title.append("���Ժ��ҵ�������ͳ�Ʊ�");
		
		List<String[]> bksData = dao.getJylForXy("����",xydm, nf);//����������
		List<String[]> zksData = dao.getJylForXy("ר��",xydm, nf);//ר��������
		List<String[]> xjData = dao.getJylForXy(xydm, nf);//С������
		List<String[]> bzxjData = dao.getJylTjForXyByXl(xydm, nf);//����ר��С��
		String[] jylZj = dao.getJylTjForXy(xydm, nf);//�ܼ�
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("��Ժ��ҵ�������ͳ�Ʊ�", 0);
		
		try {
			excel.printTitle(ws, title.toString(), 20, 800);// ����
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			
			ws.mergeCells(0, 1, 3, 1);
			ws.addCell(new Label(0, 1, "��ֹ���ڣ�XXXX��X��X��", wcfTytle));
			
			//��ͷ��Ԫ��ϲ�
			ws.mergeCells(0, 2, 0, 3);//���
			ws.mergeCells(1, 2, 1, 3);//ϵ��
			ws.mergeCells(2, 2, 2, 3);//ѧ��
			ws.mergeCells(3, 2, 3, 3);//��ҵ������
			
			ws.mergeCells(4, 2, 12, 2);//��ҵ����ϲ�
			ws.mergeCells(13, 2, 14, 2);//ǩԼ����ϲ�
			ws.mergeCells(15, 2, 16, 2);//����ҵ����ϲ�
			ws.mergeCells(17, 2, 18, 2);//��Լ����ϲ�
			ws.mergeCells(19, 2, 19, 3);//��У����
			
			
			//��ͷ--��һ��
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "ϵ��", wcfTytle));
			ws.addCell(new Label(2, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(3, 2, "��ҵ������", wcfTytle));
			ws.addCell(new Label(4, 2, "��ҵ���", wcfTytle));
			ws.addCell(new Label(13, 2, "ǩԼ���", wcfTytle));
			ws.addCell(new Label(15, 2, "����ҵ���", wcfTytle));
			ws.addCell(new Label(17, 2, "��Լ���", wcfTytle));
			
			//��ͷ--�ڶ���
			ws.addCell(new Label(4, 3, "��ǩԼ", wcfTytle));
			ws.addCell(new Label(5, 3, "����", wcfTytle));
			ws.addCell(new Label(6, 3, "��ѧ", wcfTytle));
			ws.addCell(new Label(7, 3, "�����ҵ", wcfTytle));
			ws.addCell(new Label(8, 3, "����ҵ", wcfTytle));
			ws.addCell(new Label(9, 3, "�н��պ�", wcfTytle));
			ws.addCell(new Label(10, 3, "�ط���Ŀ", wcfTytle));
			ws.addCell(new Label(11, 3, "��ҵ����", wcfTytle));
			ws.addCell(new Label(12, 3, "��ҵ��", wcfTytle));
			ws.addCell(new Label(13, 3, "ǩԼ����������ѧ������Ŀ��", wcfTytle));
			ws.addCell(new Label(14, 3, "ǩԼ��", wcfTytle));
			ws.addCell(new Label(15, 3, "����", wcfTytle));
			ws.addCell(new Label(16, 3, "����ҵ��", wcfTytle));
			ws.addCell(new Label(17, 3, "����", wcfTytle));
			ws.addCell(new Label(18, 3, "����", wcfTytle));
			ws.addCell(new Label(19, 2, "��У����", wcfTytle));
			
			for (int i = 0 ; i < bksData.size(); i++) {
				int n = i==0 ? 0 : 2;
				
				for (int j = 0 ; j < bksData.get(i).length ; j++) {
					ws.addCell(new Label(j, i+4+n*i, bksData.get(i)[j], wcfTytle));
					ws.addCell(new Label(j, i+5+n*i, zksData.get(i)[j], wcfTytle));
					ws.addCell(new Label(j, i+6+n*i, xjData.get(i)[j], wcfTytle));
				}
			}
			
			ExcelMB.mergeCells(ws, bksData.size()*3+4, 0, 4);//��źϲ�
			ExcelMB.mergeCells(ws, bksData.size()*3+4, 1, 4);//ѧԺ���ƺϲ�
			ws.mergeCells(0, bksData.size()*3+4, 1, bksData.size()*3+5);//С�ƺϲ�
			ws.mergeCells(0, bksData.size()*3+6, 2, bksData.size()*3+6);//С�ƺϲ�
			
			ws.addCell(new Label(0, bksData.size()*3+4, "С��", wcfTytle));
			ws.addCell(new Label(0, bksData.size()*3+6, "�ܼ�", wcfTytle));
			//����ר��С��
			for (int i = 0 ; i<bzxjData.size() ; i++) {
				for (int j = 0 ; j < bzxjData.get(i).length ; j++) {
					ws.addCell(new Label(j+2, bksData.size()*3+4+i, bzxjData.get(i)[j], wcfTytle));
				}
			}
			
			//�ܼƲ�������
			for (int i = 0 ; i < jylZj.length ; i++) {
				ws.addCell(new Label(3+i, bksData.size()*3+6, jylZj[i], wcfTytle));
			}
			
			wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			ws.mergeCells(0,  bksData.size()*3+7, 19, bksData.size()*3+9);
			ws.addCell(new Label(0, bksData.size()*3+7, model.getXmbz(), wcfTytle));
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * ��������--רҵ��ҵ��ͳ�Ʊ�
	 * @param xydm
	 * @param os
	 */
	protected void printJylForZy(JyglForm model,OutputStream os) {
		String nf = model.getNf();
		StringBuilder title = new StringBuilder();
		
		title.append(Base.xxmc);
		title.append(nf);
		title.append("��רҵ��ҵ�������ͳ�Ʊ�");
		
		List<String[]> xbbData = dao.getJylForZy("xbb","",model);//У��������
		List<String[]> rjxyData = dao.getJylForZy("rjxy","",model);//���ѧԺ����
		List<String[]> xbbXj = dao.getJylForZy(model,"xbb","");//У����С������
		List<String[]> rjxyXj = dao.getJylForZy(model,"rjxy","");//У����С������
		
		String[] xbbZjData = dao.getJylTjForZy(model,"xbb","");
		String[] zjData = dao.getJylTjForZy(model,"","");
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("רҵ��ҵ�������ͳ�Ʊ�", 0);
		
		try {
			excel.printTitle(ws, title.toString(), 20, 800);// ����
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			ws.mergeCells(0, 1, 3, 1);
			ws.addCell(new Label(0, 1, "��ֹ���ڣ�XXXX��X��X��", wcfTytle));
			
			//��ͷ��Ԫ��ϲ�
			ws.mergeCells(0, 2, 0, 3);//ϵ��
			ws.mergeCells(1, 2, 1, 3);//רҵ
			ws.mergeCells(2, 2, 2, 3);//ѧ��
			ws.mergeCells(3, 2, 3, 3);//רҵ����
			ws.mergeCells(19, 2, 19, 3);//��У����
			
			ws.mergeCells(4, 2, 12, 2);//��ҵ����ϲ�
			ws.mergeCells(13, 2, 14, 2);//ǩԼ����ϲ�
			ws.mergeCells(15, 2, 16, 2);//����ҵ����ϲ�
			ws.mergeCells(17, 2, 18, 2);//ΥԼ����ϲ�
			
			//��ͷ--��һ��
			ws.addCell(new Label(0, 2, "ϵ��", wcfTytle));
			ws.addCell(new Label(1, 2, "רҵ", wcfTytle));
			ws.addCell(new Label(2, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(3, 2, "רҵ��ҵ����", wcfTytle));
			ws.addCell(new Label(4, 2, "��ҵ���", wcfTytle));
			ws.addCell(new Label(13, 2, "ǩԼ���", wcfTytle));
			ws.addCell(new Label(15, 2, "����ҵ���", wcfTytle));
			ws.addCell(new Label(17, 2, "ΥԼ���", wcfTytle));
			ws.addCell(new Label(19, 2, "��У����", wcfTytle));
			//��ͷ--�ڶ���
			ws.addCell(new Label(4, 3, "��ǩԼ", wcfTytle));
			ws.addCell(new Label(5, 3, "����", wcfTytle));
			ws.addCell(new Label(6, 3, "��ѧ", wcfTytle));
			ws.addCell(new Label(7, 3, "�����ҵ", wcfTytle));
			ws.addCell(new Label(8, 3, "����ҵ", wcfTytle));
			ws.addCell(new Label(9, 3, "�н��к�", wcfTytle));
			ws.addCell(new Label(10, 3, "�ط���Ŀ", wcfTytle));
			ws.addCell(new Label(11, 3, "��ҵ����", wcfTytle));
			ws.addCell(new Label(12, 3, "��ҵ��", wcfTytle));
			ws.addCell(new Label(13, 3, "ǩԼ����������ѧ���ط���Ŀ��", wcfTytle));
			ws.addCell(new Label(14, 3, "ǩԼ��", wcfTytle));
			ws.addCell(new Label(15, 3, "����", wcfTytle));
			ws.addCell(new Label(16, 3, "����ҵ��", wcfTytle));
			ws.addCell(new Label(17, 3, "����", wcfTytle));
			ws.addCell(new Label(18, 3, "����", wcfTytle));
			
			int row = 0;
			int m = 0;
			int currRow = 0;
			
			/*
			 *У��������������漰С���֡�
			 *���ѧԺ��Ԫ��ĵ�ǰ��Ԫ����������һ��Ԫ������
			 *��һ���Ļ��ճ�һ�м���С�� 
			 */
			for (int i = 0 ; i < xbbData.size(); i++) {
				row=i+m;
				for (int j = 0 ; j < xbbData.get(i).length ; j++) {
					ws.addCell(new Label(j, row+4, xbbData.get(i)[j], wcfTytle));
				}
				
				String currCell = xbbData.get(i)[0];//��ǰ��Ԫ��
				String beforeCell = i<xbbData.size()-1 ? xbbData.get(i+1)[0] : "";//��һ��Ԫ��
				
				
				//С�Ʋ���
				if (!currCell.equals(beforeCell)) {
					
					for (int j = 0 ; j < xbbXj.get(m).length ; j++) {
						ws.addCell(new Label(j,row+5 , xbbXj.get(m)[j], wcfTytle));
					}
					m++;
				}
			}
			
			
			//У�����ϼ�
			ws.mergeCells(0, row+6, 1, row+6);//�ܼƺϲ�
			ws.addCell(new Label(0,row+6 , "�ܼ�", wcfTytle));
			ws.addCell(new Label(2,row+6 , " ", wcfTytle));
			
			//�ܼƲ�������
			for (int i = 0 ; i < xbbZjData.length ; i++) {
				ws.addCell(new Label(3+i, row+6, xbbZjData[i], wcfTytle));
			}
			
			//��ǰ��
			currRow = row+7;
			
			/*
			 *���ѧԺ������� 
			 */
			m = 0;
			int count = 0;
			
			if (null != rjxyData && rjxyData.size() > 0) {
				
				for (int i = 0 ; i < rjxyData.size(); i++) {
					count = row+i+m;
					for (int j = 0 ; j < rjxyData.get(i).length ; j++) {
						ws.addCell(new Label(j, count+7, rjxyData.get(i)[j], wcfTytle));
					}
					
					String currCell = rjxyData.get(i)[0];//��ǰ��Ԫ��
					String beforeCell = i<rjxyData.size()-1 ? rjxyData.get(i+1)[0] : "";//��һ��Ԫ��
					
					//С�Ʋ���
					if (!currCell.equals(beforeCell)) {
						
						for (int j = 0 ; j < rjxyXj.get(m).length ; j++) {
							ws.addCell(new Label(j,count+8 , rjxyXj.get(m)[j], wcfTytle));
						}
						m++;
					}
				}
				
				currRow = count+9;
				
			}
			
			ws.mergeCells(0, currRow, 1, currRow);//�ܼƺϲ�
			ws.addCell(new Label(0,currRow , "�ܼ�", wcfTytle));
			ws.addCell(new Label(2,currRow , " ", wcfTytle));
			
			//�ܼƲ�������
			for (int i = 0 ; i < zjData.length ; i++) {
				ws.addCell(new Label(3+i, currRow, zjData[i], wcfTytle));
			}
			
			 wcfTytle = ExcelMB.getWritableCellFormat(8,
						false, Alignment.LEFT, VerticalAlignment.CENTRE,
						Border.ALL);// ��Ԫ����ʽ
			ws.mergeCells(0, currRow+1, 19, currRow+3);
			ws.addCell(new Label(0,currRow+1,model.getXmbz(),wcfTytle));
			
			
			ExcelMB.mergeCells(ws, currRow+1, 0, 3);//ѧԺ�ϲ�
			ExcelMB.mergeCells(ws, currRow+1, 1, 3);//רҵ���ƺϲ�
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * ��������--�༶��ҵ��ͳ�Ʊ�
	 * @param xydm
	 * @param os
	 */
	protected void printJylForBj(JyglForm model,OutputStream os) {
		String nf = model.getNf();
		StringBuilder title = new StringBuilder();
		
		title.append(Base.xxmc);
		title.append(nf);
		title.append("��༶��ҵ��ͳ�ƻ��ܱ�");
		
		List<String[]> xbbData = dao.getJylForBj("xbb",model);//У����
		List<String[]> rjxyData = dao.getJylForBj("rjxy",model);//���ѧԺ
		String[] xbbZjData = dao.getJylTjForZy(model,"xbb","");
		String[] zjData = dao.getJylTjForZy(model,"","");
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("�༶��ҵ��ͳ�ƻ��ܱ�", 0);
		
		try {
			excel.printTitle(ws, title.toString(), 21, 800);// ����
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			
			ws.mergeCells(0, 1, 3, 1);
			ws.addCell(new Label(0, 1, "��ֹ���ڣ�XXXX��X��X��", wcfTytle));
			
			//��ͷ��Ԫ��ϲ�
			ws.mergeCells(0, 2, 0, 3);//ϵ��
			ws.mergeCells(1, 2, 1, 3);//רҵ
			ws.mergeCells(2, 2, 2, 3);//�༶
			
			ws.mergeCells(3, 2, 3, 3);//ѧ��
			ws.mergeCells(4, 2, 4, 3);//רҵ����
			ws.mergeCells(20, 2, 20, 3);//��У����
			
			ws.mergeCells(5, 2, 13, 2);//��ҵ����ϲ�
			ws.mergeCells(14, 2, 15, 2);//ǩԼ����ϲ�
			ws.mergeCells(16, 2, 17, 2);//����ҵ����ϲ�
			ws.mergeCells(18, 2, 19, 2);//ΥԼ����ϲ�
			
			//��ͷ--��һ��
			ws.addCell(new Label(0, 2, "ϵ��", wcfTytle));
			ws.addCell(new Label(1, 2, "רҵ", wcfTytle));
			ws.addCell(new Label(2, 2, "�༶", wcfTytle));
			
			ws.addCell(new Label(3, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(4, 2, "�༶��ҵ����", wcfTytle));
			ws.addCell(new Label(5, 2, "��ҵ���", wcfTytle));
			ws.addCell(new Label(14, 2, "ǩԼ���", wcfTytle));
			ws.addCell(new Label(16, 2, "����ҵ���", wcfTytle));
			ws.addCell(new Label(18, 2, "ΥԼ���", wcfTytle));
			ws.addCell(new Label(20, 2, "��У����", wcfTytle));
			//��ͷ--�ڶ���
			ws.addCell(new Label(5, 3, "��ǩԼ", wcfTytle));
			ws.addCell(new Label(6, 3, "����", wcfTytle));
			ws.addCell(new Label(7, 3, "��ѧ", wcfTytle));
			ws.addCell(new Label(8, 3, "�����ҵ", wcfTytle));
			ws.addCell(new Label(9, 3, "����ҵ", wcfTytle));
			ws.addCell(new Label(10, 3, "�н��к�", wcfTytle));
			ws.addCell(new Label(11, 3, "�ط���Ŀ", wcfTytle));
			ws.addCell(new Label(12, 3, "��ҵ����", wcfTytle));
			ws.addCell(new Label(13, 3, "��ҵ��", wcfTytle));
			ws.addCell(new Label(14, 3, "ǩԼ����������ѧ���ط���Ŀ��", wcfTytle));
			ws.addCell(new Label(15, 3, "ǩԼ��", wcfTytle));
			ws.addCell(new Label(16, 3, "����", wcfTytle));
			ws.addCell(new Label(17, 3, "����ҵ��", wcfTytle));
			ws.addCell(new Label(18, 3, "����", wcfTytle));
			ws.addCell(new Label(19, 3, "����", wcfTytle));
			
			
			
			int row = 0;
			int currRow = 0;
			
			/*
			 *У��������������漰С���֡�
			 *���ѧԺ��Ԫ��ĵ�ǰ��Ԫ����������һ��Ԫ������
			 *��һ���Ļ��ճ�һ�м���С�� 
			 */
			for (int i = 0 ; i < xbbData.size(); i++) {
				
				for (int j = 0 ; j < xbbData.get(i).length ; j++) {
					ws.addCell(new Label(j, row+4, xbbData.get(i)[j], wcfTytle));
				}
				
				row++;
			}
			
			//У�����ϼ�
			ws.mergeCells(0, row+4, 3, row+4);//�ܼƺϲ�
			ws.addCell(new Label(0,row+4 , "�ܼ�", wcfTytle));
			ws.addCell(new Label(2,row+4 , " ", wcfTytle));
			
			//�ܼƲ�������
			for (int i = 0 ; i < xbbZjData.length ; i++) {
				ws.addCell(new Label(4+i, row+4, xbbZjData[i], wcfTytle));
			}
			
			/*
			 *���ѧԺ������� 
			 */
			currRow = row + 5;
			
			if (null != rjxyData && rjxyData.size() > 0) {
				
				for (int i = 0 ; i < rjxyData.size(); i++) {
					for (int j = 0 ; j < rjxyData.get(i).length ; j++) {
						ws.addCell(new Label(j, currRow, rjxyData.get(i)[j], wcfTytle));
					}
					
					currRow++;
				}
				
				
				ws.mergeCells(0, currRow, 3, currRow);//�ܼƺϲ�
				ws.addCell(new Label(0,currRow , "�ܼ�", wcfTytle));
				ws.addCell(new Label(2,currRow , " ", wcfTytle));
				
				//�ܼƲ�������
				for (int i = 0 ; i < zjData.length ; i++) {
					ws.addCell(new Label(4+i, currRow, zjData[i], wcfTytle));
				}
				currRow++;
			}
			
			
			wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			ws.mergeCells(0, currRow, 20, currRow+2);
			ws.addCell(new Label(0, currRow, model.getXmbz(), wcfTytle));
			
			ExcelMB.mergeCells(ws, currRow, 0, 3);//ѧԺ�ϲ�
			ExcelMB.mergeCells(ws, currRow, 1, 3);//רҵ���ƺϲ�
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * ��������--��ҵ�ʻ���ͳ�Ʊ�
	 * @param xydm
	 * @param os
	 */
	protected void printJylHz(JyglForm model,OutputStream os) {
		String nf = model.getNf();
		StringBuilder title = new StringBuilder();
		
		title.append(Base.xxmc);
		title.append(nf);
		title.append("���ҵ�������ͳ���ܱ�");
		
		String[] zjData = dao.getJylTjForZy(model,"","");
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("��ҵ�������ͳ���ܱ�", 0);
		
		try {
			excel.printTitle(ws, title.toString(),17, 800);// ����
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			ws.mergeCells(0, 1, 3, 1);
			ws.addCell(new Label(0, 1, "��ֹ���ڣ�XXXX��X��X��", wcfTytle));
			
			//��ͷ��Ԫ��ϲ�
			ws.mergeCells(0, 2, 0, 3);//��ҵ����
			ws.mergeCells(16, 2, 16, 3);//��У����
			
			ws.mergeCells(1, 2, 9, 2);//��ҵ����ϲ�
			ws.mergeCells(10, 2, 11,2);//ǩԼ����ϲ�
			ws.mergeCells(12, 2, 13, 2);//����ҵ����ϲ�
			ws.mergeCells(14, 2, 15, 2);//ΥԼ����ϲ�
			
			//��ͷ--��һ��
			ws.addCell(new Label(0, 2, "��ҵ������", wcfTytle));
			ws.addCell(new Label(1, 2, "��ҵ���", wcfTytle));
			ws.addCell(new Label(10, 2, "ǩԼ���", wcfTytle));
			ws.addCell(new Label(12, 2, "����ҵ���", wcfTytle));
			ws.addCell(new Label(14, 2, "ΥԼ���", wcfTytle));
			ws.addCell(new Label(16, 2, "��У����ѧ����", wcfTytle));
			//��ͷ--�ڶ���
			ws.addCell(new Label(1, 3, "��ǩԼ", wcfTytle));
			ws.addCell(new Label(2, 3, "����", wcfTytle));
			ws.addCell(new Label(3, 3, "��ѧ", wcfTytle));
			ws.addCell(new Label(4, 3, "�����ҵ", wcfTytle));
			ws.addCell(new Label(5, 3, "����ҵ", wcfTytle));
			ws.addCell(new Label(6, 3, "�н��к�", wcfTytle));
			ws.addCell(new Label(7, 3, "�ط���Ŀ", wcfTytle));
			ws.addCell(new Label(8, 3, "��ҵ����", wcfTytle));
			ws.addCell(new Label(9, 3, "��ҵ��", wcfTytle));
			ws.addCell(new Label(10, 3, "ǩԼ����������ѧ���ط���Ŀ��", wcfTytle));
			ws.addCell(new Label(11, 3, "ǩԼ��", wcfTytle));
			ws.addCell(new Label(12, 3, "����", wcfTytle));
			ws.addCell(new Label(13, 3, "����ҵ��", wcfTytle));
			ws.addCell(new Label(14, 3, "����", wcfTytle));
			ws.addCell(new Label(15, 3, "����", wcfTytle));
			
			//�ܼƲ�������
			for (int i = 0 ; i < zjData.length ; i++) {
				ws.addCell(new Label(i, 4, zjData[i], wcfTytle));
			}
			
			wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			ws.mergeCells(0, 5, 16, 7);
			ws.addCell(new Label(0, 5, model.getXmbz(), wcfTytle));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * ��������--��������ҵ��ͳ�Ʊ�
	 * @param xydm
	 * @param os
	 */
	protected void printJylForBks(JyglForm model,OutputStream os) {
		String nf = model.getNf();
		StringBuilder title = new StringBuilder();
		
		title.append(Base.xxmc);
		title.append(nf);
		title.append("�걾�ƾ�ҵ��ͳ�Ʊ�");
		
		List<String[]> bksXbbData = dao.getJylForZy("xbb","bks",model);//У��������������
		List<String[]> xjXbbData = dao.getJylForZy(model,"xbb","bks");//У����С������
		String[] zjXbbData = dao.getJylTjForZy(model,"xbb","bks");//У�����ܼ�
		
		List<String[]> bksRjxyData = dao.getJylForZy("rjxy","bks",model);//���ѧԺ����������
		List<String[]> xjRjxyData = dao.getJylForZy(model,"rjxy","bks");//���ѧԺС������
		String[] zjData = dao.getJylTjForZy(model,"rjxy","bks");//Ժ�ܼ�
		
		
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("���ƾ�ҵ��ͳ�Ʊ�", 0);
		
		try {
			excel.printTitle(ws, title.toString(), 20, 800);// ����
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			ws.mergeCells(0, 1, 3, 1);
			ws.addCell(new Label(0, 1, "��ֹ���ڣ�XXXX��X��X��", wcfTytle));
			
			//��ͷ��Ԫ��ϲ�
			ws.mergeCells(0, 2, 0, 3);//ϵ��
			ws.mergeCells(1, 2, 1, 3);//רҵ
			ws.mergeCells(2, 2, 2, 3);//ѧ��
			ws.mergeCells(3, 2, 3, 3);//רҵ����
			ws.mergeCells(19, 2, 19, 3);//��У����
			
			ws.mergeCells(4, 2, 12, 2);//��ҵ����ϲ�
			ws.mergeCells(13, 2, 14, 2);//ǩԼ����ϲ�
			ws.mergeCells(15, 2, 16, 2);//����ҵ����ϲ�
			ws.mergeCells(17, 2, 18, 2);//ΥԼ����ϲ�
			
			//��ͷ--��һ��
			ws.addCell(new Label(0, 2, "ϵ��", wcfTytle));
			ws.addCell(new Label(1, 2, "רҵ", wcfTytle));
			ws.addCell(new Label(2, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(3, 2, "רҵ��ҵ����", wcfTytle));
			ws.addCell(new Label(4, 2, "��ҵ���", wcfTytle));
			ws.addCell(new Label(13, 2, "ǩԼ���", wcfTytle));
			ws.addCell(new Label(15, 2, "����ҵ���", wcfTytle));
			ws.addCell(new Label(17, 2, "ΥԼ���", wcfTytle));
			ws.addCell(new Label(19, 2, "��У����ѧ����", wcfTytle));
			//��ͷ--�ڶ���
			ws.addCell(new Label(4, 3, "��ǩԼ", wcfTytle));
			ws.addCell(new Label(5, 3, "����", wcfTytle));
			ws.addCell(new Label(6, 3, "��ѧ", wcfTytle));
			ws.addCell(new Label(7, 3, "�����ҵ", wcfTytle));
			ws.addCell(new Label(8, 3, "����ҵ", wcfTytle));
			ws.addCell(new Label(9, 3, "�н��к�", wcfTytle));
			ws.addCell(new Label(10, 3, "�ط���Ŀ", wcfTytle));
			ws.addCell(new Label(11, 3, "��ҵ����", wcfTytle));
			ws.addCell(new Label(12, 3, "��ҵ��", wcfTytle));
			ws.addCell(new Label(13, 3, "ǩԼ����������ѧ���ط���Ŀ��", wcfTytle));
			ws.addCell(new Label(14, 3, "ǩԼ��", wcfTytle));
			ws.addCell(new Label(15, 3, "����", wcfTytle));
			ws.addCell(new Label(16, 3, "����ҵ��", wcfTytle));
			ws.addCell(new Label(17, 3, "����", wcfTytle));
			ws.addCell(new Label(18, 3, "����", wcfTytle));
			
			int row = 0;
			int m = 0;
			int currRow = 0 ;//��ǰ��
			
			for (int i = 0 ; i < bksXbbData.size(); i++) {
				
				row = i+m;
				
				for (int j = 0 ; j < bksXbbData.get(i).length ; j++) {
					ws.addCell(new Label(j, row+4, bksXbbData.get(i)[j], wcfTytle));
				}
				
				String currCell = bksXbbData.get(i)[0];//��ǰ��Ԫ��
				String beforeCell = i<bksXbbData.size()-1 ? bksXbbData.get(i+1)[0] : "";//��һ��Ԫ��
				
				//С�Ʋ���
				if (!currCell.equals(beforeCell)) {
					
					for (int j = 0 ; j < xjXbbData.get(m).length ; j++) {
						ws.addCell(new Label(j,row+5 , xjXbbData.get(m)[j], wcfTytle));
					}
					
					m++;
				}
			}
			
			ws.mergeCells(0, row+6, 2, row+6);//�ܼƺϲ�
			ws.addCell(new Label(0,row+6 , "�ܼ�", wcfTytle));
			ws.addCell(new Label(2,row+6 , " ", wcfTytle));
			
			//�ܼƲ�������
			for (int i = 0 ; i < zjXbbData.length ; i++) {
				ws.addCell(new Label(3+i, row+6, Base.isNull(zjXbbData[i]) ? "0" : zjXbbData[i], wcfTytle));
			}
			
			currRow = row + 7;
			m = 0;
			
			if (null != bksRjxyData && bksRjxyData.size() > 0) {
				
				for (int i = 0 ; i < bksRjxyData.size(); i++) {
					
					currRow+=m;
					
					for (int j = 0 ; j < bksRjxyData.get(i).length ; j++) {
						ws.addCell(new Label(j, currRow, bksRjxyData.get(i)[j], wcfTytle));
					}
					
					String currCell = bksRjxyData.get(i)[0];//��ǰ��Ԫ��
					String beforeCell = i<bksRjxyData.size()-1 ? bksRjxyData.get(i+1)[0] : "";//��һ��Ԫ��
					
					currRow++;
					
					//С�Ʋ���
					if (!currCell.equals(beforeCell)) {
						
						for (int j = 0 ; j < xjRjxyData.get(m).length ; j++) {
							ws.addCell(new Label(j,currRow , xjRjxyData.get(m)[j], wcfTytle));
						}
						
						m++;
						currRow++;
					}
					
				}
				
				ws.mergeCells(0, currRow, 2, currRow);//�ܼƺϲ�
				ws.addCell(new Label(0,currRow , "�ܼ�", wcfTytle));
				ws.addCell(new Label(2,currRow , " ", wcfTytle));
				
				//�ܼƲ�������
				for (int i = 0 ; i < zjData.length ; i++) {
					ws.addCell(new Label(3+i, currRow, Base.isNull(zjData[i]) ? "0" : zjData[i], wcfTytle));
				}
				
			}
			
			wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			ws.mergeCells(0, currRow+1, 19, currRow+3);
			ws.addCell(new Label(0,currRow+1 , model.getXmbz(), wcfTytle));
			
			ExcelMB.mergeCells(ws, currRow, 0, 3);//ѧԺ�ϲ�
			ExcelMB.mergeCells(ws, currRow, 1, 3);//רҵ���ƺϲ�
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	

	/**
	 * ��������--ר������ҵ��ͳ�Ʊ�
	 * @param xydm
	 * @param os
	 */
	protected void printJylForZks(JyglForm model,OutputStream os) {
		String nf = model.getNf();
		StringBuilder title = new StringBuilder();
		
		title.append(Base.xxmc);
		title.append(nf);
		title.append("��ר�ƾ�ҵ��ͳ�Ʊ�");
		
		
		List<String[]> zksXbbData = dao.getJylForZy("xbb","zks",model);//У����ר��������
		List<String[]> xjXbbData = dao.getJylForZy(model,"xbb","zks");//У����С������
		String[] zjXbbData = dao.getJylTjForZy(model,"xbb","zks");//У�����ܼ�
		
		List<String[]> zksRjxyData = dao.getJylForZy("rjxy","zks",model);//���ѧԺר��������
		List<String[]> xjRjxyData = dao.getJylForZy(model,"rjxy","zks");//���ѧԺС������
		String[] zjData = dao.getJylTjForZy(model,"rjxy","zks");//Ժ�ܼ�
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("ר�ƾ�ҵ��ͳ�Ʊ�", 0);
		
		try {
			excel.printTitle(ws, title.toString(), 20, 800);// ����
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			ws.mergeCells(0, 1, 3, 1);
			ws.addCell(new Label(0, 1, "��ֹ���ڣ�XXXX��X��X��", wcfTytle));
			
			//��ͷ��Ԫ��ϲ�
			ws.mergeCells(0, 2, 0, 3);//ϵ��
			ws.mergeCells(1, 2, 1, 3);//רҵ
			ws.mergeCells(2, 2, 2, 3);//ѧ��
			ws.mergeCells(3, 2, 3, 3);//רҵ����
			ws.mergeCells(19, 2, 19, 3);//��У����
			
			ws.mergeCells(4, 2, 12, 2);//��ҵ����ϲ�
			ws.mergeCells(13, 2, 14, 2);//ǩԼ����ϲ�
			ws.mergeCells(15, 2, 16, 2);//����ҵ����ϲ�
			ws.mergeCells(17, 2, 18, 2);//ΥԼ����ϲ�
			
			//��ͷ--��һ��
			ws.addCell(new Label(0, 2, "ϵ��", wcfTytle));
			ws.addCell(new Label(1, 2, "רҵ", wcfTytle));
			ws.addCell(new Label(2, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(3, 2, "רҵ��ҵ����", wcfTytle));
			ws.addCell(new Label(4, 2, "��ҵ���", wcfTytle));
			ws.addCell(new Label(13, 2, "ǩԼ���", wcfTytle));
			ws.addCell(new Label(15, 2, "����ҵ���", wcfTytle));
			ws.addCell(new Label(17, 2, "ΥԼ���", wcfTytle));
			ws.addCell(new Label(19, 2, "��У����ѧ����", wcfTytle));
			//��ͷ--�ڶ���
			ws.addCell(new Label(4, 3, "��ǩԼ", wcfTytle));
			ws.addCell(new Label(5, 3, "����", wcfTytle));
			ws.addCell(new Label(6, 3, "��ѧ", wcfTytle));
			ws.addCell(new Label(7, 3, "�����ҵ", wcfTytle));
			ws.addCell(new Label(8, 3, "����ҵ", wcfTytle));
			ws.addCell(new Label(9, 3, "�н��к�", wcfTytle));
			ws.addCell(new Label(10, 3, "�ط���Ŀ", wcfTytle));
			ws.addCell(new Label(11, 3, "��ҵ����", wcfTytle));
			ws.addCell(new Label(12, 3, "��ҵ��", wcfTytle));
			ws.addCell(new Label(13, 3, "ǩԼ����������ѧ���ط���Ŀ��", wcfTytle));
			ws.addCell(new Label(14, 3, "ǩԼ��", wcfTytle));
			ws.addCell(new Label(15, 3, "����", wcfTytle));
			ws.addCell(new Label(16, 3, "����ҵ��", wcfTytle));
			ws.addCell(new Label(17, 3, "����", wcfTytle));
			ws.addCell(new Label(18, 3, "����", wcfTytle));
			
			int row = 0;
			int m = 0;
			int currRow = 0 ;//��ǰ��
			
			for (int i = 0 ; i < zksXbbData.size(); i++) {
				
				row = i+m;
				
				for (int j = 0 ; j < zksXbbData.get(i).length ; j++) {
					ws.addCell(new Label(j, row+4, zksXbbData.get(i)[j], wcfTytle));
				}
				
				String currCell = zksXbbData.get(i)[0];//��ǰ��Ԫ��
				String beforeCell = i<zksXbbData.size()-1 ? zksXbbData.get(i+1)[0] : "";//��һ��Ԫ��
				
				//С�Ʋ���
				if (!currCell.equals(beforeCell)) {
					
					for (int j = 0 ; j < xjXbbData.get(m).length ; j++) {
						ws.addCell(new Label(j,row+5 , xjXbbData.get(m)[j], wcfTytle));
					}
					
					m++;
				}
			}
			
			ws.mergeCells(0, row+6, 2, row+6);//�ܼƺϲ�
			ws.addCell(new Label(0,row+6 , "�ܼ�", wcfTytle));
			ws.addCell(new Label(2,row+6 , " ", wcfTytle));
			
			//�ܼƲ�������
			for (int i = 0 ; i < zjXbbData.length ; i++) {
				ws.addCell(new Label(3+i, row+6, Base.isNull(zjXbbData[i]) ? "0" : zjXbbData[i], wcfTytle));
			}
			
			currRow = row + 7;
			m = 0;
			
			if (null != zksRjxyData && zksRjxyData.size() > 0) {
				
				for (int i = 0 ; i < zksRjxyData.size(); i++) {
					
					currRow+=m;
					
					for (int j = 0 ; j < zksRjxyData.get(i).length ; j++) {
						ws.addCell(new Label(j, currRow, zksRjxyData.get(i)[j], wcfTytle));
					}
					
					currRow++;
					
					String currCell = zksRjxyData.get(i)[0];//��ǰ��Ԫ��
					String beforeCell = i<zksRjxyData.size()-1 ? zksRjxyData.get(i+1)[0] : "";//��һ��Ԫ��
					
					//С�Ʋ���
					if (!currCell.equals(beforeCell)) {
						
						for (int j = 0 ; j < xjRjxyData.get(m).length ; j++) {
							ws.addCell(new Label(j,currRow , xjRjxyData.get(m)[j], wcfTytle));
						}
						
						m++;
						currRow++;
					}
					
				}
				
				ws.mergeCells(0, currRow, 2, currRow);//�ܼƺϲ�
				ws.addCell(new Label(0,currRow , "�ܼ�", wcfTytle));
				ws.addCell(new Label(2,currRow , " ", wcfTytle));
				
				//�ܼƲ�������
				for (int i = 0 ; i < zjData.length ; i++) {
					ws.addCell(new Label(3+i, currRow, Base.isNull(zjData[i]) ? "0" : zjData[i], wcfTytle));
				}
				
			}
			
			//��ע����
			wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			ws.mergeCells(0, currRow+1, 19, currRow+3);
			ws.addCell(new Label(0,currRow+1 , model.getXmbz(), wcfTytle));
			
			ExcelMB.mergeCells(ws, currRow, 0, 3);//ѧԺ�ϲ�
			ExcelMB.mergeCells(ws, currRow, 1, 3);//רҵ���ƺϲ�
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * �������ݺ��Զ����ֶ�����
	 * 
	 * @return
	 */
	public boolean saveData(String realTable, String[] colList, String pkV,
			JyglForm model, HttpServletRequest request) throws Exception {

		String[] inputList = ModelToStrings.modelToStrings(colList, model);
		boolean updata = StandardOperation.insertNoLog(realTable, colList,
				inputList);

		if (updata) {
			//updata = new BdszDAO().updataZdyDdData(realTable, pkV, request);
		}
		return updata;
	}
	
	
	/**
	 * ��ѯ���Զ����ֶ�����
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getDataList(String tableName, JyglForm model,
			String[] colList, String[] zdyCol, String realTable, String[] pk)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getZdyQueryList(tableName, model, colList, zdyCol,
				realTable, pk);
	}
	
	
	/**
	 * ���º��Զ����ֶ�����
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean updateData(String realTable, String pk, JyglForm model,
			String pkValue, String newPkValue,String[] colList, HttpServletRequest request)
			throws Exception {

		String[] inputList = ModelToStrings.modelToStrings(colList, model);
		boolean updata = StandardOperation.updateNolog(realTable, colList,
				inputList, pk, pkValue) && new BdszDAO().delZdyData(realTable, pkValue);

		if (updata) {
			//updata = new BdszDAO().updataZdyDdData(realTable, newPkValue, request);
		}
		return updata;
	}


	/**
	 * �������������ϱ�
	 * @param pkValues
	 * @return
	 * @throws SQLException
	 */
	protected boolean dahkPlsb(String[] pkValues) throws SQLException {
		
		if (null != pkValues && pkValues.length>0) {
			return dao.dahkPlsb(pkValues);
		} else {
			return false;
		}
	}


	/**
	 * �������б�
	 * @param shenId
	 * @return
	 */
	protected List<HashMap<String, String>> getShiList(String shenId){
		
		shenId = Base.isNull(shenId) ? "110000" : shenId;
		
		return new StuInfoDAO().getShiList(shenId).get("shiList");
	}
	
	
	/**
	 * �������б�
	 * @param shenId
	 * @return
	 */
	protected List<HashMap<String, String>> getXianList(String shenId){
		
		shenId = Base.isNull(shenId) ? "110000" : shenId;
		
		return new StuInfoDAO().getShiList(shenId).get("xianList");
	}


	/**
	 * ��ȡ�۲��ּܷ�����
	 * @return
	 */
	protected HashMap<String, String> getZcfPm(String xh, String xn, String xq){
		
		return dao.getZcfPm(xh, xn, xq);
	}
	
	
	/**
	 * �����ҵ��ͳ��
	 * @param model
	 * @param os
	 */
	protected void printYxbys(JyglForm model,OutputStream os) {
		
		String sqlb = model.getSqlb();
		String xydm = model.getXydm();
		StringBuilder title = new StringBuilder();
		
		title.append("�㽭ʡ��ͨ�ߵ�ѧУ(");
		title.append(sqlb);
		title.append(")�����ҵ������");
		
		List<String[]> data = dao.getYxbysData(xydm, sqlb);
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("�����ҵ����ѡ", 0);
		
		try {
			excel.printTitle(ws, title.toString(), 7, 800);// ����
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			ws.mergeCells(0, 2, 6, 2);
			Base.YXPZXY_KEY = message.getMessage("lable.xb");
			ws.addCell(new Label(0,2 , Base.YXPZXY_KEY+"����(����)��", wcfTytle));
			
			wcfTytle.setAlignment(Alignment.CENTRE);
			ws.addCell(new Label(0,3 , "רҵ����", wcfTytle));
			ws.addCell(new Label(1,3 , "����", wcfTytle));
			ws.addCell(new Label(2,3 , "�Ա�", wcfTytle));
			ws.addCell(new Label(3,3 , "ѧ��", wcfTytle));
			ws.addCell(new Label(4,3 , "��Դ��", wcfTytle));
			ws.addCell(new Label(5,3 , "������ò", wcfTytle));
			ws.addCell(new Label(6,3 , "���֤��", wcfTytle));
			
			for (int i = 0 ; i < data.size() ; i++) {
				for (int j = 0 ;j < data.get(i).length ;j++) {
					ws.addCell(new Label(j,4+i , data.get(i)[j], wcfTytle));
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * ��ȡ������ȫ����
	 * @param tableName
	 * @return
	 */
	public String[] getColumn(String tableName) {
		
		return dao.getColumn(tableName);
	}
	
	
	/**
	 * ��ȡָ�����ָ����
	 * @param realTable
	 * @param colList
	 * @return
	 */
	public String[] getColumn(String realTable,String[] colList){
		
		return dao.getColumnNameCN(colList, realTable);
	}
	

	/**
	 * ��������-����Ա�����ҵ�����ϸ
	 */
	protected void printFdyJymx(JyglForm model,OutputStream os) {
		
		StringBuilder title = new StringBuilder();
		List<String[]> data = dao.getFdyJymx(model,"xbb");
		List<String[]> rjxyData = dao.getFdyJymx(model,"rjxy");
		
		
		title.append(Base.xxmc)
			 .append(model.getNf())
			 .append("���ҵ�ศ��Ա�����ҵ�����ϸ��");
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("��ҵ�����ϸ��", 0);
		
		try {
			excel.printTitle(ws, title.toString(), 19, 800);// ����
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			ws.mergeCells(0, 1, 3, 1);
			ws.addCell(new Label(0, 1, "��ֹ���ڣ�XXXX��X��X��", wcfTytle));
			
			//��ͷ��Ԫ��ϲ�
			ws.mergeCells(0, 2, 0, 3);//���
			ws.mergeCells(1, 2, 1, 3);//����
			ws.mergeCells(2, 2, 2, 3);//��������
			ws.mergeCells(3, 2, 11, 2);//��ҵ���
			ws.mergeCells(12,2, 13, 2);//ǩԼ���
			ws.mergeCells(14,2, 15, 2);//����ҵ���
			ws.mergeCells(16,2, 17, 2);//��Լ���
			ws.mergeCells(18,2, 18, 3);//��У����
			
			//��ͷ--��һ��
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "����", wcfTytle));
			ws.addCell(new Label(2, 2, "��������", wcfTytle));
			ws.addCell(new Label(3, 2, "��ҵ���", wcfTytle));
			ws.addCell(new Label(12, 2, "ǩԼ���������ѧ�����ҵط���Ŀ��", wcfTytle));
			ws.addCell(new Label(14, 2, "����ҵ���", wcfTytle));
			ws.addCell(new Label(16, 2, "��Լ���", wcfTytle));
			ws.addCell(new Label(18, 2, "��У����", wcfTytle));
			
			//��ͷ--�ڶ���
			ws.addCell(new Label(3, 3, "��ǩԼ", wcfTytle));
			ws.addCell(new Label(4, 3, "����", wcfTytle));
			ws.addCell(new Label(5, 3, "��ѧ", wcfTytle));
			ws.addCell(new Label(6, 3, "�����ҵ", wcfTytle));
			ws.addCell(new Label(7, 3, "����ҵ", wcfTytle));
			ws.addCell(new Label(8, 3, "�н��պ�", wcfTytle));
			ws.addCell(new Label(9, 3, "�ط���Ŀ", wcfTytle));
			ws.addCell(new Label(10, 3, "��ҵ����", wcfTytle));
			ws.addCell(new Label(11, 3, "��ҵ��", wcfTytle));
			ws.addCell(new Label(12, 3, "����", wcfTytle));
			ws.addCell(new Label(13, 3, "����", wcfTytle));
			ws.addCell(new Label(14, 3, "����", wcfTytle));
			ws.addCell(new Label(15, 3, "����ҵ��", wcfTytle));
			ws.addCell(new Label(16, 3, "����", wcfTytle));
			ws.addCell(new Label(17, 3, "����", wcfTytle));
			
			double xbbDbzs = 0;//У������������
			double yqrZs = 0;//��ǩԼ����
			double cgZs = 0;//��������
			double sxZs = 0;//��ѧ����	
			double yxjyZs = 0 ;//�����ҵ����
			double lhjyZs = 0 ;//����ҵ����
			double yjshZs = 0 ;//�н��պ�����
			double dfxmZs = 0 ;//�ط���Ŀ����
			double jyZs = 0 ;//��ҵ����
			double qyZs = 0 ;//ǩԼ����
			double djyZs = 0 ;//����ҵ����
			double jiyZs = 0 ;//��Լ����
			double lxdkZs = 0 ;//��У��������
			
			
			//У�����������
			for (int i = 0 ; i < data.size() ; i++) {
				ws.addCell(new Label(0, i+4, String.valueOf(i+1), wcfTytle));
				
				xbbDbzs += Integer.valueOf(data.get(i)[1]);
				yqrZs += Integer.valueOf(data.get(i)[2]);
				cgZs += Integer.valueOf(data.get(i)[3]);
				sxZs += Integer.valueOf(data.get(i)[4]);
				yxjyZs += Integer.valueOf(data.get(i)[5]);
				lhjyZs += Integer.valueOf(data.get(i)[6]);
				yjshZs += Integer.valueOf(data.get(i)[7]);
				dfxmZs += Integer.valueOf(data.get(i)[8]);
				jyZs += Integer.valueOf(data.get(i)[9]);
				qyZs += Integer.valueOf(data.get(i)[11]);
				djyZs += Integer.valueOf(data.get(i)[13]);
				jiyZs += Integer.valueOf(data.get(i)[15]);
				lxdkZs += Integer.valueOf(data.get(i)[17]);
				
				
				for (int j = 0 ; j < data.get(i).length ; j++) {
					ws.addCell(new Label(j+1, i+4,data.get(i)[j], wcfTytle));
				}
			}
			
			NumberFormat df = new DecimalFormat("0.00");
			
			//У�����ϼ�
			ws.mergeCells(0, data.size()+4, 1, data.size()+4);
			ws.addCell(new Label(0, data.size()+4, "У�����ϼ�", wcfTytle));
			ws.addCell(new Label(2, data.size()+4, String.valueOf(Math.round(xbbDbzs)), wcfTytle));
			ws.addCell(new Label(3, data.size()+4, String.valueOf(Math.round(yqrZs)), wcfTytle));
			ws.addCell(new Label(4, data.size()+4, String.valueOf(Math.round(cgZs)), wcfTytle));
			ws.addCell(new Label(5, data.size()+4, String.valueOf(Math.round(sxZs)), wcfTytle));
			ws.addCell(new Label(6, data.size()+4, String.valueOf(Math.round(yxjyZs)), wcfTytle));
			ws.addCell(new Label(7, data.size()+4, String.valueOf(Math.round(lhjyZs)), wcfTytle));
			ws.addCell(new Label(8, data.size()+4, String.valueOf(Math.round(yjshZs)), wcfTytle));
			ws.addCell(new Label(9, data.size()+4, String.valueOf(Math.round(dfxmZs)), wcfTytle));
			ws.addCell(new Label(10, data.size()+4, String.valueOf(Math.round(jyZs)), wcfTytle));
			ws.addCell(new Label(11, data.size()+4, df.format(jyZs/xbbDbzs*100)+"%", wcfTytle));
			ws.addCell(new Label(12, data.size()+4, String.valueOf(Math.round(qyZs)), wcfTytle));
			ws.addCell(new Label(13, data.size()+4, df.format(qyZs/xbbDbzs*100)+"%", wcfTytle));
			ws.addCell(new Label(14, data.size()+4, String.valueOf(Math.round(djyZs)), wcfTytle));
			ws.addCell(new Label(15, data.size()+4, df.format(djyZs/xbbDbzs*100)+"%", wcfTytle));
			ws.addCell(new Label(16, data.size()+4, String.valueOf(Math.round(jiyZs)), wcfTytle));
			ws.addCell(new Label(17, data.size()+4, df.format(jiyZs/xbbDbzs*100)+"%", wcfTytle));
			ws.addCell(new Label(18, data.size()+4, String.valueOf(Math.round(lxdkZs)), wcfTytle));
			
			double dbzsRjxy = 0;
			double yqrZsRjxy = 0;
			double cgZsRjxy = 0;
			double sxZsRjxy = 0;
			double yxjyZsRjxy = 0;
			double lhjyZsRjxy = 0;
			double yjshZsRjxy = 0;
			double dfxmZsRjxy = 0;
			double jyZsRjxy = 0;
			double qyZsRjxy = 0;
			double djyZsRjxy = 0;
			double jiyZsRjxy = 0;
			double lxdkZsRjxy = 0;
			
			//���ѧԺ�������
			for (int i = 0 ; i < rjxyData.size() ; i++) {
				ws.addCell(new Label(0, i+data.size()+5, String.valueOf(i+1), wcfTytle));
				
				dbzsRjxy += Integer.valueOf(rjxyData.get(i)[1]);
				yqrZsRjxy += Integer.valueOf(rjxyData.get(i)[2]);
				cgZsRjxy += Integer.valueOf(rjxyData.get(i)[3]);
				sxZsRjxy += Integer.valueOf(rjxyData.get(i)[4]);
				yxjyZsRjxy += Integer.valueOf(rjxyData.get(i)[5]);
				lhjyZsRjxy += Integer.valueOf(rjxyData.get(i)[6]);
				yjshZsRjxy += Integer.valueOf(rjxyData.get(i)[7]);
				dfxmZsRjxy += Integer.valueOf(rjxyData.get(i)[8]);
				jyZsRjxy += Integer.valueOf(rjxyData.get(i)[9]);
				qyZsRjxy += Integer.valueOf(rjxyData.get(i)[11]);
				djyZsRjxy += Integer.valueOf(rjxyData.get(i)[13]);
				jiyZsRjxy += Integer.valueOf(rjxyData.get(i)[15]);
				lxdkZsRjxy += Integer.valueOf(rjxyData.get(i)[17]);
				
				
				for (int j = 0 ; j < rjxyData.get(i).length ; j++) {
					ws.addCell(new Label(j+1, i+data.size()+5,rjxyData.get(i)[j], wcfTytle));
				}
			}
			
			
			//���ѧԺ�ϼ�
			ws.mergeCells(0, data.size()+rjxyData.size()+5, 1, data.size()+rjxyData.size()+5);
			ws.addCell(new Label(0, data.size()+rjxyData.size()+5, "���ѧԺ�ϼ�", wcfTytle));
			ws.addCell(new Label(2, data.size()+rjxyData.size()+5, String.valueOf(Math.round(dbzsRjxy)), wcfTytle));
			ws.addCell(new Label(3, data.size()+rjxyData.size()+5, String.valueOf(Math.round(yqrZsRjxy)), wcfTytle));
			ws.addCell(new Label(4, data.size()+rjxyData.size()+5, String.valueOf(Math.round(cgZsRjxy)), wcfTytle));
			ws.addCell(new Label(5, data.size()+rjxyData.size()+5, String.valueOf(Math.round(sxZsRjxy)), wcfTytle));
			ws.addCell(new Label(6, data.size()+rjxyData.size()+5, String.valueOf(Math.round(yxjyZsRjxy)), wcfTytle));
			ws.addCell(new Label(7, data.size()+rjxyData.size()+5, String.valueOf(Math.round(lhjyZsRjxy)), wcfTytle));
			ws.addCell(new Label(8, data.size()+rjxyData.size()+5, String.valueOf(Math.round(yjshZsRjxy)), wcfTytle));
			ws.addCell(new Label(9, data.size()+rjxyData.size()+5, String.valueOf(Math.round(dfxmZsRjxy)), wcfTytle));
			ws.addCell(new Label(10, data.size()+rjxyData.size()+5, String.valueOf(Math.round(jyZsRjxy)), wcfTytle));
			ws.addCell(new Label(11, data.size()+rjxyData.size()+5, df.format(jyZsRjxy/dbzsRjxy*100)+"%", wcfTytle));
			ws.addCell(new Label(12, data.size()+rjxyData.size()+5, String.valueOf(Math.round(qyZsRjxy)), wcfTytle));
			ws.addCell(new Label(13, data.size()+rjxyData.size()+5, df.format(qyZsRjxy/dbzsRjxy*100)+"%", wcfTytle));
			ws.addCell(new Label(14, data.size()+rjxyData.size()+5, String.valueOf(Math.round(djyZsRjxy)), wcfTytle));
			ws.addCell(new Label(15, data.size()+rjxyData.size()+5, df.format(djyZsRjxy/dbzsRjxy*100)+"%", wcfTytle));
			ws.addCell(new Label(16, data.size()+rjxyData.size()+5, String.valueOf(Math.round(jiyZsRjxy)), wcfTytle));
			ws.addCell(new Label(17, data.size()+rjxyData.size()+5, df.format(jiyZsRjxy/dbzsRjxy*100)+"%", wcfTytle));
			ws.addCell(new Label(18, data.size()+rjxyData.size()+5, String.valueOf(Math.round(lxdkZsRjxy)), wcfTytle));
			
			//�ܼ�
			ws.mergeCells(0, data.size()+rjxyData.size()+6, 1, data.size()+rjxyData.size()+6);
			ws.addCell(new Label(0, data.size()+rjxyData.size()+6, "�ܼ�", wcfTytle));
			ws.addCell(new Label(2, data.size()+rjxyData.size()+6, String.valueOf(Math.round(xbbDbzs)+Math.round(dbzsRjxy)), wcfTytle));
			ws.addCell(new Label(3, data.size()+rjxyData.size()+6, String.valueOf(Math.round(yqrZs)+Math.round(yqrZsRjxy)), wcfTytle));
			ws.addCell(new Label(4, data.size()+rjxyData.size()+6, String.valueOf(Math.round(cgZs)+Math.round(cgZsRjxy)), wcfTytle));
			ws.addCell(new Label(5, data.size()+rjxyData.size()+6, String.valueOf(Math.round(sxZs)+Math.round(sxZsRjxy)), wcfTytle));
			ws.addCell(new Label(6, data.size()+rjxyData.size()+6, String.valueOf(Math.round(yxjyZs)+Math.round(yxjyZsRjxy)), wcfTytle));
			ws.addCell(new Label(7, data.size()+rjxyData.size()+6, String.valueOf(Math.round(lhjyZs)+Math.round(lhjyZsRjxy)), wcfTytle));
			ws.addCell(new Label(8, data.size()+rjxyData.size()+6, String.valueOf(Math.round(yjshZs)+Math.round(yjshZsRjxy)), wcfTytle));
			ws.addCell(new Label(9, data.size()+rjxyData.size()+6, String.valueOf(Math.round(dfxmZs)+Math.round(dfxmZsRjxy)), wcfTytle));
			ws.addCell(new Label(10, data.size()+rjxyData.size()+6, String.valueOf(Math.round(jyZs)+Math.round(jyZsRjxy)), wcfTytle));
			ws.addCell(new Label(11, data.size()+rjxyData.size()+6, df.format((jyZs+jyZsRjxy)/(xbbDbzs+dbzsRjxy)*100)+"%", wcfTytle));
			ws.addCell(new Label(12, data.size()+rjxyData.size()+6, String.valueOf(Math.round(qyZs)+Math.round(qyZsRjxy)), wcfTytle));
			ws.addCell(new Label(13, data.size()+rjxyData.size()+6, df.format((qyZs+qyZsRjxy)/(xbbDbzs+dbzsRjxy)*100)+"%", wcfTytle));
			ws.addCell(new Label(14, data.size()+rjxyData.size()+6, String.valueOf(Math.round(djyZs)+Math.round(djyZsRjxy)), wcfTytle));
			ws.addCell(new Label(15, data.size()+rjxyData.size()+6, df.format((djyZs+djyZsRjxy)/(xbbDbzs+dbzsRjxy)*100)+"%", wcfTytle));
			ws.addCell(new Label(16, data.size()+rjxyData.size()+6, String.valueOf(Math.round(jiyZs)+Math.round(jiyZsRjxy)), wcfTytle));
			ws.addCell(new Label(17, data.size()+rjxyData.size()+6, df.format((jiyZs+jiyZsRjxy)/(xbbDbzs+dbzsRjxy)*100)+"%", wcfTytle));
			ws.addCell(new Label(18, data.size()+rjxyData.size()+6, String.valueOf(Math.round(lxdkZs)+Math.round(lxdkZsRjxy)), wcfTytle));
			//��ע
			wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			ws.mergeCells(0, data.size()+rjxyData.size()+7, 18, data.size()+rjxyData.size()+9);//��ע
			ws.addCell(new Label(0, data.size()+rjxyData.size()+7, model.getXmbz(), wcfTytle));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}


	/**
	 * �޸���Ŀ��ע
	 * @param xmdm
	 * @param xmbz
	 * @return
	 */
	protected boolean updateXmbz(String xmdm,String xmbz) {
		
		try {
			return dao.updateXmbz(xmdm, xmbz);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * ��λ���ͳ�Ʊ�
	 * @param model
	 * @param os
	 */
	protected void printDwlbTj(JyglForm model,OutputStream os) {
		
		String title = "��λ���ͳ�Ʊ�";
		
		List<HashMap<String, String>> dwxzList = dao.getDwxzList();
		String[] bkData = dao.getDwxzTj("����", dwxzList);
		String[] zkData = dao.getDwxzTj("ר��", dwxzList);
		
		double bksRs = Double.valueOf(dao.getBysRs("����"));
		double zksRs = Double.valueOf(dao.getBysRs("ר��"));
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		
		try {
			excel.printTitle(ws, title, dwxzList.size()+3, 800);// ����
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			ws.mergeCells(0, 1, 1, 1);
			ws.mergeCells(0, 2, 0, 3);
			ws.mergeCells(0, 4, 0, 5);
			ws.mergeCells(0, 6, 0, 7);
			
			ws.addCell(new Label(0, 1,"", wcfTytle));
			//���Ʋ���
			ws.addCell(new Label(0, 2,"����", wcfTytle));
			ws.addCell(new Label(1, 2,"����", wcfTytle));
			ws.addCell(new Label(1, 3,"�ٷֱ�", wcfTytle));
			
			//ר�Ʋ���
			ws.addCell(new Label(0, 4,"ר��", wcfTytle));
			ws.addCell(new Label(1, 4,"����", wcfTytle));
			ws.addCell(new Label(1, 5,"�ٷֱ�", wcfTytle));
			
			//�ϼƲ���
			ws.addCell(new Label(0, 6,"�ϼ�", wcfTytle));
			ws.addCell(new Label(1, 6,"����", wcfTytle));
			ws.addCell(new Label(1, 7,"�ٷֱ�", wcfTytle));
			
			DecimalFormat df = new DecimalFormat("#0.00");
			
			int bkzs = 0;//��������
			int zkzs = 0;//ר������
			for (int i = 0 ; i < dwxzList.size() ; i++) {
				
				int bks = Base.isNull(bkData[i]) ? 0 : Integer.valueOf(bkData[i]);
				int zks = Base.isNull(zkData[i]) ? 0 : Integer.valueOf(zkData[i]);
				
				bkzs+=bks;
				zkzs+=zks;
				
				//��λ�������
				ws.addCell(new Label(2+i, 1,dwxzList.get(i).get("dwxz"), wcfTytle));
				
				//�����������
				ws.addCell(new Label(2+i, 2,String.valueOf(bks), wcfTytle));
				
				//���ưٷֱ����
				ws.addCell(new Label(2+i, 3,df.format(bks/bksRs*100)+"%", wcfTytle));
				//ר���������
				ws.addCell(new Label(2+i, 4,String.valueOf(zks), wcfTytle));
				//ר�ưٷֱ����
				ws.addCell(new Label(2+i, 5,df.format(zks/zksRs*100)+"%", wcfTytle));
				//�ϼ��������
				ws.addCell(new Label(2+i, 6,String.valueOf(bks+zks), wcfTytle));
				//С�ưٷֱ����
				ws.addCell(new Label(2+i, 7,df.format((zks+bks)/(zksRs+bksRs)*100)+"%", wcfTytle));
			}
			
			ws.addCell(new Label(2+dwxzList.size(), 1,"�ϼ�", wcfTytle));
			ws.addCell(new Label(2+dwxzList.size(), 2,String.valueOf(bkzs), wcfTytle));
			ws.addCell(new Label(2+dwxzList.size(), 3,df.format(bkzs/bksRs*100)+"%", wcfTytle));
			ws.addCell(new Label(2+dwxzList.size(), 4,String.valueOf(zkzs), wcfTytle));
			ws.addCell(new Label(2+dwxzList.size(), 5,df.format(zkzs/bksRs*100)+"%", wcfTytle));
			ws.addCell(new Label(2+dwxzList.size(), 6,String.valueOf(bkzs+zkzs), wcfTytle));
			ws.addCell(new Label(2+dwxzList.size(), 7,df.format((bkzs+zkzs)/bksRs*100)+"%", wcfTytle));
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * ��ҵ����ͳ��
	 * @param model
	 * @param os
	 */
	protected void printHyflTj(JyglForm model, OutputStream os) {

		String title = "��λ��ҵ�ֲ������";

		List<HashMap<String, String>> hyflList = dao.getHyflList();
		String[] data = dao.getHyflTj(hyflList);

		double bysRs = Double.valueOf(dao.getBysRs(""));

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);

		try {
			excel.printTitle(ws, title, hyflList.size()+2, 800);// ����
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ

			NumberFormat df = new DecimalFormat("#0.00");
			
			ws.addCell(new Label(0, 1,"��λ��ҵ", wcfTytle));
			ws.addCell(new Label(0, 2,"����", wcfTytle));
			ws.addCell(new Label(0, 3,"�ٷֱ�", wcfTytle));
			
			int byzs = 0;
			
			for (int i = 0 ; i < hyflList.size() ; i++) {
				
				int bys = Base.isNull(data[i]) ? 0 : Integer.valueOf(data[i]);
				
				byzs += bys;
				
				ws.addCell(new Label(i+1, 1,hyflList.get(i).get("hyfl"), wcfTytle));
				ws.addCell(new Label(i+1, 2,data[i], wcfTytle));
				ws.addCell(new Label(i+1, 3,df.format(bys/bysRs*100)+"%", wcfTytle));
			}
			
			//�ϼ���
			ws.addCell(new Label(hyflList.size()+1, 1,"�ϼ�", wcfTytle));
			ws.addCell(new Label(hyflList.size()+1, 2,String.valueOf(byzs), wcfTytle));
			ws.addCell(new Label(hyflList.size()+1, 3,df.format(byzs/bysRs*100)+"%", wcfTytle));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	public List<HashMap<String, String>> getKsList(String lx){
		String[] en = null;
		String[] cn = null;
		
		if("sq".equalsIgnoreCase(lx)){
			en = new String[]{
				"jygl.do?method=byskssb&mk=ky",
				"jygl.do?method=byskssb&mk=kg"	
				};
			
			cn = new String[]{
					"��ҵ�������걨",
					"��ҵ��������Ա�걨"
					};
		}
		
		return DAO.getInstance().arrayToList(en, cn);
	}
	
	public List<HashMap<String, String>> getYzjgList(){
		String tableName = "jygl_zjkj_yzjgdmb";
		String[] output = new String[]{"xmdm", "xmmc"};
		return CommonQueryDAO.commonQueryforList(tableName, "", new String[]{}, output, "");
	}
	
	
	
	/**
	 * ��ҵЭ������ȡ����
	 * @param xh
	 * @param lqqk
	 * @param xysbh
	 * @return
	 */
	protected boolean saveJyxylq(String[] xh,String[] lqqk,String[] xysbh,String userName) {
		
		if (null != xh && xh.length>0) {
			try {
				return dao.saveJyxylq(xh, lqqk, xysbh,userName);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	/**
	 *��ȡ��ҵ����ģ�����������Ϣ 
	 * @return
	 */
	protected HashMap<String,String> getCssz(){
		
		return dao.getCssz();
	}
	
	
	/**
	 * ʣ��δʹ�ñ�ŵĸ���
	 * @return
	 */
	public String getSybh() {
		
		return dao.getSybh();
	}
	
	
	/**
	 * Э�����������
	 * @param pkValue
	 * @return
	 */
	protected boolean xysblsh(String[] pkValue,HashMap<String,String> valueMap) {
		
		try {
			if ("ͨ��".equals(valueMap.get("xxsh"))) {
				return dao.xysblsh(pkValue,valueMap);
			} else {
				return dao.xysblQxsh(pkValue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	/**
	 * Э���鲹�쵥����˺��޸�Э������ȡ�����
	 * @param xh
	 * @param xysbh
	 * @return
	 * @throws Exception
	 */
	public boolean updateXysbhOne(String xh, String xysbh) {
		
		try {
			return dao.updateXysbhOne(xh, xysbh);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * ��ȡ���ϱ���ҵ����
	 * @param yhm
	 * @param nd
	 * @return
	 */
	public String getYsbrsByNd(String yhm,String nd) {
		
		return dao.getYsbrsByNd(yhm, nd);
	}
	
	
	/**
	 * �������ó�ʼ��
	 *
	 */
	public void initCssz() {
		
		HashMap<String,String> cssz = dao.getCssz();
		
		if (Base.isNull(cssz.get("xxdm"))) {
			dao.initCssz();
		}
	}
	
	
	
	/**
	 * �����������ʱѧУ���״̬Ϊ���˻ء��ļ�¼Ϊ��������
	 * @param realTable
	 * @param pkValue
	 * @return
	 */
	public boolean resetXxsh(String realTable,String[] pkValue) {
		
		TableModel table = new BasicService().getTable(realTable);
		String pkKey = table.getPrimaryKey().replaceAll(",", "||");
		
		try {
			return dao.resetXxsh(realTable, pkKey, pkValue);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * ���ѧ���Ƿ������д��ҵЭ�飬
	 * �����ܷ�����ʾ��Ϣ
	 * @param pkValue
	 * @return
	 */
	public String getIsBys(String xh) {
		
		String message = "";
		HashMap<String,String> cssz = getCssz();
		
		if ("1".equals(cssz.get("lcbh"))) {
			HashMap<String,String> map = dao.getMapNotOut("select * from jy_bysxxb where shzt='ͨ��' and xh=?", new String[] {xh});
			
			if (map.isEmpty()) {
				message = "�Բ��������Ǳ�ҵ�����ҵ����Ϣδ�����ͨ����";
			}
		} else {
			HashMap<String,String> map = dao.getMapNotOut("select * from view_jygl_jyxyslqdjb where lqqk='����ȡ' and xh=?", new String[] {xh});
			
			if (map.isEmpty()) {
				message = "�Բ�������δ��ȡ��ҵЭ���飡";
			}
		}
		
		return message;
	}
	
	
	
	/**
	 * �������˵�λ����ȡ�����Ϣ
	 * @param yrdwdm
	 * @return
	 */
	public HashMap<String, String> getYrdw(String yrdwdm) {
		return dao.getYrdw(yrdwdm);
	}
	
	
	/**
	 * �������ܵ�λ�б�
	 * @param zgdwmc
	 * @return
	 */
	public List<HashMap<String, String>> getZgdwOption(String zgdwmc) {
		
		if (StringUtils.isNotNull(zgdwmc)){
			return dao.getZgdwOption(zgdwmc);
		} else{
			return dao.getZgdwOption();
		}
	}
	
	/**
	 * �������ܲ����б�
	 * @param zgdwmc
	 * @return
	 */
	public List<HashMap<String, String>> getLsbmOption(String zgdwmc) {
		
		if (StringUtils.isNotNull(zgdwmc)){
			return dao.getLsbmOption(zgdwmc);
		} else{
			return dao.getLsbmOption();
		}
	}


	
	/**
	 * ����ѧ�Ų�ѯ��ҵ��������Ϣ
	 * @param xh
	 * @return
	 */
	public HashMap<String,String> getBysxx(String xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nvl(yzbh,' ') yzbh,nvl(lxdz,' ') lxdz,nvl(zzmm,' ') zzmm,")
		   .append("nvl(lxdh,' ') lxdh,nvl(sjhm,' ') sjhm,nvl(dzyx,' ') dzyx,nvl(qq,' ') qq,")
		   .append("nvl(sydshen,' ') sydshen,nvl(sydshi,' ') sydshi,")
		   .append("nvl(sydxian,' ') sydxian from jy_bysxxb where xh=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}

	
	/**
	 * �Ƿ��ҵ����Ϣȷ��ʱ��
	 * @return
	 */
	public boolean getIsBysqrsj(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from jygl_csszb where sysdate ")
		   .append(" between to_date(bysqrkssj,'yyyymmdd') and to_date(bysqrjssj,'yyyymmdd')+1")
		   .append(" and xxdm=?");
		
		String count = dao.getOneRs(sql.toString(), new String[]{Base.xxdm}, "count");
		return "1".equals(count);
	}
	
	
	/**
	 * �Ƿ��ҵ����Ϣ���ʱ��
	 * @return
	 */
	public boolean getIsBysshsj(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from jygl_csszb where sysdate ")
		   .append(" between to_date(bysshkssj,'yyyymmdd') and to_date(bysshjssj,'yyyymmdd')+1")
		   .append(" and xxdm=?");
		
		String count = dao.getOneRs(sql.toString(), new String[]{Base.xxdm}, "count");
		return "1".equals(count);
	}
	
	/**
	 * ��ҵ��ȥ��
	 * @param zgdwmc
	 * @return
	 */
	public HashMap<String, String> getBysqx(String xh){

		return dao.getBysqx(xh);
	}
	
	/**
	 * ����ѧ�Ż�ȡ��չ����Ϣ
	 * @param xh
	 * @return
	 */
	public HashMap<String,String>getJyxyKzx(String xh){
		
		return dao.getJyxyKzx(xh);
	}
	
	/**
	 * ��ҵ����Ϣ �����ѯ 2012.1.10 qlj
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getXsxxList(JyglForm model) throws 
		IllegalArgumentException, SecurityException,
		IllegalAccessException, InvocationTargetException,
		NoSuchMethodException{

		return dao.getXsxxList(model);
	}
	
	/**
	 * �ϱ���ҵ����Ϣ  �����ѯ 2012.1.10 qlj
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getByssbList(JyglForm model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		return dao.getByssbList(model);
	}
	
	/**
	 * ��ҵ����Ϣȷ��  2012.1.10 qlj
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getByqrList(JyglForm model,String[]colArr) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		return dao.getByqrList(model, colArr);
	}
	
	/**
	 * ��ҵЭ�����  2012.1.17 qlj
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getJyxyShList(JyglForm model,String[]colArr) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		return dao.getJyxyShList(model, colArr);
	}
	
	/**
	 * ��ҵЭ���ѯ  2012.1.17 qlj
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getJyxycxList(JyglForm model,String[]colArr) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		return dao.getJyxycxList(model, colArr);
	}
	
	/**
	 * ��ȡѧУ���Ա�ҵ��ѧԺ�б�
	 * 
	 * @author honglin
	 * @date 2012-4-23
	 * @param xxdm
	 * @return
	 */
	public List<HashMap<String, String>> setXyList(String xxdm){
		String sql ="select xymc,xydm from (select xymc,xydm,xxdm from jy_bysxxb group by xymc,xydm,xxdm) where xxdm=?";
		return dao.getList(sql, new String[]{xxdm}, new String []{"xymc","xydm"});
	}
}
	
















