<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<!-- ͷ -->
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/qtsjFunction.js"></script>	
	<script language="javascript">
	function xljk_xlxhhy_modi(){	
			var bz=document.all["bz"].value;
			if (bz.length>150){
				alert ("��ע���ܳ���150������");
				document.all["bz"].focus();
				return false;
			}
			document.all["modi_flag"].value="yes";
			var xn_id=document.all['xlxhhy_xnid'].value;
			saveData('/xgxt/xljk_xlxhhy.do?act=xljk_xlxhhy&doType=Modi&xlxhhy_xnid='+xn_id,'xh-csrq-sjhm-qsdh-hybh');
	}
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/xljk_xlxhhy" method="post">
			<input type="hidden" id="modi_flag" name="modi_flag" value="no" />
			<input type="hidden" id="xlxhhy_xnid" name="xlxhhy_xnid"
				value="<bean:write name="xlxhhy_xnid" scope="request"/>" />
			<!-- ���� -->
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>������ - ����Э�� - ��Ա������Ϣ - �޸Ļ�Ա������Ϣ</a>
				</p>
			</div>
			<!-- ���� end-->
			<!-- ��Ա������Ϣ -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="6">
							<span>��Ա������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="height:22px">
					<th align="right" colspan="2">
						<font color="red">*</font>ѧ��
					</th>
					<td align="left">
						<html:text name="rs" property="hyxh" styleId="hyxh"
							readonly="true" />
					</td>
					<th align="right">
						����
					</th>
					<td align="left">
						<html:text name="rs" property="xm" styleId="xm"
							readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" colspan="2">
						�Ա�
					</th>
					<td align="left">
						<html:text name="rs" property="xb" styleId="xb" readonly="true" />
					</td>
					<th align="right">
						<font color="red">*</font>��������
					</th>
					<td align="left">
						<html:text name='rs' property="csrq" styleId="csrq"
							 style="cursor:hand;"
							onclick="return showCalendar('csrq','y-mm-dd');" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" colspan="2">
						<bean:message key="lable.xb" />
					</th>
					<td align="left">
						<html:text name="rs" property="xymc" styleId="xymc"
							readonly="true" />
					</td>
					<th align="right">
						�༶
					</th>
					<td align="left">
						<html:text name="rs" property="bjmc" styleId="bjmc"
							readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" colspan="2" nowrap="nowrap">
						<font color="red">*</font>�ֻ�����
					</th>
					<td align="left">
						<html:text name="rs" property="sjhm" styleId="zy" />
					</td>
					<th align="right" nowrap="nowrap">
						<font color="red">*</font>���ҵ绰
					</th>
					<td align="left">
						<html:text name="rs" property="qsdh" />
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						<font color="red">*</font>��Ա���
					</th>
					<td align="left">
						<html:text name="rs" property="hybh" readonly="true" />
					</td>

					<th align="right">
						ְ��
					</th>
					<td align="left">
						<html:text name="rs" property="zw" />
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						��ע
					</th>
					<td colspan="4" align="left">
						<html:textarea rows="5" name="rs" style="width:98%" property="bz"
							styleId="a" />
					</td>
				</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
							<div class="btn">
								<logic:notEqual name="doType" value="view">
									<button id="buttonSave" 
										onclick="xljk_xlxhhy_modi()"
										style="width: 80px">
										�� ��
									</button>
								</logic:notEqual>
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
			<logic:notEmpty name="message">
				<logic:equal name="message" value="update_success">
					<script>
						alert("�޸ĳɹ�!");
						dialogArgumentsQueryChick();
						Close();	
					</script>
				</logic:equal>
				<logic:equal name="message" value="update_fail">
					<script>
						alert("�޸�ʧ��!");
						document.getElementById("tmpdiv").innerHTML = "";
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		<div id="tmpdiv"></div>
	</body>
</html>
