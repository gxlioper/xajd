<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getXmlgSzdwDAO.js'></script>
	<script>
		function save(url){
			var dfs = document.getElementsByName("df");
			for(var i = 0; i < dfs.length; i++){
				if(""==dfs[i].value||dfs[i].value==null){
					alert("�÷����Ϊ�գ���ȷ�ϣ�");
					return false;
				}
			}
			if(confirm("�Ƿ�ȷ������?")){
				refreshForm(url);
			}
		}
		function checkValue(obj,ind){
			checkInputNum(obj);
			var fzqj = document.getElementById("fzqj_"+ind).value;

			if(fzqj!=null){
				var fzqjarr = fzqj.split('-');
				if(fzqjarr.length == 1){
					if(obj.value !="" &&( eval(obj.value)<0 || eval(obj.value)>eval(fzqjarr[0]))){
						obj.value="";
						alertInfo("�������ֵ�����ڷ�ֵ�����ڣ�");
						obj.focus();
					}
				}else if(fzqjarr.length == 2){
					var fzDown = fzqjarr[0];
					var fzUp = fzqjarr[1];
					if(obj.value !="" &&( eval(obj.value)<0 ||  eval(obj.value)>fzUp ||  eval(obj.value)<fzDown)){
						obj.value="";
						alertInfo("�������ֵ�����ڷ�ֵ�����ڣ�");
						obj.focus();
					}
				}
			}
		}
	</script>
</head>
<body onload="">
	<html:form action="/bjlh_fdykh" method="post">
		<html:hidden property="khbid" name="rs" styleId="khbid"/>
		
		<div class="tab">
		<table class="formlist" >
			<thead>
				<tr>
					<th colspan="5"><span>����Ա������${rs.khbmc}</span></th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<td colspan="5">
					ѧ�꣺${rs.xn }
					&nbsp;&nbsp;
					 ���ڲ��ţ�${rs.bmmc }
					&nbsp;&nbsp;
					 ����Ա������${rs.xm }
					<input type="hidden" id="fdyid" name="fdyid" value="${rs.fdyid}"/>
				</td>
			</tr>
			</tbody>
			<thead>
			<tr>
				<th>һ��ָ��</th>
				<th>����ָ��</th>
				<th>��������</th>
				<th>��ֵ</th>
				<th>�÷�</th>
			</tr>
			</thead>
			<tbody>
			<logic:present name="xmList">
			<logic:iterate id="xm" name="xmList" indexId="ind"> 
				<tr>
					<logic:notEmpty name="xm" property="yjzbRowNum">
						<td width="110px" style="word-break:break-all"  rowspan="${xm.yjzbRowNum}">${xm.yjzb }</td>
					</logic:notEmpty>
					
					<td width="110px" style="word-break:break-all" >${xm.ejzb }</td>
					<td width="270px" style="word-break:break-all" >${xm.khnr }</td>
					<td width="55px">${xm.fzqj }</td>
					<input type="hidden"  id="fzqj_${ind}" value="${xm.fzqj }"/>
					<td width="55px">
						<input type="hidden" name="xmid" value="${xm.xmid }"/>
						<logic:equal name="doType" value="view">
							${xm.df }
						</logic:equal>
						<logic:notEqual name="doType" value="view">
							<input type="text" id="df" name="df" maxlength="5" style="width: 25px"
							onblur="checkValue(this,${ind})" value="${xm.df}"/>
							<font class="red">*</font>
			          	</logic:notEqual>
					</td>
				</tr>
			</logic:iterate>
			</logic:present>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="5">
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			            <button type="button" name="����" id="buttonSave" onclick="save('/xgxt/bjlh_fdykh.do?method=fdykhJskhpf&doType=save');">����</button>
			           </logic:notEqual>
			            <button type="button" name="�ر�" id="buttonClose" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<script defer>
			alertInfo('${message}',
			function() {
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			});
		</script>
	</logic:present>
</body>
</html>
