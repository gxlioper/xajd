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
	<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script language="javascript">
		function yz(){
			var xxdm = $("xxdm").value;
			var xh = document.getElementById('xh').value;
			var bxnyhzzqk = document.getElementById('bxnyhzzqk').value;
			var jtcszrzhqk = document.getElementById('jtcszrzhqk').value;
			var jtcstfywqk = document.getElementById('jtcstfywqk').value;
			var jtcyycjnmrndnlrqk = document.getElementById('jtcyycjnmrndnlrqk').value;
			var jtcysyqk = document.getElementById('jtcysyqk').value;
			var jtqzqk = document.getElementById('jtqzqk').value;
			var qtqk = document.getElementById('qtqk').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if(bxnyhzzqk != null){
	         	if(bxnyhzzqk.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("学生本学年已获资助情况不能大于600个字符");
	          		 return false;
	       		 }
	       	}
	       	if(jtcszrzhqk != null){
	         	if(jtcszrzhqk.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("家庭遭受自然灾害情况不能大于600个字符");
	          		 return false;
	       		 }
	       	}
	       	if(jtcstfywqk != null){
	         	if(jtcstfywqk.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("家庭遭受突发意外事件不能大于600个字符");
	          		 return false;
	       		 }
	       	}
	       	if(jtcyycjnmrndnlrqk != null){
	         	if(jtcyycjnmrndnlrqk.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("家庭成员因残疾、年迈而劳动能力弱情况不能大于600个字符");
	          		 return false;
	       		 }
	       	}
	       	if(jtcysyqk != null){
	         	if(jtcysyqk.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("家庭成员失业情况不能大于600个字符");
	          		 return false;
	       		 }
	       	}
	       	if(jtqzqk != null){
	         	if(jtqzqk.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("家庭欠债情况不能大于600个字符");
	          		 return false;
	       		 }
	       	}
	       	if(qtqk != null){
	         	if(qtqk.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("其他情况不能大于600个字符");
	          		 return false;
	       		 }
	       	}
	       	
	       	if( xxdm == "11078"){//广州大学
		       	if(gzdx_yz()){
					document.forms[0].action = "/xgxt/n05_xszz.do?method=jtqkdc1sqSave";
					document.forms[0].submit();
				}
			}else{
				document.forms[0].action = "/xgxt/n05_xszz.do?method=jtqkdc1sqSave";
				document.forms[0].submit();
			}
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/n05_xszz.do?method=jtqkdc1sqb";
			document.forms[0].submit();
		}
		function changeXf(){
			var sfjxf = document.getElementById('sfjxf').value;
			
			if (sfjxf == "是") {
				document.getElementById('yjxf').disabled = false;
			} else {
				document.getElementById('yjxf').disabled = true;
			}
		}
		function changeZsf(){
			var sfjzsf = document.getElementById('sfjzsf').value;
			
			if (sfjzsf == "是") {
				document.getElementById('yjzsf').disabled = false;
			} else {
				document.getElementById('yjzsf').disabled = true;
			}
		}
		
	function gzdx_yz(){
	
		//入学前户口
		var rxqhk = $("rxqhk").value;
		
		if(rxqhk == ""){
			alert("入学前户口不能为空，请确认！");
			return false;
		}
		
		//家庭人口数
		var jtrks = $("jtrks").value;
		
		if(jtrks == ""){
			alert("家庭人口数不能为空，请确认！");
			return false;
		}
		
		//毕业学校
		var byxx = $("byxx").value;
		
		if(byxx == ""){
			alert("毕业学校不能为空，请确认！");
			return false;
		}
		
		//个人特长
		var grtc = $("grtc").value;
		
		if(grtc == ""){
			alert("个人特长不能为空，请确认！");
			return false;
		}
		
		//孤残
		var sfgc = $("sfgc").value;
		
		if(sfgc == ""){
			alert("是否孤残不能为空，请确认！");
			return false;
		}
		
		//单亲
		var sfdq = $("sfdq").value;
		
		if(sfdq == ""){
			alert("是否单亲不能为空，请确认！");
			return false;
		}
		
		//烈士子女
		var sflszn = $("sflszn").value;
		
		if(sflszn == ""){
			alert("是否烈士子女不能为空，请确认！");
			return false;
		}
		
		//家庭地址
		var jtdz = $("jtdz").value;
		
		if(jtdz == ""){
			alert("家庭地址不能为空，请确认！");
			return false;
		}
		
		//邮政编码
		var yzbm = $("yzbm").value;
		
		if(yzbm == ""){
			alert("邮政编码不能为空，请确认！");
			return false;
		}
		
		//学生在校联系电话
		var xszxlxdh = $("xszxlxdh").value;
		
		if(xszxlxdh == ""){
			alert("学生在校联系电话不能为空，请确认！");
			return false;
		}
		
		//家庭联系电话
		var jtlxdh = $("jtlxdh").value;
		
		if(jtlxdh == ""){
			alert("家庭联系电话不能为空，请确认！");
			return false;
		}
				
		//家庭人均年收入
		var jtrjnsr = $("jtrjnsr").value;
		
		if(jtrjnsr == ""){
			alert("家庭人均年收入不能为空，请确认！");
			return false;
		}
		
		//家庭人均月收入
		var jtrjysr = $("jtrjysr").value;
		
		if(jtrjysr == ""){
			alert("家庭人均月收入不能为空，请确认！");
			return false;
		}
		
		//学生本学年已获资助情况
		var bxnyhzzqk = $("bxnyhzzqk").value;
		
		if(bxnyhzzqk == ""){
			alert("学生本学年已获资助情况不能为空，请确认！");
			return false;
		}
		
		//本学年缴纳学费情况
		var sfjxf = $("sfjxf").value;
		var yjxf = $("yjxf").value;
		if(sfjxf == ""){
			alert("本学年缴纳学费情况不能为空，请确认！");
			return false;
		}else if(sfjxf == "是" && yjxf == ""){
			alert("本学年缴纳学费情况不能为空，请确认！");
			return false;
		}
		
		//缴纳住宿费情况
		var sfjzsf = $("sfjzsf").value;
		var yjzsf = $("yjzsf").value;
		if(sfjzsf == ""){
			alert("缴纳住宿费情况不能为空，请确认！");
			return false;
		}else if(sfjzsf == "是" && yjzsf == ""){
			alert("缴纳住宿费情况不能为空，请确认！");
			return false;
		}
		
		//家庭提供生活费
		var jttgshf = $("jttgshf").value;

		if(jttgshf == ""){
			alert("家庭提供生活费不能为空，请确认！");
			return false;
		}
		
		//在校需消费
		var zxxxf = $("zxxxf").value;

		if(zxxxf == ""){
			alert("在校需消费不能为空，请确认！");
			return false;
		}
		
		//	家庭遭受自然灾害情况
		var jtcszrzhqk = $("jtcszrzhqk").value;

		if(jtcszrzhqk == ""){
			alert("	家庭遭受自然灾害情况不能为空，请确认！");
			return false;
		}
		
		//	家庭遭受突发意外事件
		var jtcstfywqk = $("jtcstfywqk").value;

		if(jtcstfywqk == ""){
			alert("	家庭遭受自然灾害情况不能为空，请确认！");
			return false;
		}
		
		//	家庭成员因残疾、年迈而劳动能力弱情况
		var jtcyycjnmrndnlrqk = $("jtcyycjnmrndnlrqk").value;

		if(jtcyycjnmrndnlrqk == ""){
			alert("	家庭成员因残疾、年迈而劳动能力弱情况不能为空，请确认！");
			return false;
		}
		
		//	家庭成员失业情况
		var jtcysyqk = $("jtcysyqk").value;

		if(jtcysyqk == ""){
			alert("家庭成员失业情况不能为空，请确认！");
			return false;
		}
		
		// 家庭欠债情况
		var jtqzqk = $("jtqzqk").value;

		if(jtqzqk == ""){
			alert("家庭欠债情况不能为空，请确认！");
			return false;
		}
		
		// 是否低保户或一、二级残疾者,广州户籍的烈士家属、因公牺牲军人或疾故军人家属、低收入家庭
		var sfdbh = $("sfdbh").value;

		if(sfdbh == ""){
			alert("是否低保户或一、二级残疾者,广州户籍的烈士家属、因公牺牲军人或疾故军人家属、低收入家庭不能为空，请确认！");
			return false;
		}
		
		//其他情况
		var qtqk = $("qtqk").value;

		if(qtqk == ""){
			alert("其他情况不能为空，请确认！");
			return false;
		}
		
		//家庭成员状况
		
		//姓名
		var jtcy1_xm = $("jtcy1_xm").value;
		var jtcy2_xm = $("jtcy2_xm").value;
		var jtcy3_xm = $("jtcy3_xm").value;
		var jtcy4_xm = $("jtcy4_xm").value;
		var jtcy5_xm = $("jtcy5_xm").value;
		//年龄
		var jtcy1_nl = $("jtcy1_nl").value;
		var jtcy2_nl = $("jtcy2_nl").value;
		var jtcy3_nl = $("jtcy3_nl").value;
		var jtcy4_nl = $("jtcy4_nl").value;
		var jtcy5_nl = $("jtcy5_nl").value;
		//与本人关系
		var jtcy1_gx = $("jtcy1_gx").value;
		var jtcy2_gx = $("jtcy2_gx").value;
		var jtcy3_gx = $("jtcy3_gx").value;
		var jtcy4_gx = $("jtcy4_gx").value;
		var jtcy5_gx = $("jtcy5_gx").value;
		//工作（学习）单位
		var jtcy1_gz = $("jtcy1_gz").value;
		var jtcy2_gz = $("jtcy2_gz").value;
		var jtcy3_gz = $("jtcy3_gz").value;
		var jtcy4_gz = $("jtcy4_gz").value;
		var jtcy5_gz = $("jtcy5_gz").value;
		//年收入
		var jtcy1_sr = $("jtcy1_sr").value;
		var jtcy2_sr = $("jtcy2_sr").value;
		var jtcy3_sr = $("jtcy3_sr").value;
		var jtcy4_sr = $("jtcy4_sr").value;
		var jtcy5_sr = $("jtcy5_sr").value;
		//健康状况
		var jtcy1_zk = $("jtcy1_zk").value;
		var jtcy2_zk = $("jtcy2_zk").value;
		var jtcy3_zk = $("jtcy3_zk").value;
		var jtcy4_zk = $("jtcy4_zk").value;
		var jtcy5_zk = $("jtcy5_zk").value;
		
		if(	(jtcy1_xm != "" && jtcy1_nl != "" && jtcy1_gx != "" && jtcy1_gz != "" && jtcy1_sr != "" && jtcy1_zk != "") ||
			(jtcy1_xm != "" && jtcy1_nl != "" && jtcy1_gx != "" && jtcy1_gz != "" && jtcy1_sr != "" && jtcy1_zk != "") ||
			(jtcy1_xm != "" && jtcy1_nl != "" && jtcy1_gx != "" && jtcy1_gz != "" && jtcy1_sr != "" && jtcy1_zk != "") ||
			(jtcy1_xm != "" && jtcy1_nl != "" && jtcy1_gx != "" && jtcy1_gz != "" && jtcy1_sr != "" && jtcy1_zk != "") ||
			(jtcy1_xm != "" && jtcy1_nl != "" && jtcy1_gx != "" && jtcy1_gz != "" && jtcy1_sr != "" && jtcy1_zk != "")){
			return true;
		}else{
			alert("请至少完整填写一位家庭成员的信息！");
			return false;
		}
		
		return true;
	}
	</script>
</head>

<body>
	<div class="title">
		<div class="tab_cur" >
			<p class="location">
				<em>您的当前位置:</em><a>学生资助 - 家庭情况调查
		</a></p>
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<p align="center">
			<font color="red">目前不在填写时间范围内，暂不开放填写！</font>
		</p>
	</logic:equal>
		<html:form action="n05_xszz.do?method=jtqkdc1sq" method="post">
			<input type="hidden" id="url" name="url" value="/n05_xszz.do?method=jtqkdc1sq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal" value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="xxdm" name="xxdm"value="${xxdm }" />

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
			<div class="tab">
			<table width="99%"  border="0" class="formlist">
				<thead>
	   				<tr>
	       				<th colspan="10"><span>家庭情况调查</span></th>
	       			</tr>
	  			</thead>
					<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th>
							<font color="red">*</font>学号
						</th>
						<td colspan="4">
							<html:text name='rs' property="xh" styleId="xh" onblur="dctStuXh()"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:notEqual name="type" value="modi">
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
							</logic:notEqual>
						</td>
					</logic:equal>
					<logic:equal name="userType" value="stu" scope="session">
						<th>
							<font color="red">*</font>学号
						</th>
						<td  colspan="4">
							<input type="text" id="xh" name="xh"
								
								value="${userName }" readonly="true">
						</td>
					</logic:equal>
					<th>
							姓名
					</th>
					<td  colspan="4">
						<input type="text" readonly="readonly" id="xm" name="xm"
							
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th>
						性别
					</th>
					<td  colspan="4">
						<input type="text" id="xb" readonly="readonly" name="xb"
							
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<th>
						出生日期
					</th>
					<td  colspan="4">
						<input type="text" id="csrq" name="csrq" readonly="readonly"
							
							value="<bean:write name="rs" property="csrq"/>">
					</td>
				</tr>
				<tr>
					<th>
						民族
					</th>
					<td  colspan="4">
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<th>
						政治面貌
					</th>
					<td  colspan="4">
						<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
							
							value="<bean:write name="rs" property="zzmmmc"/>">
					</td>
				</tr>
				<tr>
					<th>
							年级
					</th>
					<td  colspan="4">
						<input type="text" id="nj" readonly="readonly" name="nj"
							
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />名称
					</th>
					<td  colspan="4">
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<th>
						专业名称
					</th>
					<td  colspan="4">
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<th>
						班级名称
					</th>
					<td  colspan="4">
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<th>
						学制
					</th>
					<td  colspan="4">
						<input type="text" id="xz" name="xz" readonly="readonly"
							
							value="<bean:write name="rs" property="xz"/>">
					</td>
					<th>
						身份证号
					</th>
					<td  colspan="4">
						<input type="text" id="sfzh" readonly="readonly" name="sfzh"
							
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<th>
							学年
					</th>
					<td  colspan="4">
						<input type="text" id="xn" name="xn" readonly="readonly"
							
							value="<bean:write name="rs" property="xn"/>">
					</td>
					<th>
							申请时间
					</th>
					<td  colspan="4">
						<input type="text" id="sqsj" readonly="readonly" name="sqsj"
							
							value="<bean:write name="rs" property="sqsj"/>">
					</td>
				</tr>
				<tr>
					<th>
						入学前户口
					</th>
					<td  colspan="4">
						<html:select name="rs" property="rxqhk">
							<html:option value=""></html:option>
							<html:option value="城镇">城镇</html:option>
							<html:option value="农村">农村</html:option>
						</html:select>
					</td>
					<th>
						家庭人口数
					</th>
					<td  colspan="4">
						<input type="text" id="jtrks" name="jtrks" maxlength="4"
							
							value="<bean:write name="rs" property="jtrks"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						毕业学校
					</th>
					<td  colspan="4">
						<input type="text" id="byxx" name="byxx" maxlength="100"
							
							value="<bean:write name="rs" property="byxx"/>">
					</td>
					<th>
						个人特长
					</th>
					<td  colspan="4">
						<input type="text" id="grtc" name="grtc" maxlength="100"
							
							value="<bean:write name="rs" property="grtc"/>">
					</td>
				</tr>
				<tr>
					<th >
							孤残
					</th>
					<td colspan="2">
						<html:select name="rs" property="sfgc">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>
					<th>
						单亲
					</th>
					<td colspan="2">
						<html:select name="rs" property="sfdq">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>
					<th>
						烈士子女
					</th>
					<td colspan="3">
						<html:select name="rs" property="sflszn">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						家庭地址
					</th>
					<td  colspan="4">
						<input type="text" id="jtdz" name="jtdz" maxlength="200"
							
							value="<bean:write name="rs" property="jtdz"/>">
					</td>
					<th>
						邮政编码
					</th>
					<td  colspan="4">
						<input type="text" id="yzbm" name="yzbm" maxlength="6"
							
							value="<bean:write name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						学生在校联系电话
					</th>
					<td  colspan="4">
						<input type="text" id="xszxlxdh" name="xszxlxdh" maxlength="20"
							
							value="<bean:write name="rs" property="xszxlxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						家庭联系电话
					</th>
					<td  colspan="4">
						<input type="text" id="jtlxdh" name="jtlxdh" maxlength="20"
							
							value="<bean:write name="rs" property="jtlxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
				<td colspan="10">
				<table width="100%">
				<tr>
					<th rowspan="6" >
						家<br />庭<br />成<br />员<br />情<br />况
					</th>
					<th>
						姓名
					</th>
					<th>
						年龄
					</th>
					<th >
						与本人关系
					</th>
					<th>
						工作（学习）单位
					</th>
					<th>
							年收入（元）
					</th>
					<th>
							健康状况
					</th>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy1_xm" name="jtcy1_xm" maxlength="30" size="10"
							value="<bean:write name="rs" property="jtcy1_xm"/>">
					</td>
					<td >
						<input type="text" id="jtcy1_nl" name="jtcy1_nl" maxlength="3" size="10"
							value="<bean:write name="rs" property="jtcy1_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy1_gx" name="jtcy1_gx" maxlength="10" size="10"
							value="<bean:write name="rs" property="jtcy1_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy1_gz" name="jtcy1_gz" maxlength="100" size="10"
							value="<bean:write name="rs" property="jtcy1_gz"/>">
					</td>
					<td>
						<input type="text" id="jtcy1_sr" name="jtcy1_sr" maxlength="10" size="10"
							value="<bean:write name="rs" property="jtcy1_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td >
						<input type="text" id="jtcy1_zk" name="jtcy1_zk" maxlength="50" size="10"
							
							value="<bean:write name="rs" property="jtcy1_zk"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy2_xm" name="jtcy2_xm" maxlength="30" size="10"
							
							value="<bean:write name="rs" property="jtcy2_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy2_nl" name="jtcy2_nl" maxlength="3" size="10"
							
							value="<bean:write name="rs" property="jtcy2_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy2_gx" name="jtcy2_gx" maxlength="10" size="10"
							
							value="<bean:write name="rs" property="jtcy2_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy2_gz" name="jtcy2_gz" maxlength="100" size="10"
							
							value="<bean:write name="rs" property="jtcy2_gz"/>">
					</td>
					<td>
						<input type="text" id="jtcy2_sr" name="jtcy2_sr" maxlength="10" size="10"
							
							value="<bean:write name="rs" property="jtcy2_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy2_zk" name="jtcy2_zk" maxlength="50" size="10"
							
							value="<bean:write name="rs" property="jtcy2_zk"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy3_xm" name="jtcy3_xm" maxlength="30" size="10"
							
							value="<bean:write name="rs" property="jtcy3_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy3_nl" name="jtcy3_nl" maxlength="3" size="10"
							
							value="<bean:write name="rs" property="jtcy3_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td >
						<input type="text" id="jtcy3_gx" name="jtcy3_gx" maxlength="10" size="10"
							
							value="<bean:write name="rs" property="jtcy3_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy3_gz" name="jtcy3_gz" maxlength="100" size="10"
							
							value="<bean:write name="rs" property="jtcy3_gz"/>">
					</td>
					<td>
						<input type="text" id="jtcy3_sr" name="jtcy3_sr" maxlength="10" size="10"
							
							value="<bean:write name="rs" property="jtcy3_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy3_zk" name="jtcy3_zk" maxlength="50" size="10"
							
							value="<bean:write name="rs" property="jtcy3_zk"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy4_xm" name="jtcy4_xm" maxlength="30" size="10"
							
							value="<bean:write name="rs" property="jtcy4_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy4_nl" name="jtcy4_nl" maxlength="3" size="10"
							
							value="<bean:write name="rs" property="jtcy4_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy4_gx" name="jtcy4_gx" maxlength="10" size="10"
							
							value="<bean:write name="rs" property="jtcy4_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy4_gz" name="jtcy4_gz" maxlength="100" size="10"
							
							value="<bean:write name="rs" property="jtcy4_gz"/>">
					</td>
					<td>
						<input type="text" id="jtcy4_sr" name="jtcy4_sr" maxlength="10" size="10"
							
							value="<bean:write name="rs" property="jtcy4_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy4_zk" name="jtcy4_zk" maxlength="50" size="10"
							
							value="<bean:write name="rs" property="jtcy4_zk"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy5_xm" name="jtcy5_xm" maxlength="30" size="10"
							
							value="<bean:write name="rs" property="jtcy5_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy5_nl" name="jtcy5_nl" maxlength="3" size="10"
							
							value="<bean:write name="rs" property="jtcy5_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy5_gx" name="jtcy5_gx" maxlength="10" size="10"
							
							value="<bean:write name="rs" property="jtcy5_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy5_gz" name="jtcy5_gz" maxlength="100" size="10"
							
							value="<bean:write name="rs" property="jtcy5_gz"/>">
					</td>
					<td>
						<input type="text" id="jtcy5_sr" name="jtcy5_sr" maxlength="10" size="10"
						
							value="<bean:write name="rs" property="jtcy5_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy5_zk" name="jtcy5_zk" maxlength="50" size="10"
				
							value="<bean:write name="rs" property="jtcy5_zk"/>">
					</td>
				</tr>
				</table>
				</td>
				</tr>
				<tr>
					<th>
							家庭人均年收入
					</th>
					<td colspan="4">
						<input type="text" id="jtrjnsr" name="jtrjnsr" maxlength="8"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrjnsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						家庭人均月收入
					</th>
					<td colspan="4">
						<input type="text" id="jtrjysr" name="jtrjysr" maxlength="8"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrjysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
							学生本学年已获资助情况
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="bxnyhzzqk" rows='3' style="width:100%" onblur="yzdx(this,'bxnyhzzqk')"/>
						<input type="hidden" id="bxnyhzzqk" name="bxnyhzzqk" value="">
					</td>
				</tr>
				<tr>
					<th>
							本学年缴纳学费情况
					</th>
					<td colspan="4">
						<html:select name="rs" property="sfjxf" onchange="changeXf()">
							<html:option value=""></html:option>
							<html:option value="是">已缴</html:option>
							<html:option value="否">未缴</html:option>
						</html:select>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" id="yjxf" name="yjxf" maxlength="6"
							style="width:60px;heigh:100%"
							value="<bean:write name="rs" property="yjxf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">元
					</td>
					<th>
							缴纳住宿费情况
					</th>
					<td colspan="4">
						<html:select name="rs" property="sfjzsf" onchange="changeZsf()">
							<html:option value=""></html:option>
							<html:option value="是">已缴</html:option>
							<html:option value="否">未缴</html:option>
						</html:select>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" id="yjzsf" name="yjzsf" maxlength="6"
							style="width:60px;heigh:100%"
							value="<bean:write name="rs" property="yjzsf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">元
					</td>
				</tr>
				<tr>
					<th colspan="1">
							家庭提供生活费(元/月)
					</th>
					<td colspan="4">
						<input type="text" id="jttgshf" name="jttgshf" maxlength="8"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jttgshf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
							在校需消费(元/月)
					</th>
					<td colspan="4">
						<input type="text" id="zxxxf" name="zxxxf" maxlength="8"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zxxxf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						家庭遭受自然灾害情况
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="jtcszrzhqk" rows='3' style="width:100%" onblur="yzdx(this,'jtcszrzhqk')"/>
						<input type="hidden" id="jtcszrzhqk" name="jtcszrzhqk" value="">
					</td>
				</tr>
				<tr>
					<th>
						家庭遭受突发意外事件
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="jtcstfywqk" rows='3' style="width:100%" onblur="yzdx(this,'jtcstfywqk')"/>
						<input type="hidden" id="jtcstfywqk" name="jtcstfywqk" value="">
					</td>
				</tr>
				<tr>
					<th>
						家庭成员因残疾、年迈而劳动能力弱情况
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="jtcyycjnmrndnlrqk" rows='3' style="width:100%" onblur="yzdx(this,'jtcyycjnmrndnlrqk')"/>
						<input type="hidden" id="jtcyycjnmrndnlrqk" name="jtcyycjnmrndnlrqk" value="">
					</td>
				</tr>
				<tr>
					<th>
						家庭成员失业情况
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="jtcysyqk" rows='3' style="width:100%" onblur="yzdx(this,'jtcysyqk')"/>
						<input type="hidden" id="jtcysyqk" name="jtcysyqk" value="">
					</td>
				</tr>
				<tr>
					<th>
							家庭欠债情况
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="jtqzqk" rows='3' style="width:100%" onblur="yzdx(this,'jtqzqk')"/>
						<input type="hidden" id="jtqzqk" name="jtqzqk" value="">
					</td>
				</tr>
				<tr>
					<th colspan="6">
						<div align="center">
							<logic:equal name="xxdm" value="11078">
								是否低保户或一、二级残疾者,广州户籍的烈士家属、因公牺牲军人或疾故军人家属、低收入家庭
							</logic:equal>
							<logic:notEqual name="xxdm" value="11078">
								是否低保户或一、二级残疾者,烈士家属、因公牺牲军人或疾故军人家属、低收入家庭
							</logic:notEqual>
						</div>
					</th>
					<td  colspan="4">
						<html:select name="rs" property="sfdbh">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						其他情况
					</th>
					<td colspan="9">
						<html:textarea name="rs" property="qtqk" rows='3' style="width:100%" onblur="yzdx(this,'qtqk')"/>
						<input type="hidden" id="qtqk" name="qtqk" value="">
					</td>
				</tr>
<!-- 				<tr>
					<td colspan="2">
						<div align="center">
							民政部门详细通讯地址
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="mzbm_xxtxdz" name="mzbm_xxtxdz" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_xxtxdz"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							邮政编码
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="mzbm_yzbm" name="mzbm_yzbm" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							当地最低生活保障(元/月)
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="ddzdshbz" name="ddzdshbz" maxlength="8"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ddzdshbz"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							经办人姓名
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jbrxm" name="jbrxm" maxlength="30"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jbrxm"/>">
					</td>
					<td>
						<div align="center">
							经办人办公联系电话
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jbrbgdh_qx" name="jbrbgdh_qx" maxlength="4"
							style="width:40px;heigh:100%"
							value="<bean:write name="rs" property="jbrbgdh_qx"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						-
						<input type="text" id="jbrbgdh_dh" name="jbrbgdh_dh" maxlength="16"
							style="width:120px;heigh:100%"
							value="<bean:write name="rs" property="jbrbgdh_dh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>-->
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="10"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button  id="buttonSave" onclick="yz();">
							提交申请
						</button>
						&nbsp;
						<button 
							onclick="toPrintOut();">
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
	
	<script language="javascript">
		var sfjxf = document.getElementById('sfjxf').value;
			
		if (sfjxf == "是") {
			document.getElementById('yjxf').disabled = false;
		} else {
			document.getElementById('yjxf').disabled = true;
		}
		var sfjzsf = document.getElementById('sfjzsf').value;
			
		if (sfjzsf == "是") {
			document.getElementById('yjzsf').disabled = false;
		} else {
			document.getElementById('yjzsf').disabled = true;
		}
	</script>
	
</html>
