   <header class="bg-white shadow">
        <nav class="container mx-auto px-4 py-4 flex justify-between items-center">
            <div class="text-2xl font-bold text-gray-800">
                JavaBlog  <c:if test="${not empty user}">${user.username}</c:if>
            </div>
            <ul class="flex space-x-6">
                <li><a href="/" class="text-gray-600 hover:text-blue-600">Home</a></li>
                <li><a href="/categories" class="text-gray-600 hover:text-blue-600">Categories</a></li>
                <li><a href="/about" class="text-gray-600 hover:text-blue-600">About</a></li>
                 <li><a href="/posts/new" class="text-gray-600 hover:text-blue-600"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-square-pen-icon lucide-square-pen"><path d="M12 3H5a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"/><path d="M18.375 2.625a1 1 0 0 1 3 3l-9.013 9.014a2 2 0 0 1-.853.505l-2.873.84a.5.5 0 0 1-.62-.62l.84-2.873a2 2 0 0 1 .506-.852z"/></svg></a></li>
                <a href="/logout" onclick="return confirm('Are you sure you want to log out?');" class="text-gray-600 hover:text-blue-600">Logout</a>
            </ul>
        </nav>
    </header>
