<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script language="JavaScript">
		function xljk_xlxhhy_view(){
			var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin("/xgxt/xljk_xlxhhy.do?act=xljk_xlxhhy&doType=Menmber_Detail&xn_id="+xn_id,600,480);
		}
		
		function xljk_xlxhhy_add(){
			showTopWin("/xgxt/xljk_xlxhhy.do?act=xljk_xlxhhy&doType=Insert",600,480);
		}
		
		function xljk_xlxhhy_del(){
			if (curr_row == null) {
				alert("请选择要删除的数据！\n（单击相应的行）");
				return false;
			}else if (confirm("确定要删除该行数据吗？")) {
				var xlxhhy_xnid=curr_row.cells[0].getElementsByTagName("input")[0].value;
				refreshForm("/xgxt/xljk_xlxhhy.do?act=xljk_xlxhhy&doType=Del&xlxhhy_xnid="+xlxhhy_xnid);
			} 
		}
		
		function xljk_xlxhhy_modi(){	
			if (curr_row == null) {
				alert("请选择要修改的数据！\n（单击相应的行）");
				return false;
			}else if(confirm("确定要修改该行数据吗？")) {
				var xlxhhy_xnid=curr_row.cells[0].getElementsByTagName("input")[0].value;
				showTopWin("/xgxt/xljk_xlxhhy.do?act=xljk_xlxhhy&doType=Modi&xlxhhy_xnid="+xlxhhy_xnid,600,480);
			}
		}	
	</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>心理健康 - 心理协会 - 会员基本信息</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/xljk_xlxhhy" method="post">
		<!-- 隐藏域 -->
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<div class="toolbox">			
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="xljk_xlxhhy_add()"
									class="btn_zj">增加</a>
							</li>
							<li>
								<a href="#"
									onclick="xljk_xlxhhy_modi()"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="xljk_xlxhhy_del()"
									class="btn_sc">删除</a>
							</li>
							<li>
								<a href="#" 
									onclick="impAndChkData()"
									class="btn_dr">导入</a>
							</li>		
						</logic:equal>
						<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#" 
								onclick="dataExport()"
								class="btn_dc">导出</a>
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
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xljk_xlxhhy.do?act=xljk_xlxhhy&doType=Search')">
											查询
										</button>
										<button class="button2" style="height:40px;display:none;"
											id="search_go1"
											onclick="refreshForm('/xgxt/xljk_xlxhhy.do?act=xljk_xlxhhy&doType=Search')">
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									会员编号
								</th>
								<td>
									<html:text property="hybh" styleId="hybh" />											
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="hyxh" styleId="hyxh" />
								</td>
								<th>
									性别
								</th>
								<td>
									<html:select property="xb" style="width:120px"
										styleId="xb">
										<html:option value=""></html:option>
										<html:options collection="sexList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="refreshForm('/xgxt/xljk_xlxhhy.do?act=xljk_xlxhhy')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>		
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" style="width:180px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						</span>
					</h3>
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
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
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)"
									style="cursor:hand;background-color:"
									ondblclick="xljk_xlxhhy_view()">
									<td>
										<input type="hidden" id="xn_id" name="xn_id"
											value="<bean:write name="s" property="xn_id"/>" />
										<bean:write name="s" property="hybh" />
									</td>
									<td>
										<bean:write name="s" property="xm" />
									</td>
									<td>
										<bean:write name="s" property="xb" />
									</td>
									<td>
										<bean:write name="s" property="hyxh" />
									</td>
									<td>
										<bean:write name="s" property="csrq" />
									</td>
									<td>
										<bean:write name="s" property="xymc" />
									</td>
									<td>
										<bean:write name="s" property="bjmc" />
									</td>
								</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>	
		</html:form>
	</body>
</html>
