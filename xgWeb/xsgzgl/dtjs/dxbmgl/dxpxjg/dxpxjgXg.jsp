<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<link rel="stylesheet" href="xsgzgl/dtjs/dtxxglnew/color/dtxxglnew.css" type="text/css" media="all" />
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/xsgzgl/dtjs/dxbmgl/dxpxjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				autoChange();
			});
		</script>
	</head>
	<body>
	<html:form method="post" styleId="form" action="dxbmgl_dxpxjg.do">
		<html:hidden property="xh" styleId="xh"/>
		<input type="hidden" id="jgid" name="jgid" value="${dxpxjgForm.jgid }"/>
		 <!-- ��ʾ��Ϣ end-->
			<div class="prompt" id="div_help">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
		 <!-- ��ʾ��Ϣ end-->		
		<div style="tab;width:100%;height:375px;overflow-x:hidden;overflow-y:auto;top: 22px;">
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th width="16%">ѧ��</th>
						<td width="34%">${dxpxjgForm.xh }</td>
						<th width="16%">����</th>
						<td width="34%">${dxpxjgForm.xm }</td>
					</tr>
					<tr>
						<th>�Ա�</th>
						<td>${dxpxjgForm.xb }</td>
						<th>���֤��</th>
						<td>${dxpxjgForm.sfzh }</td>
					</tr>
					<tr>
						<th>�꼶</th>
						<td>${dxpxjgForm.nj }</td>
						<th>ѧԺ</th>
						<td>${dxpxjgForm.xymc }</td>
					</tr>
					<tr>
						<th>רҵ</th>
						<td>${dxpxjgForm.zymc }</td>
						<th>�༶</th>
						<td>${dxpxjgForm.bjmc }</td>
					</tr>
				</tbody>
			</table>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѵ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">��ѵ�ڴ�<input type="hidden" id="pxqc" name="pxqc" value="${dxpxjgForm.pxqc }"/></th>
							<td width="34%">
								${dxpxjgForm.pxqc }
							</td>
							<th width="16%">��ѵʱ��</th>
							<td id="pxsj" width="34%"><input type="hidden" id="pxid" name="pxid" value="${dxpxjgForm.pxid}"/>
							${dxpxjgForm.pxsj }</td>
						</tr>
						<tr>
							<th>���ڳɼ�</th>
							<td><input type="hidden" id="kqcjbfb" value="${dxpxjgForm.kqcjbfb }"/>
								<html:text maxlength="3" property="kqcj" styleId="kqcj" onblur="onlyNumInput(this);jszf();"/>
							</td>
							<th>ʵ���ɼ�</th>
							<td><input type="hidden" id="sjcjbfb" value="${dxpxjgForm.sjcjbfb}"/>
								<html:text maxlength="3" property="sjcj" styleId="sjcj" onblur="onlyNumInput(this);jszf();"/>
							</td>
						</tr>
						<tr>
							<th>�ʼǳɼ�</th>
							<td><input type="hidden" id="bjcjbfb" value="${dxpxjgForm.bjcjbfb }"/>
								<html:text maxlength="3" property="bjcj" styleId="bjcj" onblur="onlyNumInput(this);jszf();"/>
							</td>
						</tr>
					<logic:equal value="xx" name="userStatus" scope="session">
						<tr>
							<th>���Գɼ�</th>
							<td><input type="hidden" id="kscjbfb" value="${dxpxjgForm.kscjbfb}"/>
								<html:text maxlength="3" property="kscj" styleId="kscj" onblur="onlyNumInput(this);jszf();"/>
							</td>
							<th>�����ɼ�</th>
							<td>
								<html:text property="zpcj" styleId="zpcj" onblur="onlyNumInput(this)"/>
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="admin" name="userStatus" scope="session">
						<tr>
							<th>���Գɼ�</th>
							<td><input type="hidden" id="kscjbfb" value="${dxpxjgForm.kscjbfb}"/>
								<html:text maxlength="3" property="kscj" styleId="kscj" onblur="onlyNumInput(this);jszf();"/>
							</td>
							<th>�����ɼ�</th>
							<td>
								<html:text property="zpcj" styleId="zpcj" onblur="onlyNumInput(this)"/>
							</td>
						</tr>
					</logic:equal>
						<tr>
							<th>���۽��</th>
							<td>
								<html:select property="pjjg">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
									<html:option value="����">����</html:option>
									<html:option value="������">������</html:option>
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="saveXg();return false;" id="buttonSave">
									�� ��
								</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</html:form>
	</body>
</html>
