<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html>
<html>
<head>
    <title>床位分配</title>
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
    <%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
    <%@ include file="/syscommon/v4_url.ini"%>
    <script type="text/javascript">
        var stylePath = "<%=stylePath%>";
    </script>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/base.css" />
    <link rel="stylesheet" href="css/style.css" />
    <script type="text/javascript" src="js/function.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-migrate-1.4.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/comm/ymPrompt.js" ></script>
    <script type='text/javascript' src="js/comm/message.js"></script>
    <script type='text/javascript' src="js/comm/watermark.js"></script>
    <script language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript" src="js/json.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <link rel="stylesheet" href="comm/skin/zfstyle/ymPrompt.css" type="text/css"  media="all" />
    <script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
    <script type="text/javascript" src="xsgzgl/gygl/cwdh/js/cwdh.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            jQuery("#xqdm1").trigger('change');
            jQuery("#xqdm2").trigger('change');
        })
    </script>
    <style>
    </style>
</head>

<body>
<html:form action="/gygl_zsgl_cwdhgl" styleId="demoForm" method="post">
    <div>
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default index_list margin_t15">
                    <div class="bed-select-area" style="min-height: 170px;">
                        <div class="bed-btn">
                            <button type="button" class="btn btn-basics" onclick="save();">确认调换</button>
                        </div>
                        <div class="exch bed-body">
                            <div class="bed-title">
                                <span>转出宿舍</span>
                            </div>
                            <div class="innerContent">
                                <div class="formControl-select exch-formControl">
                                    <li>
                                        <label for="">校区：</label>
                                        <html:select property="xqdm1" styleId="xqdm1" styleClass="sel-basics" onchange="xqChange('xqdm1','lddm1')">
                                            <html:options collection="xqList" labelProperty="xqmc" property="dm"/>
                                        </html:select>
                                    </li>
                                    <li>
                                        <label for="">楼层：</label>
                                        <html:select property="ch1" styleId="ch1" styleClass="sel-basics" onchange="lcChange('lddm1','ch1','qsh1')">
                                        </html:select>
                                    </li>
                                </div>
                                <div class="formControl-select exch-formControl">
                                    <li>
                                        <label for="">楼栋：</label>
                                        <html:select property="lddm1" styleId="lddm1" styleClass="sel-basics" onchange="ldChange('lddm1','ch1')">
                                        </html:select>
                                    </li>
                                    <li>
                                        <label for="">宿舍：</label>
                                        <html:select property="qsh1" styleId="qsh1" styleClass="sel-basics">
                                        </html:select>
                                    </li>
                                </div>
                            </div>
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="请输入学号查询" aria-describedby="basic-addon2" id="xh1">
                                <span class="input-group-addon" onclick="search('xqdm1','lddm1','ch1','qsh1','outSpan','outTable','xh1');">搜索</span>
                            </div>
                        </div>
                    </div>
                    <div class="exch-header-area">
                        <div class="exch-header">
                            <p>当前选择： <span id="outSpan"></span></p>
                            <div class="exch-header-list" id="outSelectDiv">
                                <span style="margin-top: 6px;">已选择:</span>
                            </div>
                        </div>
                    </div>
                    <div class="bed-list-area">
                        <div class="page-table frist-td">
                            <table id="outTable">
                                <thead>
                                <tr>
                                    <th>床位号</th>
                                    <th>床位性别</th>
                                    <th>是否保留</th>
                                    <th>学号</th>
                                    <th>姓名</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>



                    <div class="bed-select-area exch-list">
                        <div class="bed-btn">
                        </div>
                        <div class="exch bed-body">
                            <div class="bed-title">
                                <span>转入宿舍</span>
                            </div>
                            <div class="innerContent">
                                <div class="formControl-select exch-formControl">
                                    <li>
                                        <label for="">校区：</label>
                                        <html:select property="xqdm2" styleId="xqdm2" styleClass="sel-basics" onchange="xqChange('xqdm2','lddm2')">
                                            <html:options collection="xqList" labelProperty="xqmc" property="dm"/>
                                        </html:select>
                                    </li>
                                    <li>
                                        <label for="">楼层：</label>
                                        <html:select property="ch1" styleId="ch2" styleClass="sel-basics" onchange="lcChange('lddm2','ch2','qsh2')">
                                        </html:select>
                                    </li>
                                </div>
                                <div class="formControl-select exch-formControl">
                                    <li>
                                        <label for="">楼栋：</label>
                                        <html:select property="lddm2" styleId="lddm2" styleClass="sel-basics" onchange="ldChange('lddm2','ch2')">
                                        </html:select>
                                    </li>
                                    <li>
                                        <label for="">宿舍：</label>
                                        <html:select property="qsh2" styleId="qsh2" styleClass="sel-basics">
                                        </html:select>
                                    </li>
                                </div>
                            </div>
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="请输入学号查询" aria-describedby="basic-addon2">
                                <span class="input-group-addon" id="basic-addon2" onclick="search('xqdm2','lddm2','ch2','qsh2','inSpan','inTable','xh2');">搜索</span>
                            </div>
                        </div>
                    </div>
                    <div class="exch-header-area">
                        <div class="exch-header">
                            <p>当前选择： <span id="inSpan"></span></p>
                            <div class="exch-header-list" id="inSelectDiv">
                                <span style="margin-top: 6px;">已选择:</span>
                            </div>
                        </div>
                    </div>
                    <div class="bed-list-area">
                        <div class="page-table frist-td">
                            <table id="inTable">
                                <thead>
                                <tr>
                                    <th>床位号</th>
                                    <th>床位性别</th>
                                    <th>是否保留</th>
                                    <th>学号</th>
                                    <th>姓名</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</html:form>
</body>
</html>