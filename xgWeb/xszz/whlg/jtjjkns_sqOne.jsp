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
<html:html>

<head>
	<base target="_self" />
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function checkStuInfo(){
			var xh = document.getElementById('xh').value;
			if(xh == ""){
				alert("学号不能为空!");
				return ;
			}
			refreshForm("whlg_xszz.do?method=jtjjknssq&doType=add");
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "";
			document.forms[0].submit();
		}
		
		function getYj(str,type){
			if(str == "xy"){
				if(type){
					document.getElementById("xyyj_pc").style.display = "";
				}else{
					document.getElementById("xyyj_pc").style.display = "none";
				}	
			}else if(str == "xx"){
				if(type){
					document.getElementById("xxyj_pc").style.display = "";
				}else{
					document.getElementById("xxyj_pc").style.display = "none";
				}	
			}
		}
		
		/**判断学生是否有申请过*/
		function checkStuHaveSqRecord(){
			var userOnLine = document.getElementById("userOnLine").value;
			if(userOnLine == "student"){
				alert("你已经申请过，是否继续申请");
			}
		}
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m"> 
		当前所在位置：学生资助 - 困难生申请 - 困难生申请 
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal>
	<html:form action="/whlg_xszz.do?method=jtjjknssq" method="post">
			<input type="hidden" id="url" name="url"
				value="/whlg_xszz.do?method=jtjjknssq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc-nj-zzmmmc-sfzh" />
			<input type="hidden" id="userOnLine" name="userOnLine"
				value="<bean:write name="userOnLine" scope="session"/>" />	
			<table class="tbstyle" width="100%">
			<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" style="width:10%">
							<font color="red">*</font>学号
						</td>
						<td align="left" >
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" style="width:10%">
							<font color="red">*</font>学号
						</td>
						<td align="left">
							<input type="text" id="xh" name="xh"
								value="<bean:write name='rs' property="xh" />" readonly="readonly"/>
						</td>
					</logic:equal>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td>
						<input type="text" id="zzmmmc" name="zzmmmc"				
							value="<bean:write name='rs' property="zzmmmc" />" readonly="readonly"/>					
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<input type="text" id="xb" name="xb" 
							value="<bean:write name='rs' property="xb" />" readonly="readonly"/>
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh" 
							value="<bean:write name='rs' property="sfzh" />" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc"
							value="<bean:write name='rs' property="xymc" />" readonly="readonly"/>
					</td>
					<td scope="row">
						<div align="center">
							工行联名卡号
						</div>
					</td>
					<td>
						<input type="text" id="ghlmkh" name="ghlmkh"
							value="<bean:write name='rs' property="ghlmkh" />">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							姓名
						</div>
					</td>
					<td  scope="col">
						<input type="text" id="xm" name="xm"
							value="<bean:write name='rs' property="xm" />" readonly="readonly"/>
					</td>
					<td>
						<div align="center">
							家庭人均年收入(元)
						</div>
					</td>
					<td>
						<input type="text" id="jtrjnsr" name="jtrjnsr"	
							value="<bean:write name='rs' property="jtrjnsr" />">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc"
							
							value="<bean:write name='rs' property="bjmc" />" readonly="readonly"/>
					</td>
					<td>
						<div align="center">
							在校联系电话
						</div>
					</td>
					<td>
						<input type="text" id="zxlxdh" name="zxlxdh"
							value="<bean:write name='rs' property="zxlxdh" />">
					</td>
				</tr>
				<tr style="height:150px">
					<td rowspan="10">
						<div align="center">学<br>生<br>陈<br>述<br>申<br>请<br>认<br>定<br>理<br>由</div>
					</td>
					<td rowspan="10" colspan="3">
						<div style="width:90%">(一)  必要条件：<br><br>
							（1）家庭可提供的月均生活费低于武汉市最低生活保障线 &nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_shbzx">
							<logic:match value="是" name="rs" property="is_shbzx">
								<input type="radio" name="is_shbzx" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="is_shbzx" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:notMatch value="是" name="rs" property="is_shbzx">
								<input type="radio" name="is_shbzx" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_shbzx" value="否" checked>&nbsp;&nbsp;否
							</logic:notMatch>
							</logic:notEmpty>
							<logic:empty name="rs" property="is_shbzx">
								<input type="radio" name="is_shbzx" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_shbzx" value="否" checked>&nbsp;&nbsp;否
							</logic:empty>
							<br>
							（2）在校消费情况：生活简朴，无高档消费品&nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_shjp">
							<logic:match value="是" name="rs" property="is_shjp">
								<input type="radio" name="is_shjp" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="is_shjp" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:notMatch value="是" name="rs" property="is_shjp">
								<input type="radio" name="is_shjp" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_shjp" value="否" checked>&nbsp;&nbsp;否
							</logic:notMatch>
							</logic:notEmpty>
							<logic:empty name="rs" property="is_shjp">
								<input type="radio" name="is_shjp" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_shjp" value="否" checked>&nbsp;&nbsp;否
							</logic:empty>
						</div><br>
						
						<div style="width:90%">（二）重要参考条件：<br>
							（3）孤儿、烈士子女&nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_lszn">
							<logic:match value="是" name="rs" property="is_lszn">
								<input type="radio" name="is_lszn" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="is_lszn" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:notMatch value="是" name="rs" property="is_lszn">
								<input type="radio" name="is_lszn" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_lszn" value="否" checked>&nbsp;&nbsp;否
							</logic:notMatch>
							</logic:notEmpty>
							<logic:empty name="rs" property="is_lszn">
								<input type="radio" name="is_lszn" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_lszn" value="否" checked>&nbsp;&nbsp;否
							</logic:empty>
							<br>
							（4）身体残疾或重大疾病&nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_stcj">
							<logic:match value="是" name="rs" property="is_stcj">
								<input type="radio" name="is_stcj" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="is_stcj" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:notMatch value="是" name="rs" property="is_stcj">
								<input type="radio" name="is_stcj" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_stcj" value="否" checked>&nbsp;&nbsp;否
							</logic:notMatch>
							</logic:notEmpty>
							<logic:empty name="rs" property="is_stcj">
								<input type="radio" name="is_stcj" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_stcj" value="否" checked>&nbsp;&nbsp;否
							</logic:empty>
							<br>
							（5）当年家庭遭受严重自然灾害或重大变故&nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_zrzh">
							<logic:match value="是" name="rs" property="is_zrzh">
								<input type="radio" name="is_zrzh" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="is_zrzh" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:notMatch value="是" name="rs" property="is_zrzh">
								<input type="radio" name="is_zrzh" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_zrzh" value="否" checked>&nbsp;&nbsp;否
							</logic:notMatch></logic:notEmpty>
							<logic:empty name="rs" property="is_zrzh">
								<input type="radio" name="is_zrzh" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_zrzh" value="否" checked>&nbsp;&nbsp;否
							</logic:empty>
						</div><br>
						<div style="width:90%">（三）一般参考条件：<br>
							（6）偏远山区或农村&nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_pysq">
							<logic:match value="是" name="rs" property="is_pysq">
								<input type="radio" name="is_pysq" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="is_pysq" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:notMatch value="是" name="rs" property="is_pysq">
								<input type="radio" name="is_pysq" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_pysq" value="否" checked>&nbsp;&nbsp;否
							</logic:notMatch></logic:notEmpty>
							<logic:empty name="rs" property="is_pysq">
								<input type="radio" name="is_pysq" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_pysq" value="否" checked>&nbsp;&nbsp;否
							</logic:empty>
							<br>
							（7）单亲&nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_dq">
							<logic:match value="是" name="rs" property="is_dq">
								<input type="radio" name="is_dq" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="is_dq" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:notMatch value="是" name="rs" property="is_dq">
								<input type="radio" name="is_dq" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_dq" value="否" checked>&nbsp;&nbsp;否
							</logic:notMatch></logic:notEmpty>
							<logic:empty name="rs" property="is_dq">
								<input type="radio" name="is_dq" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_dq" value="否" checked>&nbsp;&nbsp;否
							</logic:empty>
							<br>
							（8）家庭多子女上学&nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_dznsx">
							<logic:match value="是" name="rs" property="is_dznsx">
								<input type="radio" name="is_dznsx" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="is_dznsx" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:notMatch value="是" name="rs" property="is_dznsx">
								<input type="radio" name="is_dznsx" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_dznsx" value="否" checked>&nbsp;&nbsp;否
							</logic:notMatch></logic:notEmpty>
							<logic:empty name="rs" property="is_dznsx">
								<input type="radio" name="is_dznsx" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_dznsx" value="否" checked>&nbsp;&nbsp;否
							</logic:empty>
							<br>
							（9）父母无劳动能力或父母双下岗，且无固定收入&nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_fmxg">
							<logic:match value="是" name="rs" property="is_fmxg">
								<input type="radio" name="is_fmxg" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="is_fmxg" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:notMatch value="是" name="rs" property="is_fmxg">
								<input type="radio" name="is_fmxg" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_fmxg" value="否" checked>&nbsp;&nbsp;否
							</logic:notMatch></logic:notEmpty>
							<logic:empty name="rs" property="is_fmxg">
								<input type="radio" name="is_fmxg" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="is_fmxg" value="否" checked>&nbsp;&nbsp;否
							</logic:empty>
						</div><br>
						<div>其它致困因素：<font color="blue">限100个字符</font><br>
							<html:textarea property="is_qtys" name="rs" rows="5" cols="82"></html:textarea>
						</div>
					</td>
				</tr>
				</tbody>
				<tbody id="tableadddel">
				<tr style="height:70px">
					<td rowspan="2">
						<div align="center">民<br>主<br>评<br>议</div>
					</td>
					<td colspan="3" style="height:20px">
						推荐档次:&nbsp;&nbsp;
							<input type="radio" name="mzpy_tjdc" value="A">&nbsp;&nbsp;A.家庭经济困难
							<input type="radio" name="mzpy_tjdc" value="B">&nbsp;&nbsp;B.家庭经济特殊困难
							<input type="radio" name="mzpy_tjdc" value="C">&nbsp;&nbsp;C.家庭经济不困难
							<input type="hidden" name="mzpy_tjdc" id="mzpy_tjdc_check" value="<bean:write name="rs" property="mzpy_tjdc"/>"/>
							<script type="text/javascript">
								var mzpy_tjdc_var = document.getElementById("mzpy_tjdc_check").value;
								if(!(mzpy_tjdc_var == null || mzpy_tjdc_var == "")){
									var mzpy_tjdc_group = document.getElementsByName("mzpy_tjdc");
									for(var i=0;i<mzpy_tjdc_group.length;i++){
										if(mzpy_tjdc_var == mzpy_tjdc_group[i].value){
											mzpy_tjdc_group[i].checked = true;
										}
									}
								}
							</script>
					</td>
					</tr>
					<tr>
					<td colspan="3">
						<div>陈述理由&nbsp;&nbsp;<font color="blue">限2000个字符</font></div>
					<html:textarea property="mzpy_csly" name="rs" rows="8" cols="82"></html:textarea>
					</td>
					</tr>
				</tbody>
				<tbody>
					<tr>
					<td rowspan="4">
						<div align="center">认<br>定<br>决<br>定</div>
					</td>	
					<td colspan="3">
					<div align="center"><font size="4"><bean:message key="lable.xsgzyxpzxy" />意见: </font></div>
					</td>
				</tr>
				<tr>
					<td>
					经评议小组推荐、本院（系）认真审核后：<br><br>
						<logic:notEmpty name="rs" property="rdjd_xyyj">
							<logic:match value="是" name="rs" property="rdjd_xyyj">
								<input type="radio" name="rdjd_xyyj" value="是" onclick="getYj('xy',false)" checked="checked">&nbsp;&nbsp;同意评议小组意见<br>
							    <input type="radio" name="rdjd_xyyj" value="否" onclick="getYj('xy',true)">&nbsp;&nbsp;不同意评议小组意见
							</logic:match>
							<logic:notMatch value="是" name="rs" property="rdjd_xyyj">
								<input type="radio" name="rdjd_xyyj" value="是" onclick="getYj('xy',false)">&nbsp;&nbsp;同意评议小组意见<br>
							    <input type="radio" name="rdjd_xyyj" value="否" onclick="getYj('xy',true)" checked="checked">&nbsp;&nbsp;不同意评议小组意见
							</logic:notMatch></logic:notEmpty>
							<logic:empty name="rs" property="rdjd_xyyj">
								<input type="radio" name="rdjd_xyyj" value="是" onclick="getYj('xy',false)">&nbsp;&nbsp;同意评议小组意见<br>
							    <input type="radio" name="rdjd_xyyj" value="否" onclick="getYj('xy',true)">&nbsp;&nbsp;不同意评议小组意见
							</logic:empty>
							<input type="hidden" name="rdjd_xyyjHidden" value="<bean:write name="rs" property="rdjd_xyyj"/>" id="rdjd_xyyjHidden"/>						
					</td>
					<td colspan="2">
					<div style="display:none" id="xyyj_pc">
						不同意的理由，调整为&nbsp;&nbsp;<font color="blue">限200个字符</font>
						<html:textarea property="rdjd_xyyj_pc" name="rs" rows="5" cols="40"></html:textarea>
						<script type="text/javascript">
							var rdjd_xyyjHidden = document.getElementById("rdjd_xyyjHidden").value;
							if(rdjd_xyyjHidden == "否"){
								document.getElementById("xyyj_pc").style.display = "";
							}
						</script>
					</div>
					</td>		
				</tr>
				<tr>
					<td colspan="3">
						<div align="center"><font size="4">学校学生资助管理机构意见: </font></div>
					</td>
				</tr>
				<tr>
					<td>经学生所在院（系）提请，本机构认真核实：<br><br>
						<logic:notEmpty name="rs" property="rdjd_xxyj">
							<logic:match value="是" name="rs" property="rdjd_xxyj">
								<input type="radio" name="rdjd_xxyj" value="是" onclick="getYj('xx',false)" checked="checked">&nbsp;&nbsp;同意工作组和评议小组意见<br>
							    <input type="radio" name="rdjd_xxyj" value="否" onclick="getYj('xx',true)">&nbsp;&nbsp;不同意工作组和评议小组意见
							</logic:match>
							<logic:notMatch value="是" name="rs" property="rdjd_xxyj">
								<input type="radio" name="rdjd_xxyj" value="是" onclick="getYj('xx',false)">&nbsp;&nbsp;同意工作组和评议小组意见<br>
							    <input type="radio" name="rdjd_xxyj" value="否" onclick="getYj('xx',true)" checked="checked">&nbsp;&nbsp;不同意工作组和评议小组意见
							</logic:notMatch></logic:notEmpty>
							<logic:empty name="rs" property="rdjd_xxyj">
								<input type="radio" name="rdjd_xxyj" value="是" onclick="getYj('xx',false)">&nbsp;&nbsp;同意工作组和评议小组意见<br>
							    <input type="radio" name="rdjd_xxyj" value="否" onclick="getYj('xx',true)">&nbsp;&nbsp;不同意工作组和评议小组意见
							</logic:empty>
							<input type="hidden" name="rdjd_xxyjHidden" value="<bean:write name="rs" property="rdjd_xxyj"/>" id="rdjd_xxyjHidden"/>
							
					</td>
					<td colspan="2">
					<div style="display:none" id="xxyj_pc">
						不同意的理由，调整为&nbsp;&nbsp;<font color="blue">限200个字符</font>
						<html:textarea property="rdjd_xxyj_pc" name="rs" rows="5" cols="40"></html:textarea>
						<script type="text/javascript">
								var rdjd_xxyjHidden = document.getElementById("rdjd_xxyjHidden").value;
								if(rdjd_xxyjHidden == "否"){
									document.getElementById("xxyj_pc").style.display = "";
								}	
						</script>
					</div>
					</td>
					
				</tr>
				</tbody>
				<tbody>
					<tr>
						<td colspan="4">
						<DIV align="center">
							<font color="red">注：此表填写完整后和《学生及家庭情况调查表》一并交评议小组评议认定</font>
						</DIV>
						</td>
					</tr>
				</tbody>
			</table>
			
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<logic:notEqual value="view" name="rs" property="doType">
				<button type="button" class="button2" onClick="checkStuInfo();" style="width:80px">
					提交申请
				</button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onClick="toPrintOut();" style="width:80px">
					打 印
				</button>&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notEqual>
				<logic:notEqual value="student" scope="session" name="userOnLine">
					<button type="button" class="button2" onClick="Close();" style="width:80px">
						关闭
					</button>
				</logic:notEqual>
			</div>	
		</html:form>
	</body>
	<logic:present name="ok">
		<logic:match value="ok" name="ok">
			<script language="javascript">
	   			alert("保存成功！");
	   			if(window.dialogArguments){
	   				dialogArgumentsQueryChick();
	   				Close();
	   			}
	   		
	        </script>
		</logic:match>
		<logic:match value="no" name="ok">
			<script language="javascript">
	        	alert("保存失败！");
	        </script>
		</logic:match>
	</logic:present>
	<logic:present name="have">
		<logic:match value="have" name="have">
			<script language="javascript">
	         	alert("已通过审核，不能申请！");
	         </script>
		</logic:match>
	</logic:present>
</html:html>
