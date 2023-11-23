<template>
  <ListingTab>
    <!-- Tab 1 Content -->
    <div v-show="activeTab === 0">
      <h2 class="text-2xl font-semibold">
        To Do
      </h2>
      <TaskCard v-for="item in todoTasks" :key="item.id" :data="item" @checked="handleCheck" />
    </div>
    <!-- Tab 2 Content -->
    <div v-show="activeTab === 0">
      <h2 class="text-2xl font-semibold">
        Done
      </h2>
      <TaskCard v-for="item in completeTasks" :key="item.id" :data="item" @checked="handleCheck" />
    </div>
  </ListingTab>
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
