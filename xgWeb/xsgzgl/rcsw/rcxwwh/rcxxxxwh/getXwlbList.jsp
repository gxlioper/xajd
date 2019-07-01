<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwwh/rcxxxxwh/js/getXwlbList.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
	<html:form action="/rcsw_rcxwwh_rcxwdmwhgl" method="post">
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
			<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="selectLb();" class="btn_zj">确定</a></li>
				</ul>
			</div>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">
							<logic:notEqual name="xxdm" value="13431">
								<span>所属行为大类</span>
							</logic:notEqual>
							<logic:equal name="xxdm" value="13431">
								<span>所属加分大类</span>
							</logic:equal>
						</th>
						<td width="5%">
							<html:select property="rcxwlbdlmc" styleId="rcxwlbdlmc" style="width:130px" onchange="queryRcxwlbdldm();return false;">
									<html:option value=""></html:option>
									<html:options collection="xwdlList" property="rcxwlbdlmc" labelProperty="rcxwlbdlmc" />
							</html:select>
						</td>
						<th width="12%" >
							<logic:notEqual name="xxdm" value="13431">
								<span>行为类别名称</span>
							</logic:notEqual>
							<logic:equal name="xxdm" value="13431">
								<span>加分类别名称</span>
							</logic:equal>
						</th>
						<td>
							<input type="text" id="rcxwlbmc" name="rcxwlbmc" maxlength="20" 
							   onkeypress="if(pressEnter(event)){query();return false;}"
							/>
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
					<logic:notEqual name="xxdm" value="13431">
						<span> 日常行为代码维护列表 </span>
					</logic:notEqual>
					<logic:equal name="xxdm" value="13431">
						<span>加分申请代码维护列表称</span>
					</logic:equal>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
