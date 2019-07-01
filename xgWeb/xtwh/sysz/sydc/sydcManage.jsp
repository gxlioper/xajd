<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		//查询结果
		function searchRs(){
			if(checkSjTj('kssj','开始时间','jssj','结束时间')){
				allNotEmpThenGo('/xgxt/xtwhSysz.do?method=sydcManage');
			}
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/xtwhSysz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="del_message" id="del_message" 
				value="删除的同时将清空相关统计，如果需要保留的只需关闭其‘启用’状态即可,确认删除？"/>
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="showTopWin('/xgxt/xtwhSysz.do?method=sydcUpdate','800','600');return false;"
									class="btn_zj">增加</a>
							</li>
							<li>
								<a href="#"
									onclick="showInfo('/xgxt/xtwhSysz.do?method=sydcUpdate','update','800','600');return false;"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/xtwhSysz.do?method=sydcManage','del');return false;"
									class="btn_sc">删除</a>
							</li>
							<li>
								<a href="#"
									onclick="saveUpdate('/xgxt/xtwhSysz.do?method=sydcManage&doType=save','');return false;"
									class="btn_ccg">保存</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- 按钮 end-->	
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a"/>
										<button type="button" class="btn_cx" id="search_go"
											onclick="searchRs();return false;">
											查 询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('dcnr-sfqy-kssj-jssj');return false;">
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
									调查内容
								</th>
								<td>
									<html:text property="dcnr" style="" maxlength="20" styleId="filemc"
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>						
								</td>
								<th>
									是否启用
								</th>
								<td>
									<html:select property="sfqy" style="" styleId="sfqy">
										<html:option value=""></html:option>
										<html:options collection="sfqyList" property="en" labelProperty="cn" />
									</html:select>		
								</td>
								<th>
									调查时间
								</th>
								<td>
									<html:text property="kssj" styleId="kssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 70px"
										onclick="return showCalendar('kssj','y-mm-dd');"
									/>
									-
									<html:text property="jssj" styleId="jssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 70px"
										onclick="return showCalendar('jssj','y-mm-dd');"
									/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
			</div>
			<!-- 多功能操作区end -->
			
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
							<logic:iterate id="tit" name="topTr" offset="1" length="4">
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
								ondblclick="showInfo('/xgxt/xtwhSysz.do?method=sydcUpdate','view','800','600')">
								<td align="center" width="5px">
									<input type="checkbox" id="checkVal" 
										name="primarykey_checkVal"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="1" length="1">
									<td align="left" title="<logic:iterate id="nr" name="s" offset="5" length="1">${nr }</logic:iterate>">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="2" length="2">
									<td align="left">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="4" length="1">
									<td align="left">
										<logic:equal name="v" value="是">
											<input type="radio" name="dcqy" id="dcqy${index }" value="<logic:iterate id="dc" name="s" offset="0" length="1">${dc }</logic:iterate>" checked="checked"/>
										</logic:equal>
										<logic:equal name="v" value="否">
											<input type="radio" name="dcqy" id="dcqy${index }" value="<logic:iterate id="dc" name="s" offset="0" length="1">${dc }</logic:iterate>"/>
										</logic:equal>
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