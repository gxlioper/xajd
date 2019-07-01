<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：qlj -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/gyglCwgl.js"></script>
	</head>
	<body >
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>

		<!-- 标题 end-->
		<html:form action="/cdfzPjpy">
		<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
		    <input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
		    <input type="hidden" name="isBzr" id="isBzr" value="${bzrQx}" />
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<div class="toolbox">
			 <!-- 按钮 -->
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" id="a_dc" onclick="expData('/xgxt/cdfzPjpy.do?method=printGrhjhzManage')" class="btn_dc"> 导出 </a> </li>
			    </ul>
			 </div>
			<div class="searchtab">
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="8">
			            <div class="btn">
			              <button class="btn_cx" id="search_go" 
			              	onclick="allNotEmpThenGo('/xgxt/cdfzPjpy.do?method=grhjhzManage')">
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
							<th>
								年级
							</th>
							<td>
								<html:select property="nj" styleId="nj"  style="width:150px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								<logic:equal name="userType" value="xy">
									<html:select property="queryequals_xydm" styleId="xy" disabled="true" value="${userDep }" style="width:150px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
									<html:hidden property="xydm" value="${userDep }"/>
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
								<html:select property="xydm" styleId="xy"  style="width:150px"
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
								<html:select property="zydm" styleId="zy"  style="width:150px"
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
								<html:select property="bjdm" styleId="bj"  style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>	
						<tr id="showTr" >
							
							<th>
								学年
							</th>
							<td>
								<html:select property="pjxn" styleId="xn"  style="width:150px"
										>
										<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>
								学期
							</th>
							<td>
								<html:select property="pjxq" styleId="xq"  style="width:150px"
										>
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
							<th>
								学号
							</th>
							<td>
								<html:text property="xh" styleId="xh" style="" maxlength="20"></html:text>
							</td>
							<th>
								姓名
							</th>
							<td>
								<html:text property="xm" styleId="xm" style="" maxlength="20"></html:text>
							</td>
						</tr>		                       
					</tbody>
				</table>
			</div>
			</div>
			
			<!-- 多功能操作区 end -->

			<!-- 查询结果-->
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span> 查询结果 <logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;<font color="blue"></font>
						</logic:notEmpty> <%--						<font color="blue"></font>--%> </span>
				</h3>
				<div class="con_overlfow">
					<table summary="" class="dateline" align="" width="100%">
						<!-- 表头 -->
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" >
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- 表头 end-->
						<!--内容 -->
						<tbody>
						<logic:notEmpty name="rsArrList">
						
							<logic:iterate name="rsArrList" id="ss" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<!-- 显示信息 -->
									<logic:iterate id="v" name="ss" >
										<td align="left" nowrap="nowrap">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<!-- 补空行 -->
							<%@ include file="/comm/noRows.jsp"%>
							<!-- 补空行 end-->
						<!--内容 end-->
						</tbody>
					</table>
				</div>
				<!--分页-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=cdfzPjpyForm"></jsp:include>
				<!--分页end-->
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
			</div>
			<!-- 查询结果 end-->
		</html:form>
	</body>
</html>
