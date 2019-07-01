<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/tsxs/js/tsxsXx.js"></script>
		<script type="text/javascript">
			//��ѯ�����
			var gridSetting ;
			var gridSetting1;
			var gridSetting2;
			var gridSetting3;
			jQuery(function(){
				var url = "xpj_tsxs.do?method=tsxsXx&type=query";
				url += "&xn=" + jQuery("#xn").val();
				url += "&xq=" + jQuery("#xq").val();
				url += "&lxdm=" + jQuery("#lxdm").val();
				gridSetting = {
						caption:"����ѧ��ά��",
						pager:"pager",
						url:url,
						colList:[
							{label:'key',name:'id', index: 'id',key:true ,hidden:true},
							{label:'ѧ��',name:'xh', index: 'xh',width:'12%'},
							{label:'����',name:'xm', index: 'xm',width:'8%'},
							{label:'�꼶',name:'nj', index: 'nj',width:'10%'},
							{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'20%'},
							{label:'רҵ',name:'zymc', index: 'zydm',width:'20%'},
							{label:'�༶',name:'bjmc', index: 'bjdm',width:'20%'}
						],
						sortname: "lxdm,xh",
					 	sortorder: "asc"
						};
				gridSetting1 = {
						caption:"����ѧ��ά��",
						pager:"pager",
						url:url,
						colList:[
							{label:'key',name:'id', index: 'id',key:true ,hidden:true},
							{label:'ѧ��',name:'xh', index: 'xh',width:'12%'},
							{label:'����',name:'xm', index: 'xm',width:'8%'},
							{label:'�꼶',name:'nj', index: 'nj',width:'10%'},
							{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'20%'},
							{label:'רҵ',name:'zymc', index: 'zydm',width:'20%'},
							{label:'�༶',name:'bjmc', index: 'bjdm',width:'20%'},
							{label:'Ӣ��ɼ�һ',name:'kc1', index: '',width:'20%'},
							{label:'Ӣ��ɼ���',name:'kc2', index: '',width:'20%'},
							{label:'CET-4�ɼ�',name:'cet4', index: '6',width:'20%'}
						],
						sortname: "lxdm,xh",
					 	sortorder: "asc"
						};
				var url2 = "xpj_tsxs.do?method=tsxsDtjXx";
				url2 += "&xn=" + jQuery("#xn").val();
				url2 += "&xq=" + jQuery("#xq").val();
				url2 += "&lxdm=" + jQuery("#lxdm").val();
				gridSetting2 = {
						caption:"����ѧ��ά��",
						pager:"pager",
						url:url2,
						//url:"xsxx_xsgl.do?method=showStudentsForTsxsByTy&type=query&xn="+jQuery("#xn").val()+"&xq="+jQuery("#xq").val()+"&lxdm="+jQuery("#lxdm").val(),
						colList:[
						   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',key:true},
						   {label:'����',name:'xm', index: 'xm',width:'13%'},
						   {label:'�꼶',name:'nj', index: 'nj',width:'6%'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
						   {label:'רҵ',name:'zymc', index: 'zydm',width:'15%'},
						   {label:'�༶',name:'bjmc', index: 'bjdm',width:'18%'}
						],
						sortname: "xh",
					 	sortorder: "asc"
						};
				gridSetting3 = {
						caption:"����ѧ��ά��",
						pager:"pager",
						url:url2,
						//url:"xsxx_xsgl.do?method=showStudentsForTsxsByTy&type=query&xn="+jQuery("#xn").val()+"&xq="+jQuery("#xq").val()+"&lxdm="+jQuery("#lxdm").val(),
						colList:[
						   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',key:true},
						   {label:'����',name:'xm', index: 'xm',width:'13%'},
						   {label:'�꼶',name:'nj', index: 'nj',width:'6%'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
						   {label:'רҵ',name:'zymc', index: 'zydm',width:'15%'},
						   {label:'�༶',name:'bjmc', index: 'bjdm',width:'18%'},
						   {label:'Ӣ��ɼ�һ',name:'kc1', index: '',width:'20%'},
						   {label:'Ӣ��ɼ���',name:'kc2', index: '',width:'20%'},
						   {label:'CET-4�ɼ�',name:'cet4', index: '6',width:'20%'}
						],
						sortname: "xh",
					 	sortorder: "asc"
						};
				var map = getSuperSearch();
				map["fpzt"]="0";
				if(jQuery("#xxdm").val() == '10279'){
					gridSetting1["params"]=map;
					jQuery("#dataTable").initGrid(gridSetting1);
				}else{
					gridSetting["params"]=map;
					jQuery("#dataTable").initGrid(gridSetting);
				}
				var api = frameElement.api, W = api.opener;
				if(W.jQuery('#writeAble').val()=='yes'){
					jQuery('#tsxsbtn').attr("style","display:display");
				}
			});
		</script>
	</head>

	<body>
		<html:form action="/xpj_tsxs">
			<html:hidden property="xn" styleId="xn"/>
			<html:hidden property="xq" styleId="xq"/>
			<html:hidden property="lxdm" styleId="lxdm"/>
			<input type="hidden" id="fpzt" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox" id="tsxsbtn" style="display: none;">
					<ul>
						<li id="li_xz" style="display: none;">
							<a href="javascript:void(0);" onclick="selStudent();return false;" class="btn_zj" id="xzButton">����</a>
						</li>
						<li id="li_sc">
							<a href="javascript:void(0);" onclick="delTsxs();return false;" title="��ѡһ���������¼��ɾ������ѧ��" class="btn_sc">ɾ��</a>
						</li>
						<li id="li_dc">
							<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>�����</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>δ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
			<span><font color="blue">${xn}&nbsp;${xqmc}&nbsp;${lxmc}&nbsp;&nbsp;</font>����ѧ��ά��</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
