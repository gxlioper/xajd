<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="xgxt.szdw.bjlh.SzkhCssz"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getXmlgSzdwDAO.js'></script>
	<script language="javascript">
	function changeInput(obj){
		checkInputNum(obj);
		if(obj.value=="")return false;
		if(obj == document.getElementById("jskhpfbl")){
			document.getElementById("xskhpfbl").value = 100 - eval(document.getElementById("jskhpfbl").value);
		}else if(obj == document.getElementById("xskhpfbl")){
			document.getElementById("jskhpfbl").value = 100 - eval(document.getElementById("xskhpfbl").value);
		}
		<%--
		if((document.getElementById("xspfyxbl").value)>eval(document.getElementById("xskhpfbl").value)){
			alert("学生有效评分比例不能大于学生评分比例！");
			document.getElementById("xspfyxbl").value="";
			document.getElementById("xspfyxbl").focus();
			return false;
		}
		--%>
		
	}
	function saveCssz(obj){
		var url = '/xgxt/bjlh_cssz.do?method=fdykhCssz&doType=save';
		var khkssj = document.getElementById("khkssj").value;
		var khjssj = document.getElementById("khjssj").value;
		var xxdm = document.getElementById("xxdm").value;
		if(khkssj != "" && khjssj != "" && khkssj > khjssj){
			alert("时间段先后次序不正确，请确认！");
			return false;
		}
		//池州职业技术学院个性化
		if(xxdm !=null && "13060"==xxdm){
			var khlrjzsj = document.getElementById("khlrjzsj").value;
			if(khlrjzsj<khkssj || khlrjzsj>khjssj){
				alert("考核录入截止时间不在考核开始时间和考核结束时间范围内，请确认！");
				return false;
			}
			saveUpdate(url,'xn-khkssj-khjssj-jskhpfbl-xskhpfbl-xspfyxbl-khlrjzsj');
		}else{
			saveUpdate(url,'xn-khkssj-khjssj-jskhpfbl-xskhpfbl-xspfyxbl');
		}
		
	}
</script>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /> </a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/bjlh_cssz" method="post">
			<div class="tab">
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }"/>
				<table class="formlist" border="0" align="center"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>参数设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>默认学年：
							</td>
							<td align="left" style="width: 60%">
								<html:select name="rs" property="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>考核是否启动：
							</td>
							<td align="left" style="width: 60%">
								<html:radio name="rs" property="khsfqd" value="1">是</html:radio>
								<html:radio name="rs" property="khsfqd" value="0">否</html:radio>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>考核开始时间：
							</td>
							<td >
								<html:text name="rs" property="khkssj" styleId="khkssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('khkssj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>考核结束时间：
							</td>
							<td >
								<html:text name="rs" property="khjssj" styleId="khjssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('khjssj','y-mm-dd');" />
							</td>
						</tr>
						<!-- 池州职业技术学院个性化begin -->
						<logic:equal name="xxdm" value="13060">
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>考核录入截止时间：
							</td>
							<td >
								<html:text name="rs" property="khlrjzsj" styleId="khlrjzsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('khlrjzsj','y-mm-dd');" />
							</td>
						</tr>
						</logic:equal>
						<!-- 池州职业技术学院个性化end -->
						<!-- 南通高师个性化begin -->
						<logic:notEqual name="xxdm" value="110501">
						<!-- 考核人员不为all时没有比例分配 -->
						<%if("all".equalsIgnoreCase(SzkhCssz.KHRY)){%>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>教师考核评分比例：
							</td>
							<td align="left" style="width: 50%">
								<html:text name="rs" property="jskhpfbl"
									onblur="changeInput(this)" styleId="jskhpfbl" maxlength="3" />
								%
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>学生考核评分比例：
							</td>
							<td align="left" style="width: 50%">
								<html:text name="rs" property="xskhpfbl"
									onblur="changeInput(this)" styleId="xskhpfbl" maxlength="3" />
								%
							</td>
						</tr>
						<%} %>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>学生评分有效比例：
							</td>
							<td align="left" style="width: 50%">
								<html:text name="rs" property="xspfyxbl"
									onblur="changeInput(this)" styleId="xspfyxbl" maxlength="3" />
								%
							</td>
						</tr>
						</logic:notEqual>
					</tbody>
					<tfoot>
						<tr>
							<td align="center" colspan="2">							
			        			<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveCssz(this);" id="buttonSave">
										保存
									</button>
									<button type="button" name="重置" type="reset">重 置</button>									
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<logic:present name="message1">
	<script defer="defer">
		if('${message1}' && '${message1}' != ""){
			
			alertInfo('${message1}');
			
		}
	</script>
</logic:present>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>