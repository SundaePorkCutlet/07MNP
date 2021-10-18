<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
<title>Model2 MVC Shop</title>

<link href="/css/left.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
function listcart(){
	popWin = window.open("/purchase/listCart","popWin","left=300, top=200, width=300, height=200, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
}
</script>
</head>
<script src="//twemoji.maxcdn.com/twemoji.min.js"></script>
<script>twemoji.parse(document.body, {size: 16});</script>
<body topmargin="0" leftmargin="0">
 
<table width="100%" height="50" border="0" cellpadding="0" cellspacing="0">
  <tr>
	<td height="10"></td>
	<td height="10" >&nbsp;</td>
  </tr>
  <tr>
    <td width="800" height="30"><h2>Model2 MVC Shop</h2></td>
      <td>
      <c:if test="${ ! empty user }">
     
      아이디 : ${user.userId }
      포인트 : <fmt:formatNumber value="${user.point }" pattern="#,###" />
        <a href = "javascript:listcart();" style="font-size:12px">&#128092;</a> 장바구니
   

      </c:if>
      </td>
  </tr>
  <tr>
    <td height="20" align="right" background="/images/img_bg.gif">
	    <table width="200" border="0" cellspacing="0" cellpadding="0">
	        <tr> 
	          <td width="115">
		          <c:if test="${ empty user }">
		          		<!-- //////////////////////////////////////////////////////////////////////////////////// 
		              <a href="/loginView.do" target="rightFrame">login</a>
		              	////////////////////////////////////////////////////////////////////////////////////////// -->
		              <a href="/user/login" target="rightFrame">login</a>	
		              
		           </c:if>   
	          </td>
	          <td width="14">&nbsp;</td>
	          <td width="56">
		          <c:if test="${ ! empty user }">
		          		<!-- //////////////////////////////////////////////////////////////////////////////////// 
		              <a href="/logout.do" target="_parent">logout</a>
		              	////////////////////////////////////////////////////////////////////////////////////////// -->
		            	<a href="/user/logout" target="_parent">logout</a>  
		           </c:if>
	          </td>
	        </tr>
	      </table>
      </td>
    <td height="20" background="/images/img_bg.gif">&nbsp;</td>
  </tr>
</table>

</body>
</html>