package xgxt.DAO;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import xgxt.daoActionLogic.StandardOperation;

public class LdapTestDao {
	private Hashtable env = null;

	private DirContext ctx = null;

	private boolean islogin = false;

	@SuppressWarnings("unchecked")
	public LdapTestDao(String id, String pwd) {
		env = new Hashtable();

		env.put("java.naming.factory.initial",
				"com.sun.jndi.ldap.LdapCtxFactory");
		// env.put("java.naming.provider.url", "LDAP://your ad
		// server/ou=组织名,dc=**,dc=****");
		env.put("java.naming.provider.url", "LDAP://10.31.1.24:389");
		// env.put("java.naming.base.dn", "dc=nbptnet,dc=cn");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		// 原来的用户名不正确，应该为形式 domain username
		env.put("java.naming.security.principal", id);
		env.put("java.naming.security.credentials", pwd);
		System.out.println("-------------");
	}

	public boolean checkAd() {

		try {
			System.out.println("-----ddd--------");
			@SuppressWarnings("unused")
			InitialContext tctx = new InitialContext(env);
			System.out.println("-------eee------");
			islogin = true;
		} catch (AuthenticationException aue) {
			aue.printStackTrace();
			islogin = false;

		} catch (NamingException e) {

			e.printStackTrace();
		} catch (Exception eee) {
			eee.printStackTrace();

		} finally {
			try {
				if (ctx != null)
					ctx.close();
			} catch (Exception ie) {
				ie.printStackTrace();
			}

		}

		return islogin;
	}

	public String[] ss(String userName, String passWord) {
		String userPassword = "";
		String errMessage = "";
		try {
			// Create the initial directory context
			LdapContext ctx = new InitialLdapContext(env, null);

			// Create the search controls
			SearchControls searchCtls = new SearchControls();

			// Specify the search scope
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			// specify the LDAP search filter
			String searchFilter = "(uid=" + userName + ")";

			// Specify the Base for the search
			String searchBase = "cn=users,dc=nbptnet,dc=cn";
			// initialize counter to total the group members
			// Specify the attributes to return
			String returnedAtts[] = { "userPassword" };
			searchCtls.setReturningAttributes(returnedAtts);

			// Search for objects using the filter
			NamingEnumeration answer = ctx.search(searchBase, searchFilter,
					searchCtls);

			// Loop through the search results
			while (answer.hasMoreElements()) {
				SearchResult sr = (SearchResult) answer.next();
				Attributes attrs = sr.getAttributes();

				if (attrs != null) {
					userPassword = new String((byte[]) attrs.get("userPassword").get());
				}
			}
			ctx.close();

		} catch (NamingException e) {
			e.printStackTrace();
			System.err.println("Problem searching directory: " + e);
		}
		if (!userPassword.equalsIgnoreCase(passWord)) {
			errMessage = "用户名不存在或密码错误";
		}
		return new String[] { "", "", "", "", errMessage };
	}

	// public static void main(String[] args)
	// {
	// /LDAPTest ld=new LDAPTest("0809001","123456");
	// LdapTestDao ld = new LdapTestDao("cn=root", "password");
	// ld.ss("0809001","123456");
	// }

	public String[] login(String userName, String passWord) {
		// LDAPTest ld=new LDAPTest("0809001","123456");
		LdapTestDao ld = new LdapTestDao("cn=root", "password");
		String[] loginMessage = ld.ss(userName, passWord);
		return loginMessage;
	}

	// 宁波职业
	public String getDescription(String userID) {
		String strResult = "";
		try {
			LdapContext ctx = new InitialLdapContext(env, null);
			SearchControls searchCtls = new SearchControls();
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String searchFilter = "(uid=" + userID + ")";
			String searchBase = "cn=users, DC=NBPTNET,DC=CN";
			String returnedAtts[] = { "description", "sn" };
			searchCtls.setReturningAttributes(returnedAtts);
			NamingEnumeration answer = ctx.search(searchBase, searchFilter,
					searchCtls);
			while (answer.hasMoreElements()) {
				SearchResult sr = (SearchResult) answer.next();
				Attributes attrs = sr.getAttributes();
				strResult = attrs.toString();
				System.out.println(strResult);
			}
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return strResult;
	}

	// 宁波职业获得辅导教师字符串信息
	public String getTeaInfo() throws ServiceException, MalformedURLException,
			RemoteException {
		// String ret = "";
		Service service = new Service();
		Call call = null;
		call = (Call) service.createCall();
		String nameSpaceUri = "http://ldap.nbptweb.net/itim_expi/services/person";
		call.setOperationName(new QName(nameSpaceUri, "checkXGTY"));
		call.setTargetEndpointAddress(new java.net.URL(nameSpaceUri));
		Object[] params = new Object[] { "" };
		String lret = "";
		// try {
		lret = (String) call.invoke(params);
		System.out.println(lret);
		// } catch (Exception e) {
		// return "false";
		// }
		return lret;
		// 输出结果（T,0502021@nbpt.net,0502021,李婷;T,0500024@nbpt.net,0500024,詹福海;...）
	}

	// 宁波职业获得辅导教师信息数组
	public void insertTeaInfolist(String teainfo, HttpServletRequest request) {
		String[] teainfolist = null;
		String zgh = "";
		String xm = "";
		String dzyx = "";
		if (teainfo != null) {
			teainfolist = teainfo.split(";");
		}
		int l = 0;
		if (null != teainfolist) {
			for (int i = 0; i < teainfolist.length; i++) {
				String[] teainfostringlist = teainfolist[i].split(",");
				//如果缺乏信息
				if(null != teainfostringlist && teainfostringlist.length != 4){
					//ArrayList<HashMap<String, String>> map = new ArrayList<HashMap<String, String>>();
		       //还未开发完成。。。。。
				}
					
				if (null != teainfostringlist && teainfostringlist.length == 4) {
					zgh = teainfostringlist[2];
					xm = teainfostringlist[3];
					dzyx = teainfostringlist[1];
					boolean judge = false;
					System.out.println("是否进入正常插入程序？");
					judge = StandardOperation.insert("fdyxxb", new String[] {
							"zgh", "dzyx", "xm","bmdm" },
							new String[] { zgh, dzyx, xm,"09" }, request);
					
					l++;
					if (judge) {
						System.out.println("插入辅导员表成功一例");
					} else {
						System.out.println("插入辅导员表失败一例");
					}
				}
			}
		}
		request.setAttribute("howmuchsucess", l);
		// 格式（teainfolist[0]="T,0502021@nbpt.net,0502021,李婷"）
	}
	
	//宁波职业辅导老师信息同步以后在用户表中生成登陆用户
	public void insertTeaInfolistToYhb(String teainfo, HttpServletRequest request) {
		
		String[] teainfolist = null;
		String yhm = "";   //用户名
		String xm = "";    //姓名
		String zdm = "0008";  //组代码
		String kl = "u";   //口令
		if (teainfo != null) {
			teainfolist = teainfo.split(";");
		}
		if (null != teainfolist) {
			for (int i = 0; i < teainfolist.length; i++) {
				String[] teainfostringlist = teainfolist[i].split(",");
				//如果缺乏信息
				if(null != teainfostringlist && teainfostringlist.length != 4){
					//ArrayList<HashMap<String, String>> map = new ArrayList<HashMap<String, String>>();
		       //还未开发完成。。。。。
				}
					
				
				if (null != teainfostringlist && teainfostringlist.length == 4) {
					yhm = teainfostringlist[2];
					xm = teainfostringlist[3];
					
					boolean judge = false;
					System.out.println("是否进入正常插入程序？");
					judge = StandardOperation.insert("yhb", new String[] {
							"yhm", "xm", "zdm","kl","szbm","dwdm" },
							new String[] { yhm, xm,zdm,kl,"09","01" }, request);
					if (judge) {
						System.out.println("插入用户表成功一例");
					} else {
						System.out.println("插入用户表失败一例");
					}
				}
			}
		}
		// 格式（teainfolist[0]="T,0502021@nbpt.net,0502021,李婷"）
		
	}
	
}
