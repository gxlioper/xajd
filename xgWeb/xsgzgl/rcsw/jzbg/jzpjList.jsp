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
						caption:"���������б�",
						pager:"pager",
						url:"jzbggl.do?method=jzpjList&type=query",
						colList:[
						   {label:'key',name:'jzid', index: 'jzid',key:true ,hidden:true},
						   {label:'��������',name:'mc', index: 'mc',width:'10%',formatter:mcLink},
						   {label:'����ʱ��',name:'sj', index: 'sj',width:'10%'},
						   {label:'�ص�',name:'dd', index: 'dd',width:'15%'},
						   {label:'���쵥λ',name:'zbdw', index: 'zbdw',width:'15%'},
						   {label:'������',name:'zjr', index: 'zjr',width:'8%'},
						   {label:'��������',name:'cyrs', index: 'cyrs',hidden:true},
						   {label:'������',name:'fbr', index: 'fbr',hidden:true},
						   {label:'����ʱ��',name:'fbsj', index: 'fbsj',hidden:true},
						   {label:'����',name:'zt', index: 'zt',hidden:true},
						   {label:'ѧ��',name:'xh', index: 'xh',hidden:true},
						   {label:'����',name:'pj', index: 'pj',hidden:true},
						   {label:'pjid',name:'pjid', index: 'pjid',hidden:true},
						   {label:'����',name:'', index: '',width:'12%',formatter:czLink}
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
				var mytime= getNowFormatDate();//��ȡ��ǰʱ��
				var sj = rowObject.sj;
				var xh = rowObject.xh;
				var pj = rowObject.pj;
				var dqxh= jQuery("#userName").val();
				var jzid = rowObject.jzid;
				var pjid = rowObject.pjid;
				if(xh ==dqxh){
					if(pj==""||pj==null){
						return "<button type='button' onclick='pj(\""+pjid+"\");'>����</button>";
					}else{
						return "<label type='button'>������</label>";
					}
				}else{
					if(sj>mytime){
						return "<button type='button' onclick='cj(\""+jzid+"\");'>�μ�</button>";
					}else{
						return "<label type='button'>�ѽ���</label>";
					}
				}
		}
			
		function cj(jzid){				
				var url = "jzbggl.do?method=cjJzbg";
				showConfirmDivLayer("��ȷ��Ҫ�μӸý�����", {
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
			showDialog("����",600,350,"jzbggl.do?method=pjJzbg&pjid=" + pjid);
		}
		function ckJzbg(pjid,jzid) {
		if(pjid==null||pjid=="null"||pjid==""){
				showDialog("�鿴",600,350,"jzbggl.do?method=ckJzbg&jzid=" + jzid);
			}else{
				showDialog("�鿴",600,350,"jzbggl.do?method=ckJzbgStu&pjid=" +pjid);
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
					<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>
		<html:form action="/jzbggl" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���������б�</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
