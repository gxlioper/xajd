<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="xsgzgl/xyfd/jzfdwh/jljgtj/js/jljgtj.js"></script>
	<script type="text/javascript">
        var gridSetting = {
            caption : "�յ�ԤԼ�б�",
            pager : "pager",
            url : "xyfd_jljgtj.do?method=jljgtjList&type=query",
            colList : [
                { label : 'yyh', name : 'yyh', index : 'yyh',key : true,hidden : true },
                { label : '����ʱ��', name : 'fdsj', index : 'fdsj',width:'15%'},
                { label : '�����ص�', name : 'fddd', index : 'fddd', width : '20%' },
                { label : '������Ա', name : 'fdjsxm', index : 'fdjsxm', width : '15%' },
                { label : '������Ա����', name : 'fdjslx', index : 'fdjslx', width : '5%' },
                { label : '�γ�����', name : 'kcmc', index : 'kcmc', width : '10%' },
                { label : 'ѧ������', name : 'xm', index : 'xm', width : '15%' },
                { label : 'ѧ���绰', name : 'lxdh', index : 'lxdh', hidden : true},
                { label : 'ѧ���绰', name : 'sjhm', index : 'sjhm', hidden : true},
                { label : 'ѧ���绰', name : '', index : '', width : '10%',formatter:function (cellValue, rowObject) {
					if(rowObject['lxdh']!=null&&rowObject['lxdh']!=''){
					    return rowObject['lxdh'];
					}else {
                        return rowObject['sjhm'];
					}
                }},
                { label : '�Ƿ���', name : 'sfjj', index : 'sfjj', width : '5%'},
                { label : '����', name : 'pf', index : 'pf', width : '5%'}
            ],

            radioselect:false
        }

        var gridSetting2 = {
            caption : "�յ�ԤԼ�б�",
            pager : "pager",
            url : "xyfd_jljgtj.do?method=jljgtjList&type=query",
            colList : [
                { label : 'zxid', name : 'zxid', index : 'zxid',key : true,hidden : true },
                { label : '����ʱ��', name : 'fdrq', index : 'fdrq',width:'15%'},
                { label : '�����ص�', name : 'fddd', index : 'fddd', width : '20%' },
                { label : '������Ա', name : 'fdjsxm', index : 'fdjsxm', width : '15%' },
                { label : '������Ա����', name : 'fdjslx', index : 'fdjslx', width : '5%' },
                { label : 'ѧ������', name : 'xm', index : 'xm', width : '15%' },
                { label : 'ѧ���绰', name : 'lxdh', index : 'lxdh', hidden : true},
                { label : 'ѧ���绰', name : 'sjhm', index : 'sjhm', hidden : true},
                { label : 'ѧ���绰', name : '', index : '', width : '10%',formatter:function (cellValue, rowObject) {
                    if(rowObject['lxdh']!=null&&rowObject['lxdh']!=''){
                        return rowObject['lxdh'];
                    }else {
                        return rowObject['sjhm'];
                    }
                }},
                { label : '��ѯԭ��', name : 'zxyymc', index : 'zxyymc', width : '5%'},
                { label : '�Ƿ���', name : 'sfjj', index : 'sfjj', width : '5%'},
                { label : '����', name : 'pf', index : 'pf', width : '5%'}
            ],

            radioselect:false
        }

        jQuery(function(){
            var map = getSuperSearch();
            map["cxmb"]="fd";
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
	<input type="hidden" id="cxmb" value="fd"/>
	<%@ include file="/comm/hiddenValue.jsp"%>
	<div class="toolbox">
		<!-- ��ť -->
		<div class="buttonbox">
			<ul>
				<li id="li_ck_fd">
					<a href="javascript:void(0);" onclick="ckfd();return false;"
					   title="ѡ����Ҫ�鿴�ĸ�����¼������ð�ť���Դ򿪲鿴ҳ�档"
					   class="btn_sh">�鿴����</a>
				</li>
				<li id="li_ck_zx" style="display: none">
					<a href="javascript:void(0);" onclick="ckzx();return false;"
					   title="ѡ����Ҫ�鿴��ѧҵ��רҵ��ѯ��¼������ð�ť���Դ򿪲鿴ҳ�档"
					   class="btn_ck">�鿴����</a>
				</li>
				<logic:equal name="writeAble" value="yes">
					<li id="li_dr_fd">
						<a href="javascript:void(0);" onclick="fddr();return false;"
						   class="btn_dr">����</a>
					</li>
					<li id="li_dr_zx" style="display: none">
						<a href="javascript:void(0);" onclick="zxdr();return false;"
						   class="btn_dr">����</a>
					</li>
					<li id="li_dc_fd">
						<a href="javascript:void(0);" onclick="fddc();return false;"
						   class="btn_dc">����</a>
					</li>
					<li id="li_dc_zx" style="display: none">
						<a href="javascript:void(0);" onclick="zxdc();return false;"
						   class="btn_dc">����</a>
					</li>
				</logic:equal>

			</ul>
		</div>
		<!-- �������� -->
		<%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/superSearchAreaforFdyy.jsp"%>
		<!-- �������� end-->
		<div class="comp_title" id="comp_title">
			<ul style="width:90%">
				<li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'fd');"><span>�γ̸�����¼</span></a></li>
				<li><a href="javascript:void(0);" onclick="selectTab(this,'zx');"><span>ѧҵ��רҵ��ѯ��¼</span></a></li>
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
