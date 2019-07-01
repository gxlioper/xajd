<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/znxgl/wdznx/js/wdznx.js"></script>
		<script type="text/javascript">

		var gridSetting2 = {
				caption:"������",
				pager:"pager",
				url:"wdznx.do?method=getFjxlist&type=query",
				colList : [
							{ label : 'key', name : 'xjbh', index : 'xjbh',key : true, hidden : true },
							{ label : 'jsrbh', name : 'jsrbh', index : 'jsrbh', hidden : true },
							{ label : '����', name : 'xjzt', index : 'xjzt', width : '40%',formatter : fjxBtLink },
							{ label : '������', name : 'jsrxm', index : 'jsrxm', width : '10%'},
							{ label : '����ʱ��', name : 'fssj', index : 'fssj', width : '30%' }
							],
				sortname: "fssj",
			 	sortorder: "desc"
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting2);
		});
	
			
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/wdznx">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li ><a href="znxgl_wdznx.do" ><span>�ռ���</span></a></li>
			        <li class="ha"><a href="wdznx.do?method=getFjxlist"><span>������</span></a></li>
			      </ul>
			    </div>
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" onclick="xx();return false;" 
								   class="btn_xx">д��</a>
							</li>						
							<li><a href="javascript:void(0);" onclick="fjxsc();return false;" 
								   class="btn_sc">ɾ��</a></li>	
						</logic:equal>
					</ul>
				</div>
				
			
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>������ </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
