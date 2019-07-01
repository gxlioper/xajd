package xgxt.xszz.tjgy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.db.GetSysData;

public class XfjmService {

	private XfjmDAO dao = new XfjmDAO();
	
	
	/**
	 * ������Ŀ������Ϣ
	 * @param model
	 * @return
	 */
	public boolean saveXmsz(XfjmForm model){
		
		String xmid = model.getXmid();
		
		if (StringUtils.isNotNull(xmid)){
			try {
				boolean result = dao.updateXmsz(model);//�޸���Ŀ������Ϣ
				if ("no".equals(model.getXgtj())){
					//���޸�����
					return result;
				} else {
					//�޸���Ŀ������Ϣ-��ɾ��������
					return result ? dao.delXmtj(model.getXmid()) && dao.addXmtj(model) : result;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			model.setXmid(GetSysData.getGuid());
			try {
				//������Ŀ������Ϣ
				boolean result = dao.addXmsz(model);
				//������Ŀ������Ϣ
				return result ? dao.addXmtj(model) : result;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	/**
	 * ��Ŀ����ͳ�Ʋ�ѯ
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getXmszList(XfjmForm model){
		
		return dao.getXmszList(model);
	}
	
	
	/**
	 * ��ѯ��Ŀ������Ϣ
	 * @param xmid
	 * @return
	 */
	public HashMap<String,String> getXmszInfo(String xmid){
		
		return dao.getXmszInfo(xmid);
	}
	
	
	/**
	 * ��ѯ��Ŀ�����õ�����
	 * @param xmid
	 * @return
	 */
	public List<HashMap<String,String>> getXmtjList(String xmid){
		
		return dao.getXmtjList(xmid);
	}
	
	
	/**
	 * ɾ����Ŀ���������Ϣ
	 * @param xmid
	 * @return
	 */
	public boolean delXmsz(String[] xmid){
		
		if (null != xmid && xmid.length > 0){
			try {
				return dao.delXmsz(xmid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	/**
	 * ����ѧ�Ų�ѯ��Ŀ���������Ϣ
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getXmsqInfoByXh(String xh){
		
		return dao.getXmsqInfoByXh(xh);
	}


	
	/**
	 * ������Ŀ������Ϣ
	 * @param model
	 * @return
	 */
	public boolean saveXmsqInfo(XfjmForm model){
		
		if ("modify".equals(model.getDoType())){
			String[] input = new String[]{model.getYjxf(),model.getJmxf(),model.getSjhm(),
					StringUtils.joinStrByArray(model.getTjid(), ","),model.getBz(),
					model.getXmid(),model.getXh(),Base.currXn};
			try {
				//�޸�
				return dao.updateXmsqInfo(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String[] input = new String[]{model.getXmid(),model.getXh(),
						Base.currXn,model.getYjxf(),model.getJmxf(),
						model.getSjhm(),StringUtils.joinStrByArray(model.getTjid(), ","),
						model.getBz()};
			try {
				//����
				return dao.saveXmsqInfo(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

	
	
	
	/**
	 * ��ѯ������Ŀ������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getXmsqInfo(XfjmForm model){
		
		String pkValue = model.getPkValue();
		
		if (StringUtils.isNull(pkValue)){
			
			model.setPkValue(model.getXmid()+model.getXh()+model.getXn());
		}
		
		return dao.getXmsqInfo(model);
	}
	
	
	
	/**
	 * ��Ŀ�����¼����˲�ѯ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getXmsqList(XfjmForm model,User user) throws Exception{
		
		
		return dao.getXmsqList(model,user);
	}
	
	
	
	/**
	 * ѧ�Ѽ��ⵥ�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveDgsh(XfjmForm model,User user) throws Exception{
		
		String[] field = null;
		String[] input = null;
		String sysTime = GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");
		
		if ("xy".equals(user.getUserType())){
			field = new String[]{"xysh","xyshsj","xyshr","xyshyj"};
			input = new String[]{model.getShzt(),sysTime,user.getUserName(),
					model.getXyshyj(),model.getXmid(),model.getXh(),model.getXn()};
		} else {
			field = new String[]{"xxsh","xxshsj","xxshr","xxshyj"};
			input = new String[]{model.getShzt(),sysTime,user.getUserName(),
					model.getXxshyj(),model.getXmid(),model.getXh(),model.getXn()};
		}
		
		return dao.saveDgsh(field, input);
	}
	
	
	/**
	 *�жϵ�ǰ�����Ŀ�Ƿ�ɱ�ͨ��
	 * @param model
	 * @return
	 */
	public boolean checkSfktg(XfjmForm model){
		
		String count = dao.getYshNotThisXm(model);
		
		if (Integer.valueOf(count) == 0){
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * ����������Ŀ���
	 * @param model
	 * @param pkValue
	 * @param user
	 * @return
	 */
	public boolean plxmsh(XfjmForm model , String[] pkValue ,User user){
		
		if (null != pkValue && pkValue.length > 0){
			String[] field = null;
			String[] input = null;
			String sysTime = GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");
			
			if ("xy".equals(user.getUserType())){
				field = new String[]{"xysh","xyshsj","xyshr","xyshyj"};
				input = new String[]{model.getShzt(),sysTime,user.getUserName(),model.getShyj()};
			} else {
				field = new String[]{"xxsh","xxshsj","xxshr","xxshyj"};
				input = new String[]{model.getShzt(),sysTime,user.getUserName(),model.getShyj()};
			}
			try {
				return dao.plxmsh(field, input, pkValue);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	/**
	 * �����ѯ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getJgcxList(XfjmForm model,User user) throws Exception{
		
		
		return dao.getXmsqList(model,user);
	}
	
	
	
	/**
	 * ɾ����Ŀ������Ϣ
	 * @param pkValues
	 * @return
	 */
	public boolean delXmsq(String[] pkValues){
		
		if (null != pkValues && pkValues.length > 0){
			try {
				return dao.delXmsq(pkValues);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	
	/**
	 * ����ͳ�Ʋ�ѯ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getExpXmsqList(XfjmForm model,User user) throws Exception{
		model.getPages().setPageSize(Integer.MAX_VALUE);
		return dao.getExpXmsqList(model, user);
	}
}
