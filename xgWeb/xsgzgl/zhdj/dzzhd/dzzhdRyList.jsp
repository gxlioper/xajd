<!--����֯���Ա����-->
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/dzzhd/js/dzzhdRyList.js"></script>
</head>
<body>
<div class="tab_cur">
    <p class="location">
        <em>��ǰλ�ã�</em><a>����֯�������-����֯���Ϣ-��Ա����</a>
    </p>
</div>
<html:form action="/zhdj_dzzhd.do?method=getHdPageList">
    <input type="hidden" id="hdid" value="${hdid}"/>
    <input type="hidden" id="joinStatus" value="${joinStatus}"/>
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="zhdj_dzzhd_hdxx.do" class="btn_fh">����</a>
                </li>
                <li id="li_sz">
                    <a href="javascript:void(0);" onclick="addRy();return false;" class="btn_sz">���</a>
                </li>
                <li id="li_sc" >
                    <a href="javascript:void(0);" onclick="removeRy();return false;" class="btn_sc">�Ƴ�</a>
                </li>
                <li id="li_xd">
                    <a href="javascript:void(0);" onclick="viewHdxd();return false;" class="btn_tj">�ĵ����</a>
                </li>
            </ul>
        </div>
        <!--���ѡ��-->
        <div class="comp_title" id="comp_title">
            <ul style="width:90%">
                <li id="kjr" class="ha"><a href="javascript:void(0);" onclick="selectTab('0');return false;"><span>�����</span></a></li>
                <li id="yjr"><a href="javascript:void(0);" onclick="selectTab('1');return false;"><span>�����</span></a></li>
            </ul>
        </div>
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