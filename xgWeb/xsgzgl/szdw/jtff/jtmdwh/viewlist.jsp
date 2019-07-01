<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/jtff/jtmdwh/js/jtmdwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"正常享受津贴人员明细",
				pager:"pager",
				url:"jtff_jtmdwh.do?method=getJtmdwhcx&type=query",
				colList:[
					{label:'key',name:'id', index: 'id',key:true ,hidden:true},
					{label:'职工号',name:'zgh', index: 'zgh',width:'15%',formatter:xhLink},
					{label:'姓名',name:'xm', index: 'xm',width:'10%'},
					{label:'性别',name:'xb', index: 'xb',width:'8%'},
					{label:'所属部门',name:'bmmc', index: 'bmmc',width:'25%'},
					{label:'岗位',name:'gw', index: 'gw',width:'15%'},
					{label:'所带学生数',name:'dbrs', index: 'dbrs',width:'25%'}
				],
				params:{jtlb:"zc"},
				sortname: "zgh",
			 	sortorder: "asc"
		}

		var gridSetting2 = {
				caption:"固定津贴人员明细",
				pager:"pager",
				url:"jtff_jtmdwh.do?method=getJtmdwhcx&type=query",
				colList:[
					{label:'key',name:'id', index: 'id',key:true ,hidden:true},
					{label:'职工号',name:'zgh', index: 'zgh',width:'15%',formatter:xhLink1},
					{label:'姓名',name:'xm', index: 'xm',width:'10%'},
					{label:'性别',name:'xb', index: 'xb',width:'8%'},
					{label:'所属部门',name:'bmmc', index: 'bmmc',width:'25%'},
					{label:'岗位',name:'gw', index: 'gw',width:'15%'},
					{label:'所带学生数',name:'dbrs', index: 'dbrs',width:'10%'},
					{label:'固定津贴(元)',name:'gdffje', index: 'gdffje',width:'15%'}
				],
				params:{jtlb:"gd"},
				sortname: "zgh",
			 	sortorder: "asc"
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			map["jtlb"]="zc";
			gridSetting["params"] = map;
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
		<html:form action="/jtff_jtmdwh">
			<input type="hidden" id="jtlb" value="zc"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_zc">
							<a href="javascript:void(0);" onclick="addzcjtff();return false;" 	
							   class="btn_zj">增加</a>
							<a href="javascript:void(0);" onclick="updatezcjt();return false;" 	
							   class="btn_xg">修改</a>
							<a href="javascript:void(0);" onclick="delzcjt();return false;" 	
							   class="btn_sc">删除</a>
							<a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">导入</a>
						</li>						
						<li id="li_gd" style="display: none;">
							<a href="javascript:void(0);" onclick="addgdjtff();return false;" 
							   class="btn_zj">增加</a>
							<a href="javascript:void(0);" onclick="updategdjt();return false;" 	
							   class="btn_xg">修改</a>
							<a href="javascript:void(0);" onclick="delgdjt();return false;" 	
							   class="btn_sc">删除</a>
						</li>	
						</logic:equal>
						<li>
						<a href="javascript:void(0);" onclick="exportConfig();return false;" 	
							   class="btn_dc">导出</a>	
					    </li>		
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'zc');"><span>正常津贴</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'gd');"><span>固定津贴</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span id="bt">正常享受津贴人员明细&nbsp;&nbsp; </span>
				<span id="bt1" style="display:none">固定津贴人员明细&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
