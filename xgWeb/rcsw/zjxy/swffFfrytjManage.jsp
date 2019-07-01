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
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/mdqrDWR.js"></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript">
			function showXmxz(){
				showTopWin('/xgxt/zjxyRcsw.do?method=swffXmxz&doType=query','680','480');
			}
			
			function showLqtz(){
				var blog=false;
				for (i=0; i<document.getElementsByName("checkVal").length; i++){
			    	if(document.getElementsByName("checkVal")[i].checked){
			    		blog=true;
			    	}
				}
				if(blog){
					viewTempDiv('领取通知','divq',360,160);
				}else {
					alert("请选择要操作的记录!");
				}
		    }
			
			function showFfryUpdate(){
				var url = "/xgxt/zjxyRcsw.do?method=swffFfrytjUpdate&doType=query";
				if(curr_row){
					url+="&pkValue="+curr_row.getElementsByTagName('input')[0].value;
				}
				showTopWin(url,500,500);
			}
			
			function saveFfry(){
				
				refreshForm('/xgxt/zjxyRcsw.do?method=swffFfryManage&doType=save');
				hiddenMessage(true,true);
				BatAlert.showTips('正在操作，请稍等...');
			}
			
		</script>
	</head>
	<body>

		<html:form action="/zjxyRcsw" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
			<input type="hidden" name="viewName" id="viewName" value="view_xg_swff_ffryb"/>
			<input type="hidden" name="tableName" id="tableName" value="rcsw_swffrqb"/>
			<html:hidden property="xn" />
			<html:hidden property="xq" />
			<html:hidden property="nd" />
			<html:hidden property="xmlx"/>
			<html:hidden property="ffsj"/>
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>



			<div class="toolbox">
				
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="bz">
										<logic:notEmpty name="xmxxMap">
											<font color="blue">项目类型：${xmxxMap.xmmc }&nbsp;&nbsp;&nbsp;&nbsp;学年：${xmxxMap.xn }&nbsp;&nbsp;&nbsp;&nbsp;学期：${xmxxMap.xqmc }&nbsp;&nbsp;&nbsp;&nbsp;年度：${xmxxMap.nd }&nbsp;&nbsp;&nbsp;&nbsp;发放时间：${xmxxMap.ffsj }</font>
										</logic:notEmpty>
									</div>
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffFfryManage&doType=query')">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									学号
								</th>
								<td>
									<html:text property="querylike_xh" style="width:150px"
										styleId="xh" />
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="querylike_xm" style="width:150px"
										styleId="xm" />
								</td>
								<th>
									年级
								</th>
								<td>
									<html:select property="queryequals_nj" styleId="nj"
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
										<html:select property="xydm" styleId="xy" style="width:150px"
											onchange="initZyList();initBjList();" disabled="true"
											value="${userDep }">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="queryequals_xydm" value="${userDep}" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="queryequals_xydm" styleId="xy"
											style="width:150px" onchange="initZyList();initBjList();">
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
									<html:select property="queryequals_zydm" styleId="zy"
										onchange="initBjList();" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="queryequals_bjdm" styleId="bj"
										style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									办理次数
								</th>
								<td>
									<html:select property="queryequals_ffcs" styleId="ffcs"
										style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="ffcsList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									领取次数
								</th>
								<td>
									<html:select property="queryequals_lqcs" styleId="lqcs"
										 style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="lqcsList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									查询范围
								</th>
								<td>
									<html:radio property="cxfw" value="">全部</html:radio>
									<html:radio property="cxfw" value="1">领取次数大于办理次数</html:radio>

								</td>
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">提示：双击一条记录可以显示详细信息；单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
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
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" ondblclick="showFfryUpdate()">
									<td style="display:none">
									<input type="hidden" name="xhzgh" id="xhzgh"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
				</logic:notEmpty>
			</div>
			<div id="divq" style="display: none">
				<div class="tab">
				<table class="formlist">
					<thead>
						<tr><td>
							<span color="blue">
								项目类别和项目名称将影响保存的结果。
							</span>
						</td></tr>
					</thead>
					<tbody>
					<tr>
						<td>
						是否需要短信通知
						<select name="lqtz" id="select_lqtz" style="width:100px">
							<option value="需要">需要</option>
							<option value="不需要">不需要</option>
						</select>
						</td>
					</tr>
					</tbody>
					<tfoot>
					<tr>
						<td   align='right'>
							<button type="button" onclick='saveFfry()'>确定</button>
							<button type="button" class='button2' onclick='hiddenMessage(true,true);'>关闭</button>	 
						</td>
					</tr>
					</tfoot>
				</table>
				</div>
			</div>	
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
	</body>
</html>
