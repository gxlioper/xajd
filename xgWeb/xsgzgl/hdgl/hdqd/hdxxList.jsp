<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption:"��б�",
                pager:"pager",
                url:"hdgl_hdgl_hdqd_wh.do?method=getHdxxList&type=query",
                colList:[
                    {label:'�id',name:'hdid', index: 'hdid',key:true,hidden:true},
                    {label:'�����',name:'hdmc', index: 'hdmc',width:'18%'},
                    {label:'������',name:'fbf', index: 'fbf',width:'18%'},
                    {label:'�����',name:'hdlxmc', index: 'hdlcmc',width:'18%'},
                    {label:'��ص�',name:'hddd', index: 'hddd',width:'10%'},
                    {label:'���ʼʱ��',name:'hdkssj', index: 'hdkssj',width:'10%'},
                    {label:'�����ʱ��',name:'hdjssj', index: 'hdjssj',width:'18%'},
                    {label:'�״̬',name:'hdjxzt', index: 'hdjxzt',width:'18%'},
                    {label:'��������',name:'bmlx', index: 'bmlx',width:'18%',hidden:true}
                ],
                sortname: "fbsj",
                sortorder: "desc"
            }
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function select(){
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length != 1) {
                showAlertDivLayer("��ѡ��һ����¼��");
                return false;
            }
            if(rows[0]["hdjxzt"] == "���¼�" ){
                showAlertDivLayer("��ѡ����Ч���");
                return false;
            }
			var gotoPath = jQuery("#gotoPath").val();
			if (gotoPath.split("?").length > 1){
				gotoPath = gotoPath+"&hdid="+rows[0].hdid;
			} else {
				gotoPath = gotoPath+"?hdid="+rows[0].hdid;
			}
            var api = frameElement.api;
			
			if (api){
				if (api.get('childDialog')){
					api.reload(api.get('parentDialog') ,gotoPath);
				} else {
					var W = api.opener;
					W.location=gotoPath;			
				}
			} else if (parent.window){
				parent.window.document.location=gotoPath;						
			}
            // var W = api.get('parentDialog');
//            var rowData = JSON.stringify();
            // W.selectCallBack(rows[0]);
            api.close();
        }
    </script>
</head>
<body>
	
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<input type="hidden" value="${gotoPath}" id="gotoPath"/>
<html:form action="/hdgl_hdgl_tj">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li><a href="#" class="btn_sz" onclick="select();return false;">ѡ��</a></li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>�ǩ��&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
