<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
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
	<script type="text/javascript" src="xsgzgl/xyfd/jzgzwh/gzalwh/js/gzal.js"></script>
	<script type="text/javascript">
        function saveGzjl() {
            var checkId = 'gzsj-gzdd-yzgz-xybjy';
            var xybjy = jQuery("#xybjy").val();
            if(xybjy=='06'){
                checkId += '-jtcs';
			}
            if(!checkNotNull(checkId)){
                showAlertDivLayer("请将必填项填写完整！");
                return false;
            }
            var url = 'xyfd_gzaljl.do?method=addGzjl&type=save';
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }
        function changeJy() {
			var xybjy = jQuery("#xybjy").val();
			if(xybjy=='06'){
			    jQuery("#jtcs_tr").show();
			}else {
                jQuery("#jtcs_tr").hide();
			}
        }
	</script>
</head>
<body style="width:100%">
<html:form action="/xyfd_gzaljl" method="post" styleId="demoForm">
	<input type="hidden" id="albh" name="albh" value="${model.jdh}"/>
	<div style='width:100%;height:300px;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
					<span>工作记录</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="15%"><span class="red">*</span>工作时间</th>
				<td width="20%">
					<input type="text" id="gzsj" name="gzsj" onclick="showCalendar('gzsj','yyyy-MM-dd HH:mm');" readonly="readonly"/>
				</td>
				<th width="15%"><span class="red">*</span>工作地点</th>
				<td width="50%">
					<input type="text" id="gzdd" name="gzdd" style="width: 100%"/>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>已做工作</th>
				<td colspan="3">
					<textarea name="yzgz" id="yzgz" style="width: 100%;resize:none;" ></textarea>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>下一步建议</th>
				<td colspan="3">
					<html:select property="xybjy" styleId="xybjy" onchange="changeJy()">
						<html:options collection="jyList" property="jydm"
									  labelProperty="jymc" />
					</html:select>
				</td>
			</tr>
			<tr id="jtcs_tr" style="display: none">
				<th><span class="red">*</span>具体措施</th>
				<td colspan="3">
					<textarea name="jtcs" id="jtcs" style="width: 100%;resize:none;" ></textarea>
				</td>
			</tr>
			</tbody>
		</table>
	<div style="position:fixed;bottom:0;width: 100%">
		<table width="100%" border="0" class="formlist">
			<tfoot>
			<tr>
				<td colspan="4" >
					<div class="bz">"<span class="red">*</span>"为必填项</div>
					<div class="btn">
						<button type="button" type="button" onclick="saveGzjl();return false;" >
							保存记录
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
	</div>
</html:form>
</body>
</html>

 