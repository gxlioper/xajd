<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
		
		<script	type="text/javascript">
			function expKbbb(){
				var xn = jQuery('#xn').val();
				var xq = jQuery('#xq').val();
				if(xn==""){
					alertInfo("ѧ�겻��Ϊ�գ�");
					return false;
				}
				if(xq==""){
					alertInfo("ѧ�ڲ���Ϊ�գ�");
					return false;
				}
				var id = jQuery("#id").val();
				if(id==""){
					alertInfo("��Ŀ����Ϊ�գ�");
					return false;
				}
				var url = "/xgxt/sztz.do?method=xmDy";
				url +="&id="+id;
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";	
			}
			
			function clearTjxm(){
				jQuery('#id').empty();
			}
			
			function loadTjxm(){
				var xn = jQuery('#xn').val();
				var xq = jQuery('#xq').val();
				if(xn==""){
					alertInfo("ѧ�겻��Ϊ�գ�");
					return false;
				}
				if(xq==""){
					alertInfo("ѧ�ڲ���Ϊ�գ�");
					return false;
				}
				jQuery.getJSON('sztz.do?method=getXmmc',{xn:xn,xq:xq},function(data){
					jQuery('#id').empty();
					jQuery('#id').append("<option value=''>--��ѡ��--</option>");
					if(data != null && data.length != 0){
						for(var i=0; i<data.length; i++){
							var option = "<option value=\"" + data[i].id + "\">" + data[i].xmmc + "</option>";
							jQuery('#id').append(option);
						}
					}
				});
			}
		</script>
	</head>
	<body onload="">
	
		<html:form action="/sztz" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

			<div style='width:98%;height:320px;overflow:hidden;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="8">
								<span>���ʽ������Ŀͳ��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<html:select property="xn" value="${rs.xn }" styleId="xn" onchange="clearTjxm();loadTjxm();">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
			            	</td>
			            </tr>
			            <tr>
			            	<th width="16%">
			            		<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<html:select property="xq" value="${rs.xq }" styleId="xq" onchange="clearTjxm();loadTjxm();">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
			            	</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								<font color="red">*</font>ͳ����Ŀ
							</th>
							<td width="34%" colspan="3" >
								<html:select property="id" styleId="id">
									
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8">
								<div class="btn">
									<button onclick="expKbbb();">
										����
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>

			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>