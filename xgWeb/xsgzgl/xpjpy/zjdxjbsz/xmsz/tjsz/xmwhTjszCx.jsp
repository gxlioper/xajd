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
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxjbsz/xmsz/tjsz/js/xmwhTjsz.js"></script>
		<style>
			#dataTable tr{ height:40px; }
			.gxdm,.tjz2 { width:78px;}
			.tjz1 { width:60px;}
			.xnView{ width:60px;}
			.tjzView{ width:80px;}
		</style>
	</head>
	<body>	
		<html:form action="/xpjpy_xmwh_tjsz" method="post" styleId="form1">
			<html:hidden property="xmdm" styleId="xmdm"/>
			<input type="hidden" name="flagpath" id="flagpath" value="${flagpath}"/>
			<input type="hidden" name="xmmc" id="xmmc" value="${xmmc}"/>
			<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��ǰ������ĿΪ��<font color="red">${xmmc}</font>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>		
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>		
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">����</a></li>
					</ul>
				</div>
			</div>
			<div class="formbox">
				<table id="dataTable" class="dateline">
					<thead>
						<tr>
							<td width="2%">&nbsp;
							</td>
							<td width="28%">����</td>
							<td width="10%">��ϵ</td>
							<td width="15%">����ֵ</td>
							<td width="15%">������Χ</td>
							<td width="15%">���÷�Χ</td>
							<td width="15%">����˵��</td>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>