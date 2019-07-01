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
	function isNumber(s){
		var p = /^(-|\+)?\d+$/;
		return p.test(s); 
	    } 
		function jxjSqSavett(){
			var xmdm = document.getElementById('jxjdm').value;
			var xh = document.getElementById('xh').value;
			var sqly = document.getElementById('sqly').value;
			var jfqk = document.getElementById('jfqk').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var fdyshyj = document.getElementById('fdyyj').value;
			var lshshyj = document.getElementById('lshshyj').value;
			var bz = document.getElementById('bz').value;
			var yhkh = document.getElementById('yhkh').value;

			if(!isNumber(yhkh)){
				alert("银行卡号只能是数字!");
				return false;
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
	         	if(jfqk.length > 400){	         
	          		 alert("获奖情况不能大于200个字符！");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.length > 800){	         
	          		 alert("本人申请不能大于400个字符！");
	          		 return false;
	       		 }
	       	}
	       	if(xyshyj != null){
	         	if(xyshyj.length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />意见不能大于100个字符！");
	          		 return false;
	       		 }
	       	}
	    	if(lshshyj != null){
	         	if(lshshyj.length > 200){	         
	          		 alert("桑麻奖学评金审评意审见委员会不能大于100个字符！");
	          		 return false;
	       		 }
	       	}
			if(bz != null){
	         	if(bz.length > 200){	         
	          		 alert("备注不能大于100个字符！");
	          		 return false;
	       		 }
	       	}
			if(fdyshyj != null){
	         	if(fdyshyj.length > 200){	         
	          		 alert("辅导员不能大于100个字符！");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&doType=save&jxjtype=smjxj";
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
						if(document.getElementById("jxjlb").value!=data  || document.getElementById("jxjlb").value=="专项奖学金"){
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
	function heckistype(){
			var userlx = document.getElementById("userlx").value;
			if(userlx=="stu"){
				document.getElementById("xylxyj1").style.display = "none";
				document.getElementById("xylxyj2").style.display = "none";
				document.getElementById("xxlxyj1").style.display = "none";
				document.getElementById("xxlxyj2").style.display = "none";
			}else if(userlx=="xy"){
				document.getElementById("xxlxyj1").style.display = "none";
				document.getElementById("xxlxyj2").style.display = "none";
			}else if(userlx=="xx"){
				document.getElementById("xylxyj1").style.display = "none";
				document.getElementById("xylxyj2").style.display = "none";
			}
		}
	function pjpyjxjprint(){
		var jxjdm = document.getElementById("jxjdm").value;
		if(jxjdm == ""){
			alert("请选择奖学金名称");
			return false;
		}
		var xh = document.getElementById("xh").value;
		window.open('/xgxt/zjlgPjpy.do?method=jxjReport&pkValue='+xh+'&jxjcxzj='+jxjdm)
	}
	</script>
</head>

<body onload="heckistype();">
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：评奖评优 - 奖学金申请
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
				value="smjxj">
				
				<input id="userlx" type="hidden" value="<bean:write name="userType" scope="session" />"/>	
				
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

			<table class="tbstyle" width="90%">
				<tr>
					<td align="center" colspan="1" style="width: 120px">
						<font color="red">*</font>奖学金名称
					</td>
					<td colspan="2">
						<html:select property="jxjdm" style="width:180px"
							onchange="initjxjList();">
							<html:option value=""></html:option>
							<html:options collection="jzxjxmList" property="jxjdm"
								labelProperty="jxjmc" />
						</html:select>
					</td>
					<td colspan="1" scope="row">
						<div align="center">
							奖学金类别
						</div>
					</td>
					<td colspan="4">
						<html:text property="jxjlb" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="1">
							<font color="red">*</font>学号
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" colspan="1">
							<font color="red">*</font>学号
						</td>
						<td align="left" colspan="2">
							<input type="text" id="xh" name="xh"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="11%" colspan="1">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="4" scope="col">
						<html:text name="rs" property="xm" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							<font color="red">*</font>银行卡号
						</div>
					</td>
					<td colspan="2">
						<html:text property="yhkh" ></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							银行类型
						</div>
					</td>
					<td colspan="4">
						<html:text property="yhlx" ></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							学年
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="xn" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							学期
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="xq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="xb" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							出生年月
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="csrq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="zzmm" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							担任职务
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="zw" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							生源地
						</div>
					</td>
					<td colspan="2"><bean:write name="rs" property="jgs"/>
<%--						<html:text name="rs" property="jgs" readonly="true"></html:text>--%>
						<input id="jgs" name="jgs" readonly="readonly" type="text"" value="<bean:write name="rs" property="jg"/>"/>
					</td>
					<td>
						<div align="center">
							特长
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="ah" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							城市农村
						</div>
					</td>
					<td colspan="2">
						<html:radio property="nccz" value="农村">农村</html:radio>
						<html:radio property="nccz" value="城镇">城镇</html:radio>
					</td>
					<td>
						<div align="center">
						
						</div>
					</td>
					<td colspan="4">
				
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							综合测评名次
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="zhszcpcjpm" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
						专业名次
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="zhuanypm" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							申 报 等 级
						</div>
					</td>
					<td colspan="2">
						<html:text  property="sbdj"></html:text>
					</td>
					<td>
						<div align="center">
						第几次获得桑麻奖学金
						</div>
					</td>
					<td colspan="4">
						<html:text property="djchdjxj"></html:text>
					</td>
				</tr>
				<tr >
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>获<br>奖<br>情<br>况<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea property="jfqk" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							香港<br>桑麻<br>基金<br>会简<br>介
						</div>
					</td>
					<td colspan="8">
						香港桑麻基金会是著名实业家、爱国人士、香港特别行政区大紫荆勋章获得
						<br>者查济民先生所创建。查济民先生是我国纺织界耆宿，自三十年代初至今，
						<br>为发展纺织工业倾注了大量心力，成绩卓著。他以一位成功实业家的经验和
						<br>智慧，看到科技进步和培养人才是促进我国纺织工业开拓和发展的根本问
						<br>题，因此，他于一九九二年率先出资成立了桑麻基金会，并亲自担任基金会
						<br>主席，旨在缅怀我国纺织工业起源于桑麻，用以激励国内纺织界从业人员及
						<br>在培学子发扬爱国主义精神，重视科技，努力钻研，振兴中华纺织事业，基
						<br>金会先后在天津纺织工<bean:message key="lable.xsgzyxpzxy" />、东华大学（原中国纺织大学）、浙江理工大学
						<br>（原浙江丝绸工<bean:message key="lable.xsgzyxpzxy" />）和北京服装<bean:message key="lable.xsgzyxpzxy" />设立了桑麻奖学金，对奖励先进、发
						<br>掘人才提供了有利条件，促进了纺织教育事业的发展。
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="2">
						<div align="center">
							本<br>人<br>申<br>请
						</div>
					</td>
					<td colspan="8">
					（本人申请应包括：本人德智体诸方面的表现、担任社会工作及参加社会活动情况、申请奖学金的动机及努力目标，并简要说明家庭情况。）
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<html:textarea property="sqly" rows='6' style="width:100%"/>
					</td>
				</tr>
				<tr id="xylxyj1">
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>班<br>级<br>辅<br>导<br>员<br>意<br>见<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea property="fdyyj" rows='6' style="width:100%" />
					</td>
				</tr>
				
				<tr id="xylxyj2">
					<td colspan="1" rowspan="1">
						<div align="center">
							<br><bean:message key="lable.xsgzyxpzxy" /><br>审核<br>意见<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea property="xyshyj" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr id="xxlxyj1">
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>桑麻<br>奖学<br>评金<br>审评<br>意审<br>见委<br>员会<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea property="lshshyj" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr id="xxlxyj2">
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>备<br>注<br>
						</div>
					</td>
					<td colspan="8">
					<html:textarea property="bz" rows='6' style="width:100%;display: none"  />
					1.本表一式二份，表中填写内容要求电脑打印；<br>
					2.学校、院、系班级不可缩写。
					
					</td>
				</tr>
			</table>
	<div class="buttontool" align="center">
				<button type="button" class="button2" id="buttonSave" onclick="jxjSqSavett();">
					提 交 申 请
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2" onclick="pjpyjxjprint()">
						打 印 报 表
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_cjprint" onclick="showOpenWindow('pjpy_shcbys_cjprint.do?xh='+document.getElementById('xh').value,700,600)">
						学生成绩单
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_cfqk" onclick="showOpenWindow('zjlgPjpy.do?method=jxjcfqk&xh='+document.getElementById('xh').value+'&xn='+document.getElementById('xn').value+'&xq='+document.getElementById('xq').value,700,600)">
						处罚情况
					</button>
			</div>
			<logic:equal name="done" value="true">
				<script>
			          alert("申请成功！");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("申请失败！");
			        </script>
			</logic:equal>
			<logic:equal name="isExist" value="no">
				<script>
			        alert("该奖学金已申请,且已通过相关部门审核\n或正在审核中,不能再次申请！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf1">
			<script>
			        alert("智育成绩递增不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf2">
			<script>
			        alert("智育分不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf3">
			<script>
			        alert("德育分不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf4">
			<script>
			        alert("智育分排名不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf5">
			<script>
			        alert("德育成绩排名不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf6">
			<script>
			        alert("外语成绩不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf7">
			<script>
			        alert("综合素质分排名不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf8">
			<script>
			        alert("综合素质分不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf9">
			<script>
			        alert("你不是毕业生不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf9">
			<script>
			        alert("该学生不存在综合素质测评分！");			    
			        </script>
			</logic:equal>
			<logic:equal name="jxjcftj" value="yes">
			<script>
			        alert("该奖学金已申请");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="jxjjd1">
			<script>
			        alert("励志单项奖学金和优秀学生奖学金不能兼得");			    
			        </script>
			</logic:equal>
			<logic:equal name="isczcpf" value="isczcpf">
			<script>
			        alert("找不到该学生的综合素质测评成绩，不能申请");			    
			        </script>
			</logic:equal>
			<logic:equal name="cpzisnull" value="cpzisnull">
			<script>
			        alert("该学生所在参评组为空，不能申请");			    
			        </script>
			</logic:equal>
		</html:form>
</body>
</html:html>
