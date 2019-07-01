<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"特殊宿舍列表",
					pager:"pager",
					url:"gygl_ntzd.do?method=tsqstjManage&type=query",
					colList:[
					   {label:'年月',name:'ny', index: 'ny',width:'8%',key:true},
					   {label:'楼栋名称',name:'ldmc', index: 'ldmc'},
					   {label:'寝室号',name:'qsh', index: 'qsh',width:'8%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
					   {label:'班级',name:'bjmc', index: 'bjmc'},
					   {label:'班主任',name:'bzr', index: 'bzr'},
					   {label:'分数',name:'fs', index: 'to_number(fs)',width:'10%'},
					   {label:'楼栋排名',name:'ldpm', index: 'to_number(ldpm)',width:'8%'}					 
					],
					sortname: "ny desc , to_number(ldpm), lddm, qsh, xymc, bjmc",
				 	sortorder: "asc"
			}
			function searchRs(){
				var map = getSuperSearch();
				map["qslx"] = jQuery("#qslx").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery("#btn_dc").click(exportConfig);
			});
			function exportConfig() {
				customExport("gygl_ntzd_tsqstj.do", exportData,650,500);
			}
			// 导出方法
			function exportData() {
				setSearchTj();//设置高级查询条件
				var qslx = jQuery("#qslx").val();
				var url = "gygl_ntzd.do?method=exportTsqstjData&dcclbh=gygl_ntzd_tsqstj.do&qslx="+qslx;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			function query(obj,qslx){
				jQuery("#comp_title li").removeClass();
				jQuery(obj).parent().attr("class","ha");
				jQuery("#qslx").val(qslx);
				var map = getSuperSearch();
				map["qslx"] = jQuery("#qslx").val();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
				<input type="hidden" id="qslx" value="wmqs" name="qslx" />
			</p>
		</div>
		<html:form action="/gygl_ntzd.do?method=nykhxsManage">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<logic:equal value="yes" name="writeAble">
							<li><a href="javascript:void(0);" id="btn_dc" class="btn_dc">导出</a></li>
							</logic:equal>
						</ul>
					</div>
				</logic:equal>		
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->		
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="query(this,'wmqs');"><span>文明寝室</span></a></li>
			        <li><a href="javascript:void(0);" onclick="query(this,'bhgqs');"><span>不合格寝室</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>宿舍列表</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
