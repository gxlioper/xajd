<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/ccrc/js/ccrc.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"",
				pager:"pager",
				url:"gyjc_ccrcsz.do?method=fpPerson&type=query",
				colList:[
					{label:'职工号',name:'zgh', index: 'zgh',width:'12%'},
					{label:'姓名',name:'xm', index: 'xm',width:'10%'},
					{label:'性别',name:'xb', index: 'xb',width:'15%'},
					{label:'所属部门',name:'bmmc', index: 'bmmc',width:'10%'}
				],
				params:{sffp:"not"},
				sortname: "bmdm",
			 	sortorder: "asc",
			 	radioselect:true
		}

		var gridSetting2 = {
				caption:"",
				pager:"pager",
				url:"gyjc_ccrcsz.do?method=fpPerson&type=query",
				colList:[
							{label:'职工号',name:'zgh', index: 'zgh',width:'12%'},
							{label:'姓名',name:'xm', index: 'xm',width:'10%'},
							{label:'性别',name:'xb', index: 'xb',width:'15%'},
							{label:'所属部门',name:'bmmc', index: 'bmmc',width:'10%'}
						],
				params:{sffp:""},
				sortname: "bmdm",
			 	sortorder: "asc",
			 	radioselect:true
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			map["sffp"]="not";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
	</head>

	<body>
		<html:form action="/gyjc_ccrcsz">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="userType" value="${userType}"/>
			<input type="hidden" id="sffp" value="not"/>
			<input type="hidden" id="ccid" value="${ccid }"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!--  <li id="li_fh">
							<a href="javascript:void(0);" class="btn_fh" onclick="fpqs();return false;"  >返回</a>
						</li>	
						-->
						<li id="li_fp">
							<a href="javascript:void(0);" class="btn_sz" onclick="saveFyfp();return false;"  >分配</a>
						</li>	
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelFp();return false;" 
							   class="btn_qxsh">取消分配</a>
						</li>	
						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'not');"><span>可分配用户</span></a></li>
			      <!--  <li><a href="javascript:void(0);" onclick="selectTab(this,'');"><span>已分配用户</span></a></li>-->
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>查询结果&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
