<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script language="javascript">	
	function saveLqtz(){
			refreshForm('/xgxt/zjxyRcsw.do?method=swffXmwhManage&doType=lqtz');
			hiddenMessage(true,true);
			BatAlert.showTips('正在操作，请稍等...');
	}
	
	
	</script>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/zjxyRcsw" method="post">
			<input type="hidden" name="viewName" id="viewName"
				value="view_rcsw_swffxmwh" />
			<input type="hidden" name="tableName" id="tableName"
				value="rcsw_swffxmwhb" />
			<%@ include file="/rcsw/hiddenValue.jsp"%>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" />
					</font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
					<!-- 按钮 -->
					<logic:equal value="yes" name="writeAble">
						<div class="buttonbox">
							<ul>
								<li>
									<a href="#"
										onclick="showTopWin('/xgxt/zjxyRcsw.do?method=swffXmwhUpdate',800,600);"
										class="btn_zj"> 增加 </a>
								</li>
								<li>
									<a href="#"
										onclick="showInfo('/xgxt/zjxyRcsw.do?method=swffXmwhUpdate','update',800,600);"
										class="btn_xg"> 修改 </a>
								</li>
								<li>
									<a href="#"
										onclick="sumitInfo('/xgxt/zjxyRcsw.do?method=swffXmwhManage','del');"
										class="btn_sc"> 删除 </a>
								</li>
								<li>
									<a href="#" onclick="impAndChkData()" class="btn_dr"> 导入 </a>
								</li>
								<li>
									<a href="#"
										onclick="expData('/xgxt/zjxyRcsw.do?method=expDate')"
										class="btn_dc"> 导出 </a>
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
											<input type="hidden" name="go" value="a" />
											<button type="button" class="btn_cx" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffXmwhManage');">
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
						<logic:empty name="rs">
							<h3 class="datetitle_01">
								<span> 查询结果&nbsp;&nbsp; <font color="red">未找到任何记录！</font>
								</span>
							</h3>
						</logic:empty>
						<logic:notEmpty name="rs">
							<h3 class="datetitle_01">
								<span> 查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font>
								</span>
							</h3>
							<table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr>
										<td>
											<input type="checkbox" id="selall" name="selall"
												onclick="selAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="2">
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
											ondblclick="showInfo('/xgxt/zjxyRcsw.do?method=swffXmwhUpdate','view',800,600)">
											<td>
												<input type="checkbox" id="checkVal"
													name="primarykey_checkVal"
													<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											</td>
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
							<!--分页显示-->
						</logic:notEmpty>
					</div>
				</div>
			</logic:empty>
			<div id="divq" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<td>
									<span color="blue"> 项目类别和项目名称将影响保存的结果。 </span>
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									是否需要领取通知
									<select name="lqtz" id="select_lqtz" style="width:100px">
										<option value="需要">
											需要
										</option>
										<option value="不需要">
											不需要
										</option>
									</select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td align='right'>
									<button type="button" onclick='saveLqtz()'>
										确定
									</button>
									<button type="button" class='button2' onclick='hiddenMessage(true,true);'>
										关闭
									</button>
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
				$("doType").value="";
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
				$("doType").value="";
			</script>
		</logic:equal>
	</body>
</html>
