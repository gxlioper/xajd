<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		function xszdIni(){
			var cnArr=document.getElementsByName("cn");
			var enArr=document.getElementsByName("en");
			var kind=document.getElementsByName("kind");
			var len=5;
			var xszdHtml="<table width='100%' border='0' class='formlist'><tr>";
			for(i=1;i<=cnArr.length;i++){
				xszdHtml+="<td style='width:20%'>";
				//checkBox
				xszdHtml+="<input type='checkbox' name='xszdArr' id='xszd_"+(i-1)+"' value='"+enArr[i-1].value+"'";
				if(enArr[i-1].value=="xh" || enArr[i-1].value=="xm"){
						xszdHtml+=" checked ";
						xszdHtml+=" disabled ";
						
				}else{
					for(j=0;j<kind.length;j++){
						
						if(enArr[i-1].value==kind[j].value){
							xszdHtml+=" checked ";
							break;
						}
					}
				}
				xszdHtml+=">";
				xszdHtml+=cnArr[i-1].value;
				xszdHtml+="</td>";
				if(i%len==0){
					xszdHtml+="</tr><tr>";
				}
			}
			
			if(cnArr.length%len!=0){
				for(i=1;i<=len-(cnArr.length%len);i++){
					xszdHtml+="<td></td>"
				}
			}
			
			xszdHtml+="</tr></table>";
			$("xszdDiv").innerHTML=xszdHtml;
		
		}
		
		function checkAllBox(){
			var checkArr=null
			if($("checkAll").checked){
				checkArr=true;
			}else{
				checkArr=false;
			}
			var xszdArr=document.getElementsByName("xszdArr");
			for(i=0;i<xszdArr.length;i++){
				if(!xszdArr[i].disabled){
					xszdArr[i].checked=checkArr;
				}
			}
		}
		
		function turmAllBox(){
			var xszdArr=document.getElementsByName("xszdArr");
			for(i=0;i<xszdArr.length;i++){
				if(!xszdArr[i].disabled){
					xszdArr[i].checked=!xszdArr[i].checked;
				}
			}
		}
		
		
		function canCheckOne(obj){
			if(obj.id=="checkAll" && obj.checked){
				$("turnAll").checked=!obj.checked
			}else if(obj.id=="turnAll" && obj.checked){
				$("checkAll").checked=!obj.checked
			}
		}
		
		function save(){
			var cjlx=$("cjlx").value;
			refreshForm("/xgxt/xscj.do?method=kindChoose&doType=save&cjlx="+cjlx);
		}
		</script>
	</head>
	<body onload="xszdIni()">
		<html:form action="/zhcpZczf" method="post">
			<input type="hidden" name="cjlx" id="cjlx" value="${cjlx }"/>
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ѯ�ֶ���ѡ</a>
				</p>
			</div>
			<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					�빴ѡ��Ҫ��ʾ���ֶ�<br/>	
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none'"></a>
			</div>
			<div style='width:98%;height:200px;overflow-y:auto;overflow-x:hidden'>
			<div id="xszdDiv"  >
			</div>
			
			<table class="formlist" style="margin-bottom:4px">
			<thead>
				<tr>
					<td>
						<div class="bz" align="left">
								<input type="checkbox" name="checkAll" id="checkAll" onclick ="canCheckOne(this);checkAllBox()"/>ȫѡ
								<input type="checkbox" name="turnAll" id="turnAll" onclick ="canCheckOne(this);turmAllBox()"/>��ѡ
							</div>
						<div class="btn">
							
							<button type="button" id="btn_bj" onclick='save();return false;'>
								ȷ ��
							</button>
							<button type="button" id="btn_bc" onclick='Close();return false;'>
								�� ��
							</button>
						</div>
					</td>
				</tr>
			<thead>
			</table>
			</div>
			
			<logic:iterate name="topTr" id="topList" indexId="index">
				<input type="hidden" name="cn" id="cn_${index}"
					value="${topList.cn }" />
				<input type="hidden" name="en" id="en_${index}"
					value="${topList.en }" />
			</logic:iterate>
			<!--  -->
			<logic:iterate name="checkKind" id="checkKindList" indexId="index">
				<input type="hidden" name="kind" id="kind_${index}"
					value="${checkKindList.zd }" />
			</logic:iterate>
			<input type="hidden" name="message" id="message" value="${message}"/>
			<input type="hidden" name="doType" id="doType" value="${doType}"/>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

