<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		function save(){
			if($("xqdm").value==""){
				alert("<bean:message key="lable.xiaoqu" />代码不能为空!");
				return false;
			}
			if($("xqmc").value==""){
				alert("<bean:message key="lable.xiaoqu" />名称不能为空!");
				return false;
			}
			var url="/xgxt/gyglGywh.do?method=xqxxwh&doType=save";
			refreshForm(url);
		}
		function modi(){
			if($("xqmc").value==""){
				alert("<bean:message key="lable.xiaoqu" />名称不能为空!");
				return false;
			}
			var url="/xgxt/gyglGywh.do?method=xqxxwh&doType=modi";
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
									<span><bean:message key="lable.xiaoqu" />信息</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<logic:equal name="doType" value="add">
										<button class="button2" onclick="save();return false;">
											保存
										</button>
										</logic:equal>
										<logic:equal name="doType" value="update">
										<button class="button2" onclick="modi()">
											保存
										</button>
										</logic:equal>
										<button class="button2" onclick="Close();return false;">
											关闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
							<th>
								<font color="red">*</font><bean:message key="lable.xiaoqu" />代码
							</th>
							<td coalpsn="3">
							<logic:equal name="doType" value="update">
								<input type="text" name="xqdmxx" value="${rs.xqdm}" disabled="true" />
								<html:hidden name="rs" property="xqdm" styleId="xqdm" value="${rs.xqdm}"/>
							</logic:equal>
							<logic:equal name="doType" value="add">
									<html:text name="rs" property="xqdm" styleId="xqdm" maxlength="6"
									onkeyup="value=value.replace(/[^\d]/g,'')"/>
							</logic:equal>
							</td>
							</tr>
							<tr>
							<th>
								<font color="red">*</font><bean:message key="lable.xiaoqu" />名称
							</th>
							<td coalpsn="3">
								<html:text name="rs" property="xqmc" styleId="xqmc" maxlength="10"/>
							</td>
							</tr>
						</tbody>
					</table>
				</div>


			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alert("操作失败！");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alert("操作成功！");
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
