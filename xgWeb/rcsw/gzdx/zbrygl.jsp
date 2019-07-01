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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="/xgxt/js/function.js"></script>
	<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
	<script type="text/javascript">
		function commomOperation(type,url,chkName,sendVal){
			var num = 0;
			var pkVals = "!@!";
			if("query" == type){
				refreshForm(url);
			}else if("add" == type ){
				showTopWin(url,"700","450");
			}else if("update" == type || "view" == type || "delete" == type){
				var pks = document.getElementsByName(chkName);
				for(var i=0; i<pks.length; i++){
					if(pks[i].checked == true){
						num++;
						pkVals +=pks[i].value+"!@!"; 
					}
				}
				if(num == 0){
					alert("��ѡ����Ҫ�����ļ�¼��");
					return  false;
				}
				if("update" == type || "view" == type){
					if(num > 1){
						alert("һ��ֻ�ܲ���һ����¼��");
						return  false;
					}else{
						pkVals = replaceChar(pkVals,"!@!","");
						url += "&"+sendVal+"="+pkVals;
					}
					showTopWin(url,"700","450");
				}else{
					if($('pkVStr')){
						$('pkVStr').value = pkVals;
					}else{
						url += "&"+pkVStr+"="+pkVals;
					}
					refreshForm(url);
				}			
			}
		}
 		function getRqVal(name){
			var rq=document.getElementById(name).value;
			if (rq!=""){
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++){
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
		}
		function checkNoDisabled(){
			var array = document.getElementsByName("pkV");
     		for(i=0;i<array.length;i++){
     			if(array[i].disabled != true){
     				array[i].checked=document.getElementsByName("all")[0].checked;
     			}
      			
      		}
    	}
    	function saveZbsz(){
    		var num = 0;
    		var pkVals = '!@!';
    		var pk = $('pk').value;
    		var pks = document.getElementsByName('pkV');
			for(var i=0; i<pks.length; i++){
				if(pks[i].checked == true){
					num++;
					pkVals +=pks[i].value+"!@!"; 
				}
			}
			if(num == 0){
				alert("�빴ѡ��Ӧ��ֵ����Ա��");
				return  false;
			}else{
				window.dialogArguments.document.forms[0].action='/xgxt/zbrygl.do?method=zbsz&doType=save&pk='+pk+'&pkVStr='+pkVals;
				window.dialogArguments.document.forms[0].submit();
				Close();
			}
    	}
	</script>
	<body>
		<center>
			<html:form action="/zbrygl" method="post">
				<logic:equal value="no" name="view">
					<br>
					<br>
					<br>
					<p align="center">
						<font color="red" size="2">
						��ҳ��ֻ�����ʦ��ѧ������Ա���ʣ�
						</font>
					</p>
				</logic:equal>
				<logic:equal value="wqy" name="view">
					<br>
					<br>
					<br>
					<p align="center">
						<font color="red" size="2">
						����ѧ������ԱȨ��û���ã�
						</font>
					</p>
				</logic:equal>
				<logic:notEqual value="no" name="view">
				<logic:notEqual value="wqy" name="view">
					<input type="hidden" name="pkVStr" id="pkVStr" value="" />
					<input type="hidden" name="realTable" id="realTable" value="<bean:write name="realTable"/>"/>
					<input type="hidden" name="tableName" id="tableName" value="<bean:write name="tableName"/>"/>
					<input type="hidden" name="pk" id="pk" value="${pk }"/>
					<input type="hidden" name="doType2" id="doType2" value="${doType2 }"/>
					<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />
					<div class="title">
						<div class="title_img" id="title_m">
							��ǰ����λ�ã��ճ����� - ֵ����Ա���� - ֵ����Ա��
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
										<bean:message key="lable.xsgzyxpzxy" />���ƣ�
										<html:select property="xydm" style="width:220px" styleId="xy" 
											onchange="initZyList();initBjList();">
											<html:option value="">--��ѡ��--</html:option>
											<html:options property="xydm" labelProperty="xymc"
													collection="xyList" />
										</html:select>								
										&nbsp;&nbsp;&nbsp; רҵ���ƣ�
										<html:select property="zydm" style="width:220px" styleId="zy" 
											onchange="initBjList();">
											<html:option value="">--��ѡ��--</html:option>
											<html:options property="zydm" labelProperty="zymc"
												collection="zyList" />
										</html:select>
									</td>
									<td width="10" rowspan="2"  align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="button2"  id="search_go" style="height: 40px"
											onclick="commomOperation('query','/xgxt/zbrygl.do?method=zbrygl&doType=query');">
											��ѯ
										</button>
									</td>
								</tr>
								<tr>
									<td align="left">
										�༶���ƣ�
										<html:select property="bjdm" style="width:250px" styleId="bj">
											<html:option value="">--��ѡ��--</html:option>
											<html:options property="bjdm" labelProperty="bjmc"
													collection="bjList" />
										</html:select>
										&nbsp;&nbsp;&nbsp; ѧ�ţ�
										<html:text property="xh"></html:text>
										&nbsp;&nbsp;&nbsp; ������
										<html:text property="xm"></html:text>
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
								��<bean:write name="rsNum"/>��¼&nbsp;&nbsp;
								<font color="blue">��ʾ��������ͷ��������</font>
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<td align="center">
											<input type="checkbox" name="all" value="all"
												onclick="chec()">
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand">
										<td align="center">
											<input type="checkbox" name="pkV"
												value="<bean:write name="s" property="xh"/>">
										</td>
										<td align="center">
											<bean:write name="s" property="xh" />
										</td>
										<td align="center">
											<bean:write name="s" property="xm" />
										</td>
										<td align="center">
											<bean:write name="s" property="xb" />
										</td>
										<td align="center">
											<bean:write name="s" property="xymc" />
										</td>
										<td align="center">
											<bean:write name="s" property="zymc" />
										</td>
										<td align="center">
											<bean:write name="s" property="bjmc" />
										</td>
										<td align="center">
											<bean:write name="s" property="bz" />
										</td>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
						<br />
						<br />
					</logic:notEmpty>
					<div id="tmpdiv"></div>
			        <div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
					<center>
						<div class="buttontool" id="btn"
						style="position: absolute;left:1%;" width="100%">
							<logic:notEqual value="zbsz" name="doType2">
							<button type="button" class="button2" onclick="commomOperation('add','/xgxt/zbrygl.do?method=operateZbryxx&act=add')" style="width:60px">
								�� ��
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="commomOperation('update','/xgxt/zbrygl.do?method=operateZbryxx&act=update','pkV','pkVStr')" style="width:60px">
								�� ��
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="commomOperation('delete','/xgxt/zbrygl.do?method=zbrygl&doType=delete','pkV','pkVStr')" style="width:60px">
								ɾ ��
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="impAndChkData()" style="width:60px">
								�� ��
							</button>	
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:60px">
								�� ��
							</button>
							</logic:notEqual>
							<logic:equal value="zbsz" name="doType2">
								<button type="button" class="button2" onclick="saveZbsz()" style="width:60px">
									�� ��
								</button>
							</logic:equal>
						</div>
					</center>
				</logic:notEqual>
				</logic:notEqual>
			</html:form>
			
		</center>
	</body>
	<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "98%";
					window.setInterval("initBTNTool('btn')",1);
	</script>
	<logic:equal value="true" name="result">
		<script language="javascript">
			alert('�����ɹ���');
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script language="javascript">
			alert('����ʧ�ܣ�');
		</script>
	</logic:equal>
</html>
