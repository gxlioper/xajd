<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/khbgl/js/khnrgl.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script language="javascript" >
		jQuery(function(){
			var gridSetting = {
				caption : "考核表列表",
			pager : "pager",
			url : "khglKhnrgl.do?method=getKhnrglList&type=query&khbid="+jQuery("#khbid").val(),
			colList : [ 
			   {label : 'zbmxid',name : 'zbmxid',index : 'zbmxid',key : true,hidden:true},
			   {label : 'Khnrid',name : 'Khnrid',index : 'Khnrid',hidden:true},
			   {label : 'pflx',name : 'pflx',index : 'pflx',hidden:true}, 
			   {label : 'sfnz',name : 'sfnz',index : 'sfnz',hidden:true}, 
			   {label : '考核内容',name : 'khnr',index : 'khnr',width : '60%'},
			   {label : '一级指标',name : 'yjzb',index : 'yjzb',width : '10%'},
			   {label : '二级指标',name : 'ejzb',index : 'ejzb',width : '10%'},
			   {label : '分值',name : 'fzqj',index : 'fzqj',width : '10%'},
			   {label : '评分类型',name : 'pflxmc',index : 'pflxmc',width : '10%'}
			   ]
		}
			var map = {};
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
			
		jQuery("#btn_fh").bind("click",function(){
				document.location.href='khglKhbgl.do?method=getKhbglList';
		});
		});
				
		</script>
		
	</head>
	<body>
	<html:form action="/khglKhnrgl" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" name="khbid" id="khbid" value="${khbMap.khbid }"/>
	<input type="hidden" name="sfypf" id="sfypf" value="${khbMap.sfypf }"/>
	<input type="hidden" class="btn_cx" id="search_go" onclick="searchRs();closeMore();return false;"/>
	<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a id="btn_fh" class="btn_fh"> 返 回 </a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj" >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_sz" >导入</a>
						</li>
					</ul>
				</div>
				
			</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						<span>${khbMap.khbmc} </font> </span>
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
					</div>
				</div>		
	</body>
</html>
