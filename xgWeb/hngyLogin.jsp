<%@ page language="java" contentType="text/html; charset=GBK" %>
<%@ page  import="thauth.*"%>
<%@ page  import="java.util.Hashtable"%>
<%@ page  import="xgxt.base.UserLoginCheck"%>
<jsp:directive.page import="java.util.HashMap"/>

<%
				UserLoginCheck userCheck = new UserLoginCheck();
 				String APPID="ZFXG";
                String strUserIp=request.getRemoteAddr() ;// ���IP
                String ticket =request.getParameter("bill");//���ܵ��Ż�������Ʊ��
                Hashtable ht = Roam.thauthCheckTicket(ticket,APPID,strUserIp);//������֤��������֤Ʊ���Ƿ���Ч
                if(ht==null){
				%>
				<jsp:forward page="login.jsp"/> <%--   Ҫ��ת���Ĵ�����ҳ��,�޸�Ϊ��Ӧ��֤ϵͳ�ĵ�¼ҳ��    --%>
				
				<%}
                 int code = ((Integer)ht.get(ThauthConst.THAUTH_CODE)).intValue();//��ȡ���ؽ���е�code��ֵ
                 if(code == 0){//�ж�code��ֵ�������0��˵�����γɹ�
                    
                        //J0000����ְ�̹���
						//J0054�����˽̹���
						//X0011����ʿ����
						//X0021��˶ʿ����
						//X0031����������
						//H0000����ͬ��Ա��
						//TMNIC�����������û���
						//TMOAS���칫�Զ���ϵͳ�û���
						//TMTEA����ʱ��ʦ��
                        String yhlb = (String)ht.get(ThauthConst.THAUTH_YHLB);
                        if(yhlb.equalsIgnoreCase("J0000") || yhlb.equalsIgnoreCase("J0054") || yhlb.equalsIgnoreCase("H0000") || yhlb.equalsIgnoreCase("TMNIC") || yhlb.equalsIgnoreCase("TMOAS") || yhlb.equalsIgnoreCase("TMTEA") ){
                            ht.put(ThauthConst.THAUTH_YHLB,new String("teacher"));
                        } else if(yhlb.equalsIgnoreCase("X0011") || yhlb.equalsIgnoreCase("X0021") || yhlb.equalsIgnoreCase("X0031")){
                            ht.put(ThauthConst.THAUTH_YHLB,"student");
                        }
                        HashMap<String,String> resultMap = userCheck.setAllLoginInfo(request,ht);
                        String errorMsg = resultMap.get("errorMsg");
                        if(errorMsg != null){%>
						<script type="text/javascript">                           
						    alert(<%= errorMsg %>);
						    window.location.href="login.jsp";
						</script>
                        <% } else {
								response.sendRedirect("main1.jsp");                        
                        }
 
                 }else{//������β��ɹ�����ת��һ��������ҳ��
				%>
				
				    <jsp:forward page="login.jsp"/> <%-- Ҫ��ת���Ĵ�����ҳ��,�����޸�Ϊ��Ӧ��֤ϵͳ�ĵ�¼ҳ��--%>
				<%
                 }
         
         
         //    ��login.jsp�ĳɸ��Ż�������ҳ�棺window.location.href = 'index.jsp';
%>



