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

		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="/style/main.css" type="text/css" media="all" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
		
		<base target="_self">
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/commanFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script>
		function ljsdaUpdate(url,w,h){	
			var pk="";	
			if(curr_row == null ){
					alert("��ѡ��һ�м�¼��\n����һ�м���!");
					return false;
				} 	
					
			pk= curr_row.cells[0].getElementsByTagName("input")[0].value;			
			url+=pk;
			
			if(w==""||w==null){
				w = 800;
			}
			if(h==""||h==null){
				h = 600;
			}
			showTopWin(url,w,h);		
		}
	
	
		function addInfo(){
			if(curr_row == null){
				alert("�˲�����Ҫ��ѡ�е��У�����Ҫ��ӱ�ע���У�");
				return false;
			}
			showTopWin("addStuInfo.do?xh=" + curr_row.cells[1].innerText,400,300,false);
		}
	
		function chec(){
	      for(i=0;i<document.getElementsByName("pk").length;i++){
	      	document.getElementsByName("pk")[i].checked=document.getElementsByName("xsxx")[0].checked;
	      }
	    }
    
        
		function batch(url,oper){
			var RowsStr="!!";		
			for (i=0; i<document.getElementsByName("pk").length; i++){
		    	if(document.getElementsByName("pk")[i].checked){
		    		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
		    	}
			}
			document.forms[0].delPk.value = RowsStr;
			
			if (RowsStr=="!!"){
				alert("��ѡ��Ҫ���������ļ�¼��");
				return false;
			}
			
			if (!confirm("ȷ��Ҫ������ѡ��¼��")){
				return false;
			}
			if(oper=="del"){
				refreshForm(url);
			}else{
				url += "&pk=";
				url += RowsStr;
				showTopWin(url,400,300);
			}
		}

		function batchOper(url){
			var RowsStr="!!";		
				for (i=0; i<document.getElementsByName("pk").length; i++){
			    	if(document.getElementsByName("pk")[i].checked){
			    		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
			    	}
				}
				document.forms[0].delPk.value = RowsStr;
				
				if (RowsStr=="!!"){
					alert("��ѡ��Ҫ���������ļ�¼��");
					return false;
				}
				
				if (!confirm("ȷ��Ҫ������ѡ��¼��")){
					return false;
				}
				
				url += "&pk=";
				url += RowsStr;
				refreshForm(url);	
		}

		function check_user(){
			var user=document.all['userType'].value;
			if("xy"==user){
				document.getElementById('xydm').disabled=true;
			}else if("xx"==user){
				document.getElementById('xydm').disabled=false;
			}
		}
		
		function xy_dataExport1(){
			var url='/xgxt/expData.do?tableName=view_xsjbxx';
			var xydm=document.getElementById('xydm').value;
			url=url+'&xydm='+xydm;
			dataExport1(url);
		}
		
		function showTr(){
			var xxdm = document.getElementById("xxdm").value;
			document.all.temDiv.style.display=(document.all.temDiv.style.display =='none')?'':'none';
			document.all.jgsdxDiv.style.display=(document.all.jgsdxDiv.style.display =='none')?'':'none';
		}		
		function getValue(){
			if(document.getElementById("jgshen").value!=""){
				var shenV = document.getElementById("jgshen").options[document.getElementById("jgshen").selectedIndex].text;
				document.getElementById("jgz").value='%' + shenV + '%';
			}
			if(document.getElementById("jgshi").value!=""){
				var shiV = document.getElementById("jgshi").options[document.getElementById("jgshi").selectedIndex].text;
				document.getElementById("jgz").value += '%' + shiV + '%';
			}
			if(document.getElementById("jgxian").value!=""){
				var xianV = document.getElementById("jgxian").options[document.getElementById("jgxian").selectedIndex].text;
				document.getElementById("jgz").value += '%' + xianV + '%';
			}
		}
		
		function writeCondition(){
			var	ele = 'xh-xm-xb-sfzh-ssbh-jgshen-jgshi-jgxian-zw-syd-csrq-byny-rxrq-sjhm-nj-xy-zy-bj-mz-zzmm-xjztm-xz-pycc-sfdk-jtnsrd-jtnsrg-jtcyxm';
			var strs = ele.split('-');			
			var tmp = "";
			for(var i=0; i<strs.length; i++){
				if(document.getElementById(strs[i])){
					if(document.getElementById(strs[i]).value != ''){
						
						if(strs[i] == 'xy'){
							tmp += "@xydm!!" + val(strs[i]);
						}else if(strs[i] == 'zy'){
							tmp += "@zydm!!" + val(strs[i]);
						}else if(strs[i] == 'bj'){
							tmp += "@bjdm!!" + val(strs[i]);
						}else if(strs[i] == 'jgshen' || strs[i] == 'jgshi' || strs[i] == 'jgxian'){
							tmp += "@" + strs[i]+ "!!" + selText(strs[i]);
						}else{
							tmp += "@" + strs[i]+ "!!" + val(strs[i]);
						}
					}
				}
			}
			document.getElementById('condition').value = tmp;
		}
	</script>
	</head>
	<body onload="check_user()">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="/xsxxYnys.do?method=xsxxSearch" method="post">
				<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
				<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>" />
				<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx"/>" />
				<input type="hidden" id="userName" name="userName" value="<bean:write name="userName"/>" />
				<logic:equal value="yes" name="userOper">
					<input type="hidden" id="realTable" name="realTable" value="xsxxb">
				</logic:equal>
				<logic:equal value="no" name="userOper">
					<input type="hidden" id="realTable" name="realTable" value="bks_xsjbxx"/>
				</logic:equal>
				<input type="hidden" id="delPk" name="delPk" value="pk" />
				<input type="hidden" name="xyV" value="" />
				<input type="hidden" name="zyV" value="" />
				<input type="hidden" name="bjV" value="" />
				<input type="hidden" name="jgz" value="" />
				<input type="hidden" name="mes" value="${mes}" />
				<input type="hidden" name="condition" value="" id='condition' />

				<div class="title">
					<div class="title_img" id="title_m">
						��ǰλ�ã�
						<bean:write name="title" />
					</div>
				</div>
				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left" colspan="2">
									<input type="checkbox" name="type" value="type"
										checked="checked" onclick="showTr()">
									��������
								</td>
							</tr>
							<tr>
								<td align="left">
									�꼶��
									<html:select property="nj"  styleId="nj"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm"  styleId="xy"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;רҵ��
									<html:select property="zydm"  styleId="zy"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="getValue();allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');">
										��ѯ
									</button>
								</td>
								
							</tr>
							<tr>
								<td align="left" nowrap>
									�༶��
									<html:select property="bjdm" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									&nbsp;&nbsp;ѧ�ţ�
									<html:text style="width:85px" property="xh"
										onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
										styleId='xh' />
									&nbsp;&nbsp;������
									<html:text style="width:85px" property="xm"
										onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
										styleId='xm' />
									&nbsp;&nbsp;�Ա�
									<html:select property="xb">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="Ů">Ů</html:option>
									</html:select>	
									
								</td>
							</tr>
							<tr id="temDiv">
								<td colspan="2">
									���֤�ţ�
									<html:text property="sfzh" style="width:85px"
										onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
										styleId="sfzh" />
									&nbsp;&nbsp;�������ڣ�
									<html:text property="csrq" style="width:85px"
										onclick="return showCalendar('csrq','y-mm-dd');"
										styleId="csrq" />									
									&nbsp;&nbsp;���壺
									<html:select property="mzdm"  styleId="mz">
										<html:option value=""></html:option>
										<html:options collection="mzList" property="mzdm"
											labelProperty="mzmc" />
									</html:select>
									&nbsp;&nbsp;������ò��
									<html:select property="zzmmdm" 
										styleId="zzmm">
										<html:option value=""></html:option>
										<html:options collection="zzmmList" property="zzmmdm"
											labelProperty="zzmmmc" />
									</html:select>
									<br/>��Դ�أ�
									<html:text property="syd" style="width:85px"
										onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
										styleId="syd" />
									<%--��ʾʡ����ȫ��ͳһ���������˵�����--%>
										���᣺
										<html:select  property="jgshen" styleId="jgshen"
											onchange="loadShi('jgshen','jgshi','jgxian')">
											<html:option value="">--��ѡ��--</html:option>
											<html:options collection="ssList" property="ssdm"
												labelProperty="ssmc" />
										</html:select>
										<html:select  property="jgshi" styleId="jgshi"
											onchange="loadXian('jgshi','jgxian')">
											<html:options collection="shiList" property="shidm"
												labelProperty="shimc" />
										</html:select>
										<html:select  property="jgxian" styleId="jgxian">
											<html:options collection="xianList" property="xiandm"
												labelProperty="xianmc" />
										</html:select>
									<%--end��ʾʡ����ȫ��ͳһ���������˵�--%>
									
								</td>
							</tr>
							<tr id="jgsdxDiv">
								<td colspan="2">
									ѧ��״̬��
									<html:select property="xjztm" styleId="xjztm">
										<html:option value=""></html:option>
										<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
									</html:select>
									&nbsp;&nbsp;ѧ�ƣ�
									<html:text property="xz" style="width:85px"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId="xz" />	
										&nbsp;&nbsp;������Σ� 
										<html:text property="pycc" style="width:85px"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId="pycc" />
										&nbsp;&nbsp;��ѧʱ�䣺
										<html:text property="rxrq" style="width:85px"
											onclick="return showCalendar('rxrq','y-mm-dd');"
											styleId="rxrq" readonly="true" />
										&nbsp;&nbsp;��ҵʱ�䣺	
										<html:text property="byny" style="width:85px"
											onclick="return showCalendar('byny','y-mm-dd');"
											styleId="byny" readonly="true" />
										<br/>ְ�� 
										<html:text property="zw" style="width:85px"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId='zw' />
										&nbsp;&nbsp;�س���
										<html:text property="tc" style="width:85px" styleId="tc" />
										&nbsp;&nbsp;�����ţ�
										<html:text property="ssbh" style="width:85px"
										onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
										styleId='ssbh' />									
										&nbsp;&nbsp;�ֻ����룺
										<html:text property="sjhm" style="width:85px" styleId="sjhm" />																			
										&nbsp;&nbsp;�Ƿ���Ҫ��ѧ���
										<html:select property="sfdk">
											<html:option value=""></html:option>
											<html:option value="��">��</html:option>
											<html:option value="��">��</html:option>
										</html:select>
										<br/>��ͥ��Ա������
										<html:text style="width:85px" property="jtcyxm"
										onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');" />
										&nbsp;&nbsp;��ͥ�����룺 
										<html:text property="jtnsrd" style="width:85px" styleId="jgnsrd" onkeyup="value=value.replace(/[^\d|.]/g,'') "/> - 
										<html:text property="jtnsrg" styleId="jtnsrg" style="width:85px" onkeyup="value=value.replace(/[^\d|.]/g,'') "/> Ԫ/�� (�� - ��)
										&nbsp;&nbsp;��ͥ�绰��
										<html:text property="lxdh1" style="width:85px" styleId="lxdh1" />
										
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
							��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��˫��һ�п���ѡ����������ͷ��������</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:equal value="yes" name="userOper">
										<td>
											<input type="checkbox" name="xsxx" value="all"
												onclick="chec()">
										</td>
									</logic:equal>

									<logic:iterate id="tit" name="topTr" offset="0" length="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>

									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="stuInfoWin(this)">

									<logic:equal value="yes" name="userOper">
										<td align="center">
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="checkbox" name="pk"
													value="<bean:write name="v"/>">
											</logic:iterate>
										</td>
									</logic:equal>
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
										<input type="hidden" value="<bean:write name="v" />" />
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="3">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>

						</table>
						<TABLE width="99%" id="Table" class="tbstyle">
							<TR>
								<TD height=3>
									<br>
									<br>
								</TD>
							</TR>
							<TR>
								<TD>
									<jsp:include flush="true"
										page="/sjcz/turnpage.jsp?form=xsxxYnysForm"></jsp:include>
								</TD>
							</TR>
						</TABLE>
					</fieldset>
				</logic:notEmpty>

				<logic:notEmpty name="userOper">
					<br />
					<br />
					<br />
					<br />
					<logic:equal value="yes" name="writeAble">
						<%--<bean:message key="lable.xsgzyxpzxy" />�û�--%>
						<logic:present name="isXY">
							<div class="buttontool" id="btn"
								style="position: absolute;left:1%;top:100px" width="100%">
								<logic:notPresent name="fdy">
										<button type="button" class="button2"
										onclick="ljsdaUpdate('stu_info_add.do?method=showStuInfo&oper=update&xh=')"
										style="width:60px">
										�� ��
									</button>
								</logic:notPresent>
								&nbsp;&nbsp;
								<button type="button" class="button2"
									onclick="refreshForm('xsxxgl.do?method=showExportPage')"
									style="width:60px">
									��������
								</button>
							</div>
						</logic:present>
						<%--��<bean:message key="lable.xsgzyxpzxy" />�û�--%>
						<logic:notPresent name="isXY">
							<div class="buttontool" id="btn"
								style="position: absolute;left:1%;top:100px" width="100%">
								<button type="button" class="button2"
									onclick="showTopWin('stu_info_add.do?method=showStuInfo&oper=add',800,600,false)"
									style="width:60px">
									�� ��
								</button>								
								&nbsp;&nbsp;
								<button type="button" class="button2"
									onclick="ljsdaUpdate('stu_info_add.do?method=showStuInfo&oper=update&xh=');"
									style="width:60px">
									�� ��
								</button>
								&nbsp;&nbsp;
								<button type="button" class="button2"
									onclick="batch('stu_info_add.do?method=stuInfoDelete','del')"
									style="width:60px">
									ɾ ��
								</button>
								&nbsp;&nbsp;
								<button type="button" class="button2" onclick="impAndChkData();"
									style="width:60px">
									��������
								</button>
								&nbsp;&nbsp;
								<button type="button" class="button2"
									onclick="writeCondition();refreshForm('xsxxgl.do?method=showExportPage')"
									style="width:60px">
									��������
								</button>
								&nbsp;&nbsp;
								<button type="button" class="button2" onclick="expTab('rsTable','ѧ��������Ϣ','')"
									style="width:60px">
									��ӡ�б�
								</button>
								&nbsp;&nbsp;
								<button type="button" class="button2"
									onclick="ljsdaUpdate('stu_info_add.do?method=showStuInfo&type=details&oper=update&xh=',800,600)"
									style="width:60px">
									��ϸ��Ϣ
								</button>
								
								&nbsp;&nbsp;
								<button type="button" class="button2" onclick="showConfByny()"
									style="width:95px">
									��ҵʱ���ʼ��
								</button>
							</div>
							<div id="tmpdiv"></div>
						</logic:notPresent>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="result">
					<logic:equal value="true" name="result">
						<logic:notEmpty name="mes">
							<script>
									alert(document.getElementById('mes').value);
								</script>
						</logic:notEmpty>
						<logic:empty name="mes">
							<script>
									alert("�����ɹ���");
								</script>
						</logic:empty>
						<script>
						Close();
						document.getElementById('search_go').click();						
					</script>
					</logic:equal>
					<logic:equal name="result" value="false">
						<logic:present name="mes">
							<logic:notEmpty name="mes">
								<script>
									alert(document.getElementById('mes').value);
								</script>
							</logic:notEmpty>
							<logic:empty name="mes">
								<script>
									alert("����ʧ��!");
								</script>
							</logic:empty>
						</logic:present>
						<logic:notPresent name="mes">
							<script>
								alert("����ʧ��!");
							</script>
						</logic:notPresent>
						<script>
								document.getElementById('search_go').click();
							</script>
					</logic:equal>
				</logic:notEmpty>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

