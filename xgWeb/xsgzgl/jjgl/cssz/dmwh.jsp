<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/jjgl/cssz/script/dmwh.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			/**
			*重新加载数据
			*/
			function reloadWindow(){
				jQuery("#dataTable").reloadGrid();
			}
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_fyff_ffxm">
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
				</ul>
			</div>
			</logic:equal>
			<div class="comp_title" id="comp_title">
		      <ul style="width:90%" id="tabUl">
		      	<li class="ha" >
		      		<a href="javascript:void(0);" onclick="selectTab(this,'jjxk');"><span>家教学科</span></a>
		      	</li>
				<li >
					<a href="javascript:void(0);" onclick="selectTab(this,'jjnj');"><span>家教年级</span></a>
				</li>
				</ul>
		    </div>
		</div>
		</html:form>
		
		<div class="formbox">
			<div>
				<h3 class="datetitle_01">
					<span> 
						查询结果
						<font color="red">(建议:&nbsp;对代码进行统一编码，同时请勿轻易删除以下记录！)</font>
					</span>
				</h3>
			</div>
			<input type="hidden" id="hiddenQryType" name="hiddenQryType" value="jjxk"/>
			<table id="dataTable"></table>
		</div>
		<div id="pager"></div>
	</body>
</html>
