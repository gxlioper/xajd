<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		function save(){
			var url="/xgxt/xtwhSpbz.do?method=spbzManage&doType=save";
			refreshForm(url);
		}
		function modi(){
			var url="/xgxt/xtwhSpbz.do?method=spbzManage&doType=modi";
			refreshForm(url);
		}
		</script>
	</head>
	<body >
		<html:form action="/xtwhSpbz" method="post">
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
                                    <span>����������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
                                    <html:hidden property="splc" styleId="splc"/>
									<div class="btn">
										<logic:equal name="doType" value="add">
										<button class="button2" onclick="save();return false;">
											����
										</button>
										</logic:equal>
										<logic:equal name="doType" value="update">
                                        <html:hidden name="rs" property="id" styleId="id" />
										<button class="button2" onclick="modi()">
											����
										</button>
										</logic:equal>
										<button class="button2" onclick="Close();return false;">
											�ر�
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
                        <tbody>
							<tr>
							<th>
								<font color="red">*</font>�� ��
							</th>
							<td coalpsn="3">
								<html:text name="rs" property="xh" styleId="xh" />
							</td>
							</tr>
							<tr>
							<th>
								<font color="red">*</font>������λ
							</th>
							<td coalpsn="3">
                                <html:select name="rs" property="spgw"  styleId="spgw">
                                    <html:option value=""></html:option>
                                    <html:options collection="spgwList" property="id" labelProperty="mc" />
                                </html:select>
							</td>
							</tr>
						</tbody>
					</table>
				</div>


			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alert("����ʧ�ܣ�");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alert("�����ɹ���");
					</script>
				</logic:equal>
				<script language="javascript">			
					if(window.dialogArguments){
						if(window.dialogArguments.document.getElementById("search_go")){
							dialogArgumentsQueryChick();
						}
						window.close();
					}
				</script>
			</logic:notEmpty>
		</html:form></body>
</html>