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
					jQuery("#dataTable").initGrid(gridSetting3);
					jQuery("#li_qx").css("display","none");
			});
			var gridSetting = {
			caption:"�������б�",
			pager:"pager",
			url:"dekt_jspjglsq.do?method=jspjSqList&type=query",
			colList:[
			   {label:'ְ����',name:'zgh', index: 'zgh',width:'10%'},
			   {label:'����',name:'xm', index: 'xm',width:'8%'},
			   {label:'���ڲ���',name:'bmmc', index: 'bmmc',width:'15%'},
			   {label:'��ʦ���',name:'jslbmc', index: 'jslbmc',width:'15%'},
			   {label:'ԤԼ״̬',name:'yyzt', index: 'yyzt',width:'15%'},
			   {label:'�Ƿ���ԤԼ�ֶ�',name:'zzshen', index: 'zzshen',hidden:true,width:'15%'},
			   {label:'ѧ���Ƿ�ԤԼ��ʦ',name:'xssfyy', index: 'xssfyy',hidden:true,width:'15%'},
			   {label:'��ʦ�Ƿ�ͬ��',name:'jssfty', index: 'jssfty',hidden:true,width:'15%'},
			   {label:'����',name:'', index: '',width:'12%',formatter:function(cellValue,rowObject){
                   return "<button type='button' onclick='add(\""+rowObject["zgh"]+ "\",\"1\",\""+rowObject["xm"]+ "\");'>����</button>";
			   }}
			],
			params:{pjlx:"dpj"},//Ĭ�ϴ�����
		 	radioselect:false
}
var gridSetting2 = {
		caption:"�������б�",
		pager:"pager",
		url:"dekt_jspjglsq.do?method=jspjSqList&type=query",
		colList:[
			   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
			   {label:'��������',name:'splc', index: 'splc',hidden:true},
			   {label:'ѧ��',name:'xn', index: 'xn',width:'8%'},
			   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%'},
			   {label:'������ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
			   {label:'����������',name:'xm', index: 'xm',width:'8%'},
			   {label:'��������ʦְ����',name:'pjjszgh', index: 'pjjszgh',hidden:true},
			   {label:'��������ʦ',name:'pjjsxm', index: 'pjjsxm',width:'8%'},
			   {label:'��ʦ��������',name:'bmmc', index: 'bmmc',width:'8%'},
			   {label:'��ʦ���',name:'jslbmc', index: 'jslbmc',width:'8%'},
			   {label:'����ʱ��',name:'xpjsj', index: 'xpjsj',width:'15%'},
			   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true}
			],
		params:{pjlx:"ypj"},
	 	radioselect:true
}

			var gridSetting3 = {
			caption:"��ԤԼ�б�",
			pager:"pager",
			url:"dekt_jspjglsq.do?method=jspjSqList&type=query",
			colList:[
			   {label:'ְ����',name:'zgh', index: 'zgh',width:'10%'},
			   {label:'����',name:'xm', index: 'xm',width:'8%'},
			   {label:'���ڲ���',name:'bmmc', index: 'bmmc',width:'15%'},
			   {label:'��ʦ���',name:'jslbmc', index: 'jslbmc',width:'15%'},
			   {label:'ԤԼ״̬',name:'yyzt', index: 'yyzt',width:'15%'},
			   {label:'�Ƿ���ԤԼ�ֶ�',name:'zzshen', index: 'zzshen',hidden:true,width:'15%'},
			   {label:'ѧ���Ƿ�ԤԼ��ʦ',name:'xssfyy', index: 'xssfyy',hidden:true,width:'15%'},
			   {label:'��ʦ�Ƿ�ͬ��',name:'jssfty', index: 'jssfty',hidden:true,width:'15%'},
			   {label:'����',name:'', index: '',width:'12%',formatter:function(cellValue,rowObject){
			       if(rowObject["yyzt"] == '��(��ԤԼ)'){
                       return "<button id='\""+rowObject["zgh"]+ "\"' type='button' onclick='deladd(\""+rowObject["zgh"]+ "\",\"1\",\""+rowObject["xm"]+ "\",this,\""+rowObject["zzshen"]+ "\");'>ȡ��ԤԼ</button>";
				   } else {
                       return "<button id='\""+rowObject["zgh"]+ "\"' type='button' onclick='addyy(\""+rowObject["zgh"]+ "\",\""+rowObject["xm"]+ "\",\""+rowObject["zzshen"]+ "\");'>ԤԼ</button>";
				   }
			   }}
			],
			params:{pjlx:"dyy"},//Ĭ�ϴ�����
		 	radioselect:false
	}
	
function searchRs(){
	var map = getSuperSearch();
	var pjlx = jQuery("#pjlx").val();
	if (pjlx != ""){
		map["pjlx"] = pjlx;
	}
	jQuery("#dataTable").reloadGrid(map);
}

function addyy (zgh,xm,zzshen) {
	showDialog("ѧ��ԤԼ��Ϣ",650,200, "dekt_jspjglsq.do?method=jspjyy_10698&zgh=" + zgh+ "&xm=" + xm + "&zzshen=" + zzshen);
}
			
//���ѧ�Ų鿴
function view(sqid, xh) {
	showDialog("�鿴������Ϣ",700,350, "dekt_jspjglsq.do?method=view&sqid=" + sqid+ "&xh=" + xh);
}
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='view(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//function addyy(zgh,lx,xm,obj,zzshen){
//	var url = "dekt_jspjglsq.do?method=addyy&pjjszgh="+zgh+"&lx="+lx+"&pjjsxm="+encodeURI(encodeURI(xm))+"&zzshen="+zzshen;
//	showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
//		jQuery.post(url,
//			function(data){
//			showAlertDivLayer(data["message"]);
//			jQuery("#dataTable").reloadGrid();
//		},'json');
//	}});
	//alert(zgh); 
	//document.getElementById(zgh).disabled = true;
	//alert(jQuery(this).html());
	///alert(jQuery("#"+zgh).text());
	//alert(jQuery(this).text());
	//alert(jQuery(obj).text());
	//jQuery(obj).prop("disabled",true);
//}

function deladd(zgh,lx,xm,obj,zzshen){
	var url = "dekt_jspjglsq.do?method=deladd&pjjszgh="+zgh+"&lx="+lx+"&pjjsxm="+encodeURI(encodeURI(xm))+"&zzshen="+zzshen;
	showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
		jQuery.post(url,
			function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	//alert(zgh); 
	//document.getElementById(zgh).disabled = true;
	//alert(jQuery(this).html());
	///alert(jQuery("#"+zgh).text());
	//alert(jQuery(this).text());
	//alert(jQuery(obj).text());
	//jQuery(obj).prop("disabled",true);
}

function add(zgh,lx,xm){
	var url = "dekt_jspjglsq.do?method=add&pjjszgh="+zgh+"&lx="+lx+"&pjjsxm="+encodeURI(encodeURI(xm));
	var title = "����������Ϣ";
	showDialog(title,700,350,url);
	
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var shzt = rows[0]["shzt"];
		if (shzt=='0'||shzt=='3'){
			var url = 'dekt_jspjglsq.do?method=update&xh='+rows[0]["xh"]+'&sqid=' + rows[0]["sqid"];
			showDialog("�޸�������Ϣ", 700,350, url);
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
				jQuery.post("dekt_jspjglsq.do?method=del", {
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
		var url = "dekt_jspjglsq.do?method=submit";
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
			jQuery.post("dekt_jspjglsq.do?method=cancel",
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
		showDialog("��ʦ�����������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}








//�л�lab
function selectTab(obj,pjlx){
	jQuery("#pjlx").val(pjlx);
	var xxdm = jQuery("#xxdm").val();
	if (pjlx == "dpj"){
		jQuery("#li_qx").css("display","none");
		jQuery("#dataTable").initGrid(gridSetting);
	} else if (pjlx == "ypj"){			
		jQuery("#li_qx").css("display","");
		jQuery("#dataTable").initGrid(gridSetting2);
	}else {
		jQuery("#li_qx").css("display","none");
		jQuery("#dataTable").initGrid(gridSetting3);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}




function shLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("���������������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}



var DCCLBH = "xg_dekt_dsglsh.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportData);
}


		</script>
	</head>

	<body style="min-height: 800px;">
		<input type="hidden" value="dyy" id="pjlx"/>
		<input type="hidden" value="${zzshen}" id="zzshen"/>
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
						<logic:equal name="writeAble" value="yes"><!--	
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add('','0','');return false;">����</a>
						</li>
						--><div id="li_qx">
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submit();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="Lcinfo();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a>
						</li>	
						</div>			
						</logic:equal>
						<!--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
					--></ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			     	<li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dyy');"><span>��ԤԼ</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'dpj');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ypj');"><span>������</span></a></li>
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
