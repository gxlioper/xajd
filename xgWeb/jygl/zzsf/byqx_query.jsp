<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />



	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
	    function byqxquerygo(){
		 	document.forms[0].action = "/xgxt/byqxquery.do?act=go&doType=query";
		 	document.forms[0].submit();
        }
        
        function onchanged(){
	         document.forms[0].action = "/xgxt/byqxquery.do?doType=query";
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
		 showTopWin(url, 670, 470);
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
		         showTopWin(url, 690, 500);
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
		    		showTopWin(url, 690, 500);
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
		<html:form action="/data_search" method="post">
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ҵ���� - ѧ����Ϣ - ��ҵȥ���ѯ
				</div>
			</div>
			<fieldset>
				<legend>
					�� ѯ
				</legend>
				<logic:equal name="whichtype" value="allteacher">
					<table width="100%" class="tbstyle">
						<thead>
							<tr style="cursor:hand">
								<td>
								   <bean:message key="lable.xsgzyxpzxy" />��
								   <logic:equal name="who" value="teacher">		  							   
								   <html:select name="rs1" property="xymc" style="width:151px" onchange="onchanged();" >
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xymc"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<logic:equal name="who" value="fudaoyuan">
									<html:text name="rs1" property="xymc" style="width:151px"
											readonly="true" />
									</logic:equal>
									&nbsp;&nbsp;
									רҵ��
									<logic:equal name="who" value="teacher">
									<html:select name="rs1" property="zymc" style="width:151px" onchange="onchanged()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zymc"
											labelProperty="zymc" />
									</html:select>
									</logic:equal>
									
									<logic:equal name="who" value="fudaoyuan">
									<html:text name="rs1" property="zymc" style="width:151px"
											readonly="true" />
									</logic:equal>
									&nbsp;&nbsp;
									�༶��
									<logic:equal name="who" value="teacher">
									<html:select name="rs1" property="bjmc" style="width:151px" onchange="onchanged()">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjmc"
											labelProperty="bjmc" />
									</html:select>
									</logic:equal>
									<logic:equal name="who" value="fudaoyuan">
									<html:text name="rs1" property="bjmc" style="width:151px"
											readonly="true" />
									</logic:equal>
									&nbsp;&nbsp;
                                         �꼶��
								    <html:select name="rs1" property="nj" style="width:80px" onchange="onchanged()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>


									
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<button class="button2" style="height:40px"
										onclick="byqxquerygo()" id="query_go">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td>
									ѧ�ţ�
									<input type="text" name="xsxh" width="100px"
										value="<bean:write name="rs1" property="xsxh"/>" />
									&nbsp;&nbsp;������
									<input type="text" name="name" width="50px"
										value="<bean:write name="rs1" property="name"/>" />
									&nbsp;&nbsp;
									��ҵ��ȣ�
									<html:select name="rs1" property="bynd" style="width:60px">
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
									&nbsp;&nbsp;�Ա�
									<html:select name="rs1" property="xb" style="width:40px">
										<html:option value=""></html:option>
										<html:option value="��">
										��
									</html:option>
										<html:option value="Ů">
										Ů
									</html:option>
									</html:select>
									&nbsp; ��ˣ�
									<html:select name="rs1" property="xxsh" style="width:60px">
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="��ͨ����">��ͨ����</html:option>
										<html:option value="δͨ��X">δͨ��X</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<font color="blue">��ʾ��ѧ�š�����֧��ģ����ѯ��</font>
								</td>
							</tr>
						</thead>
					</table>
				</logic:equal>
				<logic:equal name="whichtype" value="student">
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td rowspan="2">
									ѧ�ţ�
									<html:text name="rs1" property="xsxh" style="width:150px"
										readonly="true" />
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<button class="button2" style="height:40px"
										onclick="byqxquerygo()" id="query_go">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
							</tr>
						</thead>
					</table>
				</logic:equal>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="viewMoreinfo('view')">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name="v"/>" />
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="1">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>

				</fieldset>
			</logic:notEmpty>


			<logic:equal name="whichtype" value="allteacher">
				<div class="buttontool" id="btn"
					style="position: absolute;left:0px;top:100px" width="100%">
					<button class="button2" onclick="byqxsh('shenhe')"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="bysjbxxbupdate('update')"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="byqxdelete('del')"
						style="width:80px">
						ɾ ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:equal name="who" value="teacher">
						<button class="button2"
							onclick="impAndChkData();"
							style="width:80px">
							��������
						</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="jyglbyqxDataExport()"
							style="width:80px">
							��������
						</button>
					</logic:equal>
				</div>
			</logic:equal>
			<logic:equal name="whichtype" value="student">
				<div class="buttontool" id="btn"
					style="position: absolute;left:0px;top:100px" width="100%">
					<button class="button2" onclick="bysjbxxbupdate('update')"
						style="width:80px">
						�� ��
					</button>
				</div>
			</logic:equal>

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
