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
            caption:"",
            pager:"pager",
            url:"qgzx_cxtj.do?method=dwgzCx&type=query",
            colList:[
                {label:'���˵�λ',name:'yrdwmc', index: 'yrdwmc',width:'13%'},
                {label:'��λ���',name:'dwlb', index: 'dwlb',width:'7%',formatter:function(cell,row){
                    if(cell == "01"){
                        return "У�ڵ�λ";
					} else {
                        return "У����ҵ";
					}
				}},
                {label:'�о���������',name:'yjsrs', index: 'yjsrs',width:'7%'},
                {label:'������������',name:'bksrs', index: 'bksrs',width:'7%'},
                {label:'���',name:'nd', index: 'nd',width:'10%'},
                {label:'�·�',name:'yf', index: 'yf',width:'10%'},
                {label:'�ܹ�ʱ',name:'zgs', index: 'zgs',width:'10%'},
                {label:'�ϼ�',name:'ffje', index: 'ffje',width:'10%'}
            ],
            sortname: "",
            sortorder: "desc"
        };
        var gridSetting2 = {
            caption:"ѧ����λ�б�",
            pager:"pager",
            url:"qgzx_cxtj.do?method=dwgzCx&type=query",
            colList:[
                {label:'���˵�λ',name:'yrdwmc', index: 'yrdwmc',width:'13%'},
                {label:'��λ���',name:'dwlb', index: 'dwlb',width:'7%',formatter:function(cell,row){
                    if(cell == "01"){
                        return "У�ڵ�λ";
                    } else {
                        return "У����ҵ";
                    }
                }},
                {label:'�о���������',name:'yjsrs', index: 'yjsrs',width:'7%'},
                {label:'������������',name:'bksrs', index: 'bksrs',width:'7%'},
                {label:'���',name:'nd', index: 'nd',width:'10%'},
                {label:'�ܹ�ʱ',name:'zgs', index: 'zgs',width:'10%'},
                {label:'�ϼ�',name:'ffje', index: 'ffje',width:'10%'}
            ],
            sortname: "",
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

        function yrdwwhExportConfig(type) {
            //DCCLBH�������ܱ��,ִ�е�������
			if(type == "ygz"){
                customExport("qgzx_cxtj_dwgzCx", yrdwwhExportData);
			} else {
                customExport("qgzx_cxtj_dwgzCx_ngz", yrdwwhExportData2);
			}
        }

        // ��������
        function yrdwwhExportData() {
            //setSearchTj();//���ø߼���ѯ����
            var url = "qgzx_cxtj.do?method=dwgzCxExportData&dcclbh="+"qgzx_cxtj_dwgzCx&shzt=ygz";//dcclbh,�������ܱ��
            //url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function yrdwwhExportData2() {
            //setSearchTj();//���ø߼���ѯ����
            var url = "qgzx_cxtj.do?method=dwgzCxExportData&dcclbh=qgzx_cxtj_dwgzCx_ngz&shzt=ngz";//dcclbh,�������ܱ��
            //url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function selectTab(obj, shzt) {
            jQuery("#shzt").val(shzt);
            if (shzt == "ygz") {
                jQuery("#li_sh").css("display", "");
                jQuery("#li_qx").css("display", "none");
                var map = getSuperSearch();
                map["shzt"]="ygz";
                gridSetting["params"] = map;
                jQuery("#dataTable").initGrid(gridSetting);
            } else {
                jQuery("#li_sh").css("display", "none");
                jQuery("#li_qx").css("display", "");
                var map = getSuperSearch();
                map["shzt"]="ngz";
                gridSetting2["params"] = map;
                jQuery("#dataTable").initGrid(gridSetting2);
            }
            jQuery(".ha").removeClass("ha");
            jQuery(obj).parent().addClass("ha");
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
				<li id="li_sh"><a href="#" onclick="yrdwwhExportConfig('ygz');return false;" class="btn_dc">������λ�¹���</a></li>
				<li id="li_qx" style="display: none;">
					<a href="#" onclick="yrdwwhExportConfig('ngz');return false;" class="btn_dc">������λ�깤��</a>
				</li>
			</ul>
		</div>
		<!-- �������� -->
		<%@ include file="/comm/search/superSearchArea.jsp"%>
		<div class="comp_title" id="comp_title">
			<ul style="width:90%">
				<li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'ygz');"><span>��λ�¹���</span></a></li>
				<li><a href="javascript:void(0);" onclick="selectTab(this,'ngz');"><span>��λ�깤��</span></a></li>
			</ul>
		</div>
	</div>

</html:form>
<div class="main_box">
	<!--����start-->
	<h3 class="datetitle_01">
		<span> ��λ�����б� </span>
	</h3>

	<table id="dataTable" ></table>
	<div id="pager"></div>

</div>
</body>
</html>
