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

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body
		onload="bjDisabled('nj-xy-zy-bj');removeXnXq();myxyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>

		<html:form action="/csmz_sztz.do?method=data_search" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
			<input type="hidden" name="userType" id="userType"
				value="<bean:write name="userType" scope="request"/>">
			<input type="hidden" name="userName" id="userName"
				value="<bean:write name="userName" scope="session"/>">
			<input type="hidden" id="delPk" name="delPk" value="" />
			<input type="hidden" id="pkVStr" name="pkVStr"
				value="" />					
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�
					<bean:write name="tips" scope="request" />
				</div>
			</div>

			<fieldset>
				<legend>
					�� ѯ
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								�꼶��
								<html:select property="nj" style="width:80px"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								<logic:equal value="no" name="ptcx">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧ�꣺
										<html:select property="xn" style="width:100px" styleId="xn"
										onchange="genNdList(this)">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧ�ڣ�
										<html:select property="xq" style="width:90px" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</logic:equal>
								&nbsp;&nbsp;&nbsp;ѧ�ţ�
								<html:text property="xh" style="width:85px"></html:text>
								&nbsp;&nbsp;&nbsp;������
								<html:text property="xm" style="width:85px"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="document.forms[0].go.value='go';refreshForm('/xgxt/csmz_sztz.do?method=data_search&act='+$('act').value)">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" >
								<bean:message key="lable.xsgzyxpzxy" />(����)��
								<html:select property="xydm" style="width:180px" styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select property="zydm" style="width:180px" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								<br>���״̬��
								<html:select property="yesNo" style="width:80px" styleId="yesNo">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>

				<ul style="color: red;list-style:none;">
					<li>
						1���ѷ���Ȩ�޵İ�ɲ��û�ֻ�ܲ鿴����˱���ѧ���걨����Ŀ�걨�ɹ���
					</li>
					<li>
						2���ѷ���Ȩ�޵�<bean:message key="lable.xsgzyxpzxy" />(����)�û�ֻ�ܲ鿴����˱�<bean:message key="lable.xsgzyxpzxy" />��ѧ���걨����Ŀ�걨�ɹ�(�򱾲�������Ŀ�걨�ɹ�)��
					</li>
					<li>
						3���ѷ���Ȩ�޵�ѧУ�û��ܲ鿴��������е���Ŀ�걨�ɹ���
					</li>
					<li>
						4��������̣�������Ŀ�걨�ɹ�����&nbsp;&nbsp;'��ɲ�'->'<bean:message key="lable.xsgzyxpzxy" />(����)'->'ѧУ'&nbsp;&nbsp;������ˣ�
					</li>
					
				</ul>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ˣ�������ͷ��������;</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
										<input type="checkbox" name="fyxx" value="all"
											onclick="chec()">
									</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);"
								style="cursor:hand;background-color:
                                        <logic:iterate id="v" name="s" offset="0" length="1">
                                        <bean:write name="v"/>
                                        </logic:iterate>
                                        "
								ondblclick="CheckDo()">
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pkV"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:equal value="xx" name="userType" >
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">					
					<button class="button2" onclick="xmShBatch('yes')"
								style="width:80px">
							 ͨ  ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="xmShBatch('no')"
								style="width:80px">
							 ��ͨ��
					</button>						
					</div>
					</logic:equal>
<%--				<div class="buttontool" id="btn"--%>
<%--					style="position: absolute;left:1%;top:100px" width="100%">--%>
<%--					<button class="button2"--%>
<%--						onclick="allBjSh('/xgxt/csmz_sztz.do?method=all_check&bjdm='+document.forms[0].bjdm.value)"--%>
<%--						style="width:80px">--%>
<%--						ȫ��ͨ��--%>
<%--					</button>--%>
<%--				</div>--%>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
	<logic:equal name="autoChk" value="ok" scope="request">
		<script language="javascript">
                   alert("�Զ������ɣ�");
                   document.getElementById('search_go').click();	      
	    </script>
	</logic:equal>
	<logic:equal name="autoChk" value="no" scope="request">
		<script language="javascript">
                 alert("�Զ����ʧ�ܣ�");
                 document.getElementById('search_go').click();	      
 	    </script>
	</logic:equal>
	<script type="text/javascript">
function chec(){
        for(i=0;i<document.getElementsByName("pkV").length;i++){
      	document.getElementsByName("pkV")[i].checked=document.getElementsByName("fyxx")[0].checked;
       }
}	
function CheckDo(){
    var	w = 700;
	var	h = 500;
	var realTable = document.getElementById("realTable").value;
	url = "/xgxt/csmz_sztz.do?method=tzcg_sh";	
	url += "&pkValue=";
	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
	showTopWin(url, w, h);	
}

function allBjSh(url){
    confirmTxt = "ȫ��ͨ����˽���ĳѧ�ꡢѧ����Ŀ\n���༶Ϊ��λ��������ͨ�����! \n";
    if(document.forms[0].xn.value==""){
		alert(confirmTxt+"��ѡ��ѧ�꣡");
		return false;
    }
    if(document.forms[0].xq.value==""){
        alert(confirmTxt+"��ѡ��ѧ�ڣ�");
        return false;
    }
    if (document.forms[0].bjdm.value == "") {
		alert("��ѡ��༶��");
		return false;
	}else{
	   confirmTxt = "ȫ��ͨ����˽��ķѽϳ���ʱ�䣬ȷ��Ҫ��ʼ�Զ������";
		if (confirm(confirmTxt)) {		
		    alert("���\"ȷ��\"��ʼ��ˣ�");		    
		    sztzShowTips('���ڽ���������ˣ����Ժ�......');
			refreshForm(url);
			return true;
		} else {
			return false;
		}
	}	
}
function myxyDisabled(ele) {
    var userT = document.getElementById("userType").value;
	if (userT == "xy"||userT =="stu") {
		var tmp = ele.split("-");
		for (i = 0; i < tmp.length; i++) {
			document.getElementById(tmp[i]).disabled = true;
		}
	}
}
function xmShBatch(str){
           var url = "/xgxt/csmz_sztz.do?method=plCheck&check="+str; 
		   var RowsStr="!!";		  
		   var userType = $("userType").value;		   
		   xyshDone = (str=="yes")?"ͨ��":"��ͨ��";
		   var pkVArray = "'";
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    		 pkVArray+=document.getElementsByName("pkV")[i].value+"','";
	    	  }	    	  
		   }
		   
		   if (RowsStr=="!!"){
			   alert("��ѡ��Ҫ�����ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
			   return false;
		   }
		   document.forms[0].pkVStr.value = RowsStr;
		   pkVArray=pkVArray.substring(0,pkVArray.length-2);
		   		      
		   getSztzData.getInfoEx2("csmz_tzcgb"," rowid in ("+pkVArray+") and xysh<>'ͨ��' ",function(boolean){
		        if(boolean){
		             if (confirm("��ѡ�м�¼�У���<bean:message key="lable.xsgzyxpzxy" />���\"��ͨ��\"��\"δ���\"�ļ�¼��\n\nȷ��Ҫ\""+xyshDone+"\"��ѡ��¼��")){
		             refreshForm(url);   
		             }
		        }else{		            		
		         if (confirm("ȷ��Ҫ\""+xyshDone+"\"��ѡ��¼��")){
			        refreshForm(url);   
		         }
		       }
		   });
}
</script>
</html>
