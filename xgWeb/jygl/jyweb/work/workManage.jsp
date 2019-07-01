<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript">
		function choiceDisabled(usertype,ele) {
   			if($("userType")){
				if ($("userType").value == usertype) {
					var tmp = ele.split("-");
					for (i = 0; i < tmp.length; i++) {
						if(document.getElementById(tmp[i])){
			  		 		document.getElementById(tmp[i]).disabled = true;
						}
					}
  		 		}  
			}
		}
	</script>
	</head>
	<body onload="choiceDisabled('dw','gsmc');">
		<logic:notEqual value="dw" name="jyweb_userType">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>企业招聘发布</a>
				</p>
			</div>
		</logic:notEqual>

		<html:form action="/jyweb" method="post">
			<input type="hidden" name="realTable" id="realTable"
				value="${realTable }" />
			<input type="hidden" id="userType" value="${jyweb_userType }" />

			<!-- 多功能操作区一 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a
								href="javascript:showTopWin('jywebUseCheckSession.do?method=workAdd&doType=add','800','580');"
								class="btn_zj"> 增加 </a>
						</li>
						<li>
							<a href="#"
								onclick="showUpdateWindow('primarykey_cbv','jywebUseCheckSession.do?method=workUpdate&doType=update','800','580')"
								class="btn_xg"> 修改 </a>
						</li>
						<li>
							<a
								href="javascript:batchData('primarykey_cbv','jywebUseCheckSession.do?method=workManage&doType=del','del')"
								class="btn_sc"> 删除 </a>
						</li>
						<logic:notEqual value="dw" name="jyweb_userType">
							<li>
								<a href="javascript:impAndChkData();" class="btn_dr"> 导入 </a>
							</li>
							<li>
								<a
									href="javascript:expData('jywebUseCheckSession.do?method=workManage&doType=expData')"
									class="btn_dc"> 导出 </a>
							</li>
						</logic:notEqual>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('jywebUseCheckSession.do?method=workManage&doType=query')">
											查 询
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									单位名称
								</th>
								<td>
									<logic:equal value="dw" name="jyweb_userType">
										<input type="hidden" name="querylike_gsmc"
											value="${jyweb_realName }" />
									</logic:equal>
									<html:text property="querylike_gsmc" maxlength="25"
										styleId="gsmc" style="width:175px"></html:text>
								</td>
								<th>
									岗位名称
								</th>
								<td>
									<html:text property="querylike_zpzw" style="width:175px"></html:text>
								</td>
								<th>
									发布时间
								</th>
								<td>
									<html:text styleId="fbsj1" 
										style="width:80px"
										property="querygreaterequal_fbsj"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur="dateFormatChg(this);"></html:text>
									-
									<html:text styleId="fbsj2" 
										style="width:80px"
										property="querylessequal_fbsj"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur="dateFormatChg(this);"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									岗位性质
								</th>
								<td>
									<html:select property="queryequals_gwxz" style="width:180px">
										<html:option value=""></html:option>
										<html:option value="全职">全职</html:option>
										<html:option value="兼职">兼职</html:option>
									</html:select>
								</td>
								<th>
									审核状态
								</th>
								<td>
									<html:select property="queryequals_xxsh" styleId="select_shzt" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="shztList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
								</th>
								<td>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- From内容 start-->
			<!---------------------表格--有复选框的数据表单------------------->
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue" >提示：单击表头可以排序</font>
						</logic:notEmpty>
					</span>
				</h3>
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="true" style="height:17.5px" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="2" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr>
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										</td>
										<td>
												<a onclick="showTopWin('jywebUseCheckSession.do?method=workUpdate&doType=view&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>','800','580');" 
													class="pointer" style="color:#0A63BF" href="#">
													<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>
												</a>
										</td>
										<logic:iterate id="v" name="s" offset="3" >
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
									<%
										for (int i = 0 ; i < (Integer)request.getAttribute("maxNum") - ((List)request.getAttribute("rs")).size() ; i++){
									
									 %>
										<tr>
											<td>
												<input type="checkbox" disabled="disabled"/>
											</td>
											<logic:iterate id="t" name="topTr" offset="2" scope="request">
												<td>
													&nbsp;
												</td>
											</logic:iterate>
										</tr>
									<%
										}
									 %>
							</logic:notEmpty>
							<logic:empty name="rs">
									<%
										for (int i = 0 ; i < (Integer)request.getAttribute("maxNum") ; i++){
									
									 %>
										<tr>
											<td>
												<input type="checkbox" disabled="disabled"/>
											</td>
											<logic:iterate id="t" name="topTr" offset="2" scope="request">
												<td>
													&nbsp;
												</td>
											</logic:iterate>
										</tr>
									<%
										}
									 %>
							</logic:empty>
						</tbody>
					</table>
					<!--分页显示-->
					<jsp:include flush="true"
						page="/jygl/jyweb/turnPage.jsp?form=jyglActionForm"></jsp:include>
					<!--分页显示-->
				
			</div>
		</html:form>
	</body>
</html>
