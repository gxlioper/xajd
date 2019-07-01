<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/xg_v4.ini"%>
    <%--<%@ include file="/syscommon/head.ini"%>--%>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xtwh/wdyy/js/zf_dialog_1.0.js"></script>
    <script type="text/javascript" src="xtwh/wdyy/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/bjwhhdzq/js/bjwhhdzq.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/mxbzb/xymxbzbgl/js/xymxbzbglList.js"></script>
<%--    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" media="all"/>--%>
    <script type="text/javascript">

    </script>

</head>

<body id="container" >
<html:form action="/bjwhhdzq_bjwh" method="post">
<!--新增选项卡模块-->
<div class="container" style="width: 100%;">
    <div class="row" style="width: 100%">
        <div class="col-md-7 col-sm-7 padding_r0" style="width: 80%;">
            <div class="panel panel-default index_list margin_t15">
                <ul id="tagslist01" class="nav-tabs nav panel-heading notice-tabs">
                    <input type="hidden" name="firstType" id="firstType"/>
                    <span class="pull-left" style="padding:0px;"><img src="images/notice_icon.png" style="height:40px;padding:10px;"></span>
                    <a  id="more" href="" class="pull-right" title="更多">more>></a>
                </ul>
                <div class="tab-content">
                    <div id="contag001" class="tab-pane fade active in">
                        <div class="panel-body">
                            <ul class="list-group" id="content0">

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</html:form>
</body>
</html>