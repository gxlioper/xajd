<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/tsxs/js/tsxsTj.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
	</head>
	<body>	
		<html:form action="/xpj_tsxs" method="post" styleId="form1">
		<html:hidden property="writeAble" styleId="writeAble" value="${writeAble}"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal value="yes" name="writeAble">
			<div class="buttonbox">
					<ul>
							<li><a href="javascript:void(0);" onclick="add('${mklx}');return false" title="增加特殊人员" class="btn_zj" >增加</a></li>
							<li><a href="javascript:void(0);" onclick="del();return false;" title="勾选一条或多条记录，删除特殊学生" class="btn_sc">删除</a></li>
							<logic:equal value="10878" name="xxdm">
								<li><a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr">导入</a></li>
							</logic:equal>
					</ul>
			</div>
			</logic:equal>	
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">学年</th>
						<td>
							<html:select property="xn" styleId="xn" style="width:155px">
								<html:option value=""></html:option>
								<html:options collection="xnList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<th width="10%">学期</th>
						<td>
							<html:select property="xq" styleId="xq" style="width:155px">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>								
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>特殊学生统计列表</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
		</html:form>
	</body>
</html>
