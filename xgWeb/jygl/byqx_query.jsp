<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
	    function byqxquerygo(){
		 	document.forms[0].action = "/xgxt/byqxquery.do?act=go&doType=query";
		 	document.forms[0].submit();
        }
        
		function byqxdelete(doType){
		var url = "/xgxt/byqxdelete.do?doType2=del&doType=query&act=go&pkValue=";
		var pkValue = "";
			
		if (doType == "del") {
		   if (curr_row == null) {
			alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
			return false;
		    } else {
			if (confirm("ȷ��Ҫɾ������������")) {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		  }
		   return;
	      }
		}
		
		
		function viewMoreinfo(doType){
		var url ="/xgxt/viewmorebyqxinfo.do?doType=view&pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 800, 600);
		 }
		}
		
		
		
		function bysjbxxbupdate(doType){
		var url ="/xgxt/jyglByqxUpdate.do?doType=update&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 670, 480);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
		function byqxsh(doType){
			var url ="/xgxt/jyglByqxSh.do?doType=shenhe&pkValue=";
			var pkValue ="";
		
			if (doType == "shenhe") {
		   		if (curr_row == null) {
					alert("��ѡ��Ҫ��˵����ݣ�\n��������Ӧ���У�");
					return false;
		    	} else {	 
		   		 	pkValue = curr_row.getElementsByTagName("input")[0].value;
		    		url += pkValue;
		    		showTopWin(url, 670, 480);
		    		return true;
		    	}
		 	}else{
		    	return true;
		 	}
		}
		
		
		 function  jyglbyqxDataExport(){
	       document.forms[0].action = "/xgxt/jyglbyqxDataExport.do?tableName=jygl_byqx";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		</script>
	</head>
	<body>
		
		<html:form action="/data_search" method="post">
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				</p>
			</div>
			
			<div class="toolbox">
				<logic:equal name="whichtype" value="allteacher">
				<div class="buttonbox">
				    <ul>
				    <logic:equal name="view" value="yes">
				    	<li> <a href="#" onclick="byqxsh('shenhe')" class="btn_sh"> ������� </a> </li>	
				    </logic:equal>
				    <li> <a href="#" onclick="bysjbxxbupdate('update')" class="btn_xg"> �޸� </a> </li>
				    <li> <a href="#" onclick="byqxdelete('del')" class="btn_sc"> ɾ�� </a> </li>
				    <logic:equal name="who" value="teacher">
				    	<li> <a href="#" onclick="impAndChkData();" class="btn_dr"> ���� </a> </li>
				    	<li> <a href="#" onclick="jyglbyqxDataExport()" class="btn_dc"> ���� </a> </li>
				    </logic:equal>
				    </ul>
				</div>
				</logic:equal>
				<logic:equal name="whichtype" value="student">
					<li> <a href="#" onclick="bysjbxxbupdate('update')" class="btn_xg"> �޸� </a> </li>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button class="btn_cx" onclick="byqxquerygo()" id="search_go">
											�� ѯ
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<logic:equal name="whichtype" value="allteacher">
						<tbody>
							<tr style="cursor:hand">
								<logic:equal name="who" value="teacher">
									<th>
								    <bean:message key="lable.xsgzyxpzxy" />
								    </th>
								    <td>
								    	<html:select name="rs1" property="xymc" style="width:151px">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xymc"
												labelProperty="xymc" />
										</html:select>
									</td>
									</logic:equal>
									<logic:equal name="who" value="fudaoyuan">
									<th>
								    <bean:message key="lable.xsgzyxpzxy" />
								    </th>
								    <td>
									<html:text name="rs1" property="xymc" style="width:151px"
											readonly="true" />
									</td>
									</logic:equal>
									<th>
										��ҵ���
									</th>
									<td>
									<html:select name="rs1" property="bynd" style="width:150px">
										<html:option value=""></html:option>
										<html:option value="2007">
										2007
									</html:option>
										<html:option value="2008">
										2008
									</html:option>
										<html:option value="2009">
										2009
									</html:option>
										<html:option value="2010">
										2010
									</html:option>
										<html:option value="2011">
										2011
									</html:option>
										<html:option value="2012">
										2012
									</html:option>
										<html:option value="2013">
										2013
									</html:option>
										<html:option value="2014">
										2014
									</html:option>
										<html:option value="2015">
										2015
									</html:option>
									</html:select>
									</td>
									<th>
									�Ա�
									</th>
									<td>
									<html:select name="rs1" property="xb" style="width:80px">
										<html:option value=""></html:option>
										<html:option value="��">
										��
									</html:option>
										<html:option value="Ů">
										Ů
									</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<input type="text" name="xsxh"
										value="<bean:write name="rs1" property="xsxh"/>" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<th>	
									����
								</th>
								<td>
									<input type="text" name="name"
										value="<bean:write name="rs1" property="name"/>" />
								</td>
								
								<logic:equal name="view" value="yes">
								<th>
									ѧУ���
								</th>
								<td>
									<html:select name="rs1" property="xxsh" style="width:80px">
											<html:option value=""></html:option>
											<html:option value="δ���">δ���</html:option>
											<html:option value="��ͨ����">��ͨ����</html:option>
											<html:option value="δͨ��X">δͨ��X</html:option>
										</html:select>
								</td>
								</logic:equal>
							</tr>
						</tbody>
				</logic:equal>
				<logic:equal name="whichtype" value="student">
						<tbody>
							<tr>
								<td rowspan="6">
									ѧ��
									<html:text name="rs1" property="xsxh" style="width:150px"
										readonly="true" />
								</td>
							</tr>
						</tbody>
				</logic:equal>
				</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp;
						<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> 
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>
			<logic:notEmpty name="rs">
				<table summary="" class="dateline" id="rsTable" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="2" length="8">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								
								<logic:notEqual name="xxdm" value="12453" scope="session">
								<logic:iterate id="tit" name="topTr" offset="10" length="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								</logic:notEqual>
								<logic:equal name="xxdm" value="12453" scope="session">
								<logic:equal value="admin" name="userType" scope="session">
								<logic:iterate id="tit" name="topTr" offset="10" length="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								</logic:equal>
								<logic:notEqual value="admin" name="userType" scope="session">
								<logic:equal value="xx" name="userType" scope="session">
								<logic:iterate id="tit" name="topTr" offset="10" length="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								</logic:equal>
								</logic:notEqual>
								</logic:equal>
								
								<logic:equal name="xxdm" value="12453" scope="session">
								
								<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										����Ա���
									</td>
									
									<logic:iterate id="tit" name="topTr" offset="12" length="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								</logic:equal>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="viewMoreinfo('view')">
								<td style="display:none">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2" length="8">
									<td >
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:equal value="xx" name="userType" scope="session">
									<logic:iterate id="v" name="s" offset="10" length="1">
										<td >
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</logic:equal>
								<logic:notEqual value="xx" name="userType" scope="session">
									<logic:equal value="admin" name="userType" scope="session">
									<logic:iterate id="v" name="s" offset="10" length="1">
										<td >
											<bean:write name="v" />
										</td>
									</logic:iterate>
									</logic:equal>
								</logic:notEqual>
								<logic:notEqual name="xxdm" value="12453" scope="session">
									<logic:equal value="xy" name="userType" scope="session">
									<logic:iterate id="v" name="s" offset="10" length="1">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									</logic:equal>
								</logic:notEqual>
									<logic:equal name="xxdm" value="12453" scope="session">
									<logic:iterate id="v" name="s" offset="11" length="1">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</logic:equal>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
					<script type="text/javascript">
							$('choose').className="hide";
					</script>
			</logic:notEmpty>
			</div>

			<logic:notEmpty name="delete">
				<logic:equal name="delete" value="ok">
					<script>
                      alert("ɾ���ɹ�!");
                    </script>
				</logic:equal>
				<logic:equal name="delete" value="no">
					<script>
                      alert("ɾ��ʧ��");
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
