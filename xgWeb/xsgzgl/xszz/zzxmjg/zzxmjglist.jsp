<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/zzxmjg/js/zzxmjglist.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript">
		
		function drxx(){			
			toImportData("IMPORT_N100104");
			return false;
		}

		//����
		function dr() {
			// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
			toImportDataNew("IMPORT_ZZXMJG");
			return false;

		}
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xszz_knsjg" >
			<input type="hidden" name="tableName" id="tableName" value="view_xg_xszz_new_zzxmjgb"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
			<input type="hidden" id="iscanCopy" value="${iscanCopy}"/> 
			
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
							<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>	
							<logic:equal value="10335" name="xxdm">
							<li><a href="javascript:void(0);" onclick="copy();" class="btn_sz">���˸���</a></li>						
							</logic:equal>
							<logic:equal value="13011" name="xxdm">
<%--								<li><a href="javascript:void(0);" onclick="qdjddr();return false;" class="btn_dr" >����</a></li>--%>
								<li><a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr" >����</a></li>							
							</logic:equal>
							<logic:notEqual value="13011" name="xxdm">
								<logic:notEqual value="10335" name="xxdm">
									<li><a href="#" onclick="drxx();return false;" class="btn_dr">����</a></li>
								</logic:notEqual>					
								<logic:equal value="10335" name="xxdm">
									<li><a href="javascript:void(0);" onclick="zzjgdr();return false;" class="btn_dr" >����</a></li>
								</logic:equal>								
							</logic:notEqual>
							
						</logic:equal>
						
						<li>
							<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
						</li>
						
						<%--<li><a href="#" class="btn_dc" onclick="exportData();return false;">��������</a></li>--%>
						<!--  
						<li><a href="javascript:void(0);" onclick="printXmsq('xszz_zzxmjg.do?method=printJsp');return false;" class="btn_dy">��ӡ�����</a></li>		
						-->
						<%--���������Ի� --%>
						<logic:equal value="13431" name="xxdm">
							<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">��������������</a></li>	
						</logic:equal>
						<logic:notEqual value="13431" name="xxdm">
							<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">���صǼǱ�</a></li>
						</logic:notEqual>
							
						<logic:equal value="12861" name="xxdm">
						      <li><a href="javascript:void(0);" onclick="exportzzHzData();return false;" class="btn_dc">���ܵ���</a></li>	
						</logic:equal>
						<%--ɽ��������ҽ���Ի���ӡstart --%>			
						<logic:equal value="12947" name="xxdm">
						      <li><a href="javascript:void(0);" onclick="getshzxjHzexcel();return false;" class="btn_dc">�����ѧ����ܵ���</a></li>	
						      <%-- 
						      <li><a href="javascript:void(0);" onclick="getgjlzjHzexcel();return false;" class="btn_dc">������־��ѧ����ܵ���</a></li>	--%>
						      <%-- 
						      <li><a href="javascript:void(0);" onclick="getSdxm_gjzxjhzexcel();return false;" class="btn_dc">������ѧ����ܵ���</a></li>	
						      --%>
						      <%-- 
						      <li><a href="javascript:void(0);" onclick="getSdxm_gjlzjhzmbexcel();return false;" class="btn_dc">������־��ѧ����������ģ�嵼��</a></li>	
						      --%>
						</logic:equal>
						<%--ɽ��������ҽ���Ի���ӡend --%>
						<!-- ���ս�����ѧ -->
						<logic:equal value="10878" name="xxdm">
							   <li><a href="javascript:void(0);" onclick="gjjxjdc();return false;" class="btn_dc">���ҽ�ѧ����������</a></li>
							   <li><a href="javascript:void(0);" onclick="gjzxjdc();return false;" class="btn_dc">������ѧ����������</a></li>
							   <li><a href="javascript:void(0);" onclick="gjlzjdc();return false;" class="btn_dc">������־��ѧ����������</a></li>
						</logic:equal>				
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��������б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
