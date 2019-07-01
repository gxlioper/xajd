<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwwh/rcxwsh/js/rcxwshManage.js"></script>
		<script type="text/javascript">
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
							jQuery.post("rcsw_rcxwwh_rcxwshgl.do?method=cancelRcxwsh",{xxwhid:xxwhid,shzt:shzt},function(result){
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
				if(${xxdm=="13815"}){
					title = "素质学分批量审核";
				}
				if(${xxdm=="13431"}){
                    title = "加分申请批量审核";
                }
				showDialog(title,500,300,"rcsw_rcxwwh_rcxwshgl.do?method=toPlsh");
			}
		}
		
		function tjsh(shzt,shyj){
			
			var rows = jQuery("#dataTable").getSeletRow();
			
			jQuery.post("rcsw_rcxwwh_rcxwshgl.do?method=plsh",{shzt:shzt,shyj:shyj,info:JSON.stringify(rows)},function(data){
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
		<html:form action="/rcsw_rcxwwh_rcxwshgl">
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
				<logic:equal name="xxdm" value="13815">	
					<span>素质学分审核列表&nbsp;&nbsp; </span>
				</logic:equal>
				<logic:notEqual name="xxdm" value="13815">
					<logic:notEqual name="xxdm" value="13815">
						<span>日常行为审核列表&nbsp;&nbsp; </span>
					</logic:notEqual>
				</logic:notEqual>
				<logic:equal name="xxdm" value="13431">
					<span>加分申请审核列表&nbsp;&nbsp; </span>
				</logic:equal>
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
