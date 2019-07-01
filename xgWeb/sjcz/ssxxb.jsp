<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<script language="javascript">
</script>
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="checkWinType();tsfjxs();xlcws();">
			<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置</em><a>公寓管理 - 信息维护 - 房源库维护</a>
			</p>
		</div>
		<!-- 标题 end-->
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript'src='/xgxt/dwr/interface/gyglShareData.js'></script>		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<html:form action="/data_search" method="post">
							<logic:empty name="rs">
								<br />
								<br />
								<p align="center">
									<!--  有错误发生！-->
								</p>
							</logic:empty>
							<logic:notEmpty name="rs">
								<input type="hidden" id="doType" name="doType" value="<bean:write name="doType" scope="request"/>" />
								<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name="pkValue" scope="request"/>" />
								<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
								<input type="hidden" id="disableEle" name="disableEle" value="" />
								<input type="hidden" id="readonlyEle" name="readonlyEle" value="ssbh" />
								<input type="hidden" id="userOnLine" name="userOnLine" value="<bean:write name="userOnLine" scope="session"/>" />
								<input type="hidden" id="url" name="url" value="/sjcz/ssxxb.jsp" />	
								<!-- 项目基本情况 -->
								<div class="tab">		
								<table class="formlist" border="0" align="center" style="width: 100%">
									<thead>
										<tr>
											<th colspan="4">
												<span>房源库维护</span>
											</th>
										</tr>
									</thead>
									<tbody>		
										<tr>
											<th align="right">
												<font color="red">*</font>楼栋名称
											</th>
											<td align="left">
<%--											<logic:present name="showdzdx">--%>
											<html:select name="rs" property="lddm" 
													styleId="lddm" onchange="getLcList2();if(document.forms[0].qsh.value!=''&&document.forms[0].lddm.value!='') document.forms[0].ssbh.value = document.forms[0].lddm.value+'-'+document.forms[0].qsh.value">
													<html:option value="">--请选择--</html:option>
													<html:options collection="ldList" property="lddm"
														labelProperty="ldmc" />
												</html:select>
<%--											</logic:present>--%>
<%--											<logic:notPresent name="showdzdx">--%>
<%--											<html:select name="rs" property="lddm" style="width:150px"--%>
<%--													styleId="lddm" onchange="if(document.forms[0].qsh.value!=''&&document.forms[0].lddm.value!='') document.forms[0].ssbh.value = document.forms[0].lddm.value+'-'+document.forms[0].qsh.value">--%>
<%--													<html:option value=""></html:option>--%>
<%--													<html:options collection="ldList" property="lddm"--%>
<%--														labelProperty="ldmc" />--%>
<%--												</html:select>--%>
<%--											</logic:notPresent>--%>
												
												
											</td>
											<th align="right">
												<font color="red">*</font>寝室编号
											</th>
											<td align="left">
												<html:text name='rs' property="ssbh" style="width: 120px"
													styleId="ssbh" readonly="true"/>
											</td>
										</tr>
										<tr>							
											<th align="right">
												<font color="red">*</font>寝室号
											</th>
											<td align="left">
												<html:text name='rs' property="qsh" style="width: 120px"
													styleId="qsh" maxlength="10"
													onchange="if(document.forms[0].qsh.value!=''&&document.forms[0].lddm.value!='') document.forms[0].ssbh.value = document.forms[0].lddm.value+'-'+document.forms[0].qsh.value" />
											</td>
											<th align="right">
												寝室电话
											</th>
											<td align="left">
												<html:text name='rs' property="qsdh" 
													style="width: 120px;ime-mode:disabled"
													onkeydown="return onlyNum(this,15)"
													onmousedown="return onlyNum(this,15)"
													styleId="qsdh" />
											</td>
										</tr>
										<tr>
											<th align="right">
												<font color="red">*</font>床位数
											</th>
											<td align="left">
												<html:text name='rs' property="cws"
													styleId="cws" 
													onkeydown="return onlyNum(this,2)"
													onmousedown="return onlyNum(this,2)"
													style="width: 120px;ime-mode:disabled"/>
											</td>
											<!-- 长沙民政职业技术<bean:message key="lable.xsgzyxpzxy" /> 隐藏分配标记 -->
											<logic:notEqual value="10827" name="xxdm">
												<th align="right">
													<font color="red">*</font>分配标记													
												</th>
												<td align="left">
<%--												<logic:present name="showdzdx">--%>
													<html:select name="rs" property="fpbj" style="width:150px" onchange="tsfjxs()"
														styleId="fpbj">
														<html:option value=""></html:option>
														<html:options collection="fpbjList" property="en" labelProperty="cn"/>
													</html:select>
<%--												</logic:present>--%>
<%--												<logic:notPresent name="showdzdx">														--%>
<%--													<html:select name="rs" property="fpbj" style="width:150px"--%>
<%--														styleId="fpbj" onchange="tsfjxs()">--%>
<%--														<html:option value=""></html:option>--%>
<%--															<html:option value="一般">一般(本科、专科)</html:option>															--%>
<%--															<html:option value="保留">保留</html:option>--%>
<%--														</html:select>													--%>
<%--												</logic:notPresent>--%>
												</td>
											</logic:notEqual>
											<logic:equal value="10827" name="xxdm">
												<td align="right">
												</td>
												<td align="right">
												</td>
												<input type="hidden" name="fpbj" id="fpbj" value="一般">
											</logic:equal>
										</tr>
										<logic:equal name="xxdm" value="11417">
										<tr>
											<th align="right">
												可分配床位
											</th>
											<td align="left">
												<html:text name='rs' property="kfpcws" style="width: 120px"
														styleId="kfpcws" onblur="getXls()" onkeyup="value=value.replace(/[^\d]/g,'') "/>
											</td>
											<th align="right">
												行李床位数
											</th>
											<td align="left">
												<html:text name='rs' property="xlcws" style="width: 120px"
														styleId="xlcws" readonly="true"/>
											</td>
										</tr>
										</logic:equal>
										<logic:present name="showhzy">
											<tr>
												<th align="right">
													生活区
												</th>
												<td align="left">
													<html:text name='rs' property="shq" style="width: 120px"
														styleId="shq" />
												</td>
												<td align="right">
<%--													层数--%>
												</td>
												<td align="left">
<%--													<html:text name='rs' property="cs" style="width: 120px"--%>
<%--														styleId="c" onkeypress="return numJc(this,3)"/>--%>
												</td>
											</tr>
											<tr>
												<th align="right">
													电信电话
												</th>
												<td align="left">
													<html:text name='rs' property="dxdh" style="width: 120px"
														styleId="dx" />
												</td>
												<th align="right">
													铁通电话
												</th>
												<td align="left">
													<html:text name='rs' property="ttdh" style="width: 120px"
														styleId="tt" />
												</td>
											</tr>
											<tr>
												<th align="right">
													网线端口
												</th>
												<td align="left">
													<html:text name='rs' property="wxport" style="width: 120px"
														styleId="port" />
												</td>
												<td align="right">
												</td>
												<td align="left">
												</td>
											</tr>
										</logic:present>										
											<tr style="">
												<th align="right">
													<font color="red">*</font>所在层数
												</th>
												<td align="left">
													<html:select name="rs" property="cs" styleId="cs" styleClass="select" style="width:90px">														
														<html:options collection="csList" property="dm" labelProperty="mc"/>
													</html:select>
												</td>
												
												<th align="right">
													收费标准
												</th>
												<td align="left">
													<html:text name='rs'  
													style="width: 120px;ime-mode:disabled"
													onkeydown="return onlyNum(this,7)"
													onmousedown="return onlyNum(this,7)"
													property="sfbz" style="width: 120px;ime-mode:disabled"
													styleId="sfbz" />
												</td>
												
											</tr>
										<tr id="tsfjid" style="display: none">
											<th align="right">
												特殊房间说明
											</th>
											<td align="left">
												<html:text name='rs' property="sffqfj" style="width: 120px"
													maxlength="15" />
											</td>
											<td align="right">
											</td>
											<td align="left">
											</td>
										</tr>
										<tr>
											<th align="right">
												备注<br>
												<font color="blue">限150个字符</font>
											</th>
											<td colspan="3">
												<html:textarea name='rs' property='bz' style="width:400px"
													onblur="chLeng(this,150)"
													rows='4' />
											</td>
										</tr>
										</tbody>
									</table>
									<table border="0" class="formlist" align="center" style="width: 100%">
										<tfoot>
											<tr>
												<td colspan="4">
													<div class="bz">"<span class="red">*</span>"为必填项；
													<font color="blue">寝室编号由楼栋+寝室号自动生成</font>
													</div>
													<div class="btn">	
														<logic:notEqual name="doType" value="view">
															<button type="button" class="button2"
																onclick="isSsbhExists()"
																style="width:80px" id="buttonSave">
																保 存
															</button>	
														</logic:notEqual>
														&nbsp;&nbsp;&nbsp;&nbsp;
														<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
															id="buttonClose">
															关 闭
														</button>
													</div>
												</td>
											</tr>
										</tfoot>
							</logic:notEmpty>
			<logic:equal value="true" name="isSuccess">
			  <script type="text/javascript">
			    alert('操作成功！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="isSuccess">
			  <script type="text/javascript">
			    alert('操作失败！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>		
		</html:form>
	</body>
	<script type="text/javascript">
	   function tsfjxs(){	       
	       var fpbzV = $("fpbj").value;
	       if(fpbzV=="保留"){
	           $("tsfjid").style.display="";
	       }else{
	           $("tsfjid").style.display="none";
	       }
	   }
	   
	function getXls(){
	   	var cws = $("cws").value;
	   	var kfpcws = $("kfpcws").value;
	   	if( cws != "" && kfpcws != ""){
	   		if(cws<(parseInt(kfpcws)+1)){
	   			alert("可分配床位数与行李床位之和，不能大于床位总数，请确认");
	   			$("kfpcws").focus();
	   		}else{
			   	var xlcws = parseInt(cws) - parseInt(kfpcws);
			   	$("xlcws").value = xlcws;
		   	}
	   	}else if(kfpcws == ""){
	   		$("xlcws").value = "0";
	   	}
	}
	
	function xlcws(){
	   if($("xxdm").value=="11417"){	    
		   var xlcws =$("xlcws").value;
		   if(xlcws == ""){
			  $("xlcws").value = 0;
		   }
		}
	}
	</script>
</html>
