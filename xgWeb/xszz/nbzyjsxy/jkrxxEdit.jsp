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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function ckinpdata(obj){
			obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
			var inputStr = obj.value;
			
			if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g))){
				obj.value = '';
				return false;
			} else if(inputStr.match(/\d+\.\d*\.\d*/g)){
			    obj.value = obj.value.substr(0,obj.value.length-1);
			    return false;
			}
			return true;
		}
		
		function getJe(){
			var tabobj = document.getElementsByName('_jkje');
   			var zje = 0;
   			
   			for(j=0;j<tabobj.length;j++){
	            je = document.getElementsByName('_jkje')[j].value;
	            zje += toFloor(je);
	        }
	        document.getElementById("jkje").value = zje;
		}
	
		function yz(){
			document.forms[0].action = "/xgxt/nbzyjsxy_xszz.do?method=jkrxxEditSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function queryJkrxx(cod, obj,redirect,variable){
			if (cod == 13) {
				document.forms[0].action = "/xgxt/nbzyjsxy_xszz.do?method=jkrxxEdit";
				document.forms[0].submit();
			}
		}
		
function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    count=len;     
	var cellfu =[
	function(data){
	return count;
	},
	function(data){
	return "<input type='text' readonly style='cursor:hand;width:100%' onclick='return showCalendar(\"_jkrq"+count+"\",\"y-mm-dd\");' name='_jkrq' id='_jkrq"+count+"' />";
    },	
	function(data){	    
	return "<input type='text' style='width:100%'  name='_jkje' maxlength='8' onkeyup='ckinpdata(this)' onblur='getJe()'>";
    },
    function(data){
    return "<input type='text' style='width:100%'  name='_sjh' maxlength='30'>";
    },
    function(data){    
	return "<input type='text' style='width:100%'  name='_jkfs' maxlength='100'>";	 
    }
	];
	
	if(type=='add'){
       DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
    }else{
       if(num==""||num==null){	
           return false;
       }
       for(i=count;i<=num;i++){          
          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
          count++;
       }
       $("numAdd").value = "";
    }        
}
function trDel(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    if(confirm("确定要删除第"+(length)+"行？")){      
       tabobj.deleteRow(tabobj.rows.length-1);
    }
    getJe();
}
function trDelAll(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;
    var num = $("numDel").value; 
    if(length==0){
       $("numDel").value = "";
       return false;     
    }
    if(num==""||num==null){	
       return false;
    }
    if(num>length){
      num = length;
    }
    if(confirm("确定要删除最后"+num+"行？")){ 
       for(i=1;i<=num;i++){
          length--;
          tabobj.deleteRow(tabobj.rows.length-1);
       }
    }
    $("numDel").value = "";
    getJe();
}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 基础数据维护 - 捐款人详细信息
		</div>
	</div>
	<html:form action="nbzyjsxy_xszz.do?method=jkrxxEdit" method="post">
		<input type="hidden" id="pkVal" name="pkVal"
			value="<bean:write name="pkVal" />">
		<input type="hidden" id="act" name="act"
			value="<bean:write name="act" />">

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
		<table class="tbstyle" width="100%">
			<tr>
				<td align="center" width="16%">
					学号/用户名：
				</td>
				<td align="left" width="34%">
					<html:text name='rs' property="xhyhm" styleId="xhyhm"
						onkeypress="queryJkrxx(event.keyCode,this);" />
				</td>
				<td colspan="2">
					(学生请输入学号，系统用户请输入用户名。非学生或系统用户可不用填写。)
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						姓名
					</div>
				</td>
				<td>
					<input type="text" id="xm" maxlength="40" name="xm"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xm"/>">
				</td>
				<td width="16%">
					<div align="center">
						身份证号
					</div>
				</td>
				<td width="34%">
					<input type="text" id="sfzh" maxlength="18" name="sfzh"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="sfzh"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						角色
					</div>
				</td>
				<td>
					<html:select name="rs" property="jsdm" style="width:100%">
						<html:options collection="jsList" property="jsdm"
							labelProperty="jsmc" />
					</html:select>
				</td>
				<td>
					<div align="center">
						手机
					</div>
				</td>
				<td>
					<input type="text" id="sj" name="sj" maxlength="11"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="sj"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						来源
					</div>
				</td>
				<td>
					<input type="text" id="ly" name="ly" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="ly"/>">
				</td>
				<td>
					<div align="center">
						家庭邮编
					</div>
				</td>
				<td>
					<input type="text" id="jtyb" name="jtyb" maxlength="6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtyb"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						家庭地址
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="jtdz" name="jtdz" maxlength="200"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtdz"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						单位
					</div>
				</td>
				<td>
					<input type="text" id="dw" maxlength="100" name="dw"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dw"/>">
				</td>
				<td>
					<div align="center">
						部门
					</div>
				</td>
				<td>
					<input type="text" id="bm" name="bm" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="bm"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						单位联系电话
					</div>
				</td>
				<td>
					<input type="text" id="dwlxdh" maxlength="15" name="dwlxdh"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dwlxdh"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<div align="center">
						单位邮编
					</div>
				</td>
				<td>
					<input type="text" id="dwyb" name="dwyb" maxlength="6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dwyb"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						单位地址
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="dwdz" maxlength="200" name="dwdz"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="dwdz"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						备注
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="bz" maxlength="200" name="bz"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="bz"/>">
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div align="center">
						捐款具体记录
					</div>
				</td>
				<td>
					<div align="center">
						捐款总金额：
						<input type="text" id="jkje" disabled="disabled" name="jkje"
							style="width:100px;heigh:100%"
							value="<bean:write name="rs" property="jkje"/>">
						元
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<!-- 查询得到的数据量显示区域 -->
					<input  value="+" onclick="trAdd('flag','add')" />
					<input  value="-" onclick="trDel('flag')" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
					<input type="text" name="numAdd" id="numAdd" style="width: 50px"
						onblur="trAdd('flag','madd')">
					&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
					<input type="text" name="numDel" id="numDel" style="width: 50px"
						onblur="trDelAll('flag')">
					&nbsp;行
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<table align="center" style="width:100%" id="rsT" class="tbstyle">
						<thead>
							<tr>
								<td nowrap="nowrap" width="5%" align="center">
									行号
								</td>
								<td nowrap="nowrap" width="15%" align="center">
									捐款日期
								</td>
								<td nowrap="nowrap" width="17%" align="center">
									金额
								</td>
								<td nowrap="nowrap" width="23%" align="center">
									收据号
								</td>
								<td nowrap="nowrap" width="40%" align="center">
									捐款方式
								</td>
							</tr>
						</thead>
						<tbody class="tbstyle" id="flag">
							<logic:notEmpty name="jkjlList">
								<logic:iterate name="jkjlList" id="s">
									<tr>
										<td>
											<bean:write name="s" property="hh" />
										</td>
										<td>
											<input type="text" readonly style="cursor:hand;width:100%"
												onclick="return showCalendar('_jkrq','y-mm-dd');"
												value="<bean:write name='s' property="jkrq" />"
												name="_jkrq" id="_jkrq" />
										</td>
										<td>
											<input type='text' style='width:100%' name='_jkje'
												value="<bean:write name="s" property="jkje"/>" maxlength="10" onkeyup='ckinpdata(this)' onblur="getJe()">
										</td>
										<td>
											<input type='text' style='width:100%' name='_sjh'
												value="<bean:write name="s" property="sjh"/>" maxlength="30">
										</td>
										<td>
											<input type='text' style='width:100%' name='_jkfs'
												value="<bean:write name="s" property="jkfs"/>"
												maxlength="100">
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="jkjlList">
							</logic:empty>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<button type="button" class="button2" onClick="yz();">
				保 存
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2"
				onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
				关 闭
			</button>
		</div>
	</html:form>
</body>
<script language="javascript">
	var tabobj = document.getElementsByName('_jkje');
   	var zje = 0;
   			
   	for(j=0;j<tabobj.length;j++){
		je = document.getElementsByName('_jkje')[j].value;
		zje += toFloor(je);
	}
	document.getElementById("jkje").value = zje;
	</script>
</html:html>
