<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>
    <script type="text/javascript" src="xsgzgl/xyfd/js/highcharts.js"></script>
    <script type="text/javascript" src="xsgzgl/xyfd/js/jquery.raty.js"></script>
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
    <style>
        tbody td {
            border: 1px solid #ddd;
        }
        thead th {
            border: 1px solid #ddd;
        }
        .fs-12{
            font-size: 12px;
        }
        .PageNext{page-break-after: always;}
    </style>
    <style media="print">
        .noprint{display:none;}
        .print{display:block;}
    </style>
</head>
<body >
<p align="center">
    <button type="button" class="btn btn-primary noprint to_print" >打印</button>
    <button type="button" class="btn btn-default noprint to_close" >关闭</button>
</p>
<div class="print">
    <div class=" PageNext">
        <div style="text-align: center;font-size: 24px;"><b>辅导人员取消情况统计</b></div>
        <table class="table table-1 fs-12" style="width: 100%;margin: auto">
            <tbody>
            <tr>
                <td style="width: 20%;" align="center">姓名</td>
                <td style="width: 25%">${qxtj.xm }</td>
                <td style="width: 25%;" align="center">辅导人员身份</td>
                <td style="width: 30%">${qxtj.fdjslx }</td>
            </tr>
            <tr>
                <td align="center">取消次数</td>
                <td colspan="3">
                    ${qxtj.qxcs}
                </td>
            </tr>
            <tr>
                <td style="vertical-align:middle;text-align: center" rowspan="${size}">历史取消记录</td>
                <td align="center">学生姓名</td>
                <td align="center">时间</td>
                <td align="center">取消原因</td>
            </tr>
            <logic:iterate id="i" name="qxjlList" indexId="j">
                <tr>
                    <td align="center">${i.xm}</td>
                    <td align="center">${i.qxsj}</td>
                    <td align="center">${i.qxyymc}</td>
                </tr>
            </logic:iterate>
            <tr>
                <td colspan="4">
                    <div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    jQuery(function($){
        $(document).off("click touchend", ".btn.to_print").on("click touchend", ".btn.to_print", function(e) {
            //绑定新页面打印按钮
            window.print();
        }).off("click touchend", ".btn.to_close").on("click touchend", ".btn.to_close", function(e) {
            window.location.href="about:blank";
            //绑定新页面关闭按钮
            window.close();
        });

        jQuery.fn.raty.defaults.path = 'xsgzgl/xyfd/img';

        lspjtj();
    });

    function lspjtj() { //取消原因统计
        var chart = {
            type: 'column',
            inverted: true
        };
        var title = {
            text: '取消原因统计'
        };
        var xAxis = {
            categories: ['1','2','3','4','5'],
            crosshair: true
        };
        var yAxis = {
            min: 0,
            title: {
                text: '累计次数（次）'
            }
        };
        var tooltip = {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y:.1f} 次</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        };
        var plotOptions = {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        };
        var credits = {
            enabled: false
        };
        var series= [{
            name: '',
            data: ${qxyycs}
        }];
        var json = {};
        json.chart = chart;
        json.title = title;
        json.tooltip = tooltip;
        json.xAxis = xAxis;
        json.yAxis = yAxis;
        json.series = series;
        json.plotOptions = plotOptions;
        json.credits = credits;
        jQuery('#container').highcharts(json);
    }
</script>

</body>
</html>