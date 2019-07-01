<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
<head>
<title><bean:message key="lable.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="正方软件 zfsoft" />
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache, must-revalidate"> 
<meta http-equiv="expires" content="0">
</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
<script type='text/javascript' src='dwr/interface/GetListData.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/utils.js'></script>
<script type="text/javascript" src="js/commanFunction.js"></script>
<script type="text/javascript" src="js/BatAjax.js"></script>
<script type="text/javascript" src="js/webPrint.js"></script>
<script type="text/javascript" src="js/String.js"></script>
<script language="javascript" src="js/calendar.js"></script>
<script language="javascript" src="js/calendar-zh.js"></script>
<script language="javascript" src="js/calendar-setup.js"></script>

<script type="text/javascript">
DWREngine.setAsync(false);
var shuZi = ['一','二','三','四','五','六','七','八','九','十'];
function xueYuanStr() {
	var resultStr = '';
	if ($('cxxydm').options.length == 0) {
		for (var i=0; i<$('xydm').options.length; i++) {
			if (i==0) {
				resultStr += $('xydm').options[i].innerHTML;
			} else {
				resultStr += ","+$('xydm').options[i].innerHTML;
			}
		}
	} else {
		for (var i=0; i<$('cxxydm').options.length; i++) {
			if (i==0) {
				resultStr += $('cxxydm').options[i].innerHTML;
			} else {
				resultStr += ","+$('cxxydm').options[i].innerHTML;
			}
		}
	}
	return resultStr;
}
function getShuZiText(i) {
	if (i > 10) {
		return i;
	}else {
		return shuZi[i-1];
	}
}
/**
 * @author ChenHuamao  E-MAIL:chhuma@hotmail.com
 * 统计前进行效验，必须输入年度和批次及选择是列表还是图表
 */
function validate() {
	/*if ($('nd').value==null || $('nd').value=='') {
		alert('请选择年度');
		$('nd').focus();
		return false;
	}
	alert($('cxxydm').options.length);*/
	/*if ($('cxxydm').options.length == 0) {
		alert('请选择要查询的<bean:message key="lable.xsgzyxpzxy" />');
		$('cxxydm').focus();
		return false;
	}*/
	if ($('bzffny').value.trim() == '') {
		alert('请选择'+$('flag').value);
		$('bzffny').focus();
		return false;
	}
	if ($('cxxydm').options.length == 0) {
		$('cxxydmDestmc').value = "";
		$('cxxydmDest').value = "";
	}
	for (var i=0; i<$('cxxydm').options.length; i++) {
		if (i==0) {
			$('cxxydmDestmc').value = $('cxxydm').options[i].text;
			$('cxxydmDest').value = $('cxxydm').options[i].value;
		} else {
			$('cxxydmDestmc').value += "-"+$('cxxydm').options[i].text;
			$('cxxydmDest').value += "-"+$('cxxydm').options[i].value;
		}
	}
	
	for (var i=0; i<$('xydm').options.length; i++) {
		if (i==0) {
			$('cxxydmSrcmc').value = $('xydm').options[i].text;
			$('cxxydmSrc').value = $('xydm').options[i].value;
		} else {
			$('cxxydmSrcmc').value += "-"+$('xydm').options[i].text;
			$('cxxydmSrc').value += "-"+$('xydm').options[i].value;
		}
	}
	return true;
}

function init() {
	//if($("xydm"))GetList.getDxList(initTjList);
	$('flag').value = '<bean:write name="flag"/>';
	controlChange($('flag'));
	
	$('bzffny').value = '<bean:write name="bzffny"/>';
	var xydm = $('xydm');
	xydm.removeChild(xydm.options[0]);

	var destList = [];
	for (var i=0; i<xydm.options.length; i++) {
		if ($('cxxydmDest').value.indexOf(xydm.options[i].value) != -1) {
			destList[destList.length] = xydm.options[i];
		}
	}
	var cxxydmArray = $('cxxydmDest').value.split("-");
	for (var j=0; j<cxxydmArray.length; j++){
	for (var i=0; i<destList.length; i++) {
		if (destList[i].value == cxxydmArray[j]) {
			$('cxxydm').appendChild(destList[i]);
			break;
		}
	}
	}
}

function initList() {
	if ($('xydm') != null) {
		//GetListData.getXydmList(initTjList);
		GetListData.getXyList({callback:function(data){initTjList(data, 'xydm');}});
	}
}

function controlChange(selectObj) {
	//alert(selectObj.value.trim());
	if (selectObj.value.trim() == '年') {
		$('flagControl').innerHTML = "<input type='text' style='cursor:hand;' onclick=\"return showCalendar('bzffny','y');\" width='100%' name='bzffny' id='bzffny' />";
	}else if (selectObj.value.trim() == '月') {
		$('flagControl').innerHTML = "<input type='text' style='cursor:hand;' onclick=\"return showCalendar('bzffny','y-mm');\" width='100%' name='bzffny' id='bzffny' />";
	}else if (selectObj.value.trim() == '学年') {//2005-2006		 2005-09至2006-07
		$('flagControl').innerHTML = "";
		var tempSelect = document.createElement('SELECT');
		tempSelect.id = 'bzffny';
		tempSelect.name = 'bzffny';
		$('flagControl').appendChild(tempSelect);
		GetListData.getXnList({callback:function(data){initTjList(data, 'bzffny');}});
	} else {
		$('flagControl').innerHTML = "<input type='text' style='cursor:hand;' onclick=\"return showCalendar('bzffny','y');\" width='100%' name='bzffny' id='bzffny' />";
	}
	
}
</script>
<body onload="initList();init();">
<html:form action="/bjlhdx_xszz.do?method=fsbzmx" method="post">
<div class="title"> 
  <div class="title_img" id="title_m"> 当前位置：学生资助 - 学生补助 - 统计报表</div> 
</div>
<input type="hidden" name="cxxydmSrcmc"  id="cxxydmSrcmc" value="<bean:write name="cxxydmSrcmc" scope="request"/>">
<input type="hidden" name="cxxydmDestmc"  id="cxxydmSrcmc" value="<bean:write name="cxxydmDestmc" scope="request"/>">
<input type="hidden" name="cxxydmDest"  id="cxxydmDest" value="<bean:write name="cxxydmDest" scope="request"/>">
<input type="hidden" name="cxxydmSrc"  id="cxxydmSrc" value="<bean:write name="cxxydmSrc" scope="request"/>">
<input type="hidden" id="xydmV" value="<bean:write name="cxxydmSrc" scope="request"/>">
<input type="hidden" id="cxxydmV" value="<bean:write name="cxxydmDest" scope="request"/>">
<fieldset>
  <legend> 查 询 </legend>
	<table class="tbstyle" width="100%">
	<thead>
	  <tr>	     
	  	<td>
		  查询方式：
		  <select name="flag" id="flag" style="width:90" onchange="controlChange(this)">
		  	<option value="年">年</option>
		  	<option  value="月">月</option>
		  	<option  value="学年">学年</option>
		  </select>&nbsp;&nbsp;
		  <span id="flagControl"></span>
	  	</td>
	  	<td rowspan="2" width="20">
	  	  <button style="width:60px;height:20px" id="searchBtn" class="button2" onclick="if(validate())refreshForm('bjlhdx_xszz.do?method=fsbzmx');">统计</button>
	  	</td>	  	
	  </tr>
	  <TR>
		<TD>
		<table><tr  align="center">
		<TD>
		<fieldset>
		<legend><bean:message key="lable.xsgzyxpzxy" />列表：</legend>
        <select multiple size="10" style="width:200px" id="xydm" ondblclick="move($('xydm'), $('cxxydm'));">
        </select>			
        &nbsp;&nbsp;
		</fieldset>
		</TD>
		<td>
		<button class="button2"  onClick="move($('xydm'), $('cxxydm'));">=&gt;</button><br/><br/>
		<button class="button2"  onClick="move($('cxxydm'), $('xydm'));">&lt;=</button>
		</td>
		<td>
		<fieldset>
		<legend>要统计的<bean:message key="lable.xsgzyxpzxy" />：</legend>
			<select style="width:200px" multiple size="10" id="cxxydm" ondblclick="move($('cxxydm'), $('xydm'));">
    		</select>
			&nbsp;&nbsp;
		</fieldset>
		</td>
		<td><button class="button2" onClick="upJust('cxxydm')">上调</button><br/><br/><br/><button class="button2" onClick="downJust('cxxydm')">下调</button></td>
		</tr>
		</table>
	    </TD>
     </TR>	 	  	
	 </thead>
	</table>
</fieldset>
<fieldset>
  <logic:notPresent name="url">	
  <legend> 统计结果</legend>
  <table id="tabPri" class="tbstyle" width="100%">
    <thead>
	  <tr align="center" style="cursor:hand">
	  	<td>编 号</td>
		<td>学 院</td>
        <td>班 级</td>  
		<td>学 号</td>  
		<td>姓 名</td>  
		<td>一卡通号码(后面加"01")</td>  
		<td>本月应发副食补助(元)</td>  
		<td>本月补发副食补助(元)</td>  
		<td>本月实发副食补助(元)</td>
		<td>本月应发临时补助(元)</td>  
		<td>本月补发临时补助(元)</td>  
		<td>本月实发临时补助(元)</td>   
		<td>本月困难补助(元)</td>  
		<td>本月勤工助学(元)</td>  
		<td>国家奖学金(元)</td>  
		<td>国家助学金(元)</td>  
		<td>政府助学金(元)</td>  
		<td>洗澡补贴(元)</td>  
		<td>电话费(元)</td>  
		<td>其 它</td>  
		<td>学期奖学金</td>  
		<td>综合奖学金(元)</td>  
		<td>总金额(元)</td>  
		<td>备 注</td>    
      </tr>
	  
    </thead>
    <tbody id="rsTable">
	<logic:present name="list">
      <logic:iterate id="n" name="list"> 
		<tr>
            <td><bean:write name="n" property="rownum"/></td>              
			<td><bean:write name="n" property="xymc"/></td>              
			<td><bean:write name="n" property="bjdm"/></td>              
			<td><bean:write name="n" property="xh"/></td>              
			<td><bean:write name="n" property="xm"/></td>              
			<td><bean:write name="n" property="kh"/></td>              
			<td><bean:write name="n" property="fsbyyfbz"/></td>              
			<td><bean:write name="n" property="fsbybfbz"/></td>              
			<td><bean:write name="n" property="fsbysfbz"/></td>              
			<td><bean:write name="n" property="lsbyyfbz"/></td>              
			<td><bean:write name="n" property="lsbybfbz"/></td>              
			<td><bean:write name="n" property="lsbysfbz"/></td>              
			<td>&nbsp;0</td>                  
			<td>&nbsp;0</td>              
			<td>&nbsp;0</td>   
			<td>&nbsp;0</td>                  
			<td>&nbsp;0</td>              
			<td>&nbsp;0</td>           
			<td>&nbsp;0</td>              
			<td>&nbsp;0</td>              
			<td>&nbsp;0</td>              
			<td>&nbsp;0</td>              
			<td>&nbsp;<bean:write name="n" property="ze"/></td>   
			<td>&nbsp;</td>                     
        </tr>
      </logic:iterate>
    </logic:present>
	<logic:present name="zjList">
      <logic:iterate id="n" name="zjList"> 
		<tr>
            <td colspan="6">总计</td>                         
			<td><bean:write name="n" property="fsbyyfbz"/></td>              
			<td><bean:write name="n" property="fsbybfbz"/></td>              
			<td><bean:write name="n" property="fsbysfbz"/></td>              
			<td><bean:write name="n" property="lsbyyfbz"/></td>              
			<td><bean:write name="n" property="lsbybfbz"/></td>              
			<td><bean:write name="n" property="lsbysfbz"/></td>              
			<td>&nbsp;0</td>                  
			<td>&nbsp;0</td>              
			<td>&nbsp;0</td>              
			<td>&nbsp;0</td>   
			<td>&nbsp;0</td>                  
			<td>&nbsp;0</td>              
			<td>&nbsp;0</td>           
			<td>&nbsp;0</td>              
			<td>&nbsp;0</td>              
			<td>&nbsp;0</td>              
			<td>&nbsp;<bean:write name="n" property="ze"/></td>   
			<td>&nbsp;</td>                     
        </tr>
      </logic:iterate>
    </logic:present>
    </tbody>
  </table>
  </logic:notPresent>
  <div class="buttontool" id="btn" style="position: absolute;left:0px;top:100px" width="100%">
	<button class="button2" onclick="expTabWebPrint('tabPri',xueYuanStr()+$('bzffny').value+'补助明细表','','15-15-15-10', null, null)" name="priBtn" style="width:100px">打印列表</button>
  </div>
</fieldset><br/><br/><br/>
</html:form>
<script type="text/javascript" src="js/bottomButton.js"></script>
</body>
</html>
