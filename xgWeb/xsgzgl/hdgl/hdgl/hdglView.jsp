<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="xsgzgl/hdgl/js/hdgl.js"></script>
    <!--Bootstrap css-->
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--����css-->
    <link href="assets/css/style.css" rel="stylesheet">
</head>

<body>
    <div class="secondclass_title">
        <h3>${hdxx.hdmc}</h3>
    </div>

    <div class="tab-content col-sm-12 p-0">
        <div class="tab-pane fade active in" id="hdxq">
            <div class="col-sm-7 num-list p-l-0 m-15-0">

            </div>
            <div class="button-groups m-15-0 col-sm-5 text-right p-r-0">

            </div>
            <div class="details-left col-sm-8 p-half p-l-0">
                <div class="panel panel-default margin_t15 p-10 index_list">
                    <div class="details-con">
                        <h4 class="col-sm-7">�����</h4>

                        <div class="img">
                            <img src="${hdxx.hb}">
                        </div>
                        <span>
                            ${hdxx.nryq}
                        </span>
                    </div>
                </div>
            </div>
            <div class="details-right col-sm-4 p-half p-r-0">
                <div class="panel panel-default margin_t15 p-10 index_list">
                    <div class="details_title">
                        <h4>
                            ${hdxx.hdmc}
                        </h4>
                        <div class="form-group">
                            <label>ʱ�䣺</label>
                            <span class="time">${hdxx.hdkssj} - ${hdxx.hdjssj}</span>
                        </div>
                        <div class="form-group">
                            <label>�ص㣺</label>
                            <span class="address">${hdxx.hddd}</span>
                        </div>
                        <div class="form-group">
                            <label>���ͣ�</label>
                            <span class="address">${hdxx.hdlxmc}</span>
                        </div>
                        <div class="form-group">
                            <label>����ʱ�䣺</label>
                            <span class="address">${hdxx.fbsj}</span>
                        </div>
                        <div class="form-group">
                            <label>��������</label>
                            <span class="address">${hdxx.bmdx}</span>
                        </div>
                        <div class="form-group">
                            <label>�������ͣ�</label>
                            <span class="address">${hdxx.bmlx=="0"?"���":"����"}</span>
                        </div>

                    </div>
                    <div class="datails_button">
                        <button class="btn btn-primary" onclick="window.history.back()">����</button>
                    </div>
                </div>
            </div>
        </div>
    </div>


<!--Jquery-->
<script src="assets/js/jquery-min.js"></script>
<!--Bootstrap js-->
<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>