<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/mdqrDWR.js"></script>	
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript">
			function modi(url){
				if(curr_row != null){
					showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
					return true;
				}else{
					alert('请选择要修改的数据行！');
					return false;
				}
			}
			
			function getXm(){
				if($("xmlbdm")){
					var xmlbdm=$("xmlbdm").value;
					mdqrDWR.getXmList(xmlbdm,function(data){
						DWRUtil.removeAllOptions($("xmdm"));		
						DWRUtil.addOptions($("xmdm"),data,"dm","mc");
					});
				}
			}
			
			function checkXmlx(){ 
				if($("xmlbdm").value==""){
					alert("请选择项目类别！");
					return false;
				}
				
				if($("xmdm").value==""){
					alert("请选择项目！");
					return false;
				}
	
				return true;
			}
			
			function changeSzzq(){
				var xmdm=$("xmdm").value;
				mdqrDWR.getSzzq(xmdm,function(data){
					if(data=="xn"){
						$("showTr").style.display="";
						$("showXn").style.display="";
						$("showXq").style.display="none";
						$("xq").disabled="true";
						$("nd").disabled="true";
						$("xn").disabled="";
						$("showNd").style.display="none";
					}else if(data=="xq"){
						$("showTr").style.display="";
						$("showXn").style.display="";
						$("showXq").style.display="";
						$("nd").disabled="true";
						$("xn").disabled="";
						$("xq").disabled="";
						$("showNd").style.display="none";
					}else if(data=="nd"){
						$("showTr").style.display="";
						$("showXn").style.display="none";
						$("showXq").style.display="none";
						$("showNd").style.display="";
						$("xn").disabled="true";
						$("xq").disabled="true";
						$("nd").disabled="";
					}else if(data=="wzq"){
						$("showTr").style.display="none";
						$("xn").disabled="true";
						$("xq").disabled="true";
						$("nd").disabled="true";
					}else if(data=="jyc"){
						$("showTr").style.display="none";
						$("xn").disabled="true";
						$("xq").disabled="true";
						$("nd").disabled="true";
					}else {
						$("showTr").style.display="none";
						$("xn").disabled="true";
						$("xq").disabled="true";
						$("nd").disabled="true";
					}
				})
			}
		</script>
	</head>
	<body onload="changeSzzq()">
		
		<html:form action="/mdqr" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
		    <input type="hidden" name="realTable" id="realTable" value="mdqr_xmnrb" />
		    <input type="hidden" name="tabName" id="tabName" value="mdqr_xmnrb" />
		    <input type="hidden" name="viewName" id="viewName" value="view_mdqr_xmnrb" />
		    <input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
		     <input type="hidden" name="queryequals_mdqr" id="queryequals_mdqr" value="checked" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }-名单确认查询</a>
				</p>
			</div>
			
			<!-- 页签 -->
			<div class="compTab" id="card">
				<div class="comp_title" id="div1">
					<ul id="ul1">
						<li >
							<a href="#"
								onclick="refreshForm('mdqr.do?method=mdqrCx&gnmk=${gnmk }')"
							 	id="cx">
								<span>名单设置</span>
							</a>
						</li>
						<li class="ha">
							<a href="#" 
								onclick="if(checkXmlx){refreshForm('mdqr.do?method=mdqrQrcx&gnmk=${gnmk }')}"
							 	id="wh">
								<span>名单确认</span>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<!-- 页签 end-->
			
			<div class="toolbox">
			 <!-- 按钮 -->
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" onclick="if(checkXmlx()){expData('/xgxt/mdqr.do?method=expDate')}" class="btn_dc"> 导出 </a> </li>
			    </ul>
			 </div>
			<div class="searchtab">
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <button class="btn_cx" id="search_go" 
			              	onclick="if(checkXmlx()){allNotEmpThenGo('/xgxt/mdqr.do?method=mdqrQrcx&gnmk=${gnmk }&doType=query')}">
			              	查 询
			              </button>
			              &nbsp;&nbsp;&nbsp;&nbsp;
			              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
			              	重 置
			              </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>

					<tbody>
						<tr>
							<th >
								学号
							</th>
							<td>
								<html:text property="querylike_xh" styleId="xh"/>
							</td>
							<th>
								姓名
							</th>
							<td>
								<html:text property="querylike_xm" styleId="xm"/>
							</td>
							<th>
								年级
							</th>
							<td>
								<html:select property="queryequals_nj" styleId="nj"  style="width:150px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								<logic:equal name="userType" value="xy">
									<html:select property="xydm" styleId="xy" disabled="true" value="${userDep }" style="width:150px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
									<html:hidden property="queryequals_xydm" value="${userDep }"/>
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
								<html:select property="queryequals_xydm" styleId="xy"  style="width:150px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</logic:notEqual>
							</td>
							<th>
								专业
							</th>
							<td>
								<html:select property="queryequals_zydm" styleId="zy"  style="width:150px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								班级
							</th>
							<td>
								<html:select property="queryequals_bjdm" styleId="bj"  style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>	
						<tr>
							<th>
								项目类别
							</th>
							<td>
								<html:select property="queryequals_xmlbdm" styleId="xmlbdm"  style="width:150px"
											onchange="getXm()">
										<html:option value=""></html:option>
										<html:options collection="xmlbList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								项目
							</th>
							<td>
								<html:select property="queryequals_xmdm" styleId="xmdm" onchange="changeSzzq()" style="width:150px">
										<html:options collection="xmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<td colspan="2">
						</tr>
						<tr id="showTr" style="display:none">
							<td colspan="6">
								<span id="showXn" style="display:none">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年
								<html:select property="queryequals_xn" styleId="xn"  style="width:150px"
										disabled="true">
										<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								</span>
								<span id="showXq" style="display:none">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期
								<html:select property="queryequals_xq" styleId="xq"  style="width:150px"
										disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								</span>
								<span id="showNd" style="display:none">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年度
								<html:select property="queryequals_nd" styleId="nd"  style="width:150px"
										disabled="true">
										<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
								</span>
							</td>
						</tr>		                       
					</tbody>
				</table>
			</div>
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 	<font color="blue">提示：单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
	
			<logic:notEmpty name="rs">
				<div class="con_overlfow">
				 <table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								style="cursor:hand">
								<logic:iterate id="v" name="s" offset="1">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=mdqrForm"></jsp:include>
					<script type="text/javascript">
							$('choose').className="hide";
					</script>
					</div>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>	
  </body>
</html>
