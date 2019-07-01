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
<script language="javascript">
	function querygo(){
		 	document.forms[0].action = "/xgxt/zphcs.do?act=query&doType=query";
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

		function bysjbxxbupdate(doType){
		var url ="/xgxt/zphcs.do?act=update&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 600, 370);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
		
		
		function dataExport() {
	       document.forms[0].action = "/xgxt/zphcs.do?act=expdata";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		function yxjzyjsadd(){
			 var url = "/xgxt/zphcs.do?act=add";
			 showTopWin(url, 650, 350);
		}
		</script>
<body>
<html:form action="/yxjzyjs.do" method="post">
	<input type="hidden" id="realTable" name="realTable"
		value="<bean:write name='realTable' scope="request" />" />
	<input type="hidden" name="pkstring" value="" />
	<div class="title">
	<div class="title_img" id="title_m">��ǰ����λ�ã���ҵ���� - ��ҵ��Ƹ - ��Ƹ�����</div>
	</div>
	<fieldset><legend> �� ѯ </legend>

	<table width="100%" class="tbstyle">
		<thead>
			<tr style="cursor: hand">
				<td><bean:message key="lable.xsgzyxpzxy" />�� <html:select name="rs1" property="xydm" onchange=""
					style="width:160px" styleId="xy">
					<html:option value=""></html:option>
					<html:options collection="xyList" property="xydm"
						labelProperty="xymc" />
				</html:select> &nbsp;&nbsp;&nbsp; ��Ƹ��� <html:select name="rs1" property="zplx">
					<html:option value=""></html:option>
					<html:option value="������Ƹ��">������Ƹ��</html:option>
					<html:option value="��λ������">��λ������</html:option>
				</html:select></td>
				<td width="10" rowspan="2" align="center" valign="middle">
				<button class="button2" style="height: 40px" onclick="querygo()"
					id="search_go">��ѯ</button>
				</td>
			</tr>
			<tr>

			</tr>
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
				<tr onclick="rowOnClick(this)" style="cursor: hand" ondblclick="">
					<logic:iterate id="v" name="s" offset="0" length="1">
						<input type="hidden" value="<bean:write name="v"/>" />
					</logic:iterate>
					<td align="center"><logic:iterate id="v" name="s" offset="0"
						length="1">
						<input type="checkbox" name="pk" value="<bean:write name="v"/>">
					</logic:iterate></td>
					<logic:iterate id="v" name="s" offset="1" length="12">
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
	<button class="button2" onclick="del('/xgxt/zphcs.do?act=alldel')"
		style="width: 80px">����ɾ��</button>
	</div>

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
</html:form>
<script type="text/javascript" src="js/bottomButton.js"></script>
</body>
</html>
