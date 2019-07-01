<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/jgcx/jgcx/js/jgcx.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" >
		jQuery(function() {
			jQuery("#btn_fh").bind("click",function(){
				document.location.href='khgljgcx.do?method=jgcxList';
				
			});	
			var gridSetting = {
					caption : "考核对象列表",
					pager : "pager",
					radioselect:true,
					url : "khgljgcx.do?method=xmjgListOfSdty&type=query",
					sortname : "pm",
					sortorder : "asc"
				};
			var colList = [ 
						{label : 'bmdm',name : 'bmdm',index : 'bmdm',hidden:true},
						{label : '职工号',name : 'zgh',index : 'zgh',width : '20%'}, 
						{label : '姓名',name : 'xm',index : 'xm',width : '15%'}, 
						{label : '性别',name : 'xbmc',index : 'xbmc',width : '15%'}, 
						{label : '所在部门',name : 'bmmc',index : 'bmmc',width : '20%'}
						
					   ];
			var pfz = jQuery("font[name=pfz]");
			jQuery.each(pfz,function(i,n){
				var json = {label:jQuery(n).attr("pfzmc"),
							name:"fs"+i,
							index:"fs"+i
							}
				colList.push(json);
				});
			colList.push({label : '总分',name : 'zf',index : 'zf',width : '5%'});
			colList.push({label : '学院排名',name : 'xypm',index : 'xypm',width : '5%'});
			colList.push({label : '全校排名',name : 'pm',index : 'pm',width : '5%'});
				
				gridSetting["colList"] = colList;
				var map = {xmid:jQuery("#xmid").val(),khlx:jQuery("#khlx").val()};
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});

			//高级查询
			function searchRs(){
				var map = getSuperSearch();
				map["xmid"] = jQuery("#xmid").val();
				map["khlx"] = jQuery("#khlx").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			
						
		</script>
	</head>
	<body>
	<html:form action="/khgljgcx" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" name="xmid" id="xmid" value=${model.xmid} />
	<input type="hidden" name="khlx" id="khlx" value=${model.khlx} />
	<input type="hidden" name="dclb" id="dclb" value='js' />
	<logic:present name="pfzList">
			<logic:iterate id="z" name="pfzList">
				<font style="display:none;" pfzid="${z.pfzid }"  xmszid="${z.xmszid }" pfzmc="${z.pfzmc }"
				       name="pfz"></font>
			</logic:iterate>
		</logic:present>
	<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a id="btn_fh" class="btn_fh"> 返 回 </a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="exportConfigOfSdty();return false;" class="btn_dc">导出</a>
							<a href="javascript:void(0);" onclick="dftj();return false;" class="btn_dc">打分统计</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						<span>当前评分项目为：<font color="blue">${xmInfo.xmmc }</font></span>
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
						<div id="pager"></div>
					</div>
				</div>		
	</body>
</html>
