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
    <script type="text/javascript" src="xsgzgl/szdw/jfxx/js/jfxx.js"></script>
    <script type="text/javascript">
        function saveZwsq(){
            var checkId = 'xh-jfsj-jfxz-yy-nr-csjy';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("请将必填项填写完整！");
                return false;
            }
            var url = "szdw_jfxx.do?method=add&type=save";
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }
        function selectDialog(){
            showDialog('请选择一个学生',800,500,'szdw_jfxx.do?method=selectStudent&goToPath=szdw_jfxx.do?method=add');
        }
        jQuery(function(){
            onShow("add");
        });
    </script>
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
            <%--<tbody>
                <tr>
                    <th><span class="red">*</span>学号</th>
                    <td>
                        <input name="xh" id="xh" style="width:120px;">
                        <button class="btn_01" type="button" onclick="selectDialog();">选择</button>
                    </td>
                    <th>姓名</th>
                    <td id="xmTd">
                    </td>
                </tr>
                <tr>
                    <th>性别</th>
                    <td id="xbTd">
                    </td>
                    <th>年级</th>
                    <td id="njTd">
                    </td>
                </tr>
                <tr>
                    <th>书院</th>
                    <td id="syTd">
                    </td>
                    <th>学院</th>
                    <td id="xyTd">
                    </td>
                </tr>
                <tr>
                    <th>行政班级</th>
                    <td id="bjTd">
                    </td>
                    <th>专业班级</th>
                    <td id="zybjTd">
                    </td>
                </tr>
                <tr>
                    <th>民族</th>
                    <td id="mzTd">
                    </td>
                    <th>辅导员</th>
                    <td id="fdyTd">
                    </td>
                </tr>
            </tbody>
            --%>
            <%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>
                        家访对象&nbsp;&nbsp;
                        <a class="name" href="javascript:void(0);" onclick="addTr();return false;">增加</a>
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
                                <th>操作</th>
                            </tr>
                        </table>
                    </td>
                </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>家访信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <th><span class="red">*</span>家访时间</th>
                    <td>
                        <html:text property="jfsj" onclick="return showCalendar('jfsj','yyyy-MM-dd');" styleId="jfsj" readonly="true"/>
                    </td>
                    <th>地点</th>
                    <td>
                        <html:text property="dd" styleId="dd" />
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>家访性质</th>
                    <td>
                        <html:select property="jfxz" styleId="jfxz">
                            <html:option value="01">实地</html:option>
                            <html:option value="02">电话</html:option>
                            <html:option value="03">微信</html:option>
                        </html:select>
                    </td>
                    <th>参与人数</th>
                    <td>
                        <html:text property="rs" styleId="rs" maxlength="2" onblur="checkInt(this);"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>家访原因</th>
                    <td colspan="3">
                        <html:textarea property='yy' style="width:95%" styleId="yy" rows='5' onblur="checkLenBtw(this,50,1000);"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>家访内容</th>
                    <td colspan="3">
                        <html:textarea property='nr' style="width:95%" styleId="nr" rows='5' onblur="checkLenBtw(this,100,1000);"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>措施或建议</th>
                    <td colspan="3">
                        <html:textarea property='csjy' style="width:95%" styleId="csjy" rows='5' onblur="checkLenBtw(this,50,1000);"/>
                    </td>
                </tr>
                    <tr>
                        <th>
                            附件
                        </th>
                        <td  colspan="3">
                            <html:hidden property="filepath" styleId="filepath" />
                            <input type="file" id="filepath_f" name="filepath" />
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

