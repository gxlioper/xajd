<%@ page language="java" contentType="text/html; charset=GBK"%>
<style>
<!--
 .searchtab table td,.searchtab table th{
word-break : normal
}
-->
</style>

<jsp:directive.page import="java.util.*" />
<input type="hidden" id="shztStr" value="${shztStr}"/>
<% 
 		String[] shztArr = (String[])request.getAttribute("shztArr");
 		List<HashMap<String,String>> rs = 
 			(List<HashMap<String,String>>)request.getAttribute("xtgwidList"); 
 			for(int i=0; i<rs.size(); i++){
 			HashMap map = rs.get(i);
 			request.setAttribute("map",map);
 				if(i%3 == 0){				  					
 					 if(i != 0){ 
 	%>
					</tr>
 	<%			 	
 	 	
 					 }
 	%>
 					 	<tr>
						<th>${map.cn}</th>
						<td>
							<input type="hidden" name="xtgwidArr" value="${map.en}"/>
							<select name="shztArr">
								<option value=""></option>
								<logic:iterate id="v" name="shList">
									<option value="${v.en}">${v.cn}</option>
								</logic:iterate>														
							</select>
						</td>
	<%
 					 	if(rs.size()%3 !=0  && i == rs.size()-1){
 					 		for (int m = 0 ; m < 3-rs.size()%3 ; m++){
 	%>						
 					 		<th></th><td></td>
	<%
 					 		}
 					 	}
 					 
 				}else{
 	%>
 					<th>${map.cn}</th>
				<td>
					<input type="hidden" name="xtgwidArr" value="${map.en}"/>
					
					<select name="shztArr">
						<option value=""></option>
						<logic:iterate id="v" name="shList">
							<option value="${v.en}">${v.cn}</option>
						</logic:iterate>	
					</select>
				</td>
	<%
 					if(rs.size()%3 !=0  && i == rs.size()-1){
 						
   %>
 					 		<th></th><td></td>
	<%
 					 	}
 				}
 			}					  					 		
 	%>
<script>
	var shztStr = val('shztStr').split(",");
	for(var i=0; i<shztStr.length; i++){
		if(document.getElementsByName('shztArr') && document.getElementsByName('shztArr')[i] != null){
			document.getElementsByName('shztArr')[i].value = shztStr[i];
		}
	}
</script>