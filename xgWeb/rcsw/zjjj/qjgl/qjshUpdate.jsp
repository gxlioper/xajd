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
		<html:form action="/zjjj_rcsw_qjgl" method="post">				
				<input type="hidden" name="pkValue" value="${param.pkValue }"/>
				<div class="tab">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="4"><span>ѧ�������Ϣ</span></th>
							</tr>
						</thead>
						<tr>
							<th width="20%">
								<font color="red">*</font>ѧ��
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
								ѧ��
							</th>
							<td>
								${rs.xn }
							</td>
							
							<th>
								ѧ��
							</th>
							<td>
								${xqmc }
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
								���Һ�
							</th>
							<td>
								${rs.qsh }
							</td>
						</tr>
						<tr>
							<th>
								��ϵ��ʽ
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
							<th>
								�ҳ���ϵ��ʽ 
							</th>
							<td>
								${rs.jzdh }
							</td>
						</tr>
						<tr>
							<th>
								��ٿ�ʼʱ��
							</th>
							<td>
								${rs.qjkssj }
							</td>
							<th>
								��ٽ���ʱ��
							</th>
							<td>
								${rs.qjjssj }
							</td>
						</tr>
						<tr>
							<th>
								����Ա���
							</th>
							<td>
								${rs.sh1 }
							</td>
							<th>
								����Ա�����
							</th>
							<td>
								${rs.shr1 }
							</td>
						</tr>
						<tr>
							<th>
								���������
							</th>
							<td>
								${rs.sh2 }
							</td>
							<th>
								�����������
							</th>
							<td>
								${rs.shr2 }
							</td>
						</tr>
						<tr>
							<th>
								ϵ������
							</th>
							<td>
								${rs.sh3 }
							</td>
							<th>
								ϵ��������
							</th>
							<td>
								${rs.shr3 }
							</td>
						</tr>
						<tr>
							<th>
								ѧ�������
							</th>
							<td>
								${rs.sh4 }
							</td>
							<th>
								ѧ���������
							</th>
							<td>
								${rs.shr4 }
							</td>
						</tr>
						
						<tr>
							<th>
								<font color="red">*</font>�������
							</th>
							<td>
								${rs.qjts }��
							</td>
							<th>
								����ڼ��Ƿ�סУ
							</th>
							<td>
								${rs.sfzx }
							</td>
						</tr>
						
						<tr>
							<th>
								�������<br/>
								<font color="red">(����¼��200��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="qjsy" style="width: 95%;word-break:break-all;" readonly="true"
								 onblur="chLeng(this,200);" rows='5' value="${rs.qjsy}"/>
							</td>
						</tr>
						<tr>
							<th>
								��ע<br/>
								<font color="red">(����¼��100��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="bz" style="width: 95%;word-break:break-all;"  onblur="chLeng(this,100);" readonly="true" value="${rs.bz}"
									rows='5' />
							</td>
						</tr>
						
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
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
