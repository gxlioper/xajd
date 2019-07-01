<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/sxzzjygl/bjhdgl/bjhdsq/js/bjhdSq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "bjhdgl_bjhdsq.do?method=bjhdSq&type=query",
				colList : [
							{ label : 'key', name : 'sqid', index : 'sqid',key : true, hidden : true },
							{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
							{ label : '�����', name : 'hdmc', index : 'hdmc', width : '11%',formatter: hdLink},
							{ label : '�༶����', name : 'bjmc', index : 'bjmc', width : '10%' },
							{ label : '�����', name : 'hdzt', index : 'hdzt', width : '11%' },
                    		{ label : '�Ԥ��', name : 'hdys', index : 'hdys', width : '6%' },
							{ label : '�����', name : 'hdrq', index : 'hdrq', width : '6%' },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '6%' },
							{ label : '���״̬', name : 'shzt', index : 'shzt', hidden : true}],
					sortname : "hdrq",
				    sortorder : "desc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
        //��鿴
        function hdLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='View(\""
                + rowObject['sqid'] + "\");'>" + cellValue
                + "</a>";
        }


        function View(sqid) {
            showDialog("���Ϣ", 900, 450, "bjhdgl_bjhdsq.do?method=getHdInfo&sqid=" + sqid );
        }
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<logic:equal value="0" name="sqkg">
			<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��ǰδ�������룡
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
		</logic:equal>
		
		<html:form action="/ztbhgl_ztbhsq">
		<input type="hidden" name="sqkg" id="sqkg" value="${sqkg}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="edit();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submit();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="bjhdLcinfo();return false;"
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a>
						</li>
						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
