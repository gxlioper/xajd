<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>

		<script language="javascript">
	function jyglByqxUpdate(){
	var byqx = document.getElementById("byqx").value;
	 var lxdz = document.getElementById("lxdz").value;
	 var lxdh = document.getElementById("lxdh").value;
	 var yzbm = document.getElementById("yzbm").value;
	 var yddh = document.getElementById("yddh").value;
	 var email = document.getElementById("email").value;
	 
	 
	 if(byqx==""){
	 alert("毕业去向必须填写！");
	 return false;
	 }
	 if(lxdz==""){
	 alert("联系地址必须填写！");
	 return false;
	 }
	 if(yzbm==""){
	 alert("邮政编码必须填写！");
	 return false;
	 }
	 if(!isNumber(yzbm)){
	 alert("邮政编码应为数字！")
	 return false;
	 }
	 if(yzbm!=""&&yzbm.length!=6){
	 alert("邮政编码长度不合要求！");
	 return false;
	 }
	 
	 
	 if(lxdh!=""&&!isNumber(lxdh)){
	 alert("联系电话应为数字！")
	 return false;
	 }
	 if(lxdh!=""&&lxdh.length<7){
	 alert("联系电话长度不合要求！");
	 return false;
	 }
	 if(yddh!=""&&!isNumber(yddh)){
	 alert("移动电话应为数字！")
	 return false;
	 }
	 if(yddh!=""&&yddh.length!=11){
	 alert("手机号码长度不合要求！");
	 return false;
	 } 
	 if(lxdh==""&&yddh==""){
	 alert("请至少填写一个联系电话！");
	 return false;
	 }
	   
		 		document.forms[0].action = "/xgxt/jyglByqxUpdate.do?doType=update&act=update";
		 		document.forms[0].submit();
    }
   
	
	function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    }  
    
   
	</script>
	</head>
	<body>
		<html:form action="/data_search" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>就业管理 - 学生信息 - 毕业去向信息</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<td align="left" colspan="4">
								毕业年度
								<html:text property="bynd" name="rs" style="width:35px"
									readonly="true" />
								<bean:message key="lable.xsgzyxpzxy" />
								<html:text property="xymc" name="rs" style="width:130px"
									readonly="true" />
								提交时间
								<html:text name="rs" property="tjsj" style="width:140px"
									readonly="true" />
							</td>
						</tr>
					</thead>
					<tbody>
						<tr style="height:22px">
							<th width="15%">
								学号
							</th>
							<td width="35%">
								<html:text name="rs" property="xsxh" readonly="true"
									style="width=100%" />
							</td>
							<th width="15%">
								姓名
							</th>
							<td width="35%">
								<bean:write name="rs" property="name" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								性别
							</th>
							<td>
								<bean:write name="rs" property="xb" />
							<th>
								身份证号
							</th>
							<td>
								<bean:write name="rs" property="id" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								生源地区
							</th>
							<td>
								<bean:write name="rs" property="sydq" />
							</td>
							<th>
								就业单位
							</th>
							<td>
								<html:text name="rs" property="jydw" style="width=100%" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								<font color="red">*</font>毕业去向
							</th>
							<td>
								<html:select name="rs" property="byqx" styleId="byqx"
									style="width=100%">
									<html:options collection="byqxList" property="byqx"
										labelProperty="byqx" />
								</html:select>
							</td>
							<th align="right">
								<font color="red">*</font>联系地址
							</th>
							<td align="left">
								<html:text name="rs" property="lxdz" style="width=100%" maxlength="25"/>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								<font color="red">*</font>联系电话
							</th>
							<td>
								<html:text name="rs" property="lxdh" style="width=100%" maxlength='12'/>
							</td>
							<th>
								<font color="red">*</font>邮政编码
							</th>
							<td>
								<html:text name="rs" property="yzbm" style="width=100%"  maxlength='6'/>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								<font color="red">*</font>移动电话
							</th>
							<td>
								<html:text name="rs" property="yddh" style="width=100%" maxlength="11" />
							</td>
							<th>
								<font color="red">*</font>电子邮箱
							</th>
							<td>
								<html:text name="rs" property="email" style="width=100%" maxlength='20'/>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								学校审核结果
							</th>
							<td>
								<html:text name="rs" property="xxsh" style="width=100%"
									readonly="true" />
							</td>
							<th>
								审核时间
							</th>
							<td>
								<html:text name="rs" property="shsj" style="width=100%"
									readonly="true" />
							</td>
						</tr>
						<tr style="height:55px">
							<th>
								修改意见
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="xgyj" rows="3"
									style="word-break:break-all;width:99%" readonly="true"
									onblur="chLeng(this,50);"/>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								审核人
							</th>
							<td>
								<html:text name="rs" property="shperson" style="width=100%"
									readonly="true" />
							</td>
							<th>

							</th>
							<td align="center">
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button name="保存" onclick="jyglByqxUpdate()" id="buttonSave">
										保 存
									</button>
									<button name="关闭" onclick="Close();return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("修改成功!");
                      if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('query_go').click();
						}
                </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("审核已通过，你已无权进行修改！");
		               if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('query_go').click();
						}
                </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
