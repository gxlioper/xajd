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
		<base target="_self">
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
	<script language="javascript">
	function jyxyxxsh(doType){
			var url ="/xgxt/JyglViewMoreinfo.do?doType=view&shenhe=shenhe&pkValue=";
			var pkValue ="";
		
			if (doType == "shenhe") {
		   		if (curr_row == null) {
					alert("��ѡ��Ҫ��˵����ݣ�\n��������Ӧ���У�");
					return false;
		    	} else {	 
		   		 	pkValue = curr_row.getElementsByTagName("input")[0].value;
		    		url += pkValue;
		    		showTopWin(url, 550,400);
		    		return true;
		    	}
		 	}else{
		    	return true;
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
		 	document.forms[0].action = "/xgxt/jyxxtjwh.do?act=go&doType=query";
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
		var url = "/xgxt/jyxxtjwh.do?act=del&pk=";
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
		var url ="/xgxt/jyxxtjwh.do?act=view&pk=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 800, 650);
		 }
		}
		function bysjbxxbupdate(doType){
		var url ="/xgxt/jyxxtjwh.do?act=update&pk=";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
			 } else {
			if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 800, 650);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
		function add() {
	       var url = "/xgxt/jyxxtjwh.do?act=add";
	      	showTopWin(url, 850,650);
        }
		
		function jyglxsjbxxbDataExport() {
	       document.forms[0].action = "/xgxt/jyglxsjbxxbDataExport.do?tableName=zjgsjyxytj";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		
		</script>
	<body>
		<html:form action="/jyxxtjwh" method="post">
			<input type="hidden" id="realTable" name="realTable"
				value="zjgsjyxytj" />
			<input type="hidden" id="tableName" name="tableName"
				value="zjgsjyxytj" />
			<input type="hidden" name="pkstring" value="" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ҵ���� - ѧ����Ϣ - ѧ����Ϣ��ѯ
				</div>
			</div>
			<fieldset>
				<legend>
					�� ѯ
				</legend>

				<table width="100%" class="tbstyle">
					<thead>
						<tr style="cursor:hand">
							<td>
								ѧ�ţ�
								<html:text name="rsq" property="xh" style="width:85px"></html:text>
								&nbsp;&nbsp;������
								<html:text name="rsq" property="xm" style="width:85px"></html:text>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
								<html:select name="rsq" property="xymc" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xymc"
											labelProperty="xymc" />
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button class="button2" style="height:40px" onclick="querygo()"
									id="search_go">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td>
								
							</td>
						</tr>
					</thead>
				</table>
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
								��ʾ��¼����
								<bean:write name="rsNum" />
								&nbsp;
								<font color="blue"><logic:present name="qssj">(<bean:write
											name="qssj" />--</logic:present> <logic:present name="zzsj">
										<bean:write name="zzsj" /> Υ������)</logic:present>
									��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������;��סCtrl���Զ�ѡ</font>
							</legend>

							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')">
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" length="12">
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
								<logic:iterate id="v" name="s" offset="1" length="1">
									<input type="hidden" value="<bean:write name="v"/>" />
								</logic:iterate>
								<td align="center">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="pk"
											value="<bean:write name="v"/>">
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="1" length="12">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
							</table>
							<TABLE width="99%" id="rsTable1" class="tbstyle">
									<TR>
										<TD height=3></TD>
									</TR>
									<TR>
										<TD>
											<jsp:include flush="true"
												page="/sjcz/turnpage.jsp?form=zjgxJyxxForm"></jsp:include>
										</TD>
									</TR>
									<TR>
										<TD height=3></TD>
									</TR>
								</TABLE>
								<br />
								<br />
						</fieldset>
							
					</logic:notEmpty>

			<div class="buttontool" id="btn"
				style="position: absolute;left:0px;top:100px" width="98%">
				<logic:equal name="who" value="teacher">
				<logic:equal value="104911" name="xxdm">
					<button class="button2" onclick="jyxyxxsh('shenhe')"
						style="width:80px">
						���
					</button>
				&nbsp;
				<button class="button2"
					onclick="pass('/xgxt/xsxxsh.do?doType3=pass')"
					style="width:80px">
					����ͨ��
				</button>
				&nbsp;
				<button class="button2"
					onclick="notpass('/xgxt/xsxxsh.do?doType3=notpass')"
					style="width:80px">
					�������
				</button>
				</logic:equal>
				</logic:equal>
				<logic:equal name="who" value="teacher">
				<logic:equal value="103551" name="xxdm">
					<button class="button2" onclick="jyxyxxsh('shenhe')"
						style="width:80px">
						���
					</button>
				&nbsp;
				<button class="button2"
					onclick="pass('/xgxt/xsxxsh.do?doType3=pass')"
					style="width:80px">
					����ͨ��
				</button>
				&nbsp;
				<button class="button2"
					onclick="notpass('/xgxt/xsxxsh.do?doType3=notpass')"
					style="width:80px">
					�������
				</button>
				&nbsp;
				<button class="button2"
					onclick="del('/xgxt/JyglBysjbxxbDelall.do?doType2=query&doType=delall')"
					style="width:80px">
					����ɾ��
				</button>
				&nbsp;
				<button class="button2" onclick="jyglxsjbxxbDataExport()"
						style="width:80px">
						��������
					</button>
				</logic:equal>
				</logic:equal>
				&nbsp;
				<button class="button2"
					onclick="add();"
					style="width:80px">
					����
				</button>
				&nbsp;
				<button class="button2" onclick="bysjbxxbupdate('update')"
					style="width:80px">
					�� ��
				</button>
				&nbsp;
				<button class="button2" onclick="bysjbxxbdel('del')"
					style="width:80px">
					ɾ ��
				</button>
				&nbsp;
				<button class="button2"
						onclick="impAndChkData();"
						style="width:80px">
						��������
					</button>
				
			</div>

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
                      document.getElementById('search_go').click();
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
				<logic:equal name="delall" value="no">
					<script>
                      alert("����ɾ��ʧ��");
                      document.getElementById('search_go').click();
                    </script>
				</logic:equal>
			</logic:notEmpty>
				<logic:notEmpty name="allpass">
				<logic:equal name="allpass" value="ok">
					<script>
                       alert("����ͨ���ɹ���");
                       document.getElementById('search_go').click();
                    </script>
				</logic:equal>
				<logic:equal name="allpass" value="no">
					<script>
                      alert("����ͨ��ʧ�ܣ�");
                      document.getElementById('query_go').click();
                    </script>
				</logic:equal>
			</logic:notEmpty>
			
			<logic:notEmpty name="allnotpass">
				<logic:equal name="allnotpass" value="ok">
					<script>
                       alert("��������ɹ���");
                       document.getElementById('query_go').click();
                    </script>
				</logic:equal>
				<logic:equal name="allnotpass" value="no">
					<script>
                      alert("�������ʧ�ܣ�");
                      document.getElementById('query_go').click();
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
