/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package xsgzgl.jxgl.general.gfjyqk.gfjyqksh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.jxgl.general.gfjyqk.gfjyqkjg.GfjyjgDao;
import xsgzgl.jxgl.general.gfjyqk.gfjyqkjg.GfjyjgForm;


import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg.HcccqjjgDao;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg.HcccqjjgForm;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjsh.HcccqjshForm;

public class GfjyshService extends SuperServiceImpl<GfjyshForm, GfjyshDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	
	public GfjyshService() {
		super.setDao(dao);
	}
	public HashMap<String, String> getGfjyqkInfo(GfjyshForm model) {
		return dao.getGfjyqkInfo(model);
	}

	public boolean saveSh(GfjyshForm form, User user) throws Exception {
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplc());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("gfjy_gfjyqk_sh.do");
		model.setTzljsq("gfjy_gfjyqk_sq.do");
		boolean reuslt = false;
		
			String zhzt = shlc.runAuditing(model);
			GfjyshForm upForm = new GfjyshForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//���״̬Ϊͨ�������ճ���Ϊ������б����������
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				GfjyjgForm gfjyjgForm = new GfjyjgForm();
				form = getModel(form);
        		BeanUtils.copyProperties(gfjyjgForm, StringUtils.formatData(form));
        		gfjyjgForm.setJgid(form.getSqid());
        		gfjyjgForm.setSjly("1");
        		gfjyjgForm.setSqid(form.getSqid());
        		GfjyjgDao jgDao = new GfjyjgDao();
        		jgDao.deleteExist(gfjyjgForm); //��Ҫɾ����������ظ�������
        		jgDao.runInsert(gfjyjgForm);	
			}	
		
		return reuslt;
	}
	public String savePlsh(GfjyshForm t, User user) throws Exception {
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();

		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			GfjyshForm model = new GfjyshForm();
			model.setSplc(splcs[i]);
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	public boolean newCancelSh(GfjyshForm model) {
		boolean resultsq = false;
		boolean resultjg = false;
		try {
			resultsq = dao.updateGfjyqk(model.getSqid(), Constants.YW_SHZ);
			if(resultsq){
				String shzt = model.getShzt();
				if(shzt != null && shzt.equals("2")){
					resultjg = true;
				}else{
					resultjg = dao.deleteGfjyqkjg(model.getSqid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultjg;
	}



}
