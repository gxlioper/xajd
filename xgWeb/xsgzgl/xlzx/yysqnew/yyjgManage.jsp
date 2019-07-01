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
			url:"xlzx_yysqnew.do?method=yyjgManage&doType=query",
			colList:[
				{label:'预约编号',name:'id', index: 'id',key:true,hidden:true},
			   {label:'学号',name:'xh', index: 'xh',formatter:xhLink},
			   {label:'姓名',name:'xsxm', index: 'xsxm'},
			   {label:'性别',name:'xsxb', index: 'xsxb',width:'4%'},
			   <logic:equal value="10504" name="xxdm">
			   {label:'危机<br/>个案',name:'sfxlwjgamc', index: 'sfxlwjgamc',width:'6%'},
			   </logic:equal>
			   {label:'预约咨询日期',name:'yyzxrq', index: 'yyzxrq'},
			   {label:'预约状态code',name:'status', index: 'status',hidden:true},
			   //{label:'预约状态',name:'statusmc', index: 'status',formatter:getYyColor},
			   {label:'咨询安排日期',name:'zxrq', index: 'zxrq'},
			   {label:'安排咨询师',name:'apzxsxm', index: 'apzxsxm',width:'9%',formatter:apzxsxmLink},
			   {label:'安排咨询师职工号',name:'apzxs', index: 'apzxs',hidden:true},
			   <logic:equal  name="xxdm" value="10026">
			   {label:'咨询安排时间段',name:'sjdmczx', index: 'sjdmczx'},
			   </logic:equal>
			    {label:'咨询状态',name:'zxzt', index: 'zxzt',hidden:true},
			   {label:'咨询状态',name:'zxztmc', index: 'zxztmc',formatter:getZxColor},
			    {label:'咨询评价',name:'xspjzt', index: 'xspjzt',hidden:true},
			   {label:'咨询评价',name:'pjztmc', index: 'pjztmc',width:'6%',formatter:getPjColor},
			   {label:'中心反馈意见',name:'zxfknr', index: 'zxfknr',width:'10%',formatter:function(cellValue,rowObject){
				   if(!cellValue){
					   cellValue = "";
				   }
				   var cellValueTemp = cellValue;
				   if(cellValue.length > 6){
					   cellValueTemp = cellValue.substring(0,6)+"...";
				   }
				   return jQuery("<span title='"+cellValue+"'>"+cellValueTemp+"</span>");
				 }
				}
			   //{label:'学生申请',name:'yyzxzt', index: 'yyzxzt',align:'center',formatter:getSfxssq}
			],
			sortname: "zxrq,yyzxrq",
		 	sortorder: "desc"
		};
		// 咨询师姓名链接(安排)
		function apzxsxmLink(cellValue, rowObject) {
			return "<a href = 'javascript:void(0);' class='name' onclick=\"showDialog('查看咨询师信息' , 750 , 410 , 'xlzx_zxs.do?method=zxsglDetail&doType=view&zgh=" + rowObject['apzxs'] + "')\" >" + (cellValue==null?" ":cellValue) + "</a>";
		}
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
			showDialog("预约咨询详情",750,440,"xlzx_yysqnew.do?method=yyjgView&id="+id);
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

		function ckYyzxInfo(){
			var id= '';
			var rowsValue = jQuery("#dataTable").getSeletRow();
			 if(rowsValue.length != 1){
				showAlertDivLayer("请选择一条您要查看的记录！");
				return false;
			}else{
				id = rowsValue[0]["id"];
			}
			showDialog("预约咨询详情",750,560,"xlzx_yysqnew.do?method=yyjgView&doType=view&id="+id);
		}	
		
		function updateYyzxInfo(){
			var id= '';
			var rowsValue = jQuery("#dataTable").getSeletRow();
			 if(rowsValue.length != 1){
				showAlertDivLayer("请选择一条您要反馈的记录！");
				return false;
			}else{
				/*if(rowsValue[0]["status"]=="3" ||rowsValue[0]["status"]=="4" || (rowsValue[0]["status"]=="2"&& rowsValue[0]["zxzt"]!="1" )){
					showAlertDivLayer("该条记录不能反馈！");
					return false;
				}*/
				id = rowsValue[0]["id"];
				showDialog("中心反馈意见",520,190,"xlzx_yysqnew.do?method=updateYyjg&id="+id+"&xh="+rowsValue[0]["xh"]);
			}
		}
		
		function exportConfig() {
			var dcclbh = "xlzx_zxyyclnew_zxyyclnewjg.do";
			if(jQuery("#pbfs").val() == "2"){
				dcclbh = "xlzx_zxyyclnew_zxyyclnewjgforsjd.do";
			}
			customExport(dcclbh, exportData,750,500);
		}
		// 导出方法
		function exportData() {
			setSearchTj();//设置高级查询条件
			var url = "xlzx_yysqnew.do?method=exportDataJg&dcclbh=xlzx_zxyyclnew_zxyyclnewjg.do";//dcclbh,导出功能编号
			if(jQuery("#pbfs").val() == "2"){
				 url = "xlzx_yysqnew.do?method=exportDataJg&dcclbh=xlzx_zxyyclnew_zxyyclnewjgforsjd.do";//dcclbh,导出功能编号
			}
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
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
			<input type="hidden" id="pbfs" name="pbfs" value="${pbfs}"/>
			<!-- 隐藏域 -->
			<input type="hidden" id="searchTjstr" value="${searchTjstr}"/>
			
				<div class="toolbox">
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
						<logic:equal  name="writeAble" value="yes">
							<li>
								<a href="#" onclick="updateYyzxInfo();return false;" class="btn_xg">中心反馈</a>
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
