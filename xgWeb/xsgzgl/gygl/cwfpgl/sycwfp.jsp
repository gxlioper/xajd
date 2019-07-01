<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html>
<html>
<head>
    <title>��λ����</title>
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
    <script type="text/javascript" src="xsgzgl/gygl/cwfpgl/js/sycwfp.js"></script>
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
                            <button type="button" class="btn btn-basics" onclick="save();return false;">�������</button>
                            <button type="button" class="btn btn-basics" onclick="qkFp();return false;">���¥������</button>
                            <button type="button" class="btn btn-basics" onclick="fh();return false;">����</button>
                        </div>
                        <div class="bed-body">
                            <div class="innerContent">
                                <div class="formControl-select">
                                    <li>
                                        <label>�꼶��</label>
                                        <html:select property="nj" styleId="nj" styleClass="sel-basics" onchange="njChange();">
                                            <html:options collection="njList" labelProperty="nj" property="nj"/>
                                        </html:select>
                                    </li>
                                    <li>
                                        <label>��Ժ��</label>
                                        <html:select property="sydm" styleId="sydm" styleClass="sel-basics" onchange="syChange();">
                                            <html:options collection="syList" labelProperty="mc" property="dm"/>
                                        </html:select>
                                    </li>
                                    <li>
                                        <label>�����༶��</label>
                                        <html:select property="bjdm" styleId="bjdm" styleClass="sel-basics" onchange="bjChange();">
                                            <html:options collection="bjList" labelProperty="mc" property="dm"/>
                                        </html:select>
                                    </li>
                                </div>
                                <div class="formControl-select">
                                    <li>
                                        <label>¥����</label>
                                        <html:select property="lddm" styleId="lddm" styleClass="sel-basics" >
                                            <html:option value="">---��ѡ��---</html:option>
                                            <html:options collection="ldList" labelProperty="ldmc" property="lddm"/>
                                        </html:select>
                                    </li>
                                    <li>
                                        <label>�Ա�</label>
                                        <html:select property="xb" styleId="xb" styleClass="sel-basics">
                                            <option value="">---��ѡ��---</option>
                                            <option value="1">��</option>
                                            <option value="2">Ů</option>
                                        </html:select>
                                    </li>
                                    <li>
                                        <label></label>
                                        <button type="button" class="btn btn-basics btn-right" onclick="search();return false;">��ѯ</button>
                                    </li>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="bed-list-area">
                        <div class="page-hedaer">
                            <i><img src="./images/i@2x.png" alt=""></i>
                            <span class="cur">�ѷ���ͳ�ƣ� <span>�༶��������<span id="bjrs" class="red">${tjxx.rs}</span>�ˣ� �ѷ��䴲λ����<span id="yfpcws" class="red">${tjxx.yfpcws}</span>���� �ѷ�����������<span id="qss" class="red">${tjxx.yfpqss}</span>���� �ѷ���¥������<span id="lds" class="red">${tjxx.yfplds}</span>����</span></span>
                        </div>
                        <div class="page-table frist-td" id="mark">
                            <table>
                                <thead>
                                <tr>
                                    <th>¥��</th>
                                    <th>�ɷ��䴲λ����</th>
                                    <th>�ѷ��䴲λ��</th>
                                    <th>�մ�λ��</th>
                                    <th>��ǰ�༶�ѷ��䴲λ��</th>
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
                                        <td>���޷�����Ϣ</td>
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