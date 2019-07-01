<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    <style type="text/css">
        #shlccx_table th{text-align: center;}
        #shlccx_table tr{text-align: center;}
    </style>
</head>
<body style="width:100%">
<html:form action="/szdw_jfxx" method="post" styleId="demoForm">
    <div style='width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>基本信息</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>
                        家访对象
                    </span>
                </th>
            </tr>
            </thead>
            <tbody id="家访对象">
            <tr>
                <td colspan="4">
                    <table id="shlccx_table" width="100%">
                        <tr>
                            <th>姓名</th>
                            <th>与家访人关系</th>
                            <th>联系电话</th>
                            <th>备注</th>
                        </tr>
                        <logic:notEmpty name="cyList">
                            <logic:iterate id="i" name="cyList" indexId="index">
                                <tr>
                                    <td>${i.xm}</td>
                                    <td>${i.mc}</td>
                                    <td>${i.lxdh}</td>
                                    <td>${i.bz}</td>
                                </tr>
                            </logic:iterate>
                        </logic:notEmpty>
                    </table>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>家访信息</span>
                    <html:hidden property="jgid" styleId="jgid"></html:hidden>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>家访时间</th>
                <td>
                    ${model.jfsj}
                </td>
                <th>地点</th>
                <td>
                        ${model.dd}
                </td>
            </tr>
            <tr>
                <th>家访性质</th>
                <td>
                    <logic:equal value="01" name="model" property="jfxz">
                        实地
                    </logic:equal>
                    <logic:equal value="02" name="model" property="jfxz">
                        电话
                    </logic:equal>
                </td>
                <th>参与人数</th>
                <td>
                        ${model.rs}
                </td>
            </tr>
            <tr>
                <th>家访原因</th>
                <td colspan="3">
                        ${model.yy}
                </td>
            </tr>
            <tr>
                <th>家访内容</th>
                <td colspan="3">
                        ${model.nr}
                </td>
            </tr>
            <tr>
                <th>措施或建议</th>
                <td colspan="3">
                        ${model.csjy}
                </td>
            </tr>
            <tr>
                <th>
                    附件
                </th>
                <td  colspan="3">
                    <div id="commonfileupload-list-0" style="padding: 5px;"></div>
                    <script type="text/javascript">
                        //调用附件
                        jQuery(function(){
                            var gid = "${filepath}";
                            jQuery.MultiUploader_q({
                                gid : gid,
                                targetEl : 'commonfileupload-list-0'
                            });
                        });
                    </script>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div>
        <table width="100%" border="0" class="formlist">
            <tfoot>
            <tr>
                <td colspan="4" >
                    <div class="btn">
                        <button type="button" name="关 闭" onclick="iFClose();">
                            关 闭
                        </button>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</html:form>
</body>
</html>

