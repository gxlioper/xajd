<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"家教资格审核列表",
				pager:"pager",
				rowNum:10,
				url:"jjgl_jjzg.do?method=getZgshList",
				params:{shzt:'dsh'},
				colList:[
				   {label:'ID',name:'sqid', index: 'sqid',hidden:true,key:true},
				   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
				   {label:'学号',name:'xh', index: 'xh',formatter:function(v,r){
					   
					   return "<a class='name' onclick='showDialog(\"查看\",700,500,\"jjgl_jjzg.do?method=jjzgView&sqid="+r["sqid"]+"\")'>"+v+"</a>";
				   }},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'年级',name:'nj', index: 'nj'},
				   {label:'学院',name:'xymc', index: 'xydm'},
				   {label:'专业',name:'zymc', index: 'zydm'},
				   {label:'班级',name:'bjmc', index: 'bjdm'},
				   {label:'申请时间',name:'sqsj', index: 'sqsj'},
				   {label:'审核状态',name:'shztmc', index: 'shzt'}
				]
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function searchRs(){
				var map = getSuperSearch();
				map["shzt"] = jQuery("#shzt").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function selectTab(obj,zt){
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				jQuery("#shzt").val(zt);
				
				var map = getSuperSearch();
				map["shzt"] = zt;
				jQuery("#dataTable").reloadGrid(map);
			}
			
			
			function showZgsh() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 0) {
					showAlert("请选择一条您要审核的记录！");
				} else if(rows.length == 1){
					var sqid = rows[0]["sqid"];
					var url = "jjgl_jjzg.do?method=jjzgDgsh&sqid="+sqid;
					showDialog("家教资格审核",800,500,url);
				}
			}
			
			function lcgz() {
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length != 1) {
					showAlertDivLayer("请选择一条流程跟踪记录！");
				} else {
					showDialog("家教资格审核流程跟踪", 600, 400, 'comm_spl.do?method=lcgz&sqid='
							+ rows[0]['sqid'] + "&splc=" + rows[0]['splcid']);
				}
			}
		</script>
	</head>

	<body>
		<html:form action="jjgl_jjzg.do?method=zgsh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="shzt" id="shzt" value="dsh"/>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
	
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">	
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_sh" onclick="showZgsh();return false;">审核</a></li>
						<li><a href="#" class="btn_cs" onclick="lcgz();return false;">流程跟踪</a></li>
					</ul>
				</div>
			</logic:equal>
			<!-- 过滤条件 -->	
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
			<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>待处理</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>已处理</span></a></li>
			      </ul>
			</div>
		</div>
	
		<div>
			<h3 class="datetitle_01">
				<span>家教资格审核列表</span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
		<div id="pager"></div>
		</html:form>
	</body>
</html>
