<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/hcyhk" method="post">
				<logic:present name="rs">
					<logic:equal name="rs" property="stuExists" value="no">
						<script>
					    	alert("�������ѧ����Ч!");
					    </script>
					</logic:equal>
				</logic:present>

				<input type="hidden" id="pkValue" name="pkValue" value="${rs.pkValue }" />

				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" class="button2" onclick="saveData('hcyhk.do?method=hcyhkbbUpdate&doType=save','xh-sqly')">
											�� ��
										</button>
										<button type="button" class="button2" onclick="window.close();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<thead>
							<tr>
								<th colspan="4">
									<span>����������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="16%">
									<font color="red">*</font>ѧ��
								</th>
								<td width="34%">
									<html:text property="save_xh" readonly="readonly"
										value="${rs.xh }" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this);" />
								</td>
								<th width="16%">
									����
								</th>
								<td width="34%">
									${rs.xm }
								</td>
							</tr>
							<tr>
								<th>
									�Ա�
								</th>
								<td>
									${rs.xb }
								</td>
								<th>
									�꼶
								</th>
								<td>
									${rs.nj }
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
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
									����ʱ��
								</th>
								<td>
									<html:text property="save_sqsj" readonly="true" value="${rs.sqsj }"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									�˳���
								</th>
								<td colspan="3">
									<html:text property="save_qdz"  value="${rs.qdz }" maxlength="50"/>
									-
									<html:text property="save_zdz"  value="${rs.zdz }"  maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>��������
								</th>
								<td colspan="3" style="word-break:break-all;">
									<html:text value="${rs.sqly }" property="save_sqly" maxlength="50" style="width:90%" styleId="sqly"/>
								</td>
							</tr>
							<tr>
								<th>
									��ע<br/>
									<font color="red">(��400��)</font>
								</th>
								<td colspan="3" style="word-break:break-all;">
									<html:textarea property='save_bz' style="width:90%" 
												   rows='5' value="${rs.bz }" 
												   onblur="checkLen(this,400)"/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
		</html:form>
		<logic:present name="message">
			<script>
				alertInfo('${message}',function(t){
					if(t=="ok"){
						dialogArgumentsQueryChick();
			 			window.close();
					}
				});
			</script>
		</logic:present>
	</body>
</html>
