package xgxt.xtwh.zfoa;

import java.net.URLEncoder;

import com.zfsoft.zfca.tp.cas.util.encrypt.MD5;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;


public class GetUrl {

	public String getUrl(String userName,String returl) {
		try {
			DAO dao = DAO.getInstance();
			String strSysDatetime = dao.getOneRs("select to_char(sysdate,'YYYY-MM-DDHH24:MI:SS') SYSDATESTR from dual",new String[]{},"SYSDATESTR");
			//µ÷ÓÃÃÜ³×
			String sql = "select zfssokey from view_zf_key where rownum=1";
			String zfssokey = dao.getOneRs(sql, new String[] {},"zfssokey");
			String jmcs = URLEncoder.encode(userName, "utf-8")
					+ zfssokey + strSysDatetime
					+ "teacher";
			MD5 md5 = new MD5();
			String strMd5 = md5.getMD5ofStr(jmcs);

			String url = Base.getInitProperties().get("zfoaAddress") + "?verify=" + strMd5
					+ "&userName=" + DealString.toUtf8String(userName)
					+ "&strSysDatetime=" + strSysDatetime + "&jsName=teacher";
			if (!(Base.isNull(returl))) {
				url = url + "&url="
						+ URLEncoder.encode(returl, "utf-8");
			}
			return url;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}