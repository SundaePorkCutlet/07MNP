<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
</head>
<title>�̴ϰ��Ӱ��!</title>

<body>

<c:if test = "${rs=='����'}">
��������Ʈ�� �����մϴ�.<br>
���� �����Ͻ� ����Ʈ�� �°� �������ּ���.
</c:if>




<c:if test = "${rs=='����'}">
������ : ${result}<br>
�� ���� : ${gameNumber} <hr>
���ϵ帳�ϴ� �����Դϴ�!!<br>
${gamePoint}����Ʈ�� �߰� �����Ǿ����ϴ�.

</c:if>
<c:if test = "${rs=='����'}">
������ : ${result}<br>
�� ���� : ${gameNumber} <hr>
�ƽ����ϴ� �����Դϴ� �Ф�<br>
${gamePoint*-1}����Ʈ�� ���󰬽��ϴ�.
</c:if>
<br><br>

			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="../miniGame/randomView.jsp;">�ٽ��ϱ�</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>


</table>
</body>
</html>