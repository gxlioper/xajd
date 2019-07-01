<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript" src="xsgzgl/rcsw/xsgzqkbb/xsgzqkzbb/js/zbb.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "${gnmkmc}�б�",
                pager : "pager",
                url : "rcsw_xsgzqkbb_zbbgl.do?method=getZbbListData",
                colList : [
                    { label : 'key', name : 'id', index : 'id',key : true, hidden : true },
                    { label : 'ѧ��', name : 'xn', index : 'xn', width : '10%' },
                    { label : 'ѧ��', name : 'xqmc', index : 'xq', width : '10%' },
                    { label : '�ܴ�', name : 'zc', index : 'zc', width : '7%' },
                    { label : '�ܴ���ֹ����', name : 'zcksjsrq', index : 'zcksjsrq', width : '18%' },
                    { label : '������', name : 'bsrmc', index : 'bsr', width : '10%' },
                    { label : '����ʱ��', name : 'bssj', index : 'bssj', width : '15%' },
                    { label : '���͵�λ', name : 'bsdwmc', index : 'bsdw',width:'15%'},
                    { label : '��λ������', name : 'dwfzrmc', index : 'dwfzr',width:'15%'}
                ],
                sortname : "bssj",
                sortorder : "desc" }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })
		function printxxbsb(){
        	var check = jQuery("input[type=checkbox][name=grid_key]:checked");
			var ids = jQuery("#dataTable").getSeletIds();
			var len = ids.length;
			if(len == 0){
				showAlertDivLayer("�빴ѡ<font color='blue'>����һ����¼</font>");
				return false;
			}
			var url = "";
			if(check.length == 1){
				url = "rcsw_xsgzqkbb_zbbgl.do?method=getJsdj";
				url += "&id=" + ids;
			}else{
				var zghs = "";
				url = "rcsw_xsgzqkbb_zbbgl.do?method=getJsdjTy";
				url +=  "&value=" + ids;
			}
			
			window.open(url);
		}
    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>

<html:form action="/rcsw_xsgzqkbb_zbbgl">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
    <input type="hidden" id="zcListSize" value="${zcListSize}"/>

    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="writeAble" value="yes">
                    <li>
                        <a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="edit();return false;" class="btn_xg" >�޸�</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr" >����</a>
                    </li>
                </logic:equal>

                <li>
                    <a href="javascript:void(0);" onclick="view();return false;" class="btn_ck" >�鿴</a>
                </li>
                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
                
                <logic:equal name="xxdm" value="10364">
					<li>
						<a href="javascript:void(0);"
							onclick="printxxbsb();return false;" class="btn_dy">ѧ����Ϣ���ͱ�</a>
					</li>
				</logic:equal>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>${gnmkmc}�б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
