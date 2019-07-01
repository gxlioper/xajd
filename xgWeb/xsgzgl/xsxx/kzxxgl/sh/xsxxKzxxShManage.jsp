<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"学生扩展信息审核列表",
				pager:"pager",
				url:"xsxx_kzxxshgl.do?method=list&actionType=query&type=D",
				colList : [
							{ label : 'key', name : 'sqid', index : 'sqid',key : true, hidden : true },
							{ label : '学号', name : 'xh', index : 'xh', width : '8%',formatter : xhLink },
							{ label : '姓名', name : 'xm', index : 'xm', width : '8%' },
							{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
							{ label : '年级', name : 'nj', index : 'nj', width : '5%' },
							{ label : '学院', name : 'xymc', index : 'xydm', width : '10%' },
							{ label : '专业', name : 'zymc', index : 'zydm', width : '10%' },
							{ label : 'splc', name : 'splc', index : 'splc', hidden : true },
							{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
							{ label:'shid',name:'shid', index: 'shid',hidden:true},
							{ label:'xtgwid',name:'xtgwid', index: 'xtgwid',hidden:true},
							{ label : '班级', name : 'bjmc', index : 'bjdm', width : '10%' },
							{ label : '申请时间', name : 'sqsj', index : 'sqsj', width : '6%' },
							{ label : '审核状态', name : 'shztmc', index : 'shzt', width : '10%' } ],
				sortname: "sqsj",
			 	sortorder: "desc"
			}


			var gridSetting2 = {
				caption:"学生扩展信息审核列表",
				pager:"pager",
				url:"xsxx_kzxxshgl.do?method=list&actionType=query&type=Y",
				colList : [
							{ label : 'key', name : 'sqid', index : 'sqid',key : true, hidden : true },
							{ label : '学号', name : 'xh', index : 'xh', width : '8%',formatter : xhLink },
							{ label : '姓名', name : 'xm', index : 'xm', width : '8%' },
							{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
							{ label : '学院', name : 'xymc', index : 'xydm', width : '10%' },
							{ label : '专业', name : 'zymc', index : 'zydm', width : '10%' },
							{ label : 'splc', name : 'splc', index : 'splc', hidden : true },
							{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
							{ label:'shid',name:'shid', index: 'shid',hidden:true},
							{ label:'xtgwid',name:'xtgwid', index: 'xtgwid',hidden:true},
							{ label : '班级', name : 'bjmc', index : 'bjdm', width : '10%' },
							{ label : '申请时间', name : 'sqsj', index : 'sqsj', width : '6%' },
							{ label : '审核状态', name : 'shztmc', index : 'shzt', width : '10%' } ],
				sortname: "shsj",
			 	sortorder: "desc"
			}
			
			
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);	
			});

			/**
			 * 待处理、已处理面签切换
			 * @param obj
			 * @param shzt
			 * @return
			 */
			function selectTab(obj,shzt){
				jQuery("#shzt").val(shzt);

				if (shzt == "dsh"){
					jQuery("#li_sh").css("display","");
					jQuery("#li_qx").css("display","none");
					
					jQuery("#dataTable").initGrid(gridSetting);
				} else {
					jQuery("#li_sh").css("display","none");
					jQuery("#li_qx").css("display","");
					
					jQuery("#dataTable").initGrid(gridSetting2);
				}
				
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				
				searchRs();
			}
			/**
			 * 学号链接
			 * @param cellValue
			 * @param rowObject
			 * @return
			 */

			function xhLink(cellValue,rowObject){
				//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
				var onclickfn = "onclick=\"" + "showDialog('学生扩展信息' , 800 , 550 , 'xsxx_kzxxshgl.do?method=ck&sqid=" + rowObject['sqid'] + "')" + "\"";
				
				var href = "href = 'javascript:void(0);'";

				var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
				
				return el;
			}
			
			/**
			 * 高级查询
			 * @return
			 */
			function searchRs() {
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			/**
			 * 审核
			 */
			function sh(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				if(rows.length != 1){
					showAlertDivLayer("请选择一条您要审核记录！");
					return false;
				}
				
				showDialog("学生扩展信息审核",750,550,"xsxx_kzxxshgl.do?method=sh&sqid="+rows[0]["sqid"]+"&xtgwid="+rows[0]["xtgwid"]+"&shid="+rows[0]["shid"]+"&splc="+rows[0]["splc"]);
			}
			
			
			/**
			 * 撤消审核
			 * @return
			 */
			function cancelSh(){
				var rows = jQuery("#dataTable").getSeletRow();
			
				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要撤消的审核记录！");
				} else {
					
					//最后一级撤销审核后调用的路径
					var cancelPath = "xsxx_kzxxshgl.do?method=cancelShAction";
					confirmInfo("您确定要撤销操作吗?",function(ty){
						if(ty=="ok"){
							jQuery.post("comm_spl.do?method=cxshnew",{shlc:rows[0]["splc"],shid:rows[0]["shid"]},function(data){
									// 判断是否最后一级撤销(1:最后一级撤销成功）
									if("1" == data["cancelFlg"]){
										jQuery.post(cancelPath,{sqid:rows[0]["sqid"]},function(result){
											showAlertDivLayer(result["message"],{},{"clkFun":function(){
												jQuery("#dataTable").reloadGrid();
											}});
										},'json');
									}else{
										showAlertDivLayer(data["message"],{},{"clkFun":function(){
											jQuery("#dataTable").reloadGrid();
										}});
									}
								
							},'json');
						}
					});
			
				}
			}
			
			/**
			 * 流程跟踪
			 * @return
			 */
			function lcinfoSh(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("请选择一条记录！");
				} else {
					showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
				}
			}
		</script>
	</head>

	<body style="min-height: 800px;">
	
		<input type="hidden" value="dsh" id="shzt"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xsxx_kzxxshgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
					
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="sh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelSh();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_sr">撤消</a>
						</li>		
						</logic:equal>				
						<li><a href="javascript:void(0);" onclick="lcinfoSh();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>			
					</ul>
				</div>
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
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生扩展信息审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
