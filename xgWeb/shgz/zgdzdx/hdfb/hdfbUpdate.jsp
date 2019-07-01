<%@ page language="java" contentType="text/html; charset=GBK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
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
	<script language="javascript">
	function saveHdfb(obj){
		if($("hddm").value == ""){
			alert("活动名称不能为空，请确认！！");
			return false;
		}
		if($("kssj").value == ""){
			alert("活动开始时间不能为空，请确认！！");
			return false;
		}
		if($("jssj").value == ""){
			alert("活动结束时间不能为空，请确认！！");
			return false;
		}
		if($('kssj').value>$('jssj').value){
			alert('开始时间不能小于结束时间，请确认!!');
			return false;
		}
		showTips('处理数据中，请等待......');
		var url = '/xgxt/zgddShgzHdfb.do?method=hdfbUpdate&doType=save';
		if($("hdV")){
			url+="&hdV="+$("hdV").value;
		}
		if($("xqV")){
			url+="&xqV="+$("xqV").value;
		}
		if($("xnV")){
			url+="&xnV="+$("xnV").value;
		}
		refreshForm(url);
		obj.disabled=true;
	}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<html:form action="/zgddShgzHdfb" method="post">
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>社会工作 - 社团活动管理 - 活动发布</a>
				</p>
			</div>

				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
					
				<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="2"><span>活动发布</span></th>
			        </tr>
			    </thead>
		 <tfoot>
			      <tr>
			        <td colspan="2"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			        <button class=""
						onclick="saveHdfb(this);"  id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button class="" onclick="Close();return false;"
						id="buttonClose">
						关 闭
					</button>
					</div>
				</td>
			      </tr>
			    </tfoot>
			    <tbody>
			      <tr>
			        <th width="25%"><font color="red">*</font>活动名称</th>
			        <td width="25%">
			        	<logic:equal name="doType" value="add">
								<html:select name="rs" property="hddm" >
									<html:option value=""></html:option>
									<html:options collection="hdList" property="hddm" labelProperty="hdmc" />
								</html:select>	
								</logic:equal>
								<logic:notEqual name="doType" value="add">
								<input type="hidden" id="hdV" name="hdV" value="<bean:write name="rs" property="hddm"/>" />
								<html:select name="rs" property="hddm"  disabled="true">
									<html:option value=""></html:option>
									<html:options collection="hdList" property="hddm" labelProperty="hdmc" />
								</html:select>	
								</logic:notEqual>
			        </td>
			        </tr>
			        <tr>
			        <th width="25%">学年</th>
			        <td width="25%">
			        	<logic:equal name="doType" value="add">
								<html:select name="rs" property="xn" style="">
									<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>	
								</logic:equal>
								<logic:notEqual name="doType" value="add">
								<html:select name="rs" property="xn" style="" disabled="true">
									<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>	
								</logic:notEqual>
								<input type="hidden" id="xnV" name="xnV" value="<bean:write name="rs" property="xn"/>"/>
			        </td>
			      </tr>
			       <tr>
			        <th width="">学期</th>
			        <td width="">
			        	<logic:equal name="doType" value="add">
								<html:select name="rs" property="xq" >
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
								</html:select>	
								</logic:equal>
								<logic:notEqual name="doType" value="add">
								<html:select name="rs" property="xq"  disabled="true">
									<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
								</html:select>
								</logic:notEqual>
								<input type="hidden" id="xqV" name="xqV" value="<bean:write name="rs" property="xq"/>"/>
			        </td>
			        </tr>
			        <tr>
			        <th width="">开始时间</th>
			        <td width="">
			        	<html:text name="rs" property="kssj" styleId="kssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('kssj','y-mm-dd');" readonly="true"/>
			        </td>
			        
			      </tr>
			 	 <tr>
			        <th width="">结束时间</th>
			        <td width="">
			        	<html:text name="rs" property="jssj" styleId="jssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('jssj','y-mm-dd');" readonly="true"/>		
			        </td>
			        
			      </tr>
					<tr>
							<th align="">
								需要<bean:message key="lable.xsgzyxpzxy" />审核：
							</th>
							<td align="left">
								<html:select name="rs" property="isxysh">
									<html:option value="yes">是</html:option>
									<html:option value="no">否</html:option>		
								</html:select>		 	
							</td>
						</tr>
			      </tbody>
			</table>
			</div>
			<logic:present name="result">
				<logic:equal name="result" value="yes">
					<script>
				    alert("提交成功！");
				    dialogArgumentsQueryChick();
					Close();
				    </script>
				</logic:equal>
				<logic:equal name="result" value="no">
					<script>
				    alert("提交失败！");
				    </script>				
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
