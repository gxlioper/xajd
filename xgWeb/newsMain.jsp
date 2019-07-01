<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript" src="js/function.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/newsInfoDWR.js"></script>
<jsp:directive.page import="xgxt.DAO.DAO" />
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.HashMap" />

				<%	
      			String edition = (String)session.getAttribute("edition"); 
      			if("new".equals(edition)){
      			%>		
      			<h3><span>通知公告</span><a href="newsInfo.do" ></a></h3>	
      			<div  class="newscon01" width="235" height="176"><!--有图新闻此处class命名改为：newscon-->
<%--					<div class="newspic">--%>
<%--                    	<jsp:include flush="true" page="newsPic.jsp"></jsp:include>--%>
<%--                    </div>--%>
      				<ul>
      					<%	
      						String userType = (String)session.getAttribute("userType"); 
      						String userName = (String)session.getAttribute("userName");
      						String sql = "select * from (select rownum, a.* from (select a.newsid,a.newstitle,a.newspart,b.gnmkdm, b.gnmkmc newspartmc,(substr(a.pubtime,0,10))pubtime,a.puber,a.towho from "
							+ "(select * from newscontent order by pubtime desc) a ,gnmkdmb b where a.newspart = b.gnmkdm order by a.pubtime desc ) a";
							if("stu".equalsIgnoreCase(userType)){
								sql += " where (towho='4' or towho='1')";
							}else if("xy".equalsIgnoreCase(userType)){
								sql += " where (towho='3' or towho='1')";
							}else if("xx".equalsIgnoreCase(userType)){
								sql += " where (towho='2' or towho='1')";
							}else{
								sql += " where 1=1 ";
							}
							if ("stu".equalsIgnoreCase(userType)) {
								sql+=" and exists (select 1 from yhzqxb b where zdm='6727' and a.gnmkdm=b.gnmkdm and b.gnmkdm  like 'N__' )";
							} else {
								sql+="  and exists  (select 1 from yhqxb b where b.yhm like '"+userName+"' ";
								sql+="  and b.gnmkdm like 'N__' and b.gnmkdm=a.gnmkdm) ";
							}
							sql += ") where rownum<8";
							String[] colList = new String[] { "rownum", "newsid", "newstitle",
							"newspart", "newspartmc", "pubtime", "puber" };
							DAO dao = DAO.getInstance();
							List<HashMap<String, String>> rs = dao.getList(sql, new String[] {}, colList);
							for(int i=0;i<rs.size();i++){
								HashMap<String, String> newsMap = rs.get(i);
								out.print(" <li><a href='newsContent.do?newsId=");
								out.print(newsMap.get("newsid"));
								String newstitle = newsMap.get("newstitle");
								out.print("' target='blank' title='"+newstitle+"'>");
								if(newstitle.length()>35){
									newstitle = newstitle.substring(0,35)+"......";
								}
								
								out.print(newstitle);
								out.print("</a>");
								if(i<3){
								out.print("<span class='new'style='width:30px'> </span>");
								}
								out.print(" <span class='time'>");
								out.print(newsMap.get("pubtime"));
								out.print("</span></li>");
							}
							
							//小于7行自动补齐
							if(rs.size()<7){
								int len=rs.size();
								for(int i=0;i<7-len;i++){
								out.println("<li></li>");
								}
							}
      					 %>
      				</ul>
      			</div>
      			<%}else{%>
      			<h3><span>最新新闻</span><a href="#" onclick="showOpenWindow('moreNews.do')"  ></a></h3>
      			<div class="newscon01"  width="235" height="176"><!--有图新闻此处class命名改为：newscon-->
      				 <div class="newspic" style="display:none">
                    	<jsp:include flush="true" page="moreNews.jsp"></jsp:include>
                     </div>
      				<ul>
      					<%	
      						String userType = (String)session.getAttribute("userType"); 
      						String sql = "select * from (select rownum, a.* from (select a.newsid,a.newstitle,a.newspart, b.gnmkmc newspartmc,(substr(a.pubtime,0,10))pubtime,a.puber,a.towho from "
							+ "(select * from newscontent order by pubtime desc) a ,gnmkdmb b where a.newspart = b.gnmkdm order by a.pubtime desc ) a";
							if("stu".equalsIgnoreCase(userType)){
								sql += " where (towho='4' or towho='1')";
							}else if("xy".equalsIgnoreCase(userType)){
								sql += " where (towho='3' or towho='1')";
							}else if("xx".equalsIgnoreCase(userType)){
								sql += " where (towho='2' or towho='1')";
							}else{
								sql += " where 1=1 ";
							}
							sql += ") where rownum<8";
							String[] colList = new String[] { "rownum", "newsid", "newstitle",
							"newspart", "newspartmc", "pubtime", "puber" };
							DAO dao = DAO.getInstance();
							List<HashMap<String, String>> rs = dao.getList(sql, new String[] {}, colList);
							for(int i=0;i<rs.size();i++){
								HashMap<String, String> newsMap = rs.get(i);
								out.print(" <li><a href='newsContent.do?newsId=");
								out.print(newsMap.get("newsid"));
								out.print("' target='blank'>");
								String newstitle = newsMap.get("newstitle");
								if(newstitle.length()>10){
									newstitle = newstitle.substring(0,10)+"......";
								}
								
								out.print(newstitle);
								out.print("</a>");
								if(i<3){
								out.print("<span class='new'style='width:30px'> </span>");
								}
								out.print(" <span class='time'>");
								out.print(newsMap.get("pubtime"));
								out.print("</span></li>");
							}
      					 %>
      				</ul>
      			</div>
      			<%}%>