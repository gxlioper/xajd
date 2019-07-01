<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/xmsz/js/xmwhTjszCx.js"></script>
		<style>
			#dataTable tr{ height:40px; }
			.gxdm,.tjz2 { width:78px;}
			.tjz1 { width:60px;}
			.xnView{ width:60px;}
			.tjzView{ width:80px;}
		</style>
	</head>
	<body>	
		<html:form action="/xpj_xmwh_tjsz" method="post" styleId="form1">
			<html:hidden property="xmdm" styleId="xmdm"/>
			<input type="hidden" name="flagpath" id="flagpath" value="${flagpath}"/>
			<input type="hidden" name="spzt" id="spzt" value="${spzt}"/>
			<input type="hidden" name="xmmc" id="xmmc" value="${xmmc}"/>
		<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					当前设置项目为：<font color="red">${xmmc}</font>
				</p>
				<p id="spztTip" style="display:none;">
					<font color="red">注：当前项目已有学生申请，不允许修改</font>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>		
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>		
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">保存</a></li>
				</ul>
			</div>
		</div>
		<div class="formbox">
			<table id="dataTable" class="dateline">
				<thead>
					<tr>
						<td width="2%">&nbsp;
						</td>
						<td width="28%">条件</td>
						<td width="10%">关系</td>
						<td width="15%">条件值</td>
						<td width="15%">依赖范围</td>
						<logic:equal name="flagpath" value="jtpj">
						<td style="display:none">启用范围</td>
						</logic:equal>
						<logic:notEqual  name="flagpath" value="jtpj">
						<td >启用范围</td>
						</logic:notEqual>
						<td width="15%">条件说明</td>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
		</html:form>
	</body>
</html>
