<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxtjgl/jxjfp/js/jxjfp.js"></script>
	</head>

	<body style="min-height: 800px;">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
			</p>
		</div>
		
		<logic:present name="pjxmList">
			<div id="pjxmDiv">
				<logic:iterate id="p" name="pjxmList">
					<font style="display:none;" xmdm="${p.xmdm}" xmmc="${p.xmmc}" name="pjxm"></font>
				</logic:iterate>
			</div>
		</logic:present>
		
		<html:form action="/xpjpy_jxjfp">
			<input type="hidden" id="notFirst" value=""/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->	
				<div class="buttonbox">
					<ul>
						<li id="li_sh">
							<li><a href="#" class="btn_dc" onclick="jxjfpExport(); return false;">����</a></li>
							<li><a href="#" class="btn_dc" onclick="ffhzExport(); return false;">���Ż��ܵ���</a></li>
							<li><a href="#" class="btn_dc" onclick="gjjxjhzExport(); return false;">���ҽ�ѧ����ܵ���</a></li>
						</li>						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��ѧ���������һ���� </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
