<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/znxgl/wdznx/js/wdznx.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"收件箱",
				pager:"pager",
				url:"wdznx.do?method=getSjxlist&type=query",
				colList : [
							{ label : 'key', name : 'xjbh', index : 'xjbh',key : true, hidden : true },
							{ label : 'jsrydbj',name : 'jsrydbj',index : 'jsrydbj',hidden : true},
							{ label : 'jsrbh', name : 'jsrbh', index : 'jsrbh', hidden : true },
							{ label : '标题', name : 'xjzt', index : 'xjzt', width : '40%',formatter : sjxBtLink },
							{ label : '发送人', name : 'fsrxm', index : 'fsrxm', width : '10%'},
							{ label : '发送时间', name : 'fssj', index : 'fssj', width : '30%' },
							{ label : '主题类别', name : 'ztlb', index : 'ztlb', width : '20%' }
							],
				sortname: "fssj",
			 	sortorder: "desc",
			 	radioselect:false
		}
		jQuery(function(){
			var map = getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});
	
			
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/wdznx">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha" ><a href="znxgl_wdznx.do" ><span>收件箱</span></a></li>
			        <li><a href="wdznx.do?method=getFjxlist"><span>发信箱</span></a></li>
			      </ul>
			    </div>
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" onclick="xx();return false;" 
								   class="btn_xx">写信</a>
							</li>	
						</logic:equal>			
							<li>
								<a href="javascript:void(0);" onclick="znxhf();return false;" 
								   class="btn_sh">回复</a>
							</li>	
						<logic:equal name="writeAble" value="yes">					
							<li><a href="javascript:void(0);" onclick="sjxsc();return false;" 
							    class="btn_sc">删除</a>
							</li>	
						</logic:equal>	
					</ul>
				</div>
				
			
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>收件箱 </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
