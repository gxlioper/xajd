package xgxt.jxgl.gzdx;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.jxgl.JxglTyForm;
import xgxt.jxgl.JxglTyService;
import xgxt.utils.CommonQueryDAO;

public class JxglGzdxService extends JxglTyService {

	JxglGzdxDAO dao = new JxglGzdxDAO();
	
	/**
	 * ����⻺ѵѧ�����������Ϣ
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getMhxSqInfo(JxglTyForm model) {

		HashMap<String, String> map = new HashMap<String, String>();

		// ѧ��
		String xh = model.getXh();
		// ѧ��
		String xn = model.getXn();
		//����
		String pkValue = xn + xh;
		// ѧ��������Ϣ
		map = getStuInfo(xh);

		map.put("xn", xn);
		map.put("save_xn", xn);
		map.put("save_xh", xh);

		HashMap<String, String> ldInfo = dao.getStuLdInfo(model);
		// ��������
		String ldmc = ldInfo.get("ldmc");
		// ָ����ʦ����
		String jsxm = ldInfo.get("jsxm");
		map.put("ldmc", ldmc);
		map.put("jsxm", jsxm);

		return map;
	}
	
	/**
	 * ��ü�����Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String getJgInfo(String jg) throws Exception {
		return dao.getJgInfo(jg);
	}
}
