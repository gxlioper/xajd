<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/cxcy/bzsbwh/bzsbwhsh/js/bzsbwhsh.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption:"���¼����б�",
            pager:"pager",
            url:"cxcy_bzsbwhsh.do?method=getList&type=query",
            colList : [
                { label : 'sqid', name : 'sqid', index : 'sqid',key : true, hidden : true },
                { label : 'splc', name : 'splc', index : 'splc',hidden : true },
                { label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter:xhLink },
                { label : '����', name : 'xm', index : 'xm', width : '8%' },
                { label : '��Ŀ����', name : 'xmmc', index : 'xmmc', width : '14%' },
                { label : '������Ԫ��', name : 'bzje', index : 'bzje', width : '9%' },
                { label : 'ѧ��', name : 'xn', index : 'xn', width : '9%' },
                { label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '7%' },
                { label : '�������', name : 'xymc', index : 'xymc', width : '12%' },
                { label : '���', name : 'tbrmc', index : 'tbrmc', width : '8%' },
                { label : '��¼ʱ��', name : 'sqsj', index : 'sqsj', width : '15%' },
                { label : '���״̬', name : 'shztmc', index : 'shztmc', width : '18%' },
                { label : '���״̬', name : 'shzt', index : 'shzt', hidden : true},
                {label:'���Id',name:'shid', index: 'shid',hidden:true},
                {label:'��λId',name:'gwid', index: 'gwid',hidden:true}
            ],
            params:{shzt:"dsh"},
            radioselect:false
        };

        var gridSetting2 = {
            caption:"���¼����б�",
            pager:"pager",
            url:"cxcy_bzsbwhsh.do?method=getList&type=query",
            colList : [
                { label : 'sqid', name : 'sqid', index : 'sqid',key : true, hidden : true },
                { label : 'splc', name : 'splc', index : 'splc',hidden : true },
                { label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter:xhLink },
                { label : '����', name : 'xm', index : 'xm', width : '8%' },
                { label : '��Ŀ����', name : 'xmmc', index : 'xmmc', width : '14%' },
                { label : '������Ԫ��', name : 'bzje', index : 'bzje', width : '9%' },
                { label : 'ѧ��', name : 'xn', index : 'xn', width : '9%' },
                { label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '7%' },
                { label : '�������', name : 'xymc', index : 'xymc', width : '12%' },
                { label : '���', name : 'tbrmc', index : 'tbrmc', width : '8%' },
                { label : '��¼ʱ��', name : 'sqsj', index : 'sqsj', width : '15%' },
                { label : '���״̬', name : 'shztmc', index : 'shztmc', width : '18%' },
                { label : '���״̬', name : 'shzt', index : 'shzt', hidden : true},
                {label:'���Id',name:'shid', index: 'shid',hidden:true},
                {label:'��λId',name:'gwid', index: 'gwid',hidden:true}
            ],
            params:{shzt:"ysh"},
            radioselect:true
        };

        jQuery(function(){
            var map = getSuperSearch();
            map["shzt"]="dsh";
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            var shzt = jQuery("#shzt").val();
            if (shzt != ""){
                map["shzt"] = shzt;
            }
            jQuery("#dataTable").reloadGrid(map);
        }
        function selectTab(obj,shzt){
            jQuery("#shzt").val(shzt);
            if (shzt == "dsh"){
                jQuery("#li_sh").css("display","");
                jQuery("#li_qx").css("display","none");
                jQuery("#dataTable").initGrid(gridSetting);
            } else {
                jQuery("#li_sh").css("display","none");
                jQuery("#li_qx").css("display","");
                jQuery("#dataTable").initGrid(gridSetting2);
            }
            jQuery(".ha").removeClass("ha");
            jQuery(obj).parent().addClass("ha");
            searchRs();
        }
        function xhLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='view(\""
                + rowObject["sqid"]+"\");'>" + cellValue
                + "</a>";
        }
    </script>
</head>

<body>
    <div class="tab_cur">
        <p class="location">
            <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
        </p>
    </div>
    <html:form action="/cxcy_bzsbwhsh">
        <input type="hidden" id="shzt" value="dsh"/>
        <%@ include file="/comm/hiddenValue.jsp"%>
        <div class="toolbox">
            <!-- ��ť -->
            <div class="buttonbox">
                <ul>
                    <logic:equal name="writeAble" value="yes">
                        <li id="li_sh">
                            <a href="javascript:void(0);" onclick="sh();return false;"
                               title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
                               class="btn_sh">���</a>
                        </li>
                        <li id="li_qx" style="display: none;">
                            <a href="javascript:void(0);" onclick="cancelSh();return false;"
                               title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
                               class="btn_qxsh">����</a>
                        </li>
                    </logic:equal>
                    <li>
                        <a href="javascript:void(0);" onclick="lcgz();return false;"
                           title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
                           class="btn_cs">���̸���</a>
                    </li>
                    <li>
                        <a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
                    </li>
                </ul>
            </div>
            <!-- �������� -->
            <%@ include file="/comm/search/superSearchArea.jsp"%>
            <!-- �������� end-->
            <div class="comp_title" id="comp_title">
                <ul style="width:90%">
                    <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
                    <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
                </ul>
            </div>
        </div>
    </html:form>
    <div class="main_box">
        <h3 class=datetitle_01>
            <span>����б�&nbsp;&nbsp; </span>
        </h3>
        <div class="con_overlfow">
            <table id="dataTable" ></table>
            <div id="pager"></div>
        </div>
    </div>
</body>
</html>
