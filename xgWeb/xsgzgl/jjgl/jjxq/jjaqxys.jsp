<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <title>家教安全协议书</title>
</head>

<body>
<form>
    <div class="tab" style="overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
        <input type="hidden" id="xqid" value="${jjxqForm.xqid}"/>
        <table width="100%" border="0" class="formlist">
            <thead>
                <tr>
                    <th colspan="5">
                        <h2 align="center" style="color:#565656;font-size: medium">家教安全协议书</h2>
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td colspan="5">
                        <p style="color:#181818;font-size: small">
                            甲方：西安交通大学勤工助学办公室<br/>

                            乙方：西安交通大学同学<br/>

                            一、同学经向西安交通大学勤工助学办公室申请，自愿参加勤工助学活动，并经该办公室委派，从事（家教/校外兼职）工作。<br/>

                            二、乙方在从事勤工助学期间，应遵守国家法律、法规。<br/>

                            三、乙方在从事勤工助学期间，应遵守学校各项规章制度，严禁夜不归宿。<br/>

                            四、乙方承诺从事勤工助学活动不致影响自己的学业。<br/>

                            五、乙方从事勤工助学活动应注意自身安全，防止发生安全事故。因不遵守法律法规或不按学校或者用人单位要求活动而发生的意外事故，甲方不承担责任。<br/>

                            六、乙方未经甲方许可私自从事家教或其他工作，若出现安全事故，责任自负。<br/>

                            七、发生下列情形之一的，乙方应当承担相应责任，甲方不承担责任：<br/>

                            （一）擅自从事甲乙双方协商确定范围以外的其他活动，导致损害发生的；<br/>

                            （二）个人行为导致损害发生的；<br/>

                            （三）有特异体质、特定疾病或者异常心理状态，未告知甲方导致损害发生的。<br/>

                            八、乙方在从事勤工助学活动时，应衣着得体，行为文明。<br/>

                            九、乙方在从事勤工助学过程中遇到特殊情况应及时向甲方反映，甲方应根据具体情况及时处理。否则，由此引起的一切后果，由乙方完全承担。<br/>

                            十、乙方从甲方得到的勤工助学岗位，不得擅自转让。乙方若向聘请家教的家长或者用人单位辞去工作时，应及时通知甲方。<br/>

                            十一、本协议自今日起生效。<br/>

                            十二、违约产生的一切后果由违约方自行承担。<br/>

                            十三、本协议甲、乙双方已经全文阅读，并理解无误。
                        </p>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div style="height: 50px"></div>
    <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
        <tfoot>
        <tr>
            <td colspan="5">
                <div style="display:inline-block">
                    <input type="checkbox" id="yyxys"/>
                    我已阅读并同意签订本协议
                </div>
                <div class="btn">
                    <button type="button" name="保存"  onclick="tyaqxys();return false;">
                        确定
                    </button>
                    <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
                        关 闭
                    </button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
</form>

<script type="text/javascript">
    function tyaqxys() {
        if(jQuery("#yyxys:checked").length>0){
            var xqid = jQuery("#xqid").val();
            window.location.href = "jjgl_jjxq.do?method=toXssqPage&xqid="+xqid;
        }else{
            showAlert("请勾选\"我已阅读并同意签订本协议\"");
        }
    }
</script>
</body>
</html>
