<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
       function hiddenField() {
	     i = document.getElementsByTagName("select").length;
	    for (j = 0; j < i; j++) {
		  document.getElementsByTagName("select")[j].style.visibility = "hidden";
	    } 
      }
    </script>
	<script language="javascript" src="js/function.js"></script>	
	<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/String.js"></script>
<%--	<script language="javascript" src="js/AjaxFunction.js"></script>--%>
	<script language="javascript" src="js/gygl/gyglFunction.js"></script>
	</head>	

	<body onload="xyDisabled('xy');">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ������� - ��λ����</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zgdzdx_Gygl.do?method=bedCompartition&doXh=true" method="post">
			<html:hidden name="zgdzdxForm" property="conditionSqlValue"
				styleId="conditionSqlValue" />
			<input type="hidden" name="oldCondiSqlValue" id="oldCondiSqlValue" 			 
			       value="<bean:write name="oldCondiSqlValue" scope="request"/>"/>
			<input type="hidden" name="mappingItems" value="" />       				
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType"/>" />	
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName"/>" />
			
			
            <div id="items" name="items" style="display:none; position: absolute;color: blue; " >
					<span>&nbsp;&nbsp;&nbsp;&nbsp;������ѧ�ţ������س�������ѧ�Ų�ѯ�ѷ������</span>
			</div>
			<logic:notEmpty name="errINFO">
			<script language="javascript">
			     alert('<bean:write name="errINFO"/>');
			</script>
			</logic:notEmpty>
            <div id="showDiv" style="display:none" align="center">
					<table class="formlist" border="0" align="center" style="width: 100%">
						<thead>
							<tr>
								<th colspan="2">
									<span>��סʱ������</span>
								</th>
							</tr>
						</thead>
						<tbody>		
							<tr>
								<td align='right' width='40%'>
									<font color=red>*</font>��סʱ�䣺
								</td>
								<td align='left'>
								<html:text property="rzrq" styleId="rzrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('rzrq','y-mm-dd','aa');" readonly="true"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan='2' align="center">
									<button id="kfbtnSave" onclick='zgdz_addCwColum()'>
										�ύ
									</button>
									&nbsp;&nbsp;
									<button id="kfbtnClose" onclick='hiddenMessage(true,true)'>
										�ر�
									</button>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>										
			<!-- ���������� -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>��λ����</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr align="center">
						<th width="28%" align="left" rowspan="2">
							&nbsp;&nbsp; У������							
								<html:select property="xiaoqu" styleId="xq" style=""
									onfocus="bedbeforSubmit() " onchange="dataCL();SssCwXxList();">									
									<html:options collection="xiaoqquList" property="dm"
										labelProperty="mc" />
								</html:select>
							<br>	
							  �Ա��޶���
							<html:select property="xbxd" styleId="xbxd" style="" onfocus="bedbeforSubmit() "
								onchange="if($('xq').value==''){}else{SssLdList();SssXsList();SssCwXxList();SssFpCwList();}">
								<html:option value="">--��ѡ��--</html:option>
							    	<html:option value="��">��</html:option>
							    	<html:option value="Ů">Ů</html:option>
							    	<html:option value="���">���</html:option>
							</html:select>
							<br>														
           					&nbsp;<font color="red">*</font>¥������
							<html:select property="lddm" style="" styleId="ld" onfocus="bedbeforSubmit() "
								onchange="SssLcList();SssCwXxList();">								
								<html:options collection="ldList" property="dm"
									labelProperty="mc" />
							</html:select>													
						       <br> 
						       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ţ�
						       <html:select property="cs" styleId="cs" style="" onfocus="bedbeforSubmit() "
									onchange="SssCwXxList();">
								
									<html:options collection="csList" property="dm"
										labelProperty="mc" />
								</html:select>
						</th>
						<td width="67%" align="left" colspan="3">
						    <font color="red">*</font>�꼶��
							<html:select property="nj" style="width:60px" onfocus="bedbeforSubmit() " styleId="nj"
								onchange="SssCwYfpZsData();SssBjLb();SssXsList();SssFpCwList();SssXiaoqLb();">
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							&nbsp;&nbsp;<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
							<html:select property="xydm" style="width:160px" styleId="xy" onfocus="bedbeforSubmit() "
								onchange=" SssCwYfpZsData();SssXiaoqLb();SssBjLb();SssXsList();SssFpCwList();">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="xyList" property="dm"
									labelProperty="mc" />
							</html:select>						
							&nbsp;&nbsp;�༶:
							<html:select property="bjdm" style="width:150px" styleId="bj" onfocus="bedbeforSubmit() "
								onchange=" SssCwYfpZsData();SssXsList();SssFpCwList();">                                 
                                    <html:options collection="bjList" property="dm" labelProperty="mc"/>
							</html:select>								
							
<%--							&nbsp;&nbsp;ѧ�ţ�<html:text property="ksh" styleId="ksh" onfocus="bedbeforSubmit() ;showItems();"  onkeypress="" onblur="hiddenItems();"></html:text>															     		--%>
			     		</td>
					</tr>
					<tr>
						<td colspan="3">
							δ��������(��):
							<span id="allbody"><bean:write name="nototal"scope="request" />
							</span> &nbsp;&nbsp;&nbsp;&nbsp;(��):
							<span id="allboy"><bean:write name="noboy" scope="request" />
							</span> &nbsp;&nbsp;&nbsp;&nbsp;(Ů):
							<span id="allgirl"><bean:write name="nogirl" scope="request" />
							</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ѷ���������:
							<span id="totalBed"><bean:write name="total"scope="request" /></span>&nbsp;&nbsp;&nbsp;&nbsp;(��):
							<span id="boyBed"><bean:write name="boy"scope="request" /></span>&nbsp;&nbsp;&nbsp;&nbsp;(Ů):
							<span id="girlBed"><bean:write name="girl"scope="request" /></span>
						</td>						
					</tr>
					<tr align="center" bgcolor="#D0E0EE">
						<td align="center" >
							δ���䴲λ
						</td>
						<td align="center" width="19%">
							δ����ѧ��
						</td>
						<td align="center" width="6%">							
						</td>
						<td align="center" width="57%">
							�ѷ������
						</td>
					</tr>
					</tbody>
					</table>
					<table class="permissionlist" border="0" align="center" style="width: 100%">
					<tr align="center">
							<td colspan="3">
								<table width="100%" align="center" class="">
					<tr align="center">
						<td rowspan="2" valign="top">
						<font color="red" style="font-size:10px;">��ʾ����סCtrl����Shift��<br>(���������������ƶ�)���ж�ѡ</font>							
							<br>							
							���ұ��/��λ��/��λ��
							<html:select property="oracleItem" style="width:100%;" size="27" multiple="multiple"
								styleId="oracleList" onmouseover="null">
								<html:options collection="ssxxList" labelProperty="mc"
									property="dm" />
							</html:select>
						</td>
						<td valign="top">
							<font color="red" style="font-size: 10px;">��ʾ����סCtrl����Shift��<br>(���������������ƶ�)���ж�ѡ</font>
							<br>
							ѧ��/����/�Ա�
							<html:select property="xh" style="width:100%;" size="27"
								styleId="xh" multiple="multiple" onmouseover="null">
								<logic:notEmpty name="xsList">
									<html:options collection="xsList" labelProperty="mc" 
										property="dm" />
								</logic:notEmpty>
							</html:select>
						</td>
						<td valign="top">
							<br>
							<br>
							<br>
							<br>
							<br>
							<font color="blue">����</font>
							<br>
							<button class="button2" onclick="zgdz_addCwBatchColum()"
								style="width:50px;height: 20px" title="��λ����">
								&rarr;
							</button>
							<br>
							<br>
							<br>
							<br>
							<font color="blue">�ͷ�</font>
							<br>
							<button class="button2" onclick="zgdz_delCwBatchColum()"
								style="width:50px;height: 20px" title="��λ�ͷ�">
								&larr;
							</button>
						</td>
						<td valign="top">
						<font color="red" style="font-size: 10px;">��ʾ����סCtrl����Shift��<br>(���������������ƶ�)���ж�ѡ</font>								
							<br>
							ѧ��/����/�Ա�/���ұ��/��λ��/���䴲λ��
							<html:select property="sql" size="27" style="width:100%" ondblclick="" styleId="sql"  multiple="multiple" onmouseover="null">
							<html:options collection="yfpCwList" property="dm"
									labelProperty="mc" />
							</html:select>					
						</td>
						
					</tr>
					</table>
					</td>
					</tr>
					</table>
						<table class="formlist" border="0" align="center" style="width: 100%">
						<tfoot>
					<tr>
						<td align="center" colspan="3">
							<button name="button1"
								style="width:100px"
								onclick="if(confirm('�Ƿ�Ҫ�ύ��ǰ�ѷ���������ݣ�\n���\'ȷ��\'���������ݣ�\n���\'ȡ��\'���������ύ��')){SsscwfpDataSave();}">
								ȷ ��
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button name="button2"
								style="width:100px"
								onclick="refreshForm('/xgxt/zgdzdx_Gygl.do?method=bedCompartition')" >
								�� ��
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%--							<input type="button" class="button2" name="button2"--%>
<%--								style="width:100px" value="Ĭ �� �� ��"--%>
<%--								onclick="defaultDisBedList()" />--%>
						</td>
					</tr>
					</tfoot>
				</table>
			<div id="tmpdivone" ></div>
			<div id="tmpdivtow"></div>
			<div id="tmpdivthree"></div>
		</html:form>
		<logic:notEmpty name="doFlag">
			<logic:equal name="doFlag" value="true">
				<script>
<%--				document.forms[0].action = "/xgxt/zgdzdx_Gygl.do?method=bedCompartition&doXh=true";--%>
<%--				document.forms[0].submit();--%>
				alert("�����ɹ�!");				
				</script>
			</logic:equal>
			<logic:equal name="doFlag" value="false">
				<script>
				//document.forms[0].action = "/xgxt/bed_distribute.do";
				//document.forms[0].submit();
				alert("����ʧ��!");				
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
	<script language="javascript">	
function showItems(){
	var items = document.getElementById("items");
	if($("ksh").value==""){
	items.style.left = (screen.availwidth)/2.5;
	items.style.top = ((screen.availheight)/10);
	items.style.display = "block";
	}
}
function hiddenItems(){
    var items = document.getElementById("items");
    items.style.display = "none";
}
</script>
</html>
