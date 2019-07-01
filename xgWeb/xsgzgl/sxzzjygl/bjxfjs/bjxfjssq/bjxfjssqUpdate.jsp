<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script language="javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/bjxfjs/bjxfjssq/js/bjxfjssqEdit.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var dybl = 0;
            var dyrs = "${map.dyrs}";
            var bjzrs = "${map.bjzrs}";
            if(dyrs > 0 && bjzrs>0)
                dybl = (dyrs*100/bjzrs).toFixed(2)+"%";
            jQuery("#dyblTD").html(dybl);

        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
        #bxnmbTable input{width: 50px;}
    </style>
</head>
<body>
<html:form action="/sxzzjy_bjxfjssq" method="post"  styleId="form">
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <html:hidden property="sqid" styleId="sqid" />
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr>
                <th colspan="4" style="text-align: left">
                    班级信息
                </th>

            </tr>

            <tr style="">
                <th width="14%">
                    <span style="color: red">*</span> 班级
                </th>
                <td colspan="3">
                    ${map.bjmc}
                    <input type="hidden" id="bjmc" name="bjmc" value="${map.bjmc}"/>
                    <html:hidden property="bjdm" styleId="bjdm" />
                </td>
            </tr>
            <tr style="">
                <th width="14%">
                    学院
                </th>
                <td width="36%" id="xyTd">
                        ${map.xymc}
                </td>
                <th width="14%">
                    班级人数
                </th>
                <td width="36%" id="bjrsTd">
                    共${map.bjzrs}人（男${map.nansrs}人，女${map.nvsrs}人）

                </td>
            </tr>
            <tr>
                <th >
                    党员数
                </th>
                <td  id="dysTD">
                    ${map.dyrs}
                </td>
                <th >
                    党员比例
                </th>
                <td  id="dyblTD">

                </td>

            </tr>
            <tr>
                <th >
                    辅导员
                </th>
                <td  id="fdyTD">
                    ${map.fdyxm}
                </td>
                <th >
                    班主任
                </th>
                <td  id="bzrTD">
                        ${map.bzrxm}
                </td>

            </tr>
            <tr>
                <th >
                    班长
                </th>
                <td  id="bzTD">
                        ${map.bzxm}
                </td>
                <th >
                    团支书
                </th>
                <td  id="tzsTD">
                        ${map.tzsxm}
                </td>

            </tr>
            <tr style="">
                <th colspan="4"  style="text-align: left">
                    班级学风建设
                </th>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span>名 称
                </th>
                <td colspan="3">
                    <html:text property="xfjsmc" styleId="xfjsmc" style="width:60%;" />
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span>申报类型
                </th>
                <td >
                    <html:select property="sblx" styleId="sblx" style="width:150px" styleClass="select">
                        <html:option value=""></html:option>
                        <html:options collection="sblxList" property="sblxdm"
                                      labelProperty="sblxmc" />
                    </html:select>
                </td>
                <th >
                    学年学期
                </th>
                <td>
                        ${xnxq}
                    <html:hidden property="xn" styleId="xn"/>
                    <html:hidden property="xq" styleId="xq"/>
                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>本学年目标
                </th>
                <td   colspan="3">
                    <table id="bxnmbTable">
                        <tr>
                            <th>班级平均学分积</th>
                            <td width="20%"><html:text property="pjxfj" styleId="pjxfj"/></td>
                            <td colspan="2">班级学分积在年级（专业）
                                <html:text property="njzy" styleId="njzy"/>的
                                <html:text property="zyxbgs" styleId="zyxbgs"/>个小班中排名第
                                <html:text property="pjxfjpm" styleId="pjxfjpm"/>。
                            </td>
                        </tr>
                        <tr>
                            <th>班级英语四级通过率</th>
                            <td><html:text property="sjtgl" styleId="sjtgl"/></td>
                            <td colspan="2">班级英语四级通过率在年级
                                <html:text property="njxbgs" styleId="njxbgs"/>个小班中排名第
                                <html:text property="sjtglpm" styleId="sjtglpm"/>。
                            </td>
                        </tr>
                        <tr>
                            <th>不及格门次</th>
                            <td><html:text property="bjgmc" styleId="bjgmc"/>门</td>
                            <th>不及格人数</th>
                            <td><html:text property="bjgrs" styleId="bjgrs"/>人</td>
                        </tr>
                        <tr>
                            <th>不及格人次</th>
                            <td><html:text property="bjgrc" styleId="bjgrc"/>人次</td>
                            <th>班干部学习成绩前五名</th>
                            <td><html:text property="bgbqwrs" styleId="bgbqwrs"/>人</td>

                        </tr>
                        <tr>
                            <th>班干部学习成绩前十名</th>
                            <td><html:text property="bgbqsrs" styleId="bgbqsrs"/>人</td>
                            <th>获奖学生</th>
                            <td><html:text property="hjxsrs" styleId="hjxsrs"/>人</td>
                        </tr>
                        <tr>
                            <th>获集体奖</th>
                            <td><html:text property="hjtjgs" styleId="hjtjgs"/>个</td>
                            <th>社会实践获奖</th>
                            <td><html:text property="shsjhjrc" styleId="shsjhjrc"/>人次</td>
                        </tr>
                        <tr>
                            <th>宿舍获奖</th>
                            <td><html:text property="sshjcs" styleId="sshjcs"/>次</td>
                            <th>组织全班集体活动</th>
                            <td><html:text property="qbjthdcs" styleId="qbjthdcs"/>次</td>
                        </tr>
                        <tr>
                            <th>科技学术获奖</th>
                            <td><html:text property="kjxshjrc" styleId="kjxshjrc"/>人次</td>
                            <th>组织班级同学参加校院活动</th>
                            <td><html:text property="cjxyhdcs" styleId="cjxyhdcs"/>次</td>
                        </tr>
                        <tr>
                            <th>降级同学</th>
                            <td><html:text property="jjtxrs" styleId="jjtxrs"/>名</td>
                            <th>试读学生</th>
                            <td><html:text property="sdxsrs" styleId="sdxsrs"/>名</td>
                        </tr>
                        <tr>
                            <th>退学</th>
                            <td colspan="3"><html:text property="txrs" styleId="txrs"/>人</td>
                        </tr>
                    </table>
                </td>
            </tr>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>建设思路和计划<br>
                    <span style="color: red">(限500字)</span>
                </th>
                <td   colspan="3">
                    <html:textarea property="jssl" style="width:98%;margin-top:5px" rows="5"
                                   onblur="checkLen(this,500);" styleId="jssl"
                    ></html:textarea>
                </td>
            </tr>
            <tr>
                <th>
                    附件
                </th>
                <td colspan="3">
                    <html:hidden property="fjid" styleId="fjid" />
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
                                elementid : 'fjid',

                                eid : 'filepath_f'
                            });
                        });
                    </script>
                </td>
            </tr>
            </tbody>


        </table>
    </div>
</html:form>
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"为必填项
                </div>
                <div class="btn">
                    <button type="button" type="button" onclick="saveForm_update('save');">
                        保存草稿
                    </button>

                    <button type="button" type="button" onclick="saveForm_update('submit');">
                        提交申请
                    </button>

                    <button type="button" type="button" onclick="iFClose();">
                        关 闭
                    </button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
</body>
</html>

