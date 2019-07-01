<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
	
	function zxyuyueanswer(){
		var zxsqr = document.getElementById("zxsqr").value;
		var pkValue = document.getElementById('num').value+document.getElementById("xsxh").value+document.getElementById('tjsj').value;
		var url = "/xgxt/answerYuyue.do?doType=view&act=answer&pkValue=";
     
		if(zxsqr=="未确认"){ 
		     if (confirm("你还没有确认，暂时不确认吗？")) {
				url += pkValue;
				refreshForm(url);
				return true;
			    } else {
				return false;
			    }
		}else{
		url += pkValue;
		refreshForm(url);
		}
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
									<button name="关闭" onclick="Close();return false;">
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
								<html:hidden name="rs" property="tjsj" styleId="tjsj" style="width:160px" />
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
							<td>
								${rs.xsxh }
								<html:hidden name="rs" property="xsxh" 
								 />
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
								 style="width:100%" />
								</td>
						</tr>
						<tr>
							<th>
								咨询问题回复
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name="rs" property="zxwthf" rows="11"
								 style="width:100%" />
								</td>
						</tr>
						<tr>
							<th>学生确认
							</th>
							<td>
								<bean:write name="rs" property="xsqr" />
							</td>
							<th>
								<font color="red">*</font>咨询师确认
							</th>
							<td>
								<html:select name="rs" property="zxsqr">
									<html:option value="未确认">未确认</html:option>
									<html:option value="已回复√">已回复√</html:option>
								</html:select>
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
				<script defer="defer">
                       alert("回复成功！");
                       if (window.dialogArguments) {
							window.close();
							var form = window.dialogArguments.document.forms[0];
							form.action = "jyzxResultQuery.do?act=go";
							form.submit();
						}
                    </script>
			</logic:equal>
			<logic:equal name="answer" value="no">
				<script defer="defer">
                      alert("回复失败！");
                      if (window.dialogArguments) {
							window.close();
							var form = window.dialogArguments.document.forms[0];
							form.action = "jyzxResultQuery.do?act=go";
							form.submit();
						}
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
