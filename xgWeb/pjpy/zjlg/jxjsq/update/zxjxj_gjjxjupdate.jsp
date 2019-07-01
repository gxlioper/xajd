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
<base target="_self" />
<head>
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
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript">
		function jxjSqSavett(){
			var xmdm = null;
			var xh = null;
			var jfqk = null;
			var xyshyj = null;
			var xxshyj = null;
			var sqly = null;
			var fdyyj = null;


			if(document.getElementById('jxjdm')){
			 xmdm = document.getElementById('jxjdm').value;
			}
			if(document.getElementById('xh')){
			 xh = document.getElementById('xh').value;
			}
			if(document.getElementById('jfqk')){
			 jfqk = document.getElementById('jfqk').value;
			}
			if(document.getElementById('xyshyj')){
			 xyshyj = document.getElementById('xyshyj').value;
			}
			if(document.getElementById('xxshyj')){
			 xxshyj = document.getElementById('xxshyj').value;
			}
			if(document.getElementById('sqly')){
			 sqly = document.getElementById('sqly').value;
			}
			if(document.getElementById('fdyyj')){
			 fdyyj = document.getElementById('fdyyj').value;
			}
			
			if(xmdm == null || xmdm == ""){
				alert("请选择要申请的奖助学金!");
				return false;
			}
			if(xh == null || xh == ""){
				alert("学号不能为空!");
				return false;
			}
			if(jfqk != null){
	         	if(jfqk.length > 200){	         
	          		 alert("获奖情况不能大于400个字符！");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.length > 800){	         
	          		 alert("申请理由不能大于400个字符！");
	          		 return false;
	       		 }
	       	}
	       	if(xyshyj != null){
	         	if(xyshyj.length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />意见不能大于100个字符！");
	          		 return false;
	       		 }
	       	}
	    	if(fdyyj != null){
	         	if(fdyyj.length > 200){	         
	          		 alert("年级（专业）推荐意见不能大于100个字符！");
	          		 return false;
	       		 }
	       	}
			if(xxshyj != null){
	         	if(xxshyj.length > 200){	         
	          		 alert("学校意见不能大于100个字符！");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&doType=save&jxjtype=gjjxj&update=update";
			document.forms[0].submit();
		}
		
		function chang(){
			
			alert('tt');
			return false;
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmsq";
			document.forms[0].submit();
		}
		
		function toPrintOut(){
			var xmdm = document.getElementById('xmdm').value;
			if(xmdm == null || xmdm == ""){
				alert("请选择要下载的奖助学金!");
				return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmmbxz&xmdm="+xmdm;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		function initjxjList(){
			var jxjdm = document.getElementById("jxjdm").value;
			GetListData.getJxjdm(jxjdm,function initTjList(data){
					if (data != null) {
						if(document.getElementById("jxjlb").value!=data || document.getElementById("jxjlb").value=="专项奖学金"){
							document.getElementById("jxjlb").value=data;
							
							if(data=="突出贡献奖学金"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=tcgxjxj";
								document.forms[0].submit();
							}else if(data=="优秀学生奖学金"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=yxxsjxj";
								document.forms[0].submit();
							}else if(data=="社会工作奖学金"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=shgzjxj";
								document.forms[0].submit();
							}else if(data=="科研创新奖学金"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=kycxjxj";
								document.forms[0].submit();
							}else if(data=="单项奖学金"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=dxjxj";
								document.forms[0].submit();
							}else if(data=="优秀毕业生奖学金"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=yxbysjxj";
								document.forms[0].submit();
							}else if(data=="专项奖学金"){
								var jxjlb;
								if(jxjdm=="00071"){
									jxjlb="fzzgjxj";
								}else if(jxjdm=="00072"){
									jxjlb="gjjxj";
								}else if(jxjdm=="00073"){
									jxjlb="hdjxj";
								}else if(jxjdm=="00074"){
									jxjlb="smjxj";
								}else{
										alert("选择错误，请重新选择");
								}
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype="+jxjlb;
								document.forms[0].submit();
							}
						}else{
						}
					}else{
						showMsgWin("有错误出现：远程数据读取失败！");
					}
				});
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：评奖评优 - 奖学金 - 国家奖学金
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内或不符合申请条件！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="/zjlgPjpy" method="post">
			
			<input type="hidden" id="url" name="url"
				value="/zjlgPjpy.do?method=yxxsjxjsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xn" name="xn"
				value="<bean:write name="rs" property="xn" />">
			<input type="hidden" id="xq" name="xq"
				value="<bean:write name="rs" property="xq" />">
			<input type="hidden" id="jxjmc" name="jxjmc"
				value="gjjxj">
				
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
			<logic:present name="have">
				<logic:match value="have" name="have">
					<script language="javascript">
	         			alert("已通过审核，不能申请！");
	         		</script>
				</logic:match>
			</logic:present>
	<logic:equal value="update" name="forwardtype">
			<table class="tbstyle" width="100%">
				<tr>
					<td align="center" colspan="1" style="width: 80px">
						<font color="red">*</font>奖学金名称
					</td>
					<td colspan="2">
						<html:select name="rs" property="jxjdm" style="width:180px;display:none"
							onchange="initjxjList();">
							<html:option value=""></html:option>
							<html:options collection="jzxjxmList" property="jxjdm"
								labelProperty="jxjmc" />
						</html:select>
						<html:text name="rs" property="jxjmc" readonly="true"></html:text>
					</td>
					<td colspan="1" scope="row">
						<div align="center">
							奖学金类别
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="jxjlbmc" readonly="true"></html:text>
						<html:text name="rs" property="jxjlb" readonly="true" style="display: none"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row" rowspan="5">
						<div align="center">
							<b>基<br>本<br>情<br>况</b>
						</div>
					</td>
						<td align="center" colspan="1" style="width: 100px">
							<font color="red">*</font>学号
						</td>
						<td align="left" colspan="1">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</td>
					<td width="11%" colspan="1">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="1" scope="col">
						<html:text name="rs" property="xm" readonly="true"></html:text>
					</td>
					<td colspan="1" scope="row" style="width: 100px">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="xb" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							民族 
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="mz" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							出生年月
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="csrq" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							入学时间
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="rxrq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="5">
						<html:text name="rs" property="sfzh" readonly="true" style="width: 100%"></html:text>
					</td>
				</tr>
				
				
				
				
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="zzmm" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							联系电话 
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="lxdh" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							银行卡号
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="yhkh" ></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							银行类型
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="yhlx" ></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<b>学<br>习<br>等<br>情<br>况</b>
						</div>
					</td>
					<td colspan="8">
<%--						本学年必修课程     门，其中，优秀     门，良好     门<br>--%>
<%----%>
<%--						成绩排名（专业或年级）：              （名次/总人数）<br>--%>
<%----%>
<%--						综合考评成绩     分，排名            （名次/总人数）<br>--%>
						<html:textarea name="rs" property="xxjl" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<b><br>获<br>奖<br>情<br>况<br></b>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="jfqk" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="2">
						<div align="center">
							<b><br>申<br>请<br>理<br>由<br></b>
						</div>
					</td>
					<td colspan="8">
						（全面反映德智体美情况）
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<html:textarea name="rs" property="sqly" rows='6' style="width:100%" />
					</td>
				</tr>
				
			</table>
	<div class="buttontool" align="center">
				<button type="button" class="button2" id="buttonSave" onclick="jxjSqSavett();">
					提 交 申 请
				</button>
				&nbsp;&nbsp;
			</div>
			</logic:equal>
				<logic:equal value="view" name="forwardtype">
				<table class="tbstyle" width="100%">
				<tr>
					<td align="center" colspan="1" style="width: 80px">
						<font color="red">*</font>奖学金名称
					</td>
					<td colspan="2">
						<html:select name="rs" property="jxjdm" style="width:180px;display:none"
							onchange="initjxjList();">
							<html:option value=""></html:option>
							<html:options collection="jzxjxmList" property="jxjdm"
								labelProperty="jxjmc" />
						</html:select>
						<html:text name="rs" property="jxjmc" readonly="true"></html:text>
					</td>
					<td colspan="1" scope="row">
						<div align="center">
							奖学金类别
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="jxjlbmc" readonly="true"></html:text>
						<html:text name="rs" property="jxjlb" readonly="true" style="display: none"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row" rowspan="5">
						<div align="center">
							<b>基<br>本<br>情<br>况</b>
						</div>
					</td>
						<td align="center" colspan="1" style="width: 100px">
							<font color="red">*</font>学号
						</td>
						<td align="left" colspan="1">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</td>
					<td width="11%" colspan="1">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="1" scope="col">
						<html:text name="rs" property="xm" readonly="true"></html:text>
					</td>
					<td colspan="1" scope="row" style="width: 100px">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="xb" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							民族 
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="mz" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							出生年月
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="csrq" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							入学时间
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="rxrq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="5">
						<html:text name="rs" property="sfzh" readonly="true" style="width: 100%"></html:text>
					</td>
				</tr>
				
				
				
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							银行卡号
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="yhkh" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							银行类型
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="yhlx" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="zzmm" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							联系电话 
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="lxdh" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<b>学<br>习<br>等<br>情<br>况</b>
						</div>
					</td>
					<td colspan="8">
<%--						本学年必修课程     门，其中，优秀     门，良好     门<br>--%>
<%----%>
<%--						成绩排名（专业或年级）：              （名次/总人数）<br>--%>
<%----%>
<%--						综合考评成绩     分，排名            （名次/总人数）<br>--%>
						<html:textarea name="rs" property="xxjl" rows='6' style="width:100%"  readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<b><br>获<br>奖<br>情<br>况<br></b>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="jfqk" rows='6' style="width:100%" readonly="true" />
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="2">
						<div align="center">
							<b><br>申<br>请<br>理<br>由<br></b>
						</div>
					</td>
					<td colspan="8">
						（全面反映德智体美情况）
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<html:textarea name="rs" property="sqly" rows='6' style="width:100%" readonly="true" />
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<b><br>年级<br>（专业）<br>推荐意见<br></b>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="fdyyj" rows='6' style="width:100%" readonly="true" />
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<b><br>学<br>院<br>意<br>见<br></b>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="xyshyj" rows='6' style="width:100%" readonly="true" />
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<b><br>学<br>校<br>意<br>见<br></b>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="xxshyj" rows='6' style="width:100%"  readonly="true"/>
					</td>
				</tr>
			</table>
				</logic:equal>
			<logic:equal name="done" value="true">
				<script>
			          alert("修改成功！");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("修改失败！");
			        </script>
			</logic:equal>
			<logic:equal name="isExist" value="no">
				<script>
			        alert("该荣誉称号已申请,且已通过相关部门审核\n或正在审核中,不能再次申请！");			    
			        </script>
			</logic:equal>

		</html:form>
</body>
</html:html>
