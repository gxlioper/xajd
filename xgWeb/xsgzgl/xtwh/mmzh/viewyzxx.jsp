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

<!-- ������ end-->
<div class="mainbody type_mainbody">
    <div class="topframe">
        <!-- TOP -->
        <div class="head">

            <!-- ѧУLOGO -->
            <%@ include file="/homepage/info/logo.jsp"%>
            <!-- ѧУLOGO end-->

        </div>
        <!-- TOP END-->
        <!--

        <div class="menu">
            <div class="nav">
                <ul class="ul_find">
                    <li>
                        <a href="javascript:void(0);" id="li_page">�����һ�</a>
                    </li>
                    <li><a href="#"></a></li>
                </ul>
            </div>
        </div>-->
        <div class="mainframe" style="width:100%; text-align: left;" id="mainBody" >
            <div class="prompt">
                <h3>
                    <span>��ʾ��</span>
                </h3>
                <p>
                    <font color="red">
                        ��������������ֻ��ţ���ȡ��֤�룬��֤�ɹ����ܽ�����һ����
                    </font>
                </p>
                <a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
            </div>
            <html:form action="/mmzhgl_mmzh" method="post" styleId="MmZhForm" onsubmit="return false;">
            <div class="tab">

                <ul class="top-title">
                    <li class="infocus ico1">
                        <a href="#"><span>�û�����֤</span></a>
                    </li>
                    <li class="infocus">
                        <a href="#"><span>�һط�ʽ</span></a>
                    </li>
                    <li class="infocus ico2">
                        <a href="#"><span>��֤��Ϣ</span></a>
                    </li>
                    <li class="ico3">
                        <a href="#"><span>�޸�����</span></a>
                    </li>
                </ul>
                <div class="tab-con">
                    <div class="tab-list">
                        <logic:equal value="email" name="zhfs">
                            <label><span class="red">*</span>��������</label>
                        </logic:equal>
                        <logic:equal value="phone" name="zhfs">
                            <label><span class="red">*</span>��ϵ�绰</label>
                        </logic:equal>
                        <div class="tab-text">
                            <input type="text" id="zhfszh"  style="width:154px" name="zhfszh" >
                        </div>
                        <div class="tab-text">
                            <input id="captcha" name="captcha" type="text" placeholder="��������֤��..."/>
                            <button id="btn" class="btn btn-confirm btn-control" onclick="checkyzxx();">��ȡ��֤��</button>
                        </div>
                        <input type="hidden" id="yhm" value="${yhm}" name="yhm">
                        <input type="hidden" id="zhfsHd" value="${zhfs}">
                    </div>
                    <div class="tab-list">
                        <button id="nextBtn" class="btn btn-confirm btn-control" type="button" onclick="next();" disabled="disabled">��һ��</button>
                    </div>
                </div>
            </div>
                <input id="zhfs" value="${zhfs}" name="zhfs" type="hidden"/>
            </html:form>
        </div>
        <%@ include file="/homepage/info/companyInfo.jsp"%>
    </div>

</div>
</body>
</html>