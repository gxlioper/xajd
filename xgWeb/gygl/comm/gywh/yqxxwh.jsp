<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		function save(){
			if($("yqdm").value==""){
				alert("<bean:message key="lable.yuanqu" />���벻��Ϊ��!");
				return false;
			}
			if($("yqmc").value==""){
				alert("<bean:message key="lable.yuanqu" />���Ʋ���Ϊ��!");
				return false;
			}
			if($("xqdm").value==""){
				alert("<bean:message key="lable.xiaoqu" />���벻��Ϊ��!");
				return false;
			}
			var url="/xgxt/gyglGywh.do?method=yqxxwh&doType=save";
			refreshForm(url);
		}
		function modi(){
			if($("yqmc").value==""){
				alert("<bean:message key="lable.yuanqu" />���Ʋ���Ϊ��!");
				return false;
			}
			if($("xqdm").value==""){
				alert("<bean:message key="lable.xiaoqu" />���벻��Ϊ��!");
				return false;
			}
			var url="/xgxt/gyglGywh.do?method=yqxxwh&doType=modi";
			refreshForm(url);
		}
		</script>
	</head>
	<body >
		<html:form action="/gyglGywh" method="post">
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span><bean:message key="lable.yuanqu" />��Ϣ</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<logic:equal name="doType" value="add">
											<button class="button2" onclick="save();return false;">
												����
											</button>
										</logic:equal>
										<logic:equal name="doType" value="update">
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
								<font color="red">*</font><bean:message key="lable.yuanqu" />����
							</th>
							<td coalpsn="3">
								<logic:equal name="doType" value="update">
									<input type="text" name="yqdmxx" value="${rs.yqdm}" disabled="true" />
									<html:hidden name="rs" property="yqdm" styleId="yqdm" value="${rs.yqdm}"/>
								</logic:equal>
								<logic:equal name="doType" value="add">
									<html:text name="rs" property="yqdm" styleId="yqdm" maxlength="20"
									onkeyup="value=value.replace(/[^\d]/g,'')"/>
								</logic:equal>
							</td>
							</tr>
							<tr>
							<th>
								<font color="red">*</font><bean:message key="lable.yuanqu" />����
							</th>
							<td coalpsn="3">
								<html:text name="rs" property="yqmc" styleId="yqmc" maxlength="15"/>
							</td>
							</tr>
							<tr>
							<th>
								<font color="red">*</font><bean:message key="lable.xiaoqu" />����
							</th>
							<td coalpsn="3">
								<html:select name="rs" property="xqdm" styleId="xqdm">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="dm"
										labelProperty="mc" />
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
