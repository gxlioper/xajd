<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
			function zcwjupdateok(){
				if(frames('eWebEditor1').getHTML()){
					$('wjnr').value = frames('eWebEditor1').getHTML();
				}
			
			
			    var url ="/xgxt/zcwjupdate.do?act=update";
				document.forms[0].action = url;
				document.forms[0].submit();   
		    }	
		    
		    function updateokturn(){
		        var url2 ="/xgxt/zcwjfb.do";
		        document.forms[0].action = url2;
				document.forms[0].submit();
		    }
		</script>
	</head>
	<body>
		<html:form action="/savezcwj.do" method="post">

			<div class="tab" style="margin-top: 0px; padding-top: 0px">
				<table width="90%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>修改文件</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button onclick="zcwjupdateok();">
										提 交
									</button>
									<button onclick="updateokturn();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="15%">
								文件类型
							</th>
							<td>
								<html:select name="rs" property="wjlx">
									<html:option value=""></html:option>
									<html:options collection="wjlxList" property="lxdm" labelProperty="lxmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								文件标题
							</th>
							<td>
								<input type="text" name="wjbt" id="wjbt1" style="width:90%"
									onkeyup="value=value.replace(/[+&%#]/g,'')" maxlength="200" value="${rs.wjbt }" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th>
								文件内容
							</th>
							<td>
<%--								<html:textarea name="rs" property="wjnr" style="width:90%"--%>
<%--									rows="20" onblur="checkLen(this,2000)" />--%>
								<html:hidden property="wjnr" styleId="wjnr" name="rs"/>
								<input type="hidden" name="content1"  value="<bean:write name="rs" property="wjnr"/>"/>
								
								<iframe id="eWebEditor1" src="BatEditor" frameborder="0"
									scrolling="no" width="100%" height="350"></iframe>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>

		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("修改成功!");
                    </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("修改失败");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>





