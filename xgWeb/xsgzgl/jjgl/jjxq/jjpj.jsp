<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
</head>
<body style="width: 100%">
<html:form action="/jjgl_jjxq" method="post" styleId="jjxqForm" onsubmit="return false;">
	<input type="hidden" id="xqid" name="xqid" value="${pjxx.xqid}"/>
	<input type="hidden" id="pjid" name="pjid" value="${pjxx.pjid}"/>
	<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
					<span>家教评价</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="18%">家教编号</th>
				<td width="82%" colspan="3">
						${pjxx.xqid}
				</td>
			</tr>
			<logic:notEmpty name="pjxx" property="pjsj">
				<tr>
					<th width="18%">评价时间</th>
					<td width="82%" colspan="3">
						${pjxx.pjsj}
					</td>
				</tr>
			</logic:notEmpty>
			<tr>
				<th width="18%">评分</th>
				<td width="82%" colspan="3">
						<input id="pjzs" type="number" max="10" min="0" step="1" maxlength="2" onkeyup="checkInputData(this)" value="${pjxx.pjzs}"/>分
					（满分10分）
				</td>
			</tr>
			<tr>
				<th>
					<span class="red">*</span>对家长评价
					<br />
					<font color="red">(限制在100字内)</font>
				</th>
				<td colspan="3">
					<textarea id="py" name="py" rows="4" style="width:99%;" oninput="chCount(this,0,100)">${pjxx.py}</textarea>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<div style="height:35px"></div>
	<div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
			<tfoot>
			<tr>
				<td colspan="4">
					<div class="bz">
						"<span class="red">*</span>"为必填项
					</div>
					<div class="btn">
						<button type="button" onclick="jjpjSave();">
							保存
						</button>
						<button type="button" onclick="iFClose();">
							关闭
						</button>
					</div>
				</td>
			</tr>
			</tfoot>
		</table>
	</div>
</html:form>

<script type="text/javascript">
    /**
     * 家教评价的保存
     */
    function jjpjSave(){
        var pjid = jQuery("#pjid").val();
        var xqid = jQuery("#xqid").val();
        var pjzs = jQuery("#pjzs").val();
        var py = jQuery("#py").val();
        if (py == ""|| pjzs == ""){
            showAlert("请将必填项填写完整！");
            return false;
        }

        if (py.length > 100){
            showAlert("对家长评价请控制在100字以内！");
            return false;
        }

        var url = "jjgl_jjxq.do?method=jjpjSave";

        jQuery.post(url, {
            pjid:pjid,
            xqid : xqid,
			pjzs:pjzs,
			py:py
        }, function(data) {
            if(data["message"]=="保存成功！"){
                showAlert(data["message"],{},{"clkFun":function(){
                    iFClose();
                }});
            }else{
                showAlert(data["message"]);
            }
        }, 'json');
    }
</script>
</body>
</html>

