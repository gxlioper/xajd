<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		caption : "ѧ����չ��Ϣ�����",
		pager : "pager",
		url : "xsxx_kzxxjggl.do?method=list&actionType=query",
		colList : [
				{ label : 'key', name : 'jgid', index : 'jgid',key : true, hidden : true },
				{ name : 'sjly', index : 'sjly', hidden : true },
				{ label : 'ѧ��', name : 'xh', index : 'xh', width : '7%',formatter : xhLink },
				{ label : '����', name : 'xm', index : 'xm', width : '10%' },
				{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
				{ label : 'ѧԺ', name : 'xymc', index : 'xydm', width : '13%' },
				{ label : 'רҵ', name : 'zymc', index : 'zydm', width : '13%' },
				{ label : '�༶', name : 'bjmc', index : 'bjdm', width : '13%' },
				{ label : '��¼ʱ��', name : 'jrsj', index : 'jrsj', width : '8%' }],
		sortname : "jrsj", sortorder : "desc" }

	jQuery(function() {
		gridSetting["params"] = getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);

	});

	/**
	 * �߼���ѯ
	 * @return
	 */
	function searchRs() {
		var map = getSuperSearch();
		jQuery("#dataTable").reloadGrid(map);
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
				+ "showDialog('ѧ����չ��Ϣ����' , 800 , 550 , 'xsxx_kzxxjggl.do?method=ck&jgid="
				+ rowObject['jgid'] + "')" + "\"";

		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
				+ "</a>";

		return el;
	}
	
	function ck(){
		var rows = jQuery("#dataTable").getSeletRow();
				
				if(rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
					return false;
				}
				
				showDialog("ѧ����չ��Ϣ�鿴",800,550,"xsxx_kzxxjggl.do?method=ck&jgid="+rows[0]["jgid"]);
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
						<!--<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addZdzmJg();return false;" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="updateZdzmJg();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="deleteZdzmJg();return false;" class="btn_sc"  >ɾ��</a>
						</li>
						--></logic:equal>		
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="ck();return false;" >�鿴</a>
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
				<span>ѧ����չ��Ϣ&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
