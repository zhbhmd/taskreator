<template>
  <div>
    <div class="flex justify-between border-b border-gray-200">
      <div
        :class="{ 'bg-blue-500 text-white': activeTab === 0 }"
        class="flex-1 cursor-pointer py-2 px-4 hover:bg-blue-300"
        @click="activeTab = 0"
      >
        To Do
      </div>
      <div
        :class="{ 'bg-blue-500 text-white': activeTab === 1 }"
        class="flex-1 cursor-pointer py-2 px-4 hover:bg-blue-300"
        @click="activeTab = 1"
      >
        Done
      </div>
    </div>

    <div class="">
      <div v-show="activeTab === 0">
        <TaskCard v-for="item in todoTasks" :key="item.id" :data="item" @checked="handleCheck" />
      </div>

      <div v-show="activeTab === 1">
        <TaskCard v-for="item in completeTasks" :key="item.id" :data="item" @checked="handleCheck" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      activeTab: 0,
      tasks: []
    }
  },

  computed: {
    todoTasks () {
      return this.tasks.filter(t => t.status === 'TO_DO')
    },

    completeTasks () {
      return this.tasks.filter(t => t.status === 'DONE')
    }
  },

  mounted () {
    // Fetch initial data when the component is mounted
    this.fetchData()
  },
  methods: {
    handleCheck (data) {
      console.log(data)
      this.$axios.put('http://localhost:8080/tasks/markDone/' + data, {})
        .then((response) => {
          console.log('API Response:', response.data)
          // Handle the response as needed
          this.fetchData()
        })
        .catch((error) => {
          console.error('API Error:', error)
          // Handle errors
        })
    },
    fetchData () {
      // Make a GET request to fetch updated data
      this.$axios.get('http://localhost:8080/tasks')
        .then((response) => {
          console.log('Fetched Data:', response.data)
          this.tasks = response.data
        })
        .catch((error) => {
          console.error('Fetch Error:', error)
          // Handle errors
        })
    }
  }
}
</script>
