<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		var gridSetting = {
					caption:"�༶�б�",
					pager:"pager",
					url:"xtwh_syglwh.do?method=bjManage&type=query",
					colList:[      
				         {label:'key',name:'bjdm', index: 'bjdm',hidden:true,key:true},
						   <%--{label:'�꼶',name:'nj', index: 'nj',width:'7%'},--%>
						   <%--{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'16%'},--%>
						   <%--{label:'רҵ',name:'zymc', index: 'zydm',width:'22%'},--%>
						   {label:'�����༶',name:'bjmc', index: 'bjdm',width:'22%'},
						   {label:'�༶����',name:'bjrs', index: 'bjrs',hidden:true}
						],
						params:{fpzt:"dfp",sydm:'${sydm}'},
						sortname: "bjmc",
					 	sortorder: "asc"
				};

		var gridSetting2 = {
					caption:"�༶�б�",
					pager:"pager",
					url:"xtwh_syglwh.do?method=bjManage&type=query",
					colList:[      
				         {label:'key',name:'bjdm', index: 'bjdm',hidden:true,key:true},
						   <%--{label:'�꼶',name:'nj', index: 'nj',width:'7%'},--%>
						   <%--{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'16%'},--%>
						   <%--{label:'רҵ',name:'zymc', index: 'zydm',width:'22%'},--%>
						   {label:'�����༶',name:'bjmc', index: 'bjdm',width:'22%'},
						   {label:'�༶����',name:'bjrs', index: 'bjrs',hidden:true}
						],
						params:{fpzt:"yfp",sydm:'${sydm}'},
						sortname: "bjmc",
					 	sortorder: "asc"
				};
				
		jQuery(function(){
			var map = getSuperSearch();
			map["fpzt"]="dfp";
			map["sydm"]='${sydm}';
			gridSetting["params"]=map;
			jQuery("#dataTable").initGrid(gridSetting);
		});	
		
		//�л�Tabҳ
		function qh(obj, shzt) {
			jQuery("#fpzt").val(shzt);
			if (shzt == "dfp") {
				document.getElementById("zjbj").style.display='block';
				document.getElementById("scbj").style.display='none';
				jQuery("#dataTable").initGrid(gridSetting);
			} else {
				document.getElementById("scbj").style.display='block';
				document.getElementById("zjbj").style.display='none';
				jQuery("#dataTable").initGrid(gridSetting2);
			}
			jQuery(".ha").removeClass("ha");
			jQuery(obj).parent().addClass("ha");
		}
		
		
		function searchRs(){
			var map = getSuperSearch();
			var fpzt = jQuery("#fpzt").val();
			if(fpzt=="dfp"){
				map["fpzt"]="dfp";
			}else{
				map["fpzt"]="yfp";
			}
			map["sydm"]='${sydm}';
			jQuery("#dataTable").reloadGrid(map);
		}

//����
function fp(){
	var sydm = jQuery("#sydm").val();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ����İ༶��");
		return false;
	}
	showConfirmDivLayer("��ȷ��Ҫ�������ϰ༶��", {
		"okFun" : function() {
			jQuery.post("xtwh_syglwh.do?method=saveFp", {
				bjdms : ids.toString(),
				sydm : sydm
			},
			function(data){
				if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								var api = frameElement.api,W = api.opener;
								jQuery(W.document).find('#search_go').click();
								jQuery("#dataTable").initGrid(gridSetting);
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	}
			}, 'json');
		}
	});
}
//ȡ������
function qx(){
		var sydm = jQuery("#sydm").val();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫȡ������İ༶��");
		return false;
	}
	var bmdms = ids.toString();
	showConfirmDivLayer("��ȷ��Ҫȡ���������ϰ༶��", {
		"okFun" : function() {
			jQuery.post("xtwh_syglwh.do?method=cancelFp", {
				bjdms : ids.toString(),
				sydm : sydm
			},
			function(data){
				if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								var api = frameElement.api,W = api.opener;
								jQuery(W.document).find('#search_go').click();
								jQuery("#dataTable").initGrid(gridSetting2);
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	}
			}, 'json');
		}
	});
}
		</script>
	</head>

	<body>
		<html:form action="/xtwh_syglwh">
			<input type="hidden" id="fpzt" value="dfp"/> 
			<input type="hidden" id="sydm" value="${sydm}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%">
	        <li class="ha"><a href="javascript:void(0);" onclick="qh(this,'dfp');"><span>������</span></a></li>
	        <li><a href="javascript:void(0);" onclick="qh(this,'yfp');"><span>�ѷ���</span></a></li>
	      </ul>
	    </div>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�༶�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow" style="height: 365px;overflow-y: auto;">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
		
		<div style="height:30px;"></div>
			 <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td>
							<div id ="scbj" style="display:none" class="btn">
								<button  type="button" onclick="qx();">
									ɾ���༶
								</button>
								<button type="button" name="�� ��" onclick="iFClose();">
									�� ��
								</button>
							</div>
							<div id="zjbj" class="btn">
								<button type="button" onclick="fp();">
									���Ӱ༶
								</button>
								<button type="button" name="�� ��" onclick="iFClose();">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
	</body>
</html>
