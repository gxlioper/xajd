package xgxt.pjpy.njjs;


import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import common.exception.SystemException;

import xgxt.DAO.ExcelMB;
import xgxt.audit.AuditUtil;
import xgxt.comm.CommService;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class XjbjService extends CommService{

	private XjbjDAO dao = new XjbjDAO();
	
	
	/**
	 * ���ݰ༶�����ȡ�����Ϣ
	 * DWR����
	 * @param bjdm
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String,String> getBjxxByBjdm(String bjdm) throws SQLException{
		
		HashMap<String,String> bjxx = new HashMap<String,String>();
		
		if (StringUtils.isNotNull(bjdm)){
			bjxx.put("bzxh", dao.getBzxhByBjdm(bjdm));//�೤ѧ��
			bjxx.put("bjrs", dao.getBjrsByBjdm(bjdm));//�༶����
			
			String[] bzr = dao.getBzrByBjdm(bjdm);
			if (null != bzr && bzr.length>0){
				bjxx.put("bzr", Arrays.toString(bzr).replace("[", "").replace("]", ""));//�༶����
			}
		}
		
		return bjxx;
	}
	
	
	/**
	 * �����Ƚ��༶�����Ϣ
	 * @param model
	 * @param gnmc
	 * @return
	 */
	public boolean saveXjbjbbsh(XjbjForm model,String gnmc){
		
		if (StringUtils.isNotNull(gnmc)){
			try {
				String[] spgw = AuditUtil.getSpgwByGnmc(gnmc);
				return dao.saveXjbjbbsh(model, spgw);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
		
		
		/**
		 * ����༯���ѯ
		 * @param model
		 * @param gnmc
		 * @return
		 * @throws Exception
		 */
		public List<String[]> queryXjbj(XjbjForm model,String[] colList,String[] gwmc,HttpServletRequest request) throws Exception{
			
			if (null != gwmc && gwmc.length>0){
				String[] tempShzt = new String[gwmc.length]; 
				
				User user = getUser(request);
				StringBuilder query = new StringBuilder();
				for (int i = 0 ; i < gwmc.length ; i++){
					String gwmcValue = request.getParameter(gwmc[i]);
					tempShzt[i] = gwmc[i]+":"+gwmcValue;
					
					if (StringUtils.isNotNull(gwmcValue)){
						query.append(" and ").append(gwmc[i]).append("='").append(gwmcValue).append("' ");
					}
				}
				request.setAttribute("tempShzt", Arrays.toString(tempShzt));
				return dao.queryXjbj(user,model,StringUtils.joinStrArr(colList,gwmc),gwmc,query.toString());
			} else {
				throw new SystemException("Error-1023");
			}
			
		}
		
		
		/**
		 * ���� �����б�
		 * @param lx
		 * @return
		 */
		public List<HashMap<String, String>> getSelectList(String lx) {
			String[] dm = null;
			String[] mc = null;
			
			if ("shjg".equalsIgnoreCase(lx)){
				dm = new String[] { "ͨ��", "�˻�","��ͨ��","������","δ���" };
				mc = dm;
			} else if ("shzt".equalsIgnoreCase(lx)){
				dm = new String[] { "ͨ��", "�˻�","��ͨ��" };
				mc = dm;
			}
			
			return dao.arrayToList(dm, mc);
		}
		
		
		/**
		 * ��������Ƚ��༯��
		 * @param model
		 * @return
		 * @throws SQLException 
		 */
		public boolean plshXjbjt(XjbjForm model,String[] pkValues,String gnmc) throws SQLException{
			
			String nextGwmc = "";
			String[] gwmc = AuditUtil.getGwmcByGnmc(gnmc);
			
			if (null != gwmc && gwmc.length>0){
				int index = StringUtils.getStrIndexInArray(model.getShgw(),gwmc);
				
				if (index > -1 && index != gwmc.length-1){
					nextGwmc = gwmc[index+1];//��һ��������λ
				}
			}
			
			if (null != pkValues && pkValues.length>0 && StringUtils.isNotNull(gnmc)){
				try {
					
					if (StringUtils.isNotNull(nextGwmc)  && "ͨ��".equals(model.getShjg())){
						//�������һ��������λΪ�˻ص���Ҫ����Ϊ��δ��ˡ�
						return dao.plshYxbjt(model, pkValues, gnmc) && dao.resetNextShzt(model, pkValues, gnmc, nextGwmc);
					} else {
						return dao.plshYxbjt(model, pkValues, gnmc) ;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
			
			return false;
		}
		
		
		
		/**
		 * �Ƚ��༶��˲�ѯ
		 * @param model
		 * @param colList
		 * @param gwmc ��ǰ������ϵ����и�λ
		 * @return
		 * @throws Exception
		 */
		public HashMap<String,Object> queryXjbjsh(User user,XjbjForm model,String[] colList,String[] topTr,String[] gwmc) throws Exception{
			
			String shgw = model.getShgw();
			String beforeGwmc="";
			String nextGwmc ="";
			int index = StringUtils.getStrIndexInArray(shgw,gwmc);
			
			if (index > 0){
				beforeGwmc = gwmc[index-1];
			}
			
			if (index > -1 && index != gwmc.length-1){
				nextGwmc = gwmc[index+1];//��һ��������λ
			}
			
			if (StringUtils.isNotNull(shgw)){
				colList = StringUtils.joinStrArr(colList,new String[]{shgw});
				topTr = StringUtils.joinStrArr(topTr,new String[]{shgw});
			}
			
			if (StringUtils.isNotNull(nextGwmc)){
				colList = StringUtils.joinStrArr(colList,new String[]{nextGwmc});
				topTr = StringUtils.joinStrArr(topTr,new String[]{nextGwmc});
			}
			
			List<String[]> result = dao.queryXjbjsh(user,model,colList, gwmc, beforeGwmc,nextGwmc);
			
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("result", result);
			map.put("topTr", topTr);
			return map;
		}
		
		
		
		/**
		 * �Ƚ��༶�����Ϣ
		 * @param pkValue
		 * @param gnmc
		 * @return
		 */
		public List<HashMap<String,String>> getXjbjShxx(String pkValue,String gnmc){
			if (StringUtils.isNotNull(pkValue) && StringUtils.isNotNull(gnmc)){
				return dao.getXjbjShxx(pkValue, gnmc);
			}
			
			return null;
		}
		
		
		/**
		 * �Ƚ��༶ɾ��
		 * @param pkValues
		 * @return
		 */
		public boolean delXjbj(String[] pkValues){
			
			if (null != pkValues && pkValues.length>0){
				try {
					return dao.delXjbj(pkValues);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return false;
		}
		
		
		/**
		 * ����༯���ӡ
		 * @param model
		 * @param os
		 */
		public void printXjbj(XjbjForm model,OutputStream os,String gnmc){
			
			String sqsj = model.getSqsj();
			List<String[]> data = dao.getXjbjPrintData(gnmc, sqsj);
			String temp = "";
			
			if (StringUtils.isNotNull(sqsj)){
				temp = sqsj.replace("-", "��");
			}
			
			ExcelMB excel = new ExcelMB();
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
			WritableSheet ws = wwb.createSheet("����༯��", 0);
			
			try {
				excel.printTitle(ws, temp+"������༯��", 4, 400);// ����
				WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
						false, Alignment.CENTRE, VerticalAlignment.CENTRE,
						Border.ALL);// ��Ԫ����ʽ
				
				
				ws.addCell(new Label(0, 1, "ϵ��", wcfTytle));
				ws.addCell(new Label(1, 1, "���", wcfTytle));
				ws.addCell(new Label(2, 1, "�༶", wcfTytle));
				ws.addCell(new Label(3, 1, "������", wcfTytle));
				
				for (int i = 0 ; i < data.size() ; i++){
					for (int j = 0 ; j < data.get(i).length; j++){
						ws.addCell(new Label(j, 2+i, data.get(i)[j], wcfTytle));
					}
				}
				
				ExcelMB.mergeCells(ws, data.size()+1, 0, 2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//��ͻ������
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}
		
}
