<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script>
			function xfhjsh() {
				var array = document.getElementsByName("pk");
				var pk = "";
				var num = 0;
				for (var i=0;i<array.length;i++) {
					if (array[i].checked) {
						pk += array[i].value+"!@";
						num++;
					}
				}
				if (num==0) {
					alert("请选择要操作的数据！");
					return false;
				}
				var doType = num==1 ? 'dg' : 'pl';
				pk = num==1 ? pk.replace('!@','') : pk;
				if (num==1) {
					showTopWin('rcsw_nthy_xfhjsjsh.do?doType='+doType+'&num='+num+'&pk='+pk, 700,600);
				} else {
					showTopWin('rcsw_nthy_xfhjsjsh.do?doType='+doType+'&num='+num+'&pk='+pk, 400,300);
				}
				
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>

		<!-- 标题 end-->
		<html:form action="/rcsw_nthy_xfhjgl.do">
		<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
		    <input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
		    <input type="hidden" name="isBzr" id="isBzr" value="${bzrQx}" />
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<div class="toolbox">
			 <!-- 按钮 -->
			 <div class="buttonbox">
			 	<logic:equal value="yes" name="writeAble">
			    <ul>
				<li> <a href="#" id="a_dc" onclick="xfhjsh()" class="btn_sh"> 审核 </a> </li>
			    </ul>
			    </logic:equal>
			 </div>
			<div class="searchtab">
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="8">
			            <div class="btn">
			              <button type="button" class="btn_cx" id="search_go" 
			              	onclick="allNotEmpThenGo('rcsw_nthy_xfhjsh.do?doType=query')">
			              	查 询
			              </button>
			              &nbsp;&nbsp;&nbsp;&nbsp;
			              <button type="button" class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
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
								<html:select property="nj" styleId="nj"  style="width:90px"
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
									<html:select property="xydm" styleId="xy" disabled="true" value="${userDep }" style="width:150px"
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
								<html:select property="xn" styleId="xn"  style="width:90px" disabled="true">
										<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th>
								审核结果
							</th>
							<td>
								<html:select property="shjg" styleId="shjg"  style="width:150px">
										<html:option value=""></html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
										<html:option value="未审核">未审核</html:option>
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
					<span> 查询结果 <logic:empty name="rs">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rs">
							&nbsp;&nbsp;<font color="blue">提示：双击可以查看详细信息；单击表头可以排序</font>
						</logic:notEmpty> <%--						<font color="blue"></font>--%> </span>
				</h3>
				<div class="con_overlfow">
					<table summary="" class="dateline" align="" width="100%">
						<!-- 表头 -->
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="dshxl"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
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
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand;" ondblclick="showTopWin('rcsw_nthy_xfhjsjsh.do?act=view&doType=dg&num=1&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,700,600)">
								<td align="center">
									<input type="checkbox" id="cbv" name="pk" style="height:17.5px"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
										<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
								</td>
								<logic:iterate id="v" name="s" offset="2" >
									<td align="left">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								
							</tr>
						</logic:iterate>
						<!-- 补空行 -->
							<%@ include file="/comm/noRows.jsp"%>
							<!-- 补空行 end-->
						<!--内容 end-->
						</tbody>
					</table>
				</div>
				<!--分页-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xfhjglActionForm"></jsp:include>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->
		</html:form>
	</body>
</html>
