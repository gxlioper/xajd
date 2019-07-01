<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "dzdy_zzsjyj.do?method=getZzyjList&type=query",
				colList : [
                    {label : 'key', name : 'dzbid', index : 'dzbid', hidden : true},
                    {label : '��֧������', name : 'dzbmc', index : 'dzbmc', width : '20%',formatter : dzbLink},
                    {label : 'ѧ��', name : 'xh', index : 'xh', width : '15%'},
                    {label : '����', name : 'xm', index : 'xm', width : '10%'},
                    {label : 'רҵ', name : 'zymc', index : 'zymc', width : '15%'},
                    {label : '�༶', name : 'bjmc', index : 'bjmc', width : '13%'},
                    {label : '��ϵ�绰', name : 'lxdh', index : 'lxdh', width : '10%'},
					{label : '�뵳ʱ��', name : 'rdrq', index : 'rdrq', width : '10%'},
                    {label : '��ʱδת��', name : 'ccts', index : 'ccts', width : '10%'}

			],
				sortname : "xh",
				sortorder : "desc",
                multiselect:false,
				radioselect:true
			}
			jQuery("#dataTable").initGrid(gridSetting);
		})

        //��֧���鿴
        function dzbLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='View(\""
                + rowObject['dzbid'] + "\");'>" + cellValue
                + "</a>";
        }
        function View(dzbid) {
            showDialog("��֧����Ϣ", 900, 450, "dzdy_dzbgl.do?method=getDzbInfo&dzbid="+dzbid);
        }


		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dzdy_cygl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="js" styleId="js" value="${userType}"/>
		</html:form>
		
		<div class="main_box">

			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
