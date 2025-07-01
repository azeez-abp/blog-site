<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <style>
        body {
            font-family: 'Inter', sans-serif;
        }
        .form-input, .form-textarea {
            transition: border-color 0.3s ease;
        }
        .form-input:focus, .form-textarea:focus {
            border-color: #2563eb;
            outline: none;
        }
        .error, .success {
            transition: opacity 0.3s ease;
        }
    </style>
</head>
<body class="bg-gray-100 min-h-screen">
    <!-- Header -->
    <header class="bg-white shadow sticky top-0 z-10">
        <nav class="container mx-auto px-4 py-4 flex justify-between items-center">
            <div class="text-2xl font-bold text-gray-800">JavaBlog</div>
            <ul class="flex space-x-6">
                <li><a href="/" class="text-gray-600 hover:text-blue-600">Home</a></li>
                <li><a href="/dashboard" class="text-gray-600 hover:text-blue-600">Dashboard</a></li>
                <li><a href="/profile/edit" class="text-gray-600 hover:text-blue-600">Profile</a></li>
                <li><a href="/logout" class="text-gray-600 hover:text-blue-600">Logout</a></li>
            </ul>
        </nav>
    </header>

    <!-- Main Content -->
    <main class="container mx-auto px-4 py-8">
        <c:if test="${not empty user}">
            <section class="bg-white p-8 rounded-lg shadow-lg max-w-3xl mx-auto">
                <h1 class="text-3xl font-bold text-gray-800 mb-6">Create New Post</h1>
                
                <!-- Feedback Messages -->
                <c:if test="${not empty error}">
                    <div class="error bg-red-100 text-red-700 p-4 rounded-lg mb-6">${error}</div>
                </c:if>
                <c:if test="${not empty success}">
                    <div class="success bg-green-100 text-green-700 p-4 rounded-lg mb-6">${success}</div>
                </c:if>

                <!-- Create Post Form -->
                <form action="/posts/new" method="post">
                    <div class="mb-4">
                        <label for="title" class="block text-gray-700 font-medium mb-2">Post Title</label>
                        <input type="text" id="title" name="title" class="form-input w-full p-4 border rounded-lg" required maxlength="255">
                    </div>
                    <div class="mb-4">
                        <label for="content" class="block text-gray-700 font-medium mb-2">Content</label>
                        <textarea id="content" name="content" class="form-textarea w-full p-4 border rounded-lg" rows="10" required></textarea>
                    </div>
                    <button type="submit" class="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition">Publish Post</button>
                </form>
            </section>
        </c:if>
        <c:if test="${empty user}">
            <section class="bg-white p-8 rounded-lg shadow-lg max-w-3xl mx-auto">
                <h1 class="text-3xl font-bold text-gray-800 mb-4">Access Denied</h1>
                <p class="text-gray-600">Please <a href="/login" class="text-blue-600 hover:underline">log in</a> to create a post.</p>
            </section>
        </c:if>
    </main>

    <!-- Footer -->
    <footer class="bg-gray-800 text-white py-6">
        <div class="container mx-auto px-4 text-center">
            <p>© 2025 JavaBlog. All rights reserved.</p>
            <div class="mt-2">
                <a href="/terms" class="text-gray-300 hover:text-white mx-2">Terms</a>
                <a href="/privacy" class="text-gray-300 hover:text-white mx-2">Privacy</a>
                <a href="/contact" class="text-gray-300 hover:text-white mx-2">Contact</a>
            </div>
        </div>


 <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/7.9.1/tinymce.min.js" integrity="sha512-09JpfVm/UE1F4k8kcVUooRJAxVMSfw/NIslGlWE/FGXb2uRO1Nt4BXAJ3LxPqNbO3Hccdu46qaBPp9wVpWAVhA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script>
    tinymce.init({ selector:'#content', plugins:'link image', toolbar:'undo redo | bold italic | alignleft aligncenter image' });
    </script>
</footer>
</body>
</html>
```