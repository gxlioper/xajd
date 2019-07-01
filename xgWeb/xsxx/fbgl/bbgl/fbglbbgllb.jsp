<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/bbgl/js/fbglbbgl.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/gzsd/gzyl.js"></script>
		<script type="text/javascript">
		 var gridSetting = {
					caption:"��ѯ���",
					pager:"pager",
					url:"fbglbbgl.do?method=list&type=query",
					colList:[
					   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
					   {label:'�꼶',name:'nj', index: 'nj'},
					   {label:'<bean:message key="lable.xb" />',name:'xy', index: 'xy'},
					   {label:'רҵ',name:'zymc', index: 'zymc'},
					   {label:'���',name:'pyccmc', index: 'pyccmc'},
					   {label:'ѧ��',name:'xz', index: 'xz'},
					   {label:'����',name:'rs', index: 'rs'},
					   {label:'����/Ů��',name:'xbsl', index: 'xbsl'}
					]
		} 
		 var gridSetting2 = {
					caption:"��ѯ���",
					pager:"pager",
					url:"fbglbbgl.do?method=list&type=query&&bbzt=ybb",
					colList:[
					   {label:'pk',name:'pk', index: 'pk',hidden:true},
					   {label:'�༶����',name:'bjdm', index: 'bjdm' ,key:true,hidden:true},
					   {label:'�꼶',name:'nj', index: 'nj'},
					   {label:'<bean:message key="lable.xb" />',name:'xy', index: 'xy'},
					   {label:'רҵ',name:'zymc', index: 'zymc'},
					   {label:'���',name:'pyccmc', index: 'pyccmc'},
					   {label:'ѧ��',name:'xz', index: 'xz'},
					   {label:'����',name:'rs', index: 'rs'},
					   {label:'�༶����',name:'bjdm', index: 'bjdm'},
					   {label:'�༶',name:'bjmc', index: 'bjmc'},
					   {label:'�༶��������',name:'bjrssx', index: 'bjrssx'},
					   {label:'ѧ����',name:'xss', index: 'xss'},
					   {label:'������',name:'pzgzid', index: 'pzgzid',hidden:true},
					   {label:'������',name:'pzgzmc', index: 'pzgzmc',formatter:ckgz}
					]
		} 
			jQuery(function($) {
				$("#tzbb").hide();
				$("#tsxx").hide();
				$("#scbj").hide();
				$("#qxbb").hide();
				var map = getSuperSearch();
				map["bbzt"] = jQuery("#bbzt").val();
				gridSetting["params"] = map;
				$("#dataTable").initGrid(gridSetting);
				//�Ƿ��ж�Ӧ�����Ϣ
				if (isHaveGzxx("BBGZ")) {
					$("#tsxx").hide();
				}else{
					$("#tsxx").show();
				}
			});
</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<div style="" id="div_help" class="prompt">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				���ǰ�����趨�����ñ�����
			</p>
		</div>
		<html:form action="/fbglbbgl.do?method=list&type=query">
			<input type="hidden" value="wbb" id="bbzt"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li id="bb">
							<a href="javascript:void(0);" onclick="bb();return false;"
								class="btn_xg">���</a>
						</li>
						<li id="tzbb">
							<a href="javascript:void(0);" onclick="tzbj();return false;"
								class="btn_xg">�������</a>
						</li>
						<li id="qxbb">
							<a href="javascript:void(0);" onclick="scbj();return false;"
								class="btn_xg">ȡ�����</a>
						</li>
					</ul>
				</div>
				</logic:equal>
			<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
			<div class="comp_title" id="comp_title">
			      <ul style="width:90%;">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wbb');"><span>δ���</span></a></li>
			        <li><a id="ybb" href="javascript:void(0);" onclick="selectTab(this,'ybb');"><span>�ѱ��</span></a></li>
				    <span id="tsxx" style="text-align: right;color: red;margin-left: 400px;font-size: 12px;display: none;">������δ�趨��δ���ã�</span>
			      </ul>
			</div>
			</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span style="width: 50%">��ѯ��� <a id="title"
					href="javascript:;"
					style="float: right; margin-right: 30px; color: blue"></a> </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager" ></div>
		</div>
	</body>
</html>
