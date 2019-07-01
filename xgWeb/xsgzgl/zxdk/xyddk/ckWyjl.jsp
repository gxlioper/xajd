<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/wyjl.js"></script>
		
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
	</head>
	<body>
		<html:form action="/zxdkWyjl" method="post" styleId="wyjlForm">
			<html:hidden property="splcid" value="${cssz.xydshlc }"/>
			<html:hidden property="dkzesx" styleId="dkzesx" value="${cssz.dkzesx }"/>
			<input type="hidden" id="dqxn" value="${dqxn }" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<logic:equal name="xxdm" value='10335'>
						<%@ include file="/xsgzgl/comm/bdpz/viewStudentNolink.jsp" %>
					</logic:equal>
					<logic:notEqual name="xxdm" value='10335'>
						<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					</logic:notEqual>
				</table>
			</div>
			<div class="tab"  id="content" style="margin-top: 5px; margin-bottom: -10px; overflow-x:hidden;" >
			</div>
			<table style="margin-bottom: 5px" width="100%" border="0"
					class="formlist" id="dkxxList">
				<thead>
						<tr>
							<th colspan="5">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_szxx">
						<tr>
							<th>
								<div align="center">
									�ܶ�
								</div>
							</th>
							<th>
								<div align="center">
									ÿ�������
								</div>
							</th>
							<th>
								<div align="center">
									ÿ��ѧ�ӷ�
								</div>
							</th>
							<th>
								<div align="center">
									ÿ��ס�޷�
								</div>
							</th>
							<th>
								<div align="center">
									ÿ�������
								</div>
							</th>
						</tr>
						<logic:notEmpty name="dkxxList">
							<logic:iterate id="dkxxList" name="dkxxList">
								<tr>
									<td align="center">
										${dkxxList.dkje }
									</td>
									<td align="center">
										${dkxxList.mnje }
									</td>
									<td align="center">
										${dkxxList.xfdks }
									</td>
									<td align="center">
										${dkxxList.zsfdks }
									</td>
									<td align="center">
										${dkxxList.shfdks }
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="dkxxList">
							<tr>
								<td colspan="5" align="center">
									�������ݣ�
								</td>
							</tr>
						</logic:empty>
			</table>
			<div class="tab"  id="content" style="margin-top: 5px; margin-bottom: -10px; overflow-x:hidden;" >
			</div>
			<table style="margin-bottom: 5px" width="100%" border="0"
					class="formlist" id="fkList">
				<thead>
						<tr>
							<th colspan="4">
								<span>�Ŵ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_szxx">
						<tr>
							<th>
								<div align="center">
									�ſ����
								</div>
							</th>
							<th>
								<div align="center">
									��ͬ���
								</div>
							</th>
							<th>
								<div align="center">
									�Ŵ�ʱ��
								</div>
							</th>
							<th>
								<div align="center">
									�Ŵ����
								</div>
							</th>
						</tr>
						<logic:notEmpty name="fkList">
							<logic:iterate id="fk" name="fkList">
								<tr>
									<td align="center">
										${fk.dkxn }
									</td>
									<td align="center">
										${fk.htbh }
									</td>
									<td align="center">
										${fk.fksj }
									</td>
									<td align="center">
										${fk.fkje }
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="fdyList">
							<tr>
								<td colspan="4" align="center">
									�������ݣ�
								</td>
							</tr>
						</logic:empty>
			</table>
			<div class="tab"  id="content" style="margin-top: 5px; margin-bottom: -10px; overflow-x:hidden;" >
			</div>
			<table width="100%" border="0" class="formlist" style="margin-bottom: 35px;">
				<thead>
					<tr>
						<th colspan="4">
							<span>ΥԼ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tr>
					<th width="15%">�ֻ�����</th>
					<td width="35%">
						${zxdkWyjlForm.sjhm}
					</td>
					<th width="15%">QQ����</th>
					<td width="35%">
						${zxdkWyjlForm.qqhm}
					</td>
				</tr>
				<tr>
					<th width="15%">΢��</th>
					<td width="35%">
						${zxdkWyjlForm.wxhm}
					</td>
					<th width="15%">����</th>
					<td width="35%">
						${zxdkWyjlForm.dzyx}
					</td>
				</tr>
				<tr>
					<th width="15%">�Ƿ���ϵ��</th>
					<td width="35%">
						${zxdkWyjlForm.wyzt}
					</td>
					<th width="15%">ΥԼʱ��</th>
					<td width="35%">
						${zxdkWyjlForm.wysj}
					</td>
				</tr>
				<tr>
					<th width="15%">ΥԼ����</th>
					<td colspan="3">
						${zxdkWyjlForm.wyxq}
					</td>
				</tr>
				<tr>
					<th width="15%">��ע</th>
					<td colspan="3">
						${zxdkWyjlForm.bz}
					</td>
				</tr>
			</table>
			<div>
				<table class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="iFClose();">
										�ر�
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