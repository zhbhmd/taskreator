<template>
  <div>
    <div class="flex justify-between border-b border-gray-200">
      <!-- Tab items -->
      <div
        v-for="(tab, index) in tabs"
        :key="index"
        :class="{ 'bg-blue-500 text-white': activeTab === index }"
        class="flex-1 cursor-pointer py-2 px-4 hover:bg-blue-300"
        @click="activeTab = index"
      >
        {{ tab }}
      </div>
    </div>

    <!-- Tab content -->
    <div class="p-4">
      <!-- Use named slots for each tab content -->
      <slot :tab="activeTab" ></slot>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      activeTab: 0,
      tabs: ['To Do', 'Done'] // Add as many tabs as needed
    }
  }
}
</script>
