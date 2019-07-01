<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>	
		<script language="javascript" src="js/AjaxFunction.js"></script>
	</head>
	<body >	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ���� - ������������</a>
			</p>
		</div>
		<!-- ���� end-->	
		<html:form action="/ahjg_gygl" method="post">
		<input type="hidden" name="qshV" id="qshV" />	
			
			<logic:equal value="false" name="canDo" >			
			   <div align="center"><font color="red">ֻ�и���Ա�û���������!</font> </div>
			</logic:equal>
			<logic:empty name="rs">
				<br/>
				<br/>
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
			
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>������������</span>
						</th>
					</tr>
				</thead>
				<tbody>		
				<tr>
				    <th align="right">
						<font color="red">*</font>¥�����ƣ�
					</th>
					<td align="left">
							<html:select property="lddm" style="width:80px" styleId="lddm"
								onchange="GetQshList()">
								<html:option value=""></html:option>
								<html:options collection="ldList" property="lddm"
									labelProperty="ldmc" />
							</html:select>
					</td>		
					<th align="right" >
						<font color="red">*</font>ѧ�꣺
					</th>
					<td align="left">
						<html:select name="rs" property="xn" style="width:90px" styleId="xn">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>	
				</tr>					
				<tr>
					<th align="right">
						<font color="red">*</font>���Һţ�
					</th>					
					<td align="left">
					<html:select property="qsh" style="width:80px" styleId="qsh">
						<html:option value=""></html:option>
						<html:options collection="qshList" property="dm"
							  labelProperty="mc" />
					</html:select>
					</td>	
					<th align="right">
						<font color="red">*</font>ѧ�ڣ�
					</th>
					<td align="left">
						<html:select name="rs" property="xq" style="width:90px" styleId="xq">
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>					
				</tr>
				<tr>	
					<th align="right">
						<font color="red">*</font>ʱ�䣺
					</th>
					<td align="left">
						<html:text name="rs" property="pysj" readonly="true"
						onblur="dateFormatChg(this)"
						onclick="return showCalendar('pysj','y-mm-dd');"
						style="cursor:hand " />
					</td>							
					<th align="right">
					    �����ˣ�
					</th>
					<td align="left">
						<html:text name="rs" property="xm" readonly="true"></html:text>		
					</td>
				</tr>
				<logic:present name="qslb">
				<tr>	
					<th align="right">
						�������
					</th>
					<td align="left">
						<html:select name="rs" property="qslb" style="width:90px" styleId="qslb">
							<html:options collection="qslbList" property="lbdm"
								labelProperty="lbmc" />
						</html:select>
					</td>							
					<th align="right">
					   
					</th>
					<td align="left">
						
					</td>
				</tr>
				
				</logic:present>
				<tr>
					<th align="right" >
						��ע��
					</th>
					<td align="left" colspan="3">
					<textarea rows="5" cols="80" name="bz" id="bz" type="_moz"> ${rs.bz}</textarea>
					</td>
				</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<button id="buttonSave" 
									onclick="if(IsNoEmpty('xn-xq-lddm-qsh-pysj')){refreshForm('/xgxt/ahjg_gygl.do?method=wmqsSbSave');}"
									style="width: 80px">
									��  ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</logic:notEmpty>	
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
	     alert("����ɹ���");
	    </script>
		</logic:equal>	
		<logic:equal value="false" name="done">
			<script type="text/javascript">
	     alert("����ʧ�ܣ�");
	    </script>
		</logic:equal>
	</body>
  
</html>
