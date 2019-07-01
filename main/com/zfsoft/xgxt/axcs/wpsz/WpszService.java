/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-2 ����06:13:18 
 */
package com.zfsoft.xgxt.axcs.wpsz;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;

import xgxt.DAO.PicDAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ĳ��й���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2014-12-2 ����06:13:18
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class WpszService extends SuperServiceImpl<WpszForm, WpszDao> {

	public List<HashMap<String, String>> getPageList(WpszForm model) throws Exception {
		return super.getPageList(model);
	}

	/**
	 * 
	 * @����:��ȡ��Ʒ����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-3 ����03:58:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getWplbList() throws Exception {
		return dao.getWplbList();
	}
	
	/**
	 * 
	 * @����:��ȡ��Ʒ�����б�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-12-10 ����02:38:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getWpmcList(String xn) throws Exception {
		return dao.getWpmcList(xn);
	}
	
	public List<HashMap<String, String>> getTjpzList() throws Exception {
		return dao.getTjpzList();
	}

	/**
	 * @throws Exception
	 * 
	 * @����:�����Ʒ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-4 ����09:10:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return String ��������
	 * @throws
	 */
	public boolean addWp(WpszForm model) throws Exception {
		return dao.runInsert(model);
	}

	/**
	 * 
	 * @����:��Ʒ�������ñ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-4 ����10:58:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean bcWpJbsz(WpszForm model) throws Exception {
		if("0".equals(model.getSqkg())&&"0".equals(model.getShkg())){
			model.setJbsz("0");
		}
		else{
		   model.setJbsz("1");	
		}
		return dao.runUpdate(model);
	}
	
	public boolean bcWpTjsz(WpszForm model,HttpServletRequest request) throws Exception {
		CommDAO commDao = new CommDAO();
		String[] dcdm=request.getParameterValues("tjsz");
		String tjzt = null;
		if(dcdm==null){
			dcdm=new String[0];
			tjzt="0";
			
		}else{
			tjzt="1";
		}
		dao.updateWpTjZt(model.getXmdm(), tjzt);
		int tjlen=0;
		for(int i=0;i<dcdm.length;i++){
			String[] tjzdArray = request.getParameterValues("tjz_"+dcdm[i]);
			if(tjzdArray==null){
				tjzdArray=new String[0];
			}
			tjlen+=tjzdArray.length;
		}
		String[] sqls=new String[1+tjlen];
		sqls[0]="delete from XG_AXJZ_AXCSXMTJB where xmdm='"+model.getXmdm()+"'";
		String tjzdm=null;
		String tjid=null;
		int k=0;
		for(int i=0;i<dcdm.length;i++){
			String[] tjzdArray = request.getParameterValues("tjz_"+dcdm[i]);
			if(tjzdArray==null){
				tjzdArray=new String[0];
			}
			
			for (int j = 0; j < tjzdArray.length; j++) {
			    tjid = StringUtils.getGuid();
				tjzdm=Base.chgNull(tjzdArray[j],"",0);
				sqls[1+k]="insert into XG_AXJZ_AXCSXMTJB values('"+tjid+"','"+model.getXmdm()+"','"+dcdm[i]+"','"+tjzdm+"')";
				k++;
			}
			
		}
		boolean flag =commDao.saveArrDate(sqls);
		return flag;
	}

	/**
	 * 
	 * @����:��ƷͼƬ��ʾ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-3 ����04:47:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return InputStream ��������
	 * @throws
	 */
	public InputStream getPhotoStream(String xmdm) {
		if (StringUtil.isNull(xmdm)) {
			logger.error("xmdm is null !");
			throw new NullPointerException();
		}
		InputStream is = dao.getPhotoStream(xmdm);
		return is;
	}

	public String saveWpPic(WpszForm model, User user) {
		PicDAO picDao = new PicDAO();
		String xmdm = model.getXmdm();
		FormFile file = model.getXmtp();
		String fileName = file.getFileName();

		boolean isAllowType = fileName.endsWith("jpg") || fileName.endsWith("gif") || fileName.endsWith("png") || fileName.endsWith("bmp") || fileName.endsWith("JPG") || fileName.endsWith("GIF")
				|| fileName.endsWith("PNG") || fileName.endsWith("BMP");

		if (!isAllowType) {
			return "�ϴ�ʧ�ܣ��Ƿ����ļ���ʽ��";
		}
		if (file.getFileSize() > 1024 * 1024) {
			return "�ϴ�ʧ�ܣ��ļ���С����1M��";
		} else {
			try {
				picDao.saveWpPic(file.getInputStream(), xmdm);
				return "�ϴ��ɹ���";
			} catch (Exception e) {
				e.printStackTrace();
				return "�ϴ�ʧ�ܣ��������ϴ���";
			}
		}
	}

	public String getCurrTime(String dateFormat) {
		DateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(new Date());
	}
	/**
	 * �ж��Ƿ��������¼
	 */
	public boolean isHaveSqJl(String values) throws Exception {
		return dao.isHaveSqJl(values);
	}
	/**
	 * ��ȡ�ɸ���ѧ��
	 */
	public List<HashMap<String, String>> getFzXnList(String xn) {
		return dao.getFzXnList(xn);
	}
	/**
	 * 
	 * @����:������Ʒ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-5 ����01:44:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fzxn
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean wpFz(String fzxn) throws Exception {
		boolean flag = false;
		String currXn = Base.currXn; //��ǰѧ��
		List<HashMap<String, String>> wpList = dao.getKfzWpList(fzxn);
		if (wpList != null && wpList.size() > 0) {
			flag = dao.saveData(wpList, currXn);
		}
		return flag;
	}
	public HashMap<String,String> getWpxxByXmdm(String xmdm) throws Exception {
		return dao.getWpxxByXmdm(xmdm);
		
	}
	/**
	 * 
	 * @����:��ȡ��Ʒ����List
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-11 ����01:02:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getWpTjList(String xmdm) throws Exception {
		return dao.getWpTjList(xmdm);
		
	}
}
