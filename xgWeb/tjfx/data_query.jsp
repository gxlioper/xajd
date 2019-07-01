<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	   function changechoice(){
	   
	   var cols = $("cols").value;
	   var sqlstring =new Array();
	   var sqlstringtext =new Array();
	   document.forms[0].conditionSqlText.value = "";
	   document.forms[0].conditionSqlValue.value = "";
	    for (i = 0; i < document.forms[0].sql.options.length; i++) {
				sqlstring[sqlstring.length] = document.forms[0].sql.options[i].value  ;
				sqlstringtext[sqlstringtext.length] = document.forms[0].sql.options[i].text;
			}
		document.forms[0].conditionSqlText.value = sqlstringtext;
		document.forms[0].conditionSqlValue.value = sqlstring;
	   
	   document.forms[0].action = "data_query.do?cols="+cols+"&sqlstring="+sqlstring+"&sqlstringtext="+sqlstringtext;
	   document.forms[0].submit();
	  
	  }
	
   </script>
	</head>
	
	<body onload="loadConditionSql();">
	<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="titName" scope="request" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/do_data_query">
			<html:hidden name="commanForm" property="conditionSqlText"
				styleId="conditionSqlText" />			
			<html:hidden name="commanForm" property="conditionSqlValue"
				styleId="conditionSqlValue" />
			<input type="hidden" name="mkName" id="mkName"
				value="<bean:write name="mkName" scope="request"/>" />
			<input type="hidden" name="titName" id="titName"
				value="<bean:write name="titName" scope="request"/>" />
			<input type="hidden" name="cols1" id="cols1" value="" />
			<input type="hidden" name="sql1" id="sql1" value="" />
			<input type="hidden" value="1" name="page" />
			
			<div class="con_overlfow">
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
							<tr align="center">  
								<th width="24%" align="center">
									<div align="center">��ѯ����</div>
								</th>
								<th width="24%" align="center">
									<div align="center">��ѯ�ֶ�</div>
								</th>
								<th width="5%" align="center">
									<div align="center">�����</div>
								</th>
								<th width="24%" align="center">
									<div align="center">��ѯ�ֶε�ֵ</div>
								</th>
								<th width="19%" colspan="4">
									<div align="center">�߼������</div>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
							<td>
								<html:select name="commanForm" property="cxbm"
									 styleId="cxbm" onchange="querySubload()">
									<html:option value=""></html:option>
									<logic:notEmpty name="tableList">
										<html:options collection="tableList" property="table_name"
											labelProperty="comments" />
									</logic:notEmpty>
								</html:select>
							</td>
							<td>
								<html:select name="commanForm" property="cols" style="width:140px"
									 styleId="cols" onchange="changechoice();">
									<html:option value=""></html:option>
									<logic:notEmpty name="colList">
										<html:options collection="colList" property="column_name"
											labelProperty="comments" />
									</logic:notEmpty>
								</html:select>
							</td>
							<td>
								<select name="relation" id="relation">
									<option value=" = ">
										����
									</option>
									<option value=" &lt;&gt; ">
										������
									</option>
									<option value=" &gt;= ">
										���ڵ���
									</option>
									<option value=" &lt;= ">
										С�ڵ���
									</option>
									<option value=" like ">
										����
									</option>
								</select>
							</td>
							<td>
								<logic:empty name="list">
									<input type="text" name="val" style="width:90px"
										onkeypress="if(event.keyCode==13)addcondition();"
										 id="val" />
								</logic:empty>
								<logic:notEmpty name="list">
									<html:select name="rs" property="val" styleId="val" style="width:90px"
										>
										<html:option value=""></html:option>
										<html:options collection="list" labelProperty="val"
											property="val" />
									</html:select>
								</logic:notEmpty>
							</td>
							<td width="64">
								<button name="button" style="width:40px" 
									onclick="addStatisicalAnd()" >��</button>
							</td>
							<td width="55">
								<button name="button" style="width:40px" 
									onclick="addStatisicalOr()" >��</button>
							</td>
							<td width="62">
								<button name="button" style="width:40px" 
									onclick="addStatisicalZk()" >(</button>
							</td>
							<td width="55">
								<button name="button" style="width:40px" 
									onclick="addStatisicalYk()">)</button>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="left">
								<button name="button"
									onclick="addQueryCondition()">��������</button>
							</td>
							<td colspan="4">
								ѡ����ʾ�ֶ�
								<font color="red">��ѡ</font>
								<input type="checkbox" name="xszd" id="xszd" onclick="cxfx()" />
							</td>
						</tr>
						<tr>
							<td height="173" colspan="4">
								<%--								<bean:write name="rs" property="sql" />--%>
								<%--								<html:select name="rs" property="sql" size="14"--%>
								<%--									style="width:100%">--%>
								<%--								</html:select>--%>
								<select name="rs" size="17" style="width:100%" id="sql" >

								</select>
							</td>
							<td colspan="4" rowspan="2">
								<html:select name="commanForm" property="titList"
									styleId="titList" size="17" style="width:100%"
									ondblclick="chk(this)">
									<!-- ��ɳ���� -->
									<logic:equal value="10827" name="xxdm">
										<logic:present name="yrdwdm">
											<logic:notEmpty name="disColList">
												<html:options collection="disColList" property="column_name"
													labelProperty="titComments" />
											</logic:notEmpty>
										</logic:present>
										<logic:notPresent name="yrdwdm">
											<logic:notEmpty name="colList">
												<html:options collection="colList" property="column_name"
													labelProperty="titComments" />
											</logic:notEmpty>
										</logic:notPresent>
									</logic:equal>

									<logic:notEqual value="10827" name="xxdm">
										<logic:notEmpty name="colList">
											<html:options collection="colList" property="column_name"
												labelProperty="titComments" />
										</logic:notEmpty>
									</logic:notEqual>
								</html:select>
							</td>
						</tr>
						</tbody>
						<tfoot>
						<tr>
							<td height="33" colspan="8">
								<div class="btn">
								<button  name="Submit" id="buttonSearch"
									 onclick="sendValue()" >�� ѯ</button>
								<button name="Submit3" 
									onclick="delcondition()"  >ɾ������</button>
								<button  name="Submit4" 
									onclick="clearcondition()" >�������</button>
								</div>
							</td>
						</tr>
						</tfoot>
					</table>
					</div>
		</html:form>
	</body>
</html>
