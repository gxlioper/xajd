<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>

</head>
<body>
<html:form method="post" styleId="hdxxForm" action="/hdgl_hdjg"
           enctype="multipart/form-data">
    <div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="9">
                    <span>活动详情列表</span>
                </th>
            </tr>
            </thead>
            <table class="formlist">
                <thead>
                <tr>
                    <th>
                        <div align="center" >学号</div>
                    </th>
                    <th>
                        <div align="center" >姓名</div>
                    </th>
                    <th>
                        <div align="center" >性别</div>
                    </th>
                    <th>
                        <div align="center" >学院</div>
                    </th>
                    <th>
                        <div align="center" >专业</div>
                    </th>
                    <th>
                        <div align="center" >班级</div>
                    </th>
                    <th>
                        <div align="center" >活动名称</div>
                    </th>
                    <th>
                        <div align="center" >获得奖项</div>
                    </th>
                    <th>
                        <div align="center" >学分</div>
                    </th>
                </tr>
                </thead>
                <tbody>
                <logic:iterate id="s" name="list">
                    <tr class="alt">
                        <td align="center">${s.xh}</td>
                        <td align="center">${s.xm}</td>
                        <td align="center">${s.xb}</td>
                        <td align="center">${s.xymc}</td>
                        <td align="center">${s.zymc}</td>
                        <td align="center">${s.bjmc}</td>
                        <td align="center">${s.hdmc}</td>
                        <td align="center">${s.jxmc}</td>
                        <td align="center">${s.xf}</td>
                    </tr>
                </logic:iterate>
                </tbody>

            </table>
        </table>
    </div>
    <div style="height:50px;"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="btn">
                        </button>
                        <button type="button" onclick="iFClose();" id="buttonClose">
                            关 闭
                        </button>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</html:form>
</body>
</html>
