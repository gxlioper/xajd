<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="xsxx/comm/xjydnew/js/xjydlb.js"></script>
		<script type="text/javascript">

	 
		
 	   var gridSetting = {
			caption:"学籍类别列表",
			pager:"pager",
			url:"xjyd.do?method=xjydlbList&type=query",
			colList:[
			   {label:'学籍类别代码',name:'xjlbdm', index: 'xjlbdm',key:true,width:'20%'},
			   {label:'学籍类别名称',name:'xjlbmc', index: 'xjlbmc',width:'50%'},
			   {label:'是否有学籍',name:'sfyxjmc', index: 'sfyxjmc',width:'15%'},
			   {label:'是否在校',name:'sfzxmc', index: 'sfzxmc',width:'15%'},
			   {label:'是否内置',name:'sfnz', index: 'sfnz',width:'15%',hidden:true}
			],
			sortname: "xjlbdm",
		 	sortorder: "asc"
		}


		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function query(){
			var map = {};
			map["xjlbmc"] = jQuery("#xjlbmc").val();
			jQuery("#dataTable").reloadGrid(map);
		}

		</script>
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
							<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
							<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>				
					</ul>
				</div>
			</logic:equal>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="20%">学籍类别名称</th>
						<td>
							<input type="text" id="xjlbmc" name="xjlbmc" maxleng="25" 
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
				<span> 学籍类别维护列表 </span>
			</h3>	
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>	
	</body>
</html>
