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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>

	<script language="javascript">
		function dataDoSavePubGwxx(url, pkFields) {
			var jcbz = document.getElementById("jybcbz").value;			
			var eles = pkFields.split("-");
			var doType = document.getElementById("doType").value;
			var valu = "";
			for (i = 0; i < eles.length; i++) {
				if (document.getElementById(eles[i]).value =="") {
					alert("请将带\"*\"号的项目输入完整！");
					return false;
				}
			}	
				for(var i=1;i<6;i++){
					if(doType!=null && doType!="modi"){		
					var gwmc = document.getElementById("gwmc"+i).value;		
						var xyboy = document.getElementById("xyboy"+i).value;
						var xygirl = document.getElementById("xygirl"+i).value;
						var gwsl = document.getElementById("gwsl"+i).value;
						var gznr = document.getElementById("gznr"+i).value;
						var gwxz = document.getElementById("gwxz"+i).value;
						var xyboy = document.getElementById("xyboy"+i).value;
						var xygirl = document.getElementById("xygirl"+i).value;
						if(gwmc!=null && gwmc!=""){
							if (gwsl==null || gwsl==""){
								alert("岗位数量为空!");
								document.getElementById("gwsl"+i).focus();
								return false;
							}
							if(gznr==null || gznr==""){
								alert("工作内容为空!");
								document.getElementById("gznr"+i).focus();
								return false;
							}
							if(gwxz==null || gwxz==""){
								alert("岗位性质为空!");
								document.getElementById("gwxz"+i).focus();
								return false;
							}
							if(xyboy==null || xyboy==""){
								alert("需要男生人数为空!");
								document.getElementById("xyboy"+i).focus();
								return false;
							}
							if(xygirl==null || xygirl==""){
								alert("需要女生人数为空!");
								document.getElementById("xygirl"+i).focus();
								return false;
							}
						}
					}else{				
						var xyboy = document.getElementById("xyboy1").value;
						var xygirl = document.getElementById("xygirl1").value;
						var gwsl = document.getElementById("gwsl1").value;				
					}
					
					if(xyboy!=null && xyboy!="" && xyboy.match(/^\d+\.{0,1}\d{0,3}$/)==null){
						alert("数据格式错误！");
						document.getElementById("xyboy"+i).focus();
						return false;
					}
					if(xygirl!=null && xygirl!="" && xygirl.match(/^\d+\.{0,1}\d{0,3}$/)==null){
						alert("数据格式错误！");
						document.getElementById("xygirl"+i).focus();
						return false;
					}
					if(gwsl!=null && gwsl!="" && gwsl.match(/^\d+\.{0,1}\d{0,3}$/)==null){
						alert("数据格式错误！");
						document.getElementById("gwsl"+i).focus();
						return false;
					}
				}	
			if(jcbz!=null && jcbz!="" && jcbz.match(/^\d+\.{0,1}\d{0,3}$/)==null){
					alert("数据格式错误！");
					document.getElementById("jybcbz").focus();
					return false;
				}
			document.forms[0].action = url;
			document.forms[0].submit();
			if(window.dialogArguments){
			window.close();
			window.dialogArguments.document.all("search_go").click();
			}
			return true;
		}
		
		function initSelect(){
			var type=document.getElementById("doType").value;
			var gwxzz = document.getElementById("gwxzz").value;
			if(type!=null && type=="modi"){
				document.getElementById("gwxz1").value=gwxzz;
			}
		}
	</script>
		<base target="_self" />
	<body>
		
		<html:form action="/comm_pub" method="post">
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：勤工助学 - 岗位发布 - 岗位信息发布
				</div>
			</div>
				<input type="hidden" id="doType" name="doType" value="<bean:write name="doType" scope="request"/>"/>
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								&nbsp;
							</td>
						</tr>
					</thead>
					<tr>
						<td width="15%" height="22" align="right">
							校区：
						</td>
						<td width="38%" height="22" align="left">
							<html:select name="rs" property="xq" style="width:120px"
								styleId="xqdm">
								<html:option value=""></html:option>
								<html:options collection="xqList1" property="dm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						<td width="11%" height="22" align="right">
							<font color="red">*</font>申请单位：
						</td>
						<td width="32%" height="22" align="left">
							<html:select name="rs" property="sqdw" styleId="sqdw"
								style="width:120px" onchange="getYrdwInfo()">
								<html:option value=""></html:option>
								<html:options collection="sqdwList" property="yrdwdm"
									labelProperty="yrdwmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							学年：
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="xn" style="width: 90px"
								readonly="true" />
						</td>
						<td height="22" align="right">
							联系人： 
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="fzr" styleId="lxr" readonly="true" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							年度：
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="nd" style="width: 90px"
								readonly="true" />
						</td>
						<td height="22" align="right">
							联系电话： 
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="lxdh" styleId="lxdh" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							工作时间： 
						</td>
						<td height="22" align="left">
						<html:select name="rs" property="gzsj">
							<html:option value=""></html:option>
							<html:option value="上午">上午</html:option>
							<html:option value="中午">中午</html:option>
							<html:option value="晚上">晚上</html:option>
						</html:select>
							
						</td>						
						<td height="22" align="right">
							单位地点： 
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="dwdz" styleId="dwdz"/>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							工作开始<br/>日期：
						</td>
						<td height="22" align="left">
							<html:text name='rs' property="gzkssj" styleId="gzkssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('gzkssj','y-mm-dd');" />
						</td>
						<td height="22" align="right" width="200">
							<font color="red">*</font>工作结束<br/>日期：
						</td>
						<td height="22" align="left">
							<html:text name='rs' property="gzjssj" styleId="gzjssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('gzjssj','y-mm-dd');" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>计酬方式：
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="jcfs" styleId="jcfs" onchange="subloadPost();loadJcbz(this.value)">
									<html:options collection="jcfsList" property="dm" labelProperty="mc"/>
<!--								<html:option value="">------请选择------</html:option>-->
<!--								<html:option value="h">按小时</html:option>-->
<!--								<html:option value="d">按天</html:option>-->
<!--								<html:option value="w">按周</html:option>-->
<!--								<html:option value="m">按月</html:option>-->
							</html:select>
						</td>
						<td align="right">
							<font color="red">*</font>计酬标准：
						</td>
						<td>
							<html:text name="rs" property="jybcbz" styleId="jybcbz" />
							<span id="jybcbzDw"></span>
						</td>
					</tr>
					<tr>
						<td height="22" colspan="4" align="right">
							<table width="100%" class="tbstyle">
								<tr>
									<td width="120" align="center">
										岗位名称
									</td>
									<td width="60" align="center">
										岗位数量
									</td>
									<td width="120" align="center">
										岗位要求
									</td>
									<td width="120" align="center">
										工作内容
									</td>
									<td width="120" align="center">
										岗位性质
									</td>
									<td align="right">
										需要男生数
									</td>
									<td align="right">
										需要女生数
									</td>
								</tr>
								<logic:notEqual value="modi" name="doType">
								<%
									for(int i=1;i<6;i++){
						
					 			%>
									<tr>
									<td>
										<input type="text" id="gwmc<%=i %>" name="gwmc<%=i %>" value="${rs.gwdm}" style="width:120px" onkeyup="value=value.replace('-','')"/>
									</td>
									<td>
										<input type="text" id="gwsl<%=i %>" name="gwsl<%=i %>" value="${rs.gwsl}" style="width:60px"/>
									</td>
									<td>										
										<input type="textarea" id="ryyq<%=i %>" name="ryyq<%=i %>" value="${rs.ryyq}" style="width:85%"/>
									</td>
									<td>
										<input type="textarea" id="gznr<%=i %>" name="gznr<%=i %>" value="${rs.gznr}"/>
									</td>
									<td>
										<select id="gwxz<%=i %>" name="gwxz<%=i %>">
											<option value=""></option>
											<logic:iterate id="v" name="gwxzList">
												<option value="${v.gwxzdm}">${v.gwxzmc}</option>
											</logic:iterate>
										</select>
									</td>
									<td>										
										<input type="text" id="xyboy<%=i %>" name="xyboy<%=i %>" value="${rs.xyboy}" style="width:120px"/>
									</td>
									<td>
										<input type="text" id="xygirl<%=i %>" name="xygirl<%=i %>" value="${rs.xygirl}" style="width:120px"/>										
									</td>
								</tr>
								<%
									}
								 %>		
							 </logic:notEqual>
							 <logic:equal value="modi" name="doType">
							
							 <input type="hidden" id="sqsj" name="sqsj" value="${rs.sqsj}"/>
							 	<tr>
									<td>
										<input type="text" id="gwmc1" name="gwmc1" value="${rs.gwdm}" style="width:120px"/>
									</td>
									<td>
										<input type="text" id="gwsl1" name="gwsl1" value="${rs.gwsl}" style="width:60px"/>
									</td>
									<td>										
										<input type="textarea" id="ryyq1" name="ryyq1" value="${rs.ryyq}" style="width:85%"/>
									</td>
									<td>
										<input type="textarea" id="gznr1" name="gznr1" value="${rs.gznr}"　style="width:85%"/>
									</td>
									<td>
										<input type="hidden" id="gwxzz" name="gwxzz" value="${rs.gwxz}"/>
										<select id="gwxz1" name="gwxz1" value="${rs.gwxz}">
											<option value=""></option>
											<logic:iterate id="v" name="gwxzList">
												<option value="${v.gwxzdm}">${v.gwxzmc}</option>
											</logic:iterate>
										</select>
									</td>
									<td>										
										<input type="text" id="xyboy1" name="xyboy1" value="${rs.xyboy}" style="width:120px"/>
									</td>
									<td>
										<input type="text" id="xygirl1" name="xygirl1" value="${rs.xygirl}" style="width:120px"/>										
									<script>
										initSelect();
									</script>
									</td>
							 </logic:equal>					
							</table>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							岗位其它特殊要求：
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="gwtsyq" style="width:100%"
								rows="5"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							申请单位意见：
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="sqdwyj" style="width:100%"
								rows="5"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							备注：
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="bz" style="width:100%" rows="5"></html:textarea>
						</td>
					</tr>
				</table>
				<logic:present name="writeAble">
					<logic:match value="yes" name="writeAble">
						<div id="button" align="center">
							<button type="button" class="button2"
								onclick="dataDoSavePubGwxx('/xgxt/qgzxLogic.do?method=saveStationInfo','sqdw-jcfs-jybcbz-gzjssj')"
								style="width:80px" id="buttonSave">
								保 存
							</button>
							<logic:notPresent name="zdy">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="expAppTab('rsT','勤工助学岗位申报表','')">
									打 印 预 览
								</button>
							</logic:notPresent>
							<logic:present name="zdy">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2"
									onclick="printReport('qgzx_bb_gwsbb.do?gwdm=')">
									打 印 预 览
								</button>
							</logic:present>
						</div>
					</logic:match>
				</logic:present>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("操作成功！");		
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("操作失败!");
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
