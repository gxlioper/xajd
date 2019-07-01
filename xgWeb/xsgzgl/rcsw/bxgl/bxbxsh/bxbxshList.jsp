<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/bxgl/bxbxsh/js/bxbxsh.js"></script>
		<script type="text/javascript">
			     var gridSetting = {
							caption:"ѧ�����ձ��������Ϣ",
							pager:"pager",
							url:"rcswBxglBxbxsh.do?method=bxbxshList&type=query",
							colList:[
							   {label:'sqid',name:'sqid', index: 'sqid',key:true,hidden:true},
							   {label : 'splc', name : 'splc', index : 'splc',hidden : true },
							   {label:'ѧ��',name:'xh', index: 'xh', width : '10%',formatter:xhLink},
							   {label:'����',name:'xm', index: 'xm',width : '8%'},
							   <logic:notEqual name="xxdm" value="13871">
							   	{label:'֤������',name:'zlbh', index: 'zlbh',width : '18%'},
							   </logic:notEqual>
							   <logic:equal name="xxdm" value="13871">
							   	{label:'����(סԺ)��',name:'zlbh', index: 'zlbh',width : '18%'},
							   </logic:equal>
							    <logic:notEqual name="xxdm" value="13871">
								   {label:'����׼��',name:'clzbmc', index: 'clzbmc', width : '6%'},
								   </logic:notEqual>
								   <logic:equal name="xxdm" value="13871">
								   {label:'����ԭ��',name:'clzbmc', index: 'clzbmc', width : '6%'},
								   </logic:equal>
							   {label:'��������',name:'bxxzmc', index: 'bxxzmc', width : '6%'},
							   <logic:equal name="xxdm" value="13871">
							   {label:'ҽ�Ʒ���/Ԫ',name:'ylzd1', index: 'ylzd1', width : '6%'},
							   </logic:equal>
							   {label:'�������/Ԫ',name:'bxje', index: 'bxje', width : '6%'},
							   {label:'����',name:'lxmc', index: 'lxmc', width : '6%'},
							   <logic:equal name="xxdm" value="13871">
							   {label:'��Ʊʱ��',name:'csfysj', index: 'csfysj'},
							   </logic:equal>
							   <logic:notEqual name="xxdm" value="13871">
							   {label:'��������ʱ��',name:'csfysj', index: 'csfysj'},
							   </logic:notEqual>
							   {label:'���״̬',name:'shztmc', index: 'shztmc',width : '6%'},
							   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
							   { label:'���Id',name:'shid', index: 'shid',hidden:true},
								{ label:'��λId',name:'gwid', index: 'gwid',hidden:true}
							],
							params:{shzt:"dsh"},
							sortname: "csfysj",
						 	sortorder: "desc",
						 	radioselect:false
						}
			     var gridSetting2 = {
							caption:"ѧ�����ձ��������Ϣ",
							pager:"pager",
							url:"rcswBxglBxbxsh.do?method=bxbxshList&type=query",
							colList:[
							   {label:'sqid',name:'sqid', index: 'sqid',key:true,hidden:true},
							   { label : 'splc', name : 'splc', index : 'splc',hidden : true },
							   {label:'ѧ��',name:'xh', index: 'xh', width : '10%',formatter:xhLink},
							   {label:'����',name:'xm', index: 'xm',width : '8%'},
							   {label:'֤������',name:'zlbh', index: 'zlbh',width : '18%'},
							   {label:'����׼��',name:'clzbmc', index: 'clzbmc', width : '6%'},
							   {label:'��������',name:'bxxzmc', index: 'bxxzmc', width : '6%'},
							   {label:'�������/Ԫ',name:'bxje', index: 'bxje', width : '6%'},
							   {label:'����',name:'lxmc', index: 'lxmc', width : '6%'},
							   <logic:equal name="xxdm" value="13871">
							   {label:'��Ʊʱ��',name:'csfysj', index: 'csfysj'},
							   </logic:equal>
							   <logic:notEqual name="xxdm" value="13871">
							   {label:'��������ʱ��',name:'csfysj', index: 'csfysj'},
							   </logic:notEqual>
							   {label:'���״̬',name:'shztmc', index: 'shztmc',width : '6%'},
							   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
							   { label:'���Id',name:'shid', index: 'shid',hidden:true},
								{ label:'��λId',name:'gwid', index: 'gwid',hidden:true}
							],
							params:{shzt:"ysh"},
							sortname: "csfysj",
						 	sortorder: "desc"
						}
			     jQuery(function(){
						var map = getSuperSearch();
						map["shzt"]="dsh";
						gridSetting["params"] = map;
						jQuery("#dataTable").initGrid(gridSetting);
					});

		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/rcswBxglBxbxsh?method=bxbxshList&type=query">
	        <input type="hidden" name="shkg" id="shkg" value="${shkg}"/>
			<input type="hidden" id="shzt" value="dsh"/>
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="bxsh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelSh();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="splcInfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span style="width: 50%"> ѧ�����ձ��������Ϣ<a id="title" href="javascript:;" style="float:right;margin-right:30px;color: blue"></a> </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
