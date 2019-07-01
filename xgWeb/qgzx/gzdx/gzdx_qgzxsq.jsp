<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type="text/javascript">
	function save(obj){
	
		var xxdm = $("xxdm").value;
		
		//广州大学
		if("11078" == xxdm){
			var checkBoxArr = document.getElementsByName("checkVal");
			var flag = false;
			obj = "xh";
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					flag = true;
				}
			}
			
			if(!flag){
				alert("请勾选要所申请的岗位性质！！");
				return false;
			}
		}
		
		var value = obj.split("-");
		for(var i=0; i<value.length; i++){
			if(val(value[i])==""){
				alert("请将带\*号的项目填写完整！");				
				return false;
			}
		}
		var yhtc = val('yhtc');
		var bz = val('bz');    
		
		if(yhtc != null){
	         	if(yhtc.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("有何特长不能大于100个字符");
	          		 return false;
	       		 }
			}
		if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	          		 alert("备注不能大于250个字符");
	          		 return false;
	       		 }
			}		
		refreshForm('qgzxZgdzdx.do?method=saveQgzxsq');
	}
	
	function printReport(){
		window.open('qgzxZgdzdx.do?method=printGzdxQgzxsqb');
	}
	
	function onShow(){
		if($("gwxznum")){
			var xn = $("xn").value;
			var xq = $("xq").value;
			var nd = $("nd").value;
			var xh = $("xh").value;
			
			var tableName = "qgzxsqb";
			var colList = ["xh", "xn", "nd", "xq", "lxdh", "kcjqgzxsj", "sqly", "bz","zzmmdm", "yhtc", "kcsgz","jjqk"];
			var pk = "xn||xq||nd||xh";
			var pkValue = xn + xq + nd + xh;
			var query = "and rownum = 1";
			
			if(xn != "" && xq != "" && nd != "" && xh != ""){
				getTableInfo(tableName,colList,pk,pkValue,query);
			}
			
			dwr.engine.setAsync(false);
				
			var gwxznum = $("gwxznum").value;
			colList = ["gwxzdm"];
			
			getOtherData.getTableListInfo(tableName, colList, pk, pkValue,"",function(data){
				if( data != null && data.length > 0){
					for (i = 0; i < data.length; i++) {
						for(j=0; j<gwxznum; j++){
							var id = "gwxzdm"+j;
							if(data[i].gwxzdm == $(id).value){
								$(id).checked="checked";
							}
						}
					}
				}
			});
			
			dwr.engine.setAsync(true);
		}
		
	}
	</script>
	<body onload="onShow()">
		<html:form action="/qgzxZgdzdx.co" method="post">
			<div class="title">
					<div class="title_img" id="title_m">
						当前位置：勤工助学 - 申请 - 申请勤工助学
					</div>			
			</div>
			<logic:equal value="1" name="inTime">
				<br />
				<br />
				<p align="center">
					不在申请时间内，暂时不开放申请！
				</p>
			</logic:equal>
			<logic:notEqual value="1" name="inTime">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/qgzxZgdzdx.do?method=qgzxsq" />
				<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />
				<input type="hidden" id="gwxznum" name="gwxznum" value="${gwxznum }" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td height="18" colspan="4" align="center">
									<b>填写申请表</b>
							</td>
						</tr>
					</thead>					
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<input type="hidden" name="xh" id="xh" value="<bean:write name='rs' property="xh" />">
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left">								
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>
						<td align="right">
							年度：
						</td>
						<td align="left">
							<html:hidden name="rs" property="nd"/>
							<bean:write name='rs' property="nd" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right">
							学年：
						</td>
						<td align="left">
							<html:hidden name="rs" property="xn"/>
							<bean:write name='rs' property="xn" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left">
							<bean:write name='rs' property="xb" />
						</td>
						<td align="right">
							学期：
						</td>
						<td align="left">
							<bean:write name='rs' property="xqmc" />
							<html:hidden name="rs" property="xq"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<bean:write name='rs' property="xymc" />
						</td>
						<td align="right">
							宿舍编号：
						</td>
						<td align="left">
							<bean:write name='rs' property="ssbh" />
						</td>						
					</tr>
					<tr style="height:22px">
						<td align="right">
							专业：
						</td>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>
						<td align="right">
							联系电话：
						</td>
						<td align="left">
							<html:text name='rs' property="lxdh" styleId="lxdh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</td>												
					</tr>
					<tr style="height:22px">
						<td align="right">
							班级：
						</td>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right">
							有何特长：
						</td>
						<td>
							<html:text name="rs"  property="yhtc" styleId="yhtc" style="width:150px;height:21px;font-size:10pt;" maxlength="100"/><span style="width:18px;border:0px solid red;">
							<select style="margin-left:-150px;width:168px; background-color:#FFEEEE;" onchange="ele('yhtc').value=this.value">
								<option value="书法">书法</option>
								<option value="绘画">绘画</option>
								<option value="电脑打字">电脑打字</option>
								<option value="网页制作">网页制作</option>
								<option value="写作">写作</option>
							</select>
						</td>
					</tr>
					<!-- 广州大学 -->
					<logic:equal name="xxdm" value="11078">
					<tr>
						<td align="right">
							<font color="red">*</font>参加岗位性质：
						</td>
						<td colspan="3">
							<logic:iterate name="gwxzList" id="s" indexId="index">
								<input type="checkbox" name="checkVal" id="gwxzdm${index }" value="${s.gwxzdm }">${s.gwxzmc }		
							</logic:iterate>
						</td>
					</tr>
					</logic:equal>
					<!-- 非广州大学 -->
					<logic:notEqual name="xxdm" value="11078">
					<tr>
						<td align="right">
							<font color="red">*</font>参加岗位性质：
						</td>
						<td colspan="3">
							<html:select property="gwxzdm" styleId="gwxzdm" name="rs">
								<html:option value=""></html:option>
								<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
							</html:select>
						</td>
					</tr>
					</logic:notEqual>
					<tr>
						<td align="right">
							可参加勤工助学时间：
						</td>
							<logic:present name="kxList">
							<td colspan="3">
								<table border="0" cellpadding="0" cellspacing="0" align="center">
									<tr>
										<td align="center">
											时间
										</td>
										<td>
											星期一
										</td>
										<td>
											星期二
										</td>
										<td>
											星期三
										</td>
										<td>
											星期四
										</td>
										<td>
											星期五
										</td>
									</tr>
									<logic:iterate id="kxsj" name="kxList">
										<tr>
											<td>
												${kxsj.sj}
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}1"
													value="${kxsj.mon}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}2"
													value="${kxsj.tue}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}3"
													value="${kxsj.wed}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}4"
													value="${kxsj.thu}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}5"
													value="${kxsj.fri}" />
											</td>
										</tr>
									</logic:iterate>
								</table>
								</td>
								<script language="javascript">
										for(var i=0;i<8;i++){
											for(var j=1;j<6;j++){
											 	if(document.getElementById("index"+i+j)){
											 		if(document.getElementById("index"+i+j).value==1){
														document.getElementById(i+""+j).checked="checked";
												    }
											 	}
											}
										}
									</script>							
						</logic:present>
						<logic:notPresent name="kxList">
							<input type="hidden" name="sfwh" value="sfwh" />
							<td colspan="3" align="center">
								<table border="0" cellpadding="0" cellspacing="0" align="center"
									class="tbstyle">
									<tbody id="tbSj">
										<tr>
											<td align="center">
												时间
											</td>
											<td>
												星期一
											</td>
											<td>
												星期二
											</td>
											<td>
												星期三
											</td>
											<td>
												星期四
											</td>
											<td>
												星期五
											</td>
										</tr>
										<logic:iterate id="kxsj" name="whkxList">
											<tr>
												<td>
													${kxsj.sj}
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
												</td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</td>
						</logic:notPresent>
					</tr>
					<tr>
						<td align="right">
							本人家庭经济状况：
						</td>
						<td align="left" colspan="3">
							<html:textarea name='rs' property='jjqk' styleId="jjqk" style="width:99%" rows='5' onblur="chLeng(this,'600')"/>
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							可从事何种工作：
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='kcsgz' styleId="kcsgz" style="width:99%" rows='5' onblur="chLeng(this,'150')"/>
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							备注：
						</td>
						<td colspan="3"><html:textarea name='rs' property='bz' styleId="bz" style="width:99%" rows='5' onblur="chLeng(this,'250')"/>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2" onclick="save('xh-gwxzdm')">
						保 存 申 请
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="window.open('/xgxt/qgzx/gzdx/qgzxsqb.jsp')">
						打 印 预 览
					</button>
				</div>
			</logic:notEmpty>
			</logic:notEqual>

			<logic:equal name="bmztj" value="true">
					<script>
				    	alert("不满足申请勤工助学的条件！");
				    </script>
				</logic:equal>
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
					<script>
				    	alert("申请成功！");
				    	Close();
				    	if(window.dialogArguments)
						window.dialogArguments.document.getElementById('search_go').click();
				    </script>
				</logic:equal>
				<logic:equal name="result" value="false">				
				<logic:notPresent name="hmdMember">
					<script>
				    	alert("申请失败！");
				    </script>
				  </logic:notPresent>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
