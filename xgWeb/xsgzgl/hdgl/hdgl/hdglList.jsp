<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/hdgl/js/hdgl.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption:"��б�",
            pager:"pager",
            url:"hdgl_hdgl.do?method=hdglList&type=query",
            colList:[
                {label:'�id',name:'hdid', index: 'hdid',key:true,hidden:true},
                {label:'�����',name:'hdmc', index: 'hdmc',width:'18%',formatter:hdLink},
                {label:'������',name:'fbf', index: 'fbf',width:'18%'},
                {label:'�����',name:'hdlxmc', index: 'hdlcmc',width:'18%'},
                {label:'��������',name:'bmrs', index: 'bmrs',width:'10%'},
                {label:'����ʱ��',name:'fbsj', index: 'fbsj',width:'18%'},
                {label:'�״̬',name:'hdjxzt', index: 'hdjxzt',width:'18%'}
            ],
            sortname: "fbsj",
            sortorder: "desc"
        }

        function hdLink(cellValue,rowObject){
            var hdid = rowObject["hdid"];
            return "<a href='javascript:void(0);' onclick=\"hdShow('"+hdid+"')\" class='name'>"+cellValue+"</a>";
        }

        jQuery(function(){
            var map = getSuperSearch();
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
<html:form action="/hdgl_hdgl">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
        	<!-- ����֮ǰ�ķ��� -->
<%--            <ul>--%>
<%--                <li><a href="javascript:void(0);" onclick="zjtcysz()" class="btn_sz">ר���ų�Ա����</a></li>--%>
<%--            </ul>--%>
			<ul>
				<li><a href="javascript:void(0);" onclick="jdcysz()" class="btn_sz">�׶γ�Ա����</a></li>
			</ul>
        </div>
        <!-- �������� -->
        <logic:equal name="userType" value="stu">
            <%@ include file="/comm/search/superSearchAreaStu.jsp"%>
        </logic:equal>
        <logic:notEqual name="userType" value="stu">
            <%@ include file="/comm/search/superSearchArea.jsp"%>
        </logic:notEqual>
        <!-- �������� end-->
    </div>
</html:form>
<div class="formbox">
    <!--����start-->
    <h3 class="datetitle_01">
        <span> ��б� </span>
    </h3>

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
</body>
</html>
