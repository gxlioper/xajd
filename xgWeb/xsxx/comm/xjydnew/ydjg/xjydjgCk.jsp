<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsxx/comm/xjydnew/js/xjydjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">

			jQuery(function() {
				initShow();
			});
			</script>
	</head>
	<body>
		<html:form method="post" styleId="xjydjgForm" action="/xjydjg">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
			<html:hidden property="xjydjgid"/>
			<html:hidden property="xh"/>
			<html:hidden property="ydlbdm" styleId="ydlbdm"/>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>ѧ���춯������Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							ѧ���춯���
						</th>
						<td align="left">
							<span style="color:red;">${xjydjgForm.ydlbmc }</span>
						</td>
						<th align="right">
							ѧ��/ѧ��
						</th>
						<td align="left">
							
							${xjydjgForm.xn } ${xjydjgForm.xqmc } 
						</td>
					</tr>
					<tr>
						<th>ѧ��״̬[�춯]</th>
						<td colspan="3">
							<div >
								<table border="0" style="float:left">
									<tr>
										<th style="width:70px">ԭѧ�����</th>
										<td style="width:180px">${xjydjgForm.ydqxjlbmc }</td>
									</tr>
									<tr>
										<th>�Ƿ���ѧ��</th>
										<td>&nbsp;${xjydjgForm.ydqsfyxjmc }</td>
									</tr>
									<tr>
										<th>�Ƿ���У</th>
										<td>&nbsp;${xjydjgForm.ydqsfzxmc }</td>
									</tr>
								</table>
								<img style="float:left;margin:30px 10px" src='images/ssyd/to.gif' ></img>
								<table border="0"  style="float:left">
									<tr>
										<th style="width:90px">�춯��ѧ�����</th>
										<td style="width:180px">${xjydjgForm.ydlbmc }</td>
									</tr>
									<tr>
										<th>�Ƿ���ѧ��</th>
										<td>${xjydjgForm.ydhsfyxjmc }</td>
									</tr>
									<tr>
										<th>�Ƿ���У</th>
										<td>${xjydjgForm.ydhsfzxmc }</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr id="tzbj">
						<th>�����༶</th>
						<td colspan="3">
							<div >
								<table border="0"  style="float:left">
									<tr>
										<th style="width:70px;height:20px;">ԭ�꼶</th>
										<td style="width:180px" id="ydqnj">&nbsp;${xjydjgForm.ydqnj }</td>
									</tr>
									<tr>
										<th style="height:20px;">ԭ<bean:message key="lable.xb" /></th>
										<td id="ydqxy">&nbsp;${xjydjgForm.ydqxymc }</td>
									</tr>
									<tr>
										<th style="height:20px;">ԭרҵ</th>
										<td id="ydqzy">&nbsp;${xjydjgForm.ydqzymc }</td>
									</tr>
									<tr>
										<th style="height:20px;">ԭ�༶</th>
										<td id="ydqbj">&nbsp;${xjydjgForm.ydqbjmc }</td>
									</tr>
								</table>
								<img style="float:left;margin:55px 10px" src='images/ssyd/to.gif' ></img>
								<table border="0" style="float:left">
									<tr>
										<th style="width:90px">�춯���꼶</th>
										<td style="width:180px">${xjydjgForm.ydhnj }
										</td>
									</tr>
									<tr>
										<th style="height:20px;">�춯��<bean:message key="lable.xb" /></th>
										<td>${xjydjgForm.ydhxymc }
										</td>
									</tr>
									<tr>
										<th style="height:20px;">�춯��רҵ</th>
										<td>${xjydjgForm.ydhzymc }
										</td>
									</tr>
									<tr>
										<th style="height:20px;">�춯��༶</th>
										<td>${xjydjgForm.ydhbjmc }
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					
					<logic:equal name="xxdm" value="10511">					
						<tr id="xzpd">
							<th align="right" width="10%">
								ѧ��
							</th>
							<td align="left" colspan="3">
								${xjydjgForm.xz }
							</td>
						</tr>
						<tr id="xxpd">
							<th align="right">
								��ԴѧУ/ȥ��ѧУ
							</th>
							<td align="left" colspan="3">
								${xjydjgForm.xxmc }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								�춯ԭ��
							</th>
							<td align="left" colspan="3">
								${xjydjgForm.ydyymc }
							</td>
						</tr>
					</logic:equal>
									
					<tr>
						<th align="right" width="10%">
							��������&nbsp;					
						</th>
						<td colspan="3">
						${xjydjgForm.sqly }
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							ѧ���춯�ĺ�&nbsp;
						</th>
						<td>
							<span style="color:blue;">${xjydjgForm.xjydwh }</span>
						</td>
						
						<logic:notEqual name="xxdm" value="10511">
							<th align="right" width="10%">
								ѧ���춯ʱ��&nbsp;
							</th>
							<td>
								${xjydjgForm.xjydsj }
							</td>
						</logic:notEqual>
						
						<logic:equal name="xxdm" value="10511">
							<th align="right" width="10%">
								ѧ���춯���ʱ��&nbsp;
							</th>
							<td>
								${xjydjgForm.xjydsj }
							</td>
						</logic:equal>
					</tr>
					<tr id="lrqzsj">
						<th>�춯��ֹʱ��</th>
						<td colspan="3">
							${xjydjgForm.sqkssj } &nbsp;��&nbsp; ${xjydjgForm.sqjssj }
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							��ע&nbsp;
						</th>
						<td colspan="3">
							${xjydjgForm.xjydbz }
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
