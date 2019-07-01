<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>	
		<script language="javascript" src="js/prototype-1.6.0.3.js"></script>
		<script language="javascript" src="js/moreNews.js"></script>
	</head>
	
	<body class="breakword" onload="loadchange()">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>相关新闻</a>
			</p>
		</div>
		
		<html:form method="post" action="moreNews">
			<input type="hidden" id="titName" name="titName" value="${newspart }" />
			<html:hidden  property="nspName"  styleId="nspName" value="${nspName }" />
			<!-- 选项卡 -->
			<div class="compTab" id="card" style="width: 100%">
				<div class="comp_title" id="div1">
					<ul id="ul1">
					<logic:notEmpty name="mkmc" scope="request" >
							<logic:iterate id="card" name="mkmc" scope="request" indexId="s">							
								<li id="li-${s}">
									<a href="#" onclick="changePage(this)" id="${card.dm}_a">
										<span>${card.mc}</span>
									</a>
								</li>
							</logic:iterate>						
					</logic:notEmpty>
					</ul>				
				</div>
			</div>	
			<div class="comp_title" id="div2" style="display:none">
				<ul id="ul2">
				</ul>
			</div>
			<div class="comp_title" id="div3" style="display:none">
				<ul id="ul3">
				</ul>
			</div>
			<div class="comp_title" id="div4" style="display:none">
				<ul id="ul4">
				</ul>
			</div>		
			<input type="hidden" name="go" value="a" />
            <button class="btn_cx" id="search_go" style="display:none"
            	onclick="document.forms[0].go.value='go';loadDataByFy()">
            	查 询
            </button>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">您好，${nspName }暂无相关新闻！</font> 
			 		 </logic:empty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
					 <table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0" >
										<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								 style="cursor:hand">
								
									<td nowrap>
										<li>
											<a href="newsContent.do?newsId=
												<logic:iterate id="v" name="s" offset="0" length="1">
													<bean:write name="v" />
												</logic:iterate>" target='blank'>
												<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
												</logic:iterate>
											</a>
										</li>
									</td>
								
								<logic:iterate id="v" name="s" offset="2" length="1">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
				<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
					<script type="text/javascript">
											$('choose').className="hide";
					</script>
				</logic:notEmpty>
			</div>
			<script type="text/javascript">
		var sumWidth = 0;
		var splitPositionNo = 0;
		var liDropDownArr = new Array();
		var liDropDownArr2 = new Array();
		var liDropDownArr3 = new Array();
		var liArr = ele('ul1').children;
		
		for(var j=0;;j++){
			if($('li-'+j)){
			sumWidth += $('li-'+j).getWidth();
			if(sumWidth > $('card').getWidth()-180){//确定分界li序号
				if (sumWidth/2 > $('card').getWidth()-220){
						if (sumWidth/3 > $('card').getWidth()-260){
							$("div4").style.display = '';
				         	splitPositionNo = j;
				         	for(var k=j;;k++){
				            	if($('li-'+k)){//确定
				                liDropDownArr3.push($('li-'+k));//备份li对象
				                $('li-'+k).remove();//删除原来的li对象
				             }else{
				             	if(liDropDownArr3.length>0){
		  				    	 for(var p=0;p<liDropDownArr3.length;p++){
		      						$('ul4').insert(liDropDownArr3[p]);
		     						}
		 						 }
				             	break;
				             }
				           }
						}else{
		         			$("div3").style.display = '';
				         	splitPositionNo = j;
				         	
				         	for(var k=j;;k++){
				            	if($('li-'+k)){//确定
				                liDropDownArr2.push($('li-'+k));//备份li对象
				                $('li-'+k).remove();//删除原来的li对象
				             }else{
				             	if(liDropDownArr2.length>0){
		  				    	 for(var p=0;p<liDropDownArr2.length;p++){
		      						$('ul3').insert(liDropDownArr2[p]);
		     						}
		 						 }
				             	break;
				             }
				         }
		         	}
		         } else {
		         		$("div2").style.display = '';
				         splitPositionNo = j;
				         for(var k=j;;k++){
				            if($('li-'+k)){//确定
				                liDropDownArr.push($('li-'+k));//备份li对象
				                
				                $('li-'+k).remove();//删除原来的li对象
				             }else{
				             	if(liDropDownArr.length>0){
		  	 					for(var p=0;p<liDropDownArr.length;p++){
		     					 $('ul2').insert(liDropDownArr[p]);
		     						}
		 						 }
				             	break;
				             }
				         }
		         	}
				}
			}else{
				break;
			}
		}
		
		
		
		  
		  
	</script>
		</html:form>
	</body>
</html>
