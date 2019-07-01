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
    <script type="text/javascript" src="xsgzgl/gygl/cwfpgl/js/fp.js"></script>
    <script type="text/javascript" src="xsgzgl/gygl/cwfpgl/js/common.js"></script>
    <style>
        .cwClass{
            display: none;
        }
    </style>
</head>

<body>
<html:form action="/gygl_zsgl_cwgl" styleId="demoForm" method="post">
    <div>
        <div class="row">
            <div class="col-md-10">
                <div class="panel panel-default index_list margin_t15">
                    <div class="bed-select-area">
                        <div class="bed-btn">
                            <button type="button" class="btn btn-basics" onclick="save();return false;">保存分配</button>
                            <button type="button" class="btn btn-basics" onclick="qkFp();return false;">清空楼栋分配</button>
                            <button type="button" class="btn btn-basics" onclick="fh();return false;">返回</button>
                        </div>
                        <div class="bed-body">
                            <div class="innerContent">
                                <div class="formControl-select">
                                    <li>
                                        <label>年级：</label>
                                        <html:select property="nj" styleId="nj" styleClass="sel-basics" onchange="njChange();">
                                            <html:options collection="njList" labelProperty="nj" property="nj"/>
                                        </html:select>
                                    </li>
                                    <li>
                                        <label>学院：</label>
                                        <html:select property="xydm" styleId="xydm" styleClass="sel-basics" onchange="xyChange();">
                                            <html:options collection="xyList" labelProperty="mc" property="dm"/>
                                        </html:select>
                                    </li>
                                    <li>
                                        <label>专业：</label>
                                        <html:select property="zydm" styleId="zydm" styleClass="sel-basics" onchange="zyChange();">
                                            <html:options collection="zyList" labelProperty="mc" property="dm"/>
                                        </html:select>
                                    </li>
                                    <li>
                                        <label>专业班级：</label>
                                        <html:select property="bjdm" styleId="bjdm" styleClass="sel-basics" onchange="bjChange();">
                                            <html:options collection="bjList" labelProperty="mc" property="dm"/>
                                        </html:select>
                                    </li>
                                </div>
                                <div class="formControl-select">
                                    <li>
                                        <label>楼栋：</label>
                                        <html:select property="lddm" styleId="lddm" styleClass="sel-basics" >
                                            <html:option value="">---请选择---</html:option>
                                            <html:options collection="ldList" labelProperty="ldmc" property="lddm"/>
                                        </html:select>
                                    </li>
                                    <li>
                                        <label>性别：</label>
                                        <html:select property="xb" styleId="xb" styleClass="sel-basics">
                                            <option value="">---请选择---</option>
                                            <option value="1">男</option>
                                            <option value="2">女</option>
                                        </html:select>
                                    </li>
                                    <li>
                                        <label></label>
                                        <button type="button" class="btn btn-basics btn-right" onclick="search();return false;">查询</button>
                                    </li>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="bed-list-area">
                        <div class="page-hedaer">
                            <i><img src="./images/i@2x.png" alt=""></i>
                            <span class="cur">已分配统计， <span>班级总人数：<span id="bjrs" class="red">${tjxx.rs}</span>人； 已分配床位数：<span id="yfpcws" class="red">${tjxx.yfpcws}</span>个； 已分配寝室数：<span id="qss" class="red">${tjxx.yfpqss}</span>个； 已分配楼栋数：<span id="lds" class="red">${tjxx.yfplds}</span>个；</span></span>
                        </div>
                        <div class="page-table frist-td" id="mark">
                            <table>
                                <thead>
                                <tr>
                                    <th>楼栋</th>
                                    <th>可分配床位总数</th>
                                    <th>已分配床位数</th>
                                    <th>空床位数</th>
                                    <th>当前班级已分配床位数</th>
                                </tr>
                                </thead>
                                <tbody id="bjfpxx">
                                <logic:notEmpty name="bjfpxx">
                                    <logic:iterate id="i" name="bjfpxx">
                                        <tr>
                                            <td>${i.ldmc}</td>
                                            <td>${i.cws}</td>
                                            <td>${i.yfpcws}</td>
                                            <td>${i.kcws}</td>
                                            <td>${i.bjcws}</td>
                                        </tr>
                                    </logic:iterate>
                                </logic:notEmpty>
                                <logic:empty name="bjfpxx">
                                    <tr>
                                        <td>暂无分配信息</td>
                                    </tr>
                                </logic:empty>
                                </tbody>
                            </table>
                        </div>
                        <div id="lcdiv">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</html:form>
</body>
</html>