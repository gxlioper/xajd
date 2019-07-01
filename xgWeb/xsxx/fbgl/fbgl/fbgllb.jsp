<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/fbgl/js/fb.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/gzsd/gzyl.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"��ѯ���",
				pager:"pager",
				url:"fbglfbbase.do?method=list&type=query",
				colList:[
				   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
				   {label:'�꼶',name:'nj', index: 'nj'},
				   {label:'<bean:message key="lable.xb" />',name:'bmmc', index: 'bmmc'},
				   {label:'רҵ',name:'zymc', index: 'zymc'},
				   {label:'���',name:'pyccmc', index: 'pyccmc'},
				   {label:'ѧ��',name:'xz', index: 'xz'},
				   {label:'����',name:'rs', index: 'rs'},
				   {label:'����/Ů��',name:'nvqk', index: 'nvqk'},
				   {label:'δ�ְ�/�ѷְ�',name:'fbqk', index: 'fbqk'}
				]
	} 
	 var gridSetting2 = {
				caption:"��ѯ���",
				pager:"pager",
				url:"fbgl.do?method=yfblist&type=query",
				colList:[
				   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
				   {label:'�༶����',name:'bjdm', index: 'bjdm',hidden:true},
				   {label:'������',name:'ksh', index: 'ksh'},
				   {label:'����',name:'xm', index: 'xm'},
				   {label:'�Ա�',name:'xb', index: 'xb'},
				   {label:'�꼶',name:'nj', index: 'nj'},
				   {label:'<bean:message key="lable.xb" />',name:'bmmc', index: 'bmmc'},
				   {label:'רҵ',name:'zymc', index: 'zymc'},
				   {label:'רҵ',name:'zydm', index: 'zydm',hidden:true},
				   {label:'���',name:'pyccmc', index: 'pyccmc'},
				   {label:'ѧ��',name:'xz', index: 'xz'},
				   {label:'���ڰ༶',name:'bjmc', index: 'bjmc'},
				   {label:'Ͷ���ɼ�',name:'tdcj', index: 'tdcj'},
				   {label:'��Դ��',name:'sydmc', index: 'sydmc'},
				   {label:'�ְ����id',name:'fbgz', index: 'fbgz',hidden:true},
				   {label:'�ְ����',name:'fbgzmc', index: 'fbgzmc',formatter:ckgz}
				]
	} 
			jQuery(function($) {
				$("#tsxx").hide();
				$("#tzfb").hide();
				var map = getSuperSearch();
				map["fbzt"] = $("#fbzt").val();
				gridSetting["params"] = map;
				$("#dataTable").initGrid(gridSetting);
				//�Ƿ��ж�Ӧ�ְ����
				if (!isHaveGzxx("FBGZ")) {
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
		<html:form action="/fbglbbgl.do?method=list&type=query" styleId="myform">
			<input type="hidden" value="${fbzt}" id="fbzt"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li id="fb">
							<a href="javascript:void(0);" onclick="fb();return false;"
								class="btn_xg">�ְ�</a>
						</li>
						<li id="tzfb">
							<a href="javascript:void(0);" onclick="tzbj();return false;"
								class="btn_xg">�����༶</a>
						</li>
						<li id="scfb">
							<a href="javascript:void(0);" onclick="qxfb();return false;"
								class="btn_sc">ȡ���ְ�</a>
						</li>
					</ul>
				</div>
				</logic:equal>
			<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wfb');"><span>�Զ��ְ�</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'yfb');"><span>�ְ���</span></a></li>
			        <span id="tsxx" style="text-align: right;color: red;margin-left: 300px;font-size: 12px;display: none;">�ְ����δ�趨��δ���ã�</span>
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
			<div id="pager"></div>
		</div>
	</body>
</html>
