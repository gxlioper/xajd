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
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript">
        function saveZwsq(type,shzt){
            var checkId = 'mc-yjfx-clsj-jfze-cdjs';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("请将必填项填写完整！");
                return false;
            }
            var url = "szdw_fdy_gzfwh.do?method=update&type=save";
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }

    </script>
</head>
<body style="width:100%">
<html:form action="/szdw_fdy_gzfwh" method="post" styleId="demoForm">
    <html:hidden property="id"></html:hidden>
    <div style='width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>基本信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <th>职工号</th>
                    <input type="hidden" name="zgh" value="${fdyxx.zgh}">
                    <td>${fdyxx.zgh}</td>
                    <th>姓名</th>
                    <td>${fdyxx.xm}</td>
                </tr>
                <tr>
                    <th>性别</th>
                    <td>${fdyxx.xb}</td>
                    <th>名族</th>
                    <td>${fdyxx.mzmc}</td>
                </tr>
                <tr>
                    <th>所在部门</th>
                    <td>${fdyxx.bmmc}</td>
                    <th>所在书院</th>
                    <td>${fdyxx.symc}</td>
                </tr>
                <tr>
                    <th>政治面貌</th>
                    <td>${fdyxx.zzmmmc}</td>
                    <th>联系电话</th>
                    <td>${fdyxx.lxdh}</td>
                </tr>
                <tr>
                    <th>到校工作时间</th>
                    <td colspan="3">${fdyxx.rxgzsj}</td>
                </tr>
            </tbody>
            <thead>
                <tr>
                    <th colspan="4">
                        <span>工作室/工作坊信息</span>
                    </th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <th><span class="red">*</span>名称</th>
                <td>
                    <html:text property="mc" styleId="mc" />
                </td>
                <th><span class="red">*</span>研究方向</th>
                <td>
                    <html:text property="yjfx" styleId="yjfx" />
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>级别</th>
                <td>
                    <html:radio property="jb" value="01">校级</html:radio>
                    <html:radio property="jb" value="02">省级</html:radio>
                </td>
                <th><span class="red">*</span>成立时间</th>
                <td>
                    <html:text property="clsj" onclick="return showCalendar('clsj','yyyy-MM-dd');" styleId="clsj" readonly="true"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>经费总额</th>
                <td>
                    <html:text property="jfze" styleId="jfze" />
                </td>
                <th><span class="red">*</span>本人承担角色</th>
                <td>
                    <html:select property="cdjs" styleId="cdjs">
                        <html:options collection="jsList" property="dm" labelProperty="mc"></html:options>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>是否考核通过</th>
                <td colspan="3">
                    <html:radio property="sfkhtg" value="1">通过</html:radio>
                    <html:radio property="sfkhtg" value="0">未通过</html:radio>
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
                    <div class="bz">"<span class="red">*</span>"为必填项</div>
                    <div class="btn">
                        <button type="button" type="button" onclick="saveZwsq();return false;" >
                            保存
                        </button>
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

