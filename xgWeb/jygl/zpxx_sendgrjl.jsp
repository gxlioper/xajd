<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
	 function sendgrjl(){
	   var xxsh = document.getElementById("xxsh").value;
	   if(xxsh=="未审核"||xxsh=="未通过X"){
	       if (confirm("简历暂未通过审核，你确定要先投递简历然后等待审核通过吗？")) {
		      document.forms[0].action = "/xgxt/sendgrjl.do?act=send&doType=view";
		      document.forms[0].submit();			
		      return true;
		   } else {
				return false;
		   }
	   }else{
	     document.forms[0].action = "/xgxt/sendgrjl.do?act=send&doType=view";
		 document.forms[0].submit();
	   }
	 }
	 
	</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>招聘详细信息</a>
			</p>
		</div>


		<html:form action="/data_search" method="post">
			<html:hidden name="rs" property="xxsh" />
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>招聘详细信息</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="sendgrjl();return false;">
										投递简历
									</button>
									<button onclick="Close();return false;">
										关闭窗口
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="13%">
								招聘职位
							</th>
							<td width="37%">
								<html:text name="rs" property="zpzw" readonly="true"
									style="width=100%" />
							</td>
							<th width="13%">
								公司名称
							</th>
							<td width="37%">
								<html:text name="rs" property="gsmc" readonly="true"
									style="width=100%" />
							</td>
						</tr>
						<tr>
							<th>
								电子邮箱
							</th>
							<td>
								<bean:write name="rs" property="email" />
							</td>
							<th>
								联系电话
							</th>
							<td>
								<bean:write name="rs" property="lxdh" />
							</td>
						</tr>
						<tr>
							<th>
								工作地点
							</th>
							<td>
								<bean:write name="rs" property="gzdd" />
							</td>
							<th>
								招聘人数
							</th>
							<td>
								<bean:write name="rs" property="zprs" />
							</td>
						</tr>
						<tr>
							<th>
								行业分类
							</th>
							<td>
								<bean:write name="rs" property="hyfl" />
							</td>
							<th>
								外语要求
							</th>
							<td>
								<bean:write name="rs" property="wyyq" />
							</td>
						</tr>
						<tr>
							<th>
								试用期薪水
							</th>
							<td>
								<bean:write name="rs" property="syxs" />
							</td>
							<th>
								转正后薪水
							</th>
							<td>
								<bean:write name="rs" property="zzxs" />
							</td>
						</tr>
						<tr>
							<th>
								学历要求
							</th>
							<td>
								<bean:write name="rs" property="xlyq" />
							</td>
							<th>
								面试时间
							</th>
							<td>
								<bean:write name="rs" property="mssj" />
							</td>
						</tr>

						<tr>
							<th>
								岗位职责
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="gwzz" rows="8" cols="75%"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								职位要求
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="zwyq" rows="8" cols="75%"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								公司简介
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="gsjj" rows="8" cols="75%"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								发布时间
							</th>
							<td>
								<bean:write name="rs" property="fbsj" />
							</td>
							<th>

							</th>
							<td>

							</td>
						</tr>
					</tbody>
				</table>
		</html:form>
		<logic:notEmpty name="insert">
			<logic:equal name="insert" value="no">
				<script>
                       alert("不能在同一家公司同一个职位重复投简历！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="havegrjl">
			<logic:equal name="havegrjl" value="no">
				<script>
                       alert("个人简历无法找到，请先进行个人简历登记！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="send">
			<logic:equal name="send" value="ok">
				<script>
                       alert("提交成功！请静候通知");
                    </script>
			</logic:equal>
			<logic:equal name="send" value="no">
				<script>
                       alert("提交失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
