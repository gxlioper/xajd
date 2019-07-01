<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript">
			function dataSave(url,mustFill){
				var eles = mustFill.split("-");
				for (i = 0; i < eles.length; i++) {
					if (document.getElementById(eles[i]).value == "") {
						alertInfo("�����ֶ�δ��������");
						return false;
					}
				}
				
				$('buttonSave').disabled = "disabled";
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			
		</script>
	</head>
	<body>
		<html:form action="/rcsw_gzdx_lxgl" method="post">				
				<input type="hidden" name="pkValue" value="${param.pkValue }"/>
				<div class="tab">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="4"><span>ѧ����У��Ϣ</span></th>
							</tr>
						</thead>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${rs.xh }
							</td>
							<th width="20%">
								����
							</th>
							<td width="30%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								Ժϵ
							</th>
							<td>
								${rs.xymc }
							</td>
							<th>
								רҵ
							</th>
							<td>
								${rs.zymc }
							</td>
							
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td>
								${rs.bjmc }
							</td>
							<th>
								��ϵ�绰
							</th>
							<td>
								${rs.sjhm }
								<logic:notEqual value="" name="rs" property="lxdh">
									<logic:notEqual value="" name="rs" property="sjhm">
									/
									</logic:notEqual>
								</logic:notEqual>
								${rs.lxdh }
							</td>
						</tr>
						<tr>
							<th>ԭ���Һ�</th>
							<td>${rs.qsh }</td>
							<th>
								�Ƿ����ҹ��
							</th>
							<td>
								${rs.sfnyf }
							</td>
						</tr>
						<tr>
							<th>
								��У��ʼʱ��
							</th>
							<td>
								${rs.kssj }
							</td>
							<th>
								��У����ʱ��
							</th>
							<td>
								${rs.jssj }
							</td>
						</tr>
						
						<tr>
							<th>
								��У����
							</th>
							<td>
								${rs.ts }��
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xb" />���
							</th>
							<td>
								${rs.sh1 }
							</td>
							<th>
								<bean:message key="lable.xb" />�����
							</th>
							<td>
								${rs.shr1 }
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xb" />������</th>
							<td colspan="3">
								${rs.shyj1 }
							</td>
						</tr>
						<tr>
							<th>
								ѧУ���
							</th>
							<td>
								${rs.sh2 }
							</td>
							<th>
								ѧУ�����
							</th>
							<td>
								${rs.shr2 }
							</td>
						</tr>
						<tr>
							<th>ѧУ������</th>
							<td colspan="3">
								${rs.shyj2 }
							</td>
						</tr>
						<tr>
							<th>
								�ҳ���ϵ��ʽ 
							</th>
							<td colspan="3">
								${rs.jzlxfs }
							</td>
						</tr>
						<tr>
							<th>
								��Уԭ��<br/>
							</th>
							<td colspan="3">
								<html:textarea property="qjsy" style="width: 95%;word-break:break-all;" readonly="true"
								 onblur="chLeng(this,200);" rows='5' value="${rs.lxyy}"/>
							</td>
						</tr>
						<tfoot>
					      <tr>
					        <td colspan="4">
					          <div class="btn">
								  <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
					          </div></td>
					      </tr>
					    </tfoot>
					</table> 
			</div>
		</html:form>
		
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript" defer="defer">
				alertInfo($('message').value);
			</script>
		</logic:present>
	</body>
</html>
