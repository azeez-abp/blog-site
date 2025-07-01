<%@ include file="header.jsp" %>
<body class="bg-gray-100 min-h-screen">
    <!-- Header -->
    <%@ include file="nav.jsp" %>

    <!-- Main Content -->
    <main class="container mx-auto px-4 py-8">
        <c:if test="${not empty user}">
            <section class="bg-white p-8 rounded-lg shadow-lg max-w-2xl mx-auto">
                <h1 class="text-3xl font-bold text-gray-800 mb-6">Edit Profile</h1>
                
                <!-- Feedback Messages -->
                <c:if test="${not empty error and fn:trim(error) != ''}">
                    <div class="error bg-red-100 text-red-700 p-4 rounded-lg mb-6">${error}</div>
                </c:if>
                
                <c:if test="${not empty success}">
                    <div class="success bg-green-100 text-green-700 p-4 rounded-lg mb-6">${success}</div>
                </c:if>

                <!-- User Profile Display -->
                <div class="flex items-center mb-8">
                    <c:choose>
                        <c:when test="${not empty user.avatarUrl}">
                            <img src="/Uploads/${user.avatarUrl}" alt="User avatar" class="avatar-img mr-6" id="avatarPreview">
                        </c:when>
                        <c:otherwise>
                            <img src="https://via.placeholder.com/150" alt="Default avatar" class="avatar-img mr-6" id="avatarPreview">
                        </c:otherwise>
                    </c:choose>
                    <div>
                        <p class="text-xl font-semibold text-gray-800">Username: ${user.username}</p>
                        <p class="text-gray-600">Email: ${user.email}</p>
                        <p class="text-gray-600">Bio: <c:out value="${user.bio != null ? user.bio : 'No bio provided'}" /></p>
                        <p class="text-gray-600">Joined: ${user.createdAt}</p>
                    </div>
                </div>

                <!-- Update Bio Form -->
                <form action="/profile/edit" method="post" class="mb-8">
                    <div class="mb-4">
                        <label for="bio" class="block text-gray-700 font-medium mb-2">Bio (max 255 characters)</label>
                        <textarea id="bio" name="bio" class="form-textarea w-full p-4 border rounded-lg" rows="4" maxlength="255"
                                  oninput="updateCharCount()">${user.bio}</textarea>
                        <p id="bioCharCount" class="text-sm text-gray-500 mt-1">0/255 characters</p>
                    </div>
                    <button type="submit" class="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition">Update Bio</button>
                </form>

                <!-- Upload Avatar Form -->
                <form action="/upload" method="post" enctype="multipart/form-data" id="avatarForm">
                    <div class="mb-4">
                        <label for="avatar" class="block text-gray-700 font-medium mb-2">Upload Avatar (JPEG/PNG, max 5MB)</label>
                        <input type="file" id="avatar" name="avatar" accept="image/jpeg,image/png" class="form-input border p-2 rounded w-full">
                    </div>
                    <button type="submit" class="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition">Upload Avatar</button>
                </form>
            </section>
        </c:if>
        <c:if test="${empty user}">
            <section class="bg-white p-8 rounded-lg shadow-lg max-w-2xl mx-auto">
                <h1 class="text-3xl font-bold text-gray-800 mb-4">Access Denied</h1>
                <p class="text-gray-600">Please <a href="/login" class="text-blue-600 hover:underline">log in</a> to edit your profile.</p>
            </section>
        </c:if>
    </main>

    <!-- Footer -->
  <%@ include file="footer.jsp" %>

    <script>
        // Update bio character count
        function updateCharCount() {
            const bio = document.getElementById('bio');
            const charCount = document.getElementById('bioCharCount');
            charCount.textContent = `${bio.value.length}/255 characters`;
        }
        updateCharCount(); // Initial count

        // Preview avatar before upload
        document.getElementById('avatar').addEventListener('change', function(e) {
            const file = e.target.files[0];
            if (file && ['image/jpeg', 'image/png'].includes(file.type)) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    document.getElementById('avatarPreview').src = e.target.result;
                };
                reader.readAsDataURL(file);
            } else {
                alert('Please select a valid JPEG or PNG image.');
                e.target.value = '';
            }
        });
    </script>
</body>
</html>
```