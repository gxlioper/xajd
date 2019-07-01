<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/sxzzjygl/bjhdgl/bjhdjg/js/bjhdJg.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "bjhdgl_bjhdjg.do?method=bjhdJg&type=query",
				colList : [
							{ label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
                    		{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
							{ label : 'lcywid', name : 'lcywid', index : 'lcywid',hidden : true },
							{ label : '�����', name : 'hdmc', index : 'hdmc', width : '11%',formatter: hdLink},
							{ label : '�༶����', name : 'bjmc', index : 'bjmc', width : '10%' },
							{ label : '�����', name : 'hdzt', index : 'hdzt', width : '11%' },
					        { label : '�Ԥ��', name : 'hdys', index : 'hdys', width : '6%' },
							{ label : '�����', name : 'hdrq', index : 'hdrq', width : '6%' },
							{ label : '�Ƿ�Ϊ��Ʒ���', name : 'sfjpmc', index : 'sfjpmc', width : '6%' }],
					sortname : "hdrq",
				    sortorder : "desc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})

        //��鿴
        function hdLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='View(\""
                + rowObject['jgid'] + "\");'>" + cellValue
                + "</a>";
        }


        function View(jgid) {
            showDialog("���Ϣ", 900, 450, "bjhdgl_bjhdjg.do?method=getHdInfo&jgid=" + jgid );
        }

        //����
        function importConfig(){
            toImportDataNew("IMPORT_SXZZJYGL_GRXFJSJG");
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
		
		<html:form action="/bjhdgl_bjhdjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >���</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="edit();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="upload();return false;" class="btn_shuc">�ϴ�����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="rdjpbjhd();return false;" class="btn_xg">�϶���Ʒ�༶�</a>
						</li>
							<li>
								<a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a>
							</li>
						</logic:equal>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						
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
