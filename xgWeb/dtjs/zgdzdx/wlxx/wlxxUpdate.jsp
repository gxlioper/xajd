<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript">
		function saveWlxx(){
			if($("xh").value == ""){
				alert("ѧ�Ų���Ϊ�գ���ȷ�ϣ���");
				return false;
			}
			showTips('���������У���ȴ�......');
			refreshForm('/xgxt/zgddWlxx.do?method=wlxxUpdate&doType=save');
		}
</script>
</head>
	<body onload="">
		
		<html:form action="/zgddZbr" method="post">
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>���Ž��� - ����ά�� - ������Ϣ����</a>
				</p>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/dtjs/zgdzdx/wlxx/wlxxUpdate.jsp" />
				<fieldset>
					<div class="tab">
					<table width="100%" class="formlist" border="0">
					<thead>
						<tr><th colspan="4"><span>������Ϣ</span></th></tr>
					</thead>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
					          <logic:notEqual name="doType" value="view">
								<button type="button" onclick="saveWlxx();" id="buttonSave">
									�� ��
								</button>
								</logic:notEqual>
								<button type="button" onclick="Close();return false;" id="buttonClose">
									�� ��
								</button>
					          </div></td>
					      </tr>
					    </tfoot>
						
						<tbody>
						<tr>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td align="left" style="width:35%">
								<html:text name='rs' property="xh" readonly = "true"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<logic:equal name="doType" value="add">
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do?wlxx=yes',750,550);"
									class="btn_01" id="buttonFindStu" >
									ѡ��
								</button>
								</logic:equal>
							</td>
							<th>
								����
							</th>
							<td align="left" style="width:35%">
								<html:text name='rs' property="xm" styleId="xm" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" readonly = "true" />
							</td>
							<th>
								�꼶
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj"  readonly = "true" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" readonly = "true"/>
							</td>
							<th>
								רҵ
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" readonly = "true"/>
							</td>
							<th>
								ֵ��ID
							</th>
							<td align="left">
								<html:text name='rs' property="zbid" styleId="zbid" maxlength="20" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<th>
								��λ
							</th>
							<td align="left">
								<html:text name='rs' property="gwmc" styleId="gwmc" maxlength="25" readonly = "true"/>
							</td>
							<th>
								��ϵ�绰
							</th>
							<td align="left">
								<html:text name='rs' property="dh" styleId="dh" maxlength="20" 
								onkeyup="value=value.replace(/[^\d]/g,'') " readonly = "true"/>
							</td>
						</tr>
						<tr>
							<th>
								�����˺�
							</th>
							<td align="left" colspan="3">
								<html:text name='rs' property="yhzh" styleId="yhzh" style="width:95%" maxlength="50"
								onkeyup="value=value.replace(/[^\d]/g,'') " readonly = "true"/>
							</td>
						</tr>
						<tr>
							<th>
								��ʼʱ��
							</th>
							<td align="left">
								<html:text name="rs" property="kssj" styleId="kssj" style="width:95%"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('kssj','y-mm-dd');"/>
							</td>
							<th>
								����ʱ��
							</th>
							<td align="left">
								<html:text name="rs" property="jssj" styleId="jssj" style="width:95%"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jssj','y-mm-dd');"/>
							</td>
						</tr>
						<tr>
							<th>
								ֵ�����
							</th>
							<td align="left" colspan="3">
								<html:textarea name='rs' property="zbqk" styleId="zbqk" style="width:95%"
								onblur="chLeng(this,250)"/>
							</td>
						</tr>
						<tr>
							<th>
								ֵ�౸ע
							</th>
							<td align="left" colspan="3"><br /><html:textarea name='rs' property="wlbz" styleId="wlbz" style="width:95%"
								onblur="chLeng(this,250)"/>
							<br /></td>
						</tr>
						</tbody>
					</table>
					</div>
				</fieldset>
				
			</logic:notEmpty>
			<logic:present name="result">
				<logic:equal name="result" value="yes">
					<script>
				    alert("�ύ�ɹ���");
				    dialogArgumentsQueryChick();
					Close();
				    </script>
				</logic:equal>
				<logic:equal name="result" value="no">
					<script>
				    alert("�ύʧ�ܣ�");
				    </script>				
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
