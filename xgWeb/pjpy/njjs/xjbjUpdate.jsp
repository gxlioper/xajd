<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/xjbjService.js'></script>
		<script src="js/check.js"></script>
	</head>

	<body>
		<input type="hidden" id="userType" value="${userType }"/>
		<input type="hidden" id="pkValue" value="${rs.pkValue }"/>


		<html:form action="/njjsXjbj.do?method=xjbjsq" method="post">
			<div class="tab">
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button name="�� ��"
										onclick="saveUpdate('njjsXjbj.do?method=xjbjUpdate&doType=save','yxlx-bjrs')">
										�� ��
									</button>
									<button name="�� ��"
										onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>����༯���޸�</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>�༶����
							</th>
							<td width="34%">
									<html:hidden property="save_bjdm" styleId="bj" value="${rs.bjdm }"/>
									<html:select property="save_bjdm" styleId="bj" 
												 style="width:200px" value="${rs.bjdm }" 
												 disabled="true">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>����༶����
							</th>
							<td width="34%">
								<html:select property="save_yxlx" styleId="yxlx" value="${rs.yxlx }">
									<html:options collection="xjlxList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�༶����
							</th>
							<td>
								<html:text property="save_bjrs" readonly="true" styleId="bjrs" value="${rs.bjrs }"></html:text>
							</td>
							<th>
								�೤ѧ��
							</th>
							<td>
								<html:text property="save_bzxh" readonly="true" styleId="bzxh" value="${rs.bzxh }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td>
								<html:text property="save_bzrzgh" readonly="true" styleId="bzr" value="${rs.bzrzgh }"></html:text>
							</td>
							<th>
								
							</th>
							<td>

							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td>
								<html:text property="save_sqr" readonly="true" value="${rs.sqr }"></html:text>
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
								��Ҫ�¼�<br/>
								<font color="red">
									&lt;��500��&gt;
								</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_zysj" 
											   style="width:95%" rows="5" 
											   onblur="checkLen(this,500)" 
											   value="${rs.zysj }"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								��ע<br/>
								<font color="red">
									(��200��)
								</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_bz" 
											   style="width:95%" rows="5" 
											   onblur="checkLen(this,200)" value="${rs.bz }"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>

		<logic:present name="message">
			<script>
				alert("${message}");
				if(window.dialogArguments && window.dialogArguments.document.getElementById("isPage")){
					window.dialogArguments.document.getElementById("isPage").value = 'yes';
				}
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
				
			</script>
		</logic:present>

	</body>
</html>
