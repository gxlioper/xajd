<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script	type="text/javascript">
			jQuery(function(){
			});
		</script>
	</head>
	<body>
		<html:form action="/jyglnew_jygl_byqxgl" method="post" styleId="byqxForm">
			<html:hidden property="xh"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:315px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ҵȥ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="20%">�ֻ�����</th>
							<td width="30%">
								${rs.sjhm }
							</td>
							<th width="20%">QQ����</th>
							<td width="30%">
								${rs.qqhm }
							</td>
						</tr>
						<tr>
							<th width="">
							<logic:equal value="70002" name="xxdm">
								ʵ�ʹ�����λ
							</logic:equal>
							<logic:notEqual value="70002" name="xxdm">							
								��ҵ��λ
							</logic:notEqual>
							</th>
							<td width="">
								${rs.jydw }
							</td>
							<th width="">��ҵ��λ����</th>
							<td width="">
								${rs.jydwxzmc }
							</td>
					    </tr>
						<tr>
							<th width="">��ǲ��λ</th>
							<td width="">
								${rs.pqdw }
							</td>
							<th width="">��ҵ��ʽ</th>
							<td width="">
								${rs.jyxsmc }
							</td>
					    </tr>
					    <!-- �Ͼ��ߵ�ְҵ����ѧУ -->
					    <logic:equal value="10874" name="xxdm">
					    	<tr>
						    	<th>ѧ��</th>
								<td>
									${rs.xl}	
								</td>
								<th>ѧλ</th>
								<td>
									${rs.xw}
								</td>
						    </tr>
					    </logic:equal>
					    <logic:equal value="70002" name="xxdm">
					    	<tr>
						    	<th>��ҵȥ��</th>
								<td>
									${rs.byqxmc}
								</td>
								<th>��ҵ���</th>
								<td>
									${rs.jylbmc}
								</td>
						    </tr>
						    <tr>
						    	<th>��ҵ״��</th>
						    	<td>
						    		${rs.jyzkmc}
						    	</td>
						    	<th>����֤��</th>
						    	<td>
						    		${rs.bdzh}
						    	</td>
						    </tr>
						    <tr>
						    	<th>��ǲʱ��</th>
						    	<td>
						    		${rs.pqsj}
						    	</td>
						    	<th>ѧ������Ͷ�ݵ�λ</th>
						    	<td>
						    		${rs.tddw}
						    	</td>
						    </tr>
						    <tr>
						    	<th>����ת��ʱ��</th>
						    	<td>${rs.zdsj}</td>
						    	<th></th>
						    	<td></td>
						    </tr>
						    <tr>
						    	<th>��ע</th>
						    	<td colspan="3">
						    		${rs.bz}
						    	</td>
						    </tr>
					    </logic:equal>
					</tbody>
				 </table>
				</div>
				<div>
					<table width="100%" border="0" class="formlist">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" type="button" onclick="iFClose();">
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

