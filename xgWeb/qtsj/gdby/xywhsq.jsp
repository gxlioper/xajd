<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type='text/javascript' src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/qtsj/xywhgl.js"></script>
	<script type="text/javascript" src="js/qtsjFunction.js"></script>

	<body>
		<html:form action="/gdby_xywhgl.do" enctype="multipart/form-data">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�${title }
				</div>
			</div>

			<input type="hidden" id="doType" name="doType" value="" />
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }" />
			<fieldset>
				<legend>
					У԰�Ļ�����
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>У԰�Ļ�����</b>
							</td>
						</tr>

						<tr>
							<td align="right" width="20%">
								����룺
							</td>
							<td align="left" width="30%">
								<html:text property="hddm" value="${hdxx.hddm }" readonly="true"></html:text>
							</td>
							<td align="right" width="20%">
								����ƣ�
							</td>
							<td align="left" width="30%">
								<html:text property="hdmc" value="${hdxx.hdmc }" readonly="true"></html:text>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								�������
							</td>
							<td align="left" colspan="3">
								<html:textarea property="hdms" style="width:99%" rows='8' value="${hdxx.hdms }" readonly="true">
								</html:textarea>
							</td>
						</tr>

						<tr>
							<td align="right" width="20%">
								�����ˣ�
							</td>
							<td align="left" width="30%">
								<html:text property="username" value="${userNameReal }" style="width:180px" readonly="true"></html:text>
							</td>
							<td align="right" width="20%">
								<font color="red">*</font>�����˵绰��
							</td>
							<td align="left" width="30%">
								<html:text property="lxdh" styleId="lxdh" value="${sqxx.lxdh }" style="width:180px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>

						<tr>
							<td align="right" width="20%">
								���賡�أ�
							</td>
							<td align="left" width="30%" colspan="1">
								<html:select property="cdmc" style="width:180px" styleId="cdmc"  value="${sqxx.cdmc }">
									<html:option value=""></html:option>
									<logic:iterate id="map" name="cdList">
										<html:option value="${map.cdmc }">${map.cdmc }</html:option>
									</logic:iterate>
								</html:select>
							</td>
							<td align="right" width="20%">
								�������ʣ�
							</td>
							<td align="left" width="30%" colspan="1" styleId="wzmc">
								<html:select property="wzmc" style="width:180px"  value="${sqxx.wzmc }">
									<html:option value=""></html:option>
									<logic:iterate id="map" name="wzList">
										<html:option value="${map.wzmc }">${map.wzmc }</html:option>
									</logic:iterate>
								</html:select>
							</td>
						</tr>
						<logic:equal value="true" name="sfstcy">
							<td align="right" width="20%">
								<font color="red">*</font>�������ƣ�
							</td>
							<td align="left" colspan="3">
								<html:select property="stmc" value="${sqxx.stmc }" style="width:180px">
									<logic:iterate id="str" name="stList">
										<html:option value="${str }">${str }</html:option>
									</logic:iterate>
								</html:select>
							</td>
						</logic:equal>
						<tr>
							<td align="right">
								�������ɣ�<font color="red">(������400����)</font>
							</td>
							<td align="left" colspan="3">
								<html:textarea property="sqly" style="width:99%" rows='8' value="${sqxx.sqly }" onblur="chLeng(this,400)"></html:textarea>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>


			<div class="buttontool" align="center">
				<logic:equal value="true" name="isApply">
					<button class="button2" id="buttonSave"
						onclick="saveData('/xgxt/gdby_xywhgl.do?method=xywhsq&doType=add','lxdh');"
						style="width: 80px">
						�� ��
					</button> 
						&nbsp;&nbsp;
				</logic:equal>
				<button class="button2" id="buttonClose" onclick="location='gdby_xywhgl.do?method=xywhsqforuser'"
					style="width: 80px" id="buttonClose">
					�� ��
				</button>
			</div>
			<logic:present name="msg">
				<hidden type="hidden" id="msg" value="${msg}" />
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
