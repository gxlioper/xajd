<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="xsgzgl/xyfd/jzppwh/yytj/js/yytj.js"></script>
	<script type="text/javascript">

        var gridSetting = {
            caption : "ԤԼȡ��ͳ���б�",
            pager : "pager",
            url : "xyfd_yytj.do?method=yytjList&type=query",
            colList : [
                { label : 'djh', name : 'djh', index : 'djh',key : true,hidden : true },
                { label : '�ǼǺ�', name : 'djh', index : 'djh',width:'12%'},
                { label : 'ְ����', name : 'zgh', index : 'zgh',width:'12%'},
                { label : '������Ա����', name : 'xm', index : 'xm',width:'12%'},
                { label : '������Ա����', name : 'fdjslx', index : 'fdjslx',width:'10%'},
                { label : '�ۼ�ȡ������', name : 'qxcs', index : 'qxcs',width:'10%'}
            ],
            radioselect:false
        }

        jQuery(function(){
            var map = getSuperSearch();
            map["cxmb"]="yyqx";
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });



	</script>
</head>

<body>
<div class="tab_cur">
	<p class="location">
		<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
	</p>
</div>
<html:form action="/xyfd_yytj">
	<input type="hidden" id="cxmb" value="yyqx"/>
	<%@ include file="/comm/hiddenValue.jsp"%>
	<div class="toolbox">
		<!-- ��ť -->
		<div class="buttonbox">
			<ul>
				<li id="li_qxtj" >
					<a href="javascript:void(0);" onclick="grqvqk();return false;"
					   title="ѡ����Ҫ�鿴�ĸ�����¼������ð�ť���Դ򿪲鿴ҳ�档"
					   class="btn_ck">�鿴ȡ����¼</a>
				</li>

				<li>
					<a href="javascript:void(0);" onclick="ckfd();return false;" class="btn_dc">����</a>
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
		<span>���¼����б�&nbsp;&nbsp; </span>
	</h3>
	<div class="con_overlfow">
		<table id="dataTable" ></table>
		<div id="pager"></div>
	</div>
</div>
</body>
</html>
