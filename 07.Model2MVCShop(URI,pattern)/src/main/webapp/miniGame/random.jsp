<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
</head>
<title>미니게임결과!</title>

<body>

<c:if test = "${rs=='부족'}">
배팅포인트가 부족합니다.<br>
현재 보유하신 포인트에 맞게 배팅해주세요.
</c:if>




<c:if test = "${rs=='정답'}">
정답은 : ${result}<br>
내 정답 : ${gameNumber} <hr>
축하드립니다 정답입니다!!<br>
${gamePoint}포인트가 추가 적립되었습니다.

</c:if>
<c:if test = "${rs=='오답'}">
정답은 : ${result}<br>
내 정답 : ${gameNumber} <hr>
아쉽습니다 오답입니다 ㅠㅠ<br>
${gamePoint*-1}포인트가 날라갔습니다.
</c:if>
<br><br>

			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="../miniGame/randomView.jsp;">다시하기</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>


</table>
</body>
</html>