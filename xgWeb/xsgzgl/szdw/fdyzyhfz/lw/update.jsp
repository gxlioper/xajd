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
        function saveZwsq(){
            var checkId = 'tm-fbsj-kwmc';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("请将必填项填写完整！");
                return false;
            }
            var url = "szdw_fdy_lwwh.do?method=update&type=save";
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
<html:form action="/szdw_fdy_lwwh" method="post" styleId="demoForm">
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
                        <span>论文信息</span>
                    </th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <th><span class="red">*</span>题目</th>
                <td>
                    <html:text property="tm" styleId="tm" />
                </td>
                <th><span class="red">*</span>发表时间</th>
                <td>
                    <html:text property="fbsj" onclick="return showCalendar('fbsj','yyyy-MM-dd');" styleId="fbsj" readonly="true"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>刊物名称</th>
                <td>
                    <html:text property="kwmc" styleId="kwmc" />
                </td>
                <th>刊号</th>
                <td>
                    <html:text property="kh"  styleId="kh" />
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>期刊类别</th>
                <td>
                    <html:select property="qklb" styleId="qklb">
                        <html:option value="01">SSCI</html:option>
                        <html:option value="02">CSSCI</html:option>
                        <html:option value="03">CSSCI（扩展版）</html:option>
                        <html:option value="04">核心期刊</html:option>
                        <html:option value="05">其它</html:option>
                    </html:select>
                </td>
                <th><span class="red">*</span>本人承担角色</th>
                <td>
                    <html:radio property="cdjs" value="01">第一</html:radio>
                    <html:radio property="cdjs" value="02">第二</html:radio>
                    <html:radio property="cdjs" value="03">第三</html:radio>
                    <html:radio property="cdjs" value="04">其他</html:radio>
                </td>
            </tr>
            <tr>
                <th>备注</th>
                <td colspan="3">
                    <html:textarea property='bz' style="width:95%" styleId="bz" rows='5' onblur="checkLen(this,1000);"/>
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
                                eid : 'filepath_f',
                                label:'上传论文刊载截图'
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

