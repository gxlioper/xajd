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
					caption:"日常行为审核",
				pager:"pager",
				url:"rcsw_rcxwwhnew_rcxwshgl.do?method=rcxwshManage&type=query",
				colList:[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'6%'},
				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'12%'},
				   {label:'行为类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
                   {label:'行为大类',name:'dlxx', index: 'dlxx',width:'15%'},
                   {label:'行为大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%' ,hidden:true},
				   {label:'行为小类',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'8%'},
				   {label:'发生时间',name:'fssj', index: 'fssj',width:'12%'},
				   {label:'申请评定分值',name:'fz', index: 'fz',width:'8%'},
				   {label:'给分理由',name:'gfly', index: 'gfly',width:'8%',formatter:gflyText},
				   {label:'审核状态代码',name:'shzt', index: 'shzt' ,hidden:true},
				   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'14%'},
				   {label:'行为大类代码',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
				   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
				   {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
				   {label:'rcxwlbdldm',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				   {label:'rcxwlbxldm',name:'rcxwlbxldm', index: 'rcxwlbxldm',hidden:true}
				],
				params:{shzt:"dsh"},//默认待审核
				sortname: "rcxwjlsj",
			 	sortorder: "desc"
			}
		var gridSetting2 = {
				caption:"日常行为审核",
				pager:"pager",
				url:"rcsw_rcxwwhnew_rcxwshgl.do?method=rcxwshManage&type=query",
				colList:[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'12%'},
				   {label:'行为类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
                   {label:'行为大类',name:'dlxx', index: 'dlxx',width:'15%'},
				   {label:'行为大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%' ,hidden:true},
				   {label:'行为小类',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'8%'},
				   {label:'发生时间',name:'fssj', index: 'fssj',width:'12%'},
				   {label:'申请评定分值',name:'fz', index: 'fz',width:'8%'},
				   {label:'给分理由',name:'gfly', index: 'gfly',width:'8%',formatter:gflyText},
				   {label:'审核状态代码',name:'shzt', index: 'shzt' ,hidden:true},
				   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'14%'},
				   {label:'岗位id',name:'gwid', index: 'gwid',hidden:true},
				   {label:'审核人',name:'shr', index: 'shr',hidden:true},
				   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
				   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
				   {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
				   {label:'rcxwlbdldm',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				   {label:'rcxwlbxldm',name:'rcxwlbxldm', index: 'rcxwlbxldm',hidden:true}
				],
				params:{shzt:"ysh"},//默认已审核
				sortname: "rcxwjlsj",
			 	sortorder: "desc"
			}
			
		jQuery(function(){
			var map = getSuperSearch();
				map["shzt"] = "dsh";
			gridSetting["params"] = map;
			if(jQuery("#xxdm").val() == '10704'){
				gridSetting["caption"] = "综合测评审核";
				gridSetting2["caption"] = "综合测评审核";
				gridSetting["colList"] = [
				       				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				    				   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				    				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				    				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				    				   {label:'班级',name:'bjmc', index: 'bjdm',width:'12%'},
				    				   {label:'综合测评类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
				    				   {label:'综合测评大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%'},
				    				   {label:'综合测评小类',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'8%'},
				    				   {label:'发生时间',name:'fssj', index: 'fssj',width:'12%'},
				    				   {label:'申请评定分值',name:'fz', index: 'fz',width:'8%'},
				    				   {label:'给分理由',name:'gfly', index: 'gfly',width:'8%',formatter:gflyText},
				    				   {label:'审核状态代码',name:'shzt', index: 'shzt' ,hidden:true},
				    				   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'14%'},
				    				   {label:'综合测评大类代码',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				    				   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
				    				   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
				    				   {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
				    				   {label:'rcxwlbdldm',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				    				   {label:'rcxwlbxldm',name:'rcxwlbxldm', index: 'rcxwlbxldm',hidden:true}
				    				];
				gridSetting2["colList"] = [
				        				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				        				   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				        				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				        				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				        				   {label:'班级',name:'bjmc', index: 'bjdm',width:'12%'},
				        				   {label:'综合测评类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
				        				   {label:'综合测评大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%'},
				        				   {label:'综合测评小类',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'8%'},
				        				   {label:'发生时间',name:'fssj', index: 'fssj',width:'12%'},
				        				   {label:'申请评定分值',name:'fz', index: 'fz',width:'8%'},
				        				   {label:'给分理由',name:'gfly', index: 'gfly',width:'8%',formatter:gflyText},
				        				   {label:'审核状态代码',name:'shzt', index: 'shzt' ,hidden:true},
				        				   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'14%'},
				        				   {label:'岗位id',name:'gwid', index: 'gwid',hidden:true},
				        				   {label:'审核人',name:'shr', index: 'shr',hidden:true},
				        				   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
				        				   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
				        				   {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
				        				   {label:'rcxwlbdldm',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				        				   {label:'rcxwlbxldm',name:'rcxwlbxldm', index: 'rcxwlbxldm',hidden:true}
				        				]
			}
			jQuery("#dataTable").initGrid(gridSetting);
	
		});
			
		function gflyText(cellValue, rowObject){
			var cellValueNotNull = cellValue ? cellValue : "";
			var cellValueNew = cellValueNotNull.length > 10 ? cellValue.substring(0,10)+"..." : cellValueNotNull;
			return "<span title='"+cellValueNotNull+"' >" + cellValueNew + "</span>";
		}
	
		function searchRs(){
			var map = getSuperSearch();
			var shzt = jQuery("#shzt").val();
			if (shzt != ""){
				map["shzt"] = shzt;
			}
			jQuery("#dataTable").reloadGrid(map);
		}
			
		function selectTab(obj,shzt){
			jQuery("#shzt").val(shzt);
			var map = getSuperSearch();
			map["shzt"] = shzt;
			if (shzt == "dsh"){
				jQuery("#li_sh").css("display","");
				jQuery("#li_plsh").css("display","");
				jQuery("#li_qx").css("display","none");
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			} else {			
				jQuery("#li_sh").css("display","none");
				jQuery("#li_plsh").css("display","none");
				jQuery("#li_qx").css("display","");
				gridSetting2["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting2);
			}
			
			jQuery(".ha").removeClass("ha");
			jQuery(obj).parent().addClass("ha");
			
			searchRs();
		}
	
		function rcxwSh(){
			
			var rows = jQuery("#dataTable").getSeletRow();
			var shzt = jQuery("#shzt").val();
			if(shzt=="ysh"){
				showAlertDivLayer("已处理记录不能再次审核");
				return false;
			}
			else if (rows.length == 0){
				showAlertDivLayer("请选择一条您要审核的记录！");
			}else if(rows.length == 1 ){
				var url = "rcsw_rcxwwhnew_rcxwshgl.do?method=rcxwDgsh&id="+rows[0]["id"]+'&xh=' + rows[0]["xh"]+'&rcxwlbdldm=' + rows[0]["rcxwlbdldm"] + '&shid=' +rows[0]["shid"] ;
				var title = "日常行为审核";
				if(${xxdm=="12970"}){
					title = "素质学分审核";
				}
				//西安科技大学
				if(jQuery("#xxdm").val() == '10704'){
					title = "综合测评审核";
				}
				showDialog(title,700,500,url);
			} else {
				plsh();
			}
		}
		function rcxwshLcinfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length != 1){
				showAlertDivLayer("请选择一条流程跟踪记录！");
			} else {
				if(jQuery("#xxdm").val() == '10704'){
					showDialog("综合测评审批流程跟踪",600,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splc']);
				}else{
					showDialog("日常行为审批流程跟踪",600,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splc']);
				}		
			}
		}
	
		function cancelSh(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("请选择一条您要撤消的审核记录！");
			} else {
				showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
					jQuery.post("rcsw_rcxwwhnew_rcxwshgl.do?method=cancelRcxwsh",
						{
						 id:rows[0]["id"],
						 gwid:rows[0]["gwid"],
						 shr:rows[0]["shr"],
						 splc:rows[0]["splc"]
						},
						function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},
					'json');
				}});
			}
		}
	
		function xwxxView(id, xh) {
			var title = "查看日常行为审核信息";
			if(${xxdm=="12970"}){
				title = "查看素质学分审核信息";
			}
			if(jQuery("#xxdm").val() == '10704'){
				title = "查看综合测评审核信息";
			}
			showDialog(title, 700, 480, "rcsw_rcxwwhnew_rcxwshgl.do?method=viewXwsh&id=" + id
					+ "&xh=" + xh);
		}
	
		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='xwxxView(\""
					+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}
		var DCCLBH = "rcsw_rcxwwhnew_rcxwshgl.do";//dcclbh,导出功能编号
	
		//自定义导出 功能
		function exportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport(DCCLBH, rcxwxshExportData);
		}
	
		// 导出方法
		function rcxwxshExportData() {
			var shlx = jQuery("#shzt").val();
			setSearchTj();//设置高级查询条件
			var url = "rcsw_rcxwwhnew_rcxwshgl.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		function cancelShnew(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("请选择一条您要撤消的审核记录！");
			} else {				
				var splc = rows[0]["splc"];
				var shid = rows[0]["shid"];
				var xxwhid = rows[0]["id"];
				var shzt =  rows[0]["shzt"];
				showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
					jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
						// 判断是否最后一级撤销(1:最后一级撤销成功）
						if("1" == data["cancelFlg"]){
							jQuery.post("rcsw_rcxwwhnew_rcxwshgl.do?method=cancelRcxwsh",{xxwhid:xxwhid,shzt:shzt},function(result){
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
				}});
			}
		}
	
		function plsh(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length == 0){
				showAlertDivLayer("请选择您要审核的记录！");
			} else {
				var title = "日常行为批量审核";
				if(${xxdm=="12970"}){
					title = "素质学分批量审核";
				}
				if(jQuery("#xxdm").val() == '10704'){
					title = "综合测评批量审核";
				}
				showDialog(title,500,300,"rcsw_rcxwwhnew_rcxwshgl.do?method=toPlsh");
			}
		}
		
		function tjsh(shzt,shyj){
			
			var rows = jQuery("#dataTable").getSeletRow();
			
			jQuery.post("rcsw_rcxwwhnew_rcxwshgl.do?method=plsh",{shzt:shzt,shyj:shyj,info:JSON.stringify(rows)},function(data){
				showAlertDivLayer(data.message);
				searchRs();
			});
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
		<html:form action="/rcsw_rcxwwhnew_rcxwshgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">	
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="rcxwSh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelShnew();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
						<logic:equal name="writeAble" value="yes">		   
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</logic:equal>				
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
				<logic:equal name="xxdm" value="12970">	
					<span>素质学分审核列表&nbsp;&nbsp; </span>
				</logic:equal>
				<logic:notEqual name="xxdm" value="12970">
					<span>
						<logic:equal value="10704" name="xxdm">
							综合测评审核列表&nbsp;&nbsp;
						</logic:equal>
						<logic:notEqual value="10704" name="xxdm">
							日常行为审核列表&nbsp;&nbsp; 
						</logic:notEqual>
					</span>
				</logic:notEqual>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
		<div id="plsh" style="display:none;">
			<table class="formlist">
				<thead>
					<tr>
						<th colspan="2">
							<span>批量审核</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="30%">
							审核意见
						</th>
						<td>
							<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=rcxw&id=shyj" /><br/><br/>
							<textarea rows="5" style="width:95%;" id="shyj"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
