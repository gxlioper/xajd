<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <%@ include file="/syscommon/autocomplete.ini"%>
    <script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script language="javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/check.js"></script>

    <script	type="text/javascript">
        jQuery(function(){
            changeXmmc(false);
        });

        function changeXmmc(flg){
            if(flg){
                // 重置
                jQuery("#xmmc").val("");
                jQuery("#xmje").val("");
            }

            //取得数据表：xg_pjpy_new_pjxmb; 字段：xmmc
            var autoSetting = {
                dataTable:"xg_pjpy_new_pjxmb",
                dataField:"xmmc",
                dataFieldKey:"xmje",
                dataFieldKeyId:"xmje",
                sqlTj: getSqlTj,
                scrollHeight:135
            }
            // 模糊搜索下拉【项目名称】
            jQuery("#xmmc").setAutocomplete(autoSetting);
        }

        function getSqlTj(){
            var xn = jQuery("#xn").val();
            var xq = jQuery("#xq").val();
            var sqlTj = " and xn = '"+xn+"' ";
            if(xq){
                sqlTj += " and xq = '"+xq+"' ";
            }else{
                sqlTj += " and xq = 'on' ";
            }
            sqlTj += " and xzdm = '2' ";
            return sqlTj;
        }

        //加载学生信息
        function getStuInfo(){
            jQuery.ajaxSetup({async:false});
            var parameter = {};
            parameter["xh"]=escape(jQuery("#xh").val());
            var url="xpj_pjjg.do?method=addPjxmjg";
            jQuery.getJSON(url,parameter,
                function(data){
                    if(data!=null){
                        jQuery("#xm").html(data.xm);
                        jQuery("#nj").html(data.nj);
                        jQuery("#xymc").html(data.xymc);
                        jQuery("#zymc").html(data.zymc);
                        jQuery("#bjmc").html(data.bjmc);
                        jQuery("#zzmm").html(data.ldmc);
                        jQuery("#mz").html(data.qsh);
                        jQuery("#yhmc").html(data.cwh);
                        jQuery("#yhkh").html(data.qsdh);
                        changeTqs();
                    }
                }
            );
            jQuery.ajaxSetup({async:true});
        }

        //保存
        function saveForm(){
            var xh = jQuery("#xh").val();
            var xn = jQuery("#xn").val();
            var xq = jQuery("#xq").val();
            var lxdm = jQuery("#lxdm").val();
            var xzdm = jQuery("#xzdm").val();
            var xmmc = jQuery("#xmmc").val();

            if("" == xn){
                showAlert("学号不能为空");
                return false;
            }if("" == xn|| "" == lxdm|| "" == xzdm|| "" == xmmc){
                showAlert("请将申请信息当中带*号的填写完整");
                return false;
            }
            if (jQuery("#sqly").val().length > 500){
                showAlert("申请理由最大字数为500，现已经超过，请确认！！");
                return false;
            }
            var url = "bzjl_bzjg.do?method=updateBzjljg&type=update";

            ajaxSubFormWithFun("bzjljgForm",url,function(data){

                if (data["success"] == "false"){
                    var msg = "该学生在"+jQuery.trim(xn)+"学年，";
                    if (jQuery("#xq option:selected").text() != ""){
                        msg += jQuery("#xq option:selected").text() + "学期，";
                    }
                    msg += "已获得"+jQuery.trim(xmmc)+"。";
                    showAlert(msg);
                } else {
                    showAlert(data["message"],{},{"clkFun":function(){
                        if (parent.window){
                            refershParent();
                        }
                    }});
                }

            });
        }

    </script>

</head>

<body>
<html:form action="/bzjl_bzjg" method="post" styleId="bzjljgForm" onsubmit="return false;">
    <html:hidden property="id"/>
    <input type="hidden" id="cpnj" name="cpnj" value="${jbxx.nj}"/>
    <input type="hidden" id="cpxydm" name="cpxydm" value="${jbxx.xydm}"/>
    <input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
    <div style='tab;width:100%;height:480px;overflow-x:hidden;overflow-y:auto;' >
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生信息</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/viewStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>评奖项目申请信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th><span class="red">*</span>学年</th>
                <td
                        <logic:notEqual value="1" name="pjzq"> colspan="3" </logic:notEqual>>

                    <html:select  property="xn" styleId="xn" style="width:130px" onchange="changeXmmc(true);">
                        <html:options collection="xnList" labelProperty="xn" property="xn"/>
                    </html:select>
                </td>
                <logic:equal value="1" name="pjzq">
                    <th><span class="red">*</span>学期</th>
                    <td>
                        <html:select  property="xq" styleId="xq" style="width:130px" onchange="changeXmmc(true);">
                            <html:option value=""></html:option>
                            <html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
                        </html:select>
                    </td>
                </logic:equal>
            </tr>
            <tr>
                <th>项目类型</th>
                <td>
                    <html:select  property="lxdm" styleId="lxdm" style="width:130px">
                        <html:options collection="xmlxList" labelProperty="mc" property="dm"/>
                    </html:select>
                </td>
                <th><span class="red">*</span>项目性质</th>
                <td>
                    <html:hidden property="xzdm"></html:hidden>
                    <logic:equal name="xzdm" value="2">
                        表彰奖励
                    </logic:equal>
                    <logic:notEqual name="xzdm" value="2">
                        奖学金
                    </logic:notEqual>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>项目名称</th>
                <td>
                    <html:text  property="xmmc" styleId="xmmc"  style="width:180px;" maxlength="20"  styleClass="text_nor"></html:text>
                </td>

                <th>金额</th>
                <td>
                    <html:text  property="xmje" styleId="xmje"  style="width:120px;" maxlength="10"  styleClass="text_nor" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
                </td>
            </tr>
            <tr>
                <th>
                    附件信息
                </th>
                <td colspan="3">
                    <html:hidden property="ylzd5" styleId="ylzd5" />
                    <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
                    <script type="text/javascript">
                        //调用附件
                        jQuery(function(){
                            jQuery.MultiUploader({
                                maxcount : ${xxdm=='12713'?10:3},
                                //后缀
                                accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
                                //最大文件大小 单位M
                                maxsize: ${xxdm=='12713'?30:10},
                                //存放附件的隐藏域的id
                                elementid : 'ylzd5'
                            });

                        });
                    </script>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>申请时间
                </th>
                <td colspan="3">
                    <html:text property="sqsj" styleId="sqsj" onclick="return showCalendar('sqsj','yyyy-MM-dd HH:mm:ss');" readonly="true" ></html:text>
                </td>
            </tr>
            <tr>
                <th>
                        申请理由
                    <br /><font color="red">(限500字)</font>
                </th>
                <td colspan="3">
                    <html:textarea property='sqly' style="width:98%" styleId="sqly" rows='8' onblur="checkLen(this,500);"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <table  width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">"<span class="red">*</span>"为必填项</div>
                <div class="btn">
                    <button type="button" type="button" onclick="saveForm();">
                        保 存
                    </button>
                    <button type="button" type="button" onclick="iFClose();">
                        关 闭
                    </button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
</html:form>
</body>
</html>

