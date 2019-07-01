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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/nblgxy_gygl" method="post">
			<input type="hidden" name="pkValue" id="pkValue"
				value="<bean:write name="pkValue" scope="request"/>">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： 公寓管理 - 辅导员月报 - 月报填写
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
			
			<table width="100%" class="tbstyle" id="rsT">
				<thead>
					<tr>
						<td colspan="4" align="center">
							辅导员月报信息
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="15%">
						年份：
					</td>
					<td>
						<html:select name="rs" property="nf" disabled="true">
							<html:options collection="nfList" property="nd"
								labelProperty="nd"  />
						</html:select>
					</td>
					<td align="right" width="15%">
						月份：
					</td>
					<td align="left">
						<html:select name="rs"  property="yf" disabled="true">
							<html:option value="1">1</html:option>
							<html:option value="2">2</html:option>
							<html:option value="3">3</html:option>
							<html:option value="4">4</html:option>
							<html:option value="5">5</html:option>
							<html:option value="6">6</html:option>
							<html:option value="7">7</html:option>
							<html:option value="8">8</html:option>
							<html:option value="9">9</html:option>
							<html:option value="10">10</html:option>
							<html:option value="11">11</html:option>
							<html:option value="12">12</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<html:text name="rs"  property="xm" readonly="true" styleId="xm" disabled="true"></html:text>
					</td>

					<td align="right">
						工作时间段：
					</td>
					<td>
						<html:text name="rs"  property="gzksrq" styleId="gzksrq"
							onblur="dateFormatChg(this)" style="cursor:hand;width:75px"
							onclick="return showCalendar('gzksrq','y-mm-dd');"
							readonly="true" />
						--
						<html:text name="rs" property="gzjsrq" styleId="gzjsrq"
							onblur="dateFormatChg(this)" style="cursor:hand;width:75px"
							onclick="return showCalendar('gzjsrq','y-mm-dd');"
							readonly="true" />
					</td>
				</tr>
				<tr>
					<td align="right">
						负责楼栋：
					</td>
					<td align="left">
						<html:text name="rs"  property="fzld"></html:text>
					</td>

					<td align="right">
						入住楼栋寝室：
					</td>
					<td>
						<html:text name="rs"  property="rzqsh"></html:text>
					</td>
				</tr>
			</table>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td width="5%" align="center">
							主要工作内容
						</td>
					</tr>
				</thead>
				<tr>
					<td>
						<div onclick="showHide('jyfk')" style="cursor: hand"
							title="单击该行显示或隐藏文本输入框">
							每月一次寝室普查时间、内容、相关人员和整体检查情况简要反馈：
						</div>
					</td>
				</tr>
				<tr id="jyfk" style="display: none">
					<td>
						<html:textarea name="rs"  property='gznr_jyfk' style="width:99%;;"
							rows='8' />
					</td>
				</tr>
				<tr>
					<td>
						<div onclick="showHide('jyjl')" style="cursor: hand"
							title="单击该行显示或隐藏文本输入框">
							每周值班时间内接待学生、谈心、走访寝室等的简要记录：
						</div>
					</td>
				</tr>

				<tr id="jyjl" style="display: none">
					<td>
						<html:textarea name="rs"  property='jgznr_jyjl' title=""
							style="width:99%;;" rows='8' />
					</td>
				</tr>
				<tr>
					<td>
						<div onclick="showHide('jhynr')" style="cursor: hand"
							title="单击该行显示或隐藏文本输入框">
							召开楼层长等学生骨干会议的时间、地点和主要会议内容：
						</div>
					</td>
				</tr>

				<tr id="jhynr" style="display: none">
					<td>
						<html:textarea name="rs"  property='gznr_jhynr' title=""
							style="width:99%;;" rows='8' />
					</td>
				</tr>
				<tr>
					<td>
						<div onclick="showHide('jxsdt')" style="cursor: hand"
							title="单击该行显示或隐藏文本输入框">
							一个月来需要反馈的楼内学生动态：
						</div>
					</td>
				</tr>

				<tr id="jxsdt" style="display: none">
					<td>
						<html:textarea name="rs"  property='gznr_jxsdt' title=""
							style="width:99%;;" rows='8' />
					</td>
				</tr>
				<tr>
					<td>
						<div onclick="showHide('jqtnr')" style="cursor: hand"
							title="单击该行显示或隐藏文本输入框">
							其他工作内容：
						</div>
					</td>
				</tr>

				<tr id="jqtnr" style="display: none">
					<td>
						<html:textarea name="rs"  property='gznr_jqtnr'
							style="width:99%;;" rows='8' />
					</td>
				</tr>
			</table>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center">
							需要反馈的情况和工作建议
						</td>
					</tr>
				</thead>
				<tr>
					<td>
						<div onclick="showHide('xgbfk')" style="cursor: hand"
							title="单击该行显示或隐藏文本输入框">
							反馈至学工部：（存在的困难、工作建议、重点工作进度情况、需要备案的突发事件处理等）
						</div>
					</td>
				</tr>

				<tr id="xgbfk" style="display: none">
					<td>
						<html:textarea name="rs"  property='fkyj_xgbfk' title=""
							style="width:99%;;" rows='8' />
					</td>
				</tr>
				<tr>
					<td>
						<div onclick="showHide('xyfk')" style="cursor: hand"
							title="单击该行显示或隐藏文本输入框">
							反馈至分院：（包括学生动态、不稳定苗头、需关注学生等，注明分院）
						</div>
					</td>
				</tr>

				<tr id="xyfk" style="display: none">
					<td>
						<html:textarea name="rs"  property='fkyj_xyfk' title=""
							style="width:99%;;" rows='10' />
					</td>
				</tr>
				<tr>
					<td>
						<div onclick="showHide('fwzxfk')" style="cursor: hand"
							title="单击该行显示或隐藏文本输入框">
							反馈至生活园区管理服务中心：（涉及日常管理及其他需要协同的工作）
						</div>
					</td>
				</tr>

				<tr id="fwzxfk" style="display: none">
					<td>
						<html:textarea name="rs"  property='fkyj_fwzxfk' title=""
							style="width:99%;;" rows='8' />
					</td>
				</tr>
				<tr>
					<td>
						备注：
						<html:textarea name="rs"  property='bz' style="width:99%;;"
							rows='5' />
					</td>
				</tr>

			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="ybAdd()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" id="buttonReset"
					style="width:80px">
					关 闭
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" style="width:80px" onclick="showValues()">
					报表预览
				</button>						
			</div>
			<logic:equal value="false" name="done">
			<script type="text/javascript">
			     alert('修改失败！');
			</script>
			</logic:equal>
						<logic:equal value="true" name="done">
			<script type="text/javascript">
			    alert('修改成功！');
				Close();
				dialogArgumentsQueryChick();			     
			</script>
			</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
	<script type="text/javascript">
    function ybAdd(){
               if($("nf").value==""){
                  alert('请选择年份！');
                  return false;
               }
               if($("yf").value==""){
                  alert('请选择月份！');
                  return false;
               }
               var nf = "";
               var yf = "";
               var dlm ="";
               if($("nf"))nf=$("nf").value;
               if($("yf"))yf=$("yf").value;
               if($("dlm"))dlm=$("dlm").value;
              var pkValue=nf+yf+dlm;
               if(confirm("提交前可预览所输入的月报表。\n\n点击\"确定\"，保存数据；点击\"取消\"，将放弃保存；\n\n"+"确定要保存"+$('nf').value+"年"+$('yf').value+"月工作报表修改？")){
                    refreshForm('/xgxt/nblgxy_gygl.do?method=ybModi&doType=save');
               }else{
                   return false;
               }
               
    }
    function showHide(obj){		
      if(document.getElementById(obj).style.display==''){
		  document.getElementById(obj).style.display='none';		 
      }else{
		  document.getElementById(obj).style.display='';		 
	  }		
    }
   </script>
</html>
