<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        var gridSetting;
        jQuery(function(){
            gridSetting = {
                caption:"",
                pager:"pager",
                url:"xyfd_fdjswh.do?method=selectTeacher&type=query",
                colList:[
                    {label:'ְ����',name:'zgh', index: 'zgh',width:'20%'},
                    {label:'����',name:'xm', index: 'xm',width:'10%'},
                    {label:'�Ա�',name:'xb', index: 'xb',width:'10%'},
                    {label:'��������',name:'bmmc', index: 'bmmc',width:'20%'},
                    {label:'��ϵ�绰',name:'lxdh', index: 'lxdh',width:'20%'},
                    {label:'����',name:'', index: '',width:'10%',formatter:function(cell,rowObject){
                        return "<label class='btn_01' onclick=\"select('"+rowObject["zgh"]+"','"+rowObject["xm"]+"','"
                            +rowObject["lxdh"]+"','"+rowObject["bgdd"]+"','"+rowObject["bgdh"]+"','"+rowObject["zc"]+"','"
                            +rowObject["xb"]+"','"+rowObject["bmmc"]+"','"+rowObject["dzyx"]+"');\">ѡ��</label>";
                    }},
                    {label:'�칫�ص�',name:'bgdd', index: 'bgdd',width:'20%',hidden:true},
                    {label:'ְ��',name:'zc', index: 'zc',width:'20%',hidden:true},
                    {label:'�칫�绰',name:'bgdh', index: 'bgdh',width:'20%',hidden:true},
                    {label:'��������',name:'dzyx', index: 'dzyx',width:'20%',hidden:true}
                ],
                sortname: "zgh",
                sortorder: "asc",
                radioselect:false
            }

            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function select(zgh,xm,lxdh,bgdd,bgdh,zc,xb,bmmc,dzyx){
            var api = frameElement.api;
            var parent = api.get('parentDialog');
            parent.jQuery("#zgh").val(zgh);
            parent.jQuery("#lxdh").val(lxdh == 'null' ? '' : lxdh);
            parent.jQuery("#dzyx").val(dzyx == 'null' ? '' : dzyx);
            parent.jQuery("#xm").val(xm);
            parent.jQuery("#zc").html(zc=='null'?'':zc);
            parent.jQuery("#xb").html(xb);
            parent.jQuery("#bmmc").html(bmmc);
            parent.jQuery("#zhaopian").attr('src','teaPic.jsp?zgh='+zgh);
            iFClose();
        }
    </script>
</head>

<body>
<html:form action="/qgzx_jcdmwh_ajax">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>��ѯ���&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
