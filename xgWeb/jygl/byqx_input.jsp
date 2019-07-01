<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript">
	function savebyqx(){
	 var xsxh = document.getElementById("xsxh").value;
	 var byqx = document.getElementById("byqx").value;
	 var lxdz = document.getElementById("lxdz").value;
	 var lxdh = document.getElementById("lxdh").value;
	 var yzbm = document.getElementById("yzbm").value;
	 var yddh = document.getElementById("yddh").value;
	 var email = document.getElementById("email").value;

	 if(xsxh==""){
	 alert("学号必须填写！")
	 return false;
	 }
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
		 		document.forms[0].action = "/xgxt/savebyqx.do?doType=save";
		 		document.forms[0].submit();
	 }
	
	function returntobegin(){
	            document.forms[0].action = "/xgxt/savebyqx.do";
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
					<em>您的当前位置:</em><a>就业管理 - 就业协议方案 - 毕业去向录入</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
                      alert("您输入的学号无效!");
                   </script>
				</logic:equal>
				<html:hidden name="rs" property="nd" />
				<html:hidden name="rs" property="xslb" />
				
				<div class="tab">
					<table width="80%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>毕业去向录入</span>
								</th>
							</tr>
						<tr>
							<td align="left" colspan="4">
                                 毕业年度:
								<html:text property="bynd" name="rs" readonly="true"
									style="width:35px" />
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
								<html:text property="xymc" name="rs" readonly="true"
									style="width:130px" />
							</td>
						</tr>
					</thead>
					<tbody>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<th align="right" width="15%">
							    <font color="red">*</font>学号
							</th>
							<td align="left" width="35%">
								<html:text name='rs' property="xsxh" styleId="xsxh"
									style="width:210px" readonly="true" />
								<button onclick="showTopWin('/xgxt/byqxTurnInfo.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<th align="right" width="15%">
								<font color="red">*</font>学号
							</th>
							<td align="left" width="35%">
								<html:text property="xsxh" name="rs" styleId="xsxh"
									readonly="true" style="width:210px" />
							</td>
						</logic:equal>
						<th align="right" width="15%">
							姓名
						</th>
						<td align="left" width="35%">
							<html:text name="rs" property="name" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							性别
						</th>
						<td align="left">
							<html:text name="rs" property="xb" readonly="true"
								style="width:210px" />
						<th align="right">
							身份证号
						</th>
						<td align="left">
							<html:text name="rs" property="id" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							生源地区
						</th>
						<td align="left">
							<html:text name="rs" property="sydq" readonly="true"
								style="width:210px" />
						</td>
						<logic:equal name="xxdm" value="8001">
						<th align="right">
							就业单位
						</th>
						<td align="left">
							<html:text name="rs" property="jydw" 
								style="width:210px" maxlength="100"/>
						</td>
						</logic:equal>
					</tr>
					<tr style="height:22px">
						<th align="right">
							<font color="red">*</font>毕业去向
						</th>
						<td align="left">
							<html:select name="rs" property="byqx" styleId="byqx"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="byqxList" property="byqx"
									labelProperty="byqx" />
							</html:select>
						</td>
						<th align="right">
							<font color="red">*</font>联系地址
						</th>
						<td align="left">
							<html:text name="rs" property="lxdz" style="width:210px"
								maxlength="25" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							联系电话
						</th>
						<td align="left">
							<html:text name="rs" property="lxdh" style="width:210px"
								maxlength="12" />
						</td>
						<th align="right">
							<font color="red">*</font>邮政编码
						</th>
						<td align="left">
							<html:text name="rs" property="yzbm" style="width:210px"
								maxlength="6" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							移动电话
						</th>
						<td align="left">
							<html:text name="rs" property="yddh" style="width:210px"
								maxlength="11" />
						</td>
						<th align="right">
							电子邮箱
						</th>
						<td align="left">
							<html:text name="rs" property="email" style="width:210px"
								maxlength="20" onblur="if(this.value != '' && !isEmail(this.value)){alert('邮箱格式不正确！');this.focus();}"/>
						</td>
					</tr>
					</tbody>
					 <tfoot>
						      <tr>
						        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
						          <div class="btn">
						          	<button onclick="savebyqx()">
											提 交
										</button>
												           
						          </div>
						          </td>
						      </tr>
						    </tfoot>
				</table>
			</logic:notEmpty>
			<logic:notEmpty name="save">
				<logic:equal name="save" value="ok">
					<script>
    alert("提交成功！");
    </script>
				</logic:equal>
				<logic:equal name="save" value="no">
					<script>
    alert("提交失败！请检查是否重复提交！");
    </script>
				</logic:equal>
			</logic:notEmpty>
			
			<logic:notEmpty name="exists">
				<logic:equal name="exists" value="exists">
					<script>
    alert("学校审核尚未通过，请等待学校审核通过以后再来申请！");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
