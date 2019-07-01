<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var xscssqly = document.getElementById('xscssqly').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if(xscssqly != null){
	         	if(xscssqly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("学生陈述申请理由不能超过1000个字符！");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=knsrdsqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=knsrdsqb";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="tab_cur" id="jd">
		<p class="location">
			<em>您的当前位置:</em><a>困难生 - 困难生认定申请</a>
		</p>
	</div>
	<logic:present name="sfksq">
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内或还没有填写家庭情况调查表！！！
			</p>
		</center>
	</logic:equal>
	</logic:present>
		<html:form action="zgdzdx_xszz.do?method=knsrdsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zgdzdx_xszz.do?method=knsrdsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="tjdc" name="tjdc"
				value="<bean:write name="rs" property="tjdc" />">
			<input type="hidden" id="csly" name="csly"
				value="<bean:write name="rs" property="csly" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">

			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("已通过审核，不能申请！");
	         		</script>
				</logic:match>
			</logic:present>
			<div class="tab">
			<table width="100%"  border="0" class="formlist">
				<thead>
					<tr>
	    				<th colspan="6"><span>困难生认定申请</span></th>
	    			</tr>
				 </thead>
				 <tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th align="center" width="16%">
							<font color="red">*</font>学号
						</th>
						<td align="left" width="34%">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="readonly"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th align="center" width="16%">
							<font color="red">*</font>学号
						</th>
						<td align="left" width="34%">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<th width="16%">
							姓名
					</th>
					<td width="34%">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th>
						性别
					</th>
					<td>
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<th>
						学年
					</th>
					<td>
						<input type="text" id="xn" name="xn" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xn"/>">
					</td>
				</tr>
				<tr>
					<th>
						民族
					</th>
					<td>
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<th>
						身份证号
					</th>
					<td>
						<input type="text" id="sfzh" name="sfzh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<th>
						年级
					</th>
					<td>
						<input type="text" id="nj" readonly="readonly" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />名称
					</th>
					<td>
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<th>
						专业名称
					</th>
					<td>
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<th>
						班级名称
					</th>
					<td>
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<th>
						家庭人均年收入
					</th>
					<td>
						<input type="text" id="jtrjnsr" name="jtrjnsr" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrjnsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						在校联系电话
					</th>
					<td>
						<input type="text" id="zxlxdh" name="zxlxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zxlxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						家庭地址
					</th>
					<td colspan="3">
						<input type="text" id="jtdz" name="jtdz" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdz"/>">
					</td>
				</tr>
				<tr>
					<th>
						所属县市
					</th>
					<td>
						<input type="text" id="ssxs" name="ssxs" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ssxs"/>">
					</td>
					<th>
						是否地震重灾区
					</th>
					<td align="center">
						<logic:present name="rs" property="sfdzzzq">
							<logic:match value="是" name="rs" property="sfdzzzq">
								<input type="radio" name="sfdzzzq" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfdzzzq" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sfdzzzq">
								<input type="radio" name="sfdzzzq" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfdzzzq" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfdzzzq">
							<input type="radio" name="sfdzzzq" value="是">&nbsp;&nbsp;是
							<input type="radio" name="sfdzzzq" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<th>
						学生陈述申请理由
					</th>
					<td colspan="3">
						<html:textarea name="rs" property="xscssqly" rows='8' style='width:100%' onblur="yzdx(this,'xscssqly')"/>
						<input type="hidden" id="xscssqly" name="xscssqly" value="">
					</td>
				</tr>
				</tbody>
				<logic:equal name="sfksq" value="1">
				<tfoot>
					 <tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<logic:notEqual name="isQuery" value="is">
			          	<button type="button"  onClick="yz();">
							提交申请
						</button>
						<button type="button"  onClick="toPrintOut();">
							打 印
						</button>	
						</logic:notEqual>
						<button type="button" onclick="window.close();return false;" id="buttonClose">
							关 闭
						</button>		           
			          </div>
			          </td>
			      </tr>
				</tfoot>
				</logic:equal>
				<logic:notEqual name="sfksq" value="1">
				<tfoot>
					<tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
						<logic:equal name="isQuery" value="is">
							<button type="button" onclick="window.close();return false;" id="buttonClose">
								关 闭
							</button>
						</logic:equal>			           
			          </div>
			          </td>
			      </tr>
				</tfoot>
				</logic:notEqual>
			</table>
		</html:form>
</body>
</html>
