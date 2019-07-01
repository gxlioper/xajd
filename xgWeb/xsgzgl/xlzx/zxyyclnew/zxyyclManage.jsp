<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>		
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/comm/dateUtils.js"></script>
		<script type="text/javascript">
		var gridSetting = {
			caption:"预约咨询列表",
			pager:"pager",
			url:"xlzx_yysqnew.do?method=yysqManage&doType=query",
			colList:[
				{label:'预约编号',name:'id', index: 'id',key:true,hidden:true},
			   {label:'学号',name:'xh', index: 'xh',formatter:xhLink},
			   {label:'姓名',name:'xsxm', index: 'xsxm'},
			   {label:'性别',name:'xsxb', index: 'xsxb',width:'4%'},
			   <logic:equal value="10504" name="xxdm">
			   {label:'危机<br/>个案',name:'sfxlwjgamc', index: 'sfxlwjgamc',width:'6%'},
			   </logic:equal>
			   <logic:equal value="10026" name="xxdm">
			   {label:'咨询师',name:'zxsxm', index: 'zxsxm',width:'8%'},
			   </logic:equal>
			   {label:'预约咨询日期',name:'yyzxrq', index: 'yyzxrq'},
			   {label:'预约状态code',name:'status', index: 'status',hidden:true},
			   {label:'预约状态',name:'statusmc', index: 'status',formatter:getYyColor},
			   {label:'咨询安排日期',name:'zxrq', index: 'zxrq'},
			   <logic:equal value="10026" name="xxdm">
			   {label:'咨询时间段',name:'sjdmczx', index: 'sjdmczx',width:'7%'},
			   </logic:equal>
			    {label:'咨询状态',name:'zxzt', index: 'zxzt',hidden:true},
			   {label:'咨询状态',name:'zxztmc', index: 'zxztmc',formatter:getZxColor},
			    {label:'咨询评价',name:'xspjzt', index: 'xspjzt',hidden:true},
			   {label:'咨询评价',name:'pjztmc', index: 'pjztmc',width:'5%',formatter:getPjColor},
			   {label:'学生<br/>申请',name:'yyzxzt', index: 'yyzxzt',width:'3%',align:'center',formatter:getSfxssq},
				{label:'签到状态',name:'qdztmc', index: 'qdztmc'},
                {label:'签到状态',name:'qdzt', index: 'qdzt',hidden:true}
			],
			sortname: "zxrq,yyzxrq",
		 	sortorder: "desc"
		};
		
		function getSfxssq(cellValue,rowObject){
			if(!cellValue){
				return "否";
			}else{
				return "是";
			}
		}
		
		function xhLink(cellValue,rowObject){
			return "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"showDetail('"+rowObject["id"]+"');\">"+cellValue+"</a>";
		}
		
		function showDetail(id){
			showDialog("预约咨询详情",750,440,"xlzx_yysqnew.do?method=zxfkView&id="+id);
		}
		
		
		function getYyColor(cellValue,rowObject){
				if(rowObject["status"]=="1"){
					return "<font color='blue'>"+cellValue+"</font>";
				}else if(rowObject["status"]=="2"){
					return "<font color='red'>"+cellValue+"</font>";
				}else{
					return cellValue;
				}
		}
		
		function getZxColor(cellValue,rowObject){
			if(rowObject["zxzt"]=="1"){
				return "<font color='blue'>"+cellValue+"</font>";
			}else if(rowObject["zxzt"]=="2" || rowObject["zxzt"]=="3"){
				return "<font color='red'>"+cellValue+"</font>";
			}else{
				return cellValue;
			}
		}
		
		function getPjColor(cellValue,rowObject){
			if(rowObject["xspjzt"]=="1"){
				return "<font color='blue'>"+cellValue+"</font>";
			}else if(rowObject["xspjzt"]=="2"){
				return "<font color='red'>"+cellValue+"</font>";
			}
		}
		
		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function searchRs(){
			var map = getSuperSearch();

			jQuery("#dataTable").reloadGrid(map);
		}

		function addYyzxInfo(){
			showDialog("增加咨询",750,520,"xlzx_yysqnew.do?method=addYyzxInfo");
		}
		
		// 删除预约咨询
		function delYyzxInfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("请选择您要删除的记录！");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				
				for(var i=0;i<ids.length;i++){
					if(rows[i]['yyzxzt'] || rows[i]['zxzt']=='2'){
						showAlertDivLayer("已咨询或者学生申请的记录不能删除！");
						return false;
					}
				}
				
				showConfirmDivLayer("您确定要删除选择的记录吗？", {"okFun" : function() {
						jQuery.post("xlzx_yysqnew.do?method=delYyzxInfo", {
							values : ids.toString()
						}, function(data) {
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								searchRs();
							}});
						}, 'json');
						
				}});
			}
		}
		function ckYyzxInfo(){
			var id= '';
			var rowsValue = jQuery("#dataTable").getSeletRow();
			 if(rowsValue.length != 1){
				showAlertDivLayer("请选择一条您要查看的记录！");
				return false;
			}else{
				id = rowsValue[0]["id"];
			}
			showDialog("预约咨询详情",750,560,"xlzx_yysqnew.do?method=yyzxDetail&doType=view&id="+id);
		}	
		
		function updateYyzxInfo(){
			var id= '';
			var rowsValue = jQuery("#dataTable").getSeletRow();
			 if(rowsValue.length != 1){
				showAlertDivLayer("请选择一条您要修改的记录！");
				return false;
			}else{
				if(rowsValue[0]["status"]=="3" ||rowsValue[0]["status"]=="4" || (rowsValue[0]["status"]=="2"&& rowsValue[0]["zxzt"]!="1" )){
					showAlertDivLayer("该条记录不能修改！");
					return false;
				}
				id = rowsValue[0]["id"];
				showDialog("修改咨询",750,520,"xlzx_yysqnew.do?method=updateYyzxInfo&id="+id+"&xh="+rowsValue[0]["xh"]);
			}
		}
		
		function zxfkInfo(){
			var id= '';
			var rowsValue = jQuery("#dataTable").getSeletRow();
			 if(rowsValue.length != 1){
				showAlertDivLayer("请选择一条您要反馈的记录！");
				return false;
			}else{
				if(rowsValue[0]["status"]!="2"){
					showAlertDivLayer("请选择预约成功的记录！");
					return false;
				}
				if(rowsValue[0]["xspjzt"]=="2"){
					return showAlertDivLayer("请选择待评价的记录！");
				}
                 if(rowsValue[0]["qdzt"] !="yqd" && rowsValue[0]["qdzt"] !="cd" ){
                     return showAlertDivLayer("请选择签到状态为“已签到”或“迟到”的记录！");
                 }
				id = rowsValue[0]["id"];
			}
			showDialog("咨询反馈",750,440,"xlzx_yysqnew.do?method=zxfkDetail&id="+id);
		}
		

		function exportConfig() {
			var dcclbh = (jQuery("#pbfs").val() == "2" )? "xlzx_zxyyclnew_zxyyclnewforsjd.do" :"xlzx_zxyyclnew_zxyyclnew.do" ;
			customExport(dcclbh, exportData,750,500);
		}
		// 导出方法
		function exportData() {
			setSearchTj();//设置高级查询条件
			var url = "xlzx_yysqnew.do?method=exportData&dcclbh=xlzx_zxyyclnew_zxyyclnew.do";//dcclbh,导出功能编号
			if(jQuery("#pbfs").val() == "2"){
				url = "xlzx_yysqnew.do?method=exportData&dcclbh=xlzx_zxyyclnew_zxyyclnewforsjd.do";
			}
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		function qdztwh(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                showAlertDivLayer("请选择一条您要维护的记录！");
                return false;
            }
            showDialog("签到状态维护",350,300,"xlzx_yysqnew.do?method=qdztwh&id="+rows[0]["id"]);
		}
		
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xlzx_yysqnew" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="pbfs" id="pbfs" value="${pbfs}"/>
			<!-- 隐藏域 -->
			<input type="hidden" id="searchTjstr" value="${searchTjstr}"/>
			
				<div class="toolbox">
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
						<logic:equal  name="writeAble" value="yes">
							<li>
								<a href="#" onclick="addYyzxInfo();return false;" class="btn_zj">增加咨询</a>
							</li>
							<li>
								<a href="#" onclick="updateYyzxInfo();return false;" class="btn_xg">修改咨询</a>
							</li>
							<li>
								<a href="#" onclick="delYyzxInfo();return false;" class="btn_sc">删除咨询</a>
							</li>
							<li>
								<a href="#" onclick="zxfkInfo();return false;" class="btn_xg">咨询反馈</a>
							</li>
							<li>
								<a href="#" onclick="qdztwh();return false;" class="btn_xg">签到状态维护</a>
							</li>
						</logic:equal>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						</ul>
					</div> 
				</div>
		  		<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 预约咨询列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
