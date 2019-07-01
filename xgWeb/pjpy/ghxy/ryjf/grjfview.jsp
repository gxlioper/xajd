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
	<script type="text/javascript" >
		jQuery(function(){
			onShow();
		})		
	</script>

	<body >
		<html:form action="/ghxy_ryjf.do">
			<div class="title">
				<div class="title_img" id="title_m">
					<logic:equal value="view" name="operation">��ǰλ�ã����˻񽱷ֵ����鿴 
				</logic:equal>
					<logic:equal value="modi" name="operation">��ǰλ�ã����˻񽱷ֵ����޸�
				</logic:equal>

				</div>
			</div>

			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm" />
			<input type="hidden" id="url" name="url"
				value="/ghxy_ryjf.do?method=grjfsq" />
			<input type="hidden" id="doType" value="" />
			<input type="hidden" name="pkValue" value="${param.pkValue }" />
			<fieldset>
				<legend>
					ѧ��������Ϣ
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<thead>
						<tr style="height: 22px">
							<td colspan="4" align="center">
								<b>������Ϣ</b>
							</td>
						</tr>
					</thead>
					<tr style="height: 23px">
						<td align="right" width="15%">
							<font style="color: red">*</font>ѧ�ţ�
						</td>
						<td align="left">
							<input type="hidden" id="xh" name="xh" value="${rs.xh }" />
							${rs.xh }
						</td>
						<td align="right">
							ѧ�꣺
						</td>
						<td align="left">
							<input type="hidden" name="xn" value="${grjfInfo.xn }" />
							${grjfInfo.xn }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							ѧ�ڣ�
						</td>
						<td align="left">
							<input type="hidden" name="xq" value="${grjfInfo.xq }" />
							${grjfInfo.xqmc }
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
					<tr style="height: 23px">
						<td align="right">
							����Ա��ˣ�
						</td>
						<td>
							${grjfInfo.fdysh }
						</td>
						<td align="right">
							�꼶���������
						</td>
						<td>
							${grjfInfo.njbzrsh }
						</td>
					</tr>
					<tr align="left">
						<td align="right">
							ѧУ��ˣ�
						</td>
						<td>
							${grjfInfo.xxsh }
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
				<span class="openbutton"> <logic:equal value="modi"
						name="operation">
						<button type="button" class="button2" id="buttonSave"
							onclick=
	save('/xgxt/ghxy_ryjf.do?method=grjfViewAndModi&doType=save');;
style="width: 80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:equal>
					<button type="button" class="button2" onclick=
	Close();
style="width: 80px"
						id="buttonClose">
						�� ��
					</button> </span>
			</div>
			<logic:present name="msg">
				<hidden type="hidden" id="msg" value="${msg}" />
				<script>
	alert($("msg").value);
	Close();
	if (window.dialogArguments
			&& window.dialogArguments.document.all("search_go")) {
		window.dialogArguments.document.getElementById('search_go').click();
	}
</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
