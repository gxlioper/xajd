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
			jQuery(function(){
					jQuery("#dataTable").initGrid(gridSetting);
					jQuery("#li_qx").css("display","none");
					jQuery("#li_sfkyy").css("display","none");
			});
			var gridSetting = {
			caption:"δѡ��ʦ�б�",
			pager:"pager",
			url:"dekt_jswhgl.do?method=jswhList&type=query",
			colList:[
			  {label:'key',name:'zgh', index: 'zgh',key:true,hidden: true },
			  {label:'����',name:'xm', index: 'xm',width:'8%'},
			  {label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
			  {label:'��������',name:'bmmc', index: 'bmmc',width:'10%'},
			  {label:'��ʦ���',name:'jslbmc', index: 'jslbmc',width:'10%'},
			  {label:'��ϵ�绰',name:'lxdh', index: 'lxdh',width:'8%'}
			],
			params:{jslx:"wwh"},//Ĭ��δ���
		 	radioselect:false
}
var gridSetting2 = {
		caption:"��ѡ��ʦ�б�",
		pager:"pager",
		url:"dekt_jswhgl.do?method=jswhList&type=query",
		colList:[
		   	  {label:'key',name:'zgh', index: 'zgh',key:true,hidden: true },
			  {label:'����',name:'xm', index: 'xm',width:'8%'},
			  {label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
			  {label:'��������',name:'bmmc', index: 'bmmc',width:'10%'},
			  {label:'��ʦ���',name:'jslbmc', index: 'jslbmc',width:'10%'},
			  {label:'��ϵ�绰',name:'lxdh', index: 'lxdh',width:'8%'},
			  {label:'�Ƿ���ԤԼ',name:'zzshen', index: 'zzshen',width:'3%'}
		],
		params:{jslx:"ywh"},//�����
	 	radioselect:false
}
	
function searchRs(){
	var map = getSuperSearch();
	var jslx = jQuery("#jslx").val();
	if (jslx != ""){
		map["jslx"] = jslx;
	}
	jQuery("#dataTable").reloadGrid(map);
}
	

//�л�lab
function selectTab(obj,jslx){
	jQuery("#jslx").val(jslx);
	if (jslx == "wwh"){
		jQuery("#li_fp").css("display","");
		jQuery("#li_qx").css("display","none");
		jQuery("#li_sfkyy").css("display","none");
		jQuery("#dataTable").initGrid(gridSetting);
	} else {			
		jQuery("#li_fp").css("display","none");
		jQuery("#li_qx").css("display","");
		jQuery("#li_sfkyy").css("display","");
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}

function dekt_10698(){
	var array = new Array();
	var zgh = jQuery("#dataTable").getSeletIds();
	jQuery("[name=grid_key]:checked").each(function(i){
		array[i] = escape(jQuery(this).val());
	});
	if(zgh.length == 0){
		showAlertDivLayer("�빴ѡ<font color='blue'>����һ����¼</font>");
		return false;
	}
	var zgh = zgh.join('!!array!!');
	showDialog('100����ʦ����',450,150,'dekt_jswhgl.do?method=dekt_10698&zgh='+zgh);
}

//����
function fp(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ����Ľ�ʦ��");
		return false;
	}
	showConfirmDivLayer("��ȷ��Ҫ�������Ͻ�ʦ��", {
		"okFun" : function() {
			jQuery.post("dekt_jswhgl.do?method=saveFp", {
				zghs : ids.toString()
			},
			function(data){
				if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
		    		 	if (parent.window) {
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
function cancelfp(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫȡ������Ľ�ʦ��");
		return false;
	}
	showConfirmDivLayer("��ȷ��Ҫȡ���������Ͻ�ʦ��", {
		"okFun" : function() {
			jQuery.post("dekt_jswhgl.do?method=cancelFp", {
				zghs : ids.toString(),
			},
			function(data){
				if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window) {
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
//������ͨ��ѧ
function sfkyy(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ����ԤԼ�Ľ�ʦ��");
		return false;
	}
	showConfirmDivLayer("��ȷ��Ҫ����ԤԼ���Ͻ�ʦ��", {
		"okFun" : function() {
			jQuery.post("dekt_jswhgl.do?method=saveSfkyy", {
				zghs : ids.toString(),
			},
			function(data){
				if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
		    		 	if (parent.window) {
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

var DCCLBH = "xg_dekt_jswhgl.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportData);
}

// ��������
function ExportData() {
	var jslx = jQuery("#jslx").val();
	setSearchTj();//���ø߼���ѯ����
	var url = "dekt_jswhgl.do?method=exportData&dcclbh=" + DCCLBH + "&jslx=" + jslx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
		</script>
	</head>

	<body style="min-height: 800px;">
		<input type="hidden" value="wwh" id="jslx"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_jspjglsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li id="li_fp">
							<a href="javascript:void(0);" onclick="fp();return false;" class="btn_shuc">����</a>
						</li>
						<li id="li_qx">
							<a href="javascript:void(0);" onclick="cancelfp();return false;" class="btn_sr">ȡ������</a>
						</li>
						<logic:equal value="10698" name="xxdm">
							<li id="li_sfkyy">
								<a href="javascript:void(0);" onclick="dekt_10698();return false;" class="btn_shuc">�Ƿ���ԤԼ</a>
							</li>
						</logic:equal>	
						</logic:equal>		
								
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>			
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wwh');"><span>δ����</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ywh');"><span>�ѷ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��ʦ�����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
