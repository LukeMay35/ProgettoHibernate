<%@ page import="it.alexandria.hibernate.model.Profilo"%>
<%@ page import="it.alexandria.hibernate.model.Messaggio"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="java.text.*"%>
<%
	Set<String> personeUniche = (Set<String>) request.getSession().getAttribute("personeUniche");
	List<Messaggio> messaggi = (List<Messaggio>) request.getSession().getAttribute("messaggi");
	String first=(String) request.getSession().getAttribute("first");
	String username=(String) request.getSession().getAttribute("username");
%>
<!DOCTYPE html>
<html lang="Italiano">

<head>
	<title>AleXandria-Messaggi</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="description" content="Messaggi">
	<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
	<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="styles/messages_styles.css">
	<meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<body>

	<!-- Msg-->
	<div class="messaging">
		<div class="inbox_msg">
			<div class="inbox_people" id="inbox_people">
				<div class="headind_msg">
					<div class="recent_heading">
						<a href="library"><i class="fa fa-arrow-left" style=" font-size : 21px; "></i></a>
						<h4>Messaggi</h4>
					</div>
				</div>
				<div class="inbox_chat scroll">
				<% for(String s : personeUniche){ %>
					<div class="chat_list active_chat" onclick="document.getElementById('<%=s%>').submit()">
					<form id="<%=s%>" method="get" action="boxmessages">
						<input type=hidden value="show_contact" name="type">
						<input type=hidden value="<%=s%>" name="mittente">
					</form>
						<div class="chat_people">
							<div class="chat_ib">
								<h5><%=s%><span class="chat_date">Recentemente</span></h5>
							</div>
						</div>
					</div>
				<% } %>
				</div>
			</div>	
				<% 
				List<Messaggio> messaggiFiltrati=messaggi.stream().filter(x->{return (x.getMittente().getUsername().equals(first)&&x.getDestinatario().getUsername().equals(username))||(x.getDestinatario().getUsername().equals(first)&&x.getMittente().getUsername().equals(username));})
				.sorted(Comparator.comparing(Messaggio::getData)).collect(Collectors.toList());
				%>
				
				
			<div class="mesgs" id="msgs">
				<div class="msgs_header">
					<div class="message_header">
						<i class="fa fa-arrow-left" onclick="showContacts()"></i>
						<h4><%=first %></h4>
					</div>
				</div>
				<div class="msg_history" id="msg_history">
				<% for(Messaggio m:messaggiFiltrati) { 
				if(m.getMittente().getUsername().equals(request.getSession().getAttribute("username"))) {%>	
					<div class="outgoing_msg">
						<div class="sent_msg">
				<% }else { %>
					<div class="incoming_msg">
						<div class="received_msg">
							<div class="received_withd_msg">
					
				<% } %>
								<p><%=m.getTesto()%> </p>
								<span class="time_date"> <%
                    										SimpleDateFormat format=new SimpleDateFormat("hh:mm | yyyy/MM/dd");
															Calendar rapr=Calendar.getInstance();
															rapr.setTime(m.getData());
															String formatted=format.format(rapr.getTime());
                    									  %>
                    <%=formatted%></span>
             <%  if(m.getMittente().getUsername().equals(request.getSession().getAttribute("username"))) {%>
							</div>
						</div>
			<% }else { %>
						</div>
					</div>
				</div>
			<% } %>
		<% } %>
				</div>
				<div class="type_msg">
					<div class="input_msg_write">
					<form method="get" action="boxmessages" id="inviamessaggio">
						<input type="hidden" name="type" value="invia_messaggio">
						<input type="hidden" name="destinatario" value="<%=first%>">
						<input type="text" name="testo" class="write_msg" placeholder="Scrivi un messaggio" />
					</form>
						<button class="msg_send_btn" type="button" onclick="document.getElementById('inviamessaggio').submit()"><i class="fa fa-paper-plane"
								aria-hidden="true"></i></button>
					</div>
				</div>
			</div>
		</div>
	</div>

	</div>
	<script type="text/javascript">
		var element = document.getElementById("msg_history");
		element.scrollTop = element.scrollHeight;
	</script>
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="styles/bootstrap4/popper.js"></script>
	<script src="styles/bootstrap4/bootstrap.min.js"></script>
	<script src="js/messages.js"></script>
</body>

</html>