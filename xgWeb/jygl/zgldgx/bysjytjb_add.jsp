<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
		<title>就业管理信息系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">	
		
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	function add(){
		  var xsxh = document.getElementById("xsxh").value;   
	      var name = document.getElementById("name").value; 
	      var id = document.getElementById("id").value; 
	      var xb = document.getElementById("xb").value;
	      var bmlxdh = document.getElementById("bmlxdh").value;
	      if(xsxh==""){
	      alert("学号不能为空！");
	      document.getElementById("xsxh").focus();
	      return false;
	      }
	      if(name==""){
	      alert("姓名不能为空！");
	      document.getElementById("name").focus();
	      return false;
	      }
	      if(id==""){
	      alert("身份证号码不能为空！");
	      document.getElementById("id").focus();
	      return false;
	      }
	      if(xb==""){
	      alert("性别不能为空！");
	      document.getElementById("xb").focus();
	      return false;
	      }
	      if(bmlxdh != ""){
	      if(!isNumber(bmlxdh)){
		      alert("电话号码只能填写数字！");
		      return false;
		  }
	}
	      
	      	BatAlert.showTips('正在提交，请等待...');
			 document.forms[0].action = "bysjytjb.do?act=add&doType=add";
			 document.forms[0].submit();
    }
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    }  
	function initZyList(){
		var xydm = "";
		var query = "";
		var userName = "";
		var isFdy = "false";
		if($("isFdy")){isFdy = $("isFdy").value};
		if($("xy")){xydm = $("xy").value};	
		if($("userName")){userName = $("userName").value};
			if(xydm == null || xydm == ""){
				xydm = "%";
			} else{
				xydm = "%" + xydm +"%";
			}
			query = xydm;	
			if($("isBzr")){isBzr = $("isBzr").value};
			GetListData.getZyList(query,userName,isFdy,isBzr,function initTjList(data){
				if (data != null && typeof data == 'object') {
					var objId = data[0].dm;
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"dm","mc");			
						$(objId).options[0].value = "";
						if(objId){
						if($(objId).value != "" && $(objId).value != null){
							alert($(objId).tagName);
							for(var i = 0;i < $(objId).options.length; i++){
								if($(objId).options[i].value == $(objId).value){
									$(objId).options[i].selected = true;
									return true;
								}
							}
						}
						}
					}
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}
			});
	}
	function isxxtjyj(){
		var isstu = document.getElementById("isstu").value;
		if(isstu=="stu"){
		document.getElementById("pyfs").disabled="true";
		document.getElementById("jyfw").disabled="true";
		document.getElementById("yxyj").disabled="true";
		document.getElementById("jybmyj").disabled="true";
		document.getElementById("lxbm").disabled="true";
		document.getElementById("bmlxr").disabled="true";
		document.getElementById("bmlxdh").disabled="true";
		}else if(isstu == "xy"){
			document.getElementById("jybmyj").disabled="true";
			document.getElementById("lxbm").disabled="true";
			document.getElementById("bmlxr").disabled="true";
			document.getElementById("bmlxdh").disabled="true";
		}else if(isstu == "xx" || isstu =="admin"){
			document.getElementById("pyfs").disabled="true";
			document.getElementById("jyfw").disabled="true";
			document.getElementById("yxyj").disabled="true";
		}
}
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="isxxtjyj();">
		<html:form action="/bysjytjb.do" method="post">
			<table width="100%" class="tbstyle" id="grjl">
				<thead>
					<tr>
						<td colspan="8" align="center">
							2009届北京地区普通高校毕业生就业推荐表
						</td>
					</tr>
				</thead>
				<tr>
					<td rowspan="7" align="center" width="30">
						<b>个<br>人<br>信<br>息</b>
					</td>
					<td align="center" style="width: 85px">
						学号
						<input id="isstu" value="<bean:write name="userType" scope="session"/>" type="hidden"/>
					</td>
					<logic:equal value="stu" name="userType" scope="session">
					<td width="180">
					<html:text name='rs1' property="xsxh" styleId="xsxh" readonly="true" />
					</td>
					</logic:equal>
					<logic:notEqual value="stu" name="userType" scope="session">
					<td width="180">
						<html:text name='rs1' property="xsxh" styleId="xsxh" readonly="true" />
								<button onclick="showTopWin('/xgxt/byqxTurnInfo.do?bysjytjb=bysjytjb',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
					</td>
					</logic:notEqual>
					<td align="center" width="70">
						姓名
					</td>
					<td width="150">
						<html:text name="rs1" property="name" style="100%" readonly="true" />
					</td>
					<td align="center" width="70">
						性别
					</td>
					<td width="150">
						<html:text name="rs1" property="xb" style="100%" readonly="true" />
					</td>
					<td rowspan="6" align="center" width="150">
						照片
					</td>
				</tr>
				<tr>
					<td align="center" width="70">
						身份证号
					</td>
					<td width="150">
						<html:text name="rs1" property="id" style="100%" readonly="true" />
					</td>
					<td align="center">
						出生<br>日期
					</td>
					<td>
						<html:text name="rs1" property="csrq" onclick="return showCalendar('csrq','y-mm-dd');"  style="100%" />
					</td>
					<td align="center">
						政治面目
					</td>
					<td>
						<html:text name="rs1" property="zzmm" style="100%" />
					</td>
				</tr>
				<tr>
					<td align="center">
						生源地区
					</td>
					<td>
						<html:text name="rs1" property="sydq" style="100%" readonly="true" />
					</td>
					<td align="center">
						电话
					</td>
					<td>
						<html:text name="rs1" property="lxdh" style="100%" />
					</td>
					<td align="center">
						手机
					</td>
					<td>
						<html:text name="rs1" property="sjhm" style="100%"/>
					</td>
				</tr>
				<tr>
					<td align="center" >
						通讯地址
					</td>
					<td colspan="3">
						<html:text name="rs1" property="txdz" style="width: 100%" />
					</td>
					<td align="center">
						邮编号码
					</td>
					<td >
						<html:text name="rs1" property="yzbm" style="100%" />
					</td>
					
				</tr>
				<tr>
					<td align="center" >
						毕业学校
					</td>
					<td colspan="3">
						<html:text name="rs1" property="byxx" style="width: 100%" readonly="true" />
					</td>
					<td align="center">
						学历
					</td>
					<td>
						<html:text name="rs1" property="xlmc" style="100%" readonly="true" />
					</td>
					
				</tr>
				<tr>
					<td align="center" >
						专业
					</td>
					<td colspan="3">
						<html:text name="rs1" property="zymc" style="width: 100%" readonly="true" />
					</td>
					<td align="center">
						学制
					</td>
					<td >
						<html:text name="rs1" property="xz" style="100%" readonly="true" />
					</td>
					
				</tr>
				<tr>
					<td align="center" >
						毕业时间
					</td>
					<td>
						<html:text name="rs1" property="bysj" onclick="return showCalendar('bysj','y-mm-dd');"  style="width: 100%"/>
					</td>
					<td align="center">
						奖惩<br>情况
					</td>
					<td colspan="4">
						<html:text name="rs1" property="jlqk" style="width: 100%"/>
					</td>
				</tr>
				<tr>
					<td rowspan="3" align="center">
						<b>社<br>会<br>实<br>践</b>
					</td>
					<td align="right">
						1：
					</td>
					<td colspan="7">
						<html:text name="rs1" property="shsj1" style="width: 100%" maxlength="55"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						2：
					</td>
					<td colspan="7">
						<html:text name="rs1" property="shsj2" style="width: 100%" maxlength="55"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						3：
					</td>
					<td colspan="7">
						<html:text name="rs1" property="shsj3" style="width: 100%" maxlength="55"/>
					</td>
				</tr>
				<tr>
					<td rowspan="3" align="center">
						<b>特<br>长<br>及<br>能<br>力</b>
					</td>
					<td align="center" colspan="3">
						1：主修外语种类
					</td>
					<td >
						<html:select name="rs1" property="wyyz">
							<html:option value=""></html:option>
							<html:option value="英语">英语</html:option>
							<html:option value="汉语">汉语</html:option>
							<html:option value="其他">其他</html:option>
						</html:select>
					</td>
					<td align="center">
						级别
					</td>
					<td >
						<html:select name="rs1" property="jb">
							<html:option value=""></html:option>
							<html:option value="一级">一级</html:option>
							<html:option value="四级">四级</html:option>
							<html:option value="六级">六级</html:option>
							<html:option value="其他">其他</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="3">
						2:计算机水平
					</td>
					<td >
						<html:select name="rs1" property="jsjsp">
							<html:option value=""></html:option>
							<html:option value="一级">一级</html:option>
							<html:option value="二级">二级</html:option>
							<html:option value="三级">三级</html:option>
							<html:option value="四级">四级</html:option>
							<html:option value="其他">其他</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="3">
						3:特长及能力(包括在校期间担任职务)
					</td>
					<td colspan="4">
						<html:textarea name="rs1" property="tcnl" rows="4" style="width=100%"/>
					</td>
				</tr>
				<tr>
					<td align="center" rowspan="5">
						<b>学<br>校<br>推<br>荐<br>意<br>见</b>
					</td>
					<td align="center" rowspan="3" colspan="2">
						系（院）意见：
					</td>
					<td align="center" style="width: 85px">
						毕业生<br>培养方式
					</td>
					<td >
						<html:text name="rs1" property="pyfs" />
					</td>
				</tr>
				<tr>
				<td align="center">
						就业范围
					</td>
					<td >
						<html:text name="rs1" property="jyfw" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<html:textarea name="rs1" property="yxyj" rows="4" style="width=100%"/>
					</td>
				</tr>
				<tr>
					
					<td align="center" rowspan="1" colspan="2">
						学校毕业<br>生就业部<br>门意见
					</td>
					<td colspan="5">
						<html:textarea name="rs1" property="jybmyj" rows="4" style="width=100%"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						联系部门
					</td>
					<td >
						<html:text name="rs1" property="lxbm" />
					</td>
					<td align="center">
						联系人
					</td>
					<td >
						<html:text name="rs1" property="bmlxr" />
					</td>
					<td align="center">
						联系电话
					</td>
					<td >
						<html:text name="rs1" property="bmlxdh" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<b>备<br>注</b>
					</td>
					<td align="center" colspan="7">
					<html:textarea name="rs1" property="bz" rows="4" style="width=100%"/>
					</td>
				</tr>
			</table>
			<div align="center">
				<button class="button2" onclick="add();" style="width:80px">
					提 交
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px">
					关 闭
				</button>
			</div>
		</html:form>

		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				    <script>
                      alert("提交成功！");
                      dialogArgumentsQueryChick();
                      window.Close();
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("重复提交！操作失败!");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		
	</body>
</html>

