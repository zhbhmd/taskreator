<template>
  <nav class="bg-gray-800 p-4 text-white">
    <div class="container mx-auto">
      <!-- Navbar content goes here -->
      <div class="flex justify-between items-center">
        <div class="text-2xl font-semibold font-">
          Taskreator
        </div>
        <div class="flex justify-between items-center">
          <NuxtLink class="mr-8" to="/">
            Tasks
          </NuxtLink>
          <NuxtLink class="mr-8" to="/create">
            Create
          </NuxtLink>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'NavigationBar'
}
</script>
