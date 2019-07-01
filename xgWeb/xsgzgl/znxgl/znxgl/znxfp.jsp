<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="comm/editor/kindeditor.js"></script>
		<script language="javascript" src="comm/editor/zh_CN.js"></script>
		<script language="javascript" src="comm/editor/editor.js"></script>
		<script language="javascript" src="xsgzgl/znxgl/znxgl/js/znxgl.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<link rel="stylesheet" href="<%= stylePath%>/css/public.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<%=stylePath %>/css/module.css" type="text/css" media="all" />
		<script language="javascript">
			jQuery(function(){
		    })
		    //ѡ�������
		    function seljsr(){
                    var teaArr = new Array();
                    var flag = jQuery("#teagroup input").length;
                    if(flag != 0){
                    	jQuery.each(jQuery("#teagroup input"),function(i,n){
                			var zgh = jQuery(n).val();
                			teaArr.push(zgh);
                	    });
                    }
                     url = "wdznx.do?method=getTea&teaArr="+teaArr;
                     title = "��ʦ";
                showDialog(title, 800, 550, url);
                return false;
			}
			//ɾ��������
			function deljsr(){
				
			}
		</script>
		</head>
	<body>
		<html:form action="/znxgl"  method="post" styleId="ZnxglForm">
		<input type="hidden" name="xjbh" id="xjbh" value="${xjbh}"/>
		<div class="tab" style="width:100%;overflow-x:hidden;overflow-y:auto;">
			<table width="100%" border="0" class="formlist">
				<tbody>
					<tr>
						<th width="25%">
							�������
						</th>
						<td colspan="3">
							${ztlb}
						</td>
					</tr>
					<tr>
						<th width="25%">
							<font color="red">*</font>�ռ���
						</th>
						<td colspan="3">
						    <%--10λ������֮���������...����--%>
							<input type="text" name="jsrxm" id="jsrxm" readonly="readonly" style="width:500px;" value="" />
							<button class="btn_01" id="selsjr" onclick="seljsr();" type="button">ѡ��</button>
							<!-- 
							<button class="btn_01" id="delsjr" onclick="deljsr();" type="button">ɾ��</button> -->
						</td>
					</tr>
					<tr>
						<th width="25%"><font color="red">*</font>�ż�����</th>
						<td colspan="3">
							<html:text property="xjzt" maxlength="50" styleId="xjzt" value="${xjzt}"
									style="width:650px;"></html:text>
						</td>
					</tr>
					<tr>
						<th width="25%">
							<font color="red">*</font>��������
						</th>
						<td colspan="3">
							<textarea id="editorid" name="editorid" style="width:700px;height:280px;">
							  ${fsnr}
							</textarea>
					    </td>
					</tr>
				</tbody>
			</table>
			<%-- �����˴洢������--%>
			<div id="ycyz" style="display:none" >
				<div style="display:none" id="teagroup"></div>
			</div>
			<div style="display:none" id="deldiv">
				<div id="showtea"></div>
			</div> 
		</div>
		<table width="100%" border="0" class="formlist" style=" margin-bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="���� " onclick="saveXjForm();return false;">
									����
								</button>
								<button type="button" name="�ر�" onclick="iFClose();">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		</html:form>
	</body>
</html>