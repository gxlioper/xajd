<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/jxsb.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var bjdFont = jQuery("font[name=bjdFont]");
				
				if (bjdFont.size() > 0){
					jQuery("#saveButton").attr("disabled",true);
					jQuery("#saveButton").attr("class","disabled");
				}
				
				//������֤
				if (jQuery("img[name=faidImg]").size() > 0){
					jQuery("#saveButton").attr("disabled",true);
					jQuery("#saveButton").attr("class","disabled");
				}
				
			});
		</script>
	</head>
	<body>
		<html:form action="/bzjl_sqsh" method="post" styleId="sqshForm">
			<html:hidden property="xh" />
			<html:hidden property="xmdm" />
		
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" id="saveButton" onclick="saveJxsb();">
										�� ��
									</button>
									
									<button type="button"  onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${jbxx.xh }
							</td>
							<th width="20%">
								����
							</th>
							<td width="30%">
								${jbxx.xm }
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								${jbxx.xb }
							</td>
							<th>
								���֤��
							</th>
							<td>
								${jbxx.sfzh }
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td>
								${jbxx.nj }
							</td>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								${jbxx.xymc }
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td>
								${jbxx.zymc }
							</td>
							<th>
								�༶
							</th>
							<td>
								${jbxx.bjmc }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�ϱ�����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								��Ŀ����
							</th>
							<td>
								${xmwhModel.xmmc }
							</td>
							<th>
								��Ŀ���
							</th>
							<td>
								${xmwhModel.xmje }
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								
								<logic:notEmpty name="checkResult">
									<logic:iterate id="check" name="checkResult" indexId="i">
										<logic:equal value="true" name="check" property="result">
											<img src='images/ico_38.gif' title='��������'/> ${i+1 }��${check.sqts }<br/>
										</logic:equal>
										<logic:equal value="false" name="check" property="result">
											<img src='images/ico_39.gif' name='faidImg' title='����������'/> ${i+1 }��${check.sqts }<br/>
										</logic:equal>
									</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="checkResult">
									<font color='green'>����������</font>
								</logic:empty>								
							</td>
						</tr>
						<tr>
							<th>
								��������ϱ�����
							</th>
							<td colspan="3">
								<table width="99%" style="text-align: center;">
									<tr>
										<th style="text-align: center;">����</th>
										<th style="text-align: center;">���</th>
										<th style="text-align: center;">�Ƿ��뵱ǰ������</th>
									</tr>
									<logic:present name="ysqPjxmList">
										<logic:iterate id="y" name="ysqPjxmList">
											<tr>
												<td>${y.xmmc }</td>
												<td>${y.xmje }</td>
												<td>
													<logic:equal value="yes" name="y" property="bkjd">
														<font color="red" name="bjdFont">���ɼ��</font>
													</logic:equal>
													<logic:notEqual value="yes" name="y" property="bkjd">
														�ɼ��
													</logic:notEqual>
												</td>
											</tr>
										</logic:iterate>
										<logic:empty name="ysqPjxmList">
											<tr><td colspan="3">δ�ҵ��κμ�¼��</td></tr>
										</logic:empty>
									</logic:present>
								</table>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�ϱ�����
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="sqly" styleId="sqly" style="width:99%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>

