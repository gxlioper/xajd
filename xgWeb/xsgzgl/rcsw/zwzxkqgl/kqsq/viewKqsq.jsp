<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/kqsq.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/comm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function() {
			jQuery("#shlccx").load(
					"comm_spl.do?method=lccx&sqid=${kqsqForm.sqid}&tt="
							+ new Date().getTime());
		});
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/zwzxKqsq" method="post" styleId="ZwzxKqsqForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 465px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span><font id="gnmkmc_prompt_span"></font>�鿴</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${kqsqForm.xn}
							</td>
							<th width="20%">
								<span id="ccrq_span"></span>
							</th>
							<td width="30%">
								${kqsqForm.ccrq}
							</td>
						</tr>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${kqsqForm.xqmc}
							</td>
							<th width="20%">
								<span id="cclx_span"></span>
							</th>
							<td width="30%">
								${kqsqForm.cclxmc}
							</td>
						</tr>
							<tr>
								<th width="20%">
									�༶
								</th>
								<td width="30%">
									${kqsqForm.bjmc}
								</td>
								<th width="20%">
									Ӧ������
								</th>
								<td width="30%">
									${kqsqForm.ydrs}
								</td>
							</tr>
							<tr>
								<th width="20%">
									ʵ������
								</th>
								<td width="30%">
									${kqsqForm.sdrs}
								</td>
								<logic:equal name="xxdm" value="12970">
								<th width="20%">
									δ������
								</th>
								</logic:equal>
								<logic:notEqual name="xxdm" value="12970">
									<th width="20%">
										ȱ������
									</th>
								</logic:notEqual>
								<td width="30%">
									${kqsqForm.qqrs}
								</td>
							</tr>
						<tr>
							<th width="20%" id="jlf_th">
								���ɷ�
							</th>
							<td width="30%" id="jlf_td">
								${kqsqForm.jlf}
							</td>
							<th width="20%">
								<span id="jlr_span"></span>
							</th>
							<td width="30%" id="jlr_td">
								${kqsqForm.jlrxm }
							</td>
						</tr>

						<logic:equal name="xxdm" value="10704">
							<tr>
								<th>
									���ศ��Ա
								</th>
								<td id="dbfdy" colspan="3">
										${kqsqForm.dbfdy }
								</td>
							</tr>
						</logic:equal>

						<tr>
						    <th>
								��ע
							</th>
							<td colspan="3">
								${kqsqForm.bz}
							</td>
						</tr>
						<logic:equal value="11647" name="xxdm">
						<tr>
							<th>
								��ȱ�����Υ������
								<br />
								<div align="center">
									(��:�Է�,˯����...)
								</div>
							</th>
							<td align="left">
								${kqsqForm.wjrs}
							</td>
							</tr>
							</logic:equal>
					</tbody>
					<thead>
						<tr>
						<logic:equal name="xxdm" value="12970">
							<th colspan="4">
								<span>δ��ѧ����Ϣ</span>
							</th>
						</logic:equal>
						<logic:notEqual name="xxdm" value="12970">
							<th colspan="4">
								<span>ȱ��ѧ����Ϣ</span>
							</th>
						</logic:notEqual>
						</tr>
					</thead>
				 </table>
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<tr>
							<td width='15%'>ѧ��</td>
							<td width='15%'>����</td>
								<logic:notEqual name="xxdm" value="12970">
									<td width='15%'><font color="red">*</font>ȱ������</td>
									<td width='15%'>���ν���</td>
									<td width='35%'>ȱ�ڱ�ע</td>
								</logic:notEqual>
							<logic:equal name="xxdm" value="12970">
								<td width='35%'>δ��ԭ��</td>
							</logic:equal>
						</tr>
					</thead>
					<tbody id="tbody_qqryxx">
						<logic:iterate id="i" name="qqxsList" indexId="index01">
						<tr>
						<td name="xhArr">${i.xh}</td>
						<td>${i.xm}</td>
							<logic:notEqual value="12970" name="xxdm">
								<td>${i.qqlxmc}</td>
								<td>${i.kkjs}</td>
							</logic:notEqual>
						<td title="${i.ylzd1}">${i.ylzd1str}</td>
						</tr>
						</logic:iterate>
						<logic:empty name="qqxsList">
											<tr>
												<td colspan="7" align="center">��ȱ��ѧ����</td>
											</tr>
										</logic:empty>
									</tbody>
						</tbody>
				</table>
				<logic:notEqual value="�������" name="shztmc">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4" id="shlccx">

								</td>
							</tr>

						</tbody>

					</table>
				</logic:notEqual>
				
				</div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

