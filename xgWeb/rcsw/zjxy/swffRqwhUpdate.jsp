<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getRcswDAO.js'></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript">	
	
	//实物发放
	function swff(){
		var mklx = $("mklx").value;
		var url = "/xgxt/zjxyRcsw.do?method=swffRqwhUpdate";
		url += "&mklx="+mklx;
		url += "&doType=ff"
		refreshForm(url);
	}
	
	//保存发放人群
	function saveFfrq(){
		var checkBoxArr = document.getElementsByName("checkVal");
		var flag = false;
		
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		if(flag){
			saveUpdate('/xgxt/zjxyRcsw.do?method=swffRqwhUpdate&doType=save','');
		}else{
			alert("请选择一个发放对象")
			return false;
		}
	}
	
	//设置发放人群
	function setFfrq(){
		var checkBoxArr = document.getElementsByName("checkVal");
		var flag = false;
		
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked=true;
		}
	}
	
	//查询发放对象
	function showFfdx(){
		var ffsj = $("ffsj").value;
		var xn = $("xn").value;
		var xq = $("xq").value;
		var nd = $("nd").value;
		var xmlx = $("xmlx").value;
		var mklx = $("mklx").value;
		var ffrq = $("yffrq").value;
		var url = "/xgxt/zjxyRcsw.do?method=bffrManage";
		url+="&mklx="+mklx;
		url+="&ffrq="+ffrq;
		url+="&xmlx="+xmlx;
		url+="&xn="+xn;
		url+="&xq="+xq;
		url+="&nd="+nd;
		url+="&ffsj="+ffsj;
		showTopWin(url,800,600);
	}
	
	//查询发放对象
	function showFf(){
		var ffsj = $("ffsj").value;
		var xn = $("xn").value;
		var xq = $("xq").value;
		var nd = $("nd").value;
		var xmlx = $("xmlx").value;
		var mklx = $("mklx").value;
		var ffrq = $("yffrq").value;
		var url = "/xgxt/zjxyRcsw.do?method=ffrManage";
		url+="&mklx="+mklx;
		url+="&ffrq="+ffrq;
		url+="&xmlx="+xmlx;
		url+="&xn="+xn;
		url+="&xq="+xq;
		url+="&nd="+nd;
		url+="&ffsj="+ffsj;
		showTopWin(url,800,600);
	}
	</script>
	</head>
	<body onload="setFfrq()">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/zjxyRcsw">
			<%@ include file="/rcsw/hiddenValue.jsp"%>
			<input type="hidden" name="rqnum" id="rqnum" value="${rqnum }" />
			<input type="hidden" name="yffrq" id="yffrq" value="${rs.ffrq }" />
			<input type="hidden" name="stuInfo" id="stuInfo" value="${xhzgh }"/>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">


				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>事物办理</span>
								</th>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								<th width="16%">
									项目名称
								</th>
								<td colspan="3">
									<html:text name="rs" property="xmmc" style="width:95%" disabled="true"/>
								</td>
							</tr>
							<tr>
								
								<th width="16%">
									<font color="red">*</font>学年
								</th>
								<td width="34%">
									<logic:equal name="doType" value="add">
										<html:select name="rs" property="xn" style="" styleId="xn"
											onchange="getZsdyxx()">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</logic:equal>
									<logic:notEqual name="doType" value="add">
										<html:hidden name='rs' property="xn" styleId="xn" />
										<html:select name="rs" property="xn" style="" styleId="xn"
											disabled="true">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</logic:notEqual>
								</td>
								<th width="16%">
									<font color="red">*</font>项目类型
								</th>
								<td width="34%">
									<logic:equal name="doType" value="add">
										<html:select name="rs" property="xmlx" style="" styleId="xmlx">
											<html:options collection="xmlxList" property="dm"
												labelProperty="mc" />
										</html:select>
									</logic:equal>
									<logic:notEqual name="doType" value="add">
										<html:hidden name='rs' property="xmlx" styleId="xmlx" />
										<html:select name="rs" property="xmlx" style="" styleId="xmlx"
											disabled="true">
											<html:options collection="xmlxList" property="dm"
												labelProperty="mc" />
										</html:select>
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>学期
								</th>
								<td>
									<logic:equal name="doType" value="add">
										<html:select name="rs" property="xq" style="" styleId="xq"
											onchange="getZsdyxx()">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</logic:equal>
									<logic:notEqual name="doType" value="add">
										<html:hidden name='rs' property="xq" styleId="xq" />
										<html:select name="rs" property="xq" style="" styleId="xq"
											disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>
									<font color="red">*</font>办理开始时间
								</th>
								<td>
									<logic:equal name="doType" value="add">
										<html:text name="rs" property="ffsj" styleId="ffsj"
											readonly="true" onblur="dateFormatChg(this)"
											style="cursor:hand;"
											onclick="return showCalendar('ffsj','y-mm-dd');" />
									</logic:equal>
									<logic:notEqual name="doType" value="add">
										<html:text name="rs" property="ffsj" readonly="true" />
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>年度
								</th>
								<td>
									<logic:equal name="doType" value="add">
										<html:select name="rs" property="nd" style="" styleId="nd">
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>
									</logic:equal>
									<logic:notEqual name="doType" value="add">
										<html:hidden name='rs' property="nd" styleId="nd" />
										<html:select name="rs" property="nd" style="" styleId="nd"
											disabled="true">
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>
									<font color="red">*</font>办理结束时间
								</th>
								<td>
									<logic:equal name="doType" value="add">
										<html:text name="rs" property="ffsj" styleId="ffsj"
											readonly="true" onblur="dateFormatChg(this)"
											style="cursor:hand;"
											onclick="return showCalendar('ffsj','y-mm-dd');" />
									</logic:equal>
									<logic:notEqual name="doType" value="add">
										<html:text name="rs" property="ffjssj" readonly="true" />
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<th>
									备注
								</th>
								<td colspan="3">
									<html:textarea name="rs" property="bz" style="width: 100%;"
										rows="5" onblur="chLeng(this,500)" readonly="true" />
								</td>
							</tr>
						</tbody>
						<thead>
							<tr>
								<td colspan="4" align="center">
									<span>办理对象&nbsp;&nbsp;
										<logic:notEqual name="doType" value="view">
												<logic:equal name="xxdm" value="13275">
													<button type="button" onclick="showFf()"  >
														+
													</button>
												</logic:equal>
												<logic:notEqual name="xxdm" value="13275">
													<button type="button" onclick="showFfdx()" >
														+
													</button>
												</logic:notEqual>
										</logic:notEqual>
									</span>
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4">

									<div class="formbox">
										<logic:empty name="rsList">
													<font color="red">未找到任何记录！</font>
												</logic:empty>

										<logic:notEmpty name="rsList">
											<table summary="" class="dateline" align="" width="100%">
												<thead>
													<tr align="center" style="cursor:hand">
														<td>
															<input type="checkbox" id="selall" name="selall"
																onclick="selAll()" />
														</td>
														<logic:iterate id="tit" name="topTr" offset="0">
															<td id="<bean:write name="tit" property="en"/>"
																 nowrap>
																<bean:write name="tit" property="cn" />
															</td>
														</logic:iterate>
													</tr>
												</thead>
												<tbody>
													<logic:iterate name="rsList" id="s" indexId="index">
														<tr>
															<td align="center">
																<input type="checkbox" id="checkVal" name="checkVal"
																	value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
																<input type="hidden" name="xhzgh"
																	<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>
																	value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
															</td>
															<logic:iterate id="v" name="s" offset="1">
																<td align="left">
																	<bean:write name="v" />
																</td>
															</logic:iterate>
														</tr>
													</logic:iterate>
												</tbody>
											</table>
											<!--分页显示-->
											<jsp:include flush="true"
												page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
											<!--分页显示-->
										</logic:notEmpty>
									</div>

								</td>
							</tr>
						<logic:notEqual name="xxdm" value="13275">
						<tr>
							<th>
								发放备注
								<br />
								<font color="red">(限500字)</font>
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="ffbz" style="width: 100%;"
									rows="5" onblur="chLeng(this,500)" />
							</td>
						</tr>
						</logic:notEqual>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" id="search_go" onclick="swff()"
												style="display : none">
												查 询
											</button>
										<logic:notEqual name="doType" value="view">
											<button type="button" id="buttonSave"
												onclick="saveUpdate('/xgxt/zjxyRcsw.do?method=swffRqwhUpdate&doType=save','');">
												保 存
											</button>
											
										</logic:notEqual>
										<button type="button" id="buttonClose" onclick="Close();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
					</div>
					<logic:present name="result">
						<logic:equal value="true" name="result">
							<script>
						alert('操作成功！');
						dialogArgumentsQueryChick();
						window.close();
					</script>
						</logic:equal>
						<logic:equal value="false" name="result">
							<script>
						alert('操作失败！');
					</script>
						</logic:equal>
					</logic:present>
		</html:form>
	</body>
</html>
