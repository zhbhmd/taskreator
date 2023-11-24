<template>
  <div>Create</div>
</template>

<script>
export default {
  name: 'CreatePage'
}
</script>
<template>
  <div class="w-full items-center p-6">
      <div class="bg-white">
          <div class="flex justify-center">
              <span class="text-lg font-bold">Create Task</span>
          </div>
          <form>
              <div class="mb-2">
                  <label class="block mb-2">Title</label>
                  <input v-model="task.title" type="text" class="form-input border border-gray-300 px-4 py-3 block  w-full">
              </div>
              <div class="mb-2">
                  <label class="block mb-2">Description</label>
                  <textarea  type="text" v-model="task.description" class="form-textarea border border-gray-300 px-4 py-3 block w-full h-32"></textarea>
              </div>
              <div class="flex row justify-between mb-2">
                  <div class="w-1/2 mr-4">
                      <label class="block mb-2">Date</label>
                      <input type="date" v-model="task.date" class="form-input border border-gray-300 px-4 py-3 block w-full ">
                  </div>
                  <div class="w-1/2">
                      <label class="block mb-2">Time</label>
                      <input type="time" v-model="task.time" class="form-input border border-gray-300 px-4 py-3 block w-full">
                  </div>
              </div>

          </form>
          <div class="flex row justify-center mt-8">
              <button @click="handlePost" class="ml-2 bg-blue-900 text-white px-20 py-4 rounded">Add </button>
          </div>
          <div v-if="error !== ''">
            <p class="text-orange-700">*{{ this.error }}</p>
          </div>
      </div>
  </div>
</template>

<script>
export default {

  data() {
      return {
          task: {
              title: '',
              description: '',
              status: 'TO_DO',
              time: '',
              date:''
          },
          error: ''
      };
  },

  methods: {
      validateForm(){
        if(this.task.title ==='')
        {
            this.error = 'Title is required'
            return true;
        }

        return false;
      },

      handlePost() {

        if(this.validateForm())
            return;

        this.$store.commit('setLoading', true)
          this.$axios.post('http://localhost:8080/tasks', this.task)
              .then(response => {

                  console.log('API Response:', response.data);
                  this.$router.push('/')

              })
              .then(() => new Promise(resolve => setTimeout(resolve, 1000))) //simulate network delay
              .catch(error => {
                  console.error('API Error:', error);

              }).
              finally(()=>{
                this.$store.commit('setLoading', false)
              });
      },
  },

  beforeCreate () {
    this.$store.commit('setLoading', true)
  },
  mounted () {
    this.$store.commit('setLoading', false)
  }
}
</script>
