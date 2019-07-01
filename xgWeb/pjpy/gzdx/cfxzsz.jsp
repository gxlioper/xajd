<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="js/xszzFunction.js"></script>
<body>
	<html:form action="/gzdxPjpy.do" method="post">
		<input type="hidden" name="act" value="save"/>
		<script>
			function savedata() {
				var cflb = document.getElementById('cflb').value;
				if (cflb =='' || cflb==null) {
					alert("请选择要设置的处分类别等级");
					return false;
				}
				refreshForm('pjpy_gzdx_cfxzsz.do');
			}
			
		</script>
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：评奖评优 - 参数设置 - 处分限制设置
			</div>
		</div>
		<fieldset >
			<br><br>
			<div align="center">
			<table width="100%"  class="tbstyle" >
				<thead>
					<tr >
						<td align="center" width="50%">
							提示信息：当本学年所受处分等级大于或等于您设置的处分类别等级时取消其参评资格：
							<br/>
							<html:select property="cflb" styleId="cflb" style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="cflbList" property="cflbdm" labelProperty="cflbmc"/>
							</html:select>
						</td>
					</tr>
				</thead>
				<tr >
					<td align="center" >
						<button class="button2" onclick="savedata()"
							style="width: 60px">
							保 存
						</button>
						&nbsp;&nbsp;
						<button class="button2" onclick="window.close();return false;"
							style="width: 60px">
							关 闭
						</button>
					</td>
				</tr>
			</table>
			</div>
		</fieldset>
		<logic:equal value="true" name="result">
			<script>
				alert('保存成功!');
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert('保存失败!');
				window.close();
			</script>
		</logic:equal>
		</html:form>
	</body>
