<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>	
		<script language="javascript" src="js/xsgyglFunction.js"></script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ������������ - ��������� </a>
			</p>
		</div>
		<!-- ���� end-->
			<html:form action="/zgdzdx_Gygl" method="post">
			<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name="pkValue"/>" />
			
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">			 					 		
			<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								<span>���������</span>
							</td>
						</tr>
					</thead>
					<tbody>		
				<tr>
					<th height="22" width="15%" align="right">
						<font color="red">*</font>����ƣ�
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="hdmc" maxlength="40"></html:text>
					</td>
					<th height="22" align="right" width="15%">
						<font color="red">*</font>���쵥λ��
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="zbdw" maxlength="40"></html:text>
					</td>
				</tr>
				<tr>
					<th height="22" align="right" width="20%">
						<font color="red">*</font>������������
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="fzrxm" maxlength="20"></html:text>
					</td>
					<th height="22" align="right" width="20%">
						<font color="red">*</font>��������ϵ��ʽ��
					</th>
					<td height="22" align="left">
						<html:text  name="rs" property="fzrlxfs" maxlength="25"></html:text>
					</td>
				</tr>
				<tr>
					<th height="22" align="right">
						������������
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="jsrxm" maxlength="20"></html:text>
					</td>
					<th height="22" align="right">
						��������ϵ��ʽ��
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="jsrlxfs" maxlength="25"></html:text>
					</td>
				</tr>
				<tr>
					<th height="22" align="right">
						<font color="red">*</font>��ص㣺
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="hddd" maxlength="25"></html:text>
					</td>
					<th height="22" align="right" >
						�μ�������
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="cjrs" maxlength="5"></html:text>
					</td>
				</tr>
				<tr>
					<th height="22" align="right">
						<font color="red">*</font>���ʼ���ڣ�
					</th>
					<td height="22" align="left">
							<html:text name="rs" property="hdksrq" styleId="hdksrq"
							onblur="dateFormatChg(this)" style="cursor:hand;width:90px"
							onclick="return showCalendar('hdksrq','y-mm-dd');"
							readonly="true" />(��)<html:text name="rs" property="hdkssj" styleId="hdkssj"
							 style="width:70px" maxlength="20"/>(ʱ)
					
					</td>
					<th height="22" align="right">
						��������ڣ�
					</th>
					<td height="22" align="left">
							<html:text name="rs" property="hdjsrq" styleId="hdjsrq"
							onblur="dateFormatChg(this)" style="cursor:hand;width:90px"
							onclick="return showCalendar('hdjsrq','y-mm-dd');"
							readonly="true" />(��)<html:text name="rs" property="hdjssj" styleId="hdjssj"
							 style="width:70px"maxlength="20" />(ʱ)
					
					</td>					
				</tr>
				
				<tr>
					<th height="22" align="right">
						<font color="red">*</font>����ݣ�
					</th>
					<td height="22" align="left" colspan="3">
						<html:textarea name="rs" property='hdnr'style="width:99%"
							rows='15' />
					</td>					
				</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
									<button id="buttonSave" 
										onclick="jswmhdSave('hdmc-zbdw-fzrxm-fzrlxfs-hddd-hdksrq-hdkssj-hdnr')"
										style="width: 80px">
										��	��
									</button>
								&nbsp;&nbsp;
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									��	��
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
				alert("�����ɹ���");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("����ʧ��!");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function jswmhdSave(mustFill){
	           var eles = mustFill.split("-");
	           for (i = 0; i < eles.length; i++) {
		           if (document.getElementById(eles[i]).value == "") {
			       alert("�뽫��\"*\"�ŵ���Ŀ����������");
			       return false;
		           }		
	           }
	       if(confirm("ȷ��Ҫ���޸ĸû��¼��")){     	           
             refreshForm("/xgxt/zgdzdx_Gygl.do?method=jswmhdModi&doType=Save");      
	       }
	     }  
	</script>
</html>
