/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-24 ����10:42:31 
 */
package com.zfsoft.xgxt.xsxx.fbgl.fbjg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.export.excel.ExcelUtils;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglForm;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglService;
import com.zfsoft.xgxt.xsxx.fbgl.comm.BarSorce;
import com.zfsoft.xgxt.xsxx.fbgl.comm.ProgressBar;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxDao;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxService;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.bak.FbglXsxxBakService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-24 ����10:42:31
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglFbjgService extends FbglXsxxService {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	/* ������� */
	public static enum CheckType {
		none, success, fail,repeat
	}

	/**
	 * �������ѷְ��ѧ��ѧ����Ϣ
	 */
	public static final String _TJ_BCZYBXH = "0";
	/**
	 * �ύʧ��
	 */
	public static final String _TJ_TJSB = "-1";
	public static final String _TJ_TJCF = "2";//ѧ���ظ�
	/**
	 * �ύ�ɹ�
	 */
	public static final String _TJ_TJCG = "1";
	/**
	 * ���ύ
	 */
	public static final String _SFYTJ_YTJ = "ytj";
	/**
	 * δ�ύ
	 */
	public static final String _SFYTJ_WTJ = "wtj";
	/**
	 * ������key
	 */
	public static final String _BAR_KEY = "tjzsk";
	/**
	 * ���������ύ
	 */
	private static final String CZLX_TJ="0";
	/**
	 * �������ͳ���
	 */
	private static final String CZLX_CX="1";
	// Ĭ��excel���Ŀ¼
	public String errorPath = this.getClass().getResource("/").getPath()
			+ "/fbglerror.xls";

	FbglFbjgDao dao = new FbglFbjgDao();

	public FbglFbjgService() {
		this.setDao(dao);
	}

	@Override
	public List<HashMap<String, String>> getPageList(FbglXsxxForm t, User user)
			throws Exception {
		if (_SFYTJ_YTJ.equals(t.getTjzt())) {
			return dao.getPageListYtj(t, user);
		}
		return super.getPageList(t, user);
	}
	/**
	 * 
	 * @����: ��ȡ�꼶�б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-14 ����05:03:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getNjList() throws Exception {
		return dao.getNjList();
	}
	/**
	 * 
	 * @����: ��ȡ���ύ�꼶�б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-14 ����05:03:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getYtjNjList() throws Exception {
		return dao.getYtjNjList();
	}
	/**
	 * 
	 * @����: ��ȡѧ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-14 ����05:04:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param isTj �Ƿ������ύ��ѧ��
	 * @param nj   �꼶
	 * @return
	 * @throws SQLException
	 * int ��������
	 */
	public int getXsxxSl(boolean isTj,String nj) throws SQLException{
		return isTj?dao.getYtj(nj):dao.getWtj(nj);
	}
	/**
	 * 
	 * @����: ����ύ��ʽ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-25 ����02:56:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nj
	 * @return boolean ��������
	 */
	public boolean checkTj(String nj, List<HashMap<String, String>> bjlist,
			List<HashMap<String, String>> xslist) {
				Base.YXPZXY_KEY = message.getMessage("lable.xb");
		boolean isok = true;
		List<String[]> errorData = new ArrayList<String[]>();
		// check�༶
		String[] title = new String[] { "�г�ͻ�İ༶��" };
		errorData.add(title);
		errorData.add(new String[] { "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶����", "�༶����" });
		String col[];
		for (HashMap<String, String> hm : bjlist) {
			col = new String[5];
			if (checkBj(hm.get("bjdm"))) {
				putValue(col, hm, true);
				isok = false;
			}
			if (!isok) {
				errorData.add(col);
			}
			// ��ԭ��ʼֵ
			isok = true;
		}
		//������󼯺ϲ����ڰ༶������Ϣ�������
		if(errorData.size()==2){
			errorData.clear();
		}
		// checkѧ��
		title = new String[] { "�г�ͻ��ѧ�ţ�" };
		errorData.add(title);
		errorData.add(new String[] { "ѧ��", "������", "�꼶", "����", "�Ա�", Base.YXPZXY_KEY, "רҵ",
				"�༶" });
		for (HashMap<String, String> hm : xslist) {
			col = new String[8];
			// �Ƿ���ڴ�ѧ��ѧ��
			if (checkXh(hm.get("xh"))) {
				putValue(col, hm, false);
				isok = false;
			}
			if (!isok) {
				errorData.add(col);
			}
			// ��ԭ��ʼֵ
			isok = true;
		}
		if (errorData.size() > 2) {
			try {
				ExcelUtils.createExcel(errorData, errorPath);
			} catch (Exception e) {
				throw new RuntimeException("����excleʧ�ܣ�" + e.getMessage());
			}
		}
		return errorData.size() <= 2;
	}

	/**
	 * 
	 * @����: ������ʽ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-26 ����04:06:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nj
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean cxzsk(String nj,User user) throws Exception {
		String barKey = "cxzsk" + nj;
		boolean isok = false;
		isok = true;
		List<HashMap<String, String>> list = dao.getBjxxForNj(nj,
				FbglBbglService._SFYTJ_YTJ);
		List<HashMap<String, String>> xsxxlist = dao.getLsbXsxxInsertForNj(nj);
		// ��ʼ��������
		ProgressBar pb = BarSorce.initProgressBar(barKey, xsxxlist.size());
		int now = 1;

		FbglBbglService fbs = new FbglBbglService();
		FbglBbglForm fbf = new FbglBbglForm();

		for (HashMap<String, String> hm : list) {
			if (isok) {
				// ɾ����Ӧ�༶��Ϣ
				isok = dao.deleteBjxx(hm);
				// �޸�Ϊδ�ύ
				BeanUtils.copyProperties(fbf, hm);
				fbf.setSfytj(FbglBbglService._SFYTJ_WTJ);
				isok = fbs.runUpdate(fbf);
			} else {
				break;
			}
		}
		// ����ѧ��
		for (HashMap<String, String> hm : xsxxlist) {
			cxXssj(hm);
			// ���½���
			pb.change(now++);
		}
		//���������־
		dao.saveLog(nj,GetTime.getNowTime4(), CZLX_CX, user.getUserName());
		return isok;
	}

	/**
	 * 
	 * @����: �ύ�꼶��Ϣ����ʽ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-26 ����02:32:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nj
	 * @return boolean ��������
	 */
	public Map<String, String> tjzskForMessage(String nj,User user) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		CheckType ct = tjzsk(nj,user);
		switch (ct) {
		case none:
			// �������ѷְ��ѧ��ѧ����Ϣ
			map.put("message", _TJ_BCZYBXH);
			break;
		case fail:
			map.put("message", _TJ_TJSB);
			break;
		case repeat:
			map.put("message", _TJ_TJCF);
			break;
		default:
			map.put("message", _TJ_TJCG);
			break;
		}
		return map;
	}

	/**
	 * 
	 * @����: �ύ�꼶��Ϣ����ʽ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-26 ����02:32:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nj
	 * @return boolean ��������
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public CheckType tjzsk(String nj,User user) throws Exception {
		String barKey = _BAR_KEY + nj;
		boolean isok = false;

		List<HashMap<String, String>> list = dao.getBjxxForNj(nj,
				FbglBbglService._SFYTJ_WTJ);
		List<HashMap<String, String>> xsxxlist = dao.getXsxxForNj(nj);
		
		// ���ܴ��ڶ�Ӧ�ѷְ� ��ѧ�ŵ�ѧ��
		if (null == xsxxlist || xsxxlist.size() <= 0) {
			return CheckType.none;
		}
		List<String> checkList   =new ArrayList<String>();
		for (int i = 0; i < xsxxlist.size(); i++) {
			if(null==xsxxlist.get(i).get("xh")||!checkList.contains(xsxxlist.get(i).get("xh"))){
				checkList.add(xsxxlist.get(i).get("xh"));
			}
		}
		if(checkList.size()!=xsxxlist.size()){
			return CheckType.repeat;
		}
		// �Ƿ��г�ͻ
		if (checkTj(nj, list, xsxxlist)) {
			isok = true;
			// �ύ�༶
			// ��ʼ��������
			ProgressBar pb = BarSorce.initProgressBar(barKey,xsxxlist.size());
			FbglBbglService fbs = new FbglBbglService();
			FbglBbglForm fbf = new FbglBbglForm();
			for (HashMap<String, String> hm : list) {
				if (isok) {
					// ���浽��ʽ��
					isok = dao.saveBjxx(hm);
					// �޸�Ϊ�Ѿ��ύ
					BeanUtils.copyProperties(fbf, hm);
					fbf.setSfytj(FbglBbglService._SFYTJ_YTJ);
					isok = fbs.runUpdate(fbf);
				} else {
					break;
				}
			}
			// �ύѧ��
			for (HashMap<String, String> hm : xsxxlist) {
				tjXssj(hm);
				// ���½���
				pb.change();
			}
		} else {
			return CheckType.fail;
		}
		//���������־
		dao.saveLog(nj,GetTime.getNowTime4(), CZLX_TJ, user.getUserName());
		return CheckType.success;
	}

	/**
	 * 
	 * @����: �ύѧ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-25 ����02:54:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param hm
	 *            void ��������
	 */
	public void tjXssj(HashMap<String, String> hm) throws Exception {
		// xsxxbѧ����Ϣ��bks_bjdm�༶�����
		XsxxService xs = new XsxxService();
		XsxxForm xf = new XsxxForm();
		BeanUtils.copyProperties(xf, hm);
		// �ύѧ��
		xs.runInsert(xf);
		// ���ݵ�bak
		FbglXsxxBakService fxbs = new FbglXsxxBakService();
		FbglXsxxForm fxf = new FbglXsxxForm();
		BeanUtils.copyProperties(fxf, hm);
		fxbs.runInsert(fxf);
		// �޸�ѧ����ʱ��
		FbglXsxxService fxs = new FbglXsxxService();
		
		fxs.updateTjzt(fxf.getNj() + FbglXsxxDao._NJ_KSH_FGF
				+ fxf.getKsh(),"1");
	}

	/**
	 * 
	 * @����: �����ύ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-26 ����02:32:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param hm
	 *            void ��������
	 */
	public void cxXssj(HashMap<String, String> hm) throws Exception {
		// xsxxbѧ����Ϣ��bks_bjdm�༶�����
		XsxxService xs = new XsxxService();
		XsxxForm xf = new XsxxForm();
		FbglXsxxBakService fxbs = new FbglXsxxBakService();
		FbglXsxxForm fxf = new FbglXsxxForm();
		BeanUtils.copyProperties(xf, hm);
		// ����ѧ��
		xs.runDelete(new String[] { xf.getXh() });
		// ��������ѧ��ʱ���������ӵ�ѧ������
		// trigger xsmmcsh after insert on xsxxb for each row
		dao.deleteXsmm(xf.getXh());
		// ��������
		BeanUtils.copyProperties(fxf, hm);
		fxbs.runDelete(new String[] { fxf.getXh() });
		// ��ԭ����ʱ��
		FbglXsxxService fxs = new FbglXsxxService();
		
		fxs.updateTjzt(fxf.getNj() + FbglXsxxDao._NJ_KSH_FGF
				+ fxf.getKsh(),"0");
	}
	/**
	 * 
	 * @����: ���ö�Ӧֵ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-8 ����04:10:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param col
	 * @param hm
	 * @param isBj
	 * void ��������
	 */
	private void putValue(String[] col, HashMap<String, String> hm, boolean isBj) {
		//�༶��Ӧֵ
		if (isBj) {
			col[0] = hm.get("nj");
			col[1] = hm.get("xymc");
			col[2] = hm.get("zymc");
			col[3] = hm.get("bjdm");
			col[4] = hm.get("bjmc");
		} else {
			col[0] = hm.get("xh");
			col[1] = hm.get("ksh");
			col[2] = hm.get("nj");
			col[3] = hm.get("xm");
			col[4] = hm.get("xb");
			col[5] = hm.get("xymc");
			col[6] = hm.get("zymc");
			col[7] = hm.get("bjmc");
		}
	}

	/**
	 * 
	 * @����: ����Ƿ����ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-25 ����02:55:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return boolean ��������
	 */
	public boolean checkXh(String xh) {
		try {
			XsxxService xs = new XsxxService();
			XsxxForm xf = xs.getModel(xh);
			return null != xf;
		} catch (Exception e) {
			throw new RuntimeException("��ȡѧ����Ϣʧ�ܣ�" + e.getMessage());
		}

	}
	public void saveLog(String tjnj,String czsj,String czlx,String czr){
		
	}
	/**
	 * 
	 * @����: ����Ƿ���ڰ༶
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-25 ����02:55:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return boolean ��������
	 */
	public boolean checkBj(String bjdm) {
		List<HashMap<String, String>> list = dao.getBjxx(bjdm);
		return null != list && list.size() > 0;
	}
	/**
	 * 
	 * @����: ���س�ͻ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-25 ����02:55:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param response
	 *            void ��������
	 */
	public void download(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			File file = new File(errorPath);
			String path = file.getPath();
			//��ʱ�ļ�·��
			String fileName = path.substring(path.lastIndexOf("\\") + 1, path
					.length());
			//��ȡ
			InputStream is = new FileInputStream(file);
			byte[] b = new byte[2048];
			//������������
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			//���
			OutputStream os = response.getOutputStream();
			int len;
			while ((len = is.read(b)) > 0) {
				os.write(b, 0, len);
			}
			os.flush();
			os.close();
			is.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("�ļ�·�������޷���ȡ��", e);
		} catch (IOException e) {
			throw new RuntimeException("�ļ�����ת��Ϊ�����������", e);
		}
	}
}
