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
	<script type="text/javascript" src="js/pjpy/ghxy/grjfsq.js"></script>
	<script type="text/javascript">
		jQuery(function(){
			onShow();
		})
	</script>
	<body>
		<html:form action="/ghxy_ryjf.do">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã����˻񽱷����
				</div>
			</div>

			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm" />
			<input type="hidden" id="url" name="url"
				value="/ghxy_ryjf.do?method=grjfsq" />
			<input type="hidden" id="doType" value="" />
			<input type="hidden" id="operation" value="${operation }" />
			<fieldset>
				<legend>
					ѧ��������Ϣ
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>������Ϣ</b>
							</td>
						</tr>
					</thead>
					<tr style="height: 23px">
						<td align="right" width="15%">
							<font style="color: red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="35%">
							<input type="hidden" id="xh" value="${rs.xh }" />
							<html:text styleId="xh" property="xh"
								style="width:100%;heigh:100%" value="${rs.xh}" readonly="true" />
						</td>
						<td align="right">
							ѧ�꣺
						</td>
						<td align="left">
							<input type="hidden" id="xn"  name="xn" value="${xn }" />
							${xn }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							ѧ�ڣ�
						</td>
						<td align="left">
							<input type="hidden" id="xq" name="xq" value="${xqdm}" />
							${xq }
						</td>
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							${rs.nj }
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
						<td align="right" width="15%">
							Ժϵ��
						</td>
						<td align="left">
							${rs.xymc }
						</td>

						<td align="right">
							רҵ��
						</td>
						<td align="left">
							${rs.zymc }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							�༶��
						</td>
						<td align="left">
							${rs.bjmc }
						</td>
						<td align="right">
							��ϵ�绰��
						</td>
						<td align="left">
							${rs.lxdh }
						</td>
					</tr>
					<tr align="left" style="height: 23px">
						<td align="right">
							����Ա��ˣ�
						</td>
						<td>
							<logic:equal value="fdy" name="userType">
								<input type="hidden" name="fdyshsj" value="${nowtime }" />
								<html:select property="fdysh" value="${grjfInfo.fdysh}">
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
							</logic:equal>
							<logic:notEqual value="fdy" name="userType">
								<input type="hidden" name="fdysh" value="${grjfInfo.fdysh }"/>
								<input type="hidden" name="fdyshsj" value="${grjfInfo.fdyshsj }"/>
								${grjfInfo.fdysh }
							</logic:notEqual>
						</td>
						<td>
							<div align="right">
								�꼶���������
							</div>
						</td>
						<td>
							<logic:equal value="njbzr" name="userType">
								<input type="hidden" name="njbrzshsj" value="${nowtime }" />
								<html:select property="njbzrsh" value="${grjfInfo.njbzrsh}">
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
							</logic:equal>
							<logic:notEqual value="njbzr" name="userType">
								${grjfInfo.njbzrsh }
								<input type="hidden" name="njbzrsh" value="${grjfInfo.njbzrsh }"/>
								<input type="hidden" name="njbzrshsj" value="${grjfInfo.njbzrshsj }"/>
							</logic:notEqual>
						</td>
					</tr>
					<tr align="left">
						<td align="right">
							ѧУ��ˣ�
						</td>
						<td>
							<logic:equal value="xx" name="userType">
								<input type="hidden" name="xxshsj" value="${nowtime }" />
								<html:select property="xxsh" value="${grjfInfo.xxsh}">
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
							</logic:equal>
							<logic:notEqual value="xx" name="userType">							
								${grjfInfo.xxsh }
								<input type="hidden" name="xxsh" value="${grjfInfo.xxsh }"/>
								<input type="hidden" name="xxshsj" value="${grjfInfo.xxshsj }"/>
							</logic:notEqual>
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>
					��ػ����
				</legend>
				<p>
					<input  value="+"
						style="width: 20px" />
					<input  value="-" 
						style="width: 20px" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
					<input type="text" name="numAdd" id="numAdd1" style="width: 20px"/>
					&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
					<input type="text" name="numDel" id="numDel1" style="width: 20px"/>
					&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<table class="tbstyle" align="center" width="100%" id="tTb">
					<tr>
						<td>
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT"
									class="tbstyle">
									<!-- ��ӡʱ��һ�в���ʾ- -->
									<thead style="height: 10px">
										<tr>
											<td align="center" nowrap="nowrap" style='width: 15%'>
												ѡ��ɾ����
											</td>
											<td align="center" nowrap="nowrap" style='width: 15%'>
												��ʱ��
											</td>
											<td align="center" nowrap="nowrap" style='width: 20%'>
												������
											</td>
											<td align="center" nowrap="nowrap" style='width: 35%'>
												������
											</td>
											<td align="center" nowrap="nowrap" style='width: 15%'>
												��������Ļ񽱷�
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

			<div class="buttontool" align="center">
				<span class="openbutton">
						<button type="button" class="button2" id="buttonSave"
							onclick="save('/xgxt/ghxy_ryjf.do?method=grjfshone&doType=save');"
							style="width: 80px">
							�� ��
						</button>
						
						<button type="button" class="button2"
							onclick="Close();return false;"
							style="width: 80px">
							�� ��
						</button>
			</span>
			</div>
			<logic:present name="msg">
				<hidden type="hidden" id="msg" value="${msg}" />
				<script>
					alert($("msg").value);
					Close();
					if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
						window.dialogArguments.document.getElementById('search_go').click();	
					}
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
