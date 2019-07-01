package com.zfsoft.xgxt.rcsw.ylbx.ylbxsq;

import java.io.InputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

public class YlbxsqService extends SuperServiceImpl<YlbxsqForm, YlbxsqDao> {

	private YlbxsqDao dao = new YlbxsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";

	public YlbxsqService() {
		super.setDao(dao);
	}
	
	public HashMap<String, String> viewOneYlbxsq(String sqid){
		return dao.viewOneYlbxsq(sqid);
	}
	
	/**
	 * @����:����ҽ�Ʊ�������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveYlbxsq(YlbxsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		// ��ȡ��������
		String splc = dao.getShlcID();
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		if(SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//�����������
			result = shlc.runSubmit(model.getSqid(),splc,model.getXh(),"rcsw_ylbx_ylbxsh.do","rcsw_ylbx_ylbxsq.do");
		}
		return result;
	}
	
	/**
	 * @����:�޸�ҽ�Ʊ�������
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateYlbxsq(YlbxsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// ��ȡ����������
			model.setSplc(dao.getShlcID());
		}
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}
		
		boolean insertResult = super.runUpdate(model);
		boolean result = true;
		if (insertResult && SUBMIT.equals(model.getType())) {
			shlc.deleteShjl(model.getSqid());
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"rcsw_ylbx_ylbxsh.do","rcsw_ylbx_ylbxsq.do");
		}
		return insertResult && result;
	}
	
	/**
	 * @����:�ύҽ�Ʊ�������
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitYlbxsq(YlbxsqForm model) throws Exception {
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// ��ȡ����������
			model.setSplc(dao.getShlcID());
		}
		
		model.setShzt(Constants.YW_SHZ);
		boolean resultYlbxsq = dao.updateYlbxsq(model);
		boolean result = false;
		if(resultYlbxsq){
			//�����������
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"rcsw_ylbx_ylbxsh.do","rcsw_ylbx_ylbxsq.do");
		}
		return result;
	}
	
	/**
	 * @����:����ҽ�Ʊ�������
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelYlbxsq(YlbxsqForm model) throws Exception {
		boolean resultYlbxsq = dao.updateYlbxsq(model);
		return resultYlbxsq;
	}
	
	/**
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	/**
	 * @����:�Ƿ��Ѿ�����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean isExist(YlbxsqForm model) throws Exception {
		boolean flag = false;
		String num = dao.checkExistForSave(model);
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
	/** 
	 * ����
	 */
	public List<HashMap<String, String>> getExportAllList(YlbxsqForm t, User user)
			throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getExportAllList(t, user);
	}
	
	/**
	 * 
	 * @����: ȡ������е�ҽ�����ֶ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-5 ����03:08:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getYbh(String sqid) {		
		return dao.getYbh(sqid);
	}

	/** 
	 * @����:��ȡ��������list(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-14 ����04:00:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBxlxList() {
		return dao.getBxlxList();
	}

	/** 
	 * @����:ѧ����Ƭ����(��������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-15 ����10:29:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param response
	 * @param user
	 * void �������� 
	 * @throws 
	 */
	public void zpdc(List<Map<String, String>> list, HttpServletResponse response) {
		ZipOutputStream zos = null;
		DAO dao=DAO.getInstance();
		try {
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition",
					"attachment; filename=image.zip");
			zos = new ZipOutputStream(response.getOutputStream());

			for (int i = 0; i < list.size(); i++) {
				if (!Base.isNull(list.get(i).get("xh"))) {
					//��ѯ��ѧ����ƬBLOB
					Blob blob = getXszp(list.get(i).get("xh"), dao);
					if(null!=blob){
						InputStream in2 = blob.getBinaryStream();
						//��Ƭ����
						String zpmc=list.get(i).get("sfzh");
						if(StringUtil.isNull(zpmc)){
							zpmc = list.get(i).get("xh")+"_���������֤��";
						}
						//�����Ƭ��
						ZipEntry ze = new ZipEntry(zpmc + ".jpg");
						zos.putNextEntry(ze);
						zos.setEncoding("gbk"); //����������ʱ����ת�룬��������
						int blobsize = (int) blob.length();
						byte bu[] = new byte[blobsize];
						int bytesRead = 0;
						
						while ((bytesRead = in2.read(bu)) != -1) {
							zos.write(bu, 0, bytesRead);
						}
						in2.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				zos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private Blob getXszp(String xh,DAO dao){
			Blob blob=dao.getOneBlob("select zp from xszpb where xh=? ", new String[]{xh}, "zp");
			return blob;
	}

	/** 
	 * @����:��ȡ��Ƭ��ӡʱ��Ҫ����Ϣ(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-15 ����11:01:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param string
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getZpDyxx(String sqid) {
		return dao.getZpDyxx(sqid);
	}
}
