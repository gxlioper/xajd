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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
		}
	</script>
	<body>
		<html:form action="ghxyBjryf" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<logic:equal name="isFdy" value="true">
				<logic:notEqual name="isNjzr" value="true">
					<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
				</logic:notEqual>
			</logic:equal>
			<input type="hidden" name="userName" id="userName" value="${userName}" styleId="${userName }"/>
			<input type="hidden" name="realTable" id="realTable" value="ghxy_bjryfb" />
			<div class="title">
				<div class="title_img" id="title_m">
					����λ�ã�${title }		
				</div>
			</div>	
			<div class="xxk">
							<ul>
								<li id="001l"
									class="xxk_off_l"></li>
								<li id="001m"
									onclick="refreshForm('ghxyBjryf.do?method=bjryfWh&lx=wh')" class="xxk_off_m">
									&nbsp;
									�༶������ά��
									&nbsp;
								</li>
								<li id="001r"
									class="xxk_off_r"></li>
							</ul>
							<ul>
								<li id="002l"
									class="xxk_on_l"></li>
								<li id="002m"
									onclick="refreshForm('ghxyBjryf.do?method=bjryfCx')" class="xxk_on_m">
									&nbsp;
									�༶�����ֲ�ѯ
								</li> 
								<li id="002r"
									class="xxk_on_r"></li>
							</ul>
							
			  			</div>		
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								&nbsp;&nbsp;ѧ�꣺
								<html:select property="queryequals_xn" styleId="xn">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;ѧ�ڣ�
								<html:select property="queryequals_xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;�꼶��
								<html:select property="queryequals_nj" styleId="nj"
										onchange="initZyList();initBjList();">
									<logic:notEqual name="njfdy" value="true">
										<html:option value=""></html:option>
									</logic:notEqual>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;���Σ�
								<html:select property="queryequals_pc"  styleId="pc">
									<html:option value=""></html:option>
									<html:options collection="ryfPcList" property="pcdm"
										labelProperty="pcmc" />
								</html:select>	
								&nbsp;&nbsp;ָ�����ƣ�
								<html:select property="queryequals_xmdm"  styleId="zb">
									<html:option value=""></html:option>
									<html:options collection="zbList" property="zbdm"
										labelProperty="zbnr" />
								</html:select>	
							</td>
							<td width="10" align="center" valign="middle" rowspan="2">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go"
									onclick="allNotEmpThenGo('ghxyBjryf.do?method=bjryfCx&doType=qry')" style="height: 25px">
									�� ѯ
								</button>
									<br>
								<button type="button" class="button2" style="height:25px" id="cz"
											onclick="czSearchCond('xn-xq-xy-zy-bj-nj-pc-zb');">
											�� ��
								</button>
							</td>
						</tr>
						<tr>
						<td>
							<!-- �꼶���� -->
							<logic:equal name="isNjzr" value="true">
							  	&nbsp;&nbsp;<bean:message key="lable.xb" />��
							  	<html:select  property="queryequals_xydm" style="width:180px"
							  		 styleId="xy" onchange="initZyList();initBjList();">
							     	<html:option value=""></html:option>
							     	<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
								 </html:select>
							 </logic:equal>
							 
							 <!-- ���꼶���� -->
							 <logic:notEqual name="isNjzr" value="true">
							 	<!-- ѧԺ -->
							 	<logic:equal name="userType" value="xy">
							 		&nbsp;&nbsp;<bean:message key="lable.xb" />��
							 		<html:select  property="xydm" disabled="true" style="width:180px" 
							 		 value="${userDep}" styleId="xy" onchange="initZyList();initBjList();">
							    		 <html:option value=""></html:option>
							    		 <html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								 </html:select>
								  <html:hidden property="queryequals_xydm" value="${userDep}"/>
							 	</logic:equal>
							 	<!-- ��ѧԺԱ -->
								 <logic:notEqual name="userType" value="xy">
									 <logic:notEqual name="userType" value="stu">
									 &nbsp;&nbsp;<bean:message key="lable.xb" />��
									  <html:select  property="queryequals_xydm" style="width:180px" 
								 		  styleId="xy" onchange="initZyList();initBjList();">
								    		 <html:option value=""></html:option>
								    		 <html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									 </html:select>
								     </logic:notEqual>
								     <logic:equal name="userType" value="stu">
									     	<html:select  property="xydm" disabled="true" style="width:180px" 
								 		 		styleId="xy" onchange="initZyList();initBjList();">
								    			 <html:option value=""></html:option>
								    		 	<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
											</html:select>
											<html:hidden property="queryequals_xydm" value="${xydm}"/>
								     </logic:equal>
								 </logic:notEqual>
							 </logic:notEqual>
							 
							&nbsp;&nbsp;רҵ��
							<logic:notEqual name="userType" value="stu">
								<html:select property="queryequals_zydm" style="width:180px" styleId="zy"
									onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</logic:notEqual>
							
							<logic:equal name="userType" value="stu">
								<html:select property="zydm" style="width:180px" styleId="zy"
									disabled="true">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								<html:hidden property="queryequals_zydm" value="${zydm}"/>
							</logic:equal>
							&nbsp;&nbsp;�༶��
							<logic:notEqual name="userType" value="stu">
							<html:select property="queryequals_bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
							</logic:notEqual>
							
							<logic:equal name="userType" value="stu">
							<html:select property="bjdm" style="width:180px" styleId="bj"
									disabled="true">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
								<html:hidden property="queryequals_bjdm" value="${bjdm}"/>
							</logic:equal>
						</td>
						</tr>
						
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rsNum">
				<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
				</logic:empty>
			</logic:empty>
			<logic:notEmpty name="rsNum">
				<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��������ͷ��������</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()">
									
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
									<logic:notEqual name="index" value="1">
										<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
									</logic:notEqual>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>>
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="Table" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=ghxyBjryfForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
				</logic:notEmpty>
			</logic:notEmpty>
			<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%">	
				<logic:equal name="writeAble" value="yes">
					<button type="button" class="button2"
						onclick="showTopWin('ghxyBjryf.do?method=bjryfTj&doType=view',700,500)"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="modi('ghxyBjryf.do?method=bjryfXx&doType=save')" style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataBatch('ghxyBjryf.do?method=bjryfCx&doType=del')"
						style="width:80px">
						ɾ ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="impAndChkData();"
						style="width:80px">
						��������
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:equal>
					<button type="button" class="button2" onclick="wjcfDataExport('ghxyBjryf.do?method=expDate')" style="width:80px">
						��������
					</button>
					</div>
				
				<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
				</script>
				<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:notEqual>
				</logic:present>
		</html:form>
	</body>
</html>
