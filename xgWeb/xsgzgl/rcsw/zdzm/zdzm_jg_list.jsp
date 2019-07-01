<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zdzm/js/zdzm_jg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript" src="xsgzgl/rcsw/zdzm/js/print.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		caption : "�ڶ�֤�������",
		pager : "pager",
		url : "rcsw_zdzm_jggl.do?method=queryZdzmJgList&type=query",
		colList : [
				{ label : 'key', name : 'zdzmjgid', index : 'zdzmjgid',key : true, hidden : true },
				{ name : 'sjly', index : 'sjly', hidden : true },
				{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter : xhLink },
				{ label : '����', name : 'xm', index : 'xm', width : '10%' },
				{ label : '�꼶', name : 'nj', index : 'nj', width : '5%' },
				{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xydm', width : '13%' },
				{ label : 'רҵ', name : 'zymc', index : 'zydm', width : '13%' },
				{ label : '�༶', name : 'bjmc', index : 'bjdm', width : '13%' },
				{ label : '�������', name : 'pyccmc', index : 'pycc', width : '8%' },
				{ label : '����ʱ��', name : 'sqsj', index : 'sqsj', width : '8%' }],
		sortname : "sqsj", sortorder : "desc" }

	jQuery(function() {
		gridSetting["params"] = getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);

	});


	/**
	����
	*/
	function importConfig(){
		toImportData("IMPORT_RCSW_ZDZM");
		return false;
	}
	
	/**
	 * ѧ������
	 * 
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function xhLink(cellValue, rowObject) {
		var onclickfn = "onclick=\""
				+ "showDialog('�ڶ�֤����������' , 720 , 300 , 'rcsw_zdzm_jggl.do?method=viewZdzmJg&zdzmjgid="
				+ rowObject['zdzmjgid'] + "')" + "\"";

		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
				+ "</a>";

		return el;
	}
</script>
	</head>

	<body>
		<input type="hidden" name="kgzt" id="kgzt" value="${csszModel.ksqkg }"/>
		<input type="hidden" name="xzkg" id="xzkg" value="${csszModel.xzkg }"/>
		<input type="hidden" name="kxzkz" id="kxzkz" value="${csszModel.kxzkz }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/rcsw_zdzm_sqgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addZdzmJg();return false;"  title="����ð�ť�����������дҳ�档">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="updateZdzmJg();return false;" class="btn_xg" title="ѡ��һ����¼������ð�ť���޸������">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="deleteZdzmJg();return false;" class="btn_sc" title="ֻ��ȡ��δ��˵ļ�¼���Ѿ�����˵Ĳ���ȡ����" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">����</a>
						</li>	
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="printZdzmJg('rcsw_zdzm_jggl.do?method=doPrint');return false;" class="btn_down">�����ڶ�֤��</a></li>						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�ڶ�֤������&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
