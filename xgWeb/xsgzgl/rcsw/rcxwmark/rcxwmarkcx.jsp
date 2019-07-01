<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwmark/js/rcxwmark.js"></script>
		<script type="text/javascript">
		var gridSetting = {};
		
		jQuery(function(){
			if('${cxlx}' == 'wclcx'){
				gridSetting = {
						caption : "δ�����б�",
						pager : "pager",
						url : "rcsw_rcxwmark.do?method=getWclCx&type=query",
						colList : [ {
							label : 'key',
							name : 'rcxwjgid',
							index : 'rcxwjgid',
							key : true,
							hidden : true
						}, {
							label : 'ѧ��',
							name : 'xh',
							index : 'xh',
							width : '10%',
							formatter : xhLink
						}, {
							label : '����',
							name : 'xm',
							index : 'xm',
							width : '6%'
						}, {
							label : '�༶',
							name : 'bjmc',
							index :'bjmc',
							width : '15%'
						}, {
							label : 'ѧ��',
							name : 'xn',
							index : 'xn',
							width : '10%'
						}, {
							label : '��Ϊ����',
							name : 'rcxwlbdlmc',
							index : 'rcxwlbdlmc',
							width : '10%'
						},{
							label : '��Ϊ���',
							name : 'rcxwlbmc',
							index : 'rcxwlbmc',
							width : '10%'
						},{
							label : '������ֵ',
							name : 'fz',
							index : 'fz',
							width : '5%'
						}],
						sortname : "xn",
						sortorder : "desc"
					}
			  }else{
				
					gridSetting = {
							caption : "�Ѵ����б�",
							pager : "pager",
							url : "rcsw_rcxwmark.do?method=getYclCx&type=query",
							colList : [{
								label : 'id',
								name : 'id',
								index :'id',
								hidden : true,
								key : true
							},{
								label : 'key',
								name : 'rcxwjgid',
								index : 'rcxwjgid',
								hidden : true
							}, {
								label : 'ѧ��',
								name : 'xh',
								index : 'xh',
								width : '10%',
							   formatter : xhLink
							}, {
								label : '����',
								name : 'xm',
								index : 'xm',
								width : '6%'
							}, {
								label : '�༶',
								name : 'bjmc',
								index :'bjmc',
								width : '5%'
							}, {
								label : '����ѧ��',
								name : 'xn',
								index : 'xn',
								width : '5%'
							}, {
								label : '��Ϊ����',
								name : 'rcxwlbdlmc',
								index : 'rcxwlbdlmc',
								width : '10%'
							},{
								label : '��Ϊ���',
								name : 'rcxwlbmc',
								index : 'rcxwlbmc',
								width : '10%'
							},{
								label : '������ֵ',
								name : 'fz',
								index : 'fz',
								width : '5%'
							},{
								label : '���ý���',
								name : 'xmmc',
								index : '',
								width : '5%'
							}],
							sortname : "xn",
							sortorder : "desc"
						}
			}
			   
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		}
		)
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<input type="hidden" id="cxlx" value="${cxlx}"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gygl_xyzsjggl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:equal name="cxlx" value="wclcx">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="sz();return false;"  >����</a>
						    </li>
						</logic:equal>
						<logic:equal name="cxlx" value="yclcx">
							<li>
							  <a href="javascript:void(0);" class="btn_qxsh" onclick="qxsz();return false;"  >ȡ������</a>
						    </li>
						    <li>
							 <a href="javascript:void(0);" onclick="xg();return false;" class="btn_xg" >�޸�</a>
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
			      <logic:equal name="cxlx" value="wclcx">
				     <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wsz');"><span>δ����</span></a></li>
			         <li><a href="javascript:void(0);" onclick="selectTab(this,'ysz');"><span>������</span></a></li>
			      </logic:equal>
			      <logic:notEqual name="cxlx" value="wclcx">
			      	 <li ><a href="javascript:void(0);" onclick="selectTab(this,'wsz');"><span>δ����</span></a></li>
			         <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'ysz');"><span>������</span></a></li>
			      </logic:notEqual>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�������������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
