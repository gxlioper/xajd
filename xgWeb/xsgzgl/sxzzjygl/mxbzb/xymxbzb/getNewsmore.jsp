<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/mxbzb/xymxbzbgl/js/xymxbzbglList.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption: "�����б�",
            pager: "pager",
            url: "sxzzjy_xymxbzbgl.do?method=getNewsmore&type=query",
            colList: [
                {label: 'newsid', name: 'newsid', index: 'newsid',hidden:true,key:true},
                {label: '����',name:'newstitle', index: 'newstitle', width: '30%',formatter:titleLink},
                {label: '����ʱ��', name: 'fbsj', index: 'fbsj', width: '15%'},
                {label: '�Ƿ��ö�', name: 'sfzd', index: 'sfzd', width: '8%',hidden:true}
            ],
            sortname: "sfzd desc,fbsj",
            sortorder: "desc"
        };
        jQuery(function () {
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }


        function view(newsid) {
            var url="sxzzjy_xymxbzbgl.do?method=xymxbzbglView&newsid=" + newsid;
            document.forms[0].action = url;
            document.forms[0].target = "_blank";
            document.forms[0].submit();
            document.forms[0].target = "_self";
            //showDialog("�鿴", 700,550, "sxzzjy_xymxbzbgl.do?method=xymxbzbglView&newsid=" + newsid);
        }

        function titleLink(cellValue, rowObject) {
            var value = cellValue;
            if(cellValue.length > 18)
                value = value.substring(0,18)+"...";
            var zdHtml = "";
            if(rowObject["sfzd"] == "��")
                zdHtml += "<font color=\"red\" style=\"float:left;\">���ö���</font>";
            return "<a href='javascript:void(0);' class='name' title='"+cellValue+"' onclick='view(\""
                + rowObject["newsid"] + "\");'>" + value+zdHtml
                + "</a>";
        }
    </script>

</head>
<body>
<html:form action="/sxzzjy_xymxbzbgl" method="post">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
    <div class="tab_cur">
        <p class="location">
            <em>���ĵ�ǰλ�ã�</em><a>${title}</a>
        </p>
    </div>

    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <a href="sxzzjy_mxbzb_xymxbzb.do">
                    <button type="button" onclick="iFClose();">
                    ����
                    </button>
                </a>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- �������� end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>�����б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>