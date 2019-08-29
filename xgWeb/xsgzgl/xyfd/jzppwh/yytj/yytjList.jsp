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
            caption : "ԤԼ��¼�б�",
            pager : "pager",
            url : "xyfd_yytj.do?method=yytjList&type=query",
            colList : [
                { label : 'yyid', name : 'yyid', index : 'yyid',key : true,hidden : true },
                { label : 'ԤԼ��', name : 'yyh', index : 'yyh',width:'12%'},
                { label : 'ԤԼѧ��', name : 'xh', index : 'xh',hidden:true},
                { label : 'ԤԼѧ��', name : 'xm', index : 'xm',width:'10%'},
                { label : 'ԤԼ��', name : 'yyr', index : 'yyr',width:'10%'},
                { label : '����ʱ��', name : 'fdsj', index : 'fdsj',width:'10%'},
                { label : '�γ�����', name : 'kcmc', index : 'kcmc', width : '10%' },
                { label : '������ʦ', name : 'fdjsxm', index : 'fdjsxm', width : '10%' },
                { label : '�����ص�', name : 'fddd', index : 'fddd', width : '15%' },
                { label : '���״̬', name : 'shztmc', index : 'shztmc', width : '10%' },
                { label : '���״̬', name : 'zt', index : 'zt', hidden : true}
            ],
            radioselect:false
        }

        var gridSetting2 = {
            caption : "������ʦ�����б�",
            pager : "pager",
            url : "xyfd_yytj.do?method=yytjList&type=query",
            colList : [
                { label : 'djh', name : 'djh', index : 'djh',key : true,hidden : true },
                { label : '�ǼǺ�', name : 'djh', index : 'djh',width:'12%'},
                { label : 'ְ����', name : 'zgh', index : 'zgh',width:'12%'},
                { label : '������Ա����', name : 'xm', index : 'xm',width:'12%'},
                { label : '������Ա����', name : 'fdjslx', index : 'fdjslx',width:'10%'},
                { label : 'ƽ������', name : 'pjpf', index : 'pjpf',width:'10%'},
                { label : '�ۼƸ����˴�', name : 'ljrc', index : 'ljrc',width:'10%'}
            ],
            radioselect:false
        }

        var gridSetting3 = {
            caption : "ԤԼȡ��ͳ���б�",
            pager : "pager",
            url : "xyfd_yytj.do?method=yytjList&type=query",
            colList : [
                { label : 'djh', name : 'djh', index : 'djh',key : true,hidden : true },
                { label : '�ǼǺ�', name : 'djh', index : 'djh',width:'12%'},
                { label : 'ѧ��', name : 'zgh', index : 'zgh',width:'12%'},
                { label : '������Ա����', name : 'xm', index : 'xm',width:'12%'},
                { label : '������Ա����', name : 'fdjslx', index : 'fdjslx',width:'10%'},
                { label : '�ۼ�ȡ������', name : 'qxcs', index : 'qxcs',width:'10%'}
            ],
            radioselect:false
        }

        jQuery(function(){
            var map = getSuperSearch();
            map["cxmb"]="yyjl";
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
	<input type="hidden" id="cxmb" value="yyjl"/>
	<%@ include file="/comm/hiddenValue.jsp"%>
	<div class="toolbox">
		<div class="comp_title" id="comp_title">
			<ul style="width:90%">
				<li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'yyjl');"><span>ԤԼ��¼</span></a></li>
				<li><a href="javascript:void(0);" onclick="selectTab(this,'jspj');"><span>ԤԼ����ͳ��</span></a></li>
				<li><a href="javascript:void(0);" onclick="selectTab(this,'yyqx');"><span>ԤԼȡ��ͳ��</span></a></li>
			</ul>
		</div>
		<!-- ��ť -->
		<div class="buttonbox">
			<ul>
				<li id="li_fdjl">
					<a href="javascript:void(0);" onclick="qrfd();return false;"
					   title="ѡ����Ҫ��Ӹ�����¼�ļ�¼������ð�ť���Դ򿪼�¼ҳ�档"
					   class="btn_sh">��Ӹ�����¼</a>
				</li>
				<li id="li_pjtj" style="display: none">
					<a href="javascript:void(0);" onclick="grxxpj();return false;"
					   title="ѡ����Ҫ�鿴�ĸ�����¼������ð�ť���Դ򿪲鿴ҳ�档"
					   class="btn_ck">�鿴������¼</a>
				</li>
				<li id="li_qxtj" style="display: none">
					<a href="javascript:void(0);" onclick="grqvqk();return false;"
					   title="ѡ����Ҫ�鿴�ĸ�����¼������ð�ť���Դ򿪲鿴ҳ�档"
					   class="btn_ck">�鿴ȡ����¼</a>
				</li>
				<logic:equal name="canWrite" value="true">
					<li>
						<a href="javascript:void(0);" onclick="ckfd();return false;" class="btn_dr">����</a>
					</li>
				</logic:equal>
				<li>
					<a href="javascript:void(0);" onclick="ckfd();return false;" class="btn_dc">����</a>
				</li>
			</ul>
		</div>
		<!-- �������� -->
		<%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/superSearchAreaforFdyy.jsp"%>
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
