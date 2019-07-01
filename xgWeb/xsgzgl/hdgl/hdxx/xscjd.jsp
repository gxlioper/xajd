<%@ page language="java" contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
           prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="customTag" %>
<%@ include file="/syscommon/v4_url.ini" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script type="text/javascript">
        var stylePath = "<%=stylePath%>";
    </script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">

    <%@ include file="/syscommon/jquery-1.11.1_migrate.ini" %>
    <script type="text/javascript" src="js/echarts/echarts.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery-resize/1.1/jquery.ba-resize.js"></script>
    <script type="text/javascript">
        jQuery(function () {
            var dom = document.getElementById("sgybwct");
            var myChart = echarts.init(dom);
            var app = {};
            option = null;
            app.title = '4个100完成状态';
            //取数据
            var xh = jQuery("#xh").val();
            var url = "hdgl_hdxx.do?method=getviewWct";
            var data = null;
            var para = {xh: jQuery("#xh").val()};
            jQuery.ajax({
                type: 'post',
                url: url,
                dataType: 'json',
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                data: para,
                async: false,
                success: function (result) {
                    data = result;
                }
            });

            var ds = data['ds'];
            var js = data['js'];
            var jz = data['jz'];
            var hd = data['hd'];

            option = {
                title: {
                    text: '4个100完成状态图',
                    textStyle: {
                        //文字颜色
                        color: '#000',
                        //字体风格,'normal','italic','oblique'
                        fontStyle: 'normal',
                        //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                        fontWeight: 'bold',
                        //字体系列
                        fontFamily: 'sans-serif',
                        //字体大小
                        fontSize: 18
                    },
                    left: 'center'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value',
                    min: 0,
                    max: 100,
                    interval: 10
                },
                yAxis: {
                    type: 'category',
                    data: ['100本书', '100位老师', '100场讲座', '100场活动']
                },
                series: [
                    {
                        name: '完成数',
                        type: 'bar',
                        data: [ds, js, jz, hd],
                        barWidth: 15,
                        //配置样式
                        itemStyle: {
                            //通常情况下
                            normal: {
                                label: {show: true, position: 'inside'},
                                //每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                                color: function (params) {
                                    var colorList = ['#3498DB', '#EFE42A', '#64BD3D', '#EE9201'];
                                    return colorList[params.dataIndex];
                                }
                            },
                            //鼠标悬停时
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        },
                    }
                ]
            };
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }

            var dom2 = document.getElementById("dektltt");
            var myChart2 = echarts.init(dom2);
            option2 = null;

            //取数据
            var url = "hdgl_hdxx.do?method=getLDWct";
            var data2 = null;
            var para = {xh: jQuery("#xh").val()};
            jQuery.ajax({
                type: 'post',
                url: url,
                dataType: 'json',
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                data: para,
                async: false,
                success: function (result) {
                    data2 = result;
                }
            });

            var dataList = data2["hdgsList"];
            var list_mc = new Array();
            var list_gs = new Array();
            for (var i = 0; i < dataList.length; i++) {
                list_mc.push({name: dataList[i]['hdlxmc'], max: 10});
                list_gs.push(dataList[i]['hds']);
            }
            option2 = {
                title: {
                    text: ''
                },
                tooltip: {},
                radar: {
                    // shape: 'circle',
                    splitNumber: 10,
                    name: {
                        textStyle: {
                            color: '#fff',
                            backgroundColor: '#999',
                            borderRadius: 3,
                            padding: [3, 5]
                        }
                    },
                    indicator: list_mc
                },
                grid: {
                    position: 'center',
                },
                series: [{
                    name: '',
                    type: 'radar',
                    // areaStyle: {normal: {}},
                    data: [
                        {
                            value: list_gs,
                            name: '能力汇总'
                        }
                    ]
                }]
            };

            if (option2 && typeof option2 === "object") {
                myChart2.setOption(option2, true);
            }

            var ww = parseInt(jQuery(window).width());
            if (ww < 1000) {
                jQuery('.label-2').css({
                    'width': '28px'
                });
                jQuery('.head-portrait').css({
                    'vertical-align': 'middle'
                });

                jQuery('.head-portrait img').css({
                    'width': '100px'
                });
            } else {
                jQuery('.label-2').css({
                    'width': 'auto'
                });
                jQuery('.head-portrait').css({
                    'vertical-align': 'top'
                });
                jQuery('.head-portrait img').css({
                    'width': '100%'
                });
            }

        });
    </script>
    <style>
        /*.grade-form .label-2{*/
        /*width:28px;*/
        /*}*/

        /*.head-portrait{*/
        /*vertical-align: middle !important;*/
        /*}*/

    </style>
</head>

<body>
<div align="right">
    <button type="button" class="btn btn-primary" onclick="javascript:window.print();">打印</button>
</div>
<div class="container">
    <div class="grade-form ">
        <h4 class="title text-center">西安交通大学第二课堂成绩单</h4>
        <input type="hidden" id="xh" value="${xh}"/>
        <%--<div class="row" style="height: 200px;width: 1000px;">--%>
            <div class="row col-md-9  col-sm-9" style="width: 780px;">
                <table class="table table-bordered person-info-table">
                    <tr>
                        <td colspan="2" rowspan="3" class="head-portrait">
                            <div class="img-2">
                                <img style="width: 100%; height: 130px;"
                                     src="xsxx_xsgl.do?method=showPhoto&amp;xh=${jbxx.xh}" border="0">
                            </div>
                        </td>
                        <td>
                            <div class="label-2">姓名</div>
                        </td>
                        <td><span>${jbxx.xm }</span></td>
                        <td>
                            <div class="label-2">书/学院</div>
                        </td>
                        <td><span>${jbxx.symc }/${jbxx.xymc }</span></td>
                        <td>
                            <div class="label-2">政治面貌</div>
                        </td>
                        <td><span>${jbxx.zzmmmc }</span></td>
                    </tr>
                    <tr>
                        <td>
                            <div class="label-2">学号</div>
                        </td>
                        <td><span>${jbxx.xh }</span></td>
                        <td>
                            <div class="label-2">专业</div>
                        </td>
                        <td><span>${jbxx.zymc}</span></td>
                        <td>
                            <div class="label-2">民族</div>
                        </td>
                        <td><span>${jbxx.mzmc}</span></td>
                    </tr>
                    <tr>
                        <td>
                            <div class="label-2">性别</div>
                        </td>
                        <td><span>${jbxx.xb}</span></td>
                        <td>
                            <div class="label-2">班级</div>
                        </td>
                        <td><span>${jbxx.bjmc}</span></td>
                        <td>
                            <div class="label-2">电话</div>
                        </td>
                        <td><span>${jbxx.sjhm}</span></td>
                    </tr>
                </table>
            </div>

        <%--</div>--%>
        <div class="row" style="text-align: center" >
            <div class="col-md-3 col-sm-3" style="height:280px;width: 600px;vertical-align: middle;" id="dektltt">
                <!--<img src="assets/images/echart-pic01.png">-->
            </div>
        </div>

        <div class="row">
            <logic:iterate id="i" name="list" indexId="rn">
                <div class="col-md-6  col-sm-6">
                    <p class="sm-title"><i class="vertical-bar"></i><span>${i[0].hdlxmc}</span></p>
                    <logic:iterate id="j" name="i" indexId="number">
                        <p><span class="date">${j.shsj}</span> <span class="cont">${j.hdmc}</span></p>
                    </logic:iterate>
                </div>
            </logic:iterate>
            <div class="col-md-6  col-sm-6">
                <p class="sm-title"><i class="vertical-bar"></i><span>“4个100”特别成就</span></p>
                <div class="panel panel-default panel-tbcj">
                    <div class="panel-body" style="height:200px;" id="sgybwct">
                    </div>
                </div>
            </div>
            <div class="col-md-6  col-sm-6">
                <p class="sm-title"><i class="vertical-bar"></i><span>其他特别荣誉及经历</span></p>
                <div class="panel panel-default panel-llxx">
                    <div class="panel-body">
                        <ul>
                            <li><img src="assets/images/cup-icon01.png"><span></span></li>
                            <li><img src="assets/images/cup-icon02.png"><span></span></li>
                            <li><span class="ellipsis"></span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="ranking text-center">在同年级学生中排名前${njpmMap.zxfnjpm }</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6  col-sm-6">
                <div class="panel panel-default panel-xfhz">
                    <div class="panel-body">
                        <ul>
                            <li><span class="sm-title">学分汇总</span></li>
                            <logic:iterate id="i" name="hdxfList" indexId="rn">
                                <li><span>${i.hdlxmc }</span><span class="pull-right">${i.zxf }分</span></li>
                            </logic:iterate>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-6  col-sm-6 padding-lr0">
                <div class="col-md-6  col-sm-6">
                    <div class="panel panel-default panel-qz">
                        <div class="panel-body">
                            <img src="assets/images/bjqz.png">
                        </div>
                    </div>
                </div>
                <div class="col-md-6  col-sm-6">
                    <div class="panel panel-default panel-qz">
                        <div class="panel-body">
                            <img src="assets/images/xyqz.png">
                        </div>
                    </div>
                </div>
                <div class="col-md-6  col-sm-6">
                    <div class="panel panel-default panel-qz">
                        <div class="panel-body">
                            <img src="assets/images/twqz.png">
                        </div>
                    </div>
                </div>
                <div class="col-md-6  col-sm-6">
                    <div class="panel panel-default panel-qz">
                        <div class="panel-body">
                            <img src="assets/images/xscqz.png">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">

    jQuery(document).ready(function () {
        //最新详情点击事件
        jQuery("#zxxq").on("click", function () {
            getHdInfo();
            jQuery("#zxxq").parent().attr('class', 'boder-right active');
            jQuery("#plgl").parent().attr('class', 'boder-right');
            jQuery("#bmgl").parent().attr('class', 'boder-right');
        });

        //评论管理点击事件
        jQuery("#plgl").on("click", function () {
            plgl();
            jQuery("#zxxq").parent().attr('class', 'boder-right');
            jQuery("#plgl").parent().attr('class', 'boder-right active');
            jQuery("#bmgl").parent().attr('class', 'boder-right');
        });

        //报名管理点击事件
        jQuery("#bmgl").on("click", function () {
            bmgl();
            jQuery("#zxxq").parent().attr('class', 'boder-right');
            jQuery("#plgl").parent().attr('class', 'boder-right');
            jQuery("#bmgl").parent().attr('class', 'boder-right active');
        });

        jQuery("#zxxq").trigger("click");
    })
</script>
</html>
