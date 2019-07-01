<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="css/base.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="css/style.css" media="all"/>
    <script type='text/javascript' src="js/comm/message.js"></script>
    <script language="javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="xsgzgl/xtwh/mmzh/js/mmzh.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            setHeight();
            jQuery(window).resize(function(){
                setHeight();
            });
        })
        function setHeight() {
            var ww = jQuery(window).height();
            jQuery('.topframe').css({
                'min-height': ww + 'px'
            });
        }
    </script>
    <style>
        .resetCss tr{
            border-collapse: collapse;
            border-spacing: 0;
            font:inherit;
            list-style: none;
        }

        .resetCss tr td{
            border: 0;
            padding: 0;
            vertical-align: 0px;

        }
        .tab .top-title {
            width: 490px;
            margin: 0 auto;
            height: 120px;
            overflow: hidden;
        }
        .tab .top-title li {
            width: 140px;
            float: left;
            min-height: 100px;
            position: relative;
        }
    </style>
</head>
<body class="student-worker-page">
<!-- 隐藏域 end-->
<div class="mainbody type_mainbody">
    <div class="topframe">
        <!-- TOP -->
        <div class="head">

            <!-- 学校LOGO -->
            <%@ include file="/homepage/info/logo.jsp"%>
            <!-- 学校LOGO end-->

        </div>
        <!-- TOP END-->
        <!--

        <div class="menu">
            <div class="nav">
                <ul class="ul_find">
                    <li>
                        <a href="javascript:void(0);"
                            id="li_page">密码找回</a>
                    </li>

                </ul>
            </div>
        </div>

        -->
        <div class="mainframe" style="width:100%; text-align: left;" id="mainBody" >
            <div class="prompt">
                <h3>
                    <span>提示：</span>
                </h3>
                <p>
                    <font color="red">
                        请选择密码找回方式，验证成功才能进入下一步！
                    </font>
                </p>
                <a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
            </div>
            <html:form action="/mmzhgl_mmzh" method="post" styleId="MmZhForm" onsubmit="return false;">
                <div class="tab">
                    <ul class="top-title">
                        <li class="infocus ico1">
                            <a href="#"><span>用户名验证</span></a>
                        </li>
                        <li class="infocus">
                            <a href="#"><span>找回方式</span></a>
                        </li>
                        <li class="ico2">
                            <a href="#"><span>验证信息</span></a>
                        </li>
                        <li class="ico3">
                            <a href="#"><span>修改密码</span></a>
                        </li>
                    </ul>
                    <input type="hidden" id = "yhm" name ="yhm" value="${yhm}"/>
                    <h4 class="col-md-12 text-danger text-center text-padding-10px text-verif-tip" id="verifTip1" style="display: none;"></h4>
                    <div class="col-md-8 col-md-offset-3">
                        <logic:notEmpty name="list" >
                            <logic:iterate id="item" name="list">
                                <div class="form-list-half col-md-12 m-b-30">
                                    <div class="radio radio-primary ">
                                        <input type="radio" name="retakeType" id="${item.zhfs}" value="${item.zhfs}">
                                        <label for="${item.zhfs}">${item.bz}</label>
                                    </div>
                                </div>
                            </logic:iterate>
                            <div class="tab-con">
                                <div class="tab-list">
                                    <!--<label><span class="red">*</span>为必填项</label>-->
                                    <button type="button" class="" onclick="checkZhfs();">下一步</button>
                                </div>
                            </div>
                        </logic:notEmpty>
                        <logic:empty name="list" >
                            <h4 class="col-md-12 text-danger text-center text-padding-10px text-verif-tip">未配置找回方式，请联系管理员！</h4>
                        </logic:empty>
                    </div>
                </div>

            </html:form>
        </div>

        <!-- MAIN END -->
        <!-- BOTTOM-->
        <%@ include file="/homepage/info/companyInfo.jsp"%>
        <!-- BOTTOM end-->
    </div>
</div>
</body>
</html>