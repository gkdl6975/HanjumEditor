<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
   <div class="container">
     <a class="navbar-brand" href="home"><span>한줌</span>에디터</a>
     <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
       <span class="oi oi-menu"></span> Menu
   </button>

   <div class="collapse navbar-collapse" id="ftco-nav">
       <ul class="navbar-nav ml-auto">
         <li class="nav-item <%if(request.getServletPath().contains("index")){ %>active<% } %>"><a href="home" class="nav-link">Home</a></li>
         <li class="nav-item <%if(request.getServletPath().contains("intro")){ %>active<% } %>"><a href="intro" class="nav-link">About</a></li>
         <li class="nav-item <%if(request.getServletPath().contains("user")){ %>active<% } %>"><a href="My.uo" class="nav-link">My</a></li>
         <li class="nav-item <%if(request.getServletPath().contains("project")){ %>active<% } %>"><a href="ProjectList.bo" class="nav-link">Project</a></li>
         <li class="nav-item <%if(request.getServletPath().contains("editor")){ %>active<% } %>"><a href="EditorList.bo" class="nav-link">Editor</a></li>
         <li class="nav-item <%if(request.getServletPath().contains("center")){ %>active<% } %>"><a href="Center.hp" class="nav-link">Center</a></li>
     </ul>
 </div>
</div>
</nav>
