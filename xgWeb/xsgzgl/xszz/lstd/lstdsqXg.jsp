<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript"
            src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/xszz/lstd/js/lstd.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" defer="defer">

        jQuery(function(){
            jQuery("#hjfs").trigger('change');
        });
        /**
         * 验证是否存在空项
         * @param ids 要验证的控件id "-"分隔
         * @return
         */
        function check(ids){
            var id=ids.split("-");
            for(var i=0;i<id.length;i++){
                var lddm=jQuery("#"+id[i]).val();
                if(lddm==null||""==lddm){
                    return false;
                }
            }
            return true;
        }
        function xgForm(type){
            var checkId ="sqly";
            if(!check(checkId)){
                return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
            }
            var url;
            if(type == 'save'){
                url = "xszz_lstd.do?method=lstdsqXg&type="+type + "&shzt=0";
            } else {
                url = "xszz_lstd.do?method=lstdsqXg&type="+type + "&shzt=5";
            }

            ajaxSubFormWithFun("lstdForm", url, function(data) {
                showAlert(data["message"], {}, {
                    "clkFun" : function() {
                        if (parent.window) {
                            refershParent();
                        }
                    }
                });
            });
        }
    </script>
</head>
<body>
<html:form action="/xszz_lstd" method="post" styleId="lstdForm" onsubmit="return false">
    <html:hidden property="sqid"  styleId="sqid"/>
    <div style='tab;width:100%;height:650px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>申请信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>缓交方式</th>
                <td>
                    <select name="hjfs" id="hjfs" onchange="selectChange(this);">
                        <option value="01" <logic:equal value="01" name="hjfs">selected = "selected" </logic:equal>>生源地国家助学贷款</option>
                        <option value="02" <logic:equal value="02" name="hjfs">selected = "selected" </logic:equal>>校园地国家助学贷款(贷款金额:8000元)</option>
                        <option value="03" <logic:equal value="03" name="hjfs">selected = "selected" </logic:equal>>勤工助学</option>
                        <option value="04" <logic:equal value="04" name="hjfs">selected = "selected" </logic:equal>>申请助学金</option>
                        <option value="05" <logic:equal value="05" name="hjfs">selected = "selected" </logic:equal>>家庭帮助解决</option>
                        <option value="06" <logic:equal value="06" name="hjfs">selected = "selected" </logic:equal>> 其他————</option>
                    </select>
                </td>
                <th id="dkjeTh">申请贷款金额(元)</th>
                <td id="dkjeTd">
                    <html:text property="dkje" styleId="dkje" onblur="checkMoneyForKeyup(this);"/>
                </td>
            </tr>
            <tr>
                <th>交通费用金额</th>
                <td colspan="3">
                    <html:text property="jtfyje" styleId="jtfyje" onblur="checkMoneyForKeyup(this);"/>（仅限乘坐汽车、火车的单程费用）
                </td>
            </tr>
            <tr>
                <th>申请缓交金额</th>
                <td colspan="3">
                    <html:text property="sqhjje" styleId="sqhjje" onblur="checkMoneyForKeyup(this);"/>&nbsp;学费+住宿≤8000元（超过8000元必须在报到交费处缴纳差额部分方可办理报到手续）
                </td>
            </tr>
            <tr>
                <th>缓交截止时间</th>
                <td colspan="3">
                        ${jssj}
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>申请理由</th>
                <td colspan="3">
                    <html:textarea rows="5" style="width: 95%" property="sqly" styleId="sqly"/>
                </td>
            </tr>
            <tr>
                <th>附件</th>
                <td colspan="3">
                    <html:hidden property="filepath" styleId="filepath" />
                    <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
                    <script type="text/javascript">
                        //调用附件
                        jQuery(function(){
                            jQuery.MultiUploader({
                                maxcount : 3,
                                //后缀
                                accept : 'png|gif|jpg',
                                //最大文件大小 单位M
                                maxsize: 10,
                                //存放附件的隐藏域的id
                                elementid : 'filepath'
                            });
                        });
                    </script>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height:35px;" ></div>
    <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"为必填项
                </div>
                <div class="btn">
                    <button type="button" id="save_button" type="button"
                            onclick="xgForm('save');">
                        保存草稿
                    </button>
                    <button type="button" id="btn_submit" type="button"
                            onclick="xgForm('submit');">
                        提交申请
                    </button>
                    <button type="button" name="关 闭" onclick="iFClose();">
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

