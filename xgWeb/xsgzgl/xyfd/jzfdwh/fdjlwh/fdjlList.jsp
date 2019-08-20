<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="xsgzgl/xyfd/jzfdwh/fdjlwh/js/fdjl.js"></script>
	<script type="text/javascript">
        var gridSetting = {
            caption : "�յ�ԤԼ�б�",
            pager : "pager",
            url : "xyfd_fdjl.do?method=fdjlList&type=query",
            colList : [
                { label : 'yyid', name : 'yyid', index : 'yyid',key : true,hidden : true },
                { label : 'ԤԼ��', name : 'yyh', index : 'yyh',width:'12%'},
                { label : 'ԤԼѧ��', name : 'xh', index : 'xh',hidden:true},
                { label : 'ԤԼѧ��', name : 'xm', index : 'xm',width:'10%'},
                { label : 'ԤԼ��', name : 'yyr', index : 'yyr',width:'10%'},
                { label : '����ʱ��', name : 'fdsj', index : 'fdsj',width:'10%'},
                { label : '�γ�����', name : 'kcmc', index : 'kcmc', width : '10%' },
                { label : '�����ص�', name : 'fdsdd', index : 'fdsdd', width : '15%' },
                { label : '���״̬', name : 'shztmc', index : 'shztmc', width : '10%' },
                { label : '���״̬', name : 'zt', index : 'zt', hidden : true}
            ],

            radioselect:false
        }

        var gridSetting2 = {
            caption : "�յ�ԤԼ�б�",
            pager : "pager",
            url : "xyfd_fdjl.do?method=fdjlList&type=query",
            colList : [
                { label : 'yyid', name : 'yyid', index : 'yyid',key : true,hidden : true },
                { label : 'ԤԼ��', name : 'yyh', index : 'yyh',width:'12%'},
                { label : 'ԤԼѧ��', name : 'xh', index : 'xh',hidden:true},
                { label : 'ԤԼѧ��', name : 'xm', index : 'xm',width:'10%'},
                { label : 'ԤԼ��', name : 'yyr', index : 'yyr',width:'10%'},
                { label : '����ʱ��', name : 'fdsj', index : 'fdsj',width:'10%'},
                { label : '�γ�����', name : 'kcmc', index : 'kcmc', width : '10%' },
                { label : '�����ص�', name : 'fdsdd', index : 'fdsdd', width : '15%' },
                { label : '���״̬', name : 'shztmc', index : 'shztmc', width : '10%' },
                { label : '���״̬', name : 'zt', index : 'zt', hidden : true}
            ],

            radioselect:false
        }

        jQuery(function(){
            var map = getSuperSearch();
            map["yyzt"]="dfd";
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
<html:form action="/xyfd_fdjl">
	<input type="hidden" id="yyzt" value="dfd"/>
	<%@ include file="/comm/hiddenValue.jsp"%>
	<div class="toolbox">
		<!-- ��ť -->
		<div class="buttonbox">
			<ul>
				<logic:equal name="writeAble" value="yes">
					<logic:equal name="isJsOrPb" value="true">
						<li id="li_tj">
							<a href="javascript:void(0);" onclick="qrfd();return false;"
							   title="ѡ����Ҫ��Ӹ�����¼�ļ�¼������ð�ť���Դ򿪼�¼ҳ�档"
							   class="btn_sh">��Ӹ�����¼</a>
						</li>
						<li id="li_ck" style="display: none">
							<a href="javascript:void(0);" onclick="ckfd();return false;"
							   title="ѡ����Ҫ�鿴�ĸ�����¼������ð�ť���Դ򿪲鿴ҳ�档"
							   class="btn_ck">�鿴������¼</a>
						</li>
					</logic:equal>
				</logic:equal>

			</ul>
		</div>
		<!-- �������� -->
		<%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/superSearchAreaforFdyy.jsp"%>
		<!-- �������� end-->
		<div class="comp_title" id="comp_title">
			<ul style="width:90%">
				<li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dfd');"><span>������</span></a></li>
				<li><a href="javascript:void(0);" onclick="selectTab(this,'yfd');"><span>�Ѹ���</span></a></li>
			</ul>
		</div>
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
