<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script language="javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        function save(){
            showDialog("申请预约",750,530,"xlzx_zxspb.do?method=zxsPbbForXs");
            Close();
        }
        jQuery(function(){
        });
    </script>
    <style>
        h1{font-size: 18px;margin-top: 5px;}
        .concent{
            width: 96%;
            margin: 0 auto;
        }
        .concent p {
            font-size: 16px;
        }
        .formlist th{
            width: 27.5%;
        }
    </style>
</head>
<body style="width: 99%">
<html:form action="/xlzx_yysq" method="post">
<div style='width:100%;height:500px;overflow-y:auto;overflow-x:hidden'>
    <div id="cns" class="concent">
        <h1 align="center" style="" >《心理咨询须知》</h1>
        <p>${zxxzwhForm.zxxz}</p>
    </div>
</div>
    <table  border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="btn">
                    <button id="buttonSave" onclick="save();return false;">
                        确 定
                    </button>
                    <button onclick="Close();return false;">
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
