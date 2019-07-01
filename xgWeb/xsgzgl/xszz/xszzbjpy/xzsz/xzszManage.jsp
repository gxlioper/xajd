<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
					caption:"班级列表",
					pager:"pager",
					url:"xszz_xszzbjpy_xzszgl.do?method=xzszManage&type=query",
					colList:[      
				         {label:'key',name:'id', index: 'id',hidden:true,key:true},
						   {label:'年级',name:'nj', index: 'nj',width:'8%'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'14%'},
						   {label:'专业',name:'zymc', index: 'zydm',width:'15%'},
						   {label:'班级',name:'bjdm', index: 'bjdm',hidden:true},
						   {label:'班级',name:'bjmc', index: 'bjdm',width:'16%'},
						   {label:'班级人数',name:'bjrs', index: 'bjrs',width:'8%'},
						   {label:'评议小组人数',name:'xzrs', index: 'xzrs',width:'8%',
							formatter:function(cellValue,rowObject){
										var html = jQuery("<a href='javascript:void(0);' class='name'>"+cellValue+"</a>");
										html.bind("click",function(){
											var isopen = jQuery("#isopen").val();
											if(isopen==null||isopen==''){
												showAlertDivLayer('基本设置未初始化，请联系管理员！');
												return false;
											}
											if ("false" == isopen){
												showAlertDivLayer("当前未开放班级评议，请联系管理员！");
												return false;
											}
											var url = "xszz_xszzbjpy_xzszgl.do?method=updateXzsz&bjdm=" + rowObject["bjdm"] + "&sfksbjpy="+rowObject["sfksbjpy"];
											var title = "修改班级评议小组成员";
											showDialog(title,790,490,url);
										});
										return html;
									 }
						   },
						   {label:'评议小组成员',name:'xms', index: 'xms',width:'10%',formatter:function(cellValue,rowObject){
							   if(!cellValue){
								   cellValue = "";
							   }
							   var cellValueTemp = cellValue;
							   if(cellValue.length > 6){
								   cellValueTemp = cellValue.substring(0,6)+"...";
							   }
							   return jQuery("<span title='"+cellValue+"'>"+cellValueTemp+"</span>");
							 }
							},
						   {label:'评议小组代表',name:'xsdbxm', index: 'xsdbxm',width:'10%'},
						   {label:'提交状态',name:'tjztmc', index: 'tjztmc',width:'9%'},
						   {label:'提交状态',name:'tjzt', index: 'tjzt',hidden:true},
						   {label:'是否开始班级评议',name:'sfksbjpy', index: 'sfksbjpy',hidden:true}
						],
						sortname: "tjzt,nj,xydm,zydm",
					 	sortorder: "asc"
				};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function add(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('基本设置未初始化，请联系管理员！');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer("当前未开放班级评议，请联系管理员！");
				return false;
			}
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("请选择一个班级！");
			} else {
				if(rows[0]["sfksbjpy"] == 'true'){
					showAlertDivLayer("当前班级已经开始评议！");
					return false;
				}
				var url = "xszz_xszzbjpy_xzszgl.do?method=addXzsz&bjdm=" + rows[0]["bjdm"];
				var title = "增加班级评议小组成员";
				showDialog(title,790,490,url);
			}
		}

		function update() {
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('基本设置未初始化，请联系管理员！');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer("当前未开放班级评议，请联系管理员！");
				return false;
			}
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("请选择一个班级！");
			} else {
				if(rows[0]["sfksbjpy"] == 'true'){
					showAlertDivLayer("当前班级已经开始评议！");
					return false;
				}
				var url = "xszz_xszzbjpy_xzszgl.do?method=updateXzsz&bjdm=" + rows[0]["bjdm"] + "&sfksbjpy="+rows[0]["sfksbjpy"];
				var title = "修改班级评议小组成员";
				showDialog(title,790,490,url);
			}
		}

		function submitBusi(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('基本设置未初始化，请联系管理员！');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer("当前未开放班级评议，请联系管理员！");
				return false;
			}
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length != 1 ){
				showAlertDivLayer("请选择一条您要提交的记录！");
			}else{
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows[0]["tjzt"] != "0") {
					showAlertDivLayer("请选择未提交的记录！");
					return false;
				}
				if (!rows[0]["xsdbxm"]) {
					showAlertDivLayer("请先设置评议小组代表！");
					return false;
				}
				var url = "xszz_xszzbjpy_xzszgl.do?method=submitXzsz";
				showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
					jQuery.post(url,
						{bjdm:rows[0]["bjdm"],
						bjrs:rows[0]["bjrs"],
						xzrs:rows[0]["xzrs"]
						},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
				}});
			}
		}

		</script>
	</head>

	<body>
		<html:form action="/xszz_xszzbjpy_xzszgl">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" name="isopen" id="isopen" value="${jcszModel.bjpyisopen }"/>
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>班级列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
