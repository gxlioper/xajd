<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"查询结果",
				pager:"pager",
				multiselect:true,
				rowNum:10,
				url:"gyglnew_bxlbrywh.do?method=getBxlbYhList&type=query",
				colList:[
				   {label:'用户名',name:'yhm', index: 'yhm',width:'20%',key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'20%'},			
				   {label:'所在部门',name:'bmmc', index: 'bmmc',width:'20%'},
				   {label:'部门代码',name:'bmdm', index: 'bmdm',width:'20%',hidden:true}
				],
				sortname: "yhm",
			 	sortorder: "asc"
			}

			jQuery(function(){
				searchRs();
			});

			//页签切换
			function selectTab(flag){
				jQuery("#flag").val(flag);
				jQuery(".ha").removeClass("ha");
				if("0"==flag){
					jQuery("#wjr").addClass("ha");
					jQuery("#wjrbutton").css({"display":""});
					jQuery("#yjrbutton").css({"display":"none"});
				}else{
					jQuery("#yjr").addClass("ha");
					jQuery("#wjrbutton").css({"display":"none"});
					jQuery("#yjrbutton").css({"display":""});
				}
				var map = getSuperSearch();	
				var bxlb = jQuery("#bxlb").val();
				map["flag"] = flag;
				map["bxlb"] = bxlb;
				gridSetting["params"] = map;
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function searchRs(){
				var map = getSuperSearch();	
				var flag = jQuery("#flag").val();
				var bxlb = jQuery("#bxlb").val();
				map["flag"] = flag;
				map["bxlb"] = bxlb;
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			}

			function saveRy(){
				var ids = jQuery("#dataTable").getSeletRow();
				var bxlb = jQuery("#bxlb").val();
				if (ids.length == 0){
					showAlertDivLayer("请选择您需要增加的人员！");
					return false;
				} 
				var yhAll = new Array();
				for(var i=0;i<ids.length;i++){
				  yhAll[i] = ids[i]["yhm"];
				}
				jQuery.post("gyglnew_bxlbrywh.do?method=yhAdd",{bxlb:bxlb,yhAll:yhAll},function(data){
					 showAlert(data["message"],{},{"clkFun":function(){
		        			if (parent.window){
		        				jQuery("#dataTable").reloadGrid();
								var api = frameElement.api, W = api.opener;
								W.jQuery("#dataTable").reloadGrid();
		        			}
		      		  }});
					},'json');
			}
			
			function delRy(){
				var ids = jQuery("#dataTable").getSeletRow();
				var bxlb = jQuery("#bxlb").val();
				if (ids.length == 0){
					showAlertDivLayer("请选择您需要删除的人员！");
					return false;
				} 
				var yhAll = new Array();
				for(var i=0;i<ids.length;i++){
					yhAll[i] = ids[i]["yhm"];
				}
				jQuery.post("gyglnew_bxlbrywh.do?method=yhDel",{bxlb:bxlb,yhAll:yhAll},function(data){
					 showAlert(data["message"],{},{"clkFun":function(){
		        			if (parent.window){
		        				jQuery("#dataTable").reloadGrid();
								var api = frameElement.api, W = api.opener;
								W.jQuery("#dataTable").reloadGrid();
		        			}
		      		  }});
				  },'json');
			}
		</script>
	</head>

	<body>
		<html:form action="/gyglnew_bxlbrywh">
		    <html:hidden property="flag" styleId="flag" />
		    <html:hidden property="bxlb" styleId="bxlb" />
			<div class="toolbox">
				<div class="buttonbox">
				  <ul>
						<li id="wjrbutton">
							<a href="javascript:void(0);" onclick="saveRy();return false;" class="btn_zj" >保存</a>
						</li>
						<li style="display:none;" id="yjrbutton">
							<a href="javascript:void(0);" onclick="delRy();return false;" class="btn_xg" >删除</a>
						</li>
				  </ul>
				</div>
			<!-- 过滤条件 -->	
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
		    <!-- 按钮 -->
			<div class="comp_title" id="comp_title">
		      <ul style="width:70%">
		        <li class="ha" id="wjr"><a href="javascript:void(0);" onclick="selectTab('0');"><span>未加入</span></a></li>
		        <li id="yjr"><a href="javascript:void(0);" onclick="selectTab('1');"><span>已加入</span></a></li>
		      </ul>
		    </div>
			</div>
		</html:form>
		<div>
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 人员信息列表
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:280px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
		<div id="pager"></div>
	</body>
</html>
