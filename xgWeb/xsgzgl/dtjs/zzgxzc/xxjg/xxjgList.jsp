<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/zzgxzc/xxjg/js/xxjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"信息结果列表",
				pager:"pager",
				url:"dtjs_xxjg.do?method=xxjgList&type=query",
				colList:[
				   {label:'信息结果id',name:'jgid', index: 'jgid',key:true,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
				   {label:'年级',name:'nj', index: 'nj',width:'6%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'12%'},
				   {label:'专业',name:'zymc', index: 'zydm',width:'14%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'14%'},
				   {label:'政治面貌',name:'zzmmmc', index: 'zzmmmc',width:'8%'},
				   {label:'所在党支部',name:'szdzbmc', index: 'szdzbmc',width:'11%'},
				   {label:'接收本人组织关系的党组织',name:'jsdzz', index: 'jsdzz',width:'11%'},
				   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'6%'},
				   {label:'sjly',name:'sjly',index:'sjly',hidden:true}
				],
				sortname: "sqsj",
			 	sortorder: "desc"
			}

			function xhLink(cellValue,rowObject){
				var id = rowObject["jgid"];
				return "<a href='javascript:void(0);' onclick=\"xxjgShow('"+id+"')\" class='name'>"+cellValue+"</a>";
			}
			
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function dzzgxdc(){
				var ids = jQuery("#dataTable").getSeletIds();
				var len = ids.length;
				if (len == 1) {
					var url = "dtjs_xxjg.do?method=zzgxdjbDc";
					url += "&id=" + ids;
					window.open(url);
				} else if (len == 0) {
					showAlertDivLayer("请选择您要下载的记录！");
					return false;
				} else {
					var url = "dtjs_xxjg.do?method=zzgxdjbDcTy";
					url += "&value=" + ids;
					window.open(url);
				}
			}

		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xszz_zzkff">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="xxjgAdd()" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="xxjgUpdate();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="xxjgDelete()" class="btn_sc">删除</a></li>
						</logic:equal>	
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						<logic:equal value="12309" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="dzzgxdc();return false;">团员组织关系介绍信</a></li>
						</logic:equal>						
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
				<span> 信息结果列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
