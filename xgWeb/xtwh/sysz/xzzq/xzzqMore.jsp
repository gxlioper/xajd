<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript">
		//查询结果
		function searchRs(){
			if(checkSjTj('kssj','开始时间','jssj','结束时间')){
				allNotEmpThenGo('/xgxt/xtwhSysz.do?method=xzzqView&writeAble='+'${writeAble}');
			}
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em>首页-下载专区</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/xtwhSysz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										
										<input type="hidden" name="go" value="a"/>
										<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
											查 询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('filelx-filemc-kssj-jssj');">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									文件类型
								</th>
								<td>
									<html:select property="filelx" style="" styleId="filelx">
										<html:options collection="filelxList" property="dm" labelProperty="mc" />
									</html:select>								
								</td>
								<th>
									文件名
								</th>
								<td>
									<html:text property="filemc" style="" maxlength="20" styleId="filemc"
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>
								<th>
									上传时间
								</th>
								<td>
									<html:text property="kssj" styleId="kssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 70px"
										onclick="return showCalendar('kssj','y-mm-dd');"
										onkeyup="return onlyBackSpace(this,event)"
									/>
									-
									<html:text property="jssj" styleId="jssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 70px"
										onclick="return showCalendar('jssj','y-mm-dd');"
										onkeyup="return onlyBackSpace(this,event)"
									/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
			</div>
			<!-- 多功能操作区 end -->
				
			<!-- 查询结果-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						查询结果
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font> 
						</logic:empty>
						<logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						</logic:notEmpty>
					</span>
				</h3>
				<table summary="" class="dateline" align="" width="100%">
					<!-- 表头 -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="1">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)" nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- 表头 end-->
					<!--内容 -->
					<logic:equal name="hadRs" value="yes">
						<logic:iterate name="rsArrList" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand" 
								ondblclick="showOpenInfoNew('xtwhSysz.do?method=xzzqUpdate','view',600,320);">
								<td align="center" width="5px">
									<input type="checkbox" id="checkVal" 
										name="primarykey_checkVal"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="1" length="6">
									<td align="left">
										${v }
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="7" length="1">
									<td align="left">
										<logic:notEmpty name="v">
											<a href="czxxDtjsDyxx.do?method=downLoadFile&dir=${v }" target="_blank" style="color: blue">下载附件</a>
										</logic:notEmpty>
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:equal>
					<!--内容 end-->
					<!-- 补空行 -->
					<%@ include file="/comm/noRows.jsp"%>
					<!-- 补空行 end-->
				</table>
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xtwhSyszForm"></jsp:include>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>