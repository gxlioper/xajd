<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"已提交综测分学院列表",
				pager:"pager",
				url:"xpjpy_zcwh.do?method=getQxtjzcfList&doType=query",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'学院',name:'xymc', index: 'xymc',width:'19%'},
				   {label:'参评人',name:'xyrs', index: 'xyrs',width:'8%'},
				   {label:'提交人',name:'tjrxm', index: 'tjr',width:'15%'},
				   {label:'提交时间',name:'tjsj', index: 'tjsj',width:'15%'}
				],
				sortname: "xydm",
			 	sortorder: "desc",
			 	radioselect:false
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			//取消提交综测分状态
			function cancelTj(){
				var id = jQuery("#dataTable").getSeletIds();
				if (id.length != 1){
					showAlertDivLayer("请选择一条您要取消的学院！");
					return false;
				} 
				var title = "取消提交";
				var url = "xpjpy_zcwh.do?method=cancelTj&id="+id;
				showDialog(title,400,250,url);
			}
		</script>
	</head>

	<body>
		<input type="hidden" value="${cssz.xn}" id="xn"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title}</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv()" >使用帮助</a>
			</p>
		</div>
		<html:form action="/xpjpy_zcwh" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 提示信息 end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						对于已提交综测数据的班级，管理员可在此处进行<font color="red">取消提交</font>操作，取消提交的班级综测数据可<font color="red">重新调整修改</font>
					</span>
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="cancelTj();" class="btn_xg">取消提交</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span><font color="blue">${cssz.xn}&nbsp;</font>已提交学院 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>