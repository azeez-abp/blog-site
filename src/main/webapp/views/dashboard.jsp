<%@ include file="header.jsp" %>
<body class="bg-gray-100">
    <!-- Header -->
     <%@ include file="nav.jsp" %>
 
    <!-- Main Content -->
    <main class="container mx-auto px-4 py-8 flex flex-col md:flex-row gap-8">
        <!-- Blog Post Content -->
        <section class="md:w-2/3 bg-white p-6 rounded-lg shadow">
            <h1 class="text-3xl font-bold text-gray-800 mb-4">The Future of Java: What's Next for the Language</h1>
            <div class="flex items-center text-gray-500 mb-4">
                <span>By John Doe</span>
                <span class="mx-2">•</span>
                <span>June 29, 2025</span>
                <span class="mx-2">•</span>
                <span>5 min read</span>
            </div>
            <img src="https://via.placeholder.com/800x400" alt="Blog post image" class="blog-img mb-6 rounded-lg">
            <div class="prose max-w-none">
                <p class="text-gray-700 mb-4">Java has been a cornerstone of software development for decades, powering everything from enterprise applications to mobile apps. But what does the future hold for this versatile language? In this post, we explore the upcoming features in Java, its evolving ecosystem, and how it continues to stay relevant in a rapidly changing tech landscape.</p>
                <h2 class="text-2xl font-semibold text-gray-800 mt-6 mb-4">Project Loom: Revolutionizing Concurrency</h2>
                <p class="text-gray-700 mb-4">Project Loom aims to make concurrency in Java more efficient with virtual threads. This feature promises to simplify writing scalable applications, reducing the complexity of managing threads manually.</p>
                <h2 class="text-2xl font-semibold text-gray-800 mt-6 mb-4">Enhanced Pattern Matching</h2>
                <p class="text-gray-700 mb-4">Java's pattern matching capabilities are getting a major upgrade, making code more concise and readable. Features like record patterns and switch expressions are set to streamline development.</p>
                <p class="text-gray-700 mb-4">As Java continues to evolve, its focus on performance, security, and developer productivity ensures it remains a top choice for developers worldwide.</p>
            </div>
            <!-- Like Button -->
            <div class="mt-6 flex items-center">
                <button id="likeButton" class="flex items-center bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 transition">
                    <i class="fas fa-heart mr-2"></i>
                    <span id="likeCount">0</span> Likes
                </button>
            </div>
            <!-- Comment Section -->
            <div class="mt-8">
                <h3 class="text-xl font-semibold text-gray-800 mb-4">Comments</h3>
                <form id="commentForm" class="mb-6">
                    <textarea id="commentInput" class="w-full p-4 border rounded-lg" rows="4" placeholder="Add a comment..."></textarea>
                    <button type="submit" class="mt-2 bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 transition">Post Comment</button>
                </form>
                <div id="commentList" class="space-y-4"></div>
            </div>
        </section>

        <!-- Sidebar -->
        <aside class="md:w-1/3 space-y-6">
            <!-- Author Info -->
            <div class="bg-white p-6 rounded-lg shadow">
                <h3 class="text-xl font-semibold text-gray-800 mb-4">About the Author</h3>
                <div class="flex items-center mb-4">
                    <img src="https://via.placeholder.com/80" alt="Author avatar" class="w-16 h-16 rounded-full mr-4">
                    <div>
                        <h4 class="text-lg font-semibold text-gray-800">John Doe</h4>
                        <p class="text-gray-600">Senior Java Developer with a passion for sharing knowledge and building scalable systems.</p>
                    </div>
                </divბ

                <div class="mt-4">
                    <a href="/author/johndoe" class="text-blue-600 hover:underline">View Profile</a>
                </div>
            </div>
            <!-- Recent Posts -->
            <div class="bg-white p-6 rounded-lg shadow">
                <h3 class="text-xl font-semibold text-gray-800 mb-4">Recent Posts</h3>
                <ul class="space-y-4">
                    <li>
                        <a href="#" class="text-blue-600 hover:underline">Java 21: New Features and Updates</a>
                        <p class="text-sm text-gray-600 truncate-text">A deep dive into the latest features introduced in Java 21, including virtual threads and enhanced pattern matching.</p>
                    </li>
                    <li>
                        <a href="#" class="text-blue-600 hover:underline">Building Scalable Java Applications</a>
                        <p class="text-sm text-gray-600 truncate-text">Learn how to design and build scalable Java applications using modern frameworks and tools.</p>
                    </li>
                    <li>
                        <a href="#" class="text-blue-600 hover:underline">Introduction to Spring Boot</a>
                        <p class="text-sm text-gray-600 truncate-text">A beginner's guide to creating web applications with Spring Boot, Java's leading framework.</p>
                    </li>
                </ul>
            </div>
        </aside>
    </main>

    <!-- Footer -->
    <%@ include file="footer.jsp" %>

    <script>
        // Like Button Functionality
        const likeButton = document.getElementById('likeButton');
        const likeCount = document.getElementById('likeCount');
        let likes = 0;

        likeButton.addEventListener('click', () => {
            likes++;
            likeCount.textContent = likes;
            likeButton.classList.add('bg-red-600', 'hover:bg-red-700');
            likeButton.classList.remove('bg-blue-600', 'hover:bg-blue-700');
        });

        // Comment Form Functionality
        const commentForm = document.getElementById('commentForm');
        const commentInput = document.getElementById('commentInput');
        const commentList = document.getElementById('commentList');

        commentForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const commentText = commentInput.value.trim();
            if (commentText) {
                const commentDiv = document.createElement('div');
                commentDiv.className = 'border-t pt-4';
                commentDiv.innerHTML = `
                    <p class="text-gray-700"><strong>Anonymous</strong> <span class="text-gray-500 text-sm">• Just now</span></p>
                    <p class="text-gray-600">${commentText}</p>
                `;
                commentList.appendChild(commentDiv);
                commentInput.value = '';
            }
        });
    </script>
</body>
</html>