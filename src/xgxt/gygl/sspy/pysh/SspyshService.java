/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xgxt.gygl.sspy.pysh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.gygl.sspy.pyjg.SspyjgDao;
import xgxt.gygl.sspy.pyjg.SspyjgForm;
import xgxt.utils.String.StringUtils;
import xsgzgl.jxgl.general.gfjyqk.gfjyqkjg.GfjyjgDao;
import xsgzgl.jxgl.general.gfjyqk.gfjyqkjg.GfjyjgForm;
import xsgzgl.jxgl.general.gfjyqk.gfjyqksh.GfjyshForm;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

public class SspyshService extends SuperServiceImpl<SspyshForm, SspyshDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	public HashMap<String, String> getSspyInfo(SspyshForm model) {
		return dao.getSspyInfo(model);
	}
	public boolean saveSh(SspyshForm form, User user) throws Exception {
		ShxxModel model = new ShxxModel();
		model.setShlc(form.getSplc());// 审核流程ID
		model.setShr(user.getUserName());
		model.setShyj(form.getShyj());
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("sspy_sh.do");
		model.setTzljsq("sspy_sq.do");
		boolean reuslt = false;
			String zhzt = shlc.runAuditing(model);
			SspyshForm upForm = new SspyshForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//审核状态为通过的往日常行为结果表中保存该条数据
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				SspyjgForm sspyjgForm = new SspyjgForm();
				form = getModel(form);
        		BeanUtils.copyProperties(sspyjgForm, StringUtils.formatData(form));
        		sspyjgForm.setGuid(form.getSqid());
        		sspyjgForm.setSjly("1");
        		sspyjgForm.setYwid(form.getSqid());
        		SspyjgDao jgDao = new SspyjgDao();
        		jgDao.deleteExist(sspyjgForm); //需要删除结果表里重复的数据
        		jgDao.runInsert(sspyjgForm);	
			}	
		
		return reuslt;
	}
	
	public boolean newCancelSh(SspyshForm model) {
		boolean resultsq = false;
		boolean resultjg = false;
		try {
			resultsq = dao.updateSspy(model.getSqid(), Constants.YW_SHZ);
			if(resultsq){
				String shzt = model.getShzt();
				if(shzt != null && shzt.equals("2")){
					resultjg = true;
				}else{
					resultjg = dao.deletesspyjg(model.getSqid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultjg;
	}
	public String savePlsh(SspyshForm t, User user) throws Exception {
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();

		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			SspyshForm model = new SspyshForm();
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
	
	
	
	
	
	
	
	
	
	
	
}
