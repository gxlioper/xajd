package xgxt.gygl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.gygl.GyglCommForm;
import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��Ԣ����_ͨ��-service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class GyglCommService extends CommService {

	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void setGyglOptionList(GyglCommForm commForm, RequestForm rForm,
			HttpServletRequest request) throws Exception {
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, commForm);
		setList(model, rForm, request);
	}
	
	public void setSearchModel(GyglCommForm commForm,
			HttpServletRequest request){
		String xqdm=request.getParameter("del_xqdm");
		String yqdm=request.getParameter("del_yqdm");
		String dm=request.getParameter("del_dm");
		String lddm=request.getParameter("lddm");
		String cs=request.getParameter("cs");
		String qsh=request.getParameter("qsh");
		
		if(!Base.isNull(xqdm)){
			commForm.getSearchModel().setSearch_tj_xqdm(new String[]{xqdm});
		}else if(!Base.isNull(dm)){
			commForm.getSearchModel().setSearch_tj_xqdm(new String[]{dm});
		}
		if(!Base.isNull(yqdm)){
			
			commForm.getSearchModel().setSearch_tj_xqdm(new String[]{yqdm});
		}
		
		if(!Base.isNull(lddm)){
			commForm.getSearchModel().setSearch_tj_lddm(new String[]{lddm});
		}
		
		if(!Base.isNull(qsh)){
			commForm.getSearchModel().setSearch_tj_qsh(new String[]{qsh});
		}
		
		if(!Base.isNull(cs)){
			commForm.getSearchModel().setSearch_tj_cs(new String[]{cs});
		}
	}
	
	/**
	 * ��ѯ������ʼ��
	 * 
	 * @author ΰ�����
	 */
	public void setSearchModelValue(String fpdx, GyglCommForm model) {

		String[] pk = model.getPrimarykey_checkVal();

		if (pk != null && pk.length > 0) {
			if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
				model.getSearchModel().setSearch_tj_xy(pk);
			} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
				String[] nj = new String[pk.length];
				String[] xy = new String[pk.length];

				for (int i = 0; i < pk.length; i++) {
					String[] arr_value = pk[i].split("!!@@!!");
					
					boolean nj_flag = true;
					for (int j = 0; j < nj.length; j++) {
						if (arr_value[0].equalsIgnoreCase(nj[j])) {
							nj_flag = false;
							break;
						}
					}
					
					if (nj_flag) {
						nj[i] = arr_value[0];
					}
					
					boolean xy_flag = true;
					for (int j = 0; j < xy.length; j++) {
						if (arr_value[1].equalsIgnoreCase(xy[j])) {
							xy_flag = false;
							break;
						}
					}
					
					if (xy_flag) {
						xy[i] = arr_value[1];
					}
				}
				model.getSearchModel().setSearch_tj_nj(nj);
				model.getSearchModel().setSearch_tj_xy(xy);

			} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
				String[] nj = new String[pk.length];
				String[] zy = new String[pk.length];

				for (int i = 0; i < pk.length; i++) {
					String[] arr_value = pk[i].split("!!@@!!");
					
					boolean nj_flag = true;
					for (int j = 0; j < nj.length; j++) {
						if (arr_value[0].equalsIgnoreCase(nj[j])) {
							nj_flag = false;
							break;
						}
					}
					
					if (nj_flag) {
						nj[i] = arr_value[0];
					}
					
					boolean zy_flag = true;
					for (int j = 0; j < zy.length; j++) {
						if (arr_value[1].equalsIgnoreCase(zy[j])) {
							zy_flag = false;
							break;
						}
					}
					
					if (zy_flag) {
						zy[i] = arr_value[1];
					}
				}
				model.getSearchModel().setSearch_tj_nj(nj);
				model.getSearchModel().setSearch_tj_zy(zy);
			} else {// �������Ϊ�༶
				model.getSearchModel().setSearch_tj_bj(pk);
			}
		}
	}
}
