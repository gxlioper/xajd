/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-21 ����11:03:03 
 */  
package com.zfsoft.xgxt.rcsw.ylbx.ylbxjg;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
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
import xsgzgl.xsxx.general.xszpgl.XszpglForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.ylbx.ylbxsh.YlbxshForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ҽ�Ʊ��� 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2015-1-21 ����11:03:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YlbxjgService extends SuperServiceImpl<YlbxjgForm, YlbxjgDao> {
	
	private YlbxjgDao dao = new YlbxjgDao();
	public static String _BCZSCID="-1";
	
	public YlbxjgService() {
		super.setDao(dao);
	}
	public List<HashMap<String, String>> getXjbxmdList(YlbxjgForm t, User user) throws Exception{
		return dao.getXjbxmdList(t,user);
		
	}
	
	/**
	 * 
	 * @����:�жϸ�ѧ���������Ƿ��Ѿ����ڸ�ѧ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-1-22 ����04:45:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistBysqjg(YlbxjgForm model)
	throws Exception {
		boolean flag = false;
		// 
		String shzt = dao.checkExistForSave(model);
		if (!"0".equalsIgnoreCase(shzt)) {
			flag = true;
		}
		return flag;
	}
	
	
	/**
	 * 
	 * @����:�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-1-22 ����04:46:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	
	public boolean saveSqjg(YlbxjgForm model) throws Exception {
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}	
	
	/**
	 * 
	 * @����:������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-1-22 ����04:49:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateSqjg(YlbxjgForm model) throws Exception {
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}
	
	/**
	 * 
	 * @����:ɾ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-1-22 ����04:49:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] deleteSqjg(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//��¼ɾ��id
			}else{
				HashMap<String, String> hm=dao.getBbjg(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?sqjgDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @����:ɾ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-1-22 ����05:08:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	private int sqjgDelete(String[] ids) throws Exception {
		return runDelete(ids);
	}


	/**
	 * 
	 * @����:�鿴���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����11:51:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xszbbjgId
	 * @return
	 * @throws Exception
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> viewOneYlbxjgList(String jgId) throws Exception {
		return dao.viewOneYlbxjgList(jgId);
	}
	
	/** 
	 * ����
	 */
	public List<HashMap<String, String>> getExportAllList(YlbxjgForm t, User user)
			throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getExportAllList(t, user);
	}

	
	/**
	 * 
	 * @����: ȡҽ�ƽɷѵ����Թ����������ӡ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-10-26 ����10:40:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getJfdcRylbInfo(String xh) {
		
		return dao.getJfdcRylbInfo(xh); 
	}
	
	//��Ƭ����
	public void zpdc(YlbxjgForm model,HttpServletResponse response,User user)throws Exception{
		List<HashMap<String, String>> list = dao.getXszpList(model,user);
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
					Blob blob = getXszp(list.get(i).get("xh"),model.getZpType(), dao);
					if(null!=blob){
						InputStream in2 = blob.getBinaryStream();
						//��Ƭ����������ѡ��������˺�������Ӧ�ֶ�Ϊ������ѧ������)
						String[] PhotoNameTypes =model.getPhotoNameType().split("-");
						String zpmc=null;
						if(PhotoNameTypes.length!=1){
							zpmc = Base.isNull(list.get(i).get(PhotoNameTypes[0])) ? list.get(i).get("xh") : list.get(i).get("xm")+list.get(i).get(PhotoNameTypes[0]);
						}else{
							zpmc = Base.isNull(list.get(i).get(model.getPhotoNameType())) ? list.get(i).get("xh") : list.get(i).get(model.getPhotoNameType());
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
	
	private Blob getXszp(String xh,String zpType,DAO dao){
		
		Blob blob = null;
		if("xs_zs".equals(zpType)){//����������Ƭ�򵼳������򵼳�ѧ����Ƭ
			blob=dao.getOneBlob("select xszp from xszpb where xh=? and xszp is not null and nvl(length(xszp),'0')>0", new String[]{xh}, "xszp");
			if(blob==null){
				blob=dao.getOneBlob("select zp from xszpb where xh=? and zp is not null and nvl(length(zp),'0')>0", new String[]{xh}, "zp");
			}
			return blob;
		}
		return blob;
		
	}
}
