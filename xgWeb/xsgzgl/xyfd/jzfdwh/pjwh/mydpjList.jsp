<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="xsgzgl/xyfd/jzfdwh/pjwh/js/mydpj.js"></script>
	<script type="text/javascript">
        var gridSetting = {
            caption : "�յ�ԤԼ�б�",
            pager : "pager",
            url : "xyfd_mydpj.do?method=mydpjList&type=query",
            colList : [
                { label : 'jlbh', name : 'jlbh', index : 'jlbh',key : true,hidden : true },
                { label : 'ԤԼѧ��', name : 'xh', index : 'xh',hidden:true},
                { label : 'ԤԼѧ��', name : 'xm', index : 'xm',width:'10%',hidden:true},
                { label : '����', name : 'lxmc', index : 'lxmc',width:'10%'},
                { label : '����ʱ��', name : 'fdsj', index : 'fdsj',width:'10%'},
                { label : '�����ص�', name : 'fddd', index : 'fddd', width : '15%' },
                { label : '������Ա����', name : 'fdjsxm', index : 'fdjsxm', width : '15%' },
                { label : '������Ա����', name : 'fdjslx', index : 'fdjslx', width : '10%' },
                { label : '�γ�����', name : 'kcmc', index : 'kcmc', width : '10%' },
                { label : 'ԤԼ��', name : 'yyr', index : 'yyr',width:'5%'},
                { label : '���״̬', name : 'shztmc', index : 'shztmc', width : '10%',hidden:true },
                { label : '���״̬', name : 'zt', index : 'zt', hidden : true}
            ],
            sortname: "fdsj",
            sortorder: "desc",
            radioselect:false
        }

        var gridSetting2 = {
            caption : "�յ�ԤԼ�б�",
            pager : "pager",
            url : "xyfd_mydpj.do?method=mydpjList&type=query",
            colList : [
                { label : 'jlbh', name : 'jlbh', index : 'jlbh',key : true,hidden : true },
                { label : 'ԤԼѧ��', name : 'xh', index : 'xh',hidden:true},
                { label : 'ԤԼѧ��', name : 'xm', index : 'xm',width:'10%',hidden:true},
                { label : '����', name : 'lxmc', index : 'lxmc',width:'10%'},
                { label : '����ʱ��', name : 'fdsj', index : 'fdsj',width:'10%'},
                { label : '�����ص�', name : 'fddd', index : 'fddd', width : '15%' },
                { label : '������Ա����', name : 'fdjsxm', index : 'fdjsxm', width : '15%' },
                { label : '������Ա����', name : 'fdjslx', index : 'fdjslx', width : '10%' },
                { label : '�γ�����', name : 'kcmc', index : 'kcmc', width : '10%' },
                { label : 'ԤԼ��', name : 'yyr', index : 'yyr',width:'5%'},
                { label : '����', name : 'pf', index : 'pf',width:'5%'},
                { label : '���״̬', name : 'shztmc', index : 'shztmc', width : '10%',hidden:true },
                { label : '���״̬', name : 'zt', index : 'zt', hidden : true}
            ],
            sortname: "fdsj",
            sortorder: "desc",
            radioselect:false
        }

        jQuery(function(){
            var map = getSuperSearch();
            map["pjzt"]="dpj";
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
<html:form action="/xyfd_mydpj">
	<input type="hidden" id="pjzt" value="dpj"/>
	<%@ include file="/comm/hiddenValue.jsp"%>
	<div class="toolbox">
		<!-- ��ť -->
		<div class="buttonbox">
			<ul>
				<logic:equal name="writeAble" value="yes">
					<li id="li_tj">
						<a href="javascript:void(0);" onclick="txpj();return false;"
						   title="ѡ����Ҫ������۵ļ�¼������ð�ť���Դ�����ҳ�档"
						   class="btn_sh">��д����</a>
					</li>
				</logic:equal>
				<li id="li_ck" style="display: none">
					<a href="javascript:void(0);" onclick="ckpj();return false;"
					   title="ѡ����Ҫ�鿴�����ۣ�����ð�ť���Դ򿪲鿴ҳ�档"
					   class="btn_ck">�鿴����</a>
				</li>
			</ul>
		</div>
		<!-- �������� -->
		<%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/superSearchAreaforFdyy.jsp"%>
		<!-- �������� end-->
		<div class="comp_title" id="comp_title">
			<ul style="width:90%">
				<li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dpj');"><span>������</span></a></li>
				<li><a href="javascript:void(0);" onclick="selectTab(this,'ypj');"><span>������</span></a></li>
			</ul>
		</div>
	</div>
</html:form>
<div class="main_box">
	<h3 class=datetitle_01>
		<span>����������б�&nbsp;&nbsp; </span>
	</h3>
	<div class="con_overlfow">
		<table id="dataTable" ></table>
		<div id="pager"></div>
	</div>
</div>
</body>
</html>
