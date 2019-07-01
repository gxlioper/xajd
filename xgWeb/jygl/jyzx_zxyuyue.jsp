<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
	<script language="javascript">
	function savego(){
		var qwyjtime = document.getElementById("qwyjtime").value;
		var meet = document.getElementsByName("meet");
		var zxwtjs = document.getElementById("zxwtjs").value;
		var t;
		
		for(i=0;i<meet.length;i++){
		  if(meet[i].checked==true){
		     t = meet[i].value;
		  }
		}
		if(t=="否"&&qwyjtime!=""){
		alert("不要求见面，不用填写约见时间！");
		return false;
		}
		
		if(zxwtjs==""){
		alert("咨询问题简述必须填写！");
		return false;
		}
	
		 showTips('处理数据中，请等待......');
	     document.forms[0].action = "/xgxt/saveYuyue.do?doType=save";
		 document.forms[0].submit();
	}
	</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>咨询师详细信息</a>
			</p>
		</div>
	
		<html:form action="/data_search" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>咨询师详细信息</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button name="提交" onclick="savego()">
										提 交
									</button>
									<button name="重置" type="reset">
										重 置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								咨询师编号
							</th>
							<td width="34%">
								<html:text name="rs" property="num" style="100%" readonly="true" />
							</td>
							<th width="16%">
								咨询师姓名
							</th>
							<td width="34%">
								<bean:write name="rs" property="name" />
							</td>
						</tr>
						<tr>
							<th>
								咨询师年龄
							</th>
							<td>
								<bean:write name="rs" property="age" />
							</td>
							<th>
								咨询师性别
							</th>
							<td>
								<bean:write name="rs" property="xb" />
							</td>
						</tr>
						<tr>
							<th>
								咨询师资格
							</th>
							<td>
								<bean:write name="rs" property="zxszg" />
							</td>
							<th>
								电子邮箱
							</th>
							<td>
								<bean:write name="rs" property="email" />
							</td>
						</tr>
						<tr>
							<th>
								联系电话
							</th>
							<td>
								<bean:write name="rs" property="lxdh" />
							</td>
							<th>
								
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								咨询师简介
							</th>
							<td colspan="3" style="word-break:break-all">
								<html:textarea name="rs" property="zxsjj" rows="6"
								style="width:100%" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								是否要求见面
							</th>
							<td>
								<input type="radio" name="meet" value="是"/>
							&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="meet" value="否" checked="checked"/>
							&nbsp;否
							</td>
							<th>
								期望约见时间
							</th>
							<td>
								<html:text name="rs" style="cursor:hand; width=100%;"
								styleId="qwyjtime" property="qwyjtime"
								onclick="return showCalendar('qwyjtime','y-mm-dd');"
								readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								咨询问题简述
								<br/>
								<font color="red"><限500字></font>
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="zxwtjs" rows="6"
								style="width:100%" onblur="checkLen(this,500)"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                       alert("预约成功！（详细内容请查看预约结果查询）");
                       if (window.dialogArguments) {
							Close();
							dialogArgumentsQueryChick();
						}
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("预约失败！请检查是否重复提交！");
                      if (window.dialogArguments) {
						Close();
						dialogArgumentsQueryChick();
					}
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>