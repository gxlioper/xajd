<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	
<script type="text/javascript" src="/xgxt/dwr/interface/getPjpyDao.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/pjpyFunction.js"></script>
<script type="text/javascript" src="js/AjaxFunction.js"></script>
<script type="text/javascript">
/*
 显示年级、<bean:message key="lable.xsgzyxpzxy" />、专业、班级列表 
*/
function showItems(){
	var items = document.getElementById("items");
	items.style.left = (screen.availwidth)/6;
	items.style.top = ((screen.availheight)/6)-20;
	items.style.display = "block";
	if ($("userType").value == "xy") {
		var tmp = 'xy';
		document.getElementById('xy').disabled = true;
	}
}
/*
 隐藏年级、<bean:message key="lable.xsgzyxpzxy" />、专业、班级列表 
*/
function hideItems(){
	var items = document.getElementById("items");
	//items.style.left = (screen.availWidth)/2;
	//items.style.top = (screen.availHeigh)/2;
	items.style.display = "none";
	document.getElementById("bjdm").value = document.getElementById("bj").value;
	refreshForm("/xgxt/zjlgPjpy.do?method=xjbjSq");
}

function xjbjSave(){
    var bjdm = "";
    var bjqk = "";
    var xn   = "";
    if($("bjdm")){
       bjdm = $("bjdm").value;
    }
    if($("xn")){
       xn=$("xn").value;
    }
    if($("bjqk")){
       bjqk = $("bjqk").value;
    }
    if(bjdm==""){
       alert("班级代码不能为空！");
       return false;
    }
    if(xn==""){
       alert("学年不能为空！");
       return false;
    }
    if(bjqk.length>500){
       alert("班级情况字数过大，限500字内！");
       return false;
    }
    var tem = bjdm+xn;
     getPjpyDao.getInfoEx("zjlg_xjbjb","bjdm||xn",tem," yxsh='通过' ",function(tag){
		     if(tag){
		        alert("该学年、该班级已申请,且已通过相关部门审核\n或正在审核中,不能再次申请！");		           	         			        
		     }else{
		        if(confirm("确定要保存数据！")){
		           refreshForm("/xgxt/zjlgPjpy.do?method=xjbjSq&doType=save");
                   if($("buttonSave")){
                     $("buttonSave").disabled =true;
                   }
		        }              
		     }
    	});	    
}
</script>
</head>
<body onload="">
	<html:form action="/zjlgPjpy" method="post">
		<input type="hidden" id="userType" name="userType"
			value="<bean:write name="userType" scope="session"/>" />
		<input type="hidden" id="isFdy" name="isFdy"
			value="<bean:write name="fdyQx" scope="session"/>" />
		<input type="hidden" id="userName" name="userName"
			value="<bean:write name="userName" scope="session"/>" />
		<input type="hidden" name="njV" id="njV"/>
		<input type="hidden" name="xyV" id="xyV"/>
		<input type="hidden" name="zyV" id="zyV"/>
		<input type="hidden" name="bjV" id="bjV"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>评奖评优 - 荣誉称号申请 - 先进班级申请</a>
			</p>
		</div>
		<div id="items" style="display:none; position: absolute;background-color: #AFEEEE; ">
			<table class="tbstyle">
				<tr>
					<td>
						年级
					</td>
					<td>
						<html:select property="nj" onchange="initZyList();initBjList();"
							styleId="nj">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td>
						<html:select property="xydm" onchange="initZyList();initBjList();"
							styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						专业
					</td>
					<td>
						<html:select property="zydm" onchange="initBjList();" styleId="zy">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						班级
					</td>
					<td>
						<html:select property="bj" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan=2 align=center>
						<button type="button" class="button2" onclick="hideItems();">
							确 定
						</button>
					</td>
				</tr>
			</table>
		</div>
		<div class="tab">
		<table width="100%" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>填写申请表</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th>
					<font color="red">*</font>班级代码
				</th>
				<td align="left" width="30%">
					<html:text property="bjdm" readonly="true" onclick="showItems()"
						styleId="bjdm" />
				</td>
				<th>
					学年
				</th>
				<td align="left">
					<html:select property="xn" disabled="true">
						<html:options collection="xnList" property="xn" labelProperty="xn" />
					</html:select>
				</td>
			</tr>
			<tr>
				<th>
					年级
				</th>
				<td align="left">
					${rs.nj}
				</td>
				<th>
					班级名称
				</th>
				<td align="left">
					${rs.bjmc}
				</td>
			</tr>
			<tr>
				<th>
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td align="left">
					${rs.xymc}
				</td>
				<th>
					辅导员
				</th>
				<td align="left">
					${fdy}
				</td>
			</tr>
			<tr>
				<th>
					班级平均成绩
				</th>
				<td align="left">
					${bjpjf}
				</td>
				<th>
					不及格率
				</th>
				<td align="left">
					${bjbjdl}
				</td>
			</tr>
			<tr>
				<th>
					文明寝室个数
				</th>
				<td align="left">
					<html:text property="wmqsgs" styleId="wmqsgs"
						onkeypress='return sztzNumInputValue(this,3,event)'
						onblur="onlyNumInput(this)" />
				</td>
				<th>
					A级寝室个数
				</th>
				<td align="left">
					<html:text property="ajqsgs" styleId="ajqsgs"
						onkeypress='return sztzNumInputValue(this,3,event)'
						onblur="onlyNumInput(this)" />
				</td>
			</tr>
			<tr>
				<th>
					是否优秀班级
				</th>
				<td align="left">
					<html:select property="sfyxbj" styleId="sfyxbj"
						style="width:120px;">
						<html:option value="否">否</html:option>
						<html:option value="是">是</html:option>
					</html:select>
				</td>
				<th></th><td></td>
			</tr>
			<tr>
				<th>
					班级情况
					<span style="color: red">(限500字)</span>
				</th>
				<td colspan="3" scope="row" align="left">
					<html:textarea rows="12" style="width:98%" property="bjqk" />
				</td>
			</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
						  <button type="button" id="buttonSave" onclick="xjbjSave()">
							提交申请
						  </button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
</body>
<logic:equal value="true" name="done">
	<script type="text/javascript">
    alert("成功提交！");
  </script>
</logic:equal>
<logic:equal value="false" name="done">
	<script type="text/javascript">
    alert("提交失败！");
  </script>
</logic:equal>
<logic:equal name="pass" value="no">
	<script>
	alert("该班级不满足先进班级申请条件！");			    
   </script>
</logic:equal>
</html>
