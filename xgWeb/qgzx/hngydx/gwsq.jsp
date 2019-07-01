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
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/cqkjFunc.js'></script>	
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type="text/javascript">
	function getDw(){
		var pk = document.getElementById("xmdm").value;
		if(pk!=null && pk!=""){
		getOtherData.getDwmc(pk,function(data){
			document.getElementById("sqdw").value = data[0];
			document.getElementById("fzr").value = data[1];
			document.getElementById("gwlxdh").value = data[2];
		});
		}		
	}
	function getFzr(){
		var pk = document.getElementById("gwdm").value;
		if(pk!=null && pk!=""){
			getOtherData.getDwmc(pk,function(data){
				if($("fzr")){
					document.getElementById("fzr").value = data[1];
				}
				if($("dwlxdh")){
					document.getElementById("dwlxdh").value = data[2];
				}
				
			});
			//长沙民政要判断该岗位是否是“志愿服务”的
			if($("xxdm").value == "10827"){
				cqkjFunc.isGwZyfw(pk,function(data){
					if(data == "1"){
					//表示是志愿服务的岗位
						document.getElementById("bc").innerHTML="<font color='red'>该岗位[志愿服务]</font>"
					}else{
						document.getElementById("bc").innerHTML= "";
					}
				});
			}
		}		
	}
	
	function getSqtj(){
		var pk = "GWDM||'-'||GWSBSJ";
		var pkValue = document.getElementById("xmdm").value;
		var str;
		if(pk!=null && pk!=""){
			cqkjFunc.getSqtjString(pk,pkValue,function(data){
				document.getElementById("sqtj").innerHTML = data[0];
				if(data[1] == "1"){
					//增加一个选项
					//djsj();
				}
				if(data[1] == "0"){
					//document.getElementById("sfdjsj").innerHTML = "";
					//document.getElementById("sfdjsjInput").innerHTML = "";
				}
			});
		}	
	}
	
function saveXsgw(mustFill){
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	}
	var isModi = "";
	if($("isModi")){
		isModi = document.getElementById("isModi").value;
	}
	if(isModi == "modi"){
	    var xmdmmodi = document.getElementById("xmdmmodi").value;
		refreshForm('xsqgzx.do?method=modXsgwInfo&gwdm=' + xmdmmodi);
	}else{
		var xh = document.getElementById("xh").value;
		var gwdm = document.getElementById("gwdm").value;		
		var query = xh + "-" + gwdm;
		getOtherData.IsDataExist(query,TjIsDataExist);
	}
}
function TjIsDataExist(data){
	if(data != null){
		if(data == "1"){
			if(confirm("你已经申请此岗位\n此操作将保存你的最新修改，确定要修改吗？")){
				refreshForm('xsqgzx.do?method=addXsgwInfo');
			}else{
				return false;
			}
		}else if( data== "2"){
			alert("你已经申请此岗位\n且已经通过审核，你已不能再申请此岗位！");
			return false;
		}else{
			refreshForm('xsqgzx.do?method=addXsgwInfo');
		}
	}else{
		alert("获取数据失败！");
		return false;
	}
}

	function printView(){
		var xh = document.getElementById('xh').value;
		var yhtc = document.getElementById('yhtc').value;
		var gwjl = document.getElementById('gzjl').value;
		var lxdh = document.getElementById('lxdh').value;
		var url = 'xsqgzx.do?method=printXsgwxxb';

		url += '&xh=';
		url += xh;
		url += '&yhtc=';
		url += yhtc;
		url += '&gzjl=';
		url += gwjl;
		url += '&lxdh=';
		url += lxdh;
				
		window.open(url);
	}
	</script>
	<body>
	<html:form action="/xsqgzx.do" method="post">				
		<input type="hidden" name="xxdm" id="xxdm" value="<bean:write name='xxdm'/>"/>
		<input type="hidden" name="doType" id="doType" value="<bean:write name='doType'/>"/>
			<div class="title">
				<logic:equal name="do" value="no">
					<div class="title_img" id="title_m">
						当前位置：勤工助学 - 岗位申请 - 填写申请表
					</div>
				</logic:equal>
				<logic:equal name="do" value="yes">
					<div class="title_img" id="title_m">
						当前位置：勤工助学 - 岗位申请 - 修改申请表
					</div>
				</logic:equal>
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
					<logic:equal name="sqsjFlag" value="1">
						<script>
    				alert("不在设定时间范围内,暂不开放申请!");
    				location.href="about:blank";
    				</script>
					</logic:equal>

				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc-jg-zzmmmc-ssbh-lxdh" />
				<input type="hidden" id="url" name="url" value="/xsqgzx.do?method=stationApp" />
				<input type="hidden" name="sfwh" value="sfwh" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<logic:equal name="do" value="no">
									<b>填写申请表</b>
								</logic:equal>
								<logic:equal name="do" value="yes">
									<b>修改申请表</b>
								</logic:equal>
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
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left">
								<bean:write name='rs' property="xh" />
								<input type="hidden" name="xh" id="xh" value="<bean:write name='rs' property="xh" />">
							</td>
						</logic:equal>
						<td align="right">
							<font color="red">*</font>岗位名称：						
						</td>
						<logic:equal value="modi" name="doType">
							<td align="left">
								<input type="hidden" id="isModi" name="isModi"  value="<bean:write name="doType" scope="request"/>" />
								<input type="hidden" name="xmdmmodi" id="xmdmmodi" value="<bean:write name='rs' property='gwdm'/>">
										<html:select name="rs" property="gwdm" styleId="gwdm"
											style="width:150px" disabled="true" onchange="">
											<html:option value=""></html:option>
											<html:options collection="gwList" property="gwdm"
											labelProperty="gwmc" />
										</html:select>
								
							</td>
						</logic:equal>
						<logic:notEqual value="modi" name="doType">						
								<td align="left">							
									<html:select name="rs" property="gwdm" styleId="gwdm"
											style="width:150px" onchange="getFzr();">
											<html:option value=""></html:option>
											<html:options collection="gwList" property="gwdm"
												labelProperty="gwmc" />
										</html:select>
								</td>
						</logic:notEqual>	
					</tr>

					<tr style="height:22px">
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<bean:write name="rs" property="xm"/>
						</td>
						<td align="right">
							年度：
						</td>
						<td align="left">
							<html:text name="rs" property="nd" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left">
							<bean:write name="rs" property="xb"/>
						</td>
						<td align="right">
							学年：
						</td>
						<td align="left">
							<html:text name="rs" property="xn" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							年级：
						</td>
						<td align="left">
							<bean:write name="rs" property="nj"/>
						</td>
						<td align="right">
							学期：
						</td>
						<td align="left">
							<html:text name="rs" property="xq" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<bean:write name="rs" property="xymc"/>
						</td>
						<td align="right">
							联系电话：
						</td>
						<td align="left">
							<html:text property="dwlxdh" styleId="dwlxdh" name="rs"/>
						</td>
					</tr>						
						<tr>
							<td align="right">
								专业：
							</td>
							<td>
								<bean:write name="rs" property="zymc"/>
							</td>	
							<td align="right">
								联系人：
							</td>
							<td>
								<html:text property="fzr" styleId="fzr" name="rs"/>
							</td>		
						</tr>
						<tr>
							<td align="right">
								班级：
							</td>
							<td>
								<bean:write name="rs" property="bjmc"/>
							</td>
							<td align="right">
								籍贯：
							</td>							
							<td>
								<bean:write name="rs" property="jg"/>
							</td>
						</tr>						
						<tr>
						  <td align="right">宿舍号：</td>
						  <td>
						  <bean:write name="rs" property="ssbh"/>
						  </td>
						  <td align="right">
								政治面貌：
							</td>
							<td>
								<bean:write name="rs" property="zzmmmc"/>
							</td>
				    </tr>
					<tr >
						<logic:notEmpty name="kxList">
								<td colspan="4" bgcolor="#FFDDCC">
									<table border="0" cellpadding="0" cellspacing="0"
										align="center">
										<tr>
										<td colspan="8" align="center">
										可参加勤工助学空闲时间
										</td>
										</tr>
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
											<td>
												星期六
											</td>
											<td>
												星期日
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
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
													<input type="hidden" name="index${kxsj.sjIndex}6"
														value="${kxsj.sat}" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
													<input type="hidden" name="index${kxsj.sjIndex}7"
														value="${kxsj.sun}" />
												</td>
											</tr>
										</logic:iterate>
									</table>
										<script language="javascript">
										for(var i=0;i<7;i++){
											for(var j=1;j<8;j++){
											 	if(document.getElementById("index"+i+j)){
											 		if(document.getElementById("index"+i+j).value==1){
														document.getElementById(i+""+j).checked="checked";
												    }
											 	}
											}
										}
									</script>
								</td>
							</logic:notEmpty>

							<logic:empty name="kxList">								
								<td colspan="4" align="center" bgcolor="#FFDDCC">
									<table border="0" cellpadding="0" cellspacing="0"
										align="center" class="tbstyle" >
<%--										<thead>--%>
											<tr
												onclick="if(document.getElementById('tbSj').style.display == 'none'){document.getElementById('tbSj').style.display = ''}else{document.getElementById('tbSj').style.display = 'none'};">
												<td colspan="8" align="center" style="cursor:hand">
													可参加勤工助学空闲时间
												</td>
											</tr>
<%--										</thead>--%>
										<tbody id="tbSj" style="display: none">
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
												<td>
													星期六
												</td>
												<td>
													星期日
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
													<td align="center">
														<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
													</td>
													<td align="center">
														<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
													</td>
												</tr>
											</logic:iterate>
										</tbody>
									</table>
								</td>
							</logic:empty>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							联系电话：
						</td>
						<td colspan="3">
							<html:text name="rs" property="lxdh" styleId="lxdh" style="width:100%"/>
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							有何特长：
						</td>
						<td colspan="3">
							<html:textarea name='rs' property="yhtc" styleId="yhtc"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							岗位记录：
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='gzjl' styleId="gzjl"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							申请理由：
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='sqly' styleId="sqly"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							备注：
						</td>
						<td colspan="3">
						<logic:equal value="student" name="userOnLine">
						<html:textarea name='rs' property='bz' styleId="bz"
								style="width:99%" rows='5' readonly="true"/>
						</logic:equal>
						<logic:notEqual value="student" name="userOnLine">
							<html:textarea name='rs' property='bz' styleId="bz"
								style="width:99%" rows='5' />
						</logic:notEqual>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<logic:equal name="do" value="no" scope="request">
						<button type="button" class="button2" onclick="saveXsgw('xh-gwdm')">
							提 交 申 请
						</button>
					</logic:equal>
					<logic:equal name="do" value="yes" scope="request">
						<button type="button" class="button2" onclick="saveXsgw('xh-gwdm')">
							保 存 申 请
						</button>
					</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:notPresent name="printView">
						<button type="button" class="button2" onclick="expAppTab('rsT','勤工助学岗位申请表','')">
								打 印 预 览
					    </button>
				  </logic:notPresent>
				  <logic:present name="printView">
				  		<button type="button" class="button2" onclick="printView();">
								打 印 预 览
					    </button>
				  </logic:present>
				</div>
			</logic:notEmpty>
			<logic:present name="result">
				<logic:equal name="result" value="true">
					<script>
    alert("操作成功！");
    </script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
    alert("操作失败！");
    </script>
				</logic:equal>
				<logic:equal name="result" value="nopks">
					<script>
    alert("操作失败！必须是贫困生才能申请");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
