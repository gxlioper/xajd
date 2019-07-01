<%@ page language="java" contentType="text/html; charset=gb2312"%>

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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getBjlhGyglDAO.js'></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript" src="gygl/bjlh/cwfp/cwfp.js"></script>
	<script language="javascript" src="js/gygl/bjlh/bjlhFunction.js"></script>
	<script language="javascript">
      function hiddenField() {
	     i = document.getElementsByTagName("select").length;
	    for (j = 0; j < i; j++) {
		  document.getElementsByTagName("select")[j].style.visibility = "hidden";
	    } 
      }
      function setTBGbed(){
          totalBed.innerText="0";
          boyBed.innerText="0";
          girlBed.innerText="0";
      }
    function dispXylist() {
    	var fplx = document.getElementById('fplx').value;
    	var userType = document.getElementById('userType').value;
    	if (fplx=='ȫ����') {
    		xyDisabled('xy');
    	} else {
    		if (userType != 'admin' && userType != 'xx') {
    			document.getElementById('fpbj').disabled = true;
    		}
    	}
    }
    function chLx(value){
		if(value=="0110"){
			$("xslx").value="�о���";
		}else if(value=="0117"){
			$("xslx").value="�ɽ���";
		} else {
			$("xslx").value="";
		}
		setNjList($('xslx').value);
		setXyList($('xslx').value);
		setZyList($('xslx').value);
		setBjList($('xslx').value);
	}
	function queryXlCw() {
		return showTopWin("bjlh_gygl_xlcw.do",750,550);
	}
    </script>
	<base target="_self" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="dispXylist()">
		<html:form action="/bjlh_cwfp" method="post">
			<html:hidden name="commanForm" property="conditionSqlText"
				styleId="conditionSqlText" />
			<html:hidden name="commanForm" property="conditionSqlValue"
				styleId="conditionSqlValue" />
			<html:hidden name="commanForm" property="conditionXlValue"
				styleId="conditionXlValue" />
			<input type="hidden" name="oldCondiSqlValue" id="oldCondiSqlValue" 			 
			       value="${oldMappingItems }"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������Ϲ�Ԣ���� - ��λ����
				</div>
			</div>
			<input type="hidden" name="mappingItems" value="" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="fplx" id="fplx"
				value="${fplx }" />
			<input type="hidden" id="xslx" name="xslx" value="" />
			
			<div id="showDiv" style="display:none" align="center">
             <iframe  src='javascript:false' border=0px  style='position: absolute;visibility: inherit;top: 0px;left: 0px;width: 350px;height: 200px;z-index: -999;filter: Alpha(Opacity=0)'></iframe>          
					<fieldset style="width:90%;height:90%">
						<legend>
							������ʾ��Ϣ
						</legend>
						<table class='buttontool' hight='100px' >
							<thead>
								<tr>
									<td colspan='2'>
										��סʱ������
									</td>
								</tr>
							</thead>
							<tr>
								<td align='right' width='40%'>
									<font color=red>*</font>��סʱ�䣺
								</td>
								<td align='left'>
								<html:text name="commanForm" property="rzrq" styleId="rzrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('rzrq','y-mm-dd','aa');" readonly="true"/>
								</td>
							</tr>
							<tfoot>
								<tr>
									<td colspan='2'>
										<button class='button2' id="kfbtnSave" onclick='addBjlhCwColum()'>
											�ύ
										</button>
										&nbsp;&nbsp;
										<button id="kfbtnClose" onclick='hiddenMessage(true,true)' class='button2'>
											�ر�
										</button>
									</td>
								</tr>
							</tfoot>
						</table>
					</fieldset>
				</div>
			
			<div class="rightcontent" >
				<fieldset>
					<legend>
						��λ����
					</legend>
					<table width="98%" align="center" class="tbstyle"  bgcolor="#D0E0EE">
						<tr align="center">
							<td width="30%" align="left" rowspan="2">
								&nbsp;&nbsp;&nbsp;&nbsp;У������
								<html:select property="xqdm"  styleId="xq" 
									onchange="bjlhssFp_Xq()">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="xiaoqquList" property="dm"
										labelProperty="mc" />
								</html:select>
								<br>
								&nbsp;�Ա��޶���
								<html:select property="xb" styleId="xb"  onfocus="bjlhbeforSSFPSubmit()"
									onchange="if($('xq').value==''){}else{bjlhinitSsFpLdList();bjlhinitCwFpCwXxList();bjlhinitCwFpWfpXsXxList();}">
									<html:option value="">--��ѡ��--</html:option>
									<html:option value="��">��</html:option>
									<html:option value="Ů">Ů</html:option>
									<html:option value="���">���</html:option>
								</html:select>
								<br>
								&nbsp;&nbsp;&nbsp;&nbsp;¥������
								<html:select property="lddm" styleId="ld"  onfocus="bjlhbeforSSFPSubmit()"
									onchange="bblhssfpTj();bjlhinitCwFpCwXxList();bjlhinitSsFpFpCsList();bjlhinitCwFpFpSxXxList();">
									<html:options collection="ldList" property="dm"
										labelProperty="mc" />
								</html:select>							
								<br>
						          &nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;�ţ�
						           	<html:select property="cs" styleId="cs"
										onfocus="bjlhbeforSSFPSubmit()"
										onchange="bblhssfpTj();bjlhinitCwFpCwXxList();bjlhinitCwFpFpSxXxList();">										
										<html:options collection="lcList" property="dm"
										labelProperty="mc" />
									</html:select>
								<br>    							
							</td>
							<td width="70%" align="left">							
								�����ǣ�
								<html:select property="fpbj" styleId="fpbj" onfocus="bjlhbeforSSFPSubmit()" onchange="bblhssfpTj();chLx(this.value);bjlhinitCwFpCwXxList();bjlhinitCwFpWfpXsXxList();bjlhinitCwFpFpSxXxList();">
									<html:options collection="fpbjList" property="en" labelProperty="cn"/>
								</html:select>
								&nbsp;&nbsp;
								&nbsp;�꼶��
								<html:select property="nj" styleId="nj" onfocus="bjlhbeforSSFPSubmit()"
									onchange="bblhssfpTj();setZyList($('xslx').value);setBjList($('xslx').value);bjlhinitCwFpWfpXsXxList();bjlhinitCwFpFpSxXxList();">
									<html:options collection="njList" property="dm"
										labelProperty="mc" />
								</html:select>
								&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" styleId="xy" onfocus="bjlhbeforSSFPSubmit()"
									onchange="bblhssfpTj();setZyList($('xslx').value);setBjList($('xslx').value);bjlhinitCwFpFpSxXxList();bjlhinitCwFpWfpXsXxList();">
									<html:options collection="xyList" property="dm"
										labelProperty="mc" />
								</html:select>
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;רҵ��
								<html:select property="zydm" styleId="zy" onfocus="bjlhbeforSSFPSubmit()"
									onchange="bblhssfpTj();setBjList($('xslx').value);bjlhinitCwFpWfpXsXxList();bjlhinitCwFpFpSxXxList();">
									<html:options collection="zyList" property="dm"
										labelProperty="mc" />
								</html:select>
								&nbsp;�༶��
								<html:select property="bjdm" styleId="bj" onfocus="bjlhbeforSSFPSubmit()"
									onchange="bblhssfpTj();bjlhinitCwFpWfpXsXxList();bjlhinitCwFpFpSxXxList();">
									<html:options collection="bjList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td>
								δ����ѧ��&nbsp;&nbsp;����(��):
								<span id="allbody" style="width: 70px">${total }</span> (��):
								<span id="allboy" style="width: 70px">${boy }</span> (Ů):
								<span id="allgirl" style="width: 100px">${girl }
								</span> 
								<br>
								δ���ִ�λ&nbsp;&nbsp;����(��):
								<span id="totalBedF" style="width: 70px">${totalBedF }</span> (��):
								<span id="boyBedF" style="width: 70px">${boyBedF }</span> (Ů):
								<span id="girlBedF" style="width: 70px">${girlBedF }</span>��ϣ�
								<span id="bgBedF" style="width: 70px">${bgBedF }</span>
								<br>
								�ѻ��ִ�λ&nbsp;&nbsp;����(��):
								<span id="totalBed" style="width: 70px">${totalBed }</span> (��):
								<span id="boyBed" style="width: 70px">${boyBed }</span> (Ů):
								<span id="girlBed" style="width: 70px">${girlBed }</span>���
								<span id="xlBed" style="width: 70px">${xlBed }</span>
							</td>
						</tr>

						<tr align="center">
							<td rowspan="2" valign="top" colspan="2">
								<table width="97%" align="center" class="tbstyle">
									<tr align="center">
										<td align="center" width="25%">
											δ���䴲λ
										</td>
										<td align="center" width="25%">
											ѧ����Ϣ
										</td>
										<td width="10%">
										</td>
										<td align="center" width="40%">
											�ѷ������
										</td>
									</tr>
									<tr align="center">
										<td rowspan="2" valign="top">
										<font color="red" style="font-size:10px;">��ʾ����סCtrl��(���������������ƶ�)<br>�ɽ��ж�ѡ</font>
											<br>
											���ұ��/��λ��/��λ��
											<html:select property="oracleItem" style="width:100%;"
												size="27" styleId="oracleList" title="" ondblclick=""
												multiple="multiple">
												<html:options collection="cwxxList" labelProperty="mc"
													property="dm" />
											</html:select>
										</td>
										<td rowspan="2" valign="top">
										<font color="red" style="font-size:10px;">��ʾ����סCtrl��(���������������ƶ�)<br>�ɽ��ж�ѡ</font>
											<br>
											ѧ��/����/�Ա�
											<html:select property="oracleItem" style="width:100%;"
												size="27" styleId="oracleXsList" title="" ondblclick=""
												multiple="multiple">
												<html:options collection="xsxxList" labelProperty="mc"
													property="dm" />
											</html:select>
										</td>
										<td valign="top">
											<br>
											<br>
											<br>											
											<font color="blue">���λ</font>
											<br>
											<button class="button2" onclick="addBjlhXlCw()"
												style="width:50px;height: 20px" title="����Ϊ���λ">
												����
											</button>
											<br>
											<br>
											<button class="button2" onclick="queryXlCw()"
												style="width:50px;height: 20px" title="�鿴���λ��Ϣ">
												�鿴
											</button>
											<br>
											<br>
											<br>
											<br>
											<br>											
											<font color="blue">����</font>
											<br>
											<button class="button2" onclick="addBjlhCwBatchColum()"
												style="width:50px;height: 20px" title="��λ����">
												&rarr;
											</button>
											<br>
											<br>
											<br>
											<br>
										    <font color="blue">�ͷ�</font>
										    <br>
											<button class="button2" onclick="delBjlhCwBatchColum()"
												style="width:50px;height: 20px" title="��λ�ͷ�">
												&larr;
											</button>
										</td>
										<td valign="top">
										<font color="red" style="font-size:10px;">��ʾ����סCtrl��(���������������ƶ�)<br>�ɽ��ж�ѡ</font>
										<br>
											ѧ��/����/�Ա�/���ұ��/��λ��/���䴲λ��/��סʱ��
											<html:select property="sql" style="width:100%;" size="26"
												styleId="sql" ondblclick=""multiple="multiple">
												<html:options collection="yfpxsxxList" labelProperty="mc"
													property="dm" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td align="center" colspan="2">
											<input class="button2" type="button" name="button1"
												style="width:100px" value="ȷ ��"
												onclick="if(confirm('�Ƿ�Ҫ�ύ��ǰ�ѷ���������ݣ�\n���\'ȷ��\'���������ݣ�\n���\'ȡ��\'���������ύ��')){bjlhCwDistSave()}" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="button" class="button2" name="button2"
												style="width:100px" value="�� ��"
												onclick="refreshForm('bjlh_gygl_cwfp.do')" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</fieldset>				
			</div>
			<div id="tmpdiv"></div>
			<div id="tmpdivone"></div>
			<div id="tmpdivtow"></div>
			<div id="tmpdivthree"></div>			
		</html:form>
		<logic:notEmpty name="doFlag">
			<logic:equal name="doFlag" value="ok">
				<script>
					alert("�����ɹ�!");					
				</script>
			</logic:equal>
			<logic:equal name="doFlag" value="no">
				<script>
					alert("����ʧ��!");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
