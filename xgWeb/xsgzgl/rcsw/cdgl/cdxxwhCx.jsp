<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/cdgl/js/cdxxwh.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		caption : "������Ϣ�б�",
		pager : "pager",
		url : "rcsw_cdgl_cdxxwh.do?method=query",
		colList : [
				{ label : 'key', name : 'cdid', index : 'cdid',key : true, hidden : true },
				{ label : '��������', name : 'cdmc', index : 'cdmc', width : '15%',formatter : cdmcLink },
				{ label : '¥��', name : 'ld', index : 'ld', width : '10%' },
				{ label : '����', name : 'fj', index : 'fj', width : '5%' },
				{ label : '��������', name : 'rnrs', index : 'rnrs', width : '5%' },
				{ label : '�շѱ�׼', name : 'sfbz', index : 'sfbz', width : '5%' },
				{ label : 'splcid', name : 'splcid', index : 'splcid',hidden : true },
				{ label : '���⿪��ʱ��', name : 'dwkfsj', index : 'dwkfsj', width : '15%' },
				{ label : '�Ƿ񿪷�����', name : 'sfkfsqmc', index : 'sfkfsqmc', width : '5%' },
				{ name : 'sfkfsq', index : 'sfkfsq', hidden : true },
				{ label : '�������', name : 'lcxx', index : 'shzt', lcxx : '15%' } ],
		sortname : "cdmc", sortorder : "desc" }

	jQuery(function() {
		gridSetting["params"] = getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);

	});

	/**
	 * ��������
	 * 
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function cdmcLink(cellValue, rowObject) {
		var onclickfn = "onclick=\""
				+ "showDialog('������Ϣ����' , 680 , 430 , 'rcsw_cdgl_cdxxwh.do?method=cdxxwhCk&cdid="
				+ rowObject['cdid'] + "')" + "\"";

		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
				+ "</a>";

		return el;
	}
</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_cdgl_cdxxwh">

			<%@ include file="/comm/hiddenValue.jsp"%>

			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj"
									onclick="addCdxx();return false;">����</a>
							</li>
							<li>
								<a href="javascript:void(0);"
									onclick="updateCdxx();return false;" class="btn_xg"
									>�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);"
									onclick="deleteCdxx();return false;" class="btn_sc"
									>ɾ��</a>
							</li>
						</logic:equal>
						
							<li>
								<a href="javascript:void(0);"
									onclick="exportConfig();return false;" class="btn_dc">����</a>
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
				<span>����ά��&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
