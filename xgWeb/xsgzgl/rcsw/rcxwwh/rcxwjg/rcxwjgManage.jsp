<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwwh/rcxwjg/js/rcxwjgManage.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		
		<script type="text/javascript">
	
		
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_rcxwwh_rcxwjggl">
			<input type="hidden" name="tableName" id="tableName" value="xg_rcsw_rcxwjg"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
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
							<logic:equal value="12867" name="xxdm">
								<li><a href="#" onclick="importConfig();return false;" class="btn_dr">����</a></li>	
							</logic:equal>
							<logic:notEqual value="12867"  name="xxdm">
								<li><a href="#" onclick="importData();return false;" class="btn_dr">����</a></li>	
							</logic:notEqual>
						</logic:equal>
						<!-- ��дȨ -->		
						<logic:notEqual value="10355" name="xxdm">		
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						</logic:notEqual>
						<logic:equal value="10355" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="rcxwjgExportData();return false;">����</a></li>	
						</logic:equal>
						<logic:equal value="10351" name="xxdm">
						<li><a href="#" class="btn_dc" onclick="xsPxsjDc();return false;">ѧ��Ʒ��ʵ����������</a></li>
						</logic:equal>	
						<logic:equal value="13023" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="rcxwsjDc();return false;">���ʲ����ֵ���</a></li>
						</logic:equal>
						<logic:equal value="10071" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="rcxwtjbDc();return false;">�ճ���Ϊͳ�Ʊ���</a></li>
						</logic:equal>
						<logic:equal value="10868" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="sxpdcjhzDc();return false;">�ɼ����ܵ���</a></li>
						</logic:equal>
						<logic:equal value="13431" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="rcxwtjbDc();return false;">�ӷ�ͳ�Ʊ���</a></li>
						</logic:equal>
						
						<!-- �㽭����ְҵѧԺ�����Ի���ӡ��������֪ͨ�� -->
						<logic:equal name="xxdm" value="12869">
							<li><a href="javascript:void(0);" onclick="printDykptzd();return false;" class="btn_down">��������֪ͨ��</a></li>
							<li><a href="javascript:void(0);" onclick="printWjcld();return false;" class="btn_down">Υ�ʹ���</a></li>
							<li><a href="javascript:void(0);" onclick="printJlspb();return false;" class="btn_down">����������</a></li>
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
				<logic:equal name="xxdm" value="13815">	
					<span>����ѧ����Ϣ����б�&nbsp;&nbsp; </span>
				</logic:equal>
				<logic:notEqual name="xxdm" value="13815">
					<logic:notEqual name="xxdm" value="13815">
						<span>�ճ���Ϊ��Ϣ����б�&nbsp;&nbsp; </span>
					</logic:notEqual>
				</logic:notEqual>
				<logic:equal name="xxdm" value="13431">
					<span>�ӷ���Ϣ����б�&nbsp;&nbsp; </span>
				</logic:equal>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
