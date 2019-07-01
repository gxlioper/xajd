<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
    <style>
        tbody td {
            border: 1px solid #ddd;
        }
        thead th {
            border: 1px solid #ddd;
        }
        .fs-12{
            font-size: 12px;
        }
        .PageNext{page-break-after: always;}
    </style>
    <style media="print">
        .noprint{display:none;}
        .print{display:block;}
    </style>
</head>
<body>
<p align="center">
    <button type="button" class="btn btn-primary noprint to_print" >打印</button>
    <button type="button" class="btn btn-default noprint to_close" >关闭</button>
</p>
<div class="print">
    <logic:iterate id="l" name="printModels">
    <div class=" PageNext">
        <div style="text-align: center;font-size: 24px;"><b>西安交通大学学生缓交学费申请表</b></div>
        <table class="table table-1 fs-12" style="height: 700px;">
            <tbody>
            <tr>
                <td style="width: 60px;" align="center">姓名</td>
                <td>${l.xsjbxx.xm }</td>
                <td align="center">性别</td>
                <td>${l.xsjbxx.xb }</td>
                <td align="center"><nobr>出生年月</nobr></td>
                <td>${l.xsjbxx.csrq }</td>
                <td align="center">民族</td>
                <td>${l.xsjbxx.mzmc }</td>
            </tr>
            <tr>
                <td align="center">书院</td>
                <td>${l.xsjbxx.symc }</td>
                <td align="center">专业</td>
                <td>${l.xsjbxx.zymc }</td>
                <td align="center">班级</td>
                <td>${l.xsjbxx.bjmc }</td>
                <td align="center">学号</td>
                <td>${l.xsjbxx.xh }</td>
            </tr>
            <tr>
                <td align="center">电话</td>
                <td>${l.xsjbxx.sjhm }</td>
                <td align="center">QQ</td>
                <td>${l.xsjbxx.qqhm }</td>
                <td align="center">电子邮箱</td>
                <td colspan="3">${l.xsjbxx.dzyx }</td>
            </tr>
            <tr>
                <td colspan="2" align="center">宿舍号</td>
                <td colspan="2">${l.xsjbxx.ssbh }</td>
                <td colspan="2" align="center">生源地</td>
                <td colspan="2">${l.xsjbxx.syd }</td>
            </tr>
            <tr>
                <td colspan="2" rowspan="2">录取后接受过何种资助及资助金额</td>
                <td colspan="6">是否申请生源地助学贷款:
                    <logic:equal value="01" name="lstdForm" property="hjfs">
                        是
                    </logic:equal>
                    <logic:notEqual value="01" name="lstdForm" property="hjfs">
                        否
                    </logic:notEqual>
                    </br>申请金额:<u>${l.lstdForm.dkje}</u>（单位：元）
                </td>
            </tr>
            <tr>
                <td colspan="6">其他资助：</td>
            </tr>
            <tr>
                <td colspan="2">交通费用</td>
                <td colspan="6"><u>${l.lstdForm.jtfyje}</u>（单位：元）</td>
            </tr>
            <tr>
                <td rowspan="3" align="center"><div style="text-align: center;line-height: 28px;">家庭</br>情况</div></td>
                <td>家庭户口</td>
                <td style="width:100px;">

                </td>
                <td>家庭总人口数</td>
                <td>${l.xsjbxx.jtrs}</td>
                <td><nobr>家庭人均月收入</nobr></td>
                <td colspan="2">${l.xsjbxx.jtrjysr }（单位：元）</td>
            </tr>
            <tr>
                <td>家庭经济来源</td>
                <td colspan="4">${l.xsjbxx.jtsrly }</td>
                <td><nobr>家庭联系电话</nobr></td>
                <td>${l.xsjbxx.jtdh }</td>
            </tr>
            <tr>
                <td>家庭地址</td>
                <td colspan="4">${l.xsjbxx.jtdz }</td>
                <td>邮政编码</td>
                <td>${l.xsjbxx.jtyb }</td>
            </tr>
            <tr>
                <td rowspan="${l.size}" style="text-align: center;">
                    家庭</br>成员</br>情况
                </td>
                <td>姓名</td>
                <td>年龄</td>
                <td>与本人关系</td>
                <td colspan="4">工作（或学习单位）</td>
            </tr>
            <logic:iterate id="i" name="l" property="jtcyList" >
                <tr>
                    <td>${i.cyxm}</td>
                    <td>${i.cynl}</td>
                    <td>${i.cygxmc}</td>
                    <td colspan="4">${i.cyxxdw}</td>
                </tr>
            </logic:iterate>
            <tr>
                <td rowspan="2" align="center" style="text-align: center;line-height: 32px;">申</br>请</br>缓</br>交</br>理</br>由</td>
                <td colspan="7">简述申请缓缴理由：</br>${l.lstdForm.sqly}</td>
            </tr>
            <tr>
                <td colspan="7">
                    详细说明将来采取何种方式将解决欠费问题:</br>
                    <input type="checkbox" id="optionsCheckbox3" name="optionsCheckbox" value="01" <logic:equal value="01" name="lstdForm" property="hjfs">checked="checked"</logic:equal> disabled>
                    <span>生源地国家助学贷款</span></br>
                    <input type="checkbox" id="optionsCheckbox2" name="optionsCheckbox" value="02" <logic:equal value="02" name="lstdForm" property="hjfs">checked="checked"</logic:equal> disabled>
                    <span>校园地国家助学贷款(贷款金额:8000元)</span></br>
                    <input type="checkbox" id="optionsCheckbox1" name="optionsCheckbox" value="03" <logic:equal value="03" name="lstdForm" property="hjfs">checked="checked"</logic:equal> disabled>
                    <span>学校勤工助学</span></br>
                    <input type="checkbox" id="optionsCheckbox4" name="optionsCheckbox"value="04" <logic:equal value="04" name="lstdForm" property="hjfs">checked="checked"</logic:equal> disabled>
                    <span>申请助学金</span></br>
                    <input type="checkbox" id="optionsCheckbox5" name="optionsCheckbox" value="05" <logic:equal value="05" name="lstdForm" property="hjfs">checked="checked"</logic:equal> disabled>
                    <span>家庭帮助解决</span></br>
                    <input type="checkbox" id="optionsCheckbox6" name="optionsCheckbox" value="06" <logic:equal value="06" name="lstdForm" property="hjfs">checked="checked"</logic:equal> disabled>
                    <span>其他______________________________</span>
                </td>
            </tr>
            <tr>
                <td colspan="2">缓交金额</td>
                <td colspan="6"><u>${l.lstdForm.sqhjje}</u>元学费+住宿≤8000元（超过8000元必须在报到交费处缴纳差额部分方可办理报到手续）</td>
            </tr>
            <tr>
                <td colspan="2">缓交截止时间</td>
                <td colspan="6"></td>
            </tr>
            <tr>
                <td colspan="8">
                    <span>本人承诺：我承诺以上信息真实可靠，并承诺在缓交时间前补交相关费用。</span>
                    <div style="text-align: right;">
                        <span>本人签字：<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></span></br>
                        <span>____年____月_____日</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>书院</br>审核</br>意见</td>
                <td colspan="7">
                    ${l.lstdForm.syshyj}
                    <div style="text-align: right">
                        <span>书院领导签字（盖章）：___________</span></br>
                        <span>_____年____月_____日</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>学校</br>意见</td>
                <td colspan="7">
                    ${l.lstdForm.xxshyj}
                    <div style="text-align: right">
                        <span>主管部门领导签字（盖章）：___________</span></br>
                        <span>_____年____月_____日</span>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    </logic:iterate>
</div>
<script type="text/javascript">
    jQuery(function($){
        $(document).off("click touchend", ".btn.to_print").on("click touchend", ".btn.to_print", function(e) {
            //绑定新页面打印按钮
            window.print();
        }).off("click touchend", ".btn.to_close").on("click touchend", ".btn.to_close", function(e) {
            window.location.href="about:blank";
            //绑定新页面关闭按钮
            window.close();
        });
    });
</script>
</body>
</html>