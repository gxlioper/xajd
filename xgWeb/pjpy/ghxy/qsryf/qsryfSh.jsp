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
	<script language="javascript" src="js/pjpy/ghxy/qsryfCx.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/initQsxx.js"></script>
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
		function checkAjax(){
			if($("lc").value=="null"){
				$("lc").value="";
			}
			if($("qsh").value=="null"){
				$("qsh").value="";
			}
			refreshForm('/xgxt/ghxyQsryf.do?method=qsryfSh&doType=qry');
		}
		var shjg="";
		var url="";
		function chkData(){
			var isQyfdy="";
			var userType="";
			isQyfdy=$("isQyfdy").value;
			userType=$("userType").value;
			shjg=$("shjg").value;
			var dj="";
			if($("stdj")){
				dj=$("stdj").value;
			}
			if(dj!=""){
			    url="ghxyQsryf.do?method=qsryfSh&doType=modi&shjg="+shjg;
			    url+="&dj="+dj;
				dataBatch(url);
			}else{
				if(userType=="xx" || userType=="admin"){
				    url="ghxyQsryf.do?method=qsryfSh&doType=modi&shjg="+shjg;
					dataBatch(url);
				}else{
				    alert("����ѡ��༶�����ֵȼ���");
				    return false;
				}
				
			}
		}
		function tg(){
			$("shjg").value='ͨ��';
		}
		function btg(){
			$("shjg").value='��ͨ��';
		}
		function checkQx(){
			var isQyfdy="";
			if($("isQyfdy")){
				isQyfdy=$("isQyfdy").value;
			}
			if(isQyfdy!="true"){
			 	chkData();
			}else{
			 	djzh();
			}
			
		}
		function djzh(){
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 250;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 2;
		var d_top_top = (d_height - d_height_top)/ 2;
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
			dd_html += "<table width='300' class='tbstyle'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2'>";
		dd_html += "----------------��ѡ��ȼ�---------------";
		dd_html += "</td>";
			dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
	
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "��ѡ���:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='stdj' id ='stdj'>" 
		dd_html += $('dj').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
	
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick='chkData()'>ȷ��</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>�ر�</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		document.getElementById('tmpdiv1').innerHTML = dd_html;
	}
	</script>
	<body>
		<html:form action="ghxyQsryf" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="realTable" id="realTable" value="nbty_xszz_jtjjkns" />
			<input type="hidden" name="isQyfdy" id="isQyfdy" value="${isQyfdy}"/>
			<input type="hidden" name="shjg" id="shjg" styleId="shjg"/>
			<input type="hidden" name="userType" id="userType" value="${userType}" styleId="userType"/>
			<div class="title">
				<div class="title_img" id="title_m">
					����λ�ã�${title }				
				</div>
			</div>			
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								<!-- �༶�����ȼ������� -->
								<html:select property="save_djdm" style="display : none" styleId='dj' >
									<html:options collection="ryfdjList" property="djdm"
										labelProperty="djmc" />
								</html:select>
								&nbsp;&nbsp;ѧ�꣺
								<html:select property="xn" styleId="xn" disabled="true" value="${xn}">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								<html:hidden property="queryequals_xn" value="${xn}"/>
								&nbsp;&nbsp;ѧ�ڣ�
								<html:select property="xq" styleId="xq" disabled="true" value="${xq}">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								<html:hidden property="queryequals_xq" value="${xq}"/>
								&nbsp;&nbsp;�꼶��
								<html:select property="queryequals_nj" styleId="nj">
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;��Ԣ԰����
								<html:select property="queryequals_yqqdm" styleId="yqdm">
									<html:option value=""/>
									<html:options collection="yqqdmList" property="yqqdm"
										labelProperty="yqqmc" />
								</html:select>	
								&nbsp;&nbsp;���Σ�
								<html:select property="queryequals_pc" styleId="pc">
									<html:options collection="ryfPcList" property="pcdm"
										labelProperty="pcmc" />
								</html:select>	
							</td>
							<td width="10" align="center" valign="middle" rowspan="2">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go"
									onclick="checkAjax()" style="height: 25px">
									�� ѯ
								</button>
								<br>
								<button type="button" class="button2" style="height:25px" id="cz"
											onclick="czSearchCond('xn-xq-yqdm-lddm-lc-qsh-pc-nj');">
											�� ��
								</button>
							</td>
						</tr>
						<tr>
						<td>
						<logic:equal name="userType" value="admin">
								&nbsp;¥������
								<html:select property="queryequals_lddm"  styleId="lddm"
								onchange="getLcList()">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm"
									labelProperty="ldmc" />
								</html:select>
								&nbsp;¥�㣺
					    		<html:select property="queryequals_cs" styleId="lc"
						   			onchange="getQshList2()">
						   			<html:option value=""></html:option>
									<html:options collection="lcList" property="dm"
									labelProperty="mc" />
								</html:select>
								&nbsp;���Һţ�
								<html:select property="queryequals_qsh"  styleId="qsh" >
									<html:option value=""></html:option>
									<html:options collection="qshList" property="dm" 
									 labelProperty="mc" />
								</html:select>
							</logic:equal>	
							<logic:equal name="userType" value="xx">
								&nbsp;¥������
								<html:select property="queryequals_lddm"  styleId="lddm"
								onchange="getLcList()">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm"
									labelProperty="ldmc" />
								</html:select>
								&nbsp;¥�㣺
					    		<html:select property="queryequals_cs" styleId="lc"
						   			onchange="getQshList2()">
						   			<html:option value=""></html:option>
									<html:options collection="lcList" property="dm"
									labelProperty="mc" />
								</html:select>
								&nbsp;���Һţ�
								<html:select property="queryequals_qsh"  styleId="qsh" >
									<html:option value=""></html:option>
									<html:options collection="qshList" property="dm" 
									 labelProperty="mc" />
								</html:select>
							</logic:equal>
							<logic:notEqual name="userType" value="xx">
								<logic:notEqual name="userType" value="admin">
								&nbsp;¥������
								<html:select property="queryequals_lddm"  styleId="lddm" 
								  onchange="initCs();initQsh()">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm"
									labelProperty="ldmc" />
								</html:select>
								&nbsp;¥�㣺
					    		<html:select property="queryequals_cs" styleId="lc" onchange="initQsh()"
						   			>
						   			<html:option value=""></html:option>
									<html:options collection="lcList" property="cs"
									labelProperty="cs" />
								</html:select>
								&nbsp;���Һţ�
								<html:select property="queryequals_qsh"  styleId="qsh" >
									<html:option value=""></html:option>
									<html:options collection="qshList" property="qsh" 
									 labelProperty="qsh" />
								</html:select>
								</logic:notEqual>
							</logic:notEqual>
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
			<div id="tmpdiv1">
				
				</div>
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
								<logic:iterate id="tit" name="topTr" offset="3" indexId="index">
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
										<input type="hidden" name="xqArr" value="<logic:iterate id="v" name="s" offset="2" length="1">${v}</logic:iterate>"/>
										<input type="hidden" name="xnArr" value="<logic:iterate id="v" name="s" offset="3" length="1">${v}</logic:iterate>"/>
										<input type="hidden" name="checkVal" value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"/>
										<input type="hidden" name="plssbh" value="<logic:iterate id="v" name="s" offset="5" length="1">${v}</logic:iterate>"/>
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
								page="/sjcz/turnpage.jsp?form=ghxyQsryfForm"></jsp:include>
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
			 		<button type="button" class="button2" onclick="modi('ghxyQsryf.do?method=qsryfShOne&doType=view')"
						style="width:80px">
						�������
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="tg();checkQx();"
						style="width:80px">
						���ͨ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="btg();checkQx();"
						style="width:80px">
						��˲�ͨ��
					</button>
				</logic:equal>
					</div>
				<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
				</script>
				<logic:present name="result">
				<logic:equal name="bjbl" value="no">
					<script>
						alert('�����õȼ��޶��İ༶������');
					</script>		
				</logic:equal>
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
