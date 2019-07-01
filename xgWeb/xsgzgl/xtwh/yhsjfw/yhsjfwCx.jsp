<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xtwh/yhsjfw/js/yhsjfwCx.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>	
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="yhsjfwSq();" class="btn_sz">授权</a></li>
				</ul>
			</div>
			</logic:equal>
		<html:form action="/xtwh_yhsjfw" method="post" styleId="form1">
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
				<tbody id="tbody_search_query">
							<tr>
								<th>
									用户名
								</th>
								<td>
									<input type="text" name="yhm" id="yhm" style="width:200px" maxlength="20" 
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>								
								<th>
									姓名
								</th>
								<td>
									<input type="text" name="xm" id="xm" style="width:200px" maxlength="20" 
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>						
							</tr>
							<tr>
								<th>
									所属组
								</th>
								<td>
									<html:select property="zdm" style="width:200px" styleId="zdm">
										<html:option value="">--请选择--</html:option>
										<html:options collection="yhzForSzdwList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									所属部门
								</th>
								<td>							
									<html:select property="szbm" style="width:200px" styleId="szbm">
										<html:option value="">--请选择--</html:option>
										<html:options collection="yjbmList" property="bmdm" labelProperty="bmqc" />
									</html:select>
								</td>	
							</tr>
							<tr>
								<th>
									是否辅导员
								</th>
								<td>
									<select name="sffdy" id="sffdy" style="width:200px">
										<option value="">--请选择--</option>
										<option value="1">是</option>
										<option value="0">否</option>
									</select>
								</td>
								<th>
									是否班主任
								</th>
								<td>
									<select name="sfbzr" id="sfbzr" style="width:200px">
										<option value="">--请选择--</option>
										<option value="1">是</option>
										<option value="0">否</option>
									</select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="4">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button type="button"  class="btn_cx" id="search_go" onclick="searchRs();">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button"  class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
				</table>
			</div>
		</div>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 项目类别列表 </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
