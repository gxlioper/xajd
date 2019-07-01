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
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type='text/javascript' src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/xsxx/shgzadd.js"></script>
	<script type="text/javascript">
		jQuery(function(){
			onShow();
		})
	</script>
	<body >
		<html:form action="/sjxy_shgzwh.do">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�${title }
				</div>
			</div>

			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm" />
			<input type="hidden" id="url" name="url"
				value="/sjxy_shgzwh.do?method=shgzadd" />
			<input type="hidden" id="doType" name="doType" value="" />
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }" />
			<input type="hidden" id="option" name="shone" value="${option}"/>
			<fieldset>
				<legend>
					ѧ��������Ϣ
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>��д��Ṥ������</b>
							</td>
						</tr>
					</thead>
					<tr style="height: 23px">
						<td align="right" width="15%">
							<font style="color: red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="35%">
							<logic:equal value="add" name="option">
								<logic:equal value="stu" name="userType">
									<html:text styleId="xh" property="xh"
										style="width:100%;heigh:100%" value="${rs.xh}" readonly="true" />
								</logic:equal>

								<logic:notEqual value="stu" name="userType">
									<html:text property="xh" styleId="xh" readonly="readonly"
										onchange="checkXhExists($('getStuInfo').value);"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:notEqual>
							</logic:equal>

							<logic:notEqual value="add" name="option">
								<html:text styleId="xh" property="xh"
									style="width:100%;heigh:100%" value="${rs.xh}" readonly="true" />
							</logic:notEqual>
						</td>
						<td align="right" width="15%">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							${rs.xymc }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							������
						</td>
						<td align="left">
							${rs.xm }
						</td>
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							${rs.xb }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							��ϵ��ʽ��
						</td>
						<td align="left">
							${rs.sjhm }
						</td>
						<td align="right">
							����Ա����:
						</td>
						<td align="left">
							${rs.fdyxm }
						</td>
					</tr>

					<logic:equal value="shone" name="option">
						<tr style="height: 23px">
							<logic:equal value="xy" name="user">
								<input type="hidden" name="xtwshsj" value="${rs.xtwshsj }" />
								<input type="hidden" name="xtwsh" value="${rs.xtwsh }" />
								<input type="hidden" name="ftwshsj" value="${nowtime }" />
								<td align="right">
									����ί��ˣ�
								</td>
								<td align="left">
									<html:select property="ftwsh" value="${rs.ftwsh }">
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
									</html:select>
								</td>
							</logic:equal>
							<logic:equal value="xx" name="user">
								<input type="hidden" name="ftwshsj" value="${rs.ftwshsj }" />
								<input type="hidden" name="ftwsh" value="${rs.ftwsh }" />
								<input type="hidden" name="xtwshsj" value="${nowtime }" />
								<td align="right">
									У��ί��ˣ�
								</td>
								<td align="left">
									<html:select property="xtwsh" value="${rs.xtwsh }">
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
									</html:select>
								</td>
							</logic:equal>
						</tr>
					</logic:equal>
					<logic:equal value="modi" name="option">
						<input type="hidden" name="xtwshsj" value="${rs.xtwshsj }" />
						<input type="hidden" name="xtwsh" value="${rs.xtwsh }" />
						<input type="hidden" name="ftwshsj" value="${rs.ftwshsj }" />
						<input type="hidden" name="ftwsh" value="${rs.ftwsh }" />
					</logic:equal>

				</table>
			</fieldset>
			<fieldset>
				<legend>
					��ְ���
				</legend>
				<p>
					<logic:equal value="shone" name="option">
						<input  value="+"
							style="width: 20px" />
						<input  value="-" 
							style="width: 20px" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd1" style="width: 20px">
						&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel1" style="width: 20px">
						&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:equal>
					
					<logic:notEqual value="shone" name="option">
						<input  value="+"
							onclick="trAdd('flag1','add','numAdd1','rzqk');"
							style="width: 20px" />
						<input  value="-" onclick="trDel('flag1','delRow1');"
							style="width: 20px" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd1" style="width: 20px"
							onblur="trAdd('flag1','madd','numAdd1','rzqk')">
						&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel1" style="width: 20px"
							onblur="trDelAll('flag1','numDel1')">
						&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
				</p>
				<table class="tbstyle" align="center" width="100%" id="tTb">
					<tr>
						<td>
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT"
									class="tbstyle">
									<!-- ��ӡʱ��һ�в���ʾ- -->
									<thead style="height: 23px">
										<tr>
											<td align="center" nowrap="nowrap" style='width:6%'>
												ѡ��ɾ����
											</td>
											<td align="center" nowrap="nowrap" style='width:17%'>
												��ʼʱ��
											</td>
											<td align="center" nowrap="nowrap" style='width:17%'>
												����ʱ��
											</td>
											<td align="center" nowrap="nowrap" style='width:20%'>
												��ְ����
											</td>
											<td align="center" nowrap="nowrap" style='width:20%'>
												ְ��
											</td>
											<td align="center" nowrap="nowrap" style='width:20%'>
												��������
											</td>
										</tr>
									</thead>
									<tbody width="100%" align="center" class="tbstyle" id="flag1">
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>

			<fieldset>
				<legend>
					�����
				</legend>
				<p>
					<logic:equal value="shone" name="option">
					<input  value="+" style="width: 20px" />
					<input  value="-" style="width: 20px" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
					<input type="text" name="numAdd2" id="numAdd" style="width: 20px">
					&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
					<input type="text" name="numDel" id="numDel2" style="width: 20px">
					&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:equal>
					
					<logic:notEqual value="shone" name="option">
					<input  value="+"
						onclick="trAdd('flag2','add','numAdd2','hjqk');"
						style="width: 20px" />
					<input  value="-" onclick="trDel('flag2','delRow2');"
						style="width: 20px" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
					<input type="text" name="numAdd2" id="numAdd" style="width: 20px"
						onblur="trAdd('flag2','madd','numAdd2','hjqk')">
					&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
					<input type="text" name="numDel" id="numDel2" style="width: 20px"
						onblur="trDelAll('flag2','numDel2')">
					&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
				</p>
				<table class="tbstyle" align="center" width="100%" id="tTb">
					<tr>
						<td>
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT"
									class="tbstyle">
									<!-- ��ӡʱ��һ�в���ʾ- -->
									<thead style="height: 23px">
										<tr>
											<td align="center" nowrap="nowrap" style='width:6%'>
												ѡ��ɾ����
											</td>
											<td align="center" nowrap="nowrap" style='width:17%'>
												��ʼʱ��
											</td>
											<td align="center" nowrap="nowrap" style='width:17%'>
												����ʱ��
											</td>
											<td align="center" nowrap="nowrap" style='width:20%'>
												�������
											</td>
											<td align="center" nowrap="nowrap" style='width:20%'>
												�ڽ�����
											</td>

										</tr>
									</thead>
									<tbody width="100%" align="center" class="tbstyle" id="flag2">
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>

				<logic:notEqual value="add" name="option">
					<table class="tbstyle" width="100%">
						<logic:notEqual value="view" name="option">
							<tr align="left">
								<td align="right" width="20%">
									����ί��������
									<br />
									<font color="red">(������400����)</font>
								</td>
								<td colspan="3">
									<logic:equal value="xy" name="user">
										<html:textarea property='ftwshyj' style="width:99%" rows='5'
											value="${rs.ftwshyj}" onblur="chLeng(this,800)" />
									</logic:equal>
									<logic:notEqual value="xy" name="user">
										<html:textarea property='ftwshyj' style="width:99%" rows='5'
											readonly="true" value="${rs.ftwshyj}"
											onblur="chLeng(this,800)" />
									</logic:notEqual>
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									У��ί��������
									<br />
									<font color="red">(������400����)</font>
								</td>
								<td colspan="3">
									<logic:equal value="xx" name="user">
										<html:textarea property='xtwshyj' style="width:99%" rows='5'
											value="${rs.xtwshyj}" onblur="chLeng(this,800)" />
									</logic:equal>
									<logic:notEqual value="xx" name="user">
										<html:textarea property='xtwshyj' style="width:99%" rows='5'
											readonly="true" value="${rs.xtwshyj}"
											onblur="chLeng(this,800)" />
									</logic:notEqual>
								</td>
							</tr>
						</logic:notEqual>
						<logic:equal value="view" name="option">
							<tr align="left">
								<td align="right" width="20%">
									����ί��������
									<br />
									<font color="red">(������400����)</font>
								</td>
								<td colspan="3">
									<html:textarea property='ftwshyj' style="width:99%" rows='5'
										value="${rs.ftwshyj}" onblur="chLeng(this,800)"
										readonly="true" />
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									У��ί��������
									<br />
									<font color="red">(������400����)</font>
								</td>
								<td colspan="3">
									<html:textarea property='xtwshyj' style="width:99%" rows='5'
										value="${rs.xtwshyj}" onblur="chLeng(this,800)"
										readonly="true" />
								</td>
							</tr>
						</logic:equal>
					</table>
				</logic:notEqual>

			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton"> <logic:equal value="true"
						name="isApply">
						<logic:equal value="add" name="option">
							<button type="button" class="button2" id="buttonSave"
								onclick="save('/xgxt/sjxy_shgzwh.do?method=shgzadd&doType=add');"
								style="width: 80px">
								�� ��
							</button> 
						&nbsp;&nbsp;
						</logic:equal>

						<logic:equal value="shone" name="option">
							<button type="button" class="button2" id="buttonSave"
								onclick="save('/xgxt/sjxy_shgzwh.do?method=shgzshone&doType=save');"
								style="width: 80px">
								�� ��
							</button> 
						&nbsp;&nbsp;
						</logic:equal>


						<logic:equal value="modi" name="option">
							<button type="button" class="button2" id="buttonSave"
								onclick="save('/xgxt/sjxy_shgzwh.do?method=shgzViewAndModi&doType=save');"
								style="width: 80px">
								�� ��
							</button> 
						&nbsp;&nbsp;
						</logic:equal>
					</logic:equal> <logic:notEqual value="add" name="option">
						<button type="button" class="button2" id="buttonClose" onclick="window.close();return false;"
							style="width: 80px" id="buttonClose">
							�� ��
						</button>
					</logic:notEqual> </span>
			</div>
			<logic:present name="msg">
				<hidden type="hidden" id="msg" value="${msg}" />
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<logic:present name="result">
			<script type="text/javascript">
			Close();
			if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				window.dialogArguments.document.getElementById('search_go').click();	
			}
		</script>
		</logic:present>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
