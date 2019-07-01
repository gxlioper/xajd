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
		<script language="javascript" src="js/gyglFunction.js"></script>
			<script type="text/javascript">
	     function yqhdMAddSave(mustFill){
	           var eles = mustFill.split("-");
	           for (i = 0; i < eles.length; i++) {
		           if (document.getElementById(eles[i]).value == "") {
			       alert("�뽫��\"*\"�ŵ���Ŀ����������");
			       return false;
		           }		
	           }	           
           refreshForm("/xgxt/zgdzdx_Gygl.do?method=yqhdManageModi&doType=Save");
	     }
	</script>
	</head>

	<body onload="loadView();">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ԰������ - ԰������ </a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zgdzdx_Gygl" method="post">
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />	
			<input type="hidden" id="isview" name="isview"
				value="<bean:write name="isview" scope="request"/>" />									
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
					<tr>
						<th colspan="4">
							<span>԰������</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr>
						<th height="22" align="right">
							<font color="red">*</font>ѧ�꣺
						</th>
						<td height="22" align="left">
							<html:select name="rs" property="xn" style="width:120px" styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th height="22" align="right">
							<font color="red">*</font>ѧ�ڣ�
						</th>
						<td height="22" align="left">
							<html:select name="rs" property="xq" style="width:120px" styleId="xq">
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>

					<tr>
						<th height="22" align="right" width="15%">
							<font color="red">*</font>԰����
						</th>
						<td height="22" align="left">
							<html:select name="rs" property="yqdm"  style="width:120px" styleId="yqdm" >
								<html:options collection="yqList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<th height="22" align="right" width="15%">
							<font color="red">*</font>����ƣ�
						</th>
						<td height="22" align="left">
							<html:text name="rs" property="hdmc"></html:text>
						</td>
					</tr>					
					<tr>
						<th height="22" align="right">
							������ˣ�
						</th>
						<td height="22" align="left">
							<html:text name="rs" property="xm"></html:text>
						</td>
						<th height="22" align="right">
							<font color="red">*</font>���ڣ�
						</th>
						<td height="22" align="left">
						<html:text name="rs" property="rq" styleId="rq"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('rq','y-mm-dd');" readonly="true" />
					    </td>
					</tr>		
					<tr>
						<th height="22" align="right">
							��֯��λ��
						</th>
						<td height="22" align="left">
							<html:text name="rs" property="zzdw"></html:text>
						</td>
						<th height="22" align="right">
							ʱ�䣺
						</th>
						<td height="22" align="left">
							<html:text name="rs" property="sj"></html:text>
						</td>
					</tr>						
					<tr align="left">
							<th align="right">
								�μ���Ա��
							</th>
							<td colspan="4">
								<html:textarea name="rs" property='cjry' style="width:99%"
									rows='5' />
							</td>
					</tr>
					<tr align="left">
							<th align="right">
								����ݣ�
							</th>
							<td colspan="4">
								<html:textarea name="rs" property='hdnr' style="width:99%"
									rows='8' />
							</td>
					</tr>
					</tbody>
					<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<button id="buttonSave" onclick="yqhdMAddSave('yqdm-hdmc-rq')"
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
				alert("����ʧ��,��ϵͳ���Ѵ������\"*\"����Ŀ��ͬ�ļ�¼��������������ݺ����ύ��");
			</script>
		</logic:equal>
	</body>
</html>
