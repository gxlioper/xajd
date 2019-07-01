<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<%@ include file="/syscommon/pagehead_V4.ini"%>
<head>
	<base target="_self" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getOtherData.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script language="javascript" src="js/prototype-1.6.0.3.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var zqhhkrq = document.getElementById('zqhhkrq').value;
			var dkqx = document.getElementById('dkqx').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if((zqhhkrq == null) || (zqhhkrq == "")){
				alert("展期后还款开始时间不能为空!");
				return false;
			}
			re = /-/gi;
			document.getElementById('zqhhkrq').value = zqhhkrq.replace(re,"");
			if (dkqx.substr(9,8) >= zqhhkrq){
				alert("展期后贷款结束日期"+zqhhkrq+"不能小于或等于贷款最后期限"+dkqx.substr(9,8)+"!");
			}
			
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=zqxysqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=zqxysqb";
			document.forms[0].submit();
		}
		function chang(){
			var xh = document.getElementById('xh').value;
			var zqnx = document.getElementById('zqnx').value;
			var zqnxT = document.getElementById('zqnxT').value;
			var zqhhkrqT = document.getElementById('zqhhkrqT').value;
			var zqhhkrq = "";
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			
			if(zqnx != "0"){
				Event.stopObserving("zqhhkrq", 'click', clickfun);
			
				dwr.engine.setAsync(false);
				getOtherData.getZgdzdxGjzxdkZqqx(xh,zqnx,function(data){
       				if(data!=null){
       					zqhhkrq=data;
      			 	}
   			 	});
    			dwr.engine.setAsync(true);
    			document.getElementById('zqhhkrq').value = zqhhkrq;
			} else {
				if (zqnxT == "0"){
					zqhhkrq = zqhhkrqT;
				}
				document.getElementById('zqhhkrq').value = zqhhkrq;
				
				$('zqhhkrq').observe('click', clickfun);
			}
			
		}
		function clickfun(){
			return showCalendar('zqhhkrq','y-mm-dd');
		}
		function dataCl(){
			var zqhhkrq = document.getElementById('zqhhkrq').value;
			re = /-/gi;
			document.getElementById('zqhhkrq').value = zqhhkrq.replace(re,"");
		}
	</script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>${title }</a>
		</p>
	</div>
	
		<html:form action="zgdzdx_xszz.do?method=zqxysq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zgdzdx_xszz.do?method=zqxysq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="zqhhkrqT" name="zqhhkrqT"
				value="<bean:write name="rs" property="zqhhkrq" />">
			<input type="hidden" id="zqnxT" name="zqnxT"
				value="<bean:write name="rs" property="zqnx" />">

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
			<logic:present name="isNull">
				<logic:match value="is" name="isNull">
					<script language="javascript">
	         			alert("您没有审核通过的国家助学贷款信息！");
	         		</script>
				</logic:match>
			</logic:present>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4">
			        		<span>
			        			<logic:equal name="sfksq" value="-1">
										现在不在申请时间内!
								</logic:equal>
								<logic:notEqual name="sfksq" value="-1">
										展期协议
								</logic:notEqual>
			        		</span>
			        	</th>
			        </tr>
			    </thead>
			    <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <logic:equal name="sfksq" value="1">
						<div class="btn" id="btn" >
							<logic:equal name="rs" property="shjg" value="通过">
								<font color="red">已通过审核</font>
								<button type="button"  onclick="yz();" disabled="disabled">
									提&nbsp;&nbsp;&nbsp;&nbsp;交
								</button>
							</logic:equal>
							<logic:notEqual name="rs" property="shjg" value="通过">
							<button type="button"   onclick="yz();">
								提&nbsp;&nbsp;&nbsp;&nbsp;交
							</button>
							</logic:notEqual>
							<logic:equal name="sfkdy" value="0">
							<button type="button"   onclick="toPrintOut();" disabled="disabled">
								打&nbsp;&nbsp;&nbsp;&nbsp;印
							</button>
							</logic:equal>
							<logic:notEqual name="sfkdy" value="0">
							<button type="button"   onclick="toPrintOut();">
								打&nbsp;&nbsp;&nbsp;&nbsp;印
							</button>
							</logic:notEqual>
						</div>
					</logic:equal>
					</td>
			      </tr>
			    </tfoot>
			    <tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th align="center" width="16%">
							<font color="red">*</font>学号
						</th>
						<td align="left" width="34%">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="readonly"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								  id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th align="center" width="16%">
							<font color="red">*</font>学号
						</th>
						<td align="left" width="34%">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<th width="16%">
							姓名
					</th>
					<td width="34%">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th>
							行政班号
					</th>
					<td>
						<input type="text" id="bjmc" readonly="readonly" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
					<th>
							身份证号
					</th>
					<td>
						<input type="text" id="sfzh" readonly="readonly" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<th>
							合同号
					</th>
					<td>
						<input type="text" id="htbh" name="htbh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="htbh"/>">
					</td>
					<th>
							贷款总金额
					</th>
					<td>
						<input type="text" id="sqdkje" name="sqdkje" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="fkzje"/>">
					</td>
				</tr>
				<tr>
					<th>
							贷款期限
					</th>
					<td>
						<input type="text" id="dkqx" name="dkqx" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkqx"/>">
					</td>
					<th>
							贷款期限(月)
					</th>
					<td>
						<input type="text" id="dkqxy" name="dkqxy" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkqxy"/>">
					</td>
				</tr>
				<tr>
					<th>
							贷款余额
					</th>
					<td>
						<input type="text" id="dkye" name="dkye" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkye"/>">
					</td>
					<td colspan="2">
						<div align="left">
							<font color="red">如展期年限为其它，请单独写明展期后贷款结束时间。</font>
						</div>
					</td>
				</tr>
				<tr>
					<th>
							展期年限
					</th>
					<td>
						<html:select name="rs" property="zqnx" style="width:100%;heigh:100%" onchange="chang()">
							<html:option value="1">一年</html:option>
							<html:option value="2">二年</html:option>
							<html:option value="3">三年</html:option>
							<html:option value="4">四年</html:option>
							<html:option value="5">五年</html:option>
							<html:option value="6">六年</html:option>
							<html:option value="0">其它</html:option>
						</html:select>
					</td>
					<th>
							<font color="red">*</font>展期后贷款<br />结束时间
					</th>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%" onblur="dataCl();"
							value="<bean:write name='rs' property="zqhhkrq" />" name="zqhhkrq"
							id="zqhhkrq" />
					</td>
				</tr>
				</tbody>
			</table>
		</html:form>
</body>
<script language="javascript">
		var xh = document.getElementById('xh').value;
		var zqnx = document.getElementById('zqnx').value;
		var zqhhkrq = "";
			
		if((xh != null) && (xh != "")){
			if(zqnx != "0"){
				Event.stopObserving("zqhhkrq", 'click', clickfun);
			
				dwr.engine.setAsync(false);
				getOtherData.getZgdzdxGjzxdkZqqx(xh,zqnx,function(data){
       				if(data!=null){
       					zqhhkrq=data;
      			 	}
   			 	});
    			dwr.engine.setAsync(true);
    			document.getElementById('zqhhkrq').value = zqhhkrq;
			} else {
				$('zqhhkrq').observe('click', clickfun);
			}
		}
</script>
</html>