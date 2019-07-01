<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/rcsw/rcswFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">	
	function swff(doType){
		if(curr_row == null){
			alert('请选择要发放的项目！');
			return false;
		}
		var mklx = $("mklx").value;
		var url = "/xgxt/zjxyRcsw.do?method=swffRqwhUpdate";
		url += "&mklx="+mklx;
		showInfo(url,doType,800,600);
	}
	
	function swffFfjg(doType){
		var pkValue="";
		if(curr_row == null){
			alert('请选择要发放的项目！');
			return false;
		}else{
			pkValue+="&pkValue="+curr_row.getElementsByTagName('input')[0].value;
		}
		var mklx = $("mklx").value;
		var url = "/xgxt/zjxyRcsw.do?method=swffFfjgUpdate";
		url += "&mklx="+mklx+pkValue;
		refreshForm(url);
	}
	
	function tjmd(url){
		if(curr_row == null){
			alert('请选择要操作的数据！');
			return false;
		}
		
		if(0==curr_row.cells[5].innerText){
			alert("该项目没有需要提交的名单！");
			return false;
		}
		
		if (confirm("确定要在新闻模块发布该项目名单?")) {
			var pk = curr_row.getElementsByTagName('input')[0].value;
			url+="&pk="+pk;
			url+="&doType=tj";
			refreshForm(url);
		}
	}
	
	function checkTime(){
		if($("querygreaterequal_ffsj") && $("querylessequal_ffsj")){
			if(checkSearchTj("querygreaterequal_ffsj","querylessequal_ffsj")){
				return true;
			}else{
				return false;
			}
		}
	}
	
	function rqexpData(){
		var pk="";
		if(curr_row != null){
			pk ="&pk="+ curr_row.getElementsByTagName('input')[0].value;
		}
		
		expData('/xgxt/zjxyRcsw.do?method=expDate'+pk);
	}
	
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/zjxyRcsw" method="post">
			<input type="hidden" name="viewName" id="viewName" value="view_rcsw_swffrqwh"/>
			<input type="hidden" name="tableName" id="tableName" value="rcsw_swffrqb"/>
			<%@ include file="/rcsw/hiddenValue.jsp"%>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" /> </font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
					<!-- 按钮 -->
					<logic:equal value="yes" name="writeAble">
						<div class="buttonbox">
							<ul>
								<li>
									<logic:equal name="xxdm" value="13275">
										<a href="#" onclick="swffFfjg('ff')" class="btn_ck"> 发放</a>
									</logic:equal>
									<logic:notEqual name="xxdm" value="13275">
										<a href="#" onclick="swff('ff')" class="btn_ck"> 发放</a>
									</logic:notEqual>
								</li>
								<logic:equal name="xxdm" value="13275">
										<li>
											<a href="#"
												onclick="tjmd('/xgxt/zjxyRcsw.do?method=swffFfjgManage')"
												class="btn_ccg">发布名单</a>
										</li>
								</logic:equal>
								<logic:notEqual name="xxdm" value="13275">
									<logic:equal name="mklx" value="xs">
										<li>
											<a href="#"
												onclick="tjmd('/xgxt/zjxyRcsw.do?method=swffXsffManage')"
												class="btn_ccg">发布名单</a>
										</li>
	
									</logic:equal>
									<!-- 老师发放 -->
									<logic:equal name="mklx" value="ls">
										<li>
											<a href="#"
												onclick="tjmd('/xgxt/zjxyRcsw.do?method=swffLsffManage')"
												class="btn_ccg">发布名单</a>
										</li>
									</logic:equal>
								</logic:notEqual>
								<li>
									<a href="#" onclick="rqexpData()" class="btn_dc"> 导出 </a>
								</li>
							</ul>
						</div>
					</logic:equal>
					<div class="searchtab">
						<table width="100%" border="0">
							<tfoot>
								<tr>
									<td colspan="6">
										<div class="btn">
											<!-- 学生发放 -->
											<logic:equal name="xxdm" value="13275">
												<button type="button" class="btn_cx" id="search_go"
														onclick="if(checkTime()){allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffFfjgManage');}">
														查 询
												</button>
											</logic:equal>
											<logic:notEqual name="xxdm" value="13275">
												<logic:equal name="mklx" value="xs">
													<button type="button" class="btn_cx" id="search_go"
														onclick="if(checkTime()){allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffXsffManage');}">
														查 询
													</button>
												</logic:equal>
												<!-- 老师发放 -->
												<logic:equal name="mklx" value="ls">
													<button type="button" class="btn_cx" id="search_go"
														onclick="if(checkTime()){allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffLsffManage');}">
														查 询
													</button>
												</logic:equal>
											</logic:notEqual>
											<input type="hidden" name="go" value="a" />
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
										学年
									</th>
									<td>
										<html:select property="queryequals_xn" style="" onchange="">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										学期
									</th>
									<td>
										<html:select property="queryequals_xq" style="" onchange="">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
										年度
									</th>
									<td>
										<html:select property="queryequals_nd" style="" onchange="">
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										项目类型
									</th>
									<td>
										<html:select property="queryequals_xmlx" style=""
											styleId="xmlx">
											<html:options collection="xmlxList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										项目名称
									</th>
									<td>
										<html:text property="querylike_xmmc" style="" styleId="xmmc"/>
									</td>
									<th>
										办理开始时间
									</th>
									<td>
										<html:text property="querygreaterequal_ffsj"
											styleId="querygreaterequal_ffsj" onblur="dateFormatChg(this)"
											style="cursor:hand;width:80px"
											onclick="return showCalendar('querygreaterequal_ffsj','y-mm-dd');"
											onkeypress="return onlyNum(this,8)"  />
										--
										<html:text property="querylessequal_ffsj"
											styleId="querylessequal_ffsj" onblur="dateFormatChg(this)"
											style="cursor:hand;width:80px"
											onclick="return showCalendar('querylessequal_ffsj','y-mm-dd');"
											onkeypress="return onlyNum(this,8)"  />
									</td>

								</tr>
								<tr>
									<th>
										办理结束时间
									</th>
									<td colspan="5">
										<html:text property="kssj"  styleId="kssj"
											onblur="dateFormatChg(this)"
											style="cursor:hand;width:80px"
											onclick="return showCalendar('kssj','y-mm-dd');"
											onkeypress="return onlyNum(this,8)"  />
										--
										<html:text property="jssj"  styleId="jssj"
											 onblur="dateFormatChg(this)"
											style="cursor:hand;width:80px"
											onclick="return showCalendar('jssj','y-mm-dd');"
											onkeypress="return onlyNum(this,8)"  />
									</td>
									
								</tr>
							</tbody>
						</table>
					</div>
				<div class="formbox">
				
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
				    		<logic:empty name="rs">
							<font color="red">未找到任何记录！</font> 
							</logic:empty>
							<logic:notEmpty name="rs">
								<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font>
							</logic:notEmpty>
				    </span>
				    </h3>
				 
				<logic:notEmpty name="rs">
							<table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rs" id="s" indexId="index">
										<tr onclick="rowOnClick(this);" style="cursor:hand"
											ondblclick="swff('view')"
											>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<td align="center">
													<bean:write name="v" />
													<input type="checkbox" id="checkVal"
														name="primarykey_checkVal" style="display : none"
														value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
												</td>
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="2">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
							<!--分页显示-->
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
							<script type="text/javascript">
								$('choose').className="hide";
							</script>
							<!--分页显示-->
						</logic:notEmpty>
					</div>
				</div>
			</logic:empty>
		</html:form>
		<logic:present name="result">
		<logic:equal value="true" name="result">
			<script>
				$("doType").value="";
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				$("doType").value="";
				alert("操作失败");
			</script>
		</logic:equal>
		</logic:present>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
