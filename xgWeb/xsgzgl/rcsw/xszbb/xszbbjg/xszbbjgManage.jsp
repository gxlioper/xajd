<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xszbb/xszbbjg/js/xszbbjgManage.js"></script>
		<script language="javascript" src="js/LodopFuncs.js"></script>
		<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
		       <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
		</object>
		<script type="text/javascript">
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_xszbb_bbjggl">
		
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<logic:notEqual name="userType" value="stu">
					<logic:equal name="writeAble" value="yes">
						<!-- ��ť -->
						<div class="buttonbox">
							<ul>
								<li>
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="add();return false;" 
									>����</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg"
									>�޸�</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc"
									>ɾ��</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="lingq();return false;" class="btn_down"
									>��ȡ</a>
								</li>				
								<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
								<logic:equal name = "xxdm" value="11400">
									<li>
										<a href="javascript:void(0);" onclick="dyXszXaph('xsz_${xxdm }');return false;" class="btn_dy"
									>ѧ��֤��ӡ</a>
									</li>
								</logic:equal>	
								<logic:equal name = "xxdm" value="13431">
									<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">��У֤������</a></li>	
								</logic:equal>	
							</ul>
						</div>
					</logic:equal>
				</logic:notEqual>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>֤���������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
