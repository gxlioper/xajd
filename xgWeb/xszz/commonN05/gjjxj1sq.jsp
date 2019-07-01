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
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var hjqk = document.getElementById('hjqk').value;
			var sqly = document.getElementById('sqly').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if(hjqk != null){
	         	if(hjqk.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("获奖情况不能大于1000个字符");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("申请理由不能大于2000个字符");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/n05_xszz.do?method=gjjxj1sqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/n05_xszz.do?method=gjjxj1sqb";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a> 学生资助 - 国家奖学金</a>
				</p>
			</div>
		</div>
	<logic:equal name="sfksq" value="-1">
		<p align="center">
			<font color="red">目前不在申请时间范围内，暂不开放申请！</font>
		</p>
	</logic:equal>
		<html:form action="n05_xszz.do?method=gjjxj1sq" method="post">
			<input type="hidden" id="url" name="url" value="/n05_xszz.do?method=gjjxj1sq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">

			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<logic:present name="isPASS">
						<logic:match value="is" name="isPASS">
							<script language="javascript">
			         			alert("已通过审核，不能申请！");
			         		</script>
						</logic:match>
					</logic:present>
					<logic:notPresent name="isPASS">
						<script language="javascript">
			         		alert("保存失败！");
			         	</script>
					</logic:notPresent>						
				</logic:match>
			</logic:present>
			<div class="tab">
				<table width="99%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="10">
							<span>国家奖学金</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th  width="16%" >
							<font color="red">*</font>学号
						</th>
						<td  colspan="4" width="34%">
							<html:text name='rs' property="xh" styleId="xh"
								onblur="dctStuXh()" 
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:notEqual name="type" value="modi">
								<logic:equal name="sfKns" value="no">
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</logic:equal>
								<logic:notEqual name="sfKns" value="no">
									<button onclick="showTopWin('/xgxt/stu_info.do?kns=yes',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</logic:notEqual>
							</logic:notEqual>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th  width="16%">
							<font color="red">*</font>学号
						</th>
						<td colspan="4"  width="34%">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<th width="16%">
							姓名
					</th>
					<td  colspan="4" width="34%">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th>
						性别
					</th>
					<td  colspan="4">
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<th>
						身份证号
					</th>
					<td  colspan="4">
						<input type="text" id="sfzh" name="sfzh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<th>
						民族
					</th>
					<td  colspan="4"  >
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<th>
							政治面貌
					</th>
					<td  colspan="4"  >
						<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmmmc"/>">
					</td>
				</tr>
				<tr>
					<th>
						 出生年月
					</th>
					<td  colspan="4"  >
						<input type="text" id="csrq" readonly="readonly" name="csrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
					</td>
					<th>
						入学日期
					</th>
					<td   colspan="4">
						<input type="text" id="rxrq" name="rxrq" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxrq"/>">
					</td>
				</tr>
				<tr>
					<th>
						年级
					</th>
					<td   colspan="4">
						<input type="text" id="nj" readonly="readonly" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />名称
					</th>
					<td   colspan="4">
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<th>
						专业名称
					</th>
					<td   colspan="4">
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<th>
						班级名称
					</th>
					<td   colspan="4">
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<th>
						学年
					</th>
					<td   colspan="4"  >
						<input type="text" id="xn" name="xn" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xn"/>">
					</td>
					<th>
							联系电话
					</th>
					<td  colspan="4">
						<input type="text" id="lxdh" maxlength="20" name="lxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						该学年必修课程数
					</th>
					<td  colspan="4">
						<input type="text" id="gxnbxkcs" maxlength="3" name="gxnbxkcs"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gxnbxkcs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						优秀课程数
					</th>
					<td  colspan="4">
						<input type="text" id="yxkcs" maxlength="3" name="yxkcs"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yxkcs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						良好课程数
					</th>
					<td  colspan="4">
						<input type="text" id="lhkcs" maxlength="3" name="lhkcs"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lhkcs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
							成绩排名<br />
							(专业或年级)
					</th>
					<td  colspan="4">
						<input type="text" id="cjpm" name="cjpm" maxlength="15"
							style="width:70%;heigh:100%"
							value="<bean:write name="rs" property="cjpm"/>">（名次/总人数）
					</td>
				</tr>
				<tr>
					<th>
						综合考评成绩（分）
					</th>
					<td  colspan="4">
						<input type="text" id="zhkpcj" maxlength="6" name="zhkpcj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zhkpcj"/>">
					</td>
					<th>
						综合考评排名<br />
						(专业或年级)
					</th>
					<td  colspan="4">
						<input type="text" id="zhkppm" name="zhkppm" maxlength="15"
							style="width:70%;heigh:100%"
							value="<bean:write name="rs" property="zhkppm"/>">（名次/总人数）
					</td>
				</tr>
				<!-- 宁波天一 -->
				
				<logic:equal name="xxdm" value="13742" >
				<tr>
					<td colspan="10">
					<table>
						<tr>
							<th rowspan="5" width="16%"><center>大学期间主<br>要获奖情况</center></td>
							<th>
								日期
							</th>
							<th>
								奖项名称
							</th>
							<th>
								颁奖单位
							</th>
						</tr>
						<tr>
							<td>
								<html:text name="rs" property="hjsj1" styleId="hjsj1" 
										onclick="return showCalendar('hjsj1','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" style="width:100%;heigh:100%"/>
							</td>
							<td>
								<html:text name="rs" property="hjmc1" style="width:100%;heigh:100%"/>
							</td>
							<td>
								<html:text name="rs" property="bjdw1"	style="width:100%;heigh:100%"/>
							</td>
						</tr>
						<tr>
							<td>
								<html:text name="rs" property="hjsj2" styleId="hjsj2" 
										onclick="return showCalendar('hjsj2','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" style="width:100%;heigh:100%"/>
							</td>
							<td>
								<html:text name="rs" property="hjmc2"	style="width:100%;heigh:100%"/>
							</td>
							<td>
								<html:text name="rs" property="bjdw2" style="width:100%;heigh:100%"/>
							</td>
						</tr>
						<tr>
							<td>
								<html:text name="rs" property="hjsj3" styleId="hjsj3" 
										onclick="return showCalendar('hjsj3','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" style="width:100%;heigh:100%"/>
							</td>
							<td>
								<html:text name="rs" property="hjmc3"	style="width:100%;heigh:100%" />
							</td>
							<td>
								<html:text name="rs" property="bjdw3"	style="width:100%;heigh:100%"/>
							</td>
						</tr>
						<tr>
							<td>
								<html:text name="rs" property="hjsj4" styleId="hjsj4" 
										onclick="return showCalendar('hjsj4','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" style="width:100%;heigh:100%"/>
							</td>
							<td>
								<html:text name="rs" property="hjmc4" style="width:100%;heigh:100%"/>
							</td>
							<td>
								<html:text name="rs" property="bjdw4"	style="width:100%;heigh:100%" />
							</td>
				</tr>
				</table>
				</td>
				</tr>
				</logic:equal>
				<!-- 南京技师 -->
				<logic:equal name="xxdm" value="8001">
					<tr>
						<th>
							是否低保
						</th>
						<td>
							<html:select name="rs" property="sfdb">
								<html:option value=""></html:option>
								<html:option value="是">是</html:option>
								<html:option value="否">否</html:option>
							</html:select>
						</td>
						<th>
							
						</th>
						<td>
							
						</td>
					</tr>
				</logic:equal>
				<tr>
					<th>
						获奖情况
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="hjqk" rows='4' style="width:100%" onblur="yzdx(this,'hjqk')"/>
						<input type="hidden" id="hjqk" name="hjqk" value="">
					</td>
				</tr>
				<tr>
					<th>
						院级奖励(项)
					</th>
					<td colspan="4">
						<input type="text" id="yjjl" maxlength="4" name="yjjl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yjjl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						校级奖励(项)
					</th>
					<td colspan="4">
						<input type="text" id="xjjl" name="xjjl" maxlength="4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xjjl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						省级以上奖励(项)
					</th>
					<td colspan="4">
						<input type="text" id="sjjl" maxlength="4" name="sjjl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sjjl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						申请时间
					</th>
					<td colspan="4">
						<input type="text" id="sqsj" name="sqsj" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqsj"/>">
					</td>
				</tr>
				<tr>
					<th>
							申请理由<br />
							(全面反映德智体美情况)
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="sqly" rows='10' style="width:100%" onblur="yzdx(this,'sqly')"/>
						<input type="hidden" id="sqly" name="sqly" value="">
					</td>
				</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="10">
							<div class="bz">
								"
								<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button id="buttonSave" onclick="yz();">
									提交申请
								</button>
								&nbsp;
								<button onclick="toPrintOut();">
									打&nbsp;印
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
	<logic:equal name="sfksq" value="-1">
		<script language="javascript">
		  $("buttonSave").disabled=true;
		</script>
	</logic:equal>
	<logic:notEmpty name="rs">
	<logic:empty name="rs" property="xh">
		<script language="javascript">
		alert("您输入的学号不存在,请重新输入!");
		$("xh").focus();
		</script>
	</logic:empty>
	</logic:notEmpty>
	<logic:notEmpty name="rs" property="xh">
	<logic:notEqual name="sfksq" value="-1">
		<logic:equal name="isKns" value="false">
			<script language="javascript">
			  $("buttonSave").disabled=true;
			  alert("该生目前不是困难生，不能进行申请!");
			</script>
		</logic:equal>
	</logic:notEqual>
	</logic:notEmpty>
</html>
