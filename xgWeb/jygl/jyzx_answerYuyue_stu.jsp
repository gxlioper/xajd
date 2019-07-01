<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript">
	
	function zxyuyueanswer(){
		var xsqr = document.getElementById("xsqr").value;
		var pkValue = document.getElementById('num').value+document.getElementById("xsxh").value+document.getElementById('tjsj').value;
		var url = "/xgxt/answerYuyueStu.do?doType=view&act=answer&pkValue=";
       
		if(xsqr=="不赴约X"){ 
		     if (confirm("选择以后将无法更改，你确定不赴约吗？")) {
				url += pkValue;
				refreshForm(url);
				return true;
			    } else {
				return false;
			    }
		}		
		if(xsqr=="已确认√"){ 
		     if (confirm("选择以后将无法更改，你已确认吗？")) {
				url += pkValue;
				refreshForm(url);
				return true;
			    } else {
				return false;
			    }
		}
		url += pkValue;
		refreshForm(url);
	}
	
	</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>咨询预约详细信息</a>
			</p>
		</div>
	
	
		<html:form action="/data_search" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>咨询预约详细信息</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button name="提交" onclick="zxyuyueanswer()">
										提 交
									</button>
									<button name="关闭" onclick="Close();window.dialogArguments.document.getElementById('query_go').click();">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								咨询师编号
								<html:hidden name="rs" property="tjsj" styleId="tjsi" style="width:160px" />
							</th>
							<td width="34%">
								<bean:write name="rs" property="num" />
								<input  type="hidden" value="${rs.num }" id="num"/>
							</td>
							<th width="16%">
								咨询师姓名
							</th>
							<td width="34%">
								<bean:write name="rs" property="zxsname" />
							</td>
						</tr>
						<tr>
							<th>
								学生学号
							</th>
							<td><html:text name="rs" property="xsxh" style="width=100%"
								readonly="true" />
							</td>
							<th>学生姓名
							</th>
							<td><bean:write name="rs" property="name" />
							</td>
						</tr>
						<tr>
							<th>
								学生性别
							</th>
							<td><bean:write name="rs" property="xb" />
							</td>
							<th>学生年级
							</th>
							<td><bean:write name="rs" property="nj" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />名称
							</th>
							<td><bean:write name="rs" property="xymc" />
							</td>
							<th>专业名称
							</th>
							<td><bean:write name="rs" property="zymc" />
							</td>
						</tr>
						<tr>
							<th>
								是否要求见面
							</th>
							<td><bean:write name="rs" property="meet" />
							</td>
							<th>期望约见时间
							</th>
							<td><bean:write name="rs" property="qwyjtime" />
							</td>
						</tr>
						<tr>
							<th>
								咨询问题简述
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name="rs" property="zxwtjs" rows="6"
								readonly="true" style="width:100%" />
								</td>
						</tr>
						<tr>
							<th>
								咨询问题回复
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name="rs" property="zxwthf" rows="11"
								readonly="true" style="width:100%" />
								</td>
						</tr>
						<tr>
							<th>学生确认
							</th>
							<td>
								<logic:equal name="xsqr" value="ok">
								<html:select name="rs" property="xsqr">
									<html:option value="已查看">已查看</html:option>
									<html:option value="不赴约X">不赴约X</html:option>
									<html:option value="已确认√">已确认√</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="xsqr" value="no">
								<html:select name="rs" property="xsqr" disabled="true" >
								    <html:option value="不赴约X">不赴约X</html:option>
								    <html:option value="已确认√">已确认√</html:option>
								</html:select>
								
								<input type="hidden" name="xsqr" value="${rs.xsqr }" />
							</logic:equal>
							</td>
							<th>
								<font color="red">*</font>咨询师确认
							</th>
							<td>
								<bean:write name="rs" property="zxsqr" />
							</td>
						</tr>
						<tr>
							<th>
								学生确认时间
							</th>
							<td>
								<bean:write name="rs" property="xsqrsj" />
							</td>
							<th>
								咨询师确认时间
							</th>
							<td>
								<bean:write name="rs" property="zxsqrsj" />
							</td>
						</tr>
						<tr>
							<th>
								最终约见地点
							</th>
							<td>
								<bean:write name="rs" property="yjdd" />
							</td>
							<th>
								最终约见时间
							</th>
							<td>
								<bean:write name="rs" property="yjsj" />
							</td>
						</tr>
					</tbody>
				</table>
				</div>
		</html:form>
		<logic:notEmpty name="answer">
			<logic:equal name="answer" value="ok">
				<script>
                       alert("回复成功！");
                       if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('query_go').click();
						}
                    </script>
			</logic:equal>
			<logic:equal name="answer" value="no">
				<script>
                      alert("回复失败！");
                      if (window.dialogArguments) {
						window.close();
						window.dialogArguments.document.getElementById('query_go').click();
					}
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

