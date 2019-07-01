<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
						caption:"讲座报告列表",
						pager:"pager",
						url:"jzbggl.do?method=jzpjList&type=query",
						colList:[
						   {label:'key',name:'jzid', index: 'jzid',key:true ,hidden:true},
						   {label:'讲座名称',name:'mc', index: 'mc',width:'10%',formatter:mcLink},
						   {label:'讲座时间',name:'sj', index: 'sj',width:'10%'},
						   {label:'地点',name:'dd', index: 'dd',width:'15%'},
						   {label:'主办单位',name:'zbdw', index: 'zbdw',width:'15%'},
						   {label:'主讲人',name:'zjr', index: 'zjr',width:'8%'},
						   {label:'参与人数',name:'cyrs', index: 'cyrs',hidden:true},
						   {label:'发布人',name:'fbr', index: 'fbr',hidden:true},
						   {label:'发布时间',name:'fbsj', index: 'fbsj',hidden:true},
						   {label:'主题',name:'zt', index: 'zt',hidden:true},
						   {label:'学号',name:'xh', index: 'xh',hidden:true},
						   {label:'评价',name:'pj', index: 'pj',hidden:true},
						   {label:'pjid',name:'pjid', index: 'pjid',hidden:true},
						   {label:'操作',name:'', index: '',width:'12%',formatter:czLink}
						],
						sortname: "sj",
					 	sortorder: "desc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);			

		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function czLink(cellValue,rowObject){
				var mytime= getNowFormatDate();//获取当前时间
				var sj = rowObject.sj;
				var xh = rowObject.xh;
				var pj = rowObject.pj;
				var dqxh= jQuery("#userName").val();
				var jzid = rowObject.jzid;
				var pjid = rowObject.pjid;
				if(xh ==dqxh){
					if(pj==""||pj==null){
						return "<button type='button' onclick='pj(\""+pjid+"\");'>评价</button>";
					}else{
						return "<label type='button'>已评价</label>";
					}
				}else{
					if(sj>mytime){
						return "<button type='button' onclick='cj(\""+jzid+"\");'>参加</button>";
					}else{
						return "<label type='button'>已结束</label>";
					}
				}
		}
			
		function cj(jzid){				
				var url = "jzbggl.do?method=cjJzbg";
				showConfirmDivLayer("您确定要参加该讲座吗？", {
					"okFun" : function() {
						jQuery.post(url, {
					jzid : jzid
				}, function(data) {
						showAlert(data["message"], {}, {
							"clkFun" : function() {
								jQuery('#search_go').click();
							}
						});
				}, 'json');
					}
				});
				
			}	
			
		function pj(pjid) {
			showDialog("评价",600,350,"jzbggl.do?method=pjJzbg&pjid=" + pjid);
		}
		function ckJzbg(pjid,jzid) {
		if(pjid==null||pjid=="null"||pjid==""){
				showDialog("查看",600,350,"jzbggl.do?method=ckJzbg&jzid=" + jzid);
			}else{
				showDialog("查看",600,350,"jzbggl.do?method=ckJzbgStu&pjid=" +pjid);
			}
		}

		function mcLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='ckJzbg(\""+ rowObject["pjid"] + "\",\""+ rowObject["jzid"] + "\");'>" + cellValue
					+ "</a>";
		}


	function getNowFormatDate() {
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	            + " " + date.getHours() + seperator2 + date.getMinutes()
	            + seperator2 + date.getSeconds();
    return currentdate;
}
		</script>
	</head>

	<body>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>
		<html:form action="/jzbggl" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>讲座报告列表</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
