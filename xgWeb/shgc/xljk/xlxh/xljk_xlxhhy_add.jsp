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
		function xljk_xlxhhy_save(){
			
			var bz=document.all["bz"].value;
			if (bz.length>150){
				alert ("��ע���ܳ���150������");
				document.all["bz"].focus();
				return false;
			}
			document.all["add_flag"].value="yes";
			saveData('xljk_xlxhhy.do?act=xljk_xlxhhy&doType=Insert','xh-csrq-sjhm-qsdh-hybh');
		}
	</script>
	</head>	
	<body>
		<html:form action="/xljk_xlxhhy" method="post">
			<input type="hidden" name = "xxdm" id="xxdm" value="10477"/>
			<input type="hidden" id="add_flag" name="add_flag" value="no" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url"
				value="/shgc/xljk/xlxh/xljk_xlxhhy_add.jsp" />
			<!-- ���� -->
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>������ - ����Э�� - ��Ա������Ϣ - ���ӻ�Ա������Ϣ</a>
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
						<html:text name='rs' property="hyxh" styleId="xh" onblur=""
							onkeypress="" />
						<button
							onclick="showTopWin('/xgxt/xljk_xlxhhy.do?act=xljk_xlxhhy&doType=stu_info',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</td>
					<th align="right">
						����
					</th>
					<td align="left">
						<html:text name="rs" property="hyxm" styleId="hyxm" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" colspan="2" readOnly="true">
						�Ա�
					</th>
					<td align="left">
						<html:text name="rs" property="xb" styleId="xb" />
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
						<html:text name="rs" property="xymc" styleId="xymc" />
					</td>
					<th align="right">
						�༶
					</th>
					<td align="left">
						<html:text name="rs" property="bjmc" styleId="bjmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" colspan="2">
							<font color="red">*</font>�ֻ�����
					</th>
					<td align="left">
						<html:text name="rs" property="sjhm" styleId="sjhm" maxlength="20"/>
					</td>
					<th align="right">
							<font color="red">*</font>���ҵ绰
					</th>
					<td align="left">
						<html:text name="rs" property="qsdh" maxlength="20"/>
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						<font color="red">*</font>��Ա���
					</th>
					<td align="left">
						<html:text name="rs" property="hybh" maxlength="20"/>
					</td>

					<th align="right">
						ְ��
					</th>
					<td align="left">
						<html:text name="rs" property="zw" maxlength="20"/>
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
										onclick="xljk_xlxhhy_save()"
										style="width: 80px">
										��	��
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
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
	<logic:notEmpty name="message">
		<logic:equal name="message" value="bh exits">
			<script>alert("����Ѿ�����!����ʧ��")</script>
		</logic:equal>
		<logic:equal name="message" value="stu exits">
			<script>alert("��ѧ���Ѿ�����!����ʧ��")</script>
		</logic:equal>
		<logic:equal name="message" value="insert success">
			<script>
				alert("���ӳɹ�!");
				dialogArgumentsQueryChick();
				Close();	
			</script>
		</logic:equal>
		<logic:equal name="message" value="insert fail">
			<script>
				alert("����ʧ��!");
				document.getElementById("tmpdiv").innerHTML = "";
			</script>
		</logic:equal>
	</logic:notEmpty>
	<div id="tmpdiv"></div>
</html>
