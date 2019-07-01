<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
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
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script language="javascript">
		function del(url){
			var RowsStr="";	
			for (i=0; i<document.getElementsByName("pk").length; i++){
		    	if(document.getElementsByName("pk")[i].checked){
		    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
		    	}
			}
			document.forms[0].cbVal.value = RowsStr;	
			if (RowsStr==""){
				alert("��ѡ��Ҫ����ɾ���ļ�¼��");
				return false;
			}
			document.forms[0].action=url;
		    document.forms[0].submit();
		}
		/**view*/
		function chkAssisOne(url) {
			if (curr_row == null) {
				alert("��ѡ��Ҫѡ���������\n���˫��");
				return false;
			} else {
				url += "&pkVal=";    //���pkVal��myForm��װ����������myFrom��getPkVal()�õ�����
				url += curr_row.getElementsByTagName("input")[0].value;
				if($("sqms")){
					url += "&sqms=true";
				}
				showTopWin(url, 750, 550);
				return true;
			}
		}
		
		function add(url){
			return showTopWin(url,750,550);
		}
		
		function modi(url){
			if((curr_row == null) || (curr_row == "")){
				alert("��ѡ��Ҫ�޸ĵļ�¼��");
				return false;
			}
			url += "&pkVal=";
			url += curr_row.getElementsByTagName("input")[0].value;
			if($("sqms")){
				url += "&sqms=true";
			}
			return showTopWin(url,750,550);
		}
		
		/**��������*/
		function dataExport2() {
			document.forms[0].action = "/xgxt/xszz_ynys.do?method=common_exp";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
	</script>
	<body onload="xyDisabled('xy');initNdList();initNjList();initOnLoadXyList();">
		<html:form action="/xszz_ynys.do?method=szflzjxjwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					${title}
				</div>
			</div>
			<input type="hidden" id="userType" name="userType" 
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="session"/>" />
			<logic:notEmpty name="form" property="sqms">
				<input type="hidden" id="sqms" name=sqms
				value="<bean:write name="form" property="sqms" scope="request"/>" />
			</logic:notEmpty>
			<input type="hidden" id="isFdy" name="isFdy" value="${isFdy}"/>
			<input type="hidden" id="isBzr" name="isBzr" value="${isBzr}"/>
			<input type="hidden" name="xyV" value="<bean:write property="xydm" name="form"/>" id="xyV"/>
			<input type="hidden" name="zyV" value="<bean:write property="zydm" name="form"/>" id="zyV"/>
			<input type="hidden" name="bjV" value="<bean:write property="bjdm" name="form"/>" id="bjV"/>
			<input type="hidden" name="njV" value="<bean:write property="nj" name="form"/>" id="njV"/>
			<input type="hidden" name="ndV" value="<bean:write property="nd" name="form"/>" id="ndV"/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<fieldset>
				<legend>
					����ѡ��
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								�꼶��
								<html:select property="nj" onchange="initOnLoadXyList();" 
								styleId="nj" style="width:90px;padding-left:80px">
								</html:select>&nbsp;&nbsp;
								��ȣ�
								<html:select property="nd" 
								styleId="nd" style="width:90px;padding-left:80px">
								</html:select>&nbsp;&nbsp;
								ѧ�ţ�
								<html:text name="form" property="xh" styleId="xh" style="width:100px;inputtext"
								styleClass="inputtext"></html:text>&nbsp;&nbsp;
								������
								<html:text  name="form" property="xm" styleId="xm" style="width:100px;inputtext"
								styleClass="inputtext"></html:text>
								<logic:empty name="form" property="sqms">
									������
									<html:select property="shlb" name="form"
										styleId="shlb" style="width:90px;padding-left:100px">
										<html:option value=""></html:option>
										<html:options collection="checkList" property="en"
											labelProperty="cn" />
									</html:select>
								</logic:empty>
							</td>	
							<logic:notEmpty name="form" property="sqms">				
								<td width="10" rowspan="3" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/xszz_ynys.do?method=szflzjxjwh&go=go&doType=search')">
										��ѯ
									</button>
								</td>
							</logic:notEmpty>
							<logic:empty name="form" property="sqms">
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/xszz_ynys.do?method=szflzjxjwh&go=go&doType=search')">
										��ѯ
									</button>
								</td>
							</logic:empty>
						</tr>
						<tr>	
							<td>
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" onchange="initOnLoadZyList()"
								 style="width:180px" styleId="xy">
								</html:select>&nbsp;&nbsp;
								רҵ��
								<html:select property="zydm" onchange="initBjList()" 
								style="width:180px" styleId="zy">
								</html:select>&nbsp;&nbsp;
								�༶��
								<html:select property="bjdm" style="width:180px" styleId="bj">
								</html:select>
							</td>
						</tr>
						<logic:notEmpty name="form" property="sqms">
							<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />��ˣ�
								<html:select property="xysh" name="form"
									styleId="xysh" style="width:90px;padding-left:100px">
									<html:option value=""></html:option>
									<html:options collection="checkList" property="en"
										labelProperty="cn" />
								</html:select>
								ѧУ��ˣ�
								<html:select property="xxsh" name="form"
									styleId="xxsh" style="width:90px;padding-left:100px">
									<html:option value=""></html:option>
									<html:options collection="checkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							</tr>	
						</logic:notEmpty>
					</thead>
				</table>
			</fieldset>
			<div id="result">
				<div class="searchcontent">
					<logic:empty name="rs">
						<p align="center">
							δ�ҵ��κμ�¼��
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								��¼����
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="${tit.en}" onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;background-color:<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
										ondblclick="chkAssisOne('/xgxt/xszz_ynys.do?method=szflzjxjpre&doType=view')">
									  <td align=center>
										<logic:notEmpty name="form" property="sqms">
											<input type="checkbox" id="pk" name="pk" 
												value="<logic:iterate id="v" name="s" offset="0" length="2"><bean:write name="v"/></logic:iterate>" />
											<input type="hidden" 
												value="<logic:iterate id="v" name="s" offset="0" length="2"><bean:write name="v"/></logic:iterate>" />
									    </logic:notEmpty>
									    <logic:empty name="form" property="sqms">
											<input type="checkbox" id="pk" name="pk" 
												value="<logic:iterate id="v" name="s" offset="1" length="2"><bean:write name="v"/></logic:iterate>" />
											<input type="hidden" 
												value="<logic:iterate id="v" name="s" offset="1" length="2"><bean:write name="v"/></logic:iterate>" />
									    </logic:empty>
									    </td>
									    <logic:notEmpty name="form" property="sqms">
									    	<logic:iterate id="v" name="s" offset="1">
												<td align=center>
													<bean:write name="v" />
												</td>
											</logic:iterate>	
									    </logic:notEmpty>
									    <logic:empty name="form" property="sqms">
									    	<logic:iterate id="v" name="s" offset="2">
												<td align=center>
													<bean:write name="v" />
												</td>
											</logic:iterate>		
									    </logic:empty>				
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble" scope="request">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
						<logic:equal value="true" name="form" property="sqms">	
							<button type="button" class="button2" onclick="add('/xgxt/xszz_ynys.do?method=szflzjxjpre&sqms=true');"
								style="width:80px">
								�� ��
							</button>&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>		
							<button type="button" class="button2" onclick="modi('/xgxt/xszz_ynys.do?method=szflzjxjpre&doType=modi');"
								style="width:80px">
								�� ��
							</button>&nbsp;&nbsp;&nbsp;&nbsp;					
							<button type="button" class="button2" onclick="del('/xgxt/xszz_ynys.do?method=szflzjxjwh&doType=del');"
								style="width:80px">
								ɾ ��
							</button>&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="impAndChkData();"
								style="width:80px">
								��������
							</button>&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport2()" style="width:80px">
								��������
							</button>&nbsp;&nbsp;
							<a href="#" onclick="dataStencilExport()">ģ������</a>
						</div>
					</logic:equal>
					<div id="tmpdiv"></div>
				</div>
			</div>	
		</html:form>
		 <script type="text/javascript" src="js/bottomButton.js"></script> 
		<logic:present name="ok" scope="request">
		<logic:match value="ok" name="ok">
			<script language="javascript">
	   			alert("ɾ���ɹ���");  	
	        </script>
		</logic:match>
		<logic:match value="no" name="ok">
			<script language="javascript">
	        	alert("ɾ��ʧ�ܣ�");
	        </script>
		</logic:match>
	</logic:present>		 
	</body>
</html>
