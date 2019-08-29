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
        <div style="text-align: center;font-size: 24px;"><b>个人详细评价统计表</b></div>
        <table class="table table-1 fs-12" style="width: 100%;margin: auto">
            <tbody>
            <tr>
                <td style="width: 20%;" align="center">姓名</td>
                <td style="width: 25%">${pjtjxx.xm }</td>
                <td style="width: 25%;" align="center">辅导人员身份</td>
                <td style="width: 30%">${pjtjxx.fdjslx }</td>
            </tr>
            <tr>
                <td align="center">平均评价</td>
                <td colspan="3">
                    <div id="pjpj"></div>
                    <div><input type="hidden" id="pjpf" name="pjpf" value="${pjtjxx.pjpf}"/></div>
                </td>
            </tr>
            <tr>
                <td style="vertical-align:middle;text-align: center" rowspan="${size}">历史评价记录</td>
                <td>辅导学生姓名</td>
                <td>辅导时间</td>
                <td>星级</td>
            </tr>
            <logic:iterate id="i" name="pjList" indexId="j">
                <tr>
                    <td>${i.xm}</td>
                    <td>${i.fdsj}</td>
                    <td>
                        <div id="star${j}"></div>
                        <div><input type="hidden" id="pf${j}" name="" value="${i.pf}"/></div>
                    </td>
                </tr>
            </logic:iterate>
            <tr>
                <td colspan="4">
                    <div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div id="container2" style="width: 550px; height: 400px; margin: 0 auto"></div>
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
        pjxj();
        zjscqs();
        lspjtj();
    });

    function pjxj() { //平均评价、历史评价星级
        jQuery('#pjpj').raty({
            score: '${pjtjxx.pjpf}',
            half: true,
            readOnly: true
        });
        var size = '${size}'-1;
        var pf = '0';
        for(var i=0;i<size;i++){
            pf = jQuery('#pf'+i).val();
            jQuery('#star'+i).raty({
                score: pf,
                half: true,
                readOnly: true
            })
        }
    }

    function zjscqs() {//近十次评价趋势
        var title = {
            text: '近十次评价趋势'
        };
        var xAxis = {
            categories: ['', '', '', '', '', '','', '', '', '', '', '']
        };
        var yAxis = {
            title: {
                text: '（星）'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        };
        var tooltip = {
            valueSuffix: '星'
        }
        var legend = {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        };
        var series =  [
            {
                name: '',
                data: ${dataList}
            }
        ];
        var json = {};
        json.title = title;
        json.xAxis = xAxis;
        json.yAxis = yAxis;
        json.tooltip = tooltip;
        json.legend = legend;
        json.series = series;
        jQuery('#container').highcharts(json);
    }
    
    function lspjtj() { //历史评价统计
        var chart = {
            type: 'column'
        };
        var title = {
            text: '历史评价统计'
        };
        var xAxis = {
            categories: ['0','0.5','1.0','1.5','2.0','2.5','3.0','3.5','4.0','4.5','5.0'],
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
            data: ${dataList2}
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
        jQuery('#container2').highcharts(json);
    }
</script>

</body>
</html>