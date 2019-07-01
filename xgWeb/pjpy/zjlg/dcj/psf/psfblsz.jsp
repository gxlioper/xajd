<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript">
    	
     function saveBl(){
		var zpfbl = $("zpfbl").value;
		var bjfbl = $("bjfbl").value;
		var isBl = $("isBl").value;
		if(isBl == "1"){
			if(zpfbl == "" || bjfbl == "" || zpfbl==null || bjfbl==null){
				alert("自我评议分或者班级评议分比例皆不能为空，请确认！！");
				return false;
			}
		}
		if((parseFloat(zpfbl) + parseFloat(bjfbl)) > 100){
			alert("自我评议比例与班级评议分比例相加不能超过100%，请确认！！！");
			return false;
		}
		
		disBlsz();
		
		var url='pjpy_zjlg_psfblsz.do?act=save';
		$('zpfbl').disabled=false;
		$('bjfbl').disabled=false;
		refreshForm(url);
	}
	
	function disBlsz() {
		var isbl = $('isbl').value;
		if (isbl=='0') {
			$('zpfbl').disabled=true;
			$('bjfbl').disabled=true;
		} else {
			$('zpfbl').disabled=false;
			$('bjfbl').disabled=false;
		}
	}
    </script>
   </head>
	<body onload="disBlsz()">
		<html:form action="/zjlgPjpydcj" method="post">
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" value="${userName }"/>
			<input type="hidden" name="save_bzrzgh" value="${userName }"/>
			<input type="hidden" name="pkString" value="bzrzgh"/>
			
			<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>评奖评优 - 信息维护 - 德育平时分维护</a>
			</p>
			</div>
			<div class="tab">
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th height="22" colspan="2">
							<span>德育平时分比例设置</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th align="right" width="50%">
						是否启用
					</th>
					<td height="22" align="left" width="50%">
						<html:select property="save_isbl" styleId="isbl" value="${rs.isbl}" onchange="disBlsz()">
							<html:option value="1">是</html:option>
							<html:option value="0">否</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right">
						<font color="red">*</font>自我评议分所占比例
					</th>
					<td height="22" align="left">
						<html:text property="save_zpfbl" styleId="zpfbl" maxlength="5" style="width:50px" onkeyup="ckinpdata(this)" value="${rs.zpfbl}"></html:text>
						(%)
					</td>
				</tr>
				<tr>
					<th align="right">
						<font color="red">*</font>班级评议分所占比例
					</th>
					<td height="22" align="left">
						<html:text property="save_bjfbl" styleId="bjfbl"  maxlength="5" style="width:50px" onkeyup="ckinpdata(this)" value="${rs.bjfbl}"></html:text>
						(%)
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
						  <button type="button" name="提交" id="btn_save" onclick="saveBl()">提交</button>
						  <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
			<br />
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
					alert("操作成功!");
					Close();
					if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
						window.dialogArguments.document.getElementById('search_go').click();	
					}
					
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("操作失败!");
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
