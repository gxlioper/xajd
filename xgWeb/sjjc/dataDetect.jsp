<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/dataDetectDWR.js'></script>
		<script type="text/javascript">
			
			function dataDetect(jclx){
				var gnmklx=$("gnmklx").value;
			    var jcmkdm=$("jcmkdm").value;
			    var yclxdm=$("yclxdm").value;
			    var methodValue=$(yclxdm+"_method").value;
			    var tableName=$(yclxdm+"_tableName").value;
			    var zd=$(yclxdm+"_zd").value;
			    var query=$(yclxdm+"_query").value;
			    var pdlxone=$(yclxdm+"_pdlxone").value;
			    var pdlxtwo=$(yclxdm+"_pdlxtwo").value;
			    var pdzone=$(yclxdm+"_pdzone").value;
			    var pdztwo=$(yclxdm+"_pdztwo").value;
			    
			    var strArr=new Array();
			    strArr=[gnmklx,jcmkdm,yclxdm,methodValue,tableName,zd,query,pdlxone,pdlxtwo,pdzone,pdztwo];
				//判断检测类型
				if(jclx=="gnmkjc"){//模级检测
				
				}else if(jclx=="jcmk"){//子模块检测
				
				}else if(jclx=="jcyc"){//单个异常检测
					dataDetectDWR.dataDetect(strArr,function(data){
						$(yclxdm).innerHTML="异常数据<font color='red'>"+data+"</font>条";
						if(data>0){
							$(yclxdm+"_a").attachEvent('onclick', function(){
								goDelData();
							}); 
						}
					});
				}
			}
			
			function goDelData(){
				document.forms[0].action="/xgxt/dataDetect.do?method=disposeManage&mklx=pjpy";
				document.forms[0].submit();
			}
			
			function showTbody(obj,objTbody){
				if(obj.className=="up"){
					obj.className="down";
					obj.parentNode.parentNode.className="cur-tr";
					document.getElementById(objTbody).style.display="none";
				}else{
					obj.className="up";
					obj.parentNode.parentNode.className="";
					document.getElementById(objTbody).style.display="";
				}
			}
			
			
			function checkAll(){
				var btns = jQuery('.btn_common');
				for (var i = 0 ; i < btns.length; i++){
					btns[i].click();
				}
			}
			
			function setHiddenValue(jcmkdm,yclxdm){
				$('yclxdm').value=yclxdm;	
				$('jcmkdm').value=jcmkdm;	
			}
			
			function mkJc(jcmkdm){
				
				var mkjc=document.getElementsByName(jcmkdm);
				
				for(i=0;i<mkjc.length;i++){
					mkjc[i].click();
				}
			}
			
			function qmjc(yjjcBtn){
				
				var yjjc=document.getElementsByName(yjjcBtn);
				var num=yjjc.length;
				for(i=0;i<num;i++){
					
					setTimeout("btnClick('"+yjjc[i].id+"_gif')",'100');
				}
			}
			
			function btnClick(obj){
				mkJc(obj);
			}
		</script>

	</head>

	<body>
		<html:form action="/dataDetect">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
			</div>
			<html:hidden property="gnmklx" styleId="gnmklx" value="${mklx}"/>
			<html:hidden property="jcmkdm" styleId="jcmkdm" value="111"/>
			<html:hidden property="yclxdm" styleId="yclxdm" value=""/>
			<div class="tab">
				<div class="check_data">
					<img src="<%=stylePath%>/images/blue/ico_70.gif" />
					<div class="con">
						<h4>
							全面数据检测
						</h4>
						<h5>
							对导入的数据全方面的一个导常数据检测，包括指定数据检测的所有内容。
						</h5>
					</div>
					<a class="check_all" href="#" onclick="qmjc('yjjcBtn');">全面检测</a>
				</div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<td colspan="2">
								<span>指定数据检测</span>
							</td>
						</tr>
					</thead>
					<logic:iterate id="sjjc" name="sjjcList" indexId="index">
						<thead>
							<tr>
								<th colspan="2">
									<a href="#" class="up"
										onclick="showTbody(this,'myTbody${index}');return false">${sjjc.jcmkmc}</a>
								</th>
							</tr>
						</thead>
						<tbody id="myTbody${index}">
							
							<tr>
								<td rowspan="${sjjc.length }">
									<button class="btn_common" name="yjjcBtn" id="${sjjc.jcmkdm}" onclick="mkJc('${sjjc.jcmkdm}_gif');">
										一键检测
									</button>
									<br />
									<p style="margin-top:5px;">
										对${sjjc.jcmkmc}信息
										<br />
										进行检测
									</p>
								</td>
								<logic:iterate name="sjjc" property="jclx" id="jclxList"
									offset="0" length="1">
									<td nowrap="nowrap">
										${jclxList.yclxmc}
										<html:hidden  property="method" styleId="${jclxList.yclxdm}_method" value="${jclxList.method}"/>
										<html:hidden  property="tableName" styleId="${jclxList.yclxdm}_tableName" value="${jclxList.tablename}"/>
										<html:hidden  property="zd" styleId="${jclxList.yclxdm}_zd" value="${jclxList.zd}"/>
										<html:hidden  property="query" styleId="${jclxList.yclxdm}_query" value="${jclxList.searchtj}"/>
										<html:hidden  property="pdzone" styleId="${jclxList.yclxdm}_pdzone" value="${jclxList.pdzone}"/>
										<html:hidden  property="pdztwo" styleId="${jclxList.yclxdm}_pdztwo" value="${jclxList.pdztwo}"/>
										<html:hidden  property="pdlxone" styleId="${jclxList.yclxdm}_pdlxone" value="${jclxList.pdlxone}"/>
										<html:hidden  property="pdlxtwo" styleId="${jclxList.yclxdm}_pdlxtwo" value="${jclxList.pdlxtwo}"/>
										<a href="#" class="but_show"  style="cursor:hand" id="${jclxList.yclxdm}_gif" name="${jclxList.jcmkdm}_gif"
										onclick="setHiddenValue('${jclxList.jcmkdm}','${jclxList.yclxdm}');dataDetect('jcyc');return false;"> <img
											src="<%=stylePath%>/images/blue/ico_59.gif" /></a>
									
										<a href="#" class="but_show"  style="cursor:hand" id="${jclxList.yclxdm}_a" name="${jclxList.jcmkdm}_a"
											>  <span id="${jclxList.yclxdm}" class="show_data" ></span> </a>
									</td>
								</logic:iterate>
							</tr>
							<logic:iterate name="sjjc" property="jclx" id="jclxList"
								offset="1">
								<tr>
								<td nowrap="nowrap">
									${jclxList.yclxmc}
									<html:hidden  property="method" styleId="${jclxList.yclxdm}_method" value="${jclxList.method}"/>
										<html:hidden  property="tableName" styleId="${jclxList.yclxdm}_tableName" value="${jclxList.tablename}"/>
										<html:hidden  property="zd" styleId="${jclxList.yclxdm}_zd" value="${jclxList.zd}"/>
										<html:hidden  property="query" styleId="${jclxList.yclxdm}_query" value="${jclxList.searchtj}"/>
										<html:hidden  property="pdzone" styleId="${jclxList.yclxdm}_pdzone" value="${jclxList.pdzone}"/>
										<html:hidden  property="pdztwo" styleId="${jclxList.yclxdm}_pdztwo" value="${jclxList.pdztwo}"/>
										<html:hidden  property="pdlxone" styleId="${jclxList.yclxdm}_pdlxone" value="${jclxList.pdlxone}"/>
										<html:hidden  property="pdlxtwo" styleId="${jclxList.yclxdm}_pdlxtwo" value="${jclxList.pdlxtwo}"/>
									<a href="#" class="but_show"  style="cursor:hand" id="${jclxList.yclxdm}_gif" name="${jclxList.jcmkdm}_gif"
										onclick="setHiddenValue('${jclxList.jcmkdm}','${jclxList.yclxdm}');dataDetect('jcyc');return false;"> <img
											src="<%=stylePath%>/images/blue/ico_59.gif" /></a>
									
									<a href="#" class="but_show"  style="cursor:hand" id="${jclxList.yclxdm}_a" name="${jclxList.jcmkdm}_a"
										>  <span id="${jclxList.yclxdm}" class="show_data" ></span> </a>
								</td>
								</tr>
							</logic:iterate>
						</tbody>
					</logic:iterate>
				</table>
			</div>
			<br />
		</html:form>
	</body>
</html>
