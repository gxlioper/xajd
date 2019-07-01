<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type='text/javascript' src="js/uicomm.js"></script>
	<script type='text/javascript' src="js/String.js"></script>
	<script type='text/javascript' src="js/xgutil.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script>
        var gridSetting = {
            caption:"��λ����ά���б�",
            pager:"pager",
            url:"qgzx_cxtj.do?method=gwxxCx&type=query",
            colList:[
                {label:'key',name:'gwdm', index: 'gwdm',key:true,hidden:true},
                {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
                {label:'��λ����',name:'gwmc', index: 'gwmc',width:'15%'},
                {label:'���˵�λ',name:'yrdwmc', index: 'yrdwmc',width:'15%'},
                {label:'��������',name:'gwxzdm', index: 'gwxzdm',width:'3%',formatter:function(cell,rowObject){
                    if(cell == '0'){
                        return "��ʱ";
                    } else if(cell == '1'){
                        return "��ʽ";
                    } else {
                        return cell;
                    }
                }},
                {label:'��λ����',name:'gwlx', index: 'gwlx',width:'3%',formatter:function(cell,rowObject){
                    if(cell == '0'){
                        return "��ʱ";
                    } else if(cell == '1'){
                        return "����";
                    } else {
                        return cell;
                    }
                }},
                {label:'��λ���',name:'dwlb', index: 'dwlb',formatter:function(cell,rowObject){
                    if(cell == "01"){
                        return "У�ڵ�λ";
                    }
                    return "У����ҵ";
                }},
                {label:'��Ƹ����',name:'xqrs', index: 'xqrs',width:'7%'},
                {label:'�ڸ�����',name:'zgrs', index: 'zgrs',width:'7%'},
                {label:'��ְ����',name:'lzrs', index: 'lzrs',width:'7%'}
            ],
            sortname: "fbsj",
            sortorder: "desc"
        };
        //��ʼ��
        jQuery(document).ready(function(){
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }

        function yrdwwhExportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport("qgzx_cxtj_gwxx.do", yrdwwhExportData);
        }

        // ��������
        function yrdwwhExportData() {
            //setSearchTj();//���ø߼���ѯ����
            var url = "qgzx_cxtj_ajax.do?method=gwxxcxExportData&dcclbh="+"qgzx_cxtj_gwxx.do";//dcclbh,�������ܱ��
            //url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }

        function ck(){
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length != 1){
                showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
            } else {
                showDialog("�鿴��ϸ",700,500,"qgzx_cxtj.do?method=gwxxCk&gwdm="+ids[0]);
            }
        }

	</script>
</head>
<body>
<div class="tab_cur" >
	<p class="location">
		<em>���ĵ�ǰλ��:</em><a>${title }</a>
	</p>
	<p class="help">
		<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
	</p>
</div>

<html:form action="/qgzx_gwglnew" method="post">
	<!-- ������ -->
	<%@ include file="/comm/hiddenValue.jsp"%>
	<!-- ������ -->
	<div class="toolbox" id="dgncz">
		<!-- ��ť -->
		<div class="buttonbox">
			<ul>
				<%--<logic:equal value="yes" name="writeAble">--%>
					<li><a href="#" onclick="ck();return false;" class="btn_ck">�鿴��ϸ</a></li>
				<%--</logic:equal>--%>

				<li><a href="#" onclick="yrdwwhExportConfig();return false;" class="btn_dc">����</a></li>
			</ul>
		</div>
		<!-- �������� -->
		<%@ include file="/comm/search/superSearchArea.jsp"%>
	</div>

</html:form>
<div class="formbox">
	<!--����start-->
	<h3 class="datetitle_01">
		<span> ��λ��Ϣ�б� </span>
	</h3>

	<table id="dataTable" ></table>
	<div id="pager"></div>

</div>
</body>
</html>
