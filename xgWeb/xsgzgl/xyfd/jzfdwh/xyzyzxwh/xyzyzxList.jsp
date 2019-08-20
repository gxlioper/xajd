<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="xsgzgl/xyfd/jzfdwh/xyzyzxwh/js/xyzyzx.js"></script>
	<script type="text/javascript">
        var gridSetting = {
            caption : "�յ�ԤԼ�б�",
            pager : "pager",
            url : "xyfd_xyzyzxjl.do?method=xyzyzxList&type=query",
            colList : [
                { label : 'zxid', name : 'zxid', index : 'zxid',key : true ,hidden : true},
                { label : 'ѧ��ѧ��', name : 'xh', index : 'xh',hidden:true},
                { label : 'ѧ������', name : 'xm', index : 'xm',width:'10%'},
                { label : '���ڰ༶', name : 'bjmc', index : 'bjmc',width:'10%'},
                { label : '��ѯԭ��', name : 'zxyymc', index : 'zxyymc',width:'5%'},
                { label : 'ʱ��', name : 'fdrq', index : 'fdrq',width:'10%'},
                { label : '������Ա', name : 'fdjs', index : 'fdjs',width:'10%',hidden : true},
                { label : '������Ա', name : 'yhm', index : 'yhm',width:'10%',hidden : true},
                { label : '��������', name : 'fdrq', index : 'fdrq',width:'10%',hidden : true},
                { label : '����ʱ��', name : 'fdsj', index : 'fdsj',width:'10%',hidden : true},
                { label : '�����ص�', name : 'fddd', index : 'fddd', width : '15%' },
                { label : '����ٴ�', name : 'jtjc', index : 'jtjc', width : '25%',formatter:function (cellValue, rowObject) {
                    return rowObject['jtjc'].substring(0,20)+'......'
                } },
                { label : '��ѯ״̬', name : 'zxzt', index : 'zxzt', width : '15%',hidden : true },
                { label : 'lrr', name : 'lrr', index : 'lrr', width : '30%',hidden : true },
                { label : 'lrsj', name : 'lrsj', index : 'lrsj', width : '30%',hidden : true }
            ],
            sortname: "lrsj",
            sortorder: "desc",
            radioselect:false
        }

        jQuery(function(){
            var map = getSuperSearch();
//            map["yyzt"]="dfd";
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
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" id="userName" name="userName" value="${userName}"/>
	<div class="toolbox">
		<!-- ��ť -->
		<div class="buttonbox">
			<ul>
				<logic:equal name="writeAble" value="yes">
					<logic:equal name="isJsOrPb" value="true">
						<li>
							<a href="javascript:void(0);" onclick="zjjl();return false;" class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xgjl();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
						</li>
					</logic:equal>
				</logic:equal>
				<li>
					<a href="javascript:void(0);" onclick="ckjl();return false;" class="btn_ck" >�鿴</a>
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
