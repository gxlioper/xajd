<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/szdw/bjlh/fdykh/js/fdykhcptj_new.js"></script>
		<script type="text/javascript"><%--
			jQuery(function(){
				
				jQuery("[name=zcxm]:not(:disabled)").bind("click",function(){
					var index = jQuery(this).attr("index");
					var liName = jQuery("#tabUl li.ha").attr("clzt");
					
					if ("dcl" == liName){
						var gridSetting = getDclGird();
						jQuery("#dataTable").initGrid(gridSetting);
					} else {
						var gridSetting = getYclGrid();
						jQuery("#dataTable").initGrid(gridSetting);
					}
				});
				var map = getSuperSearch();
				dclGrid["params"]=map;
				jQuery("#dataTable").initGrid(dclGrid);
			});
			
		--%>
		jQuery(function(){
			var gridSettingRS = getRSGrid();
			var map = getSuperSearch();
			map["tjlx"] = "RS";
			gridSettingRS["params"]=map;
			jQuery("#dataTable").initGrid(gridSettingRS);
		});
				
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
	<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					��Чƽ�����㷨��ѧ���Ѵ���������԰༶������������ڵ��ڲ������õ�ѧ��������Ч����������Чƽ���ֵ��ڵ�ǰƽ���֣���������㡣
					<br/>
					<b>�ܷ�=ѧ�������Чƽ����*Ȩ��+����С����ƽ����*Ȩ��</b>
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/bjlh_fdykh" method="post" styleId="fdykhtjForm">
			<input type="hidden" id="tjlx" value="RS"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
				<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<li><a href="#" onclick="exportConfig();return false;" class="btn_dc">����</a></li>
						</ul>
					</div>
				</div>
			<!-- �������� -->	
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%" id="tabUl">
	      	<li class="ha"><a href="javascript:void(0);"  onclick="chageTB(this,'0');"><span>������</span></a></li>
			<li class="ycl"><a href="javascript:void(0);"  onclick="chageTB(this,'1');"><span>���༶</span></a></li>
			<li class="ycl"><a href="javascript:void(0);"  onclick="chageTB(this,'2');"><span>��ְ��</span></a></li>
	      </ul>
	    </div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>��ѯ��� </span>
			</h3>
			<table id="dataTable" style="width:100%;"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
