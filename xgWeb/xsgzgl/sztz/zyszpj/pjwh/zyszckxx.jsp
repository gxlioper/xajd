<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
			<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/comm/ymPrompt.js" ></script>
	<script type='text/javascript' src="js/comm/message.js"></script>
	<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<html:form action="/zyszpjwh" method="post"
		 styleId="form"> 
		<input type="hidden" name="url" id="url"
			value="zyszpjwh.do?method=add">
			<input type="hidden" name="tableName" id="tableName"
				value="view_xsxxb">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right" width="15%">
								ѧ�ţ�
							</td>
							<td align="left" width="40%">
								<html:hidden property="zyszid" value="${zyszid}"/>
								<html:hidden property="xn" value="${xn}"/>
								<html:hidden property="xq" value="${xq}"/>
								${stuInfo.xh}
							</td>
							<td align="right" width="15%">
								������
							</td>
							<td align="left">
								${stuInfo.xm}
							</td>
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								${stuInfo.nj}
							</td>
							<td align="right">
								<bean:message key="lable.xb" />��
							</td>
							<td align="left">
								${stuInfo.xymc}
							</td>
						</tr>
						<tr>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								${stuInfo.zymc}
							</td>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								${stuInfo.bjmc}
							</td>
						</tr>
						<tr>
							<td align="right" width="10%">
								ѧ�꣺
							</td>
							<td align="left">
								${data.xn}
							</td>
							<td align="right">
								ѧ�ڣ�
							</td>
							<td align="left">
								${data.xqmc}
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>ְҵ���ʻ���� </span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								���
							</td>
							<td>
								����Ŀ
							</td>
							<td>
								ʱ��
							</td>
							<td>
								�ص�
							</td>
							<td>
								�����
							</td>
							<td>
								���뼰�����
							</td>
						</tr>
							<tbody id="tbody_add">
							<logic:notEmpty name="zxm">
							<logic:iterate name="zxm" id="s" indexId="i">
								<tr>
									<td>
										${i+1}
										<input type="hidden" id="zxm" name="zxm" value="${s.zxmId}"/>
									</td>
									<td width="120px">
										${s.xmlbmc}
									</td>
									<td width="120px">
										${s.sj}
									</td>
									<td width="120px">
									${s.dd}
									</td>
									<td width="120px">
									${s.hdnr}
									</td>
									<td width="120px">
										${s.cyjhjqk}
									</td>
								</tr>
							</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="15%" height="120px">����</td>
							<td width="85%">${data.zpxx}</td>
						</tr>
					</tbody>
				</table>
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="15%" height="120px">����</td>
								<td width="85%">${data.hpxx}</td>
							</tr>
							<tr>
								<td width="15%" height="20px">������</td>
								<td width="85%">${data.hpr}</td>
							</tr>
						</tbody>
					</table>
					<logic:equal name="query" value="brcx">
						<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>ʦ����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="15%" height="120px">����</td>
								<td width="85%">${data.spxx}</td>
							</tr>
								<tr>
								<td width="15%" height="20px">���۵ȼ�</td>
								<td width="85%">${data.pjdj}</td>
							</tr>
						</tbody>
					</table>
					</logic:equal>
					<logic:equal name="query" value="lscx">
						<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>ʦ����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="15%" height="120px">����</td>
								<td width="85%">${data.spxx}</td>
							</tr>
								<tr>
								<td width="15%" height="20px">���۵ȼ�</td>
								<td width="85%">${data.pjdj}</td>
							</tr>
						</tbody>
						</table>
					</logic:equal>
					<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button"  onclick="iFClose();" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
	</html:form>
</html>