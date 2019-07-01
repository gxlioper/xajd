
package xgxt.pjpy.shcbys.jxj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
public class JxjService {

	JxjDAO dao = null;
	
	/**
	 * ���ڲ�ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> kqyTitleResult() throws Exception {
		dao = new JxjDAO();
		String[] enList = new String[] { "pk", "rownum", "xn", "xq","xh", "xm", "xb",
				"nj", "bjmc", "kkcs", "zdcs", "ztcs" };
		String[] cnList = new String[]{"pk","�к�","ѧ��","ѧ��","ѧ��", "����",
				"�Ա�", "�꼶", "�༶����", "���δ���", "�ٵ�����", "���˴���"};
		return dao.getTitleResult(enList, cnList);
	}
	
	/**
	 * ������Ϣ��ѯ���
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> kqxxResult(JxjModel model) throws Exception {
		dao = new JxjDAO();
		return dao.kqxxResult(model);
	}
	
	/**
	 * ������ϸ��Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> kqxxDetails(String pkValue) throws Exception {
		dao = new JxjDAO();
		return dao.kqxxDetails(pkValue);
	}
	
	/**
	 * �����޸�
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean kqxxUpdate(String pkValue, JxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new JxjDAO();
		return dao.kqxxUpdate(pkValue, model, request);
	}
	
	/**
	 * ������Ϣ����ɾ��
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String kqxxDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new JxjDAO();
		return dao.kqxxDel(keys, request);
	}
	
	/**
	 * ѧ����ϸ��Ϣ
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuDetails(String xh) throws Exception {
		dao = new JxjDAO();
		return dao.getStuDetails(xh);
	}
	
	/**
	 * ѧ������
	 * @param xqdm
	 * @return
	 * @throws Exception
	 */
	public String getXqmc(String xqdm) throws Exception {
		dao = new JxjDAO();
		return dao.getXqmc(xqdm);
	}
	
	/**
	 * ѧ���ɼ�
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjList(String xh) throws Exception {
		dao = new JxjDAO();
		String jwflag = dao.getJwFlag();
		jwflag = StringUtils.isNull(jwflag) ? "" : jwflag.trim();//1ѧ��0����
		if ("1".equalsIgnoreCase(jwflag)) {
			return dao.getCjListByxf(xh);
		} else {
			return dao.getCjList(xh);
		}
	}
	
	/**
	 * ѧ���ɼ�
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjList(HashMap<String, String> map) throws Exception {
		dao = new JxjDAO();
		String jwflag = dao.getJwFlag();
		jwflag = StringUtils.isNull(jwflag) ? "" : jwflag.trim();//1ѧ��0����
		if ("1".equalsIgnoreCase(jwflag)) {
			return dao.getCjListByxf(map);
		} else {
			return dao.getCjList(map);
		}
	}
	
	/**
	 * ѧ���ɼ��㽭��
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjzjlgList(String xh) throws Exception {
		dao = new JxjDAO();
		String jwflag = dao.getJwFlag();
		jwflag = StringUtils.isNull(jwflag) ? "" : jwflag.trim();//1ѧ��0����
		if ("1".equalsIgnoreCase(jwflag)) {
			return dao.getCjListByxf(xh);
		} else {
			return dao.getCjzjlgList(xh);
		}
	}
	
	public HashMap<String, String> getJd(String xh) throws Exception {
		dao = new JxjDAO();
		return dao.getJd(xh);
	}
	
	/**
	 * ���ѧ���Ƿ���ϸý�ѧ����������
	 * @param xh
	 * @param jd
	 * @param jxjdm
	 * @return ѧ���ɼ�,�����ɼ�,������Ϣ,����ɼ�
	 * @throws Exception
	 */
	public String chkJxjsqtj(String xh, String jd, String jxjdm) throws Exception {
		dao = new JxjDAO();
		
		/*boolean chkKqxx = dao.chkKqxx(xh);//������Ϣ
		if (!chkKqxx) {
			return "kqbhg";//���ڲ�����
		} else {
			//boolean chkCjhk = dao.chkCjhk(xh);//�Ƿ��л����ɼ�
			//if (!chkCjhk) {
			//	return "cjhkbhg";//�������ϸ�
			//} else {
				boolean chkJd = dao.chkJd(jd, jxjdm);//ѧ�ڼ���
				if (chkJd) {
					return "";//���ϸý�ѧ����������
				} else {
					return "jdbhg";//ѧ�ڼ��㲻����
				}
			}*/
		
		
		List<String[]> cjList = dao.getCjList(xh);//ѧ���ɼ�
		if (cjList != null && cjList.size() > 0) {//�ɼ������гɼ�
			boolean chkStucj = dao.chkStucj(xh);//ѧ��ѧ����ѧ���Ƿ��гɼ�������
			if (!chkStucj) {
				return "cjbhg";//������ɼ�
			} else {
				boolean chkKqxx = dao.chkKqxx(xh);//������Ϣ
				if (!chkKqxx) {
					return "kqbhg";//���ڲ�����
				} else {
					//boolean chkCjhk = dao.chkCjhk(xh);//�Ƿ��л����ɼ�
					boolean chkCjhk = true;
					if (!chkCjhk) {
						return "cjhkbhg";//�������ϸ�
					} else {
						boolean chkJd = dao.chkJd(jd, jxjdm);//ѧ�ڼ���
						if (chkJd) {
							return "";//���ϸý�ѧ����������
						} else {
							return "jdbhg";//ѧ�ڼ��㲻����
						}
					}
				}
			}
		} else {
			return "wcj";//��ѧ���ɼ�
		}
	}
	
	/**
	 * ���潱ѧ����Ϣ
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsqSave(JxjModel model, HttpServletRequest request) throws Exception {
		dao = new JxjDAO();
		return dao.jxjsqSave(model, request);
	}
	
	/**
	 * ��ѧ����˲�ѯ��ͷ
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> jxjshTableTitle(String userType, String isFdy)
			throws Exception {
		dao = new JxjDAO();
		String[] enList = null;
		String[] cnList = null;
		if (StringUtils.isEqual("xy", userType)) {// ѧԺ�û�
			enList = new String[] { "flag","bgcolor","pk", "rownum","xn", "xq", "xm", "xb",
					"bjmc", "jd","mc", "jxjdm", "xysh" };
			cnList = new String[] {"flag","bgcolor", "pk","�к�", "ѧ��", "ѧ��", "����", "�Ա�",
					"�༶����", "����","רҵ����", "��ѧ��", "ѧԺ���" };
		} else {// ѧУ�û�
			enList = new String[] {  "bgcolor","pk","�к�", "xn", "xq", "xm", "xb",
					"bjmc", "jd", "mc","jxjdm", "xysh" };
			cnList = new String[] { "bgcolor","pk","�к�", "ѧ��", "ѧ��", "����", "�Ա�",
					"�༶����", "����", "רҵ����","��ѧ��", "ѧУ���" };
		}
		if ("true".equalsIgnoreCase(isFdy)) {
			enList = new String[] { "flag","bgcolor","pk", "rownum","xn", "xq", "xm", "xb",
					"bjmc", "jd","mc1", "jxjdm", "fdysh" };
			cnList = new String[] {"flag","bgcolor", "pk","�к�", "ѧ��", "ѧ��", "����", "�Ա�",
					"�༶����", "����","רҵ����","��ѧ��", "����Ա���" };
		}
		return dao.getTitleResult(enList, cnList);
	}
	
	/**
	 * ��ѧ����˲�ѯ���
	 * @param userType
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> jxjshTableResult(String userType, JxjModel model, String isFdy, String userName) throws Exception {
		dao = new JxjDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("true".equalsIgnoreCase(isFdy)) {
			resList = dao.fdyjxjshQryRes(model, userName);
		} else {
			if (StringUtils.isEqual("xy", userType)) {// ѧԺ�û�
				resList = dao.xyjxjshQryRes(model);
			} else {// ѧУ�û�
				resList = dao.xxjxjshQryRes(model);
			}
		}
		
		return resList;
	}
	
	/**
	 * ѧԺ��˽�ѧ����(��ü��,�������)
	 * @param tg
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String xyjxjshRes(String userType, String tg, String[] keys, HttpServletRequest request, String isFdy,JxjModel model ) throws Exception {
		dao = new JxjDAO();
		if ("true".equalsIgnoreCase(isFdy)) {//����Ա���
			return dao.fdyjxjshRes(tg, keys, request,model);
		} else {
			if (StringUtils.isEqual("xy", userType)) {
				return dao.xyjxjshRes(tg, keys, request);
			} else {
				return dao.xxjxjshRes(tg, keys, request);
			}
		}
	}
	
	/**
	 * ��ѧ�𵥸����
	 * @param pkValue
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> jxjshone(String pkValue, String userType, String isFdy) throws Exception {
		dao = new JxjDAO();
		if ("true".equalsIgnoreCase(isFdy)) {
			return dao.fdyjxjshDetails(pkValue);
		} else {
			if (StringUtils.isEqual("xy", userType)) {
				return dao.xyjxjshDetails(pkValue);
			} else {
				return dao.xxjxjshDetails(pkValue);
			}
		}
	}
	
	/**
	 * ��ѧ�𵥸����
	 * @param model
	 * @param pkValue
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public String jxjshone(JxjModel model, String pkValue, String userType, String isFdy) throws Exception {
		dao = new JxjDAO();
		if ("true".equalsIgnoreCase(isFdy)) {
			return dao.fdyjxjshoneRes(model, pkValue, userType);
		} else {
			return dao.jxjshoneRes(model, pkValue, userType);
		}
	}
	
	/**
	 * ѧ���ɼ�
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjList1(String pkValue) throws Exception {
		dao = new JxjDAO();
		return dao.getCjList1(pkValue);
	}
	
	/**
	 * ѧ���ɼ�
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjList2(String xh, String pkValue) throws Exception {
		dao = new JxjDAO();
		return dao.getCjList2(xh, pkValue);
	}
	
	/**
	 * ��ѯѧ�����뽱ѧ����ϸ��Ϣ
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String,String>
	 * */
	public HashMap<String, String> getJxjsqInfo(String pk,String pkValue){
		dao = new JxjDAO();
		return dao.getJxjsqInfo(pk,pkValue);
	}
	
	/**
	 * ��⼨���Ƿ���
	 * @param xh
	 * @param jxjdm
	 * @param jd
	 * @return boolean
	 * */
	public boolean checkJxjJddb(String jxjdm, String jd){
		dao = new JxjDAO();
		boolean chkJd = dao.chkJd(jd, jxjdm);//ѧ�ڼ���
		return chkJd;
	}
	
	/**
	 * ѧ����ѧ��������Ϣ�޸�
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return boolean 
	 * */
	public boolean jxjModiSave(String pkValue, PjpyShcbysJxjActionForm model,HttpServletRequest request) throws Exception{
		String drzw = DealString.toGBK(model.getDrzw());
		String jd = DealString.toGBK(model.getJd());
		String jxjdm = DealString.toGBK(model.getJxjdm());
		String sqly = DealString.toGBK(model.getSqly());
		
		return StandardOperation.update("xsjxjb", new String[]{"drzw","jd","jxjdm","sqly"}, new String[]{drzw,jd,jxjdm,sqly}, "xh||xn||jxjdm", pkValue, request);
	}
	
	public String stuname(String xh) throws Exception {
		dao = new JxjDAO();
		return dao.stuname(xh);
	}
	
	public HashMap sn(String pkValue) {
		dao = new JxjDAO();
		return dao.sn(pkValue);
	}
	
	public String getJxjlb(String jxjdm) {
		dao = new JxjDAO();
		return dao.getJxjlb(jxjdm);
	}
	
	public HashMap<String, String> getPjpyZq() {
		dao = new JxjDAO();
		return dao.getPjpyZq();
	}
}
