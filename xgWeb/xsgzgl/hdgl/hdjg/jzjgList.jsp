<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		jQuery(function(){
            var gridSetting = {
                caption : "100�������б�",
                pager : "pager",
                url : "hdgl_hdjg.do?method=jzjgList&type=query",
                colList : [
                    { label : 'ѧ��', name : 'xh', index : 'xh', width : '15%'},
                    { label : '����', name : 'xm', index : 'xm', width : '10%' },
                    { label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
                    { label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '15%' },
                    { label : '��Ժ', name : 'symc', index : 'symc', width : '15%' },
                    { label : 'רҵ', name : 'zymc', index : 'zymc', width : '15%' },
                    { label : '�����༶', name : 'bjmc', index : 'bjmc', width : '15%' },
                    { label : 'רҵ�༶', name : 'zybjmc', index : 'zybjmc', width : '15%' },
                    <logic:equal value="stu" name="userType">
						{ label : '�����', name : 'hdmc', index : 'hdmc', width : '10%' },
						{ label : '��ý���', name : 'jxmc', index : 'jxmc', width : '10%' },
						{ label : 'ѧ��', name : 'xf', index : 'xf', width : '10%' }
                    </logic:equal>
                    <logic:notEqual value="stu" name="userType">
                    { label : '�����', name : 'hdgs', index : 'hdgs', width : '10%' ,formatter:xhLink }
                    </logic:notEqual>

                    ],
                sortname : "xh",
                sortorder : "desc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})

        function viewPjxq(xh) {
            showDialog("�鿴��������", 720, 350, "hdgl_hdjg.do?method=jzjgXqList&xh=" + xh);
        }
        function xhLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='viewPjxq(\""
                + rowObject["xh"] + "\");'>" + cellValue
                + "</a>";
        }

		function searchRs() {
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		//��ӡ
		function print() {
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if(ids.length == 0  || ids.length > 1) {
				showAlertDivLayer("��ѡ��һ����¼���е�����");
			}else{
				var xh = rows[0]['xh'];
				window.open("hdgl_hdxx.do?method=xscjdPri&xh="+xh);
			}
		}
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/hdgl_hdjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="print();return false;" class="btn_down">��ӡ�ɼ���</a></li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>100�������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
