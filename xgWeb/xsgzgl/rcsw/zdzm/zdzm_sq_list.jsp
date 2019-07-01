<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zdzm/js/zdzm_sq.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zdzm/js/print.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		caption : "�ڶ�֤�������",
		pager : "pager",
		url : "rcsw_zdzm_sqgl.do?method=queryZdzmSqList&type=query",
		colList : [
				{ label : 'key', name : 'zdzmsqid', index : 'zdzmsqid',key : true, hidden : true },
				{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter : xhLink },
				{ label : '����', name : 'xm', index : 'xm', width : '10%' },
				{ label : '�꼶', name : 'nj', index : 'nj', width : '5%' },
				{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xydm', width : '13%' },
				{ label : 'רҵ', name : 'zymc', index : 'zydm', width : '13%' },
				{ label : 'splcid', name : 'splcid', index : 'splcid',hidden : true},
				{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
				{ label : '�༶', name : 'bjmc', index : 'bjdm', width : '13%' },
				{ label : '�������', name : 'pyccmc', index : 'pycc', width : '8%' },
				{ label : '����ʱ��', name : 'sqsj', index : 'sqsj', width : '8%' },
				{ label : '���״̬', name : 'shztmc', index : 'shzt', width : '6%' }],
		sortname : "sqsj", sortorder : "desc" }

	jQuery(function() {
		gridSetting["params"] = getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);

	});

	/**
	 * ѧ������
	 * 
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function xhLink(cellValue, rowObject) {
		var onclickfn = "onclick=\""
				+ "showDialog('�ڶ�֤����������' , 720 , 350 , 'rcsw_zdzm_sqgl.do?method=viewZdzmSq&zdzmsqid="
				+ rowObject['zdzmsqid'] + "')" + "\"";

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
		<input type="hidden" name="isopen" id="isopen" value="${csszModel.isopen }" />
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
					ȡ�����룺����ȡ����δ��ˡ�������
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/rcsw_zdzm_sqgl">
			
		
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addZdzmSq();return false;"  title="����ð�ť�����������дҳ�档">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="updateZdzmSq();return false;" class="btn_xg" title="ѡ��һ����¼������ð�ť���޸������">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="deleteZdzmSq();return false;" class="btn_sc" title="ֻ��ȡ��δ��˵ļ�¼���Ѿ�����˵Ĳ���ȡ����" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a>
						</li>	
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">����</a>
						</li>	
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="printZdzmSq('rcsw_zdzm_sqgl.do?method=doPrint');return false;" class="btn_down">�����ڶ�֤��</a></li>						
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
