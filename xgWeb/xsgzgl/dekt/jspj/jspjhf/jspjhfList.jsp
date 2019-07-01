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
			});
			var gridSetting = {
			caption:"��ʦ����δ�����б�",
			pager:"pager",
			url:"dekt_jspjglyyxx.do?method=jspjyyxxList&type=query",
			colList:[
			   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
			   {label:'ѧ��',name:'xh', index: 'xh',width:'10%'},
			   {label:'����',name:'xm', index: 'xm',width:'8%'},
			   {label:'�Ա�',name:'xb', index: 'xb',width:'4%'},
			   {label:'�꼶',name:'nj', index: 'nj',width:'6%'},
			   {label:'ѧԺ����',name:'xydm', index: 'xydm',hidden:true,width:'15%'},
			   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'12%'},
                {label:'��Ժ����',name:'sydm', index: 'sydm',hidden:true,width:'15%'},
                {label:'��Ժ',name:'symc', index: 'symc',width:'8%'},
			   {label:'רҵ����',name:'zydm', index: 'zydm',hidden:true,width:'15%'},
			   {label:'רҵ',name:'zymc', index: 'zymc',width:'12%'},
			   {label:'�༶����',name:'bjdm', index: 'bjdm',hidden:true,width:'15%'},
			   {label:'�����༶',name:'bjmc', index: 'bjmc',width:'4%'},
                {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'4%'},
			   {label:'���֤��',name:'sfzh', index: 'sfzh',width:'12%'},
			   {label:'������ò����',name:'zzmm', index: 'zzmm',hidden:true,width:'10%'},
			   {label:'������ò',name:'zzmmmc', index: 'zzmmmc',width:'8%'},
			   {label:'�������',name:'mz', index: 'mz',hidden:true,width:'15%'},
			   {label:'����',name:'mzmc', index: 'mzmc',width:'4%'},
			   {label:'�ظ�',name:'', index: '',width:'4%',formatter:xhLink},	
			   {label:'�Ƿ���ԤԼ�ֶ�',name:'zzshen', index: 'zzshen',hidden:true,width:'15%'},
			   {label:'ѧ���Ƿ�ԤԼ��ʦ',name:'xssfyy', index: 'xssfyy',hidden:true,width:'15%'},
			   {label:'��ʦ�Ƿ�ͬ��',name:'jssfty', index: 'jssfty',hidden:true,width:'15%'}
			],
			params:{pjlx:"dpj"},//Ĭ�ϴ�����
		 	radioselect:false
}
var gridSetting2 = {
		caption:"��ʦ�����������б�",
		pager:"pager",
		url:"dekt_jspjglyyxx.do?method=jspjyyxxList&type=query",
		colList:[
			   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
			   {label:'ѧ��',name:'xh', index: 'xh',width:'10%'},
			   {label:'����',name:'xm', index: 'xm',width:'8%'},
			   {label:'�Ա�',name:'xb', index: 'xb',width:'4%'},
			   {label:'�꼶',name:'nj', index: 'nj',width:'6%'},
			   {label:'ѧԺ����',name:'xydm', index: 'xydm',hidden:true,width:'15%'},
			   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'12%'},
            {label:'��Ժ����',name:'sydm', index: 'sydm',hidden:true,width:'15%'},
            {label:'��Ժ',name:'symc', index: 'symc',width:'8%'},
			   {label:'רҵ����',name:'zydm', index: 'zydm',hidden:true,width:'15%'},
			   {label:'רҵ',name:'zymc', index: 'zymc',width:'12%'},
			   {label:'�༶����',name:'bjdm', index: 'bjdm',hidden:true,width:'15%'},
			   {label:'�����༶',name:'bjmc', index: 'bjmc',width:'4%'},
            {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'4%'},
			   {label:'���֤��',name:'sfzh', index: 'sfzh',width:'12%'},
			   {label:'������ò����',name:'zzmm', index: 'zzmm',hidden:true,width:'10%'},
			   {label:'������ò',name:'zzmmmc', index: 'zzmmmc',width:'8%'},
			   {label:'�������',name:'mz', index: 'mz',hidden:true,width:'15%'},
			   {label:'����',name:'mzmc', index: 'mzmc',width:'4%'},
			   {label:'�ظ�',name:'', index: '',width:'4%',formatter:xhLink},	
			   {label:'�Ƿ���ԤԼ�ֶ�',name:'zzshen', index: 'zzshen',hidden:true,width:'15%'},
			   {label:'ѧ���Ƿ�ԤԼ��ʦ',name:'xssfyy', index: 'xssfyy',hidden:true,width:'15%'},
			   {label:'��ʦ�Ƿ�ͬ��',name:'jssfty', index: 'jssfty',hidden:true,width:'15%'}
			],
		params:{pjlx:"ypj"},
	 	radioselect:true
}
	
function searchRs(){
	var map = getSuperSearch();
	var pjlx = jQuery("#pjlx").val();
	if (pjlx != ""){
		map["pjlx"] = pjlx;
	}
	jQuery("#dataTable").reloadGrid(map);
}
			
//���ѧ�Ų鿴
function view(sqid, xh) {
	showDialog("ѧ��ԤԼ�ظ�",700,350, "dekt_jspjglyyxx.do?method=view&sqid=" + sqid+ "&xh=" + xh);
}
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='view(\""
			+ rowObject["sqid"] + "\",\"" + rowObject["xh"] + "\");'>" + "�ظ�"
			+ "</a>";
}

//�л�lab
function selectTab(obj,pjlx){
	jQuery("#pjlx").val(pjlx);
	var xxdm = jQuery("#xxdm").val();
	if (pjlx == "dpj"){
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}


		</script>
	</head>

	<body style="min-height: 800px;">
		<input type="hidden" value="dpj" id="pjlx"/>
		<input type="hidden" value="${zzshen}" id="zzshen"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_jspjglsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dpj');"><span>���ظ�</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ypj');"><span>�ѻظ�</span></a></li>
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
