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
<title><bean:message key="lable.title" /></title>
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
<script language="javascript" src="js/function.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
<script language="javascript" src="js/jsFunction.js"></script>
<script language="javascript">
	function gnxxsh(doType){
			var url ="/xgxt/bysjytjb.do?act=Auditing&shenhe=auditing&pkValue=";
			var pkValue ="";
		   		if (curr_row == null) {
					alert("��ѡ��Ҫ��˵����ݣ�\n��������Ӧ���У�");
					return false;
		    	} else {	 
		   		 	pkValue = curr_row.getElementsByTagName("input")[0].value;
		    		url += pkValue;

		    		showTopWin(url, 850,650);
		    	}
		}
		
	/*
	�������ͨ��
	*/
	 function pass(url){
	   var RowsStr="!!#!!";
	
	   for (i=0; i<document.getElementsByName("pk").length; i++){
		 if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	 }
	   }
	   document.forms[0].pkstring.value = RowsStr;
	
	   if (RowsStr=="!!#!!"){
		  alert("��ѡ��Ҫ������˵ļ�¼��");
		  return false;
	   }
	
	   if (!confirm("ȷ��Ҫͨ����ѡ��¼��")){
		  return false;
	   }
	   document.forms[0].action=url;
       document.forms[0].submit();
    }
    
    /*
	������˷��
	*/
    function notpass(url){
	var RowsStr="!!#!!";
	
	  for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	  }
	  }
	  document.forms[0].pkstring.value = RowsStr;
	
	  if (RowsStr=="!!#!!"){
		alert("��ѡ��Ҫ������˵ļ�¼��");
		return false;
	  }
	
	  if (!confirm("ȷ��Ҫ�����ѡ��¼��")){
		return false;
	  }
	document.forms[0].action=url;
    document.forms[0].submit();
   }
    
    
	function querygo(){
		 	document.forms[0].action = "/xgxt/bysjytjb.do?act=query&doType=query";
		 	document.forms[0].submit();
    }
    /*
	ȫ��ѡ��
	*/    
      function chec(){
         for(i=0;i<document.getElementsByName("pk").length;i++){
      	    document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
         }
      }		
      /*
	����ɾ��
	*/
      function del(url){
	    var RowsStr="!!#!!";
	
	    for (i=0; i<document.getElementsByName("pk").length; i++){
    	   if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	   }
	    }
	    document.forms[0].pkstring.value = RowsStr;
	       if (RowsStr=="!!#!!"){
	         alert("��ѡ��Ҫ����ɾ���ļ�¼��");
		   return false;
    	}
	
	    if (!confirm("ȷ��Ҫɾ����ѡ��¼��")){
		   return false;
	    }
	    document.forms[0].action=url;
        document.forms[0].submit();
     }
	</script>
<script language="javascript">
		function bysjbxxbdel(doType){
		var url = "/xgxt/bysjytjb.do?act=del&pkValue=";
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
		var url ="/xgxt/bysjytjb.do?act=view&pkValue=";
		var pkValue ="";
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showOpenWindow(url, 850, 500,false);
		 }
		}
		function bysjbxxbupdate(doType){
		var url ="/xgxt/bysjytjb.do?act=update&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 850, 700);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		function bysjbxxbupdatesh(doType){
			var url ="/xgxt/bysjytjb.do?act=update&pkValue=";
			var pkValue ="";
			
			 if (doType == "update"){
			    if (curr_row == null) {
				alert("��ѡ��Ҫ��������ݣ�\n��������Ӧ���У�");
				return false;
				 } else {
				if (confirm("ȷ��Ҫ�������������")) {
					 pkValue = curr_row.getElementsByTagName("input")[0].value;
			         url += pkValue;
			         showTopWin(url, 850, 700);
					return true;
				} else {
					return false;
				}
			   }
		      }		
			}
		
		
		function jyglxsjbxxbDataExport() {
	       document.forms[0].action = "/xgxt/bysjytjb.do?tableName=jygl_xsjbxxb";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		function yxjzyjsadd(){
			 var url = "/xgxt/bysjytjb.do?act=add";
			 showTopWin(url, 850, 650);
			// showOpenWindow(url, 850, 700);
		}
		function shczp(){
			 if (curr_row == null) {
				alert("��ѡ��Ҫ�ϴ���Ƭ��ѧ����\n��������Ӧ���У�");
				return false;
			 }else {
				 var xsxh = curr_row.getElementsByTagName("input")[0].value;
				 document.getElementById("pkxsxh").value=xsxh;
				document.forms[0].action = "/xgxt/bysjytjb.do?act=sczp";
			    //document.forms[0].target = "_blank";
			    document.forms[0].submit();
			    document.forms[0].target = "_self";
			 }
		}
		
		</script>
<body>
<html:form action="/bysjytjb.do" method="post">
	<input type="hidden" name="pkstring" value="" />
	<input type="hidden" id="pkxsxh" name="pkxsxh" value="" />
	<input type=hidden id="userstu" name="userstu"
		value="<bean:write name="userType" scope="session"/>" />
	<input type="hidden" name="xyV" value="" />
	<input type="hidden" name="zyV" value="" />
	<input type="hidden" name="bjV" value="" />
	<div class="title">
	<div class="title_img" id="title_m">��ǰ����λ�ã���ҵ���� - ��ҵЭ�鷽�� -
	��ҵ����ҵ�Ƽ�</div>
	</div>
	<fieldset><legend> �� ѯ </legend>

	<table width="100%" class="tbstyle">
		<thead>
			<tr style="cursor: hand">
				<logic:equal value="stu" name="userType" scope="session">
					<td>ѧ�ţ� <html:text name="rs1" property="xsxh" style="display: none"></html:text>
							<input type="text" name="stuxh" value="<bean:write name="rs1" property="xsxh"/>" disabled="true"/>
					&nbsp;&nbsp;&nbsp; ������ <html:text name="rs1" property="name" style="display: none"></html:text>
							<input type="text" name="stuname" value="<bean:write name="rs1" property="name"/>" disabled="true"/>
					<div style="display: none"></div>
					</td>
				</logic:equal>
				<logic:notEqual value="stu" name="userType" scope="session">
				<td>ѧ�ţ� <html:text name="rs1" property="xsxh"></html:text>
				&nbsp;&nbsp;&nbsp; ������ <html:text name="rs1" property="name"></html:text>
				<div style="display: none"></div>
				ѧ�ƣ� <html:text name="rs1" property="xz"></html:text>
				</td>
				</logic:notEqual>
				<td width="10" rowspan="2" align="center" valign="middle">
				<button class="button2" style="height: 40px" onclick="querygo()"
					id="search_go">��ѯ</button>
				</td>
			</tr>
			<tr>
				<td>
				<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" style="width:180px"
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select property="zydm" style="width:190px"
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="bjdm" style="width:190px"
									styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
				</td>
			</tr>
			<!--						<tr>-->
			<!--							<td colspan="2">-->
			<!--								<font color="blue">��ʾ��ѧ�š�����֧��ģ����ѯ��</font>-->
			<!--							</td>-->
			<!--						</tr>-->
		</thead>
	</table>
	</fieldset>
	<logic:empty name="rs">
		<br />
		<br />
		<p align="center">δ�ҵ��κμ�¼��</p>
	</logic:empty>
	<logic:notEmpty name="rs">
		<fieldset><legend> ��¼���� <bean:write name="rsNum" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
		</legend>
		<table width="100%" id="rsTable" class="tbstyle">
			<thead>
				<tr align="center" style="cursor: hand">
					<td><input type="checkbox" name="qbxz" value="all"
						onclick="chec('qbxz')"></td>
					<logic:iterate id="tit" name="topTr" offset="1" length="12">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap="nowrap"><bean:write
							name="tit" property="cn" /></td>
					</logic:iterate>
				</tr>
			</thead>
			<logic:iterate name="rs" id="s">
				<tr onclick="rowOnClick(this)" style="cursor: hand"
					ondblclick="viewMoreinfo('view')">
					<logic:iterate id="v" name="s" offset="0" length="1">
						<input type="hidden" value="<bean:write name="v"/>" />
					</logic:iterate>
					<td align="center"><logic:iterate id="v" name="s" offset="0"
						length="1">
						<input type="checkbox" name="pk" value="<bean:write name="v"/>">
					</logic:iterate></td>
					<logic:iterate id="v" name="s" offset="1" length="1">
						<td align="center"><bean:write name="v" /></td>
					</logic:iterate>
					<logic:iterate id="v" name="s" offset="0" length="1">
						<td align="center"><bean:write name="v" /></td>
					</logic:iterate>
					<logic:iterate id="v" name="s" offset="2" length="8">
						<td align="center"><bean:write name="v" /></td>
					</logic:iterate>
				</tr>
			</logic:iterate>
		</table>
		<TABLE width="99%" id="rsTable1" class="tbstyle">
			<TR>
				<TD height="30px"></TD>
			</TR>
			<TR>
				<TD><jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include></TD>
			</TR>
			<TR>
				<TD height="30px"></TD>
			</TR>
		</TABLE>
		</fieldset>
	</logic:notEmpty>



	<div class="buttontool" id="btn"
		style="position: absolute; left: 0px; top: 100px" width="98%">
	&nbsp;
	<button class="button2" onclick="yxjzyjsadd();" style="width: 80px">
	����</button>
	&nbsp;
	<button class="button2" onclick="bysjbxxbupdate('update')"
		style="width: 80px">�� ��</button>
	&nbsp; 
	<button class="button2" onclick="shczp();" style="width: 80px">
	�ϴ���Ƭ</button>
	&nbsp;
	<button class="button2" onclick="del('/xgxt/bysjytjb.do?act=alldel')"
		style="width: 80px">ɾ��</button>
		
		<logic:equal name="userType" value="xx" scope="session">
	&nbsp;
	<button class="button2" onclick="bysjbxxbupdatesh('update')"
		style="width: 80px">ѧУ���</button>
	</logic:equal>
	<logic:equal name="userType" value="xy" scope="session">
	&nbsp;
	<button class="button2" onclick="bysjbxxbupdatesh('update')"
		style="width: 80px"><bean:message key="lable.xsgzyxpzxy" />���</button>
	</logic:equal>
	<logic:equal name="userType" value="admin" scope="session">
	&nbsp;
	<button class="button2" onclick="bysjbxxbupdatesh('update')"
		style="width: 80px">ѧУ���</button>
	</logic:equal>
	
	<logic:equal value="12453" name="xxdm" scope="session">
	<logic:equal name="who" value="xx">
		<button class="button2" onclick="gnxxsh('shenhe')" style="width: 80px">
		�Ƽ����</button>
		<logic:equal value="1111111" name="xxdm" scope="session">
				&nbsp;
				<button class="button2"
				onclick="pass('/xgxt/bysjytjb.do?act=allAuditing&shenhe=pass')"
				style="width: 80px">����ͨ��</button>
				&nbsp;
				<button class="button2"
				onclick="notpass('/xgxt/bysjytjb.do?act=allAuditing&shenhe=nopass')"
				style="width: 80px">�������</button>
		</logic:equal>
	</logic:equal> 
	</logic:equal>
	<logic:equal name="who" value="teacher1">
				&nbsp;
				<button class="button2" onclick="impAndChkData();"
			style="width: 80px">��������</button>
				&nbsp;
				<button class="button2" onclick="jyglxsjbxxbDataExport()"
			style="width: 80px">��������</button>
	</logic:equal></div>

	<logic:notEmpty name="delete">
		<logic:equal name="delete" value="ok">
			<script>
                      alert("ɾ���ɹ�!");
                      document.getElementById('search_go').click();
                    </script>
		</logic:equal>
		<logic:equal name="delete" value="no">
			<script>
                      alert("ɾ��ʧ��");
                    </script>
		</logic:equal>
	</logic:notEmpty>
	<logic:notEmpty name="delall">
		<logic:equal name="delall" value="ok">
			<script>
                      alert("����ɾ���ɹ�!");
                      document.getElementById('search_go').click();
                    </script>
		</logic:equal>
		<logic:notEqual name="delall" value="ok">
			<script type="text/javascript">
                      alert("����ɾ��ʧ��!");
                      document.getElementById('search_go').click();
                    </script>
				<</logic:notEqual>
	</logic:notEmpty>
	<logic:notEmpty name="allpass">
		<logic:equal name="allpass" value="ok">
			<script>
                       alert("������˳ɹ���");
                       document.getElementById('search_go').click();
                    </script>
		</logic:equal>
		<logic:equal name="allpass" value="no">
			<script>
                      alert("�������ʧ�ܣ�");
                      document.getElementById('search_go').click();
                    </script>
		</logic:equal>
	</logic:notEmpty>
</html:form>
<script type="text/javascript" src="js/bottomButton.js"></script>
</body>
</html>
