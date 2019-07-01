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
            var checkId = 'pxxd';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("请将必填项填写完整！");
                return false;
            }
            var url = "szdw_fdy_ywxxypxjg.do?method=update&type=save";
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
<html:form action="/szdw_fdy_ywxxypxjg" method="post" styleId="demoForm">
    <html:hidden property="jgid"></html:hidden>
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
                    <th width="30%">性别</th>
                    <td width="30%">${fdyxx.xb}</td>
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
                        <span>培训信息</span>
                    </th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <th>培训名称</th>
                <td>
                    ${result.xmmc}
                </td>
                <th>培训时间</th>
                <td>
                    ${result.pxsj}
                </td>
            </tr>
            <tr>
                <th>组织部门</th>
                <td>
                    ${result.zzbmmc}
                </td>
                <th>学时</th>
                <td>
                    ${result.pxxs}
                </td>
            </tr>
            <tr>
                <th>培训内容</th>
                <td colspan="3">
                    ${result.pxjj}
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>培训结果信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th><span class="red">*</span>考勤情况</th>
                <td colspan="3">
                    <logic:equal value="到场" property="kqqk" name="result">
                        <input type="radio" name="kqqk" value="到场" checked="checked">到场</input>
                        <input type="radio" name="kqqk" value="缺勤">缺勤</input>
                    </logic:equal>
                    <logic:equal value="缺勤" property="kqqk" name="result">
                        <input type="radio" name="kqqk" value="到场">到场</input>
                        <input type="radio" name="kqqk" value="缺勤" checked="checked">缺勤</input>
                    </logic:equal>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>培训心得</th>
                <td colspan="3">
                    <html:textarea property='pxxd' style="width:95%" styleId="pxxd" rows='5' onblur="checkLen(this,1000);"/>
                </td>
            </tr>
            <tr>
                <th>
                    附件
                </th>
                <td  colspan="3">
                    <html:hidden property="filepath" styleId="filepath" />
                    <input type="file" id="filepath_f" name="filepath" value="${filepath}"/>
                    <script type="text/javascript">
                        //调用附件
                        jQuery(function(){
                            jQuery('#filepath_f').multiUploader({
                                maxcount : 3,
                                //后缀
                                accept : 'png|gif|jpg|zip|rar|doc|docx',
                                //最大文件大小 单位M
                                maxsize: 10,
                                //存放附件的隐藏域的id
                                elementid : 'filepath',
                                eid : 'filepath_f'
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
                    <div class="bz">"<span class="red">*</span>"为必填项</div>
                    <div class="btn">
                        <button type="button" type="button" onclick="saveZwsq();return false;" >
                            保存草稿
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

