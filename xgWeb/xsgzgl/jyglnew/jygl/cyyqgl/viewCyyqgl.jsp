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
				if('${rs.sfzc}' == '1'){
					jQuery(".zcgsxx_class").show();
				}else{
					jQuery(".zcgsxx_class").hide();
				}
			});
		</script>
	</head>
	<body>
		<html:form action="/jyglnew_jygl_cyyqglgl" method="post" styleId="cyyqglForm">
			<html:hidden property="gsid"/>
			<div id="cyyqgl_div" style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;height: 465px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ҵ԰����˾��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">ѧ��</th>
							<td width="30%">
								${rs.xn }
							</td>
							<th width="20%">ѧ��</th>
							<td width="30%">
								${rs.xqmc }
							</td>
						</tr>
						<tr>
							<th width="">��˾����</th>
							<td width="" colspan="3">
								${rs.gsmc }
							</td>
					    </tr>
						<tr>
							<th width="">��˾����</th>
							<td width="">
								${rs.gslxmc }
							</td>
							<th width="">��פʱ��</th>
							<td width="">
								${rs.rzsj }
							</td>
					    </tr>
						<tr>
							<th width="">�Ŷ�����</th>
							<td width="">
								${rs.tdrs }
							</td>
					    	<th width="">������ҵ</th>
							<td width="" colspan="3">
								${rs.sshymc }
							</td>
					    </tr>
					    <tr>
					    	<th width="">�Ƿ�ע��</th>
							<td width="" colspan="3">
								${rs.sfzcmc }
							</td>
					    </tr>
					    <tr class="zcgsxx_class">
							<th width="">ע�ṫ˾����</th>
							<td width="" colspan="3">
								${rs.zcgsmc }
							</td>
					    </tr>
						<tr class="zcgsxx_class">
							<th width="">ע��ʱ��</th>
							<td width="">
								${rs.zcsj }
							</td>
							<th width="">ע���ʱ�</th>
							<td width="">
								${rs.zczb }
							</td>
					    </tr>
						<tr class="zcgsxx_class">
							<th width="">��ҵ����</th>
							<td width="">
								${rs.jyrs }
							</td>
					    	<th width="">ע��������ҵ</th>
							<td width="" colspan="3">
								${rs.zcsshymc }
							</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ҵ�Ŷӳ�Ա��Ϣ</span>
							</th>
						</tr>
					</thead>
				 </table>
				
				<div style="height:165px;overflow-y:auto;">
					<table width="100%" border="0" class="datelist" style="margin:2px auto;">
						<thead>
							<tr>
								<td width='15%'>ѧ��</td>
								<td width='12%'>����</td>
<%--								<td width='9%'>�Ա�</td>--%>
<%--								<td width='9%'>�꼶</td>--%>
								<td width='17%'><bean:message key="lable.xb" /></td>
								<td width='17%'>רҵ</td>
								<td width='17%'>�༶</td>
								<td width='11%'>�ֻ�����</td>
								<td width='11%'>QQ����</td>
							</tr>
						</thead>
						<tbody id="tbody_xs">
							<logic:notEmpty name="cyList">
								<logic:iterate id="cy" name="cyList">
									<tr>
										<td><input type='hidden' name='xhArr' value='${cy.xh }' />${cy.xh }</td>
										<td>${cy.xm }</td>
<%--										<td>${cy.xb }</td>--%>
<%--										<td>${cy.nj }</td>--%>
										<td>${cy.xymc }</td>
										<td>${cy.zymc }</td>
										<td>${cy.bjmc }</td>
										<td>${cy.sjhm }</td>
										<td>${cy.qqhm }</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
				</div>
				
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

