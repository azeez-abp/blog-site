   <header class="bg-white shadow">
        <nav class="container mx-auto px-4 py-4 flex justify-between items-center">
            <div class="text-2xl font-bold text-gray-800">
                JavaBlog  <c:if test="${not empty user}">${user.username}</c:if>
            </div>
            <ul class="flex space-x-6">
                <li><a href="/" class="text-gray-600 hover:text-blue-600">Home</a></li>
                <li><a href="/categories" class="text-gray-600 hover:text-blue-600">Categories</a></li>
                <li><a href="/about" class="text-gray-600 hover:text-blue-600">About</a></li>
                <li><a href="/login" class="text-gray-600 hover:text-blue-600">Login</a></li>
            </ul>
        </nav>
    </header>
