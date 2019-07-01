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
	jQuery("#dataTable").initGrid(gridSetting);
		jQuery("#li_sq").css("display","");
		jQuery("#li_xg").css("display","none");
		jQuery("#li_sc").css("display","none");
		jQuery("#li_tj").css("display","none");
		jQuery("#li_cx").css("display","none");
		jQuery("#li_lcgz").css("display","none");
		jQuery("#li_dc").css("display","none");
});
var gridSetting = {
			caption:"δ���鵥�б�",
			pager:"pager",
			url:"dekt_dsglsq.do?method=dsglSqList&type=query",
			colList:[
			   {label:'key',name:'smid', index: 'smid',key:true ,hidden:true},
			   {label:'�꼶',name:'nj', index: 'nj',width:'8%'},
			   {label:'����ID',name:'smid', index: 'smid',hidden:true},
			   {label:'����',name:'ssm', index: 'ssm',width:'10%',formatter:smLink},
			   {label:'������',name:'cbs', index: 'cbs',width:'15%'},
			   {label:'����',name:'author', index: 'author',width:'8%'},
			   {label:'����',name:'lx', index: 'lx',width:'8%'},
			   {label:'����������',name:'ebook', index: 'ebook',width:'15%',formatter:ebookLink}
			],
			params:{ydzt:"wdsm"},//Ĭ��δ��
		 	radioselect:false
		}
var gridSetting2 = {
			caption:"�Ѷ��鵥�б�",
			pager:"pager",
			url:"dekt_dsglsq.do?method=dsglSqList&type=query",
			colList:[
			   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
			   {label:'��������',name:'splc', index: 'splc',hidden:true},
			   {label:'ѧ��',name:'xn', index: 'xn',width:'8%'},
			   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%'},
			   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
			   {label:'����',name:'xm', index: 'xm',width:'8%'},
			   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'15%'},
			   {label:'רҵ',name:'zymc', index: 'zymc',width:'15%'},
			   {label:'�༶',name:'bjmc', index: 'bjmc',width:'15%'},
			   {label:'����',name:'ssm', index: 'ssm',width:'15%'},
			   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true}
			],
			params:{ydzt:"ydsm"},//�Ѷ�
		 	radioselect:false
		}
	
function searchRs(){
	var map = getSuperSearch();
	var ydzt = jQuery("#ydzt").val();
	if (ydzt != ""){
		map["ydzt"] = ydzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}

//�л�lab
function selectTab(obj,ydzt){
jQuery("#ydzt").val(ydzt);
	if (ydzt == "wdsm"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
		
		
		jQuery("#li_sq").css("display","");
		jQuery("#li_xg").css("display","none");
		jQuery("#li_sc").css("display","none");
		jQuery("#li_tj").css("display","none");
		jQuery("#li_cx").css("display","none");
		jQuery("#li_lcgz").css("display","none");
		jQuery("#li_dc").css("display","none");
		
		jQuery("#dataTable").initGrid(gridSetting);
	} else {			
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		
		jQuery("#li_sq").css("display","none");
		jQuery("#li_xg").css("display","");
		jQuery("#li_sc").css("display","");
		jQuery("#li_tj").css("display","");
		jQuery("#li_cx").css("display","");
		jQuery("#li_lcgz").css("display","");
		jQuery("#li_dc").css("display","");
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}

function viewsm(smid) {
	showDialog("�鿴�鱾��Ϣ",700,300, "dekt_smwhgl.do?method=view&smid=" + smid);
}

function smLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewsm(\""
			+ rowObject["smid"] + "\");'>" + cellValue
			+ "</a>";
}

function view(sqid,xh) {
	showDialog("�鿴������Ϣ", 720, 520, "dekt_dsglsq.do?method=view&sqid=" + sqid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='view(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function ebookLink(cellValue, rowObject) {
return "<a href='javascript:void(0);' class='name' onclick='ebook(\""
			+ rowObject["ebook"] + "\");'>" + cellValue
			+ "</a>";
}
function ebook (url){
window.open(url);
}
function add(){
	var datasum =  jQuery("#rowConut").text();
	if(datasum==0){
			var url = "dekt_dsglsq.do?method=add";
			var title = "���Ӷ�����Ϣ";
			showDialog(title,790,550,url);
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if(rows.length == 1){
				var url = "dekt_dsglsq.do?method=add&ssm="+ encodeURI(encodeURI(rows[0]["ssm"]));
				var title = "���Ӷ�����Ϣ";
				showDialog(title,790,550,url);
		}else{
				showAlertDivLayer("��ѡ��һ����¼��");
		}
	}

}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var shzt = rows[0]["shzt"];
		if (shzt=='0'||shzt=='3'){
			var url = 'dekt_dsglsq.do?method=update&xh='+rows[0]["xh"]+'&sqid=' + rows[0]["sqid"];
			showDialog("�޸Ķ�����Ϣ", 790,550, url);
		}else{
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
	}

}


//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("dekt_dsglsq.do?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="�������Ѿ��ύ����ɾ��!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}


function submit(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		var url = "dekt_dsglsq.do?method=submit";
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3' ){
				showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
			jQuery.post(url,
				{
				 values:ids.toString(),
				 xh : rows[0]['xh'],
				 splc : rows[0]['splc'],
				 shzt : rows[0]['shzt']
				},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function cancel(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length >1 ) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='5'){
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("dekt_dsglsq.do?method=cancel",
				{
				 values:ids.toString(),
				 splcid : rows[0]['splc'] 
				},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


function Lcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {	
		var shzt = rows[0]["shzt"];
		if ("0" == shzt){
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}	
		showDialog("���������������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}

var DCCLBH = "xg_dekt_dsglsq.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportData);
}

// ��������
function ExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "dekt_dsglsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
		
</script>
	</head>
	<body>
	<input type="hidden" value="wdsm" id="ydzt"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_dsglsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li id="li_sq">
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;">����</a>
						</li>
						<li id="li_xg">
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li id="li_sc">
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li id="li_tj">
							<a href="javascript:void(0);" onclick="submit();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li id="li_cx">
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li id="li_lcgz">
							<a href="javascript:void(0);" onclick="Lcinfo();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a>
						</li>				
						<li id="li_dc"><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wdsm');"><span>δ���鵥</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ydsm');"><span>�Ѷ��鵥</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>������Ϣ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
